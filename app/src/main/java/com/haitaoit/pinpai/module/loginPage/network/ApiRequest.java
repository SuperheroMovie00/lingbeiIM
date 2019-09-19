package com.haitaoit.pinpai.module.loginPage.network;

import com.haitaoit.pinpai.base.BaseRequest;
import com.haitaoit.pinpai.base.MyCallBack;
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

/**
 * Created by LZY on 2017/12/11.
 */

public class ApiRequest extends BaseRequest {

    public static void GetRegisterCode(Map<String, String> map, MyCallBack callBack) {
        Call<GetCodeEmailObj> call = getGeneralClient().create(IRequest.class).GetRegisterCode(map);
        call.enqueue(callBack);
    }

    public static void GetFindPwdCode(Map<String, String> map, MyCallBack callBack) {
        Call<GetCodeEmailObj> call = getGeneralClient().create(IRequest.class).GetFindPwdCode(map);
        call.enqueue(callBack);
    }

    public static void GetFindPwdEdit(Map<String, String> map, MyCallBack callBack) {
        Call<ResponseObj> call = getGeneralClient().create(IRequest.class).GetFindPwdEdit(map);
        call.enqueue(callBack);
    }

    public static void GetRegisterEmail(Map<String, String> map, MyCallBack callBack) {
        Call<GetRegisterOkObj> call = getGeneralClient().create(IRequest.class).GetRegisterEmail(map);
        call.enqueue(callBack);
    }

    public static void GetUserlogin(Map<String, String> map, MyCallBack callBack) {
        Call<GetLoginObj> call = getGeneralClient().create(IRequest.class).GetUserlogin(map);
        call.enqueue(callBack);
    }

    public static void GetOAuthConfirm(Map<String, String> map, MyCallBack callBack) {
        Call<GetLoginObj> call = getGeneralClient().create(IRequest.class).GetOAuthConfirm(map);
        call.enqueue(callBack);
    }

    public static void GetPwdEdit(Map<String, String> map, MyCallBack callBack) {
        Call<GetUpdatePassObj> call = getGeneralClient().create(IRequest.class).GetPwdEdit(map);
        call.enqueue(callBack);
    }

    public static void PostUserPhotoEdit(Map<String, String> map, PostUserPhotoEditItem item, MyCallBack callBack) {
        Call<UpdateIMageJson> call = getGeneralClient().create(IRequest.class).PostUserPhotoEdit(map, item);
        call.enqueue(callBack);
    }

    public static void GetWebsiteDetail(Map<String, String> map, MyCallBack callBack) {
        Call<GetH5Obj> call = getGeneralClient().create(IRequest.class).GetWebsiteDetail(map);
        call.enqueue(callBack);
    }

    public static void GetMessageDetail(Map<String, String> map, MyCallBack callBack) {
        Call<GetH5Obj> call = getGeneralClient().create(IRequest.class).GetMessageDetail(map);
        call.enqueue(callBack);
    }
}
