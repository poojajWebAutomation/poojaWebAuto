package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase {
    public WebElement Text_LoginBar(boolean ...ignoreException){return findElement("//android.view.View[@resource-id='com.bungii.driver:id/toolbarLogin']/android.widget.TextView", LocatorType.XPath,ignoreException);}
    // Phone Number field
    public WebElement TextField_PhoneNumber(boolean ...ignoreException) { return findElement("com.bungii.driver:id/field_phone", LocatorType.Id,ignoreException); }

    //Password field
    public WebElement TextField_Password() { return findElement("com.bungii.driver:id/field_password", LocatorType.Id); }

    //Log In button
    public WebElement Button_Login() { return findElement("com.bungii.driver:id/loginGlobalButton", LocatorType.Id); }
    public WebElement Button_Sure() { return findElement("com.bungii.driver:id/button_location_permission_sure", LocatorType.Id); }
    public WebElement Button_Allow() { return findElement("com.android.packageinstaller:id/permission_allow_button", LocatorType.Id); }
    public WebElement Header_Location(boolean ...ignoreException) { return findElement("com.bungii.driver:id/toolbar_location_permission", LocatorType.Id,ignoreException); }

    //Password field
    public WebElement Text_LoginError() { return findElement("com.bungii.driver:id/textinput_error", LocatorType.Id); }
    public WebElement Text_LoginError2() { return findElements("com.bungii.driver:id/textinput_error", LocatorType.Id).get(1); }
    public WebElement Button_ForgotPassword(boolean ...ignoreException) { return findElement("com.bungii.driver:id/login_button_forgot_password", LocatorType.Id,ignoreException); }
    public WebElement Label_TheUltimateSideHustle() { return findElement("//android.widget.TextView[@index=0]", LocatorType.XPath); }
    public WebElement Text_PendingDriverLoginError() { return findElement("//android.widget.TextView[@resource-id='com.bungii.driver:id/snackbar_text'", LocatorType.XPath); }

    public WebElement Button_StartAnApplicationHere() { return findElement("com.bungii.driver:id/tv_sign_up_info_link", LocatorType.Id); }


}
