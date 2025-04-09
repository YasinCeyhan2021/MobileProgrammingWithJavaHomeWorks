package com.example.odev;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesClass {
    public void fileWrite(Context context, String key, String value){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString(key, value);
        edit.commit();
    }
    public String fileRead(Context context, String key){
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).getString(key, null);
    }
    public void fileClear(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.clear();
        edit.commit();
    }
}