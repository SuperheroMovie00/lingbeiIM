package com.haitaoit.pinpai.module.personPage.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.module.homePage.activity.MainActivity;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxTitle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 认证失败
 * Created by Administrator on 2017/11/1.
 */

public class AnthErrorActivity extends BaseActivity {


    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.iv_IDCardOne)
    ImageView ivIDCardOne;
    @BindView(R.id.tv_Again)
    MyTextView tvAgain;
    @BindView(R.id.tv_BackIndex)
    MyTextView tvBackIndex;

    @Override
    protected int getContentView() {
        return R.layout.auto_error_activity;
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


    @OnClick({R.id.tv_Again, R.id.tv_BackIndex})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Again:
                if ("个人".equals(PreferenceUtils.getPrefString(mContext, Config.user_type, ""))) {
                    RxActivityUtils.skipActivity(mContext, PersonAuthActivity.class);
                } else {
                    RxActivityUtils.skipActivity(mContext, CompanyAuthActivity.class);
                }
                break;
            case R.id.tv_BackIndex:
                RxActivityUtils.skipActivityAndFinish(mContext, MainActivity.class);
                break;
        }
    }
}
