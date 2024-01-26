package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class VerificationPage extends PageBase {
//	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
/*	public WebElement Button_Verify() {return findElement("VERIFY", PageBase.LocatorType.Name); }
	public WebElement  TextBox_SmsCode() {return findElement("//XCUIElementTypeTextField", LocatorType.XPath); }*/
	public WebElement Button_Verify() {return findElement("VERIFY", LocatorType.AccessibilityId); }
	public WebElement  TextBox_SmsCode() {return findElement("XCUIElementTypeTextField", LocatorType.ClassName); }

	/*	public WebElement Button_ResendCode =MobileBy.name("Resend code");
	public WebElement Button_ChangeNumber =MobileBy.name("Change number");
	public WebElement Text_WrongNumber =MobileBy.name("Wrong number?");
	public WebElement Text_ResendCode =MobileBy.name("Didn't receive code?");*/
	
	

}
