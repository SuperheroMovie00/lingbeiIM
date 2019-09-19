package com.haitaoit.pinpai.module.personPage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/12.
 */

public class GetAddressListObj
{
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"id":"1","name":"hhhh","mobile":"13517293999","country":"湖北省","city":"武汉市","zipcode":"432806","address":"江汉路586号","is_default":1}]
     */

    private int ErrCode;
    private String ErrMsg;
    private List<ResponseBean> Response;

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

    public List<ResponseBean> getResponse() {
        return Response;
    }

    public void setResponse(List<ResponseBean> Response) {
        this.Response = Response;
    }

    public static class ResponseBean {
        /**
         * id : 1
         * name : hhhh
         * mobile : 13517293999
         * country : 湖北省
         * city : 武汉市
         * zipcode : 432806
         * address : 江汉路586号
         * is_default : 1
         */

        private String id;
        private String name;
        private String mobile;
        private String country;
        private String city;
        private String zipcode;
        private String address;
        private String is_default;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }
}
