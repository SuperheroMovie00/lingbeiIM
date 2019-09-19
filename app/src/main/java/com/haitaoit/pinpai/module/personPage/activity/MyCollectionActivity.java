package com.haitaoit.pinpai.module.personPage.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.customview.MyCheckBox;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyRadioButton;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.personPage.adapter.MyCollectionAdapter;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.request.PostCollectDeleteItem;
import com.haitaoit.pinpai.module.personPage.network.response.CollectionObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 我的收藏
 * Created by Administrator on 2017/10/30.
 */

public class MyCollectionActivity extends BaseActivity {

    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.cb_replace_sort)
    MyRadioButton cbReplaceSort;
    @BindView(R.id.cb_replace_hot)
    MyRadioButton cbReplaceHot;
    @BindView(R.id.rv_collec_view)
    RecyclerView rvCollecView;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    @BindView(R.id.ll_date_max)
    MyLinearLayout llDateMax;
    @BindView(R.id.ll_date_min)
    MyLinearLayout llDateMin;
    @BindView(R.id.tv_select_all)
    MyCheckBox tvSelectAll;
    @BindView(R.id.tv_delete)
    MyTextView tvDelete;
    @BindView(R.id.rel_bottom)
    RelativeLayout relBottom;
    MyCollectionAdapter mMyCollectionAdapter;
    //记录选择的Item
    List<CollectionObj.ResponseBean> mDataList = new ArrayList<>();
    @BindView(R.id.tv_search_goods)
    EditText tvSearchGoods;
    @BindView(R.id.tv_cancel)
    MyTextView tvCancel;
    private int mEditMode = MYLIVE_MODE_CHECK;
    private int currentPage = 1;
    private String mCollType = "1";

    @Override
    protected int getContentView() {
        return R.layout.my_collection_activity;
    }


    @Override
    protected void initData() {
        initCollList(true, mCollType);
        initAdapter();
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rxTitle.setRightIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataEditMode();
            }
        });
//        rxTitle.setRightTextOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updataEditMode();
//            }
//        });

        pcflRefresh.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                initCollList(false, mCollType);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currentPage = 1;
                initCollList(true, mCollType);
            }
        });
    }

    private void initAdapter() {
        rvCollecView.setLayoutManager(new LinearLayoutManager(mContext));
        rvCollecView.setAdapter(mMyCollectionAdapter = new MyCollectionAdapter(mContext, mDataList,mCollType));
        rvCollecView.setNestedScrollingEnabled(false);
        mMyCollectionAdapter.setOnItemClickListener(new MyCollectionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mDataList.get(position).getObj_id());
                RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);
            }

        });

        mMyCollectionAdapter.setCallback(new MyCollectionAdapter.CartEventCallback() {
            @Override
            public void checkAll(boolean flag) {
                if (flag) {
                    tvSelectAll.setChecked(true);
                } else {
                    tvSelectAll.setChecked(false);
                }
            }
        });
    }


    private void initCollList(final boolean isRefresh, String mCollType) {
        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        map.put("type", mCollType);
        map.put("page", currentPage + "");
        map.put("pagesize", Config.pageSize);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetCollectList(map, new MyCallBack<CollectionObj>(mContext) {
            @Override
            public void onSuccessful(CollectionObj response) {
                if (response.getErrCode() == 0) {
                    List<CollectionObj.ResponseBean> result = response.getResponse();
                    if (isRefresh) {
                        mDataList.clear();
                        mDataList.addAll(result);
                    } else {
                        mDataList.addAll(result);
                    }
                    llDateMax.setVisibility(View.VISIBLE);
                    llDateMin.setVisibility(View.GONE);
                    relBottom.setVisibility(View.GONE);
                    rxTitle.setRightTextVisibility(true);
                    mMyCollectionAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mDataList.clear();
                        llDateMax.setVisibility(View.GONE);
                        llDateMin.setVisibility(View.VISIBLE);
                        relBottom.setVisibility(View.GONE);
                        rxTitle.setRightTextVisibility(false);
                        mMyCollectionAdapter.notifyDataSetChanged();
                        RxToast.normal(response.getErrMsg());
                    }

                }
            }
        });
    }


    @OnClick({R.id.rx_title, R.id.cb_replace_sort, R.id.cb_replace_hot, R.id.tv_select_all, R.id.tv_delete, R.id.tv_cancel})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                if (TextUtils.isEmpty(tvSearchGoods.getText().toString().trim())) {
                    RxToast.error(mContext, "你输入的为空，请重新输入");
                } else {
                    if ("1".equals(mCollType)) {
                        currentPage = 1;
                        initCollList(true, mCollType);
                    } else {
                        currentPage = 1;
                        initCollList(true, mCollType);
                    }


                }
                break;
            case R.id.rx_title:
                break;
            case R.id.cb_replace_sort:
                mCollType = "1";
                currentPage = 1;
                initCollList(true, mCollType);
                break;
            case R.id.cb_replace_hot:
                mCollType = "2";
                currentPage = 1;
                initCollList(true, mCollType);
                break;
            case R.id.tv_select_all:
                if (tvSelectAll.isChecked()) {
                    mMyCollectionAdapter.setSelectAll(true);
                    mMyCollectionAdapter.notifyDataSetChanged();

                } else {
                    mMyCollectionAdapter.setSelectAll(false);
                    mMyCollectionAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_delete:
                initDelete();
                break;
        }
    }

    private void initDelete() {
        //获取要进行请求的item对象
        PostCollectDeleteItem item = new PostCollectDeleteItem();
        List<PostCollectDeleteItem.IdlistBean> shops = new ArrayList<>();
        List<String> selectPos = mMyCollectionAdapter.getSelectPos();
        if (selectPos.size() == 0) {
            RxToast.error("请先选择要删除的条目");
            return;
        }
        for (int i = 0; i < selectPos.size(); i++) {
            int pos = Integer.valueOf(selectPos.get(i));
            PostCollectDeleteItem.IdlistBean bodyBean = new PostCollectDeleteItem.IdlistBean();
            bodyBean.setId(mDataList.get(pos).getId());
            shops.add(bodyBean);
        }
        item.setIdlist(shops);
        //进行网络请求
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        String sign = GetSign.getSign(map);
        map.put("sign", sign);

        ApiRequest.PostCollectDelete(map, item, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    currentPage = 1;
                    initCollList(true, mCollType);
                } else {
                    RxToast.normal(response.getErrMsg());
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


    private void updataEditMode() {
        mEditMode = mEditMode == MYLIVE_MODE_CHECK ? MYLIVE_MODE_EDIT : MYLIVE_MODE_CHECK;
        if (mEditMode == MYLIVE_MODE_EDIT) {
            relBottom.setVisibility(View.VISIBLE);
        } else {
            relBottom.setVisibility(View.GONE);
        }
        mMyCollectionAdapter.setEditMode(mEditMode);
    }


}
