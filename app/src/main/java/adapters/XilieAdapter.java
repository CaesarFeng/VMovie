package adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jb.caesarfeng.vmovie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import bean.XileiBean;

/**
 * Created by CaesarFeng on 2017/4/17.
 */

public class XilieAdapter extends BaseAdapter{

    private Context mContext;
    private List<XileiBean.DataBean> mDataBeen;

    public XilieAdapter(Context context, List<XileiBean.DataBean> dataBeen) {
        mContext = context;
        mDataBeen = dataBeen;
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
        ViewHolder holder= null;
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xilie_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext).load(mDataBeen.get(position).getImage()).into(holder.img);
        holder.title.setText(mDataBeen.get(position).getTitle());
        holder.updata_to.setText("已更新至"+mDataBeen.get(position).getUpdate_to()+"集");
        holder.count.setText(mDataBeen.get(position).getFollower_num()+"人已订阅");
        holder.content.setText(mDataBeen.get(position).getContent());
        return convertView;
    }

    private class ViewHolder{
        private ImageView img;
        private TextView title,updata_to,count,content;

        public ViewHolder(View conveertView){
            img = (ImageView) conveertView.findViewById(R.id.img_xilie);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mContext.getResources().getDisplayMetrics().widthPixels,mContext.getResources().getDisplayMetrics().heightPixels/3);
            img.setLayoutParams(params);
            title = (TextView) conveertView.findViewById(R.id.title_xilie);
            updata_to = (TextView) conveertView.findViewById(R.id.updata_to_xilie);
            count = (TextView) conveertView.findViewById(R.id.count_xilie);
            content = (TextView) conveertView.findViewById(R.id.content_xilie);
        }
    }
}
