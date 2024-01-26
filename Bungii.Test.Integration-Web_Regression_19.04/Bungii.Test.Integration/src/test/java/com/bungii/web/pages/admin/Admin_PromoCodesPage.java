package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_PromoCodesPage extends PageBase {

    public WebElement Title_PromocodesPage (boolean...ignoreException) { return findElement("//h4[text()='Partners']", LocatorType.XPath,ignoreException); }

    public WebElement Title_StandardcodesPage (boolean...ignoreException) { return findElement("//h4[text()='Standard Codes']", LocatorType.XPath,ignoreException); }

    public WebElement Menu_Marketing (boolean... ignoreException) { return findElement("//span[text()='Marketing']", LocatorType.XPath,ignoreException); }

    public WebElement Button_NewCode () { return findElement("//button[text()='New Code']", LocatorType.XPath); }

    public WebElement Button_Save (boolean... ignoreException) { return findElement("//button[text()='Save']", LocatorType.XPath,ignoreException); }

    public WebElement Button_Cancel () { return findElement("//button[text()='Cancel']", LocatorType.XPath); }

    public WebElement DropDown_PromoType () { return findElement("//label[text()='Select Standard Code Type:']/following-sibling::button", LocatorType.XPath); }

    public WebElement Select_PromoType (String type) { return findElement("//span[text()='"+type+"']", LocatorType.XPath); }

    public WebElement TextBox_PromoCodeName () { return findElement("//label[text()='Standard Code Name:']/following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement TextBox_PromoCode () { return findElement("//label[text()='No of Codes:']/following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement TextBox_DiscountValue () { return findElement("//label[text()='Discount Value:']//following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement RadioButton_Dollars () { return findElement("//label[text()='Dollars']", LocatorType.XPath); }

    public WebElement RadioButton_Percent () { return findElement("//label[text()='Percent']", LocatorType.XPath); }

    public WebElement RadioButton_DollarsDisabled () { return findElement("//label[text()='Dollars']", LocatorType.XPath); }

    public WebElement RadioButton_PercentDisabled () { return findElement("rdPercent", LocatorType.Id); }

    public WebElement CheckBox_FirstTimeUse () { return findElement("chkIsFirstTimeUse", LocatorType.Id); }

    public WebElement Label_ErrorContainer () { return findElement("//p[text()='Oops! It looks like you missed something. Please fill out all fields before proceeding.']", LocatorType.XPath); }

    public WebElement Label_CodeErrorContainer () { return findElement("PromoCodeItem_Code-error", LocatorType.Id); }
    public WebElement Label_PromoterErrorContainer () { return findElement("//label[text()='Please select Partner.']", LocatorType.XPath); }

    public WebElement Label_CountErrorContainer (String message) { return findElement("//div[text()='"+message+"']", LocatorType.XPath); }
    public WebElement TextBox_CodeCount () { return findElement("//label[text()='No of Codes:']//following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement TextBox_Code () { return findElement("//label[text()='Code:']/following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement DropDown_Promoter () { return findElement("//label[text()='Select Partner:']//following-sibling::button", LocatorType.XPath); }

    public WebElement DropDown_Promotion () { return findElement("//label[text()=\"Select Event:\"]/following-sibling::button/div[1]/div[1]", LocatorType.XPath); }

    public WebElement TextBox_PromotionStartDate () { return findElement("//label[text()='Promo Start Date:']//following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement TextBox_PromotionExpirationDate() { return findElement("//label[text()='Expiration Date:']//following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement TextBox_Search() { return findElement("//input[@name='SearchCriteria']", LocatorType.XPath); }

    public WebElement Button_Search() { return findElement("//button[@type='submit']", LocatorType.XPath); }

    public WebElement Button_Filter() { return findElement("//button[@class='btn-filter btn btn-primary']", LocatorType.XPath); }

    public WebElement CheckBox_FilterAll() { return findElement("All", LocatorType.Id); }

    public WebElement CheckBox_FilterPromo() { return findElement("Promo", LocatorType.Id); }

    public WebElement CheckBox_FilterReferral() { return findElement("//label[text()='Referral']", LocatorType.XPath); }

    public WebElement CheckBox_FilterOneOffByAdmin() { return findElement("//label[text()='One Off']", LocatorType.XPath); }

    public WebElement CheckBox_FilterOneOffFBShare() { return findElement("//label[text()='FB Shared']", LocatorType.XPath); }

    public WebElement CheckBox_FilterDeliveryChargesByPromoter() { return findElement("//label[text()='Delivery By Partner']", LocatorType.XPath); }

    public WebElement CheckBox_FilterDeliveryChargesByPromoterMultipleUse() { return findElement("//label[text()='Delivery By Partner (M)']", LocatorType.XPath); }

    public WebElement CheckBox_HideExpired() { return findElement("//label[text()='Hide Expired']", LocatorType.XPath); }

    public WebElement CheckBox_DateFilterAll() { return findElement("AllCreationDate", LocatorType.Id); }

    public WebElement CheckBox_DateFilterSevenDays() { return findElement("chkDateFilterSevenDays", LocatorType.Id); }

    public WebElement CheckBox_DateFilterThirtyDays() { return findElement("chkDateFilterThirtyDays", LocatorType.Id); }

    public WebElement CheckBox_DateFilterDateRange() { return findElement("chkDateFilterDateRange", LocatorType.Id); }

    public WebElement TextBox_FromDate() { return findElement("FromDate", LocatorType.Id); }

    public WebElement TextBox_ToDate() { return findElement("ToDate", LocatorType.Id); }

    public WebElement Button_Apply() { return findElement("//button[text()='APPLY']", LocatorType.XPath); }

    public WebElement Button_Reset() { return findElement("//button[text()='RESET']", LocatorType.XPath); }

    public WebElement Icon_CloseFilter() { return findElement("//button[@class='btn-close']", LocatorType.XPath); }

    public WebElement Label_SelectPromoCodeType() { return findElement("//label[contains(text(),'Select Promo Code Type:')]", LocatorType.XPath);}

    public WebElement Label_PromoCodeExpiryDateErrorContainer() { return findElement("//div[text()='Please enter a valid date.']", LocatorType.XPath);}

    public WebElement Label_NoPromoCodesFound(){ return findElement("//tr/td[text()='No Data.']", LocatorType.XPath);}

    //BOC
    public WebElement Button_PreviousPage() { return findElement("//span[text()='First']", LocatorType.XPath); }

    public WebElement Button_NextPage() { return findElement("//span[text()='Last']", LocatorType.XPath); }
    //EOC

    public WebElement Button_EditPromoCode() { return findElement("//button[text()='Edit']", LocatorType.XPath); }

    public WebElement Button_EditPromoCodeView() { return findElement("//div/table/tbody/tr[1]", LocatorType.XPath); }

    public WebElement DropDown_PromoterOneOff (String text) { return findElement(String.format("//a/span[text()='%s']", text), PageBase.LocatorType.XPath); }

    public WebElement DropDown_PromoterEvent () { return findElement(String.format("//span/span[text()='Promotion']"), PageBase.LocatorType.XPath); }

    public WebElement DropDown_PromotionOption (String eventOption) { return findElement(String.format("//a/span[text()='%s']", eventOption), PageBase.LocatorType.XPath); }

    public WebElement Label_BlankspacCodeName() { return findElement("//*[text()='Should contain alphanumeric and -@#$&_: special characters.']", LocatorType.XPath);}



}