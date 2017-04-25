package com.jb.caesarfeng.vmovie;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.HomeNewLvAdapter;
import bean.HomeNew;
import path.URL_path;
import task.NewDataTask;

public class Channel2Activity extends AppCompatActivity {

    private ImageView mBack;
    private TextView mTextView;
    private SwipeRefreshLayout mRefreshLayout;
    private ListView mListView;

    private List<HomeNew.DataBean> mDataBeen = new ArrayList<>();
    private String mCateid;

    private BaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel2);

        //沉浸式通知栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        Log.d("flag", "------------> Channel2 : title= "+title);
        mTextView = (TextView) toolbar.findViewById(R.id.title_channel2);
        mTextView.setText(title);
        mCateid = intent.getStringExtra("cateid");

        initView();
        initBack();
        initData();
        initLv();
        initRefresh();

    }

    private void initRefresh() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mDataBeen.clear();
                initData();
            }
        });
    }

    private void initData() {
        new NewDataTask(new NewDataTask.OnNewDataCallback() {
            @Override
            public void callback(HomeNew homeNew) {
                mDataBeen.addAll(homeNew.getData());
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.setRefreshing(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        }).execute(URL_path.CHANNEL_2_PATH+mCateid);
    }

    private void initLv() {
        mAdapter = new HomeNewLvAdapter(this,mDataBeen);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(Channel2Activity.this, FilmSecondActivity.class);
                intent.putExtra("postid",mDataBeen.get(position).getPostid());
                Log.d("flag", "------------> NewFragment: postid= "+mDataBeen.get(position).getPostid());
                startActivity(intent);

            }
        });
    }

    private void initBack() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back_channel2);
//        mTextView = (TextView) findViewById(R.id.title_channel2);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_channel2);
        mListView = (ListView) findViewById(R.id.lv_channel2);
    }

}
