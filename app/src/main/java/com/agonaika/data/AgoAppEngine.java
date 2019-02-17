package com.agonaika.data;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;

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
}
