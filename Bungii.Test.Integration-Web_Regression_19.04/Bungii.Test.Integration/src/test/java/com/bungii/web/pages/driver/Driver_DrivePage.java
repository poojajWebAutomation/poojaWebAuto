package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_DrivePage extends PageBase {

    public WebElement Label_ExtraEarnings () { return findElement("//p[contains(text(),'Who couldn’t use more money? Earn over')]/strong", LocatorType.XPath); }
}
