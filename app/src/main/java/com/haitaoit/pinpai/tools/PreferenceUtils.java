package com.haitaoit.pinpai.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 保存一些数据到手机本地文件夹工具类 PreferenceUtils
 * PreferenceUtils
 * <p/>
 * 刘霄鹏
 * <p/>
 * 2016年1月5日 上午11:11:52
 *
 * @version 1.0.0
 */
public class PreferenceUtils {
    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "peihuotong_date";

    public static String getPrefString(Context context, String key, final String defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    public static void setPrefString(Context context, final String key, final String value) {
        final SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        settings.edit().putString(key, value).commit();
    }

    public static boolean getPrefBoolean(Context context, final String key, final boolean defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean hasKey(Context context, final String key) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
                .contains(key);
    }

    public static void setPrefBoolean(Context context, final String key, final boolean value) {
        final SharedPreferences settings = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        settings.edit().putBoolean(key, value).commit();
    }

    public static void setPrefInt(Context context, final String key, final int value) {
        final SharedPreferences settings = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        settings.edit().putInt(key, value).commit();
    }

    public static int getPrefInt(Context context, final String key, final int defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    public static void setPrefFloat(Context context, final String key, final float value) {
        final SharedPreferences settings = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        settings.edit().putFloat(key, value).commit();
    }

    public static float getPrefFloat(Context context, final String key, final float defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    public static void setSettingLong(Context context, final String key, final long value) {
        final SharedPreferences settings = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        settings.edit().putLong(key, value).commit();
    }

    public static long getPrefLong(Context context, final String key, final long defaultValue) {
        final SharedPreferences settings = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    public static void clearPreference(Context context, final SharedPreferences p) {
        final Editor editor = p.edit();
        editor.clear();
        editor.commit();
    }
}
