package android.car.hardware;

import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.property.CarPropertyManagerBase;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.util.List;

/* loaded from: classes2.dex */
public final class CarYFSettingManager implements CarManagerBase {
    public static final byte CANCLE = 6;
    public static final byte CHECK_CODE = 0;
    private static final boolean DBG = true;
    public static final byte EARSE_CODE = 1;
    public static final byte FRQ_OPERATION_COPY = 5;
    public static final byte FRQ_OPERATION_NULL = 0;
    public static final byte FRQ_OPERATION_QUERY = 1;
    public static final byte FRQ_OPERATION_RESTORE = 4;
    public static final byte FRQ_OPERATION_SET = 2;
    public static final byte FRQ_OPERATION_UPLOAD = 3;
    public static final byte FRQ_PERSINDEX_RQ_NOT_USED = 5;
    public static final byte FRQ_PERSINDEX_RQ_PERS1 = 0;
    public static final byte FRQ_PERSINDEX_RQ_PERS2 = 1;
    public static final byte FRQ_PERSINDEX_RQ_PERS3 = 2;
    public static final byte FRQ_PERSINDEX_RQ_PERS4 = 3;
    public static final byte FRQ_PERSINDEX_RQ_VEHICLE = 4;
    public static final int ID_3EC_ReCallCountAndProfile = 292582949;
    public static final int ID_ASLICONDSPLY_D_RQ = 292557100;
    public static final int ID_ASSOC_CONFIRM_D_ACTL = 292565299;
    public static final int ID_AUTOHOLDSWMDE_B_IND = 292565301;
    public static final int ID_AUTOHOLDSWTCH_D_STAT3 = 292565300;
    public static final int ID_ActivePersonality_St = 292582918;
    public static final int ID_BCM_ACTL = 292565256;
    public static final int ID_BTTRIGHT_D_RQDRV = 292565294;
    public static final int ID_CCM_ACTL = 292565282;
    public static final int ID_CCM_message_missing = 289419791;
    public static final int ID_CNTR_STK_D_RQ_ASSOC = 292565298;
    public static final int ID_CNTR_STK_KEYCODE_ACTL = 292565297;
    public static final int ID_CntrStk_D_RqRecall = 292582915;
    public static final int ID_DDM_ACTL = 292565285;
    public static final int ID_DSM_ACTL = 292565264;
    public static final int ID_DriverWindowPosition = 289437198;
    public static final int ID_DrvAsstMnuButtn_B_Stat = 292565304;
    public static final int ID_Em_D_Stat = 292582913;
    public static final int ID_EnMemButtonPairing_St = 292582919;
    public static final int ID_EnMemKeyPairing_St = 292582920;
    public static final int ID_EnMemProfilePairing_Rq = 292582912;
    public static final int ID_FCIM_ACTL = 292565269;
    public static final int ID_FEATURE_RQ = 292565248;
    public static final int ID_HCM_ACTL = 292565267;
    public static final int ID_IACCLAMP_D_RQ = 292565290;
    public static final int ID_IPC_ACTL = 292565252;
    public static final int ID_IPMA_ACTL = 292565260;
    public static final int ID_InfotainmentPersStore_St = 292582921;
    public static final int ID_MC_VEHUNTTRPCOUSRSEL_ST = 292565289;
    public static final int ID_PMCABN02MNTE_CONC_ACTL = 289419776;
    public static final int ID_PMCABN04MNTE_CONC_ACTL = 289419777;
    public static final int ID_PMCABN06MNTE_CONC_ACTL = 289419778;
    public static final int ID_PMCABN08MNTE_CONC_ACTL = 289419779;
    public static final int ID_PMCABN10MNTE_CONC_ACTL = 289419780;
    public static final int ID_PMCABN12MNTE_CONC_ACTL = 289419781;
    public static final int ID_PMCABN14MNTE_CONC_ACTL = 289419782;
    public static final int ID_PMCABN16MNTE_CONC_ACTL = 289419783;
    public static final int ID_PMCABN18MNTE_CONC_ACTL = 289419784;
    public static final int ID_PMCABN20MNTE_CONC_ACTL = 289419785;
    public static final int ID_PMCABNLVL_D_STAT = 289419789;
    public static final int ID_PMCABN_CONC_ACTL = 289419786;
    public static final int ID_PMCABN_D_STAT = 289419788;
    public static final int ID_PMSNSCABN_D_STAT = 289419787;
    public static final int ID_PM_message_missing = 289419790;
    public static final int ID_PaakConnection_St = 292582925;
    public static final int ID_PassWindowPosition = 289437200;
    public static final int ID_PersKeyPairing_St = 292582923;
    public static final int ID_PersPhonePairing_St = 292582924;
    public static final int ID_PersStore_D_Rq = 292582914;
    public static final int ID_PersonalityOptIn_St = 292582916;
    public static final int ID_PersonalityRecallCount_St = 292582922;
    public static final int ID_RBA_D_RQ = 292565302;
    public static final int ID_RBA_D_STAT = 292565303;
    public static final int ID_RearAudioCtLck_D_Rq = 289437208;
    public static final int ID_RearAudioCtlck_D_Stat = 289437209;
    public static final int ID_RearDriverWindowPos = 289437199;
    public static final int ID_RearPassWindowPos = 289437201;
    public static final int ID_RstrnImpactEvntStatus = 292582952;
    public static final int ID_SCCM_ACTL = 292565272;
    public static final int ID_SDARS_FactoryReset_Rq = 292582917;
    public static final int ID_SLMDE_D_RQDSPLY = 292565291;
    public static final int ID_SUMA_ACTL = 292565275;
    public static final int ID_StopStrtDrvMde_B_Indic = 292582945;
    public static final int ID_StopStrtDrvMde_B_Rq = 292582944;
    public static final int ID_SurndSndUpmix2_D_Rq = 289437206;
    public static final int ID_SurndSndUpmix2_D_Stat = 289437207;
    public static final int ID_TSRVLUNITMSGTXT_D_RQ = 292565288;
    public static final int ID_VDM_ACTL = 292565279;
    public static final int ID_VEHVACTLENG_D_QF = 292565296;
    public static final int ID_VEH_EMERGENCY_DATA1 = 292582950;
    public static final int ID_VEH_EMERGENCY_DATA2 = 292582951;
    public static final int ID_VEH_V_ACTLENG = 292565295;
    public static final byte KEY = 2;
    public static final byte NOT_USED = 7;
    public static final byte NULL = 3;
    public static final byte RKE = 4;
    public static final byte SET_KEYCODE = 5;
    private static final String TAG = CarYFSettingManager.class.getSimpleName();
    @GuardedBy("mLock")
    private ArraySet<CarYFSettingCallback> mCallbacks;
    private final Object mLock = new Object();
    private final CarPropertyManagerBase mPropertyManager;

