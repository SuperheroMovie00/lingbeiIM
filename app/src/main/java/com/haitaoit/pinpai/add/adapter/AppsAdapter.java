package com.haitaoit.pinpai.add.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.jpush.im.android.eventbus.EventBus;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.ExitApplication;
import com.haitaoit.pinpai.add.model.AppBean;
import com.haitaoit.pinpai.utils.event.ImageEvent;

public class AppsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<AppBean> mDdata = new ArrayList<AppBean>();

    public AppsAdapter(Context context, ArrayList<AppBean> data) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        if (data != null) {
            this.mDdata = data;
        }
    }

    @Override
    public int getCount() {
        return mDdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mDdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_app, null);
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final AppBean appBean = mDdata.get(position);
        if (appBean != null) {
            viewHolder.iv_icon.setBackgroundResource(appBean.getIcon());
            viewHolder.tv_name.setText(appBean.getFuncName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CAMERA}, 1);
                    } else {
                        if (appBean.getFuncName().equals("相册")) {
                            EventBus.getDefault().post(new ImageEvent(ExitApplication.IMAGE_MESSAGE));
                        } else if (appBean.getFuncName().equals("拍照")) {
                            EventBus.getDefault().post(new ImageEvent(ExitApplication.TAKE_PHOTO_MESSAGE));
                        } else if (appBean.getFuncName().equals("位置")) {
                            EventBus.getDefault().post(new ImageEvent(ExitApplication.TAKE_LOCATION));
                        } else if (appBean.getFuncName().equals("文件")) {
                            EventBus.getDefault().post(new ImageEvent(ExitApplication.FILE_MESSAGE));
                        } else if (appBean.getFuncName().equals("视频")) {
                            EventBus.getDefault().post(new ImageEvent(ExitApplication.TACK_VIDEO));
                        } else if (appBean.getFuncName().equals("语音")) {
                            EventBus.getDefault().post(new ImageEvent(ExitApplication.TACK_VOICE));
                        } else if (appBean.getFuncName().equals("名片")) {
                            EventBus.getDefault().post(new ImageEvent(ExitApplication.BUSINESS_CARD));
                        }
                    }

                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView iv_icon;
        public TextView tv_name;
    }
}