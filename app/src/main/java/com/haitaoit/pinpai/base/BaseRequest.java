package com.haitaoit.pinpai.base;

import com.github.retrofitutil.NetWorkManager;

import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/6/1.
 */

public class BaseRequest {
    /**
     * @return 返回对象或者集合无缓存
     */
    public static Retrofit getGeneralClient(){
        return NetWorkManager.getGeneralClient();
    }
    /**
     * @return 返回String无缓存();
     */
    public static Retrofit getGeneralStringClient(){
        return NetWorkManager.getGeneralStringClient();
    }
}
