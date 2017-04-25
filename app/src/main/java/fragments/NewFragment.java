package fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.jb.caesarfeng.vmovie.FilmSecondActivity;
import com.jb.caesarfeng.vmovie.MainActivity;
import com.jb.caesarfeng.vmovie.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapters.HeaderAdapter;
import adapters.HomeNewLvAdapter;
import bean.HomeNew;
import bean.HomeNewHeader;
import path.URL_path;
import task.NewDataTask;
import task.NewHeaderDataTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {

    private ViewPager mHeadViewPager;
    private ListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RelativeLayout mHeaderViewLayout;
    private RadioGroup mHeaderRadioGroup;
    private TextSwitcher mTextSwitcher;

    private List<Bitmap> mBitmaps = new ArrayList<>();
    private List<HomeNewHeader.DataBean> mHeaderDatas = new ArrayList<>();
    private HomeNew mHomeNew = new HomeNew();
    private List<HomeNew.DataBean> mDataList = new ArrayList<>();

    private PagerAdapter mHeaderAdapter;
    private HomeNewLvAdapter mNewAdapter;
    private int position = 0;

    public void setTextSwitcher(TextSwitcher textSwitcher) {
        mTextSwitcher = textSwitcher;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (mHandler.hasMessages(1))
                mHandler.removeMessages(1);
            switch (msg.what){
                case 1:

                    position++;

                    mHeadViewPager.setCurrentItem(position,true);
                    mHandler.sendEmptyMessageDelayed(1,5000);

                    break;
                case 2:
                    if (mHandler.hasMessages(1))
                        mHandler.removeMessages(1);
                    break;
            }

            return false;
        }
    });
    private View mView;

    public NewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_new, container, false);

        initView();
        initLv();
        initHeaderData();
        initLvData();
        initHeaderPointer();
//        initSwitcher();

        if (!mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(true);
        }
        initSwipe();
        return mView;
    }
