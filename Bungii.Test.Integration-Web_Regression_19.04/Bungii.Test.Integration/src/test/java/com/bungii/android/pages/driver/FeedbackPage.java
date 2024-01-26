package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class FeedbackPage  extends PageBase {
    public WebElement Header_Feedback() { return findElement("com.bungii.driver:id/feedback_text_view_title", LocatorType.Id); }
    public WebElement Text_Feedback() { return findElement("com.bungii.driver:id/feedback_edit_text", LocatorType.Id); }
    public WebElement Button_Send() { return findElement("com.bungii.driver:id/feedback_send_button", LocatorType.Id); }
    public WebElement Image_BungiiLogo() { return findElement("android.widget.ImageView", LocatorType.Id); }

}
