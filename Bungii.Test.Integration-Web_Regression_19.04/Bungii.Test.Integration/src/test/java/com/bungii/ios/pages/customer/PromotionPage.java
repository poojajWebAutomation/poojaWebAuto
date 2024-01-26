package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class PromotionPage extends PageBase {


    public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

/*    public WebElement Button_IdontLikePromo() {        return findElement("I DON'T LIKE FREE MONEY", PageBase.LocatorType.Name);    }



    public WebElement Button_AcceptPromo() {
        return findElement("YES, I'LL TAKE $5", PageBase.LocatorType.Name);
    }*/

    public WebElement Button_IdontLikePromo() {        return findElement("I DON'T LIKE FREE MONEY", LocatorType.AccessibilityId);    }



    public WebElement Button_AcceptPromo() {
        return findElement("YES, I'LL TAKE $5", PageBase.LocatorType.AccessibilityId);
    }
    public WebElement Button_NextFacebook() {
        return findElement("Next", LocatorType.Name);
    }
    public WebElement Button_ShareFacebook() {
        return findElement("Share", LocatorType.Name);
    }
}
