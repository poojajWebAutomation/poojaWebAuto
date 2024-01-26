package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class FaqPage extends PageBase {

	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

//	public WebElement Text_NavigationBar() {return FindElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", LocatorType.XPath); }
	public WebElement Button_Twitter() {return findElement("//XCUIElementTypeOther[@name='navigation']/XCUIElementTypeLink[1]", LocatorType.XPath); }
	public WebElement Button_Instagram() {return findElement("//XCUIElementTypeOther[@name='navigation']/XCUIElementTypeLink[2]", LocatorType.XPath); }
	public WebElement Button_Facebook () {return findElement("//XCUIElementTypeOther[@name='navigation']/XCUIElementTypeLink[3]", LocatorType.XPath); }
	public WebElement Text_FirstAnswer() {return findElement("//XCUIElementTypeStaticText[@name=\"We’re an app similar to other popular ridesharing apps but instead of moving you, we move your stuff. Here’s the quick-and-dirty rundown of how it works:\"]", LocatorType.XPath); }

/*	public WebElement Text_FirstQuestion () {return findElement("So what exactly is Bungii?", LocatorType.Name); }
	public WebElement Image_Questions() {return findElement("smartmockups0.png", LocatorType.Name); }
	public WebElement Text_Faq () {return findElement("APP FAQ", LocatorType.Name); }
	public WebElement Text_SocialMediaHeader() {return findElement("WE'RE SOCIAL", LocatorType.Name); }*/
	public WebElement Text_FirstQuestion () {return findElement("//XCUIElementTypeButton[@name=\"So what exactly is Bungii?\"]", LocatorType.XPath); }
	public WebElement Image_Questions() {return findElement("//XCUIElementTypeOther[@name=\"App FAQ - Bungii\"]/XCUIElementTypeOther[2]", LocatorType.XPath); }
	public WebElement Text_Faq () {return findElement("APP FAQ", LocatorType.AccessibilityId); }
	public WebElement Text_SocialMediaHeader() {return findElement("WE'RE SOCIAL", LocatorType.AccessibilityId); }
}
