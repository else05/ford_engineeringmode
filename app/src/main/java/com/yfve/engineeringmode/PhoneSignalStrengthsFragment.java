package com.yfve.engineeringmode;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.yfve.tools.FileUtil;
import com.yfve.tools.LU;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes1.dex */
public class PhoneSignalStrengthsFragment extends BaseFragment {
    private LineChart mLineChart;
    private LineData mLineData;
    private LineDataSet mLineDataSet;
    private MyPhoneStateListener mMyPhoneStateListener;
    private TelephonyManager mTelephonyManager;
    private TextView mtvSignal1;
    private TextView mtvSignal2;
    private TextView mtvSignal3;
    private TextView mtvSignal4;
    private TextView mtvSignal5;
    private TextView mtvState;
    private TextView mtvValue;
    private XAxis mXAxis = null;
    public final int NETWORK_NONE = 0;
    public final int NETWORK_WIFI = 1;
    public final int NETWORK_2G = 2;
    public final int NETWORK_3G = 3;
    public final int NETWORK_4G = 4;
    public final int NETWORK_UNKNOWN = 5;
    public final int MAX_DATA_LIMIT = 10000;
    private int miSignalLevel = -1;
    private int miGsmSignalStrength = 0;
    private int miSignalMode = -1;
    private Handler mHandler = null;
    private Thread mThread = null;
    private boolean mbIsRunnableStart = true;
    private boolean mbIsSignalNormal = true;
    private int miCountSignalStrength = 0;
    private StringBuffer mstrbSignalStrength = null;
    private final String SIGNAL_STRENGTH_NAME = "SignalStrength";
    private Runnable mMyRunnable = new Runnable() { // from class: com.yfve.engineeringmode.PhoneSignalStrengthsFragment.2
        @Override // java.lang.Runnable
        public void run() {
            String str = PhoneSignalStrengthsFragment.this.tag;
            LU.e(str, "mMyRunnable+run()     ++++++++ mbIsRunnableStart==" + PhoneSignalStrengthsFragment.this.mbIsRunnableStart + "  mbIsSignalNormal==" + PhoneSignalStrengthsFragment.this.mbIsSignalNormal);
            while (PhoneSignalStrengthsFragment.this.mbIsRunnableStart) {
                try {
                    Thread.sleep(5000L);
                    if (PhoneSignalStrengthsFragment.this.mbIsSignalNormal) {
                        PhoneSignalStrengthsFragment.this.mHandler.sendEmptyMessageDelayed(PhoneSignalStrengthsFragment.this.miSignalMode, 0L);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LU.e(PhoneSignalStrengthsFragment.this.tag, "mMyRunnable+run()     ---------");
        }
    };

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_phone_signal_strengths, (ViewGroup) null);
        onCheckSelfPermission();
        init(view);
        this.mHandler = new Handler() { // from class: com.yfve.engineeringmode.PhoneSignalStrengthsFragment.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String str = PhoneSignalStrengthsFragment.this.tag;
                LU.w(str, "handleMessage()   msg.what==" + msg.what);
                switch (msg.what) {
                    case 0:
                        PhoneSignalStrengthsFragment.this.mtvState.setText("UNKNOWN");
                        PhoneSignalStrengthsFragment.this.mtvValue.setText("NULL");
                        PhoneSignalStrengthsFragment.this.setSignalLevel(0);
                        break;
                    case 1:
                        PhoneSignalStrengthsFragment.this.mtvState.setText("WIFI");
                        PhoneSignalStrengthsFragment.this.mtvValue.setText("NULL");
                        PhoneSignalStrengthsFragment.this.setSignalLevel(0);
                        break;
                    case 2:
                        PhoneSignalStrengthsFragment.this.mtvState.setText("2G");
                        TextView textView = PhoneSignalStrengthsFragment.this.mtvValue;
                        textView.setText("" + PhoneSignalStrengthsFragment.this.miGsmSignalStrength);
                        PhoneSignalStrengthsFragment.this.setSignalLevel(PhoneSignalStrengthsFragment.this.miSignalLevel);
                        break;
                    case 3:
                        PhoneSignalStrengthsFragment.this.mtvState.setText("3G");
                        TextView textView2 = PhoneSignalStrengthsFragment.this.mtvValue;
                        textView2.setText("" + PhoneSignalStrengthsFragment.this.miGsmSignalStrength);
                        PhoneSignalStrengthsFragment.this.setSignalLevel(PhoneSignalStrengthsFragment.this.miSignalLevel);
                        break;
                    case 4:
                        PhoneSignalStrengthsFragment.this.mtvState.setText("4G");
                        TextView textView3 = PhoneSignalStrengthsFragment.this.mtvValue;
                        textView3.setText("" + PhoneSignalStrengthsFragment.this.miGsmSignalStrength);
                        PhoneSignalStrengthsFragment.this.setSignalLevel(PhoneSignalStrengthsFragment.this.miSignalLevel);
                        break;
                    case 5:
                        PhoneSignalStrengthsFragment.this.mtvState.setText("NULL");
                        PhoneSignalStrengthsFragment.this.mtvValue.setText("NULL");
                        PhoneSignalStrengthsFragment.this.setSignalLevel(0);
                        break;
                }
                if (PhoneSignalStrengthsFragment.this.miGsmSignalStrength < 0 && -130 <= PhoneSignalStrengthsFragment.this.miGsmSignalStrength) {
                    PhoneSignalStrengthsFragment.this.addEntry(PhoneSignalStrengthsFragment.this.miGsmSignalStrength);
                }
            }
        };
        return view;
    }

