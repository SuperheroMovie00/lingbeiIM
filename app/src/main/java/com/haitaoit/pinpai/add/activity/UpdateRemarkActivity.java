package com.haitaoit.pinpai.add.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.activeandroid.query.Update;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.add.database.FriendEntry;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.Constant;
import com.haitaoit.pinpai.module.personPage.network.request.PostUserInfoUpdatetItem;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.haitaoit.pinpai.utils.ToastUtil;
import com.haitaoit.pinpai.utils.pinyin.HanyuPinyin;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by Administrator on 2017/10/27.
 * 修改手机号码
 */

public class UpdateRemarkActivity extends BaseActivity {
    @BindView(R.id.rx_title)
    RxTitle rxTitle;
    @BindView(R.id.ed_mess)
    MyEditText edMess;
    @BindView(R.id.tv_next)
    MyTextView tvNext;

    @Override
    protected int getContentView() {
        return R.layout.update_remark_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edMess.setText(getIntent().getStringExtra("userWeChat"));
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.rx_title, R.id.tv_next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_next:
                if (TextUtils.isEmpty(edMess.getText().toString())) {
                    showToastS("微信号不能为空");
                    return;
                }
                initPhone(edMess.getText().toString());

                break;
        }
    }

    private void initPhone(String file) {

//                    JMessageClient.getUserInfo(userName, new GetUserInfoCallback() {
//                        @Override
//                        public void gotResult(int responseCode, String responseMessage, final UserInfo info) {
//                            if (responseCode == 0) {
//                                info.updateNoteName(name, new BasicCallback() {
//                                    @Override
//                                    public void gotResult(int responseCode, String responseMessage) {
//                                        if (responseCode == 0) {
//                                            Intent intent = new Intent();
//                                            intent.putExtra("updateName", name);
//                                            setResult(1, intent);
//                                            ToastUtil.shortToast(SetNoteNameActivity.this, "更新成功");
//                                            //更新备注名时候同时更新数据库中的名字和letter
//                                            new Update(FriendEntry.class).set("DisplayName=?", name).where("Username=?", userName).execute();
//                                            new Update(FriendEntry.class).set("NoteName=?", name).where("Username=?", userName).execute();
//                                            String newNote = HanyuPinyin.getInstance().getStringPinYin(name.substring(0, 1));
//                                            new Update(FriendEntry.class).set("Letter=?", newNote.toUpperCase()).where("Username=?", userName).execute();
//                                            finish();
//                                        } else {
//                                            ToastUtil.shortToast(SetNoteNameActivity.this, "更新失败" + responseMessage);
//                                        }
//                                    }
//                                });
//                            }
//                        }
//                    });
//                }
//            });
//        }
    }

}

