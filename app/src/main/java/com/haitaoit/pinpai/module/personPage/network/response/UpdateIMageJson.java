package com.haitaoit.pinpai.module.personPage.network.response;

/**
 * Created by LZY on 2018/3/6.
 */

public class UpdateIMageJson {
    /**
     * ErrCode : 0
     * ErrMsg : 头像修改成功
     * Response : {"avatar":"http://linb.hai-tao.net/upload/201803/06/18030611213166095326.jpg"}
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
         * avatar : http://linb.hai-tao.net/upload/201803/06/18030611213166095326.jpg
         */

        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
