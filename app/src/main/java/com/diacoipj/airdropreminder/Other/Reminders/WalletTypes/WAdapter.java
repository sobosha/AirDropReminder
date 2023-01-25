package com.diacoipj.airdropreminder.Other.Reminders.WalletTypes;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diacoipj.airdropreminder.Other.Reminders.FragGetReminderInfo;
import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;

import java.util.ArrayList;
import java.util.List;

public class WAdapter extends RecyclerView.Adapter<WAdapter.ViewHolder> {


    public WAdapter(List<WalletsTypes> models, FragGetReminderInfo fragDoctor, WalletTypeList referral,String type) {
        this.models = models;
        this.fragDoctor = fragDoctor;
        this.relDialogTypeOfReferral=referral;
        this.type=type;
    }
    String type;
    List<WalletsTypes> models =new ArrayList<>();
    FragGetReminderInfo fragDoctor;
    WalletTypeList relDialogTypeOfReferral;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(new ItemRecy(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemRecy item=(ItemRecy) holder.itemView;
        if(type.contains("wallet"))
        item.onstart(models,position,fragDoctor,relDialogTypeOfReferral);
        else item.onChain(models,position,fragDoctor,relDialogTypeOfReferral);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecy view;

        public ViewHolder(@NonNull ItemRecy item) {
            super(item);

            view = item;

        }
    }

    public void search (List<WalletsTypes> models) {
        this.models = models;
        notifyDataSetChanged();
    }
}