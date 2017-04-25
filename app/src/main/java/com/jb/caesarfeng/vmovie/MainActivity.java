package com.jb.caesarfeng.vmovie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

import adapters.HomePagerAdapter;
import fragments.ChannelFragment;
import fragments.MuhouFragment;
import fragments.NewFragment;
import fragments.XilieFragment;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mMenuImg,mSearch;
    private RelativeLayout rl;
    private AppBarLayout mBarLayout;
    private RadioGroup mRadioGroup;
    private RadioButton rb1,rb2,rb3;
    private View v1,v2,v3;
    private TextView xileiTitle,muhouTtiel;
    private FrameLayout fl;
    private TextSwitcher mTextSwitcher;


    private List<Fragment> mList = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    private ImageView mMenuCloseImg;

    private FragmentManager mManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initTab();

        initViewPager();
        initList();
        initRG();

        //沉浸式通知栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    private void initRG() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                FragmentTransaction mTransaction = mManager.beginTransaction();
                Fragment fragment = null;
                switch(checkedId){
                    case R.id.side_home:
                        v1.setVisibility(View.VISIBLE);
                        v2.setVisibility(View.INVISIBLE);
                        v3.setVisibility(View.INVISIBLE);


                        rl.setVisibility(View.GONE);

                        mBarLayout.setVisibility(View.VISIBLE);
                        mViewPager.setVisibility(View.VISIBLE);
                        mTabLayout.setVisibility(View.VISIBLE);

                        xileiTitle.setVisibility(View.GONE);
                        muhouTtiel.setVisibility(View.GONE);
                        fl.setVisibility(View.GONE);
                        break;
                    case R.id.side_series:
                        v2.setVisibility(View.VISIBLE);
                        v1.setVisibility(View.INVISIBLE);
                        v3.setVisibility(View.INVISIBLE);

                        rl.setVisibility(View.GONE);

                        mBarLayout.setVisibility(View.VISIBLE);
                        mViewPager.setVisibility(View.GONE);
                        mTabLayout.setVisibility(View.GONE);

                        xileiTitle.setVisibility(View.VISIBLE);
                        muhouTtiel.setVisibility(View.GONE);

                        fl.setVisibility(View.VISIBLE);

                        fragment = new XilieFragment();
                        mTransaction.replace(R.id.fl,fragment);
                        mTransaction.commit();

                        break;
                    case R.id.side_behind:
                        v3.setVisibility(View.VISIBLE);
                        v2.setVisibility(View.INVISIBLE);
                        v1.setVisibility(View.INVISIBLE);

                        rl.setVisibility(View.GONE);

                        mBarLayout.setVisibility(View.VISIBLE);
                        mViewPager.setVisibility(View.GONE);
                        mTabLayout.setVisibility(View.GONE);

                        xileiTitle.setVisibility(View.GONE);
                        muhouTtiel.setVisibility(View.VISIBLE);
                        fl.setVisibility(View.VISIBLE);

                        fragment = new MuhouFragment();
                        mTransaction.replace(R.id.fl,fragment);
                        mTransaction.commit();
                        break;
                     default :

                        break;

                }
            }
        });
    }

    private void initList() {
        NewFragment newFragment = new NewFragment();
        newFragment.setTextSwitcher(mTextSwitcher);
        mList.add(newFragment);
        mList.add(new ChannelFragment());
        mAdapter.notifyDataSetChanged();
    }



    private void initViewPager() {
        mAdapter = new HomePagerAdapter(getSupportFragmentManager(),mList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    private void initTab() {
        TabLayout.Tab tab = mTabLayout.newTab();

        TabLayout.LayoutParams params = new CollapsingToolbarLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        mTextSwitcher.setLayoutParams(params);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MainActivity.this);
                TextSwitcher.LayoutParams params = new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                params.gravity = Gravity.CENTER;

                textView.setLayoutParams(params);
                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setGravity(Gravity.CENTER);
                return textView;
            }

        });
        mTextSwitcher.setCurrentText("   最新  ");
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.in_anim);

        tab.setCustomView(mTextSwitcher);
        TabLayout.Tab tab1 = mTabLayout.newTab();
        tab1.setText("   频道   ");


        mTabLayout.addTab(tab);
        mTabLayout.addTab(tab1);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });




    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        fl = (FrameLayout) findViewById(R.id.fl);
//        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        View view = LayoutInflater.from(this).inflate(R.layout.textswitcher_layout,null,false);
        mTextSwitcher = (TextSwitcher) view.findViewById(R.id.textSwitcher);

        mMenuImg = (ImageView) findViewById(R.id.menu_main);
        mSearch = (ImageView) findViewById(R.id.search_main);
        mMenuCloseImg = (ImageView) findViewById(R.id.menu_close);

        rl = (RelativeLayout) findViewById(R.id.menu_rl_main);
        mBarLayout = (AppBarLayout) findViewById(R.id.appbarLayout);
        mRadioGroup = (RadioGroup) findViewById(R.id.menu_rg);
        rb1 = (RadioButton) findViewById(R.id.side_home);
        rb2 = (RadioButton) findViewById(R.id.side_series);
        rb3 = (RadioButton) findViewById(R.id.side_behind);
        v1 = findViewById(R.id.gb_view1);
        v2 = findViewById(R.id.gb_view2);
        v3 = findViewById(R.id.gb_view3);

        xileiTitle = (TextView) findViewById(R.id.title_xilei);
        muhouTtiel = (TextView) findViewById(R.id.title_muhou);


    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.menu_main:
                switch(rl.getVisibility()){
                    case View.GONE:
                        rl.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.INVISIBLE);
                        rb3.setVisibility(View.INVISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(250);
                                    rb2.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            rb2.setVisibility(View.VISIBLE);
                                        }
                                    });
                                    Thread.sleep(250);
                                    rb3.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            rb3.setVisibility(View.VISIBLE);
                                        }
                                    });
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        break;
                    case View.VISIBLE:
                        rl.setVisibility(View.GONE);
                        break;

                     default :

                        break;
                }

                Animation animation = AnimationUtils.loadAnimation(this, R.anim.close_anim);
                animation.setFillAfter(false);
                animation.setDuration(100);
                animation.setRepeatCount(2);
                animation.setRepeatMode(Animation.REVERSE);
                mMenuCloseImg.startAnimation(animation);
                mBarLayout.setVisibility(View.INVISIBLE);
                break;
            case R.id.menu_close:
                Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.close_anim_set);
                animation2.setRepeatMode(Animation.REVERSE);
                animation2.setFillAfter(true);
                mMenuCloseImg.startAnimation(animation2);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);

                            rl.post(new Runnable() {
                                @Override
                                public void run() {
                                    rl.setVisibility(View.GONE);
                                    mBarLayout.setVisibility(View.VISIBLE);
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


                break;
             default :

                break;

        }
    }

    private boolean isShuangji = false;
    @Override
    public void onBackPressed() {
        if (isShuangji){
            finish();
        }else{
            Toast.makeText(this, "再次点击返回键退出", Toast.LENGTH_SHORT).show();
            isShuangji = true;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        isShuangji = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
//            super.onBackPressed();
        }

    }
}
