package com.agonaika.utils;

//import com.crashlytics.android.Crashlytics;

public class AgoAnalytics {

    private static final String NEW_RELIC_APP_TOKEN = "";
    private static final String TAG = AgoAnalytics.class.getSimpleName();
    private static final String GOOGLE_ANALYTICS_TRACKING_ID = "";


    public static void leaveBreadcrumb(String message) {
        if (CommonUtils.isInDebugMode()) {
            return;
        }

        if (message != null
                && message.length() > 0) {
            //Crashlytics.log(message);
        }
    }
}
