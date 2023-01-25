package com.diacoipj.airdropreminder.Setting.CustomClasses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.diacoipj.airdropreminder.Setting.mFragment;

import java.io.Serializable;

public abstract class CustomFragment extends mFragment {
    public View parent ;

    private Bundle bundle = new Bundle();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(layout() , container,  false);

        return parent ;
    }
    public abstract int layout ();

    public abstract void onCreateMyView () ;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onCreateMyView();
    }

    public String getStringBundle (String key) {
        if (getArguments() != null) {
            String argument = getArguments().getString(key);
            return argument == null ? "" : argument;
        } else return "";
    }

    public int getIntBundle (String key) {
        if (getArguments() != null)
            return getArguments().getInt(key);
        else
            return -1 ;
    }

    public boolean getBooleanBundle (String key) {
        if (getArguments() !=null)
            return getArguments().getBoolean(key);
        else
            return false ;
    }

    public void setStringBundle (String key , String value) {
        bundle.putString(key , value);
    }

    public void setIntBundle (String key , int value) {
        bundle.putInt(key , value);
    }

    public void setBooleanBundle (String key , boolean value) {
        bundle.putBoolean(key , value);
    }

    public <T> void setObjectBundle (String key , T objectValue) {
        bundle.putSerializable(key , (Serializable) objectValue);
    }

    public <T> T getObjectBundle (String key) {
        if (getArguments()!=null) {
            return (T) getArguments().getSerializable(key);
        } else
            return null ;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public mFragment getFragBundle (mFragment mFragment) {
        mFragment.setArguments(bundle);
        return mFragment;
    }


}
