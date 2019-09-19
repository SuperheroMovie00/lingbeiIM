package com.haitaoit.pinpai.module.findPage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.findPage.activity.ScreenActivity;
import com.haitaoit.pinpai.module.findPage.network.response.GetGoodsCategoryObj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2017/6/1.
 */

public class ScreenAdapter extends RecyclerView.Adapter<ScreenAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;
    private List<GetGoodsCategoryObj.ResponseBean.DybrandlistBean> mDate;
    private int currentCheckedItemPosition=0;

    public int getCurrentCheckedItemPosition() {
        return currentCheckedItemPosition;
    }

    public void setCurrentCheckedItemPosition(int currentCheckedItemPosition) {
        this.currentCheckedItemPosition = currentCheckedItemPosition;
    }




    public void setDefaultCheckedItemPosition(int position) {
        currentCheckedItemPosition = position;
    }

    public int getCheckedItemPosition() {
        return currentCheckedItemPosition;
    }

    public void check(int position) {
        setDefaultCheckedItemPosition(position);
        notifyDataSetChanged();
    }

    public ScreenAdapter(Context context, List<GetGoodsCategoryObj.ResponseBean.DybrandlistBean> lists) {
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
        View v = inflater.inflate(R.layout.item_screen_adapter, viewGroup, false);

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

//        if (currentCheckedItemPosition == position) {
//            Log.e("currentCheckedItemPosition",currentCheckedItemPosition+"");
//            holder.llKuang.setBackgroundColor(mContext.getResources().getColor(R.color.c_white));
//        } else {
//            holder.llKuang.setBackgroundColor(mContext.getResources().getColor(R.color.white));
//        }

            Glide.with(mContext).load(mDate.get(position).getImg_url()).error(R.mipmap.ic_launcher).into(holder.ivHotImg);
        holder.tvBrandName.setText(mDate.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_hot_img)
        ImageView ivHotImg;
        @BindView(R.id.tv_BrandName)
        TextView tvBrandName;
        @BindView(R.id.ll_Kuang)
        LinearLayout llKuang;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
