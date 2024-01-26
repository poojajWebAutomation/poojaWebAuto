package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiCompletedPage extends PageBase {
	//public WebElement Button_NextTrip() {return findElement("//XCUIElementTypeButton[@name='Submit']", LocatorType.XPath); }
	public WebElement Button_Submit() {return findElement("//XCUIElementTypeButton[@name='Submit']", LocatorType.XPath); }

	public WebElement Button_Skip_This_Step() {return findElement("//XCUIElementTypeButton[@name='Skip this step']",LocatorType.XPath);}

	public WebElement Button_Next_Bungii() {return findElement("//XCUIElementTypeButton[@name='Next Bungii']",LocatorType.XPath);}

	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Text_ChooseRating() {return findElement("//XCUIElementTypeStaticText[@name='Choose your rating']",LocatorType.XPath);}

	public WebElement Text_DriverExperience() {return findElement("//XCUIElementTypeStaticText[@name ='How was your experience with the customer?']",LocatorType.XPath);}

	public WebElement Textbox_AdditionalFeedback() {return findElement("//XCUIElementTypeTextView[@value='Any additional feedback (Optional)']",LocatorType.XPath);}

	public WebElement Textbox_Additional() {return findElement("//XCUIElementTypeStaticText[@name='Additional']",LocatorType.XPath);}

	public WebElement ExperienceRating() {return findElement("//XCUIElementTypeButton[1][@name='rating unselected star icon']",LocatorType.XPath);}


		public WebElement Title_Status() { return findElement("//XCUIElementTypeStaticText[@name='Bungii completed']", LocatorType.XPath); }

		public WebElement Text_TotalTimeLabel(){return  findElement("//XCUIElementTypeStaticText[@name='Total Time']",LocatorType.XPath);}

		public WebElement Text_TotalTime() { return findElement("//XCUIElementTypeStaticText[@name='Total Time']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }

		public WebElement Text_TotalDistanceLabel(){return  findElement("//XCUIElementTypeStaticText[@name='Total Distance']",LocatorType.XPath);}

		public WebElement Text_TotalDistance() { return findElement("//XCUIElementTypeStaticText[@name='Total Distance']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }

		public WebElement Text_TotalEarningsLabel(){return  findElement("//XCUIElementTypeStaticText[@name='Total earnings']",LocatorType.XPath);}

		public WebElement Text_TotalEarnings() { return findElement("//XCUIElementTypeStaticText[@name='Total earnings']/preceding::XCUIElementTypeStaticText[1]", LocatorType.XPath); }

		public WebElement Text_Label(){return  findElement("//XCUIElementTypeStaticText[@value='Cha-Ching!']",LocatorType.XPath);}
		public WebElement Image_Dollar(){return  findElement("icon_dollar",LocatorType.AccessibilityId);}

		public WebElement Text_RateCustomer() {return findElement("//XCUIElementTypeStaticText[@name='Rate customer']",LocatorType.XPath);}

		public WebElement StarRatings(int i) {return findElements("//XCUIElementTypeButton[@name='rating unselected star icon']",LocatorType.XPath).get(i);}

		public WebElement Notification_DriverStatus(boolean...ignoreException){return findElement("//XCUIElementTypeStaticText[@name=\"Are you available for more deliveries today?\"]\n",LocatorType.XPath,ignoreException);}
		public WebElement Text_NotificationDriverStatus(){return findElement("//XCUIElementTypeStaticText[@name=\"Staying online makes you eligible for on-demand requests. Go offline if you would rather not receive any.\"]",LocatorType.XPath);}
		public WebElement Button_StayOnline(){return findElement("//XCUIElementTypeButton[@name=\"Stay Online\"]",LocatorType.XPath);}
		public WebElement Button_GoOffline(){return findElement("//XCUIElementTypeButton[@name=\"Go Offline\"]",LocatorType.XPath);}
		public WebElement Slider_Online(){return findElement("//XCUIElementTypeStaticText[@name=\"ONLINE\"]",LocatorType.XPath);}
		public WebElement Slider_Offline(){return findElement("//XCUIElementTypeStaticText[@name=\"OFFLINE\"]",LocatorType.XPath);}
		public WebElement Text_ProcessingStatus(){return findElement("//XCUIElementTypeStaticText[@name=\"Processing\"]",LocatorType.XPath);}


}
