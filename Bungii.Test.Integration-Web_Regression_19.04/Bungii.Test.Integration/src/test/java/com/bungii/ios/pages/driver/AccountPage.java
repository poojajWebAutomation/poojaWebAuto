package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class AccountPage  extends PageBase {
	public WebElement Text_Name() { return findElement("//XCUIElementTypeStaticText[@name='Phone:']/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Phone() { return findElement("//XCUIElementTypeStaticText[@name='phone:']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Email() { return findElement("//XCUIElementTypeStaticText[@name='Email:']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	

}
