package com.diacoipj.airdropreminder.Setting.Admob;

public interface adListiner {
    void onAdLoaded();
    void onAdFailedToLoad();
    void onAdOpened();
    void onAdClicked();
    void onAdClosed();
}
