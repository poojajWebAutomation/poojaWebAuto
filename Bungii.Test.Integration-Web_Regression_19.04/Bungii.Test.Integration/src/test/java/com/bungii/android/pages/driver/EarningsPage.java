package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class EarningsPage extends PageBase {
    public WebElement Header_DriverInfo() { return findElement("//android.view.View[@text='DRIVER INFO']", LocatorType.XPath); }
    public WebElement Text_MilesDriven() { return findElement("//android.view.View[contains(@text,'MILES') and contains(@text,'DRIVEN')]", LocatorType.XPath); }
    public WebElement Text_MilesDrivenValue() { return findElement("//android.view.View[contains(@text,'MILES') and contains(@text,'DRIVEN')]/following-sibling::android.view.View", LocatorType.XPath); }


    public WebElement Text_TotalEarning() { return findElement("//android.view.View[contains(@text,'TOTAL') and contains(@text,'EARNINGS')]", LocatorType.XPath); }
    public WebElement Text_TotalEarningValue() { return findElement("//android.view.View[contains(@text,'TOTAL') and contains(@text,'EARNINGS')]/following-sibling::android.view.View", LocatorType.XPath); }
    public WebElement Text_Earnings() { return findElement("com.bungii.driver:id/appCompatTextView6", LocatorType.Id); }



    public WebElement Text_HourWorked() { return findElement("//android.view.View[contains(@text,'HOURS ') and contains(@text,'WORKED')]", LocatorType.XPath); }
    public WebElement Text_HourWorkedValue() { return findElement("//android.view.View[contains(@text,'HOURS ') and contains(@text,'WORKED')]/following-sibling::android.view.View", LocatorType.XPath); }



    public WebElement Text_TripCompleted() { return findElement("//android.view.View[contains(@text,'TRIPS') and contains(@text,'COMPLETED')]", LocatorType.XPath); }
    public WebElement Text_TripCompletedValue() { return findElement("//android.view.View[contains(@text,'TRIPS') and contains(@text,'COMPLETED')]/following-sibling::android.view.View", LocatorType.XPath); }


    public WebElement Text_TotalTips() { return findElement("//android.view.View[contains(@text,'TOTAL') and contains(@text,'TIPS')]", LocatorType.XPath); }
    public WebElement Text_TotalEarningTips() { return findElement("//android.view.View[contains(@text,'TOTAL') and contains(@text,'TIPS')]/following-sibling::android.view.View", LocatorType.XPath); }

    public WebElement Link_ItemisedEarning() { return findElement("//android.view.View[@content-desc='Click here to view itemized earnings Itemized Earnings']", LocatorType.XPath); }


    public WebElement Link_ItemisedEarnings() { return findElement("com.bungii.driver:id/activity_earnings_itemized_earnings_btn", LocatorType.Id); }

    public WebElement Button_ItemizedEarnings() { return findElement("com.bungii.driver:id/activity_earnings_itemized_earnings_btn", LocatorType.Id); }

    //public WebElement Text_HistoryDataTotalEarnings() { return findElement("//*[@resource-id='tblDriverTrips']/android.view.View[6]", LocatorType.XPath);}
    public WebElement Text_HistoryDataTotalEarnings() { return findElement("com.bungii.driver:id/appCompatTextView21", LocatorType.Id);}

    public WebElement Button_NavigateUp(){return findElement("//android.widget.ImageButton[contains(@content-desc,\"Navigate up\")]",LocatorType.XPath);}

    public WebElement Button_BranchWallet() {
        return findElement("com.bungii.driver:id/tv_branch_label", LocatorType.Id);
    }
    public WebElement Screen_DefaultBrowser() {
        return findElement("//android.widget.TextView[@text='Sign in to find the latest Android apps, games, movies, music, & more']", LocatorType.XPath);
    }
    public WebElement Button_PaymentSetting() {
        return findElement("com.bungii.driver:id/tv_disbursement_type", LocatorType.Id);
    }
    public WebElement Text_PaymentSetting() { return findElement("com.bungii.driver:id/tv_payment_settings_label", LocatorType.Id);}
    public WebElement Text_PaymentSettingInfo() { return findElement("com.bungii.driver:id/tv_payment_settings_info", LocatorType.Id);}
    public WebElement Option_2xWeek() { return findElement("//android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.view.ViewGroup", LocatorType.XPath);}
    public WebElement Option_SameDay() { return findElement("//android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/android.view.ViewGroup", LocatorType.XPath);}
    public WebElement Button_Close() { return findElement("com.bungii.driver:id/tv_close", LocatorType.Id);}
    public WebElement Button_Confirm() { return findElement("com.bungii.driver:id/tv_confirm", LocatorType.Id);}
    public WebElement Checkbox_TwiceWeek() { return findElement("//androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.view.ViewGroup/android.widget.ImageView", LocatorType.XPath);}
    public WebElement Checkbox_SameDay() { return findElement("//androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/android.view.ViewGroup/android.widget.ImageView", LocatorType.XPath);}
    public WebElement Dropdown_Year() { return findElement("com.bungii.driver:id/earnings_spinner_tv", LocatorType.Id);}

    public WebElement Dropdown_Firstvalue() { return findElement("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]", LocatorType.XPath);}
    public WebElement Dropdown_Secondvalue() { return findElement("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]", LocatorType.XPath);}

    public WebElement Dropdown_Thirdvalue() { return findElement("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]", LocatorType.XPath);}

    public WebElement Icon_IOnCompletedDelivery() { return findElement("com.bungii.driver:id/iv_delivery_earnings_info", LocatorType.Id);}

}
