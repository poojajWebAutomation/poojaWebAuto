package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_LoginPage  extends PageBase {

    //Admin Login - Header
    public WebElement Header_AdminLogin() { return findElement("//h2[text()=\"Admin Login\"]", LocatorType.XPath); }

    //Admin Login - Phone Number
    public WebElement TextBox_Phone() { return findElement("phoneno", LocatorType.Name); }
    public WebElement TextBox_QueryPanelPhone() { return findElement("phone", LocatorType.Id); }

    //Admin Login - Password
    public WebElement TextBox_Password() { return findElement("password", LocatorType.Name); }
    public WebElement TextBox_QueryPanelPassword() { return findElement("password", LocatorType.Id); }

    //Admin Login - Login Button
    public WebElement Button_AdminLogin() { return findElement("login", LocatorType.Id); }
    public WebElement Button_QueryPanelLogin() { return findElement("//button[contains(text(),'LOG IN')]", LocatorType.XPath); }

    //Admin ExtraEarnings - text
    public WebElement Label_ExtraEarnings() { return findElement("//strong[text()='Earn Extra Cash.']/parent::p", LocatorType.XPath); }

    public WebElement Button_ForgotPassword() { return findElement("//a[text()=\"forgot password?\"]", LocatorType.XPath); }

    public WebElement Header_ForgotPassword() { return findElement("//div/form/h2", LocatorType.XPath); }

    public WebElement Textbox_CellPhoneNumber() { return findElement("//label[@id=\"cellPhoneNumber\"]/following::input", LocatorType.XPath); }

    public WebElement Button_SendVerificationCode() { return findElement("//button[text()=\"Send Verification Code\"]", LocatorType.XPath); }

    public WebElement Textbox_NewPassword() { return findElement("newPassword", LocatorType.Id); }

    public WebElement Textbox_VerificationCode() { return findElement("verificationcode", LocatorType.Id); }

    public WebElement Textbox_ConfrimNewPassword() { return findElement("confirmPassword", LocatorType.Id); }

    public WebElement Button_ResetPassword() { return findElement("//button[text()=\"RESET PASSWORD\"]", LocatorType.XPath); }

    public WebElement Header_AdminDashboard() { return findElement("//div/h4[text()=\"Dashboard\"]", LocatorType.XPath); }

    public WebElement Text_AccountBlockedMessage() { return findElement("//p[text()=\"Invalid login credentials. Your account has been locked.\"]", LocatorType.XPath); }


}
