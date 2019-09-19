package com.haitaoit.pinpai.module.personPage.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.loginPage.activity.RegisterNextActivity;
import com.haitaoit.pinpai.module.loginPage.network.response.GetCodeEmailObj;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.request.PostUserInfoUpdatetItem;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.tools.RegexUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/31.
 */

public class EmailActivity extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.ed_mess)
    MyEditText edMess;
    @BindView(R.id.tv_next)
    MyTextView tvNext;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.tv_get_code)
    MyTextView tvGetCode;

    private String mSendCode;

    @Override
    protected int getContentView() {
        return R.layout.email_activity;
    }

    @Override
    protected void initView() {
        edMess.setText(getIntent().getStringExtra("userEmail"));
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.rx_title, R.id.tv_next,R.id.tv_get_code})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                if (TextUtils.isEmpty(edMess.getText().toString())) {
                    showToastS("邮箱不能为空");
                    return;
                }

                if (!RegexUtils.isEmail(edMess.getText().toString())) {
                    showToastS("你输入的邮箱不合法");
                    return;
                }
                initSendCode();
                break;
            case R.id.tv_next:
                if (TextUtils.isEmpty(edMess.getText().toString())) {
                    showToastS("邮箱不能为空");
                    return;
                }

                if (!RegexUtils.isEmail(edMess.getText().toString())) {
                    showToastS("你输入的邮箱不合法");
                    return;
                }

                if (!mSendCode.equals(edCode.getText().toString().trim())){
                    showToastS("你输入的验证码有误");
                    return;
                }


                initPhone(edMess.getText().toString());

                break;
        }
    }

    private void initSendCode() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("email", edMess.getText().toString().trim());
        map.put("sign", GetSign.getSign(map));
        com.haitaoit.pinpai.module.loginPage.network.ApiRequest.GetRegisterCode(map, new MyCallBack<GetCodeEmailObj>(mContext) {
            @Override
            public void onSuccessful(GetCodeEmailObj response) {
                if (response.getErrCode() == 0) {
                    mSendCode = response.getResponse().getCode();
                    RxUtils.countDown(tvGetCode, 60000, 1000, "获取验证码");
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }


    private void initPhone(String file) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "4");
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));

        ApiRequest.PostUserMobileEdit(map, new PostUserInfoUpdatetItem(file), new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    Bundle bundle = new Bundle();
                    bundle.putString("edMess",edMess.getText().toString().trim());
                    bundle.putString("useCompany",getIntent().getStringExtra("useCompany"));
                    RxActivityUtils.skipActivityAndFinish(mContext, EmailOkActivity.class,bundle);
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