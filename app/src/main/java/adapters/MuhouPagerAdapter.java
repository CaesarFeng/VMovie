package adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import bean.MuhouTabBean;

/**
 * Created by CaesarFeng on 2017/4/17.
 */

public class MuhouPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragments;
    private List<MuhouTabBean.DataBean> mTieles;

    public MuhouPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments, List<MuhouTabBean.DataBean> tieles) {
        super(fm);
        mContext = context;
        mFragments = fragments;
        mTieles = tieles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTieles.get(position).getCatename();
    }
}
