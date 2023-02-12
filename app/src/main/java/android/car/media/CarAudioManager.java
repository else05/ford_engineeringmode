package android.car.media;

import android.car.Car;
import android.car.CarLibLog;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.media.ICarAudio;
import android.car.media.ICarAudioCallback;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.IVolumeController;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public final class CarAudioManager implements CarManagerBase {
    public static final int CAR_AUDIO_USAGE_ALARM = 6;
    public static final int CAR_AUDIO_USAGE_DEFAULT = 0;
    public static final int CAR_AUDIO_USAGE_EXTERNAL_AUDIO_SOURCE = 11;
    public static final int CAR_AUDIO_USAGE_MAX = 11;
    public static final int CAR_AUDIO_USAGE_MUSIC = 1;
    public static final int CAR_AUDIO_USAGE_NAVIGATION_GUIDANCE = 3;
    public static final int CAR_AUDIO_USAGE_NOTIFICATION = 7;
    public static final int CAR_AUDIO_USAGE_RADIO = 2;
    public static final int CAR_AUDIO_USAGE_RINGTONE = 10;
    public static final int CAR_AUDIO_USAGE_SYSTEM_SAFETY_ALERT = 9;
    public static final int CAR_AUDIO_USAGE_SYSTEM_SOUND = 8;
    public static final int CAR_AUDIO_USAGE_VOICE_CALL = 4;
    public static final int CAR_AUDIO_USAGE_VOICE_COMMAND = 5;
    public static final String CAR_EXTERNAL_SOURCE_TYPE_AUX_IN0 = "AUX_IN0";
    public static final String CAR_EXTERNAL_SOURCE_TYPE_AUX_IN1 = "AUX_IN1";
    public static final String CAR_EXTERNAL_SOURCE_TYPE_CD_DVD = "CD_DVD";
    public static final String CAR_EXTERNAL_SOURCE_TYPE_EXT_NAV_GUIDANCE = "EXT_NAV_GUIDANCE";
    public static final String CAR_EXTERNAL_SOURCE_TYPE_EXT_SAFETY_ALERT = "EXT_SAFETY_ALERT";
    public static final String CAR_EXTERNAL_SOURCE_TYPE_EXT_VOICE_CALL = "EXT_VOICE_CALL";
    public static final String CAR_EXTERNAL_SOURCE_TYPE_EXT_VOICE_COMMAND = "EXT_VOICE_COMMAND";
    public static final String CAR_RADIO_TYPE_AM_FM = "RADIO_AM_FM";
    public static final String CAR_RADIO_TYPE_AM_FM_HD = "RADIO_AM_FM_HD";
    public static final String CAR_RADIO_TYPE_DAB = "RADIO_DAB";
    public static final String CAR_RADIO_TYPE_SATELLITE = "RADIO_SATELLITE";
    private final AudioManager mAudioManager;
    private final Handler mHandler;
    private OnParameterChangeListener mOnParameterChangeListener;
    private ParameterChangeCallback mParameterChangeCallback;
    private final ICarAudio mService;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface CarAudioUsage {
    }

    /* loaded from: classes2.dex */
    public interface OnParameterChangeListener {
        void onParameterChange(String str);
    }

    public AudioAttributes getAudioAttributesForCarUsage(int carUsage) throws CarNotConnectedException {
        try {
            return this.mService.getAudioAttributesForCarUsage(carUsage);
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        }
    }

    public AudioAttributes getAudioAttributesForRadio(String radioType) throws CarNotConnectedException, IllegalArgumentException {
        try {
            return this.mService.getAudioAttributesForRadio(radioType);
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        }
    }

    public AudioAttributes getAudioAttributesForExternalSource(String externalSourceType) throws CarNotConnectedException, IllegalArgumentException {
        try {
            return this.mService.getAudioAttributesForExternalSource(externalSourceType);
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        }
    }

    public String[] getSupportedExternalSourceTypes() throws CarNotConnectedException {
        try {
            return this.mService.getSupportedExternalSourceTypes();
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        }
    }

    public String[] getSupportedRadioTypes() throws CarNotConnectedException {
        try {
            return this.mService.getSupportedRadioTypes();
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        }
    }

    public int requestAudioFocus(AudioManager.OnAudioFocusChangeListener l, AudioAttributes requestAttributes, int durationHint, int flags) throws CarNotConnectedException, IllegalArgumentException {
        return this.mAudioManager.requestAudioFocus(l, requestAttributes, durationHint, flags);
    }

    public void abandonAudioFocus(AudioManager.OnAudioFocusChangeListener l, AudioAttributes aa) {
        this.mAudioManager.abandonAudioFocus(l, aa);
    }

    public void setStreamVolume(int streamType, int index, int flags) throws CarNotConnectedException {
        try {
            this.mService.setStreamVolume(streamType, index, flags);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "setStreamVolume failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public void setVolumeController(IVolumeController controller) throws CarNotConnectedException {
        try {
            this.mService.setVolumeController(controller);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "setVolumeController failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public int getStreamMaxVolume(int stream) throws CarNotConnectedException {
        try {
            return this.mService.getStreamMaxVolume(stream);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getStreamMaxVolume failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public int getStreamMinVolume(int stream) throws CarNotConnectedException {
        try {
            return this.mService.getStreamMinVolume(stream);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getStreamMaxVolume failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public int getStreamVolume(int stream) throws CarNotConnectedException {
        try {
            return this.mService.getStreamVolume(stream);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getStreamVolume failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public boolean isMediaMuted() throws CarNotConnectedException {
        try {
            return this.mService.isMediaMuted();
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "isMediaMuted failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public boolean setMediaMute(boolean mute) throws CarNotConnectedException {
        try {
            return this.mService.setMediaMute(mute);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "setMediaMute failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public String[] getParameterKeys() throws CarNotConnectedException {
        try {
            return this.mService.getParameterKeys();
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getParameterKeys failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public void setParameters(String parameters) throws CarNotConnectedException {
        try {
            this.mService.setParameters(parameters);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "setParameters failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public String getParameters(String keys) throws CarNotConnectedException {
        try {
            return this.mService.getParameters(keys);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getParameters failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public void setOnParameterChangeListener(OnParameterChangeListener listener) throws CarNotConnectedException {
        ParameterChangeCallback oldCb = null;
        ParameterChangeCallback newCb = null;
        synchronized (this) {
            if (listener != null) {
                try {
                    if (this.mParameterChangeCallback != null) {
                        oldCb = this.mParameterChangeCallback;
                    }
                    newCb = new ParameterChangeCallback();
                } finally {
                }
            }
            this.mParameterChangeCallback = newCb;
            this.mOnParameterChangeListener = listener;
        }
        if (oldCb != null) {
            try {
                this.mService.unregisterOnParameterChangeListener(oldCb);
            } catch (RemoteException e) {
                Log.e(CarLibLog.TAG_CAR, "setOnParameterChangeListener failed", e);
                throw new CarNotConnectedException(e);
            }
        }
        if (newCb != null) {
            this.mService.registerOnParameterChangeListener(newCb);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public CarAudioManager(IBinder service, Context context, Handler handler) {
        this.mService = ICarAudio.Stub.asInterface(service);
        this.mAudioManager = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
        this.mHandler = handler;
    }

    private AudioAttributes createAudioAttributes(int contentType, int usage) {
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        return builder.setContentType(contentType).setUsage(usage).build();
    }

    /* loaded from: classes2.dex */
    private static class ParameterChangeCallback extends ICarAudioCallback.Stub {
        private final WeakReference<CarAudioManager> mManager;

        private ParameterChangeCallback(CarAudioManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        @Override // android.car.media.ICarAudioCallback
        public void onParameterChange(final String params) {
            final OnParameterChangeListener listener;
            CarAudioManager manager = this.mManager.get();
            if (manager != null && (listener = manager.mOnParameterChangeListener) != null) {
                manager.mHandler.post(new Runnable() { // from class: android.car.media.CarAudioManager.ParameterChangeCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        listener.onParameterChange(params);
                    }
                });
            }
        }
    }
}
