package com.haitaoit.pinpai.module.messagePage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.github.customview.MyRadioButton;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.add.activity.fragment.ConversationListFragment;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.module.homePage.bean.TabEntity;
import com.haitaoit.pinpai.module.personPage.fragment.PlatFormMessageFragment;
import com.vondear.rxtools.view.RxTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/26.
 */

public class MessagePageFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    CommonTabLayout tablayout;
    @BindView(R.id.pager)
    ViewPager pager;
    Unbinder unbinder;
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.cb_replace_sort)
    MyRadioButton cbReplaceSort;
    @BindView(R.id.cb_replace_hot)
    MyRadioButton cbReplaceHot;
    Unbinder unbinder1;

    private String[] titles = {"排行", "社区"};
    private ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.message_page_fragement;
    }

    @Override
    protected void initView() {
        initTabLayout();
        initViewPager();
    }

    private void initViewPager() {

        fragments.add(new ConversationListFragment());
        fragments.add(new PlatFormMessageFragment());

        pager.setAdapter(new EverybodyPagerAdapter(getChildFragmentManager()));

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                pager.setCurrentItem(position);
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
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.rx_title, R.id.cb_replace_sort, R.id.cb_replace_hot})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.cb_replace_sort:
                rxTitle.setTitle("用户消息");
                tablayout.setCurrentTab(0);
                pager.setCurrentItem(0);
                break;
            case R.id.cb_replace_hot:
                rxTitle.setTitle("平台消息");
                tablayout.setCurrentTab(1);
                pager.setCurrentItem(1);
                break;
        }
    }

