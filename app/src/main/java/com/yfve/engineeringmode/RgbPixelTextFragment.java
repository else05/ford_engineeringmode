package com.yfve.engineeringmode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/* loaded from: classes1.dex */
public class RgbPixelTextFragment extends BaseFragment {
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.RgbPixelTextFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            int id = v.getId();
            if (id != R.id.bt_rgb_show) {
                switch (id) {
                    case R.id.iv_rgb_center /* 2131230811 */:
                        RgbPixelTextFragment.this.mbtRgbShow.setBackgroundColor(RgbPixelTextFragment.this.mMainActivity.getResources().getColor(R.color.color_red_exit));
                        RgbPixelTextFragment.this.mbtRgbShow.setVisibility(0);
                        RgbPixelTextFragment.this.mMainActivity.setFullscreen(true);
                        return;
                    case R.id.iv_rgb_center_l /* 2131230812 */:
                        RgbPixelTextFragment.this.mbtRgbShow.setBackgroundColor(RgbPixelTextFragment.this.mMainActivity.getResources().getColor(R.color.color_black));
                        RgbPixelTextFragment.this.mbtRgbShow.setVisibility(0);
                        RgbPixelTextFragment.this.mMainActivity.setFullscreen(true);
                        return;
                    case R.id.iv_rgb_center_ll /* 2131230813 */:
                        RgbPixelTextFragment.this.mbtRgbShow.setBackgroundColor(RgbPixelTextFragment.this.mMainActivity.getResources().getColor(R.color.color_white));
                        RgbPixelTextFragment.this.mbtRgbShow.setVisibility(0);
                        RgbPixelTextFragment.this.mMainActivity.setFullscreen(true);
                        return;
                    case R.id.iv_rgb_center_r /* 2131230814 */:
                        RgbPixelTextFragment.this.mbtRgbShow.setBackgroundColor(RgbPixelTextFragment.this.mMainActivity.getResources().getColor(R.color.display_color2));
                        RgbPixelTextFragment.this.mbtRgbShow.setVisibility(0);
                        RgbPixelTextFragment.this.mMainActivity.setFullscreen(true);
                        return;
                    case R.id.iv_rgb_center_rr /* 2131230815 */:
                        RgbPixelTextFragment.this.mbtRgbShow.setBackgroundColor(RgbPixelTextFragment.this.mMainActivity.getResources().getColor(R.color.display_color3));
                        RgbPixelTextFragment.this.mbtRgbShow.setVisibility(0);
                        RgbPixelTextFragment.this.mMainActivity.setFullscreen(true);
                        return;
                    case R.id.iv_rgb_gray /* 2131230816 */:
                        RgbPixelTextFragment.this.mbtRgbShow.setBackgroundColor(RgbPixelTextFragment.this.mMainActivity.getResources().getColor(R.color.color_gray));
                        RgbPixelTextFragment.this.mbtRgbShow.setVisibility(0);
                        RgbPixelTextFragment.this.mMainActivity.setFullscreen(true);
                        return;
                    case R.id.iv_rgb_grid /* 2131230817 */:
                        RgbPixelTextFragment.this.mbtRgbShow.setBackground(RgbPixelTextFragment.this.mMainActivity.getResources().getDrawable(R.drawable.grid));
                        RgbPixelTextFragment.this.mbtRgbShow.setVisibility(0);
                        RgbPixelTextFragment.this.mMainActivity.setFullscreen(true);
                        return;
                    default:
                        return;
                }
            }
            RgbPixelTextFragment.this.mbtRgbShow.setVisibility(8);
            RgbPixelTextFragment.this.mMainActivity.setFullscreen(false);
        }
    };
    private Button mbtRgbShow;
    private ImageView mivCenter;
    private ImageView mivCenterL;
    private ImageView mivCenterLL;
    private ImageView mivCenterR;
    private ImageView mivCenterRR;
    private ImageView mivGray;
    private ImageView mivGrid;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_rgb_pixel, (ViewGroup) null);
        this.mbtRgbShow = (Button) view.findViewById(R.id.bt_rgb_show);
        this.mivCenter = (ImageView) view.findViewById(R.id.iv_rgb_center);
        this.mivCenterL = (ImageView) view.findViewById(R.id.iv_rgb_center_l);
        this.mivCenterLL = (ImageView) view.findViewById(R.id.iv_rgb_center_ll);
        this.mivCenterR = (ImageView) view.findViewById(R.id.iv_rgb_center_r);
        this.mivCenterRR = (ImageView) view.findViewById(R.id.iv_rgb_center_rr);
        this.mivGrid = (ImageView) view.findViewById(R.id.iv_rgb_grid);
        this.mivGray = (ImageView) view.findViewById(R.id.iv_rgb_gray);
        this.mbtRgbShow.setOnClickListener(this.mbtOnClickListener);
        this.mivCenter.setOnClickListener(this.mbtOnClickListener);
        this.mivCenterL.setOnClickListener(this.mbtOnClickListener);
        this.mivCenterLL.setOnClickListener(this.mbtOnClickListener);
        this.mivCenterR.setOnClickListener(this.mbtOnClickListener);
        this.mivCenterRR.setOnClickListener(this.mbtOnClickListener);
        this.mivGrid.setOnClickListener(this.mbtOnClickListener);
        this.mivGray.setOnClickListener(this.mbtOnClickListener);
        this.mbtRgbShow.setVisibility(8);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_apim_rgb));
    }
}
