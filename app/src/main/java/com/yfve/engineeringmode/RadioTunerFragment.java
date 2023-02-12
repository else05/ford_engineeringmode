package com.yfve.engineeringmode;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.media.radio.lib.RadioInfoManager;
import com.baidu.media.radio.lib.RadioSignal;
import com.baidu.media.radio.lib.ServiceConnectionCallback;
import com.baidu.media.radio.lib.SignalInfoCallback;
import com.yfve.tools.LU;

/* loaded from: classes1.dex */
public class RadioTunerFragment extends BaseFragment {
    private LinearLayout mllTuner2;
    private TextView mtvAMorFM;
    private TextView mtvDownC;
    private TextView mtvDownL;
    private TextView mtvDownR;
    private TextView mtvReset;
    private TextView mtvTunerAgc;
    private TextView mtvTunerBandWidth;
    private TextView mtvTunerModulation;
    private TextView mtvTunerOffSet;
    private TextView mtvTunerQuality;
    private TextView mtvTunerSs;
    private TextView mtvTunerStereo;
    private TextView mtvTunerUsn;
    private TextView mtvTunerWam;
    private TextView mtvUpC;
    private TextView mtvUpL;
    private TextView mtvUpR;
    private TextView mtvUpdate;
    private TextView mtvValueC;
    private TextView mtvValueL;
    private TextView mtvValueR;
    private int[] mintsKey = new int[100];
    private int miAmFm = -1;
    private int miIndex = -1;
    private int miValueLeft = -1;
    private int miValueRight = -1;
    private boolean mbIsUiShow = false;
    private Handler mHandler = null;
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.RadioTunerFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            int id = v.getId();
            if (id != R.id.tv_reset_a) {
                switch (id) {
                    case R.id.tv_down_center_a /* 2131230939 */:
                        if (RadioTunerFragment.this.miValueLeft == 0) {
                            RadioTunerFragment.this.miValueLeft = 15;
                            String strmiValueLeft = Integer.toHexString(RadioTunerFragment.this.miValueLeft);
                            RadioTunerFragment.this.mtvValueC.setText(strmiValueLeft);
                            return;
                        } else if (RadioTunerFragment.this.miValueLeft > 0 && 15 >= RadioTunerFragment.this.miValueLeft) {
                            RadioTunerFragment.this.miValueLeft--;
                            String strmiValueLeft2 = Integer.toHexString(RadioTunerFragment.this.miValueLeft);
                            RadioTunerFragment.this.mtvValueC.setText(strmiValueLeft2);
                            return;
                        } else {
                            return;
                        }
                    case R.id.tv_down_left_a /* 2131230940 */:
                        if (RadioTunerFragment.this.miIndex == 0) {
                            RadioTunerFragment.this.miIndex = 35;
                            RadioTunerFragment.this.setTextmtvValueL(RadioTunerFragment.this.miIndex);
                            RadioTunerFragment.this.setTextValue(RadioTunerFragment.this.mintsKey[RadioTunerFragment.this.miIndex]);
                            return;
                        } else if (RadioTunerFragment.this.miIndex > 0 && 35 >= RadioTunerFragment.this.miIndex) {
                            RadioTunerFragment.this.miIndex--;
                            RadioTunerFragment.this.setTextmtvValueL(RadioTunerFragment.this.miIndex);
                            RadioTunerFragment.this.setTextValue(RadioTunerFragment.this.mintsKey[RadioTunerFragment.this.miIndex]);
                            return;
                        } else {
                            return;
                        }
                    case R.id.tv_down_right_a /* 2131230941 */:
                        if (RadioTunerFragment.this.miValueRight == 0) {
                            RadioTunerFragment.this.miValueRight = 15;
                            String strmiValueLeft3 = Integer.toHexString(RadioTunerFragment.this.miValueRight);
                            RadioTunerFragment.this.mtvValueR.setText(strmiValueLeft3);
                            return;
                        } else if (RadioTunerFragment.this.miValueRight > 0 && 15 >= RadioTunerFragment.this.miValueRight) {
                            RadioTunerFragment.this.miValueRight--;
                            String strmiValueLeft4 = Integer.toHexString(RadioTunerFragment.this.miValueRight);
                            RadioTunerFragment.this.mtvValueR.setText(strmiValueLeft4);
                            return;
                        } else {
                            return;
                        }
                    default:
                        switch (id) {
                            case R.id.tv_up_center_a /* 2131231014 */:
                                if (15 == RadioTunerFragment.this.miValueLeft) {
                                    RadioTunerFragment.this.miValueLeft = 0;
                                    String strHexmiValueLeft = Integer.toHexString(RadioTunerFragment.this.miValueLeft);
                                    RadioTunerFragment.this.mtvValueC.setText(strHexmiValueLeft);
                                    return;
                                } else if (RadioTunerFragment.this.miValueLeft >= 0 && 15 > RadioTunerFragment.this.miValueLeft) {
                                    RadioTunerFragment.this.miValueLeft++;
                                    String strHexmiValueLeft2 = Integer.toHexString(RadioTunerFragment.this.miValueLeft);
                                    RadioTunerFragment.this.mtvValueC.setText(strHexmiValueLeft2);
                                    return;
                                } else {
                                    return;
                                }
                            case R.id.tv_up_left_a /* 2131231015 */:
                                if (35 == RadioTunerFragment.this.miIndex) {
                                    RadioTunerFragment.this.miIndex = 0;
                                    RadioTunerFragment.this.setTextmtvValueL(RadioTunerFragment.this.miIndex);
                                    RadioTunerFragment.this.setTextValue(RadioTunerFragment.this.mintsKey[RadioTunerFragment.this.miIndex]);
                                    return;
                                } else if (RadioTunerFragment.this.miIndex >= 0 && 35 > RadioTunerFragment.this.miIndex) {
                                    RadioTunerFragment.this.miIndex++;
                                    RadioTunerFragment.this.setTextmtvValueL(RadioTunerFragment.this.miIndex);
                                    RadioTunerFragment.this.setTextValue(RadioTunerFragment.this.mintsKey[RadioTunerFragment.this.miIndex]);
                                    return;
                                } else {
                                    return;
                                }
                            case R.id.tv_up_right_a /* 2131231016 */:
                                if (15 == RadioTunerFragment.this.miValueRight) {
                                    RadioTunerFragment.this.miValueRight = 0;
                                    String strHexmiValueLeft3 = Integer.toHexString(RadioTunerFragment.this.miValueRight);
                                    RadioTunerFragment.this.mtvValueR.setText(strHexmiValueLeft3);
                                    return;
                                } else if (RadioTunerFragment.this.miValueRight >= 0 && 15 > RadioTunerFragment.this.miValueRight) {
                                    RadioTunerFragment.this.miValueRight++;
                                    String strHexmiValueLeft4 = Integer.toHexString(RadioTunerFragment.this.miValueRight);
                                    RadioTunerFragment.this.mtvValueR.setText(strHexmiValueLeft4);
                                    return;
                                } else {
                                    return;
                                }
                            case R.id.tv_update_a /* 2131231017 */:
                                int miValue = (RadioTunerFragment.this.miValueLeft * 16) + RadioTunerFragment.this.miValueRight;
                                LU.w(RadioTunerFragment.this.tag, "onClick()++ miIndex==" + RadioTunerFragment.this.miIndex + "   iValue==" + miValue);
                                RadioTunerFragment.this.mintsKey[RadioTunerFragment.this.miIndex] = miValue;
                                RadioInfoManager.getInstance(RadioTunerFragment.this.mMainActivity.getApplicationContext()).setPara(0, RadioTunerFragment.this.mintsKey);
                                return;
                            default:
                                return;
                        }
                }
            }
            RadioInfoManager.getInstance(RadioTunerFragment.this.mMainActivity.getApplicationContext()).setPara(0, RadioTunerFragment.this.mintsKey);
        }
    };
    private ServiceConnectionCallback mServiceConnectionCallback = new ServiceConnectionCallback() { // from class: com.yfve.engineeringmode.RadioTunerFragment.3
        @Override // com.baidu.media.radio.lib.ServiceConnectionCallback
        public void onServiceConnected() {
            LU.e(RadioTunerFragment.this.tag, "onServiceConnected()  +++++ ");
            try {
                RadioTunerFragment.this.mintsKey = RadioInfoManager.getInstance(RadioTunerFragment.this.mMainActivity.getApplicationContext()).getPara(0, 36);
                RadioTunerFragment.this.miAmFm = RadioInfoManager.getInstance(RadioTunerFragment.this.mMainActivity.getApplicationContext()).getCurrentRadioBand();
                if (RadioTunerFragment.this.miAmFm == 0) {
                    RadioTunerFragment.this.mtvAMorFM.setText("AM");
                } else if (1 == RadioTunerFragment.this.miAmFm) {
                    RadioTunerFragment.this.mtvAMorFM.setText("FM");
                } else {
                    RadioTunerFragment.this.mtvAMorFM.setText("");
                }
                String str = RadioTunerFragment.this.tag;
                LU.e(str, "mintsKey  ==" + RadioTunerFragment.this.mintsKey.length);
                for (int i = 0; i < RadioTunerFragment.this.mintsKey.length; i++) {
                    String str2 = RadioTunerFragment.this.tag;
                    LU.e(str2, "mintsKey " + i + "  ==" + RadioTunerFragment.this.mintsKey[i]);
                }
                if (36 == RadioTunerFragment.this.mintsKey.length) {
                    RadioTunerFragment.this.miIndex = 3;
                    if (RadioTunerFragment.this.mbIsUiShow) {
                        RadioTunerFragment.this.setTextmtvValueL(RadioTunerFragment.this.miIndex);
                        RadioTunerFragment.this.setTextValue(RadioTunerFragment.this.mintsKey[RadioTunerFragment.this.miIndex]);
                        RadioTunerFragment.this.mbIsUiShow = false;
                    }
                }
            } catch (Exception e) {
                String str3 = RadioTunerFragment.this.tag;
                LU.e(str3, "onServiceConnected()  Exception ==" + e.toString());
            }
        }

        @Override // com.baidu.media.radio.lib.ServiceConnectionCallback
        public void onServiceDisconnected() {
            LU.e(RadioTunerFragment.this.tag, "onServiceDisconnected()  -----");
        }
    };
    RadioSignal mRadioSignal = null;
    private SignalInfoCallback mSignalInfoCallback = new SignalInfoCallback() { // from class: com.yfve.engineeringmode.RadioTunerFragment.4
        @Override // com.baidu.media.radio.lib.SignalInfoCallback
        public void onSignalQualityChange(RadioSignal radioSignal) {
            RadioTunerFragment.this.mRadioSignal = radioSignal;
            RadioTunerFragment.this.mHandler.sendEmptyMessage(100);
        }
    };

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_radio_tuner, (ViewGroup) null);
        this.mllTuner2 = (LinearLayout) view.findViewById(R.id.ll_tuner2);
        this.mllTuner2.setVisibility(8);
        this.mtvUpL = (TextView) view.findViewById(R.id.tv_up_left_a);
        this.mtvUpC = (TextView) view.findViewById(R.id.tv_up_center_a);
        this.mtvUpR = (TextView) view.findViewById(R.id.tv_up_right_a);
        this.mtvDownL = (TextView) view.findViewById(R.id.tv_down_left_a);
        this.mtvDownC = (TextView) view.findViewById(R.id.tv_down_center_a);
        this.mtvDownR = (TextView) view.findViewById(R.id.tv_down_right_a);
        this.mtvValueL = (TextView) view.findViewById(R.id.tv_value_left_a);
        this.mtvValueC = (TextView) view.findViewById(R.id.tv_value_center_a);
        this.mtvValueR = (TextView) view.findViewById(R.id.tv_value_right_a);
        this.mtvUpdate = (TextView) view.findViewById(R.id.tv_update_a);
        this.mtvReset = (TextView) view.findViewById(R.id.tv_reset_a);
        this.mtvAMorFM = (TextView) view.findViewById(R.id.tv_am_or_fm);
        this.mtvTunerSs = (TextView) view.findViewById(R.id.tv_tuner_ss);
        this.mtvTunerUsn = (TextView) view.findViewById(R.id.tv_tuner_usn);
        this.mtvTunerWam = (TextView) view.findViewById(R.id.tv_tuner_wam);
        this.mtvTunerOffSet = (TextView) view.findViewById(R.id.tv_tuner_offset);
        this.mtvTunerBandWidth = (TextView) view.findViewById(R.id.tv_tuner_bw);
        this.mtvTunerModulation = (TextView) view.findViewById(R.id.tv_tuner_modulation);
        this.mtvTunerQuality = (TextView) view.findViewById(R.id.tv_tuner_quality);
        this.mtvTunerStereo = (TextView) view.findViewById(R.id.tv_tuner_stereo);
        this.mtvTunerAgc = (TextView) view.findViewById(R.id.tv_tuner_agc);
        this.mHandler = new Handler() { // from class: com.yfve.engineeringmode.RadioTunerFragment.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (RadioTunerFragment.this.mRadioSignal != null) {
                    TextView textView = RadioTunerFragment.this.mtvTunerSs;
                    textView.setText("" + RadioTunerFragment.this.mRadioSignal.getSignalStrength());
                    TextView textView2 = RadioTunerFragment.this.mtvTunerUsn;
                    textView2.setText("" + RadioTunerFragment.this.mRadioSignal.getUsn());
                    TextView textView3 = RadioTunerFragment.this.mtvTunerWam;
                    textView3.setText("" + RadioTunerFragment.this.mRadioSignal.getWam());
                    TextView textView4 = RadioTunerFragment.this.mtvTunerOffSet;
                    textView4.setText("" + RadioTunerFragment.this.mRadioSignal.getOffset());
                    TextView textView5 = RadioTunerFragment.this.mtvTunerBandWidth;
                    textView5.setText("" + RadioTunerFragment.this.mRadioSignal.getBandwidth());
                    TextView textView6 = RadioTunerFragment.this.mtvTunerModulation;
                    textView6.setText("" + RadioTunerFragment.this.mRadioSignal.getModulation());
                    TextView textView7 = RadioTunerFragment.this.mtvTunerQuality;
                    textView7.setText("" + RadioTunerFragment.this.mRadioSignal.getQuality());
                    TextView textView8 = RadioTunerFragment.this.mtvTunerStereo;
                    textView8.setText("" + RadioTunerFragment.this.mRadioSignal.isStereo());
                    TextView textView9 = RadioTunerFragment.this.mtvTunerAgc;
                    textView9.setText("" + RadioTunerFragment.this.mRadioSignal.getAgc());
                }
                RadioTunerFragment.this.miAmFm = RadioInfoManager.getInstance(RadioTunerFragment.this.mMainActivity.getApplicationContext()).getCurrentRadioBand();
                if (RadioTunerFragment.this.miAmFm == 0) {
                    RadioTunerFragment.this.mtvAMorFM.setText("AM");
                } else if (1 == RadioTunerFragment.this.miAmFm) {
                    RadioTunerFragment.this.mtvAMorFM.setText("FM");
                } else {
                    RadioTunerFragment.this.mtvAMorFM.setText("");
                }
            }
        };
        this.mtvUpL.setOnClickListener(this.mbtOnClickListener);
        this.mtvUpC.setOnClickListener(this.mbtOnClickListener);
        this.mtvUpR.setOnClickListener(this.mbtOnClickListener);
        this.mtvDownL.setOnClickListener(this.mbtOnClickListener);
        this.mtvDownC.setOnClickListener(this.mbtOnClickListener);
        this.mtvDownR.setOnClickListener(this.mbtOnClickListener);
        this.mtvUpdate.setOnClickListener(this.mbtOnClickListener);
        this.mtvReset.setOnClickListener(this.mbtOnClickListener);
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).init();
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).onServiceConnectCallback(this.mServiceConnectionCallback);
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).setSignalInfoCallback(this.mSignalInfoCallback);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_radio));
        this.mbIsUiShow = true;
        this.mintsKey = RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).getPara(0, 36);
        if (this.mintsKey != null) {
            LU.e(this.tag, "onResume()  +++++   mintsKey.length==" + this.mintsKey.length);
            if (36 == this.mintsKey.length) {
                for (int i = 0; i < this.mintsKey.length; i++) {
                    LU.e(this.tag, "onResume()  mintsKey " + i + "  ==" + this.mintsKey[i]);
                }
                this.miIndex = 3;
                setTextmtvValueL(this.miIndex);
                setTextValue(this.mintsKey[this.miIndex]);
                this.mbIsUiShow = false;
            }
        }
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
        LU.e(this.tag, "onStop()  +++++   ");
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).setSignalInfoCallback(null);
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).unbindService();
        LU.e(this.tag, "onDestroy()  +++++   ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextmtvValueL(int iValueL) {
        String strValueL;
        switch (iValueL) {
            case 32:
                strValueL = "LEV";
                break;
            case 33:
                strValueL = "WAM";
                break;
            case 34:
                strValueL = "USN";
                break;
            case 35:
                strValueL = "FOF";
                break;
            default:
                strValueL = Integer.toHexString(iValueL);
                break;
        }
        this.mtvValueL.setText(strValueL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextValue(int iValue) {
        this.miValueLeft = iValue / 16;
        this.miValueRight = iValue % 16;
        String strHexValueL = Integer.toHexString(this.miValueLeft);
        String strHexValueR = Integer.toHexString(this.miValueRight);
        String str = this.tag;
        LU.w(str, "setTextValue()++ iValue==" + iValue + "  strHexValueL==" + strHexValueL + "  strHexValueR==" + strHexValueR);
        this.mtvValueC.setText(strHexValueL);
        this.mtvValueR.setText(strHexValueR);
    }
}