//
//    @BindView(R.id.rx_title)
//    RxTitle rxTitle;
//    @BindView(R.id.cb_replace_sort)
//    MyRadioButton cbReplaceSort;
//    @BindView(R.id.cb_replace_hot)
//    MyRadioButton cbReplaceHot;
//    @BindView(R.id.rv_collec_view)
//    RecyclerView rvCollecView;
//    @BindView(R.id.rv_collec_view2)
//    RecyclerView rvCollecView2;
//    Unbinder unbinder;
//    @BindView(R.id.pcfl_refresh1)
//    PtrClassicFrameLayout pcflRefresh1;
//    @BindView(R.id.pcfl_refresh2)
//    PtrClassicFrameLayout pcflRefresh2;
//    private List<GetMessagePinObj.ResponseBean> mDateList = new ArrayList();
//    private List<GetMessageUserObj.ResponseBean> mDateUserList = new ArrayList();
//    private UserMessageAdapter mUserMessageAdapter;
//    private PlatFormMessageAdapter mPlatFormMessageAdapter;
//    private int currentPage = 1;
//    private int currentPage2= 1;
//
//    private String mMessageType = "1";
//
//    @Override
//    protected int getContentView() {
//        return R.layout.message_page_fragement;
//    }
//
//    @Override
//    protected void initView() {
//        rxTitle.setTitle("用户消息");
//
//        if (mMessageType.equals("1")) {
//            pcflRefresh1.setVisibility(View.VISIBLE);
//            pcflRefresh2.setVisibility(View.GONE);
//        } else {
//            pcflRefresh1.setVisibility(View.GONE);
//            pcflRefresh2.setVisibility(View.VISIBLE);
//        }
//        rvCollecView.setLayoutManager(new LinearLayoutManager(mContext));
//        rvCollecView.setAdapter(mUserMessageAdapter = new UserMessageAdapter(mContext, mDateUserList));
//        rvCollecView.setNestedScrollingEnabled(false);
//
//        rvCollecView2.setLayoutManager(new LinearLayoutManager(mContext));
//        rvCollecView2.setAdapter(mPlatFormMessageAdapter = new PlatFormMessageAdapter(mContext, mDateList));
//        rvCollecView2.setNestedScrollingEnabled(false);
//
//        mUserMessageAdapter.setOnItemClickListener(new UserMessageAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("supplyId", mDateUserList.get(position).getId());
//                RxActivityUtils.skipActivity(mContext, MessageDetailActivity.class, bundle);
//
//            }
//
//        });
//        mPlatFormMessageAdapter.setOnItemClickListener(new PlatFormMessageAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("supplyId", mDateList.get(position).getGoods_id());
//                bundle.putString("msge_id", mDateList.get(position).getId());
//                if (mDateList.get(position).getType().equals("1")) {
//                    RxActivityUtils.skipActivity(mContext, SupplyGoodsDetailActivity.class, bundle);
//                } else if (mDateList.get(position).getType().equals("2")) {
//                    RxActivityUtils.skipActivity(mContext, PurchaseGoodsDetailActivity.class, bundle);
//                } else {
//                    RxActivityUtils.skipActivity(mContext, MessageDetailActivity.class, bundle);
//                }
//            }
//
//        });
//
//
//
//        pcflRefresh1.setPtrHandler(new PtrDefaultHandler2() {
//            @Override
//            public void onLoadMoreBegin(PtrFrameLayout frame) {
//                initCollList(false);
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                currentPage = 1;
//                initCollList(true);
//            }
//        });
//
//        pcflRefresh2.setPtrHandler(new PtrDefaultHandler2() {
//            @Override
//            public void onLoadMoreBegin(PtrFrameLayout frame) {
//                initCollList2(false);
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                currentPage2 = 1;
//                initCollList2(true);
//            }
//        });
//
//
//    }
//
//    private void initCollList(final boolean isRefresh) {
//        Map<String, String> map = new HashMap<String, String>();
//        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
//            map.put("user_id", "0");
//        } else {
//            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
//        }
//        map.put("type", "1");
//        map.put("page", currentPage + "");
//        map.put("pagesize", Config.pageSize);
//        map.put("sign", GetSign.getSign(map));
//
//        ApiRequest.GetMessageListUser(map, new MyCallBack<GetMessageUserObj>(mContext) {
//            @Override
//            public void onSuccessful(GetMessageUserObj response) {
//                if (response.getErrCode() == 0) {
//                    mDateList.clear();
//                    List<GetMessageUserObj.ResponseBean> result = response.getResponse();
//                    if (isRefresh) {
//                        mDateUserList.clear();
//                    }
//                    mDateUserList.addAll(result);
//                    mUserMessageAdapter.notifyDataSetChanged();
//                    currentPage++;
//                    pcflRefresh1.refreshComplete();
//                } else {
//                    if (isRefresh) {
//                        mDateUserList.clear();
//                        mUserMessageAdapter.notifyDataSetChanged();
//                    }
//
//                    pcflRefresh1.refreshComplete();
//                }
//            }
//        });
//    }
//
//
//    private void initCollList2(final boolean isRefresh) {
//        Map<String, String> map = new HashMap<String, String>();
//        if (RxDataUtils.isNullString(PreferenceUtils.getPrefString(mContext, Config.user_id, ""))) {
//            map.put("user_id", "0");
//        } else {
//            map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
//        }
//        map.put("type", "2");
//        map.put("page", currentPage2+ "");
//        map.put("pagesize", Config.pageSize);
//        map.put("sign", GetSign.getSign(map));
//
//        ApiRequest.GetMessageList(map, new MyCallBack<GetMessagePinObj>(mContext) {
//            @Override
//            public void onSuccessful(GetMessagePinObj response) {
//                if (response.getErrCode() == 0) {
//                    mDateList.clear();
//                    List<GetMessagePinObj.ResponseBean> result = response.getResponse();
//                    if (isRefresh) {
//                        mDateList.clear();
//                    }
//                    mDateList.addAll(result);
//                    mPlatFormMessageAdapter.notifyDataSetChanged();
//                    currentPage2++;
//                    pcflRefresh2.refreshComplete();
//                } else {
//                    if (isRefresh) {
//                        mDateList.clear();
//                        mPlatFormMessageAdapter.notifyDataSetChanged();
//                    }
//
//                    pcflRefresh2.refreshComplete();
//                }
//            }
//        });
//
//    }
//
//
//    @Override
//    protected void initData() {
//        initCollList(true);
//        initCollList2(true);
//    }
//
//
//    @OnClick({R.id.rx_title, R.id.cb_replace_sort, R.id.cb_replace_hot})
//    public void onViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.rx_title:
//                break;
//            case R.id.cb_replace_sort:
//                mMessageType = "1";
//                currentPage = 1;
//                rxTitle.setTitle("用户消息");
//                pcflRefresh1.setVisibility(View.VISIBLE);
//                pcflRefresh2.setVisibility(View.GONE);
//                rvCollecView.setVisibility(View.VISIBLE);
//                rvCollecView2.setVisibility(View.GONE);
//                initCollList(true);
//                break;
//            case R.id.cb_replace_hot:
//                rxTitle.setTitle("平台消息");
//                mMessageType = "2";
//                currentPage2 = 1;
//                pcflRefresh1.setVisibility(View.GONE);
//                pcflRefresh2.setVisibility(View.VISIBLE);
//                rvCollecView.setVisibility(View.GONE);
//                rvCollecView2.setVisibility(View.VISIBLE);
//                initCollList2(true);
//                break;
//        }
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        currentPage = 1;
//        currentPage2 = 1;
//        if (mMessageType.equals("1")) {
//            pcflRefresh1.setVisibility(View.VISIBLE);
//            pcflRefresh2.setVisibility(View.GONE);
//        } else {
//            pcflRefresh1.setVisibility(View.GONE);
//            pcflRefresh2.setVisibility(View.VISIBLE);
//        }
//        initCollList(true);
//        initCollList2(true);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
}
