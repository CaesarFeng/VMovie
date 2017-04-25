package task;

import android.os.AsyncTask;

import com.google.gson.Gson;

import bean.HomeNew;
import utils.HttpUtils;

/**
 * Created by CaesarFeng on 2017/4/15.
 */

public class NewDataTask extends AsyncTask<String, Void, HomeNew>{

    private OnNewDataCallback mNewDataCallback;

    public NewDataTask(OnNewDataCallback newDataCallback) {
        mNewDataCallback = newDataCallback;
    }

    @Override
    protected HomeNew doInBackground(String... params) {

        String newStr = HttpUtils.getStringResult(params[0]);
        Gson gson = new Gson();
        HomeNew homeNew = gson.fromJson(newStr, HomeNew.class);

        return homeNew;
    }

    @Override
    protected void onPostExecute(HomeNew homeNew) {
        mNewDataCallback.callback(homeNew);
    }

    public interface OnNewDataCallback{
        void callback(HomeNew homeNew);
    }
}
