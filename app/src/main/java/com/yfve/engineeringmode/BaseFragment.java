package com.yfve.engineeringmode;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.baidu.protect.A;
import com.yfve.tools.LU;

/* loaded from: classes1.dex */
public class BaseFragment extends Fragment {
    public final String tag = getClass().getSimpleName() + "_zyx";
    public MainActivity mMainActivity = null;

    @Override // android.app.Fragment
    public void onCreate(@Nullable Bundle savedInstanceState) {
        A.V(-1425997824, this, new Object[]{savedInstanceState});
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        LU.e(this.tag, "onResume()  ");
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        LU.e(this.tag, "onStop()  ");
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        LU.e(this.tag, "onDestroyView()  ");
    }
}
