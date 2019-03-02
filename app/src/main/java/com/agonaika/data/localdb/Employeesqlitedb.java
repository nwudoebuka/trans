package com.agonaika.data.localdb;


import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;

import static com.android.volley.VolleyLog.TAG;

public class Employeesqlitedb extends ContentProvider {
   public static final String PROVIDER_NAME = "com.agonaika.data.localdb";
    public static final String URL = "content://" + PROVIDER_NAME + "/employee";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String MEMBER = "member";

    private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

    static final int EMPLOYEE = 1;
    static final int EMPLOYEE_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "employee", EMPLOYEE);
        uriMatcher.addURI(PROVIDER_NAME, "employee/#", EMPLOYEE_ID);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "Agonaika";
    static final String EMPLOYEE_TABLE_NAME = "employee";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + EMPLOYEE_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " status TEXT NOT NULL);";

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  EMPLOYEE_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new student record
         */
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db;
        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(	EMPLOYEE_TABLE_NAME, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);

            Log.d("print", "insert: inserted");
            return _uri;
        }else{
            Log.d(TAG,"not inserted");
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection,String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(EMPLOYEE_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case EMPLOYEE:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;

            case EMPLOYEE_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = NAME;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case EMPLOYEE:
                count = db.delete(EMPLOYEE_TABLE_NAME, selection, selectionArgs);
                break;

            case EMPLOYEE_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( EMPLOYEE_TABLE_NAME, _ID +  " = " + id +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case EMPLOYEE:
                count = db.update(EMPLOYEE_TABLE_NAME, values, selection, selectionArgs);
                break;

            case EMPLOYEE_ID:
                count = db.update(EMPLOYEE_TABLE_NAME, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case EMPLOYEE:
                return "vnd.android.cursor.dir/vnd.com.agonaika.employee";
            /**
             * Get a particular student
             */
            case EMPLOYEE_ID:
                return "vnd.android.cursor.item/vnd.com.agonaika.employee";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}