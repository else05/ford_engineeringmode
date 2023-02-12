package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAudioContextFlag {
    public static final int ALARM_FLAG = 16;
    public static final int AUX_AUDIO_FLAG = 512;
    public static final int CALL_FLAG = 8;
    public static final int CD_ROM_FLAG = 256;
    public static final int EXT_SOURCE_FLAG = 4096;
    public static final int MUSIC_FLAG = 1;
    public static final int NAVIGATION_FLAG = 2;
    public static final int NOTIFICATION_FLAG = 32;
    public static final int RADIO_FLAG = 2048;
    public static final int RINGTONE_FLAG = 8192;
    public static final int SAFETY_ALERT_FLAG = 128;
    public static final int SYSTEM_SOUND_FLAG = 1024;
    public static final int UNKNOWN_FLAG = 64;
    public static final int VOICE_COMMAND_FLAG = 4;

    public static final String toString(int o) {
        if (o == 1) {
            return "MUSIC_FLAG";
        }
        if (o == 2) {
            return "NAVIGATION_FLAG";
        }
        if (o == 4) {
            return "VOICE_COMMAND_FLAG";
        }
        if (o == 8) {
            return "CALL_FLAG";
        }
        if (o == 16) {
            return "ALARM_FLAG";
        }
        if (o == 32) {
            return "NOTIFICATION_FLAG";
        }
        if (o == 64) {
            return "UNKNOWN_FLAG";
        }
        if (o == 128) {
            return "SAFETY_ALERT_FLAG";
        }
        if (o == 256) {
            return "CD_ROM_FLAG";
        }
        if (o == 512) {
            return "AUX_AUDIO_FLAG";
        }
        if (o == 1024) {
            return "SYSTEM_SOUND_FLAG";
        }
        if (o == 2048) {
            return "RADIO_FLAG";
        }
        if (o == 4096) {
            return "EXT_SOURCE_FLAG";
        }
        if (o == 8192) {
            return "RINGTONE_FLAG";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("MUSIC_FLAG");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("NAVIGATION_FLAG");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("VOICE_COMMAND_FLAG");
            flipped |= 4;
        }
        if ((o & 8) == 8) {
            list.add("CALL_FLAG");
            flipped |= 8;
        }
        if ((o & 16) == 16) {
            list.add("ALARM_FLAG");
            flipped |= 16;
        }
        if ((o & 32) == 32) {
            list.add("NOTIFICATION_FLAG");
            flipped |= 32;
        }
        if ((o & 64) == 64) {
            list.add("UNKNOWN_FLAG");
            flipped |= 64;
        }
        if ((o & 128) == 128) {
            list.add("SAFETY_ALERT_FLAG");
            flipped |= 128;
        }
        if ((o & 256) == 256) {
            list.add("CD_ROM_FLAG");
            flipped |= 256;
        }
        if ((o & 512) == 512) {
            list.add("AUX_AUDIO_FLAG");
            flipped |= 512;
        }
        if ((o & 1024) == 1024) {
            list.add("SYSTEM_SOUND_FLAG");
            flipped |= 1024;
        }
        if ((o & 2048) == 2048) {
            list.add("RADIO_FLAG");
            flipped |= 2048;
        }
        if ((o & 4096) == 4096) {
            list.add("EXT_SOURCE_FLAG");
            flipped |= 4096;
        }
        if ((o & 8192) == 8192) {
            list.add("RINGTONE_FLAG");
            flipped |= 8192;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
