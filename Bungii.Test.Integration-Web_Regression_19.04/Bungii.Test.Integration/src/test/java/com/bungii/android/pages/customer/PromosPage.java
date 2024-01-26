package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PromosPage extends PageBase {
    public WebElement Header_SavePage (boolean...ignoreException) { return findElement("//android.widget.TextView[@text='PROMOS']", LocatorType.XPath,ignoreException); }   //places_autocomplete_pickup_location is actual id

    public WebElement Textfield_PromoCode () { return findElement("com.bungii.customer:id/field_promo_code", LocatorType.Id); }

    public WebElement Button_Add () { return findElement("//TextInputLayout[@resource-id='com.bungii.customer:id/field_promo_code_layout']/following-sibling::android.widget.Button", LocatorType.XPath); }


    public WebElement Button_AddPromoPage () { return findElement("android.widget.Button", LocatorType.ClassName); }

    public WebElement Button_GetMoreMoney () { return findElement("com.bungii.customer:id/btn_get_more_money", LocatorType.Id); }

    public WebElement SaveMoney_PromoCode1 () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label'][1]", LocatorType.XPath); }

    public WebElement SaveMoney_PromoCode2 () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label'][2]", LocatorType.XPath); }

    public WebElement SaveMoney_PromoCode3 () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label'][3]", LocatorType.XPath); }

    public WebElement FirstTime_PromoCode_SelectedByDefault () { return findElement("//android.widget.RelativeLayout/android.widget.RelativeLayout[@resource-id='com.bungii.customer:id/promo_code_rl_first']/following-sibling::android.widget.ImageView[@resource-id='com.bungii.customer:id/promo_code_row_imageview_check']", LocatorType.XPath);}
    public List<WebElement> List_PromoCode(){return  findElements("com.bungii.customer:id/promo_code_label",LocatorType.Id);}
    public WebElement Snackbar () { return findElement("com.bungii.customer:id/snackbar_text", LocatorType.Id); }

    public  WebElement Image_InfoIcon(){return findElement("com.bungii.customer:id/promo_code_more_info", LocatorType.Id);}
    public WebElement Text_InformationMessage() {return findElement("android:id/message", LocatorType.Id);}
    public WebElement Button_Ok () { return findElement("android:id/button1", LocatorType.Id); }
    public WebElement Button_AddPromoCode() { return findElements("//android.widget.Button", LocatorType.XPath).get(0);}

    public WebElement Text_First(boolean ...ignoreException) { return findElement("//android.widget.TextView[@text='FIRST']", LocatorType.XPath, ignoreException);}
    public WebElement Text_FirstTimeInfo(boolean...ignoreException ) { return  findElement("com.bungii.customer:id/promo_code_tv_firsttime_info", LocatorType.Id, ignoreException);}

    public WebElement Icon_i () { return findElement("com.bungii.customer:id/promo_code_more_info", LocatorType.Id); }
    public WebElement Button_OK () { return findElement("android:id/button1", LocatorType.Id); }
    public WebElement Button_Cancel () { return findElement("android:id/button2", LocatorType.Id); }
    public WebElement PromoCode_R0D1 () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label' and @text='$10.00 - R0D1']", LocatorType.XPath); }
    public WebElement PromoCode_R3D5 () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label' and @text='$1.00 - R3D5']", LocatorType.XPath); }
    public WebElement PromoCode_J0W1 (boolean ...ignoreException) { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label' and @text='$10.00 - J0W1']", LocatorType.XPath, ignoreException); }
    public WebElement PromoCode_R0D1_OnEstimate () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/estimate_value_promo' and @text='-$10.00']", LocatorType.XPath); }
    public WebElement PromoCode_R3D5_OnEstimate () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/estimate_value_promo' and @text='-$1.00']", LocatorType.XPath); }

}
