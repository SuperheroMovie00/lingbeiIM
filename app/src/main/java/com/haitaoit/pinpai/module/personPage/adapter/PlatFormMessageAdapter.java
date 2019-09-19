package com.haitaoit.pinpai.module.personPage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyCheckBox;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.personPage.network.response.GetMessagePinObj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class PlatFormMessageAdapter extends RecyclerView.Adapter<PlatFormMessageAdapter.ViewHolder> {



    private LayoutInflater inflater;
    private Context mContext;

    private List<GetMessagePinObj.ResponseBean> mDate;

    public PlatFormMessageAdapter(Context context, List<GetMessagePinObj.ResponseBean> lists) {
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
        View v = inflater.inflate(R.layout.item_message_adapter, viewGroup, false);

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
        Glide.with(mContext).load(mDate.get(position).getGoods_img()).error(R.mipmap.ic_launcher).into(holder.ivCollectImg);
        holder.tvDateTime.setText(mDate.get(position).getAddtime());
        holder.tvTitle.setText(mDate.get(position).getTitle());
        holder.tvName.setText(mDate.get(position).getUser_name());
        holder.tvTime.setText("发布时间：" + mDate.get(position).getAddtime());

        if (mDate.get(position).getType().equals("1")) {
            holder.tvQiuhuodi.setText("求购地：" + mDate.get(position).getSource());
        } else if (mDate.get(position).getType().equals("2")) {
            holder.tvQiuhuodi.setText("货源地：" + mDate.get(position).getSource());
        } else {
            holder.tvQiuhuodi.setVisibility(View.GONE);
            holder.ivCollectImg.setVisibility(View.GONE);
            holder.tvName.setVisibility(View.GONE);
            holder.tvTime.setVisibility(View.GONE);
            holder.tvRemark.setVisibility(View.GONE);
            holder.tvTitle.setPadding(10, 10, 10, 10);

        }


    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_dateTime)
        MyTextView tvDateTime;
        @BindView(R.id.tv_Title)
        TextView tvTitle;
        @BindView(R.id.CheckBox_defeult)
        MyCheckBox CheckBoxDefeult;
        @BindView(R.id.iv_collect_img)
        ImageView ivCollectImg;
        @BindView(R.id.tv_Remark)
        TextView tvRemark;
        @BindView(R.id.tv_Time)
        TextView tvTime;
        @BindView(R.id.tv_Qiuhuodi)
        TextView tvQiuhuodi;
        @BindView(R.id.tv_Name)
        TextView tvName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
