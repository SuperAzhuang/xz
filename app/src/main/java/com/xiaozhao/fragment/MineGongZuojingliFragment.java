package com.xiaozhao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.xiaozhao.R.id.tvSex;

/**
 * Created by Administrator on 2018/5/30.
 */

public class MineGongZuojingliFragment extends BaseFragment {

    @InjectView(R.id.llRuZhi)
    LinearLayout llRuZhi;
    @InjectView(R.id.llLiZhi)
    LinearLayout llLiZhi;
    @InjectView(R.id.etGongsi)
    EditText etGongsi;
    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.etZhiwei)
    EditText etZhiwei;
    @InjectView(R.id.etBumen)
    EditText etBumen;
    @InjectView(R.id.llGuiMo)
    LinearLayout llGuiMo;
    @InjectView(R.id.llXingZhi)
    LinearLayout llXingZhi;
    @InjectView(R.id.etFujiaxinxi)
    EditText etFujiaxinxi;
    @InjectView(R.id.tvRuZhi)
    TextView tvRuZhi;
    @InjectView(R.id.tvLiZhi)
    TextView tvLiZhi;
    @InjectView(R.id.tvGuimo)
    TextView tvGuimo;
    @InjectView(R.id.tvXingZhi)
    TextView tvXingZhi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_gongzuojingli, null);
        //得到数据
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llRuZhi:
                selectRuZhi(true);
                break;
            case R.id.llLiZhi:
                selectRuZhi(false);
                break;
            case R.id.llGuiMo:
                selectGuiMo();
                break;
            case R.id.llXingZhi:
                selectXingZhi();
                break;
            case R.id.tvSave:
                getSimpleBackActivity().finish();
                break;
        }
    }

    @Override
    public void initView(View view) {
        getSimpleBackActivity().tvSave.setVisibility(View.VISIBLE);
        getSimpleBackActivity().tvSave.setOnClickListener(this);
        llRuZhi.setOnClickListener(this);
        llLiZhi.setOnClickListener(this);
        llGuiMo.setOnClickListener(this);
        llXingZhi.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    private void selectRuZhi(final boolean isRuZhi) {

        TimePickerView pvTime = new TimePickerBuilder(getSimpleBackActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (isRuZhi) {
                    tvRuZhi.setText(getTime(date));
                } else {
                    tvLiZhi.setText(getTime(date));
                }
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

    private void selectGuiMo() {

        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("20人以下");
        options1Items.add("20-99人");
        options1Items.add("100-499人");
        options1Items.add("500-999人");
        options1Items.add("1000人以上");

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText() +
//                        options2Items.get(options1).get(options2) +
//                        options3Items.get(options1).get(options2).get(options3);
                String tx = options1Items.get(options1);
                tvGuimo.setText(tx);
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

    private void selectXingZhi() {

        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("事业单位");
        options1Items.add("国家机关");
        options1Items.add("代表处");
        options1Items.add("上市公司");
        options1Items.add("民营");
        options1Items.add("外商独资");
        options1Items.add("国企");
        options1Items.add("合资");
        options1Items.add("股份制");
        options1Items.add("其他");

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText() +
//                        options2Items.get(options1).get(options2) +
//                        options3Items.get(options1).get(options2).get(options3);
                String tx = options1Items.get(options1);
                tvXingZhi.setText(tx);
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
