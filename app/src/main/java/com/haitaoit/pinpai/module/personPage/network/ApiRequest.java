package com.haitaoit.pinpai.module.personPage.network;

import com.haitaoit.pinpai.base.BaseRequest;
import com.haitaoit.pinpai.base.MyCallBack;
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

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseRequest {

    public static void GetUserInfo(Map<String, String> map, MyCallBack callBack) {
        Call<GetPersonUserObj> call = getGeneralClient().create(IRequest.class).GetUserInfo(map);
        call.enqueue(callBack);
    }

    public static void PostUserMobileEdit(Map<String, String> map, PostUserInfoUpdatetItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostUserMobileEdit(map, item);
        call.enqueue(callBack);
    }

    public static void GetAddress(Map<String, String> map, MyCallBack callBack) {
        Call<GetAddressListObj> call = getGeneralClient().create(IRequest.class).GetAddress(map);
        call.enqueue(callBack);
    }

    public static void PostAddressEdit(Map<String, String> map, PostAddressUserItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostAddressEdit(map, item);
        call.enqueue(callBack);
    }

    public static void PostPersona_Ident(Map<String, String> map, PostPersonAnthItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostPersona_Ident(map, item);
        call.enqueue(callBack);
    }

    public static void PostComanyIdent(Map<String, String> map, PostCompanyAnthItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostComanyIdent(map, item);
        call.enqueue(callBack);
    }

    public static void PostUserComplain(Map<String, String> map, PostComplainItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostUserComplain(map, item);
        call.enqueue(callBack);
    }

    public static void GetCountryList(Map<String, String> map, MyCallBack callBack) {
        Call<GetCountryListObj> call = getGeneralClient().create(IRequest.class).GetCountryList(map);
        call.enqueue(callBack);
    }

    public static void GetCityList(Map<String, String> map, MyCallBack callBack) {
        Call<GetCityObj> call = getGeneralClient().create(IRequest.class).GetCityList(map);
        call.enqueue(callBack);
    }


    public static void GetCollectList(Map<String, String> map, MyCallBack callBack) {
        Call<CollectionObj> call = getGeneralClient().create(IRequest.class).GetCollectList(map);
        call.enqueue(callBack);
    }

    public static void GetMessageList(Map<String, String> map, MyCallBack callBack) {
        Call<GetMessagePinObj> call = getGeneralClient().create(IRequest.class).GetMessageList(map);
        call.enqueue(callBack);
    }
    public static void GetMessageListUser(Map<String, String> map, MyCallBack callBack) {
        Call<GetMessageUserObj> call = getGeneralClient().create(IRequest.class).GetMessageListUser(map);
        call.enqueue(callBack);
    }


    public static void PostCollectDelete(Map<String, String> map, PostCollectDeleteItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostCollectDelete(map,item);
        call.enqueue(callBack);
    }

    public static void GetFllowList(Map<String, String> map, MyCallBack callBack) {
        Call<AttentionObj> call = getGeneralClient().create(IRequest.class).GetFllowList(map);
        call.enqueue(callBack);
    }

    public static void PostFllowDelete(Map<String, String> map, PostCollectDeleteItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostFllowDelete(map,item);
        call.enqueue(callBack);
    }


    public static void GetRedUserGoods(Map<String, String> map, MyCallBack callBack) {
        Call<GetUserGooddListObj> call = getGeneralClient().create(IRequest.class).GetRedUserGoods(map);
        call.enqueue(callBack);
    }

    public static void GetUersGoodsList(Map<String, String> map, MyCallBack callBack) {
        Call<GetUserGooddListObj> call = getGeneralClient().create(IRequest.class).GetUersGoodsList(map);
        call.enqueue(callBack);
    }

    public static void GetNeedList(Map<String, String> map, MyCallBack callBack) {
        Call<GetUserGooddListObj> call = getGeneralClient().create(IRequest.class).GetNeedList(map);
        call.enqueue(callBack);
    }


    public static void GetRedUserNeeds(Map<String, String> map, MyCallBack callBack) {
        Call<GetUserGooddListObj> call = getGeneralClient().create(IRequest.class).GetRedUserNeeds  (map);
        call.enqueue(callBack);
    }




    public static void PostGoodsDelete(Map<String, String> map, PostCollectDeleteItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostGoodsDelete(map,item);
        call.enqueue(callBack);
    }

    public static void PostNeedDelete(Map<String, String> map, PostCollectDeleteItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostNeedDelete(map,item);
        call.enqueue(callBack);
    }


    public static void PostNeedShare(Map<String, String> map, PostNeedShareItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostNeedShare(map,item);
        call.enqueue(callBack);
    }

    public static void PostGoodsShare(Map<String, String> map, PostNeedShareItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostGoodsShare(map,item);
        call.enqueue(callBack);
    }
}
