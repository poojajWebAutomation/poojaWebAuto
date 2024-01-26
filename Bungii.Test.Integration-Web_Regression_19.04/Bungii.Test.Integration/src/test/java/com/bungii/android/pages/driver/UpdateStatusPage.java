package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class UpdateStatusPage extends PageBase {
    public WebElement Text_WaitingForDriver(boolean ignoreException){return findElement("com.bungii.driver:id/progress_message",LocatorType.Id, ignoreException);}
    public WebElement Activity_loader(boolean ignoreException){return findElement("com.bungii.driver:id/progress_bar",LocatorType.Id, ignoreException);}

    public WebElement Tab_AddPhoto(){return findElement("//android.widget.TextView[@text='Tap to add photo']",LocatorType.XPath);}
    public WebElement Button_Save(){return findElement("//android.widget.Button[@text='Save']",LocatorType.XPath);}
    public WebElement Button_CLick(){return findElement("//*[@resource-id='com.motorola.camera2:id/controls']",LocatorType.XPath);}

    //Driver Rates Driver
    public WebElement Header_RateTeamate() {return findElement("//android.widget.TextView[@text=\"Rate duo teammate\"]", LocatorType.XPath);}
    public WebElement Star_Rating() {return findElement("//android.widget.RatingBar", LocatorType.XPath);}
    public WebElement Text_ChooseRating() {return findElement("//android.widget.TextView[@text='Choose your rating']", LocatorType.XPath);}
    public WebElement Text_DriverExperience() {return findElement("//android.widget.TextView[@text='Tell us about the other driver']", LocatorType.XPath);}
    public WebElement Textbox_AdditionalFeedback() {return findElement("//android.widget.EditText[@text='Any additional feedback (Optional)']", LocatorType.XPath);}
    public WebElement Button_DriverSubmit() {return findElement("//android.widget.Button[@text='Submit']", LocatorType.XPath);}
    public WebElement Text_Additional() {return findElement("//android.widget.TextView[@text='Additional']", LocatorType.XPath);}
    public WebElement RatingBar(){return findElement("//*[@resource-id='com.bungii.driver:id/rate_participants_rating_bar_customer']",LocatorType.XPath);}

    public WebElement Text_CustomerNameOnDriverApp( ){return findElement("//android.view.ViewGroup/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[2]",LocatorType.XPath);}
    public WebElement TextBox_SignedByField( ){return findElement("com.bungii.driver:id/activity_signature_et_signed_by",LocatorType.Id);}
    public WebElement TextBox_Signature( ){return findElement("com.bungii.driver:id/activity_signature_signature_pad",LocatorType.Id);}
    public WebElement Button_ClearSignature( ){return findElement("//android.view.ViewGroup/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[2]",LocatorType.XPath);}
    public WebElement Button_Submit(){return findElement("Submit",LocatorType.AccessibilityId);}
    public WebElement Header_CustomerSignature(boolean...IgnoreException){return findElement("//android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView",LocatorType.XPath,IgnoreException);}
    public WebElement Button_SkipCustomerSignature(){return findElement("com.bungii.driver:id/appCompatTextView65",LocatorType.Id);}
    public WebElement Label_DeliverySuccessMessageLive()  { return findElement("//i[contains(text(),'Pick up has been successfully updated.')]", LocatorType.XPath); }
    public WebElement Label_CancelSuccessMessageLive () { return findElement("//i[@id='delivery-cancelled-success-message']/i[2]", LocatorType.XPath); }
    public WebElement Message_AdminCompleteConfirm() {return findElement("//span[contains(text(),'Once you confirm, the delivery status and the upda')]",LocatorType.XPath);}
    public WebElement Button_CalculateCost() { return findElement("//button[contains(text(),'Calculate cost')]",LocatorType.XPath);}
    public WebElement Textbox_PickupEndDate () { return findElement("//input[@name='Date']", LocatorType.XPath); }
    public WebElement Textbox_PickupEndTime () { return findElement("//input[@name='Time']", LocatorType.XPath); }
    public WebElement Dropdown_ddlpickupEndTime () { return findElement("//select[@name='Units']", LocatorType.XPath); }
    public WebElement RadioButton_EditDeliveryStatus() { return findElement("//label[contains(text(),'Edit Delivery Status')]",LocatorType.XPath);}
    public WebElement RadioButton_DeliveryCanceled() { return findElement("//label[contains(text(),'Delivery Canceled')]",LocatorType.XPath);}
    public WebElement RadioButton_DeliveryCompleted() { return findElement("//label[contains(text(),'Delivery Completed')]",LocatorType.XPath);}

    public WebElement Text_Details() { return findElement("//android.view.ViewGroup/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[1]",LocatorType.XPath);}
    public WebElement Button_ConfirmStatus() { return findElement("//div[@class=\"modal-footer\"]/button[2]", LocatorType.XPath);}
    public WebElement Button_CloseStatus() { return findElement("//button[text()='Close']", LocatorType.XPath);}
    public WebElement Button_RemoveDrivers () { return findElement("//div[@id='tripDriverDetails']//strong[contains(text(),'Remove')]", LocatorType.XPath); }//Richa
    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm']", LocatorType.XPath); }
    public WebElement Header_CustomerSignature(){return findElement("//android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView",LocatorType.XPath);}
    public WebElement Label_CustomerSignature(boolean...IgnoreException){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]",LocatorType.XPath,IgnoreException);}
    public WebElement Alert_DropOffInstructionsGotIt(){return findElement("com.bungii.driver:id/view_instructions_btn_next",LocatorType.Id);}

    public WebElement Button_CloseDeliveryDetails() { return findElement("com.bungii.driver:id/appCompatImageView14",LocatorType.Id);}
    public WebElement Icon_Call(){return findElement("com.bungii.driver:id/view_sliding_bottom_sheet_expanded_iv_call_customer",LocatorType.Id);}
    public WebElement Icon_Text(){return findElement("com.bungii.driver:id/appCompatImageView18",LocatorType.Id);}
    public WebElement Icon_Pickup(){return findElement("//android.view.ViewGroup/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.ImageView",LocatorType.XPath);}
    public WebElement Icon_DropOff(){return findElement("//android.view.ViewGroup/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView",LocatorType.XPath);}
    public WebElement Text_ContactDuo(boolean...IgnoreException){return findElement("com.bungii.driver:id/tv_contact_duo_label",LocatorType.Id,IgnoreException);}
    public WebElement Text_TeamMate(boolean... IgnoreException){return findElement("com.bungii.driver:id/tv_teammate_label",LocatorType.Id,IgnoreException);}
    public WebElement Label_ArivalTimeAtPickup(){return findElement("com.bungii.driver:id/tv_pickup_arrival_time_label",LocatorType.Id);}
    public WebElement Label_ExpectedTimeAtDropOff(){return findElement("com.bungii.driver:id/tv_drop_off_expected_time_label",LocatorType.Id);}
    public WebElement ArrivalTimeAtPickupValue(){return findElement("com.bungii.driver:id/tv_pickup_arrival_time",LocatorType.Id);}
    public WebElement ExpectedTimeAtDropOff(){return findElement("com.bungii.driver:id/tv_drop_off_expected_time",LocatorType.Id);}
    public WebElement Label_Pickup(){return findElement("com.bungii.driver:id/tv_waypoint_type",LocatorType.Id);}
    public WebElement Label_ArrivalTime(){return findElement("com.bungii.driver:id/tv_scheduled_time_info",LocatorType.Id);}
    public WebElement Text_ArrivalTimeValue(){return findElement("com.bungii.driver:id/tv_scheduled_time",LocatorType.Id);}
    public WebElement Text_DropOffRangeFromDeliveryDetails(){return findElement("//android.view.ViewGroup[2]/android.widget.TextView[@resource-id ='com.bungii.driver:id/tv_scheduled_time']",LocatorType.XPath);}
    public WebElement Image_Barcode(){return findElement("com.bungii.driver:id/iv_scanner",LocatorType.Id);}
    public WebElement Text_BarcodeInstructions(){return findElement("com.bungii.driver:id/tv_barcode_scan_instructions",LocatorType.Id);}
    public WebElement Button_ScanItemBarCode(){return findElement("com.bungii.driver:id/btn_scan_barcode",LocatorType.Id);}
    public WebElement Header_BarcodeScanner(){return findElement( "//android.widget.ImageView[@resource-id ='com.bungii.driver:id/iv_close']/following-sibling::android.widget.TextView",LocatorType.XPath);}
    public WebElement Text_ScanBarCode(){return findElement( "com.bungii.driver:id/appCompatTextView32",LocatorType.Id);}
    public WebElement Text_BarCodeScanningInstructions(){return findElement( "com.bungii.driver:id/tv_scan_instruction",LocatorType.Id);}
    public WebElement Button_SkipBarCode(){return findElement( "com.bungii.driver:id/btn_submit",LocatorType.Id);}
    public WebElement Text_ErrorMessageOnClickingSkipScanning(){return findElement("android:id/message",LocatorType.Id);}
    public WebElement Text_NotificationTextOnArrivalAndUnloadingItemsForBarCode(){return findElement("//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView",LocatorType.XPath);}

}
