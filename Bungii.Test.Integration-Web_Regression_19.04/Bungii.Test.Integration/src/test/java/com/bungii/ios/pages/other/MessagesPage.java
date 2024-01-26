package com.bungii.ios.pages.other;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class MessagesPage extends PageBase {
	//public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

/*	public WebElement Button_Cancel() {
		return findElement("Cancel", LocatorType.Name);
	}
	public WebElement Text_ToField() {
		return findElement("To:", LocatorType.Name);
	}
	public WebElement Button_RemindMeLater(boolean... ignoreException){return findElement("Remind Me Later",LocatorType.Name,ignoreException);}*/


	public WebElement Button_Cancel() {
		return findElement("Cancel", LocatorType.AccessibilityId);
	}
	public WebElement Text_ToField() {
		return findElement("//XCUIElementTypeTextField[@name=\"To:\"]", LocatorType.XPath);
	}
	public WebElement Button_RemindMeLater(boolean... ignoreException){return findElement("Remind Me Later",LocatorType.AccessibilityId,ignoreException);}
}
