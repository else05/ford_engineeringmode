package com.yfve.engineeringmode;

import android.hardware.Rl78Manager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.yfve.adapter.ListKeyValueAdapter;
import com.yfve.tools.BeanTitleContent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes1.dex */
public class SdmPnFragment extends BaseFragment {
    private ListKeyValueAdapter mDspPnAdapter;
    private ListView mlvBdEntry;
    private Rl78Manager mrl78;
    private String[] strsEntrys = new String[0];
    private List<BeanTitleContent> mListsSdmPn = null;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_listview_show, (ViewGroup) null);
        this.mlvBdEntry = (ListView) view.findViewById(R.id.lv_main_list);
        initData();
        this.mDspPnAdapter = new ListKeyValueAdapter(this.mMainActivity, this.mListsSdmPn);
        this.mlvBdEntry.setAdapter((ListAdapter) this.mDspPnAdapter);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_sdm_pn));
        try {
            this.mrl78 = (Rl78Manager) this.mMainActivity.getSystemService("Rl78Service");
            if (this.mrl78 != null) {
                this.mrl78.Rl78_connect();
                byte[] bArr = new byte[5];
                byte[] version = this.mrl78.getVer();
                BeanTitleContent beanTitleContent0 = (BeanTitleContent) this.mDspPnAdapter.getItem(0);
                beanTitleContent0.setMstrContent(new String(version));
                this.mrl78.Rl78_disconnect();
                this.mDspPnAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
        }
    }

    private void initData() {
        this.strsEntrys = this.mMainActivity.getResources().getStringArray(R.array.lv_sdm_pn);
        this.mListsSdmPn = new ArrayList();
        for (int i = 0; i < this.strsEntrys.length; i++) {
            BeanTitleContent beanTitleContent = new BeanTitleContent(this.strsEntrys[i], "");
            this.mListsSdmPn.add(beanTitleContent);
        }
    }
}
