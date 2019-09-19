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
import com.haitaoit.pinpai.module.loginPage.network.ApiRequest;
import com.haitaoit.pinpai.module.loginPage.network.response.GetCodeEmailObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.tools.RegexUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/4.
 */

public class ForgetPassActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ed_Register_email)
    MyEditText edRegisterEmail;
    @BindView(R.id.tv_ok_register)
    MyTextView tvOkRegister;
    @BindView(R.id.tv_User_XieYi)
    TextView tvUserXieYi;

    @Override
    protected int getContentView() {
        return R.layout.forgrt_pass_activity;
    }

    @Override
    protected void initView() {
            edRegisterEmail.setText(getIntent().getStringExtra("mUserEmail"));

        if (!TextUtils.isEmpty(getIntent().getStringExtra("mUserEmail"))) {
            tvOkRegister.setSolidColor(Color.parseColor("#fb7f77"));
            tvOkRegister.setBorderWidth(0);
            tvOkRegister.complete();
        }
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
    protected void initData() {

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
            case R.id.tv_User_XieYi:
                break;
        }
    }

    private void initCodeEmail() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("email",  edRegisterEmail.getText().toString().trim());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetFindPwdCode(map, new MyCallBack<GetCodeEmailObj>(mContext) {
            @Override
            public void onSuccessful(GetCodeEmailObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    Bundle mBundle = new Bundle();
                    mBundle.putString("mEmial", edRegisterEmail.getText().toString().trim());
                    mBundle.putString("mCode", response.getResponse().getCode()+"");
                    RxActivityUtils.skipActivity(mContext, ForgetPassNextActivity.class, mBundle);
                }
                else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }
}
