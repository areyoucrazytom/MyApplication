package per.scpfoundation.busket;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import java.util.List;

import per.scpfoundation.busket.event.BaseEvent;
import per.scpfoundation.busket.views.PanelView;

public class Busket {
//    static {
//        System.loadLibrary("native-lib");
//    }
    private static Busket sInstance;

    private Context mContext;
    private Settings mSettings;
    private Busket(Context c){
        mContext = c;
        mSettings = new Settings(null);
    }
    public synchronized static Busket initialize(Context capp){
        if (sInstance == null) {
            sInstance = new Busket(capp.getApplicationContext());
        }
        return sInstance;
    }
    public synchronized static Busket get(){
        return sInstance;
    }
    public void injectActivity(Activity activity){
        if (mSettings.isListenClick() || mSettings.isListenTouch()) {
            PanelView.hookTouchPanel(activity);
        }
    }
    public void shutdown(){

    }
    public interface OnEventLineUpListener{
        public void onLineUp(List<BaseEvent> eventList);
    }
    public interface OnExportObservedViewsIdList{
        public void onExcept(List<Integer> idlist);
    }
    public static class PopLineupProxy{

    }
}
