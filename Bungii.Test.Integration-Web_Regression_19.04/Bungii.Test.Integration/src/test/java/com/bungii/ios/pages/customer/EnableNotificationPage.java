package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class EnableNotificationPage extends PageBase {
/*    public WebElement Button_Sure(boolean ...ignoreException) {
        return findElement("SURE", PageBase.LocatorType.Name,ignoreException);
    }

    public WebElement Text_Header() {
        return findElements("//XCUIElementTypeStaticText", PageBase.LocatorType.XPath).get(0);
    }

    public WebElement Text_Label() {
        return findElements("//XCUIElementTypeStaticText", PageBase.LocatorType.XPath).get(1);
    }*/
    public WebElement Button_Sure(boolean ...ignoreException) { return findElement("SURE", LocatorType.AccessibilityId,ignoreException);}

    public WebElement Text_Header() {
        return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(0);
    }

    public WebElement Text_Label() { return findElements("XCUIElementTypeStaticText", PageBase.LocatorType.ClassName).get(1);}

    public WebElement Icon_Notification(boolean ...ignoreException) { return findElement("//XCUIElementTypeImage[@name=\"icon_notification_permission\"]", LocatorType.XPath,ignoreException);}
}
