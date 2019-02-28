package com.agonaika.data.domain.user;

import com.agonaika.data.remote.JsonService;
import com.agonaika.data.remote.LoginCredential;
import com.agonaika.utils.CommonFunctions;

public class DeviceUser {

    private static DeviceUser instance;

    public static DeviceUser get() {
        if (instance == null) {
            instance = new DeviceUser();
        }
        return instance;
    }

    public static int maxLoginFailure = 6;
    private static int isLoggedIn = 0;
    private UserConfiguration userConfiguration = new UserConfiguration();
    private UserLogin userLogin;
    //private UserCredentials currentCredentials;

    public static void nullifyInstance() {
        instance = null;
    }
    public static void setUserLoggedIn(int value){
        DeviceUser.isLoggedIn = value;
    }
    public static boolean isUserLoggedIn() {
        return (DeviceUser.isLoggedIn == 1);
    }
    public static UserConfiguration getUserConfig() {
        return DeviceUser.get().getConfiguration();
    }
    public static String getEnterpriseUserId() {
        return CommonFunctions.prefixUserIdWithEnterpriseId(UserId()).toLowerCase();
    }

    public static String UserId() {
        return get().userConfiguration.UserID;
    }




    public UserConfiguration getConfiguration() {
        return userConfiguration;
    }

    public UserLogin getCurrentCredentials() {
        if (userLogin == null) {
            LoginCredential loginCredentials = JsonService.getInstance().GetCredentials();
            userLogin = new UserLogin();
            userLogin.userId = loginCredentials.userId;
            userLogin.deviceId = loginCredentials.deviceIdUserHash;
            userLogin.password = loginCredentials.password;
            userLogin.affiliateName = loginCredentials.affiliateName;
            userLogin.deviceType = LoginCredential.getDeviceType();
        }

        return userLogin;
    }
}
