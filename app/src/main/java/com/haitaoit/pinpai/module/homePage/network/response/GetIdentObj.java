package com.haitaoit.pinpai.module.homePage.network.response;

/**
 * Created by LZY on 2017/12/29.
 */

public class GetIdentObj {
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"is_certified":2}
     */

    private int ErrCode;
    private String ErrMsg;
    private ResponseBean Response;

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

    public ResponseBean getResponse() {
        return Response;
    }

    public void setResponse(ResponseBean Response) {
        this.Response = Response;
    }

    public static class ResponseBean {
        /**
         * is_certified : 2
         */

        private String is_certified;

        public String getIs_certified() {
            return is_certified;
        }

        public void setIs_certified(String is_certified) {
            this.is_certified = is_certified;
        }
    }
}
