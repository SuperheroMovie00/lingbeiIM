package com.haitaoit.pinpai.module.personPage.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.customview.MyCheckBox;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.callback.OnItemListener;
import com.haitaoit.pinpai.module.personPage.adapter.MineRadioAdapter;
import com.haitaoit.pinpai.module.personPage.adapter.MyAttentionAdapter;
import com.haitaoit.pinpai.module.personPage.bean.ItemModel;
import com.vondear.rxtools.view.RxTitle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by Administrator on 2017/12/4.
 */

public class TextActivity extends BaseActivity {

    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;
    List<ItemModel> mDataList;
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.rv_collec_view)
    RecyclerView rvCollecView;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    @BindView(R.id.ll_date_max)
    MyLinearLayout llDateMax;
    @BindView(R.id.tv_delete)
    MyTextView tvDelete;
    @BindView(R.id.tv_select_all)
    MyCheckBox tvSelectAll;
    @BindView(R.id.rel_bottom)
    RelativeLayout relBottom;
    //记录Menu的状态
    private boolean isSelectAll;
    MineRadioAdapter mRvItemAdapter;
    //记录选择的Item
    private HashSet<Integer> positionSet;
    private int mEditMode = MYLIVE_MODE_CHECK;

    @Override
    protected int getContentView() {
        return R.layout.text_activty;
    }

    @Override
    protected void initView() {

        rxTitle.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataEditMode();
            }
        });

    }

    private void initAdapter() {
        rvCollecView.setLayoutManager(new LinearLayoutManager(this));
        rvCollecView.setAdapter(mRvItemAdapter = new MineRadioAdapter(mContext, mDataList));
        rvCollecView.setNestedScrollingEnabled(false);
        mRvItemAdapter.setOnItemClickListener(new MineRadioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

        });
    }
    @Override
    protected void initData() {
        positionSet = new HashSet<>();
        mDataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ItemModel item = new ItemModel();
            mDataList.add(item);
        }
        initAdapter();
    }


    @OnClick({R.id.rx_title, R.id.tv_select_all, R.id.tv_delete})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                finish();
                break;
            case R.id.tv_select_all:
                if (!isSelectAll) {
                    isSelectAll = true;
                    for (int i = 0; i < mDataList.size(); i++) {
                        mDataList.get(i).isSelect = true;
                        positionSet.add(i);
                    }
                    mRvItemAdapter.notifyDataSetChanged();
                } else {
                    isSelectAll = false;
                    for (int i = 0; i < mDataList.size(); i++) {
                        mDataList.get(i).isSelect = false;
                        positionSet.remove(i);
                    }
                    mRvItemAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_delete:
                HashSet<ItemModel> valueSet = new HashSet<>();
                for (Integer integer : positionSet) {
                    valueSet.add(mRvItemAdapter.getItem(integer));
                }
                for (ItemModel itemModel : valueSet) {
                    mRvItemAdapter.remove(itemModel);
                }
                mRvItemAdapter.notifyDataSetChanged();
                positionSet.clear();
                break;
        }
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
        mRvItemAdapter.setEditMode(mEditMode);
    }
}