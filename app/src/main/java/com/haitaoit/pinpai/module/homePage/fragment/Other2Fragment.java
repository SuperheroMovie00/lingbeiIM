package com.haitaoit.pinpai.module.homePage.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.customview.MyRadioButton;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.activity.PurchaseGoodsDetailActivity;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.findPage.adapter.SupplyGoodsAdapter;
import com.haitaoit.pinpai.module.homePage.activity.ParagraphGOodsActivity;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.DateBeatJsonObj;
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
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2017/10/26.
 */

public class Other2Fragment extends BaseFragment {


    @BindView(R.id.tv_RemarkName)
    TextView tvRemarkName;
    @BindView(R.id.rb_Other_1)
    MyRadioButton rbOther1;
    @BindView(R.id.rb_Other_2)
    MyRadioButton rbOther2;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.rv_find_list)
    RecyclerView rvFindList;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    @BindView(R.id.pl_load)
    LinearLayout plLoad;
    Unbinder unbinder;
    private SupplyGoodsAdapter mSupplyGoodsAdapter;
    private List<DateBeatJsonObj.ResponseBean> mbeanList = new ArrayList();
    private int currentPage = 1;
    private String mMessageType = "1";

    public static Other2Fragment newInstance(String s) {
        Other2Fragment mOtherFragment = new Other2Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("hello", s);
        mOtherFragment.setArguments(bundle);
        return mOtherFragment;

    }

    @Override
    protected int getContentView() {
        return R.layout.other_fragment;
    }

    @Override
    protected void initView() {
        tvRemarkName.setText(getArguments().getString("hello"));
        rvFindList.setLayoutManager(new LinearLayoutManager(mContext));
        rvFindList.setAdapter(mSupplyGoodsAdapter = new SupplyGoodsAdapter(mContext, mbeanList,mMessageType));
        rvFindList.setNestedScrollingEnabled(false);

        pcflRefresh.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                initCollList(false, mMessageType);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currentPage = 1;
                initCollList(true, mMessageType);
            }
        });

    }

    private void initCollList(final boolean isRefresh, String mMessageType) {
        if (mMessageType.equals("1")) {
            Map<String, String> map = new HashMap<String, String>();
            if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                map.put("user_id", "0");
            } else {
                map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
            }
            map.put("seach", "0");
            map.put("sort", "5");
            map.put("category_id", "0");
            map.put("parent_id", "0");
            map.put("region", "0");
            map.put("brand", "0");
            map.put("delivery_time", "0");
            map.put("page", currentPage + "");
            map.put("pagesize", Config.pageSize);
            map.put("sign", GetSign.getSign(map));
            ApiRequest.GetGoodsList(map, new MyCallBack<DateBeatJsonObj>(mContext) {
                @Override
                public void onSuccessful(DateBeatJsonObj response) {
                    if (response.getErrCode() == 0) {
                        List<DateBeatJsonObj.ResponseBean> result = response.getResponse();
                        if (isRefresh) {
                            mbeanList.clear();
                            mbeanList.addAll(result);
                        } else {
                            mbeanList.addAll(result);
                        }
                        mSupplyGoodsAdapter.notifyDataSetChanged();
                        currentPage++;
                        pcflRefresh.refreshComplete();
                    } else {
                        RxToast.normal(response.getErrMsg());
                    }

                }
            });
        }

     else

        {
            Map<String, String> map = new HashMap<String, String>();
            if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                map.put("user_id", "0");
            } else {
                map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
            }
            map.put("seach", "0");
            map.put("sort", "5");
            map.put("category_id", "0");
            map.put("parent_id", "0");
            map.put("region", "0");
            map.put("brand", "0");
            map.put("delivery_time", "0");
            map.put("page", currentPage + "");
            map.put("pagesize", Config.pageSize);
            map.put("sign", GetSign.getSign(map));
            ApiRequest.GetNeedList(map, new MyCallBack<DateBeatJsonObj>(mContext) {
                @Override
                public void onSuccessful(DateBeatJsonObj response) {
                    if (response.getErrCode() == 0) {
                        List<DateBeatJsonObj.ResponseBean> result = response.getResponse();
                        if (isRefresh) {
                            mbeanList.clear();
                            mbeanList.addAll(result);
                        } else {
                            mbeanList.addAll(result);
                        }
                        mSupplyGoodsAdapter.notifyDataSetChanged();
                        currentPage++;
                        pcflRefresh.refreshComplete();
                    } else {
                        RxToast.normal(response.getErrMsg());
                    }

                }
            });
        }

}

    private void initAdapter() {
        rvFindList.setLayoutManager(new LinearLayoutManager(mContext));

        if (mMessageType.equals("1")) {
            rvFindList.setAdapter(mSupplyGoodsAdapter =  new SupplyGoodsAdapter(mContext, mbeanList,mMessageType));
            rvFindList.setNestedScrollingEnabled(false);
            mSupplyGoodsAdapter.setOnItemClickListener(new SupplyGoodsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("supplyId", mbeanList.get(position).getGoods_id());
                    RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);

                }

            });
        } else {
            rvFindList.setAdapter(mSupplyGoodsAdapter = new SupplyGoodsAdapter(mContext, mbeanList,mMessageType));
            rvFindList.setNestedScrollingEnabled(false);
            mSupplyGoodsAdapter.setOnItemClickListener(new SupplyGoodsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("supplyId", mbeanList.get(position).getGoods_id());
                    RxActivityUtils.skipActivity(mContext, PurchaseGoodsDetailActivity.class, bundle);

                }

            });
        }


    }

    @Override
    protected void initData() {
        initAdapter();
        initCollList(true, mMessageType);
    }


    @OnClick({R.id.rb_Other_1, R.id.rb_Other_2})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rb_Other_1:
                mMessageType = "1";
                currentPage = 1;
                initCollList(true, mMessageType);
                initAdapter();
                break;
            case R.id.rb_Other_2:
                mMessageType = "2";
                currentPage = 1;
                initCollList(true, mMessageType);
                initAdapter();
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        currentPage = 1;
        mMessageType = "1";
        initCollList(true, mMessageType);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
