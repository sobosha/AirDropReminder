package com.diacoipj.airdropreminder.Other.Reminders.General;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.util.List;

public class RelGeneralReminders extends RelativeLayout {
    public RecyclerView recyclerView ;
    public AdapterRGeneral adapterRGeneral;

  public   List<ReminderData> data ;
    public RelGeneralReminders(Context context , FragReminder fragHistory , List<ReminderData> generalData) {
        super(context );
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_recyclerview , this , true);

        this.data=generalData ;
        findViewById(R.id.newReminder).setVisibility(GONE);
        onStart(fragHistory);

    }

    public void onStart (FragReminder fragHistory) {
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterRGeneral = new AdapterRGeneral(data ,this , fragHistory);
        recyclerView.setAdapter(adapterRGeneral);
    }

}
