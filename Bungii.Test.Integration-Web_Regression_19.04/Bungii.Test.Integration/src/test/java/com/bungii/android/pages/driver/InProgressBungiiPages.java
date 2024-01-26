package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;


public class InProgressBungiiPages extends PageBase {

    public WebElement Title_Status_Generic(boolean ... ignoreException) { return findElement("//*[@resource-id='com.bungii.driver:id/toolbar_title' or 'com.bungii.customer:id/toolbar_title']", LocatorType.XPath,ignoreException); }
    public WebElement Title_Status_Generic_Alt(boolean ... ignoreException) { return findElement("//*[@resource-id='com.bungii.driver:id/toolbar' or 'com.bungii.customer:id/toolbar']/android.widget.TextView", LocatorType.XPath,ignoreException); }


    //------Page Titles--------------------------------------------------------------
    public WebElement Title_Status(boolean ... ignoreException) { return findElement("com.bungii.driver:id/toolbar_title", LocatorType.Id,ignoreException); }

    //------Cancel Bungii------------------------------------------------------------
    public WebElement Button_Cancel() { return findElement("com.bungii.driver:id/activity_more_options_cancel_trip", LocatorType.Id); }
//    public WebElement Button_CancelBungii() { return findElement("com.bungii.driver:id/scheduled_bungii_details_tv_cancel_bungii", LocatorType.Id); }
    public WebElement Button_CancelBungii() { return findElement("com.bungii.driver:id/btn_reject_bungii", LocatorType.Id); }

