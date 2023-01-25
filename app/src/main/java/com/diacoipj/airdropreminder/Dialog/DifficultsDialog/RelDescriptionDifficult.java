package com.diacoipj.airdropreminder.Dialog.DifficultsDialog;

import android.content.Context;
import android.widget.TextView;

import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.CustomClasses.CustomRel;
import com.diacoipj.airdropreminder._OfflineData.OffLineData;

public class RelDescriptionDifficult extends CustomRel {
    String tag ;
    public RelDescriptionDifficult(Context context , String tag) {
        super(context, R.layout.rel_description_difficult);
        this.tag = tag ;
        onStart();
    }

    public void onStart () {
        String title ;
        switch (tag) {
            case "xia" : {
                title = "Xiaomi";
                break;
            }
            case "hua" : {
                title = "Huawei";
                break;
            }
            case "sam" : {
                title = "Samsung";
                break;
            }
            default : {
                title = "Other";
            }
        }

        ((TextView)findViewById(R.id.txtIdea)).setText(title);
        ((TextView)findViewById(R.id.txtDescriptionOmen)).setText(new OffLineData().getDifficult(tag));

        findViewById(R.id.txtAccept).setOnClickListener(view -> MainActivity.getGlobal().HideMyRelDialog());
    }

    @Override
    public void onBackPressed() {
        MainActivity.getGlobal().FinishRelStartRel(new RelDifficultChoose(getContext()));
    }
}
