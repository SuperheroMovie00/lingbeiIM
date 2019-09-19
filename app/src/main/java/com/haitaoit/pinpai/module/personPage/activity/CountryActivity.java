package com.haitaoit.pinpai.module.personPage.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.personPage.adapter.CountryAdapter;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.Constant;
import com.haitaoit.pinpai.module.personPage.network.response.GetAddressListObj;
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

public class CountryActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.rv_country_view)
    RecyclerView rvCountryView;

    CountryAdapter mCountryAdapter;
    @BindView(R.id.tv_Country)
    TextView tvCountry;
    private List<GetCountryListObj.ResponseBean.DycaregorylistBean> mDataList = new ArrayList();

    @Override
    protected int getContentView() {
        return R.layout.country_activity;
    }

    @Override
    protected void initView() {
        tvCountry.setText(getIntent().getStringExtra("userCountry"));
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initAdapter() {
        rvCountryView.setLayoutManager(new LinearLayoutManager(mContext));
        rvCountryView.setAdapter(mCountryAdapter = new CountryAdapter(mContext, mDataList));
        rvCountryView.setNestedScrollingEnabled(false);
        mCountryAdapter.setOnItemClickListener(new CountryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = getIntent();
                intent.putExtra("mCountry", mDataList.get(position).getTitle());
                intent.putExtra("mCountryId", mDataList.get(position).getId());
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

        ApiRequest.GetCountryList(map, new MyCallBack<GetCountryListObj>(mContext) {
            @Override
            public void onSuccessful(GetCountryListObj response) {
                if (response.getErrCode() == 0) {
                    mDataList.addAll(response.getResponse().getDycaregorylist());
                    mCountryAdapter.notifyDataSetChanged();
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
