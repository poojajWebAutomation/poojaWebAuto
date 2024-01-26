package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Partner_DeliveryPage extends PageBase {

    //Delivery Details Header Text
    public WebElement Text_Delivery_Details_Header() { return findElement("//h1[contains(text(),'Delivery Details')]", LocatorType.XPath); }

    //Delivery List Text
    public WebElement Text_Delivery_List() { return findElement("//h6[contains(text(),'Delivery List')]",LocatorType.XPath);}

    //Pickup DateTime Text
    public WebElement Text_Pickup_DateTime() { return findElement("//label[contains(text(),'Pickup Date & Time:')]/following::p[1]",LocatorType.XPath);}

    //Back To Estimate Link
    public WebElement Link_Back_To_Estimate() { return findElement("//span[@class='back']",LocatorType.XPath);}

    //Items to Deliver
    public WebElement TextBox_Item_To_Deliver() { return findElement("pickUpNote",LocatorType.Name);}

    //SKU Text fiels
    public WebElement TextBox_SKU() { return findElement("skuNumber",LocatorType.Name);}

    //SKU Add
    public WebElement Button_SKU_Add() { return findElement("SKU",LocatorType.Id);}

    //Blank Item to deliver message
    public WebElement Message_Black_Item_To_Deliver() { return findElement("//div[contains(text(),'Items to deliver is required.')]",LocatorType.XPath);}

    //Special Instruction
    public WebElement TextBox_Special_Intruction() { return findElement("specialInstructions",LocatorType.Name);}

    //Customer Name
    public WebElement TextBox_Customer_Name() { return findElement("customerName",LocatorType.Name);}

    //Blank Customer Name
    public WebElement Message_Black_Customer_Name() { return findElement("//div[contains(text(),'Customer full name is required.')]",LocatorType.XPath);}

    //Customer Mobile
    public WebElement TextBox_Customer_Mobile() { return findElement("customerMobile",LocatorType.Name);}

    //Blank Customer Mobile
    public WebElement Message_Blank_Customer_Mobile() { return findElement("//div[contains(text(),'Customer mobile is required.')]",LocatorType.XPath);}

    //Pickup Contact Name
    //public WebElement TextBox_Pickup_Contact_Name() { return findElement("f2bd9004-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);
    public WebElement TextBox_Pickup_Contact_Name() { return findElement("//label[text()='Pickup contact name']/following::input[1]",LocatorType.XPath);}

    //Blank Pickup Contact Name
    public WebElement Message_Blank_Pickup_Contact_Name() { return findElement("//div[contains(text(),'Pickup contact name is required.')]",LocatorType.XPath);}

    //Pickup Contact Phone
    //public WebElement TextBox_Pickup_Contact_Phone() { return findElement("f2bd908c-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);}
    public WebElement TextBox_Pickup_Contact_Phone() { return findElement("//label[text()='Pickup contact phone']/following::input[1]",LocatorType.XPath);}

    public WebElement TextBox_DropOff_Contact_Name() { return findElement("//label[text()='Drop off contact name']/following::input[1]",LocatorType.XPath);}

    public WebElement TextBox_DropOff_Contact_Phone() { return findElement("//label[text()='Drop off contact phone']/following::input[1]",LocatorType.XPath);}

    //Scheduled By
    public WebElement TextBox_Scheduled_By() { return findElement("//label[text()='Scheduled By']/following::input[1]",LocatorType.XPath);}

    //Bodc Code
    public WebElement Dropdown_BodcCode() { return findElement("//label[text()='BODC Code']/following::div[1]/div[1]",LocatorType.XPath);}

    //Bodc Options
    public List<WebElement> Dropdown_BodcCodeOptions() {return findElements("//div/div/ul/li", LocatorType.XPath); }

    //Select Bodc Code value
    public WebElement Dropdown_BodcCodeValue() { return findElement("//li[contains(text(),'SVC02/09/00')]",LocatorType.XPath);}

    //Pickup Date Time
    public WebElement Label_Pickup_Date_Time() { return findElement("//label[contains(text(),'Pickup Date & Time:')]/following-sibling::p",LocatorType.XPath);}

    //Blank Pickup Contact Phone
    public WebElement Message_Blank_Pickup_Contact_Phone() { return findElement("//div[contains(text(),'Pickup contact phone is required.')]",LocatorType.XPath);}

    //Drop Off Contact Name
    public WebElement TextBox_Drop_Off_Contact_Name() { return findElement("f2bd90b3-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);}

    //Drop Off Contact Phone
    public WebElement TextBox_Drop_Off_Contact_Phone() { return findElement("f2bd90d3-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);}

    //Receipt Number
    public WebElement TextBox_Receipt_Number() { return findElement("f2bd91b2-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);}

    //Order Number
    public WebElement TextBox_Order_Number() { return findElement("//input[@data-field='Order Number']",LocatorType.XPath);}

    //EmployeeID
    public WebElement Input_EmployeeNo() { return findElement("f2bd91d1-6757-11ea-a4a3-00155d0a8706", LocatorType.Id);}

    //SoldBuy
    public WebElement Dropdown_SoldBuy() { return findElement("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-formControl']",LocatorType.XPath);}

    public WebElement List_StoreAssociate(String storeAssociate) { return findElement(String.format("//li[text()='%s']",storeAssociate),LocatorType.XPath);}

    //Customer Card Payment
    public WebElement Radio_Button_Customer_Card() { return findElement("//label[contains(text(),'Customer Card')]",LocatorType.XPath);}

    //Card Number
    public WebElement TextBox_Card_Number() { return findElement("//*[@id='credit-card-number']",LocatorType.XPath);}

     //Invalid Card number message
    public WebElement Message_Invalid_CardNumber() { return  findElement("//div[contains(text(),'This card number is not valid.')]",LocatorType.XPath);}

    //Invalid Card number message
    public WebElement Message_Toast() { return  findElement("//div[@role='alert']",LocatorType.XPath);}

    //Expiry Date
    public WebElement TextBox_Expiry_Date() { return findElement("//*[@id='expiration']",LocatorType.XPath);}

    //Invalid Expired date
    public WebElement Message_Invalid_ExpiredDate() { return findElement("//div[contains(text(),'This expiration date is not valid.')]",LocatorType.XPath);}

    //CVV
    public WebElement TextBox_CVV() { return  findElement("//*[@id='cvv']",LocatorType.XPath);}

    //Invalid CVV
    public WebElement Message_Invalid_CVV() { return findElement("//div[contains(text(),'This security code is not valid.')]",LocatorType.XPath);}

    //Postal Code
    public WebElement TextBox_Postal_Code() { return findElement("//*[@id='postal-code']",LocatorType.XPath);}

    //Invalid Postal code
    public WebElement Message_Invalid_Postal_Code() { return findElement("//div[contains(text(),'This postal code is not valid.')]",LocatorType.XPath);}

    //Partner Pay Radio button
    public WebElement Radio_Button_Partner_Pay() { return findElement("//label[contains(text(),'Partner Pay')]",LocatorType.XPath);}

    //Partner Invoice Radio button
    //public WebElement Radio_Button_Partner_Invoice() { return findElement("//label[contains(text(),'Partner Invoice')]",LocatorType.XPath);}
    public WebElement RadioButton_PartnerInvoice() { return findElement("MI",LocatorType.Id);}


    //Schedule Bungii button
    //public WebElement Button_Schedule_Bungii() { return findElement("submit-details",LocatorType.Id);}
    public WebElement Button_Schedule_Bungii() { return findElement("//button[@id='submit-details']",LocatorType.XPath);}

    public WebElement Button_New_Bungii() { return findElement("//div/button[text()='New Bungii']",LocatorType.XPath);}

    //Driver and truck text in summary
    public WebElement Text_Driver_Truck() { return findElement("//label[contains(text(),'s needed:')]/following-sibling::p",LocatorType.XPath);}

    //Pick up address text in summary
    public WebElement Text_Pick_Address() { return findElement("//label[contains(text(),'Pickup Address:')]/following-sibling::p",LocatorType.XPath);}

    //Delivery Address text in summary
    public WebElement Text_Delivery_Address() { return findElement("//label[contains(text(),'Delivery Address:')]/following-sibling::p",LocatorType.XPath);}

    //Estimated cost in summary
    public WebElement Text_Estiated_Cost() { return findElement("//h2[@class='estimate-label']/span",LocatorType.XPath);}

    //Delivery Cost
    public WebElement Label_Delivery_Cost() { return findElement("//h2[contains(text(),'Delivery Cost')]",LocatorType.XPath);}

    //Product Description Text fields
    public WebElement TextBox_Product_Description() { return findElement("Name",LocatorType.Name);}

    //Dimensions Text fields
    public WebElement TextBox_Dimensions() { return findElement("Dimensions",LocatorType.Name);}

    //Weight Text fields
    public WebElement TextBox_Weight() { return findElement("Weight",LocatorType.Name);}

    //Delivery Purpose Text fields
    public WebElement TextBox_Delivery_Purpose() { return findElement("//input[@data-field='Delivery purpose']",LocatorType.XPath);}

    //Lot Number Text fields
    public WebElement TextBox_LotNumber() { return findElement("//input[@data-field='Lot Number']",LocatorType.XPath);}

    //Bidder Number Text fields
    public WebElement TextBox_BidderNumber() { return findElement("//input[@data-field='Bidder Number']",LocatorType.XPath);}

    //Helper check box
    public WebElement Checkbox_Helper() { return findElement("//div[@id='helperCheckbox']",LocatorType.XPath);}

    //RB/SB NUMBER Text fields
    public WebElement TextBox_Rb_Sb_Number() { return findElement("//label[contains(text(),'RB/SB Number')]//following-sibling::input",LocatorType.XPath);}

    //SoldBy Text fields
    public WebElement TextBox_SoldBy() { return findElement("//label[contains(text(),'Scheduled By')]//following-sibling::input",LocatorType.XPath);}

    //Report
    // Dropdown hamberger Icon
    public WebElement Image_dropdown_Setting() { return findElement("//div/i[@title='Menu']/img",LocatorType.XPath);}

    // Report link
    public WebElement Link_Report() { return findElement("//div/span[text() = 'Reports']",LocatorType.XPath);}

    //on click report link
    public WebElement Label_Report_HeaderPopup() { return findElement("//div[text() ='Delivery History Report']",LocatorType.XPath);}

    //generate report butto
    public WebElement Button_GenerateReport(boolean...ignoreException) { return findElement("//div/button[text() ='GENERATE REPORT']",LocatorType.XPath,ignoreException);}

    //select month of the year
    public WebElement Text_MonthOfTheYear(int monthIndex) { return findElement(String.format("//div/ul[@role ='listbox']/li[%s]",monthIndex),LocatorType.XPath);}

   // calender number 2 in reports
    public WebElement Dropdown_Calender2_Month() { return findElement("//div[@class =\"calender-container\"]/div/div[2]/div/div/div[1]/div[2]/div[3]/div/div[1]/div[2]/div/div",LocatorType.XPath);}

    // 2 months later date disabled
    public WebElement Text_DisabledDate() { return findElement("//div[@class =\"calender-container\"]/div/div[2]/div/div/div[1]/div[2]/div[3]/div/div[3]/div[1]//div[1]/button",LocatorType.XPath);}

    // filter options on report by day
    public WebElement Link_ReportFilter(String filterBy) { return findElement(String.format("//div/ul/div/div/span[text() ='%s']",filterBy),LocatorType.XPath);}

    // days of calender for report
    public List<WebElement> List_AllDatesOfTheMonth() { return findElements("//button[contains(@class,'materialui-daterange-picker-makeStyles-filled')]/span/p",LocatorType.XPath);}

    // 0 delivery present error
    public WebElement Text_NoDeliveryError() { return findElement("err",LocatorType.ClassName);}

    //Partner portal delivery details page delivery status
    public WebElement Text_PartnerDeliveryStatus(String status) { return findElement(String.format("//div[@class ='delivery-status']/div/div/label[text() ='%s']",status),LocatorType.XPath);}

    //Partner portal delivery details page step completion time
    public WebElement Text_DeliveryCompletedStepTime(int number) { return findElement(String.format("//div[%d]/div/p[@class=\"timeStamp\"]",number),LocatorType.XPath);}

    //Phone icon on SMS
    public WebElement Icon_Phone(boolean...IgnoreException) { return findElement("//div/span/img",LocatorType.XPath,IgnoreException);}

    //Cancel button on SMS link
    public WebElement Button_CancelCall() { return findElement("//div/div/button[text()=\"Cancel\"]",LocatorType.XPath);}

    //Confirm button on SMS link
    public WebElement Button_ConfirmCall() { return findElement("//div/div/button[text()=\"Confirm\"]",LocatorType.XPath);}

    //Text displayed when popup is opened for sms
    public WebElement Alert_MessageForCall() { return findElement("//div[@class=\"modal-content\"]/div/p",LocatorType.XPath);}

    //add sms recipient
    public WebElement TextBox_Product2Description() { return findElement("//input[@id='Name_1']",LocatorType.XPath);}
    public WebElement TextBox_Product2Dimensions(){ return findElement("//input[@id='Dimensions_1']",LocatorType.XPath);}
    public WebElement TextBox_Product2Weight() { return findElement("//input[@id='Weight_1']",LocatorType.XPath);}
    public WebElement Button_AddSMSRecipient() { return findElement("//span[contains(text(),'ADD SMS RECIPIENTS')]",LocatorType.XPath);}
    public WebElement TextBox_CustomerSMSRecipient1() { return findElement("customerMobile0",LocatorType.Name);}
    public WebElement TextBox_CustomerSMSRecipient2() { return findElement("customerMobile1",LocatorType.Name);}
    public WebElement TextBox_CustomerSMSRecipient3() { return findElement("customerMobile2",LocatorType.Name);}
    public WebElement PhoneNo_Recipient1() {return findElements("//label[contains(text(),'Texting status updates to:')]//following-sibling::p[@class='phone-number']",LocatorType.XPath).get(1);}
    public WebElement PhoneNo_Recipient2(){return findElements("//label[contains(text(),'Texting status updates to:')]//following-sibling::p[@class='phone-number']",LocatorType.XPath).get(2);}
    public WebElement PhoneNo_Recipient3(){return findElements("//label[contains(text(),'Texting status updates to:')]//following-sibling::p[@class='phone-number']",LocatorType.XPath).get(3);}

    //Partner logo in header
    public WebElement Logo_PartnerPortal() {return findElement("//img[@alt=\"Partner Logo\"]/ancestor::a", LocatorType.XPath);}

    //Confirmation popup for logo redirect
    public WebElement Logo_ConfirmPopup() {return findElement("//div[@class=\"modal-body\"]/h2", LocatorType.XPath);}

    //Confirm button on Partner logo click
    public WebElement Button_ConfirmPartnerLogoClick() {return findElement("//button[contains(text(),\"Continue\")]", LocatorType.XPath);}

    public List<WebElement> Text_PartnerName() {return findElements("//tbody[@id='TripListsTBody']/tr/td[text()='Best Buy #11 - Baltimore, MD']", LocatorType.XPath);}

    public WebElement Status_CustomerSignatureEnabledRequired() {return findElement("//span[contains(text(),'Enabled (Required)')]", LocatorType.XPath);}

    public WebElement Status_CustomerSignatureEnabledNotRequired() {return findElement("//span[contains(text(),'Enabled (Not Required)')]", LocatorType.XPath);}

    public WebElement Status_CustomerSignatureDisabled() {return findElement("//span[contains(text(),'Disabled')]", LocatorType.XPath);}

}
