package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LocationPage extends PageBase {
    public WebElement Header_Location(boolean ...ignoreException) {        return findElement("//android.view.ViewGroup[@resource-id='com.bungii.driver:id/toolbar_location_permission']/android.widget.TextView", LocatorType.XPath,ignoreException);};
    //We use your location to find deliveries closest to you. We can’t send you delivery requests without this.
    public WebElement Text_Info(boolean ...ignoreException) {        return findElement("com.bungii.driver:id/textView6", LocatorType.Id,ignoreException);}
    public WebElement Image_Compass(boolean ...ignoreException) {        return findElement("com.bungii.driver:id/imageView2", LocatorType.Id,ignoreException);}
    public WebElement Button_Sure(boolean ...ignoreException) {        return findElement("com.bungii.driver:id/button_location_permission_sure", LocatorType.Id,ignoreException);}
    //Allow Bungii to access this device's location?
    public WebElement Alert_Text(boolean ...ignoreException) {        return findElement("com.android.packageinstaller:id/permission_message", LocatorType.Id,ignoreException);}
    public WebElement Button_Allow(boolean ...ignoreException) {        return findElement("com.android.packageinstaller:id/permission_allow_button", LocatorType.Id,ignoreException);}
    public WebElement Button_Deny(boolean ...ignoreException) {        return findElement("com.android.packageinstaller:id/permission_deny_button", LocatorType.Id,ignoreException);}
    public WebElement Subheader_FAQPage(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='Where to?']", LocatorType.XPath,ignoreException); }

}
