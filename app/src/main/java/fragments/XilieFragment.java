package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.jb.caesarfeng.vmovie.R;

import java.util.ArrayList;
import java.util.List;

import adapters.XilieAdapter;
import bean.XileiBean;
import path.URL_path;
import task.XilieDataTask;

import static android.view.View.X;

/**
 * A simple {@link Fragment} subclass.
 */
public class XilieFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mRefreshLayout;
    private View mView;
    private ListView lv;

    private List<XileiBean.DataBean> mDataBeen = new ArrayList<>();

    private BaseAdapter mAdapter;

    public XilieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_xilei, container, false);
        lv = (ListView) mView.findViewById(R.id.lv_xilie);
        mRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_xilie);
            mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(this);

        initData();
        mAdapter = new XilieAdapter(getContext(),mDataBeen);
        lv.setAdapter(mAdapter);

        return mView;
    }

    private void initData() {
        new XilieDataTask(new XilieDataTask.OnXilieDataCallback() {
            @Override
            public void callback(XileiBean xileiBean) {
                mDataBeen.addAll(xileiBean.getData());
                if (mRefreshLayout.isRefreshing()){
                    mRefreshLayout.setRefreshing(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        }).execute(URL_path.XILIE_PATH);
    }


    @Override
    public void onRefresh() {
        mDataBeen.clear();
        initData();
    }
}
