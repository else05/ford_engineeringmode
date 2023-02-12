package com.yfve.engineeringmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.yfve.adapter.ListTextAdapter;
import com.yfve.tools.LU;
import com.yfve.tools.PublicDefine;
import java.lang.reflect.Method;

/* loaded from: classes1.dex */
public class AhuFragment extends BaseFragment {
    private ListView mlvAhuEntry;
    private ListTextAdapter mListTextAdapter = null;
    private String[] mstrsEntrys = new String[0];
    public final String KEY_IS_SHOW_RETURN = "KeyIsShowReturn";
    public final String VALUE_SHOW_RETURN = "Show";
    private AdapterView.OnItemClickListener mlvOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.yfve.engineeringmode.AhuFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrApimPnFragmentName);
                    return;
                case 1:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.RadioSignalFragmentName);
                    return;
                case 2:
                    if (5.0f < AhuFragment.this.mMainActivity.miCurrentSpeed) {
                        Toast.makeText(AhuFragment.this.mMainActivity, "车速大于5km/h，禁止进入", 0).show();
                        return;
                    }
                    Intent intent = new Intent(AhuFragment.this.mMainActivity, SpeakerDiagnosisActivity.class);
                    intent.putExtra("KeyIsShowReturn", "Show");
                    AhuFragment.this.startActivity(intent);
                    return;
                case 3:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrSoftwareVersionsFragmentName);
                    return;
                case 4:
                    String str = AhuFragment.this.tag;
                    LU.w(str, "onItemClick(config) miCarType== " + AhuFragment.this.mMainActivity.miCarType + "  mMainActivity.miCarModeYear==" + AhuFragment.this.mMainActivity.miCarModeYear);
                    switch (AhuFragment.this.mMainActivity.miCarType) {
                        case 0:
                        case 2:
                        case 3:
                            AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.ConfigFragmentName);
                            return;
                        case 1:
                            if (1 == AhuFragment.this.mMainActivity.miCarModeYear) {
                                AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.ConfigP3FragmentName);
                                return;
                            } else {
                                AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.ConfigFragmentName);
                                return;
                            }
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                            AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.ConfigP2FragmentName);
                            return;
                        default:
                            AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.ConfigFragmentName);
                            return;
                    }
                case 5:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrApimLiFragmentName);
                    return;
                case 6:
                    try {
                        AhuFragment.this.set("sys.service.tp_calibrate", "1");
                        Toast.makeText(AhuFragment.this.mMainActivity, "Succeed", 0).show();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                case 7:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrTouchscreenFragmentName);
                    return;
                case 8:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrDisplayTestPatternFragment);
                    return;
                case 9:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrRgbPixelTextFragment);
                    return;
                case 10:
                    switch (AhuFragment.this.mMainActivity.miCarType) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrRadioTunerFragment);
                            return;
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                            AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrRadioTunerPhase2Fragment);
                            return;
                        default:
                            AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrRadioTunerFragment);
                            return;
                    }
                case 11:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrPSSFragmentName);
                    return;
                case 12:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrSensorFragmentName);
                    return;
                case 13:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrGyroscopeFragmentName);
                    return;
                case 14:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrBtTestFragmentName);
                    return;
                case 15:
                    AhuFragment.this.mMainActivity.switchToFragment(PublicDefine.WifiSettingsFragmentName);
                    return;
                default:
                    return;
            }
        }
    };
    private Method setMethod = null;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_listview_show, (ViewGroup) null);
        this.mlvAhuEntry = (ListView) view.findViewById(R.id.lv_main_list);
        this.mlvAhuEntry.setOnItemClickListener(this.mlvOnItemClickListener);
        this.mstrsEntrys = this.mMainActivity.getResources().getStringArray(R.array.lv_ahu_entrys);
        this.mListTextAdapter = new ListTextAdapter(this.mMainActivity, this.mstrsEntrys);
        this.mlvAhuEntry.setAdapter((ListAdapter) this.mListTextAdapter);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_ahu));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void set(String key, String def) {
        try {
            if (this.setMethod == null) {
                this.setMethod = Class.forName("android.os.SystemProperties").getMethod("set", String.class, String.class);
            }
            this.setMethod.invoke(null, key, def);
        } catch (Exception e) {
            String str = this.tag;
            LU.e(str, "Platform error: " + e.toString());
        }
    }
}
