package com.haitaoit.pinpai.module.releasePage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/28.
 */

public class GetLibraryObj {


    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"dyimglist":[{"img_url":"http://linb.hai-tao.net/upload/201801/05/thumb_1_201801051551507357.png"}],"title":"魏巍","dypricelist":[{"quantity":12,"price":453}],"stock":5434535,"delivery_time":11,"zhaiyao":"","brand":"11","parent_id":4,"parent":"老年装","category_id":10,"category":"毛呢外套","origin_place":"11","bar_code":"111","source":"11"}
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
         * dyimglist : [{"img_url":"http://linb.hai-tao.net/upload/201801/05/thumb_1_201801051551507357.png"}]
         * title : 魏巍
         * dypricelist : [{"quantity":12,"price":453}]
         * stock : 5434535
         * delivery_time : 11
         * zhaiyao :
         * brand : 11
         * parent_id : 4
         * parent : 老年装
         * category_id : 10
         * category : 毛呢外套
         * origin_place : 11
         * bar_code : 111
         * source : 11
         */

        private String title;
        private String stock;
        private String delivery_time;
        private String zhaiyao;
        private String brand;
        private String parent_id;
        private String parent;
        private String category_id;
        private String category;
        private String origin_place;
        private String bar_code;
        private String source;

        public String getIs_matching() {
            return is_matching;
        }

        public void setIs_matching(String is_matching) {
            this.is_matching = is_matching;
        }

        private String is_matching;
        private List<DyimglistBean> dyimglist;
        private List<DypricelistBean> dypricelist;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getZhaiyao() {
            return zhaiyao;
        }

        public void setZhaiyao(String zhaiyao) {
            this.zhaiyao = zhaiyao;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getOrigin_place() {
            return origin_place;
        }

        public void setOrigin_place(String origin_place) {
            this.origin_place = origin_place;
        }

        public String getBar_code() {
            return bar_code;
        }

        public void setBar_code(String bar_code) {
            this.bar_code = bar_code;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public List<DyimglistBean> getDyimglist() {
            return dyimglist;
        }

        public void setDyimglist(List<DyimglistBean> dyimglist) {
            this.dyimglist = dyimglist;
        }

        public List<DypricelistBean> getDypricelist() {
            return dypricelist;
        }

        public void setDypricelist(List<DypricelistBean> dypricelist) {
            this.dypricelist = dypricelist;
        }

        public static class DyimglistBean {
            /**
             * img_url : http://linb.hai-tao.net/upload/201801/05/thumb_1_201801051551507357.png
             */

            private String img_url;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }

        public static class DypricelistBean {
            /**
             * quantity : 12
             * price : 453.0
             */

            private String quantity;
            private String price;

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}

