package com.haitaoit.pinpai.module.findPage.network.response;

/**
 * Created by LZY on 2018/3/2.
 */

public class GetIMJsonObj {

    /**
     * ErrCode : 0
     * ErrMsg :
     * Response : {"username":"BDVZG01892","password":"123456","avatar":"http://linb.hai-tao.net","sendee_name":"QDJJB61330","sendee_avatar":"http://linb.hai-tao.net/upload/201802/01/18020116300804671801.jpg"}
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
         * username : BDVZG01892
         * password : 123456
         * avatar : http://linb.hai-tao.net
         * sendee_name : QDJJB61330
         * sendee_avatar : http://linb.hai-tao.net/upload/201802/01/18020116300804671801.jpg
         */

        private String username;
        private String password;
        private String avatar;
        private String sendee_name;
        private String sendee_avatar;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSendee_name() {
            return sendee_name;
        }

        public void setSendee_name(String sendee_name) {
            this.sendee_name = sendee_name;
        }

        public String getSendee_avatar() {
            return sendee_avatar;
        }

        public void setSendee_avatar(String sendee_avatar) {
            this.sendee_avatar = sendee_avatar;
        }
    }
}
