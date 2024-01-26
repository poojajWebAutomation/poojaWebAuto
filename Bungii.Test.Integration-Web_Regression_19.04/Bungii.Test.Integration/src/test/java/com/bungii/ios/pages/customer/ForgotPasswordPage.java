package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends PageBase {
//	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

/*	public WebElement Text_Info() { return findElement("//XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_SmsCode() { return findElement("//XCUIElementTypeTextField", PageBase.LocatorType.XPath); }
	public WebElement Text_Password() { return findElement("//XCUIElementTypeSecureTextField", PageBase.LocatorType.XPath); }
	public WebElement Button_Back() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
	public WebElement Text_InputNumber() { return findElement("//XCUIElementTypeTextField", PageBase.LocatorType.XPath); }


	public WebElement Image_PhoneIcon() { return findElement("icon_phone", PageBase.LocatorType.Name); }
	public WebElement Image_Password() { return findElement("textfield_password_1", PageBase.LocatorType.Name); }
	public WebElement Button_Send() { return findElement("SEND", PageBase.LocatorType.Name); }
	public WebElement Button_Continue() { return findElement("CONTINUE", PageBase.LocatorType.Name); }
	public WebElement Button_Resend() { return findElement("RESEND", PageBase.LocatorType.Name); }*/
	public WebElement Text_Info() { return findElement("XCUIElementTypeStaticText", LocatorType.ClassName); }
	public WebElement Text_SmsCode() { return findElement("XCUIElementTypeTextField", PageBase.LocatorType.ClassName); }
	public WebElement Text_Password() { return findElement("XCUIElementTypeSecureTextField", PageBase.LocatorType.ClassName); }
	public WebElement Button_Back() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
	public WebElement Text_InputNumber() { return findElement("XCUIElementTypeTextField", LocatorType.ClassName); }


	public WebElement Image_PhoneIcon() { return findElement("icon_phone", LocatorType.AccessibilityId); }
	public WebElement Image_Password() { return findElement("textfield_password_1", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Send() { return findElement("SEND", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Continue() { return findElement("CONTINUE", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Resend() { return findElement("RESEND", PageBase.LocatorType.AccessibilityId); }


}
