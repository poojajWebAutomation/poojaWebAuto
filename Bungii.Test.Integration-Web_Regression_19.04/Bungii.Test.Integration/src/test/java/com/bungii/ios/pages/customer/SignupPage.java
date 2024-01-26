package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SignupPage extends PageBase {

    public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

    public WebElement Button_Login() {
        return findElement("LOGIN", LocatorType.Name);
    }
    public WebElement Button_CheckBox_Referral() {
        return findElement("check box off", LocatorType.Name);
    }

    public WebElement Button_Verify() {
        return findElement("VERIFY", LocatorType.Name);
    }

    public WebElement Button_Keyboard_Next() {
        return findElement("Next:", LocatorType.Name);
    }

    public WebElement Button_No_Continue() {
        return findElement("No, Continue", LocatorType.Name);
    }

    public WebElement Textfield_LastName() {
        return findElement("//XCUIElementTypeImage[@name='textfield_last_name']/preceding-sibling::XCUIElementTypeTextField", LocatorType.XPath);
    }

    public WebElement Textfield_Email() {
        return findElement("//XCUIElementTypeImage[@name='textfield_email']/preceding-sibling::XCUIElementTypeTextField", LocatorType.XPath);
    }

    public WebElement Textfield_Phonenumber() {
        return findElement("//XCUIElementTypeImage[@name='textfield_phone']/preceding-sibling::XCUIElementTypeTextField", LocatorType.XPath);
    }

    public WebElement Textfield_Password() {
        return findElement("//XCUIElementTypeImage[@name='textfield_password']/preceding-sibling::XCUIElementTypeSecureTextField", LocatorType.XPath);
    }

    public WebElement Textfield_PromoCode() {
        return findElement("//XCUIElementTypeImage[@name='icon_save_money']/preceding-sibling::XCUIElementTypeTextField", LocatorType.XPath);
    }

    public WebElement Textfield_FirstName() {
        return findElement("//XCUIElementTypeImage[@name='textfield_first_name']/preceding-sibling::XCUIElementTypeTextField", LocatorType.XPath);
    }

    public WebElement Button_Facebook() {
        return findElement("//XCUIElementTypeStaticText[@value='Facebook']", LocatorType.XPath);
    }

    public WebElement Button_Keyboard_done() {
        return findElement("//XCUIElementTypeButton[@name='Done']", LocatorType.XPath);
    }

    public WebElement Button_Done() {
        return findElement("//XCUIElementTypeButton[@name='Done']", LocatorType.XPath);
    }

    public WebElement Button_Chevron() {
        return findElement("//XCUIElementTypeImage[@name='chevron']/parent::XCUIElementTypeOther", LocatorType.XPath);
    }

    public WebElement Button_Signup() {
        return findElement("//XCUIElementTypeButton[@name='SIGN UP']", LocatorType.XPath);
    }

}
