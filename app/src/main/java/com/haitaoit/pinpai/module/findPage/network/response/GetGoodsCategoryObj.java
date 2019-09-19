package com.haitaoit.pinpai.module.findPage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/21.
 */

public class GetGoodsCategoryObj {
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"dybrandlist":[{"brand_id":1,"title":"波司登"},{"brand_id":2,"title":"南极人"},{"brand_id":3,"title":"七匹狼"},{"brand_id":4,"title":"gozo"}],"dycaregorylist":[{"category_id":1,"title":"服装"},{"category_id":2,"title":"女装"},{"category_id":3,"title":"男装"}],"dysupplylist":[{"supply_id":1,"title":"服装"},{"supply_id":2,"title":"女装"},{"supply_id":3,"title":"男装"}]}
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
        private List<DybrandlistBean> dybrandlist;
        private List<DycaregorylistBean> dycaregorylist;
        private List<DysupplylistBean> dysupplylist;

        public List<DybrandlistBean> getDybrandlist() {
            return dybrandlist;
        }

        public void setDybrandlist(List<DybrandlistBean> dybrandlist) {
            this.dybrandlist = dybrandlist;
        }

        public List<DycaregorylistBean> getDycaregorylist() {
            return dycaregorylist;
        }

        public void setDycaregorylist(List<DycaregorylistBean> dycaregorylist) {
            this.dycaregorylist = dycaregorylist;
        }

        public List<DysupplylistBean> getDysupplylist() {
            return dysupplylist;
        }

        public void setDysupplylist(List<DysupplylistBean> dysupplylist) {
            this.dysupplylist = dysupplylist;
        }

        public static class DybrandlistBean {
            /**
             * brand_id : 1
             * title : 波司登
             */

            private String brand_id;
            private String title;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            private String img_url;


            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class DycaregorylistBean {
            /**
             * category_id : 1
             * title : 服装
             */

            private String category_id;
            private String title;

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class DysupplylistBean {
            /**
             * supply_id : 1
             * title : 服装
             */

            private String supply_id;
            private String title;

            public String getSupply_id() {
                return supply_id;
            }

            public void setSupply_id(String supply_id) {
                this.supply_id = supply_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
