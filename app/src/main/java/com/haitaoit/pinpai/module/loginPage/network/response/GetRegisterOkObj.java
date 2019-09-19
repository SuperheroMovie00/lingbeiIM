package com.haitaoit.pinpai.module.loginPage.network.response;

/**
 * Created by LZY on 2017/12/11.
 */

public class GetRegisterOkObj
{
    /**
     * ErrCode : 0
     * ErrMsg : 注册成功，欢迎成为本站会员！
     * Response : {"avatar":"http://chanxi.hai-tao.net","mobile":null,"nick_name":"610***.com","user_name":"QKJDJ63313","message_num":1,"user_id":"1","email":"610960144@qq.com","address":"","default_address":"","is_certified":null}
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
         * avatar : http://chanxi.hai-tao.net
         * mobile : null
         * nick_name : 610***.com
         * user_name : QKJDJ63313
         * message_num : 1
         * user_id : 1
         * email : 610960144@qq.com
         * address :
         * default_address :
         * is_certified : null
         */

        private String avatar;
        private Object mobile;
        private String nick_name;
        private String user_name;
        private String message_num;
        private String user_id;
        private String email;
        private String address;
        private String default_address;
        private Object is_certified;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private String password;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
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

        public Object getIs_certified() {
            return is_certified;
        }

        public void setIs_certified(Object is_certified) {
            this.is_certified = is_certified;
        }
    }
}
