package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SupportPage extends PageBase {


    public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

    public WebElement TextBox_Support() { return findElement("//XCUIElementTypeTextView", LocatorType.XPath); }
    public WebElement Text_SupportQuestion () { return findElement("//XCUIElementTypeButton/preceding-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }
    public WebElement Text_SupportLabelQuestion () { return findElement("//XCUIElementTypeOther/XCUIElementTypeStaticText[2]", LocatorType.XPath); }

/*    public WebElement Button_Send () { return findElement("SEND", LocatorType.Name); }
    public WebElement Image_CustLogo() { return findElement("bungii_logo_customer", LocatorType.Name); }*/
    public WebElement TextBox_Pickup() { return findElement("//XCUIElementTypeStaticText[@name='PICKUP LOCATION']/following-sibling::XCUIElementTypeTextField", LocatorType.XPath); }

    public WebElement Button_Send () { return findElement("SEND", LocatorType.AccessibilityId); }
    public WebElement Image_CustLogo() { return findElement("bungii_logo_customer", LocatorType.AccessibilityId); }

}
