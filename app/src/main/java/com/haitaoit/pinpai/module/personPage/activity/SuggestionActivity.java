package com.haitaoit.pinpai.module.personPage.activity;

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
import com.haitaoit.pinpai.module.personPage.network.request.PostAddressUserItem;
import com.haitaoit.pinpai.module.personPage.network.request.PostComplainItem;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 意见反馈
 * Created by Administrator on 2017/11/1.
 */

public class SuggestionActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.et_feedback_title)
    EditText etFeedbackTitle;
    @BindView(R.id.et_feedback_content)
    EditText etFeedbackContent;
    @BindView(R.id.tv_feedback_commit)
    MyTextView tvFeedbackCommit;

    @Override
    protected int getContentView() {
        return R.layout.suggestion_activity;
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


    @OnClick({R.id.rx_title, R.id.tv_feedback_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_feedback_commit:
                if (TextUtils.isEmpty(etFeedbackTitle.getText().toString().trim())) {
                    showToastS("标题能为空");
                    return;
                }
                if (TextUtils.isEmpty(etFeedbackContent.getText().toString().trim())) {
                    showToastS("内容不能为空");
                    return;
                }

                initDate();
                break;
        }
    }

    private void initDate() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));
        PostComplainItem bean = new PostComplainItem();
        bean.setTitle(etFeedbackTitle.getText().toString());
        bean.setContent(etFeedbackContent.getText().toString());

        ApiRequest.PostUserComplain(map, bean, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    RxActivityUtils.skipActivityAndFinish(mContext, MySettingActivity.class);
                } else {
                    showToastS(response.getErrMsg());
                }
            }
        });
    }

}
