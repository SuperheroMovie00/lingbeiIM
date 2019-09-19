package com.haitaoit.pinpai.module.releasePage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/23.
 */

public class GetCateObj
{

    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"parent_id":7,"id":"37","title":"加绒打底裤","img_url":"http://linb.hai-tao.net"},{"parent_id":7,"id":"36","title":"加绒皮裤","img_url":"http://linb.hai-tao.net"},{"parent_id":7,"id":"28","title":"羊羔毛","img_url":"http://linb.hai-tao.net"},{"parent_id":7,"id":"27","title":"皮草外套","img_url":"http://linb.hai-tao.net"},{"parent_id":7,"id":"26","title":"皮毛一体","img_url":"http://linb.hai-tao.net"}]
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
         * parent_id : 7
         * id : 37
         * title : 加绒打底裤
         * img_url : http://linb.hai-tao.net
         */

        private String parent_id;
        private String id;
        private String title;
        private String img_url;

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
