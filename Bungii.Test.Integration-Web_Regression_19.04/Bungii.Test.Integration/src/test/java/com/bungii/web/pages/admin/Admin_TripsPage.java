package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_TripsPage extends PageBase {

    public WebElement Menu_Trips () { return findElement("//ul[@id='side-menu']/li/p/a/span[contains(text(),'Deliveries')]", LocatorType.XPath); }

    public WebElement Menu_CompletedTrips () { return findElement("//a[contains(text(),'Completed Deliveries')]", LocatorType.XPath); }

    public WebElement Menu_AllTrips () { return findElement("//a[contains(text(),'All Deliveries')]", LocatorType.XPath); }

    public WebElement Menu_AllDeliveries () { return findElement("//a[contains(text(),'All Deliveries')]",LocatorType.XPath);}

    //public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement TextBox_Search() {return findElement("SearchCriteria", LocatorType.Id); }

    public WebElement DropDown_SearchForPeriod () {return findElement("//div[text()='The following deliveries from:']/select", LocatorType.XPath); }

    //public List<WebElement> Client_names () { return findElements("//td[9]", LocatorType.XPath); }
    public List<WebElement> Client_names () { return findElements("//td[9]", LocatorType.XPath); }

    public WebElement Button_Filter () { return findElement("//button[@class='btn-filter btn btn-primary']/span", LocatorType.XPath); }

    public WebElement Button_Apply () { return  findElement("//button[contains(text(),'APPLY')]", LocatorType.XPath); }

    public WebElement CheckBox_FilterPaymentUnsuccessful () { return findElement("Payment Unsuccessful", LocatorType.Id); }

    public WebElement CheckBox_FilterPaymentSuccessful () { return findElement("Payment Successful", LocatorType.Id); }

    public WebElement CheckBox_FilterCustomerCancelled () { return findElement("Customer Canceled", LocatorType.Id); }

    public WebElement CheckBox_FilterDriverCancelled () { return findElement("Driver Canceled", LocatorType.Id); }

    public WebElement CheckBox_FilterAdminCancelled () { return findElement("Admin Canceled", LocatorType.Id); }

    public WebElement CheckBox_FilterPartnerCancelled () { return findElement("Partner Canceled", LocatorType.Id); }

    public WebElement CheckBox_FilterPickupWithError () { return findElement("Pickup with Error", LocatorType.Id); }

    public WebElement CheckBox_FilterPriceEstimated () { return findElement("Price Estimated", LocatorType.Id); }

    public WebElement CheckBox_FilterDriversNotFound () { return findElement("No Driver(s) Found", LocatorType.Id); }

    public WebElement CheckBox_FilterDriverRemoved () { return findElement("Driver Removed", LocatorType.Id); }

    public WebElement CheckBox_FilterDriverNotArrived () { return findElement("Driver Not Arrived", LocatorType.Id); }

    public WebElement CheckBox_FilterPromoterPaymentPending () { return findElement("Promoter Payment Pending", LocatorType.Id); }

    public WebElement CheckBox_FilterSolo () { return findElement("Solo", LocatorType.Id); }

    public WebElement CheckBox_FilterDuo () { return findElement("Duo", LocatorType.Id); }

    public WebElement CheckBox_FilterOnDemand () { return findElement("On-Demand", LocatorType.Id); }

    public WebElement CheckBox_FilterScheduled () { return findElement("Scheduled", LocatorType.Id); }

    public WebElement Dropdown_SearchForPeriod () { return findElement("//div[text()='The following deliveries from:']/select", LocatorType.XPath); }

    public WebElement Text_AllTripIndicator () { return findElement("//tbody[@id=\"TripListsTBody\"]/tr/td[1]/label", LocatorType.XPath); }

    public WebElement RadioButton_SoloTrip () { return findElement("SOLO", LocatorType.Id); }

    public WebElement RadioButton_DuoTrip () { return findElement("DUO", LocatorType.Id); }

    public WebElement CheckBox_FilterPending () { return findElement("Pending", LocatorType.Id); }

    public WebElement Text_NoDeliveriesFound () { return findElement("//td[contains(text(),'No deliveries found.')]", LocatorType.XPath); }

    public WebElement Label_ReviveCustomerDetail () { return findElement("//div[contains(.,'Customer :')]/b[2]", LocatorType.XPath); }

    public WebElement Label_RevivePartnerDetail () { return findElement("//span[contains(.,'Partner')]/b", LocatorType.XPath); }

    public WebElement Label_RevivePickupOriginDetail (boolean...ignoreException) { return findElement("//div[contains(.,'Pickup Origin')]", LocatorType.XPath,ignoreException); }

    public WebElement CheckBox_AssigningDrivers () { return findElement("Assigning Driver(s)", LocatorType.Id); }

    public WebElement Text_AllFilterOptions (int number){ return findElement(String.format("(//div[@class='checkbox-label form-check'])[%d]",number), LocatorType.XPath); }

    public WebElement Header_Partner () { return findElement("//th[text()='Customer']/following-sibling::th[1]", LocatorType.XPath); }

    public WebElement Link_ChangePaymentStatus () { return findElement("//a[contains(text(),'Change Payment Status')]", LocatorType.XPath); }

    public WebElement Text_ChangePaymentStatusMessage () { return findElement("//div/span[contains(.,'Are you sure, you want to change the payment status?')]", LocatorType.XPath); }

    public WebElement Text_CurrentStatus () { return findElement("//span[contains(.,'Current Status :')]/b", LocatorType.XPath); }

    public WebElement Text_NewStatus () { return findElement("//span[contains(.,'New Status :')]/b", LocatorType.XPath); }

    public WebElement Button_ConfirmPaymentStatusChange () { return findElement("//button[text()='Confirm']", LocatorType.XPath); }

    public WebElement Button_CancelPaymentStatusChange () { return findElement("//button[text()='Cancel']", LocatorType.XPath); }

    public WebElement Text_ScheduledDate(){return findElement("//td[4]/a",LocatorType.XPath);}

    public WebElement Link_DeliveryDetails() {return findElement("//a[contains(text(),'Delivery Details')]",LocatorType.XPath);}

    public WebElement Text_ScheduledTime(String customerName) {return findElement("//span[contains(text(),'"+customerName+"')]//parent::td//parent::tr//td[5]//a",LocatorType.XPath);}
    public  WebElement Dropdown_DateFilter() { return findElement("//select[@class='user-trip-dropdown form-select']", LocatorType.XPath);}

    public WebElement Dropdown_FilterAll() {return findElement("//option[contains(text(),'All')]",LocatorType.XPath);}

    public WebElement Dropdown_FilterToday() {return findElement("//option[contains(text(),'Today')]",LocatorType.XPath);}

    public WebElement Dropdown_FilterTomorrow() {return findElement("//option[contains(text(),'Tomorrow')]",LocatorType.XPath);}

    public WebElement Header_LiveDeliveries() {return findElement("//body/div/div/div/div/div/div/div/h4",LocatorType.XPath);}

    public WebElement Menu_CompletedDeliveries() {return findElement("//a[contains(text(),'Completed Deliveries')]",LocatorType.XPath);}
    public WebElement Menu_RejectedAPIDeliveries () { return findElement("//a[contains(text(),'Rejected API Deliveries')]", LocatorType.XPath); }
    public WebElement Dropdown_Partner() {return findElement("partnerID",LocatorType.Name);}

    public WebElement Dropdown_SelectPartner(String Partner_name) {return findElement("//select[@name='partnerID']/option[text()='"+Partner_name+"']",LocatorType.XPath);}

    public WebElement Button_ExportRecords() {return findElement("//button[text()='Export All Records']",LocatorType.XPath);}
    public WebElement Text_CustomerNameAndNumber() {return findElement("//td/strong[contains(text(),'Customer Name & Phone Number:')]/following::td[1]",LocatorType.XPath);}



}
