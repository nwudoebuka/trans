package com.agonaika.data;

public class AppConstants {


    // Controller state
    public static final int IDLE = 0x000001;
    public static final int WAITING = 0x000002;
    public static final int SYNCING = 0x000003;
    public static final int NEED_TO_SYNC = 0x000004;

    public static final int MSG_ON_ERROR = 0x000000;
    public static final int MSG_ON_LOGIN = 0x000001;
    public static final int MSG_ON_TIME_UPDATED = 0x000003;
    public static final int MSG_ON_DISCLAIMER_ACCEPTED = 0x000004;
    public static final int MSG_ON_INITIAL_SYNC = 0x000005;
    public static final int MSG_ON_DATABASE_UPGRADE = 0x000015;
    public static final int MSG_ON_NETWORK_STATE_CHANGE = 0x00001A;
    public static final int MSG_GET_VERSION = 0x00001B;
    public static final int MSG_ON_LOGOUT = 0x000017;

    public static final int LOGIN_FAILURE_WITH_MESSSAGE = 0x000008;

    public static final String RSA_KEYSTORE = "ago_keys";
}
