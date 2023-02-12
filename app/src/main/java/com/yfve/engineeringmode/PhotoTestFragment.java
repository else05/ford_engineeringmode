package com.yfve.engineeringmode;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* loaded from: classes1.dex */
public class PhotoTestFragment extends BaseFragment {
    PagerAdapter mPagerAdapter = new PagerAdapter() { // from class: com.yfve.engineeringmode.PhotoTestFragment.2
        public int getCount() {
            return PhotoTestFragment.this.pageview.size();
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) PhotoTestFragment.this.pageview.get(arg1));
        }

        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView((View) PhotoTestFragment.this.pageview.get(arg1));
            return PhotoTestFragment.this.pageview.get(arg1);
        }
    };
    private ViewPager mviewPager;
    private ArrayList<View> pageview;

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_photo_test, (ViewGroup) null);
        this.mviewPager = view.findViewById(R.id.viewPager);
        view.findViewById(R.id.tv_back_photo).setOnClickListener(new View.OnClickListener() { // from class: com.yfve.engineeringmode.PhotoTestFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                PhotoTestFragment.this.mMainActivity.onBackPressed();
            }
        });
        View imageView1 = new View(this.mMainActivity);
        imageView1.setBackground(this.mMainActivity.getResources().getDrawable(R.drawable.p1));
        View imageView2 = new View(this.mMainActivity);
        imageView2.setBackground(this.mMainActivity.getResources().getDrawable(R.drawable.p2));
        this.pageview = new ArrayList<>();
        this.pageview.add(imageView1);
        this.pageview.add(imageView2);
        this.mviewPager.setAdapter(this.mPagerAdapter);
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
