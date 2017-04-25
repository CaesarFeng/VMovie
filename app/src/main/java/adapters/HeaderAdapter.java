package adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jb.caesarfeng.vmovie.NoVideoSecondActivity;
import com.jb.caesarfeng.vmovie.ViewPagerSecondActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import bean.HomeNewHeader;

/**
 * Created by CaesarFeng on 2017/4/15.
 */

public class HeaderAdapter extends PagerAdapter {

    private List<Bitmap> bitmaps;
    private List<HomeNewHeader.DataBean> mDataBeen;
    private Context mContext;

    public HeaderAdapter(List<HomeNewHeader.DataBean> mDataBeen, Context context) {
        this.mDataBeen = mDataBeen;
        this.mContext = context;
    }

    @Override
    public int getCount() {
//        return mDataBeen != null ? mDataBeen.size() : 0;
        return mDataBeen.size()*700;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if (mDataBeen.size()>0){
            Picasso.with(mContext).load(mDataBeen.get(position%mDataBeen.size()).getImage()).into(imageView);
            container.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String app_banner_param = mDataBeen.get(position%mDataBeen.size()).getExtra_data().getApp_banner_param();
                    if (app_banner_param.indexOf("http")!=-1) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, NoVideoSecondActivity.class);
                        intent.putExtra("title",mDataBeen.get(position%mDataBeen.size()).getTitle());
                        intent.putExtra("url",mDataBeen.get(position%mDataBeen.size()).getExtra_data().getApp_banner_param());
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(mContext, ViewPagerSecondActivity.class);
                        intent.putExtra("url",mDataBeen.get(position%mDataBeen.size()).getExtra_data().getApp_banner_param());
                        mContext.startActivity(intent);
                    }
                }
            });
        }


        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
