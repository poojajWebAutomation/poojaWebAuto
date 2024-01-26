package com.bungii.ios.pages.customer;

//import org.apache.tools.ant.taskdefs.WaitFor;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SearchingPage extends PageBase {


    public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
  //  public WebElement Activity_ProgressBar(boolean...ignoreException) {return findElement("In progress", PageBase.LocatorType.Name,ignoreException); }
    //public By Activity_ProgressBar = MobileBy.name("Progress");

    public WebElement Text_WaitingMessage(boolean...ignoreException) {
        return findElement("//XCUIElementTypeActivityIndicator[@name='In progress']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath,ignoreException);
    }

    public WebElement Activity_ProgressBar(boolean...ignoreException) {return findElement("In progress", LocatorType.AccessibilityId,ignoreException); }

}
