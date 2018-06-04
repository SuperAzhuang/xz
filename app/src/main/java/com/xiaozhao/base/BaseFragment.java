package com.xiaozhao.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaozhao.R;
import com.xiaozhao.activity.MainActivity;
import com.xiaozhao.activity.SimpleBackActivity;
import com.xiaozhao.inter.BaseFragmentInterface;
import com.xiaozhao.inter.DialogControl;
import com.xiaozhao.view.WaitDialog;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/26.
 */

public abstract class BaseFragment extends Fragment implements BaseFragmentInterface, View.OnClickListener {

    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public final String TAG = this.getClass().getName();
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
    public int mState = STATE_NONE;

    protected LayoutInflater mInflater;

    public BaseApplication getApplication() {
        return (BaseApplication) getActivity().getApplication();
    }

    public MainActivity getMAinActivity() {
        return (MainActivity) getActivity();
    }

    public SimpleBackActivity getSimpleBackActivity() {
        return (SimpleBackActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        initData();
        initView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onBackPressed() {
        return false;
    }

    protected void hideWaitDialog() {
        FragmentActivity activity = getActivity();
        if (activity instanceof DialogControl) {
            ((DialogControl) activity).hideWaitDialog();
        }
    }

    protected WaitDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    protected WaitDialog showWaitDialog(int resid) {
        FragmentActivity activity = getActivity();
        if (activity instanceof DialogControl) {
            return ((DialogControl) activity).showWaitDialog(resid);
        }
        return null;
    }

    protected WaitDialog showWaitDialog(String resid) {
        FragmentActivity activity = getActivity();
        if (activity instanceof DialogControl) {
            return ((DialogControl) activity).showWaitDialog(resid);
        }
        return null;
    }
}
