package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jb.caesarfeng.vmovie.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.HomeNew;

import static android.media.CamcorderProfile.get;

/**
 * Created by CaesarFeng on 2017/4/15.
 */

public class HomeNewLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeNew.DataBean> mDataList;
    private String mDay;
    private String mLastDay;
    private String mMonth;

    public String getMonth() {
        return mMonth;
    }

    public String getLastDay() {
        return mLastDay;
    }

    public String getDay() {
        return mDay;
    }

    public HomeNewLvAdapter(Context context, List<HomeNew.DataBean> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.new_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(mContext).load(mDataList.get(position).getImage()).into(holder.img);
        holder.title.setText(mDataList.get(position).getTitle());
        int i = Integer.parseInt(mDataList.get(position).getDuration());
        int miao = i>60?i%60:i;
        int fen = i>60?i/60:00;
        String timestr = mDataList.get(position).getCates().get(0).getCatename()+" / "+fen+"'"+miao+"\"";
        holder.time.setText(timestr);

        String publishTime = mDataList.get(position).getPublish_time();
        List<String> date= timedate(publishTime);
        mMonth = date.get(0);
        mDay = date.get(1);

        if (position>0){
            String lastPublishTime = mDataList.get(position-1).getPublish_time();
            List<String> lastDate= timedate(lastPublishTime);

            mLastDay = lastDate.get(1);
            if (mDay.equals(mLastDay)){
                holder.date.setVisibility(View.GONE);

            }else {
                holder.date.setVisibility(View.VISIBLE);
                holder.date.setText("- " + mMonth + mDay +" -");

            }
        }

        return convertView;
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

    private class ViewHolder {
        private ImageView img;
        private TextView date, title, time;

        public ViewHolder(View convertView) {
            img = (ImageView) convertView.findViewById(R.id.iv_new_item);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    mContext.getResources().getDisplayMetrics().widthPixels,
                    mContext.getResources().getDisplayMetrics().heightPixels/3);
            title = (TextView) convertView.findViewById(R.id.title_new_item);
            time = (TextView) convertView.findViewById(R.id.time_new_item);
            date = (TextView) convertView.findViewById(R.id.date_new_item);
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(mContext.getResources().getDisplayMetrics().widthPixels,mContext.getResources().getDisplayMetrics().heightPixels/12);
            date.setLayoutParams(params1);
            img.setLayoutParams(params);

        }
    }
}
