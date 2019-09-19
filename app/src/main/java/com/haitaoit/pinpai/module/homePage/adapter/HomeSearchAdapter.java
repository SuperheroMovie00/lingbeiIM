package com.haitaoit.pinpai.module.homePage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.homePage.network.response.GetHistoryListObj;
import com.haitaoit.pinpai.module.homePage.network.response.GetHotSearchObj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class HomeSearchAdapter extends RecyclerView.Adapter<HomeSearchAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;

    private List<GetHistoryListObj.ResponseBean> mbeanList;

    public HomeSearchAdapter(Context context, List<GetHistoryListObj.ResponseBean> mbeanList) {
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
        View v = inflater.inflate(R.layout.item_home_search_adapter, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.tvRemarkName.setText(mbeanList.get(position).getTitle());
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
        return mbeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_RemarkName)
        TextView tvRemarkName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
