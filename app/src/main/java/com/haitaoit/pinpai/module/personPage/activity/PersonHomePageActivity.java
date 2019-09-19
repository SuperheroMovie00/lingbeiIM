package com.haitaoit.pinpai.module.personPage.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyRadioButton;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.activity.PurchaseGoodsDetailActivity;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.homePage.network.response.GetIdentObj;
import com.haitaoit.pinpai.module.personPage.adapter.MySupplyAdapter;
import com.haitaoit.pinpai.module.personPage.adapter.WantBuyAdapter;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.response.GetPersonUserObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetUserGooddListObj;
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
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import de.hdodenhof.circleimageview.CircleImageView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by Administrator on 2017/11/30.
 */

public class PersonHomePageActivity extends BaseActivity {
    @BindView(R.id.iv_back_set)
    ImageView ivBackSet;
    @BindView(R.id.iv_person_image)
    CircleImageView ivPersonImage;
    @BindView(R.id.tv_person_name)
    TextView tvPersonName;
    @BindView(R.id.tv_YijingRenzheng)
    TextView tvYijingRenzheng;
    @BindView(R.id.tv_Name)
    TextView tvName;
    @BindView(R.id.ll_person_collection)
    LinearLayout llPersonCollection;
    @BindView(R.id.tv_Addrsss)
    TextView tvAddrsss;
    @BindView(R.id.ll_person_follow)
    LinearLayout llPersonFollow;
    @BindView(R.id.cb_replace_sort)
    MyRadioButton cbReplaceSort;
    @BindView(R.id.cb_replace_hot)
    MyRadioButton cbReplaceHot;
    @BindView(R.id.rcv_Person)
    RecyclerView rcvPerson;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    private MySupplyAdapter mMySupplyAdapter;
    //记录选择的Item
    private int currentPage = 1;
    List<GetUserGooddListObj.ResponseBean> mDataList = new ArrayList<>();
    private WantBuyAdapter mWantBuyAdapter;
    private String mPersonType = "1";

    @Override
    protected int getContentView() {
        return R.layout.person_home_page_activity;
    }

    @Override
    protected void initView() {
        Log.e("=================", getIntent().getStringExtra("UserId"));
        tvYijingRenzheng.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvYijingRenzheng.getPaint().setColor(getResources().getColor(R.color.red_ff_77));
    }

    @Override
    protected void initData() {
        initDatePerson();
        initAdapter();
        initCollListGoods(true);
        initIdent();
    }


    private void initIdent() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getIntent().getStringExtra("UserId"));
        map.put("sign", GetSign.getSign(map));
        com.haitaoit.pinpai.module.homePage.network.ApiRequest.GetIdent(map, new MyCallBack<GetIdentObj>(mContext) {
            @Override
            public void onSuccessful(GetIdentObj response) {
                if (response.getErrCode() == 0) {
                    if (response.getResponse().getIs_certified().equals("2")) {
                        tvYijingRenzheng.setVisibility(View.VISIBLE);
                    } else {
                        tvYijingRenzheng.setVisibility(View.GONE);
                    }
                } else {
                    showToastS("获取个人信息错误");
                }
            }
        });
    }

    private void initDatePerson() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getIntent().getStringExtra("UserId"));
        map.put("RegistrationID", JPushInterface.getRegistrationID(this));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetUserInfo(map, new MyCallBack<GetPersonUserObj>(mContext) {
            @Override
            public void onSuccessful(GetPersonUserObj response) {
                if (response.getErrCode() == 0) {
                    Glide.with(mContext).load(response.getResponse().getAvatar()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivPersonImage);
                    tvPersonName.setText(response.getResponse().getNick_name());
                    tvAddrsss.setText("常住地  :" + response.getResponse().getAddress());
                    tvName.setText(response.getResponse().getCertified());
                } else {
                    showToastS("获取个人信息错误");
                }
            }
        });
    }

    private void initAdapter() {


        if (mPersonType.equals("1")) {
            rcvPerson.setLayoutManager(new LinearLayoutManager(mContext));
            rcvPerson.setAdapter(mMySupplyAdapter = new MySupplyAdapter(mContext, mDataList));
            rcvPerson.setNestedScrollingEnabled(false);
            mMySupplyAdapter.setOnItemClickListener(new MySupplyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("supplyId", mDataList.get(position).getGoods_id());
                    RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);
                }

            });
        } else {
            rcvPerson.setLayoutManager(new LinearLayoutManager(mContext));
            rcvPerson.setAdapter(mWantBuyAdapter = new WantBuyAdapter(mContext, mDataList));
            rcvPerson.setNestedScrollingEnabled(false);
            mWantBuyAdapter.setOnItemClickListener(new WantBuyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("supplyId", mDataList.get(position).getGoods_id());
                    RxActivityUtils.skipActivityAndFinish(mContext, PurchaseGoodsDetailActivity.class, bundle);
                }

            });
        }

    }


    /**
     * 查看列表
     *
     * @param isRefresh
     */
    private void initCollListGoods(final boolean isRefresh) {
        Map<String, String> map = new HashMap<String, String>();

        map.put("red_user_id", getIntent().getStringExtra("UserId"));
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        map.put("page", currentPage + "");
        map.put("pagesize", "10000");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetRedUserGoods(map, new MyCallBack<GetUserGooddListObj>(mContext) {
            @Override
            public void onSuccessful(GetUserGooddListObj response) {
                if (response.getErrCode() == 0) {
                    List<GetUserGooddListObj.ResponseBean> result = response.getResponse();
                    if (isRefresh) {
                        mDataList.clear();
                    }
                    mDataList.addAll(result);
                    initAdapter();
                    mMySupplyAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mDataList.clear();
                        initAdapter();
                        mMySupplyAdapter.notifyDataSetChanged();
                        RxToast.normal(response.getErrMsg());
                    }
                    pcflRefresh.refreshComplete();
                }
            }
        });
    }


    /**
     * 查看列表
     *
     * @param isRefresh
     */
    private void initCollListBuy(final boolean isRefresh) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("red_user_id", getIntent().getStringExtra("UserId"));
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        map.put("page", currentPage + "");
        map.put("pagesize", "10000");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetRedUserNeeds(map, new MyCallBack<GetUserGooddListObj>(mContext) {
            @Override
            public void onSuccessful(GetUserGooddListObj response) {
                if (response.getErrCode() == 0) {
                    List<GetUserGooddListObj.ResponseBean> result = response.getResponse();
                    if (isRefresh) {
                        mDataList.clear();
                    }
                    mDataList.addAll(result);
                    initAdapter();
                    mWantBuyAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mDataList.clear();
                        initAdapter();
                        mMySupplyAdapter.notifyDataSetChanged();
                        RxToast.normal(response.getErrMsg());
                    }
                    pcflRefresh.refreshComplete();
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.iv_back_set, R.id.iv_person_image, R.id.ll_person_collection, R.id.ll_person_follow, R.id.cb_replace_sort, R.id.cb_replace_hot})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_set:
                finish();
                break;
            case R.id.iv_person_image:
                break;
            case R.id.ll_person_collection:
                break;
            case R.id.ll_person_follow:
                break;
            case R.id.cb_replace_sort:
                currentPage = 1;
                mPersonType = "1";
                initCollListGoods(true);
                break;
            case R.id.cb_replace_hot:
                currentPage = 1;
                mPersonType = "2";
                initCollListBuy(true);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentPage = 1;
        mPersonType = "1";
        initCollListGoods(true);
    }
}
