package com.github.mikephil.charting.utils;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class FileUtils {
    private static final String LOG = "MPChart-FileUtils";

    public static List<Entry> loadEntriesFromFile(String path) {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, path);
        List<Entry> entries = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] split = line.split("#");
                int i = 0;
                if (split.length <= 2) {
                    entries.add(new Entry(Float.parseFloat(split[0]), Integer.parseInt(split[1])));
                } else {
                    float[] vals = new float[split.length - 1];
                    while (true) {
                        int i2 = i;
                        int i3 = vals.length;
                        if (i2 >= i3) {
                            break;
                        }
                        vals[i2] = Float.parseFloat(split[i2]);
                        i = i2 + 1;
                    }
                    entries.add(new BarEntry(Integer.parseInt(split[split.length - 1]), vals));
                }
            }
        } catch (IOException e) {
            Log.e(LOG, e.toString());
        }
        return entries;
    }

    public static List<Entry> loadEntriesFromAssets(AssetManager am, String path) {
        List<Entry> entries = new ArrayList<>();
        BufferedReader reader = null;
        try {
        } catch (IOException e) {
            Log.e(LOG, e.toString());
        }
        try {
            try {
                reader = new BufferedReader(new InputStreamReader(am.open(path), "UTF-8"));
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    String[] split = line.split("#");
                    int i = 0;
                    if (split.length <= 2) {
                        entries.add(new Entry(Float.parseFloat(split[1]), Float.parseFloat(split[0])));
                    } else {
                        float[] vals = new float[split.length - 1];
                        while (true) {
                            int i2 = i;
                            int i3 = vals.length;
                            if (i2 >= i3) {
                                break;
                            }
                            vals[i2] = Float.parseFloat(split[i2]);
                            i = i2 + 1;
                        }
                        entries.add(new BarEntry(Integer.parseInt(split[split.length - 1]), vals));
                    }
                }
                reader.close();
            } catch (IOException e2) {
                Log.e(LOG, e2.toString());
                if (reader != null) {
                    reader.close();
                }
            }
            return entries;
        } catch (Throwable th) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e3) {
                    Log.e(LOG, e3.toString());
                }
            }
            throw th;
        }
    }

    public static void saveToSdCard(List<Entry> entries, String path) {
        File sdcard = Environment.getExternalStorageDirectory();
        File saved = new File(sdcard, path);
        if (!saved.exists()) {
            try {
                saved.createNewFile();
            } catch (IOException e) {
                Log.e(LOG, e.toString());
            }
        }
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(saved, true));
            for (Entry e2 : entries) {
                buf.append((CharSequence) (e2.getY() + "#" + e2.getX()));
                buf.newLine();
            }
            buf.close();
        } catch (IOException e3) {
            Log.e(LOG, e3.toString());
        }
    }

    public static List<BarEntry> loadBarEntriesFromAssets(AssetManager am, String path) {
        List<BarEntry> entries = new ArrayList<>();
        BufferedReader reader = null;
        try {
            try {
                try {
                    reader = new BufferedReader(new InputStreamReader(am.open(path), "UTF-8"));
                    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                        String[] split = line.split("#");
                        entries.add(new BarEntry(Float.parseFloat(split[1]), Float.parseFloat(split[0])));
                    }
                    reader.close();
                } catch (Throwable th) {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            Log.e(LOG, e.toString());
                        }
                    }
                    throw th;
                }
            } catch (IOException e2) {
                Log.e(LOG, e2.toString());
                if (reader != null) {
                    reader.close();
                }
            }
        } catch (IOException e3) {
            Log.e(LOG, e3.toString());
        }
        return entries;
    }
}
