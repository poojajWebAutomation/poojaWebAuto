package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_AccessorialChargesPage extends PageBase {

    public WebElement TextBox_AccessorialAmount() { return findElement("formHorizontalAmount", LocatorType.Id); }
    public WebElement TextBox_AccessorialDriverCut() { return findElement("formHorizontalDriverShare", LocatorType.Id); }
    public WebElement TextBox_AccessorialDriver1Cut() { return findElement("//input[@placeholder='Driver 1 Cut']", LocatorType.XPath); }
    public WebElement TextBox_AccessorialDriver2Cut() { return findElement("//input[@placeholder='Driver 2 Cut']", LocatorType.XPath); }
    public WebElement TextBox_Comment() { return findElement("Comment", LocatorType.Name); }
    public WebElement DropDown_AccessorialFeeType() { return findElement("formHorizontalFeeType", LocatorType.Id); }
    public WebElement Button_Save() { return findElement("//button[text()='Save']", LocatorType.XPath); }
    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm' and @class='save-btn btn btn-primary']", LocatorType.XPath); }
    public WebElement Header_Section(boolean...ignoreException) { return findElement("//h4[text()='Accessorial Charges']", LocatorType.XPath,ignoreException); }
    public WebElement Error_AccessoricalCharges() { return findElement("accessorial-fee-error", LocatorType.Name); }
    public WebElement Message_Mandatory() { return findElement("//div[@id='accessorial-charge']/label[2]", LocatorType.XPath); }
   public WebElement GridRow(String feeType) { return findElement(String.format("//td[text()='%s']/following-sibling::td",feeType), LocatorType.XPath); }
   public WebElement ExcessWaitTime() { return findElement("//div/h4/a[contains(text(),\"Excess Wait Time\")]", LocatorType.XPath); }
    public WebElement GridRowTotal(String total) { return findElement(String.format("//td/strong[text()='%s']/parent::td/following-sibling::td",total), LocatorType.XPath); }
    public WebElement Error_AccessorialFeeAmount() { return findElement("//label[contains(text(),'Amount*')]/following-sibling::div/div/div", LocatorType.XPath); }
    public WebElement Error_AccessorialFeeDriverCut() { return findElement("//input[@id='formHorizontalDriverShare']/following-sibling::div", LocatorType.XPath); }
    public WebElement Error_AccessorialFeeType() { return findElement("//label[contains(text(),'FeeType*')]/following-sibling::div/div", LocatorType.XPath); }
    public WebElement Error_AccessorialFeeComment() { return findElement("//label[contains(text(),'Comment*')]/following-sibling::div/div", LocatorType.XPath); }
    public WebElement Text_DiffAccessorial(int Index ) { return findElement(String.format("//div[%d]/button[@class='btn btn-link']",Index) ,LocatorType.XPath);}
    public WebElement Text_DriverCut(int Index) { return findElement(String.format("//div[%d]/button[@class='btn btn-link']/following-sibling::div/div[1]",Index) ,LocatorType.XPath);}
    public WebElement Dropdown_SelectFeeType() { return findElement("formHorizontalFeeType", LocatorType.Id); }
    public List<WebElement> List_SelectFeeType() { return findElements("//select[@name='FeeType']/option", LocatorType.XPath); }
}
