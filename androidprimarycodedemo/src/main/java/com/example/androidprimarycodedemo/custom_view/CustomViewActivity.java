package com.example.androidprimarycodedemo.custom_view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.andriodprimarycodedemo.R;

/**
 * 用于测试自定义View的Activity
 */
public class CustomViewActivity extends AppCompatActivity {
    private CustomViewPath mCustomViewPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

//        mCustomViewPath=findViewById(R.id.custom_view);
    }
}
