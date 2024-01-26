package com.bungii.ios.pages.other;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SafariPage extends PageBase {
    public WebElement Icon_Safari() { return findElement("//XCUIElementTypeIcon[contains(@name,'Safari')]", LocatorType.XPath);}
    public WebElement Textbox_SafariSearch() { return findElement("//XCUIElementTypeTextField[contains(@name,'TabBarItemTitle')]", LocatorType.XPath);}
    public WebElement Textbox_SafariSearchBar() { return findElement("//XCUIElementTypeTextField[contains(@name,'URL')]", LocatorType.XPath);}

    public WebElement Button_Go() { return findElement("//XCUIElementTypeButton[contains(@name,'Go')]", LocatorType.XPath);}

    //Partner Login
    public WebElement Textbox_EnterPassword() { return findElement("//XCUIElementTypeSecureTextField[contains(@value,'Enter Password')]", LocatorType.XPath);}
    public WebElement Button_SignIn() { return findElement("//XCUIElementTypeButton[contains(@name,'SIGN IN')]", LocatorType.XPath);}

    public WebElement Icon_BungiiLogo() { return findElement("//XCUIElementTypeImage[@name=\"Bungii Logo\"]", LocatorType.XPath);}
    public WebElement Icon_Logout() { return findElement("//XCUIElementTypeImage[@name=\"Logout\"]", LocatorType.XPath);}
    public WebElement Text_WhatsNeeded() { return findElement("//XCUIElementTypeStaticText[@name=\"WHAT'S NEEDED?\"] | //android.widget.TextView[@text=\"WHAT'S NEEDED?\"]", LocatorType.XPath);}
    public WebElement Text_PickUpAddress() { return findElement("//XCUIElementTypeStaticText[@name=\"PICKUP ADDRESS\"]", LocatorType.XPath);}
    public WebElement Text_DeliveryAddress() { return findElement("//XCUIElementTypeStaticText[@name=\"DELIVERY ADDRESS\"] | //android.view.View[@text=\"DELIVERY ADDRESS\"]", LocatorType.XPath);}
    public WebElement Text_PickUpDate() { return findElement("//XCUIElementTypeStaticText[@name=\"PICKUP DATE\"]", LocatorType.XPath);}
    public WebElement Text_PickUpTime() { return findElement("//XCUIElementTypeStaticText[contains(@name,'PICKUP TIME')]", LocatorType.XPath);}

    //Success Page
    public WebElement Text_SucessMsg() { return findElement("//XCUIElementTypeStaticText[contains(@name,'Your delivery has been scheduled.')]", LocatorType.XPath);}
    public WebElement Text_TrackingId() { return findElement("//XCUIElementTypeStaticText[contains(@name,'TRACKING ID:')]", LocatorType.XPath);}
    public WebElement Text_Time() { return findElement("//XCUIElementTypeStaticText[contains(@name,'PICKUP DATE & TIME:')]", LocatorType.XPath);}
    public WebElement Text_Drivers() { return findElement("//XCUIElementTypeStaticText[contains(@name,'WHAT’S NEEDED:')]", LocatorType.XPath);}
    public WebElement Text_Payment() { return findElement("//XCUIElementTypeStaticText[contains(@name,'PAYMENT:')]", LocatorType.XPath);}
    public WebElement Button_NewBungii() { return findElement("//XCUIElementTypeButton[contains(@name,'NEW BUNGII')]", LocatorType.XPath);}


    //Fixed pricing Portal
    public WebElement Text_Solo() { return findElement("//XCUIElementTypeOther[@name=\"Solo\"]", LocatorType.XPath);}
    public WebElement Text_Duo() { return findElement("//XCUIElementTypeOther[@name=\"Duo\"]", LocatorType.XPath);}
    public WebElement Text_ServiceLevel() { return findElement("//XCUIElementTypeStaticText[@name=\"SERVICE LEVEL\"]", LocatorType.XPath);}
    public WebElement Textbox_PickUpAddress() { return findElement("//XCUIElementTypeTextField[contains(@value,'Enter pickup address')]", LocatorType.XPath);}
    public WebElement Textbox_DeliveryAddress() { return findElement("//XCUIElementTypeTextField[contains(@value,'Enter dropoff address')]", LocatorType.XPath);}
    public WebElement Dropdown_ServiceLevel() { return findElement("//XCUIElementTypeOther[1]/XCUIElementTypeWebView/XCUIElementTypeWebView/XCUIElementTypeWebView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[20]/XCUIElementTypeOther", LocatorType.XPath);}
    public WebElement Dropdown_Values(String serviceLevel) { return findElement("//XCUIElementTypeOther[contains(@name,'"+serviceLevel+"')]", LocatorType.XPath);}
    public WebElement Button_Return() { return findElement("//XCUIElementTypeButton[contains(@name,'Return')]", LocatorType.XPath);}
    public WebElement Button_Continue() { return findElement("//XCUIElementTypeButton[contains(@name,'Submit')]", LocatorType.XPath);}
    public WebElement Button_Done() { return findElement("//XCUIElementTypeButton[contains(@name,'Done')]", LocatorType.XPath);}
    public WebElement Button_ScheduleBungii() { return findElement("//XCUIElementTypeButton[contains(@name,'SCHEDULE BUNGII')]", LocatorType.XPath);}
    public WebElement Button_GetEstimate() { return findElement("//XCUIElementTypeButton[contains(@name,'GET ESTIMATE')]", LocatorType.XPath);}
    public WebElement Button_ContinueKiosk() { return findElement("//XCUIElementTypeButton[contains(@name,'Continue')] | //XCUIElementTypeButton[contains(@name,'CONTINUE')]", LocatorType.XPath);}
    public WebElement Button_Cut() { return findElement("//XCUIElementTypeStaticText[contains(@name,'Cut')]", LocatorType.XPath);}
    public WebElement Text_PickUpValidation(boolean ...ignoreException) { return findElement("//XCUIElementTypeStaticText[contains(@name,'Pickup Address is required.')]", LocatorType.XPath,ignoreException);}
    public WebElement Text_DropOffValidation(boolean ...ignoreException) { return findElement("//XCUIElementTypeStaticText[contains(@name,'Drop Off Address is required.')]", LocatorType.XPath,ignoreException);}


    public WebElement Textbox_Items() { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[1]", LocatorType.XPath);}
    public WebElement Textbox_CustomerMobile() { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[3]", LocatorType.XPath);}
    public WebElement Textbox_Instructions() { return findElement("//XCUIElementTypeStaticText[contains(@name,'Enter any special instructions for the delivery')]", LocatorType.XPath);}
    public WebElement Textbox_PickupName() { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[4]", LocatorType.XPath);}
    public WebElement Textbox_PickupNumber() { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[5]", LocatorType.XPath);}
    public WebElement Textbox_DropOffName() { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[6]", LocatorType.XPath);}
    public WebElement Textbox_DropOffNumber() { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[7]", LocatorType.XPath);}
    public WebElement Textbox_Receipt() { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[8]", LocatorType.XPath);}
    public WebElement Textbox_CustomerName() { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[2]", LocatorType.XPath);}
    public WebElement Textbox_CustomerNumber() { return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[3]", LocatorType.XPath);}

    // Customer Payment
    public WebElement Textbox_CardNumber() { return findElement("//XCUIElementTypeOther[contains(@name,'Secure Credit Card Frame - Credit Card Number')]", LocatorType.XPath);}
    public WebElement Textbox_ExpirationDate() { return findElement("//XCUIElementTypeOther[contains(@name,'Secure Credit Card Frame - Expiration Date')]", LocatorType.XPath);}
    public WebElement Textbox_CVV() { return findElement("//XCUIElementTypeOther[contains(@name,'Secure Credit Card Frame - CVV')]", LocatorType.XPath);}
    public WebElement Textbox_PostalCode() { return findElement("//XCUIElementTypeOther[contains(@name,'Secure Credit Card Frame - Postal Code')]", LocatorType.XPath);}

    //Kiosk Mode
    public WebElement Text_LoadUnload() { return findElement("//XCUIElementTypeOther[@name=\"LOAD + UNLOAD TIME\"]", LocatorType.XPath);}
    public WebElement Button_Edit() { return findElement("//XCUIElementTypeStaticText[contains(@name,'EDIT')]", LocatorType.XPath);}
    public WebElement Textbox_PickUpAddressKioski() { return findElement("//XCUIElementTypeTextField[contains(@value,'6520 Macon Rd, Memphis,')]", LocatorType.XPath);}
    public WebElement Dropdown_LoadTime() { return findElement("//XCUIElementTypeWebView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[16]/XCUIElementTypeOther", LocatorType.XPath);}
    public WebElement Dropdown_LoadTimeValue(String time) { return findElement("//XCUIElementTypeOther[contains(@name,'"+time+"')]", LocatorType.XPath);}
    public WebElement PopUp_AdminPassword() { return findElement("//XCUIElementTypeStaticText[contains(@name,'Admin Password Required')]", LocatorType.XPath);}
    public WebElement Text_PasswordRequired() { return findElement("//XCUIElementTypeStaticText[contains(@name,'Password is required.')]", LocatorType.XPath);}
    public WebElement Textbox_Password() { return findElement("//XCUIElementTypeSecureTextField", LocatorType.XPath);}

    //Partner Payment
    public WebElement RadioButton_PartnerInvoice() { return findElement("//XCUIElementTypeOther[contains(@name,'Partner Invoice')]", LocatorType.XPath);}
}