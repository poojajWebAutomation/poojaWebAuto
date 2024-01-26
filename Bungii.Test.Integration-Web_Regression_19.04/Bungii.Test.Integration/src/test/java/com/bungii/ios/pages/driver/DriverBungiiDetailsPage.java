package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;


public class DriverBungiiDetailsPage extends PageBase{
	//can delete this page, Test this by deleting it
	/*
	public WebElement Button_Accept() {return findElement("START BUNGII", LocatorType.Name); }
	public WebElement Button_Cancel() {return findElement("CANCEL BUNGII", LocatorType.Name); }

	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	*//**
	 * Start bungii
	 *//*
	public void startBungii(){
		click(Button_Accept);
	}
	

	*//**
	 * Cancel bungii
	 *//*
	public void cancelBungii(){
		click(Button_Cancel);
	}
	
	*//**
	 * Check if active page is Bungii details details page. 
	 * @return return comparison result navigation header to expected msg from
	 *         property
	 *//*
	public boolean isBungiiDetailsPage() {
		textToBePresentInElementName(Text_NavigationBar,PropertyUtility.getMessage("driver.navigation.bungiidetails"));
		return getNameAttribute(Text_NavigationBar).equals(PropertyUtility.getMessage("driver.navigation.bungiidetails"));

	}*/
}

