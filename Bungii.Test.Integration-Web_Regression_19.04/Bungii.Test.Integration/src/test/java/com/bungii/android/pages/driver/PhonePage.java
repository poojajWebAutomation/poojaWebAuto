package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class PhonePage extends PageBase {
    public WebElement Container_Notification(boolean... ignoreException) {
        return findElement("com.android.systemui:id/notification_container_parent", LocatorType.Id, ignoreException);
    }

}
