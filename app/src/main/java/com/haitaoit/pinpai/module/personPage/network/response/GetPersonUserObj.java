package com.haitaoit.pinpai.module.personPage.network.response;

/**
 * Created by LZY on 2017/12/11.
 */

public class GetPersonUserObj
{
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"avatar":"http://linb.hai-tao.net/upload/201801/03/18010317095360722397.jpg","mobile":"15927262608","nick_name":"gr测试园","user_name":"IZMHQ39426","message_num":0,"user_id":"23","email":"2881647181@qq.com","weixin":"15927262608","address":"湖北省荆州市汴京大道","default_address":"","is_certified":2,"type":"个人","certified":"156***6879","certified_name":"测试小***测试小园","collect_num":0,"fllow_num":1,"RegistrationID":null,"goods_num":0,"need_num":1,"qq":"1455392295"}
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
         * avatar : http://linb.hai-tao.net/upload/201801/03/18010317095360722397.jpg
         * mobile : 15927262608
         * nick_name : gr测试园
         * user_name : IZMHQ39426
         * message_num : 0
         * user_id : 23
         * email : 2881647181@qq.com
         * weixin : 15927262608
         * address : 湖北省荆州市汴京大道
         * default_address :
         * is_certified : 2
         * type : 个人
         * certified : 156***6879
         * certified_name : 测试小***测试小园
         * collect_num : 0
         * fllow_num : 1
         * RegistrationID : null
         * goods_num : 0
         * need_num : 1
         * qq : 1455392295
         */

        private String avatar;
        private String mobile;
        private String nick_name;
        private String user_name;
        private String message_num;
        private String user_id;
        private String email;
        private String weixin;
        private String address;
        private String default_address;
        private String is_certified;
        private String type;
        private String certified;
        private String certified_name;
        private String collect_num;
        private String fllow_num;
        private Object RegistrationID;
        private String goods_num;
        private String need_num;
        private String qq;

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

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
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

        public String getCertified() {
            return certified;
        }

        public void setCertified(String certified) {
            this.certified = certified;
        }

        public String getCertified_name() {
            return certified_name;
        }

        public void setCertified_name(String certified_name) {
            this.certified_name = certified_name;
        }

        public String getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(String collect_num) {
            this.collect_num = collect_num;
        }

        public String getFllow_num() {
            return fllow_num;
        }

        public void setFllow_num(String fllow_num) {
            this.fllow_num = fllow_num;
        }

        public Object getRegistrationID() {
            return RegistrationID;
        }

        public void setRegistrationID(Object RegistrationID) {
            this.RegistrationID = RegistrationID;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getNeed_num() {
            return need_num;
        }

        public void setNeed_num(String need_num) {
            this.need_num = need_num;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }
    }


    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"avatar":"http://chanxi.hai-tao.net/upload/201712/11/17121116503261990874.jpg","mobile":"13517293997","nick_name":"125***.com","user_name":"NRORC56561","message_num":1,"user_id":"2","email":"610960144@qq.com","weixin":"13517293hy","address":"gggg","default_address":"","is_certified":1,"type":"个人","name":"","collect_num":0,"fllow_num":0}
     */


}
