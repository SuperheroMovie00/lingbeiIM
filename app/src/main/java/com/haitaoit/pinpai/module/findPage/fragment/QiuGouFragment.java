package com.haitaoit.pinpai.module.findPage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.customview.MyCheckBox;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.activity.PurchaseGoodsDetailActivity;
import com.haitaoit.pinpai.module.findPage.activity.ScreenActivity;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.findPage.adapter.SupplyGoodsAdapter;
import com.haitaoit.pinpai.module.findPage.network.response.GetCountTotal;
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

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2017/11/21.
 */

public class QiuGouFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.cb_replace_change)
    MyCheckBox cbReplaceChange;
    @BindView(R.id.rv_find_list)
    RecyclerView rvFindList;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    @BindView(R.id.tv_Good_num)
    TextView tvGoodNum;

    private SupplyGoodsAdapter mSupplyGoodsAdapter;
    private List<DateBeatJsonObj.ResponseBean> mbeanList = new ArrayList();

    private int currentPage = 1;
    public static final int REQUEST_PAYEE = 0x027;
    private String mContent;
    private String mCategoryID1 = "0";
    private String mCategoryID2 = "0";
    private String mBrandId = "0";
    private String mAddressID = "0";
    private String mTimeReplace = "0";

    public static QiuGouFragment newInstance(String s, String searchName) {
        QiuGouFragment newFragment = new QiuGouFragment();
        Bundle bundle = new Bundle();
        bundle.putString("hello", s);
        bundle.putString("searchName", searchName);
        newFragment.setArguments(bundle);
        return newFragment;

    }


    @Override
    protected int getContentView() {
        return R.layout.supple_good_fragment;
    }

    @Override
    protected void initView() {
        if (TextUtils.isEmpty(getArguments().getString("searchName"))) {
            mContent = "0";
        } else {
            mContent = getArguments().getString("searchName");
        }

        pcflRefresh.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                initDatteList(false);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mCategoryID1 ="0";
                mCategoryID2 ="0";
                mBrandId = "0";
                mAddressID = "0";
                mTimeReplace = "0";
                currentPage = 1;
                initDatteList(true);
            }
        });

        rvFindList.setLayoutManager(new LinearLayoutManager(mContext));
        rvFindList.setAdapter(mSupplyGoodsAdapter = new SupplyGoodsAdapter(mContext, mbeanList,"2"));
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

    @Override
    protected void initData() {
        initDatteList(true);
        initCount();
    }

    private void initCount() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "2");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetToalCount(map, new MyCallBack<GetCountTotal>(mContext) {
            @Override
            public void onSuccessful(GetCountTotal response) {
                if (response.getErrCode() == 0) {
                    tvGoodNum.setText("共" + response.getResponse().getTotalCount()+ "个求购");
                }else {
                    tvGoodNum.setText("共" + 0+ "个求购");
                }
            }
        });
    }


    private void initDatteList(final boolean isRefresh) {
        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        map.put("seach", mContent);
        map.put("sort", "0");
        map.put("category_id", mCategoryID1);
        map.put("parent_id", mCategoryID2);
        map.put("region", mAddressID);
        map.put("brand", mBrandId);
        map.put("delivery_time", mTimeReplace);
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
                    } else {
                    }
                    currentPage++;
                    if (result != null) {
                        mbeanList.addAll(result);
                        mSupplyGoodsAdapter.notifyDataSetChanged();
                    } else {
                        RxToast.normal("暂无数据");
                    }
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mbeanList.clear();
                        mSupplyGoodsAdapter.notifyDataSetChanged();
                    } else {
                    }
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
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

    @OnClick({R.id.cb_replace_change})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.cb_replace_change:
                startActivityForResult(new Intent(getActivity(), ScreenActivity.class), REQUEST_PAYEE);
                Log.e("---------", "---------");
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        currentPage = 1;
        initDatteList(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_PAYEE && resultCode == RESULT_OK) {
            mCategoryID1 = data.getStringExtra("mCategoryID1");
            mCategoryID2 = data.getStringExtra("mCategoryID2");
            mBrandId = data.getStringExtra("mBrandId");
            mAddressID = data.getStringExtra("mAddressID");
            mTimeReplace = data.getStringExtra("mTimeReplace");
            Log.e("----mCategoryID1-----", "---------" + data.getStringExtra("mCategoryID1"));
            Log.e("----mCategoryID2-----", "---------" + data.getStringExtra("mCategoryID2"));
            Log.e("----mBrandId-----", "---------" + data.getStringExtra("mBrandId"));
            Log.e("----mAddressID-----", "---------" + data.getStringExtra("mAddressID"));
            Log.e("----mTimeReplace-----", "---------" + data.getStringExtra("mTimeReplace"));
            currentPage = 1;
            initDatteList(true);

        }
    }
}

