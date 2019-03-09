package com.agonaika.data.remote;

import android.os.Build;
import android.text.TextUtils;

import com.agonaika.data.AgoPreferences;
import com.agonaika.utils.AgoUtils;
import com.agonaika.utils.DeviceInfo;

public class JsonService {

    private static final String TAG = JsonService.class.getSimpleName();

    //  millisec
    private static int TEN_SEC_TIMER = 10000;
    private static int ONE_MINUTE_TIMER = 60000;
    private static int FIVE_MINUTES_TIMER = 300000;

    private LoginCredential mCredentials = null;

    private static JsonService mInstance = null;

    public static JsonService getInstance() {
        if (mInstance == null)
            mInstance = new JsonService();

        return mInstance;
    }

    private void InitializeCredentials() {
        String group = AgoPreferences.getString(AgoPreferences.PREF_LAST_GROUP,
                "");
        String userId = AgoPreferences.getString(AgoPreferences.PREF_LAST_USER,
                "");
        String encryptedPasswd = AgoPreferences.getString(
                AgoPreferences.PREF_LAST_PASSWORD, "");
        String passwd = "";

        if (!TextUtils.isEmpty(encryptedPasswd)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                passwd = AgoUtils.decryptWithKeyStore(encryptedPasswd);
            } else {
                passwd = AgoUtils.decodeUData(encryptedPasswd,
                        group + DeviceInfo.getDeviceId() + ":" + userId);
            }
            if (!TextUtils.isDigitsOnly(passwd)) {
                mCredentials = new LoginCredential(group, userId, passwd);
            }
        } else {
            // pwd is empty which means the user is logging in for the first
            // time
            // SetCredentials() will take care of filling it
        }
    }

    public LoginCredential GetCredentials() {
        if (mCredentials == null) {
            mCredentials = new LoginCredential();
        }

        return mCredentials;
    }
}
