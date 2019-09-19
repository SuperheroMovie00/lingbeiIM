package com.haitaoit.pinpai.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.ExitApplication;
import com.haitaoit.pinpai.base.WXAccessTokenEntity;
import com.haitaoit.pinpai.base.WXBaseRespEntity;
import com.haitaoit.pinpai.base.WXUserInfo;
import com.haitaoit.pinpai.module.loginPage.activity.LoginActivity;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * description ：
 * project name：CCloud
 * author : Vincent
 * creation date: 2017/6/9 18:13
 *
 * @version 1.0
 */

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    /**
     * 微信登录相关
     */
    private IWXAPI api;
    WXUserInfo wxResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, Config.APP_ID_WX, true);
        //将应用的appid注册到微信
        api.registerApp(Config.APP_ID_WX);
        Log.e("-------------------", "==============");
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result = api.handleIntent(getIntent(), this);
            if (!result) {
                Log.e("-------------------", "参数不合法，未被SDK处理，退出");
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.e("-------------------", "baseReq:" + JSON.toJSONString(baseReq));
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.e("-------------------", "baseResp:--A" + JSON.toJSONString(baseResp));
        Log.e("-------------------", "baseResp--B:" + baseResp.errStr + "," + baseResp.openId + "," + baseResp.transaction + "," + baseResp.errCode);
        WXBaseRespEntity entity = JSON.parseObject(JSON.toJSONString(baseResp), WXBaseRespEntity.class);
        String result = "";
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "发送成功";
//                showDialog("正在获取个人资料..");
                //现在请求获取数据 access_token https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
//                showMsg(1,result);
                /*Call call = RetrofitUtils.getApiService("https://api.weixin.qq.com/").getWeiXinAccessToken(Config.APP_ID_WX,Config.APP_SECRET_WX,entity.getCode(),"authorization_code");
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        ViseLog.d("response:"+JSON.toJSONString(response));
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        closeDialog();
                    }
                });*/
                OkHttpUtils.get().url("https://api.weixin.qq.com/sns/oauth2/access_token")
                        .addParams("appid", Config.APP_ID_WX)
                        .addParams("secret", Config.APP_SECRET_WX)
                        .addParams("code", entity.getCode())
                        .addParams("grant_type", "authorization_code")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(okhttp3.Call call, Exception e, int id) {
                                Log.e("-------------------", "请求错误..");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("-------------------", "response:" + response);
                                WXAccessTokenEntity accessTokenEntity = JSON.parseObject(response, WXAccessTokenEntity.class);
                                if (accessTokenEntity != null) {
                                    getUserInfo(accessTokenEntity);
                                } else {
                                    Log.e("-------------------", "获取失败");
                                }
                            }
                        });
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                Log.e("-------------------", "发送取消");
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                Log.e("-------------------", "发送被拒绝");
                finish();
                break;
            case BaseResp.ErrCode.ERR_BAN:
                result = "签名错误";
                Log.e("-------------------", "签名错误");
                break;
            default:
                result = "发送返回";
//                showMsg(0,result);
                finish();
                break;
        }
        Toast.makeText(WXEntryActivity.this, result, Toast.LENGTH_LONG).show();

    }

    /**
     * 获取个人信息
     *
     * @param accessTokenEntity
     */
    private void getUserInfo(WXAccessTokenEntity accessTokenEntity) {
        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
        OkHttpUtils.get()
                .url("https://api.weixin.qq.com/sns/userinfo")
                .addParams("access_token", accessTokenEntity.getAccess_token())
                .addParams("openid", accessTokenEntity.getOpenid())//openid:授权用户唯一标识
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        Log.e("-------------------", "获取错误..");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("-------------------", "userInfo:" + response);
                        wxResponse = JSON.parseObject(response, WXUserInfo.class);
                        Log.e("-------------------", "微信登录资料已获取，后续未完成");
                        String headUrl = wxResponse.getHeadimgurl();
                        Log.e("-------------------", "头像Url:" + headUrl);
                        ExitApplication.getShared().putString("headUrl", headUrl);
                        Intent intent = getIntent();
                        intent.putExtra("openid", wxResponse.getOpenid());
                        intent.putExtra("oauth_name", "weixin");
                        intent.putExtra("nick_name", wxResponse.getNickname());
                        intent.putExtra("headUrl", headUrl);
                        PreferenceUtils.setPrefString(WXEntryActivity.this, Config.openid, wxResponse.getOpenid() + "");
                        PreferenceUtils.setPrefString(WXEntryActivity.this, Config.oauth_name, "weixin");
                        PreferenceUtils.setPrefString(WXEntryActivity.this, Config.nick_name, wxResponse.getNickname());
                        PreferenceUtils.setPrefString(WXEntryActivity.this, Config.imgurl, wxResponse.getHeadimgurl());
                        Log.e("-------------------", "头像Url:" + wxResponse.getOpenid() + "wxResponse.getOpenid()==" + wxResponse.getNickname());
                        WXEntryActivity.this.setResult(0, intent);
                        finish();
                    }
                });
    }


}

