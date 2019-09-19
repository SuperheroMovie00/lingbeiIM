package com.haitaoit.pinpai.module.personPage.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.module.loginPage.activity.UpdatePasswordActivity;
import com.haitaoit.pinpai.tools.DataCleanManager;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.utils.SharePreferenceManager;
import com.haitaoit.pinpai.utils.ToastUtil;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxTitle;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * 设置
 * Created by Administrator on 2017/11/1.
 */

public class MySettingActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.ll_setting_update)
    LinearLayout llSettingUpdate;
    @BindView(R.id.ll_setting_messgae)
    LinearLayout llSettingMessgae;
    @BindView(R.id.ll_setting_ClearCache)
    RelativeLayout llSettingClearCache;
    @BindView(R.id.ll_setting_help)
    LinearLayout llSettingHelp;
    @BindView(R.id.ll_setting_aboutMe)
    LinearLayout llSettingAboutMe;
    @BindView(R.id.ll_setting_suggestion)
    LinearLayout llSettingSuggestion;
    @BindView(R.id.tv_Exitlogin)
    MyTextView tvExitlogin;
    @BindView(R.id.clear_num)
    TextView clearNum;

    private Dialog photoDialog;

    @Override
    protected int getContentView() {
        return R.layout.my_setting_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
            clearNum.setText(DataCleanManager.getTotalCacheSize(mContext));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.rx_title, R.id.ll_setting_update, R.id.ll_setting_messgae,
            R.id.ll_setting_ClearCache, R.id.ll_setting_help, R.id.ll_setting_aboutMe,
            R.id.ll_setting_suggestion, R.id.tv_Exitlogin})
    public void onViewClick(View view) {
        Bundle mBundle = new Bundle();
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.ll_setting_update:
                RxActivityUtils.skipActivity(mContext, UpdatePasswordActivity.class);
                break;
            case R.id.ll_setting_messgae:
                RxActivityUtils.skipActivity(mContext, MessagePushActivity.class);
                break;
            case R.id.ll_setting_ClearCache:
                showPhotoDialog();
                break;
            case R.id.ll_setting_help:
                mBundle.putString("mTitle", "帮助中心");
                mBundle.putString("mContent", "help ");
                RxActivityUtils.skipActivity(mContext, HelpCenterActivity.class, mBundle);
                break;
            case R.id.ll_setting_aboutMe:
                mBundle.putString("mTitle", "关于我们");
                mBundle.putString("mContent", "about ");
                RxActivityUtils.skipActivity(mContext, HelpCenterActivity.class, mBundle);
                break;
            case R.id.ll_setting_suggestion:
                RxActivityUtils.skipActivity(mContext, SuggestionActivity.class);
                break;

            case R.id.tv_Exitlogin:
//                Logout();
                PreferenceUtils.setPrefString(mContext, Config.user_id, null);
                PreferenceUtils.setPrefString(mContext, Config.user_Email, null);
                RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                finish();
        }
    }


//    //退出登录
//    public void Logout() {
//        final Intent intent = new Intent();
//        UserInfo info = JMessageClient.getMyInfo();
//        if (null != info) {
//            SharePreferenceManager.setCachedUsername(info.getUserName());
//            if (info.getAvatarFile() != null) {
//                SharePreferenceManager.setCachedAvatarPath(info.getAvatarFile().getAbsolutePath());
//            }
//            JMessageClient.logout();
//            PreferenceUtils.setPrefString(mContext, Config.user_id, null);
//            PreferenceUtils.setPrefString(mContext, Config.user_Email, null);
//            RxActivityUtils.skipActivity(mContext, LoginActivity.class);
//            finish();
//        } else {
//            ToastUtil.shortToast(mContext, "退出失败");
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    private void showPhotoDialog() {

        View view = getLayoutInflater().inflate(R.layout.dialog_clear_choose, null);

        //初始化三个按钮
        Button btnPic = (Button) view.findViewById(R.id.btn_pic);
        Button btnCamera = (Button) view.findViewById(R.id.btn_camera);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        //响应点击事件
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                showToastS("清除缓存成功");
                DataCleanManager.clearAllCache(mContext);
                clearNum.setText("0.00k");
                hideLoading();
                photoDialog.dismiss();
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

}

