package com.haitaoit.pinpai.module.releasePage.network;

import com.haitaoit.pinpai.base.BaseRequest;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.loginPage.network.response.GetLoginObj;
import com.haitaoit.pinpai.module.releasePage.network.request.PostUserReplaceItem;
import com.haitaoit.pinpai.module.releasePage.network.response.GetBandClassObj;
import com.haitaoit.pinpai.module.releasePage.network.response.GetCateObj;
import com.haitaoit.pinpai.module.releasePage.network.response.GetLibraryObj;
import com.haitaoit.pinpai.module.releasePage.network.response.GetNeedObj;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by LZY on 2017/12/14.
 */

public class ApiRequest extends BaseRequest {

    public static void PostUserGoods(Map<String, String> map, PostUserReplaceItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostUserGoods(map, item);
        call.enqueue(callBack);
    }

    public static void PostUserNeed(Map<String, String> map, PostUserReplaceItem item, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).PostUserNeed(map, item);
        call.enqueue(callBack);
    }

    public static void GetGlibrary(Map<String, String> map, MyCallBack callBack) {
        Call<GetLibraryObj> call = getGeneralClient().create(IRequest.class).GetGlibrary(map);
        call.enqueue(callBack);
    }

    public static void GetIsgoods(Map<String, String> map, MyCallBack callBack) {
        Call<GetLibraryObj> call = getGeneralClient().create(IRequest.class).GetIsgoods(map);
        call.enqueue(callBack);
    }

    public static void GetNlibrary(Map<String, String> map, MyCallBack callBack) {
        Call<GetNeedObj> call = getGeneralClient().create(IRequest.class).GetNlibrary(map);
        call.enqueue(callBack);
    }

    public static void GetParentCateList(Map<String, String> map, MyCallBack callBack) {
        Call<GetBandClassObj> call = getGeneralClient().create(IRequest.class).GetParentCateList(map);
        call.enqueue(callBack);
    }

    public static void GetChildCateList(Map<String, String> map, MyCallBack callBack) {
        Call<GetCateObj> call = getGeneralClient().create(IRequest.class).GetChildCateList(map);
        call.enqueue(callBack);
    }
}
