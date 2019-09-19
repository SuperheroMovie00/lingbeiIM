package com.haitaoit.pinpai.module.personPage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.personPage.network.response.CollectionObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetMessagePinObj;
import com.haitaoit.pinpai.module.personPage.network.response.GetMessageUserObj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2017/6/1.
 */

public class UserMessageAdapter extends RecyclerView.Adapter<UserMessageAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;

    private List<GetMessageUserObj.ResponseBean> mDate;

    public UserMessageAdapter(Context context, List<GetMessageUserObj.ResponseBean> lists) {
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
        View v = inflater.inflate(R.layout.item_user_message_adapter, viewGroup, false);

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
        Glide.with(mContext).load(mDate.get(position).getAvatar()).error(R.mipmap.ic_launcher).into(holder.ivUserImage);

        holder.tvUserTime.setText(mDate.get(position).getAddtime());
        holder.tvUserTitle.setText(mDate.get(position).getUser_name());
        holder.tvUserContent.setText(mDate.get(position).getAddtime());
    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.iv_user_image)
        CircleImageView ivUserImage;
        @BindView(R.id.tv_user_title)
        TextView tvUserTitle;
        @BindView(R.id.tv_user_time)
        TextView tvUserTime;
        @BindView(R.id.tv_user_content)
        TextView tvUserContent;
        @BindView(R.id.iv_user_coll)
        ImageView ivUserColl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
