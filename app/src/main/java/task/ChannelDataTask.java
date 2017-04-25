package task;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.List;

import bean.HomeChannel;
import utils.HttpUtils;

/**
 * Created by CaesarFeng on 2017/4/15.
 */

public class ChannelDataTask extends AsyncTask<String, Void, HomeChannel>{

    private OnChannelDataCallback mChannelDataCallback;

    public ChannelDataTask(OnChannelDataCallback channelDataCallback) {
        mChannelDataCallback = channelDataCallback;
    }

    @Override
    protected HomeChannel doInBackground(String... params) {
        String channelStr = HttpUtils.getStringResult(params[0]);
        Gson gson = new Gson();
        HomeChannel homeChannel = gson.fromJson(channelStr, HomeChannel.class);
        return homeChannel;
    }

    @Override
    protected void onPostExecute(HomeChannel homeChannel) {
        mChannelDataCallback.callback(homeChannel);
    }

    public interface OnChannelDataCallback{
        void callback(HomeChannel homeChannel);
    }
}
