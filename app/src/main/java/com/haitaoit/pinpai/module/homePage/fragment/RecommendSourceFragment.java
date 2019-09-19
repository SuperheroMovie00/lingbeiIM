package com.haitaoit.pinpai.module.homePage.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.findPage.network.response.TuiJianHuoYuanObj;
import com.haitaoit.pinpai.module.homePage.adapter.RecommendSoureAdapter;
import com.haitaoit.pinpai.module.homePage.adapter.RecommendSoureSecondAdapter;
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

/**
 * Created by Administrator on 2017/10/26.
 */

public class RecommendSourceFragment extends BaseFragment {


    @BindView(R.id.rv_home_hot_recommed)
    RecyclerView rvHomeHotRecommed;
    Unbinder unbinder;
    @BindView(R.id.rv_home_hot_Second)
    RecyclerView rvHomeHotSecond;
    List<GetBannerObj.ResponseBean.DyadroollistBean> mbeanList = new ArrayList<>();
    private List<TuiJianHuoYuanObj.ResponseBean> mDateBeat = new ArrayList();

    private RecommendSoureAdapter mRecommendSoureAdapter;
    private RecommendSoureSecondAdapter mRecommendSoureSecondAdapter;

    @Override
    protected int getContentView() {
        return R.layout.recommed_fragment;
    }

    @Override
    protected void initView() {
        rvHomeHotRecommed.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvHomeHotRecommed.setAdapter(mRecommendSoureAdapter = new RecommendSoureAdapter(mContext, mbeanList));
        rvHomeHotRecommed.setNestedScrollingEnabled(false);

        mRecommendSoureAdapter.setOnItemClickListener(new RecommendSoureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mbeanList.get(position).getGood_id());
                RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);

            }

        });


        rvHomeHotSecond.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvHomeHotSecond.setAdapter(mRecommendSoureSecondAdapter = new RecommendSoureSecondAdapter(mContext, mDateBeat,"1"));
        rvHomeHotSecond.setNestedScrollingEnabled(false);


        mRecommendSoureSecondAdapter.setOnItemClickListener(new RecommendSoureSecondAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mDateBeat.get(position).getGoods_id());
                RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);

            }

        });


    }

    @Override
    protected void initData() {
        initSupplyTop();
        initSuppleBottom();
    }

    private void initSuppleBottom() {
        Map<String, String> map = new HashMap<String, String>();

        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }

        map.put("seach", "0");
        map.put("sort", "2");
        map.put("category_id", "0");
        map.put("parent_id", "0");

        map.put("region", "0");
        map.put("brand", "0");
        map.put("delivery_time", "0");
        map.put("page", "1");
        map.put("pagesize", "4");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetGoodsListTuijian(map, new MyCallBack<TuiJianHuoYuanObj>(mContext) {
            @Override
            public void onSuccessful(TuiJianHuoYuanObj response) {
                if (response.getErrCode() == 0) {
                    List<TuiJianHuoYuanObj.ResponseBean> result = response.getResponse();
                    mDateBeat.addAll(result);
                    mRecommendSoureSecondAdapter.notifyDataSetChanged();
                } else {
                    showToastS(response.getErrMsg());
                }
            }
        });
    }


    private void initSupplyTop() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "index_supply ");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetBanner(map, new MyCallBack<GetBannerObj>(mContext) {
            @Override
            public void onSuccessful(GetBannerObj response) {
                if (response.getErrCode() == 0) {
                    mbeanList.addAll(response.getResponse().getDyadroollist());
                    mRecommendSoureAdapter.notifyDataSetChanged();
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    @Override
    protected void onViewClick(View v) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
