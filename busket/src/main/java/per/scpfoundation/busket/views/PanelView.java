package per.scpfoundation.busket.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class PanelView extends FrameLayout {
    public PanelView(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();

        return super.dispatchTouchEvent(ev);
    }
}
