package com.baidu.media.radio.lib;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class RadioSignal2 implements Parcelable {
    public static final Parcelable.Creator<RadioSignal2> CREATOR = new Parcelable.Creator<RadioSignal2>() { // from class: com.baidu.media.radio.lib.RadioSignal2.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RadioSignal2 createFromParcel(Parcel in) {
            return new RadioSignal2(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RadioSignal2[] newArray(int size) {
            return new RadioSignal2[size];
        }
    };
    private final int ifbw;
    private final int signalStrength;
    private final int usn;
    private final int wam;

    public RadioSignal2(int signalStrength, int usn, int wam, int ifbw) {
        this.signalStrength = signalStrength;
        this.usn = usn;
        this.wam = wam;
        this.ifbw = ifbw;
    }

    protected RadioSignal2(Parcel in) {
        this.signalStrength = in.readInt();
        this.usn = in.readInt();
        this.wam = in.readInt();
        this.ifbw = in.readInt();
    }

    public int getSignalStrength() {
        return this.signalStrength;
    }

    public int getUsn() {
        return this.usn;
    }

    public int getWam() {
        return this.wam;
    }

    public int getIFBW() {
        return this.ifbw;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.signalStrength);
        dest.writeInt(this.usn);
        dest.writeInt(this.wam);
        dest.writeInt(this.ifbw);
    }

    public String toString() {
        return "\"RadioSignal2\": {\"signalStrength\": \"" + this.signalStrength + ", \"usn\": \"" + this.usn + ", \"wam\": \"" + this.wam + ", \"ifbw\": \"" + this.ifbw + '}';
    }
}
