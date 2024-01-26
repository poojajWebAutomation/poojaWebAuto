package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;



public class Admin_EditScheduledBungiiPage extends PageBase {

    public WebElement Changed_Date() { return findElement("//div[@aria-selected='true']/following::div[2]", LocatorType.XPath); }

    public WebElement Changed_Time() { return findElement("//td[6]/a", LocatorType.XPath); }

    public WebElement Dropdown_ScheduledDate_Time(String time) { return findElement("//select[@class='timepicker form-control']/option[contains(text(),'"+time+"')]/following-sibling::option[2]", LocatorType.XPath); }

    public WebElement Dropdown_Scheduled_Time_By_15(String time) { return findElement("//select[@class='timepicker form-control']/option[contains(text(),'"+time+"')]/following-sibling::option[1]", LocatorType.XPath);}
    public WebElement Dropdown_Result (boolean ...ignoreException) { return findElement("//select[@class='reason form-select']",LocatorType.XPath, ignoreException); }

    public WebElement Dropdown_Driver_Result (String driverName) { return findElement(String.format("//div[contains(text(),'%s')]",driverName),LocatorType.XPath);}

    public WebElement DatePicker_ScheduledDate () { return findElement("//input[@placeholder='MM/DD/YYYY']", LocatorType.XPath); }

    public WebElement TimePicker_Time () { return findElement("//select[@class='timepicker form-control']", LocatorType.XPath); }

    public WebElement List_TimeFrame (String time) { return findElement("//div/ul/li[text()='"+time+"']", LocatorType.XPath); }

    public WebElement Link_RemoveDriver() { return findElement("(//strong[text()='Remove'])[2]", LocatorType.XPath); }

    public WebElement TextBox_DriverSearch() { return findElement("//input[@placeholder='Enter driver name']", LocatorType.XPath); }

    public WebElement Button_Verify() { return findElement("//button[contains(text(),'VERIFY')]", LocatorType.XPath); }
    public WebElement Button_Verify_For_Live() { return findElement("//div[@class=\"live-edit\"]/div/div/button[contains(text(),'VERIFY')]", LocatorType.XPath); }


    public WebElement Button_Save() { return findElement("//button[contains(text(),'SAVE')]", LocatorType.XPath); }

    public WebElement Button_Undo() { return findElement("//button[@onclick='UndoTripChanges()']", LocatorType.Id); }

    public WebElement Checkbox_Driver (String driverName) { return findElement("//div[@class='driver-checkbox w1']/input", LocatorType.XPath); }

    public WebElement Label_VerifyError() { return findElement("//h6[@id='input-valid-message']/i", LocatorType.XPath); }

    public WebElement Label_VerifiedMessage() { return findElement("verified-message", LocatorType.Id); }

    public WebElement Label_SuccessMessage() { return findElement("//i[text()='Bungii Saved!'] | //span[text()='Bungii Saved!']", LocatorType.XPath); }

    public WebElement Label_InfoMessage() { return findElement("info-message", LocatorType.Id); }

    public WebElement RadioButton_EditTripDetails() { return findElement("//label[text()='Edit Delivery Details']/preceding-sibling::input", LocatorType.XPath); }

    public WebElement List_DriverSearchResult (String driverName) { return findElement("//input/following-sibling::div[contains(.,'"+driverName+"')]", LocatorType.XPath); }

    public WebElement TickMarkDate () { return findElement("//i[@class='fa fa-check text-green-alt success-icon']", LocatorType.XPath); }

    public WebElement TickMarkDriver () { return findElement("//div[@class='driver-verify w1 col-sm-1']", LocatorType.XPath); }

    public WebElement  Button_Edit() {return findElement("//a[contains(text(),'Edit')]",LocatorType.XPath);}

    public WebElement  Button_Delivery_Details() {return findElement("//p[@class='clickable-row']",LocatorType.XPath);}


    public WebElement Button_Close() { return findElement("//button[@class='btn-close']",LocatorType.XPath);}

