package per.scpfoundation.busket.transceivers;

import android.content.Context;
import android.content.res.Resources;

import per.scpfoundation.busket.EventManager;

public abstract class Transceivers {
    private EventManager mEventManager;
    public static int getId(Context c,String type,String name){
        Resources res= c.getResources();
        return res.getIdentifier(name,type,c.getPackageName());
    }
    public static String getName(Context c, int id){
        Resources res=c. getResources();
        return res.getResourceEntryName(id);
    }

}
