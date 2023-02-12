package android.car;

/* loaded from: classes2.dex */
public final class VehicleZoneUtil {
    public static int zoneToIndex(int zones, int zone) throws IllegalArgumentException {
        if (zone == 0 || (zone & zones) != zone || ((zone - 1) & zone) != 0) {
            throw new IllegalArgumentException("Invalid zones 0x" + Integer.toHexString(zones) + " or zone 0x" + Integer.toHexString(zone));
        }
        int index = -1;
        while ((zone & zones) != 0) {
            index++;
            zones &= zones - 1;
        }
        return index;
    }

    public static int getNumberOfZones(int zones) {
        int numZones = 0;
        while (zones != 0) {
            zones &= zones - 1;
            numZones++;
        }
        return numZones;
    }

    public static int getFirstZone(int zones) {
        if (zones == 0) {
            return 0;
        }
        int xorFlag = (zones - 1) & zones;
        return zones ^ xorFlag;
    }

    public static int getNextZone(int zones, int startingZone) throws IllegalArgumentException {
        if (((startingZone - 1) & startingZone) != 0 || startingZone == 0) {
            throw new IllegalArgumentException("Starting zone should represent only one bit flag: 0x" + Integer.toHexString(startingZone));
        }
        int mask = startingZone << 1;
        return getFirstZone(zones & (~(mask - 1)));
    }

    public static int[] listAllZones(int zones) {
        int numberOfZones = getNumberOfZones(zones);
        int[] list = new int[numberOfZones];
        if (numberOfZones == 0) {
            return list;
        }
        int arrayIndex = 0;
        while (zones != 0) {
            int xorFlag = (zones - 1) & zones;
            int zone = zones ^ xorFlag;
            list[arrayIndex] = zone;
            zones = xorFlag;
            arrayIndex++;
        }
        return list;
    }

    private VehicleZoneUtil() {
    }
}
