package com.haitaoit.pinpai.module.personPage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.Constant;
import com.haitaoit.pinpai.module.personPage.network.request.PostUserInfoUpdatetItem;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/27.
 * 常住地
 */

public class ChangResidenceActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.ed_city)
    MyTextView edCity;
    @BindView(R.id.ed_address)
    MyTextView edAddress;
    @BindView(R.id.tv_next)
    MyTextView tvNext;

    public static final int REQUEST_SHOWADDRESS = 0x023;
    public static final int REQUEST_SHOWADDCity = 0x024;
    private String mCountryId;
    private String mCityId;
    @Override
    protected int getContentView() {
        return R.layout.update_changzhudi_activity;
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


    @OnClick({R.id.ed_city, R.id.ed_address, R.id.tv_next})
    public void onViewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ed_city:
                 intent = new Intent(mContext, CountryActivity.class);
                // 启动指定Activity并等待返回的结果，其中 REQUSET 是请求码，用于标识该请求
                startActivityForResult(intent, REQUEST_SHOWADDRESS);
                break;
            case R.id.ed_address:
                if (TextUtils.isEmpty(edCity.getText().toString())) {
                    showToastS("你选择的城市国家为空");
                    return;
                }
                intent = new Intent(mContext, CityActivity.class);
                intent.putExtra("mCountryId",mCountryId);
                // 启动指定Activity并等待返回的结果，其中 REQUSET 是请求码，用于标识该请求
                startActivityForResult(intent, REQUEST_SHOWADDCity);
                break;
            case R.id.tv_next:
                if (TextUtils.isEmpty(edCity.getText().toString())) {
                    showToastS("选择国家不能为空");
                    return;
                }

                if (TextUtils.isEmpty(edAddress.getText().toString())) {
                    showToastS("选择市区不能为空");
                    return;
                }
                initPhone(edCity.getText().toString()+edAddress.getText().toString());
                break;
        }
    }

    private void initPhone(final String file) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "5");
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));

        ApiRequest.PostUserMobileEdit(map, new PostUserInfoUpdatetItem(file), new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    Intent intent = new Intent();
                    intent.putExtra(Constant.IParam.update_change, file);
                    setResult(RESULT_OK, intent);
                    finish();
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == REQUEST_SHOWADDRESS && resultCode == RESULT_OK) {
                    edCity.setText(data.getStringExtra("mCountry"));
                    mCountryId = data.getStringExtra("mCountryId");
                }

                if (requestCode == REQUEST_SHOWADDCity && resultCode == RESULT_OK) {
                    edAddress.setText(data.getStringExtra("mINameCity"));
                    mCityId = data.getStringExtra("mCountryId");
                }
            }
        }


    }
}

