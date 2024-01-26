package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TripDetailsPage extends  PageBase{
    public WebElement Button_Accept() { return findElement("com.bungii.driver:id/btn_accept_bungii", PageBase.LocatorType.Id); }

    public WebElement Text_Pickup_Location_line1() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_Pickup_Location_line2() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Text_DropOff_Location_line1() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_DropOff_Location_line2() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Text_Total_Distance() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Label_PickupInstructions() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[3]", LocatorType.XPath); }
    public WebElement Label_DropOffInstructions() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[5]", LocatorType.XPath); }
    public WebElement Text_PickupInstructions() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[4]", LocatorType.XPath); }
    public WebElement Text_DropOffInstructions() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[6]", LocatorType.XPath); }
    public WebElement Link_DeliveryDetails(){return  findElement("dLabel",LocatorType.Id);}
    public WebElement List_ViewDeliveries(){return  findElement("//td/div[@class='dropdown open']/ul/li/*[contains(text(),'Delivery Details')]",LocatorType.XPath);}

    public WebElement Text_PickupLocationAdminPortal(){return  findElement("//tr/td[text()=\"Pickup Location\"]/following-sibling::td/strong",LocatorType.XPath);}
    public WebElement Label_DriverPickupInstructionsAdminPortal(){return  findElement("//div/h4[text()=\"Driver Pickup Instructions\"]",LocatorType.XPath);}
    public WebElement Label_DriverDropOffInstructionsAdminPortal(){return  findElement("//div/h4[text()=\"Driver Dropoff Instructions\"]",LocatorType.XPath);}
    public WebElement Text_DriverDropOffInstructionsServiceAdminPortal(){return  findElement("//div/div[6]/div[2]/p",LocatorType.XPath);}
    public WebElement Text_DriverPickupInstructionsServiceAdminPortal(){return  findElement("//div/div[7]/div[2]/p",LocatorType.XPath);}

    public WebElement Label_PickupInstructionScheduleBungii() { return findElement("//android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Label_DropOffInstructionsScheduleBungii() { return findElement("//android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_PickupInstructionsScheduleBungii() { return findElement("//android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Text_DropOffInstructionsScheduleBungii() { return findElement("//android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.TextView[2]", LocatorType.XPath); }


}
