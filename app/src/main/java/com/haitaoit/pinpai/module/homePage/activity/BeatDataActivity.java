package com.haitaoit.pinpai.module.homePage.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.github.customview.MyRadioButton;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.module.homePage.fragment.Other1Fragment;
import com.haitaoit.pinpai.module.homePage.fragment.Other2Fragment;
import com.haitaoit.pinpai.module.homePage.fragment.Other3Fragment;
import com.haitaoit.pinpai.module.homePage.fragment.TranRankingFragment;
import com.vondear.rxtools.view.RxTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/22.
 */

public class BeatDataActivity extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.rb_Beat_1)
    MyRadioButton rbBeat1;
    @BindView(R.id.rb_Beat_2)
    MyRadioButton rbBeat2;
    @BindView(R.id.rb_Beat_3)
    MyRadioButton rbBeat3;
    @BindView(R.id.rb_Beat_4)
    MyRadioButton rbBeat4;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;


    private MyRadioButton selectButton;

    TranRankingFragment mTranRankingFragment;
    Other1Fragment mOther1Fragment;
    Other2Fragment mOther2Fragment;
    Other3Fragment mOther3Fragment;

    @Override
    protected int getContentView() {
        return R.layout.beat_date_activity;
    }

    @Override
    protected void initView() {

        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.e("getIntent", getIntent().getStringExtra("HomeName"));
        if (getIntent().getStringExtra("HomeName").equals("交易排名")) {
            selectButton = rbBeat1;
            rbBeat1.setChecked(true);
            mTranRankingFragment = new TranRankingFragment().newInstance("交易排名");
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mTranRankingFragment).commit();

        } else if (getIntent().getStringExtra("HomeName").equals("发布排名")) {
            selectButton = rbBeat2;
            rbBeat2.setChecked(true);
            mOther1Fragment = new Other1Fragment().newInstance("发布排名");
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mOther1Fragment).commit();

        } else if (getIntent().getStringExtra("HomeName").equals("浏览排名")) {
            selectButton = rbBeat3;
            rbBeat3.setChecked(true);
            mOther2Fragment = new Other2Fragment().newInstance("浏览排名");
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mOther2Fragment).commit();

        } else {
            selectButton = rbBeat4;
            rbBeat4.setChecked(true);
            mOther3Fragment = new Other3Fragment().newInstance("关注排名");
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mOther3Fragment).commit();

        }


    }


    @Override
    protected void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rx_title, R.id.rb_Beat_1, R.id.rb_Beat_2, R.id.rb_Beat_3, R.id.rb_Beat_4, R.id.rg_main})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.rb_Beat_1:
                selectButton = rbBeat1;
                if (mTranRankingFragment == null) {
                    mTranRankingFragment = new TranRankingFragment().newInstance("交易排名");
                    getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mTranRankingFragment).commit();
                } else {
                    showFragment(mTranRankingFragment);
                }
                hideFragment(mOther1Fragment);
                hideFragment(mOther2Fragment);
                hideFragment(mOther3Fragment);
                break;
            case R.id.rb_Beat_2:
                selectButton = rbBeat2;
                if (mOther1Fragment == null) {
                    mOther1Fragment = new Other1Fragment().newInstance("发布排名");
                    getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mOther1Fragment).commit();
                } else {
                    showFragment(mOther1Fragment);
                }
                hideFragment(mTranRankingFragment);
                hideFragment(mOther2Fragment);
                hideFragment(mOther3Fragment);
                break;
            case R.id.rb_Beat_3:
                selectButton = rbBeat3;
                if (mOther2Fragment == null) {
                    mOther2Fragment = new Other2Fragment().newInstance("浏览排名");
                    getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mOther2Fragment).commit();
                } else {
                    showFragment(mOther2Fragment);
                }
                hideFragment(mTranRankingFragment);
                hideFragment(mOther1Fragment);
                hideFragment(mOther3Fragment);

                break;
            case R.id.rb_Beat_4:
                selectButton = rbBeat4;
                if (mOther3Fragment == null) {
                    mOther3Fragment = new Other3Fragment().newInstance("关注排名");
                    getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, mOther3Fragment).commit();
                } else {
                    showFragment(mOther3Fragment);
                }
                hideFragment(mTranRankingFragment);
                hideFragment(mOther1Fragment);
                hideFragment(mOther2Fragment);
                break;
            case R.id.rg_main:
                break;
        }
    }


}
