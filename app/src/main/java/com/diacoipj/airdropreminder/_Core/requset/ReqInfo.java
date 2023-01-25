package com.diacoipj.airdropreminder._Core.requset;

import com.diacoipj.airdropreminder.MainActivity;

import co.ronash.pushe.Pushe;
import com.google.firebase.iid.FirebaseInstanceId;

public class ReqInfo {
    private String pushe;
    private String fcm;

    public ReqInfo() {
        this.pushe = Pushe.getPusheId(MainActivity.getGlobal());
        this.fcm = FirebaseInstanceId.getInstance().getToken();


    }

}
