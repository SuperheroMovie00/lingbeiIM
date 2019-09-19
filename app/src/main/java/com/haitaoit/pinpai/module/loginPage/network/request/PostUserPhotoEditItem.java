package com.haitaoit.pinpai.module.loginPage.network.request;

/**
 * Created by Administrator on 2017/11/25.
 */

public class PostUserPhotoEditItem {

    /**
     * img_url :
     */

    private String img_url;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public PostUserPhotoEditItem(String img_url) {
        this.img_url = img_url;
    }

    public PostUserPhotoEditItem() {
    }
}
