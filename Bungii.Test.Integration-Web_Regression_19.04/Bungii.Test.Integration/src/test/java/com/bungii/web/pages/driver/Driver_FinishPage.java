package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_FinishPage extends PageBase {

    //Finish - Continue Button
    public WebElement Button_FinishContinue () { return findElement("//div[@id='divStep7']/button[contains(text(),'Continue')]", LocatorType.XPath); }
        }
