package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PaymentPage extends PageBase {
//------Header-------------------------------------------------------------------------------------------------------------
    public WebElement Header_PaymentPage (boolean...ignoreException) { return findElement("//android.widget.TextView[@text='PAYMENT']", LocatorType.XPath,ignoreException); }

    //------New User Payment page---------------------------------------------------------------------------------------------
    public WebElement Button_Add () { return findElement("com.bungii.customer:id/payment_methods_button_add", LocatorType.Id); }
   // public WebElement Button_Add () { return findElement("com.bungii.customer:id/payment_methods_textview_add_new_payment", LocatorType.Id); }

    public WebElement Text_NoPaymentExists () { return findElement("//android.widget.TextView[2]", LocatorType.XPath); }

    //------Select Payment Method--------------------------------------------------------------------------------------------
    public WebElement Select_Method_Card () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/bt_payment_method_type' and @text='Credit or Debit Card']", LocatorType.XPath); }

    //------Payment page where cards have been already added-----------------------------------------------------------------
    public WebElement Link_AddNew () { return findElement("com.bungii.customer:id/payment_methods_textview_add_new_payment", LocatorType.Id); }

    public WebElement PaymentCard1 () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/payment_methods_textview_last_four']", LocatorType.XPath); }
    public List<WebElement> List_Card (){return findElements("com.bungii.customer:id/payment_method_container_main",LocatorType.Id);}
    public List<WebElement> List_Card_1 (){return findElements("//android.widget.TextView[@resource-id='com.bungii.customer:id/payment_methods_textview_last_four']",LocatorType.XPath);}
    public WebElement PaymentCard2 () { return findElements("//android.widget.TextView[@resource-id='com.bungii.customer:id/payment_methods_textview_last_four']", LocatorType.XPath).get(1); }
    public List<WebElement> List_Card1 (){return findElements("com.bungii.customer:id/payment_methods_recycler_view",LocatorType.Id);}

    public WebElement DefaultCard() { return findElement("//android.widget.ImageView[@resource-id='com.bungii.customer:id/payment_methods_imageview_default_tick']/preceding-sibling::android.widget.TextView",LocatorType.XPath);}
    public WebElement PaymentCard3 () { return findElement("//android.widget.RelativeLayout[@resource-id='com.bungii.customer:id/payment_methods_layout_row' and @instance='10']", LocatorType.XPath); }

    public WebElement PaymentCard4 () { return findElement("//android.widget.RelativeLayout[@resource-id='com.bungii.customer:id/payment_methods_layout_row' and @instance='13']", LocatorType.XPath); }

    public WebElement PaymentCard5 () { return findElement("//android.widget.RelativeLayout[@resource-id='com.bungii.customer:id/payment_methods_layout_row' and @instance='16']", LocatorType.XPath); }

    public WebElement DefaultTick () { return findElement("com.bungii.customer:id/payment_methods_imageview_default_tick", LocatorType.Id); }

    public WebElement DefaultTick_payment1 () { return findElement("(//*[@resource-id='com.bungii.customer:id/payment_methods_layout_row'])[1]/android.widget.ImageView[@resource-id='com.bungii.customer:id/payment_methods_imageview_default_tick']", LocatorType.XPath); }

    public WebElement DefaultTick_payment2 () { return findElement("(//*[@resource-id='com.bungii.customer:id/payment_methods_layout_row'])[2]/android.widget.ImageView[@resource-id='com.bungii.customer:id/payment_methods_imageview_default_tick']", LocatorType.XPath); }

    public WebElement Checkbox_Default () { return findElement("com.bungii.customer:id/payment_methods_checkbox_set_default", LocatorType.Id); }

    public WebElement Button_Save () { return findElement("com.bungii.customer:id/payment_methods_button_save", LocatorType.Id); }

    public WebElement Text_SwipeLeftToDelete () { return findElement("com.bungii.customer:id/payment_methods_textview_slide_delete", LocatorType.Id); }

    public WebElement Button_Delete (boolean ...ignoreException) { return findElement("com.bungii.customer:id/delete_button", LocatorType.Id,ignoreException); }

    public WebElement Button_Delete_Yes () { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Button_Delete_No () { return findElement("android:id/button2", LocatorType.Id); }

    //------Card Details Page---------------------------------------------------------------------------------------------
    public WebElement Header_CardDetailsPage () { return findElement("//android.widget.TextView[@text='Card Details']", LocatorType.XPath); }

    public WebElement Textfield_CardNumber () { return findElement("com.bungii.customer:id/bt_card_form_card_number", LocatorType.Id); }

    public WebElement Error_CardNumber () { return findElement("com.bungii.customer:id/textinput_error", LocatorType.Id); }

    public WebElement Expiry_Years () { return findElement("//*[@resource-id='com.bungii.customer:id/bt_expiration_year_grid_view']/android.widget.TextView", LocatorType.XPath); }
    public WebElement Month_12 () { return findElement("//android.widget.TextView[@text='12']", LocatorType.XPath); }

    public WebElement Year_2020 () { return findElement("//android.widget.TextView[@text='2020']", LocatorType.XPath); }
    public WebElement Year_2018 (boolean...ignoreException) { return findElement("//android.widget.TextView[@text='2018']", LocatorType.XPath,ignoreException); }


    public WebElement Text_Cvv () { return findElement("com.bungii.customer:id/bt_card_form_cvv", LocatorType.Id); }
    public WebElement Text_PostalCode () { return findElement("com.bungii.customer:id/bt_card_form_postal_code", LocatorType.Id); }

    public WebElement Textfield_Expiration () { return findElement("//android.widget.EditText[@resource-id='com.bungii.customer:id/bt_card_form_expiration']", LocatorType.XPath); }

    public WebElement Button_AddCard () { return findElement("//android.widget.Button[@resource-id='com.bungii.customer:id/bt_button' and @text='Add card']", LocatorType.XPath); }
}
