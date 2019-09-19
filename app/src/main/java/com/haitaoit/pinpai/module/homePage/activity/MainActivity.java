


package com.haitaoit.pinpai.module.homePage.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.github.customview.MyRadioButton;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.callback.FindEvent;
import com.haitaoit.pinpai.callback.FragmentEvent;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.findPage.fragment.FindPageFragment;
import com.haitaoit.pinpai.module.homePage.fragment.HomePageFragment;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.DateBeatJsonObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetIdentObj;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.module.messagePage.fragment.MessagePageFragment;
import com.haitaoit.pinpai.module.personPage.activity.SupplierActivity;
import com.haitaoit.pinpai.module.personPage.fragment.PersonPageFragment;
import com.haitaoit.pinpai.module.releasePage.activity.ReplaceActivity;
import com.haitaoit.pinpai.module.releasePage.fragment.ReplacePageFragment;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/25.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.layout_main_content)
    FrameLayout layoutMainContent;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.rb_home_page)
    MyRadioButton rbHomePage;
    @BindView(R.id.rb_find_page)
    MyRadioButton rbFindPage;
    @BindView(R.id.lin_replace_page)
    LinearLayout linReplacePage;
    @BindView(R.id.rb_message_page)
    MyRadioButton rbMessagePage;
    @BindView(R.id.rb_person_page)
    MyRadioButton rbPersonPage;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    private MyRadioButton selectButton;
    HomePageFragment mHomePageFragment;
    FindPageFragment mFindPageFragment;
    MessagePageFragment mMessagePageFragment;
    ReplacePageFragment mReplacePageFragment;
    PersonPageFragment mPersonPageFragment;

    private Dialog photoDialog;

    private static final int          MY_PERMISSIONS_REQUEST_CALL_CAMERA = 2;
    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    private              List<String> mPermissionList                    = new ArrayList<>();
    private              String[]     permissions                        = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECORD_AUDIO
    };



    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        selectButton = rbHomePage;
        mHomePageFragment = new HomePageFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mHomePageFragment).commit();
    }

    @Override
    protected void initData() {
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);

            } else {

                if (TextUtils.equals(permissions[i], Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                } else if (TextUtils.equals(permissions[i], Manifest.permission.READ_PHONE_STATE)) {
                    // 检查版本更新
                }
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(MainActivity.this, permissions, MY_PERMISSIONS_REQUEST_CALL_CAMERA);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @OnClick({R.id.rb_home_page, R.id.rb_find_page, R.id.lin_replace_page, R.id.rb_message_page, R.id.rb_person_page})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home_page:
                selectButton = rbHomePage;
                if (mHomePageFragment == null) {
                    mHomePageFragment = new HomePageFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mHomePageFragment).commit();
                } else {
                    showFragment(mHomePageFragment);
                }
                hideFragment(mFindPageFragment);
                hideFragment(mReplacePageFragment);
                hideFragment(mMessagePageFragment);
                hideFragment(mPersonPageFragment);
                break;
            case R.id.rb_find_page:
                selectButton = rbFindPage;
                if (mFindPageFragment == null) {
                    mFindPageFragment = new FindPageFragment().newInstance("1");
                    getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mFindPageFragment).commit();
                } else {
                    showFragment(mFindPageFragment);
                }
                hideFragment(mHomePageFragment);
                hideFragment(mReplacePageFragment);
                hideFragment(mMessagePageFragment);
                hideFragment(mPersonPageFragment);
                break;
            case R.id.lin_replace_page:
//                rbHomePage.setChecked(false);
//                rbFindPage.setChecked(false);
//                rbMessagePage.setChecked(false);
//                rbPersonPage.setChecked(false);


                RxActivityUtils.skipActivity(mContext, ReplaceActivity.class);

                break;
            case R.id.rb_message_page:
                if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                selectButton.setChecked(true);
            } else {
                    selectButton = rbMessagePage;
                    if (mMessagePageFragment == null) {
                        mMessagePageFragment = new MessagePageFragment();
                        getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mMessagePageFragment).commit();
                    } else {
                        showFragment(mMessagePageFragment);
                    }
                    hideFragment(mHomePageFragment);
                    hideFragment(mFindPageFragment);
                    hideFragment(mReplacePageFragment);
                    hideFragment(mPersonPageFragment);
                }
                break;
            case R.id.rb_person_page:

                if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                    selectButton.setChecked(true);
                } else {
                    selectButton = rbPersonPage;
                    if (mPersonPageFragment == null) {
                        mPersonPageFragment = new PersonPageFragment();
                        getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mPersonPageFragment).commit();
                    } else {
                        showFragment(mPersonPageFragment);
                    }
                    hideFragment(mHomePageFragment);
                    hideFragment(mFindPageFragment);
                    hideFragment(mReplacePageFragment);
                    hideFragment(mMessagePageFragment);
                }

                break;
        }
    }


    private void showPhotoDialog() {

        View view = getLayoutInflater().inflate(R.layout.dialog_replace_choose, null);


        photoDialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        photoDialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));
        Window window = photoDialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
//        wl.x = 0;
//        wl.y = getWindowManager().getDefaultDisplay().getHeight();
//        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.FILL_PARENT;
        wl.height = ViewGroup.LayoutParams.FILL_PARENT;
//        window.setGravity(Gravity.CENTER_VERTICAL);
        // 设置显示位置
        photoDialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
//        photoDialog.setCanceledOnTouchOutside(true);
        photoDialog.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FragmentEvent event) {
        Log.e("eventevent1111", event.id);
        rbFindPage.setChecked(true);
        selectButton = rbFindPage;
        EventBus.getDefault().post(new FindEvent(0, event.id));
        if (mFindPageFragment == null) {
            mFindPageFragment = new FindPageFragment().newInstance(event.id);
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mFindPageFragment).commit();
        } else {
            showFragment(mFindPageFragment);
        }
        hideFragment(mHomePageFragment);
        hideFragment(mReplacePageFragment);
        hideFragment(mMessagePageFragment);
        hideFragment(mPersonPageFragment);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            showToastS("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_CAMERA) {
            for (int i = 0; i < grantResults.length; i++) {

                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[i]);
                    if (TextUtils.equals(permissions[i], Manifest.permission.ACCESS_FINE_LOCATION)) {
                        if (showRequestPermission) {
                            Intent intent = new Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, 0);
                        }
                    } else {
                        if (showRequestPermission) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            //        Uri uri = Uri.fromParts("package", context.getPackageName());
                            //        intent.setData(uri);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                        }
                    }
                } else {

                    if (TextUtils.equals(permissions[i], Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    } else if (TextUtils.equals(permissions[i], Manifest.permission.READ_PHONE_STATE)) {
                        // 检查版本更新

                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
