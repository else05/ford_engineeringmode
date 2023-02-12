package com.yfve.engineeringmode;

import android.car.CarInfoManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.yfve.adapter.ListKeyValueAdapter;
import com.yfve.tools.BeanTitleContent;
import com.yfve.tools.LU;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes1.dex */
public class ConfigFragment extends BaseFragment {
    private ListKeyValueAdapter mApimPnAdapter;
    private List<BeanTitleContent> mConfigList;
    private ListView mLvAhuEntry;
    private String[] strsEntrys = new String[0];
    private Map<Integer, Integer> mConfMap = new HashMap();

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_listview_show, (ViewGroup) null);
        this.mLvAhuEntry = (ListView) view.findViewById(R.id.lv_main_list);
        initConfMap();
        this.mConfigList = new ArrayList();
        this.strsEntrys = this.mMainActivity.getResources().getStringArray(R.array.conf_list);
        String str = this.tag;
        LU.w(str, "onCreateView()  length  map =" + this.mConfMap.size() + "   strsEntrys ==" + this.strsEntrys.length);
        initDate();
        this.mApimPnAdapter = new ListKeyValueAdapter(this.mMainActivity, this.mConfigList);
        this.mLvAhuEntry.setAdapter((ListAdapter) this.mApimPnAdapter);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_ahu_config));
    }

    private void initDate() {
        String str = this.tag;
        LU.w(str, "initDate()  mCarInfoManager == " + this.mMainActivity.mCarInfoManager);
        for (int i = 0; i < this.strsEntrys.length; i++) {
            try {
                byte[] data = this.mMainActivity.mCarInfoManager.getBytesProprety(this.mConfMap.get(Integer.valueOf(i)).intValue());
                BeanTitleContent beanTitleContent = new BeanTitleContent(this.strsEntrys[i], formatByte(data));
                this.mConfigList.add(beanTitleContent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String formatByte(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(Integer.toHexString(b));
        }
        return sb.toString();
    }

    private void initConfMap() {
        this.mConfMap.put(0, Integer.valueOf((int) CarInfoManager.BASIC_INFO_KEY_MODEL_VENDOR));
        this.mConfMap.put(1, Integer.valueOf((int) CarInfoManager.BASIC_INFO_KEY_MODEL_YEAR_VENDOR));
        this.mConfMap.put(2, Integer.valueOf((int) CarInfoManager.BASIC_INFO_KEY_BODY_STYLE));
        this.mConfMap.put(3, Integer.valueOf((int) CarInfoManager.BASIC_INFO_KEY_SERIES));
        this.mConfMap.put(4, Integer.valueOf((int) CarInfoManager.BASIC_INFO_KEY_COLOR));
        this.mConfMap.put(5, Integer.valueOf((int) CarInfoManager.BASIC_INFO_KEY_ANIMATION));
        this.mConfMap.put(6, Integer.valueOf((int) CarInfoManager.BASIC_INFO_KEY_HMI));
        this.mConfMap.put(7, Integer.valueOf((int) CarInfoManager.BASIC_INFO_KEY_DISPLAY_VARIANTS));
        this.mConfMap.put(8, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_SWC));
        this.mConfMap.put(9, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_EFP));
        this.mConfMap.put(10, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_REAR_EFP));
        this.mConfMap.put(11, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_WACM));
        this.mConfMap.put(12, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_AMBIENT_LIGHT));
        this.mConfMap.put(13, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_MC_SEAT));
        this.mConfMap.put(14, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_TCS));
        this.mConfMap.put(15, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_TCU));
        this.mConfMap.put(16, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_PDC_HMI));
        this.mConfMap.put(17, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_PDC_RVC));
        this.mConfMap.put(18, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_360_CAMERA));
        this.mConfMap.put(19, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_APA));
        this.mConfMap.put(20, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_CTA));
        this.mConfMap.put(21, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_MT_OR_AT));
        this.mConfMap.put(22, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_CLIMATE_DOMAIN));
        this.mConfMap.put(23, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_REAR_HVAC));
        this.mConfMap.put(24, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_HEAT_COOL_SEAT));
        this.mConfMap.put(25, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_HEATED_SW));
        this.mConfMap.put(26, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_FRESH_AIR_CABIN));
        this.mConfMap.put(27, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_FANSPEED_VR));
        this.mConfMap.put(28, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_LIST_BROWSER));
        this.mConfMap.put(29, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_GYRO));
        this.mConfMap.put(30, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_MYKEY));
        this.mConfMap.put(31, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_LOW_FUEL));
        this.mConfMap.put(32, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_FUEL_TYPE));
        this.mConfMap.put(33, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_ILLUMINATION));
        this.mConfMap.put(34, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_CGEA_VERSION));
        this.mConfMap.put(35, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_APNURL));
        this.mConfMap.put(36, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_TPMS));
        this.mConfMap.put(37, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_BT_TURNING));
        this.mConfMap.put(38, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_EXENDED_PLAY));
        this.mConfMap.put(39, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_SMART_DSP));
        this.mConfMap.put(40, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_SOUND_MODE));
        this.mConfMap.put(41, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_QUANTUM_LOGIC_IMMERSION));
        this.mConfMap.put(42, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_QUANTUM_LOGIC_SURROUNDING));
        this.mConfMap.put(43, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_THX));
        this.mConfMap.put(44, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_REVEL));
        this.mConfMap.put(45, Integer.valueOf((int) CarInfoManager.INFO_KEY_ANC_OR_ESE));
        this.mConfMap.put(46, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_FRONT_SPEAKER));
        this.mConfMap.put(47, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_REAR_SPEAKER));
        this.mConfMap.put(48, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_FRONT_SPK_DETECT));
        this.mConfMap.put(49, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_REAR_SPK_DETECT));
        this.mConfMap.put(50, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_CHIME_STRATEGY));
        this.mConfMap.put(51, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_TURN_CHIMES));
        this.mConfMap.put(52, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_FRONT_TWEETER));
        this.mConfMap.put(53, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_REAR_TWEETER));
        this.mConfMap.put(54, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_ANC_LEFT_FRONT_MIX));
        this.mConfMap.put(55, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_ANC_RIGHT_FRONT_MIX));
        this.mConfMap.put(56, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_ANC_REAR_MIX));
        this.mConfMap.put(57, Integer.valueOf((int) CarInfoManager.INFO_KEY_ENABLE_OCCU_PHONE));
        this.mConfMap.put(58, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_ANTENNA_TEST_VALUE));
        this.mConfMap.put(59, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_AM_ANTENNA_TEST));
        this.mConfMap.put(60, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_GAIN_ADJUST));
        this.mConfMap.put(61, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_EQ));
        this.mConfMap.put(62, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_FM_SEEK_SENSITIVY));
        this.mConfMap.put(63, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_LOW_MODE_AM));
        this.mConfMap.put(64, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_HIGH_MODE_AM));
        this.mConfMap.put(65, Integer.valueOf((int) CarInfoManager.ID_INFO_AM_PRESET1));
        this.mConfMap.put(66, Integer.valueOf((int) CarInfoManager.ID_INFO_AM_PRESET2));
        this.mConfMap.put(67, Integer.valueOf((int) CarInfoManager.ID_INFO_AM_PRESET3));
        this.mConfMap.put(68, Integer.valueOf((int) CarInfoManager.ID_INFO_AM_PRESET4));
        this.mConfMap.put(69, Integer.valueOf((int) CarInfoManager.ID_INFO_AM_PRESET5));
        this.mConfMap.put(70, Integer.valueOf((int) CarInfoManager.ID_INFO_AM_PRESET6));
        this.mConfMap.put(71, Integer.valueOf((int) CarInfoManager.ID_INFO_DSP_INNER_VERSION));
        this.mConfMap.put(72, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_HEATED_WIND));
        this.mConfMap.put(73, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_TSR));
        this.mConfMap.put(74, Integer.valueOf((int) CarInfoManager.INFO_KEY_PHONE_LEVEL));
        this.mConfMap.put(75, Integer.valueOf((int) CarInfoManager.INFO_KEY_CONFIG_A2B));
        this.mConfMap.put(76, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET1));
        this.mConfMap.put(77, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET2));
        this.mConfMap.put(78, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET3));
        this.mConfMap.put(79, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET4));
        this.mConfMap.put(80, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET5));
        this.mConfMap.put(81, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET6));
        this.mConfMap.put(82, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET7));
        this.mConfMap.put(83, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET8));
        this.mConfMap.put(84, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET9));
        this.mConfMap.put(85, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET10));
        this.mConfMap.put(86, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET11));
        this.mConfMap.put(87, Integer.valueOf((int) CarInfoManager.ID_INFO_FM_PRESET12));
        this.mConfMap.put(88, Integer.valueOf((int) CarInfoManager.ID_INFO_LIN_VERSION));
        this.mConfMap.put(89, Integer.valueOf((int) CarInfoManager.ID_INFO_WIFI_MSG));
    }
}
