package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;


public class PrivacyPolicyPage extends PageBase {
    public WebElement Header_PrivacyPolicyPage (boolean...ignoreException) { return findElement("//android.widget.TextView[@text='PRIVACY POLICY']", LocatorType.XPath,ignoreException); }

    public WebElement Text_Privacy (boolean...ignoreException) { return findElement("//android.widget.TextView[@text='PRIVACY']", LocatorType.XPath,ignoreException); }
}
