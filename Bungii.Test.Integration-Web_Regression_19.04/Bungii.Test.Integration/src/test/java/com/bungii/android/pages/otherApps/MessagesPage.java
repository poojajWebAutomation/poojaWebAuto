package com.bungii.android.pages.otherApps;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class MessagesPage extends PageBase {
    public WebElement Button_Cancel() {
        return findElement("Cancel", LocatorType.AccessibilityId);
    }
    public WebElement Text_ToField() {
        return findElement("com.google.android.apps.messaging:id/tombstone_message", LocatorType.Id);
    }
    public WebElement Button_RemindMeLater(boolean... ignoreException){return findElement("Remind Me Later",LocatorType.AccessibilityId,ignoreException);}
}
