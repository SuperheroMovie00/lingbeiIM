package com.haitaoit.pinpai.module.personPage.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.homePage.activity.MainActivity;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.module.loginPage.network.request.PostUserPhotoEditItem;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.request.PostPersonAnthItem;
import com.haitaoit.pinpai.tools.BitmapTools;
import com.haitaoit.pinpai.tools.GetImagePath;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.tools.RegexUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * 个人认证
 * Created by Administrator on 2017/11/1.
 */

public class PersonAuthActivity extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.et_really_name)
    EditText etReallyName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.et_really_phone)
    EditText etReallyPhone;
    @BindView(R.id.et_really_email)
    EditText etReallyEmail;
    @BindView(R.id.iv_IDCardOne)
    ImageView ivIDCardOne;
    @BindView(R.id.iv_IDCardTwo)
    ImageView ivIDCardTwo;
    @BindView(R.id.tv_Submit)
    MyTextView tvSubmit;
    @BindView(R.id.et_really_Id)
    EditText etReallyId;

    protected static final int CHOOSE_PICTURE2 = 222;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;

    private Dialog photoDialog;
    protected static Uri tempUri;
    private String mPhotoType;
    String file = null;
    String file2 = null;

    PostPersonAnthItem item = new PostPersonAnthItem();
    List<PostPersonAnthItem.ImgListBean> beans = new ArrayList<>();
    List<PostPersonAnthItem.ImgSideListBean> mSideListbeans = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.person_auto_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (!RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_Email, ""))) {
            etReallyEmail.setText(PreferenceUtils.getPrefString(mContext, Config.user_Email, ""));
        } else {
            etReallyEmail.setText(getIntent().getStringExtra("mUserEmail"));
        }
    }

    @Override
    protected void initData() {
    }


    @OnClick({R.id.rx_title, R.id.tv_sex, R.id.iv_IDCardOne, R.id.iv_IDCardTwo, R.id.tv_Submit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_sex:
                showSexDialog();
                break;
            case R.id.iv_IDCardOne:
                mPhotoType = "1";
                showPhotoDialog();
                break;
            case R.id.iv_IDCardTwo:
                mPhotoType = "2";
                showPhotoDialog();
                break;
            case R.id.tv_Submit:
                if (TextUtils.isEmpty(etReallyName.getText().toString())) {
                    showToastS("真实姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(tvSex.getText().toString())) {
                    showToastS("性别不能为空");
                    return;
                }

                if (TextUtils.isEmpty(etReallyPhone.getText().toString())) {
                    showToastS("联系电话不能为空");
                    return;
                }
                if (TextUtils.isEmpty(etReallyEmail.getText().toString())) {
                    showToastS("邮箱不能为空");
                    return;
                }

                if (!RegexUtils.isEmail(etReallyEmail.getText().toString())) {
                    showToastS("你输入的邮箱不合法");
                    return;
                }

//                if (!RegexUtils.isMobilePhoneNumber(etReallyPhone.getText().toString())) {
//                    showToastS("你输入的联系电话不合法");
//                    return;
//                }

                if (!RegexUtils.isIdCard(etReallyId.getText().toString())) {
                    showToastS("你输入的身份证不合法");
                    return;
                }
                if (TextUtils.isEmpty(etReallyId.getText().toString())) {
                    showToastS("身份证号码不能为空");
                    return;
                }
                Log.e("身份证照不能为空", beans.size() + "");
                if (beans == null || beans.size() != 1) {
                    showToastS("身份证照正面照片不能为空");
                    return;
                }

                if (mSideListbeans == null || mSideListbeans.size() != 1) {
                    showToastS("身份证照反面照片不能为空");
                    return;
                }

                postPersonSupple();
                break;
        }
    }

    private void showSexDialog() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(this,
                isChinese ? new String[]{"男", "女"} : new String[]{"Aquarius", "Pisces"});
        picker.setCanLoop(true);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "请选择" : "Please pick");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFFEE0000);
        picker.setUnSelectedTextColor(0xFF999999);
        LineConfig config = new LineConfig();
        config.setColor(0xFFEE0000);//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                tvSex.setText(item);
            }
        });
        picker.show();
    }


    private void showPhotoDialog() {

        View view = getLayoutInflater().inflate(R.layout.dialog_photo_choose, null);
        //初始化三个按钮
        Button btnPic = (Button) view.findViewById(R.id.btn_pic);
        Button btnCamera = (Button) view.findViewById(R.id.btn_camera);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        //响应点击事件
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    photoDialog.cancel();
                    Intent openAlbumIntent = new Intent(
                            Intent.ACTION_GET_CONTENT);
                    openAlbumIntent.addCategory(Intent.CATEGORY_OPENABLE);
                    openAlbumIntent.setType("image/*");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
                        File newFile = new File(Environment.getExternalStorageDirectory(), "Android/data/com.haitaoit.pinpai/files/header");
                        if (!newFile.exists()) {
                            newFile.mkdirs();
                        }
                        File fil = new File(newFile, "image11.jpg");
                        String authority = mContext.getPackageName() + ".provider";
                        Uri uriForFile = FileProvider.getUriForFile(mContext, authority, fil);
                        openAlbumIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                        openAlbumIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        openAlbumIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE2);
                    } else {
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                    }
                }
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoDialog.dismiss();
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    photoDialog.dismiss();
                    if (Build.VERSION.SDK_INT >= 24) {
                        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
                            File newFile = new File(Environment.getExternalStorageDirectory(), "Android/data/com.haitaoit.pinpai/files/header");
                            if (!newFile.exists()) {
                                newFile.mkdirs();
                            }
                            File fil = new File(newFile, "image.jpg");
                            String authority = mContext.getPackageName() + ".provider";
                            tempUri = FileProvider.getUriForFile(mContext, authority, fil);
                            Log.i("", "contentUri = " + tempUri.toString());
                            List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(takePhotoIntent, PackageManager.MATCH_DEFAULT_ONLY);
                            for (ResolveInfo resolveInfo : resInfoList) {
                                String packageName = resolveInfo.activityInfo.packageName;
                                grantUriPermission(packageName, tempUri,
                                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            }
                            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                            startActivityForResult(takePhotoIntent, TAKE_PICTURE);
                        }
                    } else {
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoDialog.dismiss();
            }
        });


        photoDialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        photoDialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = photoDialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        photoDialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        photoDialog.setCanceledOnTouchOutside(true);
        photoDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case TAKE_PICTURE:
                Log.e("TAKE_PICTURE11111", tempUri + "");
                startPhotoZoom(tempUri);
                break;
            case CHOOSE_PICTURE:
                Log.e("CHOOSE_PICTURE从手机相册获取", data.getData() + "");
                startPhotoZoom(data.getData());
                break;
            case CHOOSE_PICTURE2:
                Log.e("CHOOSE_PICTURE从2222  ", data.getData() + "");


                File imgUri = new File(GetImagePath.getPath(mContext, data.getData()));
                String authority = mContext.getPackageName() + ".provider";
                Uri dataUri = FileProvider.getUriForFile(mContext, authority, imgUri);
                startPhotoZoom(dataUri);
                break;
            case CROP_SMALL_PICTURE:
                if (data != null) {
                    Log.e("setImageToView", data + "");
                    setImageToView(data);
                }
                break;
        }


    }

    /**
     * 图像裁剪
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
            return;
        }

        Intent intent = new Intent("com.android.camera.action.CROP");
        //sdk>=24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

//            Uri outPutUri = Uri.fromFile(mCropFile);
            intent.setDataAndType(uri, "image/*");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.putExtra("noFaceDetection", false);//去除默认的人脸识别，否则和剪裁匡重叠
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        } else {
//            Uri outPutUri = Uri.fromFile(mCropFile);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String url = GetImagePath.getPath(mContext, uri);//这个方法是处理4.4以上图片返回的Uri对象不同的处理方法
                intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
            } else {
                intent.setDataAndType(uri, "image/*");
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// 图片格式
        startActivityForResult(intent, CROP_SMALL_PICTURE);//这里就将裁剪后的图片的Uri返回了
    }


    /**
     * 裁剪之后的图片
     *
     * @param data
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();

        if (extras != null) {

            Bitmap photo = extras.getParcelable("data");
            if ("1".equals(mPhotoType)) {
                ivIDCardOne.setImageBitmap(photo);
                file = BitmapTools.bitmapToString(photo);
                PostPersonAnthItem.ImgListBean imgListBean = new PostPersonAnthItem.ImgListBean();
                imgListBean.setImg_url(file);
                beans.add(imgListBean);

            } else {
                ivIDCardTwo.setImageBitmap(photo);
                file2 = BitmapTools.bitmapToString(photo);
                PostPersonAnthItem.ImgSideListBean imgSideListBean = new PostPersonAnthItem.ImgSideListBean();
                imgSideListBean.setImg_url(file2);
                mSideListbeans.add(imgSideListBean);
            }


            Log.e("11111111111s1111beans", beans.size() + "");
            Log.e("11111111111s1111beans", mSideListbeans.size() + "");
        }
    }


    private void postPersonSupple() {
        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", getIntent().getStringExtra("mUserId"));
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        map.put("sign", GetSign.getSign(map));
        item.setPersonal_name(etReallyName.getText().toString());
        item.setSex(tvSex.getText().toString());
        item.setPersona_mobile(etReallyPhone.getText().toString());
        item.setPersona_email(etReallyEmail.getText().toString());
        item.setPersona_card(etReallyId.getText().toString());
        item.setCard_front(beans);
        item.setCard_side(mSideListbeans);
        Log.e("111111111000", beans.size() + "");
        ApiRequest.PostPersona_Ident(map, item, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    Bundle mBundleEmail = new Bundle();
                    mBundleEmail.putString("mUserEmail", etReallyEmail.getText().toString().trim());
                    RxActivityUtils.skipActivityAndFinish(mContext, AnthSuccessActivity.class, mBundleEmail);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
