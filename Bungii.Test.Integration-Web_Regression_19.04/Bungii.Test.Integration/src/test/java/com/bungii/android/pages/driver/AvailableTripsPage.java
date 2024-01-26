package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AvailableTripsPage extends PageBase {


    //public List<WebElement> List_AvailableBungiis() { return findElements("//*[@resource-id='com.bungii.driver:id/row_available_pickup_container']/android.widget.RelativeLayout", LocatorType.XPath); }
    public List<WebElement> List_AvailableBungiis() { return findElements("com.bungii.driver:id/available_trips_recyclerview_list", LocatorType.Id); }

    public WebElement NavigationBar_Text (boolean ... ignoreException) { return findElement("com.bungii.driver:id/toolbar_main_title", LocatorType.Id ,ignoreException    ); }

    //------Available Trips Page--------------------------------------------------------------------
    public WebElement Row_AvailableTrip_01() { return findElement("com.bungii.driver:id/available_trips_recyclerview_list", LocatorType.Id); }

    public WebElement Trip01_CustomerName() { return findElement("com.bungii.driver:id/row_available_pickup_drivername", LocatorType.Id); }

    public WebElement Trip01_TimeFromHome() { return findElement("com.bungii.driver:id/row_available_pickup_textview_time_home", LocatorType.Id); }

    //------Trip Details Page-----------------------------------------------------------------------
    public WebElement Image() { return findElement("com.bungii.driver:id/pickup_item_detail_image", LocatorType.Id); }

    public WebElement Text_TimeToPickUp() { return findElement("com.bungii.driver:id/activity_pickup_request_time_to_pickup_textview", LocatorType.Id); }

    public WebElement Text_TripDistance() { return findElement("com.bungii.driver:id/activity_pickup_request_trip_distance_textview", LocatorType.Id); }

    public WebElement Text_ScheduledDate() { return findElement("com.bungii.driver:id/activity_pickup_request_scheduled_date_textview", LocatorType.Id); }

    public WebElement Text_ScheduledTime() { return findElement("com.bungii.driver:id/activity_pickup_request_scheduled_time_textview", LocatorType.Id); }

    public WebElement Button_Accept() { return findElement("com.bungii.driver:id/activity_pickup_request_accept_available_pickup_button", LocatorType.Id); }

    public WebElement Button_Back() { return findElement("//android.widget.ImageButton[contains(@content-desc,\"Navigate up\")]", LocatorType.XPath); }

    public WebElement Row_AvailableTrip() {return findElements("//*[@resource-id='com.bungii.driver:id/row_available_pickup_imageview_arrow']",LocatorType.XPath).get(0);}

    public WebElement Row_SecondAvailable() {return findElements("//*[@resource-id='com.bungii.driver:id/row_available_pickup_imageview_arrow']",LocatorType.XPath).get(1);}

    public List<WebElement> Image_SelectBungiis()  {return findElements("com.bungii.driver:id/row_available_pickup_container",LocatorType.Id);}

    public WebElement Partner_Name() { return findElement("com.bungii.driver:id/tv_business_partner", LocatorType.Id);}

    public WebElement Partner_Name_For_Enroute() { return findElement("//*[@resource-id='com.bungii.driver:id/appCompatTextView27']", LocatorType.XPath);}

    public List<WebElement> Row_CustomerTrips(){return findElements("//*[@resource-id='com.bungii.driver:id/row_available_pickup_imageview_arrow' and @resource-id[not(contains(., 'com.bungii.driver:id/row_available_pickup_iv_pickuptype'))]]",LocatorType.XPath);}

    public WebElement Text_FromHomeMiles() { return findElement("com.bungii.driver:id/row_available_pickup_textview_time_home", LocatorType.Id);}

    public WebElement Text_DriverEarning() { return findElement("com.bungii.driver:id/tv_earnings", LocatorType.Id);}
    public WebElement Text_DriverEarningSchedulePage() { return findElement("com.bungii.driver:id/tv_earnings", LocatorType.Id);}

    public WebElement Text_RejectionPopup(boolean...ignoreException) {return findElement("com.bungii.driver:id/appCompatTextView21",LocatorType.Id,ignoreException);}

    public WebElement Text_RejectionReason(int i){return findElement("//android.widget.ListView/android.widget.LinearLayout["+i+"]/android.widget.LinearLayout/android.widget.TextView",LocatorType.XPath);}

    public WebElement Button_Cancel() {return findElement("com.bungii.driver:id/alert_dialog_list_btn_cancel",LocatorType.Id);}

    public WebElement Button_Submit() {return findElement("com.bungii.driver:id/alert_dialog_list_btn_submit",LocatorType.Id);}

    public WebElement RadioButton_LatestRejectionReason() {return findElement("//android.view.ViewGroup/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RadioButton",LocatorType.XPath);}

    public WebElement Text_CustomerName(boolean...ignoreException) {return findElement("com.bungii.driver:id/row_available_pickup_drivername",LocatorType.Id,ignoreException);}

    public WebElement Text_NoBungiisAvailable(boolean...ignoreException) {return findElement("com.bungii.driver:id/appCompatTextView21",LocatorType.Id,ignoreException);}

    public WebElement PageTitle_BungiiDetails() { return findElement("//android.widget.LinearLayout[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath);}

    public WebElement Label_SoloLift() {return findElement("com.bungii.driver:id/appCompatTextView37",LocatorType.Id);}
    public WebElement Text_SoloLiftMessage() { return findElement("com.bungii.driver:id/appCompatTextView39", LocatorType.Id);}
    public WebElement Label_CustomerHelp() {return findElement("com.bungii.driver:id/appCompatTextView37",LocatorType.Id);}
    public WebElement Text_CustomerHelpMessage() { return findElement("com.bungii.driver:id/appCompatTextView39", LocatorType.Id);}
    public WebElement Label_DuoLift() {return findElement("com.bungii.driver:id/appCompatTextView37",LocatorType.Id);}
    public WebElement Text_DuoLiftMessage() { return findElement("com.bungii.driver:id/appCompatTextView39", LocatorType.Id);}

    public List <WebElement> List_AllDeliveriesDriverApp()         {return findElements("//android.widget.RelativeLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout/android.view.ViewGroup",LocatorType.XPath);}
    public List <WebElement> List_AllCustomerDeliveries() {return findElements("//android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout",LocatorType.XPath);}

    public WebElement CustomerName(int number) {return findElement(String.format("//android.widget.RelativeLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[%d]/android.view.ViewGroup/android.widget.TextView[1]",number),LocatorType.XPath);}
    public WebElement Text_CustomerDeliveryTime(int number) {return findElement(String.format("//android.view.ViewGroup/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[%d]/android.widget.RelativeLayout/android.widget.TextView[1]",number),LocatorType.XPath);}

    //customer name xpath
    public List <WebElement> List_CustomerName()         {return findElements("//android.widget.TextView[contains(@resource-id,'com.bungii.driver:id/layout_waypoint_name')]",LocatorType.XPath);}
}
