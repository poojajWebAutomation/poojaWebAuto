package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_RegistrationPage extends PageBase {

    //------------------------------driver Registration---------------------------------------------------------------
    //driver Registration - Header
    public WebElement Header_DriverRegistration () { return findElement("//h2[contains(text(),'Driver Registration')]", LocatorType.XPath); }
    //driver Registration - Click Here link
    public WebElement Link_ClickHere () { return findElement("signup", LocatorType.Id); }

    //driver Registration - First Name
    public WebElement TextBox_FirstName () { return findElement("//input[@name='FirstName']", LocatorType.XPath); }

    //driver Registration - Last Name
    public WebElement TextBox_LastName () { return findElement("//input[@name='LastName']", LocatorType.XPath); }

    //driver Registration - Email
    public WebElement TextBox_Email () { return findElement("//input[@name='EmailAddress']", LocatorType.XPath); }

    //driver Registration - Phone number
    public WebElement TextBox_PhoneNumber () { return findElement("//input[@type='tel']", LocatorType.XPath); }

    //driver Registration - Create password
    public WebElement TextBox_CreatePassword () { return findElement("//input[@class='form-control password form-control']", LocatorType.XPath); }

    //driver Registration - Confirm password
    public WebElement TextBox_ConfirmPassword (boolean ...ignoreException) { return findElement("confirmpassword", LocatorType.Id,ignoreException); }

    //driver Registration - Sign Up Button
    public WebElement Button_SignUp () { return findElement("//button[@class='mb30 btn btn-primary']", LocatorType.XPath); }

    //------------------ERRORS----------------------------------
    //ERROR - First Name
    public WebElement ERR_FirstName () { return findElement("//div[contains(text(),'Oops! The first name is invalid')]", LocatorType.XPath); }

    //ERROR - Last Name
    public WebElement ERR_LastName () { return findElement("//div[contains(text(),'Oops! The last name is invalid')]", LocatorType.XPath); }

    //ERROR - Email
    public WebElement ERR_Email () { return findElement("//div[contains(text(),'Invalid format.')]", LocatorType.XPath); }

    //ERROR - Phone Number
    public WebElement ERR_Phone () { return findElement("//div[contains(text(),'Oops! The Phone number is invalid.')]", LocatorType.XPath); }
    public WebElement ERR_ExistingPhone () { return findElement("//div[contains(text(),'Oops! It looks like the phone number you entered i')]", LocatorType.XPath); }

    //ERROR - Create password
    public WebElement ERR_CreatePassword () { return findElement("//div[contains(text(),'Password should consist of an uppercase, a lowerca')]", LocatorType.XPath); }
    public WebElement ERR_ShortPassword () { return findElement("//div[contains(text(),'Password should be at least 8 characters long.')]", LocatorType.XPath); }

    //ERROR - Confirm password
    public WebElement ERR_ConfirmPassword () { return findElement("confirmpassword-error", LocatorType.Id); }

    //ERROR - Blank fields
    public WebElement ERR_BlankFields () { return findElement("//span[contains(text(),'Oops! It looks like you missed something. Please f')]", LocatorType.XPath); }

    public WebElement Label_Success () { return findElement("//h3", LocatorType.XPath); }

    //------------------------------SMS Verification Page---------------------------------------------------------------

    //driver Registration - Success message on verify phone page
    public WebElement Text_Verification () { return findElement("//div/h3[contains(text(), 'Verify your phone')]/following-sibling::p[1]", LocatorType.XPath); }

    //driver Registration - Verification code field
    public WebElement TextBox_VerificationCode () { return findElement("formHorizontalEmail", LocatorType.Id); }

    //driver Registration - Error Verification Code - blank
    public WebElement ERR_VerifiCode_Blank () { return findElement("//p[contains(text(),'Oops! Please enter verification code to proceed.')]", LocatorType.XPath); }

    //driver Registration - Error Verification Code - invalid
    public WebElement ERR_VerifiCode_Invlid () { return findElement("//p[contains(text(),'Oops! It looks like the verification code you ente')]", LocatorType.XPath); }

    //driver Registration - Resend Code Button
    public WebElement Button_ResendCode () { return findElement("//button[contains(text(),'RESEND CODE')]", LocatorType.XPath); }

    //driver Registration - Submit button
    public WebElement Button_SubmitVerification () { return findElement("//button[contains(text(),'SUBMIT')]", LocatorType.XPath); }

    //------------------------------driver Verification---------------------------------------------------------------

    //driver Verification Successful Header
    public WebElement Header_VerificationSuccess () { return findElement("//div[@class = 'row']/div/img/following-sibling::h3", LocatorType.XPath); }

    //driver Verification driver Name
    public WebElement Text_DriverName () { return findElement("//body/div/div/div/nav/div/div/div/div[3]/p", LocatorType.XPath); }

    //driver Verification Successful - Continue Button
    public WebElement Button_ContinueReg () { return findElement("//div[@class = 'row']/div/h3/following-sibling::a[contains (text(), 'Continue')]", LocatorType.XPath); }

    //Logout Link
    public WebElement Link_Logout () { return findElement("//div[@class='pull-left info']/p/a[contains(text()='log out')]", LocatorType.XPath); }

    public WebElement Dropdown_Location () { return findElement("//select[@name='Location']", LocatorType.XPath); }

    public WebElement Text_Verbiage  () { return findElement("//p[contains(text(),'Driving with Bungii is a flexible, easy way to ear')]", LocatorType.XPath); }

    public WebElement Link_EyeOpen  () { return findElement("//div/span[@class ='close-eye']", LocatorType.XPath); }
}
