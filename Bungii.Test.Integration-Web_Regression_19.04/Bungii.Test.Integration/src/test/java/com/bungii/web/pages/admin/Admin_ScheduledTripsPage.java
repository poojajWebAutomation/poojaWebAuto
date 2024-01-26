package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Admin_ScheduledTripsPage extends PageBase {

    public WebElement Menu_ScheduledTrips () { return findElement("//a[contains(text(),'Scheduled Deliveries')]", LocatorType.XPath); }

    public WebElement Menu_CompletedDeliveries () { return findElement("//a[contains(text(),'Completed Deliveries')]", LocatorType.XPath); }

    //public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Button_Submit () { return findElement("CustomerCancel", LocatorType.Name); }

    public WebElement Textbox_CancellationFee () { return findElement("cancelationFee", LocatorType.Id); }

    public WebElement Label_Drop_Off_Location () { return findElement("//label[contains(text(),'DROP-OFF')]",LocatorType.XPath);}

    public WebElement Label_Pickup_Location () { return findElement("//label[contains(text(),'PICKUP')]",LocatorType.XPath);}

    public WebElement Label_Market () { return findElement("//th[text()='Market']",LocatorType.XPath);}

    public WebElement Label_Delivery_Portal () { return findElement("//th[text()='Delivery Type']",LocatorType.XPath);}

    public WebElement Button_Edit_Drop_Off_Address () { return findElement("//img[@title='Edit Drop-off Location']",LocatorType.XPath);}

    public WebElement Button_Edit_Pickup_Address () { return findElement("//img[@title='Edit Pickup Location']",LocatorType.XPath);}


    //public WebElement Admin_Dropdown_ServiceLevel(String serviceLevel) { return findElement("//li/div/div/span[@class='service-title' and @data-name='"+serviceLevel+"']",LocatorType.XPath);}
    public WebElement Admin_Dropdown_ServiceLevel() { return findElement("//select[@class='service-level-mt form-select']",LocatorType.XPath);}
    public WebElement Admin_DropdownServiceLevelSelected() { return findElement("//select[@class='service-level-mt form-select']",LocatorType.XPath);}
    public WebElement Link_Grid_First_Row() { return findElement("//tr[@id='row1']/td[4]/a",LocatorType.XPath);}
    public WebElement Textbox_Drop_Off_Location () { return findElement("//input[contains(@placeholder,'Enter Drop-off Address')]",LocatorType.XPath);}
    public WebElement Textbox_Pickup_Location () { return findElement("//input[contains(@placeholder,'Enter Pickup Address')]",LocatorType.XPath);}

    //public WebElement FirstAddressDropdownResult () { return findElement("//div[@id='divPlacesResult']/div[1]",LocatorType.XPath);}

    public WebElement DropdownResult (String address) { return findElement(String.format("//div[contains(.,'%s')]",address),LocatorType.XPath);}

    public WebElement DropdownPickupResult () { return findElement(String.format("//div[@class='autocomplete-dropdown-container']/div[1]/span"),LocatorType.XPath);}

    public WebElement DropOff_Address() { return findElement("//label[contains(text(),'DROP-OFF')]/following::span/h4",LocatorType.XPath);}

    public WebElement Pickup_Address() { return findElement("//label[text()='PICKUP']/following-sibling::span/h4",LocatorType.XPath);}
    public WebElement Text_Pickup_Address_For_Live() { return findElements("//img[@title='Edit Pickup Location']/ancestor::div[2]/div/label",LocatorType.XPath).get(1);}


    public WebElement Textbox_CancellationComment () { return findElement("Secondary", LocatorType.Id); }

    public WebElement Dropdown_CancellationReason () { return findElement("//select[@class='form-select']", LocatorType.XPath); }

    public WebElement Dropdown_Reason () { return findElement("//select[@class='reason form-select']", LocatorType.XPath); }

    public WebElement Dropdown_ChangeAddress (String address) { return findElement("//div[@class='autocomplete-dropdown-container']/div/span[contains(text(),'"+address+"')]", LocatorType.XPath); }


    //public WebElement RadioButton_CancelBungii () { return findElement("//span[text()='Cancel entire Bungii and notify driver(s)']/preceding-sibling::input", LocatorType.XPath); }
    public WebElement RadioButton_CancelBungii () { return findElement("//label[text()='Cancel entire Bungii and notify driver(s)']/preceding-sibling::input", LocatorType.XPath); }

    public WebElement RadioButton_RemoveDriver () { return findElement("radio1", LocatorType.Id); }

    //public WebElement Button_RemoveDrivers () { return findElement("(//input[@value='Remove Driver(s)'])[2]", LocatorType.XPath); }
    public WebElement Button_RemoveDrivers () { return findElement("//button/strong[contains(text(),'Remove') and @class!='disabled']", LocatorType.XPath); }

    //public WebElement Button_Research () { return findElement("(//input[@value='Re-search'])[2]", LocatorType.XPath); }
    public WebElement Button_Research () { return findElement("//strong[contains(text(),'Re-search a driver')]", LocatorType.XPath); }

    //public WebElement Checkbox_driver (String driver) { return findElement("(//div/label[contains(.,'"+driver+"')])[2]/input[1]", LocatorType.XPath); }
    public WebElement Checkbox_driver () { return findElement("checkbox0", LocatorType.Id); }

    public WebElement Label_SuccessMessage () { return findElement("SuccessMessage", LocatorType.Id); }

    public WebElement Label_CancelSuccessMessage () { return findElement("//span[@id='cancel-success-message']/i[2]", LocatorType.XPath); }

    public WebElement Label_CancelSuccessMessageLive () { return findElement("//i[contains(text(),'Pick up has been successfully canceled.')]", LocatorType.XPath); }

    public WebElement Label_DeliverySuccessMessageLive()  { return findElement("//i[contains(text(),'Pick up has been successfully updated.')]", LocatorType.XPath); }

    public WebElement Dropdown_SearchForPeriod () { return findElement("//select[@class='user-trip-dropdown form-select']", LocatorType.XPath); }

    public WebElement Checkbox_NonControlDriver () { return findElement( "//div[1]/div[2]/div[1]/div[1]/input[1]", LocatorType.XPath);}

    public WebElement Checkbox_ControlDriver () { return findElement( "//div[@id='tripDriverDetails']/div[2]/label[1]/input", LocatorType.XPath);}

    public WebElement Checkbox_ControlDriverEdit () { return findElement( "//div[@class='driver-checkbox w1']/input[@id='checkbox0']", LocatorType.XPath);}

    public WebElement Checkbox_NonControlDriverEdit () { return findElement( "//div[@class='driver-checkbox w1']/input[@id='checkbox1']", LocatorType.XPath);}

    public WebElement Button_RemoveDriversEdit () { return findElement("(//strong[text()='Remove'])[2]", LocatorType.XPath); }

    public WebElement Label_DriverRemovalSuccessMessage () { return findElement( "//i[text()='Driver(s) removed successfully']" , LocatorType.XPath); }

    public WebElement Button_Close () { return findElement("//button[@class='btn-close']", LocatorType.XPath);}

    public WebElement Estimated_Distance_Cost () { return findElement("",LocatorType.XPath);}

    public WebElement Textbox_Search () { return findElement("SearchCriteria", LocatorType.Id); }
    public WebElement Button_Search(){return  findElement("btnSearch",LocatorType.Id);}

    public WebElement List_ViewDeliveries(){return  findElement("//div[@class='popover-hover']/a[contains(text(),'Delivery Details')]",LocatorType.XPath);}

    //Deivery details
    public WebElement Link_DeliveryDetails(){return  findElement("//div[@class='threedoticon']/img",LocatorType.XPath);}

    public WebElement Text_Admin_CustomerName(){return  findElement("//tbody/tr[1]/td[7]/a",LocatorType.XPath);}

    public WebElement Text_Admin_TrackingId(){return  findElement("//div/h4[3]",LocatorType.XPath);}

    public WebElement Dropdown_LiveDelivery_Details(){return  findElement("//a[text()=\"Delivery Details\"]",LocatorType.XPath);}
    public WebElement List_ViewEdit(){return  findElement("//div/a[text()='Edit']",LocatorType.XPath);}
    public WebElement Dropdown_Edit_DeliveryDetails(){return  findElement("//label/span[text()='Edit Delivery Details']",LocatorType.XPath);}

    public WebElement Link_Edit_dropOffLocation(){return  findElement("//div/img[@class=\"cursor editDropoffAddress\"]",LocatorType.XPath);}

    public WebElement Textbox_Edit_dropOfflocationAddress(){return  findElement("PickupDetails_DestinationAddress",LocatorType.Id);}

    public WebElement Text_SelectAdd(){return  findElement("//div[@data-name='Fort Lesley J. McNair']",LocatorType.XPath);}


    public WebElement RadioButton_EditDeliveryDetails(){return  findElement("exampleModalLongTitle",LocatorType.Id);}

    public WebElement Button_Edit_Verify(){return  findElement("//button[text() =\"VERIFY\"]",LocatorType.XPath);}

    public WebElement Button_Edit_Save(){return  findElement("//button[text() =\"SAVE\"]",LocatorType.XPath);}

    public WebElement Button_Edit_Close(){return  findElement("//div/button[@class =\"close\"]",LocatorType.XPath);}

    public WebElement Text_NewDropoffAddress(){return  findElement("lblDestinationAddress",LocatorType.Id);}


    public WebElement Text_Delivery_Scheduled(){return  findElement("//td[contains(text(),'Scheduled')]",LocatorType.XPath);}

    public WebElement Text_Delivery_Successfull(){return  findElement("//td[contains(text(),'Payment Successful')]",LocatorType.XPath);}

    public WebElement Text_Delivery_TripStarted(){return  findElement("//tbody/tr/td[contains(text() ,\"Trip Started\")]",LocatorType.XPath);}
    public WebElement Dropdown_Notes_History(){return  findElement("//body/div[@id='popover-basic']/div[2]/div[3]",LocatorType.XPath);}
    public WebElement Text_DropOff_Details(){return  findElement("(//h6[text()='Contact Details'])[2]/following-sibling::div[1]",LocatorType.XPath);}
    public WebElement Dropdown_NotesHistoryCompletedDelivery(){return  findElement("//body/div[@id='popover-basic']/div[2]/div[2]",LocatorType.XPath);}

    public WebElement Text_NotesEmpty_Message(){return  findElement("//span[text()='No notes available. Please start entering notes to appear here.']",LocatorType.XPath);}

    public WebElement Text_HistoryEmptyMessage(){return  findElement("history-tab",LocatorType.Id);}

    public WebElement Textbox_AddNote(){return  findElement("ExpiryDate",LocatorType.Id);}

    public WebElement Button_SaveNote(){return  findElement("//button[text()='Save']",LocatorType.XPath);}

    public WebElement Text_FirstSavedNote(){return  findElement("//div[1][@class =\"note\"]/div/span",LocatorType.XPath);}

    public WebElement Text_AdminName(){return  findElement("//div[2]/p[1]/strong",LocatorType.XPath);}

    public WebElement Text_AdminCreatedNote(){return  findElement("//div[1][@class =\"note\"]/h5",LocatorType.XPath);}

    public WebElement Text_Admin2Name(){return  findElement("//div[2][@class =\"note\"]/h5",LocatorType.XPath);}

    public WebElement Link_EditNote(){return  findElement("(//div/a[text() =\"Edit\"])[2]",LocatorType.XPath);}

    public WebElement Link_EditNote_NotDisplayed(boolean...ignoreException){return  findElement("(//div/a[text() =\"Edit\"])[2]",LocatorType.XPath,ignoreException); }

    public WebElement Link_DeleteNote(){return  findElement("//div/a[text() =\"Delete\"]",LocatorType.XPath);}

    public WebElement Link_ConfirmDeleteNote(){return  findElement("//div/a[text() =\"Yes\"]",LocatorType.XPath);}

    public WebElement Button_NoteClose(){return  findElement("//button[@class='btn-close']",LocatorType.XPath);}

    public WebElement Text_EditNote_TextArea(){return  findElement("(//div[1]/div/textarea)[1]",LocatorType.XPath);}

    public WebElement Link_NoteUpdate(){return  findElement("//div/div/a[text() =\"Update\"]",LocatorType.XPath);}

    public WebElement Link_Notes(){return  findElement("//span[text()='Notes']",LocatorType.XPath);}

    public WebElement Link_History(){return  findElement("//span[contains(text(),'History')]",LocatorType.XPath);}

    public List<WebElement> List_Notes(){return  findElements("//div[@class ='note']/div/span",LocatorType.XPath);}

    public List<WebElement> List_AllNotes(){return  findElements("//div[@class ='note']",LocatorType.XPath);}

    public WebElement Text_AdminCreatedNotes(int note) { return findElement(String.format("//div[%s][@class ='note']/div/span",note), LocatorType.XPath); }

    public WebElement Text_AdminNames(int admin) { return findElement(String.format("//div[%s][@class ='note']/h5",admin), LocatorType.XPath); }

    public WebElement Text_NoteTime(){return  findElement("//div[1][@class =\"note\"]/label",LocatorType.XPath);}

    public WebElement Text_DeliveryStatus(int status) { return findElement(String.format("//tr/td[%d]",status), LocatorType.XPath); }

    public WebElement Button_View(){return  findElement("//div/a[text() =\"View\"]",LocatorType.XPath);}

    public WebElement Text_TripIndicator(boolean...ignoreException){return  findElement("//tr[@id=\"row1\"]/td[1]/label",LocatorType.XPath,ignoreException);}

    public WebElement Text_ScheduledTripDate(){return  findElement("//tr[1]/td[5]/a",LocatorType.XPath);}

    public WebElement Link_EditScheduleTripCalenderNextMonth(){return  findElement("//div/a[2]/span[1]",LocatorType.XPath);}

    public WebElement Link_EditScheduleTripCalenderPreviousMonth(){return  findElement("//span[contains(@class, 'navigation-icon--previous')]",LocatorType.XPath);}

    public WebElement Link_NewScheduleDeliveryDate(String newDate){return  findElement(String.format("//div[contains(@class, 'react-datepicker')]/div[text()=\"%s\"]",newDate),LocatorType.XPath);}

    public WebElement Button_History(){return findElement("//h5[text()='History']",LocatorType.XPath);}

    public WebElement Text_AdminNameHistoryTab(){return findElement("//div[@id=\"history-tab\"]/div/h5",LocatorType.XPath);}

    public WebElement Header_HistoryEvent(){return findElement("//div[@id=\"history-tab\"]/div/div/table/tbody/tr/th[1]",LocatorType.XPath);}

    public WebElement Header_HistoryOldValue(){return findElement("//div[@id=\"history-tab\"]/div/div/table/tbody/tr/th[2]",LocatorType.XPath);}

    public WebElement Header_HistoryNewValue(){return findElement("//div[@id=\"history-tab\"]/div/div/table/tbody/tr/th[3]",LocatorType.XPath);}

    public WebElement Text_HistoryEventValue(){return findElement("//tr/th[text()='Event']/following::tr/td[1]",LocatorType.XPath);}

    public WebElement Text_HistoryOldValueData(){return findElement("//tr/th[text()='Event']/following::tr/td[2]",LocatorType.XPath);}

    public WebElement Text_HistoryNewValueData(){return findElement("//tr/th[text()='Event']/following::tr/td[3]",LocatorType.XPath);}

    public WebElement Text_HistoryEditedTime(){return findElement("//div[@id=\"history-tab\"]/div/label",LocatorType.XPath);}

    public WebElement Text_HistoryEventValuePreviousEdit(){return findElement("//div[@id=\"history-tab\"]/div[2]/div/table/tbody/tr[2]/td[1]",LocatorType.XPath);}

    public WebElement Text_HistoryOldValueDataPreviousEdit(){return findElement("//div[@id=\"history-tab\"]/div[2]/div/table/tbody/tr[2]/td[2]",LocatorType.XPath);}

    public WebElement Text_HistoryNewValueDataPreviousEdit(){return findElement("//div[@id=\"history-tab\"]/div[2]/div/table/tbody/tr[2]/td[3]",LocatorType.XPath);}


    public WebElement Text_HistoryEventValueRow2(){return findElement("//div[@id=\"history-tab\"]/div[2]/div/table/tbody/tr[2]/td[1]",LocatorType.XPath);}

    public WebElement Text_HistoryOldValueDataRow2(){return findElement("//div[@id=\"history-tab\"]/div[1]/div/table/tbody/tr[2]/td[3]",LocatorType.XPath);}

    public WebElement Text_HistoryNewValueDataRow2(){return findElement("//div[@id=\"history-tab\"]/div[1]/div/table/tbody/tr[2]/td[2]",LocatorType.XPath);}

//    public WebElement Link_NotesHistoryLiveDelivery(){return findElement("//ul[@class=\"dropdown-menu\"]/li[3]/p",LocatorType.XPath);}
    public WebElement Link_NotesHistoryLiveDelivery(){return findElement("//body/div[@id='popover-basic']/div[2]/div[3]",LocatorType.XPath);}


    public WebElement Text_HistoryTabInformation(int row ,int col){return  findElement(String.format("//div[@class=\"old-note\"]/table/tbody/tr[%d]/td[%d]",row,col),LocatorType.XPath);}

    public WebElement Button_StopSearching(boolean...ignoreException) {return findElement("//button[text()='Stop searching']",LocatorType.XPath,ignoreException);}

    public WebElement Button_ConfirmStopSearching() {return findElement("//button[text()='Confirm']",LocatorType.XPath);}

    public WebElement Text_ConfirmationPopUp() {return findElement("//div/div[contains(text(),'Stop Searching?')]",LocatorType.XPath);}

    public WebElement Text_SuccessPopUp() {return findElement("//div[contains(text(),'Search stopped successfully.')]",LocatorType.XPath);}

    public WebElement Text_ErrorPopUp() {return findElement("//p[contains(text(),'Oops! something went wrong.')]",LocatorType.XPath);}

    public WebElement Button_CloseConfirm() {return findElement("//button[text()='Close']",LocatorType.XPath);}

    public WebElement Button_Ok() { return findElement("//div[@id='btnOk']",LocatorType.XPath);}

    public WebElement Text_DeliveryDetailsStatus(){return  findElement("//h4[contains(text(),'Status :')]/following-sibling::span",LocatorType.XPath);}

    public WebElement Text_TripStatus(){return  findElement("//h4[contains(text(),'Status :')]/following-sibling::span", LocatorType.XPath);}

    public WebElement Text_InitialRequestDate(){return findElement("//td[4]", LocatorType.XPath);}

    public WebElement Text_ScheduledDate(){return findElement("//td[5]/a",LocatorType.XPath);}

    public WebElement Text_DeliveryMiles(){return  findElement("//td[text()='Delivery Distance']/following::td[1]/strong",LocatorType.XPath);}

    public WebElement Link_BungiiDate() {return findElement("//tr[1]/td/a[contains(@href,'tripRef')]", LocatorType.XPath);}

    public WebElement Header_EditLiveBungiiOrEditScheduledBungii(){return  findElement("exampleModalLongTitle",LocatorType.Id);}
    public WebElement Dropdown_ChangeAddress_For_Live (String address) { return findElement("//div[@class='autocomplete-dropdown-container']/div/span[contains(text(),'"+address+"')]", LocatorType.XPath); }
    public WebElement DropOff_Address_For_Live() { return findElement("//div[@class=\"live-edit\"]/div/div[3]/div[2]/div/div[2]/label[contains(text(),'DROP-OFF')]/following-sibling::span/h4",LocatorType.XPath);}
    public WebElement Button_Edit_Drop_Off_Address_For_Live () { return findElement("//div[@class=\"live-edit\"]/div/div[3]/div[2]/div/div[2]/label[contains(text(),'DROP-OFF')]/following-sibling::span/img",LocatorType.XPath);}
    public WebElement Label_Drop_Off_Location_For_Live () { return findElement("//div[@class=\"live-edit\"]/div/div[3]/div[2]/div/div[2]/label[contains(text(),'DROP-OFF')]",LocatorType.XPath);}
    public WebElement Textbox_Drop_Off_Location_For_Live () { return findElement("//div[@class=\"live-edit\"]/div/div[3]/div[2]/div/div[2]/label[contains(text(),'DROP-OFF')]/following-sibling::span/div/input",LocatorType.XPath);}
    public WebElement Label_Pickup_Location_For_Live () { return findElement("//div[@class=\"live-edit\"]/div/div[3]/div[2]/div/div[1]/label[contains(text(),'PICKUP')]",LocatorType.XPath);}
    public WebElement Textbox_Pickup_Location_For_Live () { return findElement("//div[@class=\"live-edit\"]/div/div[3]/div[2]/div/div[1]/label[contains(text(),'PICKUP')]/following-sibling::span/div/input",LocatorType.XPath);}
    public WebElement Button_Edit_Pickup_Address_For_Live () { return findElement("//div[@class=\"live-edit\"]/div/div[3]/div[2]/div/div[1]/label[contains(text(),'PICKUP')]/following-sibling::span/img",LocatorType.XPath);}
    public WebElement Text_EstimatedDeliveryTime() {return findElement("//h6[contains(text(),'Estimated Delivery Time')]/following-sibling::p", LocatorType.XPath);}

    public WebElement Text_ScheduledDelivery() {return findElement("//td/strong[text()=\"Scheduled Time: \"]/following::td[1]", LocatorType.XPath);}

    public WebElement Text_AdvanceScheduledStatus() {return  findElement("//table[contains(@class, 'ScheduledDeliveries')]//td[contains(text(),'Pending')]", LocatorType.XPath);}
}
