package task;

import android.os.AsyncTask;

import com.google.gson.Gson;

import bean.XileiBean;
import utils.HttpUtils;

/**
 * Created by CaesarFeng on 2017/4/17.
 */

public class XilieDataTask extends AsyncTask<String, Void, XileiBean>{

    private OnXilieDataCallback mXilieDataCallback;

    public XilieDataTask(OnXilieDataCallback xilieDataCallback) {
        mXilieDataCallback = xilieDataCallback;
    }

    @Override
    protected XileiBean doInBackground(String... params) {

        String xilieStr = HttpUtils.getStringResult(params[0]);
        Gson gson = new Gson();
        XileiBean xileiBean = gson.fromJson(xilieStr, XileiBean.class);

        return xileiBean;
    }

    @Override
    protected void onPostExecute(XileiBean xilieBean) {
        mXilieDataCallback.callback(xilieBean);
    }

    public interface OnXilieDataCallback{
        void callback(XileiBean xileiBean);
    }
}
