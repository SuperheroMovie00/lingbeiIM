package com.haitaoit.pinpai.module.homePage.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.customview.MyEditText;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.DateBeatJsonObj;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.module.releasePage.activity.ReleaseBegBuyActivity;
import com.haitaoit.pinpai.module.releasePage.activity.ReleaseSourceActivity;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 没有找到* <p>
 * Created by Administrator on 2017/11/28.
 */

public class CantFindActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_search_goods)
    MyEditText tvSearchGoods;
    @BindView(R.id.iv_SearchClose)
    ImageView ivSearchClose;
    @BindView(R.id.tv_cancel)
    MyTextView tvCancel;
    @BindView(R.id.ll_date_max)
    MyLinearLayout llDateMax;
    @BindView(R.id.tv_AddShop)
    MyTextView tvAddShop;

    @Override
    protected int getContentView() {
        return R.layout.cant_search_activity;
    }

    @Override
    protected void initView() {
        tvSearchGoods.setText(getIntent().getStringExtra("findName"));
        initKey();

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


    @OnClick({R.id.iv_back, R.id.iv_SearchClose, R.id.tv_cancel, R.id.tv_AddShop})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_SearchClose:
                break;
            case R.id.tv_cancel:
                if (TextUtils.isEmpty(tvSearchGoods.getText().toString().trim())) {
                    showToastS("搜索内容为空");
                } else {
                    initSearch();
                }
                break;
            case R.id.tv_AddShop:
                if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                } else {
                    RxActivityUtils.skipActivity(mContext, ReleaseSourceActivity.class);
                }
                break;
        }
    }

    private void initKey() {
        tvSearchGoods.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        tvSearchGoods.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //是否是回车键
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(CantFindActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //搜索
                    if (TextUtils.isEmpty(tvSearchGoods.getText().toString().trim())) {
                        showToastS("你输入的为空，请重新输入");
                    } else {
                        initSearch();
                    }
                }
                return false;
            }


        });
    }

    private void initSearch() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("seach", tvSearchGoods.getText().toString().trim());
        map.put("sort", "0");
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        map.put("category_id", "0");
        map.put("parent_id", "0");
        map.put("region", "0");
        map.put("brand", "0");
        map.put("delivery_time", "0");
        map.put("page", "1");
        map.put("pagesize", Config.pageSize);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetGoodsList(map, new MyCallBack<DateBeatJsonObj>(mContext) {
            @Override
            public void onSuccessful(DateBeatJsonObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    Bundle mBundle = new Bundle();
                    mBundle.putString("findName", tvSearchGoods.getText().toString().trim());
                    RxActivityUtils.skipActivity(mContext, SearchListActivcty.class, mBundle);
                } else {
//                    showToastS(response.getErrMsg());
                }
            }
        });
    }

}
