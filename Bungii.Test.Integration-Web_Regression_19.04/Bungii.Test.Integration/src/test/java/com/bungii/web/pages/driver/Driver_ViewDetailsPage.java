package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_ViewDetailsPage extends PageBase {

    public WebElement Label_Header_MyStats () {return findElement("//div[@class='panel panel-default getDriveApp my-stats']/div/h4[text()='My Stats']", LocatorType.XPath);}

    public WebElement Label_Statistics (String xpath) {return  findElement(xpath, LocatorType.XPath);}

    public  WebElement Label_Statistics_Total_Earnings_Per_Year () {return findElement("//h1[contains(text(),'Total Earnings per year')]/parent::div/div/div/h2", LocatorType.XPath); }

    public WebElement Label_TotalTripsCount () {return findElement("//p[contains(text(),'Total Trips')]/following-sibling::h3", LocatorType.XPath);}

    public WebElement Label_TotalTripsCount (String xpath) {return findElement(xpath, LocatorType.XPath);}

    public WebElement Calendar_TripsDaterange () {return findElement("tripsDateRange", LocatorType.Id);}

    public  WebElement Calendar_FromDateMonth () {return  findElement("//div[@class='drp-calendar left']/div/table/thead/tr/th[@class='month']",LocatorType.XPath);}

    public WebElement Calendar_ToDateMonth () {return  findElement("//div[@class='drp-calendar right']/div/table/thead/tr/th[@class='month']",LocatorType.XPath);}

    public WebElement Calendar_PreviousMonth () {return  findElement("//th[@class='prev available']",LocatorType.XPath);}

    public WebElement Calendar_NextMonth () {return  findElement("//th[@class=\"next available\"]", LocatorType.XPath);}

    public  WebElement Calendar_FromDate () {return  findElement("//th[text()='Jan 2019']/parent::tr/parent::thead/parent::table/tbody/tr/td[text()='22']", LocatorType.XPath);}

    public WebElement Calendar_ToDate () {return findElement("//th[text()='Jan 2020']/parent::tr/parent::thead/parent::table/tbody/tr/td[text()='23']",LocatorType.XPath);}

    public WebElement Label_SelectedDateRange () {return findElement("//span[text()='01/22/2019 - 01/23/2020']",LocatorType.XPath);}

    public WebElement Button_Apply () {return findElement("//button[text()='Apply']",LocatorType.XPath);}

    public WebElement Label_SearchResultDateRange () {return findElement("//p[contains(text(),\"(Jan 22, 2019 - Jan 23, 2020)\")]",LocatorType.XPath);}

}
