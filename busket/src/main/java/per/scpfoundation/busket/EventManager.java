package per.scpfoundation.busket;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import per.scpfoundation.busket.event.BaseEvent;

public class EventManager {
    private static final String KEY_SERIAL_NUMBER = "per.ccns.erialnumber";
    private static final int EVENT_LINEUP_PAGE_SIZE = 50;
    private static final int EVENT_LINEUP_AREA = 50;

    private static long sGlobalSerialNumber = 0;
    private static EventManager sInstance;

    private List<BaseEvent> mUserEvents;
    private ExecutorService mWorkPool;
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private EventManager(Context c){
        mWorkPool = Executors.newSingleThreadExecutor();
        mContext = c;
        mSharedPreferences =  c.getSharedPreferences(getClass().getName(),Context.MODE_PRIVATE);
        sGlobalSerialNumber = mSharedPreferences.getLong(KEY_SERIAL_NUMBER,0);
        mUserEvents = new ArrayList<>();
    }
    public synchronized  static EventManager getInstance(Context c){
        if (sInstance ==null) {
            sInstance = new EventManager(c);
        }
        return sInstance;
    }
    public void addEvent(final BaseEvent event){
        Thread addThread = new Thread(){
            @Override
            public void run() {
                super.run();
                event.serialNumber = sGlobalSerialNumber++;
                mUserEvents.add(event);
                mSharedPreferences.edit().putLong(KEY_SERIAL_NUMBER,sGlobalSerialNumber).apply();
                if (mUserEvents.size() >= EVENT_LINEUP_AREA) {
                    popLineup();
                }
            }
        };
        mWorkPool.execute(addThread);
    }
    private void popLineup(){
        Thread addThread = new Thread(){
            @Override
            public void run() {
                super.run();

            }
        };
        mWorkPool.execute(addThread);
    }
    public void clear(){
        Thread addThread = new Thread(){
            @Override
            public void run() {
                super.run();
                mUserEvents.clear();
            }
        };
        mWorkPool.execute(addThread);
    }
    public void revive(List<BaseEvent> list){

    }
}