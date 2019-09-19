package com.haitaoit.pinpai.module.personPage.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.customview.MyCheckBox;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetIdentObj;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.module.personPage.adapter.WantBuyAdapter;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.request.PostCollectDeleteItem;
import com.haitaoit.pinpai.module.personPage.network.response.GetUserGooddListObj;
import com.haitaoit.pinpai.module.releasePage.activity.ReleaseBegBuyActivity;
import com.haitaoit.pinpai.module.releasePage.activity.ReleaseErrorActivity;
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
 * 我的货源
 * Created by Administrator on 2017/10/30.
 */

public class MySupplyBuyActivity extends BaseActivity {
    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.tv_search_goods)
    EditText tvSearchGoods;
    @BindView(R.id.iv_SearchClose)
    ImageView ivSearchClose;
    @BindView(R.id.tv_cancel)
    MyTextView tvCancel;
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
    @BindView(R.id.tv_SuppleTitle)
    TextView tvSuppleTitle;
    @BindView(R.id.tv_Release_goods)
    MyTextView tvReleaseGoods;
    private WantBuyAdapter mWantBuyAdapter;
    //记录选择的Item
    private int mEditMode = MYLIVE_MODE_CHECK;
    private int currentPage = 1;
    List<GetUserGooddListObj.ResponseBean> mDataList = new ArrayList<>();
    private String mType = "1";
    private String mIsIdent;

    @Override
    protected int getContentView() {
        return R.layout.my_supply_of_buys_activity;
    }

    @Override
    protected void initView() {
        initIdent();
        initRefresh();
        initListener();
        initOnKey();
    }

    @Override
    protected void initData() {
        initCollList(true);
        initAdapter();
    }

    private void initListener() {
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
        rxTitle.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataEditMode();
            }
        });
    }

    private void initRefresh() {
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

    private void initOnKey() {
        tvSearchGoods.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (tvSearchGoods.getText().toString().trim().equals("")) {
                        showMsg("你搜索的内容为空");
                    } else {
                        currentPage = 1;
                        mType = "2";
                        initCollList(true);
                    }
                    return true;
                }
                return false;
            }
        });
    }


    /**
     * 查看列表
     *
     * @param isRefresh
     */
    private void initCollList(final boolean isRefresh) {
        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }

        map.put("page", currentPage + "");
        if (RxDataUtils.isNullString(tvSearchGoods.getText().toString() )) {
            map.put("seach", "0");
        } else {
            map.put("seach", tvSearchGoods.getText().toString() + "");
        }
        map.put("pagesize", Config.pageSize);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetNeedList(map, new MyCallBack<GetUserGooddListObj>(mContext) {
            @Override
            public void onSuccessful(GetUserGooddListObj response) {
                if (response.getErrCode() == 0) {
                    List<GetUserGooddListObj.ResponseBean> result = response.getResponse();
                    if (isRefresh) {
                        mDataList.clear();
                        mDataList.addAll(result);
                        if (mDataList.size() < 0) {
                            relBottom.setVisibility(View.GONE);
                            llDateMax.setVisibility(View.GONE);
                            llDateMin.setVisibility(View.VISIBLE);
                            rxTitle.setRightTextVisibility(false);
                        }
                    } else {
                        mDataList.addAll(result);
                    }
                    mWantBuyAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mDataList.clear();
                        llDateMax.setVisibility(View.GONE);
                        llDateMin.setVisibility(View.VISIBLE);
                        rxTitle.setRightTextVisibility(false);
                        mWantBuyAdapter.notifyDataSetChanged();
                        if (mType.equals("2")) {
                            tvSuppleTitle.setText("没有找到相同或类似的信息哦");
                            tvReleaseGoods.setText("提交新求购信息并发布");
                        }
                        RxToast.normal(response.getErrMsg());
                    }

                }
            }
        });
    }


    private void initAdapter() {
        rvCollecView.setLayoutManager(new LinearLayoutManager(mContext));
        rvCollecView.setAdapter(mWantBuyAdapter = new WantBuyAdapter(mContext, mDataList));
        rvCollecView.setNestedScrollingEnabled(false);
        mWantBuyAdapter.setOnItemClickListener(new WantBuyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mDataList.get(position).getGoods_id());
                RxActivityUtils.skipActivityAndFinish(mContext, WantBuyDetailsActivity.class, bundle);
            }
        });
        mWantBuyAdapter.setCallback(new WantBuyAdapter.CartEventCallback() {
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


    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.rx_title, R.id.tv_select_all, R.id.tv_delete, R.id.tv_Release_goods, R.id.tv_cancel})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                if (tvSearchGoods.getText().toString().trim().equals("")) {
                    showMsg("你搜索的内容为空");
                } else {
                    currentPage = 1;
                    mType = "2";
                    initCollList(true);
                }
                break;
            case R.id.tv_select_all:
                if (tvSelectAll.isChecked()) {
                    mWantBuyAdapter.setSelectAll(true);
                    mWantBuyAdapter.notifyDataSetChanged();
                } else {
                    mWantBuyAdapter.setSelectAll(false);
                    mWantBuyAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_delete:
                initDelete();
                break;
            case R.id.tv_Release_goods:
                Bundle mBundle = new Bundle();
                if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                } else if (mIsIdent.equals("1")) {
                    mBundle.putString("mType", "1");
                    RxActivityUtils.skipActivity(mContext, ReleaseErrorActivity.class, mBundle);
                } else if (mIsIdent.equals("4")) {
                    mBundle.putString("mType", "4");
                    RxActivityUtils.skipActivity(mContext, AnthErrorActivity.class, mBundle);
                } else {
                    RxActivityUtils.skipActivity(mContext, ReleaseBegBuyActivity.class);
                }

                break;
        }
    }



    private void initIdent() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));
        com.haitaoit.pinpai.module.homePage.network.ApiRequest.GetIdent(map, new MyCallBack<GetIdentObj>(mContext) {
            @Override
            public void onSuccessful(GetIdentObj response) {
                if (response.getErrCode() == 0) {
                    mIsIdent = response.getResponse().getIs_certified() + "";
                } else {
                    showToastS(response.getErrMsg());
                }
            }
        });
    }

    /**
     * 删除数据
     */
    private void initDelete() {
        //获取要进行请求的item对象
        PostCollectDeleteItem item = new PostCollectDeleteItem();
        List<PostCollectDeleteItem.IdlistBean> shops = new ArrayList<>();
        List<String> selectPos = mWantBuyAdapter.getSelectPos();
        if (selectPos.size() == 0) {
            RxToast.error("请先选择要删除的条目");
            return;
        }
        for (int i = 0; i < selectPos.size(); i++) {
            int pos = Integer.valueOf(selectPos.get(i));
            PostCollectDeleteItem.IdlistBean bodyBean = new PostCollectDeleteItem.IdlistBean();
            bodyBean.setId(mDataList.get(pos).getGoods_id());
            shops.add(bodyBean);
        }
        item.setIdlist(shops);
        //进行网络请求
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        String sign = GetSign.getSign(map);
        map.put("sign", sign);

        ApiRequest.PostNeedDelete(map, item, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    currentPage = 1;
                    initCollList(true);
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }


    private void updataEditMode() {
        mEditMode = mEditMode == MYLIVE_MODE_CHECK ? MYLIVE_MODE_EDIT : MYLIVE_MODE_CHECK;
        if (mEditMode == MYLIVE_MODE_EDIT) {
            rxTitle.setRightText("取消");
            relBottom.setVisibility(View.VISIBLE);
        } else {
            rxTitle.setRightText("编辑");
            relBottom.setVisibility(View.GONE);
        }
        mWantBuyAdapter.setEditMode(mEditMode);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
