package com.agonaika.data;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

//import com.agonaika.data.localdb.AgoWorkSqlOpenHelper;
import com.agonaika.data.services.AgoIntentService;
import com.agonaika.utils.AgoAnalytics;
import com.agonaika.utils.AgoLog;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.List;

public class AgoAppEngine extends Application {

    private static Application instance;

    public static void init(Application app) {
        instance = app;
    }

    public static Application getInstance() {
        return instance;
    }

    public static ContentResolver getResolver() {
        return getContext().getContentResolver();
    }

    public static Context getContext() {
        return getInstance();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this); //see https://developer.android.com/studio/build/multidex
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //AgoAnalytics.initCrashlytics(this);
        //AgoAnalytics.initNewRelic(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase.loadLibs(AgoAppEngine.this);
            }
        }).start();
    }

    public static void callService(String action){
        if (isAppInForeground()) {
            Intent serviceIntent = new Intent(getContext(), AgoIntentService.class);
            serviceIntent.setAction(action);
            getContext().startService(serviceIntent);
        }
    }

    public static Intent getServiceIntent(String action){
        Intent serviceIntent = new Intent(getContext(), AgoIntentService.class);
        serviceIntent.setAction(action);
        return serviceIntent;
    }

    public static void stopService(String action){
        if (isAppInForeground()) {
            Intent serviceIntent = new Intent(getContext(), AgoIntentService.class);
            serviceIntent.setAction(action);
            getContext().stopService(serviceIntent);
        }
    }

    public static void callService(Intent intent) {
        if (isAppInForeground()) {
            getContext().startService(intent);
        }
    }

    public static SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase db = null;

        try {
            //db = AgoWorkSqlOpenHelper.getInstance().getReadableDatabase("PassDbKey");//TODO
        } catch (Exception e) {
            AgoLog.v(getContext(), "Failure opening database");
            //AgoWorkSqlOpenHelper.removeCurrentDatabase(getContext());
        }

        return db;
    }

    public static SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase db = null;

        try {
            //db = AgoWorkSqlOpenHelper.getInstance().getWritableDatabase("PassDbKey");//TODO
        } catch (Exception e) {
            AgoLog.v(getContext(), "Failure opening database");
            //AgoWorkSqlOpenHelper.removeCurrentDatabase(getContext());
        }
        return db;
    }

//    public static SQLiteDatabase getWritableDatabase(AgoWorkSqlOpenHelper helper) {
//        SQLiteDatabase db = null;
//
//        try {
//            db = helper.getWritableDatabase("PassDbKey");
//        } catch (Exception e) {
//            AgoLog.v(getContext(), "Failure opening database");
//            AgoWorkSqlOpenHelper.removeCurrentDatabase(getContext());
//        }
//        return db;
//    }


    public static boolean isAppInForeground() {
        ActivityManager manager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = manager.getRunningAppProcesses();
        if (runningProcesses != null && !runningProcesses.isEmpty()) {
            // return if app is the first running process and is running the foreground UI
            return (runningProcesses.get(0).processName.equals(getContext().getPackageName())
                    && runningProcesses.get(0).importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND);
        }
        return false;
    }
}
