package android.car.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Method;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class CarAppBlockingPolicy implements Parcelable {
    public static final Parcelable.Creator<CarAppBlockingPolicy> CREATOR;
    private static final String TAG = CarAppBlockingPolicy.class.getSimpleName();
    private static final Method sReadBlobMethod;
    private static final Method sWriteBlobMethod;
    public final AppBlockingPackageInfo[] blacklists;
    public final AppBlockingPackageInfo[] whitelists;

    static {
        Method readBlob;
        Method writeBlob;
        try {
            readBlob = Parcel.class.getMethod("readBlob", new Class[0]);
            writeBlob = Parcel.class.getMethod("writeBlob", byte[].class);
        } catch (NoSuchMethodException e) {
            readBlob = null;
            writeBlob = null;
        }
        sReadBlobMethod = readBlob;
        sWriteBlobMethod = writeBlob;
        CREATOR = new Parcelable.Creator<CarAppBlockingPolicy>() { // from class: android.car.content.pm.CarAppBlockingPolicy.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CarAppBlockingPolicy createFromParcel(Parcel in) {
                return new CarAppBlockingPolicy(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CarAppBlockingPolicy[] newArray(int size) {
                return new CarAppBlockingPolicy[size];
            }
        };
    }

    public CarAppBlockingPolicy(AppBlockingPackageInfo[] whitelists, AppBlockingPackageInfo[] blacklists) {
        this.whitelists = whitelists;
        this.blacklists = blacklists;
    }

    public CarAppBlockingPolicy(Parcel in) {
        byte[] payload = in.readBlob();
        Parcel payloadParcel = Parcel.obtain();
        payloadParcel.unmarshall(payload, 0, payload.length);
        payloadParcel.setDataPosition(0);
        this.whitelists = (AppBlockingPackageInfo[]) payloadParcel.createTypedArray(AppBlockingPackageInfo.CREATOR);
        this.blacklists = (AppBlockingPackageInfo[]) payloadParcel.createTypedArray(AppBlockingPackageInfo.CREATOR);
        payloadParcel.recycle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Parcel payloadParcel = Parcel.obtain();
        payloadParcel.writeTypedArray(this.whitelists, 0);
        payloadParcel.writeTypedArray(this.blacklists, 0);
        byte[] payload = payloadParcel.marshall();
        dest.writeBlob(payload);
        payloadParcel.recycle();
    }

    public int hashCode() {
        int result = (1 * 31) + Arrays.hashCode(this.blacklists);
        return (result * 31) + Arrays.hashCode(this.whitelists);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CarAppBlockingPolicy other = (CarAppBlockingPolicy) obj;
        if (Arrays.equals(this.blacklists, other.blacklists) && Arrays.equals(this.whitelists, other.whitelists)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "CarAppBlockingPolicy [whitelists=" + Arrays.toString(this.whitelists) + ", blacklists=" + Arrays.toString(this.blacklists) + "]";
    }
}
