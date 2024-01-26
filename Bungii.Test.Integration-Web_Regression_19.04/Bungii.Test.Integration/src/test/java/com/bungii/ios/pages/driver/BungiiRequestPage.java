package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiRequestPage extends PageBase {
	public WebElement Text_NavigationBar(boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath,ignoreException); }
	public WebElement Text_NavigationBar_Alt() { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }

	public WebElement Button_Accept() { return findElement("//XCUIElementTypeButton[@name=' Accept'] ", LocatorType.XPath); }
	public WebElement Button_Reject() { return findElement("REJECT", LocatorType.Name); }

	public WebElement Text_DistanceTag() { return findElement("TO PICKUP", PageBase.LocatorType.Name); }
	public WebElement 	Text_ValueDistance() { return findElement("//XCUIElementTypeStaticText[@name='Estimated Duration: ']/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement 	Text_ValueTripTime() { return findElement("//XCUIElementTypeStaticText[@name='Estimated Duration: ']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	public WebElement Text_EstimatedEarningTag() { return findElement("EARNINGS", LocatorType.Name); }
	public WebElement Text_EstimatedEarningValue() { return findElement("//XCUIElementTypeStaticText[@name='EARNINGS']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }
	public WebElement Text_TimeToPickup() { return findElement("//XCUIElementTypeStaticText[@name='Time to Pickup']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }


	public WebElement TextBox_Pickup_LineOne() {return findElement("//*[@name='input_icon_pickup']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[1]", LocatorType.XPath); }
	public WebElement TextBox_Pickup_LineTwo(boolean ...ignoreException) {return findElement("//*[@name='input_icon_pickup']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[2]", LocatorType.XPath,ignoreException); }
	public WebElement TextBox_Drop_LineOne() {return findElement("//*[@name='input_icon_dropoff']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[1]", LocatorType.XPath); }
	public WebElement TextBox_Drop_LineTwo() {return findElement("//*[@name='input_icon_dropoff']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[2]", LocatorType.XPath); }

	//Live trip details
	public WebElement Text_DropOffAddress() {
		return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeStaticText[3]",LocatorType.XPath);}
	public WebElement Text_PickUpAddress() {
		return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]",LocatorType.XPath);}

	//Swipe up for delivery details
	public WebElement Text_SwipeUpDetails() {return findElement("//XCUIElementTypeStaticText[@name=\"Swipe up for details\"]", LocatorType.XPath); }
	public WebElement Navbar_DeliveryProgress() {return findElement("//XCUIElementTypeNavigationBar[@name=\"Bungii_Driver.NewPickupDetail\"]", LocatorType.XPath); }

}
