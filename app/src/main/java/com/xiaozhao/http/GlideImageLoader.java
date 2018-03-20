package com.xiaozhao.http;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2018/1/29.
 */

public class GlideImageLoader extends ImageLoader {

    public void displayImage(Context context, Object path, ImageView imageView) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage((String) path, imageView);
    }
}
