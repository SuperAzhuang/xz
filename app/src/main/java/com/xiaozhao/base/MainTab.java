package com.xiaozhao.base;


import com.xiaozhao.R;
import com.xiaozhao.fragment.CompanyFragment;
import com.xiaozhao.fragment.NearApplerFragment;
import com.xiaozhao.fragment.MineFragment;
import com.xiaozhao.fragment.NewsFragment;

public enum MainTab {

    NEWS(0, R.string.home_company, R.drawable.tab_icon_news, CompanyFragment.class),

    ZHIBO(1, R.string.home_person, R.drawable.tab_icon_zhibo, NearApplerFragment.class),

    ZHENGWU(2, R.string.home_news, R.drawable.tab_icon_zhengwu, NewsFragment.class),

    SHENGHOU(3, R.string.home_mine, R.drawable.tab_icon_shenghuo, MineFragment.class);

//    HUDONG(4,R.string.hudong, R.drawable.tab_icon_hudong, HuDongFragment.class);


    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
