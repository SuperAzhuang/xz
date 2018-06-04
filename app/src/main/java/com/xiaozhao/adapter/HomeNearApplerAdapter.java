package com.xiaozhao.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orhanobut.logger.Logger;
import com.xiaozhao.R;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.http.ImageLoaderOptions;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HomeNearApplerAdapter extends BaseQuickAdapter<NewsResult.NewsBean, BaseViewHolder> {

    private ImageView view;
    private List lists;

    public HomeNearApplerAdapter(int layoutResId, List data) {
        super(layoutResId, data);
        this.lists = data;
    }


    @Override
    protected void convert(BaseViewHolder helper, NewsResult.NewsBean item) {
        view = (ImageView) helper.getView(R.id.ivApplyer);
        Logger.t("HomeCompanyAdapter").d("size = "+lists.size());

        ImageLoader.getInstance().displayImage(item.getLitpic(),(ImageView) helper.getView(R.id.ivApplyer), ImageLoaderOptions.midOptions, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String arg0, View arg1) {

            }

            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {

            }

            @Override
            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                //给背景图增加滤镜,避免图片偏白使字体看不清楚

                view.setColorFilter(view.getResources().getColor(R.color.lecture_cover_filter));
            }

            @Override
            public void onLoadingCancelled(String arg0, View arg1) {

            }
        });
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
