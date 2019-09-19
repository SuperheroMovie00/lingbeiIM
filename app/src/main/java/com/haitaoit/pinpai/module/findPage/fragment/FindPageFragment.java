package com.haitaoit.pinpai.module.findPage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseFragment;
import com.haitaoit.pinpai.callback.FindEvent;
import com.haitaoit.pinpai.callback.FragmentEvent;
import com.haitaoit.pinpai.module.findPage.activity.SearchDateActivcty;
import com.haitaoit.pinpai.module.findPage.activity.SearchQiuActivcty;
import com.haitaoit.pinpai.module.homePage.bean.TabEntity;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/26.
 */

public class FindPageFragment extends BaseFragment {
    @BindView(R.id.iv_Left_onclick)
    ImageView ivLeftOnclick;
    @BindView(R.id.tv_find_search)
    MyEditText tvFindSearch;
    @BindView(R.id.tv_search)
    MyTextView tvSearch;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.tablayout_find)
    CommonTabLayout tablayoutFind;
    @BindView(R.id.pager_tia_second)
    ViewPager pagerTiaSecond;
    @BindView(R.id.pl_load)
    LinearLayout plLoad;
    Unbinder unbinder;

    private int mPosition=0;

    public static FindPageFragment newInstance(String searchName) {
        FindPageFragment newFragment = new FindPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("searchName", searchName);
        newFragment.setArguments(bundle);
        return newFragment;

    }


    private String[] titles = {"货源", "求购"};
    private ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.find_page_fragment;
    }

    @Override
    protected void initView() {
        initTabLayout();
        initViewPager();
        initListener();

        if (RxDataUtils.isNullString(getArguments().getString("searchName"))) {
        } else {
            if (getArguments().getString("searchName").equals("1")) {
                tablayoutFind.setCurrentTab(0);
                pagerTiaSecond.setCurrentItem(0);
            } else {
                tablayoutFind.setCurrentTab(1);
                pagerTiaSecond.setCurrentItem(1);
            }
        }

    }

    private void initListener() {

        tvFindSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (tvFindSearch.getText().toString().trim().equals("")) {
                        showMsg("你搜索的内容为空");
                    } else {
                        if (mPosition==0){
                            Bundle mBundle = new Bundle();
                            mBundle.putString("findName", tvFindSearch.getText().toString().trim());
                            RxActivityUtils.skipActivity(mContext, SearchDateActivcty.class, mBundle);
                        }else {
                            Bundle mBundle = new Bundle();
                            mBundle.putString("findName", tvFindSearch.getText().toString().trim());
                            RxActivityUtils.skipActivity(mContext, SearchQiuActivcty.class, mBundle);
                        }

                    }

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }


    private void initViewPager() {
        fragments.add(new SupplyGoodsFragment().newInstance("0", tvFindSearch.getText().toString().trim()));
        fragments.add(new QiuGouFragment().newInstance("0", tvFindSearch.getText().toString().trim()));
        pagerTiaSecond.setAdapter(new EverybodyPagerAdapter(getChildFragmentManager()));
        pagerTiaSecond.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tablayoutFind.setCurrentTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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


    private void initTabLayout() {
        for (int i = 0; i < titles.length; i++) {
            tabEntities.add(new TabEntity(titles[i], 0, 0));
        }
        tablayoutFind.setTabData(tabEntities);
        tablayoutFind.setTextsize(14);
        tablayoutFind.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(final int position) {
                tablayoutFind.setCurrentTab(position);
                pagerTiaSecond.setCurrentItem(position);
                mPosition =position;
                Log.e("===========",position+"");

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
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_Left_onclick, R.id.tv_search})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_Left_onclick:
                break;
            case R.id.tv_search:

                if (TextUtils.isEmpty(tvFindSearch.getText().toString().trim())) {
                    showToastS("搜索内容为空");
                    return;
                }
                if (mPosition==0){
                    Bundle mBundle = new Bundle();
                    mBundle.putString("findName", tvFindSearch.getText().toString().trim());
                    RxActivityUtils.skipActivity(mContext, SearchDateActivcty.class, mBundle);
                }else {
                    Bundle mBundle = new Bundle();
                    mBundle.putString("findName", tvFindSearch.getText().toString().trim());
                    RxActivityUtils.skipActivity(mContext, SearchQiuActivcty.class, mBundle);
                }
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FindEvent event) {
        Log.e("eventevent2222", event.id);
        if (event.id.equals("1")) {
            tablayoutFind.setCurrentTab(0);
            pagerTiaSecond.setCurrentItem(0);
        } else {
            tablayoutFind.setCurrentTab(1);
            pagerTiaSecond.setCurrentItem(1);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
