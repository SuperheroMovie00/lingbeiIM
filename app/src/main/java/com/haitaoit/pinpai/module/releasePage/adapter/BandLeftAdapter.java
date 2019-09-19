package com.haitaoit.pinpai.module.releasePage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.releasePage.network.response.GetBandClassObj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class BandLeftAdapter extends RecyclerView.Adapter<BandLeftAdapter.ViewHolder> {

    private int selectedPos = 0;
    private LayoutInflater inflater;
    private Context mContext;

    private List<GetBandClassObj.ResponseBean.DycaregorylistBean> mDate;

    public BandLeftAdapter(Context context, List<GetBandClassObj.ResponseBean.DycaregorylistBean> lists) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDate = lists;

    }

    public void setSelectedPos(int selectedPos) {
        Log.d("========", "selectedPos===" + selectedPos);
        this.selectedPos = selectedPos;
    }

    public int getSelectedPos() {
        return selectedPos;
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
        View v = inflater.inflate(R.layout.item_brand_adapter, viewGroup, false);

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
        holder.tvTitle.setText(mDate.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }
}