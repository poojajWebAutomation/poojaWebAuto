package com.bungii.ios.pages.customer;

//import org.apache.commons.codec.binary.Base64;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UpdateStatusPage extends PageBase {
	//public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
//	public List<WebElement>  Text_Info() {return findElements("//XCUIElementTypeImage[@name='pickup_state_1']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }
	public List<WebElement> Text_Info() { return findElements("**/XCUIElementTypeStaticText", LocatorType.ClassChain); }


//	public WebElement  Text_DriverName() {return findElement("//XCUIElementTypeButton[@name='btn call']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }

/*	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }
	public WebElement Button_Call() {return findElement("btn call", LocatorType.Name); }
	public WebElement Image_Trip_State_1() { return findElement("pickup_state_1", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_2() { return findElement("pickup_state_2", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_3() { return findElement("pickup_state_3", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_4() { return findElement("pickup_state_4", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_5() { return findElement("pickup_state_5", PageBase.LocatorType.Name); }
	public WebElement Button_DuoMoreOptions1() { return findElement("(//XCUIElementTypeImage[@name=\"more\"])[1]", PageBase.LocatorType.XPath); }
	public WebElement Button_DuoMoreOptions2() { return findElement("(//XCUIElementTypeImage[@name=\"more\"])[2]", PageBase.LocatorType.XPath); }
	public WebElement Button_CallDriver() { return findElement("Call driver", PageBase.LocatorType.Name); }
	public WebElement Button_SmsDriver() { return findElement("Text driver", PageBase.LocatorType.Name); }
		public WebElement  Button_Sms () {return findElement("//XCUIElementTypeButton[@name='btn call']/preceding-sibling::XCUIElementTypeButton", LocatorType.XPath); }
*/
	public WebElement  Button_Sms () {return findElement("btn sms", LocatorType.AccessibilityId); }
	public WebElement  Text_DriverName() {return findElement("**/XCUIElementTypeStaticText[-1]", LocatorType.ClassChain); }

	public WebElement Text_NavigationBar() { return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName); }
	public WebElement Button_Call() {return findElement("btn call", LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_1() { return findElement("pickup_state_1", PageBase.LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_2() { return findElement("pickup_state_2", PageBase.LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_3() { return findElement("pickup_state_3", PageBase.LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_4() { return findElement("pickup_state_4", PageBase.LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_5() { return findElement("pickup_state_5", PageBase.LocatorType.AccessibilityId); }
//	public WebElement Button_DuoMoreOptions1() { return findElement("(//XCUIElementTypeImage[@name=\"more\"])[1]", PageBase.LocatorType.XPath); }
//	public WebElement Button_DuoMoreOptions2() { return findElement("(//XCUIElementTypeImage[@name=\"more\"])[2]", PageBase.LocatorType.XPath); }

//	public WebElement Button_DuoMoreOptions1() { return findElements("more", PageBase.LocatorType.AccessibilityId).get(0); }
//	public WebElement Button_DuoMoreOptions2() { return findElements("more", LocatorType.AccessibilityId).get(1); }

	public WebElement Button_DuoMoreOptions1() { return findElement("**/XCUIElementTypeOther/XCUIElementTypeButton[`name != \"Return to Bungii\"`][`name != \"Return to Bungii Driver\"`][1]", LocatorType.ClassChain); }
	public WebElement Button_DuoMoreOptions2() { return findElement("**/XCUIElementTypeOther/XCUIElementTypeButton[`name != \"Return to Bungii\"`][`name != \"Return to Bungii Driver\"`][2]", LocatorType.ClassChain); }

	public WebElement Button_CallDriver() { return findElement("Call driver", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_SmsDriver() { return findElement("Text driver", PageBase.LocatorType.AccessibilityId); }

	public List<WebElement> FilledStars() {return findElements("//*[contains(@name, 'rating filled star icon')]", LocatorType.XPath);}
	public List<WebElement> HalfFilledStar() {return findElements("//*[contains(@name, 'rating half filled star icon')]", LocatorType.XPath);}

	//STACK
	public WebElement Button_CancelBungii() { return findElement("Cancel Bungii", LocatorType.AccessibilityId); }

}
