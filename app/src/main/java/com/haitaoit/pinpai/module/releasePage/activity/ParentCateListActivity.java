package com.haitaoit.pinpai.module.releasePage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.baseclass.rx.MySubscriber;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.callback.FragmentEvent;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.homePage.adapter.RecommendBottomAdapter;
import com.haitaoit.pinpai.module.releasePage.adapter.BandLeftAdapter;
import com.haitaoit.pinpai.module.releasePage.adapter.BandRightAdapter;
import com.haitaoit.pinpai.module.releasePage.network.ApiRequest;
import com.haitaoit.pinpai.module.releasePage.network.response.GetBandClassObj;
import com.haitaoit.pinpai.module.releasePage.network.response.GetCateObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.baseclass.rx.RxBusHelper.getRxBusEvent;

/**
 * Created by LZY on 2017/12/23.
 */

public class ParentCateListActivity extends BaseActivity {


    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.rcv_left)
    RecyclerView rcvLeft;
    @BindView(R.id.rcv_right)
    RecyclerView rcvRight;

    BandLeftAdapter mBandLeftAdapter;
    BandRightAdapter mBandRightAdapter;

    List<GetBandClassObj.ResponseBean.DycaregorylistBean> mDateList = new ArrayList<>();
    List<GetCateObj.ResponseBean> mCateList = new ArrayList<>();

    private String mBrandId, mBrandName;
    public static final int REQUEST_PAYEE = 0x027;
    int index = 0;
    String id;

    @Override
    protected int getContentView() {
        return R.layout.part_cate_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initAdapter();
        initBrandClass();
    }

    private void initAdapter() {
        rcvLeft.setLayoutManager(new LinearLayoutManager(mContext));
        rcvLeft.setAdapter(mBandLeftAdapter = new BandLeftAdapter(mContext, mDateList));
        rcvLeft.setNestedScrollingEnabled(false);

        mBandLeftAdapter.setOnItemClickListener(new BandLeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mBrandId = mDateList.get(position).getId();
                mBrandName = mDateList.get(position).getTitle();
                RxToast.success(mBrandId);
                inintCate(mBrandId);
            }
        });

        rcvRight.setLayoutManager((new GridLayoutManager(mContext, 3)));
        rcvRight.setAdapter(mBandRightAdapter = new BandRightAdapter(mContext, mCateList));
        rcvRight.setNestedScrollingEnabled(false);

        mBandRightAdapter.setOnItemClickListener(new BandRightAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = getIntent();
                intent.putExtra("mBrandId", mBrandId);
                intent.putExtra("mBrandName", mBrandName);
                intent.putExtra("mCateId", mCateList.get(position).getId());
                intent.putExtra("mCateName", mCateList.get(position).getTitle());
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }


    private void initBrandClass() {
        Map<String, String> map = new HashMap<String, String>();
        ApiRequest.GetParentCateList(map, new MyCallBack<GetBandClassObj>(mContext) {
            @Override
            public void onSuccessful(GetBandClassObj response) {
                if (response.getErrCode() == 0) {
                    List<GetBandClassObj.ResponseBean.DycaregorylistBean> result = response.getResponse().getDycaregorylist();
                    mDateList.addAll(result);
                    mBandLeftAdapter.notifyDataSetChanged();
                    mBrandId = mDateList.get(0).getId();
                    mBrandName = mDateList.get(0).getTitle();
                    inintCate(mBrandId);

                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    private void inintCate(String CateId) {
        Log.e("parents_id", mBrandId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("parents_id", CateId);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetChildCateList(map, new MyCallBack<GetCateObj>(mContext) {
            @Override
            public void onSuccessful(GetCateObj response) {
                if (response.getErrCode() == 0) {
                    mCateList.clear();
                    List<GetCateObj.ResponseBean> result = response.getResponse();
                    mCateList.addAll(result);
                    setAdapter();
                    mBandRightAdapter.notifyDataSetChanged();
                } else {
                    mCateList.clear();
                    mBandRightAdapter.notifyDataSetChanged();
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    private void setAdapter() {

        rcvRight.setLayoutManager((new GridLayoutManager(mContext, 3)));
        rcvRight.setAdapter(mBandRightAdapter = new BandRightAdapter(mContext, mCateList));
        rcvRight.setNestedScrollingEnabled(false);

        mBandRightAdapter.setOnItemClickListener(new BandRightAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = getIntent();
                intent.putExtra("mBrandId", mBrandId);
                intent.putExtra("mBrandName", mBrandName);
                intent.putExtra("mCateId", mCateList.get(position).getId());
                intent.putExtra("mCateName", mCateList.get(position).getTitle());
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
