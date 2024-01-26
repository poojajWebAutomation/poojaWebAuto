package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_DriverVerificationPage extends PageBase {

    public WebElement Title_DriverVerificationPage (boolean...ignoreException) { return findElement("//h4[contains(text(),'Driver verification')]", LocatorType.XPath,ignoreException); }

    //public WebElement Verify_Approve_DriverPic () { return getTextElement_DriverVerification("Driver Picture",true); }
    public WebElement Verify_DriverDetails(String field, boolean status, boolean...ignoreException) { return getTextElement_DriverVerification(field,status,ignoreException); }

    public WebElement Verify_Approve_DriverFirstName () { return getTextElement_DriverVerification("First Name",true); }

    public WebElement Verify_Approve_DriverLastName () { return getTextElement_DriverVerification("Last Name",true); }

    public WebElement Verify_Approve_DriverStreetAddress () { return getTextElement_DriverVerification("Street address",true); }

    public WebElement Verify_Approve_DriverCity () { return getTextElement_DriverVerification("City",true); }

    public WebElement Verify_Approve_DriverState () { return getTextElement_DriverVerification("State",true); }

    public WebElement Verify_Approve_DriverZip () { return getTextElement_DriverVerification("Zip code",true); }

    //public WebElement Verify_Approve_DriverSSN () { return findElement("//*[@id='btnok_8']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverBirthday () { return getTextElement_DriverVerification("Birthday",true) ;}

    public WebElement Verify_Approve_DriverPickupImages () { return getTextElement_DriverVerification("Pickup images",true);}

    public WebElement Verify_Approve_DriverPickupMake () { return getTextElement_DriverVerification("Pickup make",true);}

    public WebElement Verify_Approve_DriverPickupModel () { return getTextElement_DriverVerification("Pickup model",true);}

    public WebElement Verify_Approve_DriverPickupYear () { return getTextElement_DriverVerification("Pickup year",true); }

    public WebElement Verify_Approve_DriverPickupLicense () { return getTextElement_DriverVerification("Pickup license number",true); }

    public WebElement Verify_Approve_DriverLicenseImage () { return getTextElement_DriverVerification("License image",true); }

    public WebElement Verify_Approve_DriverLicenseNumber () { return getTextElement_DriverVerification("License number",true); }

    public WebElement Verify_Approve_DriverLicenseExpiration () { return getTextElement_DriverVerification("License expiration",true); }

    public WebElement Verify_Approve_DriverInsuranceImage () { return getTextElement_DriverVerification("Insurance image",true); }

    public WebElement Verify_Approve_DriverInsurationExpiration () { return getTextElement_DriverVerification("Insurance Expiration",true); }

    public WebElement Verify_Approve_DriverRoutingNumber () { return getTextElement_DriverVerification("Routing Number",true, true); }

    public WebElement Verify_Approve_DriverAccountNumber () { return getTextElement_DriverVerification("Account Number",true, true); }

    public WebElement Button_DriverApproveApplication () { return findElement("//*[@id='btnapprove']", LocatorType.XPath, true); }

    public WebElement Button_DriverConfirmApproval () { return findElement("//*[@id='btnapproveagree']", LocatorType.XPath); }

    public WebElement Verify_Reject_DriverPicture () { return getTextElement_DriverVerification("Driver Picture",false); }

    public WebElement Verify_Reject_FirstName () { return getTextElement_DriverVerification("First Name",false); }

    public WebElement Verify_Reject_LastName () { return getTextElement_DriverVerification("Last Name",false); }

    public WebElement Verify_Reject_StreetAddress () { return getTextElement_DriverVerification("Street address",false); }

    public WebElement Verify_Reject_City () { return getTextElement_DriverVerification("City",false); }

    public WebElement Verify_Reject_State () { return getTextElement_DriverVerification("State",false); }

    public WebElement Verify_Reject_ZipCode () { return getTextElement_DriverVerification("Zip code",false); }

    //public WebElement Verify_Reject_SSN () { return findElement("//*[@id='btnremove_8']", LocatorType.XPath); }

    public WebElement Verify_Reject_Birthday () { return getTextElement_DriverVerification("Birthday",false); }

    public WebElement Verify_Reject_DriverPickupImages () { return getTextElement_DriverVerification("Pickup images",false); }

    public WebElement Verify_Reject_PickupMake () { return getTextElement_DriverVerification("Pickup make",false); }

    public WebElement Verify_Reject_PickupModel () { return getTextElement_DriverVerification("Pickup model",false); }

    public WebElement Verify_Reject_PickupYear () { return getTextElement_DriverVerification("Pickup year",false); }

    public WebElement Verify_Reject_PickupLicenseNumber () { return getTextElement_DriverVerification("Pickup license number",false); }

    public WebElement Verify_Reject_LicenseImage () { return getTextElement_DriverVerification("License image",false); }

    public WebElement Verify_Reject_LicenseNumber () { return getTextElement_DriverVerification("License number",false); }

    public WebElement Verify_Reject_LicenseExpiration () { return getTextElement_DriverVerification("License expiration",false); }

    public WebElement Verify_Reject_InsuranceImage () { return getTextElement_DriverVerification("Insurance image",false); }

    public WebElement Verify_Reject_InsuranceExpiration () { return getTextElement_DriverVerification("Insurance Expiration",false); }

    public WebElement Verify_Reject_RoutingNumber () { return getTextElement_DriverVerification("Routing Number",false,true); }

    public WebElement Verify_Reject_AccountNumber () { return getTextElement_DriverVerification("Account Number",false, true); }

    public WebElement Status_Accepted (boolean...ignoreException) { return findElement("//td[text()='Driver Picture']/following-sibling::td[2]/div/input[@name ='AcceptedRejected']", LocatorType.XPath, ignoreException); }

    //public WebElement Textinput_ReasonforRejection_Birthday () { return getInputElement_DriverVerification("Birthday","AcceptedRejected"); }
    public WebElement Textinput_ReasonforReject_DriverDetails (String field, String var, boolean...ignoreException) { return getInputElement_DriverVerification(field,var,ignoreException); }
    public WebElement Textinput_ReasonforRejection_DriverPicture () { return getInputElement_DriverVerification("Driver Picture","AcceptedRejected"); }

    public WebElement Textinput_ReasonforRejection_PickupImages () { return getInputElement_DriverVerification("Pickup images","AcceptedRejected"); }

    public WebElement Button_DriverResentButton () { return findElement("//button[text()='Resend Application']", LocatorType.XPath, true); }

    public WebElement Button_DriverConfirmResend () { return findElement("//button[(text()='Yes')]", LocatorType.XPath); }

    public WebElement Link_RejectApplication () { return findElement("//a[text()='Reject Application']", LocatorType.XPath); }

    public WebElement Popup_RejectApplication () { return findElement("//*[@id='rejectApp']//p[2]", LocatorType.XPath); }

    public WebElement Button_DriverConfirmReject_Yes () { return findElement("//button[text()='Yes']", LocatorType.XPath); }

    public WebElement Button_DriverConfirmReject_No () { return findElement("//button[text()='No']", LocatorType.XPath); }

    public WebElement Textinput_ReasonforRejectDriverApplication () { return findElement("//input[@class='reject-reason form-control']", LocatorType.XPath); }

    public WebElement Button_Submit () { return findElement("//button[text()='Submit']", LocatorType.XPath); }

    public WebElement Validation_Message_PleaseAddRejectionReason () { return findElement("//*[@id='rejectreason-errormsg']", LocatorType.XPath); }

    public WebElement Button_Save () { return findElement("//button[text()='Save']", LocatorType.XPath); }

    public WebElement Button_SaveForDriver () { return findElement("//*[@id='btnSave']", LocatorType.XPath); }

    public WebElement Button_Cancel () { return findElement("//a[text()='cancel']", LocatorType.XPath); }

    public WebElement Popup_ConfirmCancelDriverVerificationApplication () { return findElement("//*[@id='cancelApp']//p[2]", LocatorType.XPath); }

    public WebElement Button_VerifyDOB () { return findElement("//td[text()='Birthday']/following-sibling::td/div/button[starts-with(@id,'btnok')]", LocatorType.XPath); }

   // public WebElement Button_VerifySSN () { return findElement("//td[text()='Social Security Number']/following-sibling::td/div/button[starts-with(@id,'btnok')]", LocatorType.XPath); }

    //public WebElement Textbox_SSNComment () { return findElement("//input[@id='DriverDetails_DriverVerification_DriverVerificationDetails_8__Comment']", LocatorType.XPath); }
}
