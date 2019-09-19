package com.haitaoit.pinpai.module.personPage.activity;

import android.view.View;
import android.widget.ImageView;

import com.github.customview.MyTextView;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.module.homePage.activity.MainActivity;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxTitle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 认证成功
 * Created by Administrator on 2017/11/1.
 */

public class AnthSuccessActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.iv_IDCardOne)
    ImageView ivIDCardOne;
    @BindView(R.id.tv_success_ok)
    MyTextView tvSuccessOk;

    @Override
    protected int getContentView() {
        return R.layout.auto_success_activity;
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


    @OnClick({R.id.rx_title, R.id.tv_success_ok})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_success_ok:
                RxActivityUtils.skipActivityAndFinish(mContext, MainActivity.class);
                break;
        }
    }
}
