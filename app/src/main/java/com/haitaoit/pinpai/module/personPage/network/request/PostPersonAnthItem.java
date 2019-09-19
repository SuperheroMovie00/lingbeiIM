package com.haitaoit.pinpai.module.personPage.network.request;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class PostPersonAnthItem {


    /**
     * cart_id : 购物车数据ID 没有传0
     * goods_id : 商品ID
     * quantity : 购买数量
     */


    private String personal_name;
    private String sex;
    private String persona_mobile;
    private String persona_email;

    public String getPersonal_name() {
        return personal_name;
    }

    public void setPersonal_name(String personal_name) {
        this.personal_name = personal_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPersona_mobile() {
        return persona_mobile;
    }

    public void setPersona_mobile(String persona_mobile) {
        this.persona_mobile = persona_mobile;
    }

    public String getPersona_email() {
        return persona_email;
    }

    public void setPersona_email(String persona_email) {
        this.persona_email = persona_email;
    }

    public String getPersona_card() {
        return persona_card;
    }

    public void setPersona_card(String persona_card) {
        this.persona_card = persona_card;
    }


    private String persona_card;

    public List<ImgListBean> getCard_front() {
        return card_front;
    }

    public void setCard_front(List<ImgListBean> card_front) {
        this.card_front = card_front;
    }

    public List<ImgSideListBean> getCard_side() {
        return card_side;
    }

    public void setCard_side(List<ImgSideListBean> card_side) {
        this.card_side = card_side;
    }

    private List<ImgListBean> card_front;

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

    private List<ImgSideListBean> card_side;

    public static class ImgSideListBean {
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
