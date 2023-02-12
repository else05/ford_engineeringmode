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
import com.baidu.media.radio.lib.PDStatus;
import com.baidu.media.radio.lib.PDStatusCallback;
import com.baidu.media.radio.lib.RadioInfoManager;
import com.baidu.media.radio.lib.RadioSignal;
import com.baidu.media.radio.lib.RadioSignal2;
import com.baidu.media.radio.lib.ServiceConnectionCallback;
import com.baidu.media.radio.lib.Signal2InfoCallback;
import com.baidu.media.radio.lib.SignalInfoCallback;
import com.yfve.tools.LU;

/* loaded from: classes1.dex */
public class RadioTunerPhase2Fragment extends BaseFragment {
    private TextView mbtPdOff;
    private TextView mbtPdOn;
    private LinearLayout mllTuner2;
    private TextView mtvAMorFM;
    private TextView mtvAMorFM2;
    private TextView mtvDownC;
    private TextView mtvDownL;
    private TextView mtvDownR;
    private TextView mtvPdStatus2;
    private TextView mtvReset;
    private TextView mtvTunerAgc;
    private TextView mtvTunerBandWidth;
    private TextView mtvTunerIfbw2;
    private TextView mtvTunerModulation;
    private TextView mtvTunerOffSet;
    private TextView mtvTunerQuality;
    private TextView mtvTunerSs;
    private TextView mtvTunerSs2;
    private TextView mtvTunerStereo;
    private TextView mtvTunerUsn;
    private TextView mtvTunerUsn2;
    private TextView mtvTunerWam;
    private TextView mtvTunerWam2;
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
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.RadioTunerPhase2Fragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            int id = v.getId();
            if (id != R.id.tv_reset_a) {
                switch (id) {
                    case R.id.tv_down_center_a /* 2131230939 */:
                        if (RadioTunerPhase2Fragment.this.miValueLeft == 0) {
                            RadioTunerPhase2Fragment.this.miValueLeft = 15;
                            String strmiValueLeft = Integer.toHexString(RadioTunerPhase2Fragment.this.miValueLeft);
                            RadioTunerPhase2Fragment.this.mtvValueC.setText(strmiValueLeft);
                            return;
                        } else if (RadioTunerPhase2Fragment.this.miValueLeft > 0 && 15 >= RadioTunerPhase2Fragment.this.miValueLeft) {
                            RadioTunerPhase2Fragment.this.miValueLeft--;
                            String strmiValueLeft2 = Integer.toHexString(RadioTunerPhase2Fragment.this.miValueLeft);
                            RadioTunerPhase2Fragment.this.mtvValueC.setText(strmiValueLeft2);
                            return;
                        } else {
                            return;
                        }
                    case R.id.tv_down_left_a /* 2131230940 */:
                        if (RadioTunerPhase2Fragment.this.miIndex == 0) {
                            RadioTunerPhase2Fragment.this.miIndex = 35;
                            RadioTunerPhase2Fragment.this.setTextmtvValueL(RadioTunerPhase2Fragment.this.miIndex);
                            RadioTunerPhase2Fragment.this.setTextValue(RadioTunerPhase2Fragment.this.mintsKey[RadioTunerPhase2Fragment.this.miIndex]);
                            return;
                        } else if (RadioTunerPhase2Fragment.this.miIndex > 0 && 35 >= RadioTunerPhase2Fragment.this.miIndex) {
                            RadioTunerPhase2Fragment.this.miIndex--;
                            RadioTunerPhase2Fragment.this.setTextmtvValueL(RadioTunerPhase2Fragment.this.miIndex);
                            RadioTunerPhase2Fragment.this.setTextValue(RadioTunerPhase2Fragment.this.mintsKey[RadioTunerPhase2Fragment.this.miIndex]);
                            return;
                        } else {
                            return;
                        }
                    case R.id.tv_down_right_a /* 2131230941 */:
                        if (RadioTunerPhase2Fragment.this.miValueRight == 0) {
                            RadioTunerPhase2Fragment.this.miValueRight = 15;
                            String strmiValueLeft3 = Integer.toHexString(RadioTunerPhase2Fragment.this.miValueRight);
                            RadioTunerPhase2Fragment.this.mtvValueR.setText(strmiValueLeft3);
                            return;
                        } else if (RadioTunerPhase2Fragment.this.miValueRight > 0 && 15 >= RadioTunerPhase2Fragment.this.miValueRight) {
                            RadioTunerPhase2Fragment.this.miValueRight--;
                            String strmiValueLeft4 = Integer.toHexString(RadioTunerPhase2Fragment.this.miValueRight);
                            RadioTunerPhase2Fragment.this.mtvValueR.setText(strmiValueLeft4);
                            return;
                        } else {
                            return;
                        }
                    default:
                        switch (id) {
                            case R.id.tv_tuner2_off /* 2131230998 */:
                                RadioInfoManager.getInstance(RadioTunerPhase2Fragment.this.mMainActivity.getApplicationContext()).setPDStatus(0);
                                return;
                            case R.id.tv_tuner2_on /* 2131230999 */:
                                RadioInfoManager.getInstance(RadioTunerPhase2Fragment.this.mMainActivity.getApplicationContext()).setPDStatus(1);
                                return;
                            default:
                                switch (id) {
                                    case R.id.tv_up_center_a /* 2131231014 */:
                                        if (15 == RadioTunerPhase2Fragment.this.miValueLeft) {
                                            RadioTunerPhase2Fragment.this.miValueLeft = 0;
                                            String strHexmiValueLeft = Integer.toHexString(RadioTunerPhase2Fragment.this.miValueLeft);
                                            RadioTunerPhase2Fragment.this.mtvValueC.setText(strHexmiValueLeft);
                                            return;
                                        } else if (RadioTunerPhase2Fragment.this.miValueLeft >= 0 && 15 > RadioTunerPhase2Fragment.this.miValueLeft) {
                                            RadioTunerPhase2Fragment.this.miValueLeft++;
                                            String strHexmiValueLeft2 = Integer.toHexString(RadioTunerPhase2Fragment.this.miValueLeft);
                                            RadioTunerPhase2Fragment.this.mtvValueC.setText(strHexmiValueLeft2);
                                            return;
                                        } else {
                                            return;
                                        }
                                    case R.id.tv_up_left_a /* 2131231015 */:
                                        if (35 == RadioTunerPhase2Fragment.this.miIndex) {
                                            RadioTunerPhase2Fragment.this.miIndex = 0;
                                            RadioTunerPhase2Fragment.this.setTextmtvValueL(RadioTunerPhase2Fragment.this.miIndex);
                                            RadioTunerPhase2Fragment.this.setTextValue(RadioTunerPhase2Fragment.this.mintsKey[RadioTunerPhase2Fragment.this.miIndex]);
                                            return;
                                        } else if (RadioTunerPhase2Fragment.this.miIndex >= 0 && 35 > RadioTunerPhase2Fragment.this.miIndex) {
                                            RadioTunerPhase2Fragment.this.miIndex++;
                                            RadioTunerPhase2Fragment.this.setTextmtvValueL(RadioTunerPhase2Fragment.this.miIndex);
                                            RadioTunerPhase2Fragment.this.setTextValue(RadioTunerPhase2Fragment.this.mintsKey[RadioTunerPhase2Fragment.this.miIndex]);
                                            return;
                                        } else {
                                            return;
                                        }
                                    case R.id.tv_up_right_a /* 2131231016 */:
                                        if (15 == RadioTunerPhase2Fragment.this.miValueRight) {
                                            RadioTunerPhase2Fragment.this.miValueRight = 0;
                                            String strHexmiValueLeft3 = Integer.toHexString(RadioTunerPhase2Fragment.this.miValueRight);
                                            RadioTunerPhase2Fragment.this.mtvValueR.setText(strHexmiValueLeft3);
                                            return;
                                        } else if (RadioTunerPhase2Fragment.this.miValueRight >= 0 && 15 > RadioTunerPhase2Fragment.this.miValueRight) {
                                            RadioTunerPhase2Fragment.this.miValueRight++;
                                            String strHexmiValueLeft4 = Integer.toHexString(RadioTunerPhase2Fragment.this.miValueRight);
                                            RadioTunerPhase2Fragment.this.mtvValueR.setText(strHexmiValueLeft4);
                                            return;
                                        } else {
                                            return;
                                        }
                                    case R.id.tv_update_a /* 2131231017 */:
                                        int miValue = (RadioTunerPhase2Fragment.this.miValueLeft * 16) + RadioTunerPhase2Fragment.this.miValueRight;
                                        LU.w(RadioTunerPhase2Fragment.this.tag, "onClick()++ miIndex==" + RadioTunerPhase2Fragment.this.miIndex + "   iValue==" + miValue);
                                        RadioTunerPhase2Fragment.this.mintsKey[RadioTunerPhase2Fragment.this.miIndex] = miValue;
                                        RadioInfoManager.getInstance(RadioTunerPhase2Fragment.this.mMainActivity.getApplicationContext()).setPara(0, RadioTunerPhase2Fragment.this.mintsKey);
                                        return;
                                    default:
                                        return;
                                }
                        }
                }
            }
            RadioInfoManager.getInstance(RadioTunerPhase2Fragment.this.mMainActivity.getApplicationContext()).setPara(0, RadioTunerPhase2Fragment.this.mintsKey);
        }
    };
    private ServiceConnectionCallback mServiceConnectionCallback = new ServiceConnectionCallback() { // from class: com.yfve.engineeringmode.RadioTunerPhase2Fragment.3
        @Override // com.baidu.media.radio.lib.ServiceConnectionCallback
        public void onServiceConnected() {
            LU.e(RadioTunerPhase2Fragment.this.tag, "onServiceConnected()  +++++ ");
            try {
                RadioTunerPhase2Fragment.this.mintsKey = RadioInfoManager.getInstance(RadioTunerPhase2Fragment.this.mMainActivity.getApplicationContext()).getPara(0, 36);
                RadioTunerPhase2Fragment.this.miAmFm = RadioInfoManager.getInstance(RadioTunerPhase2Fragment.this.mMainActivity.getApplicationContext()).getCurrentRadioBand();
                if (RadioTunerPhase2Fragment.this.miAmFm == 0) {
                    RadioTunerPhase2Fragment.this.mtvAMorFM.setText("AM");
                } else if (1 == RadioTunerPhase2Fragment.this.miAmFm) {
                    RadioTunerPhase2Fragment.this.mtvAMorFM.setText("FM");
                } else {
                    RadioTunerPhase2Fragment.this.mtvAMorFM.setText("");
                }
                String str = RadioTunerPhase2Fragment.this.tag;
                LU.e(str, "mintsKey  ==" + RadioTunerPhase2Fragment.this.mintsKey.length);
                for (int i = 0; i < RadioTunerPhase2Fragment.this.mintsKey.length; i++) {
                    String str2 = RadioTunerPhase2Fragment.this.tag;
                    LU.e(str2, "mintsKey " + i + "  ==" + RadioTunerPhase2Fragment.this.mintsKey[i]);
                }
                if (36 == RadioTunerPhase2Fragment.this.mintsKey.length) {
                    RadioTunerPhase2Fragment.this.miIndex = 3;
                    if (RadioTunerPhase2Fragment.this.mbIsUiShow) {
                        RadioTunerPhase2Fragment.this.setTextmtvValueL(RadioTunerPhase2Fragment.this.miIndex);
                        RadioTunerPhase2Fragment.this.setTextValue(RadioTunerPhase2Fragment.this.mintsKey[RadioTunerPhase2Fragment.this.miIndex]);
                        RadioTunerPhase2Fragment.this.mbIsUiShow = false;
                    }
                }
            } catch (Exception e) {
                String str3 = RadioTunerPhase2Fragment.this.tag;
                LU.e(str3, "onServiceConnected()  Exception ==" + e.toString());
            }
        }

        @Override // com.baidu.media.radio.lib.ServiceConnectionCallback
        public void onServiceDisconnected() {
            LU.e(RadioTunerPhase2Fragment.this.tag, "onServiceDisconnected()  -----");
        }
    };
    RadioSignal mRadioSignal = null;
    private SignalInfoCallback mSignalInfoCallback = new SignalInfoCallback() { // from class: com.yfve.engineeringmode.RadioTunerPhase2Fragment.4
        @Override // com.baidu.media.radio.lib.SignalInfoCallback
        public void onSignalQualityChange(RadioSignal radioSignal) {
            RadioTunerPhase2Fragment.this.mRadioSignal = radioSignal;
            RadioTunerPhase2Fragment.this.mHandler.sendEmptyMessage(100);
        }
    };
    RadioSignal2 mRadioSignal2 = null;
    private Signal2InfoCallback mSignalInfo2Callback = new Signal2InfoCallback() { // from class: com.yfve.engineeringmode.RadioTunerPhase2Fragment.5
        @Override // com.baidu.media.radio.lib.Signal2InfoCallback
        public void onSignalQualityChange(RadioSignal2 radioSignal2) {
            RadioTunerPhase2Fragment.this.mRadioSignal2 = radioSignal2;
            String str = RadioTunerPhase2Fragment.this.tag;
            LU.e(str, "onSignalQualityChange()  +++++   radioSignal2==" + radioSignal2);
            RadioTunerPhase2Fragment.this.mHandler.sendEmptyMessage(200);
        }
    };
    private int mipdStatus = -1;
    private PDStatusCallback mPDStatusCallback = new PDStatusCallback() { // from class: com.yfve.engineeringmode.RadioTunerPhase2Fragment.6
        @Override // com.baidu.media.radio.lib.PDStatusCallback
        public void onPDStatus(PDStatus pdStatus) {
            RadioTunerPhase2Fragment.this.mipdStatus = pdStatus.getPDStatus();
            RadioTunerPhase2Fragment.this.mHandler.sendEmptyMessage(201);
        }
    };

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_radio_tuner, (ViewGroup) null);
        this.mllTuner2 = (LinearLayout) view.findViewById(R.id.ll_tuner2);
        this.mllTuner2.setVisibility(0);
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
        this.mbtPdOn = (TextView) view.findViewById(R.id.tv_tuner2_on);
        this.mbtPdOff = (TextView) view.findViewById(R.id.tv_tuner2_off);
        this.mbtPdOn.setOnClickListener(this.mbtOnClickListener);
        this.mbtPdOff.setOnClickListener(this.mbtOnClickListener);
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
        this.mtvAMorFM2 = (TextView) view.findViewById(R.id.tv_am_or_fm2);
        this.mtvPdStatus2 = (TextView) view.findViewById(R.id.tv_tuner_pd_Stauts2);
        this.mtvTunerSs2 = (TextView) view.findViewById(R.id.tv_tuner_ss2);
        this.mtvTunerUsn2 = (TextView) view.findViewById(R.id.tv_tuner_usn2);
        this.mtvTunerWam2 = (TextView) view.findViewById(R.id.tv_tuner_wam2);
        this.mtvTunerIfbw2 = (TextView) view.findViewById(R.id.tv_tuner_ifbw2);
        this.mHandler = new Handler() { // from class: com.yfve.engineeringmode.RadioTunerPhase2Fragment.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int i = msg.what;
                if (i == 100) {
                    if (RadioTunerPhase2Fragment.this.mRadioSignal != null) {
                        TextView textView = RadioTunerPhase2Fragment.this.mtvTunerSs;
                        textView.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal.getSignalStrength());
                        TextView textView2 = RadioTunerPhase2Fragment.this.mtvTunerUsn;
                        textView2.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal.getUsn());
                        TextView textView3 = RadioTunerPhase2Fragment.this.mtvTunerWam;
                        textView3.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal.getWam());
                        TextView textView4 = RadioTunerPhase2Fragment.this.mtvTunerOffSet;
                        textView4.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal.getOffset());
                        TextView textView5 = RadioTunerPhase2Fragment.this.mtvTunerBandWidth;
                        textView5.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal.getBandwidth());
                        TextView textView6 = RadioTunerPhase2Fragment.this.mtvTunerModulation;
                        textView6.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal.getModulation());
                        TextView textView7 = RadioTunerPhase2Fragment.this.mtvTunerQuality;
                        textView7.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal.getQuality());
                        TextView textView8 = RadioTunerPhase2Fragment.this.mtvTunerStereo;
                        textView8.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal.isStereo());
                        TextView textView9 = RadioTunerPhase2Fragment.this.mtvTunerAgc;
                        textView9.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal.getAgc());
                        RadioTunerPhase2Fragment.this.miAmFm = RadioInfoManager.getInstance(RadioTunerPhase2Fragment.this.mMainActivity.getApplicationContext()).getCurrentRadioBand();
                        if (RadioTunerPhase2Fragment.this.miAmFm == 0) {
                            RadioTunerPhase2Fragment.this.mtvAMorFM.setText("AM");
                            return;
                        } else if (1 == RadioTunerPhase2Fragment.this.miAmFm) {
                            RadioTunerPhase2Fragment.this.mtvAMorFM.setText("FM");
                            return;
                        } else {
                            RadioTunerPhase2Fragment.this.mtvAMorFM.setText("");
                            return;
                        }
                    }
                    return;
                }
                switch (i) {
                    case 200:
                        if (RadioTunerPhase2Fragment.this.mRadioSignal2 != null) {
                            TextView textView10 = RadioTunerPhase2Fragment.this.mtvTunerSs2;
                            textView10.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal2.getSignalStrength());
                            TextView textView11 = RadioTunerPhase2Fragment.this.mtvTunerUsn2;
                            textView11.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal2.getUsn());
                            TextView textView12 = RadioTunerPhase2Fragment.this.mtvTunerWam2;
                            textView12.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal2.getWam());
                            TextView textView13 = RadioTunerPhase2Fragment.this.mtvTunerIfbw2;
                            textView13.setText("" + RadioTunerPhase2Fragment.this.mRadioSignal2.getIFBW());
                            return;
                        }
                        return;
                    case 201:
                        if (1 == RadioTunerPhase2Fragment.this.mipdStatus) {
                            RadioTunerPhase2Fragment.this.mtvPdStatus2.setText("ON");
                            return;
                        } else if (RadioTunerPhase2Fragment.this.mipdStatus == 0) {
                            RadioTunerPhase2Fragment.this.mtvPdStatus2.setText("OFF");
                            RadioTunerPhase2Fragment.this.mtvTunerSs2.setText("--");
                            RadioTunerPhase2Fragment.this.mtvTunerUsn2.setText("--");
                            RadioTunerPhase2Fragment.this.mtvTunerWam2.setText("--");
                            RadioTunerPhase2Fragment.this.mtvTunerIfbw2.setText("--");
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
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
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).setSignal2InfoCallback(this.mSignalInfo2Callback);
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).setPDStatusCallback(this.mPDStatusCallback);
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
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).setSignal2InfoCallback(null);
        RadioInfoManager.getInstance(this.mMainActivity.getApplicationContext()).setPDStatusCallback(null);
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
