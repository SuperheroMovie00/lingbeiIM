package com.haitaoit.pinpai.module.personPage.activity;

import android.os.Bundle;
import android.view.View;

import com.github.customview.MyCheckBox;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxTitle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 供应商认证
 * Created by Administrator on 2017/11/1.
 */

public class SupplierActivity extends BaseActivity {
    @BindView(R.id.cb_supper_Company)
    MyCheckBox cbSupperCompany;

    @BindView(R.id.cb_supper_Person)
    MyCheckBox cbSupperPerson;

    @BindView(R.id.tv_Next)
    MyTextView tvNext;

    @BindView(R.id.rx_title)
    RxTitle rxTitle;

    private String supperType = "1";
    private String mUserId,mUserEmail;

    @Override
    protected int getContentView() {
        return R.layout.supplier_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            mUserId =  getIntent().getStringExtra("mUserId");
        } else {
            mUserId= PreferenceUtils.getPrefString(mContext, Config.user_id, "");
        }

        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_Email, ""))) {
            mUserEmail =  getIntent().getStringExtra("mUserEmail");
        } else {
            mUserEmail= PreferenceUtils.getPrefString(mContext, Config.user_Email, "");
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.rx_title, R.id.cb_supper_Company, R.id.cb_supper_Person, R.id.tv_Next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.cb_supper_Company:
                supperType = "1";
                cbSupperPerson.setChecked(false);
                break;
            case R.id.cb_supper_Person:
                supperType = "2";
                cbSupperCompany.setChecked(false);
                break;
            case R.id.tv_Next:
                Bundle mBundle = new Bundle();


                if ("1".equals(supperType)) {
                    mBundle.putString("mUserEmail", mUserEmail);
                    mBundle.putString("mUserId", mUserId);
                    RxActivityUtils.skipActivity(mContext, CompanyAuthActivity.class, mBundle);
                } else {
                    mBundle.putString("mUserId", mUserId);
                    mBundle.putString("mUserEmail", mUserEmail);
                    RxActivityUtils.skipActivity(mContext, PersonAuthActivity.class,mBundle);
                }
                break;
        }
    }
}
