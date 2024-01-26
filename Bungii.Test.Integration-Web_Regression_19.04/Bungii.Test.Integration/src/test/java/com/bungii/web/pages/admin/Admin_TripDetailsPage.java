package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_TripDetailsPage extends PageBase {

    public WebElement Link_ManuallyEndBungii () { return findElement("btnEndPickup", LocatorType.Id); }

    public WebElement Link_PrivacyPolicy () { return findElement("//span[contains(text(),'Privacy Policy')]", LocatorType.XPath); }

    public WebElement Button_CalculateCost () { return findElement("btnCost", LocatorType.Id); }

    public WebElement Button_Cancel () { return findElement("//a[contains(text(),'cancel')]", LocatorType.XPath); }

    public WebElement Button_Confirm () { return findElement("btnConfirm", LocatorType.Id); }

    public WebElement Textbox_PickupEndDate () { return findElement("pickupEndDate", LocatorType.Id); }

    public WebElement Textbox_PickupEndTime () { return findElement("pickupEndTime", LocatorType.Id); }

    public WebElement Dropdown_ddlpickupEndTime () { return findElement("ddlpickupEndTime", LocatorType.Id); }

    public WebElement Label_TripDetails (String label) { return findElement("//div/h4[contains(text(),'"+ label+"')]/following-sibling::span", LocatorType.XPath); }

    public WebElement Dropdown_Drivers () { return findElement("Drivers", LocatorType.Id); }

    public WebElement Label_ScheduledTime(String xpath_scheduled_time) { return findElement( xpath_scheduled_time, LocatorType.XPath);}

    public WebElement Schedule_Date_Row() {return findElement("//td[3]/a",LocatorType.XPath);}

    public WebElement Text_DropOff_Location() { return findElement("//div[text()='DROP-OFF']/following::h6[text()='Address']/following-sibling::div[1]/div/p",LocatorType.XPath);}

    public WebElement Text_Pickup_Location() { return findElement("//div[text()='PICKUP']/following::h6[1][text()='Address']/following-sibling::div[1]/div/p",LocatorType.XPath);}

    public WebElement Text_Service_Level() { return findElement("//td[contains(text(),'Service Level')]/following-sibling::td/strong",LocatorType.XPath);}

    public WebElement Text_Estimated_Charge() { return findElement("//body/div/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[3]/div/div/table/tbody/tr[1]/td[2]",LocatorType.XPath);}

    public WebElement Text_Driver_Est_Earnings_Customer_Delivery() { return findElement("//td[contains(text(),'Driver Earnings')]/following::td[1]",LocatorType.XPath);}

    public WebElement Text_Driver_Est_Eranings() { return findElement("//tr[2]/td[2]",LocatorType.XPath);}

    public WebElement Text_Driver_Est_Eranings_Fnd() { return findElement("//tr[19]/td/strong",LocatorType.XPath);}

    public WebElement Icon_Price_Override(){ return findElement("//img[@alt='price-overridden']",LocatorType.XPath);}

    public WebElement Icon_DriverEarnings(){ return findElement("//td[text()='Driver Earnings']/img",LocatorType.XPath);}

    public WebElement Dropdown_Driver_Result (String driverName) { return findElement(String.format("//div[@id='divDriversResult']/div[contains(.,'%s')]",driverName),LocatorType.XPath);}

    public WebElement Text_Partner_Delivery_Cost() { return findElement("//h2[contains(text(),'Delivery Cost')]/span/strong",LocatorType.XPath);}

    public WebElement Button_Ok() { return findElement("//button[text()='ok']",LocatorType.XPath);}

    public WebElement Button_Price_Override(boolean...ignoreException) { return findElement("//a[contains(text(),'Price Override')]",LocatorType.XPath,ignoreException);}

    public WebElement Textbox_Override_Customer_Price() { return findElement("//input[@name='CustomerPrice']",LocatorType.XPath);}

    public WebElement Textbox_Override_Driver_Cut() { return findElement("//input[@name='DriverCut1']",LocatorType.XPath);}

    public WebElement Textbox_Override_Driver_Cut_Duo() { return findElement("//input[@name='DriverCut2']",LocatorType.XPath);}

    public WebElement Dropdown_Reason_Override_Customer_Price() { return findElement("//select[@name='CustomerReason']",LocatorType.XPath);}

    public WebElement Dropdown_Reason_Override_Driver_Cut() { return findElement("//select[@name='DriverReason']",LocatorType.XPath);}

    public WebElement Label_Price_Override() { return findElement("exampleModalLongTitle",LocatorType.Id);}

    public WebElement Button_Save() { return findElement("//button[text()='Save']",LocatorType.XPath);}

    public WebElement Button_Success_Ok() { return findElement("//button[text()='OK']",LocatorType.XPath);}

    public WebElement Button_Override_Cancel() { return findElement("//button[text()='Cancel']",LocatorType.XPath);}

    public WebElement Text_Driver_Cut_Error() { return findElement("//form[@id='form']/span",LocatorType.XPath);}

    public WebElement Text_DriverOneEarnings() { return findElement("//tr[19]/td/following-sibling::td/strong",LocatorType.XPath);}
    public WebElement Text_DriverTwoEarnings() { return findElement("//tr[20]/td/following-sibling::td/strong",LocatorType.XPath);}
    public WebElement Text_PriceOverrideError() { return findElement("errorMessageFields",LocatorType.Id);}

    public WebElement Label_Partner() { return findElement("//table[@class='table table-striped']/tbody/tr[1]/td[1]",LocatorType.XPath);}
    public WebElement PhoneNo_Customer() {return findElement("//table[@class='table table-striped']/tbody/tr[2]/td[2])",LocatorType.XPath);}

    public WebElement Text_TitleTransactionHistory() {return findElement("//h4[contains(text(),'Transaction history')]", LocatorType.XPath);}
    public WebElement Text_TransactionHistoryDeliveryTotal() {return findElement("//div[@id='transactionHistory']//div[@class='col-sm-4']", LocatorType.XPath);}
    public WebElement Text_TransactionHistoryCustomerRefundAmount() {return findElement("//div[@id='transactionHistory']//tr[1]/descendant::td[2]", LocatorType.XPath);}
    public WebElement Text_TrackingId() {return findElement("//div/h4[contains(text(),'Tracking ID' )]/following-sibling::span",LocatorType.XPath);}
    public WebElement Text_TransactionHistoryDriverEarnings() {return findElement("//div[@id='transactionHistory']//tr[2]/descendant::td[2]", LocatorType.XPath);}
    public WebElement Text_TransactionHistoryBungiiEarnings() {return findElement("//div[@id='transactionHistory']//tr[3]/descendant::td[2]", LocatorType.XPath);}

    public WebElement Text_DeliveryDetailsHeader() {return findElement("//h4[contains(.,'Delivery Details')]", LocatorType.XPath);}
    public WebElement Icon_DriverSameDayPayment(String driverName,boolean...ignoreException) {return findElement("//td[contains(.,'"+driverName+"')]/img", LocatorType.XPath,ignoreException);}
    public WebElement Text_InitialRequestTime() {return findElement("//td[text()='Initial Request']/following::td[1]/strong", LocatorType.XPath);}
    public WebElement Text_ScheduleTime() {return findElement("//td[text()='Scheduled Time']/following::td[1]/strong", LocatorType.XPath);}
    public WebElement Text_SoloDriverEarnings() {return findElement("//td[contains(text(),'Driver Fixed Earnings')]/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_TripStatus() {return findElement("//td[contains(text(),'Status')]/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Input_DriverBoostedPeriod() {return findElement("//td[text()='Driver Boosted earning period']/following::td[1]/div[1]/div/div/div/input", LocatorType.XPath);}

    public WebElement Button_DeliveryOverview() { return findElement("//button[contains(text(),'Delivery Overview')]",LocatorType.XPath);}
    public WebElement Button_PaymentDetails() { return findElement("//button[contains(text(),'Payment Details')]",LocatorType.XPath);}

}
