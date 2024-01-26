package com.bungii.android.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class DashBoardPage extends PageBase {

    public WebElement Button_Trips() {
        return findElement("//span[text()='Deliveries']", LocatorType.XPath);
    }

    public WebElement Button_PromoCode() {
        return findElement("adminmenu-promocodes-menu", LocatorType.Id);
    }

    public WebElement Button_Marketing() {
        return findElement("//p/a/span[text()=\"Marketing\"]", LocatorType.XPath);
    }
    public WebElement Link_StandardCodes() {
        return findElement("adminmenu-promocode", LocatorType.Id);
    }

    public WebElement Link_ReferralSource() {
        return findElement("//a[text()=\"Referral Sources\"]", LocatorType.XPath);
    }

    public WebElement Button_ScheduledTrips() {
        return findElement("//ul[@id=\"side-menu\"]/li/ul/li/a[text()=\"Scheduled Deliveries\"]", LocatorType.XPath);
    }

    public WebElement Button_CompletedTrips() {
        return findElement("//ul[@id=\"side-menu\"]/li/ul/li/a[text()=\"Completed Deliveries\"]", LocatorType.XPath);
    }
    public WebElement Button_LiveTrips() {
        return findElement("//ul[@id=\"side-menu\"]/li/ul/li/a[text()=\"Live Deliveries\"]", LocatorType.XPath);
    }

    public  WebElement Button_Customers() { return findElement("//span[text()='Customers']", LocatorType.XPath);}
    public WebElement List_Geofence() {return findElement("dropdownMenuButton" , LocatorType.Id);}
    public WebElement TextBox_SearchGeofence() {return findElement("myInput" , LocatorType.Id);}
    public WebElement Button_ApplyGeofence() {return findElement("btnApply" , LocatorType.Id);}
    public WebElement Checkbox_Geofence(String geofence) {return findElement(String.format("//span[contains(.,'%s')]/preceding-sibling::span/label/input",geofence) , LocatorType.XPath);}
    public WebElement Message_NoCustomerFound() { return findElement("//h5[contains(text(),'No Customers found.')]",LocatorType.XPath);}
    public WebElement Message_NoDeliveriesFound() { return findElement("//h5[contains(text(),'No Deliveries found.')]",LocatorType.XPath);}
    public WebElement Header_StatusChange() { return findElement("//h2[text()='STATUS CHANGE SUCCESSFUL']",LocatorType.XPath);}

    public WebElement Text_AdminName(){return  findElement("//div[2]/p[1]/strong",LocatorType.XPath);}
    public WebElement Menu_CompletedDeliveries() {return findElement("//a[contains(text(),'Completed Deliveries')]",LocatorType.XPath);}

}
