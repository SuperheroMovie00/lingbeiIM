package com.haitaoit.pinpai.module.loginPage.network.response;

/**
 * Created by LZY on 2017/12/11.
 */

public class GetLoginObj {


    /**
     * ErrCode : 0
     * ErrMsg : 登录成功！
     * Response : {"avatar":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201641010172.png","mobile":"120","nick_name":"610***.com","user_name":"QKJDJ63313","password":"12345678","message_num":1,"user_id":"1","email":"610960144@qq.com","address":"","default_address":"","is_certified":2,"type":"公司"}
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
         * avatar : http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201641010172.png
         * mobile : 120
         * nick_name : 610***.com
         * user_name : QKJDJ63313
         * password : 12345678
         * message_num : 1
         * user_id : 1
         * email : 610960144@qq.com
         * address :
         * default_address :
         * is_certified : 2
         * type : 公司
         */

        private String avatar;
        private String mobile;
        private String nick_name;
        private String user_name;
        private String password;
        private String message_num;
        private String user_id;
        private String email;
        private String address;
        private String default_address;
        private String is_certified;
        private String type;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMessage_num() {
            return message_num;
        }

        public void setMessage_num(String message_num) {
            this.message_num = message_num;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDefault_address() {
            return default_address;
        }

        public void setDefault_address(String default_address) {
            this.default_address = default_address;
        }

        public String getIs_certified() {
            return is_certified;
        }

        public void setIs_certified(String is_certified) {
            this.is_certified = is_certified;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
