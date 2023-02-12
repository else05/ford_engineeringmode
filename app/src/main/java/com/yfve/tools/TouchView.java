package com.yfve.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes1.dex */
public class TouchView extends View {
    public float currentx;
    public float currenty;
    private Path mpath;
    private Context mycontext;
    public Paint p;
    public float prex;
    public float prey;

    public TouchView(Context context) {
        super(context);
        this.currentx = 40.0f;
        this.currenty = 50.0f;
        this.mpath = new Path();
    }

    public TouchView(Context context, AttributeSet set) {
        super(context, set);
        this.currentx = 40.0f;
        this.currenty = 50.0f;
        this.mpath = new Path();
        this.mycontext = context;
        this.p = new Paint();
        this.p.setColor(-1);
        this.p.setStyle(Paint.Style.STROKE);
        this.p.setStrokeCap(Paint.Cap.ROUND);
        this.p.setStrokeWidth(40.0f);
        this.p.setAntiAlias(true);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint m = new Paint();
        m.setColor(-16776961);
        canvas.drawPath(this.mpath, this.p);
        canvas.drawCircle(this.currentx, this.currenty, 40.0f, m);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        this.currentx = event.getX();
        this.currenty = event.getY();
        int action = event.getAction();
        if (action == 0) {
            this.mpath.moveTo(this.currentx, this.currenty);
            this.prex = this.currentx;
            this.prey = this.currenty;
            this.mpath.quadTo(this.prex, this.prey, this.currentx, this.currenty);
        } else if (action == 2) {
            this.mpath.quadTo(this.prex, this.prey, this.currentx, this.currenty);
            this.prex = this.currentx;
            this.prey = this.currenty;
        }
        invalidate();
        return true;
    }
}
