package android.car.navigation;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public class CarNavigationInstrumentCluster implements Parcelable {
    public static final int CLUSTER_TYPE_CUSTOM_IMAGES_SUPPORTED = 1;
    public static final int CLUSTER_TYPE_IMAGE_CODES_ONLY = 2;
    public static final Parcelable.Creator<CarNavigationInstrumentCluster> CREATOR = new Parcelable.Creator<CarNavigationInstrumentCluster>() { // from class: android.car.navigation.CarNavigationInstrumentCluster.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarNavigationInstrumentCluster createFromParcel(Parcel in) {
            return new CarNavigationInstrumentCluster(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarNavigationInstrumentCluster[] newArray(int size) {
            return new CarNavigationInstrumentCluster[size];
        }
    };
    private final Bundle mExtra;
    private final int mImageColorDepthBits;
    private final int mImageHeight;
    private final int mImageWidth;
    private int mMinIntervalMillis;
    private final int mType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ClusterType {
    }

    public static CarNavigationInstrumentCluster createCluster(int minIntervalMillis) {
        return new CarNavigationInstrumentCluster(minIntervalMillis, 2, 0, 0, 0);
    }

    public static CarNavigationInstrumentCluster createCustomImageCluster(int minIntervalMs, int imageWidth, int imageHeight, int imageColorDepthBits) {
        return new CarNavigationInstrumentCluster(minIntervalMs, 1, imageWidth, imageHeight, imageColorDepthBits);
    }

    public int getMinIntervalMillis() {
        return this.mMinIntervalMillis;
    }

    public int getType() {
        return this.mType;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public int getImageHeight() {
        return this.mImageHeight;
    }

    public Bundle getExtra() {
        return this.mExtra;
    }

    public int getImageColorDepthBits() {
        return this.mImageColorDepthBits;
    }

    public CarNavigationInstrumentCluster(CarNavigationInstrumentCluster that) {
        this(that.mMinIntervalMillis, that.mType, that.mImageWidth, that.mImageHeight, that.mImageColorDepthBits);
    }

    public boolean supportsCustomImages() {
        return this.mType == 1;
    }

    private CarNavigationInstrumentCluster(int minIntervalMillis, int type, int imageWidth, int imageHeight, int imageColorDepthBits) {
        this.mMinIntervalMillis = minIntervalMillis;
        this.mType = type;
        this.mImageWidth = imageWidth;
        this.mImageHeight = imageHeight;
        this.mImageColorDepthBits = imageColorDepthBits;
        this.mExtra = new Bundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mMinIntervalMillis);
        dest.writeInt(this.mType);
        dest.writeInt(this.mImageWidth);
        dest.writeInt(this.mImageHeight);
        dest.writeInt(this.mImageColorDepthBits);
        dest.writeBundle(this.mExtra);
    }

    private CarNavigationInstrumentCluster(Parcel in) {
        this.mMinIntervalMillis = in.readInt();
        this.mType = in.readInt();
        this.mImageWidth = in.readInt();
        this.mImageHeight = in.readInt();
        this.mImageColorDepthBits = in.readInt();
        this.mExtra = in.readBundle(getClass().getClassLoader());
    }

    public String toString() {
        return CarNavigationInstrumentCluster.class.getSimpleName() + "{ minIntervalMillis: " + this.mMinIntervalMillis + ", type: " + this.mType + ", imageWidth: " + this.mImageWidth + ", imageHeight: " + this.mImageHeight + ", imageColourDepthBits: " + this.mImageColorDepthBits + "extra: " + this.mExtra + " }";
    }
}
