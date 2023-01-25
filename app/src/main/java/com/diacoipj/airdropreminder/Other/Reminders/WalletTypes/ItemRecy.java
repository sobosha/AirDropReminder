package com.diacoipj.airdropreminder.Other.Reminders.WalletTypes;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diacoipj.airdropreminder.Other.Reminders.FragGetReminderInfo;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.Setting;

import java.util.List;


public class ItemRecy extends RelativeLayout {
    WalletTypeList typeOfReferral;
    Setting setting ;
    public ItemRecy(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.frag_item_recy, this, true);
        setting = new Setting();
        setting.setTypeFace(findViewById(R.id.name));
    }

    public void onstart(List<WalletsTypes> models, int pos, FragGetReminderInfo fragDoctor, WalletTypeList referral) {
        this.typeOfReferral=referral;
        ((TextView)findViewById(R.id.name)).setText(models.get(pos).getName());
        ((RadioButton)findViewById(R.id.ic_check)).setChecked(models.get(pos).isCheck());

        this.setOnClickListener(v -> {
                new Setting().HideKeyBoard();
                ((EditText) fragDoctor.parent.findViewById(R.id.walletType)).setText(models.get(pos).getName());
                ((RadioButton) findViewById(R.id.ic_check)).setChecked(true);
                fragDoctor.hideDialogWallet();
                fragDoctor.parent.findViewById(R.id.dialog_type).setVisibility(GONE);
                ((RelativeLayout) fragDoctor.parent.findViewById(R.id.dialog_type)).removeAllViews();

        });
        findViewById(R.id.ic_check).setOnClickListener(view -> callOnClick());
    }

    public void onChain(List<WalletsTypes> models, int position, FragGetReminderInfo fragDoctor, WalletTypeList relDialogTypeOfReferral) {
        this.typeOfReferral=relDialogTypeOfReferral;
        ((TextView)findViewById(R.id.name)).setText(models.get(position).getName());
        ((RadioButton)findViewById(R.id.ic_check)).setChecked(models.get(position).isCheck());

        this.setOnClickListener(v -> {
            new Setting().HideKeyBoard();
            ((EditText) fragDoctor.parent.findViewById(R.id.ChainType)).setText(models.get(position).getName());
            ((RadioButton) findViewById(R.id.ic_check)).setChecked(true);
            fragDoctor.hideDialogChain();
            fragDoctor.parent.findViewById(R.id.dialog_Chaintype).setVisibility(GONE);
            ((RelativeLayout) fragDoctor.parent.findViewById(R.id.dialog_Chaintype)).removeAllViews();

        });
        findViewById(R.id.ic_check).setOnClickListener(view -> callOnClick());
    }
}