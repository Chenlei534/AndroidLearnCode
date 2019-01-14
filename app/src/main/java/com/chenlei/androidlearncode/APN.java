package com.chenlei.androidlearncode;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Create by chenlei on 2018-12-14
 */
public class APN {
    private Uri APN_URI=Uri.parse("content://telephony/carriers");
    private Uri CURRENT_APN_URI=Uri.parse("content://telephony/carriers/preferapn");
    private Context mContext;
    public static boolean hasAPN;
    private static int cmiot_ID,apn_1_ID,apn_2_ID,apn_3_ID;

    public APN(Context context) {
        this.mContext=context;
    }

    // 新增一个cmnet接入点
    public void APN(){
//        checkAPN();
    }
    public int addAPN() {
        int id = -1;
        String NUMERIC = getSIMInfo();
        if (NUMERIC == null) {
            return -1;
        }
        ContentResolver resolver = mContext.getContentResolver();
        ContentValues values = new ContentValues();

//        SIMCardInfo siminfo = new SIMCardInfo(MainActivity.this);
        // String user = siminfo.getNativePhoneNumber().substring(start);

        values.put("name", "cmiot");                                  //apn中文描述
        values.put("apn", "cmiot");                                     //apn名称
        values.put("type", "default");                            //apn类型
        values.put("numeric", NUMERIC);
        values.put("mcc", NUMERIC.substring(0, 3));
        values.put("mnc", NUMERIC.substring(3, NUMERIC.length()));
        values.put("proxy", "");                                        //代理
        values.put("port", "");                                         //端口
        values.put("mmsproxy", "");                                     //彩信代理
        values.put("mmsport", "");                                      //彩信端口
        values.put("user", "");                                         //用户名
        values.put("server", "");                                       //服务器
        values.put("password", "");                                     //密码
        values.put("mmsc", "");                                          //MMSC

        Cursor c = null;
        Uri newRow = resolver.insert(APN_URI, values);
        if (newRow != null) {
            c = resolver.query(newRow, null, null, null, null);
            int idIndex = c.getColumnIndex("_id");
            c.moveToFirst();
            id = c.getShort(idIndex);
        }
        if (c != null)
            c.close();
        return id;
    }
    public int addAPN(String name,String apn) {
        int id = -1;
        String NUMERIC = getSIMInfo();
        if (NUMERIC == null) {
            return -1;
        }

        ContentResolver resolver = mContext.getContentResolver();
        ContentValues values = new ContentValues();

//        SIMCardInfo siminfo = new SIMCardInfo(MainActivity.this);
        // String user = siminfo.getNativePhoneNumber().substring(start);

        values.put("name", name);                                  //apn中文描述
        values.put("apn", apn);                                     //apn名称
        values.put("type", "default");                            //apn类型
        values.put("numeric", NUMERIC);
        values.put("mcc", NUMERIC.substring(0, 3));
        values.put("mnc", NUMERIC.substring(3, NUMERIC.length()));
        values.put("proxy", "");                                        //代理
        values.put("port", "");                                         //端口
        values.put("mmsproxy", "");                                     //彩信代理
        values.put("mmsport", "");                                      //彩信端口
        values.put("user", "");                                         //用户名
        values.put("server", "");                                       //服务器
        values.put("password", "");                                     //密码
        values.put("mmsc", "");                                          //MMSC
        Cursor c = null;
        Uri newRow = resolver.insert(APN_URI, values);
        if (newRow != null) {
            c = resolver.query(newRow, null, null, null, null);
            int idIndex = c.getColumnIndex("_id");
            c.moveToFirst();
            id = c.getShort(idIndex);
        }
        if (c != null)
            c.close();
        return id;
    }
    protected String getSIMInfo() {
        TelephonyManager iPhoneManager = (TelephonyManager) mContext
                .getSystemService(Context.TELEPHONY_SERVICE);
        Log.e("abc", "getSIMInfo:"+iPhoneManager.getSimOperator());
        return iPhoneManager.getSimOperator();
    }

    // 设置接入点
    public void SetAPN(int id) {
        ContentResolver resolver = mContext.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("apn_id", id);
        resolver.update(CURRENT_APN_URI, values, null, null);
        // resolver.delete(url, where, selectionArgs)
    }
    public void checkAPN(String apn) {
        // 检查当前连接的APN
        Cursor cr = mContext.getContentResolver().query(APN_URI, null, null, null, null);

        Log.e("abc", "cr" + cr);
        while (cr != null && cr.moveToNext()) {
            if(cr.getString(cr.getColumnIndex("apn")).equals(apn)){
                APN.hasAPN=true;
                Log.e("APN.hasAPN在checkAPN（）中", ": "+APN.hasAPN);
                break;
            }
        }
        cr.close();
    }
    public void checkAPN() {
        // 检查当前连接的APN
        Cursor cr = mContext.getContentResolver().query(APN_URI, null, null, null, null);

        Log.e("abc", "cr" + cr);
        while (cr != null && cr.moveToNext()) {
            APN.hasAPN=true;
            Log.e("APN.hasAPN在checkAPN（）中", ": "+APN.hasAPN);

        }
        cr.close();
    }
}
