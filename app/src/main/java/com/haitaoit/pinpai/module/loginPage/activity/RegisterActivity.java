package com.haitaoit.pinpai.module.loginPage.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.customview.MyEditText;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.loginPage.network.ApiRequest;
import com.haitaoit.pinpai.module.loginPage.network.response.GetCodeEmailObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.RegexUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jiguang.share.android.api.AuthListener;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.model.AccessTokenInfo;
import cn.jiguang.share.android.model.BaseResponseInfo;
import cn.jiguang.share.android.model.UserInfo;
import cn.jiguang.share.android.utils.Logger;

/**
 * Created by Administrator on 2017/10/30.
 */

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ed_Register_email)
    MyEditText edRegisterEmail;
    @BindView(R.id.tv_ok_register)
    MyTextView tvOkRegister;
    @BindView(R.id.ll_weChat_login)
    MyLinearLayout llWeChatLogin;
    @BindView(R.id.tv_User_XieYi)
    TextView tvUserXieYi;

    private List<String> dataList= new ArrayList<String>();
    private static final String TAG="SelectPlatActivity";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String toastMsg = (String) msg.obj;
            Toast.makeText(RegisterActivity.this, toastMsg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected int getContentView() {
        return R.layout.register_activity;
    }

    @Override
    protected void initView() {
        List<String> platformList = JShareInterface.getPlatformList();

//        if (platformList == null || platformList.isEmpty()) {
//            Toast.makeText(this, "请检查配置文件是否正确", Toast.LENGTH_SHORT).show();
//            finish();
//            return;
//        }

        dataList.addAll(platformList);

    }

    @Override
    protected void initData() {
        edRegisterEmail.addTextChangedListener(new MyTextChangedListener());

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
            if (!TextUtils.isEmpty(edRegisterEmail.getText().toString().trim())) {
                tvOkRegister.setSolidColor(Color.parseColor("#fb7f77"));
                tvOkRegister.setBorderWidth(0);
                tvOkRegister.complete();
            }
        }
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

    @OnClick({R.id.iv_back, R.id.tv_ok_register, R.id.ll_weChat_login, R.id.tv_User_XieYi})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ok_register:
                String phone = edRegisterEmail.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToastS("输入你的邮箱地址不能为空");
                    return;
                }

                if (!RegexUtils.isEmail(edRegisterEmail.getText().toString().trim())) {
                    showToastS("你输入的邮箱不合法");
                    return;
                }


                initCodeEmail();

                break;
            case R.id.ll_weChat_login:
               initLogin();
                break;
            case R.id.tv_User_XieYi:
                RxActivityUtils.skipActivity(mContext, UserXieYiActivty.class);
                break;
        }
    }

    private void initLogin() {
        final String platform = dataList.get(0);
        Log.e(TAG,platform);
        if (!JShareInterface.isAuthorize(platform)) {
            Log.e(TAG,platform+"444444444");
            JShareInterface.authorize(platform, mAuthListener);
        } else {
            Log.e(TAG,platform+"wwwwwwwwwwwww");
            JShareInterface.removeAuthorize(platform, mAuthListener);
        }

        JShareInterface.getUserInfo(platform, mAuthListener);



    }


    /**
     * 授权、获取个人信息回调
     * action ：Platform.ACTION_AUTHORIZING 授权
     * Platform.ACTION_USER_INFO 获取个人信息
     */
    AuthListener mAuthListener = new AuthListener() {
        @Override
        public void onComplete(Platform platform, int action, BaseResponseInfo data) {
            Log.e(TAG, "onComplete:" + platform + ",action:" + action + ",data:" + data);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    if (data instanceof AccessTokenInfo) {        //授权信息
                        String token = ((AccessTokenInfo) data).getToken();//token
                        long expiration = ((AccessTokenInfo) data).getExpiresIn();//token有效时间，时间戳
                        String refresh_token = ((AccessTokenInfo) data).getRefeshToken();//refresh_token
                        String openid = ((AccessTokenInfo) data).getOpenid();//openid
                        //授权原始数据，开发者可自行处理
                        String originData = data.getOriginData();
                        toastMsg = "授权成功:" + data.toString();
                        Log.e(TAG, "openid:" + openid + ",token:" + token + ",expiration:" + expiration + ",refresh_token:" + refresh_token);
                        Log.e(TAG,  "originData:" + originData);
                    }
                    break;
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    toastMsg = "删除授权成功";
                    break;
                case Platform.ACTION_USER_INFO:
                    if (data instanceof UserInfo) {      //第三方个人信息
                        String openid = ((UserInfo) data).getOpenid();  //openid
                        String name = ((UserInfo) data).getName();  //昵称
                        String imageUrl = ((UserInfo) data).getImageUrl();  //头像url
                        int gender = ((UserInfo) data).getGender();//性别, 1表示男性；2表示女性
                        //个人信息原始数据，开发者可自行处理
                        String originData = data.getOriginData();
                        toastMsg = "获取个人信息成功:" + data.toString();
                        Log.e(TAG,  "openid:" + openid + ",name:" + name + ",gender:" + gender + ",imageUrl:" + imageUrl);
                        Log.e(TAG, "originData:" + originData);
                    }
                    break;
            }
            if (handler != null) {
                Message msg = handler.obtainMessage(1);
                msg.obj = toastMsg;
                msg.sendToTarget();
            }
        }

        @Override
        public void onError(Platform platform, int action, int errorCode, Throwable error) {
            Log.e(TAG,  "onError:" + platform + ",action:" + action + ",error:" + error);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    toastMsg = "授权失败";
                    break;
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    toastMsg = "删除授权失败";
                    break;
                case Platform.ACTION_USER_INFO:
                    toastMsg = "获取个人信息失败";
                    break;
            }
            if (handler != null) {
                Message msg = handler.obtainMessage(1);
                msg.obj = toastMsg + (error != null ? error.getMessage() : "") + "---" + errorCode;
                msg.sendToTarget();
            }
        }

        @Override
        public void onCancel(Platform platform, int action) {
            Logger.dd(TAG, "onCancel:" + platform + ",action:" + action);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    toastMsg = "取消授权";
                    break;
                // TODO: 2017/6/23 删除授权不存在取消
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    break;
                case Platform.ACTION_USER_INFO:
                    toastMsg = "取消获取个人信息";
                    break;
            }
            if (handler != null) {
                Message msg = handler.obtainMessage(1);
                msg.obj = toastMsg;
                msg.sendToTarget();
            }
        }
    };
    private void initCodeEmail() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("email", edRegisterEmail.getText().toString().trim());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetRegisterCode(map, new MyCallBack<GetCodeEmailObj>(mContext) {
            @Override
            public void onSuccessful(GetCodeEmailObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    Bundle mBundle = new Bundle();
                    mBundle.putString("mEmial", edRegisterEmail.getText().toString().trim());
                    mBundle.putString("mCode", response.getResponse().getCode() + "");
                    RxActivityUtils.skipActivity(mContext, RegisterNextActivity.class, mBundle);
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }


}
