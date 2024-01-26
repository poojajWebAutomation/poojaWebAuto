package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_ReferralSourcePage extends PageBase {

    public WebElement Header_Source () { return findElement("//th[text()='Sources']", LocatorType.XPath); }

    public WebElement Header_AccountsCreated () { return findElement("//th[text()='Accounts Created']", LocatorType.XPath); }

    public WebElement Header_PercentageOfTotalAC () { return findElement("//tr/th[3][text()='Percentage of total']", LocatorType.XPath); }

    public WebElement Header_TripsCompleted () { return findElement("//th[text()='Deliveries Completed']", LocatorType.XPath); }

    public WebElement Header_PercentageOfTotalTC () { return findElement("//tr/th[5][text()='Percentage of total']", LocatorType.XPath); }

    public WebElement Menu_ReferralSource(boolean... ignoreException) { return findElement("//a[text()='Referral Sources']", LocatorType.XPath,ignoreException); }

    public WebElement Title_ReferralSourcePage (boolean...ignoreException) { return findElement("(//h4[text()='Referral Sources'])[1]", LocatorType.XPath,ignoreException); }

    public WebElement Button_Search ( ) { return findElement("//button[text()='Search']", LocatorType.XPath); }

    public WebElement TextBox_FromDate ( ) { return findElement("FromDate", LocatorType.Name); }

    public WebElement TextBox_ToDate ( ) { return findElement("ToDate", LocatorType.Name); }

    public WebElement Label_FromDateError ( ) { return findElement("//p[text()='From Date is required']", LocatorType.XPath); }

    public WebElement Label_ToDateError ( ) { return findElement("//p[text()='To Date is required']", LocatorType.XPath); }

}