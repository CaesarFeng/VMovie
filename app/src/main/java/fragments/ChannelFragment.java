package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.jb.caesarfeng.vmovie.Channel2Activity;
import com.jb.caesarfeng.vmovie.R;

import java.util.ArrayList;
import java.util.List;

import adapters.HomeChannelAdapter;
import bean.HomeChannel;
import path.URL_path;
import task.ChannelDataTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment {

    private View mView;
    private GridView mGridView;

    private HomeChannel mHomeChannel;
    private List<HomeChannel.DataBean> mDataList = new ArrayList<>();

    private HomeChannelAdapter mAdapter;

    public ChannelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_channel, container, false);
       
        initView();
        initGvData();
        initGv();
        return mView;
    }

    private void initGv() {
        mAdapter = new HomeChannelAdapter(getContext(), mDataList);

        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();

                intent.setClass(getContext(), Channel2Activity.class);
                intent.putExtra("title",mDataList.get(position).getCatename());
                Log.d("flag", "------------> ChannelFragment: getCatename " + mDataList.get(position).getCatename());
                intent.putExtra("cateid",mDataList.get(position).getCateid());

                getActivity().startActivity(intent);
            }
        });
    }

    private void initGvData() {
        new ChannelDataTask(new ChannelDataTask.OnChannelDataCallback() {
            @Override
            public void callback(HomeChannel homeChannel) {
//                mHomeChannel=homeChannel;                mHomeChannel.setData(homeChannel.getData());
                Log.d("flag", "------------> callback:homeChannel.getData().size() = "+homeChannel.getData().size());
                mDataList.addAll(homeChannel.getData());
                mAdapter.notifyDataSetChanged();
            }
        }).execute(URL_path.HOME_CHANNEL_PATH);
    }

    private void  initView() {
        mGridView = (GridView) mView.findViewById(R.id.gv_ChannelFragment);
    }

}
