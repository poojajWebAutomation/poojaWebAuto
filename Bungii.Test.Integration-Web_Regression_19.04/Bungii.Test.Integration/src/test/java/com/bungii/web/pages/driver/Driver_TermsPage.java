package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_TermsPage extends PageBase {

    //Terms & Conditions - Text
    public WebElement Text_Terms() { return findElement("//table[@class='table table-bordered']/tbody/tr[9]/td[2]", LocatorType.XPath); }

    //Terms & Conditions - Error - Agree unchecked
    public WebElement Err_Terms () { return findElement("summary5", LocatorType.Id); }

    //Terms & Conditions - H5
    public WebElement Terms_H5 () { return findElement("//div[@id='divStep5']/div[2]/div/h5", LocatorType.XPath); }

    //Terms & Conditions - Agree checkbox
    public WebElement CheckBox_Agree_Click() { return findElement("//input[@id='TermsnConditions.HasAgreedToTerms']/following-sibling::label", LocatorType.XPath); }

    //Terms & Conditions - Agree checkbox
    public WebElement CheckBox_Agree(boolean...ignoreException) { return findElement("//input[@id='TermsnConditions.HasAgreedToTerms']", LocatorType.XPath,ignoreException); }

    //Terms & Conditions - Next Button
    public WebElement Button_TermsNext () { return findElement("//li[contains(text(),'Terms & Conditions')]", LocatorType.XPath); }
    public WebElement Button_PrivacyPolicyNext () { return findElement("//li[contains(text(),'Privacy Policy')]", LocatorType.XPath); }
}
