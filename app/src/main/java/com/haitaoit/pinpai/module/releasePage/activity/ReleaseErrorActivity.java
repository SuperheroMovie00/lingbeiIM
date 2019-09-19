package com.haitaoit.pinpai.module.releasePage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.module.personPage.activity.CompanyAuthActivity;
import com.haitaoit.pinpai.module.personPage.activity.PersonAuthActivity;
import com.haitaoit.pinpai.module.personPage.activity.SupplierActivity;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 认证Error
 * Created by Administrator on 2017/11/1.
 */

public class ReleaseErrorActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.iv_IDCardOne)
    ImageView ivIDCardOne;
    @BindView(R.id.tv_success_ok)
    MyTextView tvSuccessOk;
    @BindView(R.id.tv_RenzhengCode)
    TextView tvRenzhengCode;

    @Override
    protected int getContentView() {
        return R.layout.release_error_activity;
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
        if (getIntent().getStringExtra("mType").equals("1")) {
            tvRenzhengCode.setText("你还没有进行认证！");
        } else {
            tvRenzhengCode.setText("你认证失败了，请重新提交认证！");
        }

    }


    @OnClick({R.id.rx_title, R.id.tv_success_ok})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_success_ok:
                if ("个人".equals(PreferenceUtils.getPrefString(mContext, Config.user_type, ""))) {
                    RxActivityUtils.skipActivity(mContext, PersonAuthActivity.class);
                } else {
                    RxActivityUtils.skipActivity(mContext, CompanyAuthActivity.class);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
