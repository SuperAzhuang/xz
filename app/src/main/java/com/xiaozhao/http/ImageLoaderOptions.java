package com.xiaozhao.http;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.xiaozhao.R;

public class ImageLoaderOptions {
	public static DisplayImageOptions bigOptions = new DisplayImageOptions.Builder().cacheInMemory(true) // 设置下载的图片是否缓存在内存中
			.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
			.cacheInMemory(false).cacheOnDisk(true).resetViewBeforeLoading(true)// 在加载图片之前情况ImageView中的图片
			.imageScaleType(ImageScaleType.EXACTLY)// 设置缩放类型，会按照ImageView真实的宽高进行缩放
			.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的rgb显示模式，会让图片显示比较高清，而且占用内存较小
			.considerExifParams(true).build(); // 创建配置过得DisplayImageOption对象

	public static DisplayImageOptions midOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.loading)
			.showImageOnFail(R.mipmap.page_icon_network).showImageOnLoading(R.drawable.loading).showImageForEmptyUri(R.mipmap.page_icon_empty)
			.resetViewBeforeLoading(true)// 在加载图片之前情况ImageView中的图片
			.cacheOnDisk(true).cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY)// 设置缩放类型，会按照ImageView真实的宽高进行缩放
			.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的rgb显示模式，会让图片显示比较高清，而且占用内存较小
			.considerExifParams(true)
			// .displayer(new FadeInBitmapDisplayer(1500))
			.displayer(new SimpleBitmapDisplayer()).build();

	public static DisplayImageOptions list_options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.pic_bg)
			.showImageForEmptyUri(R.drawable.pic_bg).showImageOnLoading(R.drawable.pic_bg).showImageOnFail(R.drawable.pic_bg)
			.resetViewBeforeLoading(true)// 在加载图片之前情况ImageView中的图片
			.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)// 会识别图片的方向信息
			.displayer(new FadeInBitmapDisplayer(500)).build();// 渐渐显示的动画
//
//	public static DisplayImageOptions userHead = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.widget_dface)
//			.showImageForEmptyUri(R.drawable.widget_dface).showImageOnLoading(R.drawable.widget_dface).showImageOnFail(R.drawable.widget_dface)
//			.resetViewBeforeLoading(true)// 在加载图片之前情况ImageView中的图片
//			.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)// 会识别图片的方向信息
////			.displayer(new FadeInBitmapDisplayer(500))
//			.displayer(new SimpleBitmapDisplayer())
//			.build();// 渐渐显示的动画

	public static DisplayImageOptions photoWall_options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
			.imageScaleType(ImageScaleType.EXACTLY)// 设置缩放类型，会按照ImageView真实的宽高进行缩放
			.considerExifParams(true)// 会识别图片的方向信息
			.displayer(new FadeInBitmapDisplayer(500)).build();// 渐渐显示的动画
}
