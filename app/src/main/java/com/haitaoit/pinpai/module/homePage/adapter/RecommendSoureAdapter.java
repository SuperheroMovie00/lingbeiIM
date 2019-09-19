package com.haitaoit.pinpai.module.homePage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyLinearLayout;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.homePage.network.response.GetBannerObj;
import com.haitaoit.pinpai.view.MyImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class RecommendSoureAdapter extends RecyclerView.Adapter<RecommendSoureAdapter.ViewHolder> {



    private LayoutInflater inflater;
    private Context mContext;

    private List<GetBannerObj.ResponseBean.DyadroollistBean> mbeanList;

    public RecommendSoureAdapter(Context context, List<GetBannerObj.ResponseBean.DyadroollistBean> mbeanList) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mbeanList = mbeanList;

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
        View v = inflater.inflate(R.layout.item_recomment_adapter, viewGroup, false);

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
        Glide.with(mContext).load(mbeanList.get(position).getImg_url()).error(R.mipmap.ic_launcher).into(holder.ivHotImg);
        holder.tvRemarkName.setText(mbeanList.get(position).getRemarks());
        holder.tvRemarkCate.setText(mbeanList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mbeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot_img)
        MyImageView ivHotImg;
        @BindView(R.id.tv_RemarkName)
        TextView tvRemarkName;
        @BindView(R.id.tv_RemarkCate)
        TextView tvRemarkCate;
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