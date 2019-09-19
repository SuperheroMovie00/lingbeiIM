package com.haitaoit.pinpai.module.personPage.activity;

import android.util.Log;
import android.view.View;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.suke.widget.SwitchButton;
import com.vondear.rxtools.view.RxTitle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 消息退送
 * Created by Administrator on 2017/11/1.
 */

public class MessagePushActivity extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;

    @Override
    protected int getContentView() {
        return R.layout.message_push_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked){
                   Log.e("开口我阿凯","开口我阿凯");
                }else{
                    Log.e("官宦关关关","官宦关关关");
                }

            }
        });

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.rx_title})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
        }
    }
}
