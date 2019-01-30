package api;



import java.util.List;
import java.util.Map;

import model.Registerrequest;
import model.Registerresp;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //to get the response as it is
//    @POST("api/registrations")
//    Call<ResponseBody> loginUser(@Body LoginRequest loginRequest);

    @POST("api/registrations")
    Call<Registerresp> registeruser(@Body Registerrequest reg);


    //@GET("api/v1/feature/android_version/")
//    Call<AppVersionResponse> getVersion();
//
//    @POST("api/v1/user/login_android")
//    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
//
//    @GET("api/v1/user/my_profile/")
//    Call<UserDataResponse> fetchUserProfile(@Header("Authorization") String token);
//
//    @POST("api/v1/user/register/")
//    Call<CreateNewAccountResponse> createNewAccount(@Body CreateNewAccountRequest createNewAccountRequest);
//
//    @POST("api/v1/feature/fast_save/")
//    Call<UserFastSaveResponse> sendPaymentRequest (@Header("Authorization") String token, @Body FastSaveAmountRequest deposit_amount);
//
//    @POST("api/v1/feature/bag_locker/")
//    Call<BagLockerResponse> sendBagLockerPayments (@Header("Authorization") String token, @Body BagLockerRequest deposit_amount);
//
//    @POST("api/v1/feature/locksavings/")
//    Call<LockSavingResponse> fetchBagLockerSavings (@Header("Authorization") String token, @Body LockSavingRequest lockSavingRequest);
//
//    @PATCH("api/v1/feature/auto_save/{autosave_id}/")
//    Call<ModifyAutoSaveResponse> dailyAutoSave(@Header("Authorization") String token, @Body ModifyAutoSaveRequest modifyAutoSaveRequest, @Path("autosave_id") String autosave_id);
//
//    @PATCH("api/v1/feature/auto_save/{autosave_id}/")
//    Call<ModifyAutoSaveResponse> weeklyAutoSave(@Header("Authorization") String token, @Body ModifyAutoSaveRequest modifyAutoSaveRequest, @Path("autosave_id") String autosave_id);
//
//    @PATCH("api/v1/feature/auto_save/{autosave_id}/")
//    Call<ModifyAutoSaveResponse> monthlyAutoSave(@Header("Authorization") String token, @Body ModifyAutoSaveRequest modifyAutoSaveRequest, @Path("autosave_id") String autosave_id);
//
//    @POST("api/v1/feature/referal_to_savings/")
//    Call<ServerResponse>  referralPool (@Header("Authorization") String token);
//
//    @GET("api/v1/bag/lock_mode")
//    Call<List<FundMediumResponse>> getMediumOfFund (@Header("Authorization") String token);
//
//    @GET("api/v1/bag/settings")
//    Call<List<LockPeriodResponse>> getLockPeriod (@Header("Authorization") String token);
//
//    @GET("api/v1/user/referrals/")
//    Call<ReferralResponse> getReferrals (@Header("Authorization") String token);
//
//    //to with draw from savings balance
//    @POST("api/v1/transaction/withdraw_funds/")
//    Call<WithdrawResponse> withdrawSavingsFund (@Header("Authorization") String token, @Body WithdrawalRequest withdrawal_amount);
//
//    //to with draw from withdrawable balance
//    @POST("api/v1/transaction/withdraw_funds_withdrawable/")
//    Call<WithdrawResponse> getWithdrawableFund (@Header("Authorization") String token, @Body WithdrawalRequest withdrawal_amount);
//
////    @GET("api/v1/transaction/withdrawal/")
////    Call<WithdrawHistoryResponse> getWithdrawalDetails (@Header("Authorization") String token);
//
////    @GET("api/v1/bank_detail/user_bank/")
////    Call<UserBankListResponse> checkDefaultBank (@Header("Authorization") String token);
//
//    @GET("api/v1/bank_detail/user_bank/")
//    Call<UserBankListResponse> getUserBankLists (@Header("Authorization") String token);
//
//
//    @POST("api/v1/bank_detail/add_new_card/")
//    Call<AddNewCardResponse> addNewCard (@Header("Authorization") String token, @Body AddNewCardRequest addNewCard);
//
////    @GET("api/v1/feature/trans_details/")
////    Call<TransactionDetailsResponse> getTransactionDetails (@Header("Authorization") String token);
//
//    //Call to add new bank and card details
//    @POST("api/v1/bank_detail/user_bank/")
//    Call<AddNewBankResponse> addNewBank (@Header("Authorization") String token, @Body AddNewBankRequest addDetails );
//
//    @GET("api/v1/feature/auto_save/")
//    Call<AutoSaveListResponse> getAutoSaveListDetails(@Header("Authorization") String token);
//
//    @GET("api/v1/bank_detail/banks")
//    Call<List<BankListResponse>> getBankList (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/frequency")
//    Call<List<FrequencyResponse>> getFrequency (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/month")
//    Call<List<MonthResponse>> getMonth (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/day_of_month")
//    Call<List<DayOfMonthResponse>> getDayOfMonth (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/day")
//    Call<List<DayResponse>> getDay (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/time_field")
//    Call<List<TimeResponse>> getTime (@Header("Authorization") String token);
//
//    @GET("api/v1/withdrawal_setting/user_set_day/")
//    Call<UserSetDayListResponse> getUserWithdrawalSetDayList (@Header("Authorization") String token);
//
//    @GET("api/v1/withdrawal_setting/freedays/")
//    Call<WithdrawalDaysResponse> getWithdrawalFreeDays (@Header("Authorization") String token);
//
//    @PUT("api/v1/user/change_password")
//    Call<ChangePasswordResponse> changePassword (@Header("Authorization") String token, @Body ChangePasswordRequest changePassword);
//
//    @POST("api/v1/user/check_pin/")
//    Call<CheckPinResponse> checkPin (@Header("Authorization") String token, @Body CheckPinRequest checkPinRequest);
//
//    @POST("api/v1/user/set_pin/")
//    Call<SetPinResponse> setPin (@Header("Authorization") String token, @Body SetPinRequest checkPinRequest);
//
//    @POST("api/v1/user/password_reset_invite/")
//    Call<ResetPasswordInviteResponse> resetPasswordInvite (@Header("Authorization") String token, @Body ResetPasswordInviteRequest resetPasswordInviteRequest);
//
//    @POST("api/v1/user/password_reset/")
//    Call<ResetPasswordResponse> resetPassword (@Header("Authorization") String token, @Body ResetPasswordRequest resetPasswordRequest);
//
//    @POST("api/v1/transaction/validate_otp_activation/")
//    Call<PayOtpResponse> makePaymentOtp (@Header("Authorization") String token, @Body PayOtpRequest payOtpRequest);
//
//    @POST("api/v1/transaction/pay_with_interswitch/")
//    Call<PayResponse> makePayment (@Header("Authorization") String token, @Body PayRequest payRequest);
//
//    @POST("api/v1/transaction/isw_confirm/")
//    Call<PayOtpResponse> iswConfirm (@Header("Authorization") String token, @Body IswConfirmRequest iswConfirmRequest);
//
//    @POST("api/v1/transaction/pay_with_interswitch2/")
//    Call<PayResponse> makeFastSavePayment (@Header("Authorization") String token, @Body PayRequest payRequest);
//
//    @POST("api/v1/bank_detail/validate_bank/")
//    Call<ValidateAccountResponse> validateBankAccount(@Header("Authorization") String token, @Body ValidateBankRequest validateBankRequest);
//
//    @POST("api/v1/bank_detail/change_bank/")
//    Call<ChangeBankResponse> changeBank (@Header("Authorization") String token,@Body  ChangeBankRequest changeBankRequest);
//
//
//    @POST("api/v1/transaction/target_to_savings/")
//    Call<TargetToSavingsResponse> sendTargetToSavings (@Header("Authorization") String  token, @Body TargetToSavingsRequest targetToSavingsRequest);
//
//    @POST("api/v1/transaction/target_withdraw_funds/")
//    Call<TargetToBankAccountResponse> sendTargetToBank (@Header("Authorization") String  token, @Body TargetToBankAccountRequest targetToBankAccountRequest);
//
//    @GET("api/v1/bank_detail/user_card_detail/")
//    Call<UserCardResponse> getUserCardsDetails (@Header("Authorization") String token);
//
//    @POST("api/v1/notification/message_to_user_detail/")
//    Call<NotificationUserDetailResponse> displayNotificationUserDetail(@Header("Authorization") String token, @Body NotificationUserDetailRequest notificationUserDetailRequest);
//
//    @POST("api/v1/transaction/end_target_session/")
//    Call<EndTargetSessionResponse> endTargetSession (@Header("Authorization") String token, @Body EndTargetSessionRequest endTargetSessionRequest);
//
//    @POST("api/v1/transaction/target_withdraw_funds/")
//    Call<WithdrawTargetFundsResponse> withdrawTargetFunds (@Header("Authorization") String token, @Body WithdrawTargetFundsRequest withdrawTargetFundsRequest);
//
//    @POST("api/v1/transaction/baglocker_withdraw_funds/")
//    Call<WithdrawBaglockerFundsResponse> withdrawBaglockerFunds (@Header("Authorization") String token, @Body WithdrawBaglockerFundsRequest withdrawBaglockerFundsRequest);
//
//
//    @POST("api/v1/feature/activate_user_detail/")
//    Call<ActivateUserDetailResponse> getActivateUserDetail (@Header("Authorization") String token, @Body ActivateUserDetailRequest activateUserDetailRequest);
//
//    @POST("api/v1/feature/activate_user/")
//    Call<ActivateUserResponse> activateUser (@Header("Authorization") String token, @Body ActivateUserRequest activateUserRequest);
//
//    @POST("api/v1/target_saving_v2/target_calculator/")
//    Call<TargetSaveCalculatorResponse> calculateTargetSave (@Header("Authorization") String token, @Body TargetSaveCalculatorRequest targetSaveCalculatorRequest);
//
//    @POST("api/v1/target_saving_v2/target_savings/")
//    Call<TargetSaveResponse> setTargetSaveMoney (@Header("Authorization") String  token, @Body TargetSaveRequest targetSaveRequest);
//
//    @GET("api/v1/target_saving_v2/target_savings/")
//    Call<TargetSaveHistoryResponse> getTargetSaveList (@Header("Authorization") String token);
//
//    @GET("api/v1/general/view_detail/")
//    Call<WithdrawCalculatorResponse> getWithdrawCalculatorData (@Header("Authorization") String token);
//
//    @POST("api/v1/bank_detail/change_card/")
//    Call<MakeCardDefaultResponse> makeCardDefault(@Header("Authorization") String token, @Body MakeCardDefaultRequest makeCardDefaultRequest);
//
//    @POST("api/v1/bank_detail/validate_otp/")
//    Call<ValidateOtpResponse> validateOtp(@Header("Authorization") String token, @Body ValidateOtpRequest validateOtpRequest);
//
//    @GET("api/v1/feature/bag_locker/")
//    Call<BagLockerHistoryResponse> getBagLockerHistoryList (@Header("Authorization") String token);
//
//    @POST("api/v1/withdrawal_setting/update_set_day/")
//    Call<UserUpdateFreeDaysListResponse> updateWithDrawalFreeDay (@Header("Authorization") String token, @Body UserUpdateFreeDaysListRequest userUpdateFreeDaysListRequest );
//
//    //clique bag api
//    @GET("api/v1/clique_bag/archived_cliques/")
//    Call<ArchivedCliquesResponse> getArchivedCliquesList (@Header("Authorization") String token);
//
//    @GET("api/v1/clique_bag/check_status/")
//    Call<CheckCliqueBagStatusResponse> getUserCliqueBagStatus (@Header("Authorization") String token);
//
//    @GET("api/v1/clique_bag/clique_invites/")
//    Call<CliqueInvitesResponse> getUserCliqueInvites (@Header("Authorization") String token);
//
//    @POST("api/v1/clique_bag/create_clique/")
//    Call<CreateCliqueResponse> createClique (@Header("Authorization") String token, @Body CreateCliqueRequest createCliqueRequest);
//
//    @POST("api/v1/clique_bag/delete_invite/")
//    Call<DeleteInviteResponse> deleteInvite (@Header("Authorization") String token, @Body DeleteInviteRequest deleteInviteRequest);
//
//    @POST("api/v1/clique_bag/temp_invite/")
//    Call<TempInviteResponse> sendInvite (@Header("Authorization") String token, @Body TempInviteRequest tempInviteRequest);
//
//    @POST("api/v1/clique_bag/transfer_admin/")
//    Call<TransferAdminResponse> transferAdmin (@Header("Authorization") String token, @Body TransferAdminRequest transferAdminRequest);
//
//    @POST("api/v1/clique_bag/start_session/")
//    Call<StartSessionResponse> startCliqueSession (@Header("Authorization") String token);
//
//    @POST("api/v1/clique_bag/swap_members/")
//    Call<SwapMembersResponse> swapMembers (@Header("Authorization") String token, @Body SwapMembersRequest swapMembersRequest);
//
//
//    @Multipart
//    @POST("api/v1/user/update_kyc/")
//    Call<UpdateKycResponse> updateKyc (@Header("Authorization") String token,
//                                       @Part("next_of_kin_name")RequestBody next_of_kin_name,
//                                       @Part("next_of_kin_relationship")RequestBody next_of_kin_relationship,
//                                       @Part("next_of_kin_email")RequestBody next_of_kin_email,
//                                       @Part("next_of_kin_mobile_number")RequestBody next_of_kin_mobile_number,
//                                       @Part("next_of_kin_address")RequestBody next_of_kin_address,
//                                       @Part("employment_status")RequestBody employment_status,
//                                       @Part("relationship_status")RequestBody relationship_status,
//                                       @Part("income_range")RequestBody income_range,
//                                       @Part("identification_type")RequestBody identification_type,
//                                       @Part("identification_number")RequestBody identification_number,
//                                       @Part("issue_date")RequestBody issue_date,
//                                       @Part("expiry_date")RequestBody expiry_date,
//                                       @Part("bvn_number")RequestBody bvn_number,
//                                       @Part("security_question")RequestBody security_question,
//                                       @Part("security_answer")RequestBody security_answer,
//                                       @Part List<MultipartBody.Part> file);
//
//
//    @POST("api/v1/user/update_kyc/")
//    Call<UpdateKycResponse> updateKyc (@Header("Authorization") String token, @Body UpdateKycRequest updateKycRequest);
//
//    @Multipart
//    @POST("api/v1/user/update_kyc/")
//    Call<UpdateKycResponse> updateKyc (@Header("Authorization") String token, @Part MultipartBody.Part file); //@Part List<MultipartBody.Part> file
//
//
//    @GET("api/v1/clique_bag/temp_invite/")
//    Call<TempInviteListResponse> getTempInviteList(@Header("Authorization") String token);
//
//    @GET("api/v1/clique_bag/details/")
//    Call<CliqueBagDetailsResponse> getCliqueBagDetails (@Header("Authorization") String token);
//
//    @POST("api/v1/clique_bag/accept_invite/")
//    Call<AcceptInviteResponse> acceptInvite (@Header("Authorization") String token, @Body AcceptInviteRequest acceptInviteRequest);
//
//    @POST("api/v1/user/update_profile/")
//    Call<UpdateUserResponse> updateUserProfile (@Header("Authorization") String token, @Body UpdateUserRequest updateUserRequest);
//
//    @Multipart
//    @POST("api/v1/user/update_profile/")
//    Call<UpdateUserResponse> updateUserProfile (@Header("Authorization") String token, @Part MultipartBody.Part file);
//
//    @POST("api/v1/user/update_autosave/")
//    Call<GeneralOptionResponse> toggleAutoSave(@Header("Authorization") String token, @Body AutoSaveRequest AutoSaveValue);
//
//    @POST("api/v1/user/update_interestflow/")
//    Call<GeneralOptionResponse> changeInterestDuration(@Header("Authorization") String token, @Body EnableInterestSavingsRequest enableInterestDailyRequest);
//
//    @POST("api/v1/user/update_push_notification/")
//    Call<GeneralOptionResponse> togglePushNotification (@Header("Authorization") String token, @Body EnablePushNotificationRequest enablePushNotificationRequest);
//
//    @POST("api/v1/user/update_enable_sms/")
//    Call<GeneralOptionResponse> toggleSMS (@Header("Authorization") String token, @Body EnableSMSRequest enableSMSRequest);
//
//    @POST("api/v1/user/update_email_service/")
//    Call<GeneralOptionResponse> toggleEmailService (@Header("Authorization") String token, @Body EnableEmailServiceRequest enableEmailServiceRequest);
//
//    @POST("api/v1/user/update_disable_interest/")
//    Call<GeneralOptionResponse> toggleEnableInterest (@Header("Authorization") String token, @Body EnableInterestRequest enableInterestValue);
//
//    @POST("api/v1/bank_detail/remove_card/")
//    Call<RemoveCardResponse> removeCard (@Header("Authorization") String token, @Body RemoveCardRequest removeCardRequest);
//
//    // @Multipart
//    @POST("api/v1/prom/request_card/")
//    Call<RequestCardResponse> requestCard (@Header("Authorization") String token, @Body RequestCardRequest requestCardRequest);
//
//    @GET("api/v1/prom/card_price")
//    Call<CardPriceResponse> getCardPrice (@Header("Authorization") String token);
//
//    @POST("api/v1/user/check_security_answer/")
//    Call<SecurityAnswerResponse> answerSecurityQuestion (@Header("Authorization") String token, @Body SecurityAnswerRequest securityAnswerRequest);
//
//    @POST("api/v1/user/change_set_pin/")
//    Call<ChangePinResponse> changePin(@Header("Authorization") String token, @Body ChangePinRequest changePinRequest);
//
//
//    /* using RxJava*/
//    @GET("api/v1/user/my_profile/")
//    Single<UserDataResponse> fetchUserProfileRx(@Header("Authorization") String token);
//
//    @GET("api/v1/feature/auto_save/")
//    Single<AutoSaveListResponse> fetchAutoSaveListDetailsRx(@Header("Authorization") String token);
//
//    @GET("api/v1/target_saving_v2/target_savings/")
//    Single<TargetSaveHistoryResponse> getTargetSaveListRx (@Header("Authorization") String token);
//
//    @GET("api/v1/feature/fast_save/")
//    Single<FastSaveHistoryResponse> getFastSaveList (@Header("Authorization") String token);
//
//    @GET("api/v1/feature/bag_locker/")
//    Single<BagLockerHistoryResponse> getBagLockerList (@Header("Authorization") String token);
//
//    @GET("api/v1/transaction/withdrawal/")
//    Single<WithdrawHistoryResponse> getWithdrawalHistory(@Header("Authorization") String token);
//
//    @GET("api/v1/bag/lock_mode")
//    Single<List<FundMediumResponse>> getMediumOfFundRx(@Header("Authorization") String token);
//
//    @GET("api/v1/bag/settings")
//    Single<List<LockPeriodResponse>> getLockPeriodRx(@Header("Authorization") String token);
//
//    @GET("api/v1/user/referrals/")
//    Single<ReferralResponse> getReferralsRx (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/frequency")
//    Single<List<FrequencyResponse>> getFrequencyRx (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/month")
//    Single<List<MonthResponse>> getMonthRx (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/day_of_month")
//    Single<List<DayOfMonthResponse>> getDayOfMonthRx (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/day")
//    Single<List<DayResponse>> getDayRx (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/time_field")
//    Single<List<TimeResponse>> getTimeRx (@Header("Authorization") String token);
//
//    @GET("api/v1/bank_detail/user_bank/")
//    Single<UserBankListResponse> getUserBankListRx(@Header("Authorization") String token);
//
//    @GET("api/v1/bank_detail/banks")
//    Single<List<BankListResponse>> getBankListRx (@Header("Authorization") String token);
//
//    @GET("api/v1/bank_detail/account_type")
//    Single<List<AccountTypeResponse>> getAccountTypeOptionsRx (@Header("Authorization") String token);
//
//    @GET("api/v1/feature/trans_details/")
//    Single<TransactionDetailsResponse> getTransactionDetailsRx (@Header("Authorization") String token);
//
//    @GET("api/v1/bank_detail/user_card_detail/")
//    Single<UserCardResponse> getUserCardsDetailsRx (@Header("Authorization") String token);
//
//    @GET("api/v1/notification/message_to_user/")
//    Single<MessageToUserNotificationResponse> getUserNotificationListRx (@Header("Authorization") String token);
//
//    @GET("api/v1/settings/reason_for_saving")
//    Single<List<ReasonForSavingResponse>> getReasonForSavingsListRx (@Header("Authorization") String token);
//
//    @GET("api/v1/target_saving_v2/duration")
//    Single<List<TargetSavingDurationResponse>> getDurationListRx2 (@Header("Authorization") String token);
//
//    @GET("api/v1/target_saving_v2/day")
//    Single<List<TargetSavingDaysResponse2>> getTargetSaveDaysList2 (@Header("Authorization") String token);
//
//    @GET("api/v1/target_saving_v2/week")
//    Single<List<TargetSavingWeeksResponse2>> getTargetSaveWeeksList2 (@Header("Authorization") String token);
//
//    @GET("api/v1/target_saving_v2/month")
//    Single<List<TargetSavingMonthsResponse2>> getTargetSaveMonthsList2 (@Header("Authorization") String token);
//
//    @GET("api/v1/withdrawal_setting/freedays/")
//    Single<WithdrawalDaysResponse> getWithdrawalFreeDaysRx (@Header("Authorization") String token);
//
//    @GET("api/v1/withdrawal_setting/user_set_day/")
//    Single<UserSetDayListResponse> getUserWithdrawalSetDayListRx (@Header("Authorization") String token);
//
//    //kyc stuffs
//
//    @GET("api/v1/kyc/employement_view")
//    Single<List<KycEmploymentViewResponse>> getKycEmploymentViewRx (@Header("Authorization") String token);
//
//    @GET("api/v1/kyc/identification_view")
//    Single<List<KycIdentificationViewResponse>> getKycIdentificationViewRx (@Header("Authorization") String token);
//
//    @GET("api/v1/kyc/income_view")
//    Single<List<KycIncomeViewResponse>> getKycIncomeViewRx (@Header("Authorization") String token);
//
//    @GET("api/v1/kyc/relationship_view")
//    Single<List<KycRelationshipViewResponse>> getKycRelationshipViewRx (@Header("Authorization") String token);
//
//    @GET("api/v1/kyc/security_question_view")
//    Single<List<KycSecurityQuestionResponse>> getKycSecurityQuestionRx (@Header("Authorization") String token);
//
//
//    //clique bag api rx
//    @GET("api/v1/clique_bag/archived_cliques/")
//    Single<ArchivedCliquesResponse> getArchivedCliquesListRx (@Header("Authorization") String token);
//
//    @GET("api/v1/clique_bag/check_status/")
//    Single<CheckCliqueBagStatusResponse> getUserCliqueBagStatusRx (@Header("Authorization") String token);
//
//    @GET("api/v1/clique_bag/clique_invites/")
//    Single<CliqueInvitesResponse> getUserCliqueInvitesRx (@Header("Authorization") String token);
//
//    @GET("api/v1/clique_bag/temp_invite/")
//    Single<TempInviteListResponse> getTempInviteListRx(@Header("Authorization") String token);
//
//    @GET("api/v1/clique_bag/details/")
//    Single<CliqueBagDetailsResponse> getCliqueBagDetailsRx (@Header("Authorization") String token);
//
//    @GET("api/v1/prom/card_price")
//    Single<CardPriceResponse> getCardPriceRx (@Header("Authorization") String token);
//
//    @GET("api/v1/feature/activate_user/")
//    Single<ActivatedFriendsResponse> getActivatedFriendsListRx (@Header("Authorization") String token);
//
//    /****************************************************************************************************************************************/
//    //Version 2.0
//
//    @GET("api/v1/feature/android_version/")
//    Observable<AppVersionResponse> getVersionOb();
//
//    @GET("api/v1/user/my_profile/")
//    Observable<UserDataResponse> fetchUserProfileOb(@Header("Authorization") String token);
//
//    @POST("api/v1/user/register/")
//    Observable<CreateNewAccountResponse> createNewAccountOb(@Body CreateNewAccountRequest createNewAccountRequest);

}

