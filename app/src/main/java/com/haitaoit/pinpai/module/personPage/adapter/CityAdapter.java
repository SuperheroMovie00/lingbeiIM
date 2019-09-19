package com.haitaoit.pinpai.module.personPage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.personPage.network.response.GetCityObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetCountryListObj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;

    private List<GetCityObj.ResponseBean> mDate;

    public CityAdapter(Context context, List<GetCityObj.ResponseBean> lists) {
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
        View v = inflater.inflate(R.layout.item_country_adapter, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvCountryItem.setText(mDate.get(position).getTitle());
        holder.tvRemark.setVisibility(View.GONE);
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
        @BindView(R.id.tv_Country_item)
        TextView tvCountryItem;
        @BindView(R.id.tv_Remark)
        TextView tvRemark;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
