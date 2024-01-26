package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_PromoterPage extends PageBase {

    public WebElement Title_PromoterPage (boolean...ignoreException) { return findElement("//h4[text()='Partners']", LocatorType.XPath,ignoreException); }

    public WebElement Menu_Promotion (boolean... ignoreException) { return findElement("//span[text()='Promo Codes']", LocatorType.XPath,ignoreException); }

    public WebElement Menu_Promotion_Free_Delivery (boolean... ignoreException) { return findElement("//a[text()='Free Delivery Codes']", LocatorType.XPath,ignoreException); }

    public WebElement Menu_PromoterPayment (boolean... ignoreException) { return findElement("//span[text()='Free Delivery Credit Card']", LocatorType.XPath,ignoreException); }

    public WebElement Button_NewPromoter () { return findElement("//button[text()='New Partner']", LocatorType.XPath); }

    public WebElement Button_SavePromoter () { return findElement("//button[text()='Save']", LocatorType.XPath); }

    public WebElement Button_Cancel () { return findElement("//button[text()='Cancel']", LocatorType.XPath); }

    public WebElement TextBox_PromoterName () { return findElement("//label[text()='Partner Name:']//following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement TextBox_CodeInitials () { return findElement("//label[text()='Code Initials:']//following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement TextBox_Discription () { return findElement("//label[text()='Description:']//following-sibling::div/div/textarea", LocatorType.XPath); }

    public WebElement DropDown_Status () { return findElement("//label[text()='Status:']//following-sibling::select", LocatorType.XPath); }

    public WebElement Button_NewPromotion () { return findElement("//button[text()='New Event']", LocatorType.XPath); }

    public WebElement Button_SavePromotion () { return findElement("//button[text()='Save']", LocatorType.XPath); }

    public WebElement Button_SavePayment () { return findElement("//button[text()='Save']", LocatorType.XPath); }

    public WebElement TextBox_PromotionName () { return findElement("//label[text()='Event Name:']/following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement TextBox_PromoStartDate () { return findElement("//label[text()='Promotion Start Date:']/following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement TextBox_PromoEndDate () { return findElement("//label[text()='Expiration Date:']/following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement DropDown_SelectPromoter() { return findElement("//select[@class='form-select']", LocatorType.XPath); }

    public WebElement TextBox_DiscountValue () { return findElement("PromotionDetailItem_Value", LocatorType.Id); }

    public WebElement RadioButton_PercentSelected () { return findElement("//input[@id='PromotionDetailItem_ValueType' and @value='1']/parent::label[contains(.,'Percent')]", LocatorType.XPath); }

    public WebElement Button_SavePromotionYes () { return findElement("//button[text()='Yes']", LocatorType.XPath); }

    public WebElement Button_SavePromotionNo () { return findElement("//button[text()='No']", LocatorType.XPath); }

    public WebElement TextBox_Search () { return findElement("SearchCriteria", LocatorType.Id); }

    public WebElement Label_GeneratePromocodeMessageContainer() { return findElement("//p[text()='Do you wish to generate promo codes?']", LocatorType.XPath); }

    public WebElement Label_SuccessMessage( ) { return findElement("//span[text()='Payment details added successfully for partner.']", LocatorType.XPath); }

    public WebElement Label_CodeInitialsContainer( ) { return findElement("//div[contains(text(),'Please enter a valid Code containing alphanumeric ')]", LocatorType.XPath); }

    public WebElement TextBox_CreditCardNumber( ) { return findElement("credit-card-number", LocatorType.Id); }

    public WebElement TextBox_ExpirationDate( ) { return findElement("expiration", LocatorType.Id); }

    public WebElement TextBox_CVV( ) { return findElement("cvv", LocatorType.Id); }

    public WebElement TextBox_PostalCode( ) { return findElement("postal-code", LocatorType.Id); }

    public WebElement Header_Name() { return findElement("//th[text()='Name']", LocatorType.XPath); }

    public WebElement Header_Created () { return findElement("//th[contains(text(),'Created')]", LocatorType.XPath); }

    public WebElement Header_CodeInitials () { return findElement("//th[contains(text(),'Code Initials')]", LocatorType.XPath); }

}