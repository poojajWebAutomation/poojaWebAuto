package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PaymentPage extends PageBase {
	public WebElement Text_PaymentMethod() {return findElement(" payment method", PageBase.LocatorType.Name); }
	public WebElement Button_Save() {return findElement("SAVE", PageBase.LocatorType.Name); }

	public WebElement Image_Add() {return findElement("icon-add-new-payment", PageBase.LocatorType.Name); }
	public WebElement Button_CheckBoxOff() {return findElement("payment check off", PageBase.LocatorType.Name); }
	public WebElement Button_CheckBoxOn() {return findElement("payment check on", PageBase.LocatorType.Name); }
	public WebElement Button_AddPayment() {return findElement("Add Payment Method", PageBase.LocatorType.Name); }
	public WebElement Button_ADD() {return findElement("ADD", PageBase.LocatorType.Name); }

	public WebElement TextBox_CardNumber() {return findElement("Card Number", PageBase.LocatorType.Name); }
	public WebElement TextBox_expiry() {return findElement("MM/YY", PageBase.LocatorType.Name); }
	public WebElement TextBox_InvalidCardNumber() {return findElement("Invalid: Card Number", PageBase.LocatorType.Name); }
	public WebElement TextBox_InvalidExpiry() {return findElement("Invalid: MM/YY", PageBase.LocatorType.Name); }
	public WebElement Button_PayPal() {return findElement("PayPal", PageBase.LocatorType.Name); }
	public WebElement Button_Cancel() {return findElement("Cancel", PageBase.LocatorType.Name); }

	public WebElement TextBox_CVV() {return findElement("CVV", PageBase.LocatorType.Name); }
	public WebElement TextBox_PostalCode() {return findElement("Postal Code", PageBase.LocatorType.Name); }

	public WebElement Button_Delete(boolean ...ignoreException) {return findElement("icon delete", PageBase.LocatorType.Name,ignoreException); }


	public List<WebElement> Cell_CardNumber() {return findElements("//XCUIElementTypeOther[@name='Other cards']/following::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
	public WebElement Cell_DefaultCard() {return findElement("//XCUIElementTypeOther[@name='Default card']/following-sibling::XCUIElementTypeCell[1]", PageBase.LocatorType.XPath); }

	//public WebElement Cell_DefaultCardNumber() {return findElement(
	//		"//XCUIElementTypeOther[@name='Default card']/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath); }
	public WebElement Cell_DefaultCardNumber() {return findElement(
			"//XCUIElementTypeOther[@name='Default card']/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }

	public List<WebElement> Text_CardNumber () {return findElements("//XCUIElementTypeStaticText[@name='**** **** **** ']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public List<WebElement> Text_CardNumber_iOS11_2 () {return findElements("//XCUIElementTypeStaticText[@name='**** **** **** ']/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_AddHeader() {return findElement("//XCUIElementTypeStaticText[@name='Add payment option']", PageBase.LocatorType.XPath); }
	public WebElement Text_AddInfo() {return findElement(
		"//XCUIElementTypeStaticText[@name='Add payment option']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	public WebElement Text_NavigationBar() {return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_AddNew() {return findElement("//XCUIElementTypeOther/XCUIElementTypeButton[@name='Add new']", PageBase.LocatorType.XPath); }

}
