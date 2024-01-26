package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_ForgotPasswordPage extends PageBase {

    //---------------------------Forgot Password--------------------------------------------------------------------
    //driver Login - Forgot Password Link
    public WebElement Link_ForgotPassword () { return findElement("//a[contains(text(),'forgot password?')]", LocatorType.XPath); }

    //driver Forgot Password - Header
    public WebElement Header_ForgotPassword () { return findElement("//h2[contains(text(),'Forgot Password')]", LocatorType.XPath); }

    //driver Forgot Password - Blank field validation error
    public WebElement Err_ForgotPass_BlankField () { return findElement("//p[contains(text(),'Oops! It looks like you missed something. Please f')]", LocatorType.XPath); }

    //driver Forgot Password - Phone Number field
    public WebElement Textfield_ForgotPass_Phone() { return findElement("//div/label[@id='cellPhoneNumber']/following-sibling::input", LocatorType.XPath); }

    //driver Forgot Password - Phone Number field Error
    public WebElement Err_ForgotPass_Phone() { return findElement("(//div[contains(text(),'Oops! The phone number is invalid.')])[2]", LocatorType.XPath); }

    //driver Forgot Password - Send Verification Code button
    public WebElement Button_SendVerifCode() { return findElement("//button[contains(text(),'Send Verification Code')]", LocatorType.XPath); }

    //driver Forgot Password - Back to Login link
    public WebElement Link_BackToLogin() { return findElement("//a[contains(text(),'Back to Login')]", LocatorType.XPath); }
}
