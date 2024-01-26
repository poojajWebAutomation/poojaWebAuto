package com.bungii.ios.pages.driver;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase {
/*    public WebElement TextField_PhoneNumber(boolean ... ignoreException) {
        return findElement("//XCUIElementTypeTextField", PageBase.LocatorType.XPath,ignoreException);
    }

    public WebElement Textfield_Password() {
        return findElement("//XCUIElementTypeSecureTextField", PageBase.LocatorType.XPath);
    }

    public WebElement Button_Login() {
        return findElement("//XCUIElementTypeButton[@name='LOG IN']", PageBase.LocatorType.XPath);
    }*/
    public WebElement TextField_PhoneNumber(boolean ... ignoreException) {
        return findElement("XCUIElementTypeTextField", LocatorType.ClassName,ignoreException);
    }

    public WebElement Textfield_Password() {
        return findElement("XCUIElementTypeSecureTextField", LocatorType.ClassName);
    }

    public WebElement Button_Login() {
        return findElement("type == 'XCUIElementTypeButton' AND name == 'LOG IN'", LocatorType.Predicate);
    }

    public WebElement Button_ForgotPassword() {
        return findElement("Forgot password?", LocatorType.AccessibilityId);
    }

}
