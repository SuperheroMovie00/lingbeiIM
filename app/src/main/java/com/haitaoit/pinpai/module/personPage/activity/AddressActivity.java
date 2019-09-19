package com.haitaoit.pinpai.module.personPage.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.customview.MyCheckBox;
import com.github.customview.MyTextView;
import com.haitaoit.pinpai.Config;
import com.haitaoit.pinpai.R;
import com.haitaoit.pinpai.base.BaseActivity;
import com.haitaoit.pinpai.base.MyCallBack;
import com.haitaoit.pinpai.base.ResponseObj;
import com.haitaoit.pinpai.module.personPage.network.ApiRequest;
import com.haitaoit.pinpai.module.personPage.network.Constant;
import com.haitaoit.pinpai.module.personPage.network.request.PostAddressUserItem;
import com.haitaoit.pinpai.module.personPage.network.request.PostUserInfoUpdatetItem;
import com.haitaoit.pinpai.module.personPage.network.response.GetAddressListObj;
import com.haitaoit.pinpai.tools.GetSign;
import com.haitaoit.pinpai.tools.PreferenceUtils;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxDataUtils;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/31.
 */

public class AddressActivity extends BaseActivity {
    @BindView(R.id.et_call_name)
    EditText etCallName;
    @BindView(R.id.et_call_phone)
    EditText etCallPhone;
    @BindView(R.id.tv_call_country)
    TextView tvCallCountry;
    @BindView(R.id.tv_call_address)
    TextView tvCallAddress;
    @BindView(R.id.et_call_zipcode)
    EditText etCallZipcode;
    @BindView(R.id.et_call_detail_address)
    EditText etCallDetailAddress;
    @BindView(R.id.cb_call_default)
    MyCheckBox cbCallDefault;
    @BindView(R.id.tv_sava)
    MyTextView tvSava;
    @BindView(R.id.rx_title)
    RxTitle rxTitle;

    private String CbDefault = "1";
    private String mCountryId;
    private String mCityId;
    public static int flag = 0;
    public static final int REQUEST_SHOWADDRESS = 0x023;
    public static final int REQUEST_SHOWADDCity = 0x024;
    List<GetAddressListObj.ResponseBean> mDataList=new ArrayList<>();
    private String mAddressId="";
    @Override
    protected int getContentView() {
        return R.layout.address_activity;
    }

