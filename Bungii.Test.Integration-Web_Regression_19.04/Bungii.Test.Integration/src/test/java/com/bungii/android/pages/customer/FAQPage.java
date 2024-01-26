package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class FAQPage extends PageBase {

    //------Title-------------------------------------------------------------------------------
    public WebElement Header_FAQPage(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='FAQ']", LocatorType.XPath,ignoreException); }

    //------Page Elements------------------------------------------------------------------------
    public WebElement FAQ_BungiiLogo() { return findElement("com.bungii.customer:id/promo_code_label", LocatorType.Id); }
    public WebElement FAQ_Loading(boolean...ignoreException) { return findElement("com.bungii.customer:id/faq_progressbar", LocatorType.Id,ignoreException); }

    public WebElement FAQ_Image() { return findElement("", LocatorType.Id); }

    public WebElement FAQ_TitleImage() { return findElement("", LocatorType.Id); }

    public WebElement FAQ_AppFAQTitle() { return findElement("", LocatorType.Id); }

    // public WebElement FAQ_FirstQuestion() { return findElement("//android.view.View[3][@instance='33']", LocatorType.XPath); }
    public WebElement FAQ_FirstQuestion() { return findElement("//android.view.View[@content-desc=\"What is your approach to COVID-19?\"]/android.widget.TextView", LocatorType.XPath); }
    public WebElement FAQ_FirstQuestion_open() { return findElement("//android.view.View[3][@instance='35']", LocatorType.XPath); }
  //  public WebElement FAQ_FirstAnswer(boolean...ignoreException) { return findElement("//android.view.View[contains(@text,'So what exactly is Bungii?')]/following-sibling::android.view.View", LocatorType.XPath,ignoreException); }
   // public WebElement FAQ_FirstAnswer(boolean...ignoreException) { return findElement("//android.view.View[@resource-id='elementor-tab-content-1261']", LocatorType.XPath,ignoreException); }
    public WebElement FAQ_FirstAnswer(boolean...ignoreException) { return findElement("//android.widget.TabWidget/android.view.View[2]/android.widget.ListView/android.view.View/android.widget.TextView", LocatorType.XPath,ignoreException); }


    public WebElement FAQ_LastQuestion(boolean...ignoreException) { return findElement("//android.view.View[contains(@text,\"What if there's an issue with my Bungii and/or I have other questions?\")]", LocatorType.XPath,ignoreException); }
  //  public WebElement FAQ_LastQuestion(boolean...ignoreException) { return findElement("-h2-data-preserve-html-node-true-style-font-size-20px-what-if-there-s-an-issue-with-my-bungii-and-or-i-have-other-questions-h-data-preserve-html-node-true-style-float-right-h-hr-data-preserve-html-node-true-style-width-100-h2-", LocatorType.Id,ignoreException); }

    public WebElement FAQ_LastAnswer() { return findElement("//android.view.View[@content-desc='Please contact text support at (913) 353-6683 or email us at support@bungii.com immediately.' and @instance='54']", LocatorType.XPath); }

    public WebElement FAQ_TwitterLogo(boolean...ignoreException) { return findElement("//android.view.View[@resource-id=\"block-yui_3_17_2_1_1410291973006_4664\"]/android.view.View/android.view.View[1]", LocatorType.XPath,ignoreException); }

    public WebElement FAQ_InstagramLogo (boolean...ignoreException) { return findElement("//android.view.View[@resource-id=\"block-yui_3_17_2_1_1410291973006_4664\"]/android.view.View/android.view.View[last()-1]", LocatorType.XPath,ignoreException); }

    public WebElement FAQ_FBLogo (boolean...ignoreException) { return findElement("//android.view.View[@resource-id=\"block-yui_3_17_2_1_1410291973006_4664\"]/android.view.View/android.view.View[last()]", LocatorType.XPath,ignoreException); }
}
