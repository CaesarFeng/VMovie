package task;

import android.os.AsyncTask;

import com.google.gson.Gson;

import bean.MuhouTabBean;
import utils.HttpUtils;

/**
 * Created by CaesarFeng on 2017/4/17.
 */

public class MuhouTabDataTask extends AsyncTask<String, Void, MuhouTabBean>{

    private OnMuhouTabDataCallback mMuhouTabDataCallback;

    public MuhouTabDataTask(OnMuhouTabDataCallback muhouTabDataCallback) {
        mMuhouTabDataCallback = muhouTabDataCallback;
    }

    @Override
    protected MuhouTabBean doInBackground(String... params) {
        String tabStr = HttpUtils.getStringResult(params[0]);
        Gson gson = new Gson();
        MuhouTabBean muhouTabBean = gson.fromJson(tabStr, MuhouTabBean.class);

        return muhouTabBean;
    }

    @Override
    protected void onPostExecute(MuhouTabBean muhouTabBean) {
        mMuhouTabDataCallback.callback(muhouTabBean);
    }

    public interface OnMuhouTabDataCallback{
        void callback(MuhouTabBean muhouTabBean);
    }
}
