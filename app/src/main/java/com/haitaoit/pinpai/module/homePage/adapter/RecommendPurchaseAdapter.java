package com.haitaoit.pinpai.module.homePage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitaoit.pinpai.R;

import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class RecommendPurchaseAdapter extends RecyclerView.Adapter<RecommendPurchaseAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;

    private List<String> mDate;

    public RecommendPurchaseAdapter(Context context, List<String> lists) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDate = lists;

    }

    public RecommendPurchaseAdapter.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(RecommendPurchaseAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private RecommendPurchaseAdapter.OnItemClickListener onItemClickListener;

    @Override
    public RecommendPurchaseAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_purchase_adapter, viewGroup, false);

        return new RecommendPurchaseAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecommendPurchaseAdapter.ViewHolder holder, final int position) {

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }
}