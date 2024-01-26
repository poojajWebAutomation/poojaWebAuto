package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_Delivery_StatusPage extends PageBase {

    //Delivery Status Title
    public WebElement Label_DeliveryDetailsTitle() { return findElement("//label[contains(text(),'Delivery Status')]", LocatorType.XPath); }

    //Delivery Status Text
    public WebElement Text_DeliveryStatus(String status){return findElement(String.format("//div[@class='delivery-status']/div/span[contains(text(),'%s')]",status),LocatorType.XPath);}

    //Pickup Time
    public WebElement Text_PickupTime(String datetime){return findElement(String.format("//div[contains(text(),'Pick Up Time')]/following-sibling::div[contains(text(),'%s')]",datetime),LocatorType.XPath);}

    //Distance
    public WebElement Text_Distance(String distance){return findElement(String.format("//div[contains(text(),'DISTANCE')]/following-sibling::div[contains(text(),'%s')]",distance),LocatorType.XPath);}

    //Est. Delivery Time
    public WebElement Text_EstDeliveryTime(String time){return findElement(String.format("//div[contains(text(),'Est. Delivery Time')]/following-sibling::div[contains(text(),'%s')]",time),LocatorType.XPath);}

    //Pickup Address
    public WebElement Text_PickupAddress(String address){return findElement(String.format("//label[contains(text(),'Pickup Address:')]/following-sibling::p[contains(text(),'%s')]",address),LocatorType.XPath);}

    //Delivery Address
    public WebElement Text_DeliveryAddress(String address){return findElement(String.format("//label[contains(text(),'Delivery Address:')]/following-sibling::p[contains(text(),'%s')]",address),LocatorType.XPath);}

    //Driver(s)
    public WebElement Text_Driver1(String address){return findElement(String.format("//h6[contains(text(),'DRIVER(S)')]/following-sibling::div/div/div/div/p[contains(text(),'%s')]",address),LocatorType.XPath);}

    //Calling icon
    public WebElement Icon_CallDriver(){return findElement("//img[@alt='driver']/following-sibling::span/img[@class='call']",LocatorType.XPath);}

    //Successful Delivery Completion Message
    public WebElement Label_SuccessMessage(){return findElement("//span[contains(text(),'Successfully Completed!')]",LocatorType.XPath);}
    public WebElement Text_SuccessMessage(){return findElement("//p[contains(text(),'The delivery you booked has been completed. Thank you for using Bungii.')]",LocatorType.XPath);}

    //Canceled Delivery Message
    public WebElement Label_CanceledMessage(){return findElement("//span[contains(text(),'Delivery Cancelled')]",LocatorType.XPath);}
    public WebElement Text_CanceledMessage(){return findElement("//p[contains(text(),'The delivery you booked has been canceled. You could either reschedule or book a delivery again. Thank you for using Bungii.')]",LocatorType.XPath);}

}
