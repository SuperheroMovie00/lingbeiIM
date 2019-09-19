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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.loginPage.activity.UpdatePasswordActivity;
import com.haitaoit.pinpai.module.loginPage.network.ApiRequest;
import com.haitaoit.pinpai.module.loginPage.network.request.PostUserPhotoEditItem;
import com.haitaoit.pinpai.module.personPage.network.Constant;
import com.haitaoit.pinpai.module.personPage.network.response.GetPersonUserObj;
import com.haitaoit.pinpai.module.personPage.network.response.UpdateIMageJson;
import com.haitaoit.pinpai.tools.BitmapTools;
import com.haitaoit.pinpai.tools.GetImagePath;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 账户设置  个人
 * Created by Administrator on 2017/10/26.
 */

public class AccountSettingPersonActivity extends BaseActivity {


    protected static final int CHOOSE_PICTURE = 0;
    protected static final int CHOOSE_PICTURE2 = 222;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.iv_setting_image)
    CircleImageView ivSettingImage;
    @BindView(R.id.tv_setting_name)
    TextView tvSettingName;
    @BindView(R.id.tv_setting_remark)
    TextView tvSettingRemark;
    @BindView(R.id.ll_setting_update)
    LinearLayout llSettingUpdate;
    @BindView(R.id.tv_setting_name_num)
    TextView tvSettingNameNum;
    @BindView(R.id.ll_setting_Name)
    LinearLayout llSettingName;
    @BindView(R.id.tv_setting_phone_num)
    TextView tvSettingPhoneNum;
    @BindView(R.id.ll_setting_phone)
    LinearLayout llSettingPhone;
    @BindView(R.id.tv_setting_wechat_num)
    TextView tvSettingWechatNum;
    @BindView(R.id.ll_setting_wechat)
    LinearLayout llSettingWechat;
    @BindView(R.id.tv_person_email_num)
    TextView tvPersonEmailNum;
    @BindView(R.id.ll_person_email)
    LinearLayout llPersonEmail;
    @BindView(R.id.ll_setting_Pass)
    LinearLayout llSettingPass;
    @BindView(R.id.tv_person_residence)
    TextView tvPersonResidence;
    @BindView(R.id.ll_setting_residence)
    LinearLayout llSettingResidence;
    @BindView(R.id.ll_setting_address)
    LinearLayout llSettingAddress;
    private Dialog photoDialog;
    protected static Uri tempUri;

    @Override
    protected int getContentView() {
        return R.layout.account_setting_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        initUserInfor();

    }

    private void initUserInfor() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("RegistrationID", JPushInterface.getRegistrationID(this));
        map.put("sign", GetSign.getSign(map));

        com.haitaoit.pinpai.module.personPage.network.ApiRequest.GetUserInfo(map, new MyCallBack<GetPersonUserObj>(mContext) {
            @Override
            public void onSuccessful(GetPersonUserObj response) {
                if (response.getErrCode() == 0) {
                    Glide.with(mContext).load(response.getResponse().getAvatar()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivSettingImage);
                    tvSettingName.setText(response.getResponse().getNick_name());
                    tvSettingNameNum.setText(response.getResponse().getNick_name());
                    tvSettingRemark.setText(response.getResponse().getType() + "  |  " + " " + response.getResponse().getCertified_name());
                    tvSettingPhoneNum.setText(response.getResponse().getMobile());
                    tvSettingWechatNum.setText(response.getResponse().getWeixin());
                    tvPersonEmailNum.setText(response.getResponse().getEmail());
                    tvPersonResidence.setText(response.getResponse().getAddress());
                } else {
                    showToastS("获取个人信息错误");
                }
            }
        });


    }


    @OnClick({R.id.rx_title, R.id.ll_setting_Name, R.id.ll_setting_Pass, R.id.ll_setting_update, R.id.ll_setting_phone, R.id.ll_setting_wechat, R.id.ll_person_email, R.id.ll_setting_residence, R.id.ll_setting_address})
    public void onViewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.ll_setting_update:
                showPhotoDialog();
                break;
            case R.id.ll_setting_Pass:
                RxActivityUtils.skipActivity(mContext, UpdatePasswordActivity.class);
                break;
            case R.id.ll_setting_Name:
                intent = new Intent();
                intent.putExtra(Constant.update_type, Constant.update_name);
                intent.putExtra("userName", tvSettingNameNum.getText().toString());
                STActivityForResult(intent, CompanyNameActivity.class, Constant.RCode.company_name);
                break;
            case R.id.ll_setting_phone:
                intent = new Intent();
                intent.putExtra(Constant.update_type, Constant.update_name);
                intent.putExtra("userPhone", tvSettingPhoneNum.getText().toString());
                STActivityForResult(intent, UpdatePhoneActivity.class, Constant.RCode.update_name);
                break;
            case R.id.ll_setting_wechat:
                intent = new Intent();
                intent.putExtra(Constant.update_type, Constant.update_phone);
                intent.putExtra("userWeChat", tvSettingWechatNum.getText().toString());
                STActivityForResult(intent, UpdateWeChatActivity.class, Constant.RCode.update_phone);
                break;
            case R.id.ll_person_email:
                intent = new Intent();
                intent.putExtra(Constant.update_type, Constant.update_email);
                intent.putExtra("useCompany", "2");
                intent.putExtra("userEmail", tvPersonEmailNum.getText().toString());
                STActivityForResult(intent, EmailActivity.class, Constant.RCode.update_email);
                break;
            case R.id.ll_setting_residence:
                intent = new Intent();
                intent.putExtra(Constant.update_type, Constant.update_change);
                intent.putExtra("userChange", tvPersonResidence.getText().toString());
                STActivityForResult(intent, ChangResidenceActivity.class, Constant.RCode.update_change);

                break;
            case R.id.ll_setting_address:
                RxActivityUtils.skipActivity(mContext, AddressActivity.class);

                break;
        }
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
            case Constant.RCode.company_name:
                String userName2 = data.getStringExtra(Constant.IParam.company_name);
                tvSettingNameNum.setText(userName2);

                break;
            case Constant.RCode.update_name:
                String userName = data.getStringExtra(Constant.IParam.name);
                tvSettingPhoneNum.setText(userName);
                tvSettingName.setText(userName);
                break;
            case Constant.RCode.update_phone:
                String mobile = data.getStringExtra(Constant.IParam.mobile);
                tvSettingWechatNum.setText(mobile);
                break;

            case Constant.RCode.update_change:
                String change = data.getStringExtra(Constant.IParam.update_change);
                tvPersonResidence.setText(change);
                break;

            case Constant.RCode.update_email:
                String email = data.getStringExtra(Constant.IParam.update_email);
                tvPersonEmailNum.setText(email);
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
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
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
            ivSettingImage.setImageBitmap(photo);
            String file = BitmapTools.bitmapToString(photo);
            postUserPhotoEditFromNet(file, photo);
        }
    }


    private void postUserPhotoEditFromNet(String file, final Bitmap photo) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));

        ApiRequest.PostUserPhotoEdit(map, new PostUserPhotoEditItem(file), new MyCallBack<UpdateIMageJson>(mContext) {
            @Override
            public void onSuccessful(UpdateIMageJson response) {
                if (response.getErrCode() == 0) {
                    showToastS("修改头像成功");
                    Glide.with(mContext).load(response.getResponse().getAvatar() ).error(R.mipmap.img32).into(ivSettingImage);
                    PreferenceUtils.setPrefString(mContext, Config.avatar, response.getResponse().getAvatar() + "");
                } else {
                    RxToast.normal(response.getErrMsg());
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
