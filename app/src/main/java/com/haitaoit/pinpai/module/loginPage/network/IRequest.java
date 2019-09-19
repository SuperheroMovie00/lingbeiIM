package com.haitaoit.pinpai.module.loginPage.network;

import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.loginPage.network.request.PostUserPhotoEditItem;
import com.haitaoit.pinpai.module.loginPage.network.response.GetCodeEmailObj;
import com.haitaoit.pinpai.module.loginPage.network.response.GetH5Obj;
import com.haitaoit.pinpai.module.loginPage.network.response.GetLoginObj;
import com.haitaoit.pinpai.module.loginPage.network.response.GetRegisterOkObj;
import com.haitaoit.pinpai.module.loginPage.network.response.GetUpdatePassObj;
import com.haitaoit.pinpai.module.personPage.network.response.UpdateIMageJson;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by LZY on 2017/12/11.
 */

public interface IRequest {


    //注册发送验证码
    @GET("api/User/GetRegisterCode")
    public Call<GetCodeEmailObj> GetRegisterCode(@QueryMap Map<String, String> map);

    //找回密码发送验证码
    @GET("api/User/GetFindPwdCode")
    public Call<GetCodeEmailObj> GetFindPwdCode(@QueryMap Map<String, String> map);

    //找回密码修改密码
    @GET("api/User/GetFindPwdEdit")
    public Call<ResponseObj> GetFindPwdEdit(@QueryMap Map<String, String> map);

    //注册
    @GET("api/User/GetRegister")
    public Call<GetRegisterOkObj> GetRegisterEmail(@QueryMap Map<String, String> map);

    //用户登录
    @GET("api/User/GetLogin")
    public Call<GetLoginObj> GetUserlogin(@QueryMap Map<String, String> map);

    //微信快捷注册登录
    @GET("api/User/GetOAuthConfirm")
    public Call<GetLoginObj> GetOAuthConfirm(@QueryMap Map<String, String> map);


    //修改密码
    @GET("api/User/GetPwdEdit")
    public Call<GetUpdatePassObj> GetPwdEdit(@QueryMap Map<String, String> map);

    //用户修改用户头像
    @POST("api/User/PostUserPhotoEdit")
    public Call<UpdateIMageJson> PostUserPhotoEdit(@QueryMap Map<String, String> map, @Body PostUserPhotoEditItem item);


    //查询单页内容详情
    @GET("api/Other/GetWebsiteDetail")
    public Call<GetH5Obj> GetWebsiteDetail(@QueryMap Map<String, String> map);


    //查询单页内容详情
    @GET("api/User/GetMessageDetail")
    public Call<GetH5Obj> GetMessageDetail(@QueryMap Map<String, String> map);


}
