package task;

import android.os.AsyncTask;

import com.google.gson.Gson;

import bean.MuhouBean;
import utils.HttpUtils;

/**
 * Created by CaesarFeng on 2017/4/17.
 */

public class MuhouDataTask extends AsyncTask<String, Void, MuhouBean>{
    private OnMuhouDataCallback mMuhouDataCallback;

    public MuhouDataTask(OnMuhouDataCallback muhouDataCallback) {
        mMuhouDataCallback = muhouDataCallback;
    }

    @Override
    protected MuhouBean doInBackground(String... params) {
        String MuhouDataStr = HttpUtils.getStringResult(params[0]);
        Gson gson = new Gson();
        MuhouBean muhouBean = gson.fromJson(MuhouDataStr, MuhouBean.class);
        return muhouBean;
    }

    @Override
    protected void onPostExecute(MuhouBean muhouBean) {
        mMuhouDataCallback.callback(muhouBean);
    }

    public interface OnMuhouDataCallback{
        void callback(MuhouBean muhouBean);
    }
}
