package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_GeofenceAtrributesPage extends PageBase{
    public WebElement Button_NewAttribute() { return findElement("btnCreateAttribute", PageBase.LocatorType.Id); }

    public WebElement TextBox_Key() { return findElement("GeofenceAttributeItem_Key", PageBase.LocatorType.Id); }

    public WebElement TextBox_DefaultValue() { return findElement("GeofenceAttributeItem_DefaultValue", PageBase.LocatorType.Id); };

    public WebElement TextBox_Description() { return findElement("GeofenceAttributeItem_Description", PageBase.LocatorType.Id); };

    public WebElement TextBox_Label() { return findElement("GeofenceAttributeItem_Label", PageBase.LocatorType.Id); };

    public WebElement Button_Save() { return findElement("btnSaveAttribute", PageBase.LocatorType.Id); }

    public WebElement Button_Cancel() { return findElement("//button[text()='Cancel']", PageBase.LocatorType.XPath); }

    public WebElement TextBox_SearchCriteria() { return findElement("SearchCriteria", PageBase.LocatorType.Id); }

    public WebElement Link_Logout(){return  findElement("//a[.='log out']", PageBase.LocatorType.XPath);}

    public WebElement Label_ErrorTextOnEmpty() { return findElement("error-summary-modal", PageBase.LocatorType.Id); }

    public WebElement Label_Geofence_Attributes () { return findElement("//h4[contains(text(),'Geofence Attributes')]",LocatorType.XPath);}

    public WebElement Menu_Attributes () { return findElement("//a[contains(text(),'Attributes')]",LocatorType.XPath);}

}
