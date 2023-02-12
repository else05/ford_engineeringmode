package android.car.content.pm;

import android.content.pm.Signature;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class AppBlockingPackageInfo implements Parcelable {
    public static final Parcelable.Creator<AppBlockingPackageInfo> CREATOR = new Parcelable.Creator<AppBlockingPackageInfo>() { // from class: android.car.content.pm.AppBlockingPackageInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppBlockingPackageInfo createFromParcel(Parcel in) {
            return new AppBlockingPackageInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppBlockingPackageInfo[] newArray(int size) {
            return new AppBlockingPackageInfo[size];
        }
    };
    public static final int FLAG_SYSTEM_APP = 1;
    public static final int FLAG_WHOLE_ACTIVITY = 2;
    public final String[] activities;
    public final int flags;
    public final int maxRevisionCode;
    public final int minRevisionCode;
    public final String packageName;
    public final Signature[] signatures;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ConstrcutorFlags {
    }

    public AppBlockingPackageInfo(String packageName, int minRevisionCode, int maxRevisionCode, int flags, Signature[] signatures, String[] activities) {
        if (packageName == null) {
            throw new IllegalArgumentException("packageName cannot be null");
        }
        this.packageName = packageName;
        this.flags = flags;
        this.minRevisionCode = minRevisionCode;
        this.maxRevisionCode = maxRevisionCode;
        this.signatures = signatures;
        this.activities = activities;
        verify();
    }

    public AppBlockingPackageInfo(Parcel in) {
        this.packageName = in.readString();
        this.flags = in.readInt();
        this.minRevisionCode = in.readInt();
        this.maxRevisionCode = in.readInt();
        this.signatures = (Signature[]) in.createTypedArray(Signature.CREATOR);
        this.activities = in.createStringArray();
        verify();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.packageName);
        dest.writeInt(this.flags);
        dest.writeInt(this.minRevisionCode);
        dest.writeInt(this.maxRevisionCode);
        dest.writeTypedArray(this.signatures, 0);
        dest.writeStringArray(this.activities);
    }

    public void verify() throws IllegalArgumentException {
        if (this.signatures == null && (this.flags & 1) == 0) {
            throw new IllegalArgumentException("Only system package with FLAG_SYSTEM_APP can have null signatures");
        }
    }

    public boolean isActivityCovered(String className) {
        String[] strArr;
        if ((this.flags & 2) != 0) {
            return true;
        }
        if (this.activities == null) {
            return false;
        }
        for (String activityName : this.activities) {
            if (activityName.equals(className)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int result = (1 * 31) + Arrays.hashCode(this.activities);
        return (((((((((result * 31) + this.flags) * 31) + this.maxRevisionCode) * 31) + this.minRevisionCode) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + Arrays.hashCode(this.signatures);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppBlockingPackageInfo other = (AppBlockingPackageInfo) obj;
        if (!Arrays.equals(this.activities, other.activities) || this.flags != other.flags || this.maxRevisionCode != other.maxRevisionCode || this.minRevisionCode != other.minRevisionCode) {
            return false;
        }
        if (this.packageName == null) {
            if (other.packageName != null) {
                return false;
            }
        } else if (!this.packageName.equals(other.packageName)) {
            return false;
        }
        if (Arrays.equals(this.signatures, other.signatures)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "AppBlockingPackageInfo [packageName=" + this.packageName + ", flags=" + this.flags + ", minRevisionCode=" + this.minRevisionCode + ", maxRevisionCode=" + this.maxRevisionCode + ", signatures=" + Arrays.toString(this.signatures) + ", activities=" + Arrays.toString(this.activities) + "]";
    }
}
