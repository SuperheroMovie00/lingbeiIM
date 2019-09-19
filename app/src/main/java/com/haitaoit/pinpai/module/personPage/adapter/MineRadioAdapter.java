package com.haitaoit.pinpai.module.personPage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.customview.MyCheckBox;
import com.github.customview.MyLinearLayout;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.callback.OnItemListener;
import com.haitaoit.pinpai.module.personPage.bean.ItemModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by guohao on 2017/9/6.
 */

public class MineRadioAdapter extends RecyclerView.Adapter<MineRadioAdapter.MyViewHolder> {


    private Context mContext;
    private List<ItemModel> mDataList;
    private LayoutInflater mInflater;
    private OnItemListener mOnItemListener;

    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;

    public MineRadioAdapter(Context context, List<ItemModel> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
        mInflater = LayoutInflater.from(context);
    }


    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }


    public MineRadioAdapter.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(MineRadioAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private MineRadioAdapter.OnItemClickListener onItemClickListener;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mInflater.inflate(R.layout.item_attention_adapter, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(rootView);
        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ItemModel item = mDataList.get(position);

        if (mEditMode == MYLIVE_MODE_CHECK) {
            holder.CheckBoxDefeult.setVisibility(View.GONE);
        } else {
            holder.CheckBoxDefeult.setVisibility(View.VISIBLE);
        }


        holder.CheckBoxDefeult.setChecked(item.isSelect);
        holder.CheckBoxDefeult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.isSelect = holder.CheckBoxDefeult.isChecked();
//                mOnItemListener.checkBoxClick(position);
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

    }



    /**
     * ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.CheckBox_defeult)
        MyCheckBox CheckBoxDefeult;
        @BindView(R.id.iv_person_image)
        CircleImageView ivPersonImage;
        @BindView(R.id.ll_Change)
        MyLinearLayout llChange;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void remove(ItemModel itemModel) {
        mDataList.remove(itemModel);
    }

    public ItemModel getItem(int pos) {
        return mDataList.get(pos);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
