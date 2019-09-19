package com.haitaoit.pinpai.module.personPage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/28.
 */

public class GetMessageUserObj
{
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"id":1,"user_id":2,"user_name":"125***.com","avatar":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201641148463.png","addtime":"16:35","is_read":null}]
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
         * user_id : 2
         * user_name : 125***.com
         * avatar : http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201641148463.png
         * addtime : 16:35
         * is_read : null
         */

        private String id;
        private String user_id;
        private String user_name;
        private String avatar;
        private String addtime;
        private Object is_read;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public Object getIs_read() {
            return is_read;
        }

        public void setIs_read(Object is_read) {
            this.is_read = is_read;
        }
    }
}
