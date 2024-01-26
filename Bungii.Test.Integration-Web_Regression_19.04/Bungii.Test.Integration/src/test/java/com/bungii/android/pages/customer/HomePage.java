package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends PageBase {

    public WebElement Generic_Element (boolean... ignoreException) { return findElement("//*[contains(@resource-id,\"com.bungii.customer\")]", LocatorType.XPath,ignoreException); }


    public WebElement Button_NavigationBar () { return findElement("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]", LocatorType.XPath); }
    public WebElement Button_NavigationBarCompleter (boolean ...ignoreException) { return findElement("android.view.View", LocatorType.ClassName,ignoreException); }

    //------Title-----------------------------------------------------------------------------------------
    public WebElement Title_HomePage (boolean ...ignoreException) { return findElement("com.bungii.customer:id/toolbar_main_title",LocatorType.Id,ignoreException); }

    //------Text-----------------------------------------------------------------------------------------
    //public WebElement Text_ETAvalue (boolean ... ignoreException) { return findElement("com.bungii.customer:id/eta_bar_textview_estimate",LocatorType.Id,ignoreException); }
    public WebElement Text_ETAvalue (boolean ... ignoreException) { return findElement("com.bungii.customer:id/home_tv_eta_label",LocatorType.Id,ignoreException); }
    //------Buttons---------------------------------------------------------------------------------------
    public WebElement Link_Invite () { return findElement("com.bungii.customer:id/menu_invite",LocatorType.Id); }

    public WebElement Button_Locator () { return findElement("//android.widget.ImageView[@content-desc='My Location']",LocatorType.XPath); }

    public WebElement Button_GetEstimate (boolean ...ignoreException) { return findElement("com.bungii.customer:id/pickup_location_get_estimate_button",LocatorType.Id,ignoreException); }

    public WebElement Switch_SoloDuo () { return findElement("com.bungii.customer:id/home_switch_noofdrivers",LocatorType.Id); }

    public WebElement Selector_Solo () { return findElement("com.bungii.customer:id/home_imageview_solo",LocatorType.Id); }

    public WebElement Selector_Duo () { return findElement("com.bungii.customer:id/home_imageview_duo",LocatorType.Id); }

    public WebElement Button_MyLocation () { return findElement("My Location",LocatorType.Id); }

    public WebElement MapPanningArea () { return findElement("android.widget.FrameLayout",LocatorType.ClassName); }

    public WebElement Button_ETASet (boolean ... ignoreException) { return findElement("com.bungii.customer:id/eta_bar_button_set",LocatorType.Id,ignoreException); }
    public WebElement Button_ETAPickupSet (boolean ... ignoreException) { return findElement("//eta_bar_button_set[]",LocatorType.XPath,ignoreException); }
    public WebElement Button_ETADropoffSet (boolean ... ignoreException) { return findElement("//eta_bar_button_set[]",LocatorType.XPath,ignoreException); }

    public WebElement Text_ETAHeader(){return findElement("com.bungii.customer:id/eta_bar_title",LocatorType.Id);}
    public WebElement Header_HomePage (boolean ... ignoreException) { return findElement("//android.widget.TextView[@text='BUNGII']",LocatorType.XPath,ignoreException); }

    public WebElement Link_Slider () { return findElement("com.bungii.customer:id/header_textview_username",LocatorType.Id); }

    public WebElement Link_Menu () { return findElement("android.widget.ImageButton",LocatorType.ClassName); }

    public WebElement Button_Closetutorials (boolean ...ignoreException) { return findElement("com.bungii.customer:id/tutorials_alert_iv_cancel",LocatorType.Id,ignoreException); }
    public WebElement Text_TutorialPdf(){return findElement("com.bungii.customer:id/pdfView",LocatorType.Id);}
    //------Estimate and Customer Cancel Posted Success page---------------------------------------------------------------------
    public WebElement Title_Success () { return findElement("//android.view.View[@id='com.bungii.customer:id/action_bar']/android.widget.TextView[@text='Success!']",LocatorType.XPath); }

    public WebElement Image_Tick () { return findElement("//android.widget.FrameLayout[@id='android:id/content']/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView",LocatorType.XPath); }

    public WebElement Button_Done (boolean ... ignoreException) { return findElement("com.bungii.customer:id/bungii_posted_button_done",LocatorType.Id,ignoreException); }
    public WebElement Button_AlertDone (boolean ... ignoreException) { return findElement("android:id/button2",LocatorType.Id,ignoreException); }

    public WebElement Button_NavHome() { return findElement("//android.widget.CheckedTextView[@text='HOME']",LocatorType.XPath); }
    public WebElement Button_NavFAQ() { return findElement("//android.widget.CheckedTextView[@text='FAQ']",LocatorType.XPath); }
    //public WebElement Button_NavAccount() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(2); }
    public WebElement Button_NavAccount(boolean... ignoreException) { return findElement("//android.widget.CheckedTextView[@text='ACCOUNT']",LocatorType.XPath, ignoreException); }
    public WebElement Button_NavSchBungii() { return findElement("//android.widget.CheckedTextView[@text='MY BUNGIIS']",LocatorType.XPath); }
    //public WebElement Button_NavPayment() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(4); }
    public WebElement Button_NavAccountInfo() { return findElement("//android.widget.TextView[@text='ACCOUNT INFO']",LocatorType.XPath); }
    //public WebElement Button_NavPayment() { return findElements("//*[@resource-id='com.bungii.customer:id/layout_account_settings_constraint_layout']",LocatorType.XPath).get(2); }
    public WebElement Button_NavPayment(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='PAYMENT']",LocatorType.XPath,ignoreException);}
    public WebElement Button_NavSupport() { return findElement("//android.widget.CheckedTextView[@text='SUPPORT']",LocatorType.XPath); }
    //public WebElement Button_NavPromos() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(6); }
    public WebElement Button_NavPromos() { return findElement("//android.widget.TextView[@text='PROMOS']",LocatorType.XPath); }
    public WebElement Button_NavPrivacyPolicy() { return findElement("//android.widget.TextView[@text='PRIVACY POLICY']",LocatorType.XPath); }
    public WebElement Button_NavDrives() { return findElement("//android.widget.CheckedTextView[@text='SIGN UP TO DRIVE']",LocatorType.XPath); }
    //public WebElement Button_Navlogout() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(8); }
    public WebElement Button_Navlogout() { return findElement("//android.widget.TextView[@text='LOGOUT']",LocatorType.XPath); }

    public WebElement Button_ClearPickUp (boolean ...ignoreException) { return findElement("com.bungii.customer:id/layout_places_autocomplete_iv_clear",LocatorType.Id,ignoreException); }
    public WebElement Button_ClearDrop () { return findElements("com.bungii.customer:id/layout_places_autocomplete_iv_clear",LocatorType.Id).get(1); }
    public WebElement TextBox_PickUpLocLine1(){return findElement("com.bungii.customer:id/layout_places_autocomplete_addressline_one",LocatorType.Id);}
    public WebElement TextBox_PickUpLocLine2(){return findElement("com.bungii.customer:id/layout_places_autocomplete_addressline_two",LocatorType.Id);}
    public WebElement TextBox_PickUp(){return findElement("com.bungii.customer:id/address_ll_container",LocatorType.Id);}
    public WebElement TextBox_PickUpTextBox(){return findElement("com.bungii.customer:id/autocomplete_textview",LocatorType.Id);}
    public WebElement TextBox_DropOffLine1(){return findElements("com.bungii.customer:id/layout_places_autocomplete_addressline_one",LocatorType.Id).get(1);}
    public WebElement TextBox_DropOffLine2(){return findElements("com.bungii.customer:id/layout_places_autocomplete_addressline_two",LocatorType.Id).get(1);}
    public WebElement TextBox_DropOff(boolean ...ignoreException){return findElement("//*[@resource-id='com.bungii.customer:id/places_autocomplete_dropoff_location']/descendant::*[@resource-id='com.bungii.customer:id/address_ll_container']",LocatorType.XPath,ignoreException);}
    public WebElement TextBox_DropOffTextBox(){return findElement("//*[@resource-id='com.bungii.customer:id/places_autocomplete_dropoff_location']/descendant::*[@resource-id='com.bungii.customer:id/autocomplete_textview']",LocatorType.XPath);}

    public WebElement Text_ErrorMessage150Miles() {return findElement("android:id/message", LocatorType.Id);}
    public WebElement Button_ErrorMessage150Miles() {return findElement("android:id/button1", LocatorType.Id);}

    //public WebElement Text_ErrorNonGeofence() { return findElement("//*[@resource-id='com.bungii.customer:id/eta_bar_error_where_to_next']/android.widget.TextView[2]", LocatorType.XPath);}
    public WebElement Text_ErrorNonGeofence() { return findElement("//*[@resource-id='com.bungii.customer:id/fragment_home_not_operating_container']/android.widget.TextView[2]", LocatorType.XPath);}

    //Tutuorial
   // public List<WebElement> Button_PdfPages(){return findElements("//androidx.appcompat.app.ActionBar.Tab", LocatorType.XPath);}
   // public WebElement Text_TutorialPdfPage1() { return findElements("//androidx.appcompat.app.ActionBar.Tab", LocatorType.XPath).get(0);}

    //Tutorial Moto 9.0
    public List<WebElement> Button_PdfPages(){return findElements("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.LinearLayout", LocatorType.XPath);}
    public WebElement Text_TutorialPdfPage1() { return findElements("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.LinearLayout", LocatorType.XPath).get(0);}

    public WebElement Button_StartApp() { return findElement("com.bungii.customer:id/tutorials_screen_five_btn_start",LocatorType.Id); }
    public WebElement Button_BackOfPage() {return findElement("//android.widget.ImageButton[contains(@content-desc,\"Navigate up\")]", LocatorType.XPath);}


    public WebElement Tab_MyBungiisScheduled() {return findElement("com.bungii.customer:id/my_bungii_radio_upcoming_trips",  LocatorType.Id);}
    public WebElement Tab_MyBungiisPast() {return findElement("com.bungii.customer:id/my_bungii_radio_past_trips",  LocatorType.Id);}
    public WebElement Text_ScheduledBungiisInfo(){return findElement("com.bungii.customer:id/my_bungii_tv_no_bungiis_info", LocatorType.Id);}
    public WebElement Text_PastBungiisInfo(){return findElement("com.bungii.customer:id/my_bungii_tv_no_bungiis_info", LocatorType.Id);}

    public WebElement LocationPicker() {return findElement("com.bungii.customer:id/pickup_location_imageview_idle_eta", LocatorType.Id);}
    public WebElement DropOffLocationPicker(){return findElement("com.bungii.customer:id/pickup_location_eta_bar", LocatorType.Id);}
    public WebElement TextBox_CancellationReason(){return findElement("com.bungii.customer:id/alert_cancellation_reason_et", LocatorType.Id);}
    public WebElement TextBox_ETAContainer(){return findElement("com.bungii.customer:id/home_eta_container", LocatorType.Id);}

    public WebElement Label_ETAContainer(boolean ...ignoreexception){ return findElement("com.bungii.customer:id/home_eta_container", LocatorType.Id,ignoreexception);}
    //public WebElement Tab_MyBungiisScheduled() {return findElement("com.bungii.customer:id/my_bungii_radio_upcoming_trips",  LocatorType.Id);}
    //public WebElement Tab_MyBungiisPast() {return findElement("com.bungii.customer:id/my_bungii_radio_upcoming_trips",  LocatorType.Id);}
    //public WebElement Text_ScheduledBungiisInfo(){return findElement("com.bungii.customer:id/my_bungii_tv_no_bungiis_info", LocatorType.Id);}
   // public WebElement Text_PastBungiisInfo(){return findElement("com.bungii.customer:id/my_bungii_tv_no_bungiis_info", LocatorType.Id);}

}
