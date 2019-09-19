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
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.tools.RegexUtils;
import com.vondear.rxtools.RxActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册下一步   输入验证码
 * Created by Administrator on 2017/10/30.
 */

public class RegisterNextActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ed_register_email)
    MyTextView edRegisterEmail;
    @BindView(R.id.ed_register_code)
    MyEditText edRegisterCode;
    @BindView(R.id.tv_ok_register)
    MyTextView tvOkRegister;
    @BindView(R.id.ll_weChat_login)
    MyLinearLayout llWeChatLogin;
    @BindView(R.id.tv_User_XieYi)
    TextView tvUserXieYi;

    @Override
    protected int getContentView() {
        return R.layout.register_next_activity;
    }

    @Override
    protected void initView() {
        edRegisterEmail.setText(getIntent().getStringExtra("mEmial"));
        if (!TextUtils.isEmpty(getIntent().getStringExtra("mEmial"))) {
            tvOkRegister.setSolidColor(Color.parseColor("#fb7f77"));
            tvOkRegister.setBorderWidth(0);
            tvOkRegister.complete();
        }
        edRegisterCode.addTextChangedListener(new MyTextChangedListener());

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
            if (!TextUtils.isEmpty(edRegisterCode.getText().toString().trim())) {
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

    @OnClick({R.id.iv_back, R.id.tv_ok_register, R.id.ll_weChat_login, R.id.tv_User_XieYi})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ok_register:
                String phone = edRegisterEmail.getText().toString();
                String pwd = edRegisterCode.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToastS("你输入的邮箱地址为空");
                    return;
                }

                if (!RegexUtils.isEmail(edRegisterEmail.getText().toString().trim())) {
                    showToastS("你输入的邮箱不合法");
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {
                    showToastS("你输入的邮箱验证码为空");
                    return;
                }

                if (!getIntent().getStringExtra("mCode").equals(edRegisterCode.getText().toString())) {
                    showToastS("你输入的邮箱验证码错误，请重新输入");
                    return;
                }

                Bundle mBundle = new Bundle();
                mBundle.putString("mEmial", getIntent().getStringExtra("mEmial"));
                RxActivityUtils.skipActivity(mContext, RegisterSuccessActivity.class,mBundle);
                break;
            case R.id.ll_weChat_login:
                break;
            case R.id.tv_User_XieYi:
                RxActivityUtils.skipActivity(mContext, UserXieYiActivty.class);
                break;
        }
    }
}
