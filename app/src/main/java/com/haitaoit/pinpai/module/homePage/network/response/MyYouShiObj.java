package com.haitaoit.pinpai.module.homePage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/19.
 */

public class MyYouShiObj

{
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"goods_id":44,"title":"gghh","dyimglist":[{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932198834.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932249801.png"}],"dypricelist":[{"quantity":12,"price":78},{"quantity":78,"price":7}]},{"goods_id":22,"title":"大风刮过","dyimglist":[{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712191512455592.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712191512513434.png"}],"dypricelist":[{"quantity":125,"price":12}]}]
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
         * goods_id : 44
         * title : gghh
         * dyimglist : [{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932198834.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932249801.png"}]
         * dypricelist : [{"quantity":12,"price":78},{"quantity":78,"price":7}]
         */

        private String goods_id;
        private String title;
        private List<DyimglistBean> dyimglist;
        private List<DypricelistBean> dypricelist;

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
             * img_url : http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932198834.png
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
             * price : 78.0
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
