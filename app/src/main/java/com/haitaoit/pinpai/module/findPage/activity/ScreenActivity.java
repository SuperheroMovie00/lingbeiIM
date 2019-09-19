package com.haitaoit.pinpai.module.findPage.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.customview.MyEditText;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.adapter.ScreenAdapter;
import com.haitaoit.pinpai.module.findPage.network.ApiRequest;
import com.haitaoit.pinpai.module.findPage.network.response.GetGoodsCategoryObj;
import com.haitaoit.pinpai.module.releasePage.activity.ParentCateListActivity;
import com.haitaoit.pinpai.view.FlowLayout;
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
 * 筛选
 * * Created by Administrator on 2017/12/1.
 */

public class ScreenActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.time_tv)
    TextView timeTv;
    @BindView(R.id.iv_Shop_class)
    LinearLayout ivShopClass;
    @BindView(R.id.fl_Shop_out)
    FlowLayout flShopOut;
    @BindView(R.id.iv_Band_class)
    ImageView ivBandClass;
    @BindView(R.id.rv_Band_view)
    RecyclerView rvBandView;
    @BindView(R.id.iv_Address_class)
    ImageView ivAddressClass;
    @BindView(R.id.fl_Address_out)
    FlowLayout flAddressOut;
    @BindView(R.id.tv_time_Replace)
    MyEditText tvTimeReplace;
    @BindView(R.id.tv_Shop_class)
    TextView tvShopClass;
    private String mCategoryID1;
    private String mCategoryID2;
    private String mBrandId, mAddressID,mTimeReplace;

    List<GetGoodsCategoryObj.ResponseBean.DybrandlistBean> mBrandBeanList = new ArrayList<>();
    List<GetGoodsCategoryObj.ResponseBean.DycaregorylistBean> mCaregoryBeanList = new ArrayList<>();
    List<GetGoodsCategoryObj.ResponseBean.DysupplylistBean> mSupplyBeanList = new ArrayList<>();
    private ScreenAdapter mScreenAdapter;

    public static int flag = 0;
    public static int flagBrand = 0;
    public static int flagAddress = 0;
    public static final int REQUEST_PAYEE = 0x027;

    @Override
    protected int getContentView() {
        return R.layout.screen_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCategoryID1 = "";
                mCategoryID2 = "";
                mBrandId = "";
                mAddressID = "";
                refreshCheckBoxTime("");
                tvTimeReplace.setText("0");
            }
        });
        rxTitle.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mCategoryID1)) {
                    mCategoryID1 = "0";
                }

                if (TextUtils.isEmpty(mCategoryID2)) {
                    mCategoryID2 = "0";
                }

                if (TextUtils.isEmpty(mAddressID)) {
                    mAddressID = "0";
                }

                if (TextUtils.isEmpty(tvTimeReplace.getText().toString().trim())) {
                    mTimeReplace = "0";
                }else{
                    mTimeReplace = tvTimeReplace.getText().toString().trim();
                }

                Intent intent = getIntent();
                intent.putExtra("mCategoryID1", mCategoryID1);
                intent.putExtra("mCategoryID2", mCategoryID2);
                intent.putExtra("mBrandId", mBrandId);
                intent.putExtra("mAddressID", mAddressID);
                intent.putExtra("mTimeReplace", tvTimeReplace.getText().toString().trim());
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

    @Override
    protected void initData() {
        initCategory();
        initAdapter();
    }

    private void initCategory() {
        Map<String, String> map = new HashMap<String, String>();
        ApiRequest.GetGoodsCategoryList(map, new MyCallBack<GetGoodsCategoryObj>(mContext) {
            @Override
            public void onSuccessful(GetGoodsCategoryObj response) {
                if (response.getErrCode() == 0) {
                    List<GetGoodsCategoryObj.ResponseBean.DybrandlistBean> mBrandList = response.getResponse().getDybrandlist();
                    List<GetGoodsCategoryObj.ResponseBean.DycaregorylistBean> mCaregoryList = response.getResponse().getDycaregorylist();
                    List<GetGoodsCategoryObj.ResponseBean.DysupplylistBean> mSupplyList = response.getResponse().getDysupplylist();
                    mBrandBeanList.addAll(mBrandList);
                    if (mBrandBeanList.size()>0){
                        mBrandId="0";
                    }else {
                        mBrandId="0";
                    }
                    mCaregoryBeanList.addAll(mCaregoryList);
                    mSupplyBeanList.addAll(mSupplyList);
                    mScreenAdapter.notifyDataSetChanged();
                    initFlow();
                } else {
                    showToastS(response.getErrMsg());
                }
            }
        });
    }


    private void initAdapter() {
        rvBandView.setLayoutManager(new GridLayoutManager(mContext, 3));
        rvBandView.setAdapter(mScreenAdapter = new ScreenAdapter(mContext, mBrandBeanList));
        rvBandView.setNestedScrollingEnabled(false);
        mScreenAdapter.setDefaultCheckedItemPosition(0);
        mScreenAdapter.setOnItemClickListener(new ScreenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mBrandId = mBrandBeanList.get(position).getTitle();

            }

        });

    }

    private void initFlow() {
        //第二步：移除FlowLayout中的所有子布局
        flShopOut.removeAllViews();
        //第三步：循环创建子View，添加到FlowLayout中
        for (int x = 0; x < mCaregoryBeanList.size(); x++) {
            //3.1初始化子view
            int ranHeight = dip2px(this, ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ranHeight);
            lp.setMargins(dip2px(this, 10), 15, dip2px(this, 10), 10);
            CheckBox checkBox = (CheckBox) View.inflate(mContext, R.layout.item_hot_flowlayout, null);
            checkBox.setText(mCaregoryBeanList.get(x).getTitle());

            final int finalX = x;
            //3.2设置子view的点击事件
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //遍历FlowLayout中的所有view，如果是当前选中的view，设置为选中状态，其他设置为未选中状态
                    refreshCheckBox(mCaregoryBeanList.get(finalX).getCategory_id());
                }
            });
            //3.3将子view添加到FlowLayout中
            flShopOut.addView(checkBox, lp);
        }

        //第二步：移除FlowLayout中的所有子布局
        flAddressOut.removeAllViews();
        //第三步：循环创建子View，添加到FlowLayout中
        for (int x = 0; x < mSupplyBeanList.size(); x++) {
            //3.1初始化子view
            int ranHeight = dip2px(this, ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ranHeight);
            lp.setMargins(dip2px(this, 10), 15, dip2px(this, 10), 10);
            CheckBox checkBox = (CheckBox) View.inflate(mContext, R.layout.item_hot_flowlayout, null);
            checkBox.setText(mSupplyBeanList.get(x).getTitle());

            final int finalX = x;
            //3.2设置子view的点击事件
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAddressID = mSupplyBeanList.get(finalX).getTitle();
                    //遍历FlowLayout中的所有view，如果是当前选中的view，设置为选中状态，其他设置为未选中状态
                    refreshCheckBoxTime(mSupplyBeanList.get(finalX).getTitle());
                }
            });
            //3.3将子view添加到FlowLayout中
            flAddressOut.addView(checkBox, lp);
        }
    }


    /**
     * 遍历FlowLayout中的所有view，如果是当前选中的view，设置为选中状态，其他设置为未选中状态
     *
     * @param name
     */
    private void refreshCheckBox(String name) {
        for (int y = 0; y < flShopOut.getChildCount(); y++) {
            CheckBox radio = (CheckBox) flShopOut.getChildAt(y);

            if (name.equals(radio.getText())) {
                radio.setChecked(true);
            } else {
                radio.setChecked(false);
            }

        }
    }

    private void refreshCheckBoxTime(String name) {
        for (int y = 0; y < flAddressOut.getChildCount(); y++) {
            CheckBox radio = (CheckBox) flAddressOut.getChildAt(y);

            if (name.equals(radio.getText())) {
                radio.setChecked(true);
            } else {
                radio.setChecked(false);
            }

        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rx_title, R.id.iv_Shop_class, R.id.iv_Band_class, R.id.iv_Address_class})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.iv_Shop_class:
                startActivityForResult(new Intent(this, ParentCateListActivity.class), REQUEST_PAYEE);
                break;
            case R.id.iv_Band_class:
                if (flagBrand == 0) {
                    // 第一次单击触发的事件
                    flagBrand = 1;
                    rvBandView.setVisibility(View.GONE);
                } else {
                    // 第二次单击buttont改变触发的事件
                    flagBrand = 0;
                    rvBandView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_Address_class:
                if (flagAddress == 0) {
                    // 第一次单击触发的事件
                    flagAddress = 1;
                    flAddressOut.setVisibility(View.GONE);
                } else {
                    // 第二次单击buttont改变触发的事件
                    flagAddress = 0;
                    flAddressOut.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_PAYEE && resultCode == RESULT_OK) {
            tvShopClass.setText(data.getStringExtra("mBrandName") + "  " + data.getStringExtra("mCateName"));
            mCategoryID1 = data.getStringExtra("mBrandId");
            mCategoryID2 = data.getStringExtra("mCateId");
        }
    }
}
