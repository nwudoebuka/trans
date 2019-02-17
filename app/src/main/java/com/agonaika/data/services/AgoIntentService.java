package com.agonaika.data.services;

import android.app.NotificationManager;
import android.content.Intent;

import com.agonaika.data.AgoController;
import com.agonaika.utils.AgoLog;

public class AgoIntentService extends AgoBaseIntentService {

    private static final String TAG = AgoIntentService.class.getSimpleName();
    private static final int STANDARD_NOTIFICATION = 1;
    private NotificationManager mNotificationManager;

    public AgoIntentService() {
        super();
        AgoLog.i(TAG, "AgoIntentService being created!");
        addListener(AgoController.getInstance());
    }

    public interface AgoBaseIntentServiceListener {
        public void onError(int ErrorCode);

        public void onError(int ErrorCode, String ErrorMessage);
    }

    public interface AgoServiceListener extends AgoBaseIntentServiceListener {

        public void onLogin(String userId);
        public void onInitialSync(String title, String msg);
        public void onRecordsUpdated();
        public void onEulaAccepted();
        public void onDatabaseUpgrade();
        public void onStateChange(int state, Intent i);
        public void onDataPushed();
        public void onDataPulled(int type);
        public void onLogout();
        public void onGetVersionNumber(String ver);
        public void onNetworkStateChange(String state);

    }

    private void notifyLoginListeners(String userId) {
        AgoLog.i(TAG, "notifyLoginListeners called!");
        for (AgoServiceListener listener : super.getListeners(AgoServiceListener.class)) {
            listener.onLogin(userId);
        }
    }

    private void notifyInitialSync(String title, String msg) {
        for (AgoServiceListener listener : super.getListeners(AgoServiceListener.class)) {
            listener.onInitialSync(title, msg);
        }
    }

    private void notifyNetworkStateChange(String state) {
        for (AgoServiceListener listener : super.getListeners(AgoServiceListener.class)) {
            listener.onNetworkStateChange(state);
        }
    }

    private void notifyRecordsUpdated() {
        for (AgoServiceListener listener : super.getListeners(AgoServiceListener.class)) {
            listener.onRecordsUpdated();
        }
    }
}
