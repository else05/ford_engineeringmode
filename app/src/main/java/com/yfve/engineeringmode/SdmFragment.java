package com.yfve.engineeringmode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.yfve.adapter.ListTextAdapter;
import com.yfve.tools.PublicDefine;

/* loaded from: classes1.dex */
public class SdmFragment extends BaseFragment {
    private ListView mlvSdmEntry;
    private ListTextAdapter mListTextAdapter = null;
    private String[] mstrsEntrys = new String[0];
    private AdapterView.OnItemClickListener mlvOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.yfve.engineeringmode.SdmFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                SdmFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrSdmPnFragmentName);
            }
        }
    };

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_listview_show, (ViewGroup) null);
        this.mlvSdmEntry = (ListView) view.findViewById(R.id.lv_main_list);
        this.mlvSdmEntry.setOnItemClickListener(this.mlvOnItemClickListener);
        this.mstrsEntrys = this.mMainActivity.getResources().getStringArray(R.array.lv_sdm_entrys);
        this.mListTextAdapter = new ListTextAdapter(this.mMainActivity, this.mstrsEntrys);
        this.mlvSdmEntry.setAdapter((ListAdapter) this.mListTextAdapter);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_sdm));
    }
}
