package com.haitaoit.pinpai.tools;

/**
 * Created by 赵航 on 2017/9/21.
 */

public class NameValuePairSign {
    private String name;
    private String value;

    public NameValuePairSign(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
