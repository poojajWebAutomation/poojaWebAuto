package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class AccountsPage extends PageBase{
    public WebElement Header_AccountPage(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='ACCOUNT']", PageBase.LocatorType.XPath,ignoreException); }

    public WebElement Text_Name() { return findElement("com.bungii.driver:id/account_info_textview_name", PageBase.LocatorType.Id); }

    public WebElement Text_Phone() { return findElement("//android.widget.LinearLayout[@resource-id='com.bungii.driver:id/account_info_layout_phone']/android.widget.TextView[2]", PageBase.LocatorType.XPath); }

    public WebElement Text_Email() { return findElement("com.bungii.driver:id/account_info_textview_email", PageBase.LocatorType.Id); }

    public WebElement Link_Account_Info() { return findElement("//android.view.ViewGroup[@resource-id='com.bungii.driver:id/layout_account_settings_constraint_layout'][1]",LocatorType.XPath);}

    public WebElement Link_Account_Settings() { return findElement("//android.view.ViewGroup[@resource-id='com.bungii.driver:id/layout_account_settings_constraint_layout'][2]",LocatorType.XPath);}

    public WebElement Link_Privacy_Policy() { return findElement("//android.view.ViewGroup[@resource-id='com.bungii.driver:id/layout_account_settings_constraint_layout'][3]",LocatorType.XPath);}

    public WebElement Link_Logout() { return findElement("//android.view.ViewGroup[@resource-id='com.bungii.driver:id/layout_account_settings_constraint_layout'][4]",LocatorType.XPath);}

    public WebElement Button_Navigate_Up(boolean...ignoreException) { return findElement("//android.widget.ImageButton[contains(@content-desc,'Navigate up')]",LocatorType.XPath,ignoreException);}

    //public WebElement Button_Navigate_Up() { return findElement("Navigate up",LocatorType.AccessibilityId);}

}
