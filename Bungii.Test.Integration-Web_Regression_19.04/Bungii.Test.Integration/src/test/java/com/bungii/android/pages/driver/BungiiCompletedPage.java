package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiCompletedPage extends PageBase {

    public WebElement Title_Status() { return findElement("com.bungii.driver:id/pickup_summary_toolbar_title", LocatorType.Id); }

    public WebElement Text_TotalTimeLabel(){return  findElement("//*[@text='Total Time']",LocatorType.XPath);}

    public WebElement Text_TotalTime() { return findElement("com.bungii.driver:id/pickup_summary_text_total_time", LocatorType.Id); }

    public WebElement Text_TotalDistanceLabel(){return  findElement("//*[@text='Total Distance']",LocatorType.XPath);}

    public WebElement Text_TotalDistance() { return findElement("com.bungii.driver:id/pickup_summary_text_total_distance", LocatorType.Id); }

    //public WebElement Text_TotalEarningsLabel(){return  findElement("//*[@text='Total Earnings']",LocatorType.XPath);}
    public WebElement Text_TotalEarningsLabel(){return  findElement("//*[@resource-id='com.bungii.driver:id/activity_pickup_summary_rl_top']/android.widget.TextView",LocatorType.XPath);}
    public WebElement Text_TotalEarnings() { return findElement("com.bungii.driver:id/appCompatTextView43", LocatorType.Id); }

    //public WebElement Button_OnToTheNext(boolean ...ignoreException) { return findElement("com.bungii.driver:id/pickup_summary_button_close_summary", LocatorType.Id,ignoreException); }
    public WebElement Button_OnToTheNext(boolean ...ignoreException) { return findElement("com.bungii.driver:id/pickup_summary_button_next_bungii", LocatorType.Id,ignoreException); }

    public WebElement Text_Label(){return  findElement("//*[@text='Cha-Ching!']",LocatorType.XPath);}
    public WebElement Image_Dollar(){return  findElement("//*[@text=\"Cha-Ching!\"]/preceding-sibling::android.widget.ImageView",LocatorType.XPath);}

    public WebElement Text_BungiiStatus(){return findElement("//android.widget.TextView[@text='Bungii completed']",LocatorType.XPath);}
    public WebElement Button_NextBungii(boolean ...ignoreException) { return findElement("com.bungii.driver:id/appCompatButton4", LocatorType.Id,ignoreException); }

    //Rate Customer
    public WebElement Icon_RatingFriendly(){return findElement("//*[@resource-id='com.bungii.driver:id/view_rating_option_tv_option' and contains(@text ,'Friendly')]",LocatorType.XPath);}
    public WebElement Icon_RatingRespectful(){return findElement("//*[@resource-id='com.bungii.driver:id/view_rating_option_tv_option' and contains(@text ,'Respectful')]",LocatorType.XPath);}
    public WebElement Icon_RatingClearExpectations(){return findElement("//*[@resource-id='com.bungii.driver:id/view_rating_option_tv_option' and contains(@text ,'Clear Expectations')]",LocatorType.XPath);}
    public WebElement Icon_RatingSmiled(){return findElement("//*[@resource-id='com.bungii.driver:id/view_rating_option_tv_option' and contains(@text ,'Smiled')]",LocatorType.XPath);}
    public WebElement Icon_RatingAvailable(){return findElement("//*[@resource-id='com.bungii.driver:id/view_rating_option_tv_option' and contains(@text ,'Available')]",LocatorType.XPath);}
    public WebElement Icon_RatingGrateful(){return findElement("//*[@resource-id='com.bungii.driver:id/view_rating_option_tv_option' and contains(@text ,'Grateful')]",LocatorType.XPath);}

    public WebElement RatingBar(){return findElement("//*[@resource-id='com.bungii.driver:id/rate_participants_rating_bar_customer']",LocatorType.XPath);}

    public WebElement Button_SubmitRating(){return findElement("com.bungii.driver:id/appCompatButton3",LocatorType.Id);}

    //Stay online/offline pop-up
    public WebElement Notification_DriverStatus(boolean...ignoreException){return findElement("com.bungii.driver:id/appCompatTextView21",LocatorType.Id,ignoreException);}
    public WebElement Text_NotificationDriverStatus(){return findElement("com.bungii.driver:id/appCompatTextView22",LocatorType.Id);}
    public WebElement Button_StayOnline(){return findElement("com.bungii.driver:id/btnStayOnline_alert_dialog_controller",LocatorType.Id);}
    public WebElement Button_GoOffline(){return findElement("com.bungii.driver:id/btnOffline_alert_dialog_controller",LocatorType.Id);}
    public WebElement Slider_Online(){return findElement("com.bungii.driver:id/tvThumbStateOnline",LocatorType.Id);}
    public WebElement Slider_Offline(){return findElement("com.bungii.driver:id/tvThumbStateOffline",LocatorType.Id);}

    public WebElement Text_ChooseRating() {return findElement("com.bungii.driver:id/appCompatTextView64",LocatorType.Id);}
    public WebElement Text_DriverExperience() {return findElement("com.bungii.driver:id/appCompatTextView62",LocatorType.Id);}
    public WebElement Textbox_AdditionalFeedback() {return findElement("com.bungii.driver:id/activity_rate_delivery_participants_edit_text_additional_comments",LocatorType.Id);}

}
