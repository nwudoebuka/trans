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
