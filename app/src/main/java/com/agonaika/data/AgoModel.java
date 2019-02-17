package com.agonaika.data;

public class AgoModel {
    public static AgoModel mInstance;

    public static AgoModel getInstance() {
        if (mInstance == null) {
            mInstance = new AgoModel();
        }
        return mInstance;
    }

    private static final String TAG = AgoModel.class.getSimpleName();
}

