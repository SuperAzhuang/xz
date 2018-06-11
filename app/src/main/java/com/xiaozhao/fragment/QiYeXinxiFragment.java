package com.xiaozhao.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.libmedia.MediaItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.utils.FileUtil;
import com.xiaozhao.utils.ImageUtils;
import com.xiaozhao.utils.StringUtils;
import com.xiaozhao.utils.UIHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.xiaozhao.R.id.tvHanye;
import static com.xiaozhao.R.id.tvgongzuo;
import static com.xiaozhao.R.id.tvqiuzhixingzhi;
import static com.xiaozhao.R.id.tvxingzhi;
import static com.xiaozhao.R.id.tvyuexin;
import static com.xiaozhao.R.id.tvzhuantai;
import static org.litepal.crud.DataSupport.select;

/**
 * Created by Administrator on 2018/5/25.
 */
public class QiYeXinxiFragment extends BaseFragment {


    @InjectView(R.id.etNicheng)
    EditText etNicheng;
    @InjectView(R.id.llhangye)
    LinearLayout llhangye;
    @InjectView(R.id.llleixing)
    LinearLayout llleixing;
    @InjectView(R.id.llguimo)
    LinearLayout llguimo;
    @InjectView(R.id.llHuanjin)
    LinearLayout llHuanjin;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.llDizhi)
    LinearLayout llDizhi;
    @InjectView(R.id.llJianjie)
    LinearLayout llJianjie;
    @InjectView(R.id.llZhizhao)
    LinearLayout llZhizhao;
    @InjectView(R.id.llFaren)
    LinearLayout llFaren;
    @InjectView(R.id.tvleixing)
    TextView tvleixing;
    @InjectView(R.id.tvqiyeleixing)
    TextView tvqiyeleixing;
    @InjectView(R.id.tvguimo)
    TextView tvguimo;
    @InjectView(R.id.tvhuanjing)
    TextView tvhuanjing;
    private String theLarge, theThumbnail;
    private File imgFile;
    ArrayList<String> picImgs = new ArrayList<String>();

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
        View view = inflater.inflate(R.layout.qiyexinxi_fragment, null);
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
            case R.id.llhangye:
                selectHangye();
                break;
            case R.id.tvSave:
                getSimpleBackActivity().finish();
                break;
            case R.id.llleixing:
                selectQiyeLeixing();
                break;
            case R.id.llguimo:
                selectGuimo();
                break;
            case R.id.llHuanjin:
                selectHuanjing();
                break;
            case R.id.llDizhi:

                break;
            case R.id.llJianjie:
                break;
            case R.id.llZhizhao:
                break;
            case R.id.llFaren:
                break;
        }

    }

    private void selectHuanjing() {

        String[] stringItems = {"拍照", "从相册中选择"};
        ActionSheetDialog dialog = new ActionSheetDialog(getSimpleBackActivity(), stringItems, null);
        dialog.isTitleShow(false).
                layoutAnimation(null);
        dialog.show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position
                switch (position) {
                    case 0:
//              拍照
                        break;
                    case 1:
//                相册
//                        UIHelper.showSimpleBack(this,ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD, SimpleBackPage.PHOTO_ALBUM_FRAGMENT);
                        UIHelper.showSimpleBackForResult(QiYeXinxiFragment.this, ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD, SimpleBackPage.PHOTO_ALBUM_FRAGMENT, null);
                        break;
                }
            }
        });
    }

    @Override
    public void initView(View view) {
        llhangye.setOnClickListener(this);
        llleixing.setOnClickListener(this);
        llguimo.setOnClickListener(this);
        llHuanjin.setOnClickListener(this);
        llDizhi.setOnClickListener(this);
        llJianjie.setOnClickListener(this);
        llZhizhao.setOnClickListener(this);
        llFaren.setOnClickListener(this);
        getSimpleBackActivity().tvSave.setOnClickListener(this);
        getSimpleBackActivity().tvSave.setVisibility(View.VISIBLE);

    }

    @Override
    public void initData() {

    }

    private void selectGuimo() {

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
                String tx = options1Items.get(options1);
                tvguimo.setText(tx);
            }
        }).setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();

    }


    private void selectQiyeLeixing() {
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
                tvqiyeleixing.setText(tx);
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

    private void selectHangye() {


        OptionsPickerView pvOptions = new OptionsPickerBuilder(getSimpleBackActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1) +
                        options2Items.get(options1).get(options2);
                tvleixing.setText(tx);
            }
        })

                .setTitleText("行业类型")
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
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;

//        if (mLayPicContainer != null) {
//            mLayPicContainer.removeAllViews();
//            picImgs.clear();
//        }
//
//        mScrollPicContainer.setVisibility(View.VISIBLE);

        new Thread() {
            private String selectedImagePath;
            @Override
            public void run() {
                Bitmap bitmap = null;
                if (requestCode == ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD) {
                    if (data == null)
                        return;

                    ArrayList<MediaItem> picItems = data.getParcelableArrayListExtra("pics");

                    for (MediaItem mediaItem : picItems) {
                        picImgs.add(mediaItem.getSource().toString());
                    }
                }

                if (picImgs.size() > 0) {
                    String savePath = ImageUtils.IMGS;
                    File savedir = new File(savePath);
                    if (!savedir.exists()) {
                        savedir.mkdirs();
                    }
                    //
                    for (int i = 0; i < picImgs.size(); i++) {

                        String largeFileName = FileUtil.getFileName(picImgs.get(i));
                        String largeFilePath = savePath + largeFileName;
                        // 判断是否已存在缩略图
                        if (largeFileName.startsWith("thumb_") && new File(largeFilePath).exists()) {
                            // theThumbnail = largeFilePath;
                            picImgs.set(i, largeFilePath);
                            // imgFile = new File(theThumbnail);
                        } else {
                            // 生成上传的宽度图片
                            String thumbFileName = "thumb_" + largeFileName;
                            theThumbnail = savePath + thumbFileName;

                            if (new File(theThumbnail).exists()) {
                                // imgFile = new File(theThumbnail);
                            } else {
                                try {
                                    // 压缩上传的图片
                                    ImageUtils.createImageThumbnail(getActivity(), picImgs.get(i), theThumbnail, 1000, 80);
                                    // imgFile = new File(theThumbnail);
                                    // picImgs.set(i,imgFile.getAbsolutePath());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            picImgs.set(i, theThumbnail);
                        }

                        //
                    }

                    // 保存动弹临时图片
                    // ((AppContext) getApplication()).setProperty(
                    // tempTweetImageKey, theThumbnail);

                    // Message msg = new Message();
                    // msg.what = 1;
                    // msg.obj = bitmap;
                    // handler.sendMessage(msg);
                }
            };
        }.start();
    }

}
