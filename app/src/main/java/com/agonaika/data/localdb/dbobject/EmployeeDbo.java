package com.agonaika.data.localdb.dbobject;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.agonaika.data.domain.BaseEntity;
import com.agonaika.data.domain.DbBaseEntity;
import com.agonaika.data.domain.employee.Employee;
import com.agonaika.data.localdb.LocalDbHelper;

import java.util.ArrayList;

//import org.apache.commons.lang3.NotImplementedException;

public class EmployeeDbo extends DbBaseEntity {

    // ==================================================
    // Constants
    // ==================================================

    public static final String EMPLOYEE_PATH = "employees";
    public static final Uri CONTENT_URI =
            LocalDbHelper.CONTENT_URI.buildUpon().appendPath(EMPLOYEE_PATH).build();

    // =========================
    // Columns
    // =========================

    public static final String COL_BADGE_NUMBER = "BadgeNumber";
    public static final String COL_PIN = "Pin";
    public static final String COL_INITIALS = "Initials";
    public static final String COL_EMP_DATA = "EmpData";
    public static final String COL_WAS_SENT = "Sent";
    public static final String SQL_CREATE_TABLE = createEmployeeTableSql();
    public static final String TABLE_NAME = "EMPLOYEE";


    private static String createEmployeeTableSql() {
        StringBuilder createEmployee = new StringBuilder();
        createEmployee.append("CREATE TABLE ");
        createEmployee.append(TABLE_NAME);
        createEmployee.append(" (");
        createEmployee.append(_ID);
        createEmployee.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        createEmployee.append(COL_BADGE_NUMBER);
        createEmployee.append(" TEXT, ");
        createEmployee.append(COL_PIN);
        createEmployee.append(" INTEGER DEFAULT(0), ");
        createEmployee.append(COL_EMP_DATA);
        createEmployee.append(" TEXT, ");
        createEmployee.append(COL_INITIALS);
        createEmployee.append(" TEXT, ");
        createEmployee.append(COL_WAS_SENT);
        createEmployee.append(" INTEGER NOT NULL DEFAULT(0), ");
        createEmployee.append(");");

        return createEmployee.toString();
    }


    @Override
    public ArrayList<? extends BaseEntity> buildFromCursor(Cursor cursor) {
        ArrayList<Employee> employees = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.id = getInteger(cursor, ID);
                employee.Sent = getBoolean(cursor, COL_WAS_SENT);
                employees.add(employee);
            } while (cursor.moveToNext());
        }
        return employees;
    }

    @Override
    public BaseEntity buildFromContentValues(ContentValues contentValues) {
        return null;
    }

    @Override
    public ContentValues toContentValues(BaseEntity objectToConvert) {
        Employee employee = (Employee) objectToConvert;
        ContentValues cv = new ContentValues();
        return cv;
    }
}
