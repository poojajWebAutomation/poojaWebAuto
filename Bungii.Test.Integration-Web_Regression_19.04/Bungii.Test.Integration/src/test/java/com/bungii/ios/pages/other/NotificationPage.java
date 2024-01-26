package com.bungii.ios.pages.other;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NotificationPage extends PageBase{
	public List<WebElement> Cell_Notification() { return findElements("NotificationCell",LocatorType.AccessibilityId);};
	public WebElement Cell_Notification(boolean... ignoreException) { return findElement("NotificationCell",LocatorType.AccessibilityId,ignoreException);};

	public List<WebElement> Cell_Notitication_ios10(){return findElements("XCUIElementTypeCell",LocatorType.ClassName);}
	public List<WebElement> Cell_Notitication_ios10_2(){return findElements("//*[contains(@label,'BUNGII DRIVER,')]",LocatorType.XPath);}
	public WebElement Button_NotificationScreen(boolean... ignoreException) { return findElement("Press Home to open",LocatorType.AccessibilityId,ignoreException);};
	public List<WebElement> Button_NotificationClear() { return findElements("Clear notifications in section",LocatorType.AccessibilityId);};
	public WebElement Button_NotificationClearConfirm(boolean... ignoreException) { return findElement("Confirm clear notifications in section",LocatorType.AccessibilityId,ignoreException);};


	public WebElement Generic_Notification(boolean ...ignoreException) {return findElement("name == 'NotificationCell' or name ='Clear notifications in section' or name='Press Home to open' or name='No Older Notifications'", PageBase.LocatorType.Predicate,ignoreException); }


}
