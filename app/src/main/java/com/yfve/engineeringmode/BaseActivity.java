package com.yfve.engineeringmode;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.baidu.protect.A;
import com.yfve.tools.LU;
import com.yfve.tools.PublicDefine;
import java.util.HashMap;

/* loaded from: classes1.dex */
public class BaseActivity extends Activity {
    public final String tag = getClass().getSimpleName() + "zyx";
    public FragmentManager mFragmentManager = null;
    public HashMap<String, BaseFragment> mmpaFragments = null;

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        A.V(-1425997823, this, new Object[]{savedInstanceState});
    }

    public void switchToFragment(String strFragmentClassName) {
        String str = this.tag;
        LU.d(str, "switchToFragment()  strFragmentClassName==" + strFragmentClassName);
        if (strFragmentClassName == null || "".equals(strFragmentClassName)) {
            return;
        }
        BaseFragment fragment = this.mmpaFragments.get(strFragmentClassName);
        if (fragment == null) {
            fragment = createFragment(strFragmentClassName);
        }
        showFragment(strFragmentClassName, fragment);
    }

    public BaseFragment createFragment(String strFragmentClassName) {
        BaseFragment baseFragment = null;
        if (PublicDefine.mstrBezelFragmentName.equals(strFragmentClassName)) {
            baseFragment = new BezelFragment();
        } else if (PublicDefine.mstrAhuFragmentName.equals(strFragmentClassName)) {
            baseFragment = new AhuFragment();
        } else if (PublicDefine.mstrDspFragmentName.equals(strFragmentClassName)) {
            baseFragment = new DspFragment();
        } else if (PublicDefine.mstrSdmFragmentName.equals(strFragmentClassName)) {
            baseFragment = new SdmFragment();
        } else if (PublicDefine.mstrApimPnFragmentName.equals(strFragmentClassName)) {
            baseFragment = new AhuPnFragment();
        } else if (PublicDefine.mstrApimLiFragmentName.equals(strFragmentClassName)) {
            baseFragment = new AhuLocationIFragment();
        } else if (PublicDefine.mstrTouchscreenFragmentName.equals(strFragmentClassName)) {
            baseFragment = new TouchscreenFragment();
        } else if (PublicDefine.mstrDisplayTestPatternFragment.equals(strFragmentClassName)) {
            baseFragment = new DisplayTestPatternFragment();
        } else if (PublicDefine.mstrRgbPixelTextFragment.equals(strFragmentClassName)) {
            baseFragment = new RgbPixelTextFragment();
        } else if (PublicDefine.mstrDspPnFragmentName.equals(strFragmentClassName)) {
            baseFragment = new DspPnFragment();
        } else if (PublicDefine.mstrSdmPnFragmentName.equals(strFragmentClassName)) {
            baseFragment = new SdmPnFragment();
        } else if (PublicDefine.mstrGyroscopeFragmentName.equals(strFragmentClassName)) {
            baseFragment = new GyroscopeFragment();
        } else if (PublicDefine.mstrRadioTunerFragment.equals(strFragmentClassName)) {
            baseFragment = new RadioTunerFragment();
        } else if (PublicDefine.mstrRadioTunerPhase2Fragment.equals(strFragmentClassName)) {
            baseFragment = new RadioTunerPhase2Fragment();
        } else if (PublicDefine.mstrPSSFragmentName.equals(strFragmentClassName)) {
            baseFragment = new PhoneSignalStrengthsFragment();
        } else if (PublicDefine.mstrSensorFragmentName.equals(strFragmentClassName)) {
            baseFragment = new SensorFragment();
        } else if (PublicDefine.mstrMainFragmentName.equals(strFragmentClassName)) {
            baseFragment = new MainFragment();
        } else if (PublicDefine.mstrWifiDebugFragmentName.equals(strFragmentClassName)) {
            baseFragment = new WifiDebugFragment();
        } else if (PublicDefine.mstrSoftwareVersionsFragmentName.equals(strFragmentClassName)) {
            baseFragment = new SoftwareVersionsFragment();
        } else if (PublicDefine.ConfigFragmentName.equals(strFragmentClassName)) {
            baseFragment = new ConfigFragment();
        } else if (PublicDefine.RadioSignalFragmentName.equals(strFragmentClassName)) {
            baseFragment = new RadioSignalFragment();
        } else if (PublicDefine.mstrBtTestFragmentName.equals(strFragmentClassName)) {
            baseFragment = new BtTestFragment();
        } else if (PublicDefine.WifiSettingsFragmentName.equals(strFragmentClassName)) {
            baseFragment = new WifiSettingsFragment();
        } else if (PublicDefine.PhotoTestFragmentName.equals(strFragmentClassName)) {
            baseFragment = new PhotoTestFragment();
        } else if (PublicDefine.ConfigP2FragmentName.equals(strFragmentClassName)) {
            baseFragment = new ConfigP2Fragment();
        } else if (PublicDefine.ConfigP3FragmentName.equals(strFragmentClassName)) {
            baseFragment = new ConfigP3Fragment();
        }
        this.mmpaFragments.put(strFragmentClassName, baseFragment);
        return baseFragment;
    }

    public void showFragment(String strFragmentTag, Fragment fragment) {
        String str = this.tag;
        LU.d(str, "switchToFragment()  strFragmentTag==" + strFragmentTag + "  fragment==" + fragment);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = this.mFragmentManager.beginTransaction();
            fragmentTransaction.setTransition(0);
            fragmentTransaction.replace(R.id.rl_main_window, fragment, strFragmentTag);
            fragmentTransaction.addToBackStack(strFragmentTag);
            fragmentTransaction.commit();
        }
    }

    public void onPssFragmentUpdate() {
        onBackPressed();
        switchToFragment(PublicDefine.mstrPSSFragmentName);
    }
}
