package com.xiaozhao.bean;


import com.xiaozhao.R;
import com.xiaozhao.activity.SettingsActivity;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.fragment.SettingsFragment;

/**
 * Created by Administrator on 2018/3/31.
 */


public enum SimpleBackPage {

    SETTINGS(1, R.string.setting, SettingsFragment.class),
//    AUDIO(2, R.string.audio_weidiantai, AudioFragment.class),
//    NEWS_DETAIL(3, R.string.news_detail, NewsDetailFragment.class),
//    NEWS_IMAGES(4, R.string.news_iamges, ImageDetailFragment.class),
//    WEIBA(2,R.string.weiba,WeiBaListViewPagerFragment.class),
    ;

    private int title;
    private Class<?> clz;
    private int value;

    private SimpleBackPage(int value, int title, Class<?> clz) {
        this.value = value;
        this.title = title;
        this.clz = clz;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static SimpleBackPage getPageByValue(int val) {
        for (SimpleBackPage p : values()) {
            if (p.getValue() == val)
                return p;
        }
        return null;
    }
}
