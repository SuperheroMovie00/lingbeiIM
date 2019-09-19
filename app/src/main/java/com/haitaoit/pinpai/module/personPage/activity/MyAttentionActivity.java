package com.haitaoit.pinpai.module.personPage.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.customview.MyCheckBox;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.add.activity.FriendInfoActivity;
import com.haitaoit.pinpai.add.model.InfoModel;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.findPage.network.response.AttentionObj;
import com.haitaoit.pinpai.module.findPage.network.response.GetIMJsonObj;
import com.haitaoit.pinpai.module.personPage.adapter.MyAttentionAdapter;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.request.PostCollectDeleteItem;
import com.haitaoit.pinpai.tools.BackCall;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.utils.ToastUtil;
import com.haitaoit.pinpai.utils.dialog.LoadDialog;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 我的关注
 * Created by Administrator on 2017/10/30.
 */

public class MyAttentionActivity extends BaseActivity {

    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.ll_date_max)
    MyLinearLayout llDateMax;
    @BindView(R.id.tv_delete)
    MyTextView tvDelete;
    @BindView(R.id.tv_select_all)
    MyCheckBox tvSelectAll;
    @BindView(R.id.rel_bottom)
    RelativeLayout relBottom;
    @BindView(R.id.rv_collec_view)
    RecyclerView rvCollecView;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    //记录Menu的状态
    MyAttentionAdapter mMyAttentionAdapter;
    @BindView(R.id.ll_date_min)
    MyLinearLayout llDateMin;
    //记录选择的Item
    private int mEditMode = MYLIVE_MODE_CHECK;
    private int currentPage = 1;
    List<AttentionObj.ResponseBean> mDataList = new ArrayList<>();


    @Override
    protected int getContentView() {
        return R.layout.my_attention_activity;
    }

    @Override
    protected void initView() {

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

    private void initAdapter() {
        rvCollecView.setLayoutManager(new LinearLayoutManager(this));
        rvCollecView.setAdapter(mMyAttentionAdapter = new MyAttentionAdapter(mContext, mDataList));
        rvCollecView.setNestedScrollingEnabled(false);

        mMyAttentionAdapter.setBackCall(new BackCall() {
            @Override
            public void deal(int which, Object... obj) {
                super.deal(which, obj);
                int pos = Integer.valueOf(obj[0].toString());
                switch (which) {
                    case R.id.tv_Remark:
                        initWeChat(mDataList.get(pos).getUser_id());
                        break;
                }
            }
        });
        mMyAttentionAdapter.setOnItemClickListener(new MyAttentionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("UserId", mDataList.get(position).getUser_id());
                RxActivityUtils.skipActivity(mContext, PersonHomePageActivity.class, bundle);
            }

        });

        mMyAttentionAdapter.setCallback(new MyAttentionAdapter.CartEventCallback() {
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


    private void initWeChat(String mUserID) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("publisher_id", mUserID);
        map.put("sign", GetSign.getSign(map));

        com.haitaoit.pinpai.module.homePage.network.ApiRequest.GetContactAdd(map, new MyCallBack<GetIMJsonObj>(mContext) {
            @Override
            public void onSuccessful(GetIMJsonObj response) {
                if (response.getErrCode() == 0) {
                    initQuery(response.getResponse().getUsername(), "123456", response.getResponse().getAvatar(), response.getResponse().getSendee_name(), response.getResponse().getSendee_avatar());

                } else {
                    showToastS(response.getErrMsg());

                }


            }
        });

    }

    private void initQuery(String userName, final String userPass, final String UserImage, String SendName, final String SendImage) {
        JMessageClient.getUserInfo(SendName, new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                LoadDialog.dismiss(mContext);
                if (responseCode == 0) {
                    InfoModel.getInstance().friendInfo = info;
                    //已经是好友则不显示"加好友"按钮
                    //这个接口会在本地寻找头像文件,不存在就异步拉取
                    File avatarFile = new File(SendImage.toString());
                    Log.e("targetIdavatarFile", avatarFile.toString() + "========" + avatarFile.getAbsolutePath());
                    if (avatarFile != null) {
                        InfoModel.getInstance().setBitmap(BitmapFactory.decodeFile(avatarFile.getAbsolutePath()));
                    } else {
                        InfoModel.getInstance().setBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    }

//                    UserInfo myUserInfo = JMessageClient.getMyInfo();
//                    if (myUserInfo != null) {
//                        myUserInfo.setNickname(mYiUserName);
//                    }
//                    //注册时候更新昵称
//                    JMessageClient.updateMyInfo(UserInfo.Field.nickname, myUserInfo, new BasicCallback() {
//                        @Override
//                        public void gotResult(final int status, String desc) {
//                            //更新跳转标志
//                            SharePreferenceManager.setCachedFixProfileFlag(false);
//                        }
//                    });
                    final Intent intent = new Intent();
                    intent.setClass(mContext, FriendInfoActivity.class);
                    intent.putExtra("addFriend", true);
                    intent.putExtra("targetId", InfoModel.getInstance().friendInfo.getUserName());
                    Log.e("targetId", InfoModel.getInstance().friendInfo.getUserName());
                    Log.e("targetId", SendImage);
                    startActivity(intent);
                } else {
                    ToastUtil.shortToast(mContext, "该用户不存在");
                }
            }
        });
    }
    @Override
    protected void initData() {
        initCollList(true);
        initAdapter();
    }


    private void initCollList(final boolean isRefresh) {
        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }
        map.put("page", currentPage + "");
        map.put("pagesize", Config.pageSize);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetFllowList(map, new MyCallBack<AttentionObj>(mContext) {
            @Override
            public void onSuccessful(AttentionObj response) {
                if (response.getErrCode() == 0) {
                    List<AttentionObj.ResponseBean> result = response.getResponse();
                    if (isRefresh) {
                        mDataList.clear();
                        mDataList.addAll(result);
                    } else {
                        mDataList.addAll(result);
                    }
                    mMyAttentionAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                }else {
                    if (isRefresh) {
                        mDataList.clear();
                        rxTitle.setRightTextVisibility(false);
                        llDateMax.setVisibility(View.GONE);
                        llDateMin.setVisibility(View.VISIBLE);
                        mMyAttentionAdapter.notifyDataSetChanged();
                        RxToast.normal(response.getErrMsg());
                    }

                }
            }
        });
    }


    @OnClick({R.id.rx_title, R.id.tv_select_all, R.id.tv_delete})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_select_all:
                if (tvSelectAll.isChecked()) {
                    mMyAttentionAdapter.setSelectAll(true);
                    mMyAttentionAdapter.notifyDataSetChanged();

                } else {
                    mMyAttentionAdapter.setSelectAll(false);
                    mMyAttentionAdapter.notifyDataSetChanged();
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
        List<String> selectPos = mMyAttentionAdapter.getSelectPos();
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

        ApiRequest.PostFllowDelete(map, item, new MyCallBack<ResponseObj>(mContext) {
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
        mMyAttentionAdapter.setEditMode(mEditMode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}