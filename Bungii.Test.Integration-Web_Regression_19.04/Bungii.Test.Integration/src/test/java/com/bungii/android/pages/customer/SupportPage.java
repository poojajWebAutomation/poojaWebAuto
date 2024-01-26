package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SupportPage extends PageBase {

    public WebElement Header_SupportPage(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='SUPPORT']", LocatorType.XPath,ignoreException); }

    public WebElement Text_Title() { return findElement("com.bungii.customer:id/feedback_text_view_title", LocatorType.Id); }
    public WebElement Text_SubTitle() { return findElement("com.bungii.customer:id/feeback_subtitle", LocatorType.Id); }



  //  public WebElement TextField() { return findElement("com.bungii.customer:id/feedback_edit_text", LocatorType.Id); }

    public WebElement TextField() { return findElement("//*[@resource-id='com.bungii.customer:id/feedback_edit_text']", LocatorType.XPath); }

    public WebElement Button_Send() { return findElement("com.bungii.customer:id/feedback_send_button", LocatorType.Id); }

    public WebElement Error_Blank() { return findElement("com.bungii.customer:id/textinput_error", LocatorType.Id); }

    public WebElement Snackbar() { return findElement("com.bungii.customer:id/snackbar_text", LocatorType.Id); }


}
