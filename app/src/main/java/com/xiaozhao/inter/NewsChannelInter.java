package com.xiaozhao.inter;

import com.xiaozhao.bean.Channel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */

public interface NewsChannelInter {

    void loadData(List<Channel> channels, List<Channel> otherChannels);
}
