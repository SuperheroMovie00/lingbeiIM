package com.haitaoit.pinpai.base;

import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.ClickUtils;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.StatusBarUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.activity.IBaseActivity;
import com.haitaoit.pinpai.R;

import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;


/**
 * Created by Administrator on 2017/6/1.
 */

public abstract class BaseActivity extends IBaseActivity implements View.OnClickListener {
    @Override
    protected void onResume() {
        super.onResume();
        // 页面埋点，需要使用Activity的引用，以便代码能够统计到具体页面名
//        StatService.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 页面结束埋点，需要使用Activity的引用，以便代码能够统计到具体页面名
//        StatService.onPause(this);
        //页面结束慢点，粗腰使用AC替比太阳的隐隐有  以便代码能统计到具体页面名你能拿到那你呢；首付款斯洛伐克感觉到了  楼上的房间时代峰峻离开房间都是法律框架凉快圣诞节福利减肥方法
    }

    /*************************************************/
    protected Toolbar toolbar;
    private boolean showNavigationIcon = true;
    private int navigationIcon = -1;
    protected int pageNum = 1;
    protected int pageSize = 20;
    private String appTitle, appRightTitle;
    private int appTitleColor, appRightTitleColor;
    private int appRightImg;
    private int titleBackgroud = R.color.white;
    private int statusBarBackgroud = R.color.white;
    protected TextView app_title, app_right_tv;
    protected ImageView app_right_iv;
    private View status_bar;
    protected PtrClassicFrameLayout pcfl;


    /****************************************************/
    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void onViewClick(View v);

    protected void initRxBus() {};

    protected void setAppTitle(String title) {
        appTitle = title;
        if (app_title != null) {
            app_title.setText(appTitle == null ? "" : appTitle);
        }
    }

    public void setAppRightTitle(String appRightTitle) {
        this.appRightTitle = appRightTitle;
    }

    public void setAppRightImg(int appRightImg) {
        this.appRightImg = appRightImg;
        if (app_right_iv != null && appRightImg != 0) {
            app_right_iv.setImageResource(appRightImg);

            app_right_tv.setVisibility(View.GONE);
            app_right_iv.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleBackgroud(int titleBackgroud) {
        this.titleBackgroud = titleBackgroud;
    }

    public void setStatusBarBackgroud(int statusBarBackgroud) {
        this.statusBarBackgroud = statusBarBackgroud;
        if (status_bar != null) {
            status_bar.setBackgroundColor(getResources().getColor(statusBarBackgroud));
        }
    }

    public void setAppTitleColor(int appTitleColor) {
        this.appTitleColor = appTitleColor;
    }

    public void setAppRightTitleColor(int appRightTitleColor) {
        this.appRightTitleColor = appRightTitleColor;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setTheme(R.style.AppTheme_NoActionBar);
        mContext = this;
        setContentView(getContentView());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            StatusBarUtils.setTransparent(this);
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        ButterKnife.bind(this);
        if (null != findViewById(R.id.toolbar)) {
            status_bar = findViewById(R.id.status_bar);
            int statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.height = statusBarHeight;
            status_bar.setLayoutParams(layoutParams);
            status_bar.setBackgroundColor(getResources().getColor(statusBarBackgroud));

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        finishAfterTransition();
                    } else {
                        finish();
                    }
                }
            });
            toolbar.setBackgroundColor(getResources().getColor(titleBackgroud));
        }
        if (null != findViewById(R.id.app_title)) {
            app_title = (TextView) findViewById(R.id.app_title);
            app_title.setText(appTitle == null ? "" : appTitle);
            if (TextUtils.isEmpty(appTitle)) {
                if (null != findViewById(R.id.v_bottom_line)) {
                    findViewById(R.id.v_bottom_line).setVisibility(View.GONE);
                }
            }
            if (appTitleColor != 0) {
                app_title.setTextColor(appTitleColor);
            }
        }
        if (null != findViewById(R.id.app_right_tv)) {
            app_right_tv = (TextView) findViewById(R.id.app_right_tv);
            if (appRightTitle != null) {
                app_right_tv.setText(appRightTitle);
                app_right_tv.setVisibility(View.VISIBLE);
                app_right_iv.setVisibility(View.GONE);
                if (appRightTitleColor != 0) {
                    app_right_tv.setTextColor(appRightTitleColor);
                }
            }
        }
        if (null != findViewById(R.id.app_right_iv)) {
            app_right_iv = (ImageView) findViewById(R.id.app_right_iv);
            if (appRightImg != 0) {
                app_right_iv.setImageResource(appRightImg);

                app_right_tv.setVisibility(View.GONE);
                app_right_iv.setVisibility(View.VISIBLE);
            }
        }
        if (null != findViewById(R.id.pcfl_refresh)) {
            pcfl = (PtrClassicFrameLayout) findViewById(R.id.pcfl_refresh);
            pcfl.setLastUpdateTimeRelateObject(this);
        }
        setInput();
        initRxBus();
        initView();
        if (toolbar != null) {
            if (navigationIcon != -1) {
                getSupportActionBar().setHomeAsUpIndicator(navigationIcon);
            } else {
                getSupportActionBar().setHomeAsUpIndicator(R.mipmap.img63);
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(showNavigationIcon);
        }
        initData();
    }

    protected void setBackIcon(int resId) {
        navigationIcon = resId;
    }

    protected void hiddenBackIcon() {
        showNavigationIcon = false;
    }

    protected void hiddenBackIcon(boolean show) {
        showNavigationIcon = show;
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
        if (!ClickUtils.isFastClick(v, 800)) {
            onViewClick(v);
        }
    }

    private void setInput() {
        final View rootView = ((ViewGroup) this.findViewById(android.R.id.content))
                .getChildAt(0);
        final View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                int screenHeight = decorView.getRootView().getHeight();
                int heightDifferent = screenHeight - rect.bottom - PhoneUtils.getNavigationBarHeight(mContext);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) rootView.getLayoutParams();
                lp.setMargins(0, 0, 0, heightDifferent);
                rootView.requestLayout();
            }
        });

    }
}
