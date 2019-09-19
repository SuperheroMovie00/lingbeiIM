package com.haitaoit.pinpai.module.personPage.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.activity.PurchaseGoodsDetailActivity;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.personPage.activity.MessageDetailActivity;
import com.haitaoit.pinpai.module.personPage.adapter.PlatFormMessageAdapter;
import com.haitaoit.pinpai.module.personPage.adapter.UserMessageAdapter;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.response.CollectionObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetMessagePinObj;
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
 * 平台消息
 * Created by Administrator on 2017/11/3.
 */

public class PlatFormMessageFragment extends BaseFragment {

    @BindView(R.id.rv_collec_view)
    RecyclerView rvCollecView;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;

    private List<GetMessagePinObj.ResponseBean> mDateList = new ArrayList();
    private PlatFormMessageAdapter mPlatFormMessageAdapter;
    private int currentPage = 1;

    @Override
    protected int getContentView() {
        return R.layout.my_plat_fragment;
    }


    @Override
    protected void initView() {
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

    private void initCollList(final boolean isRefresh) {
        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        map.put("type", "2");
        map.put("page", currentPage + "");
        map.put("pagesize", Config.pageSize);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetMessageList(map, new MyCallBack<GetMessagePinObj>(mContext) {
            @Override
            public void onSuccessful(GetMessagePinObj response) {
                if (response.getErrCode() == 0) {
                    List<GetMessagePinObj.ResponseBean> result = response.getResponse();
                    if (isRefresh) {
                        mDateList.clear();
                    }
                    mDateList.addAll(result);
                    mPlatFormMessageAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mDateList.clear();
                        mPlatFormMessageAdapter.notifyDataSetChanged();
                        RxToast.normal(response.getErrMsg());
                    }

                    pcflRefresh.refreshComplete();
                }
            }
        });
    }

    private void initAdapter() {
        rvCollecView.setLayoutManager(new LinearLayoutManager(mContext));
        rvCollecView.setAdapter(mPlatFormMessageAdapter = new PlatFormMessageAdapter(mContext, mDateList));
        rvCollecView.setNestedScrollingEnabled(false);
        mPlatFormMessageAdapter.setOnItemClickListener(new PlatFormMessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mDateList.get(position).getGoods_id());
                bundle.putString("msge_id", mDateList.get(position).getId());
                if (mDateList.get(position).getType().equals("1")) {
                    RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);
                } else if (mDateList.get(position).getType().equals("2")) {
                    RxActivityUtils.skipActivity(mContext, PurchaseGoodsDetailActivity.class, bundle);
                } else {
                    RxActivityUtils.skipActivity(mContext, MessageDetailActivity.class, bundle);
                }
            }

        });


    }


    @Override
    protected void initData() {
        initAdapter();
        initCollList(true);
    }

    @Override
    protected void onViewClick(View v) {

    }


}
