package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_BankDetailsPage extends PageBase {
    //Bank Details - Blank field validation
    public WebElement Err_BankDetails_AllBlank() { return findElement("summary4", LocatorType.Id); }

    //Bank Details - Routing number
    public WebElement Err_RoutingNumber() { return findElement("BankDetails_BankRoutingNumber-error", LocatorType.Id); }

    //Bank Details - Routing number - Error
    public WebElement TextBox_RoutingNumber() { return findElement("BankDetails_BankRoutingNumber", LocatorType.Id); }

    //Bank Details - Bank Account Number
    public WebElement TextBox_BankAccNumber() { return findElement("BankDetails_BankAccountNumber", LocatorType.Id); }

    //Bank Details - Bank Account Number
    public WebElement Err_BankAccNumber() { return findElement("BankDetails_BankAccountNumber-error", LocatorType.Id); }

    //Bank Details - Next Button
    public WebElement Button_BankNext() { return findElement("btnBankInfo", LocatorType.Id); }
}
