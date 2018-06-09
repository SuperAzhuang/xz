package com.xiaozhao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.xiaozhao.R;
import com.xiaozhao.adapter.ViewPageFragmentAdapter;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.utils.UIHelper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.xiaozhao.R.id.tv;
import static org.litepal.crud.DataSupport.select;

/**
 * Created by Administrator on 2018/5/25.
 */

public class FaBuZhiWeiFragment extends BaseFragment {

    @InjectView(R.id.etNicheng)
    EditText etNicheng;
    @InjectView(R.id.llName)
    LinearLayout llName;
    @InjectView(R.id.tvLieBie)
    TextView tvLieBie;
    @InjectView(R.id.llLieBie)
    LinearLayout llLieBie;
    @InjectView(R.id.llYueXin)
    LinearLayout llYueXin;
    @InjectView(R.id.llXingZhi)
    LinearLayout llXingZhi;
    @InjectView(R.id.llDiDian)
    LinearLayout llDiDian;
    @InjectView(R.id.llRenshu)
    LinearLayout llRenshu;
    @InjectView(R.id.llXueLi)
    LinearLayout llXueLi;
    @InjectView(R.id.llNianXian)
    LinearLayout llNianXian;
    @InjectView(R.id.llMiaoshu)
    LinearLayout llMiaoshu;
    @InjectView(R.id.llDaiYu)
    LinearLayout llDaiYu;
    @InjectView(R.id.llDiqu)
    LinearLayout llDiqu;
    @InjectView(R.id.tvyuexin)
    TextView tvyuexin;
    @InjectView(R.id.tvxingzhi)
    TextView tvxingzhi;
    @InjectView(R.id.tvdidian)
    TextView tvdidian;
    @InjectView(R.id.tvrenshu)
    TextView tvrenshu;
    @InjectView(R.id.tvyaoqiu)
    TextView tvyaoqiu;
    @InjectView(R.id.tvnianxian)
    TextView tvnianxian;
    @InjectView(R.id.tvmiaoshu)
    TextView tvmiaoshu;
    @InjectView(R.id.tvdaiyu)
    TextView tvdaiyu;
    @InjectView(R.id.tvdiqu)
    TextView tvdiqu;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ViewPageFragmentAdapter mAdapter;
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    private String[] parentStrings = {"中原区", "二七区", "管城区", "金水区", "上街区", "惠济区", "郑东新区", "高新区", "经开区", "郑州周边"};
    private String[][] childrenStrings = {
            {"中原1", "中原2", "中原3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"二七1", "二七2", "二七3", "二七4", "二七5", "二七6", "二七7", "二七8", "二七9", "二七10", "二七11", "二七12", "二七13", "二七14", "二七15"},
            {"管城1", "管城2", "管城3", "管城4", "管城5", "管城6", "管城7", "管城8", "管城9", "管城10", "管城11", "管城12", "管城13", "管城14", "管城15"},
            {"金水1", "金水2", "金水3", "金水4", "金水5", "金水6", "金水7", "金水8", "金水9", "金水10", "金水11", "金水12", "金水13", "金水14", "金水15"},
            {"上街1", "上街2", "上街3", "上街4", "上街5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"中原1", "中原2", "中原3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"郑东新区1", "郑东新区2", "郑东新区3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"高新区1", "高新区2", "高新区3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"经开区1", "经开区2", "经开区3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"周边1", "周边2", "周边3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fabuzhiwei_fragment, null);
        //得到数据
        ButterKnife.inject(this, view);
        for (int i = 0; i < parentStrings.length; i++) {
            options1Items.add(parentStrings[i]);
        }
        for (int i = 0; i < childrenStrings.length; i++) {
            ArrayList<String> strings = new ArrayList<>();
            for (int y = 0; y < childrenStrings[i].length; y++) {
//                System.out.print(childrenStrings[i][y] + "、");
                strings.add(childrenStrings[i][y]);
            }
            options2Items.add(strings);
        }
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSave:
                getSimpleBackActivity().finish();
                break;
            case R.id.llLieBie:
                selectLiebie();

                break;
            case R.id.llYueXin:
                selectYuexin();
                break;
            case R.id.llXingZhi:
                selecZhiweiXingzhi();
                break;
            case R.id.llDiDian:

                break;
            case R.id.llRenshu:

                selectRenshu();

                break;
            case R.id.llXueLi:
                selectXueLi();
                break;
            case R.id.llNianXian:
                selectNianXian();
                break;
            case R.id.llMiaoshu:
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.ZHIWEIMIAOSHU,null);
                break;
            case R.id.llDaiYu:

                break;
            case R.id.llDiqu:

                break;
        }
    }

    private void selectNianXian() {
        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("经验不限");
        options1Items.add("一年以下");
        options1Items.add("1-3年");
        options1Items.add("3-5年");
        options1Items.add("5-10年");
        options1Items.add("10年以上");

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1);
                tvnianxian.setText(tx);
            }
        })

                .setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();
    }

    private void selectXueLi() {

        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("学历不限");
        options1Items.add("初中");
        options1Items.add("高中");
        options1Items.add("职专");
        options1Items.add("大专");
        options1Items.add("本科");
        options1Items.add("硕士");
        options1Items.add("博士");

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1);
                tvyaoqiu.setText(tx);
            }
        })

                .setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();
    }

    private void selectRenshu() {

        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("1人");
        options1Items.add("2人");
        options1Items.add("3人");
        options1Items.add("5人");
        options1Items.add("10人");
        options1Items.add("20以上");

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1);
                tvrenshu.setText(tx);
            }
        })

                .setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();
    }

    @Override
    public void initView(View view) {

        getSimpleBackActivity().tvSave.setText("立即发布");
        getSimpleBackActivity().tvSave.setVisibility(View.VISIBLE);
        getSimpleBackActivity().tvSave.setOnClickListener(this);
        llLieBie.setOnClickListener(this);
        llYueXin.setOnClickListener(this);
        llXingZhi.setOnClickListener(this);
        llDiDian.setOnClickListener(this);
        llRenshu.setOnClickListener(this);
        llXueLi.setOnClickListener(this);
        llNianXian.setOnClickListener(this);
        llMiaoshu.setOnClickListener(this);
        llDiqu.setOnClickListener(this);
        llDaiYu.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }


    private void selectqiuzhixingzhi() {
        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("全职");
        options1Items.add("兼职");
        options1Items.add("实习");

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1);
            }
        })

                .setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();
    }

    private void selecZhiweiXingzhi() {
        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("全职");
        options1Items.add("兼职");
        options1Items.add("实习");

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1);
                tvxingzhi.setText(tx);
            }
        })

                .setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();
    }

    private void selectQiyeXingzhi() {
        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("事业单位");
        options1Items.add("国家机关");
        options1Items.add("代表处");
        options1Items.add("上市公司");
        options1Items.add("民营");
        options1Items.add("外商独资");
        options1Items.add("合资");
        options1Items.add("股份制企业");
        options1Items.add("其他");

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText() +
//                        options2Items.get(options1).get(options2) +
//                        options3Items.get(options1).get(options2).get(options3);
                String tx = options1Items.get(options1);
//                tvxingzhi.setText(tx);
            }
        })

                .setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();
    }

    private void selectYuexin() {

        final ArrayList<String> options1Items = new ArrayList<>();
        options1Items.add("1000以下");
        options1Items.add("1000-3000");
        options1Items.add("3000-5000");
        options1Items.add("5000-7000");
        options1Items.add("7000-10000");
        options1Items.add("10000-20000");
        options1Items.add("20000以上");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1);
                tvyuexin.setText(tx);
            }
        })

                .setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();
    }

    private void selecGongzuo() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1) +
                        options2Items.get(options1).get(options2);

//                tvgongzuo.setText(options1Items.get(options1) +
//                        options2Items.get(options1).get(options2));
            }
        })

                .setTitleText("选择岗位")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.setPicker(options1Items, options2Items);//三级选择器
        pvOptions.show();
    }

    private void selectLiebie() {


        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1) +
                        options2Items.get(options1).get(options2);

//                Toast.makeText(getSimpleBackActivity(), tx, Toast.LENGTH_SHORT).show();
                tvLieBie.setText(options1Items.get(options1) +
                        options2Items.get(options1).get(options2));
            }
        })

                .setTitleText("职位列表")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.setPicker(options1Items, options2Items);//三级选择器
        pvOptions.show();
    }

}
