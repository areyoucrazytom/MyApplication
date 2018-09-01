package per.scpfoundation.busket.eventmanagers;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import per.scpfoundation.busket.Busket;
import per.scpfoundation.busket.EventManager;
import per.scpfoundation.busket.event.BaseEvent;

public class ObserveViewsTransceivers extends Transceivers{
    private TreeSet<Integer> mIDSet;
    public ObserveViewsTransceivers(){
        mIDSet = new TreeSet<Integer>();
    }
    public void addViewID(int id){
        mIDSet.add(id);
    }
    public void newClickEventOnView(View v){
        if (contains(v)){

        }
    }
    public boolean contains(View v) {
        return mIDSet.contains(v.getId());
    }
    public void setObservedViewIDList(List<Integer> list){
        mIDSet.clear();
        list.addAll(mIDSet);
    }
    public List<Integer> exportObserveViewIDList(){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        mIDSet.addAll(ret);
        return ret;
    }
    public static int getCompentID(String packageName, String className, String idName) {
        int id = 0;
        try {
            Class<?> cls = Class.forName(packageName + ".R$" + className);
            id = cls.getField(idName).getInt(cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
