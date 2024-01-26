package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DriverHomePage extends PageBase {

    public WebElement Generic_Element (boolean... ignoreException) { return findElement("//*[contains(@resource-id,\"com.bungii.driver\")]", LocatorType.XPath,ignoreException); }
    public WebElement Generic_HeaderElement (boolean... ignoreException) { return findElement("//android.view.View[@resource-id='com.bungii.driver:id/toolbar' or 'com.bungii.driver:id/toolbarLogin' or 'com.bungii.driver:id/toolbar_title']/android.widget.TextView | //android.view.ViewGroup[@resource-id='com.bungii.driver:id/toolbar' or 'com.bungii.driver:id/toolbarLogin']/android.widget.TextView", LocatorType.XPath,ignoreException); }

    public WebElement Generic_DriverCustomerApp(boolean ignoreException){return findElement("//*[contains(@resource-id,'com.bungii.driver')] | //*[contains(@resource-id,'com.bungii.customer')]", LocatorType.XPath,ignoreException);}
    // public WebElement Title_Status (boolean ... ignoreException) { return findElement("//*[@resource-id='com.bungii.driver:id/toolbar_main_title' or 'com.bungii.driver:id/toolbar_title']", LocatorType.XPath ,ignoreException    ); }
    public WebElement Title_Status (boolean ... ignoreException) { return findElement("//*[@resource-id='com.bungii.driver:id/toolbar_main_title'] | //*[@resource-id='com.bungii.driver:id/toolbar_title'] | //android.widget.TextView[@text='ALERT SETTINGS'] | //*[@resource-id='com.bungii.driver:id/activity_web_view_title']", LocatorType.XPath ,ignoreException    ); }

    public WebElement Button_NavigationBar (boolean ... ignoreException) { return findElement("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]", LocatorType.XPath, ignoreException); }
    public List<WebElement> Button_NavigationBarText () { return findElements("//*[@resource-id='com.bungii.driver:id/design_menu_item_text']", LocatorType.XPath); }
    public List<WebElement> Label_SubMenuText () { return findElements("//*[@resource-id='com.bungii.driver:id/layout_account_settings_tv_submenu']", LocatorType.XPath); }



    public WebElement Image_DriverProfilePhoto () { return findElement("com.bungii.driver:id/home_driver_profile_image", LocatorType.Id); }

   // public WebElement Button_OnlineOffline () { return findElement("com.bungii.driver:id/home_button_go_online", LocatorType.Id); }
    public WebElement Button_OnlineOffline () { return findElement("//*[contains(@resource-id,'com.bungii.driver:id/tvThumbState')]", LocatorType.XPath); }
   // public WebElement Link_AvailableTrips (boolean ...ignoreException) { return findElement("com.bungii.driver:id/home_textview_available_trips", LocatorType.Id,ignoreException); }
    public WebElement Link_AvailableTrips (boolean ...ignoreException) { return findElement("//android.widget.TextView[@text='View Available Bungiis']", LocatorType.XPath,ignoreException); }

    public WebElement Alert_NewBungii (boolean ...ignoreException) { return findElement("com.bungii.driver:id/notification_alert_message", LocatorType.Id,ignoreException); }
    public WebElement Text_DriverInfo () { return findElements("android.widget.TextView", LocatorType.ClassName).get(2); }
    public WebElement Text_DriverName () { return findElements("android.widget.TextView", LocatorType.ClassName).get(0); }
    public WebElement Text_RattingBar () { return findElement("android.widget.RatingBar", LocatorType.ClassName); }

    public WebElement Notification_AlertAccept () { return findElement("com.bungii.driver:id/notification_alert_button_positive", LocatorType.Id); }
    public WebElement Notification_AlertReject () { return findElement("com.bungii.driver:id/notification_alert_button_negative", LocatorType.Id); }

    public WebElement Text_CommonQuestions () { return findElement("//android.view.View[@text='WHAT IS THIS PAGE FOR?']",LocatorType.XPath); }
    public WebElement Text_CommonQuestions1 () { return findElement("//android.widget.TextView[@text='Common Questions']",LocatorType.XPath); }
    public WebElement Text_Leaderboard () { return findElement("//*[@resource-id='content']/android.view.View[2]/descendant::android.view.View[last()]/android.widget.TextView", LocatorType.XPath); }
    public WebElement Text_ScheduledBungiis () { return findElement("//android.widget.TextView[@text='No Bungiis']",LocatorType.XPath); }
    public WebElement Text_AvailableTrips () { return findElement("//android.widget.TextView[@text='No Bungiis available']",LocatorType.XPath); }
    public WebElement Text_NoDeliveriesAvailable () { return findElement( "//android.widget.TextView[2]",LocatorType.XPath); }
    //public WebElement Text_Earnings () { return findElement("//android.view.View[@text='DRIVER INFO']",LocatorType.XPath); }
    public WebElement Text_Earnings () { return findElement("//android.widget.TextView[@text='EARNINGS' and @resource-id='com.bungii.driver:id/toolbar_main_title']",LocatorType.XPath); }
    public WebElement Text_Account () { return findElement("//*[@resource-id='com.bungii.driver:id/account_info_textview_name']",LocatorType.XPath); }
    public WebElement Text_TripAlertSettings () { return findElement("//*[@resource-id='com.bungii.driver:id/text_settings_radio_trip_alerts']",LocatorType.XPath); }
    public WebElement Text_PrivacyPolicy () { return findElement("//*[@resource-id='com.bungii.driver:id/activity_web_view_title']",LocatorType.XPath); }
    public WebElement Text_Feedback() { return findElement("//*[@resource-id='com.bungii.driver:id/feedback_text_view_title']",LocatorType.XPath); }
    //public WebElement Text_Store () { return findElement("//android.view.View[@text='BUNGII STORE']",LocatorType.XPath); }
    public WebElement Text_Store () { return findElement("//android.widget.TextView[@text='STORE']",LocatorType.XPath); }
    public WebElement Text_Logout () { return findElement("//android.widget.TextView[@text='LOGIN']",LocatorType.XPath); }

    public WebElement Text_ErrorMessage(){ return findElement("//android.widget.TextView[@text='It looks like we ran into a hiccup. Please contact support@bungii.com for more information.']", LocatorType.XPath);}

    public WebElement Text_ScheduledBungiiSolo(boolean ignoreException) { return findElements("//*[@resource-id='com.bungii.driver:id/pickup_request_toolbar']/android.widget.LinearLayout/child::android.widget.TextView", LocatorType.XPath).get(0);}
    public WebElement Text_ScheduledBungiisSolo(boolean ignoreException) { return findElement("com.bungii.driver:id/toolbar_main_title", LocatorType.Id);}
    public WebElement Text_BungiiCompleted(boolean ignoreException) { return findElement("com.bungii.driver:id/pickup_summary_toolbar_title", LocatorType.Id);}

    public WebElement Button_Sure(boolean ignoreException){ return findElement("com.bungii.driver:id/button_location_permission_sure", LocatorType.Id,ignoreException);}

    public WebElement Text_OverriddenPrice() { return findElement("com.bungii.driver:id/scheduled_row_textview_status", LocatorType.Id);}

    //Driver Referral Page
    public WebElement Icon_Referral (boolean ...ignoreException) { return findElement("//android.widget.LinearLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.ImageView[1]", LocatorType.XPath,ignoreException); }
    public WebElement Text_SubHeader () { return findElement("//android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Icon_DollarSign () { return findElement("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageView", LocatorType.XPath); }
    public WebElement Text_Instructions () { return findElement("//android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Tab_ReferralCode () { return findElement("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView", LocatorType.XPath); }
    public WebElement Text_TapToCopy () { return findElement("//android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[3]", LocatorType.XPath); }
    public WebElement Button_Share () { return findElement("//android.widget.Button[contains(@text,\"SHARE\")]", LocatorType.XPath); }
    public WebElement Text_ShareToContacts () { return findElement("//android.widget.TextView[contains(@text,\"Share to contacts\")]", LocatorType.XPath); }
    public WebElement Text_ShareOnMedia () { return findElement("//android.widget.TextView[contains(@text,\"Share on social media\")]", LocatorType.XPath); }
    public WebElement Text_MoreInformation () { return findElement("//android.widget.TextView[contains(@text,\"More information\")]", LocatorType.XPath); }
    public WebElement Text_ReferralHistory () { return findElement("//android.widget.TextView[contains(@text,\"Referral history\")]", LocatorType.XPath); }
    public WebElement Text_Footer() { return findElement("//androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView", LocatorType.XPath); }
    public WebElement Button_Back () { return findElement("//android.widget.ImageButton[contains(@content-desc,\"Navigate up\")]", LocatorType.XPath); }

    //Home page
    public WebElement Tab_ReferralMessage () { return findElement("//android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]", LocatorType.XPath); }
    public WebElement Text_ReferralHeader () { return findElement("//android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_ReferralSubHeader () { return findElement("//android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Button_Invite () { return findElement("//android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[3]", LocatorType.XPath); }

    public WebElement Icon_Earnings (boolean ...ignoreException) { return findElement("com.bungii.driver:id/iv_earnings", LocatorType.Id,ignoreException); }
    public WebElement Text_EarningsInfo (boolean ...ignoreException) { return findElement("com.bungii.driver:id/tv_alert_info", LocatorType.Id,ignoreException); }
    public WebElement Text_OnlineOrOffline () { return findElement("//android.view.ViewGroup[@resource-id='com.bungii.driver:id/online_offline_switch_container']/android.widget.TextView", LocatorType.XPath); }

}