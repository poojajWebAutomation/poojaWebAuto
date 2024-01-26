package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_PaymentMethodsPage extends PageBase {

    public WebElement Label_PartnerCardHeader () { return findElement("//h4[text()='Partner Cards']",LocatorType.XPath);}

    public WebElement Menu_Partners() { return findElement("(//span[contains(text(),'Partners')])[2]", LocatorType.XPath); }

    public WebElement Menu_PartnersSubMenu () { return findElement("//a[contains(text(),'Partner List')]", LocatorType.XPath); }

    public WebElement Menu_LocationsSubMenu () { return findElement("adminmenu-partner-locations-menu", LocatorType.Id); }
    public WebElement Menu_UnlockPartnersSubMenu () { return findElement("//a[contains(text(),'Unlock Portals')]", LocatorType.XPath); }

    public WebElement Menu_PaymentMethodsSubMenu () { return findElement("//a[contains(text(),'Portal Payments')]", LocatorType.XPath); }

    public WebElement Menu_BungiiCards(){return findElement("//span[contains(text(),'Bungii Card')]", LocatorType.XPath);}

    public WebElement Dropdown_Partners () { return findElement("//select[@class='partner-card-select form-select']", LocatorType.XPath); }

    public WebElement Button_AddPaymentMethod () { return findElement("//button[contains(text(),'Add Payment Method')]", LocatorType.XPath); }

    public WebElement TextBox_CardNumber () { return findElement("credit-card-number", LocatorType.Id); }

    public WebElement TextBox_ExpirationDate () { return findElement("expiration", LocatorType.Id); }

    public WebElement TextBox_Cvv () { return findElement("cvv", LocatorType.Id); }

    public WebElement TextBox_PostalCode () { return findElement("postal-code", LocatorType.Id); }

    public WebElement Checkbox_Default () { return findElement("PaymentRequestModel_IsDefault", LocatorType.Id); }

    public WebElement Button_Save (boolean ignoreException) { return findElement("//button[contains(text(),'Save')]", LocatorType.XPath,ignoreException); }

    public WebElement Button_Cancel () { return findElement("//button[contains(text(),'Cancel')]", LocatorType.XPath); }

    public WebElement Label_SuccessMessageForPartner() { return findElement("//span[contains(text(),'Partner Payment Method added successfully.')]", LocatorType.XPath); }

    public WebElement Label_SuccessMessageForBungii() { return findElement("//span[contains(text(),'Bungii Payment Method added successfully.')]", LocatorType.XPath); }

    public WebElement Label_ErrorContainerPayWithCard(){ return  findElement("//div[contains(text(),'Please check your information and try again.')]", LocatorType.XPath);}

    public WebElement Label_ErrorContainerInvalidCarNumber(){ return  findElement("//div[contains(text(),'This card number is not valid.')]", LocatorType.XPath);}

    public WebElement Label_ErrorContainerInvalidExpiryDate(){ return  findElement("//div[contains(text(),'This expiration date is not valid.')]", LocatorType.XPath);}

    public WebElement Label_ErrorContainerInvalidCVV(){ return  findElement("//div[contains(text(),'This security code is not valid.')]", LocatorType.XPath);}

}