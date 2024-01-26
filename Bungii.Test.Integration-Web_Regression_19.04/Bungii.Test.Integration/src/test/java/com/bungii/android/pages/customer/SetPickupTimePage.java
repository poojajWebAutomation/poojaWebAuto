package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SetPickupTimePage extends PageBase {

    public WebElement Link_CancelOnDemandBungii() {return findElement("com.bungii.customer:id/toolbar_button_cancel", LocatorType.Id);}

    public WebElement Text_SetPickupTimeTitle(){return findElement("com.bungii.customer:id/toolbar_title", LocatorType.Id);}

    public WebElement Text_DriversBusyMessage(){return findElement("com.bungii.customer:id/searching_view_driver_unavailable_subtitle", LocatorType.Id);}

    public WebElement Icon_PickupTimeInfo(){return findElement("com.bungii.customer:id/activity_schedule_bungii_iv_info", LocatorType.Id);}

    public WebElement Icon_PickupTimeInfoMessage(){return findElement("android:id/message", LocatorType.Id);}

    public WebElement Button_Ok(){return findElement("android:id/button1", LocatorType.Id);}

    public WebElement Text_DateTime(){return findElement("com.bungii.customer:id/date_time_picker_textview_selectedtime", LocatorType.Id);}

    public WebElement Button_ScheduleBungii(){return findElement("com.bungii.customer:id/fragment_pickup_searching_driver_btn_schedule", LocatorType.Id);}

    public WebElement Text_MessageOnTimeSelector(){return findElement("com.bungii.customer:id/layout_timepicker_tv_customerinfo", LocatorType.Id);}

    public WebElement Text_SelectHours(){return findElements("//*[@resource-id='android:id/numberpicker_input']", LocatorType.XPath).get(0);}

    public WebElement Text_SelectMinutes(){return findElements("//*[@resource-id='android:id/numberpicker_input']", LocatorType.XPath).get(1);}

    public WebElement Button_TimePickerOK(){return findElement("com.bungii.customer:id/timepicker_okay", LocatorType.Id);}

    public WebElement RadioButton_INeededItRightAway() {return findElements("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_recycler_view']/android.widget.LinearLayout", LocatorType.XPath).get(0);}

    public WebElement RadioButton_IFoundAnAlternative() {return findElements("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_recycler_view']/android.widget.LinearLayout", LocatorType.XPath).get(1);}

    public WebElement RadioButton_IDontNeedItAnymore() {return findElements("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_recycler_view']/android.widget.LinearLayout", LocatorType.XPath).get(2);}

    public WebElement RadioButton_Others() {return findElements("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_recycler_view']/android.widget.LinearLayout", LocatorType.XPath).get(3);}

    public WebElement Button_EnterCancellationReason(){return findElement("com.bungii.customer:id/alert_cancellation_reason_tv_list_submit", LocatorType.Id);}

    public WebElement Button_GoBack(){return findElement("com.bungii.customer:id/alert_cancellation_reason_tv_list_try_again", LocatorType.Id);}

    public WebElement Page_TitleSuccess(){return findElement("//*[@resource-id='com.bungii.customer:id/action_bar']/android.widget.TextView", LocatorType.XPath);}

    public WebElement Popup_CancelReasonTitle() {return findElement("//android.widget.TextView[contains(@text,\"What's your reason\")]", LocatorType.XPath);}
    public WebElement Text_MonthPicker(){return findElement("android:id/date_picker_month", LocatorType.Id);}
    public WebElement Text_DayPicker(){return findElement("android:id/date_picker_day", LocatorType.Id);}
    public WebElement Text_YearPicker(){return findElement("android:id/date_picker_year", LocatorType.Id);}
    public WebElement Text_BungiiTime() { return findElement("com.bungii.customer:id/item_my_bungii_tv_date", LocatorType.Id);}

    public WebElement Text_FirstCancellationReason() {return findElement("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_tv' and @text='I needed it right away.']", LocatorType.XPath);}
    public WebElement Text_SecondCancellationReason() {return findElement("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_tv' and @text='I found an alternative.']", LocatorType.XPath);}
    public WebElement Text_ThirdCancellationReason() {return findElement("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_tv' and contains(@text,'need it anymore.')]", LocatorType.XPath);}
    public WebElement Text_FourthCancellationReason() {return findElement("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_tv' and @text='Other...']", LocatorType.XPath);}
    public WebElement TextBox_CancellationReason() {return findElement("com.bungii.customer:id/alert_cancellation_reason_et", LocatorType.Id);}
    public WebElement Button_SubmitCancellationReason(){return findElement("com.bungii.customer:id/alert_cancellation_reason_et_submit", LocatorType.Id);}
}
