package com.diacoipj.airdropreminder.Setting.ThreadManage;

import android.os.Handler;
import android.os.Looper;

import com.diacoipj.airdropreminder._Core.updateMyData;


public class ThreadManager extends Thread {

    com.diacoipj.airdropreminder._Core.updateMyData updateMyData ;
    TaskBackground taskBackground ;
    Handler handler = new Handler(Looper.getMainLooper());
    public ThreadManager (TaskBackground taskBackground) {
        this.taskBackground = taskBackground ;
        start();
    }

    public ThreadManager (TaskBackground taskBackground , updateMyData updateMyData) {
        this.taskBackground = taskBackground ;
        this.updateMyData = updateMyData ;
        start();
    }

    @Override
    public void run() {
        taskBackground.taskBackground();
        interrupt();
        if (updateMyData!=null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    updateMyData.update();
                    handler.removeCallbacks(this);
                }
            });
        }
    }
}
