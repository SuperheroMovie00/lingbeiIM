package com.haitaoit.pinpai.module.personPage.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.github.customview.MyCheckBox;
import com.github.customview.MyRadioButton;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.personPage.adapter.OneButtonBuyAdapter;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.request.PostCollectDeleteItem;
import com.haitaoit.pinpai.module.personPage.network.request.PostNeedShareItem;
import com.haitaoit.pinpai.module.personPage.network.response.GetUserGooddListObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialog;

import java.io.File;
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
 * 一键分享求购
 * Created by Administrator on 2017/10/30.
 */

public class OneButtonBuyActivity extends BaseActivity {

    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.rv_collec_view)
    RecyclerView rvCollecView;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    @BindView(R.id.tv_select_all)
    MyCheckBox tvSelectAll;
    @BindView(R.id.tv_share)
    MyTextView tvShare;
    private OneButtonBuyAdapter mOneButtonBuyAdapter;
    //记录选择的Item
    private int currentPage = 1;
    List<GetUserGooddListObj.ResponseBean> mDataList = new ArrayList<>();
    private Dialog photoDialog;

    private String mInPrices = "0";
    private String mInNUm = "0";
    private IWXAPI api;

    @Override
    protected int getContentView() {
        return R.layout.one_button_share_goods_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setTitle("一件分享求购");
        initWx();
        initRefresh();
        initListener();
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
        map.put("seach", "");
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
                    } else {
                        mDataList.addAll(result);
                    }
                    mOneButtonBuyAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    RxToast.normal(response.getErrMsg());
                }

            }
        });
    }

    private void initAdapter() {
        rvCollecView.setLayoutManager(new LinearLayoutManager(mContext));
        rvCollecView.setAdapter(mOneButtonBuyAdapter = new OneButtonBuyAdapter(mContext, mDataList));
        rvCollecView.setNestedScrollingEnabled(false);
        mOneButtonBuyAdapter.setOnItemClickListener(new OneButtonBuyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mDataList.get(position).getGoods_id());
                RxActivityUtils.skipActivityAndFinish(mContext, SupplyDetailActivity.class, bundle);
            }

        });
        mOneButtonBuyAdapter.setCallback(new OneButtonBuyAdapter.CartEventCallback() {
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
    @OnClick({R.id.rx_title, R.id.tv_select_all, R.id.tv_share})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select_all:
                if (tvSelectAll.isChecked()) {
                    mOneButtonBuyAdapter.setSelectAll(true);
                    mOneButtonBuyAdapter.notifyDataSetChanged();
                } else {
                    mOneButtonBuyAdapter.setSelectAll(false);
                    mOneButtonBuyAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_share:
                initShare();
                break;
        }
    }


    /**
     * 删除数据
     */
    private void initShare() {
        List<String> selectPos = mOneButtonBuyAdapter.getSelectPos();
        if (selectPos.size() == 0) {
            RxToast.error("请先要分享的条目");
            return;
        }

        showPhotoDialog();

    }


    private void showPhotoDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_popwindows_choose, null);

        //初始化三个按钮
        MyRadioButton rbInvisPrices = (MyRadioButton) view.findViewById(R.id.Rb_InvisPrice);
        MyRadioButton rbOnvisPrices = (MyRadioButton) view.findViewById(R.id.Rb_OnvisPrice);
        MyRadioButton rbInvisNum = (MyRadioButton) view.findViewById(R.id.Rb_InvisNum);
        MyRadioButton rbOnvisNum = (MyRadioButton) view.findViewById(R.id.Rb_OnvisNum);

        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        rbInvisPrices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInPrices = "1";
            }
        });
        rbOnvisPrices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInPrices = "2";
            }
        });

        rbInvisNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInNUm = "1";
            }
        });

        rbOnvisNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInNUm = "2";
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInPrices.equals("0")) {
                    showToastL("是否显示价格为空");
                    return;
                }

                if (mInNUm.equals("0")) {
                    showToastL("是否显示数量为空");
                    return;
                }

                initVisibleShare();
            }
        });
        photoDialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        photoDialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = photoDialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();


//        wl.x = 0;
//        wl.y = getWindowManager().getDefaultDisplay().getHeight();
//        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER_VERTICAL);
        // 设置显示位置
        photoDialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
//        photoDialog.setCanceledOnTouchOutside(true);
        photoDialog.show();

    }

    private void initVisibleShare() {
        PostNeedShareItem item = new PostNeedShareItem();
        List<PostNeedShareItem.IdlistBean> shops = new ArrayList<>();
        List<String> selectPos = mOneButtonBuyAdapter.getSelectPos();
        for (int i = 0; i < selectPos.size(); i++) {
            int pos = Integer.valueOf(selectPos.get(i));
            PostNeedShareItem.IdlistBean bodyBean = new PostNeedShareItem.IdlistBean();
            bodyBean.setId(mDataList.get(pos).getGoods_id());
            shops.add(bodyBean);
        }
        item.setIs_price(mInPrices);
        item.setIs_num(mInNUm);
        item.setIdlist(shops);
        //进行网络请求
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("page", "1");
        map.put("pagesize", Config.pageSize);
        String sign = GetSign.getSign(map);
        map.put("sign", sign);

        ApiRequest.PostNeedShare(map, item, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    photoDialog.dismiss();
                    showShareDialog();
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    private void showShareDialog() {
        final RxDialog dialog = new RxDialog(mContext);
        View v = LayoutInflater.from(mContext).inflate(R.layout.dialog_my_share, null);
        dialog.setContentView(v);
        dialog.show();

        WindowManager.LayoutParams params = dialog.getLayoutParams();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);


        v.findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        v.findViewById(R.id.quan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                sendToWeiXin("拎贝", "http://www.linkpel.com/", "拎贝", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 0);

            }

        });
        v.findViewById(R.id.sina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                sendToWeiXin("拎贝", "http://www.linkpel.com/", "拎贝", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 1);

            }
        });

    }

    private void initWx() {
        api = WXAPIFactory.createWXAPI(this, Config.APP_ID_WX, true);
        // 将该app注册到微信
        api.registerApp(Config.APP_ID_WX);
    }

    /**
     * @param title       分享的标题
     * @param openUrl     点击分享item打开的网页地址url
     * @param description 网页的描述
     * @param icon        分享item的图片
     * @param requestCode 0表示为分享到微信好友  1表示为分享到朋友圈 2表示微信收藏
     */
    public void sendToWeiXin(String title, String openUrl, String description, Bitmap icon, int requestCode) {
        //初始化一个WXWebpageObject对象，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = openUrl;
        //Y用WXWebpageObject对象初始化一个WXMediaMessage对象，填写标题、描述
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;//网页标题
        msg.description = description;//网页描述
        msg.setThumbImage(icon);
        //构建一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "supplier";
        req.message = msg;
        req.scene = requestCode;
        api.sendReq(req);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
