package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SuccessPage extends PageBase {
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

/*	public WebElement Text_BungiiPosted () { return findElement("Bungii Posted!", LocatorType.Name); }
	public WebElement Button_Done(boolean ...ignoreException) { return findElement("DONE", LocatorType.Name,ignoreException); }*/

	public WebElement Text_BungiiPosted () { return findElement("Bungii Posted!", LocatorType.AccessibilityId); }
	public WebElement Button_Done(boolean ...ignoreException) { return findElement("DONE", LocatorType.AccessibilityId,ignoreException); }
}
