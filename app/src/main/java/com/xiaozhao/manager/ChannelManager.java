package com.xiaozhao.manager;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.bean.Channel;
import com.xiaozhao.dao.ChannelDao;
import com.xiaozhao.inter.NewsChannelInter;
import com.xiaozhao.utils.LogUtils;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */

public class ChannelManager {
    /**
     * 获取推荐频道和我的频道
     */
    public static void getChannel(NewsChannelInter channelInter) {

        List<Channel> channelList;
        List<Channel> myChannels = new ArrayList<>();
        List<Channel> otherChannels = new ArrayList<>();
        channelList = ChannelDao.getChannels();
        LogUtils.printList(channelList);
        if (channelList.size() < 1) {
            List<String> channelName = Arrays.asList(BaseApplication.context().getResources()
                    .getStringArray(R.array.news));
            List<String> channelId = Arrays.asList(BaseApplication.context().getResources()
                    .getStringArray(R.array.news_channel_id));
            List<Channel> channels = new ArrayList<>();

            for (int i = 0; i < channelName.size(); i++) {
                Channel channel = new Channel();
                channel.setChannelId(channelId.get(i));
                channel.setChannelName(channelName.get(i));
                channel.setChannelType(i < 1 ? 1 : 0);
                channel.setChannelSelect(i < channelId.size() - 3);
                if (i < channelId.size() - 3) {
                    myChannels.add(channel);
                } else {
                    otherChannels.add(channel);
                }
                channels.add(channel);
            }
        //存到数据库
            DataSupport.saveAllAsync(channels).listen(new SaveCallback() {
                @Override
                public void onFinish(boolean success) {

                }
            });

            channelList = new ArrayList<>();
            channelList.addAll(channels);
        } else {
            channelList = ChannelDao.getChannels();
            Iterator<Channel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                Channel channel = iterator.next();
                if (!channel.isChannelSelect()) {
                    otherChannels.add(channel);
                    iterator.remove();
                }
            }
            myChannels.addAll(channelList);
        }
        channelInter.loadData(myChannels, otherChannels);
    }
}
