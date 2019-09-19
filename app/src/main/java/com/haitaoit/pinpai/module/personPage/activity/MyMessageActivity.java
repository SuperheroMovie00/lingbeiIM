package com.haitaoit.pinpai.module.personPage.activity;

import android.view.View;
import android.widget.FrameLayout;

import com.github.customview.MyRadioButton;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.add.activity.fragment.ConversationListFragment;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.module.personPage.fragment.PlatFormMessageFragment;
import com.vondear.rxtools.view.RxTitle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的消息
 * Created by Administrator on 2017/11/3.
 */

public class MyMessageActivity extends BaseActivity

{
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.cb_replace_sort)
    MyRadioButton cbReplaceSort;
    @BindView(R.id.cb_replace_hot)
    MyRadioButton cbReplaceHot;
    @BindView(R.id.layout_main_content)
    FrameLayout layoutMainContent;

    private MyRadioButton selectButton;
    ConversationListFragment mConversationListFragment;
    PlatFormMessageFragment mPlatFormMessageFragment;

    @Override
    protected int getContentView() {
        return R.layout.my_message_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setTitle("用户消息");
        selectButton = cbReplaceSort;
        mConversationListFragment = new ConversationListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mConversationListFragment).commit();

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.rx_title, R.id.cb_replace_sort, R.id.cb_replace_hot})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                finish();
                break;
            case R.id.cb_replace_sort:
                rxTitle.setTitle("用户消息");
                selectButton = cbReplaceSort;
                if (mConversationListFragment == null) {
                    mConversationListFragment = new ConversationListFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mConversationListFragment).commit();
                } else {
                    showFragment(mConversationListFragment);
                }
                hideFragment(mPlatFormMessageFragment);
                break;
            case R.id.cb_replace_hot:
                rxTitle.setTitle("平台消息");
                selectButton = cbReplaceHot;
                if (mPlatFormMessageFragment == null) {
                    mPlatFormMessageFragment = new PlatFormMessageFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mPlatFormMessageFragment).commit();
                } else {
                    showFragment(mPlatFormMessageFragment);
                }
                hideFragment(mConversationListFragment);
                break;
        }
    }
}
