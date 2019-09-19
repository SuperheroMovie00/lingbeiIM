package com.haitaoit.pinpai.module.personPage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.customview.MyCheckBox;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.findPage.network.response.AttentionObj;
import com.haitaoit.pinpai.module.personPage.bean.ItemModel;
import com.haitaoit.pinpai.tools.BackCall;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2017/6/1.
 */

public class MyAttentionAdapter extends RecyclerView.Adapter<MyAttentionAdapter.MyViewHolder> {


    private Context mContext;
    private List<AttentionObj.ResponseBean> mDataList;
    private LayoutInflater mInflater;

    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;
    private BackCall backCall;
    public void setBackCall(BackCall backCall) {
        this.backCall = backCall;
    }
    public List<String> getSelectPos() {
        return selectPos;
    }

    public void setSelectPos(List<String> selectPos) {
        this.selectPos = selectPos;
    }

    private List<String> selectPos = new ArrayList<>();
    private boolean isSelectAll;

    public MyAttentionAdapter(Context context, List<AttentionObj.ResponseBean> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
        mInflater = LayoutInflater.from(context);
    }
    public interface CartEventCallback {
        void checkAll(boolean flag);
    }
    private MyAttentionAdapter.CartEventCallback callback;

    public void setCallback(MyAttentionAdapter.CartEventCallback callback) {
        this.callback = callback;
    }

    public void setSelectAll(boolean selectAll) {
        isSelectAll = selectAll;
        selectPos.clear();
        if (selectAll) {
            for (int i = 0; i < mDataList.size(); i++) {
                selectPos.add(i+"");
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
        final AttentionObj.ResponseBean item = mDataList.get(position);

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
                    selectPos.add(position+"");
                } else {
                    selectPos.remove(position+"");
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

        Glide.with(mContext).load(mDataList.get(position).getAvatar()).error(R.mipmap.ic_launcher).into(holder.ivPersonImage);
        holder.tvName.setText(mDataList.get(position).getUser_name());
        holder.tvTime.setText(mDataList.get(position).getAddtime());

        holder.tvRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (backCall != null) {
                    backCall.deal(R.id.tv_Remark,position);
                }
            }
        });

    }


    /**
     * ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.CheckBox_defeult)
        MyCheckBox CheckBoxDefeult;
        @BindView(R.id.iv_person_image)
        CircleImageView ivPersonImage;
        @BindView(R.id.tv_Name)
        TextView tvName;
        @BindView(R.id.tv_Time)
        TextView tvTime;
        @BindView(R.id.tv_Remark)
        MyTextView tvRemark;
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

    public AttentionObj.ResponseBean getItem(int pos) {
        return mDataList.get(pos);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
