package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TermsAndConditionPage  extends PageBase {
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
/*
	public WebElement Button_CheckOff(boolean ...ignoreException) {return findElement("check box off", LocatorType.Name,ignoreException); }
	public WebElement Button_CheckOn(boolean ...ignoreException) {return findElement("check box on", LocatorType.Name,ignoreException); }

	public WebElement Button_Continue () {return findElement("CONTINUE", LocatorType.Name); }
	public WebElement Text_Label (boolean ...ignoreException) {return findElement("TERMS OF USE", LocatorType.Name,ignoreException); }
	public WebElement Text_Accept (boolean ...ignoreException) {return findElement("//XCUIElementTypeStaticText[@name=\"I agree to Terms and Conditions\"]", LocatorType.XPath,ignoreException); }
*/


	public WebElement Button_CheckOff(boolean ...ignoreException) {return findElement("check box off", LocatorType.AccessibilityId,ignoreException); }
	public WebElement Button_CheckOn(boolean ...ignoreException) {return findElement("check box on", LocatorType.AccessibilityId,ignoreException); }

	public WebElement Button_Continue () {return findElement("CONTINUE", LocatorType.AccessibilityId); }
	public WebElement Text_Label (boolean ...ignoreException) {return findElement("TERMS OF USE", LocatorType.AccessibilityId,ignoreException); }
	public WebElement Text_Accept (boolean ...ignoreException) {return findElement("type == 'XCUIElementTypeStaticText' AND name == 'I agree to Terms and Conditions'", LocatorType.Predicate,ignoreException); }



}
