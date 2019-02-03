package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registerresp {

    @Expose @SerializedName("employeeId") String employeeId;
    @Expose @SerializedName("memberId") String memberId;
    @Expose @SerializedName("affiliateId") String affiliateId;
    @Expose @SerializedName("siteId") String siteId;
    @Expose @SerializedName("userId") String userId;
    @Expose @SerializedName("memberCode") String memberCode;
    @Expose @SerializedName("memberName") String memberName;
    @Expose @SerializedName("siteName") String siteName;
    @Expose @SerializedName("affiliateName") String affiliateName;
    @Expose @SerializedName("firstName") String firstName;
    @Expose @SerializedName("lastName") String lastName;
    @Expose @SerializedName("ein") String ein;
    @Expose @SerializedName("emailConfirm") String emailConfirm;
    @Expose @SerializedName("mobileNumber") String mobileNumber;
    @Expose @SerializedName("mobileCarrierId") String mobileCarrierId;
    @Expose @SerializedName("fileNo") String fileNo;
    @Expose @SerializedName("geoAddress1") String geoAddress1;
    @Expose @SerializedName("geoAddress2") String geoAddress2;
    @Expose @SerializedName("city") String city;
    @Expose @SerializedName("geoState") String geoState;
    @Expose @SerializedName("postalCode") String postalCode;
    @Expose @SerializedName("lastSignIn") String lastSignIn;

//    "employeeId": 1,

    public Registerresp(String employeeId, String memberId, String affiliateId, String siteId, String userId, String memberCode, String memberName, String siteName, String affiliateName, String firstName, String lastName, String ein, String emailConfirm, String mobileNumber, String mobileCarrierId, String fileNo, String geoAddress1, String geoAddress2, String city, String geoState, String postalCode, String lastSignIn) {
        this.employeeId = employeeId;
        this.memberId = memberId;
        this.affiliateId = affiliateId;
        this.siteId = siteId;
        this.userId = userId;
        this.memberCode = memberCode;
        this.memberName = memberName;
        this.siteName = siteName;
        this.affiliateName = affiliateName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ein = ein;
        this.emailConfirm = emailConfirm;
        this.mobileNumber = mobileNumber;
        this.mobileCarrierId = mobileCarrierId;
        this.fileNo = fileNo;
        this.geoAddress1 = geoAddress1;
        this.geoAddress2 = geoAddress2;
        this.city = city;
        this.geoState = geoState;
        this.postalCode = postalCode;
        this.lastSignIn = lastSignIn;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(String affiliateId) {
        this.affiliateId = affiliateId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAffiliateName() {
        return affiliateName;
    }

    public void setAffiliateName(String affiliateName) {
        this.affiliateName = affiliateName;
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

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getEmailConfirm() {
        return emailConfirm;
    }

    public void setEmailConfirm(String emailConfirm) {
        this.emailConfirm = emailConfirm;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileCarrierId() {
        return mobileCarrierId;
    }

    public void setMobileCarrierId(String mobileCarrierId) {
        this.mobileCarrierId = mobileCarrierId;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getGeoAddress1() {
        return geoAddress1;
    }

    public void setGeoAddress1(String geoAddress1) {
        this.geoAddress1 = geoAddress1;
    }

    public String getGeoAddress2() {
        return geoAddress2;
    }

    public void setGeoAddress2(String geoAddress2) {
        this.geoAddress2 = geoAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGeoState() {
        return geoState;
    }

    public void setGeoState(String geoState) {
        this.geoState = geoState;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLastSignIn() {
        return lastSignIn;
    }

    public void setLastSignIn(String lastSignIn) {
        this.lastSignIn = lastSignIn;
    }
//            "memberId": 1,
//            "affiliateId": 1,
//            "siteId": null,
//            "userId": 1,
//            "memberCode": null,
//            "memberName": "KunataLogic Nigeria",
//            "siteName": null,
//            "affiliateName": "Agonaika Nigeria",
//            "firstName": "Ugochi",
//            "lastName": "Amako",
//            "ein": "742-22-1705",
//            "email": "",
//            "emailConfirm": null,
//            "mobileNumber": "2345551743",
//            "mobileCarrierId": 1,
//            "fileNo": null,
//            "geoAddress1": "21 Adeyemo Alakija Street",
//            "geoAddress2": null,
//            "city": "Victoria Island",
//            "geoState": "Lagos",
//            "postalCode": null,
//            "lastSignIn": "1970-01-01T00:00:00"

}
