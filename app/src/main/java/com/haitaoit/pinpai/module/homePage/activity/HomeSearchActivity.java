package com.haitaoit.pinpai.module.homePage.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.homePage.adapter.HomeSearchAdapter;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.DateBeatJsonObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetBannerObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetHistoryListObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetHotSearchObj;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.module.releasePage.activity.ReleaseSourceActivity;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.view.FlowLayout;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页查找
 * Created by Administrator on 2017/11/23.
 */

public class HomeSearchActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_search_goods)
    EditText tvSearchGoods;
    @BindView(R.id.tv_cancel)
    MyTextView tvCancel;
    @BindView(R.id.ll_date_max)
    MyLinearLayout llDateMax;
    @BindView(R.id.tv_replace_change)
    TextView tvReplaceChange;
    @BindView(R.id.fl_Hot_search)
    FlowLayout flHotSearch;
    @BindView(R.id.im_clear_History)
    ImageView imClearHistory;
    @BindView(R.id.rv_History_view)
    RecyclerView rvHistoryView;
    private Context context;
    List<GetHotSearchObj.ResponseBean> mbeanList = new ArrayList<>();
    List<GetHistoryListObj.ResponseBean> mhisList = new ArrayList<>();
    private HomeSearchAdapter mHomeSearchAdapter;

    @Override
    protected int getContentView() {
        return R.layout.home_search_activity;
    }

    @Override
    protected void initView() {
        initKey();
        initAdapter();

    }


    private void initHotTop() {
        Map<String, String> map = new HashMap<String, String>();
        ApiRequest.GetSeachList(map, new MyCallBack<GetHotSearchObj>(mContext) {
            @Override
            public void onSuccessful(GetHotSearchObj response) {
                if (response.getErrCode() == 0) {
                    mbeanList.addAll(response.getResponse());
                    initFlow();
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    private void initHisTop() {
        Map<String, String> map = new HashMap<String, String>();
        ApiRequest.GeHistoryList(map, new MyCallBack<GetHistoryListObj>(mContext) {
            @Override
            public void onSuccessful(GetHistoryListObj response) {
                if (response.getErrCode() == 0) {
                    mhisList.clear();
                    mhisList.addAll(response.getResponse());
                    mHomeSearchAdapter.notifyDataSetChanged();
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
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
                            .hideSoftInputFromWindow(HomeSearchActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //搜索
                    if (TextUtils.isEmpty(tvSearchGoods.getText().toString().trim())) {
                        RxToast.error(mContext, "你输入的为空，请重新输入");
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
        map.put("pagesize", "10");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetGoodsList(map, new MyCallBack<DateBeatJsonObj>(mContext) {
            @Override
            public void onSuccessful(DateBeatJsonObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    initHisTop();
                    Bundle mBundle = new Bundle();
                    mBundle.putString("findName", tvSearchGoods.getText().toString().trim());
                    RxActivityUtils.skipActivity(mContext, SearchListActivcty.class, mBundle);
                } else {
                    initHisTop();
                    Bundle mBundle = new Bundle();
                    mBundle.putString("findName", tvSearchGoods.getText().toString().trim());
                    RxActivityUtils.skipActivity(mContext, CantFindActivity.class, mBundle);
                }
            }
        });
    }

    private void initAdapter() {
        rvHistoryView.setLayoutManager(new LinearLayoutManager(mContext));
        rvHistoryView.setAdapter(mHomeSearchAdapter = new HomeSearchAdapter(mContext, mhisList));
        rvHistoryView.setNestedScrollingEnabled(false);
        mHomeSearchAdapter.setOnItemClickListener(new HomeSearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                tvSearchGoods.setText(mhisList.get(position).getTitle());
                initSearch();
            }

        });

    }

    @Override
    protected void initData() {
        initHotTop();
        initHisTop();
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        context = this;
    }


    private void initFlow() {
        //第二步：移除FlowLayout中的所有子布局
        flHotSearch.removeAllViews();
        //第三步：循环创建子View，添加到FlowLayout中
        for (int x = 0; x < mbeanList.size(); x++) {
            //3.1初始化子view
            int ranHeight = dip2px(this, ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ranHeight);
            lp.setMargins(dip2px(this, 5), 15, dip2px(this, 5), 10);
            CheckBox checkBox = (CheckBox) View.inflate(mContext, R.layout.item_hot_flowlayout, null);
            checkBox.setText(mbeanList.get(x).getTitle());

            final int finalX = x;
            //3.2设置子view的点击事件
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSearchGoods.setText(mbeanList.get(finalX).getTitle());
                    //遍历FlowLayout中的所有view，如果是当前选中的view，设置为选中状态，其他设置为未选中状态
                    refreshCheckBox(mbeanList.get(finalX).getTitle());
                }
            });
            //3.3将子view添加到FlowLayout中
            flHotSearch.addView(checkBox, lp);
        }
    }


    /**
     * 遍历FlowLayout中的所有view，如果是当前选中的view，设置为选中状态，其他设置为未选中状态
     *
     * @param name
     */
    private void refreshCheckBox(String name) {
        for (int y = 0; y < flHotSearch.getChildCount(); y++) {
            CheckBox radio = (CheckBox) flHotSearch.getChildAt(y);

            if (name.equals(radio.getText())) {
                radio.setChecked(true);
            } else {
                radio.setChecked(false);
            }

        }
    }

    @OnClick({R.id.iv_back, R.id.tv_cancel, R.id.tv_replace_change, R.id.im_clear_History})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_cancel:
                initSearch();
                break;
            case R.id.tv_replace_change:
                initReplace();
                break;
            case R.id.im_clear_History:
                initClaer();
                break;
        }
    }

    private void initReplace() {

        Map<String, String> map = new HashMap<String, String>();
        ApiRequest.GetChangeList(map, new MyCallBack<GetHotSearchObj>(mContext) {
            @Override
            public void onSuccessful(GetHotSearchObj response) {
                if (response.getErrCode() == 0) {
                    mbeanList.clear();
                    mbeanList.addAll(response.getResponse());
                    Log.e("mListmbeanListmbeanList",mbeanList.size()+"");
                    initFlow();
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    private void initClaer() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetSearchDelete(map, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    mhisList.clear();
                    mHomeSearchAdapter.notifyDataSetChanged();
                    initHisTop();
                    showToastS(response.getErrMsg());
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }
}
