package com.haitaoit.pinpai.module.homePage.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyCheckBox;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.homePage.network.response.DateBeatJsonObj;
import com.haitaoit.pinpai.view.MyImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class BeatDateAdapter extends RecyclerView.Adapter<BeatDateAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;

    private List<DateBeatJsonObj.ResponseBean> mDate;

    public BeatDateAdapter(Context context, List<DateBeatJsonObj.ResponseBean> lists) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDate = lists;

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
        View v = inflater.inflate(R.layout.item_beat_adapter, viewGroup, false);

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
        if (mDate.get(position).getDyimglist().size() > 0) {
            Glide.with(mContext).load(mDate.get(position).getDyimglist().get(0).getImg_url()).error(R.mipmap.ic_launcher).into(holder.ivHotImg);
        } else {
            Glide.with(mContext).load(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.ivHotImg);
        }
        holder.tvTitle.setText(mDate.get(position).getTitle());
        holder.tvTime.setText("发布时间: " + mDate.get(position).getAddtime());
        holder.tvAddress.setText(mDate.get(position).getUser_name());
        holder.tvAddress.setText("货源地：" + mDate.get(position).getSource());


        switch (position) {
            case 0:
                Glide.with(mContext).load(R.mipmap.nub_1).error(R.mipmap.nub_1).into(holder.tvPosition);
                break;
            case 1:
                Glide.with(mContext).load(R.mipmap.nub_2).error(R.mipmap.nub_2).into(holder.tvPosition);
                break;
            case 2:
                Glide.with(mContext).load(R.mipmap.nub_3).error(R.mipmap.nub_3).into(holder.tvPosition);
                break;
            case 3:
                Glide.with(mContext).load(R.mipmap.nub_4).error(R.mipmap.nub_4).into(holder.tvPosition);
                break;
            default:
             holder.tvPosition.setVisibility(View.GONE);
                break;
        }

//        if (mDate.get(position).getIs_colllect().equals("1")) {
//            holder.TvCollection.setCompoundDrawablesRelativeWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.img62), null, null, null);
//            holder.TvCollection.setTextColor(mContext.getResources().getColor(R.color.gray_89));
//        } else {
//            holder.TvCollection.setCompoundDrawablesRelativeWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.img63), null, null, null);
//            holder.TvCollection.setTextColor(mContext.getResources().getColor(R.color.red_ff_77));
//        }

    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot_img)
        MyImageView ivHotImg;
        @BindView(R.id.tv_Position)
        ImageView tvPosition;
        @BindView(R.id.tv_Title)
        TextView tvTitle;
        @BindView(R.id.tv_Time)
        TextView tvTime;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.ll_addRemark)
        MyLinearLayout llAddRemark;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}