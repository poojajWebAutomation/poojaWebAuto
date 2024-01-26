package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ScheduledBungiisPage extends PageBase {

    //------Scheduled Bungii page---------------------------------------------------------------------
    public WebElement Title_ScheduledBungiis () { return findElement("com.bungii.customer:id/toolbar_main_title", LocatorType.Id); }

    public WebElement Text_NoBungiis () { return findElement("//android.widget.LinearLayout[@id='com.bungii.customer:id/scheduled_bungii_list_rl_container_nobungii']/android.widget.TextView[@instance='1']", LocatorType.XPath); }

    public WebElement Text_NoBungiis_msg () { return findElement("//android.widget.LinearLayout[@id='com.bungii.customer:id/scheduled_bungii_list_rl_container_nobungii']/android.widget.TextView[@instance='2']", LocatorType.XPath); }

    public WebElement Button_SaveMoney (boolean... ignoreException) { return findElement("com.bungii.customer:id/my_bungii_save_money", LocatorType.Id, ignoreException); }

    public WebElement Text_TimeToNextBungii() { return findElement("com.bungii.customer:id/fragment_scheduled_bungii_textview_hours", LocatorType.Id); }

    public WebElement Alert_ScheduledTripAccepted () { return findElement("android:id/message", LocatorType.Id); }

    public WebElement Button_OK () { return findElement("android:id/button1", LocatorType.Id); }

    //------Scheduled Bungii Details-------------------------------------------------------------
    public WebElement ScheduledBungii_01 () { return findElement("com.bungii.customer:id/container_scheduled_trip_row", LocatorType.Id); }

    public WebElement Text_BungiiScheduledTime_01 () { return findElement("com.bungii.customer:id/scheduled_row_textview_scheduleddatetime", LocatorType.Id); }

    public WebElement Text_BungiiStatus_01 () { return findElement("com.bungii.customer:id/scheduled_row_textview_status", LocatorType.Id); }

    public WebElement Text_TripStatus() { return findElement("com.bungii.customer:id/item_my_bungii_tv_pickup_cost", LocatorType.Id);}

}
