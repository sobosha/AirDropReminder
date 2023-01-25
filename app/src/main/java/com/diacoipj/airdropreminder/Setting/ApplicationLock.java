package com.diacoipj.airdropreminder.Setting;

import android.os.Handler;

public class ApplicationLock {

    private static final String TAG = ApplicationLock.class.getSimpleName();
    private static final int LOCK_TIME = 180000; //lock after a second
    public static boolean lock = false; //default is locked
    private static Handler handler = new Handler();
    private static Runnable runnable = () -> {
        lock = true;
    };

    public static boolean activityStarted() {
        handler.removeCallbacks(runnable);
        if (lock) {
            return true;
        } else {
            return false;
        }
    }

    public static void activityStopped() {
        handler.postDelayed(runnable, LOCK_TIME);

    }
}