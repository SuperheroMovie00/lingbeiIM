package com.haitaoit.pinpai.callback;

import android.view.View;

/**
 * Created by Song on 2016/9/7.
 */
public interface OnItemListener {
    void checkBoxClick(int position);

    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}
