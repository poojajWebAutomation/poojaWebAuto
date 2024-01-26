package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TripDetailsPage extends PageBase {
//	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Button_Accept() { return findElement("//XCUIElementTypeButton[@name=\" Accept\"]", LocatorType.XPath); }
	public WebElement Text_Distance() { return findElement("//XCUIElementTypeStaticText[contains(@name,'miles')]", PageBase.LocatorType.XPath); }
	public WebElement Text_EstimatedEarnings() { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_ScheduledDateTime() { return findElement("//XCUIElementTypeStaticText[@name='WHEN']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
//	public WebElement Text_ScheduledTime () { return findElement("//XCUIElementTypeStaticText[@name='Trip Scheduled Time']/following::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_EstimatedEarningsSchedule() { return findElement("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText", LocatorType.XPath); }

	public WebElement Text_DriverVehicleModel() { return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[3]", LocatorType.XPath); }

	public WebElement Text_DriverVehicleLicenseNumber() { return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText", LocatorType.XPath); }

	public WebElement PopUp_InadequatePayload() { return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeAlert/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText", LocatorType.XPath); }
	public WebElement Text_PalletOne() { return findElement("//XCUIElementTypeStaticText[@name=\"Pallet 1\"]", LocatorType.XPath); }
	public WebElement Text_PalletTwo() { return findElement("//XCUIElementTypeStaticText[@name=\"Pallet 2\"]", LocatorType.XPath); }
	public WebElement Button_Ok() { return findElement("//XCUIElementTypeButton[@name=\"OK\"]", LocatorType.XPath); }
	public WebElement PopUp_AlreadyAcceptedPallet() { return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeAlert/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]", LocatorType.XPath); }
	public WebElement Text_PalletOneWeight() { return findElement("//XCUIElementTypeOther[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]", LocatorType.XPath); }
	public WebElement Text_PalletOneDimensions() { return findElement("//XCUIElementTypeOther[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[4]", LocatorType.XPath); }
	public WebElement Text_PalletOneName() { return findElement("//XCUIElementTypeOther[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[5]", LocatorType.XPath); }
	public WebElement Text_PalletOneWeightSchedulePage() { return findElement("//XCUIElementTypeOther [@name=\"No images available\"]/XCUIElementTypeStaticText[4]", LocatorType.XPath); }
	public WebElement Text_PalletOneDimensionsSchedulePage() { return findElement("//XCUIElementTypeOther [@name=\"No images available\"]/XCUIElementTypeStaticText[5]", LocatorType.XPath); }
	public WebElement Text_PalletOneNameSchedulePage() { return findElement("//XCUIElementTypeOther [@name=\"No images available\"]/XCUIElementTypeStaticText[6]", LocatorType.XPath); }


}
