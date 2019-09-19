package com.haitaoit.pinpai.module.personPage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.customview.MyCheckBox;
import com.github.customview.MyEditText;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.GetGoodsDetailJson;
import com.haitaoit.pinpai.module.releasePage.activity.AddressCityActivity;
import com.haitaoit.pinpai.module.releasePage.network.request.PostUserReplaceItem;
import com.haitaoit.pinpai.module.releasePage.network.request.ReleaseBeen;
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

/**
 * 编辑货源
 * Created by Administrator on 2017/10/31.
 */

public class EditorSupplyActivity extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.tv_replace)
    TextView tvReplace;
    @BindView(R.id.my_tv_editor)
    MyTextView myTvEditor;
    @BindView(R.id.layout01)
    LinearLayout layout01;
    @BindView(R.id.tv_AddShop)
    TextView tvAddShop;
    @BindView(R.id.tv_StockNum)
    EditText tvStockNum;
    @BindView(R.id.tv_Goods_Sources)
    TextView tvGoodsSources;
    @BindView(R.id.ll_Goods_Sources)
    MyLinearLayout llGoodsSources;
    @BindView(R.id.tv_Delivery_time)
    EditText tvDeliveryTime;
    @BindView(R.id.ll_Delivery_time)
    MyLinearLayout llDeliveryTime;
    @BindView(R.id.tv_sava)
    MyTextView tvSava;
    @BindView(R.id.ll_Add_prices)
    LinearLayout llAddPrices;


    MyEditText tvQiDingNum;
    MyEditText tvQiDingPrice;
    MyEditText tvQiDingID;
    @BindView(R.id.CBox_defeult)
    MyCheckBox CBoxDefeult;
    @BindView(R.id.nsv_home)
    NestedScrollView nsvHome;
    private List<ReleaseBeen> viewTests = new ArrayList<>();
    private LayoutInflater layoutInflater;
    View views;
    List<GetGoodsDetailJson.ResponseBean.DypricelistBean> mPricesList = new ArrayList<>();
    private List<PostUserReplaceItem.PriceListBean> priceDatas = new ArrayList<>();
    public static final int REQUEST_SHOWADDTwo = 0x013;
    private String mPlaceOfOriginId, mGoodsSourcesId;
    private String mAdvantage = "1";

    @Override
    protected int getContentView() {
        return R.layout.editor_supple_activity;
    }

    @Override
    protected void initView() {
        layoutInflater = getLayoutInflater();
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CBoxDefeult.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    CBoxDefeult.setEnabled(true);
                    mAdvantage="2";
                }else {
                    CBoxDefeult.setEnabled(false);
                    mAdvantage="1";
                }
            }
        });
    }

    @Override
    protected void initData() {
        initDetailList();
    }


    private void initDetailList() {
        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        if (RxDataUtils.isNullString(getIntent().getStringExtra("supplyId"))) {
            map.put("msge_id", "0");
        } else {
            map.put("msge_id", getIntent().getStringExtra("supplyId"));
        }
        map.put("goods_id", getIntent().getStringExtra("supplyId"));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetGoodsDetail(map, new MyCallBack<GetGoodsDetailJson>(mContext) {
            @Override
            public void onSuccessful(GetGoodsDetailJson response) {
                if (response.getErrCode() == 0) {

                    /**
                     * 价格
                     */
                    List<GetGoodsDetailJson.ResponseBean.DypricelistBean> pricelist = response.getResponse().getDypricelist();
                    mPricesList.addAll(pricelist);
                    if (mPricesList.size() == 3) {
                        llAddPrices.setVisibility(View.GONE);
                    }

                    for (int i = 0; i < mPricesList.size(); i++) {
                        layoutInflater = LayoutInflater.from(getApplicationContext());
                        views = layoutInflater.inflate(R.layout.activity_index_gallery_add, null);
                        layout01.addView(views);
                        getViewInstance(views);
                        tvQiDingPrice.setText(mPricesList.get(i).getPrice());
                        tvQiDingNum.setText(mPricesList.get(i).getQuantity());
                        tvQiDingID.setText(mPricesList.get(i).getPrice_id());
                    }
                    tvStockNum.setText(response.getResponse().getStock());
                    tvGoodsSources.setText(response.getResponse().getSource());
                    tvDeliveryTime.setText(response.getResponse().getDelivery_time());
                    mGoodsSourcesId = response.getResponse().getSource();

                    if (response.getResponse().getIs_advantage().equals("2")) {
                        CBoxDefeult.setChecked(true);
                        mAdvantage = "2";
                    } else {
                        CBoxDefeult.setChecked(false);
                        mAdvantage = "1";
                    }
                }
            }
        });
    }


    private void initEditor() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("goods_id", getIntent().getStringExtra("supplyId"));
        map.put("sign", GetSign.getSign(map));


        for (int i = 0; i < viewTests.size(); i++) {
            Log.e(viewTests.size() + "-priceListBean", viewTests.size() + "==========555555555566666666666666545454545454545454545454545454=====");
            ReleaseBeen holderView = viewTests.get(i);
            PostUserReplaceItem.PriceListBean priceListBean = new PostUserReplaceItem.PriceListBean();
            priceListBean.setPrice(holderView.getTv_QiDingID().getText().toString() + "|" + holderView.getTv_QiDingPrice().getText().toString() + "|" + holderView.getTv_QiDingNum().getText().toString());
            priceDatas.add(priceListBean);
        }


        if (priceDatas.size() == 0) {
            RxToast.error("起订量不可以小于1组");
            return;
        }
        PostUserReplaceItem bean = new PostUserReplaceItem();
        bean.setStock(tvStockNum.getText().toString());
        bean.setSource(tvGoodsSources.getText().toString());
        bean.setDelivery_time(tvDeliveryTime.getText().toString());
        bean.setIs_advantage(mAdvantage+"");
        bean.setPrice_list(priceDatas);


        ApiRequest.PostEditGoods(map, bean, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    RxActivityUtils.skipActivityAndFinish(mContext, MySupplyGoodsActivity.class);
                } else {
                    RxToast.normal(response.getErrMsg());
                    finish();
                }
            }
        });
    }


    @OnClick({R.id.tv_replace, R.id.ll_Add_prices, R.id.tv_Goods_Sources, R.id.tv_sava})
    public void onViewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_replace:
                initClear();

                break;
            case R.id.ll_Add_prices:
                if (viewTests.size() == 3) {
                    showToastS("最多只能添加三条");
                } else {
                    views = layoutInflater.inflate(R.layout.activity_index_gallery_add, null);
                    layout01.addView(views);
                    getViewInstance(views);
                }

                break;
            case R.id.tv_Goods_Sources:
