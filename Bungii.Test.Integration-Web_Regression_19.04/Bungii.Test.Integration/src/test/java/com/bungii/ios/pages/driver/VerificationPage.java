package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class VerificationPage extends PageBase {
    public WebElement Button_Verify() {return findElement("VERIFY", LocatorType.AccessibilityId); }
    public WebElement  TextBox_SmsCode() {return findElement("XCUIElementTypeTextField", LocatorType.ClassName); }
}
