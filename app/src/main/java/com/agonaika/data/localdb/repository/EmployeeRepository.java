package com.agonaika.data.localdb.repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.agonaika.data.domain.BaseEntity;
import com.agonaika.data.domain.DbBaseEntity;

import java.util.ArrayList;

public class EmployeeRepository extends DbBaseEntity {

    @Override
    public ArrayList<? extends BaseEntity> buildFromCursor(Cursor cursor) {
        return null;
    }

    @Override
    public ContentValues toContentValues(BaseEntity objectToConvert) {
        return null;
    }

    @Override
    public BaseEntity buildFromContentValues(ContentValues contentValues) {
        return null;
    }
}
