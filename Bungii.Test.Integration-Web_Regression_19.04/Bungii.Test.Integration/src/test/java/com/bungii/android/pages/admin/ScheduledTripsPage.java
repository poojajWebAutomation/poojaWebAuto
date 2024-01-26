package com.bungii.android.pages.admin;

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
        return findElement("//p[@id='cancel-success-message']/i[last()]", LocatorType.XPath);

    }
    public WebElement TextBox_Phone() {
        return findElement("PhoneNo", LocatorType.Name);
    }
    public List<WebElement> Row_TripDetails() { return findElements("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]", LocatorType.XPath); }

    public WebElement TableBody_TripDetails() {
        return findElement("TripListsTBody", LocatorType.Id);
    }

    public WebElement RadioBox_Cancel() {return findElement("//label[text()='Cancel entire Bungii and notify driver(s)']/preceding-sibling::input", LocatorType.XPath);}

    public WebElement RadioBox_Research() {return findElement("//label[contains(@class,'vertical-middle mb20 custom-input driverCancel')]/span", LocatorType.XPath); }

    public WebElement TextBox_CancelFee() {
        return findElement("txtCancellationFee", LocatorType.Id);
    }

    public WebElement TextBox_Comments() {
        return findElement("txtCustomerCancellationComments", LocatorType.Id);
    }

    public WebElement Button_Submit(boolean ...ignoreException) {
        return findElement("CustomerCancel", LocatorType.Name, ignoreException);
    }
    //changes as per v2
    public WebElement Label_Drop_Off_Location () { return findElement("//div[@class=\"live-edit\"]/div/div[4]/div[1]/span[contains(text(),'Drop Off Location:')]",LocatorType.XPath);}
    public WebElement Label_Drop_Off_Location_For_Scheduled () { return findElement("//span[contains(text(),'Drop Off Location:')]",LocatorType.XPath);}
    public WebElement Button_Edit_Drop_Off_Address () { return findElement("//div[@class=\"live-edit\"]/div/div[4]/div[2]/div/div[2]/img[@title='Edit drop off Location']",LocatorType.XPath);}
    public WebElement Button_Edit_Drop_Off_Address_For_scheduled () { return findElement("//img[@title='Edit drop off Location']",LocatorType.XPath);}
    public WebElement Textbox_Drop_Off_Location () { return findElement("//img[@title='Edit drop off Location']/ancestor::div[2]/div[1]/div[@class=\"address-textbox\"]/div/input",LocatorType.XPath);}
    public WebElement Textbox_Drop_Off_Location_For_Scheduled () { return findElement("//img[@title='Edit drop off Location']/ancestor::div[2]/div[1]/div/div/input",LocatorType.XPath);}
    public WebElement DropdownResult (String address) { return findElement(String.format("//div[@id='divPlacesResult']/div[contains(.,'%s')]",address),LocatorType.XPath);}
    public WebElement DropOff_Address() { return findElement("//img[@title='Edit drop off Location']/ancestor::div[2]/div[1]/label[@class=\"address2\"]",LocatorType.XPath);}
    public WebElement DropOff_Address_For_Scheduled() { return findElement("//img[@title='Edit drop off Location']/ancestor::div[2]/div/label",LocatorType.XPath);}
    public WebElement Label_Pickup_Location () { return findElement("//p[contains(text(),'Pickup Location:')]",LocatorType.XPath);}
    public WebElement Button_Edit_Pickup_Address () { return findElement("//img[@title='Edit pickup location']",LocatorType.XPath);}
    public WebElement Textbox_Pickup_Location () { return findElement("PickupDetails_PickupOriginAddress",LocatorType.Id);}
    public WebElement DropdownPickupResult (String address) { return findElement(String.format("//div[@id='divPickupPlacesResult']/div[contains(.,'%s')]",address),LocatorType.XPath);}
    public WebElement Pickup_Address() { return findElement("//label[@id='lblPickupAddress']",LocatorType.XPath);}


    public void waitForLoadingToDisappear(){
        waitForLoadingToDisappear();
    }
    public WebElement Text_SearchCriteria(){return  findElement("SearchCriteria",LocatorType.Id);}
    public WebElement Label_Message(){return  findElement("//span[@id='cancel-success-message']/i[2]",LocatorType.XPath);}
    public WebElement Icon_Dropdown(){return  findElement("//tbody/tr/td/div/img | //div[@class=\"threedoticon\"]",LocatorType.XPath);}
    public WebElement Option_Edit(){return  findElement("//div/a[text()=\"Edit\"]",LocatorType.XPath);}

    public WebElement Button_Search(){return  findElement("btnSearch",LocatorType.Id);}
    public WebElement Button_Research() {return findElement("//strong[text()=\"Re-search a driver\"]", LocatorType.XPath); }
    // wait
    //public WebElement CheckBox_Driver1() {return findElement("//div[@class='tripDrivers row']//label[@class='custom-input checkboxDiv mt0 pull-left']/span", LocatorType.XPath); }
    public WebElement CheckBox_DriverByName(String Name) {return findElement(String.format("//div[@id='tripDriverDetails']/div[@class='driver-research row']/span[contains(text(),'%s')]/preceding-sibling::label/span",Name), LocatorType.XPath); }

    public WebElement CheckBox_Driver1() {return findElement("checkbox0", LocatorType.Id);}
    public WebElement CheckBox_Driver2() {return findElement("checkbox1", LocatorType.Id); }
    public WebElement Button_Remove() {return findElement("//strong[text()=\"Remove\"]", LocatorType.XPath); }

    public WebElement CheckBox_Driver1_Edit() {return findElement("//tr[1]/td/label/input", LocatorType.XPath); }
    public WebElement CheckBox_Driver2_Edit() {return findElement("//tr[2]/td/label/input", LocatorType.XPath); }
    public WebElement Button_Remove_Edit() {return findElement("btnRemoveDriver", LocatorType.Id); }
    public WebElement RadioBox_EditTrip() {return findElement("radio5", LocatorType.Id);}
    public WebElement RadioBox_EditTripForScheduled() {return findElement("radio3", LocatorType.Id);}
    public WebElement Text_EditTripType() {return findElement("//div[@class='CancelComments row']/p[2]", LocatorType.XPath);}
    public WebElement Calendar_EditTripDetailsScheduledDate() {return findElement("PickupDetails_ScheduledDate", LocatorType.Id);}
    public WebElement Calendar_NextDate() {return findElement("//td[@data-handler='selectDay'][1]/following-sibling::td[1]/a", LocatorType.XPath);}
    public WebElement Time_FirstAvailable(){return findElement("//ul[@class='ui-timepicker-list']/li[1]", LocatorType.XPath);}
    public WebElement icon_Close(){return findElement("//button[@class='close']", LocatorType.XPath);}

    public WebElement Time_EditTripDetailsTime(){return findElement("PickupDetails_ScheduledTime", LocatorType.Id);}
    public WebElement TextBox_DriverSearch() {return findElement("//div/div/input[@placeHolder=\"Enter driver name\"]", LocatorType.XPath);}
    public WebElement Button_VerifyDriver(){return findElement("//div[@class=\"live-edit\"]/div/div[5]//button[contains(text(),'VERIFY')]", LocatorType.XPath);}
    public WebElement Button_VerifyDriverForScheduled(){return findElement("//button[contains(text(),'VERIFY')]", LocatorType.XPath);}

    public WebElement Select_TestDriver(){return findElement("//input[@placeholder='Enter driver name']/following-sibling::div/div[1]", LocatorType.XPath);}

    public WebElement Text_EditTrpDetailsDriver1Name(){return findElement("//div/span[text()=\"Driver Details:\"]/following::div[@class=\"driver-edit\"][1]/div/span[1]", LocatorType.XPath);} //3 is correct index
    public WebElement Text_EditTrpDetailsDriver2Name(){return findElement("//div/span[text()=\"Driver Details:\"]/following::div[@class=\"driver-edit\"][2]/div/span[1]", LocatorType.XPath);} //3 is correct index

    public WebElement Text_EditTrpDetailsDriver1NamePrefilled(){return findElement("//div[1]/div[@class=\"driver-edit\"]/div/span[1]", LocatorType.XPath);} //2 is correct index for name for prefilled
    public WebElement Text_EditTrpDetailsDriver2NamePrefilled(){return findElement("//table[@id='editTripDrivers']/tbody/tr[2]/td/table/tbody/tr/td[2]", LocatorType.XPath);} //2 is correct index for name for preifilled


    public WebElement Text_Driver1Name(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tv_drivername_value']", LocatorType.XPath).get(0);}
    public WebElement Text_Driver2Name(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tv_drivername_value']", LocatorType.XPath).get(1);}
     //v2 changes
    public WebElement Text_VerifyChangesSavedMessage() {return findElement("//span[@id='verified-message']/span", LocatorType.XPath);}
    public WebElement Button_SaveChanges(){return findElement("//button[contains(text(),'SAVE')]", LocatorType.XPath);}

    public WebElement Text_SuccessMessage(){return findElement("//span/span[text()=\"Bungii Saved!\"]", LocatorType.XPath);}
    public WebElement Label_IconTextMessage(){return findElement("//table[@id='editTripDrivers']/tbody/tr/td/table/tbody/tr/td", LocatorType.XPath);}
    public WebElement Label_StaticText(){return findElement("//em", LocatorType.XPath);}

    public WebElement Label_ChangedScheduledTime(){return findElement("//div[@class='tripDrivers row']//p[contains(text(),'Schedule Time:')]/following-sibling::p[1]", LocatorType.XPath);}
    public WebElement Button_ClosePopUp(){return findElement("//button[@class='btn-close']", LocatorType.XPath);}

    public WebElement Text_BungiiTime(){return findElements("//android.widget.RelativeLayout/android.widget.TextView", LocatorType.XPath).get(2);}
    public WebElement Text_ConflictMessageError() {return findElement("//p[@id='conflict-message']/strong/i[@id='verify-error']", LocatorType.XPath);}

    public WebElement Text_ScheduledBungiiTime(){return findElement("//div[@class='tripDrivers row']/p[@class='col-sm-7 col-md-8 col-lg-9 lblScheduledTime']", LocatorType.XPath);}
    public WebElement Text_DriverNames(){return findElement("//div[@id='tripDriverDetails']/div/span[1]", LocatorType.XPath);}

    public WebElement Select_CancellationReason() {return findElement("txtCancellationRemark", LocatorType.Id);}

    public WebElement Select_EditReason() {return findElement("ddEditDeliveryRemark",LocatorType.Id);}


    public WebElement Button_StopSearching() {return findElement("btnStopSearch",LocatorType.Id);}

    public WebElement Button_ConfirmStopSearching() {return findElement("btnConfirm",LocatorType.Id);}

    public WebElement Button_CloseConfirm() {return findElement("//div[@id='stop-search-success-modal']/div/div/div/button[text()='Close']",LocatorType.XPath);}

    public WebElement Text_BungiiStatus(){return findElement("//td[text()='Status']/following::td[1]/strong", LocatorType.XPath);}

    public WebElement Button_Price_Override() {return findElement("//td/a[@id='btnAdminOverride']",LocatorType.XPath);}
    public WebElement Text_Driver_Est_Earnings() {return findElement("//td[text()=' Driver Fixed Earnings - Pallet 1(1111 lbs)']/following::td[1]/strong",LocatorType.XPath);}
    public WebElement Textbox_Override_Driver_Cut() {return findElement("//div/input[@id='driverOneShare']",LocatorType.XPath);}
    public WebElement Dropdown_Reason_Override_Driver_Cut() { return findElement("//div/select[@id='driverEarningsReason']",LocatorType.XPath);}
    public WebElement Button_Save() { return findElement("saveAdminOverride",LocatorType.Id);}
    public WebElement Button_Success_Ok() { return findElement("//div[@class='modal-footer']/button[text()='Ok']",LocatorType.XPath);}

    public WebElement Button_Ok() { return findElement("//div[@id='btnOk']",LocatorType.XPath);}
    public WebElement Dropdown_Result (boolean ...ignoreException) { return findElement("ddEditDeliveryRemark",LocatorType.Id, ignoreException); }
    public WebElement TimePicker_Time () { return findElement("PickupDetails_ScheduledTime", LocatorType.Id); }
    public WebElement Dropdown_ScheduledDateTime() { return findElement("//li[@class='ui-timepicker-am ui-timepicker-selected']/following-sibling::li[3]", LocatorType.XPath); }
    public WebElement RadioButton_Solo() { return findElement("//input[@value='Solo']", LocatorType.XPath); }
    public WebElement Dropdown_ServiceLevel() { return findElement("//select[@class='service-level-mt form-select']",LocatorType.XPath);}
    public WebElement Button_Accept() { return findElement("com.bungii.driver:id/activity_pickup_request_accept_available_pickup_button", PageBase.LocatorType.Id); }


    public WebElement Button_ReviveTrip() { return findElement("//a[@class='revive-trip-link']/img",LocatorType.XPath);}
    public WebElement Label_HeaderPopup() { return findElement("//p[text()='Are you sure you want to revive the trip?']", LocatorType.XPath); }
    public WebElement Label_PickupId() { return findElement("revive-pickup-id", LocatorType.Id); }
    public WebElement Label_PickupCustomer() { return findElement("revive-pickup-customer", LocatorType.Id); }
    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm']", LocatorType.XPath); }

    //partner portal
    public WebElement TextBox_PartnerLoginPassword() { return findElement("formBasicPassword", LocatorType.Id); }
    public WebElement Button_SignIn() { return findElement("login", LocatorType.Id); }
    public WebElement Dropdown_Setting(boolean...ignoreException) { return findElement("//div[@class='dropdown-menu-right dropdown']",LocatorType.XPath, ignoreException);}
    public WebElement Button_TrackDeliveries() { return findElement("track-deliveries",LocatorType.Id);}
    public WebElement Label_StartOver() { return findElement("//span[contains(text(),'Start Over')]",LocatorType.XPath);}
    public WebElement Link_Setting() { return findElement("//i[@title='Menu']", LocatorType.XPath);}
    public WebElement Textbox_Password() { return findElement("//input[@id='name']",LocatorType.XPath);}
    public WebElement Button_Continue() { return  findElement("//div[@class='btn-group']//button[@class='btn'][contains(text(),'Continue')]",LocatorType.XPath);}
    public WebElement Link_CancelDelivery() { return findElement("//button[@class='btn cancel-delivery']",LocatorType.XPath);}
    public WebElement Button_OK() { return findElement("//button[@class='btn']",LocatorType.XPath);}
    public WebElement Button_OkOnDeliveryCancellationFailed() { return findElement("//button[@class='btn btn btn-primary']",LocatorType.XPath);}
    public WebElement Button_CancelDelivery() { return findElement("//button[@class='btn btn-clear btn-clear-red']",LocatorType.XPath);}
    public WebElement Text_TrackingIdColumn() { return findElement("//tr/th[2]/div",LocatorType.XPath);}
    public WebElement Text_TripCustomer() { return findElement("//tr[1]/td[3]/div[1]",LocatorType.XPath);}
    public WebElement Text_TripTrackingId() { return findElement("//tr[1]/td[2]/div[1]",LocatorType.XPath);}
    public WebElement Text_TripDeliveryAddress() { return findElement("//tr[1]/td[4]/div[1]",LocatorType.XPath);}
    public WebElement Textbox_SearchBar() { return findElement("searchText", LocatorType.Id); }
    public WebElement Link_SelectTripTrackDeliveries() { return findElement("//table/tbody/tr",LocatorType.XPath);}
    //Driver earnings
    public WebElement Text_SoloDriverEarnings() {return findElement("//td[contains(text(),' Driver Fixed Earnings')]/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_DuoDriver1Earnings() {return findElement("//td[contains(text(),' Driver Fixed Earnings - Pallet 1')]/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_DuoDriver2Earnings() {return findElement("//td[contains(text(),' Driver Fixed Earnings - Pallet 2')]/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_EstimateCharge() {return findElement("//td[contains(text(),'Estimated Charge')]/following-sibling::td/strong", LocatorType.XPath);}

    public WebElement Text_SoloDriverEarningsApp() {return findElement("//android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]", LocatorType.XPath);}
    public WebElement Text_SoloDriverEarningsApp1() {return findElement("//android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]", LocatorType.XPath);}
    public WebElement Text_DuoDriver1EarningsApp() {return findElement("//android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[2]", LocatorType.XPath);}
    public WebElement Text_DuoDriver2EarningsApp() {return findElement("//android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[2]", LocatorType.XPath);}
    public WebElement Textbox_CancellationFee () { return findElement("cancelationFee", LocatorType.Id); }
    public WebElement Textbox_CancellationComment () { return findElement("Secondary", LocatorType.Id); }
    public WebElement Dropdown_CancellationReason () { return findElement("//select[@class='form-select']", LocatorType.XPath); }
    public WebElement Label_CancelSuccessMessage () { return findElement("//span[@id='cancel-success-message']/i[2]", LocatorType.XPath); }
    public WebElement Button_ReviveTrip (boolean... IgnoreException) { return findElement("//tbody/tr/td[11]/a/img", LocatorType.XPath,IgnoreException); }
    public WebElement TextBox_Search() {return findElement("SearchCriteria", LocatorType.Id); }
    public WebElement Button_Cancel() { return findElement("//button[text()='Cancel']", LocatorType.XPath); }
    public WebElement Button_Submit () { return findElement("CustomerCancel", LocatorType.Name); }

    public WebElement RadioButton_PalletOne() {return findElement("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.bungii.driver:id/rv_pallet_list']/android.view.ViewGroup[1]/android.widget.RadioButton[1]", LocatorType.XPath);}
    public WebElement RadioButton_PalletTwo() {return findElement("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.bungii.driver:id/rv_pallet_list']/android.view.ViewGroup[2]/android.widget.RadioButton[1]", LocatorType.XPath);}
    public WebElement Text_PalletOne() { return findElement("//android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_PalletTwo() { return findElement("//android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_PalletOneWeight() { return findElement("//android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_PalletOneDimensions() { return findElement("//android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Text_PalletOneName() { return findElement("//android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[3]", LocatorType.XPath); }
    public WebElement Text_PalletOneWeightSchedulePage() { return findElement("//androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_PalletOneDimensionsSchedulePage() { return findElement("//androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Text_PalletOneNameSchedulePage() { return findElement("//android.view.ViewGroup/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Link_DeliveryDetails(){return  findElement("//tr/td[14]/div/img",LocatorType.XPath);}

    public WebElement Link_CompletedDeliveryDetails(){return  findElement("//tr/td[13]/div/img",LocatorType.XPath);}

    public WebElement Link_LiveDeliveryDetails(){return  findElement("//tr/td[13]/div/img[1]",LocatorType.XPath);}
    public WebElement List_ViewDeliveries(){return  findElement("//a[contains(text(),'Delivery Details')]",LocatorType.XPath);}
    public WebElement Icon_CustomerHelpAdminPortal(){return  findElement("//div/span[text() =\"Customer help\"]",LocatorType.XPath);}
    //Photo tab on admin portal
    public List<WebElement> List_Photos(){return  findElements("//h5/following-sibling::div",LocatorType.XPath);}
     public WebElement Header_EditLiveBungiiOrEditScheduledBungii(){return  findElement("exampleModalLongTitle",LocatorType.Id);}

}
