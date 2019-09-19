package com.haitaoit.pinpai.module.findPage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.homePage.network.response.GetGoodsDetailJson;
import com.haitaoit.pinpai.view.MyImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;

    private List<GetGoodsDetailJson.ResponseBean.DypricelistBean> mDate;
    private String mPricesType;

    public GridViewAdapter(Context context, List<GetGoodsDetailJson.ResponseBean.DypricelistBean> lists, String mPricesType) {
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
        View v = inflater.inflate(R.layout.activity_index_gridview_add, viewGroup, false);

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

        holder.tvType2.setText(mDate.get(position).getQuantity() + "个起批");
        holder.tvType3.setText(mDate.get(position).getPrice() + "元");

//        Log.e("===========个起批个起批个起批个起批========", mPricesType);
//        if (mPricesType.equals("1")) {
//            holder.tvType3.setVisibility(View.VISIBLE);
//        } else {
//            holder.tvType3.setVisibility(View.GONE);
//        }

    }

    @Override
    public int getItemCount() {
        return mDate.size();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_Type2)
        TextView tvType2;
        @BindView(R.id.tv_Type3)
        TextView tvType3;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
