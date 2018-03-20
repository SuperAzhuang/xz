package com.xiaozhao.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiaozhao.R;
import com.xiaozhao.utils.UIHelper;


public class SplashAdapter extends PagerAdapter implements OnClickListener {

	private Context mContext;
	int[] imgs;

	public SplashAdapter(Context context) {
		this.mContext = context;
	}

	@Override
	public int getCount() {

		imgs = new int[3];
		imgs[0] = R.mipmap.image1;
		imgs[1] = R.mipmap.image2;
		imgs[2] = R.mipmap.image3;
//		imgs[3] = R.drawable.image4;
		return imgs.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = View
				.inflate(mContext, R.layout.item_viewpager_splash, null);
		ImageView bg = (ImageView) view.findViewById(R.id.bg);

		bg.setImageResource(imgs[position]);
		if (position == imgs.length-1) {
			View bt = (View) view.findViewById(R.id.bt);
			bt.setOnClickListener(this);
			bt.setVisibility(View.VISIBLE);
		}
		container.addView(view);
		return view;
	}
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.bt) {
			// 跳转到登陆页面
//			UIHelper.showLoginActivity(mContext);
			UIHelper.showMainActivity(mContext);
			((Activity)mContext).finish();
		}
	}
}
