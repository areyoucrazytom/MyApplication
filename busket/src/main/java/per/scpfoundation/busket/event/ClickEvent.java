package per.scpfoundation.busket.event;

import android.view.View;
import android.widget.TextView;

import per.scpfoundation.busket.transceivers.Transceivers;

public class ClickEvent extends BaseEvent{
    private static final String DEFAULT_NAME_NO_NAME = "undefined";

    public int clickViewID = -1;
    public String idName = DEFAULT_NAME_NO_NAME;
    public String pageName = DEFAULT_NAME_NO_NAME;
    public String viewInfo = "";

    public static ClickEvent fromView(View v) {
        ClickEvent ret = new ClickEvent();
        ret.clickViewID = v.getId();
        ret.idName = Transceivers.getName(v.getContext(),v.getId());
        ret.pageName = v.getContext().getClass().getName();
        ret.viewInfo = getViewInfoString(v);
        return ret;
    }
    public static String getViewInfoString(View v){
//        StringBuffer ret = new StringBuffer();
        if (v instanceof TextView) {
            TextView vtv = (TextView) v;
            return vtv.getText().toString();
        }
        return v.toString();
    }
}
