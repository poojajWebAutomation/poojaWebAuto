package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BungiiRequest extends PageBase {

    public WebElement Navigation_Header() { return findElement("//*[@resource-id='com.bungii.driver:id/pickup_request_toolbar']/android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath); }


    //------On Demand Request Alert
    public WebElement Alert_Msg(boolean ignoreException) { return findElement("com.bungii.driver:id/notification_alert_message", LocatorType.Id,ignoreException); }

    public WebElement Alert_Msg(boolean ...ignoreexception) { return findElement("com.bungii.driver:id/notification_alert_message", LocatorType.Id, ignoreexception); }

    public WebElement AlertButton_View() { return findElement("com.bungii.driver:id/notification_alert_button_positive", LocatorType.Id); }

    public WebElement AlertButton_Cancel(boolean ...ignoreException) { return findElement("com.bungii.driver:id/notification_alert_button_negative", LocatorType.Id,ignoreException); }

    public WebElement Alert_NewBungiiRequest(boolean ignoreException){return findElement("//android.widget.TextView[@text='New Bungii Request']",LocatorType.XPath,ignoreException);}

    public WebElement Alert_NewBungiiRequestMessage(boolean ignoreException){return findElement("com.bungii.driver:id/appCompatTextView34",LocatorType.Id);}

    //------On Demand Request
//    public WebElement Button_Accept() { return findElement("com.bungii.driver:id/activity_pickup_request_accept_bungii_button", LocatorType.Id); }
    public WebElement Button_Accept() { return findElement("com.bungii.driver:id/btn_accept_bungii", LocatorType.Id); }

    public WebElement Button_Reject(boolean ...ignoreException) { return findElement("com.bungii.driver:id/activity_pickup_request_reject_bungii_button", LocatorType.Id,ignoreException); }


    public WebElement Alert_MsgTitle() { return findElement("com.bungii.driver:id/notification_alert_title", LocatorType.Id); }

    public WebElement Text_ValueEarning() { return findElement("//android.widget.TextView[@text='EARNINGS']/following-sibling::android.widget.TextView", LocatorType.XPath); }
  //REMOVE THIS
    public WebElement Text_Earning() { return findElement("//android.widget.TextView[@text='EARNINGS']", LocatorType.XPath); }

    public WebElement Text_Distance() { return findElement("//*[contains(@text,'TO PICKUP')]", LocatorType.XPath); }
    public WebElement Text_ValueDistance() { return findElement("//android.widget.TextView[@resource-id='com.bungii.driver:id/pickup_request_tv_estimated_duration']/preceding-sibling::android.widget.TextView", LocatorType.XPath); }

    public WebElement Text_PickupLocation_LineOne () { return findElement("//android.widget.ImageView[@resource-id='com.bungii.driver:id/pickup_request_iv_pickup']/following-sibling::android.widget.LinearLayout/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_PickupLocation_LineTwo () { return findElement("//android.widget.ImageView[@resource-id='com.bungii.driver:id/pickup_request_iv_pickup']/following-sibling::android.widget.LinearLayout/android.widget.TextView[2]", LocatorType.XPath); }

    public WebElement Text_DropOffLocation_LineOne () { return findElement("//android.widget.ImageView[@resource-id='com.bungii.driver:id/pickup_request_iv_dropoff']/following-sibling::android.widget.LinearLayout/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_DropOffLocation_LineTwo () { return findElement("//android.widget.ImageView[@resource-id='com.bungii.driver:id/pickup_request_iv_dropoff']/following-sibling::android.widget.LinearLayout/android.widget.TextView[2]", LocatorType.XPath); }


    public WebElement Text_PickupLocation_LineOne1 () { return findElements("//android.widget.LinearLayout/following::android.widget.TextView", LocatorType.XPath).get(2);}
    public WebElement Text_PickupLocation_LineTwo2 () { return findElements("//android.widget.LinearLayout/following::android.widget.TextView", LocatorType.XPath).get(3);}
    public WebElement Text_DropOffLocation_LineOne1 () { return findElements("//android.widget.LinearLayout/following::android.widget.TextView", LocatorType.XPath).get(4);}
    public WebElement Text_DropOffLocation_LineTwo2 () { return findElements("//android.widget.LinearLayout/following::android.widget.TextView", LocatorType.XPath).get(5);}

    public WebElement Text_DistanceValue() { return findElements("//android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath).get(8);}
    public WebElement Button_StartBungii(boolean...ignoreException) { return findElement("//android.widget.Button[@text='Start Bungii']", LocatorType.XPath,ignoreException);}
    public WebElement Button_CancelBungii() { return findElement("com.bungii.driver:id/scheduled_bungii_details_tv_cancel_bungii", LocatorType.Id);}
//    public WebElement Button_Start() { return findElement("com.bungii.driver:id/activity_driver_scheduled_bungii_details_btn_start_trip", LocatorType.Id);}
    public WebElement Button_Start() { return findElement("com.bungii.driver:id/btn_start_bungii", LocatorType.Id);}

    public WebElement Text_CustomerNote(){return findElement("//androidx.recyclerview.widget.RecyclerView/following::android.widget.TextView[2]", LocatorType.XPath);}

    public WebElement Alert_Msg_Stay_Online() { return findElement("com.bungii.driver:id/appCompatTextView21", LocatorType.Id); }
    public WebElement Button_Stay_Online() { return findElement("com.bungii.driver:id/btnStayOnline_alert_dialog_controller", LocatorType.Id); }

    public WebElement Alert_ViewRequest() {return findElement("com.bungii.driver:id/appCompatButton2",LocatorType.Id);}

    public WebElement Button_No_Thanks() {return findElement("com.bungii.driver:id/appCompatTextView33",LocatorType.Id);}

    public WebElement Notification_AddressChanged() { return findElement("//android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout",LocatorType.XPath);}
    public WebElement Button_NotificationOk() { return findElement("//android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.Button",LocatorType.XPath);}

    //Live trip details
    public WebElement Text_DropOffAddress() { return findElement("//android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[4]",LocatorType.XPath);}
    public WebElement Text_PickUpAddress() { return findElement("com.bungii.driver:id/layout_waypoint_address",LocatorType.Id);}

    public WebElement Alert_DeliveryAccepted(boolean ignoreException){return findElement("//android.widget.TextView[@text='Delivery Accepted']",LocatorType.XPath,ignoreException);}

}
