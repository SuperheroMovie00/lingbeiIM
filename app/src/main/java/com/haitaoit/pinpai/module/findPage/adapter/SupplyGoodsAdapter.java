package com.haitaoit.pinpai.module.findPage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyCheckBox;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.homePage.network.response.DateBeatJsonObj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class SupplyGoodsAdapter extends RecyclerView.Adapter<SupplyGoodsAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;

    private List<DateBeatJsonObj.ResponseBean> mDate;
    private String mMessageType;

    public SupplyGoodsAdapter(Context context, List<DateBeatJsonObj.ResponseBean> lists,String mMessageType) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDate = lists;
        this.mMessageType = mMessageType;

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
        View v = inflater.inflate(R.layout.item_supply_adapter, viewGroup, false);

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
        if (mDate.get(position).getDyimglist().size()>0){
            Glide.with(mContext).load(mDate.get(position).getDyimglist().get(0).getImg_url()).error(R.mipmap.ic_launcher).into(holder.ivCollectImg);
        }
      else {
            Glide.with(mContext).load(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.ivCollectImg);
        }
        holder.tvTitle.setText(mDate.get(position).getTitle());
        holder.tvTime.setText("发布时间: "+mDate.get(position).getAddtime());
        holder.tvName.setText(mDate.get(position).getUser_name());

        if (mMessageType.equals("1")){
            holder.tvQiuhuodi.setText("货源地："+mDate.get(position).getSource());
        }else{
            holder.tvQiuhuodi.setText("求购地："+mDate.get(position).getSource());
        }


        if (mDate.get(position).getIs_colllect().equals("1")) {
            holder.TvCollection.setCompoundDrawablesRelativeWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.img62),null, null, null);
            holder.TvCollection.setTextColor(mContext.getResources().getColor(R.color.gray_89));
        } else {
            holder.TvCollection.setCompoundDrawablesRelativeWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.img63), null, null, null);
            holder.TvCollection.setTextColor(mContext.getResources().getColor(R.color.red_ff_77));
        }

    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.CheckBox_defeult)
        MyCheckBox CheckBoxDefeult;
        @BindView(R.id.iv_collect_img)
        ImageView ivCollectImg;
        @BindView(R.id.tv_Title)
        TextView tvTitle;
        @BindView(R.id.tv_Time)
        TextView tvTime;
        @BindView(R.id.tv_Name)
        TextView tvName;
        @BindView(R.id.tv_Qiuhuodi)
        TextView tvQiuhuodi;
        @BindView(R.id.Tv_Collection)
        TextView TvCollection;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
