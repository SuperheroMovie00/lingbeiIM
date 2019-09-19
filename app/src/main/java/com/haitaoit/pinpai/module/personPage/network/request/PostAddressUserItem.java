package com.haitaoit.pinpai.module.personPage.network.request;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class PostAddressUserItem {





        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * cart_id : 购物车数据ID 没有传0
         * goods_id : 商品ID
         * quantity : 购买数量
         */

        private String name;

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

        private String mobile;
        private String country;
        private String city;
        private String zipcode;
        private String address;

}
