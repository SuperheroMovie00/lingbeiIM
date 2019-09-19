package com.haitaoit.pinpai.module.personPage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/28.
 */

public class GetMessagePinObj {


    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"id":1,"type":1,"title":"33","goods_id":1,"goods_title":"33","goods_img":"/upload/201712/28/thumb_1_201712281010486442.png","source":"33","addtime":"2018-01-16  10:59","is_read":1,"user_name":"李媛媛586！"},{"id":2,"type":2,"title":"蓝猫","goods_id":2,"goods_title":"蓝猫","goods_img":"/upload/201801/06/18010613345213066512.jpg","source":"上海市上海市黄浦区","addtime":"2018-01-16  11:02","is_read":1,"user_name":"李媛媛586！"},{"id":17,"type":1,"title":"33","goods_id":17,"goods_title":"33","goods_img":"/upload/201712/28/thumb_1_201712281010486442.png","source":"33","addtime":"2018-01-18  13:32","is_read":1,"user_name":"李媛媛586！"}]
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
         * type : 1
         * title : 33
         * goods_id : 1
         * goods_title : 33
         * goods_img : /upload/201712/28/thumb_1_201712281010486442.png
         * source : 33
         * addtime : 2018-01-16  10:59
         * is_read : 1
         * user_name : 李媛媛586！
         */

        private String id;
        private String type;
        private String title;
        private String goods_id;
        private String goods_title;
        private String goods_img;
        private String source;
        private String addtime;
        private String is_read;
        private String user_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
