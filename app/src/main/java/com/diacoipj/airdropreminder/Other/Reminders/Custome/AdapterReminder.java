package com.diacoipj.airdropreminder.Other.Reminders.Custome;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.util.List;

public class AdapterReminder extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<ReminderData> itemList;
    FragReminder fragReminder ;

    public AdapterReminder(List<ReminderData> itemList , FragReminder fragReminder) {
        this.itemList = itemList;
        this.fragReminder = fragReminder ;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterReminder.RecStoryViewHolder(new RelitemRemider(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RelitemRemider rel = (RelitemRemider) holder.itemView;
        rel.OnStart(itemList.get(position),position , itemList , this , fragReminder);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class RecStoryViewHolder extends RecyclerView.ViewHolder {

        RelitemRemider VIEW;
        RecStoryViewHolder(RelitemRemider view) {
            super(view);
            VIEW = view;
        }
    }
}
