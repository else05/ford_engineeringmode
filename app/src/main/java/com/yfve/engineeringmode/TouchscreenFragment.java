package com.yfve.engineeringmode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yfve.tools.TouchView;

/* loaded from: classes1.dex */
public class TouchscreenFragment extends BaseFragment {
    private TouchView mTouchView;
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.TouchscreenFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.bt_ts_cancel) {
                TouchscreenFragment.this.mMainActivity.onBackPressed();
                TouchscreenFragment.this.mMainActivity.setFullscreen(false);
            } else if (id == R.id.rl_ts_hint) {
                TouchscreenFragment.this.mrlHint.setVisibility(8);
                TouchscreenFragment.this.mTouchView.setVisibility(0);
            }
        }
    };
    private TextView mbtReturn;
    private RelativeLayout mrlHint;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_touchscreen, (ViewGroup) null);
        this.mbtReturn = (TextView) view.findViewById(R.id.bt_ts_cancel);
        this.mrlHint = (RelativeLayout) view.findViewById(R.id.rl_ts_hint);
        this.mTouchView = (TouchView) view.findViewById(R.id.touchView);
        this.mbtReturn.setOnClickListener(this.mbtOnClickListener);
        this.mrlHint.setOnClickListener(this.mbtOnClickListener);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setFullscreen(true);
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
        this.mMainActivity.setFullscreen(false);
    }
}
