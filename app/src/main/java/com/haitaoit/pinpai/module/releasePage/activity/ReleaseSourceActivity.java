package com.haitaoit.pinpai.module.releasePage.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyCheckBox;
import com.github.customview.MyEditText;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.releasePage.network.ApiRequest;
import com.haitaoit.pinpai.module.releasePage.network.request.PostUserReplaceItem;
import com.haitaoit.pinpai.module.releasePage.network.request.ReleaseBeen;
import com.haitaoit.pinpai.module.releasePage.network.response.GetLibraryObj;
import com.haitaoit.pinpai.tools.BitmapTools;
import com.haitaoit.pinpai.tools.GetImagePath;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.OtherUtils;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.view.AddressPickTask;
import com.haitaoit.pinpai.view.ImageFlowLayout;
import com.vondear.rxtools.RxActivityUtils;
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
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;

/**
 * Created by Administrator on 2017/11/13.
 */

public class ReleaseSourceActivity extends BaseActivity {


    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.tv_CommodityName)
    EditText tvCommodityName;
    @BindView(R.id.tv_CommodityCode)
    EditText tvCommodityCode;
    @BindView(R.id.tv_BrandName)
    EditText tvBrandName;
    @BindView(R.id.tv_GoodsCategory1)
    TextView tvGoodsCategory1;
    @BindView(R.id.tv_GoodsCategory2)
    TextView tvGoodsCategory2;
    @BindView(R.id.tv_Place_of_Origin)
    TextView tvPlaceOfOrigin;
    @BindView(R.id.tv_ShopNmabrief)
    EditText tvShopNmabrief;
    @BindView(R.id.img_flow_layout)
    ImageFlowLayout imgFlowLayout;
    @BindView(R.id.tv_Reset)
    TextView tvReset;
    @BindView(R.id.my_tv_editor)
    MyTextView myTvEditor;
    @BindView(R.id.tv_QiDingNum1)
    MyEditText tvQiDingNum1;
    @BindView(R.id.tv_QiDingPrice1)
    MyEditText tvQiDingPrice1;
    @BindView(R.id.layout01)
    LinearLayout layout01;
    @BindView(R.id.tv_AddShop)
    TextView tvAddShop;
    @BindView(R.id.ll_Add_prices)
    LinearLayout llAddPrices;
    @BindView(R.id.tv_StockNum)
    MyEditText tvStockNum;
    @BindView(R.id.tv_Goods_Sources)
    TextView tvGoodsSources;
    @BindView(R.id.ll_Goods_Sources)
    MyLinearLayout llGoodsSources;
    @BindView(R.id.tv_Delivery_time)
    EditText tvDeliveryTime;
    @BindView(R.id.ll_Delivery_time)
    MyLinearLayout llDeliveryTime;
    @BindView(R.id.CBox_defeult)
    MyCheckBox CBoxDefeult;
    @BindView(R.id.tv_SetAsGoodsSources)
    MyTextView tvSetAsGoodsSources;
    @BindView(R.id.gridlayout)
    GridLayout gridlayout;
    private String mYouShiGoods = "1";

    private List<PostUserReplaceItem.ImgListBean> imgDatas = new ArrayList<>();
    private List<PostUserReplaceItem.PriceListBean> priceDatas = new ArrayList<>();
    private int updateIndex = -1;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int CHOOSE_PICTURE2 = 222;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private Dialog photoDialog;
    protected static Uri tempUri;
    private LayoutInflater layoutInflater;
    View views;
    MyEditText tvQiDingID;
    MyEditText tvQiDingNum;
    MyEditText tvQiDingPrice;
    private List<ReleaseBeen> viewTests = new ArrayList<>();
    List<GetLibraryObj.ResponseBean.DypricelistBean> mPricesList = new ArrayList<>();
    List<GetLibraryObj.ResponseBean.DyimglistBean> mimgList = new ArrayList<>();

    public static final int REQUEST_PAYEE = 0x027;
    private String mParentId;
    private String mCateId;
    private int sw;

    public static final int REQUEST_SHOWADDOne = 0x012;
    public static final int REQUEST_SHOWADDTwo = 0x013;

    private String mPlaceOfOriginId, mGoodsSourcesId;

    private String mTatching;

    @Override
    protected int getContentView() {
        return R.layout.replace_source_activity;
    }

    @Override
    protected void initView() {
        layoutInflater = getLayoutInflater();
        sw = (OtherUtils.getScreenWidth(mContext) - OtherUtils.dip2px(mContext, 40)) / 3;
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CBoxDefeult.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    CBoxDefeult.setEnabled(true);
                    mYouShiGoods = "2";
                } else {
                    CBoxDefeult.setEnabled(false);
                    mYouShiGoods = "1";
                }
            }
        });

        tvCommodityName.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    if (TextUtils.isEmpty(tvCommodityName.getText().toString().trim())) {
                        showToastS("请输入商品名称");
                        return;
                    }

                    initGlibrary();
                }
            }
        });
    }

    @Override
    protected void initData() {
        initImgFlow();
    }


    @OnClick({R.id.my_tv_editor, R.id.CBox_defeult, R.id.tv_AddShop, R.id.tv_SetAsGoodsSources, R.id.tv_Goods_Sources, R.id.tv_Reset
            , R.id.tv_BrandName, R.id.tv_GoodsCategory1, R.id.tv_Place_of_Origin})
    public void onViewClick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.tv_Reset:
                layout01.removeAllViews();
                llAddPrices.setVisibility(View.VISIBLE);
                viewTests.clear();
                break;
            case R.id.tv_Place_of_Origin:
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
//                            tvPlaceOfOrigin.setText(province.getAreaName() + city.getAreaName());
//                        } else {
//                            tvPlaceOfOrigin.setText(province.getAreaName() + city.getAreaName() + county.getAreaName());
//                        }
//                    }
//                });
//                task.execute("上海", "上海", "黄浦");
                intent = new Intent(mContext, AddressCityActivity.class);
                // 启动指定Activity并等待返回的结果，其中 REQUSET 是请求码，用于标识该请求
                startActivityForResult(intent, REQUEST_SHOWADDOne);
                break;
            case R.id.tv_BrandName:

                break;
            case R.id.tv_GoodsCategory1:
                startActivityForResult(new Intent(this, ParentCateListActivity.class), REQUEST_PAYEE);
                break;
            case R.id.tv_Goods_Sources:
