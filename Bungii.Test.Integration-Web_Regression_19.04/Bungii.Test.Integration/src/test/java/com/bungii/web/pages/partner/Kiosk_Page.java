package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Kiosk_Page extends PageBase {
    //Setting link
    //Setting icon is changed to Menu - CORE-3106
    public WebElement Link_Setting() { return findElement("//i[@title='Menu']", LocatorType.XPath); }

    //Setting Close
    public WebElement Link_SettingClose() { return findElement("//i[@title='Close']", LocatorType.XPath); }

    //Admin Access Password
    public WebElement Textbox_Password() { return findElement("//input[@id='name']",LocatorType.XPath);}

    //Admin Access Text
    public WebElement Text_Admin_Access() { return findElement("//span[@class='MuiTypography-root MuiFormControlLabel-label MuiTypography-body1']",LocatorType.XPath);}

    //Continue Button
    public WebElement Button_Continue() { return  findElement("//div[@class='btn-group']//button[@class='btn'][contains(text(),'Continue')]",LocatorType.XPath);}

    //Incorrect Password Text
    public WebElement Text_Incorrect_Password() { return findElement("//div[@class='invalid-feedback d-block text-left']",LocatorType.XPath);}

    //Admin Access toggle button
    public WebElement Button_Admin_Access() { return findElement("//input[@name='kioskMode']",LocatorType.XPath);}



}
