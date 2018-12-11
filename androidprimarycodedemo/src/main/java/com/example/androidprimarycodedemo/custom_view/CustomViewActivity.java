package com.example.androidprimarycodedemo.custom_view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.andriodprimarycodedemo.R;

/**
 * 用于测试自定义View的Activity
 */
public class CustomViewActivity extends AppCompatActivity {
    private CustomBasicShapeView customBasicShapeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

//        customBasicShapeView=findViewById(R.id.custom_basic_shape_view);
    }
}
