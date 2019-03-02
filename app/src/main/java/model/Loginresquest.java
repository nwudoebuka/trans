package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loginresquest {

    @Expose
    @SerializedName("email") String email;
    @Expose @SerializedName("password") String password;

    public Loginresquest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
