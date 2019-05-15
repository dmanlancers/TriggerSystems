package com.wetrig.dev.wetrig;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.google.gson.Gson;
import com.wetrig.dev.wetrig.Authentication.LoginActivity;

public class Utils {


    public static void addFragment(AppCompatActivity activity, int containerView, Fragment currentFragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerView, currentFragment)
                .addToBackStack(null)
                .commit();
    }
    public static void removeFragment(AppCompatActivity activity, int containerView, Fragment currentFragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerView, currentFragment)
                .addToBackStack(null)
                .commit();
    }



}
