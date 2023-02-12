package com.yfve.engineeringmode;

import android.car.CarInfoManager;
import android.car.hardware.LocationProtocal;
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
public class ConfigP2Fragment extends BaseFragment {
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
        this.strsEntrys = this.mMainActivity.getResources().getStringArray(R.array.conf_list_p2);
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
        this.mConfMap.put(89, Integer.valueOf((int) CarInfoManager.ID_INFO_ANC_OR_ESE_EQ_CARLINE));
        this.mConfMap.put(90, Integer.valueOf((int) CarInfoManager.ID_INFO_ANC_OR_ESE_BODY_STYLE));
        this.mConfMap.put(91, Integer.valueOf((int) CarInfoManager.ID_INFO_ANC_OR_ESE_SPEAK_CONFIG));
        this.mConfMap.put(92, Integer.valueOf((int) CarInfoManager.ID_INFO_ANC_OR_ESE_BRANDING));
        this.mConfMap.put(93, Integer.valueOf((int) CarInfoManager.ID_INFO_ANC_OR_ESE_SWP));
        this.mConfMap.put(94, Integer.valueOf((int) CarInfoManager.ID_INFO_ANC_OR_ESE_EQ_SPECIAL_MODES));
        this.mConfMap.put(95, Integer.valueOf((int) CarInfoManager.ID_INFO_ANC_OR_ESE_ENGINE));
        this.mConfMap.put(96, Integer.valueOf((int) CarInfoManager.ID_INFO_ANC_OR_ESE_GEARBOX));
        this.mConfMap.put(97, Integer.valueOf((int) CarInfoManager.ID_INFO_AES_NO_OVERRIDE));
        this.mConfMap.put(98, Integer.valueOf((int) CarInfoManager.ID_INFO_AES_OVERRIDE));
        this.mConfMap.put(99, Integer.valueOf((int) CarInfoManager.ID_INFO_ACC_MENU));
        this.mConfMap.put(100, Integer.valueOf((int) CarInfoManager.ID_INFO_ADPT_HEAD_LAMP_CONTROL));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_textAppearanceSearchResultTitle), Integer.valueOf((int) CarInfoManager.ID_INFO_ADJ_SPEED_LIMITER_DEVICE));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu), Integer.valueOf((int) CarInfoManager.ID_INFO_ADVANCETRAC_CONTROL));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_textColorAlertDialogListItem), Integer.valueOf((int) CarInfoManager.ID_INFO_AUTO_HIGH_BEAM_CONTROL));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_textColorSearchUrl), Integer.valueOf((int) CarInfoManager.ID_INFO_AUTOLAMP_DELAY));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_toolbarNavigationButtonStyle), Integer.valueOf((int) CarInfoManager.ID_INFO_AUTOLOCK_CONTROL));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_toolbarStyle), Integer.valueOf((int) CarInfoManager.ID_INFO_ADPT_HEAD_LAMP_TRAFFIC));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_tooltipForegroundColor), Integer.valueOf((int) CarInfoManager.ID_INFO_AUTO_RELOCK));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_tooltipFrameBackground), Integer.valueOf((int) CarInfoManager.ID_INFO_AUTOUNLOCK_CONTROL));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_windowActionBar), Integer.valueOf((int) CarInfoManager.ID_INFO_CITY_SAFETY));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_windowActionBarOverlay), Integer.valueOf((int) CarInfoManager.ID_INFO_APPROACH_DETECTION_CONTROL));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_windowActionModeOverlay), Integer.valueOf((int) CarInfoManager.ID_INFO_COURTESY_WIPE_AFTER_WASH));
        this.mConfMap.put(112, Integer.valueOf((int) CarInfoManager.ID_INFO_DRIVER_ALERT_SYSTEM));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_windowFixedHeightMinor), Integer.valueOf((int) CarInfoManager.ID_INFO_EASY_ENTRY_EXIT));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_windowFixedWidthMajor), Integer.valueOf((int) CarInfoManager.ID_INFO_FORWARD_COLLISION_WARNING));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_windowFixedWidthMinor), Integer.valueOf((int) CarInfoManager.ID_INFO_FUEL_OPERATED_HEATER));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_windowMinWidthMajor), Integer.valueOf((int) CarInfoManager.ID_INFO_WINDOWS_REMOTE_OPEN));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_windowMinWidthMinor), Integer.valueOf((int) CarInfoManager.ID_INFO_WINDOWS_REMOTE_CLOSE));
        this.mConfMap.put(Integer.valueOf((int) R.styleable.AppCompatTheme_windowNoTitle), Integer.valueOf((int) CarInfoManager.ID_INFO_DAYTIME_RUNLAMP_CONTROL));
        this.mConfMap.put(119, Integer.valueOf((int) CarInfoManager.ID_INFO_TEMPORARY_MOBILITY_KIT));
        this.mConfMap.put(120, Integer.valueOf((int) CarInfoManager.ID_INFO_MIRRORS_AUTOFOLD));
        this.mConfMap.put(121, Integer.valueOf((int) CarInfoManager.ID_INFO_MIRRORS_REVERSE_TILT));
        this.mConfMap.put(122, Integer.valueOf((int) CarInfoManager.ID_INFO_DO_NOT_DISTURB));
        this.mConfMap.put(123, Integer.valueOf((int) CarInfoManager.ID_INFO_FUEL_OPERATED_PARK_HEATER));
        this.mConfMap.put(124, Integer.valueOf((int) CarInfoManager.ID_INFO_ONE_OR_TWO_STAGE_UNLOCKING));
        this.mConfMap.put(125, Integer.valueOf((int) CarInfoManager.ID_INFO_PERIMETER_ALARM_CONTROL));
        this.mConfMap.put(126, Integer.valueOf((int) CarInfoManager.ID_INFO_POWER_LIFTGATE_CONTROL));
        this.mConfMap.put(127, Integer.valueOf((int) CarInfoManager.ID_INFO_RRGW));
        this.mConfMap.put(128, Integer.valueOf((int) CarInfoManager.ID_INFO_RS_CLIMATE_SETTINGS));
        this.mConfMap.put(129, Integer.valueOf((int) CarInfoManager.ID_INFO_RS_DRIVER_SEAT));
        this.mConfMap.put(130, Integer.valueOf((int) CarInfoManager.ID_INFO_RS_FEATURE));
        this.mConfMap.put(131, Integer.valueOf((int) CarInfoManager.ID_INFO_RS_PASSENGER_SEAT));
        this.mConfMap.put(132, Integer.valueOf((int) CarInfoManager.ID_INFO_RS_REAR_DEFROST));
        this.mConfMap.put(133, Integer.valueOf((int) CarInfoManager.ID_INFO_RS_STEERING_WHEEL));
        this.mConfMap.put(134, Integer.valueOf((int) CarInfoManager.ID_INFO_BLINDSPOT));
        this.mConfMap.put(135, Integer.valueOf((int) CarInfoManager.ID_INFO_INTLIG_SPEED_ASSISTANCE));
        this.mConfMap.put(136, Integer.valueOf((int) CarInfoManager.ID_INFO_TRAILER_SWAY));
        this.mConfMap.put(137, Integer.valueOf((int) CarInfoManager.ID_INFO_PRECOLLISION));
        this.mConfMap.put(138, Integer.valueOf((int) CarInfoManager.ID_INFO_INTLIG_ACCESS_MENU));
        this.mConfMap.put(139, Integer.valueOf((int) CarInfoManager.ID_INFO_SILENT_MODE_CONTROL));
        this.mConfMap.put(140, Integer.valueOf((int) CarInfoManager.ID_INFO_TPMS_RESET));
        this.mConfMap.put(141, Integer.valueOf((int) CarInfoManager.ID_INFO_AUTO_HOLD));
        this.mConfMap.put(142, Integer.valueOf((int) CarInfoManager.ID_INFO_HILL_START_ASSIST));
        this.mConfMap.put(143, Integer.valueOf((int) CarInfoManager.ID_INFO_MYKEY_MENU));
        this.mConfMap.put(144, Integer.valueOf((int) CarInfoManager.ID_INFO_RAIN_SENSOR));
        this.mConfMap.put(145, Integer.valueOf((int) CarInfoManager.ID_INFO_FCW_BRAKING_ONOFF));
        this.mConfMap.put(146, Integer.valueOf((int) CarInfoManager.ID_INFO_LOCKING_FDB_AUDIBLE));
        this.mConfMap.put(147, Integer.valueOf((int) CarInfoManager.ID_INFO_LOCKING_FDB_VISUAL));
        this.mConfMap.put(148, Integer.valueOf((int) CarInfoManager.ID_INFO_EVA_STEERING_ASSIST));
        this.mConfMap.put(149, Integer.valueOf((int) CarInfoManager.ID_INFO_LANE_ASSIST_HAPTIC_INSITY));
        this.mConfMap.put(150, Integer.valueOf((int) CarInfoManager.ID_INFO_INTLIG_ADPT_CRUISE_CONTROL));
        this.mConfMap.put(151, Integer.valueOf((int) CarInfoManager.ID_INFO_ADPT_HEADLAMPS_FEATURE));
        this.mConfMap.put(152, Integer.valueOf((int) CarInfoManager.ID_INFO_LANE_CHANGE_ASSIST));
        this.mConfMap.put(153, Integer.valueOf((int) CarInfoManager.ID_INFO_LANE_KEEPING_SENTIVTY));
        this.mConfMap.put(154, Integer.valueOf((int) CarInfoManager.ID_INFO_ADVTRAC_HARD_BUTTON_CONTROL));
        this.mConfMap.put(155, Integer.valueOf((int) CarInfoManager.ID_INFO_TRACTION_CONTROL));
        this.mConfMap.put(156, Integer.valueOf((int) CarInfoManager.ID_INFO_ADAPTIVE_CRUISE));
        this.mConfMap.put(157, Integer.valueOf((int) CarInfoManager.ID_INFO_TSR_NCAP_ADAPTATIONS));
        this.mConfMap.put(158, Integer.valueOf((int) CarInfoManager.ID_INFO_TSR_OVERSPEED_CHIME));
        this.mConfMap.put(159, Integer.valueOf((int) CarInfoManager.ID_INFO_TWO_HAUL));
        this.mConfMap.put(160, Integer.valueOf((int) CarInfoManager.ID_INFO_TRAILER_BLIND_SPOT));
        this.mConfMap.put(161, Integer.valueOf((int) CarInfoManager.ID_INFO_WRONG_WAY_ALERT));
        this.mConfMap.put(162, Integer.valueOf((int) CarInfoManager.ID_INFO_HILL_DESCENT_CONTROL));
        this.mConfMap.put(163, Integer.valueOf((int) CarInfoManager.ID_INFO_REAR_BRAKE_ASSIST));
        this.mConfMap.put(164, Integer.valueOf((int) CarInfoManager.ID_INFO_EV_AND_MODE));
        this.mConfMap.put(165, Integer.valueOf((int) CarInfoManager.ID_INFO_RUNING_BOARD_CONTROL));
        this.mConfMap.put(166, Integer.valueOf((int) CarInfoManager.ID_INFO_PARK_LOCK_CONTROL_ALLW));
        this.mConfMap.put(167, Integer.valueOf((int) CarInfoManager.ID_INFO_AUTO_START_STOP));
        this.mConfMap.put(168, Integer.valueOf((int) CarInfoManager.ID_INFO_PERIM_ALARM_GUARD_REMINDER));
        this.mConfMap.put(169, Integer.valueOf((int) CarInfoManager.ID_INFO_TRAFFIC_SIGN_RECOG));
        this.mConfMap.put(170, Integer.valueOf((int) CarInfoManager.ID_INFO_TPMS_BY_LOCATION));
        this.mConfMap.put(171, Integer.valueOf((int) CarInfoManager.ID_INFO_TPMS_PLA_PRESSURE_DISPLAY));
        this.mConfMap.put(172, Integer.valueOf((int) CarInfoManager.ID_INFO_CENTERSTACK_SETTING));
        this.mConfMap.put(173, Integer.valueOf((int) CarInfoManager.ID_INFO_PREDICTIVE_LIGHTS));
        this.mConfMap.put(174, Integer.valueOf((int) CarInfoManager.ID_INFO_LANEA_NCAP_AID));
        this.mConfMap.put(175, Integer.valueOf((int) CarInfoManager.ID_INFO_LANEA_NCAP_ALERT));
        this.mConfMap.put(176, Integer.valueOf((int) CarInfoManager.ID_INFO_KEY_FREE));
        this.mConfMap.put(177, Integer.valueOf((int) CarInfoManager.ID_INFO_GRADE_ASSIST));
        this.mConfMap.put(178, Integer.valueOf((int) CarInfoManager.ID_INFO_ONE_OR_TWO_STAGE_UNLOCK));
        this.mConfMap.put(Integer.valueOf((int) LocationProtocal.longitudeDIMax), Integer.valueOf((int) CarInfoManager.ID_INFO_AUTO_HIGH_BEAM_MENU));
        this.mConfMap.put(180, Integer.valueOf((int) CarInfoManager.ID_INFO_AIR_SUSP_SUMA_CONTROL));
        this.mConfMap.put(181, Integer.valueOf((int) CarInfoManager.ID_INFO_AIR_SUSP_AUTO_HEIGHT_SUMA));
        this.mConfMap.put(182, Integer.valueOf((int) CarInfoManager.ID_INFO_BTT_LITE));
        this.mConfMap.put(183, Integer.valueOf((int) CarInfoManager.ID_INFO_PASGER_AIRBAG_SETTINGS));
        this.mConfMap.put(184, Integer.valueOf((int) CarInfoManager.ID_INFO_MHEV_START_STOP_THRESHOLD));
        this.mConfMap.put(185, Integer.valueOf((int) CarInfoManager.ID_INFO_AIR_SUSP_CARGO_LOADING_SUMA));
        this.mConfMap.put(186, Integer.valueOf((int) CarInfoManager.ID_INFO_CLIMATE_AUTO_LEVELS));
        this.mConfMap.put(187, Integer.valueOf((int) CarInfoManager.ID_INFO_EV_RANGE_RING));
        this.mConfMap.put(188, Integer.valueOf((int) CarInfoManager.ID_INFO_KEYPAD_OR_PAAK));
        this.mConfMap.put(189, Integer.valueOf((int) CarInfoManager.ID_INFO_CHARGE_PORT_LOCK));
        this.mConfMap.put(190, Integer.valueOf((int) CarInfoManager.ID_INFO_WIFI_MSG));
    }
}
