package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiProgressPage extends PageBase {
    public WebElement Title_Status(boolean ... ignoreException) { return findElement("com.bungii.customer:id/toolbar_title", LocatorType.Id,ignoreException); }

    //------Page Titles--------------------------------------------------------------
    public WebElement PageTitle () { return findElement("com.bungii.customer:id/toolbar_title",LocatorType.Id); }

    //------Estimate and Customer Cancel Statuses----------------------------------------------------------
    public WebElement BungiiStatus_Enroute () { return findElement("com.bungii.customer:id/pickup_details_status_1",LocatorType.Id); }

    public WebElement BungiiStatus_Arrived () { return findElement("com.bungii.customer:id/pickup_details_status_2",LocatorType.Id); }

    public WebElement BungiiStatus_LoadingItem () { return findElement("com.bungii.customer:id/pickup_details_status_3",LocatorType.Id); }

    public WebElement BungiiStatus_DrivingToDropOff () { return findElement("com.bungii.customer:id/pickup_details_status_4",LocatorType.Id); }

    public WebElement BungiiStatus_UnloadingItem () { return findElement("com.bungii.customer:id/pickup_details_status_5",LocatorType.Id); }

    //------Location Details---------------------------------------------------------
    public WebElement Bungii_LocationTitle () { return findElement("com.bungii.customer:id/pickup_details_address_name",LocatorType.Id); }

    public WebElement Bungii_Location () { return findElement("com.bungii.customer:id/pickup_details_address_value",LocatorType.Id); }

    public WebElement Bungii_ETA () { return findElement("com.bungii.customer:id/pickup_details_estimate",LocatorType.Id); }

    //------driver Details-----------------------------------------------------------
    public WebElement Bungii_Driver_Image () { return findElement("com.bungii.customer:id/pickup_details_caller_image",LocatorType.Id); }

    public WebElement Bungii_Driver_Title () { return findElement("com.bungii.customer:id/pickup_details_caller_title",LocatorType.Id); }

    public WebElement Bungii_Driver_Name () { return findElement("com.bungii.customer:id/pickup_details_caller_name",LocatorType.Id); }

    public WebElement Bungii_Driver_RatingBar () { return findElement("com.bungii.customer:id/ratingbarPickupDetails",LocatorType.Id); }

    public WebElement Button_Bungii_Driver_SMS () { return findElement("com.bungii.customer:id/pickup_details_sms_button",LocatorType.Id); }

    public WebElement Button_Bungii_Driver_Call () { return findElement("com.bungii.customer:id/pickup_details_call_button",LocatorType.Id); }


    //More incas of duo
    public WebElement Button_DuoMore1() { return findElements("com.bungii.customer:id/pickup_details_contact_options", LocatorType.Id).get(0); }
    public WebElement Button_DuoMore2() { return findElements("com.bungii.customer:id/pickup_details_contact_options", LocatorType.Id).get(1); }

    public WebElement Button_DuoDriver_SMS() { return findElement("com.bungii.customer:id/caller_communication_mode_sms_textview", LocatorType.Id); }
    public WebElement Button_DuoDriver_Call() { return findElement("com.bungii.customer:id/caller_communication_mode_phone_call_textview", LocatorType.Id); }

    public WebElement Button_CancelOnMoreOptions() { return findElement("com.bungii.customer:id/caller_cancel", LocatorType.Id); }
    public WebElement Text_DuoDriver1_Name() { return findElements("com.bungii.customer:id/pickup_details_person_name", LocatorType.Id).get(0); }
    public WebElement Text_DuoDriver2_Name() { return findElements("com.bungii.customer:id/pickup_details_person_name", LocatorType.Id).get(1); }

    public WebElement Alert_Message(boolean ... ignoreException){return findElement("android:id/message",LocatorType.Id,ignoreException);}



}
