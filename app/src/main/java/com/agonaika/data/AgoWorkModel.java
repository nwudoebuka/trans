package com.agonaika.data;

public class AgoWorkModel {
    public static AgoWorkModel mInstance;

    public static AgoWorkModel getInstance() {
        if (mInstance == null) {
            mInstance = new AgoWorkModel();
        }
        return mInstance;
    }

    private static final String TAG = AgoWorkModel.class.getSimpleName();
}

