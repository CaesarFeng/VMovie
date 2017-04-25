package com.jb.caesarfeng.vmovie;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import path.URL_path;

public class MuhouSecondActivity extends AppCompatActivity {

    private TextView mTextView;
    private WebView mWebView;
    private ImageView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_video_second);

        //沉浸式通知栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFab();
        initView();

        Intent intent = getIntent();
        String postid = intent.getStringExtra("postid");
        String url = URL_path.MUHOU_WEB_PATH.replace("postid", postid);
        Log.d("flag", "------------>  : url = " + url);

        initWebView(url);
        mTextView.setText("幕后文章");
        initBack();

    }

    private void initBack() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWebView(String url) {
        Log.d("flag", "------------> initWebView: url"+url);
        //点击页面中链接，直接在本页面进行跳转
        mWebView.setWebViewClient(new WebViewClient());

        //是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
        mWebView.setWebChromeClient(new WebChromeClient());

        //设置支持js脚本语言
        mWebView.getSettings().setJavaScriptEnabled(true);

        // 开启 DOM storage API 功能
        mWebView.getSettings().setDomStorageEnabled(true);

        //开启 database storage API 功能
        mWebView.getSettings().setDatabaseEnabled(true);

        //开启 Application Caches 功能
        mWebView.getSettings().setAppCacheEnabled(true);
        Log.d("flag","------------->"+url);

        mWebView.loadUrl(url);
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.title_novideo_second);
        mWebView = (WebView) findViewById(R.id.webView_NoVideo_second);
        mBack = (ImageView) findViewById(R.id.back_novideo_second_actionBar);
    }

    private void initFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                View customLayout = getLayoutInflater().inflate(R.layout.fenxiang_diolog, (ViewGroup) findViewById(R.id.customDialog));
                final AlertDialog.Builder builder = new AlertDialog.Builder(MuhouSecondActivity.this);
                builder.setView(customLayout).show();
            }
        });
    }

}
