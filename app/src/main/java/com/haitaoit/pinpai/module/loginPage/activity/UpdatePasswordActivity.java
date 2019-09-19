package com.haitaoit.pinpai.module.loginPage.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.homePage.activity.MainActivity;
import com.haitaoit.pinpai.module.loginPage.network.ApiRequest;
import com.haitaoit.pinpai.module.loginPage.network.response.GetLoginObj;
import com.haitaoit.pinpai.module.loginPage.network.response.GetUpdatePassObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 * Created by Administrator on 2017/10/31.
 */

public class UpdatePasswordActivity extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.et_update_pass)
    EditText etUpdatePass;
    @BindView(R.id.et_update_new_pass)
    EditText etUpdateNewPass;
    @BindView(R.id.et_update_again_pass)
    EditText etUpdateAgainPass;
    @BindView(R.id.tv_update_next)
    MyTextView tvUpdateNext;

    @Override
    protected int getContentView() {
        return R.layout.update_password_activity;
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

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rx_title, R.id.tv_update_next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_update_next:
                String UpdatePass = etUpdatePass.getText().toString();
                String NewPass = etUpdateNewPass.getText().toString();
                String AgainPass = etUpdateAgainPass.getText().toString();
                if (TextUtils.isEmpty(UpdatePass)) {
                    showToastS("现在的密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(NewPass)) {
                    showToastS("新密码不能为空");
                    return;
                }

                if (TextUtils.isEmpty(AgainPass)) {
                    showToastS("确认新密码不能为空");
                    return;
                }

                if (!AgainPass.equals(NewPass)) {
                    showToastS("新密码和确认密码不同");
                    return;
                }

                if (NewPass.length()<6||NewPass.length()>12) {
                    showToastS("密码长度为6-12个字符");
                    return;
                }

                if (AgainPass.length()<6||AgainPass.length()>12) {
                    showToastS("密码长度为6-12个字符");
                    return;
                }

                initUpdate();
                break;
        }
    }

    private void initUpdate() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id",   PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("password",  etUpdateAgainPass.getText().toString());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetPwdEdit(map, new MyCallBack<GetUpdatePassObj>(mContext) {
            @Override
            public void onSuccessful(GetUpdatePassObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                }
                else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

}
