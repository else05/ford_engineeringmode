package android.car.hardware;

import android.os.Bundle;

/* loaded from: classes2.dex */
public class LocationProtocal {
    public static final int CompassDirectionMax = 8;
    public static final int CompassDirectionMin = 1;
    public static final int HeadingMax = 3599;
    public static final int HeadingMin = 0;
    public static final int MSLMax = 2000;
    public static final int MSLMin = 0;
    public static final int SISMax = 15;
    public static final int SISMin = 0;
    public static final String TAG = "LocationProtocal";
    public static final int VelocityMax = 4095;
    public static final int VelocityMin = 0;
    public static final int latitudeDFMax = 999999;
    public static final int latitudeDFMin = 0;
    public static final int latitudeDIMax = 89;
    public static final int latitudeDIMin = 0;
    public static final int longitudeDFMax = 999999;
    public static final int longitudeDFMin = 0;
    public static final int longitudeDIMax = 179;
    public static final int longitudeDIMin = 0;
    private byte[] locationBytes;

    public void setLocationByte(Bundle location1Bundle) {
        byte[] bArr = new byte[8];
        int latitudeDI = location1Bundle.getInt("LatitudeDI");
        if (latitudeDI > 89) {
            latitudeDI = 89;
        } else if (latitudeDI < 0) {
            latitudeDI = 0;
        }
        int latitudeSign = location1Bundle.getInt("LatitudeSign");
        int latitudeDF = location1Bundle.getInt("LatitudeDF");
        if (latitudeDF > 999999) {
            latitudeDF = 999999;
        } else if (latitudeDF < 0) {
            latitudeDF = 0;
        }
        int longitudeDI = location1Bundle.getInt("LongitudeDI");
        if (longitudeDI > 179) {
            longitudeDI = longitudeDIMax;
        } else if (longitudeDI < 0) {
            longitudeDI = 0;
        }
        int longitudeDF = location1Bundle.getInt("LongitudeDF");
        if (longitudeDF > 999999) {
            longitudeDF = 999999;
        } else if (longitudeDF < 0) {
            longitudeDF = 0;
        }
        int longitudeSign = location1Bundle.getInt("LongitudeSign");
        String binaryString = "0000" + toBinary(latitudeDI, 7) + toBinary(latitudeSign, 1) + toBinary(latitudeDF, 20) + toBinary(longitudeDI, 8) + toBinary(longitudeDF, 20) + toBinary(longitudeSign, 1) + "000";
        byte[] location = binStrToByteArr(binaryString);
        this.locationBytes = location;
    }

    public byte[] getMetaDataTime(Bundle metaDataTimeBundle) {
        int UTCYear = metaDataTimeBundle.getInt("UTCYear") - 2014;
        int UTCMonth = metaDataTimeBundle.getInt("UTCMonth");
        int UTCTD = metaDataTimeBundle.getInt("UTCTD");
        int UTCTH = metaDataTimeBundle.getInt("UTCTH");
        int UTCTM = metaDataTimeBundle.getInt("UTCTM");
        int UTCTS = metaDataTimeBundle.getInt("UTCTS");
        byte[] metaTimeBytes = {1, (byte) (UTCTH & 255), (byte) (UTCTM & 255), (byte) (UTCTS & 255), (byte) (UTCTD & 255), (byte) (UTCMonth & 255), (byte) (UTCYear & 255), 0};
        return metaTimeBytes;
    }

    public byte[] getLocation3() {
        byte[] location3bytes = this.locationBytes;
        location3bytes[0] = (byte) ((this.locationBytes[0] & 15) | 96);
        return location3bytes;
    }

    public byte[] getLocation4() {
        byte[] location4bytes = this.locationBytes;
        location4bytes[0] = (byte) ((this.locationBytes[0] & 15) | 112);
        return location4bytes;
    }

    public byte[] getLocation5() {
        byte[] location5bytes = this.locationBytes;
        location5bytes[0] = (byte) ((this.locationBytes[0] & 15) | 128);
        return location5bytes;
    }

    public byte[] getLocation1() {
        byte[] location1bytes = this.locationBytes;
        location1bytes[0] = (byte) ((this.locationBytes[0] & 15) | 16);
        return location1bytes;
    }

