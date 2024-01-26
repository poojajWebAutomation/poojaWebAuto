package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SignupPage extends PageBase {
    public WebElement Header_SignUp(boolean ...ignoreException) { return findElement("//android.widget.TextView[@text='SIGN UP']", LocatorType.XPath,ignoreException); }
        public WebElement GenericHeader(boolean ...ignoreException) { return findElement("//android.view.View[@resource-id='com.bungii.customer:id/toolbar' or 'com.bungii.customer:id/toolbarSignUp']/android.widget.TextView[last()] | //android.view.ViewGroup[@resource-id='com.bungii.customer:id/toolbar' or 'com.bungii.customer:id/toolbarSignUp']/android.widget.TextView[last()]", LocatorType.XPath,ignoreException); }
        public WebElement Header_HomePage(boolean ...ignoreException) { return findElement("//android.view.View[@resource-id='com.bungii.customer:id/toolbar' or 'com.bungii.customer:id/toolbarSignUp']/android.widget.TextView[last()] | //android.view.ViewGroup[@resource-id='com.bungii.customer:id/toolbar' or 'com.bungii.customer:id/toolbarSignUp']/android.widget.TextView[last()]", LocatorType.XPath,ignoreException); }


        public WebElement Message_Error(boolean ...ignoreException) { return findElement("android:id/message", LocatorType.Id,ignoreException); }
        public WebElement Button_Retry(boolean ...ignoreException) { return findElement("android:id/button1", LocatorType.Id,ignoreException); }

    // Login link
    public WebElement Link_Login(boolean ...ignoreException) { return findElement("com.bungii.customer:id/title_login", LocatorType.Id,ignoreException); }

    //-------------Signup fields---------------------------------------------------------------------------
    public WebElement TextField_FirstName() { return findElement("com.bungii.customer:id/signup_field_first_nam", LocatorType.Id); }

    public WebElement TextField_LastName() { return findElement("com.bungii.customer:id/signup_field_last_name", LocatorType.Id); }

   public WebElement TextField_Email() { return findElement("com.bungii.customer:id/signup_field_email", LocatorType.Id); }
   public WebElement CheckBox_Promo() { return findElement("com.bungii.customer:id/checkbox_signup_promo_referral", LocatorType.Id); }

 //   public WebElement TextField_Email() { return findElement("com.bungii.customer:id/signup_textinputlayout_email", LocatorType.Id); }


    public WebElement TextField_Phonenumber() { return findElement("com.bungii.customer:id/signup_field_phone", LocatorType.Id); }

    public WebElement TextField_Password() { return findElement("com.bungii.customer:id/signup_field_password", LocatorType.Id); }

    public WebElement TextField_Referral() { return findElement("com.bungii.customer:id/signup_field_prmo_code", LocatorType.Id); }

    public WebElement Select_ReferralSource() { return findElement("com.bungii.customer:id/signup_chevron_right", LocatorType.Id); }

    public WebElement Option_ReferralSource() { return findElement("//android.widget.CheckedTextView[@text='Other']", LocatorType.XPath); }

    public WebElement Link_ReferralSourceDone() { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Text_ReferralSource() { return findElement("com.bungii.customer:id/signup_textview_hear_about_us_choice", LocatorType.Id); }

    public WebElement Button_Signup(boolean ...ignoreException) { return findElement("com.bungii.customer:id/signupGlobalButton", LocatorType.Id,ignoreException); }

    public WebElement Popup_ReferralCode() { return findElement("com.bungii.customer:id/buttonPanel", LocatorType.Id); }

    public WebElement Button_NoReferralConfirm() { return findElement("android:id/button2", LocatorType.Id); }

    public WebElement Button_NoReferralYes() { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Snackbar_Error() { return findElement("com.bungii.customer:id/snackbar_text", LocatorType.Id); }

    public WebElement Text_ReferralSourceAdded() { return findElement("com.bungii.customer:id/signup_textview_hear_about_us_choice", LocatorType.Id); }

    //--------------Sign up fields error messages-----------------------------------------------------------
  //  public WebElement Cust_Signup_Error_Email() { return findElement("//*[@resource-id='com.bungii.customer:id/signup_field_email']/parent::*/following-sibling::android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath); }
   // public WebElement Cust_Signup_Error_Phone() { return findElement("//*[@resource-id='com.bungii.customer:id/signup_field_phone']/parent::*/following-sibling::android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath); }

  //  public WebElement Cust_Signup_Error_Password() { return findElement("//*[@resource-id='com.bungii.customer:id/signup_field_password']/parent::*/following-sibling::android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath); }

    public WebElement Cust_Signup_Error_Email() { return findElement("//*[@resource-id='com.bungii.customer:id/signup_field_email']/parent::*/following-sibling::android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView", LocatorType.XPath); }

    public WebElement Cust_Signup_Error_Phone() { return findElement("//*[@resource-id='com.bungii.customer:id/signup_field_phone']/parent::*/following-sibling::android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView", LocatorType.XPath); }

    public WebElement Cust_Signup_Error_Password() { return findElement("//*[@resource-id='com.bungii.customer:id/signup_field_password']/parent::*/following-sibling::android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView", LocatorType.XPath); }

    public WebElement Cust_Signup_Error_InactivePromo() { return findElement("//android.widget.TextView[@text='The added promo code is not active. Would you like to enter a new one?']", LocatorType.XPath); }
    //--------------Verification page elements---------------------------------------------------------------

    public WebElement Textfield_SMSCode() { return findElement("com.bungii.customer:id/field_sms_code", LocatorType.Id); }

    public WebElement Button_VerifyContinue() { return findElement("com.bungii.customer:id/smsVerifyContinue", LocatorType.Id); }

    public WebElement Link_Resend() { return findElement("//android.widget.Button[@text='RESEND CODE']", LocatorType.XPath); }

    public WebElement Title_Verification() { return findElement("//android.widget.TextView[@text='VERIFICATION']", LocatorType.XPath); }

    public WebElement Button_Yes() { return findElement("//android.widget.Button[@text='YES']", LocatorType.XPath); }

}
