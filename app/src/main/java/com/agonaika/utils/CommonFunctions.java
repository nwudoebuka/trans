package com.agonaika.utils;

import android.text.TextUtils;

import com.agonaika.data.domain.user.DeviceUser;

public class CommonFunctions {




    public static String prefixUserIdWithEnterpriseId(String userId) {
        if (userId.contains("::") || TextUtils.isEmpty(userId)){
            return userId;
        }
        else {
            return String.format("%s::%s", DeviceUser.getUserConfig().EnterpriseName, userId);
        }
    }
}
