package com.haitaoit.pinpai.base;

import android.content.Context;
import android.widget.Toast;

import com.github.baseclass.view.Loading;
import com.github.retrofitutil.RetrofitCallBack;

/**
 * Created by Administrator on 2017/5/18.
 */

public abstract class MyCallBack<T> extends RetrofitCallBack<T> {
    private Context context;
    private boolean noLoading=false;

    public  abstract void onSuccessful(T response);
    public MyCallBack(Context ctx) {
        context=ctx;
        Loading.show(ctx);
    }
    public MyCallBack(Context ctx, boolean noLoading) {
        this.noLoading=noLoading;
        context=ctx;
        if(!noLoading){
            Loading.show(ctx);
        }
    }
    @Override
    protected void onSuccess(T response) {
        onSuccessful(response);
        if(!noLoading){
            Loading.dismissLoading();
        }
    }
    @Override
    protected void onError(Throwable throwable) {
        if(!noLoading){
            Loading.dismissLoading();
        }
        Toast.makeText(context,"连接失败", Toast.LENGTH_SHORT).show();
        context=null;
        throwable.printStackTrace();
    }
}
