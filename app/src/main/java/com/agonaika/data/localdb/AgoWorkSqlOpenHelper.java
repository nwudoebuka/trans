package com.agonaika.data.localdb;

import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.support.annotation.Nullable;

import com.agonaika.utils.AgoLog;

import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class AgoWorkSqlOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "AgoWork.db";
    private static final String TAG = AgoWorkSqlOpenHelper.class.getSimpleName();

    public AgoWorkSqlOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        AgoLog.d(TAG, "onCreate");
        createTables(sqLiteDatabase);
        enableSecureDeletes(sqLiteDatabase);

    }

    private void enableSecureDeletes(SQLiteDatabase db) {
        Cursor pragmaCursor = null;
        pragmaCursor = db.rawQuery(" PRAGMA secure_delete = true ", null);
        if (pragmaCursor != null)
            pragmaCursor.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void createTables(SQLiteDatabase db) {

    }
}
