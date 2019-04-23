package com.example.androidprimarycodedemo.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.example.andriodprimarycodedemo.R;

public class AnimationActivity extends AppCompatActivity {
    private Button animBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        animBtn=findViewById(R.id.animation_button);
//        animationXMLMethod();
        animationCodeMethod();
    }

    /**
     * xml中使用View动画的使用
     */
    private void animationXMLMethod(){
        Animation animation_translate= AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        Animation animation_rotate= AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        Animation animation_scale= AnimationUtils.loadAnimation(this,R.anim.anim_scale);
        Animation animation_alpha= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);

        animBtn.startAnimation(animation_translate);
//        animBtn.startAnimation(animation_rotate);
//        animBtn.startAnimation(animation_scale);
//        animBtn.startAnimation(animation_alpha);
    }

    /**
     * 代码中使用View动画
     */
    private void animationCodeMethod(){
//        TranslateAnimation translateAnimation=new TranslateAnimation(-300,-300,300,300);
//        translateAnimation.setDuration(1000);   //持续时间
////        translateAnimation.setRepeatMode(Animation.RESTART);
////        translateAnimation.setRepeatCount(-1);
////        translateAnimation.setInterpolator(new LinearInterpolator());   //时间插值器
//        translateAnimation.setAnimationListener(new Animation.AnimationListener() { //动画监听
//            @Override
//            public void onAnimationStart(Animation animation) {
//                Log.e("animation","开始");
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Log.e("animation","结束");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                Log.e("animation","重复");
//            }
//        });

//        animBtn.startAnimation(translateAnimation);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(3000);

        animBtn.startAnimation(alphaAnimation);
    }
}
