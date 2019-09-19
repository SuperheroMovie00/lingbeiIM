package com.haitaoit.pinpai.module.homePage.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.homePage.activity.CantFindActivity;
import com.haitaoit.pinpai.module.homePage.activity.SearchListActivcty;
import com.haitaoit.pinpai.module.homePage.adapter.BeatDateAdapter;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.DateBeatJsonObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetBannerObj;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2017/10/26.
 */

public class TranRankingFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_RemarkName)
    TextView tvRemarkName;
    @BindView(R.id.rv_find_list)
    RecyclerView rvFindList;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    @BindView(R.id.pl_load)
    LinearLayout plLoad;
    private List<DateBeatJsonObj.ResponseBean> mbeanList = new ArrayList();
    private int currentPage = 1;

    private BeatDateAdapter mBeatDateAdapter;

    public static TranRankingFragment newInstance(String s) {
        TranRankingFragment mTranRankingFragment = new TranRankingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("hello", s);
        mTranRankingFragment.setArguments(bundle);
        return mTranRankingFragment;

    }

    @Override
    protected int getContentView() {
        return R.layout.beat_date_fragment;
    }

    @Override
    protected void initView() {

        pcflRefresh.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                initDatteList(false);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currentPage = 1;
                initDatteList(true);
            }
        });
        tvRemarkName.setText(getArguments().getString("hello"));
        rvFindList.setLayoutManager(new LinearLayoutManager(mContext));
        rvFindList.setAdapter(mBeatDateAdapter = new BeatDateAdapter(mContext, mbeanList));
        rvFindList.setNestedScrollingEnabled(false);
        mBeatDateAdapter.setOnItemClickListener(new BeatDateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mbeanList.get(position).getGoods_id());
                RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);

            }

        });


    }

    @Override
    protected void initData() {

        initDatteList(true);
    }

    @Override
    protected void onViewClick(View v) {

    }


    private void initDatteList(final boolean isRefresh) {

        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        map.put("seach", "0");
        map.put("sort", "3");
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
                        mbeanList.clear();
                    }
                    mbeanList.addAll(result);
                    mBeatDateAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mbeanList.clear();
                        mBeatDateAdapter.notifyDataSetChanged();
                        RxToast.normal(response.getErrMsg());
                    }
                    pcflRefresh.refreshComplete();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        currentPage = 1;
        initDatteList(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