    /* loaded from: classes2.dex */
    public interface CarYFSettingCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    public CarYFSettingManager(IBinder service, Handler handler) {
        this.mPropertyManager = new CarPropertyManagerBase(service, handler, true, TAG);
        Log.i(TAG, "CarYFSettingManager");
    }

    public void registerCallback(CarYFSettingCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mCallbacks == null) {
                this.mPropertyManager.registerCallback(new CarPropertyManagerBase.CarPropertyEventCallback() { // from class: android.car.hardware.CarYFSettingManager.1
                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onChangeEvent(CarPropertyValue value) {
                        CarYFSettingCallback[] callbacks;
                        Log.i(CarYFSettingManager.TAG, "CarYFSettingCallBack Prop: " + value.getPropertyId());
                        for (CarYFSettingCallback listener : CarYFSettingManager.this.getCallbacks()) {
                            listener.onChangeEvent(value);
                        }
                    }

                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onErrorEvent(int propertyId, int zone) {
                        CarYFSettingCallback[] callbacks;
                        for (CarYFSettingCallback listener : CarYFSettingManager.this.getCallbacks()) {
                            listener.onErrorEvent(propertyId, zone);
                        }
                    }
                });
                this.mCallbacks = new ArraySet<>(1);
            }
            this.mCallbacks.add(callback);
        }
    }

    public void unregisterCallback(CarYFSettingCallback callback) {
        synchronized (this.mLock) {
            this.mCallbacks.remove(callback);
            if (this.mCallbacks.isEmpty()) {
                this.mPropertyManager.unregisterCallback();
                this.mCallbacks = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CarYFSettingCallback[] getCallbacks() {
        CarYFSettingCallback[] carYFSettingCallbackArr;
        synchronized (this.mLock) {
            carYFSettingCallbackArr = (CarYFSettingCallback[]) this.mCallbacks.toArray(new CarYFSettingCallback[this.mCallbacks.size()]);
        }
        return carYFSettingCallbackArr;
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
        String str = TAG;
        Log.i(str, "set YFSetting Property: " + propId);
        this.mPropertyManager.setProperty(propertyClass, propId, 0, value);
    }

    public <E> void setProperty(Class<E> propertyClass, int propId, int area, E value) throws CarNotConnectedException {
        this.mPropertyManager.setProperty(propertyClass, propId, area, value);
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
            subs[j] = binaryString.substring(j2, j2 + 8);
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
