package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_RevivalPage extends PageBase {

    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm']", LocatorType.XPath); }

    public WebElement Button_Cancel() { return findElement("//button[text()='Cancel']", LocatorType.XPath); }

    public WebElement Label_HeaderPopup() { return findElement("//span[contains(text(),'Are you sure you want to revive the trip?')]", LocatorType.XPath); }

    public WebElement Label_PickupOrigin() { return findElement("revive-pickup-customer-type", LocatorType.Id); }

    public WebElement Label_PickupId() { return findElement("//div[contains(.,'Pickup Id :')]/b[1]", LocatorType.XPath); }

    public WebElement Label_PickupCustomer() { return findElement("//div[contains(.,'Customer :')]/b[2]", LocatorType.XPath); }

    public WebElement Label_PickupPartnerPortal() { return findElement("//body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]", LocatorType.XPath); }

    public WebElement Link_ChangeDeliveryStatus(boolean...ignoreException) { return findElement("//tr/td/span/img", LocatorType.XPath,ignoreException); }


    public WebElement DropDown_DeliveryStatus() { return findElement("//div[@class=\"change-status\"]/form/div[2]/div/select", LocatorType.XPath); }

    public WebElement Text_DeliveryStatus(String status) { return findElement(String.format("//select/option[text() =\"%s\"]",status), LocatorType.XPath); }

    public WebElement DropDown_DeliveryStatusReason() { return findElement("//label[text()='Reason:']/following-sibling::div/select", LocatorType.XPath);}
    public WebElement Text_DeliveryStatusReason(String statusReason) { return findElement(String.format("//div/select/option[text() =\"%s\"]",statusReason), LocatorType.XPath); }

    public WebElement Textbox_CommentForStatus() { return findElement("reason-other-comments mt1", LocatorType.Id);}

    public WebElement Button_ConfirmStatus() { return findElement("", LocatorType.XPath);}

    public WebElement Text_SuccessMessage() { return findElement("//div[@id=\"edit-status-success-modal\"]/div/div/div[1]", LocatorType.XPath);}

    public WebElement Button_CloseStatus() { return findElement("//button[text()=\"Close\"]", LocatorType.XPath);}

    public WebElement Text_DeliveryStatus(int number) { return findElement(String.format("//tbody/tr/td[%d]",number), LocatorType.XPath);}

    public WebElement Icon_CancelledTrip() { return findElement("//img[@alt='canceled']", LocatorType.XPath); }

    public WebElement Label_PickUpReference() { return findElement("//div/div[@class=\"panel-heading\"]/h4[2]", LocatorType.XPath); }

    public WebElement Button_ReviveTrip (boolean... IgnoreException) { return findElement("//tbody/tr/td[11]/a/img", LocatorType.XPath,IgnoreException); }

}
