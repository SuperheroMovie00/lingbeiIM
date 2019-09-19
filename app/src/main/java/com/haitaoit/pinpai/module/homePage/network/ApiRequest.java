package com.haitaoit.pinpai.module.homePage.network;

import com.haitaoit.pinpai.base.BaseRequest;
import com.haitaoit.pinpai.base.MyCallBack;
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
import com.haitaoit.pinpai.module.loginPage.network.*;
import com.haitaoit.pinpai.module.loginPage.network.response.GetUpdatePassObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetNeedDetailObj;
import com.haitaoit.pinpai.module.releasePage.network.request.PostUserReplaceItem;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by LZY on 2017/12/13.
 */

public class ApiRequest extends BaseRequest {

    public static void GetBanner(Map<String, String> map, MyCallBack callBack) {
        Call<GetBannerObj> call = getGeneralClient().create(IRequest.class).GetBanner(map);
        call.enqueue(callBack);
    }

    public static void GetRedUsers(Map<String, String> map, MyCallBack callBack) {
        Call<GetTuijianObj> call = getGeneralClient().create(IRequest.class).GetRedUsers(map);
        call.enqueue(callBack);
    }

    public static void GetSeachList(Map<String, String> map, MyCallBack callBack) {
        Call<GetHotSearchObj> call = getGeneralClient().create(IRequest.class).GetSeachList(map);
        call.enqueue(callBack);
    }

    public static void GeHistoryList(Map<String, String> map, MyCallBack callBack) {
        Call<GetHistoryListObj> call = getGeneralClient().create(IRequest.class).GeHistoryList(map);
        call.enqueue(callBack);
    }

    public static void GetChangeList(Map<String, String> map, MyCallBack callBack) {
        Call<GetHotSearchObj> call = getGeneralClient().create(IRequest.class).GetChangeList(map);
        call.enqueue(callBack);
    }

    public static void GetSearchDelete(Map<String, String> map, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).GetSearchDelete(map);
        call.enqueue(callBack);
    }

    public static void GetGoodsList(Map<String, String> map, MyCallBack callBack) {
        Call<DateBeatJsonObj> call = getGeneralClient().create(IRequest.class).GetGoodsList(map);
        call.enqueue(callBack);
    }

    public static void GetNeedList(Map<String, String> map, MyCallBack callBack) {
        Call<DateBeatJsonObj> call = getGeneralClient().create(IRequest.class).GetNeedList(map);
        call.enqueue(callBack);
    }

    public static void GetToalCount(Map<String, String> map, MyCallBack callBack) {
        Call<GetCountTotal> call = getGeneralClient().create(IRequest.class).GetToalCount(map);
        call.enqueue(callBack);
    }


    public static void GetSameGoodsList(Map<String, String> map, MyCallBack callBack) {
        Call<DateBeatJsonObj> call = getGeneralClient().create(IRequest.class).GetSameGoodsList(map);
        call.enqueue(callBack);
    }

    public static void GetIdent(Map<String, String> map, MyCallBack callBack) {
        Call<GetIdentObj> call = getGeneralClient().create(IRequest.class).GetIdent(map);
        call.enqueue(callBack);
    }

    public static void GetSameNeedList(Map<String, String> map, MyCallBack callBack) {
        Call<DateBeatJsonObj> call = getGeneralClient().create(IRequest.class).GetSameNeedList(map);
        call.enqueue(callBack);
    }


    public static void GetGoodsListTuijian(Map<String, String> map, MyCallBack callBack) {
        Call<TuiJianHuoYuanObj> call = getGeneralClient().create(IRequest.class).GetGoodsListTuijian(map);
        call.enqueue(callBack);
    }

    public static void GetNeedList4(Map<String, String> map, MyCallBack callBack) {
        Call<TuiJianHuoYuanObj> call = getGeneralClient().create(IRequest.class).GetNeedList4(map);
        call.enqueue(callBack);
    }

    public static void GetGoodsDetail(Map<String, String> map, MyCallBack callBack) {
        Call<GetGoodsDetailJson> call = getGeneralClient().create(IRequest.class).GetGoodsDetail(map);
        call.enqueue(callBack);
    }

    public static void GetNeedDetail(Map<String, String> map, MyCallBack callBack) {
        Call<GetNeedDetailObj> call = getGeneralClient().create(IRequest.class).GetNeedDetail(map);
        call.enqueue(callBack);
    }

    public static void GetAdvantageList(Map<String, String> map, MyCallBack callBack) {
        Call<MyYouShiObj> call = getGeneralClient().create(IRequest.class).GetAdvantageList(map);
        call.enqueue(callBack);
    }

    public static void GetUserNeedList(Map<String, String> map, MyCallBack callBack) {
        Call<MyYouShiObj> call = getGeneralClient().create(IRequest.class).GetUserNeedList(map);
        call.enqueue(callBack);
    }


    public static void GetCollectAdd(Map<String, String> map, MyCallBack callBack) {
        Call<GetCollAddObj> call = getGeneralClient().create(IRequest.class).GetCollectAdd(map);
        call.enqueue(callBack);
    }


    public static void GetFllowAdd(Map<String, String> map, MyCallBack callBack) {
        Call<GetCollAddObj> call = getGeneralClient().create(IRequest.class).GetFllowAdd(map);
        call.enqueue(callBack);
    }


    public static void GetContactAdd(Map<String, String> map, MyCallBack callBack) {
        Call<GetIMJsonObj> call = getGeneralClient().create(IRequest.class).GetContactAdd(map);
        call.enqueue(callBack);
    }

    public static void PostEditGoods(Map<String, String> map, PostUserReplaceItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostEditGoods(map, item);
        call.enqueue(callBack);
    }

    public static void PostEditNeed(Map<String, String> map, PostUserReplaceItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostEditNeed(map, item);
        call.enqueue(callBack);
    }


    public static void GetPriceDelete(Map<String, String> map, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).GetPriceDelete(map);
        call.enqueue(callBack);
    }


}
