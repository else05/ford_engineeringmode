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
import com.yfve.tools.PublicDefine;

/* loaded from: classes1.dex */
public class DspFragment extends BaseFragment {
    private ListView mlvDspEntry;
    private ListTextAdapter mListTextAdapter = null;
    private String[] mstrsEntrys = new String[0];
    public final String KEY_IS_SHOW_RETURN = "KeyIsShowReturn";
    public final String VALUE_SHOW_RETURN = "Show";
    private AdapterView.OnItemClickListener mlvOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.yfve.engineeringmode.DspFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    DspFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrDspPnFragmentName);
                    return;
                case 1:
                    if (5.0f < DspFragment.this.mMainActivity.miCurrentSpeed) {
                        Toast.makeText(DspFragment.this.mMainActivity, "车速大于5km/h，禁止进入", 0).show();
                        return;
                    }
                    Intent intent = new Intent(DspFragment.this.mMainActivity, SpeakerDiagnosisActivity.class);
                    intent.putExtra("KeyIsShowReturn", "Show");
                    DspFragment.this.startActivity(intent);
                    return;
                default:
                    return;
            }
        }
    };

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_listview_show, (ViewGroup) null);
        this.mlvDspEntry = (ListView) view.findViewById(R.id.lv_main_list);
        this.mlvDspEntry.setOnItemClickListener(this.mlvOnItemClickListener);
        this.mstrsEntrys = this.mMainActivity.getResources().getStringArray(R.array.lv_dsp_entrys);
        this.mListTextAdapter = new ListTextAdapter(this.mMainActivity, this.mstrsEntrys);
        this.mlvDspEntry.setAdapter((ListAdapter) this.mListTextAdapter);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_dsp));
    }
}
