package com.bungii.android.pages.otherApps;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class FacebookPage extends PageBase {
    public WebElement Text_Input() { return findElement("//android.widget.EditText[@content-desc=\"Username\"]", LocatorType.XPath);
    }
}
