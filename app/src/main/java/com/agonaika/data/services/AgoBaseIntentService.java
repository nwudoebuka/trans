package com.agonaika.data.services;

import android.app.IntentService;
import android.content.Intent;

import com.agonaika.data.AgoController;
import com.agonaika.data.IObservable;
import com.agonaika.data.Observable;

import java.util.List;

public class AgoBaseIntentService extends IntentService implements IObservable {
    private static final String TAG = AgoBaseIntentService.class.getSimpleName();
    protected final Observable mObservable;

    public AgoBaseIntentService() {
        super(TAG);
        mObservable = new Observable();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public interface AgoBaseIntentServiceListener {
        public void onError(int ErrorCode);
    }

    public interface AgoServiceListener extends
            AgoBaseIntentServiceListener {
        public void onLogin(String userId);
    }

    @SuppressWarnings("unused")
    private void notifyLoginListeners(String userId) {
        // may not be needed as the cursor can be listened to directly
        for (AgoServiceListener listener : getListeners(AgoServiceListener.class)) {
            listener.onLogin(userId);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public <L> void addListener(L listener) {
        synchronized (mObservable) {
            mObservable.addListener(listener);
        }
    }

    public <L> void removeListener(L listener) {
        synchronized (mObservable) {
            mObservable.removeListener(listener);
        }
    }

    protected void clearListeners() {
        synchronized (mObservable) {
            mObservable.removeAll();
        }
    }

    @SuppressWarnings("unchecked")
    public void removeListeners(@SuppressWarnings("rawtypes") Class clazz) {
        synchronized (mObservable) {
            mObservable.removeListeners(clazz);
        }
    }

    public void removeAllListeners() {
        synchronized (mObservable) {
            mObservable.removeAll();
        }
    }

    protected <L> List<L> getListeners(Class<L> clazz) {
        List<L> listeners = null;
        synchronized (mObservable) {
            listeners = mObservable.getListeners(clazz);
            //This is to ensure that the IMBillsController singleton is listening to the IntentService
            if (listeners.size() == 0) {
                mObservable.addListener(AgoController.getInstance());
            }
        }
        return listeners;
    }
}
