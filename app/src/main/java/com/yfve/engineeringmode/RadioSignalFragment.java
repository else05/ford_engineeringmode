package com.yfve.engineeringmode;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import com.baidu.media.radio.lib.RadioInfoManager;
import com.baidu.media.radio.lib.RadioSeekCallBack;
import com.baidu.media.radio.lib.RadioSignal;
import com.baidu.media.radio.lib.ServiceConnectionCallback;
import com.baidu.media.radio.lib.SignalInfoCallback;
import com.yfve.tools.LU;

/* loaded from: classes1.dex */
public class RadioSignalFragment extends BaseFragment {
    private RadioButton mcbRdioAm;
    private RadioButton mcbRdioFm;
    private TextView mtvRadioLeft;
    private TextView mtvRadioRifht;
    private TextView mtvRadioSignal;
    private TextView mtvRadioValue;
    private Handler mHandler = null;
    private int miAmFm = -1;
    private int miCurrentFrequency = -1;
    private int miCurrentAm = 531;
    private int miCurrentFm = 87500;
    private RadioSignal mRadioSignal = null;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.RadioSignalFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cb_radio_am /* 2131230764 */:
                    RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).openRadioBand(0);
                    RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).tuner(RadioSignalFragment.this.miCurrentAm, 0);
                    RadioSignalFragment.this.miAmFm = RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).getCurrentRadioBand();
                    RadioSignalFragment.this.miCurrentFrequency = RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).getCurrentFrequency();
                    if (RadioSignalFragment.this.miCurrentFrequency < 76000) {
                        RadioSignalFragment.this.miCurrentAm = RadioSignalFragment.this.miCurrentFrequency;
                        TextView textView = RadioSignalFragment.this.mtvRadioValue;
                        textView.setText(RadioSignalFragment.this.miCurrentFrequency + "  KHZ");
                        return;
                    }
                    RadioSignalFragment.this.miCurrentFm = RadioSignalFragment.this.miCurrentFrequency;
                    TextView textView2 = RadioSignalFragment.this.mtvRadioValue;
                    textView2.setText((RadioSignalFragment.this.miCurrentFrequency / 1000.0f) + "  MHZ");
                    return;
                case R.id.cb_radio_fm /* 2131230765 */:
                    RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).openRadioBand(1);
                    RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).tuner(RadioSignalFragment.this.miCurrentFm, 0);
                    RadioSignalFragment.this.miAmFm = RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).getCurrentRadioBand();
                    RadioSignalFragment.this.miCurrentFrequency = RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).getCurrentFrequency();
                    if (RadioSignalFragment.this.miCurrentFrequency < 76000) {
                        RadioSignalFragment.this.miCurrentAm = RadioSignalFragment.this.miCurrentFrequency;
                        TextView textView3 = RadioSignalFragment.this.mtvRadioValue;
                        textView3.setText(RadioSignalFragment.this.miCurrentFrequency + "  KHZ");
                        return;
                    }
                    RadioSignalFragment.this.miCurrentFm = RadioSignalFragment.this.miCurrentFrequency;
                    TextView textView4 = RadioSignalFragment.this.mtvRadioValue;
                    textView4.setText((RadioSignalFragment.this.miCurrentFrequency / 1000.0f) + "  MHZ");
                    return;
                case R.id.tv_radio_left /* 2131230961 */:
                    RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).seek(RadioSignalFragment.this.miAmFm, 1, RadioSignalFragment.this.mRadioSeekCallBack);
                    return;
                case R.id.tv_radio_right /* 2131230962 */:
                    RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).seek(RadioSignalFragment.this.miAmFm, 0, RadioSignalFragment.this.mRadioSeekCallBack);
                    return;
                default:
                    return;
            }
        }
    };
    private ServiceConnectionCallback mServiceConnectionCallback = new ServiceConnectionCallback() { // from class: com.yfve.engineeringmode.RadioSignalFragment.3
        @Override // com.baidu.media.radio.lib.ServiceConnectionCallback
        public void onServiceConnected() {
            RadioSignalFragment.this.miAmFm = RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).getCurrentRadioBand();
            RadioSignalFragment.this.miCurrentFrequency = RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).getCurrentFrequency();
            String str = RadioSignalFragment.this.tag;
            LU.e(str, "onServiceConnected()  +++++    miAmFm==" + RadioSignalFragment.this.miAmFm + "   miCurrentFrequency==" + RadioSignalFragment.this.miCurrentFrequency);
            if (RadioSignalFragment.this.miAmFm == 0) {
                RadioSignalFragment.this.mcbRdioAm.setChecked(true);
                RadioSignalFragment.this.mcbRdioFm.setChecked(false);
            } else if (1 == RadioSignalFragment.this.miAmFm) {
                RadioSignalFragment.this.mcbRdioFm.setChecked(true);
                RadioSignalFragment.this.mcbRdioAm.setChecked(false);
            }
            if (RadioSignalFragment.this.miCurrentFrequency < 76000) {
                if (RadioSignalFragment.this.miCurrentFrequency <= 0) {
                    RadioSignalFragment.this.mtvRadioValue.setText("--");
                    return;
                }
                RadioSignalFragment.this.miCurrentAm = RadioSignalFragment.this.miCurrentFrequency;
                TextView textView = RadioSignalFragment.this.mtvRadioValue;
                textView.setText(RadioSignalFragment.this.miCurrentFrequency + "  KHZ");
                return;
            }
            RadioSignalFragment.this.miCurrentFm = RadioSignalFragment.this.miCurrentFrequency;
            TextView textView2 = RadioSignalFragment.this.mtvRadioValue;
            textView2.setText((RadioSignalFragment.this.miCurrentFrequency / 1000.0f) + "  MHZ");
        }

        @Override // com.baidu.media.radio.lib.ServiceConnectionCallback
        public void onServiceDisconnected() {
            LU.e(RadioSignalFragment.this.tag, "onServiceDisconnected()  -----");
        }
    };
    private SignalInfoCallback mSignalInfoCallback = new SignalInfoCallback() { // from class: com.yfve.engineeringmode.RadioSignalFragment.4
        @Override // com.baidu.media.radio.lib.SignalInfoCallback
        public void onSignalQualityChange(RadioSignal radioSignal) {
            RadioSignalFragment.this.mRadioSignal = radioSignal;
            RadioSignalFragment.this.mHandler.sendEmptyMessage(100);
        }
    };
    private RadioSeekCallBack mRadioSeekCallBack = new RadioSeekCallBack() { // from class: com.yfve.engineeringmode.RadioSignalFragment.5
        @Override // com.baidu.media.radio.lib.RadioSeekCallBack
        public void onSeek(int iValue) {
            RadioSignalFragment.this.miCurrentFrequency = iValue;
            if (iValue < 76000) {
                TextView textView = RadioSignalFragment.this.mtvRadioValue;
                textView.setText(iValue + "  KHZ");
                return;
            }
            TextView textView2 = RadioSignalFragment.this.mtvRadioValue;
            textView2.setText((iValue / 1000.0f) + "  MHZ");
        }

        @Override // com.baidu.media.radio.lib.RadioSeekCallBack
        public void onFinish(int iValue) {
            String str = RadioSignalFragment.this.tag;
            LU.i(str, "onFinish()  iValue ==" + iValue + "  miAmFm==" + RadioSignalFragment.this.miAmFm);
            RadioSignalFragment.this.miCurrentFrequency = iValue;
            if (iValue < 76000) {
                RadioSignalFragment.this.miCurrentAm = iValue;
                TextView textView = RadioSignalFragment.this.mtvRadioValue;
                textView.setText(iValue + "  KHZ");
            } else {
                RadioSignalFragment.this.miCurrentFm = iValue;
                TextView textView2 = RadioSignalFragment.this.mtvRadioValue;
                textView2.setText((iValue / 1000.0f) + "  MHZ");
            }
            RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).updateSeekUi(RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).getCurrentRadioBand(), iValue);
        }
    };

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_radio_signal, (ViewGroup) null);
        initView(view);
        this.mHandler = new Handler() { // from class: com.yfve.engineeringmode.RadioSignalFragment.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (RadioSignalFragment.this.mRadioSignal != null) {
                    TextView textView = RadioSignalFragment.this.mtvRadioSignal;
                    textView.setText(RadioSignalFragment.this.mRadioSignal.getSignalStrength() + "dbuv");
                }
                RadioSignalFragment.this.miAmFm = RadioInfoManager.getInstance(RadioSignalFragment.this.mMainActivity.getApplicationContext()).getCurrentRadioBand();
                if (RadioSignalFragment.this.miAmFm == 0) {
                    RadioSignalFragment.this.mcbRdioAm.setChecked(true);
                    RadioSignalFragment.this.mcbRdioFm.setChecked(false);
                } else if (1 == RadioSignalFragment.this.miAmFm) {
                    RadioSignalFragment.this.mcbRdioFm.setChecked(true);
                    RadioSignalFragment.this.mcbRdioAm.setChecked(false);
                }
            }
        };
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).init();
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).onServiceConnectCallback(this.mServiceConnectionCallback);
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).setSignalInfoCallback(this.mSignalInfoCallback);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_radio_signal));
    }

    private void initView(View view) {
        this.mtvRadioValue = (TextView) view.findViewById(R.id.tv_curr_radio_value);
        this.mtvRadioSignal = (TextView) view.findViewById(R.id.tv_radio_ss);
        this.mtvRadioLeft = (TextView) view.findViewById(R.id.tv_radio_left);
        this.mtvRadioRifht = (TextView) view.findViewById(R.id.tv_radio_right);
        this.mcbRdioFm = (RadioButton) view.findViewById(R.id.cb_radio_fm);
        this.mcbRdioAm = (RadioButton) view.findViewById(R.id.cb_radio_am);
        this.mtvRadioLeft.setOnClickListener(this.mOnClickListener);
        this.mtvRadioRifht.setOnClickListener(this.mOnClickListener);
        this.mcbRdioFm.setOnClickListener(this.mOnClickListener);
        this.mcbRdioAm.setOnClickListener(this.mOnClickListener);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).setSignalInfoCallback(null);
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).unbindService();
        LU.e(this.tag, "onDestroy()  +++++   ");
    }
}
