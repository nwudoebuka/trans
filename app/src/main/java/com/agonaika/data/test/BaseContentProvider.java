package com.agonaika.data.test;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.agonaika.data.SQLiteDBHelper;

import java.util.HashMap;

import static com.agonaika.data.test.DatabaseHelper.EMPLOYEE_TABLE_NAME;

public class BaseContentProvider extends ContentProvider {
    public static final String PROVIDER_NAME = "com.agonaika.data.test.BaseContentProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/employee";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String STATUS = "status";

    private String DB_NAME = "Agonaika.db";

    private int DB_VERSION = 1;

    private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

    static final int EMPLOYEE = 1;
    static final int EMPLOYEE_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "employee", EMPLOYEE);
        uriMatcher.addURI(PROVIDER_NAME, "employee/#", EMPLOYEE_ID);
    }

    SQLiteDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHelper  = new SQLiteDBHelper(context, DB_NAME, null, DB_VERSION);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

       // db = dbHelper.getWritableDatabase();
       // return (db == null)? false:true;
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case EMPLOYEE:
                return "vnd.android.cursor.dir/vnd.com.agonaika.data..employee";
            /**
             * Get a particular student
             */
            case EMPLOYEE_ID:
                return "vnd.android.cursor.item/vnd.com.agonaika.employee";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowID = db.insert(EMPLOYEE_TABLE_NAME, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);

            Log.d("print", "insert: inserted");
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

}
