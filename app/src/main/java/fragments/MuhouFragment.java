package fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.jb.caesarfeng.vmovie.R;

import java.util.ArrayList;
import java.util.List;

import adapters.MuhouPagerAdapter;
import bean.MuhouTabBean;
import fragments.muhouFragments.AllFragment;
import fragments.muhouFragments.EquipmentFragment;
import fragments.muhouFragments.FilmFunnyFragment;
import fragments.muhouFragments.FilmStudyRoomFragment;
import fragments.muhouFragments.LaterFragment;
import fragments.muhouFragments.ShootFragment;
import fragments.muhouFragments.SummarizeFragment;
import fragments.muhouFragments.VRFragment;
import path.URL_path;
import task.MuhouTabDataTask;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * A simple {@link Fragment} subclass.
 */
public class MuhouFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ProgressBar mEmptyBar;

    List<Fragment> mFragments = new ArrayList<>();
    List<MuhouTabBean.DataBean> mTabList = new ArrayList<>();

    private FragmentPagerAdapter mPagerAdapter;
    private BaseAdapter mAdapter;
    private View mView;

    public MuhouFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_muhou, container, false);

        initView();
        initTab();
        initTabData();
        initPager();


        return mView;
    }


    private void initFragmentsData() {
        mFragments.add(new AllFragment(mTabList));
        mFragments.add(new FilmStudyRoomFragment(mTabList));
        mFragments.add(new FilmFunnyFragment(mTabList));
        mFragments.add(new ShootFragment(mTabList));
        mFragments.add(new EquipmentFragment(mTabList));
        mFragments.add(new VRFragment(mTabList));
        mFragments.add(new LaterFragment(mTabList));
        mFragments.add(new SummarizeFragment(mTabList));
    }

    private void initTabData() {
        new MuhouTabDataTask(new MuhouTabDataTask.OnMuhouTabDataCallback() {
            @Override
            public void callback(MuhouTabBean muhouTabBean) {
                mTabList.addAll(muhouTabBean.getData());
                initFragmentsData();
                mEmptyBar.setVisibility(View.GONE);
                mPagerAdapter.notifyDataSetChanged();
            }
        }).execute(URL_path.MUHOU_TAB_PATH);
    }

    private void initPager() {
        mPagerAdapter = new MuhouPagerAdapter(getActivity().getSupportFragmentManager(),getContext(),mFragments,mTabList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    private void initTab() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mTabLayout = (TabLayout) mView.findViewById(R.id.tabLayout_muhou);
        mViewPager = (ViewPager) mView.findViewById(R.id.viewPager_muhou);
        mEmptyBar = (ProgressBar) mView.findViewById(R.id.empty);
    }


}
