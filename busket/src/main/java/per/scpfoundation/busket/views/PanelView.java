package per.scpfoundation.busket.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import per.scpfoundation.busket.event.ClickEvent;

public class PanelView extends FrameLayout {
    private static final int CLICK_DELAY = 500;

    private long mDownTimestamp = -1l;
    private int mDownViewID = -1;
    private View mActualView;
    private List<View> mTouchViewList;
    private View mLastTouchDownView;
    private View mLastTouchUpView;
    public PanelView(@NonNull Context context,View actualView) {
        super(context);
        mActualView = actualView;
        mTouchViewList = new ArrayList<>();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownTimestamp = android.os.SystemClock.elapsedRealtime();
                findViewPileByCoodinate(ev.getX(),ev.getY());
                mLastTouchDownView = mTouchViewList.get(mTouchViewList.size() - 1);
                break;
            case MotionEvent.ACTION_UP:
                findViewPileByCoodinate(ev.getX(),ev.getY());
                mLastTouchUpView = mTouchViewList.get(mTouchViewList.size() - 1);
                long uptime = android.os.SystemClock.elapsedRealtime() - mDownTimestamp;
                if (mLastTouchDownView == mLastTouchUpView && mLastTouchUpView.isClickable()){
                    if (uptime <= CLICK_DELAY) {
                        ClickEvent event = new ClickEvent();
                        event.clickViewID = mLastTouchUpView.getId();
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    private synchronized void findViewPileByCoodinate(float x,float y) {
        mTouchViewList.clear();
        Rect visibleRect= new Rect();
        int intx = (int)x;
        int inty = (int)y;
        if (mActualView == null) {
            mTouchViewList.add(this);
            return;
        }
        mTouchViewList.add(mActualView);
        View currentLevel = mActualView;
        while (currentLevel instanceof ViewGroup && ((ViewGroup) currentLevel).getChildCount()> 0) {
            ViewGroup currentLevelGroup = (ViewGroup)currentLevel;
            int count = currentLevelGroup.getChildCount();
            for (int i = 0;i < count;i++) {
                View child = currentLevelGroup.getChildAt(i);
                if (child.getGlobalVisibleRect(visibleRect) && visibleRect.contains(intx,inty)) {
                    mTouchViewList.add(child);
                    if (child instanceof AdapterView) {
                        
                    }
                    currentLevel = child;
                }
            }
        }
    }
    public static void hookTouchPanel(Activity activity){
        View remt = activity.getWindow().getDecorView();
        if (!(remt instanceof ViewGroup)) {
            throw new IllegalStateException("Activiry root views status error");
        }
        ViewGroup rootView = (ViewGroup)remt;
        View actualView = rootView.getChildAt(0);
        FrameLayout hookViewParent = new FrameLayout(activity);
        PanelView panelView = new PanelView(activity,actualView);
        rootView.removeAllViews();
        rootView.addView(hookViewParent);
        hookViewParent.addView(actualView);
        hookViewParent.addView(panelView);
    }

}
