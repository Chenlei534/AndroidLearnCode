package com.example.androidprimarycodedemo.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
//        animationCodeMethod();
    }

    /**
     * xml中使用View动画的使用
     */
    private void animationXMLMethod(){
        Animation animation_translate= AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        Animation animation_rotate= AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        Animation animation_scale= AnimationUtils.loadAnimation(this,R.anim.anim_scale);
        Animation animation_alpha= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        Animation animation_set=AnimationUtils.loadAnimation(this,R.anim.anim_set);

//        animBtn.startAnimation(animation_translate);
//        animBtn.startAnimation(animation_rotate);
//        animBtn.startAnimation(animation_scale);
//        animBtn.startAnimation(animation_alpha);
        animBtn.startAnimation(animation_set);
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

    /**
     * 属性动画 xml实现
     */
    private void objectAnimatorXMLMethod(){
        AnimatorSet animatorSet= (AnimatorSet)AnimatorInflater.loadAnimator(this,R.animator.animator_demo);
        animatorSet.setTarget(animBtn);
        animatorSet.start();
    }

    /**
     * 属性动画 代码实现
     */
    private void objectAnimatorCodeMethod(){
        ObjectAnimator translationAnimator=ObjectAnimator.ofFloat(animBtn,"translationX",300);
        translationAnimator.start();

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(animBtn,"rotationX",0,360),
                ObjectAnimator.ofFloat(animBtn,"rotationY",0,180),
                ObjectAnimator.ofFloat(animBtn,"rotation",0,-90),
                ObjectAnimator.ofFloat(animBtn,"translationX",0,90),
                ObjectAnimator.ofFloat(animBtn,"translationY",0,90),
                ObjectAnimator.ofFloat(animBtn,"scaleX",1,1.5f),
                ObjectAnimator.ofFloat(animBtn,"scaleY",1,0.5f),
                ObjectAnimator.ofFloat(animBtn,"alpha",1,0.25f,1)
        );
        animatorSet.start();
    }
}
