package com.haitaoit.pinpai.module.homePage.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.activity.PurchaseGoodsDetailActivity;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.findPage.adapter.SearchGoodsAdapter;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.DateBeatJsonObj;
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
 * Created by LZY on 2017/12/25.
 */

public class ParagraphWantActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.rv_find_list)
    RecyclerView rvFindList;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    @BindView(R.id.tv_Submit)
    MyTextView tvSubmit;
    @BindView(R.id.ll_date_min)
    MyLinearLayout llDateMin;

    private SearchGoodsAdapter mSearchGoodsAdapter;
    private int currentPage = 1;
    List<DateBeatJsonObj.ResponseBean> mDataList = new ArrayList<>();


    @Override
    protected int getContentView() {
        return R.layout.paragraph_goods_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setTitle("查看同款求购");
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    }

    @Override
    protected void initData() {
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
        map.put("goods_id", getIntent().getStringExtra("mGoodsId"));
        map.put("goods_title", getIntent().getStringExtra("mGoodTitle"));
        map.put("seach", "0");
        map.put("category_id", "0");
        map.put("region", "0");
        map.put("brand", "0");
        map.put("delivery_time", "0");
        map.put("page", currentPage + "");
        map.put("pagesize", Config.pageSize);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetSameNeedList(map, new MyCallBack<DateBeatJsonObj>(mContext) {
            @Override
            public void onSuccessful(DateBeatJsonObj response) {
                if (response.getErrCode() == 0) {
                    List<DateBeatJsonObj.ResponseBean> result = response.getResponse();
                    if (isRefresh) {
                        mDataList.clear();
                    }
                    mDataList.addAll(result);
                    mSearchGoodsAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mDataList.clear();
                        mSearchGoodsAdapter.notifyDataSetChanged();
                        RxToast.normal(response.getErrMsg());
                    }

                    pcflRefresh.refreshComplete();
                }
            }
        });
    }

    private void initAdapter() {
        rvFindList.setLayoutManager(new LinearLayoutManager(mContext));
        rvFindList.setAdapter(mSearchGoodsAdapter = new SearchGoodsAdapter(mContext, mDataList,"2"));
        rvFindList.setNestedScrollingEnabled(false);
        mSearchGoodsAdapter.setOnItemClickListener(new SearchGoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mDataList.get(position).getGoods_id());
                RxActivityUtils.skipActivity(mContext, PurchaseGoodsDetailActivity.class, bundle);

            }

        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rx_title, R.id.tv_Submit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_Submit:
                RxActivityUtils.skipActivityAndFinish(mContext, ReleaseSourceActivity.class);
                break;
        }
    }
}
