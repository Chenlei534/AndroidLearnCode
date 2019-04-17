package com.example.androidprimarycodedemo.other;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 封装DiskLruCache实现硬盘缓存
 */
public class DiskLruCacheUtil {
    private DiskLruCacheUtil instance;
    private Context mContext;
    private DiskLruCache mDiskLruCache=null;
    private final String fileName="bitmap";
    private long cacheSize=10 * 1024 * 1024;
    private boolean setCacheIsSuccess=false;
    private boolean getCacheIsSuccess=false;

    private DiskLruCacheUtil(Context context){
        this.mContext=context;
    }

    public DiskLruCacheUtil getInstance(Context context){
        if(instance==null){
            synchronized (DiskLruCacheUtil.this){
                if(instance==null){
                    instance=new DiskLruCacheUtil(context);
                }
            }
        }
        return instance;
    }

    /**
     * 打开硬盘缓存
     */
    public void openCache(){
        try {
            File cacheDir = getDiskCacheDir(mContext, fileName);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            //第三个参数为每个key值对应的文件数
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(mContext), 1, cacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean setCache(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String key = hashKeyForDisk(url);
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (downloadUrlToStream(url, outputStream)) {
                            editor.commit();
                            setCacheIsSuccess=true;
                        } else {
                            editor.abort();
                            setCacheIsSuccess=false;
                        }
                    }
                    mDiskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return setCacheIsSuccess;
    }

    /**
     * 得到缓存文件
     * @param context
     * @param uniqueName 文件名
     * @return
     */
    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 读取缓存数据流
     * @param url
     * @return
     */
    public InputStream getCache(String url){
        InputStream is=null;
        try {
            String key = hashKeyForDisk(url);
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                is = snapShot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
            }else{
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 删除缓存
     * @param url
     */
    public void deleteCache(String url){
        try {
            String key = hashKeyForDisk(url);
            mDiskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭缓存
     */
    public void closeCache(){
        try {
            mDiskLruCache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到包名
     * @param context
     * @return
     */
    private int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 通过url将资源下载到本地
     * @param urlString
     * @param outputStream
     * @return
     */
    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 对字符串进行md5编码
     * @param key
     * @return
     */
    private String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
