package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_GetAllBungiiDriversPage extends PageBase {

    public WebElement TextBox_Search () { return findElement("SearchCriteria", LocatorType.Name); }

    public WebElement Button_Search () { return findElement("btnSearch", LocatorType.Id); }

    public WebElement GridRow_BungiiActiveDriver (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td[text()='Active']", LocatorType.XPath); }
    public WebElement GridRow_BungiiRejectedDriver (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td[text()='Rejected']", LocatorType.XPath); }
    public WebElement GridRow_BungiiResentToDriver (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td[text()='Re-sent to Driver']", LocatorType.XPath); }
    public WebElement GridRow_BungiiPendingVerification (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td[text()='Pending Verification']", LocatorType.XPath); }

    public WebElement GridRow_PendingVerificationLink (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td[text()='Pending Verification']/following-sibling::td/div[1]/*[2]", LocatorType.XPath); }
    public WebElement Driver_Profile (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td/div/a/img[@title='Profile']", LocatorType.XPath); }
    public WebElement Driver_Mobile_Edit() { return findElement("//td[contains(text(),'Phone Number')]/following-sibling::td/div/div/img[@title='Edit Phone']",LocatorType.XPath);}
    public WebElement Driver_Phone(boolean...ignoreException) { return findElement("//td[contains(text(),'Phone Number')]/following-sibling::td/div/div/div/strong",LocatorType.XPath,ignoreException);}
    public WebElement Driver_PhoneEntry(boolean...ignoreException) { return findElement("//input[@id='PhoneNumber']",LocatorType.XPath,ignoreException);}
    public WebElement Driver_Mobile_Save() { return findElement("//td[contains(text(),'Phone Number')]/following-sibling::td/div/div/img[@title='Save']",LocatorType.XPath);}
    public WebElement Driver_Mobile_Updated_Comment() { return findElement("//label[contains(text(),'Comments')]/following-sibling::textarea",LocatorType.XPath);}
    public WebElement Driver_Mobile_Updated_Comment_Save() { return findElement("//button[contains(text(),'Save')]",LocatorType.XPath);}
    public WebElement Driver_Mobile_Cancel() { return findElement("//td[contains(text(),'Phone Number')]/following-sibling::td/div/div/img[@title='Cancel']",LocatorType.XPath);}
    public WebElement Driver_Mobile_Updated_Comment_Cancel() { return findElement("//button[contains(text(),'Cancel')]",LocatorType.XPath);}
}