//    Unbinder unbinder;
//    @BindView(R.id.tv_RemarkName)
//    TextView tvRemarkName;
//    @BindView(R.id.rv_find_list)
//    RecyclerView rvFindList;
//    @BindView(R.id.pcfl_refresh)
//    PtrClassicFrameLayout pcflRefresh;
//    @BindView(R.id.pl_load)
//    LinearLayout plLoad;
//    @BindView(R.id.rb_Other_1)
//    MyRadioButton rbOther1;
//    @BindView(R.id.rb_Other_2)
//    MyRadioButton rbOther2;
//    @BindView(R.id.rg_main)
//    RadioGroup rgMain;
//    private List<DateBeatJsonObj.ResponseBean> mbeanList = new ArrayList();
//    private int currentPage = 1;
//
//    private BeatDateAdapter mBeatDateAdapter;
//
//    public static OtherFragment newInstance(String s) {
//        OtherFragment mOtherFragment = new OtherFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("hello", s);
//        mOtherFragment.setArguments(bundle);
//        return mOtherFragment;
//
//    }
//
//    @Override
//    protected int getContentView() {
//        return R.layout.other_fragment;
//    }
//
//    @Override
//    protected void initView() {
//
//        pcflRefresh.setPtrHandler(new PtrDefaultHandler2() {
//            @Override
//            public void onLoadMoreBegin(PtrFrameLayout frame) {
//                initDatteList(false);
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                currentPage = 1;
//                initDatteList(true);
//            }
//        });
//
//        tvRemarkName.setText(getArguments().getString("hello"));
//        rvFindList.setLayoutManager(new LinearLayoutManager(mContext));
//        rvFindList.setAdapter(mBeatDateAdapter = new BeatDateAdapter(mContext, mbeanList));
//        rvFindList.setNestedScrollingEnabled(false);
//        mBeatDateAdapter.setOnItemClickListener(new BeatDateAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("supplyId", "1");
//                RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);
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
//
//        initDatteList(true);
//    }
//
//
//    private void initDatteList(final boolean isRefresh) {
//
//        Map<String, String> map = new HashMap<String, String>();
//        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
//            map.put("user_id", "0");
//        } else {
//            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
//        }
//        map.put("seach", "0");
//        map.put("sort", "0");
//        map.put("category_id", "0");
//        map.put("parent_id", "0");
//        map.put("region", "0");
//        map.put("brand", "0");
//        map.put("delivery_time", "0");
//        map.put("page", currentPage + "");
//        map.put("pagesize", Config.pageSize);
//        map.put("sign", GetSign.getSign(map));
//        ApiRequest.GetGoodsList(map, new MyCallBack<DateBeatJsonObj>(mContext) {
//            @Override
//            public void onSuccessful(DateBeatJsonObj response) {
//                if (response.getErrCode() == 0) {
//                    List<DateBeatJsonObj.ResponseBean> result = response.getResponse();
//                    if (isRefresh) {
//                        mbeanList.clear();
//                    }
//                    mbeanList.addAll(result);
//                    mBeatDateAdapter.notifyDataSetChanged();
//                    currentPage++;
//                    pcflRefresh.refreshComplete();
//                } else {
//                    if (isRefresh) {
//                        mbeanList.clear();
//                        mBeatDateAdapter.notifyDataSetChanged();
//                        RxToast.normal(response.getErrMsg());
//                    }
//                    pcflRefresh.refreshComplete();
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @OnClick({R.id.rb_Other_1, R.id.rb_Other_2})
//    public void onViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.rb_Other_1:
//                break;
//            case R.id.rb_Other_2:
//                break;
//        }
//    }
}
