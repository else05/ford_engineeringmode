package com.yfve.engineeringmode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/* loaded from: classes1.dex */
public class DisplayTestPatternFragment extends BaseFragment {
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.DisplayTestPatternFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (v.getId() == R.id.ll_back) {
                DisplayTestPatternFragment.this.mMainActivity.onBackPressed();
                DisplayTestPatternFragment.this.mMainActivity.setFullscreen(false);
            }
        }
    };
    private LinearLayout mllBack;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_display, (ViewGroup) null);
        this.mllBack = (LinearLayout) view.findViewById(R.id.ll_back);
        this.mllBack.setOnClickListener(this.mbtOnClickListener);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_sdtest));
        this.mMainActivity.setFullscreen(true);
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
        this.mMainActivity.setFullscreen(false);
    }
}
