package com.haitaoit.pinpai.module.findPage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/19.
 */

public class TuiJianHuoYuanObj

{

    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"goods_id":44,"dyimglist":[{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932198834.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932249801.png"}],"title":"gghh","addtime":"2017-12-18","source":"上海","type":1,"is_colllect":1,"user_name":"125***.com","avatar":"http://linb.hai-tao.net/upload/201712/15/17121514485856447712.jpg"}]
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
         * dyimglist : [{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932198834.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932249801.png"}]
         * title : gghh
         * addtime : 2017-12-18
         * source : 上海
         * type : 1
         * is_colllect : 1
         * user_name : 125***.com
         * avatar : http://linb.hai-tao.net/upload/201712/15/17121514485856447712.jpg
         */

        private String goods_id;
        private String title;
        private String addtime;
        private String source;
        private String type;
        private String is_colllect;
        private String user_name;
        private String avatar;
        private List<DyimglistBean> dyimglist;

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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_colllect() {
            return is_colllect;
        }

        public void setIs_colllect(String is_colllect) {
            this.is_colllect = is_colllect;
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

        public List<DyimglistBean> getDyimglist() {
            return dyimglist;
        }

        public void setDyimglist(List<DyimglistBean> dyimglist) {
            this.dyimglist = dyimglist;
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
    }
}
