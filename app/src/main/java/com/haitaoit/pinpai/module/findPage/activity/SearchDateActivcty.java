package com.haitaoit.pinpai.module.findPage.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.github.customview.MyEditText;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.adapter.SearchGoodsAdapter;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.DateBeatJsonObj;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.module.releasePage.activity.ReleaseBegBuyActivity;
import com.haitaoit.pinpai.module.releasePage.activity.ReleaseSourceActivity;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 搜索拎呗产品库
 * Created by Administrator on 2017/12/1.
 */

public class SearchDateActivcty extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.tv_search_goods)
    MyEditText tvSearchGoods;
    @BindView(R.id.tv_cancel)
    MyTextView tvCancel;
    @BindView(R.id.rv_collec_view)
    RecyclerView rvCollecView;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    @BindView(R.id.tv_Submit)
    MyTextView tvSubmit;
    @BindView(R.id.ll_date_min)
    MyLinearLayout llDateMin;
    @BindView(R.id.ll_date_max)
    LinearLayout llDateMax;
    @BindView(R.id.tv_NotReplace)
    MyTextView tvNotReplace;
    @BindView(R.id.tv_NewReplace)
    MyTextView tvNewReplace;
    @BindView(R.id.rel_bottom)
    LinearLayout relBottom;
    private SearchGoodsAdapter mSearchGoodsAdapter;
    private int currentPage = 1;
    List<DateBeatJsonObj.ResponseBean> mDataList = new ArrayList<>();


    @Override
    protected int getContentView() {
        return R.layout.search_date_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setTitle("发布商品");
        tvSubmit.setText("提交新货源信息并发布");
        tvNewReplace.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvSearchGoods.setText(getIntent().getStringExtra("findName"));
        pcflRefresh.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                initCollList(false);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currentPage = 1;
                initCollList(true);
            }
        });
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void initData() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initAdapter();
        initCollList(true);
    }

    private void initCollList(final boolean isRefresh) {
        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        map.put("seach", tvSearchGoods.getText().toString().trim());
        map.put("sort", "0");
        map.put("category_id", "0");
        map.put("parent_id", "0");
        map.put("region", "0");
        map.put("brand", "0");
        map.put("delivery_time", "0");
        map.put("page", currentPage + "");
        map.put("pagesize", Config.pageSize);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetGoodsList(map, new MyCallBack<DateBeatJsonObj>(mContext) {
            @Override
            public void onSuccessful(DateBeatJsonObj response) {
                if (response.getErrCode() == 0) {
                    List<DateBeatJsonObj.ResponseBean> result = response.getResponse();
                    if (isRefresh) {
                        mDataList.clear();
                        mDataList.addAll(result);
                    } else {
                        mDataList.addAll(result);
                    }
                    mSearchGoodsAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mDataList.clear();
                        llDateMax.setVisibility(View.GONE);
                        llDateMin.setVisibility(View.VISIBLE);
                        relBottom.setVisibility(View.GONE);
                        mSearchGoodsAdapter.notifyDataSetChanged();
                        RxToast.normal(response.getErrMsg());
                    }

                }
            }
        });
    }


    private void initAdapter() {
        rvCollecView.setLayoutManager(new LinearLayoutManager(mContext));
        rvCollecView.setAdapter(mSearchGoodsAdapter = new SearchGoodsAdapter(mContext, mDataList, "1"));
        rvCollecView.setNestedScrollingEnabled(false);
        mSearchGoodsAdapter.setOnItemClickListener(new SearchGoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mDataList.get(position).getGoods_id());
                RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);

            }

        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rx_title, R.id.tv_cancel, R.id.tv_Submit, R.id.tv_NewReplace})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_NewReplace:
                RxActivityUtils.skipActivity(mContext, ReleaseSourceActivity.class);
                break;
            case R.id.tv_cancel:
                if (TextUtils.isEmpty(tvSearchGoods.getText().toString().trim())) {
                    showToastS("搜索内容为空");
                    return;
                }
                currentPage = 1;
                initCollList(true);

                break;
            case R.id.tv_Submit:
                if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                } else {
                    RxActivityUtils.skipActivity(mContext, ReleaseSourceActivity.class);
                }

                break;
        }
    }
}
