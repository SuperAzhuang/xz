package com.xiaozhao.im.presenter;


import com.xiaozhao.im.model.MessageModel;
import com.xiaozhao.im.mvp.MessageMVP;

/**
 * Created by gabriel on 2017/3/3.
 */

public class MessagePresenter implements MessageMVP.Presenter {
    private MessageMVP.Model model;
    private MessageMVP.View view;

    public MessagePresenter(MessageMVP.View view) {
        model = new MessageModel();
        this.view = view;
    }
}
