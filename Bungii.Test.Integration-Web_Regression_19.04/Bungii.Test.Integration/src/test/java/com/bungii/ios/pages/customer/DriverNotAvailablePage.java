package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class DriverNotAvailablePage extends PageBase {
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_OK() {
		return findElement("OK", LocatorType.AccessibilityId);
	}
/*	public WebElement Button_OK() {
		return findElement("OK", PageBase.LocatorType.Name);
	}*/


}