    @Override
    protected void initView() {
        rxTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        if (getIntent().getStringExtra("ArddressId") != null) {
//            etCallName.setText(getIntent().getStringExtra("ArddressName"));
//            etCallPhone.setText(getIntent().getStringExtra("ArddressPhone"));
//            tvCallCountry.setText(getIntent().getStringExtra("ArddressCountry"));
//            tvCallAddress.setText(getIntent().getStringExtra("ArddressCity"));
//            etCallDetailAddress.setText(getIntent().getStringExtra("ArddressAddress"));
//            etCallZipcode.setText(getIntent().getStringExtra("ArddressZip"));
//            if ("1".equals(getIntent().getStringExtra("ArddressDefault"))) {
//                cbCallDefault.setChecked(false);
//            } else {
//                cbCallDefault.setChecked(true);
//            }

//        }
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));
        ApiRequest.GetAddress(map, new MyCallBack<GetAddressListObj>(mContext) {
            @Override
            public void onSuccessful(GetAddressListObj response) {
                if (response.getErrCode() == 0) {
                    mDataList.addAll( response.getResponse());
                    if (mDataList.size()>0){
                        for (int i = 0; i < mDataList.size(); i++) {
                            etCallName.setText(mDataList.get(0).getName());
                            etCallPhone.setText(mDataList.get(0).getMobile());
                            tvCallCountry.setText(mDataList.get(0).getCountry());
                            tvCallAddress.setText(mDataList.get(0).getCity());
                            etCallDetailAddress.setText(mDataList.get(0).getAddress());
                            etCallZipcode.setText(mDataList.get(0).getZipcode());
                            mAddressId = mDataList.get(0).getId();
                        }
                    }
                }
                else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }


    @OnClick({R.id.rx_title, R.id.tv_call_country, R.id.tv_call_address, R.id.cb_call_default, R.id.tv_sava})
    public void onViewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rx_title:
                break;
            case R.id.tv_call_country:
                intent = new Intent(mContext, CountryActivity.class);
                // 启动指定Activity并等待返回的结果，其中 REQUSET 是请求码，用于标识该请求
                startActivityForResult(intent, REQUEST_SHOWADDRESS);
                break;
            case R.id.tv_call_address:

                if (TextUtils.isEmpty(tvCallCountry.getText().toString())) {
                    showToastS("你选择的城市国家为空");
                    return;
                }
                intent = new Intent(mContext, CityActivity.class);
                intent.putExtra("mCountryId",mCountryId);
                // 启动指定Activity并等待返回的结果，其中 REQUSET 是请求码，用于标识该请求
                startActivityForResult(intent, REQUEST_SHOWADDCity);
                break;
            case R.id.cb_call_default:
                if (flag == 0) {
                    // 第一次单击触发的事件
                    flag = 1;
                    CbDefault = "1";
                    cbCallDefault.setChecked(true);
                } else {
                    // 第二次单击buttont改变触发的事件
                    flag = 0;
                    CbDefault = "2";
                    cbCallDefault.setChecked(false);
                }
                break;
            case R.id.tv_sava:
//
//                if (TextUtils.isEmpty(etCallName.getText().toString())) {
//                    showToastS("联系人不能为空");
//                    return;
//                }
//                if (TextUtils.isEmpty(etCallPhone.getText().toString())) {
//                    showToastS("联系电话不能为空");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(tvCallCountry.getText().toString())) {
//                    showToastS("所在国家不能为空");
//                    return;
//                }
//                if (TextUtils.isEmpty(tvCallAddress.getText().toString())) {
//                    showToastS("所在城市不能为空");
//                    return;
//                }
//                if (TextUtils.isEmpty(etCallZipcode.getText().toString())) {
//                    showToastS("邮政编码不能为空");
//                    return;
//                }
//                if (TextUtils.isEmpty(etCallDetailAddress.getText().toString())) {
//                    showToastS("详细地址不能为空");
//                    return;
//                }

                initPhone();
//

                break;
        }
    }


    private void initPhone() {
        Map<String, String> map = new HashMap<String, String>();

        if (!RxDataUtils.isNullString(mAddressId)) {
            map.put("address_id", mAddressId);
        }else {
            map.put("address_id", "0");
        }

        map.put("is_default", CbDefault + "");
        map.put("user_id", PreferenceUtils.getPrefString(mContext, Config.user_id, ""));
        map.put("sign", GetSign.getSign(map));

        PostAddressUserItem bean = new PostAddressUserItem();
        bean.setName(etCallName.getText().toString());
        bean.setMobile(etCallPhone.getText().toString());
//        bean.setCountry(mCountryId);
//        bean.setCity(mCityId);
        bean.setCountry(tvCallCountry.getText().toString());
        bean.setCity(tvCallAddress.getText().toString());
        bean.setZipcode(etCallZipcode.getText().toString());
        bean.setAddress(etCallDetailAddress.getText().toString());

        ApiRequest.PostAddressEdit(map, bean, new MyCallBack<ResponseObj>(mContext) {
            @Override
            public void onSuccessful(ResponseObj response) {
                if (response.getErrCode() == 0) {
                    showToastS(response.getErrMsg());
                    finish();
                } else {
                    RxToast.normal(response.getErrMsg());
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == REQUEST_SHOWADDRESS && resultCode == RESULT_OK) {
                    tvCallCountry.setText(data.getStringExtra("mCountry"));
                    mCountryId = data.getStringExtra("mCountryId");
                }

                if (requestCode == REQUEST_SHOWADDCity && resultCode == RESULT_OK) {
                    tvCallAddress.setText(data.getStringExtra("mINameCity"));
                    mCityId = data.getStringExtra("mCountryId");
                }
            }
        }


    }
}
