package com.agonaika.data.localdb;

import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.support.annotation.Nullable;

import com.agonaika.data.AgoAppEngine;
import com.agonaika.data.AgoPreferences;
import com.agonaika.data.localdb.dbobject.EmployeeDbo;
import com.agonaika.utils.AgoLog;

import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
//import net.sqlcipher.database.SQLiteCursor;

public class AgoWorkSqlOpenHelper extends SQLiteOpenHelper {

    private static AgoWorkSqlOpenHelper mInstance = null;
    private static String mDatabaseName = "";
    private static final int DB_VERSION = 1;
    private static final String DATABASE_NAME = "AgoWork.db";
    private static final String TAG = AgoWorkSqlOpenHelper.class.getSimpleName();

    private AgoWorkSqlOpenHelper(String databaseName) {
        super(AgoAppEngine.getContext(), databaseName, null, DB_VERSION);

        SQLiteDatabase.loadLibs(AgoAppEngine.getContext());
    }

    public static synchronized AgoWorkSqlOpenHelper getInstance() {
        if (mInstance == null) {
            if (!mDatabaseName.equals("")) {
                mInstance = new AgoWorkSqlOpenHelper(mDatabaseName);
            } else {
                String member = AgoPreferences.getString(AgoPreferences.LAST_LOGIN_MEMBER, "");
                String user = AgoPreferences.getString(AgoPreferences.LAST_LOGIN_USER, "");
                mDatabaseName = member + "_" + user + ".db";
                mInstance = new AgoWorkSqlOpenHelper(mDatabaseName);
            }
        }
        return mInstance;
    }

    public static synchronized void removeCurrentDatabase(Context context) {
        mInstance.close();
        context.deleteDatabase(mDatabaseName);
        //ResetDatabaseKeyInMemory; TODO
    }

    public static synchronized AgoWorkSqlOpenHelper get(String dbName) {
        if (mInstance != null) {
            if (mDatabaseName.equalsIgnoreCase(dbName)) {
                return mInstance;
            }

            mInstance.close();
            mInstance = null;
        }

        mInstance = new AgoWorkSqlOpenHelper(dbName);
        mDatabaseName = dbName;
        return mInstance;
    }

    public AgoWorkSqlOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        AgoLog.d(TAG, "onCreate");
        createTables(sqLiteDatabase);
        enableSecureDeletes(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void enableSecureDeletes(SQLiteDatabase db) {
        Cursor pragmaCursor = null;
        pragmaCursor = db.rawQuery(" PRAGMA secure_delete = true ", null);
        if (pragmaCursor != null)
            pragmaCursor.close();
    }

    private void createTables(SQLiteDatabase db) {
        createEmployeeTable(db);


        AgoLog.d(TAG, "Created database at path: " + db.getPath());
    }

        private void createEmployeeTable(SQLiteDatabase db) {
            StringBuilder createEmployee = new StringBuilder();
            createEmployee.append("CREATE TABLE ");
            createEmployee.append(LocalDbHelper.TABLE_EMPLOYEE);
            createEmployee.append(" (");
            createEmployee.append(EmployeeDbo._ID);
            createEmployee.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
            createEmployee.append(EmployeeDbo.BADGE_NUMBER);
            createEmployee.append(" TEXT, ");
            createEmployee.append(EmployeeDbo.PIN);
            createEmployee.append(" INTEGER DEFAULT(0), ");
            createEmployee.append(EmployeeDbo.EMP_DATA);
            createEmployee.append(" TEXT, ");
            createEmployee.append(EmployeeDbo.INITIALS);
            createEmployee.append(" TEXT, ");
            createEmployee.append(EmployeeDbo.WAS_SENT);
            createEmployee.append(" INTEGER NOT NULL DEFAULT(0), ");
            createEmployee.append(");");
            db.execSQL(createEmployee.toString());

    }
}
