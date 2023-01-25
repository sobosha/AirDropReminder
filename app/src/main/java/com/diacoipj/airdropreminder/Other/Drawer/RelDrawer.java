package com.diacoipj.airdropreminder.Other.Drawer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diacoipj.airdropreminder.Dialog.RelMessageBox;
import com.diacoipj.airdropreminder.Main.FragBuyPermium;
import com.diacoipj.airdropreminder.MainActivity;

import com.diacoipj.airdropreminder.Other.Privacy.FragPrivacy;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder.Setting.mLocalData;
import com.diacoipj.airdropreminder.Start.FragSplash;

import ir.metrix.Metrix;

public class RelDrawer extends RelativeLayout {
    public RelDrawer(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.drawer, this, true);

        setClicks();
        setStyle();

    }

    public void setClicks() {



        findViewById(R.id.btnLogin).setOnClickListener(view -> {
            mAnimation.PressClick(view);
            MainActivity.getGlobal().hideDrawer();
                //todo check values that should reset
                MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext()).ButtonWithListeners(true, "Exit","Are you sure to exit account?", "No", "Exit", viewe -> {
                    mAnimation.PressClick(view);
                    if (MainActivity.getGlobal().isMainDialogShow)
                        MainActivity.getGlobal().hideMainDialogs();
                    MainActivity.getGlobal().HideMessageBox();
                    MainActivity.getGlobal().hideDrawer();
                }, viewe -> {
                    if (MainActivity.getGlobal().isMainDialogShow)
                        MainActivity.getGlobal().hideMainDialogs();
                    mAnimation.PressClick(view);
                    MainActivity.getGlobal().HideMessageBox();
                    mLocalData.SetToken(getContext() , "");
                    mLocalData.setEmail(getContext() , "");
                    mLocalData.setName(getContext() ,"");
                    MainActivity.getGlobal().FinishFragStartFrag(new FragSplash());

                }, "#ffb31a", R.drawable.yellow_alert).setBackOKBtn(R.drawable.shape_btn_cancel).setBackCancelBtn(R.drawable.shape_redbtn));


        });

        new Setting().setTypeFace(findViewById(R.id.txtTop));


        findViewById(R.id.relBuyPermium).setOnClickListener(v -> {
            mAnimation.PressClick(v);
            MainActivity.getGlobal().HideMessageBox();
            MainActivity.getGlobal().hideDrawer();
            MainActivity.getGlobal().FinishFragStartFrag(new FragBuyPermium());
        });
        findViewById(R.id.relPrivacy).setOnClickListener(v -> {
            mAnimation.PressClick(v);
            MainActivity.getGlobal().HideMessageBox();
            MainActivity.getGlobal().hideDrawer();
            MainActivity.getGlobal().FinishFragStartFrag(new FragPrivacy());
        });



    }

    boolean isLogin = false;

    public void setStyle() {
        Metrix.newEvent("fbygs");

        if (mLocalData.getEmail(getContext()).equals("")) {
            isLogin = false;
            findViewById(R.id.btnLogin).setBackgroundColor(Color.parseColor("#17bd79"));
            ((TextView) findViewById(R.id.btnLogin)).setText(MainActivity.getGlobal().getResources().getString(R.string.submitInformation) + "/" + MainActivity.getGlobal().getResources().getString(R.string.LogIn2));
        } else {
            isLogin = true;
            findViewById(R.id.btnLogin).setBackgroundColor(Color.parseColor("#fe0856"));
            ((TextView) findViewById(R.id.btnLogin)).setText(MainActivity.getGlobal().getResources().getString(R.string.Logout));
        }


    }




}