package com.agonaika.data.domain;

import android.content.ContentValues;
import android.database.Cursor;

import com.agonaika.utils.AgoLog;

import java.util.ArrayList;

public abstract class BaseEntity {

    private static String TAG = BaseEntity.class.getSimpleName();

    public transient Integer id = null;

    public static ArrayList<? extends BaseEntity> buildFromCursor(Class<? extends BaseEntity> classType, Cursor cursor) {
        return buildFromCursor(classType, cursor, true);
    }

    public static ArrayList<? extends BaseEntity> buildFromCursor(Class<? extends BaseEntity> classType, Cursor cursor, boolean closeCursor) {
        if (cursor == null) {
            return null;
        }

        DbBaseEntity dbObject = getDbObject(classType);

        if (dbObject == null) {
            return null;
        }

        ArrayList<? extends BaseEntity> results = dbObject.buildFromCursor(cursor);

        if (closeCursor) {
            cursor.close();
        }
        return results;
    }

    public static ContentValues toContentValues(Class<? extends BaseEntity> classType, BaseEntity objectToConvert) {
        DbBaseEntity dbObject = getDbObject(classType);

        if (dbObject == null) {
            return null;
        }

        ContentValues values = dbObject.toContentValues(objectToConvert);

        if (objectToConvert.id != null) {
            values.put(DbBaseEntity.ID, objectToConvert.id);
        }
        return values;
    }

    public static ContentValues[] toContentValues(Class<? extends BaseEntity> classType, ArrayList<? extends BaseEntity> objectsToConvert) {
        DbBaseEntity dbObject = getDbObject(classType);

        if (dbObject == null) {
            return null;
        }

        ContentValues[] values = new ContentValues[objectsToConvert.size()];

        for (int i = 0; i < values.length; i++) {
            BaseEntity objectToConvert = objectsToConvert.get(i);
            ContentValues singleValues = BaseEntity.toContentValues(classType, objectToConvert);
            values[i] = singleValues;
        }
        return values;
    }

    public static BaseEntity buildFromContentValues(Class<? extends BaseEntity> classType, ContentValues contentValues) {
        DbBaseEntity dbObject = getDbObject(classType);

        if (dbObject == null) {
            return null;
        }

        return dbObject.buildFromContentValues(contentValues);
    }

    public static BaseEntity buildSingleObjectFromCursor(Class<? extends BaseEntity> classType, Cursor cursor) {
        return buildSingleObjectFromCursor(classType, cursor, false);
    }

    public static BaseEntity buildSingleObjectFromCursor(Class<? extends BaseEntity> classType, Cursor cursor, boolean closeCursor) {
        ArrayList<BaseEntity> imObjects = (ArrayList<BaseEntity>) buildFromCursor(classType, cursor, closeCursor);

        if (imObjects != null) {
            if (imObjects.size() > 1) {
                AgoLog.w(TAG, "Developer asked for single object, but multiple objects were found and built.",
                        "Only object at index 0 was returned!");
            }

            if (imObjects.isEmpty()) {
                AgoLog.w(TAG, "Developer asked for single object, but no objects could be found.",
                        "Returning null!");
                return null;
            }

            return imObjects.get(0);
        }

        return null;
    }

    // =========================
    // Getters and Setters
    // =========================

    private static DbBaseEntity getDbObject(Class<? extends BaseEntity> classType) {
        try {
            return classType.newInstance().getDbObjectClass().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // Abstract Methods
    // =========================

    public abstract Class<? extends DbBaseEntity> getDbObjectClass();
}
