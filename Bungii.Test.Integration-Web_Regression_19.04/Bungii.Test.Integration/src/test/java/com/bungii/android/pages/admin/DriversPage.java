package com.bungii.android.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;


public class DriversPage extends PageBase {

    public void waitForLoadingToDisappear(){
        waitForLoadingToDisappear();
    }
    public WebElement Text_SearchCriteria(){return  findElement("SearchCriteria",LocatorType.Id);}

    public WebElement Button_Search(){return  findElement("btnSearch",LocatorType.Id);}

    public WebElement Text_DriverRatting() {
        return findElement("//tbody[@id='NewApplicantsTBody']/tr[1]/td[7]", LocatorType.XPath);
    }
    public WebElement Button_DriverProfileLink() {
        return findElement("//tbody[@id='NewApplicantsTBody']/tr[1]/td/a/img[@title='Profile']/parent::a", LocatorType.XPath);
    }

    public WebElement Text_DriverRattingProfile() {
        return findElement("//td[text()='Rating']/following-sibling::td/strong", LocatorType.XPath);
    }
}
