package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class MenuPage extends PageBase {
    //---------Menu Button---------------------------------------------------------------------------
    public WebElement Button_Menu () { return findElement("android.widget.ImageButton", LocatorType.ClassName); }

    public WebElement CustMenu_Email () { return findElement("com.bungii.customer:id/header_textview_email", LocatorType.Id); }

    //---------Driver Menu-------------------------------------------------------------------------
    public WebElement Menu_Home () { return findElement("//android.widget.CheckedTextView[@text='HOME']", LocatorType.XPath); }

    public WebElement Menu_FAQ () { return findElement("//android.widget.CheckedTextView[@text='FAQ']", LocatorType.XPath); }

    public WebElement Menu_Account () { return findElement("//android.widget.CheckedTextView[@text='ACCOUNT']", LocatorType.XPath); }

    public WebElement Menu_Payment () { return findElement("//android.widget.CheckedTextView[@text='PAYMENT']", LocatorType.XPath); }

    public WebElement Menu_Support () { return findElement("//android.widget.CheckedTextView[@text='SUPPORT']", LocatorType.XPath); }

    public WebElement Menu_SaveMoney () { return findElement("//android.widget.CheckedTextView[@text='SAVE MONEY']", LocatorType.XPath); }

    public WebElement Menu_Logout () { return findElement("//android.widget.CheckedTextView[@text='LOGOUT']", LocatorType.XPath); }

    //---------Page Titles---------------------------------------------------------------------
    public WebElement PageTitle_Home () { return findElement("//android.widget.TextView[@text='BUNGII']", LocatorType.XPath); }

    public WebElement PageTitle_FAQ () { return findElement("//android.widget.TextView[@text='FAQ']", LocatorType.XPath); }

    public WebElement PageTitle_Account () { return findElement("//android.widget.TextView[@text='ACCOUNT']", LocatorType.XPath); }

    public WebElement PageTitle_Payment () { return findElement("//android.widget.TextView[@text='PAYMENT']", LocatorType.XPath); }

    public WebElement PageTitle_Support () { return findElement("//android.widget.TextView[@text='SUPPORT']", LocatorType.XPath); }

    public WebElement PageTitle_SaveMoney () { return findElement("//android.widget.TextView[@text='SAVE MONEY']", LocatorType.XPath); }

    //---------Page Elements---------------------------------------------------------------------
    //----------------FAQ-----------------------------------------------------
    public WebElement FAQ_BungiiLogo () { return findElement("com.bungii.customer:id/promo_code_label", LocatorType.Id); }

    public WebElement FAQ_Image () { return findElement("", LocatorType.Id); }

    public WebElement FAQ_TitleImage () { return findElement("", LocatorType.Id); }

    public WebElement FAQ_AppFAQTitle () { return findElement("", LocatorType.Id); }

    public WebElement FAQ_LastQuestion () { return findElement("", LocatorType.Id); }

    public WebElement FAQ_TwitterLogo () { return findElement("//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][1]", LocatorType.XPath); }

    public WebElement FAQ_InstagramLogo () { return findElement("//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][2]", LocatorType.XPath); }

    public WebElement FAQ_FBLogo () { return findElement("//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][3]", LocatorType.XPath); }
    //----------------Save Money----------------------------------------------
    public WebElement SaveMoney_PromoCode () { return findElement("", LocatorType.Id); }
}
