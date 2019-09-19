package com.haitaoit.pinpai.module.personPage.network.request;

/**
 * Created by Administrator on 2017/11/25.
 */

public class PostUserInfoUpdatetItem {

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    /**
     * img_url :
     */

    private String field;


    public PostUserInfoUpdatetItem(String img_url) {
        this.field = img_url;
    }

    public PostUserInfoUpdatetItem() {
    }
}
