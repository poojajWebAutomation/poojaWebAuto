package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BungiiAcceptedPage extends PageBase {
    public WebElement Text_NavigationBar() {
        return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath);
    }

/*    public WebElement Button_Ok() {
        return findElement("OK", LocatorType.Name);
    }*/
public WebElement Button_Ok(boolean... ignoreExcetion) {
    return findElement("OK", LocatorType.AccessibilityId, ignoreExcetion);
}


    //STACK
    public WebElement Text_StackInfo() { return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(3); } //device handling
    public WebElement Text_BungiiAccepted() { return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(1); } //device handling
    public WebElement Image_RattingBar() { return findElement("rating filled star icon", LocatorType.Name); }
    public WebElement Label_DriverName(){ return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(2); } //device handling

    public WebElement Textlabel_DriverNearby() { return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(1); } //device handling
    public WebElement Textlabel_StackSubtitle() { return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(2); } //device handling
    public WebElement Textlabel_ProjectedTimeValue(){ return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(4); } //device handling
    public WebElement Textlabel_ProjectedTime() { return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(3); } //device handling

    public WebElement Button_CancelBungii() { return findElement("Cancel Bungii", LocatorType.Name); }
    public List<WebElement> FilledStars() {return findElements("//*[contains(@name, 'rating filled star icon')]", LocatorType.XPath);}
    public List<WebElement> HalfFilledStar() {return findElements("//*[contains(@name, 'rating half filled star icon')]", LocatorType.XPath);}

}
