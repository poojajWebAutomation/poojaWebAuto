package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_VerifyPhonePage extends PageBase {

    //---------------------------Forgot Password - Verify Your Phone-------------------------------------------------
    //driver - Verify your phone - Header
    public WebElement Header_VerifyPhone () { return findElement("//h2[contains(text(),'Verify your phone')]", LocatorType.XPath); }

    //driver - Verify your phone - text
    public WebElement Text_Verify_PhoneNo () { return findElement("//p[@id='subtitle']", LocatorType.XPath); }

    //Verify Your Phone - Blank field validation error
    public WebElement Err_VerifyPhone_BlankField () { return findElement("//span[contains(text(),'Oops! It looks like you missed something. Please f')]", LocatorType.XPath); }

    //Verify Your Phone - Resend Code
    public WebElement Button_ResendCode () { return findElement("//button[contains(text(),'RESEND CODE')]", LocatorType.XPath); }

    //Verify Your Phone - Code
    public WebElement Textfield_Code () { return findElement("verificationcode", LocatorType.Id); }

    //Verify Your Phone - Password
    public WebElement Textfield_Password () { return findElement("newPassword", LocatorType.Id); }

    //Verify Your Phone - Confirm Password
    public WebElement Textfield_ConfirmPassword () { return findElement("confirmPassword", LocatorType.Id); }

    //Verify Your Phone - Password - Passwords dont match -error
    public WebElement Button_ResetPassword () { return findElement("//button[contains(text(),'RESET PASSWORD')]", LocatorType.XPath); }

    //Verify Your Phone - Blank field validation error
    public WebElement Err_VerifyPhone_BlankPasswords () { return findElement("verificationerrorsummary", LocatorType.Id); }

    //Verify Your Phone - Incorrect Verification Code
    public WebElement Err_VerifyPhone_Code_Incorrect () { return findElement("//span[contains(text(),'Oops! It looks like the verification code you ente')]", LocatorType.XPath); }

    //Verify Your Phone - Password - error
    public WebElement Err_VerifyPhone_Password_Invalid () { return findElement("new-password-error", LocatorType.Id); }

    //Verify Your Phone - Password - Passwords dont match -error
    public WebElement Err_VerifyPhone_ConfirmPassword () { return findElement("//div[contains(text(),'Oops! The two passwords you entered do not match.')]", LocatorType.XPath); }
}
