package com.haitaoit.pinpai;

import android.content.Context;

/**
 * Created by Administrator on 2017/7/12.
 */

public class Config {
    public static final Object KEY = "D98TZQWpTVlb1nqfkfO615U5ZEignoqW";
    public static String pageSize = "10";
    //用户id
    public static final String user_id = "user_id";
    public static final String user_name = "user_name";
    public static final String sex = "sex";
    public static final String area = "area";
    public static final String mobile = "mobile";
    public static final String avatar = "avatar";
    public static final String user_type = "user_type";
    public static final String user_Email = "user_Email";

    public static final String openid = "openid";
    public static final String oauth_name = "oauth_name";
    public static final String nick_name = "nick_name";
    public static final String imgurl = "imgurl";


    public static int EMOTICON_CLICK_TEXT = 1;
    public static int EMOTICON_CLICK_BIGIMAGE = 2;

    /**
     * app_id是从微信官网申请到的合法APPid
     */
    public static final String APP_ID_WX = "wxa4ab9536880c24c7";

    /**
     * 微信AppSecret值
     */
    public static final String  APP_SECRET_WX = "1adabf22712ca8c4ae2acb9db3a8e7ec";

    public static class SP {
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
