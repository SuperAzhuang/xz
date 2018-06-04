package com.xiaozhao.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.Logger;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.utils.FileUtil;
import com.xiaozhao.utils.ImageUtils;
import com.xiaozhao.utils.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

import static com.xiaozhao.base.BaseApplication.showToast;
import static com.xiaozhao.base.BaseApplication.showToastShort;

/**
 * Created by Administrator on 2018/5/24.
 */

public class CompanyInfoEditFragment extends BaseFragment {
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
    private Uri origUri;
    private Uri cropUri;
    private String theLarge;
    private File protraitFile;
    private Bitmap protraitBitmap;
    private String protraitPath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.company_info_edit, null);
        //得到数据
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llHuanjin:
                String[] stringItems = {"拍照", "从相册中选择"};
                ActionSheetDialog dialog = new ActionSheetDialog(getActivity(), stringItems, null);
                dialog.isTitleShow(false).
                        layoutAnimation(null)
                ;
                dialog.show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //position
                        switch (position) {
                            case 0:
//                                拍照
                                startTakePhoto();
                                break;
                            case 1:
//                                相册
                                startImagePick();
                                break;
                            case 2:

                                break;
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void initView(View view) {
        llZhizhao.setOnClickListener(this);
        llHuanjin.setOnClickListener(this);
    }

    @Override
    public void initData() {

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

        PackageManager manager = getActivity().getPackageManager();

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
            thePath = ImageUtils.getAbsoluteImagePath(getActivity(), uri);
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
}
