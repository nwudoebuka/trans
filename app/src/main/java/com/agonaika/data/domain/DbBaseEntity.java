package com.agonaika.data.domain;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

import com.agonaika.data.AgoAppEngine;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteStatement;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class DbBaseEntity implements BaseColumns {

    //@SuppressWarnings("unused")
    //private static final String TAG = DbBaseEntity.class.getSimpleName();

    public static String ID = BaseColumns._ID;

    public static Boolean getBoolean(Cursor cursor, String columnName) {
        boolean returnValue = false;
        if (cursor.getColumnIndex(columnName) != -1) {
            returnValue = (cursor.getInt(cursor.getColumnIndex(columnName)) == 1);
        }
        return returnValue;
    }

    public static boolean fromIntToBoolean(int value) {
        return value == 1;
    }

    public static Double getDouble(Cursor cursor, String columnName) {
        if (cursor.getColumnIndex(columnName) != -1) {
            return cursor.getDouble(cursor.getColumnIndex(columnName));
        } else {
            return null;
        }
    }

    public static Integer getInteger(Cursor cursor, String columnName) {
        if (cursor.getColumnIndex(columnName) != -1) {
            return cursor.getInt(cursor.getColumnIndex(columnName));
        } else {
            return null;
        }
    }

    public static int getInt(Cursor cursor, String columnName) {
        if (cursor.getColumnIndex(columnName) != -1) {
            return cursor.getInt(cursor.getColumnIndex(columnName));
        } else {
            return 0;
        }
    }


    public static int fromBooleanToInt(boolean value) {
        return value ? 1 : 0;
    }

    public static Long getLong(Cursor cursor, String columnName) {
        if (cursor.getColumnIndex(columnName) != -1) {
            return cursor.getLong(cursor.getColumnIndex(columnName));
        } else {
            return null;
        }
    }

    /**
     * Converts the comma delimited string to an ArrayList
     * */
    public ArrayList<String> getArrayListFromString(Cursor cursor, String columnName) {
        ArrayList<String> returnValue = null;

        try {
            int columnIndex = cursor.getColumnIndex(columnName);

            if ( columnIndex != -1){
                String columnValue = cursor.getString(columnIndex);
                String[] values = columnValue == null ? new String[]{""} : columnValue.split(",");
                returnValue = new ArrayList<String>(Arrays.asList(values));
            }
        } catch (Exception exception) {
            // ignore if the parsing fails
            exception.printStackTrace();
        }

        return returnValue;
    }

    public static String getString(Cursor cursor, String columnName) {
        String value = null;
        if (cursor.getColumnIndex(columnName) != -1) {
            value = cursor.getString(cursor.getColumnIndex(columnName));
        } else {
            value = null;
        }
        return value;
    }

    public static void addBoolean(ContentValues cv, String key, Boolean value) {
        if (value != null) {
            cv.put(key, value);
        }
    }

    public static void addDouble(ContentValues cv, String key, Double value) {
        if (value != null) {
            cv.put(key, value);
        }
    }

    public static void addInteger(ContentValues cv, String key, Integer value) {
        if (value != null) {
            cv.put(key, value);
        }
    }

    public static void addLong(ContentValues cv, String key, Long value) {
        if (value != null) {
            cv.put(key, value);
        }
    }

    public static void addString(ContentValues cv, String key, String value) {
        if (value != null) {
            cv.put(key, value);
        }
    }

    public static ArrayList<? extends BaseEntity> getAllObjects(Class<? extends BaseEntity> classType, Uri uri) {
        return getAllObjects(classType, uri, null);
    }

    public static ArrayList<? extends BaseEntity> getAllObjects(Class<? extends BaseEntity> classType, Uri uri, String sortOrder) {
        ContentResolver contentResolver = AgoAppEngine.getContext().getContentResolver();

        Cursor userConversationLinkerCursor = contentResolver.query(uri,
                null, null, null,
                sortOrder);

        return BaseEntity.buildFromCursor(classType, userConversationLinkerCursor, true);
    }

    public static ArrayList<? extends BaseEntity> getObjectsFiltered(Class<? extends BaseEntity> classType, Uri uri, String where, String[] whereArgs, String sortOrder) {
        ContentResolver contentResolver = AgoAppEngine.getContext().getContentResolver();

        Cursor userConversationLinkerCursor = contentResolver.query(uri,
                null, where, whereArgs,
                sortOrder);

        return BaseEntity.buildFromCursor(classType, userConversationLinkerCursor, true);
    }

    public static void bulkInsertObjects(Class<? extends BaseEntity> classType, Uri uri, ArrayList<? extends BaseEntity> objectsToInsert) {
        ContentValues[] contentValues = BaseEntity.toContentValues(classType, objectsToInsert);
        AgoAppEngine.getContext().getContentResolver().bulkInsert(uri, contentValues);
    }

    public static void insertObject(Uri uri, BaseEntity object) {
        ContentResolver contentResolver = AgoAppEngine.getInstance().getContentResolver();
        ContentValues objectValues = BaseEntity.toContentValues(object.getClass(), object);

        if (objectValues.containsKey(ID)) {
            objectValues.remove(ID);
        }

        contentResolver.insert(uri, objectValues);
    }

    public static void insertObject(Uri uri, BaseEntity[] objects) {
        ContentResolver contentResolver = AgoAppEngine.getInstance().getContentResolver();

        for (BaseEntity imObject : objects) {
            ContentValues objectValues = BaseEntity.toContentValues(imObject.getClass(), imObject);

            if (objectValues.containsKey(ID)) {
                objectValues.remove(ID);
            }

            contentResolver.insert(uri, objectValues);
        }
    }

    public static int updateObject(Uri uri, BaseEntity object) {
        ContentResolver contentResolver = AgoAppEngine.getInstance().getContentResolver();
        ContentValues objectValues = BaseEntity.toContentValues(object.getClass(), object);

        if (objectValues.containsKey(ID)) {
            return contentResolver.update(uri,
                    objectValues,
                    ID + " = ?",
                    new String[] {
                            objectValues.getAsString(ID)
                    });
        }
        else
            return -1;
    }

    public static void insertOrUpdateObject(Uri uri, BaseEntity object) {
        if (object.id != null) {
            updateObject(uri, object);
        } else {
            insertObject(uri, object);
        }
    }

    public static void insertOrUpdateObject(Uri uri, BaseEntity object, ContentValues objectValues) {
        if (objectValues.containsKey(ID)) {
            updateObject(uri, object);
        } else {
            insertObject(uri, object);
        }
    }

    public abstract ArrayList<? extends BaseEntity> buildFromCursor(Cursor cursor);
    public abstract ContentValues toContentValues(BaseEntity objectToConvert);
    public abstract BaseEntity buildFromContentValues(ContentValues contentValues);

    public static Cursor getCursorAsSqlResult(String sql) {
        Cursor cursor = null;

        if (!TextUtils.isEmpty(sql)) {
            try {
                //IMLog.d("getCursorAsSqlResult.getCursorAsSqlResult_SQL", sql);
                SQLiteDatabase sqLiteDatabase = getReadableDatabase();
                cursor = sqLiteDatabase.rawQuery(sql, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cursor;
    }

    /*
     * Since the SQLiteStatement doesn't allow null values when bindString is used, replace the null with empty string
     * */
    public static void bindStringValue(SQLiteStatement mergeStatement, int columnIndex, String value) {
        mergeStatement.bindString(columnIndex, value == null ? "" : value);
    }

    public static SQLiteDatabase getReadableDatabase() {
        return 	AgoAppEngine.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase() {
        return 	AgoAppEngine.getWritableDatabase();
    }


    public static String getStringFromProperty(String value) {
        return value == null ? "" : value.trim();
    }

    public static String getStringFromProperty(Double value) {
        return value == null ? "" : value.toString();
    }


    public static int getIntegerFromBooleanProperty(boolean value) {
        return value ? 1 : 0;
    }

    public static int getIntegerFromProperty(Integer value) {
        return value == null ? 0 : value;
    }



}
