package com.haitaoit.pinpai.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.androidtools.ClickUtils;
import com.github.baseclass.fragment.IBaseFragment;
import com.github.baseclass.rx.RxBus;
import com.haitaoit.pinpai.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by Administrator on 2017/7/13.
 */

public abstract class BaseFragment extends IBaseFragment implements View.OnClickListener {
    protected int pageNum = 1;
    protected int pageSize = 20;

    private boolean isFirstLoadData = true;
    private boolean isPrepared;
    protected PtrClassicFrameLayout pcfl;

    /************************************************/
    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void onViewClick(View v);

    protected void initRxBus() {
    }

    ;
    protected Unbinder mUnBind;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        mUnBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (null != view.findViewById(R.id.pcfl_refresh)) {
            pcfl = (PtrClassicFrameLayout) view.findViewById(R.id.pcfl_refresh);
            pcfl.setLastUpdateTimeRelateObject(this);
        }
        if (null != view.findViewById(R.id.pl_load)) {
        }
        initView();
        initRxBus();
        isPrepared = true;
        setUserVisibleHint(true);
    }

//    public void showProgress(ProgressLayout.OnAgainInter inter) {
//        if (pl_load != null) {
//            pl_load.setInter(inter);
//            pl_load.showProgress();
//        }
//    }
//
//    public void showContent() {
//        if (pl_load != null) {
//            pl_load.showContent();
//        }
//    }
//
//    public void showErrorText() {
//        if (pl_load != null) {
//            pl_load.showErrorText();
//        }
//    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isFirstLoadData && isPrepared && getUserVisibleHint() && isVisibleToUser) {
            initData();
            isFirstLoadData = false;
        }
    }

    protected String getSStr(View view) {
        if (view instanceof TextView) {
            return ((TextView) view).getText().toString();
        } else if (view instanceof EditText) {
            return ((EditText) view).getText().toString();
        } else {
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        if (!ClickUtils.isFastClick(v)) {
            onViewClick(v);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        mUnBind.unbind();
        RxBus.getInstance().removeAllStickyEvents();
    }
}
