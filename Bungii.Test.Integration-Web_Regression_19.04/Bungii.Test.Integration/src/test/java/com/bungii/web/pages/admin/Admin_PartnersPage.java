package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_PartnersPage extends PageBase {

   // public WebElement Menu_Partners () { return findElement("adminmenu-partners", LocatorType.Id); }
    public WebElement Menu_Partners () { return findElement("//ul[@id='side-menu']/li/p/span[contains(text(),'Partners')]", LocatorType.XPath); }

    public WebElement Assign_Partners () { return findElement("//span[contains(text(),'Partner List')]", LocatorType.XPath); }

    public WebElement Text_Invalid_Password_Message () { return findElement("//div[@class='form-group']/div", LocatorType.XPath); }

    public WebElement Label_Unlock_Partners () { return findElement("//h4[@class='Partners-title']",LocatorType.XPath);}
        //  public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Button_Unlock (String lockedPartner,boolean...ignoreException) { return findElement("//tr/td/div[contains(text(),'"+lockedPartner+"')]/following::td/div/button", LocatorType.XPath,ignoreException); }


}