//                AddressPickTask taskSources = new AddressPickTask(this);
//                taskSources.setHideProvince(false);
//                taskSources.setHideCounty(false);
//                taskSources.setCallback(new AddressPickTask.Callback() {
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
//                taskSources.execute("上海", "上海", "黄浦");
                intent = new Intent(mContext, AddressCityActivity.class);
                // 启动指定Activity并等待返回的结果，其中 REQUSET 是请求码，用于标识该请求
                startActivityForResult(intent, REQUEST_SHOWADDTwo);
                break;
            case R.id.tv_AddShop:
                if (viewTests.size() == 3) {
                    showToastS("最多只能添加三条");
                } else {
                    views = layoutInflater.inflate(R.layout.activity_index_gallery_add, null);
                    layout01.addView(views);
                    getViewInstance(views);
                }
                break;
            case R.id.tv_SetAsGoodsSources:
                if (TextUtils.isEmpty(tvCommodityName.getText().toString())) {
                    showToastS("商品名称不能为空");
                    return;
                }

                if (TextUtils.isEmpty(tvCommodityCode.getText().toString())) {
                    showToastS("商品条形码不能为空");
                    return;
                }

                if (TextUtils.isEmpty(tvBrandName.getText().toString())) {
                    showToastS("品牌名不能为空");
                    return;
                }

                if (TextUtils.isEmpty(tvGoodsCategory1.getText().toString())) {
                    showToastS("商品分类不能为空");
                    return;
                }

                if (TextUtils.isEmpty(tvGoodsCategory2.getText().toString())) {
                    showToastS("商品二级分类不能为空");
                    return;
                }
                if (TextUtils.isEmpty(tvPlaceOfOrigin.getText().toString())) {
                    showToastS("产地不能为空");
                    return;
                }

                if (TextUtils.isEmpty(tvGoodsSources.getText().toString())) {
                    showToastS("货源地不能为空");
                    return;
                }

                if (TextUtils.isEmpty(tvDeliveryTime.getText().toString())) {
                    showToastS("交货不能为空");
                    return;
                }

                if (TextUtils.isEmpty(tvStockNum.getText().toString())) {
                    showToastS("库存不能为空");
                    return;
                }


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

                if (imgDatas.size() == 0) {
                    RxToast.error("图片不能为空");
                    return;
                }


                priceDatas.clear();
                for (int i = 0; i < viewTests.size(); i++) {
                    Log.e(viewTests.size() + "-responsible_person", viewTests.size() + "==========555555555566666666666666545454545454545454545454545454=====");
                    ReleaseBeen holderView = viewTests.get(i);
                    PostUserReplaceItem.PriceListBean priceListBean = new PostUserReplaceItem.PriceListBean();
                    priceListBean.setPrice(holderView.getTv_QiDingPrice().getText().toString() + "|" + holderView.getTv_QiDingNum().getText().toString());
                    priceDatas.add(priceListBean);
                }

                if (priceDatas.size() == 0) {
                    RxToast.error("起订量不可以小于1组");
                    return;
                }
                initReplace();
                break;
        }
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


    private void initReplace() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));
        PostUserReplaceItem bean = new PostUserReplaceItem();
        bean.setTitle(tvCommodityName.getText().toString());
        bean.setBar_code(tvCommodityCode.getText().toString());
        bean.setBrand(tvBrandName.getText().toString());
        bean.setCategory_id(mCateId);
        bean.setParent_id(mParentId);
        bean.setOrigin_place(tvPlaceOfOrigin.getText().toString());
        bean.setZhaiyao(tvShopNmabrief.getText().toString());
        bean.setStock(tvStockNum.getText().toString());
        bean.setSource(tvGoodsSources.getText().toString());
        bean.setDelivery_time(tvDeliveryTime.getText().toString());
        bean.setIs_advantage(mYouShiGoods + "");
        bean.setIs_matching(mTatching);
        bean.setImg_list(imgDatas);
        bean.setPrice_list(priceDatas);

        ApiRequest.PostUserGoods(map, bean, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    RxActivityUtils.skipActivityAndFinish(mContext, ReleaseSuccessActivity.class);
                } else if (response.getErrCode() == 4) {
                    showToastS(response.getErrMsg());
                    RxActivityUtils.skipActivityAndFinish(mContext, ReleaseErrorActivity.class);
                } else if (response.getErrCode() == 2) {
                    showToastS(response.getErrMsg());
                    RxActivityUtils.skipActivityAndFinish(mContext, ReleaseErrorActivity.class);
                } else if (response.getErrCode() == 3) {
                    showToastS(response.getErrMsg());
                    RxActivityUtils.skipActivityAndFinish(mContext, ReleaseErrorActivity.class);
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    //品牌 下面的分类
    private void initImgFlow() {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(Config.dip2px(mContext, 180), Config.dip2px(mContext, 180));
        lp.leftMargin = 15;
        lp.rightMargin = 15;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        final ImageView view = new ImageView(this);
        view.setTag("add");
        view.setImageResource(R.mipmap.img34);
        view.setScaleType(ImageView.ScaleType.FIT_XY);

        imgFlowLayout.addView(view, lp);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag().toString().equals("add")) {
                    updateIndex = -1;
                    showPhotoDialog();
                }
            }
        });
    }

    private void showPhotoDialog() {

        View view = getLayoutInflater().inflate(R.layout.dialog_photo_choose, null);
        //初始化三个按钮
        Button btnPic = (Button) view.findViewById(R.id.btn_pic);
        Button btnCamera = (Button) view.findViewById(R.id.btn_camera);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        //响应点击事件
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    photoDialog.cancel();
                    Intent openAlbumIntent = new Intent(
                            Intent.ACTION_GET_CONTENT);
                    openAlbumIntent.addCategory(Intent.CATEGORY_OPENABLE);
                    openAlbumIntent.setType("image/*");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
                        File newFile = new File(Environment.getExternalStorageDirectory(), "Android/data/com.haitaoit.pinpai/files/header");
                        if (!newFile.exists()) {
                            newFile.mkdirs();
                        }
                        File fil = new File(newFile, "image11.jpg");
                        String authority = mContext.getPackageName() + ".provider";
                        Uri uriForFile = FileProvider.getUriForFile(mContext, authority, fil);
                        openAlbumIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                        openAlbumIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        openAlbumIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE2);
                    } else {
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                    }
                }
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoDialog.dismiss();
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    photoDialog.dismiss();
                    if (Build.VERSION.SDK_INT >= 24) {
                        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
                            File newFile = new File(Environment.getExternalStorageDirectory(), "Android/data/com.haitaoit.pinpai/files/header");
                            if (!newFile.exists()) {
                                newFile.mkdirs();
                            }
                            File fil = new File(newFile, "image.jpg");
                            String authority = mContext.getPackageName() + ".provider";
                            tempUri = FileProvider.getUriForFile(mContext, authority, fil);
                            Log.i("", "contentUri = " + tempUri.toString());
                            List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(takePhotoIntent, PackageManager.MATCH_DEFAULT_ONLY);
                            for (ResolveInfo resolveInfo : resInfoList) {
                                String packageName = resolveInfo.activityInfo.packageName;
                                grantUriPermission(packageName, tempUri,
                                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            }
                            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                            startActivityForResult(takePhotoIntent, TAKE_PICTURE);
                        }
                    } else {
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoDialog.dismiss();
            }
        });


        photoDialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        photoDialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = photoDialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        photoDialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        photoDialog.setCanceledOnTouchOutside(true);
        photoDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_SHOWADDOne && resultCode == RESULT_OK) {
            tvPlaceOfOrigin.setText(data.getStringExtra("mCountry"));
            mPlaceOfOriginId = data.getStringExtra("mCountryId");
        }

        if (requestCode == REQUEST_SHOWADDTwo && resultCode == RESULT_OK) {
            tvGoodsSources.setText(data.getStringExtra("mCountry"));
            mGoodsSourcesId = data.getStringExtra("mCountryId");
        }
        if (requestCode == REQUEST_PAYEE && resultCode == RESULT_OK) {
            tvGoodsCategory1.setText(data.getStringExtra("mBrandName"));
            tvGoodsCategory2.setText(data.getStringExtra("mCateName"));
            mParentId = data.getStringExtra("mBrandId");
            mCateId = data.getStringExtra("mCateId");
        }
        switch (requestCode) {
            case TAKE_PICTURE:
                Log.e("TAKE_PICTURE11111", tempUri + "");
                startPhotoZoom(tempUri);
                break;
            case CHOOSE_PICTURE:
                Log.e("CHOOSE_PICTURE从手机相册获取", data.getData() + "");
                startPhotoZoom(data.getData());
                break;
            case CHOOSE_PICTURE2:
                Log.e("CHOOSE_PICTURE从2222  ", data.getData() + "");


                File imgUri = new File(GetImagePath.getPath(mContext, data.getData()));
                String authority = mContext.getPackageName() + ".provider";
                Uri dataUri = FileProvider.getUriForFile(mContext, authority, imgUri);
                startPhotoZoom(dataUri);
                break;
            case CROP_SMALL_PICTURE:
                if (data != null) {
                    Log.e("setImageToView", data + "");
                    setImageToView(data);
                }
                break;
        }


    }

    /**
     * 图像裁剪
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
            return;
        }

        Intent intent = new Intent("com.android.camera.action.CROP");
        //sdk>=24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

//            Uri outPutUri = Uri.fromFile(mCropFile);
            intent.setDataAndType(uri, "image/*");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.putExtra("noFaceDetection", false);//去除默认的人脸识别，否则和剪裁匡重叠
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        } else {
//            Uri outPutUri = Uri.fromFile(mCropFile);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String url = GetImagePath.getPath(mContext, uri);//这个方法是处理4.4以上图片返回的Uri对象不同的处理方法
                intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
            } else {
                intent.setDataAndType(uri, "image/*");
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// 图片格式
        startActivityForResult(intent, CROP_SMALL_PICTURE);//这里就将裁剪后的图片的Uri返回了
    }


    /**
     * 裁剪之后的图片
     *
     * @param data
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                        Config.dip2px(mContext, 160), Config.dip2px(mContext, 160));
                lp.leftMargin = 15;
                lp.rightMargin = 15;
                lp.topMargin = 5;
                lp.bottomMargin = 5;

                ImageView view;

                if (updateIndex == -1) {
                    view = new ImageView(this);
                    int index = imgFlowLayout.getChildCount() - 1;
                    view.setTag(index + "");
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                view.setLayoutParams(params);
                    view.setImageBitmap(photo);
                    view.setScaleType(ImageView.ScaleType.FIT_XY);
                    imgFlowLayout.addView(view, index, lp);

                    String file = BitmapTools.bitmapToString(photo);
                    PostUserReplaceItem.ImgListBean imgListBean = new PostUserReplaceItem.ImgListBean();
                    imgListBean.setImg_url(file);
                    imgDatas.add(imgListBean);
                } else {
                    view = (ImageView) imgFlowLayout.getChildAt(updateIndex);
                    int index = updateIndex;
                    view.setTag(index + "");
                    view.setImageBitmap(photo);
                    view.setScaleType(ImageView.ScaleType.FIT_XY);
                    String file = BitmapTools.bitmapToString(photo);
                    PostUserReplaceItem.ImgListBean imgListBean = new PostUserReplaceItem.ImgListBean();
                    imgListBean.setImg_url(file);
                    imgDatas.set(index, imgListBean);
                }

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!v.getTag().toString().equals("add")) {
                            updateIndex = Integer.valueOf(v.getTag().toString());
                            showPhotoDialog();
                        }
                    }
                });
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (!v.getTag().toString().equals("add")) {
                            initDeleteDialog(v.getTag().toString());
                        }
                        return false;
                    }
                });
            }
        }
    }

    private void initDeleteDialog(String s) {
        final int pos = Integer.valueOf(s);
        new AlertDialog.Builder(mContext)
                .setMessage("是否要删除此张图片？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        imgFlowLayout.removeViewAt(pos);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 根据商品名称匹配商品库中的商品
     */
    private void initGlibrary() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("goods_title", tvCommodityName.getText().toString().trim());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetGlibrary(map, new MyCallBack<GetLibraryObj>(mContext) {
            @Override
            public void onSuccessful(GetLibraryObj response) {
                if (response.getErrCode() == 0) {
                    mTatching = response.getResponse().getIs_matching();
//                    除了价格/数量，货源地/求购地，交货时间和货源设置为优势货源按钮能修改，其他的显示但不能修改
                    tvCommodityName.setText(response.getResponse().getTitle());
                    tvCommodityName.setEnabled(false);
                    tvCommodityCode.setText(response.getResponse().getBar_code());
                    tvCommodityCode.setEnabled(false);
                    tvBrandName.setText(response.getResponse().getBrand());
                    tvBrandName.setEnabled(false);
                    tvGoodsCategory1.setText(response.getResponse().getParent());
                    tvGoodsCategory1.setEnabled(false);
                    tvGoodsCategory2.setText(response.getResponse().getCategory());
                    tvPlaceOfOrigin.setText(response.getResponse().getOrigin_place());
                    tvPlaceOfOrigin.setEnabled(false);
                    tvShopNmabrief.setText(response.getResponse().getZhaiyao());
                    tvShopNmabrief.setEnabled(false);
                    tvStockNum.setText(response.getResponse().getStock());
                    tvGoodsSources.setText(response.getResponse().getSource());
                    tvDeliveryTime.setText(response.getResponse().getDelivery_time());
                    mCateId = response.getResponse().getCategory_id();
                    mParentId = response.getResponse().getParent_id();

                    mPlaceOfOriginId = response.getResponse().getOrigin_place();
                    mGoodsSourcesId = response.getResponse().getSource();

                    /**
                     * 价格
                     */
                    layout01.removeAllViews();
                    viewTests.clear();
                    List<GetLibraryObj.ResponseBean.DypricelistBean> pricelistBean = response.getResponse().getDypricelist();
                    mPricesList.addAll(pricelistBean);
                    /**
                     * 图片
                     */
                    mimgList.clear();
                    List<GetLibraryObj.ResponseBean.DyimglistBean> imglistBean = response.getResponse().getDyimglist();
                    mimgList.addAll(imglistBean);
                    for (int i = 0; i < mimgList.size(); i++) {

                        if (mimgList.size() > 0) {
                            gridlayout.setVisibility(View.VISIBLE);
                            imgFlowLayout.setVisibility(View.GONE);
                            ImageView iv = new ImageView(mContext);
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
                            iv.setPadding(0, 0, 0, 7);
                            Glide.with(mContext).load(mimgList.get(i).getImg_url()).error(R.mipmap.ic_launcher).into(iv);
                            GridLayout.LayoutParams params = new GridLayout.LayoutParams(new LinearLayout.LayoutParams(sw, sw));
                            params.rightMargin = OtherUtils.dip2px(mContext, 10);
                            gridlayout.addView(iv, params);

                            PostUserReplaceItem.ImgListBean imgListBean = new PostUserReplaceItem.ImgListBean();
                            imgListBean.setImg_url(mimgList.get(i).getImg_url());
                            imgDatas.add(imgListBean);
                            Log.e("-------------", imgDatas.size() + "");

                        } else {
                            gridlayout.setVisibility(View.GONE);
                            imgFlowLayout.setVisibility(View.VISIBLE);
                        }

                    }

                    priceDatas.clear();
                    for (int i = 0; i < mPricesList.size(); i++) {
                        layoutInflater = LayoutInflater.from(getApplicationContext());
                        views = layoutInflater.inflate(R.layout.activity_index_gallery_add, null);
                        layout01.addView(views);
                        getViewInstance(views);
                        tvQiDingPrice.setText(mPricesList.get(i).getPrice());
                        tvQiDingNum.setText(mPricesList.get(i).getQuantity());
                    }
                } else if (response.getErrCode() == 2) {
                    Log.e("000000mTatching", response.getResponse().getIs_matching());
                    mTatching = response.getResponse().getIs_matching();
                } else if (response.getErrCode() == 3) {
                    GetIsgoods();
                }
            }
        });
    }


    /**
     * 根据商品名称匹配商品库中的商品
     */
    private void GetIsgoods() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("goods_title", tvCommodityName.getText().toString().trim());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetGlibrary(map, new MyCallBack<GetLibraryObj>(mContext) {
            @Override
            public void onSuccessful(GetLibraryObj response) {
                if (response.getErrCode() == 0) {
                    mTatching = response.getResponse().getIs_matching();
//                    除了价格/数量，货源地/求购地，交货时间和货源设置为优势货源按钮能修改，其他的显示但不能修改
                    tvCommodityName.setText(response.getResponse().getTitle());
                    tvCommodityName.setEnabled(false);
                    tvCommodityCode.setText(response.getResponse().getBar_code());
                    tvCommodityCode.setEnabled(false);
                    tvBrandName.setText(response.getResponse().getBrand());
                    tvBrandName.setEnabled(false);
                    tvGoodsCategory1.setText(response.getResponse().getParent());
                    tvGoodsCategory1.setEnabled(false);
                    tvGoodsCategory2.setText(response.getResponse().getCategory());
                    tvPlaceOfOrigin.setText(response.getResponse().getOrigin_place());
                    tvPlaceOfOrigin.setEnabled(false);
                    tvShopNmabrief.setText(response.getResponse().getZhaiyao());
                    tvShopNmabrief.setEnabled(false);
                    tvStockNum.setText(response.getResponse().getStock());
                    tvGoodsSources.setText(response.getResponse().getSource());
                    tvDeliveryTime.setText(response.getResponse().getDelivery_time());
                    mCateId = response.getResponse().getCategory_id();
                    mParentId = response.getResponse().getParent_id();

                    mPlaceOfOriginId = response.getResponse().getOrigin_place();
                    mGoodsSourcesId = response.getResponse().getSource();

                    /**
                     * 价格
                     */
                    layout01.removeAllViews();
                    viewTests.clear();
                    List<GetLibraryObj.ResponseBean.DypricelistBean> pricelistBean = response.getResponse().getDypricelist();
                    mPricesList.addAll(pricelistBean);
                    /**
                     * 图片
                     */
                    mimgList.clear();
                    List<GetLibraryObj.ResponseBean.DyimglistBean> imglistBean = response.getResponse().getDyimglist();
                    mimgList.addAll(imglistBean);
                    for (int i = 0; i < mimgList.size(); i++) {

                        if (mimgList.size() > 0) {
                            gridlayout.setVisibility(View.VISIBLE);
                            imgFlowLayout.setVisibility(View.GONE);
                            ImageView iv = new ImageView(mContext);
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
                            iv.setPadding(0, 0, 0, 7);
                            Glide.with(mContext).load(mimgList.get(i).getImg_url()).error(R.mipmap.ic_launcher).into(iv);
                            GridLayout.LayoutParams params = new GridLayout.LayoutParams(new LinearLayout.LayoutParams(sw, sw));
                            params.rightMargin = OtherUtils.dip2px(mContext, 10);
                            gridlayout.addView(iv, params);

                            PostUserReplaceItem.ImgListBean imgListBean = new PostUserReplaceItem.ImgListBean();
                            imgListBean.setImg_url(mimgList.get(i).getImg_url());
                            imgDatas.add(imgListBean);
                            Log.e("-------------", imgDatas.size() + "");

                        } else {
                            gridlayout.setVisibility(View.GONE);
                            imgFlowLayout.setVisibility(View.VISIBLE);
                        }

                    }

                    priceDatas.clear();
                    for (int i = 0; i < mPricesList.size(); i++) {
                        layoutInflater = LayoutInflater.from(getApplicationContext());
                        views = layoutInflater.inflate(R.layout.activity_index_gallery_add, null);
                        layout01.addView(views);
                        getViewInstance(views);
                        tvQiDingPrice.setText(mPricesList.get(i).getPrice());
                        tvQiDingNum.setText(mPricesList.get(i).getQuantity());
                    }
                } else if (response.getErrCode() == 2) {
                    Log.e("000000mTatching", response.getResponse().getIs_matching());
                    mTatching = response.getResponse().getIs_matching();
                }
            }
        });
    }
}
