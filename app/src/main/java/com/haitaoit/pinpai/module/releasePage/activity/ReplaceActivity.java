package com.haitaoit.pinpai.module.releasePage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.homePage.network.response.GetIdentObj;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.module.personPage.activity.AnthErrorActivity;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/4.
 */

public class ReplaceActivity extends BaseActivity {
    @BindView(R.id.tv_BackIndex)
    ImageView tvBackIndex;
    @BindView(R.id.my_tv_replace_source)
    MyTextView myTvReplaceSource;
    @BindView(R.id.my_tv_replace_buy)
    MyTextView myTvReplaceBuy;
    private String mIsIdent;

    @Override
    protected int getContentView() {
        return R.layout.replace_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
        RxActivityUtils.skipActivity(mContext, LoginActivity.class);
    }else{
            initIdent();
        }

    }

    private void initIdent() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));
        com.haitaoit.pinpai.module.homePage.network.ApiRequest.GetIdent(map, new MyCallBack<GetIdentObj>(mContext) {
            @Override
            public void onSuccessful(GetIdentObj response) {
                if (response.getErrCode() == 0) {
                    mIsIdent = response.getResponse().getIs_certified() + "";
                } else {
                    showToastS(response.getErrMsg());
                }
            }
        });
    }

    @OnClick({R.id.tv_BackIndex, R.id.my_tv_replace_source, R.id.my_tv_replace_buy})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_BackIndex:
                finish();
                break;
            case R.id.my_tv_replace_source:
                Bundle mBundle = new Bundle();
                if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                } else if (mIsIdent.equals("1")) {
                    mBundle.putString("mType", "1");
                    RxActivityUtils.skipActivity(mContext, ReleaseErrorActivity.class, mBundle);
                } else if (mIsIdent.equals("4")) {
                    mBundle.putString("mType", "4");
                    RxActivityUtils.skipActivity(mContext, AnthErrorActivity.class, mBundle);
                } else {
                    RxActivityUtils.skipActivity(mContext, ReleaseSourceActivity.class);
                }
              

                break;
            case R.id.my_tv_replace_buy:
                Bundle mBundle2 = new Bundle();
                if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                } else if (mIsIdent.equals("1")) {
                    mBundle2.putString("mType", "1");
                    RxActivityUtils.skipActivity(mContext, ReleaseErrorActivity.class, mBundle2);
                } else if (mIsIdent.equals("4")) {
                    mBundle2.putString("mType", "4");
                    RxActivityUtils.skipActivity(mContext, AnthErrorActivity.class, mBundle2);
                } else {
                    RxActivityUtils.skipActivity(mContext, ReleaseBegBuyActivity.class);
                }
                break;
        }
    }
}
