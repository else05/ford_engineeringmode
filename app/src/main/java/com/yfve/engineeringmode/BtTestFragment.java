package com.yfve.engineeringmode;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.yfve.tools.LU;
import java.lang.reflect.Method;

/* loaded from: classes1.dex */
public class BtTestFragment extends BaseFragment {
    public static final String ACTION_BT_HCI_COMPLETE_RECV_CALLBACK = "android.bluetooth.device.action.HCI_COMPLETE_RECV_CALLBACK";
    public static final String ACTION_BT_LE_COMPLETE_RECV_CALLBACK = "android.bluetooth.device.action.LE_COMPLETE_RECV_CALLBACK";
    public static final String ACTION_LINKLOST_STATE_CHANGED = "android.bluetooth.link.lost.action.CONNECTION_STATE_CHANGED";
    private static final int ITEM_MAX = 14;
    private static final int ITEM_SFTT_END = 13;
    private static final short OPCODE_BLE_RECEIVER = 8221;
    private static final short OPCODE_BLE_TEST_END = 8223;
    private static final short OPCODE_BLE_TRANSMITTER = 8222;
    private static final short OPCODE_SFTT_END = 16148;
    private static final short OPCODE_TESTMODE_DISABLE = 3139;
    private static final short OPCODE_TESTMODE_ENABLE = 6147;
    public static final String RESPONSE_LE_PACKET_COUNT = "packet_count";
    public static final String RESPONSE_LE_STATUS = "status";
    public static final String RESPONSE_TYPE_OPCODE = "opcode";
    public static final String RESPONSE_TYPE_PARAMS_LEN = "len";
    public static final String RESPONSE_TYPE_RETURN_PARAMS = "ReturnParams";
    public static final String RESPONSE_TYPE_RETURN_PARAMS_BUFARRAY = "ReturnParams_buf_array";
    private Switch mSwitchTestModeConfig;
    private EditText metHciOcf;
    private EditText metHciOgf;
    private EditText metHciParms;
    private EditText metSendRece;
    private EditText metSendTra;
    private EditText metSingChannel;
    private EditText metSingChannel3;
    private EditText metSingMode3;
    private EditText metSingTyte;
    private EditText metSingTyte3;
    private TextView mtvBLEReceiverTest;
    private TextView mtvBLETestEnd;
    private TextView mtvBLETransmitterTest;
    private TextView mtvEnableTestMode;
    private TextView mtvExitTestMode;
    private TextView mtvIntoTestMode;
    private TextView mtvSendBleTestEnd;
    private TextView mtvSendHci;
    private TextView mtvSendHciCommand;
    private TextView mtvSendHciNext;
    private TextView mtvSendHciShow;
    private TextView mtvSendRece;
    private TextView mtvSendSing;
    private TextView mtvSendSing3;
    private TextView mtvSendTra;
    private TextView mtvShowInfo;
    private TextView mtvTestModeConfigFalse;
    private TestModeBroadcastReceiver mTestModeBroadcastReceiver = null;
    private StringBuffer mStringBuffer = null;
    private Handler mHandler = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    private StringBuffer mStringBufferHci = null;
    private byte[] mbyteHcis = null;
    private byte[] mbyteHcis2 = null;
    private int miHciCount = 0;
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.BtTestFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            try {
                int id = v.getId();
                if (id == R.id.tv_exit_testm) {
                    Intent disable_command_intent = new Intent();
                    disable_command_intent.setAction("android.bluetooth.DISABLE_HCI_COMMAND");
                    BtTestFragment.this.mStringBuffer.append("android.bluetooth.DISABLE_HCI_COMMAND\r\n");
                    BtTestFragment.this.mMainActivity.sendBroadcast(disable_command_intent);
                } else if (id == R.id.tv_hci_command_parms_next) {
                    if (BtTestFragment.this.mbyteHcis == null) {
                        BtTestFragment.this.mbyteHcis = new byte[1024];
                    }
                    if (BtTestFragment.this.mStringBufferHci == null) {
                        BtTestFragment.this.mStringBufferHci = new StringBuffer();
                    }
                    String strHciParms = BtTestFragment.this.metHciParms.getText().toString();
                    int iHciParms = Integer.valueOf(strHciParms, 16).intValue();
                    BtTestFragment.this.mbyteHcis[BtTestFragment.this.miHciCount] = (byte) iHciParms;
                    BtTestFragment.access$2008(BtTestFragment.this);
                    StringBuffer stringBuffer = BtTestFragment.this.mStringBufferHci;
                    stringBuffer.append(strHciParms + "  ");
                    BtTestFragment.this.metHciParms.getText().clear();
                    BtTestFragment.this.mtvSendHciShow.setText(BtTestFragment.this.mStringBufferHci.toString());
                } else if (id == R.id.tv_into_testm) {
                    Intent enable_command_intent = new Intent();
                    enable_command_intent.setAction("android.bluetooth.ENABLE_HCI_COMMAND");
                    BtTestFragment.this.mStringBuffer.append("android.bluetooth.ENABLE_HCI_COMMAND\r\n");
                    BtTestFragment.this.mMainActivity.sendBroadcast(enable_command_intent);
                } else if (id == R.id.tv_test_mode_config_f) {
                    BtTestFragment.this.testModeConfigure(false);
                    BtTestFragment.this.mStringBuffer.append("testModeConfigure(false)\r\n");
                } else {
                    switch (id) {
                        case R.id.tv_ble_receiver_t /* 2131230933 */:
                            Intent ble_receiver_command_intent = new Intent();
                            ble_receiver_command_intent.setAction("android.bluetooth.BLE_RECEIVER_HCI_COMMAND");
                            BtTestFragment.this.mStringBuffer.append("android.bluetooth.BLE_RECEIVER_HCI_COMMAND\r\n");
                            BtTestFragment.this.mMainActivity.sendBroadcast(ble_receiver_command_intent);
                            return;
                        case R.id.tv_ble_test_end /* 2131230934 */:
                            Intent ble_test_end_command_intent = new Intent();
                            ble_test_end_command_intent.setAction("android.bluetooth.BLE_TEST_END_HCI_COMMAND");
                            BtTestFragment.this.mStringBuffer.append("android.bluetooth.BLE_TEST_END_HCI_COMMAND\r\n");
                            BtTestFragment.this.mMainActivity.sendBroadcast(ble_test_end_command_intent);
                            return;
                        case R.id.tv_ble_tran_t /* 2131230935 */:
                            Intent ble_transmitter_command_intent = new Intent();
                            ble_transmitter_command_intent.setAction("android.bluetooth.BLE_TRANSMITTER_HCI_COMMAND");
                            BtTestFragment.this.mStringBuffer.append("android.bluetooth.BLE_TRANSMITTER_HCI_COMMAND\r\n");
                            BtTestFragment.this.mMainActivity.sendBroadcast(ble_transmitter_command_intent);
                            return;
                        default:
                            switch (id) {
                                case R.id.tv_send_ble_test_end /* 2131230968 */:
                                    BtTestFragment.this.sendBleTestEndCommand();
                                    BtTestFragment.this.mStringBuffer.append("sendBleTestEndCommand()\r\n");
                                    return;
                                case R.id.tv_send_hci_command /* 2131230969 */:
                                    if (BtTestFragment.this.mbyteHcis == null) {
                                        BtTestFragment.this.mbyteHcis = new byte[1024];
                                    }
                                    if (BtTestFragment.this.mStringBufferHci == null) {
                                        BtTestFragment.this.mStringBufferHci = new StringBuffer();
                                    }
                                    String strHciOgf = BtTestFragment.this.metHciOgf.getText().toString();
                                    String strHciOcf = BtTestFragment.this.metHciOcf.getText().toString();
                                    String strHciParmsC = BtTestFragment.this.metHciParms.getText().toString();
                                    if (strHciOgf != null && !"".equals(strHciOgf) && strHciOcf != null && !"".equals(strHciOcf) && strHciParmsC != null && !"".equals(strHciParmsC)) {
                                        int iHciParmsC = Integer.valueOf(strHciParmsC, 16).intValue();
                                        BtTestFragment.this.mbyteHcis[BtTestFragment.this.miHciCount] = (byte) iHciParmsC;
                                        BtTestFragment.access$2008(BtTestFragment.this);
                                        BtTestFragment.this.mbyteHcis2 = new byte[BtTestFragment.this.miHciCount];
                                        System.arraycopy(BtTestFragment.this.mbyteHcis, 0, BtTestFragment.this.mbyteHcis2, 0, BtTestFragment.this.miHciCount);
                                        BtTestFragment.this.mStringBufferHci.append(strHciParmsC);
                                        int iHciOgf = Integer.valueOf(strHciOgf, 16).intValue();
                                        int iHciOcf = Integer.valueOf(strHciOcf, 16).intValue();
                                        String str = BtTestFragment.this.tag;
                                        LU.w(str, "tv_send_hci_command    strHciOgf==" + strHciOgf + "   strHciOcf==" + strHciOcf + "  miHciCount==" + BtTestFragment.this.miHciCount);
                                        for (int i = 0; i < BtTestFragment.this.mbyteHcis2.length; i++) {
                                            String str2 = BtTestFragment.this.tag;
                                            LU.e(str2, "for    mbyteHcis2[i]==" + ((int) BtTestFragment.this.mbyteHcis2[i]));
                                        }
                                        BtTestFragment.this.sendHciCommand((byte) iHciOgf, (byte) iHciOcf, BtTestFragment.this.mbyteHcis2);
                                        StringBuffer stringBuffer2 = BtTestFragment.this.mStringBuffer;
                                        stringBuffer2.append("sendHciCommand(" + strHciOgf + "," + strHciOcf + "," + BtTestFragment.this.mStringBufferHci.toString() + ")\r\n");
                                        Message message = Message.obtain();
                                        message.what = 100;
                                        message.obj = BtTestFragment.this.mStringBuffer.toString();
                                        BtTestFragment.this.mHandler.sendMessageDelayed(message, 0L);
                                        BtTestFragment.this.mStringBuffer.append("\r\n");
                                        BtTestFragment.this.miHciCount = 0;
                                        BtTestFragment.this.mtvSendHciShow.setText("");
                                        BtTestFragment.this.mbyteHcis = null;
                                        BtTestFragment.this.mStringBufferHci = null;
                                        BtTestFragment.this.metHciParms.getText().clear();
                                        BtTestFragment.this.metHciOgf.getText().clear();
                                        BtTestFragment.this.metHciOcf.getText().clear();
                                        return;
                                    }
                                    return;
                                case R.id.tv_send_receiver /* 2131230970 */:
                                    String strRece = BtTestFragment.this.metSendRece.getText().toString();
                                    if (strRece != null && !"".equals(strRece)) {
                                        int iRece = Integer.parseInt(strRece);
                                        String str3 = BtTestFragment.this.tag;
                                        LU.w(str3, "tv_send_receiver    strRece==" + iRece);
                                        if (iRece >= 0 && 39 >= iRece) {
                                            BtTestFragment.this.sendBleReceiverCommand(iRece);
                                            StringBuffer stringBuffer3 = BtTestFragment.this.mStringBuffer;
                                            stringBuffer3.append("sendBleReceiverCommand(" + iRece + ")\r\n");
                                        } else {
                                            Toast.makeText(BtTestFragment.this.mMainActivity, "请输入有效值", 0).show();
                                        }
                                        BtTestFragment.this.metSendRece.getText().clear();
                                        return;
                                    }
                                    return;
                                case R.id.tv_send_single_ftt_command /* 2131230971 */:
                                    String strType = BtTestFragment.this.metSingTyte.getText().toString();
                                    String strChan = BtTestFragment.this.metSingChannel.getText().toString();
                                    if (strType != null && !"".equals(strType) && strChan != null && !"".equals(strChan)) {
                                        int iType = Integer.parseInt(strType);
                                        int iChan = Integer.parseInt(strChan);
                                        String str4 = BtTestFragment.this.tag;
                                        LU.w(str4, "tv_send_single_ftt_command    strType==" + iType + "  strChan== " + iChan);
                                        if (iType >= 0 && 2 >= iType && 1 <= iChan && 2 >= iChan) {
                                            BtTestFragment.this.sendSingleFTTCommand(iType, iChan);
                                            StringBuffer stringBuffer4 = BtTestFragment.this.mStringBuffer;
                                            stringBuffer4.append("sendSingleFTTCommand(" + iType + "," + iChan + ")\r\n");
                                            Message message2 = Message.obtain();
                                            message2.what = 100;
                                            message2.obj = BtTestFragment.this.mStringBuffer.toString();
                                            String str5 = BtTestFragment.this.tag;
                                            LU.d(str5, "tv_send_single_ftt_command   mStringBuffer.toString() = " + BtTestFragment.this.mStringBuffer.toString());
                                            BtTestFragment.this.mHandler.sendMessageDelayed(message2, 0L);
                                            BtTestFragment.this.mStringBuffer.append("\r\n");
                                        } else {
                                            Toast.makeText(BtTestFragment.this.mMainActivity, "请输入有效值", 0).show();
                                        }
                                        BtTestFragment.this.metSingTyte.getText().clear();
                                        BtTestFragment.this.metSingChannel.getText().clear();
                                        return;
                                    }
                                    return;
                                case R.id.tv_send_single_ftt_command3 /* 2131230972 */:
                                    String strMode3 = BtTestFragment.this.metSingMode3.getText().toString();
                                    String strType3 = BtTestFragment.this.metSingTyte3.getText().toString();
                                    String strChan3 = BtTestFragment.this.metSingChannel3.getText().toString();
                                    if (strMode3 != null && !"".equals(strMode3) && strType3 != null && !"".equals(strType3) && strChan3 != null && !"".equals(strChan3)) {
                                        int iMode3 = Integer.parseInt(strMode3);
                                        int iType3 = Integer.parseInt(strType3);
                                        int iChan3 = Integer.parseInt(strChan3);
                                        String str6 = BtTestFragment.this.tag;
                                        LU.w(str6, "tv_send_single_ftt_command    strMode3==" + iMode3 + "   strType3==" + iType3 + "  strChan3== " + iChan3);
                                        if (iMode3 >= 0 && 1 >= iMode3 && iType3 >= 0 && 3 >= iType3 && 1 <= iChan3 && 2 >= iChan3) {
                                            BtTestFragment.this.sendSingleFTTCommand(iMode3, iType3, iChan3);
                                            StringBuffer stringBuffer5 = BtTestFragment.this.mStringBuffer;
                                            stringBuffer5.append("sendSingleFTTCommand(" + iMode3 + "," + iType3 + "," + iChan3 + ")\r\n");
                                            Message message3 = Message.obtain();
                                            message3.what = 100;
                                            message3.obj = BtTestFragment.this.mStringBuffer.toString();
                                            String str7 = BtTestFragment.this.tag;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("tv_send_single_ftt_command   mStringBuffer.toString() = ");
                                            sb.append(BtTestFragment.this.mStringBuffer.toString());
                                            LU.d(str7, sb.toString());
                                            BtTestFragment.this.mHandler.sendMessageDelayed(message3, 0L);
                                            BtTestFragment.this.mStringBuffer.append("\r\n");
                                        } else {
                                            Toast.makeText(BtTestFragment.this.mMainActivity, "请输入有效值", 0).show();
                                        }
                                        BtTestFragment.this.metSingMode3.getText().clear();
                                        BtTestFragment.this.metSingTyte3.getText().clear();
                                        BtTestFragment.this.metSingChannel3.getText().clear();
                                        return;
                                    }
                                    return;
                                case R.id.tv_send_transmitter /* 2131230973 */:
                                    String strTran = BtTestFragment.this.metSendTra.getText().toString();
                                    if (strTran != null && !"".equals(strTran)) {
                                        int iTran = Integer.parseInt(strTran);
                                        String str8 = BtTestFragment.this.tag;
                                        LU.w(str8, "tv_send_transmitter    strTran==" + iTran);
                                        if (iTran >= 0 && 39 >= iTran) {
                                            BtTestFragment.this.sendBleTransmitterCommand(iTran);
                                            StringBuffer stringBuffer6 = BtTestFragment.this.mStringBuffer;
                                            stringBuffer6.append("sendBleTransmitterCommand(" + iTran + ")\r\n");
                                        } else {
                                            Toast.makeText(BtTestFragment.this.mMainActivity, "请输入有效值", 0).show();
                                        }
                                        BtTestFragment.this.metSendTra.getText().clear();
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                    }
                }
            } catch (Exception e) {
                String str9 = BtTestFragment.this.tag;
                LU.e(str9, "onclick ()   Exception == " + e.toString());
            }
        }
    };
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.yfve.engineeringmode.BtTestFragment.3
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == R.id.switch_test_mode_config) {
                String str = BtTestFragment.this.tag;
                LU.e(str, "onCheckedChanged()   buttonView.getId()==" + buttonView.getId() + "  isChecked==" + isChecked);
                if (isChecked) {
                    BtTestFragment.this.testModeConfigure(true);
                    BtTestFragment.this.mStringBuffer.append("testModeConfigure(true)\r\n");
                    return;
                }
                BtTestFragment.this.testModeConfigure(false);
                BtTestFragment.this.mStringBuffer.append("testModeConfigure(false)\r\n");
            }
        }
    };

    static /* synthetic */ int access$2008(BtTestFragment x0) {
        int i = x0.miHciCount;
        x0.miHciCount = i + 1;
        return i;
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bt_test, (ViewGroup) null);
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        onCheckSelfPermission();
        initView(view);
        onRegisterReceiver();
        this.mHandler = new Handler() { // from class: com.yfve.engineeringmode.BtTestFragment.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 100) {
                    String strInfo = (String) msg.obj;
                    String str = BtTestFragment.this.tag;
                    LU.d(str, "handleMessage()   strInfo = " + strInfo);
                    BtTestFragment.this.mtvShowInfo.setText(strInfo);
                }
            }
        };
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bt_test_mode));
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
        this.metSingTyte.getText().clear();
        this.metSingChannel.getText().clear();
        this.metSendTra.getText().clear();
        this.metSendRece.getText().clear();
        this.metSingMode3.getText().clear();
        this.metSingTyte3.getText().clear();
        this.metSingChannel3.getText().clear();
        this.metHciParms.getText().clear();
        this.metHciOgf.getText().clear();
        this.metHciOcf.getText().clear();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LU.d(this.tag, "onDestroy()++++");
        if (this.mTestModeBroadcastReceiver != null) {
            this.mMainActivity.unregisterReceiver(this.mTestModeBroadcastReceiver);
        }
        this.miHciCount = 0;
        this.mbyteHcis = null;
    }

    private void onRegisterReceiver() {
        this.mStringBuffer = new StringBuffer();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.android.hci.command.send.finish");
        filter.addAction(ACTION_BT_HCI_COMPLETE_RECV_CALLBACK);
        filter.addAction(ACTION_BT_LE_COMPLETE_RECV_CALLBACK);
        filter.addAction(ACTION_LINKLOST_STATE_CHANGED);
        if (this.mTestModeBroadcastReceiver == null) {
            this.mTestModeBroadcastReceiver = new TestModeBroadcastReceiver();
        }
        this.mMainActivity.registerReceiver(this.mTestModeBroadcastReceiver, filter);
    }

    private void initView(View view) {
        this.mtvShowInfo = (TextView) view.findViewById(R.id.tv_show_info);
        this.mtvIntoTestMode = (TextView) view.findViewById(R.id.tv_into_testm);
        this.mtvExitTestMode = (TextView) view.findViewById(R.id.tv_exit_testm);
        this.mtvBLETransmitterTest = (TextView) view.findViewById(R.id.tv_ble_tran_t);
        this.mtvBLEReceiverTest = (TextView) view.findViewById(R.id.tv_ble_receiver_t);
        this.mtvBLETestEnd = (TextView) view.findViewById(R.id.tv_ble_test_end);
        this.mtvIntoTestMode.setOnClickListener(this.mbtOnClickListener);
        this.mtvExitTestMode.setOnClickListener(this.mbtOnClickListener);
        this.mtvBLETransmitterTest.setOnClickListener(this.mbtOnClickListener);
        this.mtvBLEReceiverTest.setOnClickListener(this.mbtOnClickListener);
        this.mtvBLETestEnd.setOnClickListener(this.mbtOnClickListener);
        this.mSwitchTestModeConfig = (Switch) view.findViewById(R.id.switch_test_mode_config);
        this.mSwitchTestModeConfig.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        this.mtvTestModeConfigFalse = (TextView) view.findViewById(R.id.tv_test_mode_config_f);
        this.mtvTestModeConfigFalse.setOnClickListener(this.mbtOnClickListener);
        this.mtvSendTra = (TextView) view.findViewById(R.id.tv_send_transmitter);
        this.mtvSendRece = (TextView) view.findViewById(R.id.tv_send_receiver);
        this.mtvSendBleTestEnd = (TextView) view.findViewById(R.id.tv_send_ble_test_end);
        this.mtvSendSing = (TextView) view.findViewById(R.id.tv_send_single_ftt_command);
        this.mtvSendSing3 = (TextView) view.findViewById(R.id.tv_send_single_ftt_command3);
        this.mtvSendTra.setOnClickListener(this.mbtOnClickListener);
        this.mtvSendRece.setOnClickListener(this.mbtOnClickListener);
        this.mtvSendBleTestEnd.setOnClickListener(this.mbtOnClickListener);
        this.mtvSendSing.setOnClickListener(this.mbtOnClickListener);
        this.mtvSendSing3.setOnClickListener(this.mbtOnClickListener);
        this.metSendTra = (EditText) view.findViewById(R.id.et_send_transmitter);
        this.metSendRece = (EditText) view.findViewById(R.id.et_send_receiver);
        this.metSingTyte = (EditText) view.findViewById(R.id.et_send_single_ftt_command_t);
        this.metSingChannel = (EditText) view.findViewById(R.id.et_send_single_ftt_command_c);
        this.metSingMode3 = (EditText) view.findViewById(R.id.et_single_ftt_command_m);
        this.metSingTyte3 = (EditText) view.findViewById(R.id.et_single_ftt_command_t);
        this.metSingChannel3 = (EditText) view.findViewById(R.id.et_single_ftt_command_c);
        this.mtvSendHciNext = (TextView) view.findViewById(R.id.tv_hci_command_parms_next);
        this.mtvSendHciNext.setOnClickListener(this.mbtOnClickListener);
        this.mtvSendHciShow = (TextView) view.findViewById(R.id.tv_hci_command_parms_show);
        this.mtvSendHci = (TextView) view.findViewById(R.id.tv_send_hci_command);
        this.mtvSendHci.setOnClickListener(this.mbtOnClickListener);
        this.metHciOgf = (EditText) view.findViewById(R.id.et_hci_command_ogf);
        this.metHciOcf = (EditText) view.findViewById(R.id.et_hci_command_ocf);
        this.metHciParms = (EditText) view.findViewById(R.id.et_hci_command_parms);
    }

    private void onCheckSelfPermission() {
        if (ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.BLUETOOTH_ADMIN") != 0 || ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.BLUETOOTH") != 0 || ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.READ_CALL_LOG") != 0 || ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.WRITE_CALL_LOG") != 0) {
            ActivityCompat.requestPermissions(this.mMainActivity, new String[]{"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG"}, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes1.dex */
    public class TestModeBroadcastReceiver extends BroadcastReceiver {
        private TestModeBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String str = BtTestFragment.this.tag;
            LU.d(str, "TestModeBroadcastReceiver  onReceive()    action==" + action);
            BtTestFragment.this.handleTestModeRsp(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTestModeRsp(Intent intent) {
        byte[] ret;
        byte[] ret2;
        byte[] ret3;
        String action = intent.getAction();
        if (ACTION_BT_HCI_COMPLETE_RECV_CALLBACK.equals(action)) {
            short opcode = intent.getShortExtra(RESPONSE_TYPE_OPCODE, (short) 0);
            Bundle intentBundleExtra = intent.getBundleExtra(RESPONSE_TYPE_RETURN_PARAMS);
            String str = this.tag;
            LU.d(str, "opcode: " + ((int) opcode));
            StringBuffer stringBuffer = this.mStringBuffer;
            stringBuffer.append("opcode: " + ((int) opcode) + "\r\n");
            if (opcode != 3139) {
                if (opcode == 6147) {
                    if (intentBundleExtra != null) {
                        boolean rsp = false;
                        short len = intent.getShortExtra(RESPONSE_TYPE_PARAMS_LEN, (short) 0);
                        if (len > 0 && (ret2 = intentBundleExtra.getByteArray(RESPONSE_TYPE_RETURN_PARAMS_BUFARRAY)) != null && (ret2[0] & 255) == 0) {
                            rsp = true;
                        }
                        String str2 = this.tag;
                        LU.d(str2, "test mode enable status = " + rsp);
                        StringBuffer stringBuffer2 = this.mStringBuffer;
                        stringBuffer2.append("test mode enable status :  " + rsp + "\r\n");
                    }
                } else if (opcode == 16148 && intentBundleExtra != null) {
                    boolean rsp2 = false;
                    short len2 = intent.getShortExtra(RESPONSE_TYPE_PARAMS_LEN, (short) 0);
                    if (len2 > 0 && (ret3 = intentBundleExtra.getByteArray(RESPONSE_TYPE_RETURN_PARAMS_BUFARRAY)) != null && (ret3[0] & 255) == 0) {
                        rsp2 = true;
                    }
                    String str3 = this.tag;
                    LU.d(str3, "sftt test = " + rsp2);
                    StringBuffer stringBuffer3 = this.mStringBuffer;
                    stringBuffer3.append("sftt test :  " + rsp2 + "\r\n");
                }
            } else if (intentBundleExtra != null) {
                boolean rsp3 = false;
                short len3 = intent.getShortExtra(RESPONSE_TYPE_PARAMS_LEN, (short) 0);
                if (len3 > 0 && (ret = intentBundleExtra.getByteArray(RESPONSE_TYPE_RETURN_PARAMS_BUFARRAY)) != null && (ret[0] & 255) == 0) {
                    rsp3 = true;
                }
                String str4 = this.tag;
                LU.d(str4, "test mode disable status = " + rsp3);
                StringBuffer stringBuffer4 = this.mStringBuffer;
                stringBuffer4.append("test mode disable status :  " + rsp3 + "\r\n");
            }
        } else if (ACTION_BT_LE_COMPLETE_RECV_CALLBACK.equals(action)) {
            int status = intent.getIntExtra(RESPONSE_LE_STATUS, 0);
            short packet_count = intent.getShortExtra(RESPONSE_LE_PACKET_COUNT, (short) 0);
            String str5 = this.tag;
            LU.d(str5, "ble mode status = " + status + ",packet_count = " + ((int) packet_count));
            StringBuffer stringBuffer5 = this.mStringBuffer;
            stringBuffer5.append("ble mode status :  " + status + " ,packet_count : " + ((int) packet_count) + "\r\n");
        }
        Message message = Message.obtain();
        message.what = 100;
        message.obj = this.mStringBuffer.toString();
        String str6 = this.tag;
        LU.d(str6, "handleTestModeRsp()   mStringBuffer.toString() = " + this.mStringBuffer.toString());
        this.mHandler.sendMessageDelayed(message, 0L);
        this.mStringBuffer.append("\r\n");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBleReceiverCommand(int channel) {
        Class clazz = this.mBluetoothAdapter.getClass();
        try {
            Method method = clazz.getMethod("sendBleReceiverCommand", Integer.TYPE);
            method.invoke(this.mBluetoothAdapter, Integer.valueOf(channel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBleTransmitterCommand(int channel) {
        Class clazz = this.mBluetoothAdapter.getClass();
        try {
            Method method = clazz.getMethod("sendBleTransmitterCommand", Integer.TYPE);
            method.invoke(this.mBluetoothAdapter, Integer.valueOf(channel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void testModeConfigure(boolean isB) {
        Class clazz = this.mBluetoothAdapter.getClass();
        try {
            Method method = clazz.getMethod("testModeConfigure", Boolean.TYPE);
            method.invoke(this.mBluetoothAdapter, Boolean.valueOf(isB));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSingleFTTCommand(int type, int channel) {
        Class clazz = this.mBluetoothAdapter.getClass();
        try {
            Method method = clazz.getDeclaredMethod("sendSingleFTTCommand", Integer.TYPE, Integer.TYPE);
            method.invoke(this.mBluetoothAdapter, Integer.valueOf(type), Integer.valueOf(channel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSingleFTTCommand(int mode, int type, int channel) {
        Class clazz = this.mBluetoothAdapter.getClass();
        try {
            Method method = clazz.getDeclaredMethod("sendSingleFTTCommand", Integer.TYPE, Integer.TYPE, Integer.TYPE);
            method.invoke(this.mBluetoothAdapter, Integer.valueOf(mode), Integer.valueOf(type), Integer.valueOf(channel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendHciCommand(byte ogf, byte ocf, byte[] parms) {
        Class clazz = this.mBluetoothAdapter.getClass();
        try {
            Method method = clazz.getDeclaredMethod("sendHciCommand", Byte.TYPE, Byte.TYPE, byte[].class);
            method.invoke(this.mBluetoothAdapter, Byte.valueOf(ogf), Byte.valueOf(ocf), parms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBleTestEndCommand() {
        Class clazz = this.mBluetoothAdapter.getClass();
        try {
            Method method = clazz.getMethod("sendBleTestEndCommand", new Class[0]);
            method.invoke(this.mBluetoothAdapter, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
