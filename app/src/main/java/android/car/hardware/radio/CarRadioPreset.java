package android.car.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class CarRadioPreset implements Parcelable {
    public static final Parcelable.Creator<CarRadioPreset> CREATOR = new Parcelable.Creator<CarRadioPreset>() { // from class: android.car.hardware.radio.CarRadioPreset.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarRadioPreset createFromParcel(Parcel in) {
            return new CarRadioPreset(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarRadioPreset[] newArray(int size) {
            return new CarRadioPreset[size];
        }
    };
    private final int mBand;
    private final int mChannel;
    private final int mPresetNumber;
    private final int mSubChannel;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mPresetNumber);
        out.writeInt(this.mBand);
        out.writeInt(this.mChannel);
        out.writeInt(this.mSubChannel);
    }

    private CarRadioPreset(Parcel in) {
        this.mPresetNumber = in.readInt();
        this.mBand = in.readInt();
        this.mChannel = in.readInt();
        this.mSubChannel = in.readInt();
    }

    public CarRadioPreset(int presetNumber, int bandType, int channel, int subChannel) {
        this.mPresetNumber = presetNumber;
        this.mBand = bandType;
        this.mChannel = channel;
        this.mSubChannel = subChannel;
    }

    public int getPresetNumber() {
        return this.mPresetNumber;
    }

    public int getBand() {
        return this.mBand;
    }

    public int getChannel() {
        return this.mChannel;
    }

    public int getSubChannel() {
        return this.mSubChannel;
    }

    public String toString() {
        return "Preset Number: " + this.mPresetNumber + "\nBand: " + this.mBand + "\nChannel: " + this.mChannel + "\nSub channel: " + this.mSubChannel;
    }

    public boolean equals(Object o) {
        CarRadioPreset that = (CarRadioPreset) o;
        return that.getPresetNumber() == this.mPresetNumber && that.getBand() == this.mBand && that.getChannel() == this.mChannel && that.getSubChannel() == this.mSubChannel;
    }
}
