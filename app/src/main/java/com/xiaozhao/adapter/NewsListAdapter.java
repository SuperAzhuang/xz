package com.xiaozhao.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xiaozhao.R;
import com.xiaozhao.base.ListBaseAdapter;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.http.ImageLoaderOptions;
import com.xiaozhao.utils.StringUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/1/29.
 */

public class NewsListAdapter extends ListBaseAdapter<NewsResult.NewsBean> {

    private NewsResult.NewsBean mNewBean;

    public NewsListAdapter() {
    }


    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {

        mNewBean = mDatas.get(position);
        final ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(R.layout.item_news, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tvTitle.setText(mNewBean.getTitle());
        vh.tvDate.setText(StringUtils.getNormalTime(Long.parseLong(mNewBean.getPubdate())*1000));
        ImageLoader.getInstance().displayImage(mNewBean.getLitpic(), vh.leftImage, ImageLoaderOptions.midOptions, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String arg0, View arg1) {

            }

            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {

            }

            @Override
            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                //给背景图增加滤镜,避免图片偏白使字体看不清楚
                vh.leftImage.setColorFilter(vh.leftImage.getResources().getColor(R.color.lecture_cover_filter));
            }

            @Override
            public void onLoadingCancelled(String arg0, View arg1) {

            }
        });

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.left_image)
        ImageView leftImage;
        @InjectView(R.id.tvTitle)
        TextView tvTitle;
        @InjectView(R.id.tvDate)
        TextView tvDate;
        @InjectView(R.id.title_layout)
        RelativeLayout titleLayout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

