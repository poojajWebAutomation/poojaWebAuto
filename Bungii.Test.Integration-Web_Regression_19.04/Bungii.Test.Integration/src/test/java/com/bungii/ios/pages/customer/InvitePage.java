package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;
public class InvitePage  extends PageBase {



/*	public WebElement Button_Share() { return findElement("SHARE", PageBase.LocatorType.Name);}
	public WebElement Button_Done() { return findElement("Done", PageBase.LocatorType.Name);}
	public WebElement Image_InviteIcon() { return findElement("icon_invite_referrals", PageBase.LocatorType.Name);}
	public WebElement Text_PromoCode() { return findElement("PROMO CODE", PageBase.LocatorType.Name);}
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_Facebook() { return findElement("Share on Facebook", PageBase.LocatorType.Name);}
	public WebElement Button_Twitter() { return findElement("Share on Twitter", PageBase.LocatorType.Name);}
	public WebElement Button_Email() { return findElement("Share by Email", PageBase.LocatorType.Name);}
	public WebElement Button_TextMessage() { return findElement("Share by Text Message", PageBase.LocatorType.Name);}
	public WebElement Button_CancelApp() { return findElement("Cancel", PageBase.LocatorType.Name);}
	public WebElement Text_SMS() { return findElement("messageBodyField", PageBase.LocatorType.Name);}
	public WebElement Text_EmailSubject() { return findElement("subjectField", PageBase.LocatorType.Name);}
	public WebElement Button_Delete() { return findElement("Delete Draft", PageBase.LocatorType.Name);}


	public WebElement Text_EmailBody() { return findElement("//XCUIElementTypeTextView/XCUIElementTypeStaticText", PageBase.LocatorType.XPath);}
	public WebElement Text_InviteHeader() { return findElement("//XCUIElementTypeImage[@name='icon_invite_referrals']/following-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath);}
	public WebElement Text_InviteInfo() { return findElement("//XCUIElementTypeImage[@name='icon_invite_referrals']/following-sibling::XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath);}
	public WebElement Button_Code() { return findElement("//XCUIElementTypeImage[@name='icon_invite_referrals']/following-sibling::XCUIElementTypeButton", PageBase.LocatorType.XPath);}
	public WebElement Text_TwitterBody() { return findElement("//XCUIElementTypeTextView", PageBase.LocatorType.XPath);}
	public WebElement Buttin_Tweet() { return findElement("Tweet", LocatorType.Name);}
	public WebElement Button_TwitterCancel() { return findElement("Cancel", PageBase.LocatorType.Name);}*/
	public WebElement Button_Share() { return findElement("SHARE", LocatorType.AccessibilityId);}
	public WebElement Button_Done(boolean...ignoreException) { return findElement("Done", PageBase.LocatorType.AccessibilityId,ignoreException);}
	public WebElement Image_InviteIcon() { return findElement("icon_invite_referrals", PageBase.LocatorType.AccessibilityId);}
	public WebElement Text_PromoCode() { return findElement("PROMO CODE", PageBase.LocatorType.AccessibilityId);}
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_Facebook() { return findElement("Share on Facebook", PageBase.LocatorType.AccessibilityId);}
	public WebElement Button_Twitter() { return findElement("Share on Twitter", PageBase.LocatorType.AccessibilityId);}
	public WebElement Button_Email() { return findElement("Mail", PageBase.LocatorType.Name);}
	public WebElement Button_TextMessage() { return findElement("Message", LocatorType.Name);}
	public WebElement Button_CancelApp() { return findElement("Cancel", PageBase.LocatorType.AccessibilityId);}
	public WebElement Text_SMS() { return findElement("messageBodyField", LocatorType.Name);}
	public WebElement Text_EmailSubject() { return findElement("subjectField", LocatorType.Name);}
	public WebElement Button_Delete() { return findElement("Delete Draft", PageBase.LocatorType.AccessibilityId);}


	public WebElement Text_EmailBody() { return findElement("//XCUIElementTypeTextView/XCUIElementTypeStaticText", PageBase.LocatorType.XPath);}
	public WebElement Text_InviteHeader() { return findElement("//XCUIElementTypeImage[@name='icon_invite_referrals']/following-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath);}
	public WebElement Text_InviteInfo() { return findElement("//XCUIElementTypeImage[@name='icon_invite_referrals']/following-sibling::XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath);}
	public WebElement Button_Code() { return findElement("//XCUIElementTypeImage[@name='icon_invite_referrals']/following-sibling::XCUIElementTypeButton", PageBase.LocatorType.XPath);}
	public WebElement Text_TwitterBody() { return findElement("XCUIElementTypeTextView", LocatorType.ClassName);}
	public WebElement Buttin_Tweet() { return findElement("Tweet", LocatorType.AccessibilityId);}
	public WebElement Button_TwitterCancel() { return findElement("Cancel", PageBase.LocatorType.AccessibilityId);}
	public WebElement Button_ScheduleBungii() { return findElement("//XCUIElementTypeButton[@name='SCHEDULE BUNGII']", LocatorType.XPath);}
}
