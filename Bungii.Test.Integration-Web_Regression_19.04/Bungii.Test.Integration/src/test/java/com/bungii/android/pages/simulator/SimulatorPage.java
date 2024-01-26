package com.bungii.android.pages.simulator;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SimulatorPage extends PageBase {

    //Start Button
    public WebElement DSim_Btn_Start () { return findElement("btnStart", LocatorType.Id); }

    // Message
    public WebElement DSim_Text_Msg () { return findElement("lblMessage", LocatorType.Id); }

    //Accept Button
    public WebElement DSim_Btn_Accept () { return findElement("btnAccept", LocatorType.Id); }

    //Reject Button
    public WebElement DSim_Btn_Reject () { return findElement("btnReject", LocatorType.Id); }

    //Arrived Button
    public WebElement DSim_Btn_Arrived () { return findElement("btnArrived", LocatorType.Id); }

    //Cancel Button
    public WebElement DSim_Btn_Cancel () { return findElement("btnCancel", LocatorType.Id); }

    //Loading Button
    public WebElement DSim_Btn_Loading () { return findElement("btnLoading", LocatorType.Id); }

    //Driving To Dropoff Button
    public WebElement DSim_Btn_DrivingToDropoff () { return findElement("btnDrivingToDropoff", LocatorType.Id); }

    //Unloading Button
    public WebElement DSim_Btn_Unloading () { return findElement("btnUnloading", LocatorType.Id); }

    //Complete Button
    public WebElement DSim_Btn_Complete () { return findElement("btnComplete", LocatorType.Id); }

    //Click here to test again Link
    public WebElement Link_Restart () { return findElement("//div[@id='driver-Actions-Complete']/div/h4/a[text()='here']", LocatorType.XPath); }

}
