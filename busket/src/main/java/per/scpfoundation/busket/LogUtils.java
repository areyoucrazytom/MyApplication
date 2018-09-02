package per.scpfoundation.busket;

import android.util.Log;

public class LogUtils {
    private static boolean sDebug = true;
    public static boolean isDebug() {
        return sDebug;
    }
    public static void LogD(String log) {
        if (sDebug) {
            Log.d("Busket-log",log);
        }
    }
}
