package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TripAlertSettingsPage extends PageBase {
    //Scheduled Trip Alerts notify you when there are scheduled Bungiis available in you area. Here you can select the times you'd like to receive scheduled deliveries. NOTE: If your schedule is fairly open, we recommend leaving this setting 'ON' for all days.
    //SMS Alerts notify you when an unfulfilled trip is pending in your area. Here you can select the times you'd like to be notified via text message.
    public WebElement TripAlertHeader() { return findElement("com.bungii.driver:id/text_settings_heading", LocatorType.Id); }
   //checked	=false/true
    public WebElement Radio_TripAlert() { return findElement("com.bungii.driver:id/text_settings_radio_trip_alerts", LocatorType.Id); }
    public WebElement Radio_SmsAlert() { return findElement("com.bungii.driver:id/text_settings_radio_sms_alerts", LocatorType.Id); }

    public WebElement Tab_TripAlerts() { return findElement("//android.widget.RadioButton[@text='Delivery Alerts']", LocatorType.XPath);}

    public WebElement Tab_SMSAlerts() { return findElement("//android.widget.RadioButton[@text='SMS Alerts']", LocatorType.XPath);}

    public WebElement Text_TripAndSMSAlertsText(){return findElement("//*[@resource-id='com.bungii.driver:id/text_settings_heading']", LocatorType.XPath);}

    public List<WebElement> Text_TripAlertsTime () { return findElements("com.bungii.driver:id/text_settings_row_text_time", LocatorType.Id); }
    public List<WebElement> Text_TripAlertsDay () { return findElements("com.bungii.driver:id/text_settings_row_text_day", LocatorType.Id); }

    public WebElement Text_SMSAlertsTime(){ return findElement("//[@resource-id='com.bungii.driver:id/text_settings_row_text_time']", LocatorType.XPath);}

    public WebElement Image_TimeSettingsArrow() { return findElement("//*[@resource-id='com.bungii.driver:id/text_settings_iv_row_arrow']", LocatorType.XPath);}

    public WebElement Text_TimeSettingsFromTime() { return findElement("//*[@resource-id='com.bungii.driver:id/edit_texting_time_text_from_value']", LocatorType.XPath);}

    public WebElement Button_SaveTime() { return findElement("//*[@resource-id='com.bungii.driver:id/menu_save']", LocatorType.XPath);}

    public WebElement TimePicker_ChangeTime(){return findElement("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[contains(@content-desc,\"8\")]", LocatorType.XPath);}

    public WebElement TimePicker_OK(){return findElement("//*[@resource-id='android:id/button1']", LocatorType.XPath);}
    public WebElement Button_UpdateBungii(){return findElement("//button[contains(text(),'UPDATE BUNGII')]", LocatorType.XPath);}


    public WebElement Text_Sunday() {return findElement("//*[@resource-id='com.bungii.driver:id/text_settings_row_text_day' and @text='Sunday']", LocatorType.XPath);}

    public WebElement Button_OKPopupOnAcceptedDelivery() {return findElement("com.bungii.driver:id/tv_submit", LocatorType.Id);}

}
