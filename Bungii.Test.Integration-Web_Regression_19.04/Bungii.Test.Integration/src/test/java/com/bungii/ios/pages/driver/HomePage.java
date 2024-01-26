package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends PageBase {

/*	public WebElement Button_GoOnline(boolean ...ignoreException) { return findElement("GO ONLINE", PageBase.LocatorType.Name,ignoreException); }
	public WebElement Button_GoOffline(boolean ...ignoreException) { return findElement("GO OFFLINE", PageBase.LocatorType.Name,ignoreException); }
	public WebElement Text_AvailableTrips() { return findElement("Available Bungiis", PageBase.LocatorType.Name); }

	public WebElement NavigationBar_Status () { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }
//	public WebElement Text_NavigationBar () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	//public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath,ignoreException); }
	public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName,ignoreException); }

	public WebElement Button_AppMenu () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }

	public WebElement Text_DriverName() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath); }
	public WebElement Text_DriverInfo() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
	public WebElement Button_DriverStatus() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeButton", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_AvailableTrip() { return findElement("//XCUIElementTypeStaticText[@name='AVAILABLE BUNGIIS']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_Home() { return findElement("//XCUIElementTypeStaticText[@name='HOME']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_Account() { return findElement("//XCUIElementTypeStaticText[@name='ACCOUNT']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_ScheduledTrip() { return findElement("//XCUIElementTypeStaticText[@name='SCHEDULED BUNGIIS']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_LogOut() { return findElement("//XCUIElementTypeStaticText[@name='LOGOUT']", PageBase.LocatorType.XPath); }*/

    //public WebElement Button_GoOnline(boolean ...ignoreException) { return findElement("GO ONLINE", LocatorType.AccessibilityId,ignoreException); }
    public WebElement Button_GoOnline(boolean ...ignoreException) { return findElement("//XCUIElementTypeStaticText[@name='OFFLINE']", LocatorType.XPath,ignoreException); }
    public WebElement Button_GoOffline(boolean ...ignoreException) { return findElement("GO OFFLINE", PageBase.LocatorType.AccessibilityId,ignoreException); }
    public WebElement Text_AvailableTrips() { return findElement("Available Bungiis", PageBase.LocatorType.AccessibilityId); }
    public WebElement Link_View_AvailableTrips() { return findElement("//XCUIElementTypeStaticText[@name='View Available Bungiis']", LocatorType.XPath); }

    //public WebElement GoOnline_Btn() { return findElement("GO ONLINE", PageBase.LocatorType.AccessibilityId); }
    public WebElement GoOnline_Btn() { return findElement("//XCUIElementTypeStaticText[@name='OFFLINE']", LocatorType.XPath); }
    //public WebElement GoOffline_Btn() { return findElement("GO OFFLINE", PageBase.LocatorType.AccessibilityId); }
    public WebElement GoOffline_Btn() { return findElement("//XCUIElementTypeStaticText[@name='ONLINE']", LocatorType.XPath); }


    public WebElement NavigationBar_Status (boolean ...ignoreException) { return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName, ignoreException); }
    //	public WebElement Text_NavigationBar () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

    //public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath,ignoreException); }
    public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar", LocatorType.XPath,ignoreException); }
    public WebElement Text_DriverNavigationBar (String screen) { return findElement("//XCUIElementTypeStaticText[@name='"+screen+"']", LocatorType.XPath); }
    public WebElement Text_LoginNavigationBar (String screen) { return findElement("//XCUIElementTypeNavigationBar[@name='"+screen+"']", LocatorType.XPath); }
    public WebElement Text_HomeLoginNavigationBar () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeImage", LocatorType.XPath); }
    public WebElement NavigationBar_Text() {return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName); }
    public WebElement Text_Bungii_Completed() { return findElement("//XCUIElementTypeStaticText[@name='Bungii completed']",LocatorType.XPath);}
    public WebElement Header_ItemizedEarnings() { return findElement("//XCUIElementTypeStaticText[@name=\"ITEMIZED EARNINGS\"]",LocatorType.XPath);}
    public WebElement Header_Earnings() { return findElement("//XCUIElementTypeStaticText[@name=\"EARNINGS\"]",LocatorType.XPath);}

    //public WebElement Button_AppMenu () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
    public WebElement Button_AppMenu (boolean ...ignoreException) { return findElement("**/XCUIElementTypeNavigationBar/XCUIElementTypeButton", LocatorType.ClassChain, ignoreException); }
