package com.haitaoit.pinpai.module.personPage.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.homePage.network.response.GetIdentObj;
import com.haitaoit.pinpai.module.personPage.activity.AccountSettingCompanyActivity;
import com.haitaoit.pinpai.module.personPage.activity.AccountSettingPersonActivity;
import com.haitaoit.pinpai.module.personPage.activity.AnthErrorActivity;
import com.haitaoit.pinpai.module.personPage.activity.CompanyAuthActivity;
import com.haitaoit.pinpai.module.personPage.activity.MyAttentionActivity;
import com.haitaoit.pinpai.module.personPage.activity.MyCollectionActivity;
import com.haitaoit.pinpai.module.personPage.activity.MyMessageActivity;
import com.haitaoit.pinpai.module.personPage.activity.MySettingActivity;
import com.haitaoit.pinpai.module.personPage.activity.MySupplyBuyActivity;
import com.haitaoit.pinpai.module.personPage.activity.MySupplyGoodsActivity;
import com.haitaoit.pinpai.module.personPage.activity.OneButtonBuyActivity;
import com.haitaoit.pinpai.module.personPage.activity.OneButtonGoodsActivity;
import com.haitaoit.pinpai.module.personPage.activity.PersonAuthActivity;
import com.haitaoit.pinpai.module.personPage.activity.SupplierActivity;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.response.GetPersonUserObj;
import com.haitaoit.pinpai.module.releasePage.activity.ReleaseErrorActivity;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 用户消息
 * Created by Administrator on 2017/10/26.
 */

public class PersonPageFragment extends BaseFragment {
    @BindView(R.id.iv_person_image)
    CircleImageView ivPersonImage;

    @BindView(R.id.tv_person_name)
    TextView tvPersonName;

    @BindView(R.id.tv_person_collection_num)
    TextView tvPersonCollectionNum;

    @BindView(R.id.ll_person_collection)
    LinearLayout llPersonCollection;

    @BindView(R.id.tv_person_follow_num)
    TextView tvPersonFollowNum;

    @BindView(R.id.ll_person_follow)
    LinearLayout llPersonFollow;

    @BindView(R.id.ll_person_date)
    LinearLayout llPersonDate;

    @BindView(R.id.ll_person_certification)
    LinearLayout llPersonCertification;

    @BindView(R.id.tv_person_goods_num)
    TextView tvPersonGoodsNum;

    @BindView(R.id.ll_person_goods)
    LinearLayout llPersonGoods;

    @BindView(R.id.tv_person_buy_num)
    TextView tvPersonBuyNum;

    @BindView(R.id.ll_person_buy)
    LinearLayout llPersonBuy;

    @BindView(R.id.tv_red_num)
    MyTextView tvRedNum;

    @BindView(R.id.ll_person_message)
    LinearLayout llPersonMessage;

    @BindView(R.id.ll_person_setting)
    LinearLayout llPersonSetting;

    @BindView(R.id.ll_person_share_goods)
    LinearLayout llPersonShareGoods;

    @BindView(R.id.ll_person_share_bug)
    LinearLayout llPersonShareBug;

    @BindView(R.id.nsv_home)
    NestedScrollView nsvHome;
    @BindView(R.id.tv_YijingRenzheng)
    TextView tvYijingRenzheng;
    Unbinder unbinder;

    private String mIsIdent;
    GetPersonUserObj mGetPersonUserObj;


    @Override
    protected int getContentView() {
        return R.layout.person_page_fragment;
    }

    @Override
    protected void initView() {
        tvYijingRenzheng.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvYijingRenzheng.getPaint().setColor(getResources().getColor(R.color.red_ff_77));

    }

