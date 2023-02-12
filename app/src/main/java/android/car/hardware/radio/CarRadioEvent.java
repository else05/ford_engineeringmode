package android.car.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class CarRadioEvent implements Parcelable {
    public static final Parcelable.Creator<CarRadioEvent> CREATOR = new Parcelable.Creator<CarRadioEvent>() { // from class: android.car.hardware.radio.CarRadioEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarRadioEvent createFromParcel(Parcel in) {
            return new CarRadioEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarRadioEvent[] newArray(int size) {
            return new CarRadioEvent[size];
        }
    };
    public static final int RADIO_PRESET = 0;
    private final CarRadioPreset mPreset;
    private final int mType;

    public CarRadioPreset getPreset() {
        return this.mPreset;
    }

    public int getEventType() {
        return this.mType;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mType);
        dest.writeParcelable(this.mPreset, 0);
    }

    public CarRadioEvent(int type, CarRadioPreset preset) {
        this.mType = type;
        this.mPreset = preset;
    }

    private CarRadioEvent(Parcel in) {
        this.mType = in.readInt();
        this.mPreset = (CarRadioPreset) in.readParcelable(CarRadioPreset.class.getClassLoader());
    }

    public String toString() {
        String data = "";
        if (this.mType == 0) {
            data = this.mPreset.toString();
        }
        return this.mType + " " + data;
    }
}