    public WebElement Button_Cancel_Yes() { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Button_Cancel_Cancel() { return findElement("android:id/button2", LocatorType.Id); }

    //------Slider-------------------------------------------------------------------
    public WebElement Slider() { return findElement("com.bungii.driver:id/view_sliding_bottom_sheet_slider_view", LocatorType.Id); }


    public WebElement Button_More() { return findElement("//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.ImageView", LocatorType.XPath); }


    //More incas of duo
    public WebElement Button_DuoMore(boolean...ignoreException) { return findElement("com.bungii.driver:id/pickup_details_contact_options", LocatorType.Id,ignoreException); }

    public WebElement Button_DuoMore1() { return findElements("com.bungii.driver:id/pickup_details_contact_options", LocatorType.Id).get(0); }
    public WebElement Button_DuoMore2() { return findElements("com.bungii.driver:id/pickup_details_contact_options", LocatorType.Id).get(1); }
    public WebElement Button_DuoCustomer_SMS(boolean...ignoreException) { return findElement("com.bungii.driver:id/caller_communication_mode_sms_textview", LocatorType.Id,ignoreException); }

    public WebElement Button_DuoCustomer_Call(boolean...ignoreException) { return findElement("com.bungii.driver:id/caller_communication_mode_phone_call_textview", LocatorType.Id,ignoreException); }

    public WebElement Button_DuoCustomer_ViewItem(boolean...ignoreException) { return findElement("com.bungii.driver:id/caller_communication_mode_view_pickup_items_images_textview", LocatorType.Id,ignoreException); }

    public WebElement Button_DuoCustomer_CallSupport(boolean...ignoreException) { return findElement("com.bungii.driver:id/caller_communication_mode_contact_driver_support_textview", LocatorType.Id,ignoreException); }
    public WebElement Button_DuoCancel() { return findElement("com.bungii.driver:id/caller_cancel", LocatorType.Id); }



    //------SMS----------------------------------------------------------------------
   // public WebElement Button_Customer_SMS() { return findElement("com.bungii.driver:id/pickup_details_sms_button", LocatorType.Id); }
    //public WebElement Button_Customer_SMS() { return findElement("//*[@resource-id='android:id/select_dialog_listview']/android.widget.TextView[3]", LocatorType.XPath); }
    public WebElement Button_Customer_SMS() { return findElement("//*[@resource-id='com.bungii.driver:id/alert_dialog_solo_driver_inprogess_tv_text_customer']/android.widget.TextView", LocatorType.XPath); }

    //------Call---------------------------------------------------------------------
  //  public WebElement Button_Customer_Call() { return findElement("com.bungii.driver:id/pickup_details_call_button", LocatorType.Id); }
    //public WebElement Button_Customer_Call() { return findElement("//*[@resource-id='android:id/select_dialog_listview']/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Button_Customer_Call() { return findElement("//*[@resource-id='com.bungii.driver:id/alert_dialog_solo_driver_inprogess_tv_call_customer']/android.widget.TextView", LocatorType.XPath); }

    //public WebElement Button_Customer_ViewItem() { return findElement("//*[@resource-id='android:id/select_dialog_listview']/android.widget.TextView[1]", LocatorType.XPath); }
//    public WebElement Button_Customer_ViewItem() { return findElement("//*[@resource-id='com.bungii.driver:id/alert_dialog_solo_driver_inprogess_tv_view_items']/android.widget.TextView", LocatorType.XPath); }
    public WebElement Button_Customer_ViewItem() { return findElement("//android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ImageView", LocatorType.XPath); }

   // public WebElement Button_Customer_CallSupport() { return findElement("//*[@resource-id='android:id/select_dialog_listview']/android.widget.TextView[4]", LocatorType.XPath); }
//    public WebElement Button_Customer_CallSupport() { return findElement("//*[@resource-id='com.bungii.driver:id/alert_dialog_solo_driver_inprogess_tv_text_support']/android.widget.TextView", LocatorType.XPath); }
    public WebElement Button_Customer_CallSupport() { return findElement("com.bungii.driver:id/driver_options_iv_camera", LocatorType.Id); }

    public WebElement Button_CancelImage() { return findElement("com.bungii.driver:id/activity_view_all_images_iv_cancel", LocatorType.Id); }
    public WebElement Image_BungiiItem() { return findElement("com.bungii.driver:id/viewpager_sliding_item_imageview_item", LocatorType.Id); }





    //------Estimate and Customer Cancel Statuses----------------------------------------------------------
    public WebElement BungiiStatus_Enroute(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_status_1", LocatorType.Id,ignoreException); }

    public WebElement BungiiStatus_Arrived(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_status_2", LocatorType.Id,ignoreException); }

    public WebElement BungiiStatus_LoadingItem(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_status_3", LocatorType.Id,ignoreException); }

    public WebElement BungiiStatus_DrivingToDropOff(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_status_4", LocatorType.Id,ignoreException); }

    public WebElement BungiiStatus_UnloadingItem(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_status_5", LocatorType.Id,ignoreException); }

    //------Location Details---------------------------------------------------------
    public WebElement Bungii_LocationTitle() { return findElement("com.bungii.driver:id/pickup_details_address_name", LocatorType.Id); }

    public WebElement Bungii_Location() { return findElement("com.bungii.driver:id/pickup_details_address_value", LocatorType.Id); }

    public WebElement Bungii_ETA(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_estimate", LocatorType.Id,ignoreException); }
    public WebElement Bungii_ETACustomer(boolean ... ignoreException) { return findElement("com.bungii.customer:id/pickup_details_estimate", LocatorType.Id,ignoreException); }

    //------Driver Details-----------------------------------------------------------
    public WebElement Bungii_Customer_Title() { return findElement("com.bungii.driver:id/pickup_details_caller_title", LocatorType.Id); }

    public WebElement Bungii_Customer_Name() { return findElement("com.bungii.driver:id/pickup_details_caller_name", LocatorType.Id); }
    public WebElement Alert_Message(boolean ... ignoreException){return findElement("android:id/message",LocatorType.Id,ignoreException);}
    public WebElement Alert_Accept(){return  findElement("android:id/button1",LocatorType.Id);}

    //DUO
    public WebElement Text_DuoCustomer_Title() { return findElements("com.bungii.driver:id/pickup_details_person_title", LocatorType.Id).get(0); }
    public WebElement Text_DuoCustomer_Name() { return findElements("com.bungii.driver:id/pickup_details_person_name", LocatorType.Id).get(0); }
    public WebElement Text_DuoDriver_Title() { return findElements("com.bungii.driver:id/pickup_details_person_title", LocatorType.Id).get(1); }
    public WebElement Text_DuoDriver_Name() { return findElements("com.bungii.driver:id/pickup_details_person_name", LocatorType.Id).get(1); }


    //STACK
    public WebElement Text_NextLabel(boolean ... ignoreException) { return findElement("com.bungii.driver:id/stack_widget_tv_next", LocatorType.Id,ignoreException); }
    public WebElement Text_OnDeckLabel(boolean ... ignoreException) { return findElement("com.bungii.driver:id/appCompatTextView24", LocatorType.Id,ignoreException); }
    public WebElement Text_StackCustomer(boolean ... ignoreException) { return findElement("com.bungii.driver:id/stack_customer_name_textview", LocatorType.Id,ignoreException); }
    public WebElement Text_FinishBy() { return findElement("com.bungii.driver:id/appCompatTextView24", LocatorType.Id); }
    public WebElement Button_StackInfo() { return findElement("com.bungii.driver:id/pickup_details_iv_stack_info", LocatorType.Id); }

    //Details Note
    public WebElement Button_DetailsFromCustomer(boolean ... ignoreException) { return findElement("//android.widget.TextView[@text='Delivery Details']", LocatorType.XPath, ignoreException); }
    public WebElement Text_CustomerNote(boolean ... ignoreException) {return findElement("com.bungii.driver:id/activity_customer_details_tv_customernote", LocatorType.Id, ignoreException);}

    //General Instructions
    public WebElement Header_GeneralInstructions() {return findElement("com.bungii.driver:id/view_instructions_header", LocatorType.Id);}
    public WebElement Text_GeneralInstructions(){return findElement("com.bungii.driver:id/view_instructions_body",LocatorType.Id);}
    public WebElement Button_GeneralInstructions_GotIt(){return findElement("com.bungii.driver:id/view_instructions_btn_next",LocatorType.Id);}

    //DriverBungiiProgress Screen
    public WebElement Title_BungiiStatus1() { return findElement("//*[@resource-id='com.bungii.driver:id/activity_driver_pickup_details_toolbar']/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Title_BungiiStatus2() { return findElement("//*[@resource-id='com.bungii.driver:id/activity_driver_pickup_details_toolbar']/android.view.ViewGroup/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_ETA() { return findElement("//*[@resource-id='com.bungii.driver:id/activity_driver_pickup_details_toolbar']/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[2]", LocatorType.XPath); }

    //Photo Verification by driver
    public WebElement Text_PhotoVerification(){return findElement("//android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView",LocatorType.XPath);}
    public WebElement Tab_AddPhoto(){return findElement("com.bungii.driver:id/appCompatImageView19",LocatorType.Id);}
    public WebElement Button_SavePhotos(){return findElement("com.bungii.driver:id/activity_photo_verification_btn_save",LocatorType.Id);}


    //*[@resource-id='com.bungii.driver:id/activity_driver_pickup_details_toolbar']/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[1]
    //DriverRatingcustomer screen
    public WebElement Title_RateCustomer(boolean ... ignoreException) {return findElement("//*[@resource-id='com.bungii.driver:id/constraintLayout16']/android.widget.TextView",LocatorType.XPath);}
    public WebElement Link_SkipRating(boolean ... ignoreException){return findElement("//*[@resource-id='com.bungii.driver:id/appCompatTextView60']",LocatorType.XPath,ignoreException);}

    public WebElement Button_MoreOptions(){return findElement("com.bungii.driver:id/appCompatImageView37",LocatorType.Id);}

    public WebElement Button_TakePhotos(){return findElement("//android.widget.Button[@text='Take photo']",LocatorType.XPath);}

    public WebElement Button_DeliveryInstructions(){return findElement("com.bungii.driver:id/driver_options_container_delivery_instructions",LocatorType.Id);}

    public WebElement Text_DeliveryInstructions(){return findElement("com.bungii.driver:id/view_customer_note_markdown_instructions",LocatorType.Id);}

    //Duo trip second driver vehicle information
    public WebElement Image_BungiiDuoTeammate(boolean...ignoreException){return findElement("com.bungii.driver:id/duo_driver_imageview",LocatorType.Id,ignoreException);}
    public WebElement Text_DuoDriverVehicleModel(){return findElement("com.bungii.driver:id/activity_teammate_tv_vehicle_info",LocatorType.Id);}
    public WebElement Text_DuoDriverVehicleNumber(){return findElement("//android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.widget.TextView",LocatorType.XPath);}

    //Photo Verification
    public WebElement Title_PhotoVerification(){return findElement("//android.widget.TextView[@text='PHOTO VERIFICATION']",LocatorType.XPath);}
    public WebElement Text_PhotoVerificationBeforeLoading(){return findElement("//android.widget.TextView[@text='Photo verification (before loading)']",LocatorType.XPath);}
    public WebElement Text_PhotoVerificationAfterLoading(){return findElement("//android.widget.TextView[@text='Photo verification (after loading)']",LocatorType.XPath);}
    public WebElement Text_PhotoVerificationBeforeUnloading(){return findElement("//android.widget.TextView[@text='Photo verification (before loading)']",LocatorType.XPath);}

    public WebElement Button_AddPhoto(){return findElement("com.bungii.driver:id/appCompatImageView19",LocatorType.Id);}
    public WebElement Button_Save(boolean...IgnoreException){return findElement("com.bungii.driver:id/activity_photo_verification_btn_save",LocatorType.Id,IgnoreException);}

    public WebElement Image_UploadedImage(boolean... IgnoreException){return findElement("//android.widget.ImageView[@resource-id='com.bungii.driver:id/view_driver_captured_photo_image']",LocatorType.XPath,IgnoreException);}
    public WebElement Tab_CustomerSignature(){return findElement("com.bungii.driver:id/activity_more_options_btn_customer_signature",LocatorType.Id);}
    public WebElement Button_Submit(){return findElement("com.bungii.driver:id/appCompatButton6",LocatorType.Id);}

}
