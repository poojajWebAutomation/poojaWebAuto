package com.bungii.android.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class CustomersPage extends PageBase {

    public WebElement TextBox_SearchCustomer() {return findElement("SearchCriteria", LocatorType.Id);}
}
