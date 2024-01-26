package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends PageBase {

    public WebElement Text_Info() { return findElement("//XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]", LocatorType.XPath); }
    public WebElement Text_SmsCode() { return findElement("XCUIElementTypeTextField", PageBase.LocatorType.ClassName); }
    public WebElement Text_Password() { return findElement("XCUIElementTypeSecureTextField", PageBase.LocatorType.ClassName); }

    public WebElement Text_Confirm_Password() { return findElement("Confirm Password", PageBase.LocatorType.AccessibilityId); }

    public WebElement Button_Back() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
    public WebElement Text_InputNumber() { return findElement("XCUIElementTypeTextField", LocatorType.ClassName); }


    public WebElement Button_Send() { return findElement("SEND", PageBase.LocatorType.AccessibilityId); }
    public WebElement Button_Continue() { return findElement("CONTINUE", PageBase.LocatorType.AccessibilityId); }
    public WebElement Button_Resend() { return findElement("RESEND", PageBase.LocatorType.AccessibilityId); }

    public WebElement Button_StartAnApplicationHere() { return findElement("//XCUIElementTypeLink[@name=\"start an application here.\"]", LocatorType.XPath); }

    public WebElement Label_BungiiTheUltimateSideHustle() { return findElement("//XCUIElementTypeOther[@name=\"Bungii: The Ultimate Side Hustle.\"]", LocatorType.XPath); }
}
