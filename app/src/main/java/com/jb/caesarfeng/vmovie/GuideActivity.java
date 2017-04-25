package com.jb.caesarfeng.vmovie;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import adapters.GuideFragmentPagerAdapter;
import fragments.guide_fragments.Guide1Fragment;
import fragments.guide_fragments.Guide2Fragment;
import fragments.guide_fragments.Guide3Fragment;

public class GuideActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //沉浸式通知栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor edit = data.edit();
        edit.putBoolean("isFirst",false);
        edit.apply();
        mViewPager = (ViewPager) findViewById(R.id.viewPager_Guide);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_guide);

        mFragments.add(new Guide1Fragment());
        mFragments.add(new Guide2Fragment());
        mFragments.add(new Guide3Fragment());


        initViewPager();

        initRadioGoup();
    }

    private void initViewPager() {
        FragmentManager manager = getSupportFragmentManager();
        mAdapter = new GuideFragmentPagerAdapter(manager,mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(position);

                childAt.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initRadioGoup() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.rb1_guide:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb2_guide:
                        mViewPager.setCurrentItem(1);

                        break;
                    case R.id.rb3_guide:
                        mViewPager.setCurrentItem(2);

                        break;

                    default :

                        break;

                }
            }
        });
    }
}