    public byte[] getLocation2(Bundle location2Bundle) {
        byte[] bArr = new byte[8];
        int Heading = (int) (location2Bundle.getFloat("Heading") * 10.0f);
        if (Heading > 3599) {
            Heading = HeadingMax;
        } else if (Heading < 0) {
            Heading = 0;
        }
        int MSL = (location2Bundle.getInt("MSL") + 1000) / 5;
        if (MSL > 2000) {
            MSL = MSLMax;
        } else if (MSL < 0) {
            MSL = 0;
        }
        int Velocity = (int) (location2Bundle.getFloat("Velocity") * 10.0f);
        if (Velocity > 4095) {
            Velocity = VelocityMax;
        } else if (Velocity < 0) {
            Velocity = 0;
        }
        int CompassDirection = location2Bundle.getInt("CompassDirection");
        if (CompassDirection > 8) {
            CompassDirection = 8;
        } else if (CompassDirection < 1) {
            CompassDirection = 1;
        }
        int GPSSIS = location2Bundle.getInt("GPSSIS");
        if (GPSSIS > 15) {
            GPSSIS = 15;
        } else if (GPSSIS < 0) {
            GPSSIS = 0;
        }
        int GLONASSSIS = location2Bundle.getInt("GLONASSSIS");
        if (GLONASSSIS > 15) {
            GLONASSSIS = 15;
        } else if (GLONASSSIS < 0) {
            GLONASSSIS = 0;
        }
        int GalileoSIS = location2Bundle.getInt("GalileoSIS");
        if (GalileoSIS > 15) {
            GalileoSIS = 15;
        } else if (GalileoSIS < 0) {
            GalileoSIS = 0;
        }
        int CompassSIS = location2Bundle.getInt("CompassSIS");
        if (CompassSIS > 15) {
            CompassSIS = 15;
        } else if (CompassSIS < 0) {
            CompassSIS = 0;
        }
        String binaryString = "0010" + toBinary(Heading, 12) + toBinary(MSL, 11) + "0" + toBinary(Velocity, 12) + toBinary(CompassDirection, 4) + "0100" + toBinary(GPSSIS, 4) + toBinary(GLONASSSIS, 4) + toBinary(GalileoSIS, 4) + toBinary(CompassSIS, 4);
        byte[] location2 = binStrToByteArr(binaryString);
        return location2;
    }

    public byte[] getLocationQuality() {
        byte[] locationQuality = {48, 0, 5, 5, 5};
        return locationQuality;
    }

    public byte[] getSensorQuality() {
        byte[] sensorQuality = {64, 18, 0, 32, 70};
        return sensorQuality;
    }

    public byte[] getSkyView(Bundle skyViewBundle) {
        byte[] skyView = new byte[8];
        int SPID = skyViewBundle.getInt("SPID");
        int SCTN = skyViewBundle.getInt("SCTN");
        skyView[0] = 80;
        skyView[2] = (byte) (SPID & 255);
        skyView[3] = (byte) (SCTN & 255);
        return skyView;
    }

    private static String toBinary(int num, int digits) {
        String cover = Integer.toBinaryString(1 << digits).substring(1);
        String s = Integer.toBinaryString(num);
        if (s.length() < digits) {
            String binaryString = cover.substring(s.length()) + s;
            return binaryString;
        }
        return s;
    }

    private byte[] binStrToByteArr(String binaryString) {
        int len = binaryString.length() / 8;
        String[] subs = new String[len];
        for (int j = 0; j < len; j++) {
            int j2 = j * 8;
            if (j2 + 8 <= binaryString.length()) {
                subs[j] = binaryString.substring(j2, j2 + 8);
            }
        }
        int i = subs.length;
        byte[] b = new byte[i];
        for (int i2 = 0; i2 < b.length; i2++) {
            b[i2] = Long.valueOf(subs[i2], 2).byteValue();
        }
        return b;
    }

    private static String byteArrToBinStr(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            result.append(Long.toString(b[i] & 255, 2) + ",");
        }
        return result.toString().substring(0, result.length() - 1);
    }
}
