package per.scpfoundation.busket.transceivers;

import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class ObserveViewsTransceivers extends Transceivers{
    private TreeSet<Integer> mIDSet;
    private HashSet<String> mIDNameSet;
    public ObserveViewsTransceivers(){
        mIDSet = new TreeSet<Integer>();
        mIDNameSet = new HashSet<>();
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

}
