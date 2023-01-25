package com.diacoipj.airdropreminder.Other.Reminders.General;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.util.List;

public class AdapterRGeneral extends RecyclerView.Adapter<AdapterRGeneral.ViewHolder> {

    List<ReminderData> data ;
    RelGeneralReminders rel1 ;
    FragReminder fragHistory ;

    public AdapterRGeneral(List<ReminderData> data, RelGeneralReminders rel1 , FragReminder fragHistory) {
        this.data = data;
        this.rel1 = rel1;
        this.fragHistory = fragHistory ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(new _RelGeneral_items(parent.getContext(),rel1));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        _RelGeneral_items relGeneralitems1 = (_RelGeneral_items)holder.itemView ;
        relGeneralitems1.onStart(data.get(position) , position , fragHistory , this);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        _RelGeneral_items relGeneralitems;
        public ViewHolder(@NonNull _RelGeneral_items itemView) {
            super(itemView);
            relGeneralitems = itemView ;
        }
    }
}
