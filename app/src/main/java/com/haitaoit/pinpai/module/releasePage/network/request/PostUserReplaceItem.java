package com.haitaoit.pinpai.module.releasePage.network.request;


import java.util.List;

/**
 * Created by LZY on 2017/12/14.
 */

public class PostUserReplaceItem {


    /**
     * cart_id : 购物车数据ID 没有传0
     * goods_id : 商品ID
     * quantity : 购买数量
     */


    private String title;
    private String bar_code;
    private String brand;
    private String origin_place;
    private String zhaiyao;
    private String is_matching;

    public String getIs_matching() {
        return is_matching;
    }

    public void setIs_matching(String is_matching) {
        this.is_matching = is_matching;
    }



    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    private String category_id;
    private String parent_id;

    private String stock;
    private String source;
    private String delivery_time;
    private String is_advantage;
    private List<PostUserReplaceItem.ImgListBean> img_list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin_place() {
        return origin_place;
    }

    public void setOrigin_place(String origin_place) {
        this.origin_place = origin_place;
    }

    public String getZhaiyao() {
        return zhaiyao;
    }

    public void setZhaiyao(String zhaiyao) {
        this.zhaiyao = zhaiyao;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getIs_advantage() {
        return is_advantage;
    }

    public void setIs_advantage(String is_advantage) {
        this.is_advantage = is_advantage;
    }

    public List<ImgListBean> getImg_list() {
        return img_list;
    }

    public void setImg_list(List<ImgListBean> img_list) {
        this.img_list = img_list;
    }

    public List<PriceListBean> getPrice_list() {
        return price_list;
    }

    public void setPrice_list(List<PriceListBean> price_list) {
        this.price_list = price_list;
    }

    private List<PostUserReplaceItem.PriceListBean> price_list;

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

    public static class PriceListBean {
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        /**
         * img_url : 图片路径
         */

        private String price;


    }

}
