package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LiveTripsPage extends PageBase{
    public WebElement Text_SearchCriteria(){return  findElement("SearchCriteria", PageBase.LocatorType.Id);}
    public WebElement Text_SearchPeroid(){return  findElement("SearchForPeriod", PageBase.LocatorType.Id);}

    public WebElement Button_Search(){return  findElement("btnSearch", PageBase.LocatorType.Id);}
    public WebElement Button_StartDateSort(){return  findElement("span-StartDate", PageBase.LocatorType.Id);}
    public WebElement Button_ManuallyEndBungii(boolean ...ignoreException){return  findElement("btnEndPickup", PageBase.LocatorType.Id,ignoreException);}
    public WebElement Button_RowOne(){return  findElement("//tr[1]/td[4]/a", LocatorType.XPath);}
    public WebElement Text_TripStatus(){return  findElement("//h4[contains(text(),'Status')]/following::span[1]", LocatorType.XPath);}
    public WebElement Text_Code(){return  findElement("//td[text()='Code']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_CodeType(){return  findElement("//td[text()='Code Type']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_CodeValue(){return  findElement("//td[text()='Code Value']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_PromoCode(){return  findElement("//td[text()='Promo Value']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_EstimatedCharge(){return  findElement("//td[text()='Estimated Charge']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_TripPayment(){return  findElement("//td[text()='Delivery Payment']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Link_ManuallyEndBungii () { return findElement("btnEndPickup", LocatorType.Id); }
    //public WebElement Image_Three_Dot(){return findElement("//img[@id='dLabel']",LocatorType.XPath);}
    public WebElement Link_DeliveryDetails(){return findElement("//a[contains(text(),'Delivery Details')]",LocatorType.XPath);}
    public WebElement Button_RowOneAll(){return  findElement("//tr[1]/td[3]", LocatorType.XPath);}

    public WebElement Textbox_PickupEndDate () { return findElement("pickupEndDate", LocatorType.Id); }

    public WebElement Textbox_PickupEndTime () { return findElement("pickupEndTime", LocatorType.Id); }

    public WebElement Dropdown_ddlpickupEndTime () { return findElement("ddlpickupEndTime", LocatorType.Id); }

    public WebElement Button_CalculateCost () { return findElement("btnCost", LocatorType.Id); }

    public WebElement Button_Cancel () { return findElement("btnCancel", LocatorType.Id); }

    public WebElement Button_Confirm () { return findElement("btnConfirm", LocatorType.Id); }
    public WebElement Menu_Trips () { return findElement("adminmenu-trips", LocatorType.Id); }
    public WebElement Dropdown_SearchForPeriod () { return findElement("SearchForPeriod", LocatorType.Name); }

    public WebElement Menu_LiveTrips () { return findElement("adminmenu-livetrips", LocatorType.Id); }

   // public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Button_ApplyGeofenceFilter () { return findElement("btnApplyGeofence", LocatorType.Id); }

    //Driver Referral Page
    public WebElement Icon_Referral (boolean ...ignoreException) { return findElement("//XCUIElementTypeImage[@name='referral-invite-foreground']", LocatorType.XPath,ignoreException); }
    public WebElement Text_SubHeader () { return findElement("//XCUIElementTypeStaticText[@name=\"YOUR $5 GUARANTEE IS WAITING*\"]", LocatorType.XPath); }
    public WebElement Icon_DollarSign () { return findElement("//XCUIElementTypeImage[@name=\"referral-invite-foreground\"]", LocatorType.XPath); }
    public WebElement Text_Instructions () { return findElement("//XCUIElementTypeStaticText[@name=\"Make upto $5 for each new driver you invite that completes 10 deliveries.*\"]", LocatorType.XPath); }
    public WebElement Tab_ReferralCode () { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText", LocatorType.XPath); }
    public WebElement Text_TapToCopy () { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]", LocatorType.XPath); }
    public WebElement Button_Share () { return findElement("//XCUIElementTypeButton[@name='SHARE']", LocatorType.XPath); }
    public WebElement Text_ShareToContacts () { return findElement("//XCUIElementTypeStaticText[@name='Share to contacts']", LocatorType.XPath); }
    public WebElement Text_ShareOnMedia () { return findElement("//XCUIElementTypeStaticText[@name='Share on social media']", LocatorType.XPath); }
    public WebElement Text_MoreInformation () { return findElement("//XCUIElementTypeStaticText[@name='More information']", LocatorType.XPath); }
    public WebElement Text_ReferralHistory () { return findElement("//XCUIElementTypeStaticText[@name='Referral history']", LocatorType.XPath); }
    public WebElement Text_Footer() { return findElement("//XCUIElementTypeStaticText[@name=\"*Guarantee will be paid based on value of current promotion at the time when your referral completes their application.\"]", LocatorType.XPath); }
    public WebElement Button_Back () { return findElement("//XCUIElementTypeButton[@name='Back']", LocatorType.XPath); }
    public WebElement Text_UpdatedSubHeader (String amt) { return findElement("//XCUIElementTypeStaticText[@name=\"YOUR $"+amt+" GUARANTEE IS WAITING*\"]", LocatorType.XPath); }

    //Home page
    public WebElement Tab_ReferralMessage () { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther", LocatorType.XPath); }
    public WebElement Text_ReferralHeader () { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText[1]", LocatorType.XPath); }
    public WebElement Button_Invite () { return findElement("//XCUIElementTypeButton[@name=\"INVITE\"]", LocatorType.XPath); }

    public WebElement Text_EarningsInfo () { return findElement("//XCUIElementTypeStaticText[contains(@name,'Same day payment')]", LocatorType.XPath); }
    public WebElement Icon_EarningsInfo () { return findElement("//XCUIElementTypeImage[@name=\"icon-info\"]", LocatorType.XPath); }

}
