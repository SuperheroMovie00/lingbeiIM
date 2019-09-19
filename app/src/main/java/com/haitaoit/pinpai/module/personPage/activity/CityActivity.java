package com.haitaoit.pinpai.module.personPage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.personPage.adapter.CityAdapter;
import com.haitaoit.pinpai.module.personPage.adapter.CountryAdapter;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.Constant;
import com.haitaoit.pinpai.module.personPage.network.response.GetCityObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetCountryListObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
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

public class CityActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.rv_country_view)
    RecyclerView rvCountryView;

    CityAdapter mCityAdapter;
    @BindView(R.id.tv_Country)
    TextView tvCountry;
    private List<GetCityObj.ResponseBean> mDataList = new ArrayList();

    @Override
    protected int getContentView() {
        return R.layout.country_activity;
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
        rvCountryView.setAdapter(mCityAdapter = new CityAdapter(mContext, mDataList));
        rvCountryView.setNestedScrollingEnabled(false);
        mCityAdapter.setOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = getIntent();
                intent.putExtra("mIdCity",  mDataList.get(position).getId());
                intent.putExtra("mINameCity", mDataList.get(position).getTitle());
                setResult(RESULT_OK, intent);
                finish();

            }

        });


    }


    @Override
    protected void initData() {
        initAdapter();
        initList();
    }

    private void initList() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("parent_id", getIntent().getStringExtra("mCountryId"));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetCityList(map, new MyCallBack<GetCityObj>(mContext) {
            @Override
            public void onSuccessful(GetCityObj response) {
                if (response.getErrCode() == 0) {
                    mDataList.addAll(response.getResponse());
                    mCityAdapter.notifyDataSetChanged();
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }


    @OnClick({R.id.rx_title, R.id.tv_Country})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_Country:
                Intent intent = new Intent();
                intent.putExtra(Constant.IParam.update_address, tvCountry.getText().toString() + "");
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
