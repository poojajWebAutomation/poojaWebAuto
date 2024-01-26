package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_VideoTrainingPage extends PageBase {

    //Video Blank - error
    public WebElement Err_Video() { return findElement("summary6", LocatorType.Id); }

    //Video iFrame
    public WebElement Screen_Video(boolean ... ignoreException) { return findElement("//div[@id='divStep6']/div/div/iframe", LocatorType.XPath,ignoreException); }

    //Video - Viewed checkbox
    public WebElement CheckBox_Viewed_Click() { return findElement("//input[@id='TermsnConditions.ViewedVideo']/following-sibling::label", LocatorType.XPath); }

    //Video - Viewed checkbox
    public WebElement CheckBox_Viewed(boolean... ignoreException ) { return findElement("//input[@id='TermsnConditions.ViewedVideo']", LocatorType.XPath,ignoreException); }

    //Video - Next Button
    public WebElement Button_VideoNext() { return findElement("btnVideo", LocatorType.Id); }
}
