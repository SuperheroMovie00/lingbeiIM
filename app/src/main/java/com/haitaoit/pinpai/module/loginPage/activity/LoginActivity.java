package com.haitaoit.pinpai.module.loginPage.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyEditText;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.ExitApplication;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.add.database.UserEntry;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.homePage.activity.MainActivity;
import com.haitaoit.pinpai.module.loginPage.network.ApiRequest;
import com.haitaoit.pinpai.module.loginPage.network.response.GetLoginObj;
import com.haitaoit.pinpai.module.personPage.activity.AnthErrorActivity;
import com.haitaoit.pinpai.module.personPage.activity.CompanyAuthActivity;
import com.haitaoit.pinpai.module.personPage.activity.MyMessageActivity;
import com.haitaoit.pinpai.module.personPage.activity.PersonAuthActivity;
import com.haitaoit.pinpai.module.personPage.activity.SupplierActivity;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.tools.RegexUtils;
import com.haitaoit.pinpai.utils.DialogCreator;
import com.haitaoit.pinpai.utils.HandleResponseCode;
import com.haitaoit.pinpai.utils.SharePreferenceManager;
import com.haitaoit.pinpai.utils.ThreadUtil;
import com.haitaoit.pinpai.utils.ToastUtil;
import com.haitaoit.pinpai.utils.photochoose.LoadDialog;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by Administrator on 2017/10/30.
 */

public class LoginActivity extends BaseActivity

{

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_weChat_login)
    MyLinearLayout llWeChatLogin;
    @BindView(R.id.ed_login_phone)
    MyEditText edLoginPhone;
    @BindView(R.id.ed_login_password)
    MyEditText edLoginPassword;
    @BindView(R.id.tv_login)
    MyTextView tvLogin;
    @BindView(R.id.tv_Register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_User_XieYi)
    TextView tvUserXieYi;
    String userId;
    String password;
    private IWXAPI api;

    // handler对象，用来接收消息~

    public static Handler handler = new Handler() {
        @Override

        public void handleMessage(android.os.Message msg) {  //这个是发送过来的消息
            // 处理从子线程发送过来的消息
            int arg1 = msg.arg1;  //获取消息携带的属性值
            int arg2 = msg.arg2;
            int what = msg.what;
            Object result = msg.obj;
            System.out.println("-arg1--->>" + arg1);
            System.out.println("-arg2--->>" + arg2);
            System.out.println("-what--->>" + what);
            System.out.println("-result--->>" + result);
            Bundle bundle = msg.getData(); // 用来获取消息里面的bundle数据
            System.out.println("-getData--->>"
                    + bundle.getStringArray("strs").length);

        }

        ;

    };

    @Override
    protected int getContentView() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView() {


        SharePreferenceManager.setCachedFixProfileFlag(true);
        SharePreferenceManager.setRegisterAvatarPath(null);
        if (!RxDataUtils.isNullString(getIntent().getStringExtra("mUserEmail"))) {
            edLoginPhone.setText(getIntent().getStringExtra("mUserEmail"));
        }

        edLoginPassword.addTextChangedListener(new MyTextChangedListener());

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
            if (!TextUtils.isEmpty(edLoginPhone.getText().toString().trim()) || !TextUtils.isEmpty(edLoginPassword.getText().toString().trim())) {
                tvLogin.setSolidColor(Color.parseColor("#fb7f77"));
                tvLogin.setBorderWidth(0);
                tvLogin.complete();
            }
        }
    }
