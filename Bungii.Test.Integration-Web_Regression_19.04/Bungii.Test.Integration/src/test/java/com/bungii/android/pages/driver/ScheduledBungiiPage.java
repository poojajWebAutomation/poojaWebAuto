package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ScheduledBungiiPage extends  PageBase {

    public WebElement Text_PageTitle() {return findElement("com.bungii.driver:id/toolbar_main_title", LocatorType.Id);}
    //public List<WebElement> List_ScheduledBungiis() { return findElements("com.bungii.driver:id/container_scheduled_trip_row", PageBase.LocatorType.Id); }
    public List<WebElement> List_ScheduledBungiis() { return findElements("com.bungii.driver:id/scheduled_trip_row_container", PageBase.LocatorType.Id); }
    public WebElement Button_Start() { return findElement("//android.widget.Button[@text='Start Bungii']", LocatorType.XPath); }

    public WebElement Cell_FirstTrip() { return findElement("(//android.widget.TextView[@resource-id='com.bungii.customer:id/item_my_bungii_tv_date'])[1]", LocatorType.XPath);}
    public WebElement Cell_SecondTrip() { return findElement("//android.widget.LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.view.ViewGroup", LocatorType.XPath);}

    public WebElement Text_ScheduledBungiiStatus() { return  findElement("com.bungii.driver:id/scheduled_row_textview_status", LocatorType.Id);}
    public WebElement Link_DeliveryDetails(){return  findElement("//div[@class='threedoticon']/img",LocatorType.XPath);}
    public WebElement List_ViewDeliveries(){return  findElement("//td/div[@class='dropdown open']/ul/li/*[contains(text(),'Delivery Details')]",LocatorType.XPath);}
    public WebElement Label_CustomerSignature(){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]",LocatorType.XPath);}
    public WebElement Link_ChangeDeliveryStatus(boolean...ignoreException) { return findElement("//tr/td/span/img", LocatorType.XPath,ignoreException); }
    public WebElement DropDown_DeliveryStatus() { return findElement("//label[text()='New status:']/following::div[1]/select", LocatorType.XPath); }
    public WebElement Text_DeliveryStatus(String status) { return findElement(String.format("//select/option[text() =\"%s\"]",status), LocatorType.XPath); }
    public WebElement DropDown_DeliveryStatusReason() { return findElement("//label[text()='Reason:']/following::div[1]/select", LocatorType.XPath);}
    public WebElement Text_DeliveryStatusReason(String statusReason) { return findElement(String.format("//div/select/option[text() =\"%s\"]",statusReason), LocatorType.XPath); }
    public WebElement Button_ConfirmStatus() { return findElement("//div[@class=\"modal-footer\"]/p/following-sibling::button[2]", LocatorType.XPath);}
    public WebElement Button_CloseStatus() { return findElement("//div[@id=\"edit-status-success-modal\"]/div/div/div[2]/button", LocatorType.XPath);}
    public WebElement Label_CustomerSignatureNA(){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]/following-sibling::td",LocatorType.XPath);}
    public WebElement Image_CustomerSignature(){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]/following-sibling::td/img",LocatorType.XPath);}
    public WebElement Checkbox_driver () { return findElement("//div[@id='tripDriverDetails']//span[@class='checkmark'][1]", LocatorType.XPath); }//richa
    public WebElement  Button_Edit() {return findElement("//a[contains(text(),'Edit')]",LocatorType.XPath);}
    public WebElement TextBox_Search() {return findElement("SearchCriteria", LocatorType.Id); }
    public WebElement Icon_Dropdown(){return  findElement("//div/img[@id='dLabel']",LocatorType.XPath);}
    public WebElement Option_Edit(){return  findElement("//a[contains(text(),'Edit')]",LocatorType.XPath);}

    public WebElement Button_ThreeDot() { return findElement("//android.view.ViewGroup[@resource-id='com.bungii.driver:id/appCompatImageView37']/android.widget.ImageView",LocatorType.XPath);}

    public WebElement Link_ScheduledBungiis() { return findElement("//android.widget.Button[@resource-id='com.bungii.driver:id/activity_more_options_btn_scheduled_bungiis']",LocatorType.XPath);}

    public WebElement Customer_ScheduledDelivery() { return findElement("//android.widget.TextView[@resource-id='com.bungii.driver:id/scheduled_row_textview_scheduleddatetime']",LocatorType.XPath);}
}
