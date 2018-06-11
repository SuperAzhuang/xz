package com.xiaozhao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.example.libmedia.MediaItem;
import com.example.libmedia.MediaPickerFragment;
import com.example.libmedia.sources.MediaSource;
import com.example.libmedia.sources.MediaSourceDeviceImages;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.view.EmptyLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.InjectView;

import static com.xiaozhao.R.id.etPingjia;
import static com.xiaozhao.R.id.tv;

public class SimpleBackActivity extends BaseActivity implements MediaPickerFragment.OnMediaSelected {


    public final static String BUNDLE_KEY_PAGE = "BUNDLE_KEY_PAGE";
    public final static String BUNDLE_KEY_ARGS = "BUNDLE_KEY_ARGS";
    protected WeakReference<Fragment> mFragment;
    protected int mPageValue = -1;


    @InjectView(R.id.framentTitle)
    TextView framentTitle;
    @InjectView(R.id.container)
    FrameLayout container;
    @InjectView(R.id.activity_root)
    RelativeLayout activityRoot;
    public RelativeLayout rlTitle;
    @InjectView(R.id.ivBack)
    public ImageView ivBack;
    @InjectView(R.id.error_layout)
    public EmptyLayout mEmptyLayout;
    public TextView tvSave;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                if (getCurrentFocus() != null) {
                    if (getCurrentFocus().getWindowToken() != null) {
                        hideKeyboard(getCurrentFocus().getWindowToken());
                    }
                }
                finish();
                break;
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        if (mPageValue == -1) {
            mPageValue = getIntent().getIntExtra(BUNDLE_KEY_PAGE, 0);
        }
        initFromIntent(mPageValue, getIntent());
    }

    protected void initFromIntent(int pageValue, Intent data) {

        if (data == null) {
            throw new RuntimeException(
                    "you must provide a page info to display");
        }
        SimpleBackPage page = SimpleBackPage.getPageByValue(pageValue);
        if (page == null) {
            throw new IllegalArgumentException("can not find page by value:"
                    + pageValue);
        }
        try {
            Fragment fragment = (Fragment) page.getClz().newInstance();

            if (fragment instanceof MediaPickerFragment) {

//                tvSave.setVisibility(View.VISIBLE);
//                tvSave.setText("上传");
//                tvSave.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        finish();
//                    }
//                });
                MediaPickerFragment frag = (MediaPickerFragment) fragment;
                ArrayList<MediaSource> imageSources = new ArrayList<>();
                imageSources.add(new MediaSourceDeviceImages());
                frag.setCustomLayout(R.layout.media_fragment_three_grid);
                frag.setMediaSources(imageSources);

            }

            Bundle args = data.getBundleExtra(BUNDLE_KEY_ARGS);
            if (args != null) {
                fragment.setArguments(args);
            }

            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();
            trans.replace(R.id.container, fragment, TAG);
            trans.commitAllowingStateLoss();

            mFragment = new WeakReference<Fragment>(fragment);
            setActionBarTitle(page.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "generate fragment error. by value:" + pageValue);
        }
    }

    public void setActionBarTitle(int resId) {
        if (resId != 0) {
            framentTitle.setText(resId);
        }
    }

    @Override
    public void initView() {
//        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.swiperefresh_color1).init();

        ivBack.setOnClickListener(this);
        tvSave = (TextView) findViewById(R.id.tvSave);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_simple_back_fragment;
    }

    @Override
    public void onMediaSelectionStarted() {

    }

    @Override
    public void onMediaSelected(MediaItem mediaContent, boolean selected) {

    }

    @Override
    public void onMediaSelectionConfirmed(ArrayList<MediaItem> mediaContent) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("pics", mediaContent);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onMediaSelectionCancelled() {

    }

    @Override
    public boolean onMenuItemSelected(MenuItem menuItem, ArrayList<MediaItem> selectedContent) {
        return false;
    }

    @Override
    public ImageLoader.ImageCache getImageCache() {
        return null;
    }
}