//    private void initSwitcher() {
//        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
//            @Override
//            public View makeView() {
//                TextView textView = new TextView(getActivity());
//                textView.setTextColor(Color.parseColor("#ffffff"));
//                return textView;
//            }
//        });
//        Animation inAnimation = AnimationUtils.loadAnimation(getActivity(),R.anim.in_anim);
//        Animation outAnimation = AnimationUtils.loadAnimation(getActivity(),R.anim.out_anim);
//        mTextSwitcher.setInAnimation(inAnimation);
//        mTextSwitcher.setOutAnimation(outAnimation);
//
//    }
    private void initHeaderPointer() {
        mHeaderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                switch(checkedId){
//                    case R.id.rb1_home_new_header:
//                        mHeadViewPager.setCurrentItem(position%mHeaderDatas.size());
//                        break;
//                    case R.id.rb2_home_new_header:
//                        mHeadViewPager.setCurrentItem(position%mHeaderDatas.size());
//                        break;
//                    case R.id.rb3_home_new_header:
//                        mHeadViewPager.setCurrentItem(position%mHeaderDatas.size());
//                        break;
//                    case R.id.rb4_home_new_header:
//                        mHeadViewPager.setCurrentItem(position%mHeaderDatas.size());
//                        break;
//                    case R.id.rb5_home_new_header:
//                        mHeadViewPager.setCurrentItem(position%mHeaderDatas.size());
//                        break;
//                    case R.id.rb6_home_new_header:
//                        mHeadViewPager.setCurrentItem(Integer.MAX_VALUE/2+5);
//                        break;
//                    case R.id.rb7_home_new_header:
//                        mHeadViewPager.setCurrentItem(Integer.MAX_VALUE/2+6);
//                        break;
//                     default :
//
//                        break;
//
//                }
            }
        });
    }

    private void initLvData() {
        new NewDataTask(new NewDataTask.OnNewDataCallback() {
            @Override
            public void callback(HomeNew homeNew) {
                mHomeNew = homeNew;
//                Log.d("flag", "------------> callback:mHomeNew.getData().get(0).getTitle() = "+mHomeNew.getData().get(0).getTitle());
                mDataList.addAll(homeNew.getData());
//                Log.d("flag", "------------> callback:mDataList.size() = "+mDataList.size());

                mNewAdapter.notifyDataSetChanged();
                if (mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        }).execute(URL_path.HOME_NEW_PATH);
    }

    private void initSwipe() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);

                            mSwipeRefreshLayout.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (mSwipeRefreshLayout.isRefreshing())
                                        mSwipeRefreshLayout.setRefreshing(false);
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private void initView() {
        mListView = (ListView) mView.findViewById(R.id.lv_NewFragment);
        mSwipeRefreshLayout = ((SwipeRefreshLayout) mView.findViewById(R.id.swipe));
        mHeaderViewLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.home_viewpager_pointer,null,false);
        mHeadViewPager = (ViewPager) mHeaderViewLayout.findViewById(R.id.viewPager_Home);
        mHeaderRadioGroup = (RadioGroup) mHeaderViewLayout.findViewById(R.id.rg_home_new_header);
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main,null,false);
//        mTextSwitcher = ((TextSwitcher) view.findViewById(R.id.textSwitcher_main));
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.textswitcher_layout,null,false);
//        mTextSwitcher = (TextSwitcher) view.findViewById(R.id.textSwitcher);


    }

    private void initHeaderData() {
        new NewHeaderDataTask(new NewHeaderDataTask.OnHeaderDataCallback() {
            @Override
            public void Callback(HomeNewHeader headerData) {
                mHeaderDatas.addAll(headerData.getData());
                mHeaderAdapter.notifyDataSetChanged();

                mHandler.sendEmptyMessageDelayed(1,5000);
            }
        }).execute(URL_path.NEW_HEADER_PATH);

    }
    boolean isBottom = false;
    int p = 2;
    String d1 ="最新";
    private void initLv() {

        //添加头部视图
        initHeaderView();
//        mListView.addHeaderView(mHeadViewPager);
        mListView.addHeaderView(mHeaderViewLayout);

        mNewAdapter = new HomeNewLvAdapter(getContext(),mDataList);

        mListView.setAdapter(mNewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getContext(), FilmSecondActivity.class);
                intent.putExtra("postid",mDataList.get(position-1).getPostid());
                Log.d("flag", "------------> NewFragment: postid= "+mDataList.get(position).getPostid());
                getActivity().startActivity(intent);

            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                int firstVisiblePosition = mListView.getFirstVisiblePosition();
                if (firstVisiblePosition>4){
                    String publish_time = mDataList.get(firstVisiblePosition).getPublish_time();
                    List<String> timedate = timedate(publish_time);

                    String month = timedate.get(0);
                    String day = timedate.get(1);

                    if (d1.equals(day)){

                    } else {
                        d1 = day;
                        mTextSwitcher.setText(month + day);
                    }
                }else {
                    if (d1.equals("最新")){

                    } else {
                        d1 = "最新";
                        mTextSwitcher.setText("最新");
                    }
                }

                switch(scrollState){
                    case SCROLL_STATE_FLING://正在滚动

                        break;
                    case SCROLL_STATE_IDLE://视图不滚动



                        if (isBottom){

                            new NewDataTask(new NewDataTask.OnNewDataCallback() {
                                @Override
                                public void callback(HomeNew homeNew) {
                                    mDataList.addAll(homeNew.getData());

                                    mNewAdapter.notifyDataSetChanged();
                                    if (mSwipeRefreshLayout.isRefreshing()){
                                        mSwipeRefreshLayout.setRefreshing(false);
                                    }
                                }
                            }).execute(URL_path.HOME_NEW_PATH+"?p="+p);
                            p++;
                        }

//                        String day = mNewAdapter.getDay();
//                        String lastDay = mNewAdapter.getLastDay();
                        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.new_item,null,false);
                        TextView date = ((TextView) view2.findViewById(R.id.date_new_item));
                        int visibility = date.getVisibility();
                        Log.d("flag", "------------> onScrollStateChanged: visibility = "+visibility);
                        switch(visibility){
                            case View.VISIBLE:


                                break;

                             default :

                                break;

                        }

                        break;
                    case SCROLL_STATE_TOUCH_SCROLL://用户使用触摸滚动,手指仍在屏幕上

                        break;
                     default :

                        break;

                }
            }

            //判断是否在底部
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //按参数来写  在屏幕内显示的 第一条条目的下标 + 能看见的最后一条条目的下标 == 总条目数
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    isBottom = true;
                } else {
                    isBottom = false;
                }

            }

        });
    }

    public static List<String> timedate(String time) {
        SimpleDateFormat months = new SimpleDateFormat("MM");
        SimpleDateFormat days = new SimpleDateFormat("dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
//        int i = Integer.parseInt(time);
        String monthNum = months.format(new Date(lcc * 1000L));
        String month = "Zero";
        switch(monthNum){
            case "01":
                month = "Jan.";
                break;
            case "02":
                month = "Feb.";
                break;
            case "03":
                month = "Mar.";
                break;
            case "04":
                month = "Apr.";
                break;
            case "05":
                month = "May.";
                break;
            case "06":
                month = "Jun.";
                break;
            case "07":
                month = "Jul.";
                break;
            case "08":
                month = "Aug.";
                break;
            case "09":
                month = "Sep.";
                break;
            case "10":
                month = "Oct.";
                break;
            case "11":
                month = "Nov.";
                break;
            case "12":
                month = "Dec.";
                break;

            default :

                break;

        }
        List<String> dateList = new ArrayList<>();
        dateList.clear();
        String day = days.format(new Date(lcc * 1000L));
        dateList.add(month);
        dateList.add(day);
        return dateList;

    }

    private void initHeaderView() {

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels / 3);
//        mHeadViewPager.setLayoutParams(params);
        mHeaderViewLayout.setLayoutParams(params);

        mHeaderAdapter = new HeaderAdapter(mHeaderDatas, getContext());
        mHeadViewPager.setAdapter(mHeaderAdapter);
//        mHeadViewPager.setCurrentItem(71);
        mHeadViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position1) {
                position = position1;
                mHandler.sendEmptyMessageDelayed(1,5000);
                RadioButton childAt = (RadioButton) mHeaderRadioGroup.getChildAt(position1%mHeaderDatas.size());
                childAt.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch(state){
                    case ViewPager.SCROLL_STATE_DRAGGING://用户正在拖拽
                        mHandler.sendEmptyMessage(2);
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING://正在设置新的界面

                        break;
                    case ViewPager.SCROLL_STATE_IDLE://停止
                        mHandler.sendEmptyMessageDelayed(1,5000);
                        break;

                    default :

                        break;
                }
            }
        });

//        mHeadViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//            }
//        });
    }

}
