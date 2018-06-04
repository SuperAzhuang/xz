package com.xiaozhao.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xiaozhao.R;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.http.ImageLoaderOptions;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/3/21.
 */

public class AppliAdapter extends BaseAdapter {

    private List<NewsResult.NewsBean> mDatas;
    private Context mContext;
    private NewsResult.NewsBean mNewBean;

    public AppliAdapter(Context context, List<NewsResult.NewsBean> mDatas) {
        this.mDatas = mDatas;
        mContext = context;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDatas.size();
    }

    @Override
    public NewsResult.NewsBean getItem(int position) {
        // TODO Auto-generated method stub
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;

        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.apply_grid_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        mNewBean = mDatas.get(position);

//        vh.tvTitle.setText(mNewBean.getTitle());
//        vh.ivv.setText(StringUtils.getNormalTime(Long.parseLong(mNewBean.getPubdate())*1000));
//        ImageLoader.getInstance().displayImage(mNewBean.getLitpic(), vh.ivApplyer, ImageLoaderOptions.midOptions, new ImageLoadingListener() {
//
//            @Override
//            public void onLoadingStarted(String arg0, View arg1) {
//
//            }
//
//            @Override
//            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
//
//            }
//
//            @Override
//            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
//                //给背景图增加滤镜,避免图片偏白使字体看不清楚
//                vh.ivApplyer.setColorFilter(vh.ivApplyer.getResources().getColor(R.color.lecture_cover_filter));
//            }
//
//            @Override
//            public void onLoadingCancelled(String arg0, View arg1) {
//
//            }
//        });

        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.ivApplyer)
        ImageView ivApplyer;
        @InjectView(R.id.tvApplyName)
        TextView tvApplyName;
        @InjectView(R.id.tvZhaopin)
        TextView tvZhaopin;
        @InjectView(R.id.ll)
        LinearLayout ll;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
