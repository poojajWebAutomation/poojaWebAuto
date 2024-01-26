package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ScheduledTripsPage extends PageBase {
    public By Text_Success = By.id("SuccessMessage");//"modal fade loader", LocatorType.ClassName); }
   // public By Loader_Wrapper = By.className("modal fade loader");//"modal fade loader", LocatorType.ClassName); }
    public WebElement Loader_Wrapper() {
        return findElement("modal fade loader", LocatorType.ClassName);
    }
    public WebElement Text_Success() {
        return       findElement("//p[@id='cancel-success-message']/i[last()]", LocatorType.XPath);
    }
    public WebElement TextBox_Phone() {
        return findElement("PhoneNo", LocatorType.Name);
    }
    public List<WebElement> Row_TripDetails() { return findElements("//table/tbody/tr", LocatorType.XPath); }

    public WebElement TableBody_TripDetails() {
        return findElement("//tbody/tr", LocatorType.XPath);
    }
    public WebElement Dropdown_CancellationReason () { return findElement("//div[@class='cancelation-fee']/ancestor::div[1]/following::div/select[@class='form-select']", LocatorType.XPath); }

    public WebElement RadioBox_Cancel() {return findElement("//label[contains(text(),'Cancel entire Bungii and notify driver(s)')]/preceding::input[1]", LocatorType.XPath);}

    public WebElement RadioBox_EditTrip() {return findElement("radio5", LocatorType.Id);}
   public WebElement RadioBox_EditTripForScheduled() {return findElement("radio3", LocatorType.Id);}

    public WebElement RadioBox_Research() {return findElement("//label[contains(@class,'driverCancel')]/input", LocatorType.XPath); }
 //   public WebElement Button_Research() {return findElement("//*[contains(@id,'tripDriverDetails')]//button[2]", LocatorType.XPath); }
    public WebElement Button_Research() {return findElement("//strong[text()='Re-search a driver']", LocatorType.XPath); }

    public WebElement TextBox_CancelFee() {
        return findElement("cancelationFee", LocatorType.Id);
    }

    public WebElement TextBox_Comments() {
        return findElement("Secondary", LocatorType.Id);
    }

    public WebElement Button_Submit() {
        return findElement("CustomerCancel", LocatorType.Name);
    }

    public WebElement Admin_Dropdown_ServiceLevel() { return findElement("//select[@class='service-level-mt form-select']",LocatorType.XPath);}

    public WebElement Button_ScheduledDateSort(){return  findElement("span-ScheduledDate",LocatorType.Id);}
    public WebElement Text_SearchCriteria(){return  findElement("SearchCriteria",LocatorType.Id);}
    public WebElement TimePicker_Time () { return findElement("PickupDetails_ScheduledTime", LocatorType.Id); }
    public WebElement Dropdown_ScheduledDate_Time() { return findElement("//li[@class='ui-timepicker-am ui-timepicker-selected']/following-sibling::li[3]", LocatorType.XPath); }

    public WebElement Button_Search(){return  findElement("btnSearch",LocatorType.Id);}
    public WebElement Icon_Dropdown(){return  findElement("//div[@class=\"threedoticon\"]/img",LocatorType.XPath);}
    public WebElement Option_Edit(){return  findElement("//div/a[text()=\"Edit\"]",LocatorType.XPath);}
    public WebElement Label_Drop_Off_Location () { return findElement("//div[@class=\"live-edit\"]/div/div[4]/div[1]/span[contains(text(),'Drop Off Location:')]",LocatorType.XPath);}
    public WebElement Button_Edit_Drop_Off_Address () { return findElement("//div[@class=\"live-edit\"]/div/div[4]/div[2]/div/div[2]/img[@title='Edit drop off Location']",LocatorType.XPath);}
    public WebElement Textbox_Drop_Off_Location () { return findElement("//img[@title='Edit drop off Location']/ancestor::div[2]/div[1]/div[@class=\"address-textbox\"]/div/input",LocatorType.XPath);}
    public WebElement DropdownResult (String address) { return findElement(String.format("//div[@id='divPlacesResult']/div[contains(.,'%s')]",address),LocatorType.XPath);}
    public WebElement DropOff_Address() { return findElement("//img[@title='Edit drop off Location']/ancestor::div[2]/div[1]/label[@class=\"address2\"]",LocatorType.XPath);}
    public WebElement Label_Pickup_Location () { return findElement("//span[contains(text(),'Pickup Location:')]",LocatorType.XPath);}
    public WebElement Button_Edit_Pickup_Address () { return findElement("//img[@title='Edit Pickup Location']",LocatorType.XPath);}
    public WebElement Label_Pickup_Location_For_Live () { return findElement("//div[@class=\"live-edit\"]/div/div[3]/div/span[contains(text(),'Pickup Location:')]",LocatorType.XPath);}
    public WebElement Button_Edit_Pickup_Address_For_Live () { return findElement("//div[@class='live-edit']/div/div[3]/div[2]/div/div[2]/img[@title='Edit Pickup Location']",LocatorType.XPath);}
    public WebElement Textbox_Pickup_Location_For_Live () { return findElement("//img[@title='Edit Pickup Location']/ancestor::div[2]/div[1]/div[@class=\"address-textbox\"]/div/input",LocatorType.XPath);}
    public WebElement Textbox_Pickup_Location () { return findElement("PickupDetails_PickupOriginAddress",LocatorType.Id);}
    public WebElement DropdownPickupResult (String address) { return findElement(String.format("//div[@class='autocomplete-dropdown-container']/div/span[contains(text(),'"+address+"')]",address),LocatorType.XPath);}
    public WebElement Text_Pickup_Address_For_Live() { return findElements("//img[@title='Edit Pickup Location']/ancestor::div[2]/div/label",LocatorType.XPath).get(1);}
    public WebElement Pickup_Address() { return findElement("//label[@id='lblPickupAddress']",LocatorType.XPath);}
    public WebElement Button_VerifyDriver(){return findElement("//div[@class=\"live-edit\"]/div/div[5]//button[contains(text(),'VERIFY')] | //div[@class=\"live-edit\"]/div/div[6]/button[contains(text(),'VERIFY')]", LocatorType.XPath);}
    public WebElement Text_VerifyChangesSavedMessage() {return findElement("//span[@id='verified-message']/i[2]", LocatorType.XPath);}
    public WebElement Button_SaveChanges(){return findElement("//button[contains(text(),'SAVE')]", LocatorType.XPath);}
    public WebElement Text_SuccessMessage(){return findElement("//span[@id='verified-message']/i[2]", LocatorType.XPath);}
    public WebElement Button_ClosePopUp(){return findElement("//button[@class='btn-close']", LocatorType.XPath);}
    public WebElement Dropdown_Result (boolean ...ignoreException) { return findElement("ddEditDeliveryRemark",LocatorType.Id, ignoreException); }
    public WebElement Dropdown_ScheduledDateTime() { return findElement("//li[@class='ui-timepicker-am ui-timepicker-selected']/following-sibling::li[3]", LocatorType.XPath); }
    public WebElement RadioButton_Solo() { return findElement("//input[@value='Solo']", LocatorType.XPath); }
    public WebElement Dropdown_FirstAddress (String address) { return findElement(String.format("//span[contains(text(),'%s')][1]",address),LocatorType.XPath);}

    //Driver earnings
    public WebElement Text_SoloDriverEarnings() {return findElement("//td[contains(text(),'Driver Fixed Earnings')]/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_DuoDriver1Earnings() {return findElement("//td[contains(text(),'Driver Fixed Earnings - Pallet 1')]/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_DuoDriver2Earnings() {return findElement("//td[contains(text(),'Driver Fixed Earnings - Pallet 2')]/following-sibling::td/strong", LocatorType.XPath);}

    public WebElement Text_EstimateCharge() {return findElement("//td[contains(text(),'Estimated Charge')]/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_SoloDriverEarningsApp() {return findElement("//XCUIElementTypeStaticText[contains(@name,'$')]", LocatorType.XPath);}
    public WebElement Text_DuoDriver1EarningsApp() {return findElement("//XCUIElementTypeStaticText[@name=\"Pallet 1\"]/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);}
    public WebElement Text_DuoDriver2EarningsApp() {return findElement("//XCUIElementTypeStaticText[@name=\"Pallet 2\"]/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);}


    public void waitForLoadingToDisappear(){
        waitForLoadingToDisappear();
    }

    public WebElement CheckBox_Driver1() {return findElement("checkbox0", LocatorType.Id); }
    public WebElement CheckBox_Driver2() {return findElements("//div[@class='tripDrivers row']//label[@class='custom-input checkboxDiv mt0 pull-left']/span", LocatorType.XPath).get(1); }
    public WebElement Button_Remove() {return findElement("//strong[text()='Remove']", LocatorType.XPath); }
    public WebElement TextBox_DriverSearch() {return findElement("//div[@class='addDriver']/div/div/input", LocatorType.XPath);}
    public WebElement Select_TestDriver(){return findElement("//input[@placeholder='Enter driver name']/following-sibling::div/div[1]", LocatorType.XPath);}
    public WebElement Text_EditTrpDetailsDriver1Name(){return findElement("//div[@class=\"driver-edit\"]/div/span[1]", LocatorType.XPath);}

    public WebElement Time_EditTripDetailsTime(){return findElement("PickupDetails_ScheduledTime", LocatorType.Id);}
    public WebElement Select_EditReason() {return findElement("ddEditDeliveryRemark",LocatorType.Id);}
    public WebElement Button_ReviveTrip() { return findElement("//img[@alt='revive']",LocatorType.XPath);}
    public WebElement Button_Confirm() { return findElement("//button[contains(text(),'Confirm')]", LocatorType.XPath); }
    public WebElement Text_PartnerNameLiveDeliveryPage() { return findElement("//tbody[@id='TripListsTBody']/tr[1]/td[9]",LocatorType.XPath);}
    public WebElement Link_DeliveryDetails(){return  findElement("//div[@class='threedoticon']/img",LocatorType.XPath);}
    public WebElement List_ViewDeliveries(){return  findElement("//a[contains(text(),'Delivery Details')]",LocatorType.XPath);}
    public WebElement Label_CustomerSignature(boolean...ignoreException){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]",LocatorType.XPath,ignoreException);}
    public WebElement Link_ChangeDeliveryStatus(boolean...ignoreException) { return findElement("//tr/td/a/img", LocatorType.XPath,ignoreException); }
    public WebElement DropDown_DeliveryStatus() { return findElement("txtNewStatus", LocatorType.Id); }
    public WebElement Text_DeliveryStatus(String status) { return findElement(String.format("//select/option[text() =\"%s\"]",status), LocatorType.XPath); }
    public WebElement DropDown_DeliveryStatusReason() { return findElement("txtNewStatusReason", LocatorType.Id);}
    public WebElement Text_DeliveryStatusReason(String statusReason) { return findElement(String.format("//div/select/option[text() =\"%s\"]",statusReason), LocatorType.XPath); }
    public WebElement Button_ConfirmStatus() { return findElement("//div[@class=\"modal-footer\"]/p/following-sibling::button[2]", LocatorType.XPath);}
    public WebElement Button_CloseStatus() { return findElement("//div[@id=\"edit-status-success-modal\"]/div/div/div[2]/button", LocatorType.XPath);}
    public WebElement Label_CustomerSignatureNA(){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]/following-sibling::td",LocatorType.XPath);}
    public WebElement Image_CustomerSignature(){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]/following-sibling::td/img",LocatorType.XPath);}
    public WebElement Checkbox_driver () { return findElement("//div[@id='tripDriverDetails']//span[@class='checkmark'][1]", LocatorType.XPath); }//richa
    public WebElement Button_RemoveDrivers () { return findElement("//div[@id='tripDriverDetails']//strong[contains(text(),'Remove')]", LocatorType.XPath); }//Richa
    public WebElement  Button_Edit() {return findElement("//a[contains(text(),'Edit')]",LocatorType.XPath);}

    public WebElement Label_SoloLift() { return findElement("SOLO LIFT", LocatorType.AccessibilityId); }
    public WebElement Text_SoloLiftMessage() { return findElement("You are responsible for loading and unloading the item(s) by yourself", LocatorType.AccessibilityId); }
    public WebElement Button_GotIt() {return findElement("Got it", LocatorType.AccessibilityId);}
    public WebElement Button_SkipCustomerRating() {return findElement("Skip customer signature", LocatorType.AccessibilityId);}
    public WebElement Label_CustomerHelp() { return findElement("SOLO LIFT", LocatorType.AccessibilityId); }
    public WebElement Text_CustomerHelpMessage() { return findElement("The customer will help you unload the item(s)", LocatorType.AccessibilityId); }
    public WebElement Label_DuoLift() { return findElement("DUO LIFT", LocatorType.AccessibilityId); }
    public WebElement Text_DuoLiftMessage() { return findElement("You and your duo teammate are responsible for loading and unloading the item(s)", LocatorType.AccessibilityId); }
    public WebElement Icon_CustomerHelpAdminPortal(){return  findElement("//div/span[text() =\"Customer help\"]",LocatorType.XPath);}
    public WebElement Button_Duo(){return  findElement("duo",LocatorType.Id);}
    public WebElement Text_PickupTime() { return findElement("//label[text() =\"Pickup Time\"]/following-sibling::div[1]/div/div/div",LocatorType.XPath);}
    public WebElement Textbox_CancellationFee () { return findElement("txtCancellationFee", LocatorType.Id); }
    public WebElement Textbox_CancellationComment () { return findElement("txtCustomerCancellationComments", LocatorType.Id); }
    public WebElement Label_CancelSuccessMessage () { return findElement("//p[@id='cancel-success-message']/i[2]", LocatorType.XPath); }
    public WebElement Button_ReviveTrip (boolean... IgnoreException) { return findElement("//tbody/tr/td[11]/a/img", LocatorType.XPath,IgnoreException); }
    public WebElement TextBox_Search() {return findElement("SearchCriteria", LocatorType.Id); }
    public WebElement Button_Cancel() { return findElement("//button[text()='Cancel']", LocatorType.XPath); }
    public WebElement Label_HeaderPopup() { return findElement("//p[text()='Are you sure you want to revive the trip?']", LocatorType.XPath); }
    public WebElement Label_PickupId() { return findElement("revive-pickup-id", LocatorType.Id); }
    public WebElement Label_PickupCustomer() { return findElement("revive-pickup-customer", LocatorType.Id); }
    public WebElement Label_Drop_Off_Location_For_Scheduled () { return findElement("//span[contains(text(),'Drop Off Location:')]",LocatorType.XPath);}
    public WebElement Button_Edit_Drop_Off_Address_For_scheduled () { return findElement("//img[@title='Edit drop off Location']",LocatorType.XPath);}
    public WebElement Textbox_Drop_Off_Location_For_Scheduled () { return findElement("//img[@title='Edit drop off Location']/ancestor::div[2]/div[1]/div/div/input",LocatorType.XPath);}
    public WebElement DropOff_Address_For_Scheduled() { return findElement("//img[@title='Edit drop off Location']/ancestor::div[2]/div/label",LocatorType.XPath);}
    public WebElement Header_EditLiveBungiiOrEditScheduledBungii(){return  findElement("exampleModalLongTitle",LocatorType.Id);}
    public WebElement Button_VerifyDriverForScheduled(){return findElement("//button[contains(text(),'VERIFY')]", LocatorType.XPath);}
    public WebElement Image_ThreeDots () {return findElement("//tr[1]/td[14]/div/img",LocatorType.XPath);}

    public WebElement Menu_Trips () { return findElement("//ul[@id='side-menu']/li/p/a/span[contains(text(),'Deliveries')]", LocatorType.XPath); }
    public WebElement Dropdown_SearchForPeriod () { return findElement("//div[text()='The following deliveries from:']/select", LocatorType.XPath); }
    public WebElement Menu_AllDeliveries () { return findElement("//a[contains(text(),'Completed Deliveries')]",LocatorType.XPath);}
    public WebElement Text_DeliveryStatus(int number) { return findElement(String.format("//tbody/tr/td[%d]",number), LocatorType.XPath);}

}