//    Unbinder unbinder;
//    @BindView(R.id.cb_replace_change)
//    MyCheckBox cbReplaceChange;
//    @BindView(R.id.rv_find_list)
//    RecyclerView rvFindList;
//    @BindView(R.id.pcfl_refresh)
//    PtrClassicFrameLayout pcflRefresh;
//    @BindView(R.id.tv_Good_num)
//    TextView tvGoodNum;
//
//    private SupplyGoodsAdapter mSupplyGoodsAdapter;
//    private List<DateBeatJsonObj.ResponseBean> mbeanList = new ArrayList();
//
//    private int currentPage = 1;
//    public static final int REQUEST_PAYEE = 0x027;
//    private String mContent;
//    private String mCategoryID1 = "0";
//    private String mCategoryID2 = "0";
//    private String mBrandId = "0";
//    private String mAddressID = "0";
//    private String mTimeReplace = "0";
//
//    public static QiuGouFragment newInstance(String s, String searchName) {
//        QiuGouFragment newFragment = new QiuGouFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("hello", s);
//        bundle.putString("searchName", searchName);
//        newFragment.setArguments(bundle);
//        return newFragment;
//
//    }
//
//
//    @Override
//    protected int getContentView() {
//        return R.layout.supple_good_fragment;
//    }
//
//    @Override
//    protected void initView() {
//        if (TextUtils.isEmpty(getArguments().getString("searchName"))) {
//            mContent = "0";
//        } else {
//            mContent = getArguments().getString("searchName");
//        }
//
//        pcflRefresh.setPtrHandler(new PtrDefaultHandler2() {
//            @Override
//            public void onLoadMoreBegin(PtrFrameLayout frame) {
//                initDatteList(false);
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                mCategoryID1 ="0";
//                mCategoryID2 ="0";
//                mBrandId = "0";
//                mAddressID = "0";
//                mTimeReplace = "0";
//                currentPage = 1;
//                initDatteList(true);
//            }
//        });
//
//        rvFindList.setLayoutManager(new LinearLayoutManager(mContext));
//        rvFindList.setAdapter(mSupplyGoodsAdapter =  new SupplyGoodsAdapter(mContext, mbeanList,"2"));
//        rvFindList.setNestedScrollingEnabled(false);
//        mSupplyGoodsAdapter.setOnItemClickListener(new SupplyGoodsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("supplyId", mbeanList.get(position).getGoods_id());
//                RxActivityUtils.skipActivity(mContext, PurchaseGoodsDetailActivity.class, bundle);
//
//            }
//
//        });
//    }
//
//    @Override
//    protected void initData() {
//        initDatteList(true);
//    }
//
//
//    private void initDatteList(final boolean isRefresh) {
//        Map<String, String> map = new HashMap<String, String>();
//        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
//            map.put("user_id", "0");
//        } else {
//            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
//        }
//        map.put("seach", mContent);
//        map.put("sort", "0");
//        map.put("category_id", mCategoryID1);
//        map.put("parent_id", mCategoryID2);
//        map.put("region", mAddressID);
//        map.put("brand", mBrandId);
//        map.put("delivery_time", mTimeReplace);
//        map.put("page", currentPage + "");
//        map.put("pagesize", Config.pageSize);
//        map.put("sign", GetSign.getSign(map));
//        ApiRequest.GetNeedList(map, new MyCallBack<DateBeatJsonObj>(mContext) {
//            @Override
//            public void onSuccessful(DateBeatJsonObj response) {
////                if (response.getErrCode() == 0) {
////                    List<DateBeatJsonObj.ResponseBean> result = response.getResponse();
////                    if (isRefresh) {
////                        mbeanList.clear();
////                        mbeanList.addAll(result);
////                        tvGoodNum.setText("共" + mbeanList.size() + "个求购");
////                    } else {
////                        mbeanList.addAll(result);
////                    }
////                    mSupplyGoodsAdapter.notifyDataSetChanged();
////                    currentPage++;
////                    pcflRefresh.refreshComplete();
////                } else {
////                    mbeanList.clear();
////                    tvGoodNum.setText("共" +0+ "个求购");
////                    mSupplyGoodsAdapter.notifyDataSetChanged();
////                    RxToast.normal(response.getErrMsg());
////                }
////
////            }
////        });
////    }
//                if (response.getErrCode() == 0) {
//                    List<DateBeatJsonObj.ResponseBean> result = response.getResponse();
//                    if (isRefresh) {
//                        mbeanList.clear();
//                    } else {
//                    }
//                    currentPage++;
//                    if (result != null) {
//                        mbeanList.addAll(result);
//                        tvGoodNum.setText("共" + mbeanList.size() + "个求购");
//                        mSupplyGoodsAdapter.notifyDataSetChanged();
//                    } else {
//                        RxToast.normal(response.getErrMsg());
//                        tvGoodNum.setText("共" +0+ "个货源");
//                    }
//                    pcflRefresh.refreshComplete();
//                } else {
//                    RxToast.normal(response.getErrMsg());
//                }
//            }
//        });
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
//            savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//    @OnClick({R.id.cb_replace_change})
//    public void onViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.cb_replace_change:
//                startActivityForResult(new Intent(getActivity(), ScreenActivity.class), REQUEST_PAYEE);
//                Log.e("---------", "---------");
//                break;
//
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        currentPage = 1;
//        Log.e("-------2222--", "---------");
//        initDatteList(true);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_OK) {
//            return;
//        }
//        if (requestCode == REQUEST_PAYEE && resultCode == RESULT_OK) {
//            mCategoryID1 = data.getStringExtra("mCategoryID1");
//            mCategoryID2 = data.getStringExtra("mCategoryID2");
//            mBrandId = data.getStringExtra("mBrandId");
//            mAddressID = data.getStringExtra("mAddressID");
//            mTimeReplace = data.getStringExtra("mTimeReplace");
//            Log.e("----mCategoryID1-----", "---------" + data.getStringExtra("mCategoryID1"));
//            Log.e("----mCategoryID2-----", "---------" + data.getStringExtra("mCategoryID2"));
//            Log.e("----mBrandId-----", "---------" + data.getStringExtra("mBrandId"));
//            Log.e("----mAddressID-----", "---------" + data.getStringExtra("mAddressID"));
//            Log.e("----mTimeReplace-----", "---------" + data.getStringExtra("mTimeReplace"));
//            currentPage = 1;
//            initDatteList(true);
//
//        }
//    }
//}
