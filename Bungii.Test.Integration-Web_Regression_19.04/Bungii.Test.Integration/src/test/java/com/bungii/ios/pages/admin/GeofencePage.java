package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class GeofencePage extends PageBase {

    public WebElement Select_ChicagoGeofence() { return findElement("//td[contains(text(),'Chicago')]", LocatorType.XPath);}

    public WebElement Button_Settings(){return findElement("//button[contains(text(),'Settings')]",LocatorType.XPath);}

    public WebElement Button_SaveGeofenceSettings(){return findElement("//button[contains(text(),'Save')]",LocatorType.XPath);}

    public WebElement TextBox_MinimumScheduledtimeforduo(){return findElement("//td[contains(text(),'Minimum scheduled time for Duo trip')]/following-sibling::td/div/div/div/div/input",LocatorType.XPath);}
    public WebElement TextBox_MinimumScheduledtimeforsolo(){return findElement("//td[contains(text(),'Minimum scheduled time for Solo trip')]/following-sibling::td/div/div/div/div/input",LocatorType.XPath);}
    public WebElement Dropdown_Status() { return findElement("drpStatus", LocatorType.Id); }

    public WebElement Button_Scale() { return findElement("btnAddGeofence", LocatorType.Id); }

    public WebElement Button_Save() { return findElement("btnSave", LocatorType.Id); }
    public WebElement Text_ErrorScheduleTimeForDuo() { return findElement("attributeValueEarliestScheduleTimeDuo-error", LocatorType.Id);}
    public WebElement Text_ErrorScheduleTimeForSolo() { return findElement("attributeValueEarliestScheduleTimeSolo-error", LocatorType.Id);}

    public WebElement Button_Cancel() { return findElement("btnCancel", LocatorType.Id);}

    public WebElement Button_Edit() { return findElement("btnEdit", LocatorType.Id); }

    public WebElement Input_ReferralAmount() { return findElement("//td[contains(text(),'Driver referral Bonus amount')]/following-sibling::td/div/div/div/input", LocatorType.XPath); }
    public WebElement Input_NoOfDeliveries() { return findElement("//td[contains(text(),'Driver referral bonus payout # deliveries')]/following-sibling::td/div/div/input", LocatorType.XPath); }

}
