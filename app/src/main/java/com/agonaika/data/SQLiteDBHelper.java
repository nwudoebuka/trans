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

    public static final String USER_SIGN_IN_TABLE_NAME = "usersignin";
    //public static final String MEMBER_TABLE_NAME = "member";

    public static final String AFFILIATE_TABLE_NAME = "affiliate";

    public static final String LOG_TAG_SQLITE_DB = "LOG_TAG_SQLITE_DB";

    static final String CREATE_DB_TABLE1 =
            " CREATE TABLE " + USER_SIGN_IN_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " userId TEXT NOT NULL, " +
                    " employeeId TEXT NOT NULL, " +
                    " firstName TEXT NOT NULL, " +
                    " lastName TEXT NOT NULL, " +
                    " email TEXT NOT NULL, " +
                    "phone TEXT NOT NULL, " +
                    "affiliateId TEXT NOT NULL, " +
                    "affiliateName TEXT NOT NULL, " +
                    "siteId TEXT NOT NULL, " +
                    "siteName TEXT NOT NULL, " +
                    "primaryDeptId TEXT NOT NULL, " +
                    "primaryDeptName TEXT NOT NULL, " +
                    "mostUsedDeptId TEXT NOT NULL, " +
                    "mostUsedDeptName TEXT NOT NULL, " +
                    "ein TEXT NOT NULL, " +
                    "clockStatus TEXT NOT NULL, " +
                    "phoneCarrier TEXT NOT NULL, " +
                    "photoFileName TEXT NOT NULL, " +
                    "isScheduled TEXT NOT NULL, " +
                    "isMobileOnlyEmployee TEXT NOT NULL, " +
                    "member TEXT NOT NULL, " +
                    "memberId TEXT NOT NULL, " +
                    "memberCode TEXT NOT NULL, " +
                    "memberName TEXT NOT NULL, " +
                    "displayName TEXT NOT NULL, " +
                    "memberType TEXT NOT NULL, " +
                    "webImageUrl TEXT NOT NULL, " +
                    "useLocationTimeEntry TEXT NOT NULL, " +
                    "enablePto TEXT NOT NULL, " +
                    "affiliates TEXT NOT NULL, " +
                    "config TEXT NOT NULL, " +
                    "syncIntervalMinutes TEXT NOT NULL, " +
                    "updateIntervalMinutes TEXT NOT NULL, " +
                    "allowPhotoCapture TEXT NOT NULL, " +
                    "reasons TEXT NOT NULL, " +
                    "depts TEXT NOT NULL, " +
                    "timeHistory TEXT NOT NULL);";


    static final String CREATE_DB_TABLE2 =
                    " CREATE TABLE " + AFFILIATE_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " affiliateId TEXT NOT NULL, " +
                    " memberId TEXT NOT NULL, " +
                    " affiliateName TEXT NOT NULL, " +
                    " displayName TEXT NOT NULL);";



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
        //this.buildCreateTableSql();
        sqLiteDatabase.execSQL(CREATE_DB_TABLE1);
        sqLiteDatabase.execSQL(CREATE_DB_TABLE2);

        Toast.makeText(ctx, "Table " + AFFILIATE_TABLE_NAME + " is created successfully. ", Toast.LENGTH_SHORT).show();

        // Create book category table only when sqlite upgrade.
        if(isUpgrade) {
            sqLiteDatabase.execSQL(CREATE_DB_TABLE1);
            sqLiteDatabase.execSQL(CREATE_DB_TABLE2);
            Toast.makeText(ctx, "Table " + AFFILIATE_TABLE_NAME + " is created successfully. ", Toast.LENGTH_SHORT).show();
        }
    }

    /* This method will be invoked when newVersion is bigger than oldVersion.*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        Toast.makeText(ctx, "SQLiteDBHelper onUpgrade() method is invoked.", Toast.LENGTH_SHORT).show();

        // Drop table first if exist.
        sqLiteDatabase.execSQL("drop table if exists " + USER_SIGN_IN_TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + AFFILIATE_TABLE_NAME);

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
        employeeSqlBuf.append(USER_SIGN_IN_TABLE_NAME);
        employeeSqlBuf.append("( id integer primary key autoincrement,");
        employeeSqlBuf.append(" name text,");
        employeeSqlBuf.append(" member text");



            createEmployeeTableSql = employeeSqlBuf.toString();


        // Build create category table sql.
        StringBuffer userSqlBuf = new StringBuffer();

        // Create table sql.
        userSqlBuf.append("create table ");
        userSqlBuf.append(AFFILIATE_TABLE_NAME);
        userSqlBuf.append("( id integer primary key autoincrement,");
        userSqlBuf.append(" last_login text, )");
        userSqlBuf.append(" status text )");

        createUserTableSql = userSqlBuf.toString();
    }
}