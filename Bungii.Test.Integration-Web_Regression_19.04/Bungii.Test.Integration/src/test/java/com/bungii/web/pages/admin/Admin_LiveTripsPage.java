package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_LiveTripsPage extends PageBase {

    public WebElement Menu_LiveTrips () { return findElement("//a[contains(text(),'Live Deliveries')]", LocatorType.XPath); }

    //public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Button_ApplyGeofenceFilter () { return findElement("btnApplyGeofence", LocatorType.Id); }

    public WebElement TextBox_Search_Field() { return findElement("SearchCriteria",LocatorType.Id);}

    public WebElement Button_Search() { return findElement("btnSearch",LocatorType.Id);}

    public WebElement Button_UpdateBungii() { return findElement("//button[contains(text(),'UPDATE BUNGII')]",LocatorType.XPath);}

    public WebElement Button_CalculateCost() { return findElement("//button[text()='Calculate cost']",LocatorType.XPath);}

    public WebElement RadioButton_EditDeliveryStatus() { return findElement("radio4",LocatorType.Id);}

    public WebElement RadioButton_DeliveryCanceled() { return findElement("cancel",LocatorType.Id);}

    public WebElement Text_Page_Header() {return findElement("//h4[contains(text(),'Scheduled')]|//h4[contains(text(),'Live')]",LocatorType.XPath);}

    public WebElement Text_DeliveryHighlight() {return findElement("//tbody/tr[1]",LocatorType.XPath);}

    public WebElement Text_AllDeliveryHighlight() {return findElement("//tbody[@id =\"TripListsTBody\"]/tr[1]",LocatorType.XPath);}

    public WebElement Message_AdminCompleteConfirm() {return findElement("//span[@class='confirm-text']",LocatorType.XPath);}

    public WebElement Icon_Hover() {return findElement("//tr[1]/td/a/i[@class='fa fa-exclamation']",LocatorType.XPath);}

    public WebElement Label_Tooltip() {return findElement("//tr[1]/td/a/i[@class='fa fa-exclamation']/following-sibling::span",LocatorType.XPath);}

    public WebElement Icon_LoadingIconSearching(boolean...IgnoreException) {return findElement("//div[@class=\"driver-search\"]",LocatorType.XPath,IgnoreException);}

    public WebElement Icon_LoadingIconStoppedSearching() {return findElement("//div[@class=\"driver-search complete\"]",LocatorType.XPath);}

    public WebElement Text_DeliveryStatusScheduledDeliveriesAndLiveDeliveries() {return findElement("//tr[1]/td[12]",LocatorType.XPath);}

    public WebElement Text_DeliveryStatusAllDeliveries() {return findElement("//tr/td[12]",LocatorType.XPath);}

    public WebElement Dropdown_Icon(){return  findElement("//div[@class='threedoticon']/img",LocatorType.XPath);}

    public WebElement Option_Edit(boolean...IgnoreException){return  findElement("//a[text()='Edit']",LocatorType.XPath,IgnoreException);}

    public WebElement Text_ScheduledDate(){return findElement("//td[4]/a",LocatorType.XPath);}

    public WebElement Button_LoadMap() {return findElement("//button[text()='Load map']",LocatorType.XPath);}

    public WebElement Button_ZoomOut() {return findElement("//button[@title='Zoom out']",LocatorType.XPath);}

    public WebElement Image_DriverLocation(String driver,boolean...IgnoreException) {return findElement("//div[@title='"+driver+"']/img",LocatorType.XPath,IgnoreException);}


}
