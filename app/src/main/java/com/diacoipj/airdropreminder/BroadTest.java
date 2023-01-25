package com.diacoipj.airdropreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadTest extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && !intent.getAction().equals("Instagram") && !intent.getAction().equals("TRACKING")) {
            Intent intent1 = new Intent(context, MainActivity.class);
            String action = "";
            if(intent.getAction().equals("KEYSHARED"))
                action = "KEYSHARED";
            else if(intent.getAction().equals("Water") )
                action = "Water";
            else if (intent.getAction().equals("liomAlarm"))
                action = "liomAlarm" ;
            else
                action = "notif";
            intent1.putExtra("key",action);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent1);
        }
        else if (intent.getAction() != null && intent.getAction().equals("TRACKING")) {
            String action = "TRACKING";
            if (MainActivity.getGlobal() == null ) {
                Intent intent1 = new Intent(context, MainActivity.class);
                intent1.putExtra("key",action);
                intent1.putExtra("FRAG" , intent.getStringExtra("FRAG"));
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent1);
            } else {
                MainActivity.getGlobal().HideMessageBox();
            }
        }

    }


}
