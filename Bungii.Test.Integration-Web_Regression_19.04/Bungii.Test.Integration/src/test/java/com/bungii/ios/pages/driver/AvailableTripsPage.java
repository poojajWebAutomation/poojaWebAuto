package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AvailableTripsPage extends PageBase {
	public WebElement NavigationBar_Status() { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }
	public WebElement Image_SelectBungii() { return findElement("//XCUIElementTypeImage[@name='disclosure-arrow-right']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
	public List<WebElement> Image_SelectBungiis() { return findElements("//XCUIElementTypeImage[@name='disclosure-arrow-right']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
	public WebElement Partner_Name() { return findElement("//XCUIElementTypeOther/XCUIElementTypeStaticText", LocatorType.XPath);}
	public WebElement Text_FromHomeMiles() { return findElement("//XCUIElementTypeCell/XCUIElementTypeStaticText[2]", LocatorType.XPath);}
	public WebElement Text_PartnerName(String text) { return findElement("//XCUIElementTypeStaticText[@name=\""+text+"\"]",LocatorType.XPath);}

	public WebElement Button_Back() { return findElement("//XCUIElementTypeNavigationBar[@name=\"BUNGII DETAILS\"]/XCUIElementTypeButton", LocatorType.XPath);}
	public WebElement Text_RejectionPopup(boolean...ignoreException) { return findElement("//XCUIElementTypeStaticText[@name=\"What's your reason for rejecting?\"]", LocatorType.XPath,ignoreException);}
	public WebElement Text_RejectionReasons(String name) { return findElement("//XCUIElementTypeButton[@name=\""+name+"\"]", LocatorType.XPath);}
	public WebElement Button_Cancel() { return findElement("//XCUIElementTypeButton[@name=\"Cancel\"]", LocatorType.XPath);}
	public WebElement Button_Submit() { return findElement("//XCUIElementTypeButton[@name=\"Submit\"]", LocatorType.XPath);}
	public WebElement Label_PickupInstructions() { return findElement("PICKUP INSTRUCTIONS", LocatorType.AccessibilityId);}
	public WebElement Label_DropOffInstructions() { return findElement("DROP OFF INSTRUCTIONS", LocatorType.AccessibilityId);}
	public WebElement Text_PickupInstructions() { return findElement("//XCUIElementTypeStaticText[@name=\"PICKUP INSTRUCTIONS\"]/following-sibling::XCUIElementTypeTextView", LocatorType.XPath);}
	public WebElement Text_DropOffInstructions() { return findElement("//XCUIElementTypeStaticText[@name=\"DROP OFF INSTRUCTIONS\"]/following-sibling::XCUIElementTypeTextView", LocatorType.XPath);}
	public WebElement Text_PickupAddressLineOneDriverApp() { return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]", LocatorType.XPath);}
	public WebElement Text_PickupAddressLineTwoDriverApp() { return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]", LocatorType.XPath);}
	public WebElement Text_PickupInstructionsScheduleBungii() { return findElement("//XCUIElementTypeOther[@name=\"No images available\"]/XCUIElementTypeTextView[2]", LocatorType.XPath);}
	public WebElement Text_DropOffInstructionsScheduleBungii() { return findElement("//XCUIElementTypeOther[@name=\"No images available\"]/XCUIElementTypeTextView[3]", LocatorType.XPath);}
	public WebElement Link_DeliveryDetails(){return  findElement("dLabel",LocatorType.Id);}
	public WebElement List_ViewDeliveries(){return  findElement("//td/div[@class='dropdown open']/ul/li/*[contains(text(),'Delivery Details')]",LocatorType.XPath);}

	public WebElement Text_PickupLocationAdminPortal(){return  findElement("//tr/td[text()=\"Pickup Location\"]/following-sibling::td/strong",LocatorType.XPath);}
	public WebElement Label_DriverPickupInstructionsAdminPortal(){return  findElement("//div/h4[text()=\"Driver Pickup Instructions\"]",LocatorType.XPath);}
	public WebElement Label_DriverDropOffInstructionsAdminPortal(){return  findElement("//div/h4[text()=\"Driver Dropoff Instructions\"]",LocatorType.XPath);}
	public WebElement Text_DriverDropOffInstructionsServiceAdminPortal(){return  findElement("//div/div[6]/div[2]/p",LocatorType.XPath);}
	public WebElement Text_DriverPickupInstructionsServiceAdminPortal(){return  findElement("//div/div[7]/div[2]/p",LocatorType.XPath);}


	public List<WebElement> List_AllAvailableDeliveriesDriverApp() { return findElements("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell", LocatorType.XPath);}

	public List<WebElement> List_AllAvailableDeliveriesCustomerApp() { return findElements("//XCUIElementTypeApplication[@name=\"Bungii QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell", LocatorType.XPath);}

	public WebElement Text_DeliveryTime(int number) { return findElement(String.format("//XCUIElementTypeApplication[@name=\"Bungii QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[%d]/XCUIElementTypeStaticText",number), LocatorType.XPath);}

	public WebElement Text_CustomerName(int number) { return findElement(String.format("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[%d]/XCUIElementTypeStaticText",number), LocatorType.XPath);}

	public WebElement Text_DriverInstructionsInBulletsAtPickup(){return  findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText",LocatorType.XPath);}

	public WebElement Text_DriverInstructionsInBulletsAtDropOff(){return  findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText",LocatorType.XPath);}

}
