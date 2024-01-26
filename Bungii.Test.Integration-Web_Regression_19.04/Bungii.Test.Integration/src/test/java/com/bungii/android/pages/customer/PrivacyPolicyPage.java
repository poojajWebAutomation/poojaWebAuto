package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;


public class PrivacyPolicyPage extends PageBase {
    public WebElement Header_PrivacyPolicyPage (boolean...ignoreException) { return findElement("//android.widget.TextView[@text='PRIVACY POLICY']", LocatorType.XPath,ignoreException); }

    public WebElement Text_Privacy (boolean...ignoreException) { return findElement("//android.widget.TextView[@text='PRIVACY']", LocatorType.XPath,ignoreException); }

    public WebElement Button_Cust_Navigate_Up(boolean...ignoreException) { return findElement("//android.widget.ImageButton[contains(@content-desc,'Navigate up')]",LocatorType.XPath,ignoreException);}

}
