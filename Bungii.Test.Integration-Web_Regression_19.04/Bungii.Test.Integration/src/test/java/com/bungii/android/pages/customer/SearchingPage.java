package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SearchingPage extends PageBase {

    //public WebElement Header_DriverNotAvailable(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='DRIVER NOT AVAILABLE']", LocatorType.XPath,ignoreException); }

    public WebElement Header_DriverNotAvailable(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='SET PICKUP TIME']", LocatorType.XPath,ignoreException); }

    public WebElement Link_CancelSearch () { return findElement("com.bungii.customer:id/toolbar_button_cancel", LocatorType.Id); }

    public WebElement Button_CancelConfirm () { return findElement("android:id/button1", LocatorType.Id); }          //By.XPath("//android.widget.Button[@text='YES']")

    public WebElement Button_CloseCancel () { return findElement("android:id/button2", LocatorType.Id); }

    public WebElement PageTitle_Searching () { return findElement("//android.widget.TextView[@text='SEARCHING…']", LocatorType.XPath); }

    public WebElement Loader () { return findElement("com.bungii.customer:id/searching_view_progressbar", LocatorType.Id); }

    public WebElement Header_SetPickupTime () { return findElement("//android.widget.TextView[@text='Set pickup time']", LocatorType.XPath); }

    public WebElement Text_MsgSearching () { return findElement("com.bungii.customer:id/searching_view_subtitle", LocatorType.Id); }

    public WebElement ProgressBar (boolean...ignoreException) { return findElement("com.bungii.customer:id/searching_view_horizontal_progressbar", LocatorType.Id,ignoreException); }

    public WebElement PageTitle_DriverNotAvailable () { return findElement("//android.widget.TextView[@text='DRIVER NOT AVAILABLE']", LocatorType.XPath); }
}
