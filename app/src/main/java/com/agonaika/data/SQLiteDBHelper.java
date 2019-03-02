package com.agonaika.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Jerry on 1/12/2018.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private Context ctx;

    //
    private String createEmployeeTableSql = "";


    private String createUserTableSql = "";

    private boolean isUpgrade = false;

    public static final String EMPLOYEE_TABLE_NAME = "employee";

    public static final String USER_TABLE_NAME = "user";

    public static final String LOG_TAG_SQLITE_DB = "LOG_TAG_SQLITE_DB";

    static final String CREATE_DB_TABLE1 =
            " CREATE TABLE " + EMPLOYEE_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " grade TEXT NOT NULL);";
    /*
     *  context : Android activity context object.
     *  name : SQLite database name.
     *  factory : CursorFactory object, generally is null.
     *  version : SQLite database version.
     * */
    public SQLiteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        ctx = context;
    }

    /*
     *  This method execute create table sql command for sqlite database.
     *  It is invoked when SQLiteDBHelper instance is created.
     * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.buildCreateTableSql();
        sqLiteDatabase.execSQL(CREATE_DB_TABLE1);

        Toast.makeText(ctx, "Table " + EMPLOYEE_TABLE_NAME + " is created successfully. ", Toast.LENGTH_SHORT).show();

        // Create book category table only when sqlite upgrade.
        if(isUpgrade) {
            sqLiteDatabase.execSQL(createUserTableSql);
            Toast.makeText(ctx, "Table " + USER_TABLE_NAME + " is created successfully. ", Toast.LENGTH_SHORT).show();
        }
    }

    /* This method will be invoked when newVersion is bigger than oldVersion.*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        Toast.makeText(ctx, "SQLiteDBHelper onUpgrade() method is invoked.", Toast.LENGTH_SHORT).show();

        // Drop table first if exist.
        sqLiteDatabase.execSQL("drop table if exists " + EMPLOYEE_TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + USER_TABLE_NAME);

        if(newVersion > oldVersion)
        {
            this.isUpgrade = true;
        }

        this.onCreate(sqLiteDatabase);
    }

    // Build all the create table sql, because each app has it's own database,
    // so we manage all create table sql command in one method.
    private void buildCreateTableSql()
    {
        // Build create book table sql.
        StringBuffer employeeSqlBuf = new StringBuffer();

        // Create table sql.
        employeeSqlBuf.append("create table ");
        employeeSqlBuf.append(EMPLOYEE_TABLE_NAME);
        employeeSqlBuf.append("( id integer primary key autoincrement,");
        employeeSqlBuf.append(" name text,");
        employeeSqlBuf.append(" member text");



            createEmployeeTableSql = employeeSqlBuf.toString();


        // Build create category table sql.
        StringBuffer userSqlBuf = new StringBuffer();

        // Create table sql.
        userSqlBuf.append("create table ");
        userSqlBuf.append(USER_TABLE_NAME);
        userSqlBuf.append("( id integer primary key autoincrement,");
        userSqlBuf.append(" last_login text, )");
        userSqlBuf.append(" status text )");

        createUserTableSql = userSqlBuf.toString();
    }
}