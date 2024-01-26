package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TripAlertSettingsPage extends PageBase {

    public WebElement Button_DeliveryAlerts() { return findElement("Delivery Alerts", LocatorType.AccessibilityId); }
    public WebElement Button_Done() { return findElement("Set", LocatorType.AccessibilityId); }
    public WebElement Button_Cancel() { return findElement("Cancel", LocatorType.AccessibilityId); }
    public WebElement Button_Save() { return findElement("SAVE", LocatorType.AccessibilityId); }
    public WebElement Button_SMSAlerts() { return findElement("SMS Alerts", LocatorType.AccessibilityId); }
    public WebElement Text_ScheduledInfo() { return findElement("XCUIElementTypeStaticText", LocatorType.ClassName); }
    public List<WebElement> Row_TripTime() { return findElements("//XCUIElementTypeSegmentedControl/following-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[1]", LocatorType.XPath); }

    public WebElement Text_Sunday() { return findElement("Sunday", PageBase.LocatorType.AccessibilityId); }
    public WebElement Scroll_Min() { return findElement("//XCUIElementTypePickerWheel[2]", LocatorType.XPath); }
    public WebElement Scroll_Hour() { return findElement("//XCUIElementTypePickerWheel[1]", LocatorType.XPath); }
    public WebElement Scroll_Marid() { return findElement("//XCUIElementTypePickerWheel[3]", LocatorType.XPath); }
    public WebElement Text_CurrentFromTime() { return findElement("//XCUIElementTypeStaticText[@name=\"From:\"]/following-sibling::XCUIElementTypeTextField", LocatorType.XPath); }
    public WebElement Text_CurrentToTime() { return findElement("//XCUIElementTypeStaticText[@name=\"To:\"]/following-sibling::XCUIElementTypeTextField", LocatorType.XPath); }


}