package com.agonaika.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AgoPreferences {

    private static SharedPreferences mSharedPreferences;

    // ==================================================
    // Constants
    // ==================================================
    public static final String VALUE_TRUE_STRING = "true";
    public static final String VALUE_FALSE_STRING = "false";


    public static final String VALUE_ONE_STRING = "1";
    public static final String VALUE_ZERO_STRING = "0";

    public static final String SHARED_PREFERENCES_NAME = "agonaika";

    public static final String LAST_LOGIN_MEMBER = "last_login_member";
    public static final String LAST_LOGIN_USER = "last_login_user";

    public static final String PREF_LAST_GROUP = "last_login_affiliate";
    public static final String PREF_LAST_USER = "last_login_username";
    public static final String PREF_LAST_PASSWORD = "last_login_password";

    public static final String PREF_QUICK_PASSCODE = "QuickPasscode";


    public static boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    public static String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        getSharedPreferences().edit().putBoolean(key, value).commit();
    }

    public static void putInt(String key, int value) {
        getSharedPreferences().edit().putInt(key, value).commit();
    }

    public static void putLong(String key, long value) {
        getSharedPreferences().edit().putLong(key, value).commit();
    }

    public static void putString(String key, String value) {
        getSharedPreferences().edit().putString(key, value).commit();
    }


    public static SharedPreferences getSharedPreferences() {
        if (mSharedPreferences == null) {
            mSharedPreferences = AgoAppEngine.getInstance().getApplicationContext()
                    .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        }

        return mSharedPreferences;
    }

    public static String getKeyForCurrentUser(String baseKey) {
        String group = getString(AgoPreferences.PREF_LAST_GROUP, "");
        String user = getString(AgoPreferences.PREF_LAST_USER, "");
        return (group + user + baseKey);
    }
}
