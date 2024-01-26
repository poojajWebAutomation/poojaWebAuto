package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiAcceptedPage extends PageBase {

    public WebElement PageTitle_BungiiAccepted()
    {
        return findElement("//android.widget.TextView[@text='BUNGII ACCEPTED']", LocatorType.XPath);
    }

    public WebElement Label_BungiiAccepted()
    {
        return findElement("//android.widget.LinearLayout[@id='com.bungii.customer:id/searching_view_bungii_accepted_container']/android.widget.TextView[@instance='1']", LocatorType.XPath);
    }
    public WebElement Label_DriverEnRoute()
    {
        return findElement("com.bungii.customer:id/bungii_accepted_enroute", LocatorType.Id);
    }

    public WebElement Image_Driver()
    {
        return findElement("com.bungii.customer:id/bungii_accepted_driver_image", LocatorType.Id);
    }
    public WebElement DriverRatingBar()
    {
        return findElement("com.bungii.customer:id/rating_bar", LocatorType.Id);
    }
    public WebElement Label_DriverName()
    {
        return findElement("com.bungii.customer:id/bungii_accepted_driver_name", LocatorType.Id);
    }
    public WebElement Button_OK(boolean...ignoreException)
    {
        return findElement("com.bungii.customer:id/bungii_accepted_button_ok", LocatorType.Id,ignoreException);
    }

    //STACK
    public WebElement Text_StackInfo() { return findElement("com.bungii.customer:id/bungii_driver_accepted_stack_info", LocatorType.Id); }
    public WebElement Text_HeaderTitle() { return findElement("com.bungii.customer:id/toolbar_title", LocatorType.Id); }
    public WebElement Image_RattingBar() { return findElement("com.bungii.customer:id/rating_bar", LocatorType.Id); }
    public WebElement Text_BungiiAcceped() { return findElement("//*[@text='Your Bungii has been accepted!']", LocatorType.XPath); }
    public WebElement Textlabel_DriverNearby() { return findElement("com.bungii.customer:id/bungii_stacked_driver_name", LocatorType.Id); }
    public WebElement Textlabel_StackSubtitle() { return findElement("com.bungii.customer:id/bungii_stacked_driver_subtitle", LocatorType.Id); }
    public WebElement Textlabel_ProjectedTimeValue() { return findElement("com.bungii.customer:id/bungii_stacked_eta", LocatorType.Id); }
    public WebElement Textlabel_ProjectedTime() { return findElement("//*[@text='Projected Driver Arrival:']", LocatorType.XPath); }
    public WebElement Button_CancelBungii() { return findElement("com.bungii.customer:id/searching_view_stack_bungii_container_btn_cancel", LocatorType.Id); }
    public WebElement Text_StackConfirmation() { return findElement("com.bungii.customer:id/custom_alert_hyperlink_tv_message", LocatorType.Id); }
    public WebElement Alert_CancelBungii() { return findElement("com.bungii.customer:id/custom_alert_hyperlink_tv_cancel_bungii", LocatorType.Id); }
    public WebElement Alert_ContactSupport() { return findElement("com.bungii.customer:id/custom_alert_hyperlink_tv_contact_support", LocatorType.Id); }
    public WebElement Alert_Dismiss() { return findElement("com.bungii.customer:id/custom_alert_hyperlink_tv_dismiss", LocatorType.Id); }




}
