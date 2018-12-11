package com.example.androidprimarycodedemo.four_components.about_content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 本地内容提供器向其他应用程序提供本地内容的CRUD
 */
public class CreateLocalContentProvider extends ContentProvider {
    /**
     * URI(Uniform Resource Identifier)：统一资源标识符
     *    可以用来唯一标识ContentProvider和其中的数据
     * 格式如下：
     *    content://com.example.provider/User/1
     *    主题名       授权信息           表名 记录（表中的某个信息）
     *    可以使用通配符*和#
     *    *：任意长度的有效字符串
     *    #：任意长度的数字字符串
     * MIME：
     *    用于指定某个扩展名的文件用某种应用程序打开
     *    由 类型+子类型 组成
     *    1.必须以vnd开头
     *    2.内容uri以路径结尾即表名结尾后接android.cursor.dir/
     *      如果以id结尾即数据库表id则后接android.cursor.item/
     *    3.最后接上vnd.<authority>.<path>
     *    例：
     *      content://com.chenlei.content_provider.user
     *      对应MIME类型为：vnd.android.cursor.dir/vnd.com.chenlei.content_provider.user
     *      content://com.chenlei.content_provider.user/1
     *      对应MIME类型为：vnd.android.cursor.item/vnd.com.chenlei.content_provider.user
     */
    private Context mContext;
    private DBHelper mDBHelper=null;
    private SQLiteDatabase sqLiteDatabase=null;

    private static final int user_code=1;
    private static final int job_code=2;
    //ContentProvider的唯一标识
    private static final String AUTHORITY="com.chenlei.content_provider";
    /**
     * UriMatcher：
     *     在ContentProvider中注册uri
     *     根据URI匹配ContentProvider中对应的数据库表
     * UriMatcher.match(uri)
     *     根据uri返回匹配该uri的自定义代码
     */
    private static UriMatcher mUriMatcher = null;
    static {
        //初始化
        mUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        //在ContentProvider中注册uri
        mUriMatcher.addURI(AUTHORITY,"user",user_code);
        mUriMatcher.addURI(AUTHORITY,"job",job_code);
        // 若URI资源路径 = content://com.chenlei.content_provider/user ，则返回注册码user_code
        // 若URI资源路径 = content://com.chenlei.content_provider/job ，则返回注册码job_code
    }

    /**
     * 初始化ContentProvider的使用使用
     * 通常完成数据库的创建和升级操作
     * @return true初始化成功，false初始化失败
     */
    @Override
    public boolean onCreate() {
        mContext=getContext();
        mDBHelper=new DBHelper(getContext());
        sqLiteDatabase=mDBHelper.getWritableDatabase();
        //初始化数据库表
        sqLiteDatabase.execSQL("delete from user");
        sqLiteDatabase.execSQL("insert into user values(1,'Carson');");
        sqLiteDatabase.execSQL("insert into user values(2,'Kobe');");
        sqLiteDatabase.execSQL("delete from job");
        sqLiteDatabase.execSQL("insert into job values(1,'Android');");
        sqLiteDatabase.execSQL("insert into job values(2,'iOS');");

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        String tableName = getTableName(uri);
        sqLiteDatabase=mDBHelper.getReadableDatabase();
        Cursor cursor=null;

        cursor=sqLiteDatabase.query(tableName,strings,s,strings1,null,null,s1,null);

        return cursor;
    }

    /**
     * 根据传入的内容来返回相应的MIME类型
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        String mime = null;
        switch (mUriMatcher.match(uri)){
            case user_code:
                mime="vnd.android.cursor.dir/vnd.com.chenlei.content_provider.user";
                break;
            case job_code:
                mime="vnd.android.cursor.dir/vnd.com.chenlei.content_provider.job";
                break;
        }
        return mime;
    }

    /**
     * 插入数据
     * @param uri 数据的资源路径
     * @param contentValues 要插入的数据内容
     * @return 返回一个用于记录新纪录的uri
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        String tableName=getTableName(uri);
        //插入数据
        sqLiteDatabase.insert(tableName,null,contentValues);

        mContext.getContentResolver().notifyChange(uri,null);

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private String getTableName(Uri uri){
        String tableName=null;
        switch (mUriMatcher.match(uri)){
            case user_code:
                tableName=DBHelper.USER_TABLE_NAME;
                break;
            case job_code:
                tableName=DBHelper.JOB_TABLE_NAME;
                break;
        }
        return tableName;
    }
}
