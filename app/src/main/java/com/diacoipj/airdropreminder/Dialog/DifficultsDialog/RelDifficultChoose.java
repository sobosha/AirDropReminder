package com.diacoipj.airdropreminder.Dialog.DifficultsDialog;

import android.content.Context;

import com.diacoipj.airdropreminder.Dialog.DayOfWeek.ModelWeeks;
import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.CustomClasses.CustomRel;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;

import java.util.ArrayList;
import java.util.List;

public class RelDifficultChoose extends CustomRel {
    List<ModelWeeks> modelWeeks = new ArrayList<>();
    public RelDifficultChoose(Context context) {
        super(context, R.layout.rel_difficult_choose);
        initializeValue();
        setClickOnDays();
    }
    
    public void initializeValue () {
        modelWeeks.add(new ModelWeeks(findViewById(R.id.linSaturday), findViewById(R.id.rdbSaturday)));
        modelWeeks.add(new ModelWeeks(findViewById(R.id.linWednesday), findViewById(R.id.rdbWednesday)));
        modelWeeks.add(new ModelWeeks(findViewById(R.id.linSunday), findViewById(R.id.rdbSunday)));
        modelWeeks.add(new ModelWeeks(findViewById(R.id.linThursday), findViewById(R.id.rdbThursday)));
        modelWeeks.get(0).getDays().setTag("sam");
        modelWeeks.get(1).getDays().setTag("hua");
        modelWeeks.get(2).getDays().setTag("xia");
        modelWeeks.get(3).getDays().setTag("other");
        new Setting().setTypeFace(findViewById(R.id.txtDayOfWeek));
    }

    private void setClickOnDays() {
        for (int i = 0; i < modelWeeks.size(); i++) {
            final int I = i;
            modelWeeks.get(i).getDays().setOnClickListener(view -> {
                modelWeeks.get(I).setCheck(!modelWeeks.get(I).isCheck());
                style(modelWeeks.get(I).getDays().getTag().toString());
            });
            modelWeeks.get(i).getRdbDays().setOnClickListener(view -> {
                modelWeeks.get(I).getDays().callOnClick();
            });
        }

        findViewById(R.id.txtAccept).setOnClickListener(view -> {
            String tag = null;
            for (int i = 0; i < modelWeeks.size(); i++) {
                if (modelWeeks.get(i).isCheck()) {
                    tag = modelWeeks.get(i).getDays().getTag().toString();
                }
            }
            if (tag!=null)
                MainActivity.getGlobal().FinishRelStartRel(new RelDescriptionDifficult(getContext() , tag));
            else {
                mAnimation.Viberation(this);
            }
        });


    }

    private void style (String tag) {
        for (int i = 0; i < modelWeeks.size() ; i++) {
            modelWeeks.get(i).getRdbDays().setChecked(modelWeeks.get(i).getDays().getTag().toString().equals(tag));
            modelWeeks.get(i).setCheck(modelWeeks.get(i).getDays().getTag().toString().equals(tag));
        }
    }

    @Override
    public void onBackPressed() {
        MainActivity.getGlobal().HideMyRelDialog();
    }
}
