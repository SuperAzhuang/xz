package com.xiaozhao.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.orhanobut.logger.Logger;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.utils.FileUtil;
import com.xiaozhao.utils.ImageUtils;
import com.xiaozhao.utils.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.InjectView;

import static android.R.id.button3;
import static com.xiaozhao.base.BaseApplication.showToast;
import static org.litepal.crud.DataSupport.select;

public class MineInfoActivity extends BaseActivity {

    @InjectView(R.id.ivHeader)
    ImageView ivHeader;
    @InjectView(R.id.ivBack)
    ImageView ivBack;
    @InjectView(R.id.etNicheng)
    EditText etNicheng;
    @InjectView(R.id.etName)
    EditText etName;
    @InjectView(R.id.tvSex)
    TextView tvSex;
    @InjectView(R.id.tvSave)
    TextView tvSave;
    @InjectView(R.id.tvNianling)
    TextView tvNianling;
    @InjectView(R.id.llnianning)
    LinearLayout llnianning;
    @InjectView(R.id.etPhone)
    EditText etPhone;
    @InjectView(R.id.llXingbie)
    LinearLayout llXingbie;
    private Uri origUri;
    private Uri cropUri;
    private String theLarge;
    private File protraitFile;
    private Bitmap protraitBitmap;
    private String protraitPath;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.llXingbie:
                selectXingbie();
                break;
            case R.id.llnianning:
                selectNianling();
                break;
            case R.id.tvSave:
//                保存
                upLoadUserInfo();
                break;
            case R.id.ivHeader:
//                头像选择
                selectHeader();
                break;
        }
    }

    private void selectHeader() {

        String[] stringItems = {"拍照", "从相册中选择"};
        ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
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
                        startTakePhoto();
                        break;
                    case 1:
//                相册
                        startImagePick();
                        break;
                    case 2:

                        break;
                }
            }
        });
    }
    /**
     * 选择图片裁剪
     *
     * @param
     */
    private void startImagePick() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"), ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"), ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
        }
    }

    /**
     * 拍照
     */
    private void startTakePhoto() {
        Intent intent;
        // 判断是否挂载了SD卡
        String savePath = "";

        PackageManager manager = getPackageManager();

        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/XiaoZhao/Camera/";
            File savedir = new File(savePath);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        }
        // 没有挂载SD卡，无法保存文件
        if (StringUtils.isEmpty(savePath)) {
            BaseApplication.showToastShort("无法保存照片，请检查SD卡是否挂载");
            return;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = "xiaozhao_" + timeStamp + ".jpg";// 照片命名
        File out = new File(savePath, fileName);
        Uri uri = Uri.fromFile(out);
        origUri = uri;

        theLarge = savePath + fileName;// 该照片的绝对路径

        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
        Logger.t(TAG).d("theLarge=" + theLarge + "--uri = " + uri);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA:
                startActionCrop(origUri);// 拍照后裁剪
                Logger.t(TAG).d("REQUEST_CODE_GETIMAGE_BYCAMERA--" + origUri);
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                Logger.t(TAG).d("REQUEST_CODE_GETIMAGE_BYCROP");
                startActionCrop(data.getData());// 选图后裁剪
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD:
                Logger.t(TAG).d("REQUEST_CODE_GETIMAGE_BYSDCARD  ");
                uploadNewPhoto();
                break;
        }
    }

    /**
     * 拍照后裁剪
     *
     * @param data 原始图片
     */
    private void startActionCrop(Uri data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("output", this.getUploadTempFile(data));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", ImageUtils.CROP);// 输出图片大小
        intent.putExtra("outputY", ImageUtils.CROP);
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent, ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
    }


    // 裁剪头像的绝对路径
    private Uri getUploadTempFile(Uri uri) {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(ImageUtils.FILE_SAVEPATH);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            BaseApplication.showToast("无法保存上传的头像，请检查SD卡是否挂载");
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String thePath = ImageUtils.getAbsolutePathFromNoStandardUri(uri);

        // 如果是标准Uri
        if (StringUtils.isEmpty(thePath)) {
            thePath = ImageUtils.getAbsoluteImagePath(this, uri);
        }
        String ext = FileUtil.getFileFormat(thePath);
        ext = StringUtils.isEmpty(ext) ? "jpg" : ext;
        // 照片命名
        String cropFileName = "xiaozhao_crop_" + timeStamp + "." + ext;
        // 裁剪头像的绝对路径
        protraitPath = ImageUtils.FILE_SAVEPATH + cropFileName;
        protraitFile = new File(protraitPath);

        cropUri = Uri.fromFile(protraitFile);
        return this.cropUri;
    }

    /**
     * 上传公司环境照片
     */
    private void uploadNewPhoto() {

        // 获取头像缩略图
        Logger.t(TAG).d("uploadNewPhoto");
        if (!StringUtils.isEmpty(protraitPath) && protraitFile.exists()) {
            protraitBitmap = ImageUtils.loadImgThumbnail(protraitPath, 200, 200);
        } else {
            showToast("图像不存在，上传失败");
        }
        Logger.t(TAG).d("protraitBitmap = " + protraitBitmap);
        if (protraitBitmap != null) {
            showToast("上传中....");
//            showWaitDialog("上传中...");
            try {

//                RedDragonflyApi.updatePortrait(protraitFile, new AsyncHttpResponseHandler() {
//
//                    @Override
//                    public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
//                        hideWaitDialog();
//                        BaseApplication.showToast("上传头像失败");
//                    }
//
//                    @Override
//                    public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
//                        hideWaitDialog();
////                        mIvAvatar.setImageBitmap(protraitBitmap);
//                    }
//
//                });
            } catch (Exception e) {
                hideWaitDialog();
                showToast("图像不存在，上传失败");
            }
        }
    }
    private void upLoadUserInfo() {
        BaseApplication.showToastShort("保存成功");
        finish();
    }

    private void selectNianling() {

        TimePickerView pvTime = new TimePickerView.Builder(MineInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                String time = getTime(date2);
                tvNianling.setText(time);
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
//                        .setTitleText("请选择时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                        .setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)//默认是1900-2100年
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                        .setRangDate(startDate,endDate)//起始终止年月日设定
//                        .setLabel("年","月","日","时","分","秒")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .isDialog(true)//是否显示为对话框样式
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    private void selectXingbie() {
        String[] stringItems = {"男", "女"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
        dialog.isTitleShow(false).
                layoutAnimation(null);
        dialog.show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position
                switch (position) {
                    case 0:
                        tvSex.setText("男");
                        dialog.dismiss();
                        break;
                    case 1:
                        dialog.dismiss();
                        tvSex.setText("女");
                        break;

                }
            }
        });
    }

    @Override
    public void initView() {
        ivBack.setOnClickListener(this);
        llXingbie.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        ivHeader.setOnClickListener(this);
        llnianning.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mime_info;
    }

}
