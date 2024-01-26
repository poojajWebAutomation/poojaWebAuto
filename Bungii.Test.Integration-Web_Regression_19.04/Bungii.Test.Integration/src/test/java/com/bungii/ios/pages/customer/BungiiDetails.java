package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BungiiDetails extends PageBase {


	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Text_Driver1Name() { return findElement("//XCUIElementTypeCell[2]/XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Driver2Name() { return findElement("//XCUIElementTypeCell[3]/XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Button_CancelBungii() {return findElement("//XCUIElementTypeStaticText[@name='CANCEL BUNGII']/preceding-sibling::XCUIElementTypeButton", LocatorType.XPath); }
	public WebElement Text_PickUpLocationLine1() {return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(5); } //updated phonewise
	public WebElement Text_PickUpLocationLine2() {return findElements("XCUIElementTypeStaticText", PageBase.LocatorType.ClassName).get(6); } //updated phonewise

	public WebElement Text_DropLocationLine1() {return findElements("XCUIElementTypeStaticText", PageBase.LocatorType.ClassName).get(7); } //updated phonewise
	public WebElement Text_DropLocationLine2() {return findElements("XCUIElementTypeStaticText", PageBase.LocatorType.ClassName).get(8); } //updated phonewise

	public WebElement Text_Time() {return findElement("**/XCUIElementTypeStaticText[4]", LocatorType.ClassChain); }
	public WebElement Text_TotalEstimate() {return findElement("**/XCUIElementTypeStaticText[5]", LocatorType.ClassChain); }
	public WebElement Text_Driver1Status(boolean ...ignoreException) {return findElement("//XCUIElementTypeStaticText[@name='Driver #1']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath,ignoreException); }
	public WebElement Text_Driver2Status() {return findElement("//XCUIElementTypeStaticText[@name='Driver #2']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	public WebElement Text_Time_iOS11_2() {return findElement("**/XCUIElementTypeStaticText[4]", LocatorType.ClassChain); }
	public WebElement Text_TotalEstimate_iOS11_2() {return findElement("**/XCUIElementTypeStaticText[5]", LocatorType.ClassChain); }
	public WebElement Text_Driver1Status_iOS11_2() {return findElement("//XCUIElementTypeStaticText[@name='Driver #1']/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Driver1Status_iOS11_Tag() {return findElement("Driver #1", LocatorType.Name); }
	public WebElement Text_Driver2Status_iOS11_2() {return findElement("//XCUIElementTypeStaticText[@name='Driver #2']/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Driver2Status_iOS11_Tag() {return findElement("Driver #2", LocatorType.Name); }


	public WebElement Row_TimeSelect() {return findElement("//XCUIElementTypeStaticText[@name='Time']/parent::XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_Back() {return findElement("Back", LocatorType.Name); }
	public WebElement Text_MessageToCustomer(boolean ...ignoreException) {return findElement("//XCUIElementTypeStaticText[@name=\"BUNGII DRIVERS\"]/preceding-sibling::XCUIElementTypeStaticText", LocatorType.XPath,ignoreException); }
	public WebElement Button_Driver1Call() {return findElement("//XCUIElementTypeStaticText[@name=\"BUNGII DRIVERS\"]/parent::XCUIElementTypeCell/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeButton[@name=\"Button\"][1]", LocatorType.XPath); }
	public WebElement Button_Driver1SMS() {return findElement("//XCUIElementTypeStaticText[@name=\"BUNGII DRIVERS\"]/parent::XCUIElementTypeCell/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeButton[@name=\"Button\"][2]", LocatorType.XPath); }


	public WebElement Button_Driver2Call() {return findElement("//XCUIElementTypeStaticText[@name=\"BUNGII DRIVERS\"]/parent::XCUIElementTypeCell/following-sibling::XCUIElementTypeCell[2]/XCUIElementTypeButton[@name=\"Button\"][1]", LocatorType.XPath); }
	public WebElement Button_Driver2SMS() {return findElement("//XCUIElementTypeStaticText[@name=\"BUNGII DRIVERS\"]/parent::XCUIElementTypeCell/following-sibling::XCUIElementTypeCell[2]/XCUIElementTypeButton[@name=\"Button\"][2]", LocatorType.XPath); }

	public List<WebElement> FilledStars() {return findElements("//*[contains(@name, 'rating filled star icon')]", LocatorType.XPath);}
	public List<WebElement> HalfFilledStar() {return findElements("//*[contains(@name, 'rating half filled star icon')]", LocatorType.XPath);}
}
