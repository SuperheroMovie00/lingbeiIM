package com.haitaoit.pinpai.module.personPage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/14.
 */

public class GetCityObj
{
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"id":"3","title":"上海"},{"id":"4","title":"北京"},{"id":"5","title":"深圳"},{"id":"6","title":"广东"},{"id":"7","title":"重庆"}]
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
         * id : 3
         * title : 上海
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
