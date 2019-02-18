package com.agonaika.utils;

import android.util.Log;

public class AgoLog {

    public static void d(Object tag, Object... messages) {
        d(tag.getClass().getSimpleName(), messages);
    }

    public static void d(String TAG, Object... messages) {
        if (CommonUtils.isInDebugMode()) {
            for (Object message : messages) {
                Log.d(TAG, getToPrintFromObject(message));
            }
        }
    }

    public static void i(Object tag, Object... messages) {
        i(tag.getClass().getSimpleName(), messages);
    }

    public static void i(String TAG, Object... messages) {
        if (CommonUtils.isInDebugMode()) {
            for (Object message : messages) {
                Log.i(TAG, getToPrintFromObject(message));
            }
        }
    }

    public static void e(Object tag, Object... messages) {
        e(tag.getClass().getSimpleName(), messages);
    }

    public static void e(String TAG, Object... messages) {
        if (CommonUtils.isInDebugMode()) {
            for (Object message : messages) {
                Log.e(TAG, getToPrintFromObject(message));
            }
        } else {
            for (Object message : messages) {
                AgoAnalytics.leaveBreadcrumb("AgoLog" + getToPrintFromObject(message));
            }
        }
    }

    public static void v(Object tag, Object... messages) {
        v(tag.getClass().getSimpleName(), messages);
    }

    public static void v(String TAG, Object... messages) {
        if (CommonUtils.isInDebugMode()) {
            for (Object message : messages) {
                Log.v(TAG, getToPrintFromObject(message));
            }
        }
    }

    public static void w(Object tag, Object... messages) {
        w(tag.getClass().getSimpleName(), messages);
    }

    public static void w(String TAG, Object... messages) {
        if (CommonUtils.isInDebugMode()) {
            for (Object message : messages) {
                Log.w(TAG, getToPrintFromObject(message));
            }
        }
    }

    public static String getToPrintFromObject(Object object) {
        return (String) ((object == null) ? "null" : ((object instanceof String) ? object : object.toString()));
    }
}
