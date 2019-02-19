package com.agonaika.data.domain.employee;

import com.agonaika.data.domain.BaseEntity;
import com.agonaika.data.domain.DbBaseEntity;
import com.agonaika.data.localdb.dbobject.EmployeeDbo;

import java.io.Serializable;

public class Employee extends BaseEntity implements Serializable {

    public String BadgeNumber = "";
    public int Pin = 0;
    public String Initials = "";
    public String EmpData = "";
    public Boolean Sent;

    @Override
    public Class<? extends DbBaseEntity> getDbObjectClass() {
        return EmployeeDbo.class;
    }
}
