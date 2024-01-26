package com.bungii.ios.pages.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TutorialPage extends PageBase {

/*    public WebElement Button_Close() {
        return findElement("Button", PageBase.LocatorType.Name);
    }
    public WebElement Text_ToutorialHeader(boolean... ignoreException){return findElement("SO WHAT IS BUNGII?", LocatorType.Name,ignoreException);}*/
    public WebElement Button_Close(boolean... ignoreException) {
        return findElement("Button", LocatorType.AccessibilityId,ignoreException);
    }
    public WebElement Image_tutorialstep1(boolean... ignoreException) {
        return findElement("bungii_tutorialstep1", LocatorType.Name,ignoreException);
    }
    public WebElement Image_tutorialstep2(boolean... ignoreException) {
        return findElement("bungii_tutorialstep2", LocatorType.Name,ignoreException);
    }
    public WebElement Image_Generictutorialstep(boolean... ignoreException) {
        return findElement("//XCUIElementTypeImage[contains(@name,'bungii_tutorialstep')]", LocatorType.XPath,ignoreException);
    }
    public WebElement Image_tutorialstep3(boolean... ignoreException) {
        return findElement("bungii_tutorialstep3", LocatorType.Name,ignoreException);
    }
    public WebElement Image_tutorialstep4(boolean... ignoreException) {
        return findElement("bungii_tutorialstep4", LocatorType.Name,ignoreException);
    }
    public WebElement Image_tutorialstep5(boolean... ignoreException) {
        return findElement("bungii_tutorialstep5", LocatorType.Name,ignoreException);
    }
    public WebElement Button_Start(boolean... ignoreException) {
        return findElement("START", LocatorType.Name,ignoreException);
    }
    public WebElement Text_ToutorialHeader(boolean... ignoreException){return findElement("SO WHAT IS BUNGII?", LocatorType.AccessibilityId,ignoreException);}
    //page 5 of 5
    public WebElement PageIndicator(boolean... ignoreException){return findElement("XCUIElementTypePageIndicator", LocatorType.ClassName,ignoreException);}
}
