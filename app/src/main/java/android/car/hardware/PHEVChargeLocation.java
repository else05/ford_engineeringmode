package android.car.hardware;

import android.util.Log;
import java.math.BigDecimal;

/* loaded from: classes2.dex */
public class PHEVChargeLocation {
    public int id;
    public BigDecimal latitude;
    public BigDecimal longitude;

    public PHEVChargeLocation(byte[] args) {
        if (args.length != 13) {
            this.id = 0;
            return;
        }
        String inputStr = "";
        for (int i = 0; i < 13; i++) {
            inputStr = inputStr + Integer.toHexString(args[i] & 255) + " ";
        }
        Log.w("CAR.L.PHEV", "PHEVChargeLocation, input byte[]: " + inputStr);
        this.id = args[0] & 255;
        StringBuffer strBuf = new StringBuffer();
        strBuf.append((args[2] & 255) == 1 ? "-" : "");
        strBuf.append(Long.toUnsignedString(args[1] & 255) + ".");
        strBuf.append(Long.toUnsignedString((long) (((args[4] & 15) << 16) + ((args[5] & 255) << 8) + (args[6] & 255))));
        this.latitude = new BigDecimal(strBuf.toString());
        strBuf.setLength(0);
        strBuf.append((args[8] & 255) == 1 ? "-" : "");
        strBuf.append(Long.toUnsignedString(args[7] & 255) + ".");
        strBuf.append(Long.toUnsignedString((long) (((args[10] & 15) << 16) + ((args[11] & 255) << 8) + (args[12] & 255))));
        this.longitude = new BigDecimal(strBuf.toString());
    }

    public String toString() {
        return "id:" + this.id + ";latitude:" + this.latitude + ";longitude:" + this.longitude;
    }

    public boolean isValid() {
        return this.id != 0;
    }
}