    @Override
    protected void initData() {
        initUserInfor();
        initIdent();
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
                    if (response.getResponse().getIs_certified().equals("2")) {
                        tvYijingRenzheng.setVisibility(View.VISIBLE);
                    } else {
                        tvYijingRenzheng.setVisibility(View.GONE);
                    }
                } else {
                    showToastS("获取个人信息错误");
                }
            }
        });
    }

    private void initUserInfor() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("RegistrationID", JPushInterface.getRegistrationID(getActivity()));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetUserInfo(map, new MyCallBack<GetPersonUserObj>(mContext) {
            @Override
            public void onSuccessful(GetPersonUserObj response) {
                if (response.getErrCode() == 0) {
                    mGetPersonUserObj = response;
//                    Glide.with(mContext).load(response.getResponse().getAvatar()).placeholder(R.mipmap.img32).error(R.mipmap.img32).into(ivPersonImage);

                    Glide.with(mContext).load(response.getResponse().getAvatar()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.img32).into(ivPersonImage);
                    tvPersonName.setText(response.getResponse().getNick_name());
                    tvPersonCollectionNum.setText(response.getResponse().getCollect_num());
                    tvPersonFollowNum.setText(response.getResponse().getFllow_num());
                    tvRedNum.setText(response.getResponse().getMessage_num());
                    tvPersonGoodsNum.setText(response.getResponse().getGoods_num());
                    tvPersonBuyNum.setText(response.getResponse().getNeed_num());
                } else {
                    showToastS("获取个人信息错误");
                }
            }


        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initUserInfor();
        initIdent();
    }

    @OnClick({R.id.iv_person_image, R.id.ll_person_collection, R.id.ll_person_follow, R.id.ll_person_date, R.id.ll_person_certification, R.id.ll_person_goods, R.id.ll_person_buy, R.id.ll_person_message, R.id.ll_person_setting, R.id.ll_person_share_goods, R.id.ll_person_share_bug})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_person_image:
                /**
                 * 2是公司 1是个人
                 */
                if ("个人".equals(PreferenceUtils.getPrefString(mContext, Config.user_type, ""))) {
                    RxActivityUtils.skipActivity(mContext, AccountSettingPersonActivity.class);
                } else {
                    RxActivityUtils.skipActivity(mContext, AccountSettingCompanyActivity.class);
                }
                break;
            case R.id.ll_person_collection:
                RxActivityUtils.skipActivity(mContext, MyCollectionActivity.class);
                break;
            case R.id.ll_person_follow:
                RxActivityUtils.skipActivity(mContext, MyAttentionActivity.class);
                break;
            case R.id.ll_person_date:
                /**
                 * 2是公司 1是个人
                 */
                if ("个人".equals(PreferenceUtils.getPrefString(mContext, Config.user_type, ""))) {
                    RxActivityUtils.skipActivity(mContext, AccountSettingPersonActivity.class);
                } else {
                    RxActivityUtils.skipActivity(mContext, AccountSettingCompanyActivity.class);
                }
                break;
            case R.id.ll_person_certification:
                if ("4".equals(mIsIdent)) {
                    Bundle mBundle = new Bundle();
                    mBundle.putString("mUserId", mGetPersonUserObj.getResponse().getUser_id());
                    mBundle.putString("mUserType", mGetPersonUserObj.getResponse().getType());
                    mBundle.putString("mUserEmail", mGetPersonUserObj.getResponse().getEmail());
                    RxActivityUtils.skipActivity(mContext, AnthErrorActivity.class);
                } else if ("2".equals(mIsIdent)) {
                    showToastS("已经认证过了不能再点击了");
                } else if ("5".equals(mIsIdent)) {
                    showToastS("认证审核中");
                } else {
                    if ("个人".equals(PreferenceUtils.getPrefString(mContext, Config.user_type, ""))) {
                        RxActivityUtils.skipActivity(mContext, PersonAuthActivity.class);
                    } else {
                        RxActivityUtils.skipActivity(mContext, CompanyAuthActivity.class);
                    }
                }

                break;
            case R.id.ll_person_goods:
                Bundle mBundle = new Bundle();
                mBundle.putString("mType", "1");
                RxActivityUtils.skipActivity(mContext, MySupplyGoodsActivity.class, mBundle);
                break;
            case R.id.ll_person_buy:
                Bundle mBundlebuy = new Bundle();
                mBundlebuy.putString("mType", "1");
                RxActivityUtils.skipActivity(mContext, MySupplyBuyActivity.class, mBundlebuy);
                break;
            case R.id.ll_person_message:
                RxActivityUtils.skipActivity(mContext, MyMessageActivity.class);
                break;
            case R.id.ll_person_setting:
                RxActivityUtils.skipActivity(mContext, MySettingActivity.class);
                break;
            case R.id.ll_person_share_goods:
                RxActivityUtils.skipActivity(mContext, OneButtonGoodsActivity.class);
                break;
            case R.id.ll_person_share_bug:
                RxActivityUtils.skipActivity(mContext, OneButtonBuyActivity.class);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
}
