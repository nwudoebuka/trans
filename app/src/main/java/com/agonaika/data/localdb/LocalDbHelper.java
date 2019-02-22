package com.agonaika.data.localdb;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.text.format.Time;

import com.agonaika.data.AgoAppEngine;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteQueryBuilder;

public class LocalDbHelper extends ContentProvider {

    private static final String TAG = LocalDbHelper.class.getSimpleName();
    private ContentResolver mResolver;
    private AgoWorkSqlOpenHelper mOpenHelper = null;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final String AUTHORITY = "com.agonaika.mobile.provider";
    private static final String URI_STRING = "content://" + AUTHORITY;

    public static final Uri CONTENT_URI = Uri.parse(URI_STRING);
    public static final String TABLE_EMPLOYEE = "EMPLOYEE";


    @Override
    public boolean onCreate() {
        Context c = getContext();
        mResolver = c.getContentResolver();
        return true;
    }

    public void shutdown() {
        if (mOpenHelper != null) {
            mOpenHelper.close();
            mOpenHelper = null;
        }
    }

    @Override
    public String getType(Uri uri) {

        return "";
    }

    @Override
    public synchronized Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = AgoAppEngine.getReadableDatabase();
        Cursor cursor = null;
        String WHERE_FORMAT = "%1$s=\'%2$s\'";
        String id = uri.getPathSegments().get(uri.getPathSegments().size() - 1);
        int pathId = sUriMatcher.match(uri);

        try {
            switch (pathId) {
                case 1234:
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Uri " + uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return cursor;
    }

    @Override
    public synchronized Uri insert(@NonNull Uri uri, ContentValues initialValues) {
        Uri ret = null;

        // Get the database and run the query
        SQLiteDatabase db = AgoAppEngine.getWritableDatabase();

        long item_id = -1;
        boolean shouldNotify = true;
        int uri_matcher = sUriMatcher.match(uri);

        return ret;
    }

    @Override
    public synchronized int update(@NonNull Uri uri, ContentValues values, String where, String[] whereArgs) {
        int count = 0;

        // Get the database and run the query
        SQLiteDatabase db = AgoAppEngine.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        return count;
    }

    @Override
    public synchronized int delete(@NonNull Uri uri, String where, String[] whereArgs) {
        int count = 0;
        // Get the database and run the query
        SQLiteDatabase db = AgoAppEngine.getWritableDatabase();
        Cursor c = null;
        return count;
    }

    @Override
    public synchronized int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        mOpenHelper = AgoWorkSqlOpenHelper.getInstance();

        SQLiteDatabase db = AgoAppEngine.getWritableDatabase();

        db.beginTransaction();
        String table_name = "";
        int matcher = sUriMatcher.match(uri);

        db.setTransactionSuccessful();
        db.endTransaction();

        return 1;
    }
}
