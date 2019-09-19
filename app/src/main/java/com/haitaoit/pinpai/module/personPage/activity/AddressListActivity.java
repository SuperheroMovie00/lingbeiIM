package com.haitaoit.pinpai.module.personPage.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.homePage.activity.MainActivity;
import com.haitaoit.pinpai.module.loginPage.network.response.GetLoginObj;
import com.haitaoit.pinpai.module.personPage.adapter.AddressListAdapter;
import com.haitaoit.pinpai.module.personPage.adapter.WantBuyAdapter;
import com.haitaoit.pinpai.module.personPage.bean.ItemModel;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.response.GetAddressListObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LZY on 2017/12/12.
 */

public class AddressListActivity extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.tv_next)
    MyTextView tvNext;
    List<GetAddressListObj.ResponseBean> mDataList=new ArrayList<>();
    AddressListAdapter mAddressListAdapter;

    @Override
    protected int getContentView() {
        return R.layout.address_list_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initAdapter() {
        rvView.setLayoutManager(new LinearLayoutManager(mContext));
        rvView.setAdapter(mAddressListAdapter = new AddressListAdapter(mContext, mDataList));
        rvView.setNestedScrollingEnabled(false);
//        mAddressListAdapter.setOnItemClickListener(new AddressListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("supplyId", "1");
//                RxActivityUtils.skipActivity(mContext, WantBuyDetailsActivity.class, bundle);
//            }
//
//        });


    }
    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetAddress(map, new MyCallBack<GetAddressListObj>(mContext) {
            @Override
            public void onSuccessful(GetAddressListObj response) {
                if (response.getErrCode() == 0) {
                    mDataList.addAll( response.getResponse());
                    mAddressListAdapter.notifyDataSetChanged();
                }
                else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
        initAdapter();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rx_title, R.id.tv_next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_next:
                RxActivityUtils.skipActivity(mContext, AddressActivity.class);
                break;
        }
    }
}
