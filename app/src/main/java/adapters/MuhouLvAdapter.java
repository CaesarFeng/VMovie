package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jb.caesarfeng.vmovie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import bean.MuhouBean;

/**
 * Created by CaesarFeng on 2017/4/17.
 */

public class MuhouLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<MuhouBean.DataBean> muhouDatas;

    public MuhouLvAdapter(Context context, List<MuhouBean.DataBean> muhouDatas) {
        mContext = context;
        this.muhouDatas = muhouDatas;
    }

    @Override
    public int getCount() {
        return muhouDatas!=null?muhouDatas.size():0;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.muhou_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext).load(muhouDatas.get(position).getImage()).into(holder.img);
        holder.title.setText(muhouDatas.get(position).getTitle());
        holder.share.setText(muhouDatas.get(position).getShare_num());
        holder.like.setText(muhouDatas.get(position).getLike_num());
        return convertView;
    }

    private class ViewHolder{
        private ImageView img;
        private TextView title,share,like;
        public ViewHolder(View convertView){
            img = (ImageView) convertView.findViewById(R.id.img_muhou);
            title = (TextView) convertView.findViewById(R.id.title_item_muhou);
            share = (TextView) convertView.findViewById(R.id.share_muhou);
            like = (TextView) convertView.findViewById(R.id.like_muhou);
        }
    }
}
