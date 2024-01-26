package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_LogviewPage extends PageBase {

    public WebElement Dropdown_Database () { return findElement("drpdbEnvironment",PageBase.LocatorType.Id);}

    public WebElement Textarea_Query () { return findElement("txtQuery",PageBase.LocatorType.Id);}

    public WebElement Button_Execute () { return findElement("btnExecuteQuery",PageBase.LocatorType.Id);}

    public WebElement Button_Reset () { return findElement("btnClearQuery",PageBase.LocatorType.Id);}

    public WebElement Link_DownloadQueryResultWithComma () { return findElement("lnkDownloadQueryResultWithComma",PageBase.LocatorType.Id);}

    public WebElement Link_DownloadQueryResultWithPipe () { return findElement("lnkDownloadQueryResultWithPipe",PageBase.LocatorType.Id);}

}