//代码中运用

    @Override
    protected void initData() {

    }


    @Override
    protected void initRxBus() {
        super.initRxBus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_weChat_login, R.id.tv_login, R.id.tv_Register, R.id.tv_forget_password
            , R.id.tv_User_XieYi})
    public void onViewClick(View view) {
        switch (view.getId()) {

            case R.id.tv_User_XieYi:
                RxActivityUtils.skipActivity(mContext, UserXieYiActivty.class);
                break;
            case R.id.iv_back:
                RxActivityUtils.skipActivityAndFinish(mContext, MainActivity.class);
                break;
            case R.id.ll_weChat_login:
//                api = WXAPIFactory.createWXAPI(this, Config.APP_ID_WX, true);
//                //将应用的appid注册到微信
//                api.registerApp(Config.APP_ID_WX);
//                SendAuth.Req req = new SendAuth.Req();
//                req.scope = "snsapi_userinfo";
////                req.scope = "snsapi_login";//提示 scope参数错误，或者没有scope权限
//                req.state = "wechat_sdk_微信登录";
//                api.sendReq(req);
//                initWeChat();
                break;
            case R.id.tv_login:
                String phone = edLoginPhone.getText().toString();
                String pwd = edLoginPassword.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToastS("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    showToastS("密码不能为空");
                    return;
                }
                if (!RegexUtils.isEmail(phone)) {
                    showToastS("你输入的邮箱不合法");
                    return;
                }

                if (pwd.length()<6||pwd.length()>12) {
                    showToastS("密码长度为6-12个字符");
                    return;
                }
                initLogin();
                break;
            case R.id.tv_Register:
                RxActivityUtils.skipActivity(mContext, RegisterActivity.class);
                break;
            case R.id.tv_forget_password:
                Bundle mBundle = new Bundle();
                mBundle.putString("mUserEmail", edLoginPhone.getText().toString().trim());
                RxActivityUtils.skipActivity(mContext, ForgetPassActivity.class, mBundle);

                break;
        }
    }

    private void initLogin() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", edLoginPhone.getText().toString());
        map.put("password", edLoginPassword.getText().toString());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetUserlogin(map, new MyCallBack<GetLoginObj>(mContext) {
            @Override
            public void onSuccessful(GetLoginObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    initIM(response.getResponse().getUser_name(),
                            "123456",
                            response.getResponse().getAvatar(),
                            response.getResponse().getNick_name());
                    PreferenceUtils.setPrefString(mContext, Config.user_Email, response.getResponse().getEmail() + "");
                    PreferenceUtils.setPrefString(mContext, Config.user_type, response.getResponse().getType() + "");
                    PreferenceUtils.setPrefString(mContext, Config.user_id, response.getResponse().getUser_id() + "");
                    PreferenceUtils.setPrefString(mContext, Config.avatar, response.getResponse().getAvatar() + "");

                    RxActivityUtils.skipActivity(mContext, MainActivity.class);

                } else if (response.getErrCode() == 3) {
                    showToastS(response.getErrMsg());
                    Bundle mBundle = new Bundle();
                    mBundle.putString("mUserId", response.getResponse().getUser_id());
                    mBundle.putString("mUserEmail", response.getResponse().getEmail());
                    mBundle.putString("mUserType", response.getResponse().getType());
                    RxActivityUtils.skipActivity(mContext, AnthErrorActivity.class, mBundle);
                } else if (response.getErrCode() == 4) {
                    /**
                     * 2是公司 1是个人
                     */
                    showToastS(response.getErrMsg());
                    Bundle mBundle = new Bundle();
                    mBundle.putString("mUserEmail", response.getResponse().getEmail());
                    mBundle.putString("mUserId", response.getResponse().getUser_id());
                    Log.e("111111mUserId", response.getResponse().getUser_id());
                    if (response.getResponse().getType().equals("个人")) {
                        RxActivityUtils.skipActivity(mContext, PersonAuthActivity.class, mBundle);
                    } else {
                        RxActivityUtils.skipActivity(mContext, CompanyAuthActivity.class, mBundle);
                    }
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }


        });
    }

    private void initIM(String userName, final String userPass, final String UserImage, final String Nike_name) {


        //登陆
        Log.e("responseCode===============  000//登陆", ExitApplication.registerOrLogin % 2 + "           registerOrLogin");
        Log.e("responseCode===============  000//登陆", ExitApplication.registerOrLogin % 2 + "           registerOrLogin" + userId + "     " + password);
        final Dialog dialog = DialogCreator.createLoadingDialog(mContext, mContext.getString(R.string.login_hint));
        dialog.show();
        JMessageClient.login(userName, userPass, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                dialog.dismiss();
                Log.e("responseCode===============  000//登陆", responseCode + "");
                Log.e("responseCode=============== 000  //登陆", responseCode + "");
                if (responseCode == 0) {
                    SharePreferenceManager.setCachedPsw(userPass);
                    UserInfo myInfo = JMessageClient.getMyInfo();
                    File avatarFile = new File(UserImage.toString());
                    Log.e("responseCode===============  000//JMessageClient", JMessageClient.getMyInfo() + "");
                    Log.e("responseCode=============== 000  //myInfo", avatarFile.toString() + "");
                    //登陆成功,如果用户有头像就把头像存起来,没有就设置null
                    if (avatarFile != null) {
                        SharePreferenceManager.setCachedAvatarPath(UserImage);
                    } else {
                        SharePreferenceManager.setCachedAvatarPath(null);
                    }
                    String username = myInfo.getUserName();
                    String appKey = myInfo.getAppKey();
                    Log.e("============= username", username + "");
                    Log.e("=============== appKey", appKey + "");
                    UserEntry user = UserEntry.getUser(username, appKey);
                    if (null == user) {
                        user = new UserEntry(username, appKey);
                        user.save();
                    }


                    UserInfo myUserInfo = JMessageClient.getMyInfo();
                    if (myUserInfo != null) {
                        myUserInfo.setNickname(Nike_name);
                    }
                    //注册时候更新昵称
                    JMessageClient.updateMyInfo(UserInfo.Field.nickname, myUserInfo, new BasicCallback() {
                        @Override
                        public void gotResult(final int status, String desc) {
                            //更新跳转标志
                            SharePreferenceManager.setCachedFixProfileFlag(false);
                        }
                    });


//                    SharePreferenceManager.setRegisterAvatarPath(UserImage);
//                    JMessageClient.updateUserAvatar(new File(UserImage), new BasicCallback() {
//                        @Override
//                        public void gotResult(int responseCode, String responseMessage) {
//                            if (responseCode == 0) {
//                                ToastUtil.shortToast(mContext, "更新成功");
//                            } else {
//                                ToastUtil.shortToast(mContext, "更新失败" + responseMessage);
//                            }
//                            Log.e("=============== appKey", responseCode + "responseMessage" + responseMessage);
//                        }
//                    });

                    //注册时更新头像
                    final String avatarPath = SharePreferenceManager.getRegisterAvatarPath();
                    if (avatarPath != null) {
                        ThreadUtil.runInThread(new Runnable() {
                            @Override
                            public void run() {
                                JMessageClient.updateUserAvatar(new File(UserImage), new BasicCallback() {
                                    @Override
                                    public void gotResult(int responseCode, String responseMessage) {
                                        if (responseCode == 0) {
                                            SharePreferenceManager.setCachedAvatarPath(avatarPath);
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        SharePreferenceManager.setCachedAvatarPath(null);
                    }
                } else {
                    ToastUtil.shortToast(mContext, "登陆失败" + responseMessage);
                }
            }
        });
//        注册
//        } else {
//            JMessageClient.register(userId, password, new BasicCallback() {
//                @Override
//                public void gotResult(int i, String s) {
//                    if (i == 0) {
//                        SharePreferenceManager.setRegisterName(userId);
//                        SharePreferenceManager.setRegistePass(password);
//                        mContext.startActivity(new Intent(mContext, MyMessageActivity.class));
//                        ToastUtil.shortToast(mContext, "注册成功");
//                    } else {
//                        HandleResponseCode.onHandle(mContext, i, false);
//                    }
//                }
//            });
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
            String headUrl = data.getStringExtra("headUrl");
//            Glide.with(LoginActivity.this).load(headUrl).into(ivHead);
//            Log.e("headUrlheadUrlheadUrlheadUrl", data.getStringExtra("oauth_openid") +
//                    data.getStringExtra("oauth_name") +
//                    data.getStringExtra("nick_name") +
//                    data.getStringExtra("headUrl"));
//            initWeChat(data.getStringExtra("oauth_openid"),
//                    data.getStringExtra("oauth_name"),
//                    data.getStringExtra("nick_name"),
//                    data.getStringExtra("headUrl"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initWeChat() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("oauth_openid", PreferenceUtils.getPrefString(mContext, Config.openid, ""));
        map.put("oauth_name", PreferenceUtils.getPrefString(mContext, Config.oauth_name, ""));
        map.put("nick_name", PreferenceUtils.getPrefString(mContext, Config.nick_name, ""));
        map.put("imgurl", PreferenceUtils.getPrefString(mContext, Config.imgurl, ""));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetOAuthConfirm(map, new MyCallBack<GetLoginObj>(mContext) {
            @Override
            public void onSuccessful(GetLoginObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    initIM(response.getResponse().getUser_name(),
                            "123456",
                            response.getResponse().getAvatar(),
                            response.getResponse().getNick_name());
                    PreferenceUtils.setPrefString(mContext, Config.user_Email, response.getResponse().getEmail() + "");
                    PreferenceUtils.setPrefString(mContext, Config.user_type, response.getResponse().getType() + "");
                    PreferenceUtils.setPrefString(mContext, Config.user_id, response.getResponse().getUser_id() + "");
                    PreferenceUtils.setPrefString(mContext, Config.avatar, response.getResponse().getAvatar() + "");
                    RxActivityUtils.skipActivity(mContext, MainActivity.class);

                }
            }


        });
    }

}