package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registerrequest {
    @Expose
    @SerializedName("memberCode") String memberCode;
    @Expose @SerializedName("email") String email;
    @Expose @SerializedName("pin") int pin;
    @Expose @SerializedName("password") String password;
    @Expose @SerializedName("lookupType") String lookupType;
    @Expose @SerializedName("lookupValue") String lookupValue;
    @Expose @SerializedName("firstName") String firstName;
    @Expose @SerializedName("lastName") String lastName;
    @Expose @SerializedName("isNewEmployee") boolean isNewEmployee;
    @Expose @SerializedName("mobilePhoneNumber") String mobilePhoneNumber;
    @Expose @SerializedName("mobileCarrierId") int mobileCarrierId;
    @Expose @SerializedName("prefContactAdjustment") String prefContactAdjustment;
    @Expose @SerializedName("prefContactApproval") String prefContactApproval;
    @Expose @SerializedName("prefContactPto") String prefContactPto;

    public Registerrequest(String memberCode, String email, int pin, String password, String lookupType, String lookupValue, String firstName, String lastName, boolean isNewEmployee, String mobilePhoneNumber, int mobileCarrierId, String prefContactAdjustment, String prefContactApproval, String prefContactPto) {
        this.memberCode = memberCode;
        this.email = email;
        this.pin = pin;
        this.password = password;
        this.lookupType = lookupType;
        this.lookupValue = lookupValue;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isNewEmployee = isNewEmployee;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.mobileCarrierId = mobileCarrierId;
        this.prefContactAdjustment = prefContactAdjustment;
        this.prefContactApproval = prefContactApproval;
        this.prefContactPto = prefContactPto;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLookupType() {
        return lookupType;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    public String getLookupValue() {
        return lookupValue;
    }

    public void setLookupValue(String lookupValue) {
        this.lookupValue = lookupValue;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getIsNewEmployee() {
        return isNewEmployee;
    }

    public void setIsNewEmployee(boolean isNewEmployee) {
        this.isNewEmployee = isNewEmployee;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public int getMobileCarrierId() {
        return mobileCarrierId;
    }

    public void setMobileCarrierId(int mobileCarrierId) {
        this.mobileCarrierId = mobileCarrierId;
    }

    public String getPrefContactAdjustment() {
        return prefContactAdjustment;
    }

    public void setPrefContactAdjustment(String prefContactAdjustment) {
        this.prefContactAdjustment = prefContactAdjustment;
    }

    public String getPrefContactApproval() {
        return prefContactApproval;
    }

    public void setPrefContactApproval(String prefContactApproval) {
        this.prefContactApproval = prefContactApproval;
    }

    public String getPrefContactPto() {
        return prefContactPto;
    }

    public void setPrefContactPto(String prefContactPto) {
        this.prefContactPto = prefContactPto;
    }

    //   "memberCode": "KUNATA",
//           "email": "ugochi@kunatalogic.com",
//           "pin": 1705,
//           "password": "Pa$$3word",
//           "lookupType": "EIN4",
//           "lookupValue": "1705",
//           "firstName": "Ugochi",
//           "lastName": "Amako",
//           "isNewEmployee": false,
//           "mobilePhoneNumber": "2345551743",
//           "mobileCarrierId": 1,
//           "prefContactAdjustment": "",
//           "prefContactApproval": "",
//           "prefContactPto": ""


}
