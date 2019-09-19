package com.haitaoit.pinpai.module.personPage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/14.
 */

public class GetCountryListObj
{
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"dycaregorylist":[{"id":1,"title":"中国"},{"id":2,"title":"日本"}]}
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
             * id : 1
             * title : 中国
             */

            private String id;
            private String title;

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
