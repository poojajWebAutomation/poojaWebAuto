package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class EnableLocationPage extends PageBase{

/*    public WebElement Button_Sure(boolean ...ignoreException) {
        return findElement("SURE", PageBase.LocatorType.Name,ignoreException);
    }
    public WebElement Text_Header() {
        return findElements("//XCUIElementTypeStaticText", LocatorType.XPath).get(0);
    }

    public WebElement Text_Label() {
        return findElements("//XCUIElementTypeStaticText", PageBase.LocatorType.XPath).get(1);
    }*/

    public WebElement Button_Sure(boolean ...ignoreException) {
        //return findElement("SURE", LocatorType.AccessibilityId,ignoreException);
        return findElement("//XCUIElementTypeButton[@name='SURE']",LocatorType.XPath,ignoreException);
    }
    public WebElement Text_Header() {
        return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(0);
    }

    public WebElement Text_Label() {
        return findElements("XCUIElementTypeStaticText", PageBase.LocatorType.ClassName).get(1);
    }
    public WebElement Button_Done(boolean ...ignoreException) {
        return findElement("//XCUIElementTypeButton[@name='DONE']",LocatorType.XPath,ignoreException);
    }
}
