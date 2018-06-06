package com.xiaozhao.bean;


import com.xiaozhao.R;
import com.xiaozhao.activity.SettingsActivity;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.fragment.JiaoyuShengChengYeFragment;
import com.xiaozhao.fragment.JingLiShengChengyeFragment;
import com.xiaozhao.fragment.MineFaBuFragment;
import com.xiaozhao.fragment.MineFragment;
import com.xiaozhao.fragment.MineGongZuojingliFragment;
import com.xiaozhao.fragment.MineGuanZhuFragment;
import com.xiaozhao.fragment.MineJiBenXinxiFragment;
import com.xiaozhao.fragment.MineJianliFragment;
import com.xiaozhao.fragment.MineJiaoyubeijingFragment;
import com.xiaozhao.fragment.MineQiuZhiYixiangFragment;
import com.xiaozhao.fragment.MinezhuanyejinengFragment;
import com.xiaozhao.fragment.PingJiaFragment;
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
    JIANLI(2,  R.string.mine_jianli, MineJianliFragment.class),
    GUANGZHU(3,  R.string.mine_guanzhu, MineGuanZhuFragment.class),
    FABU(4,  R.string.mine_fabu, MineFaBuFragment.class),
    QIUZHIYIXIANG(6,  R.string.mine_qiuzhiyixiang, MineQiuZhiYixiangFragment.class),
    JIAOYUBEIJING(7,  R.string.mine_jiaoyubeijing, MineJiaoyubeijingFragment.class),
    GONGZUOJINGLI(8,  R.string.mine_gongzuojingli, MineGongZuojingliFragment.class),
    ZHUANYEJINENG(9,  R.string.mine_zhuanyejineng, MinezhuanyejinengFragment.class),
    JYSHENGCHENGYE(10,  R.string.jiaoyushengchengye, JiaoyuShengChengYeFragment.class),
    JINGLISHENGCHENGYE(11,  R.string.mine_gongzuojingli, JingLiShengChengyeFragment.class),
    PINGJIA(12,  R.string.jianli_ziwopingjia, PingJiaFragment.class),
    JIBENXINXI(5,  R.string.mine_jibenxinxi, MineJiBenXinxiFragment.class), ;

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
