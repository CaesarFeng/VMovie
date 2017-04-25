package com.jb.caesarfeng.vmovie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static android.R.attr.data;
import static android.R.id.edit;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initAnim();
        //沉浸式通知栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        final SharedPreferences mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor edit = mSharedPreferences.edit();
//        edit.putBoolean("isFirst",true);
        edit.apply();
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isFirst = mSharedPreferences.getBoolean("isFirst", true);

                try {
                    Thread.sleep(3000);
                    if (isFirst){
                        Intent intent = new Intent();
                        intent.setClass(WelcomeActivity.this,GuideActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(WelcomeActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void initAnim() {
        ImageView imgWelCome = (ImageView) findViewById(R.id.img_welcome);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.welcome);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        imgWelCome.setAnimation(animation);
    }

}
