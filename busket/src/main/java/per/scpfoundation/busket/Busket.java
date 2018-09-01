package per.scpfoundation.busket;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import per.scpfoundation.busket.exception.ActivityStatusException;
import per.scpfoundation.busket.views.PanelView;

public class Busket {
    private static Busket sInstance;

    private Context mContext;
    private Settings mSettings;
    private Busket(Context c){
        mContext = c;
    }
    public synchronized static Busket initialize(Application capp){
        if (sInstance == null) {
            sInstance = new Busket(capp);
        }
        return sInstance;
    }

    private void hookTouchPanel(Activity activity){
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
