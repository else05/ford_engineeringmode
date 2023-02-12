package android.car.hardware;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.lang.reflect.Array;

/* loaded from: classes2.dex */
public class CarPropertyConfig<T> implements Parcelable {
    public static final Parcelable.Creator<CarPropertyConfig> CREATOR = new Parcelable.Creator<CarPropertyConfig>() { // from class: android.car.hardware.CarPropertyConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyConfig createFromParcel(Parcel in) {
            return new CarPropertyConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPropertyConfig[] newArray(int size) {
            return new CarPropertyConfig[size];
        }
    };
    private final int mAreaType;
    private final int mPropertyId;
    private final SparseArray<AreaConfig<T>> mSupportedAreas;
    private final Class<T> mType;

    private CarPropertyConfig(Class<T> type, int propertyId, int areaType, SparseArray<AreaConfig<T>> supportedAreas) {
        this.mPropertyId = propertyId;
        this.mType = type;
        this.mAreaType = areaType;
        this.mSupportedAreas = supportedAreas;
    }

    public int getPropertyId() {
        return this.mPropertyId;
    }

    public Class<T> getPropertyType() {
        return this.mType;
    }

    public int getAreaType() {
        return this.mAreaType;
    }

    public boolean isGlobalProperty() {
        return this.mAreaType == 0;
    }

    public int getAreaCount() {
        return this.mSupportedAreas.size();
    }

    public int[] getAreaIds() {
        int[] areaIds = new int[this.mSupportedAreas.size()];
        for (int i = 0; i < areaIds.length; i++) {
            areaIds[i] = this.mSupportedAreas.keyAt(i);
        }
        return areaIds;
    }

    public int getFirstAndOnlyAreaId() {
        if (this.mSupportedAreas.size() != 1) {
            throw new IllegalStateException("Expected one and only area in this property. Prop: 0x" + Integer.toHexString(this.mPropertyId));
        }
        return this.mSupportedAreas.keyAt(0);
    }

    public boolean hasArea(int areaId) {
        return this.mSupportedAreas.indexOfKey(areaId) >= 0;
    }

    public T getMinValue(int areaId) {
        AreaConfig<T> area = this.mSupportedAreas.get(areaId);
        if (area == null) {
            return null;
        }
        return area.getMinValue();
    }

    public T getMaxValue(int areaId) {
        AreaConfig<T> area = this.mSupportedAreas.get(areaId);
        if (area == null) {
            return null;
        }
        return area.getMaxValue();
    }

    public T getMinValue() {
        AreaConfig<T> area = this.mSupportedAreas.valueAt(0);
        if (area == null) {
            return null;
        }
        return area.getMinValue();
    }

    public T getMaxValue() {
        AreaConfig<T> area = this.mSupportedAreas.valueAt(0);
        if (area == null) {
            return null;
        }
        return area.getMaxValue();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mPropertyId);
        dest.writeString(this.mType.getName());
        dest.writeInt(this.mAreaType);
        dest.writeInt(this.mSupportedAreas.size());
        for (int i = 0; i < this.mSupportedAreas.size(); i++) {
            dest.writeInt(this.mSupportedAreas.keyAt(i));
            dest.writeParcelable(this.mSupportedAreas.valueAt(i), flags);
        }
    }

    private CarPropertyConfig(Parcel in) {
        this.mPropertyId = in.readInt();
        String className = in.readString();
        try {
            this.mType = (Class<T>) Class.forName(className);
            this.mAreaType = in.readInt();
            int areaSize = in.readInt();
            this.mSupportedAreas = new SparseArray<>(areaSize);
            for (int i = 0; i < areaSize; i++) {
                int areaId = in.readInt();
                AreaConfig<T> area = (AreaConfig) in.readParcelable(getClass().getClassLoader());
                this.mSupportedAreas.put(areaId, area);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class not found: " + className);
        }
    }

    public String toString() {
        return "CarPropertyConfig{mPropertyId=" + this.mPropertyId + ", mType=" + this.mType + ", mAreaType=" + this.mAreaType + ", mSupportedAreas=" + this.mSupportedAreas + '}';
    }

    /* loaded from: classes2.dex */
    public static class AreaConfig<T> implements Parcelable {
        public static final Parcelable.Creator<AreaConfig<Object>> CREATOR = getCreator(Object.class);
        private final T mMaxValue;
        private final T mMinValue;

        private AreaConfig(T minValue, T maxValue) {
            this.mMinValue = minValue;
            this.mMaxValue = maxValue;
        }

        private static <E> Parcelable.Creator<AreaConfig<E>> getCreator(final Class<E> clazz) {
            return new Parcelable.Creator<AreaConfig<E>>() { // from class: android.car.hardware.CarPropertyConfig.AreaConfig.1
                @Override // android.os.Parcelable.Creator
                public AreaConfig<E> createFromParcel(Parcel source) {
                    return new AreaConfig<>(source);
                }

                @Override // android.os.Parcelable.Creator
                public AreaConfig<E>[] newArray(int size) {
                    return (AreaConfig[]) Array.newInstance(clazz, size);
                }
            };
        }

        private AreaConfig(Parcel in) {
            this.mMinValue = (T) in.readValue(getClass().getClassLoader());
            this.mMaxValue = (T) in.readValue(getClass().getClassLoader());
        }

        public T getMinValue() {
            return this.mMinValue;
        }

        public T getMaxValue() {
            return this.mMaxValue;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.mMinValue);
            dest.writeValue(this.mMaxValue);
        }

        public String toString() {
            return "CarAreaConfig{mMinValue=" + this.mMinValue + ", mMaxValue=" + this.mMaxValue + '}';
        }
    }

    public static <T> Builder<T> newBuilder(Class<T> clazz, int propertyId, int areaType, int areaCapacity) {
        return new Builder<>(clazz, propertyId, areaType, areaCapacity);
    }

    public static <T> Builder<T> newBuilder(Class<T> clazz, int propertyId, int areaType) {
        return newBuilder(clazz, propertyId, areaType, 0);
    }

    /* loaded from: classes2.dex */
    public static class Builder<T> {
        private final int mAreaType;
        private final SparseArray<AreaConfig<T>> mAreas;
        private final int mPropertyId;
        private final Class<T> mType;

        private Builder(Class<T> type, int propertyId, int areaType, int areaCapacity) {
            this.mType = type;
            this.mPropertyId = propertyId;
            this.mAreaType = areaType;
            if (areaCapacity != 0) {
                this.mAreas = new SparseArray<>(areaCapacity);
            } else {
                this.mAreas = new SparseArray<>();
            }
        }

        public Builder<T> addAreas(int[] areaIds) {
            for (int id : areaIds) {
                this.mAreas.put(id, null);
            }
            return this;
        }

        public Builder<T> addArea(int areaId) {
            return addAreaConfig(areaId, null, null);
        }

        public Builder<T> addAreaConfig(int areaId, T min, T max) {
            if (min != null || max != null) {
                this.mAreas.put(areaId, new AreaConfig<>(min, max));
            } else {
                this.mAreas.put(areaId, null);
            }
            return this;
        }

        public CarPropertyConfig<T> build() {
            return new CarPropertyConfig<>(this.mType, this.mPropertyId, this.mAreaType, this.mAreas);
        }
    }
}
