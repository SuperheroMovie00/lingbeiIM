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
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.personPage.network.response.GetUserGooddListObj;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class MySupplyAdapter extends RecyclerView.Adapter<MySupplyAdapter.MyViewHolder> {



    private Context mContext;
    private List<GetUserGooddListObj.ResponseBean> mDataList;
    private LayoutInflater mInflater;

    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;

    public List<String> getSelectPos() {
        return selectPos;
    }

    public void setSelectPos(List<String> selectPos) {
        this.selectPos = selectPos;
    }

    private List<String> selectPos = new ArrayList<>();
    private boolean isSelectAll;

    public interface CartEventCallback {
        void checkAll(boolean flag);
    }

    public MySupplyAdapter(Context context, List<GetUserGooddListObj.ResponseBean> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
        mInflater = LayoutInflater.from(context);
    }


    private CartEventCallback callback;

    public void setCallback(CartEventCallback callback) {
        this.callback = callback;
    }

    public void setSelectAll(boolean selectAll) {
        isSelectAll = selectAll;
        selectPos.clear();
        if (selectAll) {
            for (int i = 0; i < mDataList.size(); i++) {
                selectPos.add(i + "");
            }
        }

    }

    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mInflater.inflate(R.layout.item_supply_adapter, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(rootView);
        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (isSelectAll) {
            holder.CheckBoxDefeult.setChecked(true);
        } else {
            holder.CheckBoxDefeult.setChecked(false);
        }
        if (mEditMode == MYLIVE_MODE_CHECK) {
            holder.CheckBoxDefeult.setVisibility(View.GONE);
        } else {
            holder.CheckBoxDefeult.setVisibility(View.VISIBLE);
        }
        holder.CheckBoxDefeult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.CheckBoxDefeult.isChecked()) {
                    selectPos.add(position + "");
                } else {
                    selectPos.remove(position + "");
                }
                //当选中位置集合的长度等于列表总长度，代表全选
                if (selectPos.size() == mDataList.size()) {
                    if (callback != null) {
                        callback.checkAll(true);
                    }
                } else {
                    callback.checkAll(false);
                }

            }
        });

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }

        if (mDataList.get(position).getDyimglist().size() > 0) {
            Glide.with(mContext).load(mDataList.get(position).getDyimglist().get(0).getImg_url()).error(R.mipmap.ic_launcher).into(holder.ivCollectImg);
        } else {
            Glide.with(mContext).load(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.ivCollectImg);
        }
        holder.tvTitle.setText(mDataList.get(position).getTitle());
        holder.tvName.setText(mDataList.get(position).getUser_name());
        holder.tvTime.setText("发布时间："+mDataList.get(position).getAddtime());
        holder.tvQiuhuodi.setText("货源地："+mDataList.get(position).getSource());



        if (mDataList.get(position).getIs_colllect().equals("1")) {
            holder.TvCollection.setCompoundDrawablesRelativeWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.img62),null, null, null);
            holder.TvCollection.setTextColor(mContext.getResources().getColor(R.color.gray_89));
        } else {
            holder.TvCollection.setCompoundDrawablesRelativeWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.img63), null, null, null);
            holder.TvCollection.setTextColor(mContext.getResources().getColor(R.color.red_ff_77));
        }
    }


    /**
     * ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

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


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
