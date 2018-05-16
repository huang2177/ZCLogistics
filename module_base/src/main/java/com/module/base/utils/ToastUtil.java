package com.module.base.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author Huangshuang  2018/3/16 0016
 */

public class ToastUtil {

    public static void show(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 400);
        toast.show();
    }
}
