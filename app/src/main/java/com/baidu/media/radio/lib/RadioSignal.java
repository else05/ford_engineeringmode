package com.baidu.media.radio.lib;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class RadioSignal implements Parcelable {
    public static final Parcelable.Creator<RadioSignal> CREATOR = new Parcelable.Creator<RadioSignal>() { // from class: com.baidu.media.radio.lib.RadioSignal.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RadioSignal createFromParcel(Parcel source) {
            return new RadioSignal(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RadioSignal[] newArray(int size) {
            return new RadioSignal[size];
        }
    };
    private final int agc;
    private final int bandwidth;
    private final int modulation;
    private final int offset;
    private final int quality;
    private final int signalStrength;
    private final boolean stereo;
    private final int usn;
    private final int wam;

    public RadioSignal(int signalStrength, int usn, int wam, int offset, int bandwidth, int modulation, int quality, boolean stereo, int agc) {
        this.signalStrength = signalStrength;
        this.usn = usn;
        this.wam = wam;
        this.offset = offset;
        this.bandwidth = bandwidth;
        this.modulation = modulation;
        this.quality = quality;
        this.stereo = stereo;
        this.agc = agc;
    }

    protected RadioSignal(Parcel in) {
        this.signalStrength = in.readInt();
        this.usn = in.readInt();
        this.wam = in.readInt();
        this.offset = in.readInt();
        this.bandwidth = in.readInt();
        this.modulation = in.readInt();
        this.quality = in.readInt();
        this.stereo = in.readByte() != 0;
        this.agc = in.readInt();
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

    public int getOffset() {
        return this.offset;
    }

    public int getBandwidth() {
        return this.bandwidth;
    }

    public int getModulation() {
        return this.modulation;
    }

    public int getQuality() {
        return this.quality;
    }

    public boolean isStereo() {
        return this.stereo;
    }

    public int getAgc() {
        return this.agc;
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
        dest.writeInt(this.offset);
        dest.writeInt(this.bandwidth);
        dest.writeInt(this.modulation);
        dest.writeInt(this.quality);
        dest.writeByte(this.stereo ? (byte) 1 : (byte) 0);
        dest.writeInt(this.agc);
    }

    public String toString() {
        return "\"RadioSignal\": {\"signalStrength\": \"" + this.signalStrength + ", \"usn\": \"" + this.usn + ", \"wam\": \"" + this.wam + ", \"offset\": \"" + this.offset + ", \"bandwidth\": \"" + this.bandwidth + ", \"modulation\": \"" + this.modulation + ", \"quality\": \"" + this.quality + ", \"stereo\": \"" + this.stereo + ", \"agc\": \"" + this.agc + '}';
    }
}
