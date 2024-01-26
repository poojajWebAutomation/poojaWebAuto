package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_MenuLinksPage extends PageBase {

    public WebElement Menu_Dashboard (boolean... ignoreException) { return findElement("//h4[text()='Dashboard']", LocatorType.XPath,ignoreException); }

    public WebElement Menu_Customers () { return findElement("adminmenu-customers", LocatorType.Id); }

    public WebElement Menu_Drivers () { return findElement("adminmenu-drivers", LocatorType.Id); }

    public WebElement Menu_Trips () { return findElement("adminmenu-trips", LocatorType.Id); }

    public WebElement Menu_Trips_Trips () { return findElement("adminmenu-completedtrips", LocatorType.Id); }

    public WebElement Menu_Trips_LiveTrips () { return findElement("adminmenu-livetrips", LocatorType.Id); }

    public WebElement Menu_Marketing () { return findElement("adminmenu-marketing", LocatorType.Id); }

    public WebElement Menu_Promocodes () { return findElement("adminmenu-promocodes-menu", LocatorType.Id); }

    public WebElement Menu_Standardcodes () { return findElement("adminmenu-promocode", LocatorType.Id); }

    public WebElement Menu_Marketing_ReferralSource () { return findElement("adminmenu-referralsource", LocatorType.Id); }

    public WebElement Menu_Geofences () { return findElement("adminmenu-geofences", LocatorType.Id); }
}
