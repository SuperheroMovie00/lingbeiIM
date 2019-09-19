package com.haitaoit.pinpai.module.findPage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.personPage.network.response.GetNeedDetailObj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class GridViewBuyAdapter extends RecyclerView.Adapter<GridViewBuyAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;

    private List<GetNeedDetailObj.ResponseBean.DypricelistBean> mDate;
    private String mPricesType;

    public GridViewBuyAdapter(Context context, List<GetNeedDetailObj.ResponseBean.DypricelistBean> lists, String mPricesType) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDate = lists;
        this.mPricesType = mPricesType;

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
        View v = inflater.inflate(R.layout.activity_index_gridview_buy_add, viewGroup, false);

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

        holder.tvQiNum1.setText(mDate.get(position).getQuantity() + "个起批");
        holder.tvQiPrces1.setText(mDate.get(position).getPrice() + "元");


    }

    @Override
    public int getItemCount() {
        return mDate.size();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_QiPrces1)
        TextView tvQiPrces1;
        @BindView(R.id.tv_QiNum1)
        TextView tvQiNum1;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
