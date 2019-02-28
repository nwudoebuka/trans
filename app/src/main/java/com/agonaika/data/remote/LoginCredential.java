package com.agonaika.data.remote;

import android.text.TextUtils;

import com.agonaika.utils.DeviceInfo;

public class LoginCredential {

    public String deviceIdUserHash = "";

    public String deviceId = "";

    public String affiliateName = "";
    public String userId = "";
    public String password = "";

    // html encoded info
    public String htmlEncodedGroup = "";
    public String htmlEncodedUserId = "";
    public String htmlEncodedPassword = "";

    public LoginCredential() {
    }

    public LoginCredential(String grpName, String uid, String pswd) {
        deviceId = DeviceInfo.getDeviceId();
        deviceIdUserHash = deviceId + ":" + uid;

        affiliateName = grpName;
        userId = uid;
        password = pswd;

        htmlEncodedGroup = TextUtils.htmlEncode(grpName);
        htmlEncodedUserId = TextUtils.htmlEncode(uid);
        htmlEncodedPassword = TextUtils.htmlEncode(pswd);
    }

    public static String getDeviceType() {
        return "Android";
    }
}
