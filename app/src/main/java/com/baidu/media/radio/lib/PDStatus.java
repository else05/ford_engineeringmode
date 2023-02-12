package com.baidu.media.radio.lib;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class PDStatus implements Parcelable {
    public static final Parcelable.Creator<PDStatus> CREATOR = new Parcelable.Creator<PDStatus>() { // from class: com.baidu.media.radio.lib.PDStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PDStatus createFromParcel(Parcel in) {
            return new PDStatus(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PDStatus[] newArray(int size) {
            return new PDStatus[size];
        }
    };
    int mPdStatus;

    public PDStatus(int pdStatus) {
        this.mPdStatus = -1;
        this.mPdStatus = pdStatus;
    }

    public int getPDStatus() {
        return this.mPdStatus;
    }

    protected PDStatus(Parcel in) {
        this.mPdStatus = -1;
        this.mPdStatus = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mPdStatus);
    }
}
