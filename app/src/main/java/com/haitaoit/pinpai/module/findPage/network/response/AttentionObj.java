package com.haitaoit.pinpai.module.findPage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/20.
 */

public class AttentionObj {


    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"user_id":25,"id":27,"user_name":"qy测试高","avatar":"http://linb.hai-tao.net/upload/201801/04/18010411310460316870.jpg","addtime":"15:45"}]
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
         * user_id : 25
         * id : 27
         * user_name : qy测试高
         * avatar : http://linb.hai-tao.net/upload/201801/04/18010411310460316870.jpg
         * addtime : 15:45
         */

        private String user_id;
        private String id;
        private String user_name;
        private String avatar;
        private String addtime;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
