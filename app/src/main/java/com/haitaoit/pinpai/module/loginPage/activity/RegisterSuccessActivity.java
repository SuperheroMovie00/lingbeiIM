package com.haitaoit.pinpai.module.loginPage.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.customview.MyCheckBox;
import com.github.customview.MyEditText;
import com.github.customview.MyRadioButton;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.ExitApplication;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.add.activity.FinishRegisterActivity;
import com.haitaoit.pinpai.add.activity.MainActivity;
import com.haitaoit.pinpai.add.database.UserEntry;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.loginPage.network.ApiRequest;
import com.haitaoit.pinpai.module.loginPage.network.response.GetCodeEmailObj;
import com.haitaoit.pinpai.module.loginPage.network.response.GetRegisterOkObj;
import com.haitaoit.pinpai.module.personPage.activity.CompanyAuthActivity;
import com.haitaoit.pinpai.module.personPage.activity.MyMessageActivity;
import com.haitaoit.pinpai.module.personPage.activity.PersonAuthActivity;
import com.haitaoit.pinpai.module.personPage.activity.SupplierActivity;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.utils.HandleResponseCode;
import com.haitaoit.pinpai.utils.SharePreferenceManager;
import com.haitaoit.pinpai.utils.ThreadUtil;
import com.haitaoit.pinpai.utils.ToastUtil;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by Administrator on 2017/10/30.
 */

public class RegisterSuccessActivity extends BaseActivity {


    @BindView(R.id.ed_register_name)
    MyEditText edRegisterName;
    @BindView(R.id.ed_register_password)
    MyEditText edRegisterPassword;
    @BindView(R.id.ck_person)
    MyRadioButton ckPerson;
    @BindView(R.id.ck_company)
    MyRadioButton ckCompany;
    @BindView(R.id.tv_ok_register)
    MyTextView tvOkRegister;
    @BindView(R.id.tv_User_XieYi)
    TextView tvUserXieYi;

    private String mEmail;
    private String mUserType;

    String userId;
    String password;

    @Override
    protected int getContentView() {
        return R.layout.register_success_activity;
    }

    @Override
    protected void initView() {
        mEmail = getIntent().getStringExtra("mEmial");
        edRegisterPassword.addTextChangedListener(new MyTextChangedListener());

    }


    class MyTextChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.e("MyEditTextChangeListener", "beforeTextChanged---" + charSequence.toString());
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.e("MyEditTextChangeListener", "onTextChanged---" + charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.e("MyEditTextChangeListener", "afterTextChanged---");
            if (!TextUtils.isEmpty(edRegisterName.getText().toString().trim())||!TextUtils.isEmpty(edRegisterPassword.getText().toString().trim())) {
                tvOkRegister.setSolidColor(Color.parseColor("#fb7f77"));
                tvOkRegister.setBorderWidth(0);
                tvOkRegister.complete();
            }
        }
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    protected void initRxBus() {
        super.initRxBus();
    }

    @OnClick({R.id.ck_person, R.id.ck_company, R.id.tv_ok_register, R.id.tv_User_XieYi})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ck_person:
                mUserType = "1";
                break;
            case R.id.ck_company:
                mUserType = "2";
                break;
            case R.id.tv_ok_register:
                String phone = edRegisterName.getText().toString();
                String pwd = edRegisterPassword.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToastS("你输入的昵称为空");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    showToastS("你输入的密码为空");
                    return;
                }

                if (pwd.length()<6||pwd.length()>12) {
                    showToastS("密码长度为6-12个字符");
                    return;
                }

                if (TextUtils.isEmpty(mUserType)) {
                    showToastS("你选择的用户类型为空");
                    return;
                }

                initRegisterEmail();

                break;
            case R.id.tv_User_XieYi:
                RxActivityUtils.skipActivity(mContext, UserXieYiActivty.class);
                break;
        }
    }


    private void initRegisterEmail() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("email", mEmail);
        map.put("nick_name", edRegisterName.getText().toString());
        map.put("password", edRegisterPassword.getText().toString());
        map.put("type", mUserType);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetRegisterEmail(map, new MyCallBack<GetRegisterOkObj>(mContext) {
            @Override
            public void onSuccessful(GetRegisterOkObj response) {

                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    initIMRegister(response.getResponse().getUser_name(), "123456");
                    Bundle mBundle = new Bundle();
                    mBundle.putString("mUserEmail", response.getResponse().getEmail());
                    mBundle.putString("mUserId", response.getResponse().getUser_id());
                    RxActivityUtils.skipActivityAndFinish(mContext, LoginActivity.class, mBundle);
                } else if (response.getErrCode() == 3) {
                    showToastS(response.getErrMsg());
                    initIMRegister(response.getResponse().getUser_name(), "123456");
                    Bundle mBundleEmail = new Bundle();
                    mBundleEmail.putString("mUserEmail", response.getResponse().getEmail());
                    RxActivityUtils.skipActivityAndFinish(mContext, LoginActivity.class, mBundleEmail);
                } else if (response.getErrCode() == 1) {
                    initIMRegister(response.getResponse().getUser_name(), "123456");
                    showToastS(response.getErrMsg());
                    RxActivityUtils.skipActivityAndFinish(mContext, LoginActivity.class);
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    private void initIMRegister(String userName, String userPass) {
        Log.e("responseCode===============  000//登陆", userName + userPass);
        JMessageClient.register(userName, userPass, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

                if (i == 0) {

                    SharePreferenceManager.setRegisterName(userId);
                    SharePreferenceManager.setRegistePass(password);
                    initRxImM();
                } else {
                    HandleResponseCode.onHandle(mContext, i, false);
                }
            }
        });
    }

    private void initRxImM() {
        final String userId = SharePreferenceManager.getRegistrName();
        final String password = SharePreferenceManager.getRegistrPass();
        SharePreferenceManager.setRegisterUsername(userId);
        JMessageClient.login(userId, password, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                if (responseCode == 0) {
                    String username = JMessageClient.getMyInfo().getUserName();
                    String appKey = JMessageClient.getMyInfo().getAppKey();
                    UserEntry user = UserEntry.getUser(username, appKey);
                    if (null == user) {
                        user = new UserEntry();
                        user.save();
                    }

//                    String nickName = edRegisterName.getText().toString();
//
//                    UserInfo myUserInfo = JMessageClient.getMyInfo();
//                    if (myUserInfo == null) {
//                        myUserInfo.setNickname(nickName);
//                    }
//                    //注册时候更新昵称
//                    JMessageClient.updateMyInfo(UserInfo.Field.nickname, myUserInfo, new BasicCallback() {
//                        @Override
//                        public void gotResult(final int status, String desc) {
//                            //更新跳转标志
//                            SharePreferenceManager.setCachedFixProfileFlag(false);
//                        }
//                    });

//                    //注册时更新头像
//                    final String avatarPath = SharePreferenceManager.getRegisterAvatarPath();
//                    if (avatarPath != null) {
//                        ThreadUtil.runInThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                JMessageClient.updateUserAvatar(new File(avatarPath), new BasicCallback() {
//                                    @Override
//                                    public void gotResult(int responseCode, String responseMessage) {
//                                        if (responseCode == 0) {
//                                            SharePreferenceManager.setCachedAvatarPath(avatarPath);
//                                        }
//                                    }
//                                });
//                            }
//                        });
//                    } else {
//                        SharePreferenceManager.setCachedAvatarPath(null);
//                    }
                }
            }
        });
    }
}
