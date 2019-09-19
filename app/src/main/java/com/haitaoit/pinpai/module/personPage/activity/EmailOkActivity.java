package com.haitaoit.pinpai.module.personPage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.customview.MyTextView;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.module.homePage.activity.MainActivity;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邮箱验证成功
 * Created by LZY on 2017/12/25.
 */

public class EmailOkActivity extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.tv_EmailNum)
    TextView tvEmailNum;
    @BindView(R.id.tv_IDO)
    MyTextView tvIDO;

    @Override
    protected int getContentView() {
        return R.layout.email_ok_activity;
    }

    @Override
    protected void initView() {
        tvEmailNum.setText("您的邮箱："+getIntent().getStringExtra("edMess"));

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

    @OnClick({R.id.rx_title, R.id.tv_IDO})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_IDO:
                if ("1".equals(getIntent().getStringExtra("useCompany"))){
                    Bundle mBundleEmail = new Bundle();
                    mBundleEmail.putString("mUserEmail",getIntent().getStringExtra("useCompany"));
                    RxActivityUtils.skipActivityAndFinish(mContext, MainActivity.class,mBundleEmail);
                }else {
                    Bundle mBundleEmail = new Bundle();
                    mBundleEmail.putString("mUserEmail",getIntent().getStringExtra("useCompany"));
                    RxActivityUtils.skipActivityAndFinish(mContext, MainActivity.class);
                }

                break;
        }
    }
}
