package com.yfve.engineeringmode;

import android.car.CarInfoManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.yfve.adapter.ListKeyValueAdapter;
import com.yfve.tools.BeanTitleContent;
import com.yfve.tools.LU;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes1.dex */
public class AhuPnFragment extends BaseFragment {
    private ListKeyValueAdapter mApimPnAdapter;
    private List<BeanTitleContent> mListsApuPn;
    private ListView mlvApuEntry;
    private String[] mstrsEntrys = new String[0];
    private final float DEVICE_SIZE_32_OR_64 = 16.0f;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_listview_show, (ViewGroup) null);
        this.mlvApuEntry = (ListView) view.findViewById(R.id.lv_main_list);
        initData();
        onServiceConnected();
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_ahu_pn));
    }

    private void initData() {
        this.mstrsEntrys = this.mMainActivity.getResources().getStringArray(R.array.lv_apim_pn);
        this.mListsApuPn = new ArrayList();
        for (int i = 0; i < this.mstrsEntrys.length; i++) {
            BeanTitleContent beanTitleContent = new BeanTitleContent(this.mstrsEntrys[i], "");
            this.mListsApuPn.add(beanTitleContent);
        }
        this.mApimPnAdapter = new ListKeyValueAdapter(this.mMainActivity, this.mListsApuPn);
        this.mlvApuEntry.setAdapter((ListAdapter) this.mApimPnAdapter);
    }

    public void onServiceConnected() {
        try {
            String number = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.BASIC_INFO_KEY_ESN);
            this.mListsApuPn.get(0).setMstrContent(number);
            String vin = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.BASIC_INFO_KEY_VIN);
            this.mListsApuPn.get(1).setMstrContent(vin);
            String ass = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.ID_INFO_AUH_ASS);
            this.mListsApuPn.get(2).setMstrContent(ass);
            String hw = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.ID_INFO_AUH_HW);
            this.mListsApuPn.get(3).setMstrContent(hw);
            String ccpu = get8033ValueShow();
            if ("NULL".equals(ccpu)) {
                ccpu = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.ID_INFO_AUH_CCPU);
            }
            this.mListsApuPn.get(4).setMstrContent(ccpu);
            String vmcu = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.ID_INFO_AUH_VMCU);
            this.mListsApuPn.get(5).setMstrContent(vmcu);
            String calF10A = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.ID_INFO_AUH_CAL_F10A);
            this.mListsApuPn.get(6).setMstrContent(calF10A);
            String calF16B = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.ID_INFO_AUH_CAL_F16B);
            this.mListsApuPn.get(7).setMstrContent(calF16B);
            String calF16C = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.ID_INFO_AUH_CAL_F16C);
            this.mListsApuPn.get(8).setMstrContent(calF16C);
            this.mApimPnAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            String str = this.tag;
            LU.e(str, "onServiceConnected()  Exception==" + e.toString());
            this.mApimPnAdapter.notifyDataSetChanged();
        }
    }

    private String get8033ValueShow() {
        String str8033Value;
        try {
            StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
            long totalBytes = stat.getTotalBytes();
            float fTotal = ((float) totalBytes) / 1.0E9f;
            String str = this.tag;
            LU.e(str, "get8033ValueShow()     fTotal==" + fTotal);
            if (fTotal > 16.0f) {
                LU.w(this.tag, "get8033ValueShow()  size is 64");
                str8033Value = get("ro.vendor.yfve.ccpupn.adasmap");
            } else {
                LU.w(this.tag, "get8033ValueShow()  size is 32");
                str8033Value = get("ro.vendor.yfve.ccpupn");
            }
            String str2 = this.tag;
            LU.w(str2, "get8033ValueShow()  str8033Value==" + str8033Value);
            return str8033Value;
        } catch (Exception e) {
            String str3 = this.tag;
            LU.e(str3, "getMemoryInfo()  Exception e==" + e.toString());
            return "NULL";
        }
    }

    public String get(String key) {
        Method getMethod = null;
        if (0 == 0) {
            try {
                getMethod = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            } catch (Exception e) {
                return "null";
            }
        }
        return (String) getMethod.invoke(null, key);
    }
}
