package com.haitaoit.pinpai.module.personPage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/19.
 */

public class CollectionObj {
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
         * id : 10
         * obj_img_url : http://linb.hai-tao.net/upload/201712/19/thumb_2_201712191351532984.png
         * obj_title : ①韩都衣舍韩版女装冬装新款羽绒服（每个ID限购5件）
         * address : 上海
         * obj_id : 43
         * obj_time : 2017-12-18
         * user_name : 125***.com
         * avatar : http://linb.hai-tao.net/upload/201712/15/17121514485856447712.jpg
         */
        public boolean isSelect;
        private String id;
        private String obj_img_url;
        private String obj_title;
        private String address;
        private String obj_id;
        private String obj_time;
        private String user_name;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getObj_img_url() {
            return obj_img_url;
        }

        public void setObj_img_url(String obj_img_url) {
            this.obj_img_url = obj_img_url;
        }

        public String getObj_title() {
            return obj_title;
        }

        public void setObj_title(String obj_title) {
            this.obj_title = obj_title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getObj_id() {
            return obj_id;
        }

        public void setObj_id(String obj_id) {
            this.obj_id = obj_id;
        }

        public String getObj_time() {
            return obj_time;
        }

        public void setObj_time(String obj_time) {
            this.obj_time = obj_time;
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
    }


}
