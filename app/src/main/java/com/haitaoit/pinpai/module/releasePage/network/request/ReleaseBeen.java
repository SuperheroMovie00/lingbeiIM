package com.haitaoit.pinpai.module.releasePage.network.request;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by LZY on 2017/12/18.
 */

public class ReleaseBeen {
    private int id = -1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TextView getTv_QiDingNum() {
        return tv_QiDingNum;
    }

    public void setTv_QiDingNum(TextView tv_QiDingNum) {
        this.tv_QiDingNum = tv_QiDingNum;
    }

    TextView tv_QiDingNum;

    public TextView getTv_QiDingID() {
        return tv_QiDingID;
    }

    public void setTv_QiDingID(TextView tv_QiDingID) {
        this.tv_QiDingID = tv_QiDingID;
    }

    TextView tv_QiDingID;


    public EditText getTv_QiDingPrice() {
        return tv_QiDingPrice;
    }

    public void setTv_QiDingPrice(EditText tv_QiDingPrice) {
        this.tv_QiDingPrice = tv_QiDingPrice;
    }

    EditText tv_QiDingPrice;
}
