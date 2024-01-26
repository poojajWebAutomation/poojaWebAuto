package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase {

//Header
    public WebElement   Header_LoginPage(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='LOGIN']", LocatorType.XPath,ignoreException); }

    // Phone Number field
    public WebElement TextField_PhoneNumber() { return findElement("com.bungii.customer:id/field_phone", LocatorType.Id); }

    //Password field
    public WebElement TextField_Password() { return findElement("com.bungii.customer:id/field_password", LocatorType.Id); }

    //Log In button
    public WebElement Button_Login(boolean...ignoreException) { return findElement("com.bungii.customer:id/loginGlobalButton", LocatorType.Id,ignoreException); }

    //Sign up button
    public WebElement Button_Signup(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='SIGN UP']", LocatorType.XPath,ignoreException); }

    // Forgot Password link
    public WebElement Link_ForgotPassword() { return findElement("com.bungii.customer:id/login_button_forgot_password", LocatorType.Id); }

    // Sign up Link
    public WebElement Link_Signup() { return findElement("com.bungii.customer:id/menu_signup", LocatorType.Id); }

    //Snackbar - Invalid Password
    public WebElement Snackbar_IncorrectPassword() { return findElement("com.bungii.customer:id/snackbar_text", LocatorType.Id); }

    //Error message - Phone Number
    public WebElement Error_EnterPhone() { return findElements("com.bungii.customer:id/textinput_error", LocatorType.Id).get(0); }

    //Error message - Password
    //public WebElement Error_EnterPassword() { return findElements("com.bungii.customer:id/textinput_error", LocatorType.Id).get(1); }
    public WebElement Error_EnterPassword() { return findElement("//*[@resource-id='com.bungii.customer:id/field_password_layout']/*/*/android.widget.TextView[@resource-id='com.bungii.customer:id/textinput_error']", LocatorType.XPath); }



    //Snackbar - Invalid Password
    public WebElement Snackbar() { return findElement("com.bungii.customer:id/snackbar_text", LocatorType.Id); }

}
