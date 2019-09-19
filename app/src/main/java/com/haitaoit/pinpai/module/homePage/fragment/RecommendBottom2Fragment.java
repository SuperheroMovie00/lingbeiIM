package com.haitaoit.pinpai.module.homePage.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.homePage.adapter.RecommendBottomAdapter;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.GetTuijianObj;
import com.haitaoit.pinpai.module.personPage.activity.PersonHomePageActivity;
import com.haitaoit.pinpai.tools.GetSign;
import com.vondear.rxtools.RxActivityUtils;

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

public class RecommendBottom2Fragment extends BaseFragment {


    @BindView(R.id.rv_home_hot_recommed)
    RecyclerView rvHomeHotRecommed;
    Unbinder unbinder;
    @BindView(R.id.rv_home_hot_Second)
    RecyclerView rvHomeHotSecond;
    List<GetTuijianObj.ResponseBean> mbeanList = new ArrayList<>();

    private RecommendBottomAdapter mRecommendBottomAdapter;

    @Override
    protected int getContentView() {
        return R.layout.recommed_fragment;
    }

    @Override
    protected void initView() {
        rvHomeHotRecommed.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvHomeHotRecommed.setAdapter(mRecommendBottomAdapter = new RecommendBottomAdapter(mContext, mbeanList));
        rvHomeHotRecommed.setNestedScrollingEnabled(false);

        mRecommendBottomAdapter.setOnItemClickListener(new RecommendBottomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("UserId", mbeanList.get(position).getId());
                RxActivityUtils.skipActivity(mContext, PersonHomePageActivity.class, bundle);

            }

        });


    }

    @Override
    protected void initData() {
        initSuppleBottom();
    }

    private void initSuppleBottom() {
        Map<String, String> map = new HashMap<String, String>();

//        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
//            map.put("user_id", "0");
//        } else {
//            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
//        }

        map.put("type", "2");
        map.put("page", "1");
        map.put("pagesize", "8");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetRedUsers(map, new MyCallBack<GetTuijianObj>(mContext) {
            @Override
            public void onSuccessful(GetTuijianObj response) {
                if (response.getErrCode() == 0) {
                    List<GetTuijianObj.ResponseBean> result = response.getResponse();
                    mbeanList.addAll(result);
                    mRecommendBottomAdapter.notifyDataSetChanged();
                } else {
                    showToastS(response.getErrMsg());
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

//    @BindView(R.id.rv_home_hot_recommed)
//    RecyclerView rvHomeHotRecommed;
//    Unbinder unbinder;
//    private List<String> lists = new ArrayList();
//
//    private RecommendBottomAdapter mRecommendBottomAdapter;
//
//    @Override
//    protected int getContentView() {
//        return R.layout.recommed_bottomfragment;
//    }
//
//    @Override
//    protected void initView() {
//        rvHomeHotRecommed.setLayoutManager(new GridLayoutManager(mContext, 4));
//        rvHomeHotRecommed.setAdapter(mRecommendBottomAdapter = new RecommendBottomAdapter(mContext, lists));
//        rvHomeHotRecommed.setNestedScrollingEnabled(false);
//        mRecommendBottomAdapter.setOnItemClickListener(new RecommendBottomAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("supplyId", "1");
//                RxActivityUtils.skipActivity(mContext, PersonHomePageActivity.class, bundle);
//
//            }
//
//        });
//
//
//    }
//
//    @Override
//    protected void initData() {
//        initList();
//    }
//
//    @Override
//    protected void onViewClick(View v) {
//
//    }
//
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//    private void initList() {
//        for (int i = 0; i < 8; i++) {
//            lists.add("" + i);
//        }
//
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
}
