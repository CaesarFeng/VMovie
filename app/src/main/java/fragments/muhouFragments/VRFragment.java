package fragments.muhouFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.jb.caesarfeng.vmovie.MuhouSecondActivity;
import com.jb.caesarfeng.vmovie.R;

import java.util.ArrayList;
import java.util.List;

import adapters.MuhouLvAdapter;
import bean.MuhouBean;
import bean.MuhouTabBean;
import path.URL_path;
import task.MuhouDataTask;

public class VRFragment extends Fragment {

    private List<MuhouTabBean.DataBean> mTab;
    private View mView;
    private ListView lv;
    private SwipeRefreshLayout mRefreshLayout;

    private List<MuhouBean.DataBean> mMuhouDatas = new ArrayList<>();

    private BaseAdapter mAdapter;

    public VRFragment(List<MuhouTabBean.DataBean> tab) {
        mTab = tab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_equipment, container, false);
        mRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMuhouDatas.clear();
                initData();
            }
        });

        lv = (ListView) mView.findViewById(R.id.lv_muhou);
        initData();
        initLv();

        return mView;
    }

    private void initLv() {
        mAdapter = new MuhouLvAdapter(getContext(),mMuhouDatas);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getContext(), MuhouSecondActivity.class);
                intent.putExtra("postid",mMuhouDatas.get(position).getPostid());

                getActivity().startActivity(intent);
            }
        });
    }

    private void initData() {
        new MuhouDataTask(new MuhouDataTask.OnMuhouDataCallback() {
            @Override
            public void callback(MuhouBean muhouBean) {
                Log.d("flag","muhou Callback: muhouBean = "+muhouBean);
                mMuhouDatas.addAll(muhouBean.getData());
                if (mRefreshLayout.isRefreshing()){
                    mRefreshLayout.setRefreshing(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        }).execute(URL_path.MUHOU_VIEWPAGER_PATH+mTab.get(5).getCateid());
    }





}
