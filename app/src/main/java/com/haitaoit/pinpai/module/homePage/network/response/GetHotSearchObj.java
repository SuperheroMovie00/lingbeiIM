package com.haitaoit.pinpai.module.homePage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/13.
 */

public class GetHotSearchObj {

    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"Oid":7,"Title":" 你的完美主义倾"},{"Oid":2,"Title":"好美好美"},{"Oid":6,"Title":"热门搜索管理"},{"Oid":4,"Title":"冰果消消乐"},{"Oid":10,"Title":" 家长因孩子成绩差发短信道歉 班主任这样回复\u2026"},{"Oid":9,"Title":" 哈佛耶鲁普林斯顿究竟录取了哪些人"},{"Oid":1,"Title":" 可转债遇冷是其难逃的宿命"},{"Oid":3,"Title":"好漂亮好漂亮"},{"Oid":11,"Title":" 窦靖童出家？这件事和90后丧掉都不是真的"},{"Oid":8,"Title":" 艺术启蒙有个规律 父母一定得抓住"}]
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
         * Oid : 7
         * Title :  你的完美主义倾
         */

        private String Oid;
        private String Title;

        public String getOid() {
            return Oid;
        }

        public void setOid(String Oid) {
            this.Oid = Oid;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }
    }
}
