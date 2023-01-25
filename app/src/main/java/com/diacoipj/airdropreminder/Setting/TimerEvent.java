package com.diacoipj.airdropreminder.Setting;

public interface TimerEvent {

    void onTick(String time);
    void onFinish();
}
