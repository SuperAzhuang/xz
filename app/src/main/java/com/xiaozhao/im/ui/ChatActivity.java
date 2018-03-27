package com.xiaozhao.im.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.TIMMessage;
import com.xiaozhao.R;
import com.xiaozhao.im.ConstantValues;
import com.xiaozhao.im.adapter.ChatAdapter;
import com.xiaozhao.im.mvp.ChatMVP;
import com.xiaozhao.im.presenter.ChatPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ChatActivity extends AppCompatActivity implements ChatMVP.View, View.OnClickListener {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.btn_voice)
    ImageButton btn_voice;
    @InjectView(R.id.btn_keyboard)
    ImageButton btn_keyboard;
    @InjectView(R.id.voice_panel)
    TextView voice_panel;
    @InjectView(R.id.input)
    EditText input;
    @InjectView(R.id.btnEmoticon)
    ImageButton btn_emoticon;
    @InjectView(R.id.text_panel)
    LinearLayout text_panel;
    @InjectView(R.id.btn_add)
    ImageButton btnAdd;
    @InjectView(R.id.btn_send)
    ImageButton btn_send;
    @InjectView(R.id.btn_image)
    LinearLayout btnImage;
    @InjectView(R.id.btn_photo)
    LinearLayout btnPhoto;
    @InjectView(R.id.btn_video)
    LinearLayout btnVideo;
    @InjectView(R.id.btn_file)
    LinearLayout btnFile;
    @InjectView(R.id.morePanel)
    LinearLayout morePanel;
    @InjectView(R.id.emoticonPanel)
    LinearLayout emoticonPanel;
    @InjectView(R.id.frame)
    FrameLayout frame;
    @InjectView(R.id.voice_sending)
    TextView voice_sending;
    @InjectView(R.id.chat_recycler_view)
    RecyclerView recyclerView;
    @InjectView(R.id.activity_chat)
    RelativeLayout activityChat;
    private ChatMVP.Presenter presenter;
    private ChatAdapter mAdapter;
    private List<TIMMessage> mTimMessages;
    private float downY;
    private boolean mIsRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        initToolbar();

        btn_voice.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_keyboard.setOnClickListener(this);
        voice_panel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mIsRecording = true;
                        downY = event.getY();
                        voice_sending.setVisibility(View.VISIBLE);
                        voice_panel.setText(getText(R.string.chat_release_send));
                        presenter.recordSound();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (downY - event.getY() > 200) {
                            voice_sending.setText(getText(R.string.chat_release_cancel));
                        } else {
                            voice_sending.setText(getText(R.string.chat_cancel_send));
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mIsRecording = false;
                        voice_sending.setText(getText(R.string.chat_cancel_send));
                        voice_sending.setVisibility(View.GONE);
                        voice_panel.setText(getText(R.string.chat_press_talk));
                        if (downY - event.getY() > 200) {
                            presenter.sendSoundMessage(false);
                        } else {
                            presenter.sendSoundMessage(true);
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        recordFail();
                        break;
                }
                return true;
            }
        });
        mTimMessages = new ArrayList<>();
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        hideInput();
                        break;
                }
                return false;
            }
        });
        presenter = new ChatPresenter(this, getIntent().getStringExtra(ConstantValues.FRIEND_IDENTIFIER));
        presenter.getMessage(new TIMMessage());
    }

    private void hideInput() {
        input.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

    private void initToolbar() {
        String name = getIntent().getStringExtra(ConstantValues.FRIEND_NAME);
        if (!TextUtils.isEmpty(name)) {
            toolbar.setTitle(name);
        } else {
            toolbar.setTitle(getIntent().getStringExtra(ConstantValues.FRIEND_IDENTIFIER));
        }
        setSupportActionBar(toolbar);
    }

    @Override
    public void updateRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new ChatAdapter(mTimMessages);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        if (mAdapter.getItemCount() > 0) {
            recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
        }
    }

    @Override
    public List<TIMMessage> getAdapterData() {
        return mTimMessages;
    }

    @Override
    public void recordFail() {
        if (mIsRecording) {
            mIsRecording = false;
            voice_sending.setText(getText(R.string.chat_cancel_send));
            voice_sending.setVisibility(View.GONE);
            voice_panel.setText(getText(R.string.chat_press_talk));
            presenter.sendSoundMessage(false);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                String message = input.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    presenter.sendTextMessage(message);
                    input.setText("");
                }
                break;
            case R.id.btn_voice:
                hideInput();
                btn_voice.setVisibility(View.GONE);
                text_panel.setVisibility(View.GONE);
                btn_keyboard.setVisibility(View.VISIBLE);
                voice_panel.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_keyboard:
                btn_voice.setVisibility(View.VISIBLE);
                text_panel.setVisibility(View.VISIBLE);
                btn_keyboard.setVisibility(View.GONE);
                voice_panel.setVisibility(View.GONE);
                break;
        }

    }


}
