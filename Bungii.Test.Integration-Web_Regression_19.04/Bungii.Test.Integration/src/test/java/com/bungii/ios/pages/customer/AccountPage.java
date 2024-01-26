package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class AccountPage extends PageBase{
    //public By Text_NavigationBar =MobileBy.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeOther");
	public WebElement Button_MenuBack() {
		//  return findElement("//XCUIElementTypeButton[@name='LOG IN']", LocatorType.XPath);
		return findElement("type == 'XCUIElementTypeButton' AND name == 'Back'", LocatorType.Predicate);

	}

	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Text_Name() { return findElement("//XCUIElementTypeStaticText[@name='Phone:']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Phone() { return findElement("//XCUIElementTypeStaticText[@name='Phone:']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Button_DeleteAccount() { return findElement("//XCUIElementTypeButton[@name='Delete account']",LocatorType.XPath);}
	public WebElement Text_AccountPassword() { return findElement("value == 'Password'", LocatorType.Predicate);}
	public WebElement Button_Delete() { return findElement("//XCUIElementTypeButton[@name='Delete']",LocatorType.XPath);}
	public WebElement Button_Cancel() { return findElement("//XCUIElementTypeButton[@name='Cancel']",LocatorType.XPath);}

}
