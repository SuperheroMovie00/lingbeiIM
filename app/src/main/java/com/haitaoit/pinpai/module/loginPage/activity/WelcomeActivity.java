package com.haitaoit.pinpai.module.loginPage.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.homePage.activity.MainActivity;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.GetBannerObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LZY on 2018/1/10.
 */

public class WelcomeActivity extends Activity implements Runnable {
    @BindView(R.id.ivHotImg)
    ImageView ivHotImg;
    private Context mContext;
    //是否是第一次使用
    private boolean isFirstUse;
    private String tag = "WelcomActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome_activity);
        ButterKnife.bind(this);
        mContext = this;
        initHotTop();
//        initView();;
        /**
         * 启动一个延迟线程
         */
        new Thread(this).start();
    }


    private void initHotTop() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "start_up");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetBanner(map, new MyCallBack<GetBannerObj>(mContext) {
            @Override
            public void onSuccessful(GetBannerObj response) {

                if (response.getErrCode() == 0) {
                    List<GetBannerObj.ResponseBean.DyadroollistBean> result = response.getResponse().getDyadroollist();
                    for (int j = 0; j < result.size(); j++) {
                        Glide.with(mContext).load(result.get(j).getImg_url()).error(R.mipmap.ic_launcher).into(ivHotImg);
                    }
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }


    public void run() {
        try {
            /**
             * 延迟5秒时间
             */
            Thread.sleep(5000);
            //读取SharedPreferences中需要的数据

            SharedPreferences preferences = getSharedPreferences("isFirstUse", MODE_PRIVATE);
            /**
             *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
             */
            isFirstUse = preferences.getBoolean("isFirstUse", true);

            if (isFirstUse) {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }
            //实例化Editor对象
            SharedPreferences.Editor editor = preferences.edit();
            //存入数据
            editor.putBoolean("isFirstUse", false);
            //提交修改
            editor.commit();


        } catch (InterruptedException e) {

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
