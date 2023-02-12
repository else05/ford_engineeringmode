package android.car.app.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/* loaded from: classes2.dex */
public abstract class CarUiEntry {
    protected final Context mAppContext;
    protected final Context mUiLibContext;

    public abstract void closeDrawer();

    public abstract View getContentView();

    public abstract int getFragmentContainerId();

    public abstract CharSequence getSearchBoxText();

    public abstract void hideMenuButton();

    public abstract void hideTitle();

    public abstract void onPause();

    public abstract void onRestoreInstanceState(Bundle bundle);

    public abstract void onResume();

    public abstract void onSaveInstanceState(Bundle bundle);

    public abstract void onStart();

    public abstract void onStop();

    public abstract void openDrawer();

    public abstract void restoreMenuDrawable();

    public abstract void setAutoLightDarkMode();

    public abstract void setBackground(Bitmap bitmap);

    public abstract void setCarMenuCallbacks(CarMenuCallbacks carMenuCallbacks);

    public abstract void setDarkMode();

    public abstract void setLightMode();

    public abstract void setMenuButtonBitmap(Bitmap bitmap);

    public abstract void setMenuButtonColor(int i);

    public abstract void setScrimColor(int i);

    public abstract void setSearchBoxColors(int i, int i2, int i3, int i4);

    public abstract void setSearchBoxEditListener(SearchBoxEditListener searchBoxEditListener);

    public abstract void setSearchBoxEndView(View view);

    public abstract void setTitle(CharSequence charSequence);

    public abstract void showMenu(String str, String str2);

    public abstract void showSearchBox(View.OnClickListener onClickListener);

    public abstract void showTitle();

    public abstract void showToast(String str, long j);

    public abstract EditText startInput(String str, View.OnClickListener onClickListener);

    public abstract void stopInput();

    public CarUiEntry(Context uiLibContext, Context appContext) {
        this.mUiLibContext = uiLibContext.createConfigurationContext(appContext.getResources().getConfiguration());
        this.mAppContext = appContext;
    }
}
