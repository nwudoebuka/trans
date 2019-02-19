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

    public static final String PATH = "employees";
    public static final Uri CONTENT_URI =
            LocalDbHelper.CONTENT_URI.buildUpon().appendPath(PATH).build();

    // =========================
    // Columns
    // =========================

    public static final String BADGE_NUMBER = "BadgeNumber";
    public static final Integer PIN = 0;
    public static final String INITIALS = "Initials";
    public static final String EMP_DATA = "EmpData";
    public static final String WAS_SENT = "Sent";


    @Override
    public ArrayList<? extends BaseEntity> buildFromCursor(Cursor cursor) {
        ArrayList<Employee> employees = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.id = getInteger(cursor, ID);
                employee.Sent = getBoolean(cursor, WAS_SENT);
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
