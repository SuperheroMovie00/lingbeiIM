package com.haitaoit.pinpai.module.personPage.network.request;

import java.util.List;

/**
 * Created by LZY on 2017/12/21.
 */

public class PostNeedShareItem {

    public String getIs_price() {
        return is_price;
    }

    public void setIs_price(String is_price) {
        this.is_price = is_price;
    }

    public String getIs_num() {
        return is_num;
    }

    public void setIs_num(String is_num) {
        this.is_num = is_num;
    }

    private String  is_price;
    private String  is_num;

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
