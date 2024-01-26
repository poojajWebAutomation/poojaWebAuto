package com.bungii.android.pages.otherApps;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ChromePage extends PageBase {
    //Mobile Actions
    public WebElement Icon_Swipe() { return findElement("//android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View[1]/android.view.View", LocatorType.XPath);}
    public WebElement Swipe_EndPoint() { return findElement("//android.view.ViewGroup/android.view.ViewGroup/com.android.launcher3.widget.LauncherAppWidgetHostView[1]/android.widget.RelativeLayout/android.widget.RelativeLayout", LocatorType.XPath);}
    public WebElement Textbox_SearchBar() { return findElement("//android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.EditText", LocatorType.XPath);}
    public WebElement Icon_Chrome() { return findElement("//android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.TextView[1]", LocatorType.XPath);}
    public WebElement Textbox_GoogleSearchBar() { return findElement("//android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.EditText", LocatorType.XPath);}
    public WebElement DropDown_FirstValue() { return findElement("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.LinearLayout", LocatorType.XPath);}

    //Partner Login
    public WebElement Textbox_EnterPassword() { return findElement("//android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText", LocatorType.XPath);}
    public WebElement Button_SignIn() { return findElement("//android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.Button", LocatorType.XPath);}

    public WebElement Icon_BungiiLogo() { return findElement("//android.widget.Image[@text=\"Bungii Logo\"]", LocatorType.XPath);}
    public WebElement Icon_Logout() { return findElement("//android.widget.Image[@text=\"Logout\"]", LocatorType.XPath);}
    public WebElement Text_WhatsNeeded() { return findElement("//android.view.View[@text=\"WHAT'S NEEDED?\"] | //android.widget.TextView[@text=\"WHAT'S NEEDED?\"]", LocatorType.XPath);}
    public WebElement Text_PickUpAddress() { return findElement("//android.widget.TextView[@text=\"PICKUP ADDRESS\"]", LocatorType.XPath);}
    public WebElement Text_DeliveryAddress() { return findElement("//android.widget.TextView[@text=\"DELIVERY ADDRESS\"] | //android.view.View[@text=\"DELIVERY ADDRESS\"]", LocatorType.XPath);}
    public WebElement Text_PickUpDate() { return findElement("//android.widget.TextView[@text=\"PICKUP DATE\"]", LocatorType.XPath);}
    public WebElement Text_PickUpTime() { return findElement("//android.widget.TextView[contains(@text,'PICKUP TIME')] | //android.view.View[contains(@text,'PICKUP TIME')]", LocatorType.XPath);}
    public WebElement Text_SucessMsg() { return findElement("//android.widget.TextView[contains(@text,'Your delivery has been scheduled.')]", LocatorType.XPath);}
    public WebElement Text_TrackingId() { return findElement("//android.view.View[contains(@text,'TRACKING ID:')]", LocatorType.XPath);}
    public WebElement Text_Time() { return findElement("//android.view.View[contains(@text,'PICKUP DATE & TIME:')]", LocatorType.XPath);}
    public WebElement Text_Drivers() { return findElement("//android.view.View[contains(@text,'WHAT’S NEEDED:')]", LocatorType.XPath);}
    public WebElement Text_Payment() { return findElement("//android.view.View[contains(@text,'PAYMENT:')]", LocatorType.XPath);}
    public WebElement Button_NewBungii() { return findElement("//android.widget.Button[contains(@text,'NEW BUNGII')]", LocatorType.XPath);}


    //Weight-based Portal
    public WebElement Icon_PartnerLogo() { return findElement("//android.widget.Image[@text=\"Partner Logo\"]", LocatorType.XPath);}
    public WebElement Text_Pallet1() { return findElement("//android.view.View[@text=\"1 Pallet\"]", LocatorType.XPath);}
    public WebElement Text_Pallet2() { return findElement("//android.view.View[@text=\"2 Pallets\"]", LocatorType.XPath);}
    public WebElement Text_CustomQuotes() { return findElement("//android.widget.TextView[@text=\"Custom Quotes\"]", LocatorType.XPath);}
    public WebElement Text_ServiceLevel() { return findElement("//android.view.View[@text=\"SERVICE LEVEL\"]", LocatorType.XPath);}
    public WebElement Button_Edit() { return findElement("//android.widget.TextView[contains(@text,'EDIT')]", LocatorType.XPath);}
    public WebElement Button_ClearAddress() { return findElement("//android.widget.TextView[contains(@resource-id,'pickupAddCloseIcon')]", LocatorType.XPath);}
    public WebElement Textbox_PickUpAddress() { return findElement("//android.widget.EditText[contains(@resource-id,'pickValue')]", LocatorType.XPath);}
    public WebElement Textbox_DeliveryAddress() { return findElement("//android.widget.EditText[contains(@resource-id,'dropValue')]", LocatorType.XPath);}
    public WebElement Dropdown_ServiceLevel() { return findElement("//android.view.View[contains(@resource-id,'service-level-menu')]", LocatorType.XPath);}
    public WebElement Dropdown_FirstThreshold() { return findElement("//android.view.View/android.widget.ListView/android.view.View[2]", LocatorType.XPath);}
    public WebElement Textbox_Items() { return findElement("//android.widget.EditText[contains(@resource-id,'Name_0')]", LocatorType.XPath);}
    public WebElement Textbox_Dimensions() { return findElement("//android.widget.EditText[contains(@resource-id,'Dimensions_0')]", LocatorType.XPath);}
    public WebElement Textbox_Weight() { return findElement("//android.widget.EditText[contains(@resource-id,'Weight_0')]", LocatorType.XPath);}
    public WebElement Textbox_CustomerName() { return findElement("//android.view.View[contains(@text,'CUSTOMER NAME (FIRST & LAST)*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_CustomerNumber() { return findElement("//android.view.View[contains(@text,'CUSTOMER MOBILE*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_DeliveryPurpose() { return findElement("//android.view.View[contains(@text,'DELIVERY PURPOSE*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_RbNumber() { return findElement("//android.view.View[contains(@text,'RB/SB NUMBER*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_ScheduleBy() { return findElement("//android.view.View[contains(@text,'SCHEDULED BY*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_PickupName() { return findElement("//android.widget.EditText[contains(@resource-id,'f2bd9004-6757-11ea-a4a3-00155d0a8706')]", LocatorType.XPath);}
    public WebElement Textbox_PickupNumber() { return findElement("//android.widget.EditText[contains(@resource-id,'f2bd908c-6757-11ea-a4a3-00155d0a8706')]", LocatorType.XPath);}
    public WebElement Textbox_DropOffName() { return findElement("//android.widget.EditText[contains(@resource-id,'f2bd90b3-6757-11ea-a4a3-00155d0a8706')]", LocatorType.XPath);}
    public WebElement Textbox_DropOffNumber() { return findElement("//android.widget.EditText[contains(@resource-id,'f2bd90d3-6757-11ea-a4a3-00155d0a8706')]", LocatorType.XPath);}


    //Geofence-based Portal
    public WebElement Text_Solo() { return findElement("//android.view.View[@text=\"Solo\"]", LocatorType.XPath);}
    public WebElement Text_Duo() { return findElement("//android.view.View[@text=\"Duo\"]", LocatorType.XPath);}
    public WebElement Button_GetQuote() { return findElement("//android.widget.Button[@text=\"GET QUOTE\"]", LocatorType.XPath);}
    public WebElement Text_Disclaimer() { return findElement("//android.view.View[contains(@resource-id,'helperCheckbox')]", LocatorType.XPath);}
    public WebElement Textbox_ItemsToDeliver() { return findElement("//android.view.View[contains(@text,'ITEMS TO DELIVER*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_LotNumber() { return findElement("//android.view.View[contains(@text,'LOT NUMBER*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_BidderNumber() { return findElement("//android.view.View[contains(@text,'BIDDER NUMBER*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}

    // Customer Payment
    public WebElement Textbox_CardNumber() { return findElement("//android.widget.EditText[contains(@resource-id,'credit-card-number')]", LocatorType.XPath);}
    public WebElement Textbox_ExpirationDate() { return findElement("//android.widget.EditText[contains(@resource-id,'expiration')]", LocatorType.XPath);}
    public WebElement Textbox_CVV() { return findElement("//android.widget.EditText[contains(@resource-id,'cvv')]", LocatorType.XPath);}
    public WebElement Textbox_PostalCode() { return findElement("//android.widget.EditText[contains(@resource-id,'postal-code')]", LocatorType.XPath);}
}
