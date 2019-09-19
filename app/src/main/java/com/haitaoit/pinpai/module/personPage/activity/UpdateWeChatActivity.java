package com.haitaoit.pinpai.module.personPage.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.github.customview.MyEditText;
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
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/27.
 * 修改手机号码
 */

public class UpdateWeChatActivity extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.ed_mess)
    MyEditText edMess;
    @BindView(R.id.tv_next)
    MyTextView tvNext;

    @Override
    protected int getContentView() {
        return R.layout.update_wechat_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edMess.setText(getIntent().getStringExtra("userWeChat"));
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.rx_title, R.id.tv_next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_next:
                if (TextUtils.isEmpty(edMess.getText().toString())) {
                    showToastS("微信号不能为空");
                    return;
                }
                initPhone(edMess.getText().toString());

                break;
        }
    }

    private void initPhone(String file) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "3");
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));

        ApiRequest.PostUserMobileEdit(map, new PostUserInfoUpdatetItem(file), new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    Intent intent = new Intent();
                    intent.putExtra(Constant.IParam.mobile, getSStr(edMess));
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

}

