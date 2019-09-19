package com.haitaoit.pinpai.module.releasePage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/23.
 */

public class GetBandClassObj
{

    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"dycaregorylist":[{"parent_id":0,"id":4,"title":"老年装"},{"parent_id":0,"id":6,"title":"妈妈装衣服"},{"parent_id":0,"id":7,"title":"儿童"},{"parent_id":0,"id":8,"title":"少女"},{"parent_id":0,"id":5,"title":"青少年装"},{"parent_id":0,"id":39,"title":"情侣"}]}
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
        private List<DycaregorylistBean> dycaregorylist;

        public List<DycaregorylistBean> getDycaregorylist() {
            return dycaregorylist;
        }

        public void setDycaregorylist(List<DycaregorylistBean> dycaregorylist) {
            this.dycaregorylist = dycaregorylist;
        }

        public static class DycaregorylistBean {
            /**
             * parent_id : 0
             * id : 4
             * title : 老年装
             */

            private String parent_id;
            private String id;
            private String title;

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
        }
    }
}
