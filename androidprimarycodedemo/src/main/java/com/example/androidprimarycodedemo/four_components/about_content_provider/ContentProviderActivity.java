package com.example.androidprimarycodedemo.four_components.about_content_provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.andriodprimarycodedemo.R;
import com.orhanobut.logger.Logger;

/**
 * 用于测试ContentProvider的Activity
 */
public class ContentProviderActivity extends AppCompatActivity {
    private Button insertButton;
    private Button queryButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        insertButton=findViewById(R.id.insert_button);
        queryButton=findViewById(R.id.query_button);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryData();
            }
        });
    }
    //user表的资源路径
    private Uri uriUser=Uri.parse("content://com.chenlei.content_provider/user");
    /**
     * ContentResolver统一管理ContentProvider间的操作
     * 由于如果需要使用多个ContentProvider进行操作，需要了解各个不同ContentProvider的实现等再进行操作
     * 使用ContentResolver同一管理方便操作和使用。
     */
    private ContentResolver contentResolver=null;
    /**
     * 向ContentProvider中插入数据
     */
    private void insertData(){
        contentResolver=getContentResolver();
        //插入表中的数据
        ContentValues contentValues=new ContentValues();
        contentValues.put("_id", 3);
        contentValues.put("name", "Iverson");

        contentResolver.insert(uriUser,contentValues);
        Logger.e("插入完成");
    }

    /**
     * 从ContentProvider中查询数据
     */
    private void queryData(){
        contentResolver=getContentResolver();
        Cursor cursor=contentResolver.query(uriUser,new String[]{"_id","name"}, null, null, null);
        while (cursor.moveToNext()){
            Logger.e(cursor.getInt(0) +","+ cursor.getString(1));
        }
        cursor.close();
    }
}
