package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class DashBoardPage extends PageBase {

    public WebElement Button_Trips() {
        return findElement("//span[contains(text(),'Deliveries')]", LocatorType.XPath);
    }

    public WebElement Button_PromoCode() {
        return findElement("//span[contains(text(),'Promo Codes')]", LocatorType.XPath);
    }
    public WebElement Link_StandardCodes() {
        return findElement("//a[contains(text(),'Standard Codes')]", LocatorType.XPath);
    }
    public WebElement Button_Marketing() {
        return findElement("//span[contains(text(),'Marketing')]", LocatorType.XPath);
    }

    public WebElement Button_ReferralSource() {
        return findElement("//a[contains(text(),'Referral Sources')]", LocatorType.XPath);
    }

    public WebElement Button_ScheduledTrips() {
        return findElement("//a[contains(text(),'Scheduled Deliveries')]", LocatorType.XPath);
    }

    public WebElement Button_LiveTrips() {
        return findElement("//a[contains(text(),'Live Deliveries')]", LocatorType.XPath);
    }
    public WebElement Button_Deliveries() {
        return findElement("//a[contains(text(),'Completed Deliveries')]", LocatorType.XPath);
    }

    public WebElement Button_Drivers() {
        return findElement("//span[contains(text(),'Drivers')]", LocatorType.XPath);
    }
    public WebElement Button_Customers() {
        return findElement("//span[contains(text(),'Customers')]", LocatorType.XPath);
    }
    public WebElement Menu_Geofences(){return findElement("//span[contains(text(),'Geofences')]",LocatorType.XPath);}

    public WebElement Checkbox_Active_geofence() {return findElement("activeGeofenceOnly",LocatorType.Id);}

    public WebElement List_Geofence() {return findElement("dropdownMenuButton" , LocatorType.Id);}
    public WebElement TextBox_SearchGeofence() {return findElement("myInput" , LocatorType.Id);}
    public WebElement Button_ApplyGeofence() {return findElement("btnApply" , LocatorType.Id);}
    public WebElement Checkbox_Geofence(String geofence) {return findElement(String.format("//span[contains(.,'%s')]/preceding-sibling::span/label/input",geofence) , LocatorType.XPath);}

    //delivery details page
    public WebElement Text_SearchCriteria(){return  findElement("SearchCriteria",LocatorType.Id);}
    public WebElement Button_Search(){return  findElement("btnSearch",LocatorType.Id);}
    public WebElement Text_BungiiStatus(){return findElement("//th[text()='Status']/following::td[12]", LocatorType.XPath);}
    public WebElement Text_AdminName(){return  findElement("//div[2]/p[1]/strong",LocatorType.XPath);}
    public WebElement Button_Submit () { return findElement("CustomerCancel", LocatorType.Name); }
    public WebElement Text_DeliveryMiles(){return  findElement("//td[text()='Delivery Distance']/following::td[1]/strong",LocatorType.XPath);}


}
