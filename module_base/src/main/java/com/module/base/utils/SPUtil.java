package com.module.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Huangshuang  2018/3/8 0008
 */

public class SPUtil {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private final String FILE_NAME = "save_file_name";

    private static Context context;

    private static final SPUtil OUR_INSTANCE = new SPUtil();

    public static SPUtil getInstance(Context context) {
        SPUtil.context = context.getApplicationContext();
        return OUR_INSTANCE;
    }

    private SPUtil() {
    }


    public void setString(String key, String values) {
        try {
            sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString(key, values);
            editor.apply();
        } catch (Exception e) {
        }
    }

    public void setBooloean(String key, boolean values) {
        try {
            sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putBoolean(key, values);
            editor.apply();
        } catch (Exception e) {
        }
    }

    public void setInt(String key, int values) {
        try {
            sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putInt(key, values);
            editor.apply();
        } catch (Exception e) {
        }
    }


    public String getString(String key) {
        try {
            sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            ;
            String data = sharedPreferences.getString(key, "");
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean getBoolean(String key) {
        try {
            sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            boolean data = sharedPreferences.getBoolean(key, false);
            return data;
        } catch (Exception e) {
            return false;
        }
    }

    public int getInt(String key) {
        try {
            sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            ;
            int data = sharedPreferences.getInt(key, 0);
            return data;
        } catch (Exception e) {
            return 0;
        }
    }

    public void clearData() {
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
