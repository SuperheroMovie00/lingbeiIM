package com.haitaoit.pinpai.module.loginPage.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.loginPage.network.ApiRequest;
import com.haitaoit.pinpai.module.loginPage.network.response.GetRegisterOkObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.tools.RegexUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码下一步   输入验证码
 * Created by Administrator on 2017/10/30.
 */

public class ForgetPassNextActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ed_register_email)
    MyEditText edRegisterEmail;
    @BindView(R.id.ed_register_code)
    MyEditText edRegisterCode;
    @BindView(R.id.ed_forget_pass)
    MyEditText edForgetPass;
    @BindView(R.id.ed_forget_pass_again)
    MyEditText edForgetPassAgain;
    @BindView(R.id.tv_ok_register)
    MyTextView tvOkRegister;
    @BindView(R.id.tv_User_XieYi)
    TextView tvUserXieYi;

    @Override
    protected int getContentView() {
        return R.layout.forget_next_activity;
    }

    @Override
    protected void initView() {
        edRegisterEmail.setText(getIntent().getStringExtra("mEmial"));

        edForgetPassAgain.addTextChangedListener(new MyTextChangedListener());

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
            if (!TextUtils.isEmpty(edRegisterEmail.getText().toString().trim()) ||
                    !TextUtils.isEmpty(edRegisterCode.getText().toString().trim()) ||
                    !TextUtils.isEmpty(edForgetPass.getText().toString().trim()) ||
                    !TextUtils.isEmpty(edForgetPassAgain.getText().toString().trim())
                    ) {
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
    protected void initRxBus() {
        super.initRxBus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.iv_back, R.id.tv_ok_register, R.id.tv_User_XieYi})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ok_register:
                String phone = edRegisterEmail.getText().toString();
                String codeEmail = edRegisterCode.getText().toString();
                String pass1 = edForgetPass.getText().toString();
                String pass2 = edForgetPassAgain.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToastS("你输入的邮箱地址为空");
                    return;
                }

                if (!RegexUtils.isEmail(phone)) {
                    showToastS("你输入的邮箱不合法");
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    showToastS("你输入的邮箱验证码为空");
                    return;
                }

                if (!getIntent().getStringExtra("mCode").equals(codeEmail)) {
                    showToastS("你输入的邮箱验证码错误，请重新输入");
                    return;
                }


                if (TextUtils.isEmpty(pass1)) {
                    showToastS("你输入的第一个密码为空");
                    return;
                }

                if (!pass1.equals(pass2)) {
                    showToastS("你输入的密码不一样，请重新输入");
                    return;
                }

                if (TextUtils.isEmpty(pass2)) {
                    showToastS("你输入的第二个密码为空");
                    return;
                }

                if (pass1.length() < 6 || pass1.length() > 12) {
                    showToastS("密码长度为6-12个字符");
                    return;
                }

                if (pass2.length() < 6 || pass2.length() > 12) {
                    showToastS("密码长度为6-12个字符");
                    return;
                }
                initForget();
                break;
            case R.id.tv_User_XieYi:
                RxActivityUtils.skipActivity(mContext, UserXieYiActivty.class);
                break;
        }
    }

    private void initForget() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", edRegisterEmail.getText().toString());
        map.put("password", edForgetPassAgain.getText().toString());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetFindPwdEdit(map, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    PreferenceUtils.setPrefString(mContext, Config.user_Email, edRegisterEmail.getText().toString() + "");
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }
}
