package com.haitaoit.pinpai.module.findPage.network;

import com.haitaoit.pinpai.base.BaseRequest;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.module.findPage.network.response.GetGoodsCategoryObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetBannerObj;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by LZY on 2017/12/11.
 */

public class ApiRequest extends BaseRequest {
    public static void GetGoodsCategoryList(Map<String, String> map, MyCallBack callBack) {
        Call<GetGoodsCategoryObj> call = getGeneralClient().create(IRequest.class).GetGoodsCategoryList(map);
        call.enqueue(callBack);
    }

    public static void GetGoodsList(Map<String, String> map, MyCallBack callBack) {
        Call<GetBannerObj> call = getGeneralClient().create(IRequest.class).GetGoodsList(map);
        call.enqueue(callBack);
    }

}