    public WebElement Text_Estimated_Price() { return findElement("//td[text()='Estimated Charge']/following-sibling::td",LocatorType.XPath);}

    public WebElement Text_Pickup_Note() { return findElement("//td[text()='Pickup Note']/following-sibling::td",LocatorType.XPath);}

    public WebElement Text_Additional_Note(boolean ...ignoreException) { return findElement("//div/span[text()='Additional notes:']/following::div/div/textarea",LocatorType.XPath,ignoreException);}

    public WebElement Text_Additional_Instructions() { return findElement("//div/div[1]/span[contains(text(),'Special Instructions:')]/following::div/textarea",LocatorType.XPath);}

    public WebElement RadioButton_Solo() { return findElement("SOLO", LocatorType.Id); }
    public WebElement RadioButton_Duo() { return findElement("DUO", LocatorType.Id); }

    public WebElement Label_Driver1MessageOnResearch() { return findElement("//div[@class='driver-research']/span[contains(.,'Driver 1: Bungii driver is being searched')]", LocatorType.XPath); }

    public WebElement Label_Driver2MessageOnResearch(boolean...ignoreException) { return findElement("//div[@class='driver-research']/span[contains(.,'Driver 2: Bungii driver is being searched')]", LocatorType.XPath,ignoreException); }

    public WebElement Label_Driver1MessageOnEdit() { return findElement("//span[contains(.,'Driver 1:Add driver below or Bungii driver search will continue')]", LocatorType.XPath); }

    public WebElement Label_Driver2MessageOnEdit(boolean...ignoreException) { return findElement("//span[contains(.,'Driver 2:Add driver below or Bungii driver search will continue')]", LocatorType.XPath,ignoreException); }

    public WebElement Label_DeliveryTypeOnResearch() { return findElement("//div[@class='tripDrivers']/div/div[2]/div[3]/span", LocatorType.XPath); }

    public WebElement Label_DeliveryTypeOnCancel() { return findElement("//div[@class='tripDrivers']/div/div[2]/div[3]/span", LocatorType.XPath); }

    public WebElement Label_Driver1NameOnResearch() { return findElement("//div[@class='driver-checkbox']/following-sibling::span[1]", LocatorType.XPath); }

    public WebElement Label_Driver2NameOnResearch(boolean...ignoreException) { return findElement("//div[@id='tripDriverDetails']/div[2]/span[1]", LocatorType.XPath,ignoreException); }

    public WebElement Label_Driver1NameOnEdit() { return findElement("//div[@class='driver-checkbox w1']/following-sibling::span[1]", LocatorType.XPath); }

    public WebElement Label_Driver2NameOnEdit(boolean...ignoreException) { return findElement("//table[@id='editTripDrivers']/tbody/tr[2]/td/table/tbody/tr/td[2]", LocatorType.XPath,ignoreException); }

    public WebElement Label_ErrorMessage() { return findElement("//h6[@id='input-valid-message']/i", LocatorType.XPath); }

    public WebElement Label_AdditionalNotes() { return findElement("//span[text()='Special Instructions:']", LocatorType.XPath); }

    public WebElement Text_LastTimeSlotAdminEdit() { return findElement("//div[@class=\"ui-timepicker-wrapper\"]/ul/li[96]", LocatorType.XPath); }

    public WebElement Label_WarningForOutsideBungiiHoursTimeSet() { return findElement("//div/small[@id=\"warning-message\"]/em", LocatorType.XPath); }

    public WebElement Icon_Warning() { return findElement("//div/small[@id=\"warning-message\"]/i", LocatorType.XPath); }
    public WebElement RadioButton_EditTripDetails_For_Live() { return findElement("radio5", LocatorType.Id); }


    public WebElement Icon_Dropdown(){return  findElement("//div[@class='threedoticon']/img",LocatorType.XPath);}

    public WebElement Option_Edit(){return  findElement("//div/a[text()=\"Edit\"]",LocatorType.XPath);}
}
