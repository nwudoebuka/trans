package com.agonaika.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import com.agonaika.data.AgoAppEngine;

public class CommonUtils {

    public static boolean isInDebugMode() {
        if (isEmulator()) {
            return true;
        }
        return isInDebugMode(AgoAppEngine.getContext());
    }

    public static boolean isEmulator() {
        return Build.PRODUCT.toLowerCase().contains("sdk")
                || Build.DEVICE.toLowerCase().contains("sdk")
                || Build.DEVICE.toLowerCase().contains("chrome dev")
                || Build.MODEL.toLowerCase().contains("sdk")
                || Build.MODEL.toLowerCase().contains("chrome")
                || Build.MODEL.toLowerCase().contains("emulator");
    }

    public static boolean isInDebugMode(Context context) {
        return (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }
}
