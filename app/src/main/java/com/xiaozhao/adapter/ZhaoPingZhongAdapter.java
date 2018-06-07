package com.xiaozhao.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaozhao.R;
import com.xiaozhao.bean.NewsResult;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class ZhaoPingZhongAdapter extends BaseQuickAdapter<NewsResult.NewsBean, BaseViewHolder> {

    public ZhaoPingZhongAdapter(int layoutResId, List<NewsResult.NewsBean> data, Context context) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, NewsResult.NewsBean mNewBean) {
        helper.addOnClickListener(R.id.ivDelete);
//        helper.setText(R.id.tvTitle, mNewBean.getTitle());
//        helper.setText(R.id.tvDate, StringUtils.getNormalTime(Long.parseLong(mNewBean.getPubdate()) * 1000));
//        ImageLoader.getInstance().displayImage(mNewBean.getLitpic(), (ImageView) helper.getView(R.id.left_image), ImageLoaderOptions.midOptions, new ImageLoadingListener() {
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
//                ImageView imageView = (ImageView) helper.getView(R.id.left_image);
////                        vh.leftImage.setColorFilter(vh.leftImage.getResources().getColor(R.color.lecture_cover_filter));
//                imageView.setColorFilter(imageView.getResources().getColor(R.color.lecture_cover_filter));
//            }
//
//            @Override
//            public void onLoadingCancelled(String arg0, View arg1) {
//
//            }
//        });
    }
}
