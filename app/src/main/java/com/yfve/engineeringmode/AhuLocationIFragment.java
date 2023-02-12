package com.yfve.engineeringmode;

import android.location.Address;
import android.location.Geocoder;
import android.location.GpsStatus;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.yfve.adapter.ListKeyValueAdapter;
import com.yfve.tools.BeanTitleContent;
import com.yfve.tools.Gps;
import com.yfve.tools.GpsUtils;
import com.yfve.tools.LU;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* loaded from: classes1.dex */
public class AhuLocationIFragment extends BaseFragment {
    private ListKeyValueAdapter mApimLiAdapter;
    private ListView mlvApimLiEntry;
    private String[] strsTitle = new String[0];
    private List<BeanTitleContent> mListsApimPn = null;
    private GpsStatusChangedListener mGpsStatusChangedListener = null;
    private GpsLocationChangedListener mGpsLocationChangedListener = null;
    private GpsNmeaListener mGpsNmeaListener = null;
    private Gps mGps = null;
    private Handler mHandler = null;
    private final int ADDRESS_POSITION = 9;
    private boolean mbIsStopThread = false;
    private AdapterView.OnItemClickListener mlvOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.yfve.engineeringmode.AhuLocationIFragment.3
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        }
    };
    private double mlongitude = 10000.0d;
    private double mlatitude = 10000.0d;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_listview_show, (ViewGroup) null);
        this.mlvApimLiEntry = (ListView) view.findViewById(R.id.lv_main_list);
        this.mlvApimLiEntry.setOnItemClickListener(this.mlvOnItemClickListener);
        this.mGps = new Gps();
        initData();
        this.mApimLiAdapter = new ListKeyValueAdapter(this.mMainActivity, this.mListsApimPn);
        this.mlvApimLiEntry.setAdapter((ListAdapter) this.mApimLiAdapter);
        this.mHandler = new Handler() { // from class: com.yfve.engineeringmode.AhuLocationIFragment.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 9) {
                    AhuLocationIFragment.this.refreshGpsValue((String) msg.obj, 9);
                }
            }
        };
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_apim_li));
        this.mbIsStopThread = true;
        GpsUtils.getInstance(this.mMainActivity).setLocationChangedListener(this.mGpsLocationChangedListener);
        GpsUtils.getInstance(this.mMainActivity).setGpsNmeaListener(this.mGpsNmeaListener);
        GpsUtils.getInstance(this.mMainActivity).setStatusListener(this.mGpsStatusChangedListener);
        GpsUtils.getInstance(this.mMainActivity).initGpsLocationData();
        GpsUtils.getInstance(this.mMainActivity).initGpsNmeaData();
        GpsUtils.getInstance(this.mMainActivity).initGpsStatusData();
        new Thread(new Runnable() { // from class: com.yfve.engineeringmode.AhuLocationIFragment.2
            @Override // java.lang.Runnable
            public void run() {
                while (AhuLocationIFragment.this.mbIsStopThread) {
                    try {
                        Thread.sleep(3000L);
                        if (10000.0d != AhuLocationIFragment.this.mlatitude && 10000.0d != AhuLocationIFragment.this.mlongitude) {
                            AhuLocationIFragment.this.getAddress(AhuLocationIFragment.this.mlatitude, AhuLocationIFragment.this.mlongitude);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void sendMessage(int what, Object ob) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = ob;
        this.mHandler.sendMessageDelayed(msg, 0L);
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
        this.mbIsStopThread = false;
        GpsUtils.getInstance(this.mMainActivity).removeLocationUpdates();
        GpsUtils.getInstance(this.mMainActivity).removeNmeaUpdates();
        GpsUtils.getInstance(this.mMainActivity).removeStatusUpdates();
    }

    private void initData() {
        this.mGpsStatusChangedListener = new GpsStatusChangedListener();
        this.mGpsLocationChangedListener = new GpsLocationChangedListener();
        this.mGpsNmeaListener = new GpsNmeaListener();
        this.strsTitle = this.mMainActivity.getResources().getStringArray(R.array.lv_apim_li_title);
        this.mListsApimPn = new ArrayList();
        for (int i = 0; i < this.strsTitle.length; i++) {
            BeanTitleContent beanTitleContent = new BeanTitleContent(this.strsTitle[i], "");
            this.mListsApimPn.add(beanTitleContent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes1.dex */
    public class GpsStatusChangedListener implements GpsUtils.IGpsStatusChangedListener {
        private GpsStatusChangedListener() {
        }

        @Override // com.yfve.tools.GpsUtils.IGpsStatusChangedListener
        public void onGetGpsLocationChangedSnr(String strSnr) {
        }

        @Override // com.yfve.tools.GpsUtils.IGpsStatusChangedListener
        public void onGetGpsStatusCount(int igps) {
            AhuLocationIFragment ahuLocationIFragment = AhuLocationIFragment.this;
            ahuLocationIFragment.refreshGpsValue("" + igps, 13);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes1.dex */
    public class GpsLocationChangedListener implements GpsUtils.IGpsLocationChangedListener {
        private GpsLocationChangedListener() {
        }

        @Override // com.yfve.tools.GpsUtils.IGpsLocationChangedListener
        public void onGetGpsLocationChangedCalendar(Calendar calendar) {
            String date = calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5);
            String time = calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
            LU.d(AhuLocationIFragment.this.tag, "getLocalDatetimeString() date:" + date + " " + time);
            BeanTitleContent beanTitleContent0 = (BeanTitleContent) AhuLocationIFragment.this.mApimLiAdapter.getItem(0);
            beanTitleContent0.setMstrContent(time);
            BeanTitleContent beanTitleContent1 = (BeanTitleContent) AhuLocationIFragment.this.mApimLiAdapter.getItem(1);
            beanTitleContent1.setMstrContent(date);
        }

        @Override // com.yfve.tools.GpsUtils.IGpsLocationChangedListener
        public void onGetGpsLocationChangedLongitude(double longitude) {
            AhuLocationIFragment.this.mlongitude = longitude;
            BeanTitleContent beanTitleContent3 = (BeanTitleContent) AhuLocationIFragment.this.mApimLiAdapter.getItem(3);
            beanTitleContent3.setMstrContent("" + longitude);
        }

        @Override // com.yfve.tools.GpsUtils.IGpsLocationChangedListener
        public void onGetGpsLocationChangedLatitude(double latitude) {
            AhuLocationIFragment.this.mlatitude = latitude;
            BeanTitleContent beanTitleContent2 = (BeanTitleContent) AhuLocationIFragment.this.mApimLiAdapter.getItem(2);
            beanTitleContent2.setMstrContent("" + latitude);
        }

        @Override // com.yfve.tools.GpsUtils.IGpsLocationChangedListener
        public void onGetGpsLocation(Location location) {
            BeanTitleContent beanTitleContent4 = (BeanTitleContent) AhuLocationIFragment.this.mApimLiAdapter.getItem(4);
            beanTitleContent4.setMstrContent("" + location.getBearing());
            BeanTitleContent beanTitleContent10 = (BeanTitleContent) AhuLocationIFragment.this.mApimLiAdapter.getItem(10);
            beanTitleContent10.setMstrContent("" + location.getAccuracy());
            BeanTitleContent beanTitleContent11 = (BeanTitleContent) AhuLocationIFragment.this.mApimLiAdapter.getItem(11);
            beanTitleContent11.setMstrContent("" + location.getAccuracy());
            BeanTitleContent beanTitleContent12 = (BeanTitleContent) AhuLocationIFragment.this.mApimLiAdapter.getItem(12);
            beanTitleContent12.setMstrContent("" + location.getSpeed());
            BeanTitleContent beanTitleContent5 = (BeanTitleContent) AhuLocationIFragment.this.mApimLiAdapter.getItem(5);
            beanTitleContent5.setMstrContent("" + location.getAltitude());
            AhuLocationIFragment.this.mApimLiAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes1.dex */
    public class GpsNmeaListener implements GpsUtils.IGpsNmeaListenerListener {
        private GpsNmeaListener() {
        }

        @Override // com.yfve.tools.GpsUtils.IGpsNmeaListenerListener
        public void onGetNmeaReceived(long timestamp, String nmea) {
            AhuLocationIFragment.this.mGps.processNmeaData(nmea);
            if (nmea.contains("GPGGA")) {
                nmea.split(",");
                AhuLocationIFragment.this.refreshGpsValue(String.valueOf(AhuLocationIFragment.this.mGps.getHdop()), 6);
                AhuLocationIFragment.this.refreshGpsValue(String.valueOf(AhuLocationIFragment.this.mGps.getPdop()), 7);
                AhuLocationIFragment.this.refreshGpsValue(String.valueOf(AhuLocationIFragment.this.mGps.getVdop()), 8);
                AhuLocationIFragment.this.refreshGpsValue(String.valueOf(AhuLocationIFragment.this.mGps.getSpeed()), 12);
                AhuLocationIFragment.this.refreshGpsValue("GPS", 14);
                GpsStatus gpsStatus = GpsUtils.getInstance(AhuLocationIFragment.this.mMainActivity).getGpsStatus();
                AhuLocationIFragment.this.refreshGpsValue(String.valueOf(gpsStatus.getTimeToFirstFix()), 15);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshGpsValue(String data, int position) {
        this.mListsApimPn.get(position).setMstrContent(data);
        String str = this.tag;
        LU.d(str, "setGpsValue: position= " + position + ",data = " + data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAddress(double latitude, double longitude) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Geocoder gc = new Geocoder(this.mMainActivity, Locale.getDefault());
            List<Address> addresses = gc.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                stringBuilder.append(address.getCountryName());
                stringBuilder.append("_");
                stringBuilder.append(address.getAdminArea());
                stringBuilder.append("_");
                stringBuilder.append(address.getLocality());
                stringBuilder.append("_");
                stringBuilder.append(address.getSubLocality());
                stringBuilder.append("_");
                stringBuilder.append(address.getFeatureName());
                sendMessage(9, stringBuilder.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }
}
