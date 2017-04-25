package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by CaesarFeng on 2017/4/18.
 */

public class GuideFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    public GuideFragmentPagerAdapter(FragmentManager manager,List<Fragment> mFragments) {
        super(manager);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
