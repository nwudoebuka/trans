package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loginresponse {
    @Expose
    @SerializedName("employeename") String employeename;
    @Expose @SerializedName("employeestatus") String employeestatus;
    @Expose @SerializedName("member") String member;
    @Expose @SerializedName("phone") String phone;
    @Expose @SerializedName("status") String status;
    @Expose @SerializedName("last_login") String last_login;

    public Loginresponse(String employeename, String employeestatus, String member, String phone, String status, String last_login) {
        this.employeename = employeename;
        this.employeestatus = employeestatus;
        this.member = member;
        this.phone = phone;
        this.status = status;
        this.last_login = last_login;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmployeestatus() {
        return employeestatus;
    }

    public void setEmployeestatus(String employeestatus) {
        this.employeestatus = employeestatus;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }
}
