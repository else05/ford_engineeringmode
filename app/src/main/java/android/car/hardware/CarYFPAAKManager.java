package android.car.hardware;

import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.property.CarPropertyManagerBase;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public final class CarYFPAAKManager implements CarManagerBase {
    private static final boolean DBG = true;
    public static final int ID_BACKUPIGNITION_RQ = 292574212;
    public static final int ID_BACKUPIGNITION_RSP = 292574211;
    public static final int ID_IGNPSSWRDDSPLY_B_RQ = 292574215;
    public static final int ID_IGNPSSWRDLCKOUT_B_STAT = 292574214;
    public static final int ID_IGNPSSWRDSETUP_RQ = 292574213;
    public static final int ID_MODEMRESET_RQ = 292574208;
    public static final int ID_MODEMRESET_STAT = 292574209;
    public static final int ID_PAAKESN_ST = 292574210;
    private static final String TAG = CarYFPAAKManager.class.getSimpleName();
    public static final byte rspcodeCKEVM = 5;
    public static final byte rspcodeIC = 1;
    public static final byte rspcodeSCPwithPR = 3;
    public static final byte rspcodeSCPwithoutPR = 4;
    public static final byte rspcodeVCCRA = 10;
    @GuardedBy("mLock")
    private ArraySet<CarYFPAAKManagerCallback> mCallbacks;
    private final Object mLock = new Object();
    private final CarPropertyManagerBase mPropertyManager;

    /* loaded from: classes2.dex */
    public interface CarYFPAAKManagerCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    public CarYFPAAKManager(IBinder service, Handler handler) {
        this.mPropertyManager = new CarPropertyManagerBase(service, handler, true, TAG);
        Log.i(TAG, "CarYFPAAKManager");
    }

    public void registerCallback(CarYFPAAKManagerCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mCallbacks == null) {
                this.mPropertyManager.registerCallback(new CarPropertyManagerBase.CarPropertyEventCallback() { // from class: android.car.hardware.CarYFPAAKManager.1
                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onChangeEvent(CarPropertyValue value) {
                        CarYFPAAKManagerCallback[] callbacks;
                        Log.i(CarYFPAAKManager.TAG, "CarYFPAAKManagerCallback");
                        for (CarYFPAAKManagerCallback listener : CarYFPAAKManager.this.getCallbacks()) {
                            listener.onChangeEvent(value);
                        }
                    }

                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onErrorEvent(int propertyId, int zone) {
                        CarYFPAAKManagerCallback[] callbacks;
                        for (CarYFPAAKManagerCallback listener : CarYFPAAKManager.this.getCallbacks()) {
                            listener.onErrorEvent(propertyId, zone);
                        }
                    }
                });
                this.mCallbacks = new ArraySet<>(1);
            }
            this.mCallbacks.add(callback);
        }
    }

    public void unregisterCallback(CarYFPAAKManagerCallback callback) {
        synchronized (this.mLock) {
            this.mCallbacks.remove(callback);
            if (this.mCallbacks.isEmpty()) {
                this.mPropertyManager.unregisterCallback();
                this.mCallbacks = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CarYFPAAKManagerCallback[] getCallbacks() {
        CarYFPAAKManagerCallback[] carYFPAAKManagerCallbackArr;
        synchronized (this.mLock) {
            carYFPAAKManagerCallbackArr = (CarYFPAAKManagerCallback[]) this.mCallbacks.toArray(new CarYFPAAKManagerCallback[this.mCallbacks.size()]);
        }
        return carYFPAAKManagerCallbackArr;
    }

    public List<CarPropertyConfig> getProperties() throws CarNotConnectedException {
        return this.mPropertyManager.getPropertyList();
    }

    public <E> E getGlobalProperty(Class<E> propertyClass, int propId) throws CarNotConnectedException {
        return (E) getProperty(propertyClass, propId, 0);
    }

    public <E> E getProperty(Class<E> propertyClass, int propId, int area) throws CarNotConnectedException {
        return this.mPropertyManager.getProperty(propertyClass, propId, area).getValue();
    }

    public <E> void setGlobalProperty(Class<E> propertyClass, int propId, E value) throws CarNotConnectedException {
        Log.i(TAG, "set YFPAAK prop");
        this.mPropertyManager.setProperty(propertyClass, propId, 0, value);
    }

    public <E> void setProperty(Class<E> propertyClass, int propId, int area, E value) throws CarNotConnectedException {
        this.mPropertyManager.setProperty(propertyClass, propId, area, value);
    }

    public BackupIgnitionRspObject getTPbackupIgnition_Rsp(byte[] backupIgnition_Rsp) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        BackupIgnitionRspObject bo = new BackupIgnitionRspObject();
        if (backupIgnition_Rsp.length >= 6) {
            boolean z5 = false;
            bo.setSignalId(backupIgnition_Rsp[0]);
            bo.setUtilization(backupIgnition_Rsp[1]);
            bo.setCes(backupIgnition_Rsp[2]);
            bo.setCharacterCoding(backupIgnition_Rsp[3]);
            bo.setRspCode(backupIgnition_Rsp[4]);
            bo.setRspStatus(backupIgnition_Rsp[5]);
            if (backupIgnition_Rsp[4] != 5) {
                z = false;
            } else {
                z = true;
            }
            if (backupIgnition_Rsp[4] != 10) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (!(z | z2)) {
                if (backupIgnition_Rsp[4] != 1) {
                    if (backupIgnition_Rsp[4] != 3) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    if (backupIgnition_Rsp[4] != 4) {
                        z4 = false;
                    } else {
                        z4 = true;
                    }
                    boolean z6 = z3 | z4;
                    if (backupIgnition_Rsp.length > 23) {
                        z5 = true;
                    }
                    if (z5 & z6) {
                        bo.setSalt2(backupIgnition_Rsp);
                        bo.setNumberOfItems(backupIgnition_Rsp[22]);
                        byte[] vector = Arrays.copyOfRange(backupIgnition_Rsp, 23, backupIgnition_Rsp.length);
                        bo.setPhoneVector(parsePhoneVector(vector));
                    }
                } else {
                    bo.setChallengeNonce(backupIgnition_Rsp);
                    bo.setSalt1(backupIgnition_Rsp);
                }
            } else {
                bo.setValetPassword(backupIgnition_Rsp);
            }
        }
        return bo;
    }

    private ArrayList<String> parsePhoneVector(byte[] rsp) {
        ArrayList<String> itemList = new ArrayList<>();
        String vecStr = new String(rsp);
        String[] vecSplit = vecStr.split(String.valueOf((char) 0));
        for (String str : vecSplit) {
            char[] item = str.toCharArray();
            if (item.length > 2) {
                StringBuilder builder = new StringBuilder();
                builder.append("ItemIndex: ");
                builder.append(Integer.toHexString((byte) item[0]));
                builder.append(" KeyIndex: ");
                builder.append(Integer.toHexString((byte) item[1]));
                builder.append(" PhoneName: ");
                for (int j = 2; j < item.length; j++) {
                    builder.append(item[j]);
                }
                itemList.add(builder.toString());
                Log.i(TAG + "parsePhoneVector", builder.toString());
            }
        }
        return itemList;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00de A[Catch: NoSuchAlgorithmException -> 0x00ea, TRY_LEAVE, TryCatch #0 {NoSuchAlgorithmException -> 0x00ea, blocks: (B:13:0x009b, B:25:0x00b9, B:29:0x00db, B:31:0x00de, B:26:0x00be, B:27:0x00c7), top: B:42:0x009b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] TPbackupIgnition_Rq(byte r8, byte r9, java.lang.String r10, byte[] r11, byte[] r12) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.car.hardware.CarYFPAAKManager.TPbackupIgnition_Rq(byte, byte, java.lang.String, byte[], byte[]):byte[]");
    }

    public static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[0];
        if (data1 == null || data2 == null) {
            return data3;
        }
        byte[] data32 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data32, 0, data1.length);
        System.arraycopy(data2, 0, data32, data1.length, data2.length);
        return data32;
    }

    public byte[] TPbackupIgnition_Rq(byte opCode) {
        byte[] defaultSalt = {0};
        byte[] defaultNonce = {0};
        return TPbackupIgnition_Rq(opCode, (byte) 0, "", defaultSalt, defaultNonce);
    }

    public byte[] keypadCode2bytes(String pwd) {
        byte[] bArr = new byte[2];
        char[] pwdChar = pwd.toCharArray();
        StringBuilder builder = new StringBuilder();
        builder.append("0");
        for (char c : pwdChar) {
            switch (c) {
                case '0':
                case '9':
                    builder.append("101");
                    break;
                case '1':
                case '2':
                    builder.append("001");
                    break;
                case '3':
                case '4':
                    builder.append("010");
                    break;
                case '5':
                case '6':
                    builder.append("011");
                    break;
                case '7':
                case '8':
                    builder.append("100");
                    break;
            }
        }
        String binaryString = builder.toString();
        Log.i(TAG, binaryString);
        byte[] pwdBytes = binStrToByteArr(binaryString);
        return pwdBytes;
    }

    private byte[] binStrToByteArr(String binaryString) {
        int len = binaryString.length() / 8;
        String[] subs = new String[len];
        for (int j = 0; j < len; j++) {
            int j2 = j * 8;
            if (j2 + 8 <= binaryString.length()) {
                subs[j] = binaryString.substring(j2, j2 + 8);
            }
        }
        int i = subs.length;
        byte[] b = new byte[i];
        for (int i2 = 0; i2 < b.length; i2++) {
            b[i2] = Long.valueOf(subs[i2], 2).byteValue();
        }
        return b;
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mPropertyManager.onCarDisconnected();
    }
}
