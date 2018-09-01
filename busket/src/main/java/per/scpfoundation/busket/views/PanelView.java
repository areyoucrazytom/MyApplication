package per.scpfoundation.busket.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class PanelView extends FrameLayout {
    private static final int CLICK_DELAY = 500;

    private long mDownTimestamp = -1l;
    private int mDownViewID = -1;
    public PanelView(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownTimestamp = android.os.SystemClock.elapsedRealtime();
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    public static void hookTouchPanel(Activity activity){
        View remt = activity.getWindow().getDecorView();
        if (!(remt instanceof ViewGroup)) {
            throw new IllegalStateException("Activiry root views status error");
        }
        ViewGroup rootView = (ViewGroup)remt;
        View actualView = rootView.getChildAt(0);
        FrameLayout hookViewParent = new FrameLayout(activity);
        PanelView panelView = new PanelView(activity);
        rootView.removeAllViews();
        rootView.addView(hookViewParent);
        hookViewParent.addView(actualView);
        hookViewParent.addView(panelView);
    }
}
