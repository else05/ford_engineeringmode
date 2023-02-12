package android.car.settings;

import android.car.settings.CarSettings;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public abstract class GarageModeSettingsObserver extends ContentObserver {
    private final WeakReference<Context> mContext;
    public static final Uri GARAGE_MODE_ENABLED_URI = Settings.Global.getUriFor(CarSettings.Global.KEY_GARAGE_MODE_ENABLED);
    public static final Uri GARAGE_MODE_WAKE_UP_TIME_URI = Settings.Global.getUriFor(CarSettings.Global.KEY_GARAGE_MODE_WAKE_UP_TIME);
    public static final Uri GARAGE_MODE_MAINTENANCE_WINDOW_URI = Settings.Global.getUriFor(CarSettings.Global.KEY_GARAGE_MODE_MAINTENANCE_WINDOW);
    public static final Uri[] GARAGE_SETTING_URIS = {GARAGE_MODE_ENABLED_URI, GARAGE_MODE_WAKE_UP_TIME_URI, GARAGE_MODE_MAINTENANCE_WINDOW_URI};

    public GarageModeSettingsObserver(Context context, Handler handler) {
        super(handler);
        this.mContext = new WeakReference<>(context);
    }

    public void register() {
        Uri[] uriArr;
        if (this.mContext.get() == null) {
            return;
        }
        for (Uri uri : GARAGE_SETTING_URIS) {
            this.mContext.get().getContentResolver().registerContentObserver(uri, false, this);
        }
    }

    public void unregister() {
        if (this.mContext.get() == null) {
            return;
        }
        this.mContext.get().getContentResolver().unregisterContentObserver(this);
    }
}
