package com.haitaoit.pinpai.module.homePage.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.callback.FragmentEvent;
import com.haitaoit.pinpai.module.findPage.network.response.TuiJianHuoYuanObj;
import com.haitaoit.pinpai.view.MyImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class RecommendSoureSecondAdapter extends RecyclerView.Adapter<RecommendSoureSecondAdapter.ViewHolder> {



    private LayoutInflater inflater;
    private Context mContext;

    private List<TuiJianHuoYuanObj.ResponseBean> mDyimglist;
    private String mtype;


    public RecommendSoureSecondAdapter(Context context, List<TuiJianHuoYuanObj.ResponseBean> lists,String mtype ) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDyimglist = lists;
        this.mtype = mtype;

    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_recomment_second_adapter, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });

        }

        if (mDyimglist.get(position).getDyimglist().size() > 0) {
            Glide.with(mContext).load(mDyimglist.get(position).getDyimglist().get(0).getImg_url()).error(R.mipmap.ic_launcher).into(holder.ivHotImg);
        } else {
            Glide.with(mContext).load(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.ivHotImg);
        }

        Log.e("mDyimglist.size()mDyimglist.size()mDyimglist.size()mDyimglist.size()mDyimglist.size()",mDyimglist.size()+"");
        if (mDyimglist.size()>3) {
            if (position ==3){
                holder.llMore.setVisibility(View.VISIBLE);
                holder.tvPriceNum.setText("");

            }else {
                holder.llMore.setVisibility(View.GONE);
                holder.tvPriceNum.setText(mDyimglist.get(position).getTitle());
            }
        } else {
           holder.llMore.setVisibility(View.GONE);
            holder.tvPriceNum.setText(mDyimglist.get(position).getTitle());
        }

        holder.llMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mtype.equals("1")){
                    EventBus.getDefault().post(new FragmentEvent(0,"1"));
                    Log.e("1111111111EventBusEventBusEventBus","EventBusEventBusEventBus");
                }else {
                    EventBus.getDefault().post(new FragmentEvent(0,"2"));
                    Log.e("22222222222222EventBusEventBusEventBus","EventBusEventBusEventBus");
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return mDyimglist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_hot_img)
        MyImageView ivHotImg;
        @BindView(R.id.ll_More)
        LinearLayout llMore;
        @BindView(R.id.tv_PriceNum)
        TextView tvPriceNum;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }
}