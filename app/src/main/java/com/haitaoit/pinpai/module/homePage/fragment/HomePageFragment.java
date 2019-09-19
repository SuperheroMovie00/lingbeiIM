package com.haitaoit.pinpai.module.homePage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.callback.FragmentEvent;
import com.haitaoit.pinpai.module.findPage.activity.SupplyGoodsDetailActivity;
import com.haitaoit.pinpai.module.homePage.activity.BeatDataActivity;
import com.haitaoit.pinpai.module.homePage.activity.HomeSearchActivity;
import com.haitaoit.pinpai.module.homePage.bean.TabEntity;
import com.haitaoit.pinpai.module.homePage.network.ApiRequest;
import com.haitaoit.pinpai.module.homePage.network.response.GetBannerObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.RxGlideLoader;
import com.haitaoit.pinpai.view.MyImageView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/26.
 */

public class HomePageFragment extends BaseFragment {


    @BindView(R.id.Tv_Location)
    TextView TvLocation;
    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.search_et)
    TextView searchEt;
    @BindView(R.id.iv_SearchContent)
    ImageView ivSearchContent;
    @BindView(R.id.body_linear)
    LinearLayout bodyLinear;
    @BindView(R.id.bn_home)
    Banner bnBannerTop;
    @BindView(R.id.tv_home_edu1)
    TextView tvHomeEdu1;
    @BindView(R.id.tv_home_edu2)
    TextView tvHomeEdu2;
    @BindView(R.id.tv_home_edu3)
    TextView tvHomeEdu3;
    @BindView(R.id.tv_home_edu4)
    TextView tvHomeEdu4;
    @BindView(R.id.tv_home_edu5)
    TextView tvHomeEdu5;
    @BindView(R.id.lin_home_pin1)
    LinearLayout linHomePin1;
    @BindView(R.id.lin_home_pin2)
    LinearLayout linHomePin2;
    @BindView(R.id.lin_home_pin3)
    LinearLayout linHomePin3;
    @BindView(R.id.lin_home_pin4)
    LinearLayout linHomePin4;
    @BindView(R.id.iv_hot_img)
    MyImageView ivHotImg;
    @BindView(R.id.tv_sum_avg_weight)
    MyTextView tvSumAvgWeight;
    @BindView(R.id.ll_addRemark)
    MyLinearLayout llAddRemark;
    @BindView(R.id.tablayout)
    CommonTabLayout tablayout;
    @BindView(R.id.pager_tia)
    ViewPager pagerTia;
    @BindView(R.id.tablayout_second)
    CommonTabLayout tablayoutSecond;
    @BindView(R.id.pager_tia_second)
    ViewPager pagerTiaSecond;
    @BindView(R.id.pl_load)
    LinearLayout plLoad;
    @BindView(R.id.tv_RemarkName)
    TextView tvRemarkName;
    private String[] titles = {"推荐货源", "推荐求购"};
    private ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    Unbinder mUnbinder;

    private String[] titles2 = {"推荐供应商", "推荐求购商"};
    private ArrayList<CustomTabEntity> tabEntities2 = new ArrayList<>();
    private List<Fragment> fragments2 = new ArrayList<>();

    private String mGoodID;


    @Override
    protected int getContentView() {
        return R.layout.home_page_fragment;
    }

    @Override
    protected void initView() {
        initTabLayout();
        initViewPager();


    }


    @Override
    protected void initData() {
        initBannerTop();
        initHotTop();
    }


    private void initBannerTop() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "index_roll");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetBanner(map, new MyCallBack<GetBannerObj>(mContext) {
            @Override
            public void onSuccessful(GetBannerObj response) {
                if (response.getErrCode() == 0) {
                    List<GetBannerObj.ResponseBean.DyadroollistBean> result = response.getResponse().getDyadroollist();
                    setBanner(result);
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    private void initHotTop() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "new_goods");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetBanner(map, new MyCallBack<GetBannerObj>(mContext) {
            @Override
            public void onSuccessful(GetBannerObj response) {

                if (response.getErrCode() == 0) {
                    List<GetBannerObj.ResponseBean.DyadroollistBean> result = response.getResponse().getDyadroollist();
                    for (int j = 0; j < result.size(); j++) {
                        Glide.with(mContext).load(result.get(j).getImg_url()).error(R.mipmap.ic_launcher).into(ivHotImg);
                        tvRemarkName.setText(result.get(j).getTitle());
                        tvSumAvgWeight.setText(result.get(j).getRemarks());
                        mGoodID = result.get(j).getGood_id();

                    }
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    private void setBanner(final List<GetBannerObj.ResponseBean.DyadroollistBean> result) {
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

        bnBannerTop.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

                Bundle bundle = new Bundle();
                bundle.putString("supplyId", result.get(position).getGood_id());
                RxActivityUtils.skipActivity(getActivity(), SupplyGoodsDetailActivity.class, bundle);


            }
        });
    }

    private void initViewPager() {
        /**
         * 推荐货源
         */
        fragments.add(new RecommendSourceFragment());
        /**
         * 推荐求购
         */
        fragments.add(new RecommendPurchaseFragment());
        /**
         * 推荐供应商
         */
        fragments2.add(new RecommendBottom1Fragment());
        /**
         * 推荐求购商
         */
        fragments2.add(new RecommendBottom2Fragment());
        pagerTia.setAdapter(new EverybodyPagerAdapter(getChildFragmentManager()));
        pagerTia.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tablayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pagerTiaSecond.setAdapter(new EverybodyPagerAdapter2(getChildFragmentManager()));
        pagerTiaSecond.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tablayoutSecond.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabLayout() {
        for (int i = 0; i < titles.length; i++) {
            tabEntities.add(new TabEntity(titles[i], 0, 0));
        }
        tablayout.setTabData(tabEntities);
        tablayout.setTextsize(14);
        tablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                pagerTia.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        for (int i = 0; i < titles2.length; i++) {
            tabEntities2.add(new TabEntity(titles2[i], 0, 0));
        }
        tablayoutSecond.setTabData(tabEntities2);
        tablayoutSecond.setTextsize(14);
        tablayoutSecond.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                pagerTiaSecond.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.Tv_Location, R.id.search_et, R.id.iv_SearchContent, R.id.tv_home_edu1, R.id.tv_home_edu2, R.id.tv_home_edu3, R.id.tv_home_edu4, R.id.tv_home_edu5, R.id.lin_home_pin1, R.id.lin_home_pin2, R.id.lin_home_pin3, R.id.lin_home_pin4, R.id.iv_hot_img, R.id.ll_addRemark})
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.Tv_Location:
                break;
            case R.id.search_et:
                RxActivityUtils.skipActivity(mContext, HomeSearchActivity.class);
                break;
            case R.id.iv_SearchContent:
                break;
            case R.id.tv_home_edu1:
                EventBus.getDefault().post(new FragmentEvent(0, "1"));
                break;
            case R.id.tv_home_edu2:
                EventBus.getDefault().post(new FragmentEvent(0, "2"));
                break;
            case R.id.tv_home_edu3:
//                bundle.putString("HomeName", "浏览排名");
//                RxActivityUtils.skipActivity(mContext, BeatDataActivity.class, bundle);
                break;
            case R.id.tv_home_edu4:
//                bundle.putString("HomeName", "关注排名");
//                RxActivityUtils.skipActivity(mContext, BeatDataActivity.class, bundle);
                break;
            case R.id.tv_home_edu5:
//                bundle.putString("HomeName", "1");
//                RxActivityUtils.skipActivity(mContext, BeatDataActivity.class, bundle);
                break;
            case R.id.lin_home_pin1:
                bundle.putString("HomeName", "交易排名");
                RxActivityUtils.skipActivity(mContext, BeatDataActivity.class, bundle);
                break;
            case R.id.lin_home_pin2:
                bundle.putString("HomeName", "发布排名");
                RxActivityUtils.skipActivity(mContext, BeatDataActivity.class, bundle);
                break;
            case R.id.lin_home_pin3:
                bundle.putString("HomeName", "浏览排名");
                RxActivityUtils.skipActivity(mContext, BeatDataActivity.class, bundle);
                break;
            case R.id.lin_home_pin4:
                bundle.putString("HomeName", "关注排名");
                RxActivityUtils.skipActivity(mContext, BeatDataActivity.class, bundle);
                break;
            case R.id.iv_hot_img:
                bundle.putString("supplyId", mGoodID);
                RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);
                break;
            case R.id.ll_addRemark:
                break;
        }
    }

    public class EverybodyPagerAdapter extends FragmentPagerAdapter {

        public EverybodyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    public class EverybodyPagerAdapter2 extends FragmentPagerAdapter {

        public EverybodyPagerAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments2.get(position);
        }

        @Override
        public int getCount() {
            return fragments2.size();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


}

