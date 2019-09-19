package com.haitaoit.pinpai.module.releasePage.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.network.response.GetGoodsCategoryObj;
import com.haitaoit.pinpai.module.personPage.adapter.CountryAdapter;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.Constant;
import com.haitaoit.pinpai.module.personPage.network.response.GetCountryListObj;
import com.haitaoit.pinpai.module.releasePage.adapter.AddressCityAdapter;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 国家
 * Created by Administrator on 2017/10/31.
 */

public class AddressCityActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.rv_country_view)
    RecyclerView rvCountryView;

    AddressCityAdapter mCountryAdapter;
    @BindView(R.id.tv_Country)
    TextView tvCountry;
    List<GetGoodsCategoryObj.ResponseBean.DysupplylistBean> mSupplyBeanList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.address_city_activity;
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
        rvCountryView.setLayoutManager(new LinearLayoutManager(mContext));
        rvCountryView.setAdapter(mCountryAdapter = new AddressCityAdapter(mContext, mSupplyBeanList));
        rvCountryView.setNestedScrollingEnabled(false);
        mCountryAdapter.setOnItemClickListener(new AddressCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = getIntent();
                intent.putExtra("mCountry", mSupplyBeanList.get(position).getTitle());
                intent.putExtra("mCountryId", mSupplyBeanList.get(position).getSupply_id());
                setResult(RESULT_OK, intent);
                finish();

            }

        });


    }


    @Override
    protected void initData() {
        initAdapter();
        initCategory();
    }

    private void initCategory() {
        Map<String, String> map = new HashMap<String, String>();
        com.haitaoit.pinpai.module.findPage.network.ApiRequest.GetGoodsCategoryList(map, new MyCallBack<GetGoodsCategoryObj>(mContext) {
            @Override
            public void onSuccessful(GetGoodsCategoryObj response) {
                if (response.getErrCode() == 0) {
                    List<GetGoodsCategoryObj.ResponseBean.DysupplylistBean> mSupplyList = response.getResponse().getDysupplylist();
                    mSupplyBeanList.addAll(mSupplyList);
                    mCountryAdapter.notifyDataSetChanged();
                } else {
                    showToastS(response.getErrMsg());
                }
            }
        });
    }


    @OnClick({R.id.rx_title, R.id.tv_Country})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
        }
    }
}
