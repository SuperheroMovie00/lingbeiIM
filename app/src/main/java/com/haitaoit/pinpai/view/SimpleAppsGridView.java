package com.haitaoit.pinpai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.add.adapter.AppsAdapter;
import com.haitaoit.pinpai.add.model.AppBean;

public class SimpleAppsGridView extends RelativeLayout {

    protected View view;

    public SimpleAppsGridView(Context context) {
        this(context, null);
    }

    public SimpleAppsGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_apps, this);
        init();
    }

    protected void init() {
        GridView gv_apps = (GridView) view.findViewById(R.id.gv_apps);
        ArrayList<AppBean> mAppBeanList = new ArrayList<>();
        mAppBeanList.add(new AppBean(R.mipmap.img_add_01, "相册"));
        mAppBeanList.add(new AppBean(R.mipmap.img_add_02, "拍照"));
//        mAppBeanList.add(new AppBean(R.mipmap.icon_file, "文件"));
//        mAppBeanList.add(new AppBean(R.mipmap.icon_loaction, "位置"));
//        mAppBeanList.add(new AppBean(R.mipmap.businesscard, "名片"));
//        mAppBeanList.add(new AppBean(R.mipmap.icon_audio, "视频"));
//        mAppBeanList.add(new AppBean(R.mipmap.icon_voice, "语音"));
        AppsAdapter adapter = new AppsAdapter(getContext(), mAppBeanList);
        gv_apps.setAdapter(adapter);
    }
}
