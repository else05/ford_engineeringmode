package android.car.cluster;

import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.cluster.IInstrumentClusterManagerCallback;
import android.car.cluster.IInstrumentClusterManagerService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class CarInstrumentClusterManager implements CarManagerBase {
    public static final String CATEGORY_NAVIGATION = "android.car.cluster.NAVIGATION";
    public static final String KEY_EXTRA_ACTIVITY_STATE = "android.car.cluster.ClusterActivityState";
    private static final String TAG = CarInstrumentClusterManager.class.getSimpleName();
    private final EventHandler mHandler;
    private final IInstrumentClusterManagerService mService;
    private ClusterManagerCallback mServiceToManagerCallback;
    private final Map<String, Set<Callback>> mCallbacksByCategory = new HashMap(0);
    private final Object mLock = new Object();
    private final Map<String, Bundle> mActivityStatesByCategory = new HashMap(0);

    /* loaded from: classes2.dex */
    public interface Callback {
        void onClusterActivityStateChanged(String str, Bundle bundle);
    }

    public void startActivity(Intent intent) throws CarNotConnectedException {
        try {
            this.mService.startClusterActivity(intent);
        } catch (RemoteException e) {
            throw new CarNotConnectedException(e);
        }
    }

    public void registerCallback(String category, Callback callback) throws CarNotConnectedException {
        String str = TAG;
        Log.i(str, "registerCallback, category: " + category + ", callback: " + callback);
        ClusterManagerCallback callbackToCarService = null;
        synchronized (this.mLock) {
            Set<Callback> callbacks = this.mCallbacksByCategory.get(category);
            if (callbacks == null) {
                callbacks = new HashSet(1);
                this.mCallbacksByCategory.put(category, callbacks);
            }
            if (!callbacks.add(callback)) {
                Log.w(TAG, "registerCallback: already registered");
                return;
            }
            if (this.mActivityStatesByCategory.containsKey(category)) {
                Log.i(TAG, "registerCallback: sending activity state...");
                callback.onClusterActivityStateChanged(category, this.mActivityStatesByCategory.get(category));
            }
            if (this.mServiceToManagerCallback == null) {
                Log.i(TAG, "registerCallback: registering callback with car service...");
                this.mServiceToManagerCallback = new ClusterManagerCallback();
                callbackToCarService = this.mServiceToManagerCallback;
            }
            try {
                this.mService.registerCallback(callbackToCarService);
                Log.i(TAG, "registerCallback: done");
            } catch (RemoteException e) {
                throw new CarNotConnectedException(e);
            }
        }
    }

    public void unregisterCallback(Callback callback) throws CarNotConnectedException {
        List<String> keysToRemove = new ArrayList<>(1);
        synchronized (this.mLock) {
            for (Map.Entry<String, Set<Callback>> entry : this.mCallbacksByCategory.entrySet()) {
                Set<Callback> callbacks = entry.getValue();
                if (callbacks.remove(callback) && callbacks.isEmpty()) {
                    keysToRemove.add(entry.getKey());
                }
            }
            for (String key : keysToRemove) {
                this.mCallbacksByCategory.remove(key);
            }
            if (this.mCallbacksByCategory.isEmpty()) {
                try {
                    this.mService.unregisterCallback(this.mServiceToManagerCallback);
                    this.mServiceToManagerCallback = null;
                } catch (RemoteException e) {
                    throw new CarNotConnectedException(e);
                }
            }
        }
    }

    public CarInstrumentClusterManager(IBinder service, Handler handler) {
        this.mService = IInstrumentClusterManagerService.Stub.asInterface(service);
        this.mHandler = new EventHandler(handler.getLooper());
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    /* loaded from: classes2.dex */
    private class EventHandler extends Handler {
        static final int MSG_ACTIVITY_STATE = 1;

        EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            String str = CarInstrumentClusterManager.TAG;
            Log.i(str, "handleMessage, message: " + msg);
            if (msg.what != 1) {
                String str2 = CarInstrumentClusterManager.TAG;
                Log.e(str2, "Unexpected message: " + msg.what);
                return;
            }
            Pair<String, Bundle> info = (Pair) msg.obj;
            String category = (String) info.first;
            Bundle state = (Bundle) info.second;
            List<Callback> callbacks = null;
            synchronized (CarInstrumentClusterManager.this.mLock) {
                if (CarInstrumentClusterManager.this.mCallbacksByCategory.containsKey(category)) {
                    callbacks = new ArrayList<>((Collection) CarInstrumentClusterManager.this.mCallbacksByCategory.get(category));
                }
            }
            String str3 = CarInstrumentClusterManager.TAG;
            Log.i(str3, "handleMessage, callbacks: " + callbacks);
            if (callbacks != null) {
                for (Callback cb : callbacks) {
                    cb.onClusterActivityStateChanged(category, state);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    private class ClusterManagerCallback extends IInstrumentClusterManagerCallback.Stub {
        private ClusterManagerCallback() {
        }

        @Override // android.car.cluster.IInstrumentClusterManagerCallback
        public void setClusterActivityState(String category, Bundle clusterActivityState) throws RemoteException {
            String str = CarInstrumentClusterManager.TAG;
            Log.i(str, "setClusterActivityState, category: " + category);
            synchronized (CarInstrumentClusterManager.this.mLock) {
                CarInstrumentClusterManager.this.mActivityStatesByCategory.put(category, clusterActivityState);
            }
            CarInstrumentClusterManager.this.mHandler.sendMessage(CarInstrumentClusterManager.this.mHandler.obtainMessage(1, new Pair(category, clusterActivityState)));
        }
    }
}
