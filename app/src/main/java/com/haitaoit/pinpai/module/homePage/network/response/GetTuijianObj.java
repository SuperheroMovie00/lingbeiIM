package com.haitaoit.pinpai.module.homePage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/23.
 */

public class GetTuijianObj
{
    /**
     * ErrCode : 0
     * ErrMsg :
     * Response : [{"id":2,"user_name":"125***.com","avatar":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201641148463.png"}]
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
         * id : 2
         * user_name : 125***.com
         * avatar : http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201641148463.png
         */

        private String id;
        private String user_name;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
