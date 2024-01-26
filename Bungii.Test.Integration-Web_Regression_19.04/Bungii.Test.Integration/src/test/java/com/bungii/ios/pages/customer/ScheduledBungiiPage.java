package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ScheduledBungiiPage extends PageBase {
    public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }


    public WebElement Button_SaveMoney() {
        return findElement("SAVE MONEY", LocatorType.Name);
    }

    public WebElement Cell_TripInformation() {
        return findElement(
                "//XCUIElementTypeButton[@name='SAVE MONEY']/preceding-sibling::XCUIElementTypeTable/XCUIElementTypeCell", LocatorType.XPath);
    }

    public WebElement Cell_TripTimeInformation() {
        return findElement(
                "//XCUIElementTypeButton[@name='SAVE MONEY']/preceding-sibling::XCUIElementTypeTable/XCUIElementTypeOther", LocatorType.XPath);
    }

    public WebElement Image_Bungii() {
        return findElement(
                "//XCUIElementTypeButton[@name='SAVE MONEY']/preceding-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeImage", LocatorType.XPath);
    }

    public WebElement Text_BungiiTime() {
        return findElement(
                "//XCUIElementTypeButton[@name='SAVE MONEY']/preceding-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText", LocatorType.XPath);
    }

    public WebElement Text_BungiiStatus() {
        return findElement(
                "//XCUIElementTypeButton[@name='SAVE MONEY']/preceding-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[1]", LocatorType.XPath);
    }

    public WebElement Table_NoBungii() {
        return findElement("//XCUIElementTypeTable", LocatorType.XPath);
    }
    public List<WebElement> List_SchBungii() {
        return findElements("//XCUIElementTypeImage[@name='disclosure-arrow-right']/preceding-sibling::XCUIElementTypeStaticText[last()]", LocatorType.XPath);
    }

    public WebElement Trip_Status() {
        return findElement("(//XCUIElementTypeImage[@name=\"disclosure-arrow-right\"]/preceding-sibling::XCUIElementTypeStaticText[1])[2]", LocatorType.XPath);
    }

    public WebElement Cell_FirstTrip() {
        return findElement("//XCUIElementTypeImage[@name=\"profile_placeholder\"]/parent::XCUIElementTypeCell", LocatorType.XPath);
    }

    public WebElement Label_MinutesSolo() {
        return findElement("//XCUIElementTypeImage[@name='bungii_type-solo']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);
    }
    public WebElement Label_MinutesDuo() {
        return findElement("//XCUIElementTypeImage[@name='bungii_type-duo']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);
    }
    public WebElement Text_DropOffLocation_LineOne1() { return  findElement("//XCUIElementTypeTable/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[8]", LocatorType.XPath);}
    public WebElement Text_PickupLocation_LineOne1() { return  findElement("//XCUIElementTypeTable/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[6]", LocatorType.XPath);}

    public WebElement Cell_SecondTrip() { return  findElement("//XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]", LocatorType.XPath);}
    public WebElement Notification_ConflictingDelivery() { return  findElement("//XCUIElementTypeStaticText[@name=\"There is another delivery conflicting with this one. Please check your Scheduled List.\"]", LocatorType.XPath);}
    public WebElement Button_NotificationOk() { return  findElement("//XCUIElementTypeButton[@name=\"OK\"]", LocatorType.XPath);}
    public WebElement Cell_FirstScheduledTrip() { return  findElement("//XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]", LocatorType.XPath);}

    public WebElement Text_CustomerTrip_DateTime()
    {return findElement("//XCUIElementTypeApplication[@name=\"Bungii QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText",LocatorType.XPath);}

    //public WebElement Text_ScheduledTime() { return findElements("//XCUIElementTypeOther[@name='TIME TO NEXT SCHEDULED BUNGII']/following::XCUIElementTypeCell/XCUIElementTypeStaticText",LocatorType.XPath).get(0);}
    public WebElement Text_ScheduledTime() { return findElements("//XCUIElementTypeStaticText[@name=\"Next Scheduled Bungii in \"]/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText",LocatorType.XPath).get(0);}

    public WebElement Customer_ScheduledDelivery(String DateTime) { return findElement("//XCUIElementTypeStaticText[@name='"+DateTime+"']",LocatorType.XPath);}
}
