package com.xiaozhao.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.xiaozhao.R;
import com.xiaozhao.adapter.TagAdapter;
import com.xiaozhao.utils.TDevice;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by azhuang on 2018/3/22.
 */

public class TagPopwindow extends PopupWindow {

    private Context context;
    private View mshopPopView;
    private  GridView gridView;

    public TagPopwindow( Context context,
                               final WindowManager.LayoutParams lp, final Window w,
                               List<String> mData) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mshopPopView = inflater.inflate(R.layout.tag_popwindow, null);
        gridView = mshopPopView.findViewById(R.id.grideview);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("制造业");
        arrayList.add("IT");
        arrayList.add("医疗");
        arrayList.add("服务业");
        arrayList.add("机械");
        arrayList.add("知识");
        arrayList.add("鞋服");
        arrayList.add("手工");
        TagAdapter tagAdapter =     new TagAdapter(context,arrayList);
        gridView.setAdapter(tagAdapter);

        lp.alpha = 0.3f;
        w.setAttributes(lp);
        this.setBackgroundDrawable(new ColorDrawable(0));
		/* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.mshopPopView);
        // 设置弹出窗体的宽和高
		int i = w.getWindowManager().getDefaultDisplay().getWidth() / 2;
        int ii = (int) context.getResources().getDimension(R.dimen.space_100);
//        this.setHeight(TDevice.dip2px(context, ii));
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        this.setAnimationStyle(android.R.style.Animation_Dialog);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(
                context.getResources().getColor(R.color.white));
//        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // 设置popwindow如果点击外面区域，便关闭。
        this.setOutsideTouchable(true);
        // 设置动画
//        this.setAnimationStyle(R.style.take_photo_anim);
        this.update();
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1;
                w.setAttributes(lp);
            }
        });
    }


}
