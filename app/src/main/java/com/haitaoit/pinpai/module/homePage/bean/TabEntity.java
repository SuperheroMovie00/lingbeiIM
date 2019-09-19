package com.haitaoit.pinpai.module.homePage.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by Administrator on 2017/3/31.
 */

public class TabEntity implements CustomTabEntity {
    private String title;
    private int selectIcon;
    private int unSelectIcon;

    public TabEntity(String title, int selectIcon, int unSelectIcon) {
        this.title = title;
        this.selectIcon = selectIcon;
        this.unSelectIcon = unSelectIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectIcon;
    }
}
