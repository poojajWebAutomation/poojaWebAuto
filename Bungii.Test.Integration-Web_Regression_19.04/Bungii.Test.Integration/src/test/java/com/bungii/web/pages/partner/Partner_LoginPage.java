package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_LoginPage extends PageBase {

    //Partner Password Field
    public WebElement TextBox_PartnerLogin_Password() { return findElement("formBasicPassword", LocatorType.Id); }

    //Partner Sign In button
    public WebElement Button_Sign_In() { return findElement("login", LocatorType.Id); }

    //Blank-Incorrect password on Sing In
    public WebElement Message_Blank_Incorrect_Password() { return findElement("//div[@class='invalid-feedback d-block text-left p-l-18']", LocatorType.XPath);}

    public WebElement Input_OrderNo() { return findElement("//input[@data-field='Order Number']", LocatorType.XPath); }

    public WebElement Input_EmployeeNo() { return findElement("(//input[@class='form-control'])[9]", LocatorType.XPath);}
}
