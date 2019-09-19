package com.haitaoit.pinpai.module.personPage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

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
import com.haitaoit.pinpai.tools.RegexUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/27.
 * 修改手机号码
 */

public class UpdatePhoneActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.ed_mess)
    EditText edMess;
    @BindView(R.id.tv_next)
    MyTextView tvNext;

    @Override
    protected int getContentView() {
        return R.layout.update_phone_activity;
    }

    @Override
    protected void initView() {
        edMess.setText(getIntent().getStringExtra("userPhone"));
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rxTitle.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edMess.getText().toString())) {
                    showToastS("手机号不能为空");
                    return;
                }

                if (!RegexUtils.isMobilePhoneNumber(edMess.getText().toString())) {
                    showToastS("你输入的手机号不合法");
                    return;
                }

                initPhone(edMess.getText().toString());
            }
        });
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


                break;
        }
    }

    private void initPhone(String file) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "1");
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));

        ApiRequest.PostUserMobileEdit(map, new PostUserInfoUpdatetItem(file), new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    Intent intent = new Intent();
                    intent.putExtra(Constant.IParam.name, getSStr(edMess));
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
}
