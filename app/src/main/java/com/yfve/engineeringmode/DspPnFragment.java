package com.yfve.engineeringmode;

import android.car.CarInfoManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.yfve.adapter.ListKeyValueAdapter;
import com.yfve.tools.BeanTitleContent;
import com.yfve.tools.LU;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes1.dex */
public class DspPnFragment extends BaseFragment {
    private ListKeyValueAdapter mDspPnAdapter;
    private ListView mlvBdEntry;
    private String[] strsEntrys = new String[0];
    private List<BeanTitleContent> mListsAhuPn = null;
    private AdapterView.OnItemClickListener mlvOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.yfve.engineeringmode.DspPnFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        }
    };

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_listview_show, (ViewGroup) null);
        this.mlvBdEntry = (ListView) view.findViewById(R.id.lv_main_list);
        this.mlvBdEntry.setOnItemClickListener(this.mlvOnItemClickListener);
        initData();
        this.mDspPnAdapter = new ListKeyValueAdapter(this.mMainActivity, this.mListsAhuPn);
        this.mlvBdEntry.setAdapter((ListAdapter) this.mDspPnAdapter);
        return view;
    }

    private void initData() {
        this.strsEntrys = this.mMainActivity.getResources().getStringArray(R.array.lv_dsp_pn);
        this.mListsAhuPn = new ArrayList();
        for (int i = 0; i < this.strsEntrys.length; i++) {
            BeanTitleContent beanTitleContent = new BeanTitleContent(this.strsEntrys[i], "");
            this.mListsAhuPn.add(beanTitleContent);
        }
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_dsp_pn));
        onServiceConnected();
    }

    public void onServiceConnected() {
        try {
            byte[] numberDsp = this.mMainActivity.mCarInfoManager.getBytesProprety(CarInfoManager.ID_INFO_DSP_INNER_VERSION);
            BeanTitleContent beanTitleContent0 = (BeanTitleContent) this.mDspPnAdapter.getItem(0);
            beanTitleContent0.setMstrContent(new String(numberDsp));
            this.mDspPnAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            String str = this.tag;
            LU.e(str, "onServiceConnected()  Exception == " + e.toString());
        }
    }
}
