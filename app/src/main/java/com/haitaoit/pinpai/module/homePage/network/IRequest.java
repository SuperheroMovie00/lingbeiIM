package com.haitaoit.pinpai.module.homePage.network;

import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.findPage.network.response.GetCollAddObj;
import com.haitaoit.pinpai.module.findPage.network.response.GetCountTotal;
import com.haitaoit.pinpai.module.findPage.network.response.GetIMJsonObj;
import com.haitaoit.pinpai.module.findPage.network.response.TuiJianHuoYuanObj;
import com.haitaoit.pinpai.module.homePage.network.response.DateBeatJsonObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetBannerObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetGoodsDetailJson;
import com.haitaoit.pinpai.module.homePage.network.response.GetHistoryListObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetHotSearchObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetIdentObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetTuijianObj;
import com.haitaoit.pinpai.module.homePage.network.response.MyYouShiObj;
import com.haitaoit.pinpai.module.loginPage.network.response.GetCodeEmailObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetNeedDetailObj;
import com.haitaoit.pinpai.module.releasePage.network.request.PostUserReplaceItem;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by LZY on 2017/12/13.
 */

public interface IRequest {

    //广告图列表
    @GET("api/Other/GetBanner")
    public Call<GetBannerObj> GetBanner(@QueryMap Map<String, String> map);


    //推荐供应商 或 推荐求购商
    @GET("api/User/GetRedUsers")
    public Call<GetTuijianObj> GetRedUsers(@QueryMap Map<String, String> map);

    //获取热门搜索关键词
    @GET("api/Website/GetSeachList")
    public Call<GetHotSearchObj> GetSeachList(@QueryMap Map<String, String> map);

    //获取历史搜索
    @POST("api/Website/GeHistoryList")
    public Call<GetHistoryListObj> GeHistoryList(@QueryMap Map<String, String> map);

    //换-换
    @GET("api/Website/GetChangeList")
    public Call<GetHotSearchObj> GetChangeList(@QueryMap Map<String, String> map);

    //清空历史搜索记录
    @GET("api/Website/GetSearchDelete")
    public Call<ResponseObj> GetSearchDelete(@QueryMap Map<String, String> map);

    //获取商品列表
    @GET("api/Goods/GetGoodsList")
    public Call<DateBeatJsonObj> GetGoodsList(@QueryMap Map<String, String> map);

    //获取求购列表
    @GET("api/Goods/GetNeedList")
    public Call<DateBeatJsonObj> GetNeedList(@QueryMap Map<String, String> map);

    //获取货源或求购的总数量
    @GET("api/Goods/GetToalCount")
    public Call<GetCountTotal> GetToalCount(@QueryMap Map<String, String> map);


    //根据商品名称查看同款商品
    @GET("api/Goods/GetSameGoodsList")
    public Call<DateBeatJsonObj> GetSameGoodsList(@QueryMap Map<String, String> map);

    //判断是否认证成功
    @GET("api/User/GetIdent")
    public Call<GetIdentObj> GetIdent(@QueryMap Map<String, String> map);

    //根据商品名称查看同款需求
    @GET("api/Goods/GetSameNeedList")
    public Call<DateBeatJsonObj> GetSameNeedList(@QueryMap Map<String, String> map);


    //获取商品列表
    @GET("api/Goods/GetGoodsList")
    public Call<TuiJianHuoYuanObj> GetGoodsListTuijian(@QueryMap Map<String, String> map);


    //获取商品列表
    @GET("api/Goods/GetNeedList")
    public Call<TuiJianHuoYuanObj> GetNeedList4(@QueryMap Map<String, String> map);


    //查询商品参数
    @GET("api/Goods/GetGoodsDetail")
    public Call<GetGoodsDetailJson> GetGoodsDetail(@QueryMap Map<String, String> map);

    //查询求购参数
    @GET("api/Goods/GetNeedDetail")
    public Call<GetNeedDetailObj> GetNeedDetail(@QueryMap Map<String, String> map);

    //我的优势货源
    @GET("api/Goods/GetAdvantageList")
    public Call<MyYouShiObj> GetAdvantageList(@QueryMap Map<String, String> map);


    //她的优势货源
    @GET("api/Goods/GetUserNeedList")
    public Call<MyYouShiObj> GetUserNeedList(@QueryMap Map<String, String> map);


    //添加收藏商品
    @GET("api/Goods/GetCollectAdd")

    public Call<GetCollAddObj> GetCollectAdd(@QueryMap Map<String, String> map);

    //添加关注
    @GET("api/Goods/GetFllowAdd")
    public Call<GetCollAddObj> GetFllowAdd(@QueryMap Map<String, String> map);

    //GetContactAdd
    @GET("api/Goods/GetContactAdd")
    public Call<GetIMJsonObj> GetContactAdd(@QueryMap Map<String, String> map);


    //修改货源商品信息
    @POST("api/User/PostEditGoods")
    public Call<ResponseObj> PostEditGoods(@QueryMap Map<String, String> map, @Body PostUserReplaceItem item);

    //修改货源商品信息
    @POST("api/User/PostEditNeed")
    public Call<ResponseObj> PostEditNeed(@QueryMap Map<String, String> map, @Body PostUserReplaceItem item);

    //重置起批价格组
    @GET("api/User/GetPriceDelete")
    public Call<ResponseObj> GetPriceDelete(@QueryMap Map<String, String> map);


}
