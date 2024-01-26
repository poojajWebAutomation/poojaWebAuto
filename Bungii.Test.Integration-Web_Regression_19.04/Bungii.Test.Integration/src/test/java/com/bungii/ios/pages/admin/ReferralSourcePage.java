package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ReferralSourcePage extends PageBase {
    public WebElement Text_NumOtherAccount() {
        return findElement("//td[text()='Other']/following-sibling::td[1]", LocatorType.XPath);
    }

    public WebElement Text_PerOtherAccount() {
        return findElement("//td[text()='Other']/following-sibling::td[2]", LocatorType.XPath);
    }

    public WebElement Text_NumFacebookAccount() {
        return findElement("//td[text()='Facebook']/following-sibling::td[1]", LocatorType.XPath);
    }

    public WebElement Text_PerFacebookAccount() {
        return findElement("//td[text()='Facebook']/following-sibling::td[2]", LocatorType.XPath);
    }
}
