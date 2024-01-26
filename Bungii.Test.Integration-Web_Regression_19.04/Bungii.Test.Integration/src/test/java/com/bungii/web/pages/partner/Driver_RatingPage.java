package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_RatingPage extends PageBase {

    public WebElement Label_Delivery_Status() { return findElement("//label[contains(text(),'Delivery Status')]",LocatorType.XPath);}
    public WebElement Text_Successfully_Completed() { return findElement("//label[contains(text(),'Delivery Status')]",LocatorType.XPath);}
    public WebElement Text_Sucessfully_Completed_Para() { return findElement("//p[contains(text(),'The delivery you booked has been completed. Thank ')]",LocatorType.XPath);}
    public WebElement Text_Rate_Your_Drivers() { return findElement("//h6[contains(text(),'Rate Your Drivers')]",LocatorType.XPath);}
    public WebElement Label_Driver_Name(String Driver) {return findElement("//label[contains(text(),'"+Driver+"')]",LocatorType.XPath);}
    public WebElement Image_All_Stars(boolean ...ignoreException) { return findElement("//span[@id='driver_0']",LocatorType.XPath,ignoreException);}
    public WebElement Image_All_Stars2(boolean ...ignoreException) { return findElement("//span[@id='driver_1']",LocatorType.XPath,ignoreException);}
    public WebElement Image_All_Ok_Solo() { return findElement("//div[@class='d-none d-md-block d-lg-block text-center desktop-1-driver col-md-3']/img[@alt='Completed']",LocatorType.XPath);}
    public WebElement Image_All_Ok_Duo() { return findElement("//div[@class='d-none d-md-block d-lg-block text-center desktop col-md-3']/img[@alt='Completed']",LocatorType.XPath);}
    public WebElement Image_Stars(int i) {return findElements("//span[@id='driver_0']/span/span[2]/img[@alt='starFilled']",LocatorType.XPath).get(i);}
    public WebElement Image_Stars2(int i) {return findElements("//span[@id='driver_1']/span/span[2]/img[@alt='starFilled']",LocatorType.XPath).get(i);}
    public WebElement Button_Submit(boolean ...ignoreException) {return findElement("//input[@value='Submit']",LocatorType.XPath,ignoreException);}
    public WebElement Text_Rating_Submitted_Successfully() { return findElement("//span[contains(text(),'Ratings submitted successfully.')]",LocatorType.XPath);}

}
