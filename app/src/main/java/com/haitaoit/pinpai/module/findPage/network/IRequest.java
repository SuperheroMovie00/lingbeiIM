package com.haitaoit.pinpai.module.findPage.network;

import com.haitaoit.pinpai.module.findPage.network.response.GetGoodsCategoryObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetBannerObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by LZY on 2017/12/11.
 */

public interface IRequest {

    //商品列表筛选条件集合
    @GET("api/Goods/GetGoodsCategoryList")
    public Call<GetGoodsCategoryObj> GetGoodsCategoryList(@QueryMap Map<String,String> map);

    //获取商品列表
    @GET("api/Goods/GetGoodsList")
    public Call<GetBannerObj> GetGoodsList(@QueryMap Map<String,String> map);
}
