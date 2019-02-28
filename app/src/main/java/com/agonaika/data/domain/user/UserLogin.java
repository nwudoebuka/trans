package com.agonaika.data.domain.user;

import com.agonaika.data.domain.BaseEntity;
import com.agonaika.data.domain.DbBaseEntity;
import com.agonaika.data.localdb.dbobject.UserLoginDbo;

public class UserLogin extends BaseEntity {

    public String deviceId;
    public String deviceType;
    public String affiliateName;
    public String password;
    public String userId;
    public String agoUserId;


    public static UserLogin buildUserLogin() {

        UserLogin userLogin = new UserLogin();
        return userLogin;


    }

    @Override
    public Class<? extends DbBaseEntity> getDbObjectClass() { return UserLoginDbo.class;
    }
}
