package task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.HomeNewHeader;
import utils.HttpUtils;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by CaesarFeng on 2017/4/15.
 */

public class NewHeaderDataTask extends AsyncTask<String,Void,HomeNewHeader> {

    private OnHeaderDataCallback mCallback;

    public NewHeaderDataTask(OnHeaderDataCallback callback) {
        mCallback = callback;
    }

    @Override
    protected HomeNewHeader doInBackground(String... params) {

        String path = HttpUtils.getStringResult(params[0]);

        Gson gson = new Gson();
        HomeNewHeader homeNewHeader = gson.fromJson(path, HomeNewHeader.class);

//        List<Map<String,Bitmap>> mapList = new ArrayList<>();
//        Map<String,Bitmap> map = new Hashtable<>();
//        for (int i = 0; i < homeNewHeader.getData().size(); i++) {
//            String imgUrl= homeNewHeader.getData().get(i).getImage();
//            Bitmap bitmap = HttpUtils.getBitmapResult(imgUrl);
//            map.put(homeNewHeader.getData().get(i).getExtra_data().getApp_banner_param(),bitmap);
//            mapList.add(map);
//        }


        return homeNewHeader;
    }

    @Override
    protected void onPostExecute(HomeNewHeader homeNewHeader) {
        mCallback.Callback(homeNewHeader);
    }

    public interface OnHeaderDataCallback{
        void Callback(HomeNewHeader homeNewHeader);
    }
}
