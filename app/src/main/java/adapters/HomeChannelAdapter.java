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

import java.util.List;

import bean.HomeChannel;

/**
 * Created by CaesarFeng on 2017/4/15.
 */

public class HomeChannelAdapter extends BaseAdapter {

    private Context mContext;

    private List<HomeChannel.DataBean> mDataBeen;

    public HomeChannelAdapter(Context context,List<HomeChannel.DataBean> mDataBeen) {
        mContext = context;
        this.mDataBeen = mDataBeen;
    }

    @Override
    public int getCount() {
        return mDataBeen!=null?mDataBeen.size():0;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.channel_gv_item,parent,false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext).load(mDataBeen.get(position).getIcon()).into(holder.icon);
        holder.title.setText("#"+mDataBeen.get(position).getCatename()+"#");

        return convertView;
    }

    private class ViewHolder{
        private ImageView icon;
        private TextView title;

        public ViewHolder(View convertView){
            icon = (ImageView) convertView.findViewById(R.id.iv_channel_item);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mContext.getResources().getDisplayMetrics().widthPixels,mContext.getResources().getDisplayMetrics().widthPixels/2);
            icon.setLayoutParams(params);
            title = (TextView) convertView.findViewById(R.id.title_channel);
        }
    }

}
