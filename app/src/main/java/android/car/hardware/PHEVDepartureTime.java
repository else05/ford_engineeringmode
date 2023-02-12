package android.car.hardware;

import android.util.Log;

/* loaded from: classes2.dex */
public class PHEVDepartureTime {
    public int allOnOFF;
    public int elementID;
    public int hour;
    public int minute;
    public int nextElementID;
    public int preCondition;

    public PHEVDepartureTime(byte[] args) {
        if (args.length != 6) {
            this.elementID = 0;
            return;
        }
        String inputStr = "";
        for (int i = 0; i < 6; i++) {
            inputStr = inputStr + Integer.toHexString(args[i] & 255) + " ";
        }
        Log.w("CAR.L.PHEV", "PHEVDepartureTime, input byte[]: " + inputStr);
        this.elementID = args[0];
        this.hour = args[1];
        this.minute = args[2] * 5;
        this.preCondition = args[3];
        this.allOnOFF = args[4];
        this.nextElementID = args[5];
    }

    public String toString() {
        String str = "elementID:" + this.elementID + ";";
        return ((((str + "hour:" + this.hour + ";") + "minute:" + this.minute + ";") + "preCondition:" + this.preCondition + ";") + "allOnOFF:" + this.allOnOFF + ";") + "nextElementID:" + this.nextElementID + ";";
    }

    public boolean isValid() {
        return this.elementID != 0;
    }
}
