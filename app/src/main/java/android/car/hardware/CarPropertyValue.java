package android.car.hardware;

import android.os.Parcel;
import android.os.Parcelable;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public class CarPropertyValue<T> implements Parcelable {
    private final int mAreaId;
    private final int mPropertyId;
    private final T mValue;
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public static final Parcelable.Creator<CarPropertyValue> CREATOR = new Parcelable.Creator<CarPropertyValue>() { // from class: android.car.hardware.CarPropertyValue.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyValue createFromParcel(Parcel in) {
            return new CarPropertyValue(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyValue[] newArray(int size) {
            return new CarPropertyValue[size];
        }
    };

    public CarPropertyValue(int propertyId, T value) {
        this(propertyId, 0, value);
    }

    public CarPropertyValue(int propertyId, int areaId, T value) {
        this.mPropertyId = propertyId;
        this.mAreaId = areaId;
        this.mValue = value;
    }

    public CarPropertyValue(Parcel in) {
        this.mPropertyId = in.readInt();
        this.mAreaId = in.readInt();
        String valueClassName = in.readString();
        try {
            Class<?> valueClass = Class.forName(valueClassName);
            if (String.class.equals(valueClass)) {
                byte[] bytes = in.readBlob();
                this.mValue = (T) new String(bytes, DEFAULT_CHARSET);
            } else if (byte[].class.equals(valueClass)) {
                this.mValue = (T) in.readBlob();
            } else {
                this.mValue = (T) in.readValue(valueClass.getClassLoader());
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class not found: " + valueClassName);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mPropertyId);
        dest.writeInt(this.mAreaId);
        Class<?> valueClass = this.mValue == null ? null : this.mValue.getClass();
        dest.writeString(valueClass != null ? valueClass.getName() : null);
        if (String.class.equals(valueClass)) {
            dest.writeBlob(((String) this.mValue).getBytes(DEFAULT_CHARSET));
        } else if (byte[].class.equals(valueClass)) {
            dest.writeBlob((byte[]) this.mValue);
        } else {
            dest.writeValue(this.mValue);
        }
    }

    public int getPropertyId() {
        return this.mPropertyId;
    }

    public int getAreaId() {
        return this.mAreaId;
    }

    public T getValue() {
        return this.mValue;
    }

    public String toString() {
        return "CarPropertyValue{mPropertyId=0x" + Integer.toHexString(this.mPropertyId) + ", mAreaId=0x" + Integer.toHexString(this.mAreaId) + ", mValue=" + this.mValue + '}';
    }
}
