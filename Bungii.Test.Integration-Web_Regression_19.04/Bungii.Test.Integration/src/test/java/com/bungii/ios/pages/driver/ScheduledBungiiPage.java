package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ScheduledBungiiPage extends PageBase{
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Image_SelectBungii () { return findElement("//XCUIElementTypeImage[@name='disclosure-arrow-right']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
	public WebElement Text_Trip_DateTime(){return findElement( "//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[1]",LocatorType.XPath);}
	//partner portal
	public WebElement TextBox_PartnerLoginPassword() { return findElement("formBasicPassword", LocatorType.Id); }
	public WebElement Button_SignIn() { return findElement("login", LocatorType.Id); }
	public WebElement Dropdown_Setting(boolean...ignoreException) { return findElement("//div[@class='dropdown-menu-right dropdown']",LocatorType.XPath, ignoreException);}
	public WebElement Button_TrackDeliveries() { return findElement("track-deliveries",LocatorType.Id);}
	public WebElement Label_StartOver() { return findElement("//span[contains(text(),'Start Over')]",LocatorType.XPath);}
	public WebElement Link_Setting() { return findElement("//i[@title='Menu']", LocatorType.XPath);}
	public WebElement Textbox_Password() { return findElement("//input[@id='name']",LocatorType.XPath);}
	public WebElement Button_Continue() { return  findElement("//div[@class='btn-group']//button[@class='btn'][contains(text(),'Continue')]",LocatorType.XPath);}
	public WebElement Link_CancelDelivery() { return findElement("//button[@class='btn cancel-delivery']",LocatorType.XPath);}
	public WebElement Button_OK() { return findElement("//button[@class='btn']",LocatorType.XPath);}
	public WebElement Button_OkOnDeliveryCancellationFailed() { return findElement("//button[@class='btn btn btn-primary']",LocatorType.XPath);}
	public WebElement Button_CancelDelivery() { return findElement("//button[@class='btn btn-clear btn-clear-red']",LocatorType.XPath);}


}
