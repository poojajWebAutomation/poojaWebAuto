package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Partner_Done extends PageBase {

    //Trip Schedule Done text
    public WebElement Text_Schedule_Done_Success_Header() { return findElement("//h1[@class='heading']", LocatorType.XPath); }

    //Setting dropdown
    public WebElement Dropdown_Setting(boolean...ignoreException) { return findElement("//div[@class='dropdown-menu-right dropdown']",LocatorType.XPath, ignoreException);}

    //Track Deliveries button
    public WebElement Button_Track_Deliveries() { return findElement("track-deliveries",LocatorType.Id);}

    //Filter
    public WebElement Dropdown_Filter() { return findElement("//span[contains(text(),'Filter:')]",LocatorType.XPath);}

    //Checked/Unchecked radio button
    public WebElement Checkbox_Check_UnCheck_All() { return findElement("//label[contains(text(),'Check / uncheck all')]",LocatorType.XPath);}

    //Apply button
    public WebElement Button_Apply() { return findElement("//button[text()=\"Apply\"] | //button[text()=\"APPLY\"]",LocatorType.XPath);}

    // Report link in dropdown
    public WebElement Text_Report() { return findElement("//span[contains(text(),'Reports')]",LocatorType.XPath);}
}

