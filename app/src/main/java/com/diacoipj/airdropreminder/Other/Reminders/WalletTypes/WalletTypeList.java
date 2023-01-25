package com.diacoipj.airdropreminder.Other.Reminders.WalletTypes;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diacoipj.airdropreminder.Other.Reminders.FragGetReminderInfo;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.Setting;

public class WalletTypeList extends RelativeLayout {

    FragGetReminderInfo fragReminder;

    public WalletTypeList(Context context,FragGetReminderInfo fragReminder,String Type) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.type_wallet, this, true);

        this.fragReminder =fragReminder;
        ((RecyclerView) findViewById(R.id.recy_pill)).setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        if(Type.contains("wallet"))
            fragReminder.ada = new WAdapter(fragReminder.models,fragReminder,this,Type);
        else fragReminder.ada = new WAdapter(fragReminder.modelChain,fragReminder,this,Type);
        ((RecyclerView)findViewById(R.id.recy_pill)).setAdapter(fragReminder.ada);

        findViewById(R.id.main).setOnClickListener(v -> {
            if(Type.contains("wallet")) {
                fragReminder.parent.findViewById(R.id.dialog_type).setVisibility(GONE);
                ((RelativeLayout) fragReminder.parent.findViewById(R.id.dialog_type)).removeAllViews();
            }
            else{
                fragReminder.parent.findViewById(R.id.dialog_Chaintype).setVisibility(GONE);
                ((RelativeLayout) fragReminder.parent.findViewById(R.id.dialog_Chaintype)).removeAllViews();
            }
            new Setting().HideKeyBoard();
        });


    }

}