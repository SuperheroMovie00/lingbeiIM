package com.haitaoit.pinpai.module.loginPage.network.response;

/**
 * Created by LZY on 2017/12/11.
 */

public class GetUpdatePassObj {
    /**
     * ErrCode : 0
     * ErrMsg : 修改成功
     * Response : null
     */

    private int ErrCode;
    private String ErrMsg;
    private Object Response;

    public int getErrCode() {
        return ErrCode;
    }

    public void setErrCode(int ErrCode) {
        this.ErrCode = ErrCode;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public Object getResponse() {
        return Response;
    }

    public void setResponse(Object Response) {
        this.Response = Response;
    }
}
