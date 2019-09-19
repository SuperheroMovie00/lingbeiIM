package com.haitaoit.pinpai.module.findPage.network.response;

/**
 * Created by LZY on 2018/1/16.
 */

public class GetCountTotal {
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"totalCount":31}
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
         * totalCount : 31
         */

        private String totalCount;

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }
    }
}
