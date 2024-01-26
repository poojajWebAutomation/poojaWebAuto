package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase {
/*    public WebElement Button_Trips() {
        return findElement("adminmenu-trips", LocatorType.Id);
    }


    public WebElement Textfield_PhoneNumber() {
        return findElement("//XCUIElementTypeTextField", LocatorType.XPath);
    }

    public WebElement Textfield_Password() {
        return findElement("//XCUIElementTypeSecureTextField", LocatorType.XPath);
    }

    public WebElement Button_Login() {
        return findElement("//XCUIElementTypeButton[@name='LOG IN']", LocatorType.XPath);
    }

    public WebElement Text_NavigationBar() {
        return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", LocatorType.XPath);
    }

    public WebElement Button_SignUp() {
        return findElement("SIGN UP", LocatorType.Name);
    }

    public WebElement Button_ForgotPassword() {
        return findElement("Forgot Password?", LocatorType.Name);
    }*/
public WebElement Button_Trips() {
    return findElement("adminmenu-trips", LocatorType.Id);
}


    public WebElement Textfield_PhoneNumber() {
        return findElement("XCUIElementTypeTextField", LocatorType.ClassName);
    }

    public WebElement Textfield_Password() {
        return findElement("XCUIElementTypeSecureTextField", LocatorType.ClassName);
    }

    public WebElement Button_Login() {
      //  return findElement("//XCUIElementTypeButton[@name='LOG IN']", LocatorType.XPath);
        return findElement("type == 'XCUIElementTypeButton' AND name == 'LOG IN'", LocatorType.Predicate);

    }

    public WebElement Text_NavigationBar() {
        return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", LocatorType.XPath);
    }

    public WebElement Button_SignUp() {
        return findElement("SIGN UP", LocatorType.AccessibilityId);
    }

    public WebElement Button_ForgotPassword() {
        return findElement("Forgot Password?", LocatorType.AccessibilityId);
    }

}
