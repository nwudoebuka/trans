package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registerrequest {
    @Expose
    @SerializedName("memberCode") String memberCode;
    @Expose @SerializedName("email") String email;


    public Registerrequest(String memberCode, String email) {
        this.memberCode = memberCode;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }


}
