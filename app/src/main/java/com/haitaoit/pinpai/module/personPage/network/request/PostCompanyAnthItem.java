package com.haitaoit.pinpai.module.personPage.network.request;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class PostCompanyAnthItem {






    /**
     * cart_id : 购物车数据ID 没有传0
     * goods_id : 商品ID
     * quantity : 购买数量
     */



    private String company;
    private String legal_name;
    private String c_email;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLegal_name() {
        return legal_name;
    }

    public void setLegal_name(String legal_name) {
        this.legal_name = legal_name;
    }


    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }

    public String getContacts_email() {
        return contacts_email;
    }

    public void setContacts_email(String contacts_email) {
        this.contacts_email = contacts_email;
    }

    public String getContacts_mobile() {
        return contacts_mobile;
    }

    public void setContacts_mobile(String contacts_mobile) {
        this.contacts_mobile = contacts_mobile;
    }

    private String contacts_name;
    private String contacts_email;
    private String contacts_mobile;


    public List<ImgListBean> getLicense_card() {
        return license_card;
    }

    public void setLicense_card(List<ImgListBean> license_card) {
        this.license_card = license_card;
    }

    private List<ImgListBean> license_card;

    public static class ImgListBean {
        /**
         * img_url : 图片路径
         */

        private String img_url;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }

}
