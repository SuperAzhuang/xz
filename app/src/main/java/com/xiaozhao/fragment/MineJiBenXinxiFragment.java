package com.xiaozhao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.xiaozhao.R;
import com.xiaozhao.activity.MineInfoActivity;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.xiaozhao.R.id.tv;
import static com.xiaozhao.R.id.tvNianling;
import static com.xiaozhao.R.id.tvSex;
import static com.xiaozhao.R.id.tvXingbie;
import static org.litepal.crud.DataSupport.select;

/**
 * Created by Administrator on 2018/5/30.
 */

public class MineJiBenXinxiFragment extends BaseFragment {


    @InjectView(R.id.etNicheng)
    EditText etNicheng;
    @InjectView(R.id.tvXingbie)
    TextView tvXingbie;
    @InjectView(R.id.llXingbie)
    LinearLayout llXingbie;
    @InjectView(R.id.eMinZhu)
    EditText eMinZhu;
    @InjectView(R.id.etJiGuan)
    EditText etJiGuan;
    @InjectView(R.id.tvSr)
    TextView tvSr;
    @InjectView(R.id.etXianJuDizhi)
    EditText etXianJuDizhi;
    @InjectView(R.id.etHuKou)
    EditText etHuKou;
    @InjectView(R.id.tvHunyin)
    TextView tvHunyin;
    @InjectView(R.id.llHUnyin)
    LinearLayout llHUnyin;
    @InjectView(R.id.llsr)
    LinearLayout llsr;
    @InjectView(R.id.etWeChat)
    EditText etWeChat;
    @InjectView(R.id.etMail)
    EditText etMail;
    @InjectView(R.id.etPhone)
    EditText etPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_jibenxinxi, null);
        //得到数据
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSave:
                BaseApplication.showToastShort("保存成功");
                getSimpleBackActivity().finish();
                break;
            case R.id.llXingbie:
                selectXingbie();
                break;
            case R.id.llHUnyin:
                selectHunyin();
                break;
            case R.id.llsr:
                selectsr();
                break;

        }
    }

    private void selectsr() {

        TimePickerView pvTime = new TimePickerBuilder(getSimpleBackActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvSr.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setContentTextSize(18)//滚轮文字大小
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvTime.show();
    }

    private void selectHunyin() {


        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("已婚");
        options1Items.add("未婚");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText() +
//                        options2Items.get(options1).get(options2) +
//                        options3Items.get(options1).get(options2).get(options3);
                String tx = options1Items.get(options1);
                tvHunyin.setText(tx);
            }
        })

                .setTitleText("")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();


    }

    @Override
    public void initView(View view) {
        getSimpleBackActivity().tvSave.setVisibility(View.VISIBLE);
        getSimpleBackActivity().tvSave.setOnClickListener(this);
        llXingbie.setOnClickListener(this);
        llHUnyin.setOnClickListener(this);
        llsr.setOnClickListener(this);

    }


    @Override
    public void initData() {

    }

    private void selectXingbie() {
        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("男");
        options1Items.add("女");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText() +
//                        options2Items.get(options1).get(options2) +
//                        options3Items.get(options1).get(options2).get(options3);
                String tx = options1Items.get(options1);
                tvXingbie.setText(tx);
            }
        })

                .setTitleText("")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();
    }

    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

}
