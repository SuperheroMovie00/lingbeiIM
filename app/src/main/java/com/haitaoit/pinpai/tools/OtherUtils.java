package com.haitaoit.pinpai.tools;

import android.content.Context;
import android.text.TextUtils;

import com.vondear.rxtools.RxSPUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/10.
 */

public class OtherUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    // 用于匹配固定电话号码  
    private final static String REGEX_FIXEDPHONE = "^(010|02\\d|0[3-9]\\d{2})?\\d{6,8}$";
    private static Pattern PATTERN_FIXEDPHONE;

    static {
        PATTERN_FIXEDPHONE = Pattern.compile(REGEX_FIXEDPHONE);
    }

    /**
     *  
     *      * 判断是否为固定电话号码 
     *      *  
     *      * @param number 
     *      *            固定电话号码 
     *      * @return 
     *      
     */
    public static boolean isFixedPhone(String number)

    {
        Matcher match = PATTERN_FIXEDPHONE.matcher(number);
        return match.matches();
    }

    //获取屏幕宽度
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static void putUserID(Context mContext, String user_id) {
        RxSPUtils.putContent(mContext, "user_id", user_id);
    }

    public static String getUserID(Context mContext) {
        return RxSPUtils.getContent(mContext, "user_id");
    }

    public static void putUserName(Context mContext, String user_name) {
        RxSPUtils.putContent(mContext, "username", user_name);
    }

    public static String getUserName(Context mContext) {
        return RxSPUtils.getContent(mContext, "username");
    }

    public static void cleanUserInfo(Context mContext) {
        RxSPUtils.clearPreference(mContext, "user_id", null);
//        RxSPUtils.clearPreference(mContext, "username", null);
    }
}
