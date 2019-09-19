package com.haitaoit.pinpai.module.findPage.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyLinearLayout;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.add.activity.FriendInfoActivity;
import com.haitaoit.pinpai.add.model.InfoModel;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.adapter.GridViewBuyAdapter;
import com.haitaoit.pinpai.module.findPage.adapter.SupplyGoodsBottomAdapter;
import com.haitaoit.pinpai.module.findPage.network.response.GetCollAddObj;
import com.haitaoit.pinpai.module.findPage.network.response.GetIMJsonObj;
import com.haitaoit.pinpai.module.homePage.activity.ParagraphWantActivity;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.MyYouShiObj;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.module.personPage.activity.PersonHomePageActivity;
import com.haitaoit.pinpai.module.personPage.network.response.GetNeedDetailObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.tools.RxGlideLoader;
import com.haitaoit.pinpai.utils.ToastUtil;
import com.haitaoit.pinpai.utils.dialog.LoadDialog;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.melnykov.fab.ScrollDirectionListener;
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
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

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
import de.hdodenhof.circleimageview.CircleImageView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 求购详情
 * Created by Administrator on 2017/11/2.
 */

public class PurchaseGoodsDetailActivity extends BaseActivity

{


    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.bn_BannerTop)
    Banner bnBannerTop;
    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.tv_paragraph)
    TextView tvParagraph;
    @BindView(R.id.tv_ReplaceTime)
    TextView tvReplaceTime;
    @BindView(R.id.tv_ReplaceAddress)
    TextView tvReplaceAddress;
    @BindView(R.id.tv_ReplaceNum)
    TextView tvReplaceNum;
    @BindView(R.id.rv_GridView)
    RecyclerView rvGridView;
    @BindView(R.id.tv_GoBuy1)
    TextView tvGoBuy1;
    @BindView(R.id.tv_GoBuy2)
    TextView tvGoBuy2;
    @BindView(R.id.tv_invisible)
    TextView tvInvisible;
    @BindView(R.id.tv_StockNum)
    TextView tvStockNum;
    @BindView(R.id.tv_Remark)
    TextView tvRemark;
    @BindView(R.id.tv_introduce)
    TextView tvIntroduce;
    @BindView(R.id.iv_Open)
    ImageView ivOpen;
    @BindView(R.id.tv_Brand)
    TextView tvBrand;
    @BindView(R.id.tv_Place_of_Origin)
    TextView tvPlaceOfOrigin;
    @BindView(R.id.tv_Category)
    TextView tvCategory;
    @BindView(R.id.tv_bar_Code)
    TextView tvBarCode;
    @BindView(R.id.iv_collect_img)
    CircleImageView ivCollectImg;
    @BindView(R.id.tv_ShopNma)
    TextView tvShopNma;
    @BindView(R.id.tv_Address)
    TextView tvAddress;
    @BindView(R.id.tv_share_goods)
    ImageView tvShareGoods;
    @BindView(R.id.ll_Shop_address)
    MyLinearLayout llShopAddress;
    @BindView(R.id.tv_goods_Number)
    TextView tvGoodsNumber;
    @BindView(R.id.tv_Purchase_number)
    TextView tvPurchaseNumber;
    @BindView(R.id.tv_attention_Number)
    TextView tvAttentionNumber;
    @BindView(R.id.pcfl_refresh)
    PtrClassicFrameLayout pcflRefresh;
    @BindView(R.id.rv_advantage_View)
    RecyclerView rvAdvantageView;
    @BindView(R.id.floating_btn_main)
    FloatingActionButton floatingBtnMain;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.nsv_home)
    ObservableScrollView nsvHome;
    @BindView(R.id.Tv_Collection)
    TextView TvCollection;
    @BindView(R.id.Tv_kefu)
    TextView TvKefu;
    @BindView(R.id.Tv_Communicate)
    TextView TvCommunicate;
    private SupplyGoodsBottomAdapter mSupplyGoodsBottomAdapter;
    private GridViewBuyAdapter mGridViewBuyAdapter;
    List<MyYouShiObj.ResponseBean> mYouHui = new ArrayList<>();
    List<GetNeedDetailObj.ResponseBean.DypricelistBean> mPricesList = new ArrayList<>();
    private int currentPage = 1;
    private IWXAPI api;

    private String mGoodsId, mUserID, mGoodTitle,mContent;

    @Override
    protected int getContentView() {
        return R.layout.home_want_buy_details_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //添加下划线
        tvParagraph.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        initDetailList();
        initAdapter();
        initWx();

        rxTitle.setRightIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShareDialog();

            }
        });
        pcflRefresh.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                initMyList(false);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currentPage = 1;
                initMyList(true);
            }
        });

        floatingBtnMain.hide(false);
        floatingBtnMain.attachToScrollView(nsvHome, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
                floatingBtnMain.show();
            }

            @Override
            public void onScrollUp() {
                floatingBtnMain.show();
            }
        }, new ObservableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                if (t > rvAdvantageView.getHeight()) {
                    floatingBtnMain.show();
                } else {
                    floatingBtnMain.hide();
                }
            }
        });

    }

    private void initMyList(final boolean isRefresh) {
        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", mUserID);
        } else {
            map.put("user_id",mUserID);
        }


        map.put("goods_id", getIntent().getStringExtra("supplyId"));
        map.put("page", currentPage + "");
        map.put("pagesize", "1000");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetUserNeedList(map, new MyCallBack<MyYouShiObj>(mContext) {
            @Override
            public void onSuccessful(MyYouShiObj response) {
                if (response.getErrCode() == 0) {

                    List<MyYouShiObj.ResponseBean> result = response.getResponse();
                    if (isRefresh) {
                        mYouHui.clear();
                        mYouHui.addAll(result);
                    } else {
                        mYouHui.addAll(result);
                    }
                    mSupplyGoodsBottomAdapter.notifyDataSetChanged();
                    currentPage++;
                    pcflRefresh.refreshComplete();
                } else {
                    if (isRefresh) {
                        mSupplyGoodsBottomAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }


    private void initDetailList() {
        Map<String, String> map = new HashMap<String, String>();
        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
            map.put("user_id", "0");
        } else {
            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        }

        if (RxDataUtils.isNullString(getIntent().getStringExtra("msge_id"))) {
            map.put("msge_id", "0");
        } else {
            map.put("msge_id", getIntent().getStringExtra("msge_id"));
        }
        map.put("need_id", getIntent().getStringExtra("supplyId"));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetNeedDetail(map, new MyCallBack<GetNeedDetailObj>(mContext) {
            @Override
            public void onSuccessful(GetNeedDetailObj response) {
                if (response.getErrCode() == 0) {
                    List<GetNeedDetailObj.ResponseBean.DyimglistBean> result = response.getResponse().getDyimglist();
                    setBanner(result);

                    mGoodsId = response.getResponse().getGoods_id() + "";
                    mUserID = response.getResponse().getUser_id() + "";
                    mGoodTitle = response.getResponse().getTitle() + "";
                    initMyList(true);
                    Log.e("mUserID", response.getResponse().getUser_id() + "");
                    tvTitle.setText(response.getResponse().getTitle());
                    tvReplaceTime.setText(response.getResponse().getAdd_time().substring(0, 10));
                    tvReplaceAddress.setText(response.getResponse().getSource());
                    tvReplaceNum.setText(response.getResponse().getColllect_num());
                    mContent = response.getResponse().getZhaiyao();
                    /**
                     * 价格
                     */
                    List<GetNeedDetailObj.ResponseBean.DypricelistBean> pricelist = response.getResponse().getDypricelist();
                    mPricesList.addAll(pricelist);
                    if (pricelist.size() > 0) {
                        for (int i = 0; i < pricelist.size(); i++) {
                            tvGoBuy1.setText(pricelist.get(0).getQuantity() + "件");
                            tvGoBuy2.setText(pricelist.get(0).getPrice() + "元");
                        }
//                        rvGridView.setLayoutManager(new GridLayoutManager(mContext, pricelist.size()));
                    } else {
//                        rvGridView.setLayoutManager(new GridLayoutManager(mContext, 1));
                    }
                    rvGridView.setAdapter(mGridViewBuyAdapter = new GridViewBuyAdapter(mContext, mPricesList, response.getResponse().getIs_price()));
                    rvGridView.setNestedScrollingEnabled(false);
                    mGridViewBuyAdapter.notifyDataSetChanged();


//                    tvStockNum.setText(response.getResponse().getStock() + "件");
                    tvRemark.setText("备货时间预订：" + response.getResponse().getDelivery_time() + "天以内");
                    tvIntroduce.setText(response.getResponse().getZhaiyao());
                    tvBrand.setText(response.getResponse().getBrand());
                    tvPlaceOfOrigin.setText(response.getResponse().getOrigin_place());
                    tvCategory.setText(response.getResponse().getParent()+response.getResponse().getCatrgory());
                    tvBarCode.setText(response.getResponse().getBar_code());

                    Glide.with(mContext).load(response.getResponse().getAvatar()).error(R.mipmap.ic_launcher).into(ivCollectImg);
                    tvShopNma.setText(response.getResponse().getUser_name());
                    tvAddress.setText(response.getResponse().getCertified());
                    tvGoodsNumber.setText(response.getResponse().getGoods_num());


//                    is_colllect=1未收藏   2 已收藏   你写反了
                    if (response.getResponse().getIs_colllect().equals("1")) {
                        TvCollection.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                                mContext.getResources().getDrawable(R.mipmap.img62),
                                null,
                                null);
                    } else {
                        TvCollection.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                                mContext.getResources().getDrawable(R.mipmap.img63),
                                null,
                                null);

                    }

                    if (response.getResponse().getIs_fllow().equals("1")) {
                        TvKefu.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                                mContext.getResources().getDrawable(R.mipmap.img71),
                                null,
                                null);
                    } else {
                        TvKefu.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                                mContext.getResources().getDrawable(R.mipmap.img99),
                                null,
                                null);

                    }


                } else {
                    showToastS(response.getErrMsg());
                }
            }
        });
    }


    private void setBanner(List<GetNeedDetailObj.ResponseBean.DyimglistBean> result) {
        bnBannerTop.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        List<String> images = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            images.add(result.get(i).getImg_url());
        }
        bnBannerTop.setImages(images).setImageLoader(new RxGlideLoader()).start();
        bnBannerTop.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initAdapter() {
        rvAdvantageView.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvAdvantageView.setAdapter(mSupplyGoodsBottomAdapter = new SupplyGoodsBottomAdapter(mContext, mYouHui));
        rvAdvantageView.setNestedScrollingEnabled(false);
        mSupplyGoodsBottomAdapter.setOnItemClickListener(new SupplyGoodsBottomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("supplyId", mYouHui.get(position).getGoods_id());
                RxActivityUtils.skipActivity(mContext, PurchaseGoodsDetailActivity.class, bundle);

            }

        });
    }


    @Override
    protected void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.rx_title, R.id.tv_paragraph, R.id.tv_invisible, R.id.iv_Open, R.id.ll_Shop_address
            , R.id.Tv_Collection, R.id.Tv_kefu, R.id.Tv_Communicate, R.id.floating_btn_main})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_paragraph:
                Bundle parBundle = new Bundle();
                parBundle.putString("mGoodsId", mGoodsId);
                parBundle.putString("mGoodTitle", mGoodTitle);
                RxActivityUtils.skipActivity(mContext, ParagraphWantActivity.class, parBundle);
                break;
            case R.id.tv_invisible:
                break;
            case R.id.iv_Open:
                break;
            case R.id.ll_Shop_address:
                Bundle bundle = new Bundle();
                bundle.putString("UserId", mUserID + "");
                RxActivityUtils.skipActivity(mContext, PersonHomePageActivity.class, bundle);
                break;
            case R.id.Tv_Collection:
                if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                } else {
                    initCollAdd();
                }

                break;
            case R.id.Tv_kefu:
                if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                } else {
                    iniFllowAdd();
                }

                break;
            case R.id.Tv_Communicate:
                if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
                    RxActivityUtils.skipActivity(mContext, LoginActivity.class);
                } else {
                    initWeChat();
                }
                break;
            case R.id.floating_btn_main:
                nsvHome.fullScroll(ScrollView.FOCUS_UP);
                break;


        }
    }


    private void initCollAdd() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("obj_id", mGoodsId);
        map.put("type", "2");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetCollectAdd(map, new MyCallBack<GetCollAddObj>(mContext) {
            @Override
            public void onSuccessful(GetCollAddObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    if (response.getErrMsg().equals("收藏成功")) {
                        TvCollection.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                                mContext.getResources().getDrawable(R.mipmap.img63),
                                null,
                                null);
                    } else if (response.getErrMsg().equals("取消收藏成功")) {
                        TvCollection.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                                mContext.getResources().getDrawable(R.mipmap.img62),
                                null,
                                null);

                    }

                } else {
                    showToastS(response.getErrMsg());

                }


            }
        });


    }

    private void initWeChat() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("publisher_id", mUserID);
        map.put("sign", GetSign.getSign(map));

        ApiRequest.GetContactAdd(map, new MyCallBack<GetIMJsonObj>(mContext) {
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



    private void iniFllowAdd() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("obj_id", mUserID);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetFllowAdd(map, new MyCallBack<GetCollAddObj>(mContext) {
            @Override
            public void onSuccessful(GetCollAddObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    if (response.getErrMsg().equals("关注成功")) {
                        TvKefu.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                                mContext.getResources().getDrawable(R.mipmap.img99),
                                null,
                                null);
                    } else if (response.getErrMsg().equals("取消关注成功")) {
                        TvKefu.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                                mContext.getResources().getDrawable(R.mipmap.img71),
                                null,
                                null);

                    }

                } else {
                    showToastS(response.getErrMsg());

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
                sendToWeiXin(mGoodTitle, "http://www.linkpel.com/", mContent, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 0);

            }

        });
        v.findViewById(R.id.sina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                sendToWeiXin(mGoodTitle, "http://www.linkpel.com/", mContent, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 1);

            }
        });

    }

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

    private void initWx() {
        api = WXAPIFactory.createWXAPI(this, Config.APP_ID_WX, true);
        // 将该app注册到微信
        api.registerApp(Config.APP_ID_WX);
    }

}
