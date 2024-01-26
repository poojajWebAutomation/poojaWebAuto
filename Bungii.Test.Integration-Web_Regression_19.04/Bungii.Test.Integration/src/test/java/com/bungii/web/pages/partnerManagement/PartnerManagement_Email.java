package com.bungii.web.pages.partnerManagement;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class PartnerManagement_Email extends PageBase {

    //To Search for partner
    public WebElement TextBox_Search() { return findElement("//div[@class=\"sidebar\"]/div[2]/input", LocatorType.XPath); }

    //Edit Email address for partner
    public WebElement Button_EditEmail() { return findElement("//div/a/following-sibling::button", LocatorType.XPath); }

    //Header of edit email popup
    public WebElement Header_EditEmailAddress() { return findElement("//div[text()=\"Edit Email Address\"]", LocatorType.XPath); }

    //email address text
    public WebElement Text_OldEmailAddress() { return findElement("//form/div[1]/label/following-sibling::input", LocatorType.XPath); }

    //email address displayed on location setting
    public WebElement Text_EmailAddressOnLocationSetting() { return findElement("//div[@class=\"value\"]/div/div[1]/a", LocatorType.XPath); }

    //Add new Email address
    public WebElement TextBox_AddNewEmail() { return findElement("//form/div/label[text()=\"New Email Address\"]/following-sibling::input", LocatorType.XPath); }

    //Select parent node
    public WebElement Link_PartnerLocationSelectFromSideBar() { return findElement("//div[@class=\"menu\"]/div[2]/div[2]/div[1]", LocatorType.XPath); }

    //Save email button
    public WebElement Button_Save() { return findElement("//button[text()=\"Save\"]", LocatorType.XPath); }

    public WebElement Button_AddEmailAddress() { return findElement("//label[text()=\"Add Email Address\"]", LocatorType.XPath); }

    public WebElement Button_EditEmailAddress(int num) { return findElements("//div/a/following-sibling::button",LocatorType.XPath).get(num);}
}