    private void onCheckSelfPermission() {
        if (ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.ACCESS_NETWORK_STATE") != 0 || ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.CHANGE_NETWORK_STATE") != 0 || ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.WRITE_EXTERNAL_STORAGE") != 0 || ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this.mMainActivity, new String[]{"android.permission.CHANGE_NETWORK_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 1);
        }
    }

    private void onInitFile() {
        if (this.mstrbSignalStrength == null) {
            this.mstrbSignalStrength = new StringBuffer();
        }
        FileUtil.getInstance().init("SignalStrength");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        FileUtil fileUtil = FileUtil.getInstance();
        StringBuffer stringBuffer = this.mstrbSignalStrength;
        stringBuffer.append(simpleDateFormat.format(date));
        stringBuffer.append("\tData refresh interval : 2s\r\n");
        fileUtil.write(stringBuffer);
        this.mstrbSignalStrength.setLength(0);
    }

    private void init(View view) {
        onInitFile();
        this.mtvState = (TextView) view.findViewById(R.id.tv_pss_state);
        this.mtvValue = (TextView) view.findViewById(R.id.tv_pss_value);
        this.mtvSignal1 = (TextView) view.findViewById(R.id.tv_pss_signal1);
        this.mtvSignal2 = (TextView) view.findViewById(R.id.tv_pss_signal2);
        this.mtvSignal3 = (TextView) view.findViewById(R.id.tv_pss_signal3);
        this.mtvSignal4 = (TextView) view.findViewById(R.id.tv_pss_signal4);
        this.mtvSignal5 = (TextView) view.findViewById(R.id.tv_pss_signal5);
        setSignalLevel(0);
        if (this.mTelephonyManager == null) {
            this.mTelephonyManager = (TelephonyManager) this.mMainActivity.getSystemService("phone");
            String str = this.tag;
            LU.w(str, "init()   mTelephonyManager.getSimOperatorName()==" + this.mTelephonyManager.getSimOperatorName());
        }
        if (this.mMyPhoneStateListener == null) {
            this.mMyPhoneStateListener = new MyPhoneStateListener();
        }
        initLineChart(view);
    }

    private void initLineChart(View view) {
        this.mLineChart = (LineChart) view.findViewById(R.id.lineChart_pss);
        this.mLineChart.setDrawBorders(true);
        this.mLineDataSet = new LineDataSet(null, "Signal Strengths");
        this.mLineDataSet.setLineWidth(2.0f);
        this.mLineDataSet.setValueTextSize(10.0f);
        this.mLineDataSet.setValueTextColor(this.mMainActivity.getResources().getColor(R.color.color_blue));
        this.mLineDataSet.setDrawCircleHole(false);
        this.mLineData = new LineData();
        this.mLineChart.setData(this.mLineData);
        this.mLineChart.invalidate();
        Description description = new Description();
        description.setEnabled(false);
        this.mLineChart.setDescription(description);
        this.mXAxis = this.mLineChart.getXAxis();
        this.mXAxis.setGranularity(1.0f);
        YAxis leftYAxis = this.mLineChart.getAxisLeft();
        YAxis rightYAxis = this.mLineChart.getAxisRight();
        leftYAxis.setTextColor(this.mMainActivity.getResources().getColor(R.color.color_red_exit));
        leftYAxis.setAxisMinimum(-130.0f);
        leftYAxis.setAxisMaximum(10.0f);
        leftYAxis.setTextSize(18.0f);
        rightYAxis.setEnabled(false);
    }

    public void writeDateToFile() {
        if (this.mbIsSignalNormal) {
            FileUtil fileUtil = FileUtil.getInstance();
            StringBuffer stringBuffer = this.mstrbSignalStrength;
            stringBuffer.append(this.miCountSignalStrength);
            stringBuffer.append(".\tNetwork type : ");
            stringBuffer.append(this.miSignalMode);
            stringBuffer.append("G\tSignal Strength :");
            stringBuffer.append(this.miSignalLevel);
            stringBuffer.append("\tValue : ");
            stringBuffer.append(this.miGsmSignalStrength);
            stringBuffer.append("\r\n");
            fileUtil.write(stringBuffer);
            this.mstrbSignalStrength.setLength(0);
            this.miCountSignalStrength++;
        }
    }

    public void addEntry(int number) {
        writeDateToFile();
        int iEntryCount = this.mLineData.getEntryCount();
        if (this.mLineData.getDataSetByIndex(0) == 0) {
            this.mLineData.addDataSet(this.mLineDataSet);
        }
        if (10 > iEntryCount) {
            this.mXAxis.setLabelCount(iEntryCount, false);
        }
        Entry entry = new Entry(iEntryCount, number);
        this.mLineData.addEntry(entry, 0);
        this.mLineData.notifyDataChanged();
        this.mLineChart.notifyDataSetChanged();
        this.mLineChart.setVisibleXRangeMaximum(10.0f);
        this.mLineChart.moveViewToX(this.mLineData.getEntryCount() - 1);
        if (10000 == iEntryCount) {
            this.mMainActivity.onPssFragmentUpdate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSignalLevel(int iLevel) {
        switch (iLevel) {
            case 1:
                this.mtvSignal1.setVisibility(0);
                this.mtvSignal2.setVisibility(4);
                this.mtvSignal3.setVisibility(4);
                this.mtvSignal4.setVisibility(4);
                this.mtvSignal5.setVisibility(4);
                return;
            case 2:
                this.mtvSignal1.setVisibility(0);
                this.mtvSignal2.setVisibility(0);
                this.mtvSignal3.setVisibility(4);
                this.mtvSignal4.setVisibility(4);
                this.mtvSignal5.setVisibility(4);
                return;
            case 3:
                this.mtvSignal1.setVisibility(0);
                this.mtvSignal2.setVisibility(0);
                this.mtvSignal3.setVisibility(0);
                this.mtvSignal4.setVisibility(4);
                this.mtvSignal5.setVisibility(4);
                return;
            case 4:
                this.mtvSignal1.setVisibility(0);
                this.mtvSignal2.setVisibility(0);
                this.mtvSignal3.setVisibility(0);
                this.mtvSignal4.setVisibility(0);
                this.mtvSignal5.setVisibility(4);
                return;
            case 5:
                this.mtvSignal1.setVisibility(0);
                this.mtvSignal2.setVisibility(0);
                this.mtvSignal3.setVisibility(0);
                this.mtvSignal4.setVisibility(0);
                this.mtvSignal5.setVisibility(0);
                return;
            default:
                this.mtvSignal1.setVisibility(4);
                this.mtvSignal2.setVisibility(4);
                this.mtvSignal3.setVisibility(4);
                this.mtvSignal4.setVisibility(4);
                this.mtvSignal5.setVisibility(4);
                return;
        }
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_pss));
        this.mTelephonyManager.listen(this.mMyPhoneStateListener, 256);
        this.mbIsRunnableStart = true;
        this.mbIsSignalNormal = true;
        this.mThread = new Thread(this.mMyRunnable);
        this.mThread.start();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        LU.w(this.tag, "onPause()++++  ");
        this.mTelephonyManager.listen(this.mMyPhoneStateListener, 0);
        this.mbIsRunnableStart = false;
        this.mbIsSignalNormal = false;
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LU.w(this.tag, "onDestroy()++++  ");
        this.miCountSignalStrength = 0;
        this.mLineDataSet.clear();
        this.mLineData.clearValues();
        FileUtil.getInstance().close();
        this.mThread = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getGsmSignalStrength(SignalStrength signalStrength) {
        Class clazz = signalStrength.getClass();
        try {
            Method method = clazz.getMethod("getDbm", new Class[0]);
            int igetDbm = ((Integer) method.invoke(signalStrength, new Object[0])).intValue();
            return igetDbm;
        } catch (Exception e) {
            e.printStackTrace();
            return 99;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes1.dex */
    public class MyPhoneStateListener extends PhoneStateListener {
        MyPhoneStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            if (Build.VERSION.SDK_INT >= 23) {
                PhoneSignalStrengthsFragment.this.miSignalLevel = signalStrength.getLevel();
            }
            int iNetWorkType = PhoneSignalStrengthsFragment.this.getNetWorkType(PhoneSignalStrengthsFragment.this.mMainActivity);
            PhoneSignalStrengthsFragment.this.miGsmSignalStrength = PhoneSignalStrengthsFragment.this.getGsmSignalStrength(signalStrength);
            if (iNetWorkType != 0) {
                switch (iNetWorkType) {
                    case 2:
                    case 3:
                    case 4:
                        PhoneSignalStrengthsFragment.this.mbIsSignalNormal = true;
                        break;
                    default:
                        if (PhoneSignalStrengthsFragment.this.miGsmSignalStrength >= 0 || PhoneSignalStrengthsFragment.this.miGsmSignalStrength <= -130) {
                            PhoneSignalStrengthsFragment.this.miGsmSignalStrength = -130;
                            PhoneSignalStrengthsFragment.this.mbIsSignalNormal = false;
                            PhoneSignalStrengthsFragment.this.mHandler.sendEmptyMessageDelayed(0, 0L);
                            break;
                        } else {
                            PhoneSignalStrengthsFragment.this.mbIsSignalNormal = true;
                            break;
                        }
                        break;
                }
            } else {
                PhoneSignalStrengthsFragment.this.mbIsSignalNormal = false;
            }
            if (-1 == PhoneSignalStrengthsFragment.this.miSignalMode && iNetWorkType != 0) {
                PhoneSignalStrengthsFragment.this.mHandler.sendEmptyMessageDelayed(iNetWorkType, 0L);
            }
            PhoneSignalStrengthsFragment.this.miSignalMode = iNetWorkType;
            String str = PhoneSignalStrengthsFragment.this.tag;
            LU.e(str, "onSignalStrengthsChanged(+2)  value===" + PhoneSignalStrengthsFragment.this.miSignalMode + "  ==" + PhoneSignalStrengthsFragment.this.miSignalLevel + "  ==" + PhoneSignalStrengthsFragment.this.miGsmSignalStrength);
        }
    }

    public int getNetWorkType(Context context) {
        int iNetworkType = this.mTelephonyManager.getNetworkType();
        String str = this.tag;
        LU.d(str, "getNetWorkType(+)  iNetworkType===" + iNetworkType);
        switch (iNetworkType) {
            case 0:
                return 0;
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 2;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 3;
            case 13:
                return 4;
            default:
                return 5;
        }
    }
}
