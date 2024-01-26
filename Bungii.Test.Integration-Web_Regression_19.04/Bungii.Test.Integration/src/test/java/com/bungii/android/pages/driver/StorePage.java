package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class StorePage  extends PageBase {
    public WebElement Header_BungiiStore() { return findElement("//android.view.View[@text='BUNGII STORE']", LocatorType.XPath); }
    //$9.99
    public WebElement Text_BungiiHat() { return findElement("//android.view.View[@text='Bungii Hat']", LocatorType.XPath); }
    public WebElement Text_BungiiHatValue() { return findElement("//android.view.View[@text='Bungii Hat']/following-sibling::android.view.View/android.view.View", LocatorType.XPath); }
    public WebElement Text_BungiiCottonTee() { return findElement("//android.view.View[@text='Bungii Cotton Tee']", LocatorType.XPath); }
    //$9.99
    public WebElement Text_BungiiCottonTeeValue() { return findElement("//android.view.View[@text='Bungii Cotton Tee']/following-sibling::android.view.View/android.view.View", LocatorType.XPath); }

    //links after swipe
    public WebElement Text_TrackOrder() { return findElement("//android.view.View[@text='Track Orders']", LocatorType.XPath); }
    public WebElement Text_Shoppingbag() { return findElement("//android.view.View[@text='Shopping Bag']", LocatorType.XPath); }
    public WebElement Text_SignIn() { return findElement("//android.view.View[@text='Sign In']", LocatorType.XPath); }

}
