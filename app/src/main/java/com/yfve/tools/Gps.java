package com.yfve.tools;

import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes1.dex */
public class Gps {
    public static long m_TimeStamp = 0;
    private double SigmaEast;
    private double SigmaNorth;
    private long Time;
    private int age;
    private boolean bUsedBDS;
    private boolean bUsedGPS;
    private boolean bUsedGalileo;
    private boolean bUsedGlonass;
    private double dUndulation;
    private int day;
    private double deltTime;
    private double fSpeed_M_S;
    private Date gpsDate;
    private int gpsStatue;
    private double hdop;
    private double heading;
    private double hrms;
    private int m_SatNum;
    public boolean m_bUpdating;
    private int m_tSatNum;
    private int month;
    private double pdop;
    private double rms;
    private Date utcDate;
    private double vdop;
    private double vrms;
    private int year;
    List<Byte> gps_data = new ArrayList();
    int GSVIndex = 0;
    private String strGsa = "";
    private double latitude = Utils.DOUBLE_EPSILON;
    private double longitude = Utils.DOUBLE_EPSILON;
    private double altitude = Utils.DOUBLE_EPSILON;

    private boolean checkNMEAData(String strData) {
        if (strData.indexOf("*") == -1 || strData.indexOf("$") == -1 || strData.indexOf("$") > strData.indexOf("*")) {
            return false;
        }
        String stem = strData.substring(0, strData.indexOf("*"));
        byte[] cData = stem.getBytes();
        int nCheckSum = cData[1];
        for (int n = 2; n < cData.length; n++) {
            nCheckSum ^= cData[n];
        }
        String str = String.format("%02x", Integer.valueOf(nCheckSum));
        int index = strData.indexOf("*");
        if (index != -1 && strData.length() >= index + 3) {
            String str0 = strData.substring(index + 1, index + 3);
            return str.equalsIgnoreCase(str0);
        }
        return false;
    }

    public static int stringNumbers(String str, String specter) {
        int count = 0;
        char c = specter.charAt(0);
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if (Character.valueOf(c).equals(Character.valueOf(tmp))) {
                count++;
            }
        }
        return count;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void processNmeaData(java.lang.String r28) {
        /*
            Method dump skipped, instructions count: 2006
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yfve.tools.Gps.processNmeaData(java.lang.String):void");
    }

    public double getVdop() {
        return this.vdop;
    }

    public double getHdop() {
        return this.hdop;
    }

    public double getPdop() {
        return this.pdop;
    }

    public double getHrms() {
        return this.hrms;
    }

    public double getVrms() {
        return this.vrms;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public int getStatusType() {
        return this.gpsStatue;
    }

    public double getSpeed() {
        return this.fSpeed_M_S;
    }

    public double getBearing() {
        return this.heading;
    }

    public int getVisibleGnssCount() {
        return this.m_tSatNum;
    }

    public int getLockGnssCount() {
        return this.m_SatNum;
    }

    public int getAge() {
        return this.age;
    }

    public Date getLocalTime() {
        return this.gpsDate;
    }

    public Date getUtcTime() {
        return this.utcDate;
    }
}
