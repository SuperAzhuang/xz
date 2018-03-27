package com.xiaozhao.im.adapter;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.Dimension;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.TIMElem;
import com.tencent.TIMElemType;
import com.tencent.TIMImage;
import com.tencent.TIMImageElem;
import com.tencent.TIMMessage;
import com.tencent.TIMTextElem;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.im.TimeUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by gabriel on 2017/3/6.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<TIMMessage> mTIMMessageList;
    private MediaPlayer mMediaPlayer;
    private static final String TAG = "ChatAdapter";

    public ChatAdapter(List<TIMMessage> timMessage) {
        mTIMMessageList = timMessage;
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.
                from(BaseApplication.getContext()).inflate(R.layout.item_message, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //由于sdk返回的list是按时间顺序排列的，所以显示时需要反过来
        position = mTIMMessageList.size() - position - 1;
        final TIMMessage timMessage = mTIMMessageList.get(position);

        holder.systemMessage.setVisibility(View.VISIBLE);
        holder.systemMessage.setText(TimeUtil.getChatTimeStr(timMessage.timestamp()));
        if (position != mTIMMessageList.size() - 1) {
            TIMMessage lastTimMessage = mTIMMessageList.get(position + 1);
            //如果上一条消息与当前这一条的时间间隔小于300秒，则不显示这一条消息的发送时间
            if (timMessage.timestamp() - lastTimMessage.timestamp() < 300) {
                holder.systemMessage.setVisibility(View.GONE);
            }
        }

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout
                .LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
                , RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);

        TIMElem element = timMessage.getElement(0);
        if (timMessage.isSelf()) {     //自己向好友发送的消息
            holder.leftPanel.setVisibility(View.GONE);
            holder.rightPanel.setVisibility(View.VISIBLE);
            holder.rightMessage.removeAllViews();
            //显示自己向好友发送的消息的发送状态
            switch (timMessage.status()) {
                case Sending:
                    holder.sendError.setVisibility(View.GONE);
                    holder.sending.setVisibility(View.VISIBLE);
                    break;
                case SendSucc:
                    holder.sendError.setVisibility(View.GONE);
                    holder.sending.setVisibility(View.GONE);
                    break;
                case SendFail:
                    holder.sendError.setVisibility(View.VISIBLE);
                    holder.sending.setVisibility(View.GONE);
                    break;
            }
            //文字信息处理
            if (element.getType() == TIMElemType.Text) {
                TIMTextElem textElem = (TIMTextElem) element;
                TextView textView = new TextView(BaseApplication.getContext());
                textView.setText(textElem.getText());
                textView.setTextSize(Dimension.SP, 16);
                holder.rightMessage.addView(textView, layoutParams);
                //语音信息处理
            } else if (element.getType() == TIMElemType.Sound) {
                ImageView imageView = new ImageView(BaseApplication.getContext());
//                imageView.setImageResource(R.drawable.right_voice);
                holder.rightMessage.addView(imageView, layoutParams);
                holder.rightMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mMediaPlayer.isPlaying()) {
                            mMediaPlayer.stop();
                        } else {
//                            ChatModel.playSound(mMediaPlayer, timMessage);
                        }
                    }
                });
                //图片信息处理
            } else if (element.getType() == TIMElemType.Image) {
                TIMImageElem imageElem = (TIMImageElem) element;
                for (TIMImage timImage : imageElem.getImageList()) {
                    ImageView imageView = new ImageView(BaseApplication.getContext());
//                    Glide.with(BaseApplication.getContext())
//                            .load(timImage.getUrl())
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                            .into(imageView);
                    layoutParams.setMargins(0, 0, 0, 0);
                    holder.rightMessage.addView(imageView, layoutParams);
                }
            }
        } else {   //好友向自己发送的消息
            holder.leftPanel.setVisibility(View.VISIBLE);
            holder.rightPanel.setVisibility(View.GONE);
            holder.leftMessage.removeAllViews();
            holder.sender.setText(timMessage.getSender());

            //文字信息处理
            if (element.getType() == TIMElemType.Text) {
                TIMTextElem textElem = (TIMTextElem) element;
                TextView textView = new TextView(BaseApplication.getContext());
                textView.setText(textElem.getText());
                textView.setTextColor(Color.DKGRAY);
                textView.setTextSize(Dimension.SP, 16);

                holder.leftMessage.addView(textView, layoutParams);

                //语音信息处理
            } else if (element.getType() == TIMElemType.Sound) {
                ImageView imageView = new ImageView(BaseApplication.getContext());
//                imageView.setImageResource(R.drawable.left_voice);
                holder.leftMessage.addView(imageView, layoutParams);
                holder.leftMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mMediaPlayer.isPlaying()) {
                            mMediaPlayer.stop();
                        } else {
//                            ChatModel.playSound(mMediaPlayer, timMessage);
                        }
                    }
                });

                //图片信息处理
            } else if (element.getType() == TIMElemType.Image) {
                TIMImageElem imageElem = (TIMImageElem) element;
                for (TIMImage timImage : imageElem.getImageList()) {
                    ImageView imageView = new ImageView(BaseApplication.getContext());
//                    Glide.with(BaseApplication.getContext())
//                            .load(timImage.getUrl())
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                            .into(imageView);
                    layoutParams.setMargins(0, 0, 0, 0);
                    holder.leftMessage.addView(imageView, layoutParams);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mTIMMessageList.size();
    }



    class ViewHolder extends  RecyclerView.ViewHolder{
        @InjectView(R.id.systemMessage)
        TextView systemMessage;
        @InjectView(R.id.leftAvatar)
        ImageView leftAvatar;
        @InjectView(R.id.sender)
        TextView sender;
        @InjectView(R.id.leftMessage)
        RelativeLayout leftMessage;
        @InjectView(R.id.leftPanel)
        RelativeLayout leftPanel;
        @InjectView(R.id.rightAvatar)
        ImageView rightAvatar;
        @InjectView(R.id.rightMessage)
        RelativeLayout rightMessage;
        @InjectView(R.id.rightDesc)
        TextView rightDesc;
        @InjectView(R.id.sending)
        ProgressBar sending;
        @InjectView(R.id.sendError)
        ImageView sendError;
        @InjectView(R.id.sendStatus)
        RelativeLayout sendStatus;
        @InjectView(R.id.rightPanel)
        RelativeLayout rightPanel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
