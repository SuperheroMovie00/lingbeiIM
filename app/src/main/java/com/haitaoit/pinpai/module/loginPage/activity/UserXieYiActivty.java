package com.haitaoit.pinpai.module.loginPage.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.homePage.network.response.GetHotSearchObj;
import com.haitaoit.pinpai.module.loginPage.network.ApiRequest;
import com.haitaoit.pinpai.module.loginPage.network.response.GetH5Obj;
import com.haitaoit.pinpai.tools.GetSign;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户协议
 * Created by Administrator on 2017/12/6.
 */

public class UserXieYiActivty extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.WebView_content)
    WebView WebViewContent;

    @Override
    protected int getContentView() {
        return R.layout.user_xieyi_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initWebView(WebViewContent);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code","agreement ");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetWebsiteDetail(map, new MyCallBack<GetH5Obj>(mContext) {
            @Override
            public void onSuccessful(GetH5Obj response) {
                if (response.getErrCode() == 0) {
                    showWebView(response.getResponse().getContent(), WebViewContent);
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }

    @Override
    protected void initData() {

    }


    private void showWebView(String content, WebView webView) {
        Document doc_Dis = Jsoup.parse(content);
        Elements ele_Img = doc_Dis.getElementsByTag("img");
        if (ele_Img.size() != 0) {
            for (Element e_Img : ele_Img) {
                e_Img.attr("width", "100%");
                e_Img.attr("height", "auto");
            }
        }
        String newHtmlContent = doc_Dis.toString();
        webView.loadDataWithBaseURL(null, newHtmlContent, "text/html", "utf-8", null);
    }


    private void initWebView(WebView webView) {
        WebSettings wb = webView.getSettings();
        wb.setJavaScriptEnabled(true);
        //设置自适应
        wb.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL.SINGLE_COLUMN);
        wb.setDefaultTextEncodingName("UTF-8");
        wb.setAppCacheEnabled(true);
        wb.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setDefaultTextEncodingName("utf-8");// 避免中文乱码
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setNeedInitialFocus(false);
        settings.setSupportZoom(true);
        settings.setLoadWithOverviewMode(true);//适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadsImagesAutomatically(true);//自动加载图片
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rx_title, R.id.WebView_content})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.WebView_content:
                break;
        }
    }
}
