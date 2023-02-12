package android.car.input;

import android.app.Service;
import android.car.input.ICarInputListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public abstract class CarInputHandlingService extends Service {
    private static final boolean DBG = false;
    public static final int INPUT_CALLBACK_BINDER_CODE = 1;
    public static final String INPUT_CALLBACK_BINDER_KEY = "callback_binder";
    private static final String TAG = "CAR.L.INPUT";
    private final InputFilter[] mHandledKeys;
    private InputBinder mInputBinder;

    protected abstract void onKeyEvent(KeyEvent keyEvent, int i);

    protected CarInputHandlingService(InputFilter[] handledKeys) {
        if (handledKeys == null) {
            throw new IllegalArgumentException("handledKeys is null");
        }
        this.mHandledKeys = new InputFilter[handledKeys.length];
        System.arraycopy(handledKeys, 0, this.mHandledKeys, 0, handledKeys.length);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        doCallbackIfPossible(intent.getExtras());
        if (this.mInputBinder == null) {
            this.mInputBinder = new InputBinder();
        }
        return this.mInputBinder;
    }

    private void doCallbackIfPossible(Bundle extras) {
        if (extras == null) {
            Log.i("CAR.L.INPUT", "doCallbackIfPossible: extras are null");
            return;
        }
        IBinder callbackBinder = extras.getBinder(INPUT_CALLBACK_BINDER_KEY);
        if (callbackBinder == null) {
            Log.i("CAR.L.INPUT", "doCallbackIfPossible: callback IBinder is null");
            return;
        }
        Parcel dataIn = Parcel.obtain();
        dataIn.writeTypedArray(this.mHandledKeys, 0);
        try {
            callbackBinder.transact(1, dataIn, null, 1);
        } catch (RemoteException e) {
            Log.e("CAR.L.INPUT", "doCallbackIfPossible: callback failed", e);
        }
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.println("**" + getClass().getSimpleName() + "**");
        StringBuilder sb = new StringBuilder();
        sb.append("input binder: ");
        sb.append(this.mInputBinder);
        writer.println(sb.toString());
    }

    /* loaded from: classes2.dex */
    private class InputBinder extends ICarInputListener.Stub {
        private final EventHandler mEventHandler;

        InputBinder() {
            this.mEventHandler = new EventHandler(CarInputHandlingService.this);
        }

        @Override // android.car.input.ICarInputListener
        public void onKeyEvent(KeyEvent keyEvent, int targetDisplay) throws RemoteException {
            this.mEventHandler.doKeyEvent(keyEvent, targetDisplay);
        }
    }

    /* loaded from: classes2.dex */
    private static class EventHandler extends Handler {
        private static final int KEY_EVENT = 0;
        private final WeakReference<CarInputHandlingService> mRefService;

        EventHandler(CarInputHandlingService service) {
            this.mRefService = new WeakReference<>(service);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            CarInputHandlingService service = this.mRefService.get();
            if (service == null) {
                return;
            }
            if (msg.what == 0) {
                service.onKeyEvent((KeyEvent) msg.obj, msg.arg1);
                return;
            }
            throw new IllegalArgumentException("Unexpected message: " + msg);
        }

        void doKeyEvent(KeyEvent event, int targetDisplay) {
            sendMessage(obtainMessage(0, targetDisplay, 0, event));
        }
    }

    /* loaded from: classes2.dex */
    public static class InputFilter implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: android.car.input.CarInputHandlingService.InputFilter.1
            @Override // android.os.Parcelable.Creator
            public InputFilter createFromParcel(Parcel in) {
                return new InputFilter(in);
            }

            @Override // android.os.Parcelable.Creator
            public InputFilter[] newArray(int size) {
                return new InputFilter[size];
            }
        };
        public final int mKeyCode;
        public final int mTargetDisplay;

        public InputFilter(int keyCode, int targetDisplay) {
            this.mKeyCode = keyCode;
            this.mTargetDisplay = targetDisplay;
        }

        InputFilter(Parcel in) {
            this.mKeyCode = in.readInt();
            this.mTargetDisplay = in.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mKeyCode);
            dest.writeInt(this.mTargetDisplay);
        }
    }
}
