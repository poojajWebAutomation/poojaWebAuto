package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class WantDollar5Page extends PageBase {

    public WebElement Button_Take5 (boolean ... ignoreException) { return findElement("com.bungii.customer:id/postTripShareAcceptButton", LocatorType.Id,ignoreException); }

    public WebElement Button_NoFreeMoney (boolean ... ignoreException) { return findElement("com.bungii.customer:id/postTripShareNoFreeMoney", LocatorType.Id,ignoreException); }
    public WebElement Titlebar_WantDollar5Page (boolean ... ignoreException) { return findElement("com.bungii.customer:id/title_post_trip", LocatorType.Id,ignoreException); }
}
