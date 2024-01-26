package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_PartnerPortalPage extends PageBase {

    public WebElement Label_PartnerListHeader () { return  findElement("//h4[text()='Partners']", LocatorType.XPath);}
    public WebElement Button_NewPartner () { return  findElement("add-partner-btn", LocatorType.Id);}

    public WebElement TextBox_PartnerName () { return  findElement("partner-name", LocatorType.Id);}
    public WebElement TextBox_Phone () { return  findElement("partner-phone", LocatorType.Id);}
    public WebElement TextBox_Email () { return  findElement("partner-email", LocatorType.Id);}
    public WebElement TextBox_PartnerFirstName () { return  findElement("partner-first-name", LocatorType.Id);}
    public WebElement TextBox_PartnerLastName () { return  findElement("partner-last-name", LocatorType.Id);}
    public WebElement TextBox_AddressOne () { return  findElement("partner-address-1", LocatorType.Id);}
    public WebElement TextBox_AddressTwo () { return  findElement("partner-address-2", LocatorType.Id);}
    public WebElement TextBox_City () { return  findElement("partner-city", LocatorType.Id);}
    public WebElement DropDown_State () { return  findElement("partner-state", LocatorType.Id);}
    public WebElement TextBox_Zip () { return  findElement("partner-zipcode", LocatorType.Id);}
    public WebElement TextBox_Comments () { return  findElement("partner-comments", LocatorType.Id);}
    public WebElement Swtich_PartnerStatus () { return  findElement("partner-status-switch", LocatorType.Id);}
    public WebElement Value_PartnerStatus () { return  findElement("partner-status", LocatorType.Id);}

    public WebElement Input_Logo () { return  findElement("partner-img-upload", LocatorType.Id);}
    public WebElement DropDown_PartnerType() { return  findElement("partner-type", LocatorType.Id);}

    public WebElement Button_Save () { return  findElement("//button[text()='Save']", LocatorType.XPath);}
    public WebElement Button_Cancel () { return  findElement("//button[text()='Cancel']", LocatorType.XPath);}
    public WebElement TextBox_Search () { return  findElement("partner-search", LocatorType.Id);}

    public WebElement Label_PartnerNameError () { return  findElement("partner-name-error", LocatorType.Id);}
    public WebElement Label_PartnerTypeError () { return  findElement("partner-type-error", LocatorType.Id);}
    public WebElement Label_PartnerPhoneError () { return  findElement("partner-phone-error", LocatorType.Id);}
    public WebElement Label_PartnerEmailError () { return  findElement("partner-email-error", LocatorType.Id);}
    public WebElement Label_PartnerAddress1Error () { return  findElement("partner-address-1-error", LocatorType.Id);}
    public WebElement Label_PartnerCityError () { return  findElement("partner-city-error", LocatorType.Id);}
    public WebElement Label_PartnerStateError () { return  findElement("partner-state-error", LocatorType.Id);}
    public WebElement Label_PartnerZipCodeError () { return  findElement("partner-zipcode-error", LocatorType.Id);}
    public WebElement Menu_UnlockPartners() { return  findElement("//a[text()='Unlock Portals']", LocatorType.XPath);}

}
