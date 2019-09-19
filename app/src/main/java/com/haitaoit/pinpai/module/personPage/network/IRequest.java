package com.haitaoit.pinpai.module.personPage.network;

import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.findPage.network.response.AttentionObj;
import com.haitaoit.pinpai.module.personPage.network.request.PostAddressUserItem;
import com.haitaoit.pinpai.module.personPage.network.request.PostCollectDeleteItem;
import com.haitaoit.pinpai.module.personPage.network.request.PostCompanyAnthItem;
import com.haitaoit.pinpai.module.personPage.network.request.PostComplainItem;
import com.haitaoit.pinpai.module.personPage.network.request.PostNeedShareItem;
import com.haitaoit.pinpai.module.personPage.network.request.PostPersonAnthItem;
import com.haitaoit.pinpai.module.personPage.network.request.PostUserInfoUpdatetItem;
import com.haitaoit.pinpai.module.personPage.network.response.CollectionObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetAddressListObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetCityObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetCountryListObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetMessagePinObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetMessageUserObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetPersonUserObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetUserGooddListObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {


    //查询用户信息
    @GET("api/User/GetUserInfo")
    public Call<GetPersonUserObj> GetUserInfo(@QueryMap Map<String, String> map);

    //修改用户信息
    @POST("api/User/PostUserMobileEdit")
    public Call<ResponseObj> PostUserMobileEdit(@QueryMap Map<String, String> map, @Body PostUserInfoUpdatetItem item);

    //我的收货地址
    @GET("api/User/GetAddress")
    public Call<GetAddressListObj> GetAddress(@QueryMap Map<String, String> map);

    //添加修改地址
    @POST("api/User/PostAddressEdit")
    public Call<ResponseObj> PostAddressEdit(@QueryMap Map<String, String> map, @Body PostAddressUserItem item);

    //个人认证
    @POST("api/User/PostPersona_Ident")
    public Call<ResponseObj> PostPersona_Ident(@QueryMap Map<String, String> map, @Body PostPersonAnthItem item);

    //个人认证
    @POST(" api/User/PostComanyIdent")
    public Call<ResponseObj> PostComanyIdent(@QueryMap Map<String, String> map, @Body PostCompanyAnthItem item);

    //添加投诉建议
    @POST("api/User/PostUserComplain")
    public Call<ResponseObj> PostUserComplain(@QueryMap Map<String, String> map, @Body PostComplainItem item);

    //国家列表
    @GET("api/User/GetCountryList")
    public Call<GetCountryListObj> GetCountryList(@QueryMap Map<String, String> map);

    //城市列表
    @GET("api/User/GetCityList")
    public Call<GetCityObj> GetCityList(@QueryMap Map<String, String> map);

    //我的收藏列表
    @GET("api/User/GetCollectList")
    public Call<CollectionObj> GetCollectList(@QueryMap Map<String, String> map);


    //我的消息列表
    @GET("api/User/GetMessageList")
    public Call<GetMessagePinObj> GetMessageList(@QueryMap Map<String, String> map);

    //我的消息列表
    @GET("api/User/GetMessageList")
    public Call<GetMessageUserObj> GetMessageListUser(@QueryMap Map<String, String> map);

    //删除收藏
    @POST("api/User/PostCollectDelete")
    public Call<ResponseObj> PostCollectDelete(@QueryMap Map<String, String> map, @Body PostCollectDeleteItem item);


    //我的关注列表
    @GET("api/User/GetFllowList")
    public Call<AttentionObj> GetFllowList(@QueryMap Map<String, String> map);

    //删除关注
    @POST("api/User/PostFllowDelete")
    public Call<ResponseObj> PostFllowDelete(@QueryMap Map<String, String> map, @Body PostCollectDeleteItem item);


    //推荐人的货源列表
    @GET("api/User/GetRedUserGoods")
    public Call<GetUserGooddListObj> GetRedUserGoods(@QueryMap Map<String, String> map);
    //我的货源列表(或一键分享货源)
    @GET("api/User/GetUersGoodsList")
    public Call<GetUserGooddListObj> GetUersGoodsList(@QueryMap Map<String, String> map);

    // 我的求购列表(或一键分享求购)
    @GET("api/User/GetUserNeedList")
    public Call<GetUserGooddListObj> GetNeedList(@QueryMap Map<String, String> map);

    // 推荐人的求购列表
    @GET("api/User/GetRedUserNeeds")
    public Call<GetUserGooddListObj> GetRedUserNeeds(@QueryMap Map<String, String> map);


    //删除货源
    @POST("api/User/PostGoodsDelete")
    public Call<ResponseObj> PostGoodsDelete(@QueryMap Map<String, String> map, @Body PostCollectDeleteItem item);

    //删除求购
    @POST("api/User/PostNeedDelete")
    public Call<ResponseObj> PostNeedDelete(@QueryMap Map<String, String> map, @Body PostCollectDeleteItem item);

    //一键分享求购
    @POST(" api/User/PostNeedShare")
    public Call<ResponseObj> PostNeedShare(@QueryMap Map<String, String> map, @Body PostNeedShareItem item);

    //一键分享货源
    @POST("api/User/PostGoodsShare")
    public Call<ResponseObj> PostGoodsShare(@QueryMap Map<String, String> map, @Body PostNeedShareItem item);


}