//                AddressPickTask task = new AddressPickTask(this);
//                task.setHideProvince(false);
//                task.setHideCounty(false);
//                task.setCallback(new AddressPickTask.Callback() {
//                    @Override
//                    public void onAddressInitFailed() {
//                        showToastS("数据初始化失败");
//                    }
//
//                    @Override
//                    public void onAddressPicked(Province province, City city, County county) {
//                        if (county == null) {
//                            tvGoodsSources.setText(province.getAreaName() + city.getAreaName());
//                        } else {
//                            tvGoodsSources.setText(province.getAreaName() + city.getAreaName() + county.getAreaName());
//                        }
//                    }
//                });
//                task.execute("上海", "上海", "黄浦");
                intent = new Intent(mContext, AddressCityActivity.class);
                // 启动指定Activity并等待返回的结果，其中 REQUSET 是请求码，用于标识该请求
                startActivityForResult(intent, REQUEST_SHOWADDTwo);
                break;
            case R.id.tv_sava:
                boolean isEmpty_sum = false;
                for (int i = 0; i < viewTests.size(); i++) {
                    if (TextUtils.isEmpty(viewTests.get(i).getTv_QiDingNum().getText().toString())) {
                        isEmpty_sum = true;
                        break;//只要有一个为空的就结束循环，然后在下面判断isEmpty是否等于true
                    }

                    if (TextUtils.isEmpty(viewTests.get(i).getTv_QiDingPrice().getText().toString())) {
                        isEmpty_sum = true;
                        break;//只要有一个为空的就结束循环，然后在下面判断isEmpty是否等于true
                    }
                }

                if (isEmpty_sum) {
                    showToastS("你填写的起订数量和起订价格为空");
                    return;
                }

                initEditor();
                break;
        }
    }

    private void initClear() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("goods_id", getIntent().getStringExtra("supplyId"));
        map.put("type", "1");
        map.put("sign", GetSign.getSign(map));

        ApiRequest.GetPriceDelete(map, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    layout01.removeAllViews();
                    llAddPrices.setVisibility(View.VISIBLE);
                    viewTests.clear();
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }


    private void getViewInstance(View views) {
        ReleaseBeen holderView = new ReleaseBeen();

        tvQiDingID = (MyEditText) views.findViewById(R.id.tv_QiDingID);
        tvQiDingNum = (MyEditText) views.findViewById(R.id.tv_QiDingNum);
        tvQiDingPrice = (MyEditText) views.findViewById(R.id.tv_QiDingPrice);
        holderView.setTv_QiDingNum(tvQiDingNum);
        holderView.setTv_QiDingPrice(tvQiDingPrice);
        holderView.setTv_QiDingID(tvQiDingID);
        viewTests.add(holderView);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_SHOWADDTwo && resultCode == RESULT_OK) {
            tvGoodsSources.setText(data.getStringExtra("mCountry"));
            mGoodsSourcesId = data.getStringExtra("mCountryId");
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
