package com.haitaoit.pinpai.module.personPage.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.personPage.activity.MessageDetailActivity;
import com.haitaoit.pinpai.module.personPage.adapter.UserMessageAdapter;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.response.CollectionObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetMessagePinObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetMessageUserObj;
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
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2017/11/3.
 */

public class UserMessageFragment extends BaseFragment {


    @BindView(R.id.rv_collec_view)
    RecyclerView rvCollecView;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;

    private List<GetMessageUserObj.ResponseBean> mDateList = new ArrayList();
    private UserMessageAdapter mUserMessageAdapter;
    private int currentPage = 1;

    @Override
    protected int getContentView() {
        return R.layout.my_message_fragment;
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
        map.put("type", "1");
        map.put("page", currentPage + "");
        map.put("pagesize", Config.pageSize);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetMessageListUser(map, new MyCallBack<GetMessageUserObj>(mContext) {
            @Override
            public void onSuccessful(GetMessageUserObj response) {
                if (response.getErrCode() == 0) {
                    List<GetMessageUserObj.ResponseBean> result = response.getResponse();
                    if (isRefresh) {
                        mDateList.clear();
                    }
                    mDateList.addAll(result);
                    mUserMessageAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mDateList.clear();
                        mUserMessageAdapter.notifyDataSetChanged();
                        RxToast.normal(response.getErrMsg());
                    }

                    pcflRefresh.refreshComplete();
                }
            }
        });
    }

    private void initAdapter() {
        rvCollecView.setLayoutManager(new LinearLayoutManager(mContext));
        rvCollecView.setAdapter(mUserMessageAdapter = new UserMessageAdapter(mContext, mDateList));
        rvCollecView.setNestedScrollingEnabled(false);
        mUserMessageAdapter.setOnItemClickListener(new UserMessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mDateList.get(position).getId());
                RxActivityUtils.skipActivity(mContext, MessageDetailActivity.class, bundle);

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
