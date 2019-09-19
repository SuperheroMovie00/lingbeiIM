package com.haitaoit.pinpai.module.homePage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/19.
 */

public class GetGoodsDetailJson {


    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"dyimglist":[{"img_url":"http://linb.hai-tao.net/upload/201801/08/18010811404464495256.jpg"}],"goods_id":83,"title":"ttyuuu","dypricelist":[{"price_id":219,"quantity":2,"price":2}],"stock":22,"delivery_time":0,"zhaiyao":"dfg","brand":"gg","parent":"羽绒","catrgory":"老年装","origin_place":"上海市上海市黄浦区","bar_code":"rt","is_colllect":1,"is_fllow":1,"colllect_num":0,"user_name":"610***.com","avatar":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201641010172.png","source":"上海市上海市黄浦区","fllow_num":0,"goods_num":1,"need_num":1,"is_price":1,"user_id":1,"add_time":"2018-01-08  11:40:44","is_advantage":1}
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
         * dyimglist : [{"img_url":"http://linb.hai-tao.net/upload/201801/08/18010811404464495256.jpg"}]
         * goods_id : 83
         * title : ttyuuu
         * dypricelist : [{"price_id":219,"quantity":2,"price":2}]
         * stock : 22
         * delivery_time : 0
         * zhaiyao : dfg
         * brand : gg
         * parent : 羽绒
         * catrgory : 老年装
         * origin_place : 上海市上海市黄浦区
         * bar_code : rt
         * is_colllect : 1
         * is_fllow : 1
         * colllect_num : 0
         * user_name : 610***.com
         * avatar : http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201641010172.png
         * source : 上海市上海市黄浦区
         * fllow_num : 0
         * goods_num : 1
         * need_num : 1
         * is_price : 1
         * user_id : 1
         * add_time : 2018-01-08  11:40:44
         * is_advantage : 1
         */

        private String goods_id;
        private String title;
        private String stock;
        private String delivery_time;
        private String zhaiyao;
        private String brand;
        private String parent;
        private String catrgory;
        private String origin_place;
        private String bar_code;
        private String is_colllect;
        private String is_fllow;
        private String colllect_num;
        private String user_name;
        private String avatar;
        private String source;
        private String fllow_num;
        private String goods_num;
        private String need_num;
        private String is_price;
        private String user_id;
        private String add_time;
        private String is_advantage;
        private List<DyimglistBean> dyimglist;
        private List<DypricelistBean> dypricelist;
        private String certified;
        public String getCertified() {
            return certified;
        }

        public void setCertified(String certified) {
            this.certified = certified;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

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

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getCatrgory() {
            return catrgory;
        }

        public void setCatrgory(String catrgory) {
            this.catrgory = catrgory;
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

        public String getIs_colllect() {
            return is_colllect;
        }

        public void setIs_colllect(String is_colllect) {
            this.is_colllect = is_colllect;
        }

        public String getIs_fllow() {
            return is_fllow;
        }

        public void setIs_fllow(String is_fllow) {
            this.is_fllow = is_fllow;
        }

        public String getColllect_num() {
            return colllect_num;
        }

        public void setColllect_num(String colllect_num) {
            this.colllect_num = colllect_num;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getFllow_num() {
            return fllow_num;
        }

        public void setFllow_num(String fllow_num) {
            this.fllow_num = fllow_num;
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

        public String getIs_price() {
            return is_price;
        }

        public void setIs_price(String is_price) {
            this.is_price = is_price;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getIs_advantage() {
            return is_advantage;
        }

        public void setIs_advantage(String is_advantage) {
            this.is_advantage = is_advantage;
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
             * img_url : http://linb.hai-tao.net/upload/201801/08/18010811404464495256.jpg
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
             * price_id : 219
             * quantity : 2
             * price : 2.0
             */

            private String price_id;
            private String quantity;
            private String price;

            public String getPrice_id() {
                return price_id;
            }

            public void setPrice_id(String price_id) {
                this.price_id = price_id;
            }

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
