package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class InvitePage extends PageBase {

    //------Invite Page Elements-----------------------------------------------------------------
    public WebElement Header_Invite() { return findElement("//android.widget.TextView[@text='INVITE']", LocatorType.XPath); }

    public WebElement Invite_Logo() { return findElement("com.bungii.customer:id/invite_referral_imageview", LocatorType.Id); }

    public WebElement Invite_Title() { return findElement("com.bungii.customer:id/invite_referral_title", LocatorType.Id); }

    public WebElement Invite_Text() { return findElement("com.bungii.customer:id/invite_referral_description", LocatorType.Id); }

    public WebElement Invite_Code() { return findElement("com.bungii.customer:id/invite_referral_code_textview", LocatorType.Id); }

    public WebElement Button_Share() { return findElement("com.bungii.customer:id/invite_referral_share_button", LocatorType.Id); }

    //------Share to---------------------------------------------------------------------------
    public WebElement Share_Facebook() { return findElement("//android.widget.TextView[@text='Share on Facebook']", LocatorType.XPath); }

    public WebElement Share_Twitter() { return findElement("//android.widget.TextView[@text='Share on Twitter']", LocatorType.XPath); }

    public WebElement Share_Email() { return findElement("//android.widget.TextView[@text='Share by Email']", LocatorType.XPath); }

    public WebElement Share_TextMessage() { return findElement("//android.widget.TextView[@text='Share by Text Message']", LocatorType.XPath); }

    //------Share - Facebook App---------------------------------------------------------------
    public WebElement FBApp_Title() { return findElement("com.facebook.katana:id/title_view", LocatorType.Id); }

   // public WebElement FBApp_StatusText() { return findElement("com.facebook.katana:id/status_text", LocatorType.Id); }
   public WebElement FBApp_StatusText() { return findElement("//android.widget.EditText[@resource-id='com.facebook.katana:id/(name removed)']", LocatorType.XPath); }

    public WebElement FBApp_PreviewText() { return findElement("com.facebook.katana:id/link_attachment_context_text", LocatorType.Id); }

  //  public WebElement FBApp_PostLink() { return findElement("com.facebook.katana:id/button_share", LocatorType.Id); }

    public WebElement FBApp_PostLink(boolean ...ignoreException) { return findElement("//android.widget.Button[@content-desc=\"POST\"]", LocatorType.XPath,ignoreException); }
    public WebElement FBApp_Policy(boolean ...ignoreException) { return findElement("//android.widget.TextView[@text=\"By clicking continue, you agree to our Terms and Privacy Policy. We use a service that's pre-installed on your device to auto-update apps. You can turn off the service at any time. Learn more.\"]", LocatorType.XPath,ignoreException); }

    //  public WebElement FBApp_PostLink(boolean ...ignoreException) { return findElement("//android.widget.ImageView[@content-desc=\"Set Album\"]/following-sibling::android.widget.TextView", LocatorType.XPath,ignoreException); }


    //------Share - Samsung Msg App-------------------------------------------------------------
    public WebElement Samsung_TextMsg_TextField() { return findElement("com.android.mms:id/edit_text_bottom", LocatorType.Id); }

    public WebElement TextMsg_TextField(boolean ...ignoreException) { return findElement("com.android.mms:id/embedded_text_editor", LocatorType.Id,ignoreException); }

    //------Share - Gmail App-------------------------------------------------------------------
    public WebElement Gmail_Referral_Subject() { return findElement("com.google.android.gm:id/subject", LocatorType.Id); }
    public WebElement Gmail_SkipTutorial(boolean ...ignoreException) { return findElement("com.google.android.gm:id/welcome_tour_skip", LocatorType.Id,ignoreException); }
    public WebElement Gmail_Referral_Body_other(boolean ...ignoreException) { return findElement("//*[@resource-id='com.google.android.gm:id/body_wrapper']/descendant::android.view.View", LocatorType.XPath,ignoreException); }
    public WebElement Gmail_Referral_Body() { return findElement("//*[@resource-id='com.google.android.gm:id/body_wrapper']/descendant::android.widget.EditText", LocatorType.XPath); }

    //------Share - Twitter App-----------------------------------------------------------------
    public WebElement Twitter_Referral_Body(boolean ...ignoreException) { return findElement("//*[@resource-id='status']", LocatorType.XPath,ignoreException); }
    public WebElement Twitter_Referral_BodyChrome() { return findElement("android.widget.EditText", LocatorType.ClassName); }

    public WebElement Twitter_SignUP(boolean ...ignoreException) { return findElement("//android.widget.Button[@text='Sign up']", LocatorType.XPath,ignoreException); }
    public WebElement Twitter_Login(boolean ...ignoreException) { return findElements("android.widget.Button", LocatorType.ClassName).get(1); }

    public WebElement Twitter_PhoneNumber() { return findElements("android.widget.EditText", LocatorType.ClassName).get(0); }
    public WebElement Twitter_Password() { return findElements("android.widget.EditText", LocatorType.ClassName).get(1); }
    public WebElement Browser_bar(boolean ...ignoreException) { return findElement("com.android.chrome:id/url_bar", LocatorType.Id,ignoreException); }



    public WebElement Button_Back(){return findElement("//android.widget.ImageButton[contains(@content-desc,\"Navigate up\")]",LocatorType.XPath);}


    //MOTO G4 specific
    public WebElement Text_Receipient(boolean ...ignoreException) { return findElement("com.google.android.apps.messaging:id/recipient_text_view", LocatorType.Id,ignoreException); }
    public WebElement Text_Body(boolean ...ignoreException) { return findElement("com.google.android.apps.messaging:id/compose_message_text", LocatorType.Id,ignoreException); }

    public WebElement Image_Invite(){return findElement("com.bungii.customer:id/menu_invite", LocatorType.Id);}
}
