package com.haitaoit.pinpai.module.personPage.network.request;

import java.util.List;

/**
 * Created by LZY on 2017/12/21.
 */

public class PostCollectDeleteItem {

    private List<IdlistBean> idlist;

    public List<IdlistBean> getIdlist() {
        return idlist;
    }

    public void setIdlist(List<IdlistBean> idlist) {
        this.idlist = idlist;
    }

    public static class IdlistBean {
        /**
         * id : 1
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
