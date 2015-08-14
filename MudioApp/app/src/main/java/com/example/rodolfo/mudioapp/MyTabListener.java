package com.example.rodolfo.mudioapp;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

/**
 * Created by Rodolfo on 23/06/2015.
 */
public class MyTabListener implements ActionBar.TabListener {
    Context mCtx;
    int mIndex;

    public MyTabListener (Context ctx, int index){
        mCtx = ctx;
        mIndex = index;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
