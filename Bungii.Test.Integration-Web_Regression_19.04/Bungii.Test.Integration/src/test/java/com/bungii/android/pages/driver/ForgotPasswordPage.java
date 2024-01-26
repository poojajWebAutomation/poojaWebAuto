package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends PageBase {
    public WebElement Button_Send (boolean ... ignoreException) { return findElement("com.bungii.driver:id/button2", LocatorType.Id ,ignoreException    ); }
    public WebElement Text_ForgotError(){return findElement("com.bungii.driver:id/textinput_error",LocatorType.Id);}
    public WebElement Textbox_PhoneNumber (boolean ... ignoreException) { return findElement("com.bungii.driver:id/field_phone", LocatorType.Id ,ignoreException    ); }
    public WebElement Text_ForgotInfo () { return findElements("android.widget.TextView", LocatorType.ClassName ).get(1); }

    public WebElement Text_SmsCode() { return findElement("com.bungii.driver:id/field_sms_code", PageBase.LocatorType.Id); }
    public WebElement Text_Password() { return findElement("com.bungii.driver:id/field_password", PageBase.LocatorType.Id); }

    public WebElement Text_Confirm_Password() { return findElement("com.bungii.driver:id/field_re_password", PageBase.LocatorType.Id); }


    public WebElement Button_Continue() { return findElement("com.bungii.driver:id/resetPasswordContinue", PageBase.LocatorType.Id); }
    public WebElement Button_Resend() { return findElement("com.bungii.driver:id/reset_password_btn_resend", PageBase.LocatorType.Id); }


}
