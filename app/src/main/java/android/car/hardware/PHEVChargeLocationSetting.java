package android.car.hardware;

import android.util.Log;

/* loaded from: classes2.dex */
public class PHEVChargeLocationSetting {
    public int ackFlag;
    public int chargeNowOrNot;
    public int chargeTimesProfileForWeekdays;
    public int chargeTimesProfileForWeekends;
    public int id;
    public String percentWeekdayValue;
    public String percentWeekendValue;

    public PHEVChargeLocationSetting(byte[] args) {
        System.out.println(args.length);
        if (args.length != 11) {
            this.id = 0;
            return;
        }
        String inputStr = "";
        for (int i = 0; i < 11; i++) {
            inputStr = inputStr + Integer.toHexString(args[i] & 255) + " ";
        }
        Log.w("CAR.L.PHEV", "PHEVChargeLocationSetting, input byte[]: " + inputStr);
        this.percentWeekendValue = convertChargerPercent(args[0]);
        this.percentWeekdayValue = convertChargerPercent(args[1]);
        this.id = args[2];
        this.chargeTimesProfileForWeekdays = ((args[3] & 255) << 16) + ((args[4] & 255) << 8) + (args[5] & 255);
        this.chargeTimesProfileForWeekends = ((args[6] & 255) << 16) + ((args[7] & 255) << 8) + (args[8] & 255);
        this.chargeNowOrNot = args[9];
        this.ackFlag = args[10];
    }

    public String toString() {
        String str = "percentWeekendValue:" + this.percentWeekendValue + ";";
        return (((((str + "percentWeekdayValue:" + this.percentWeekdayValue + ";") + "id:" + this.id + ";") + "chargeTimesProfileForWeekdays:" + this.chargeTimesProfileForWeekdays + ";") + "chargeTimesProfileForWeekends:" + this.chargeTimesProfileForWeekends + ";") + "chargeNowOrNot:" + this.chargeNowOrNot + ";") + "ackFlag:" + this.ackFlag + ";";
    }

    public boolean isValid() {
        return this.id != 0;
    }

    private String convertChargerPercent(int value) {
        switch (value) {
            case 0:
                return "100%";
            case 1:
                return "95%";
            case 2:
                return "90%";
            case 3:
                return "85%";
            case 4:
                return "80%";
            case 5:
                return "70%";
            case 6:
                return "60%";
            case 7:
                return "50%";
            default:
                return "";
        }
    }
}
