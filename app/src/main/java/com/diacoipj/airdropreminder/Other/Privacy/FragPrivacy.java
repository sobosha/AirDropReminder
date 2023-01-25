package com.diacoipj.airdropreminder.Other.Privacy;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.diacoipj.airdropreminder.Dialog.RelMessageBox;
import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder.Setting.mFragment;
import com.diacoipj.airdropreminder._Core.IView;
import com.diacoipj.airdropreminder._Core.Presenter;
import com.diacoipj.airdropreminder._Core.respons.PrivacyResult;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class FragPrivacy extends mFragment implements IView<PrivacyResult> {
    View parent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.frag_rules, container, false);

        style();

        parent.findViewById(R.id.relHeader).setOnClickListener(view -> {
            mAnimation.PressClick(parent.findViewById(R.id.arrowBack)).setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mBackPressed();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        });

        SendRequest();

        return parent;
    }

    private void style() {
        //MainActivity.getGlobal().setHeaderAndFooter(false, "", false);
        parent.findViewById(R.id.imgFadeTop).setBackground(Setting.setBackGradiantFullRadTB(getContext(),Color.parseColor("#EED8C6"), android.R.color.transparent, 0));
        parent.findViewById(R.id.scrollVIEw).setVisibility(View.VISIBLE);

        new Setting().setTypeFace(parent.findViewById(R.id.txtRules));
        new Setting().setTypeFace(parent.findViewById(R.id.txtRules));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView) parent.findViewById(R.id.txtRules)).setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        ((TextView) parent.findViewById(R.id.txtRules)).setText(MainActivity.getGlobal().getResources().getString(R.string.PrivacyPolicy));
    }


    @Override
    public void SendRequest() {
        Presenter.get_global().GetAction(this, "app", "privacy", "", PrivacyResult.class);
    }

    @Override
    public void OnSucceed(PrivacyResult object) {
        ((TextView) parent.findViewById(R.id.txtPrivacy)).setText(object.getText());
    }

    @Override
    public void OnError(String error, int statusCode) {

        MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext()).ExitButton("connectionError",  MainActivity.getGlobal().getResources().getString(R.string.connected1)));
    }


    @Override
    public void mBackPressed() {
        super.mBackPressed();
        MainActivity.getGlobal().FinishFragStartFrag(new FragReminder());
    }

}
