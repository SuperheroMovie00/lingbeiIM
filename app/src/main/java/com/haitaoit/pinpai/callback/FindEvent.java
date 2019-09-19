package com.haitaoit.pinpai.callback;

import java.io.Serializable;

/**
 * Created by LZY on 2017/8/14.
 */

public class FindEvent implements Serializable {
    public  int index;
    public String id;

    public FindEvent(int index, String id) {
        this.index = index;
        this.id = id;
    }
}
