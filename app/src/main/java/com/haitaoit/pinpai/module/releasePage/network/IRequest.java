package com.haitaoit.pinpai.module.releasePage.network;

import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.personPage.network.request.PostComplainItem;
import com.haitaoit.pinpai.module.releasePage.network.request.PostUserReplaceItem;
import com.haitaoit.pinpai.module.releasePage.network.response.GetBandClassObj;
import com.haitaoit.pinpai.module.releasePage.network.response.GetCateObj;
import com.haitaoit.pinpai.module.releasePage.network.response.GetLibraryObj;
import com.haitaoit.pinpai.module.releasePage.network.response.GetNeedObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by LZY on 2017/12/14.
 */

public interface IRequest {

    //发布货源
    @POST("api/Goods/PostUserGoods")
    public Call<ResponseObj> PostUserGoods(@QueryMap Map<String, String> map, @Body PostUserReplaceItem item);

    //发布求购
    @POST("api/Goods/PostUserNeed")
    public Call<ResponseObj> PostUserNeed(@QueryMap Map<String, String> map, @Body PostUserReplaceItem item);


    // //根据商品名称匹配商品库中的商品
    @GET("api/Goods/GetGlibrary")
    public Call<GetLibraryObj> GetGlibrary(@QueryMap Map<String, String> map);

    //根据商品名称判断该用户是否发布过此商品
    @GET("api/Goods/GetIsgoods")
    public Call<GetLibraryObj> GetIsgoods(@QueryMap Map<String, String> map);


    // 根据求购商品名称匹配所发布的货源中的商品
    @GET("api/Goods/GetNlibrary")
    public Call<GetNeedObj> GetNlibrary(@QueryMap Map<String, String> map);




    //商品一级分类列表
    @GET("api/Goods/GetParentCateList")
    public Call<GetBandClassObj> GetParentCateList(@QueryMap Map<String, String> map);


    //根据商品分类父ID获取商品分类子级列表
    @GET("api/Goods/GetChildCateList")
    public Call<GetCateObj> GetChildCateList(@QueryMap Map<String, String> map);


}
