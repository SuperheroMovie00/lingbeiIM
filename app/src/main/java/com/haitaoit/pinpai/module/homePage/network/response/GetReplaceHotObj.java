package com.haitaoit.pinpai.module.homePage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/13.
 */

public class GetReplaceHotObj {

    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"dyadroollist":[{"good_id":3,"img_url":"http://chanxi.hai-tao.net/upload/201712/13/201712131417255602.png","title":"4555","remarks":"#"}]}
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
        private List<DyadroollistBean> dyadroollist;

        public List<DyadroollistBean> getDyadroollist() {
            return dyadroollist;
        }

        public void setDyadroollist(List<DyadroollistBean> dyadroollist) {
            this.dyadroollist = dyadroollist;
        }

        public static class DyadroollistBean {
            /**
             * good_id : 3
             * img_url : http://chanxi.hai-tao.net/upload/201712/13/201712131417255602.png
             * title : 4555
             * remarks : #
             */

            private String good_id;
            private String img_url;
            private String title;
            private String remarks;

            public String getGood_id() {
                return good_id;
            }

            public void setGood_id(String good_id) {
                this.good_id = good_id;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }
        }
    }
}
