package com.haitaoit.pinpai.module.personPage.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.customview.MyCheckBox;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.module.homePage.activity.BeatDataActivity;
import com.haitaoit.pinpai.module.personPage.activity.AddressActivity;
import com.haitaoit.pinpai.module.personPage.network.response.GetAddressListObj;
import com.vondear.rxtools.RxActivityUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/1.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder> {



    private Context mContext;
    private List<GetAddressListObj.ResponseBean> mDataList;
    private LayoutInflater mInflater;


    public AddressListAdapter(Context context, List<GetAddressListObj.ResponseBean> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
        mInflater = LayoutInflater.from(context);
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
        View rootView = mInflater.inflate(R.layout.item_address_adapyer, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(rootView);
        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.TvAddressName.setText(mDataList.get(position).getName());
        holder.TvAddressPhone.setText(mDataList.get(position).getMobile());
        holder.TvAddress.setText(mDataList.get(position).getCountry()+mDataList.get(position).getCity()+mDataList.get(position).getAddress());

        if ("1".equals(mDataList.get(position).getIs_default())) {
            holder.CheckBoxDefeult.setChecked(false);
        } else {
            holder.CheckBoxDefeult.setChecked(true);
        }


        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });

        }

        holder.TvAddressEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle mBundle = new Bundle();
                mBundle.putString("ArddressId", mDataList.get(position).getId());
                mBundle.putString("ArddressName",  mDataList.get(position).getName());
                mBundle.putString("ArddressPhone", mDataList.get(position).getMobile());
                mBundle.putString("ArddressCity", mDataList.get(position).getCity());
                mBundle.putString("ArddressCountry", mDataList.get(position).getCountry());
                mBundle.putString("ArddressAddress", mDataList.get(position).getAddress());
                mBundle.putString("ArddressZip", mDataList.get(position).getZipcode());
                mBundle.putString("ArddressDefault", mDataList.get(position).getIs_default());
                RxActivityUtils.skipActivity(mContext, AddressActivity.class, mBundle);
            }
        });

    }


    /**
     * ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.Tv_AddressName)
        TextView TvAddressName;
        @BindView(R.id.Tv_AddressPhone)
        TextView TvAddressPhone;
        @BindView(R.id.Tv_Address)
        TextView TvAddress;
        @BindView(R.id.CheckBox_defeult)
        MyCheckBox CheckBoxDefeult;
        @BindView(R.id.Tv_Address_Edit)
        TextView TvAddressEdit;
        @BindView(R.id.Tv_Address_Delete)
        TextView TvAddressDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

}
