package com.agonaika.data;

import android.content.Intent;
import android.os.Handler;

import com.agonaika.data.services.AgoIntentService;

import java.util.ArrayList;

public class AgoController implements AgoIntentService.AgoServiceListener {
    private static AgoController mInstance;
    private ArrayList<Handler> mHandlers = null;
    private AgoModel mModel;
    private int mState = AppConstants.IDLE;

    public static AgoController getInstance() {
        if (mInstance == null) {
            mInstance = new AgoController();
        }
        return mInstance;
    }

    public AgoController() {
        mHandlers = new ArrayList<Handler>();
        mModel = AgoModel.getInstance();
    }

    public int getState() {
        return mState;
    }

    private synchronized void notifyListeners(int msgId, Object obj) {
        if (mHandlers == null
                || mHandlers.isEmpty()) {
            return;
        }

        for (Handler listener : mHandlers) {
            listener.sendMessage(listener.obtainMessage(msgId, obj));
        }
    }

    @Override
    public void onDatabaseUpgrade() {
        notifyListeners(AppConstants.MSG_ON_DATABASE_UPGRADE, null);
    }

    @Override
    public void onDataPulled(int type) {
    }

    @Override
    public void onDataPushed() {
    }

    @Override
    public void onGetVersionNumber(String version) {
        notifyListeners(AppConstants.MSG_GET_VERSION, version);
    }

    @Override
    public void onError(int ErrorCode) {
        notifyListeners(AppConstants.MSG_ON_ERROR, ErrorCode);
    }

    @Override
    public void onError(int ErrorCode, String ErrorMessage) {
        String[] ErrorStuff = new String[]{ErrorCode + "", ErrorMessage};
        notifyListeners(AppConstants.MSG_ON_ERROR, ErrorStuff);
    }

    @Override
    public void onInitialSync(String title, String msg) {
        String[] message = new String[]{title, msg};
        notifyListeners(AppConstants.MSG_ON_INITIAL_SYNC, message);
    }

    @Override
    public void onEulaAccepted() {
        notifyListeners(AppConstants.MSG_ON_DISCLAIMER_ACCEPTED, null);
    }


    @Override
    public void onLogin(String userId) {
        notifyListeners(AppConstants.MSG_ON_LOGIN, userId);
    }

    @Override
    public void onLogout() {
        notifyListeners(AppConstants.MSG_ON_LOGOUT, null);
    }

    @Override
    public void onNetworkStateChange(String state) {
        String[] message = new String[]{state};
        notifyListeners(AppConstants.MSG_ON_NETWORK_STATE_CHANGE, message);
    }

    @Override
    public void onRecordsUpdated() {
        notifyListeners(AppConstants.MSG_ON_TIME_UPDATED, null);
    }

    @Override
    public void onStateChange(int state, Intent i) {
        mState = state;
    }







    public void dispose() {
        mHandlers.clear();
        mModel = null;
    }
}
