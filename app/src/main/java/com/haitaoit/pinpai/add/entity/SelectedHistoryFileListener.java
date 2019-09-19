package com.haitaoit.pinpai.add.entity;


public interface SelectedHistoryFileListener {
    void onSelected(int msgId, int position);

    void onUnselected(int msgId, int position);
}