//'**/XCUIElementTypeNavigationBar/XCUIElementTypeButton

    public WebElement Text_DriverName() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath); }
    public WebElement Text_DriverName1(String driverName) { return findElement("//XCUIElementTypeStaticText[@name='"+driverName+"']", PageBase.LocatorType.XPath); }
    public WebElement Text_DriverInfo() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
    public WebElement Text_DriverInfoOnline() { return findElement("//XCUIElementTypeStaticText[@name='ONLINE']/following::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
    public WebElement Button_DriverStatus() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeButton", PageBase.LocatorType.XPath); }

    public WebElement AppMenu_AvailableTrip() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'AVAILABLE BUNGIIS'", LocatorType.Predicate); }
    public WebElement AppMenu_Home() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'HOME'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Account() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'ACCOUNT'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_AccountInfo() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'ACCOUNT INFO'", PageBase.LocatorType.Predicate); }

    public WebElement Button_MenuBack() {
        //  return findElement("//XCUIElementTypeButton[@name='LOG IN']", LocatorType.XPath);
        return findElement("type == 'XCUIElementTypeButton' AND name == 'Back'", LocatorType.Predicate);

    }

    public WebElement AppMenu_ScheduledTrip() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'SCHEDULED BUNGIIS'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_FAQ() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'FAQ'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_EARNINGS() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'EARNINGS'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_TripAlertSettings() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'ALERT SETTINGS'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_PrivacyPolicy() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'PRIVACY POLICY'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Feedback() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'FEEDBACK'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Store() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'BUNGII STORE'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_LEADERBOARD() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'LEADERBOARD'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_LogOut1(boolean ...ignoreException) { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'LOGOUT'", PageBase.LocatorType.Predicate,ignoreException); }
  //  public WebElement AppMenu_LogOut() { return findElement("//XCUIElementTypeStaticText[@name=\"LOGOUT\"]", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_LogOut() { return findElement("//XCUIElementTypeStaticText[@name=\"LOGOUT\"]/parent::XCUIElementTypeCell", LocatorType.XPath); }

    public List<WebElement> FilledStars() {return findElements("//*[contains(@name, 'rating filled star icon')]", LocatorType.XPath);}
    public List<WebElement> HalfFilledStar() {return findElements("//*[contains(@name, 'rating half filled star icon')]", LocatorType.XPath);}
    public List<WebElement> UnselectedStars() {return findElements("//*[contains(@name, 'rating unselected star icon')]", LocatorType.XPath);}

    public WebElement Text_CommonQuestions() { return findElement("type == 'XCUIElementTypeOther' AND name == 'Common Questions'", PageBase.LocatorType.Predicate); }
    public WebElement Text_Leaderboard() { return findElement("//XCUIElementTypeOther[@name=\"Driver Of The Month\"]/following-sibling::XCUIElementTypeOther", LocatorType.XPath); }
    public WebElement Text_ScheduledBungiis() { return findElement("//XCUIElementTypeTable[@name=\"No Bungiis, You don't have any scheduled\u2028Bungiis at this time.\"]", LocatorType.XPath); }
    public WebElement Text_AvailableTripsData() { return findElement("//XCUIElementTypeStaticText[@name=\"No Bungiis available\"]", LocatorType.XPath); }
    public WebElement Text_Earnings() { return findElement("//XCUIElementTypeStaticText[@name=\"Disbursement info\"]", LocatorType.XPath); }
    public WebElement Text_TripAlertSettings() { return findElement("//XCUIElementTypeButton[@name=\"Delivery Alerts\"]", LocatorType.XPath); }
    public WebElement Text_SMSAlertSettings() { return findElement("//XCUIElementTypeButton[@name=\"SMS Alerts\"]", LocatorType.XPath); }
    public WebElement Text_Privacy() {return findElement("//XCUIElementTypeStaticText[@name=\"Privacy\"]",LocatorType.XPath);}
    public WebElement Text_NoDelivery() {return findElement("//XCUIElementTypeStaticText[@name=\"No Bungiis available\"]/following-sibling::XCUIElementTypeStaticText",LocatorType.XPath);}
    public WebElement Button_Back() {return findElement("//XCUIElementTypeButton[@name=\"Back\"]",LocatorType.XPath);}
    public WebElement Text_Feedback() { return findElement("//XCUIElementTypeStaticText[@name=\"Send us your feedback\"]", LocatorType.XPath); }
    public WebElement Text_Store() { return findElement("//XCUIElementTypeStaticText[@name=\"BUNGII STORE\"]", LocatorType.XPath); }
    public WebElement Text_ApplicationError(boolean ...ignoreException) { return findElement("//XCUIElementTypeStaticText[@name=\"An application error has occured.\"]", LocatorType.XPath,ignoreException); }
    public WebElement Text_MyStat() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'My Stats'", PageBase.LocatorType.Predicate); }
    public WebElement Text_TotalTrip() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Total Trips'", PageBase.LocatorType.Predicate); }
    public WebElement Text_TripMonths() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Trips / Month'", PageBase.LocatorType.Predicate); }
    public WebElement Text_EarningMonth() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Earnings / Month'", PageBase.LocatorType.Predicate); }
    public WebElement Text_Total_Earnings() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Total Earnings'", PageBase.LocatorType.Predicate); }
    public WebElement Text_Total_Tips() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Total Tips'", PageBase.LocatorType.Predicate); }
    public WebElement Text_My_Rating() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'My Rating'", PageBase.LocatorType.Predicate); }
    public WebElement Link_Itemized_Earnings() { return findElement("type == 'XCUIElementTypeLink' AND name == 'Click here to view itemized earnings Itemized Earnings'", PageBase.LocatorType.Predicate); }


    public WebElement Application_Name(boolean ...ignoreException) {return findElement("XCUIElementTypeApplication", LocatorType.ClassName,ignoreException); }

    //Itemized Earnings Page
    public WebElement Button_ItemizedEarnings() {return findElement("//XCUIElementTypeButton[@name=\"Itemized earnings\"]",LocatorType.XPath);}
    public WebElement Text_ItemizedEarnings() {return findElement("//XCUIElementTypeOther/XCUIElementTypeStaticText[2]",LocatorType.XPath);}
    public WebElement Button_BackItemizedEarnings() {return findElement("//XCUIElementTypeButton[@name=\"Back\"]",LocatorType.XPath);}
    public WebElement Dropdown_StartDate(){return findElement("//XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]", LocatorType.XPath);}
    public WebElement Dropdown_EndDate(){return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]", LocatorType.XPath);}
    public WebElement Calendar_StartDate(){return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[4]/XCUIElementTypeOther/XCUIElementTypeOther", LocatorType.XPath);}
    public WebElement Button_Cancel(){return findElement("//XCUIElementTypeButton[@name=\"Cancel\"]", LocatorType.XPath);}

    //Earnings page
    public WebElement Dropdown_SelectYear(){return findElement("//XCUIElementTypeOther[@name=\"My Stats\"]/XCUIElementTypeTextField", LocatorType.XPath);}
    public WebElement Text_Disclaimer(){return findElement("//XCUIElementTypeStaticText[@name=\"This info does not include today’s trips.\"]", LocatorType.XPath);}
    public WebElement Text_MilesDriven(){return findElement("//XCUIElementTypeStaticText[@name=\"MILES DRIVEN\"]", LocatorType.XPath);}
    public WebElement Text_WorkHours(){return findElement("//XCUIElementTypeStaticText[@name=\"WORK HOURS\"]", LocatorType.XPath);}
    public WebElement Text_NoOfTrips(){return findElement("(//XCUIElementTypeStaticText[@name=\"TRIPS\"])[1]", LocatorType.XPath);}
    public WebElement Text_DisbursementInfo(){return findElement("//XCUIElementTypeStaticText[@name=\"Disbursement info\"]", LocatorType.XPath);}

    //Admin Driver Page
    public WebElement Icon_DriverEarnings(){return findElement("//img[@title='Driver Earnings']", LocatorType.XPath);}
    public WebElement Link_ViewTrips(){return findElement("//p/a[text()='View']", LocatorType.XPath);}
    public WebElement Text_DriverEarnings(){return findElement("//div[@class='card-title h5']/h1[@class='info']", LocatorType.XPath);}


    //driver duo rating page
    public WebElement Header_RateDuoTeammate(){return findElement("//XCUIElementTypeStaticText[@name=\"Rate duo teammate\"]", LocatorType.XPath);}

    public WebElement Header_Referral(){return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[@name=\"REFERRAL\"]", LocatorType.XPath);}

    public WebElement Header_ReferralHistory(){return findElement("//XCUIElementTypeStaticText[@name=\"REFERRAL HISTORY\"]", LocatorType.XPath);}

    public WebElement Text_PaymentSetting() { return findElement("//XCUIElementTypeStaticText[@name='Payment settings']", LocatorType.XPath);}
    public WebElement Text_PaymentSettingInfo() { return findElement("//XCUIElementTypeStaticText[@name='Would you like to change the default payment settings for your future deliveries?']", LocatorType.XPath);}
    public WebElement Option_2xWeek() { return findElement("//XCUIElementTypeCell/XCUIElementTypeStaticText[@name='2x week']", LocatorType.XPath);}
    public WebElement Option_SameDay() { return findElement("//XCUIElementTypeCell/XCUIElementTypeStaticText[@name='Same day*']", LocatorType.XPath);}
    public WebElement Button_Close() { return findElement("//XCUIElementTypeButton[@name='Close']", LocatorType.XPath);}
    public WebElement Button_Confirm() { return findElement("//XCUIElementTypeButton[@name='Confirm']", LocatorType.XPath);}
    public WebElement Header_Searching(){return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[@name=\"SEARCHING...\"]", LocatorType.XPath);}
    public WebElement Header_Invite(){return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText[@name=\"INVITE\"]", LocatorType.XPath);}

}
