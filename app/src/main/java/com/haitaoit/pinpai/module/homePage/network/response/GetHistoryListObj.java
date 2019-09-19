package com.haitaoit.pinpai.module.homePage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/14.
 */

public class GetHistoryListObj {
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"title":"1111"},{"title":"可转债遇冷是其难逃的宿命"},{"title":"1"},{"title":"冰果消消乐"},{"title":"风格还回家"},{"title":"ggh"},{"title":"111"}]
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
         * title : 1111
         */

        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
