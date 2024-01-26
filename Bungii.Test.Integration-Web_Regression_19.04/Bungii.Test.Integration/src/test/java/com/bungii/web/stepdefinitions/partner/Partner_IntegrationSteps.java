package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.web.pages.partner.Partner_Delivery_StatusPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import com.bungii.api.utilityFunctions.CoreServices;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryList;
import com.bungii.web.stepdefinitions.admin.Admin_BusinessUsersSteps;
import com.bungii.web.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Partner_IntegrationSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Partner_Delivery_StatusPage partner_Delivery_StatusPage = new Partner_Delivery_StatusPage();
    Partner_DeliveryList Page_Partner_Delivery_List = new Partner_DeliveryList();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    com.bungii.web.utilityfunctions.DbUtility dbUtility = new DbUtility();

    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_CustomerPage admin_CustomerPage = new Admin_CustomerPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    Admin_EditScheduledBungiiPage admin_EditScheduledBungiiPage = new Admin_EditScheduledBungiiPage();
    CoreServices coreServices = new CoreServices();

    Admin_BusinessUsersSteps admin_businessUsersSteps = new Admin_BusinessUsersSteps();

    @When("^I request \"([^\"]*)\" Bungii trip in partner portal configured for \"([^\"]*)\" in \"([^\"]*)\" geofence$")
    public void i_request_something_bungii_trip_in_partner_portal_for_some_geofence(String Type, String Site, String geofence, DataTable data) throws InterruptedException {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String Pickup_Address;
            String Delivery_Address;
            String addressEnter="";
            SetupManager.getDriver().manage().window().maximize();

            cucumberContextManager.setScenarioContext("Bungii_Type", Type);
            cucumberContextManager.setScenarioContext("BUNGII_TYPE", Type);
            cucumberContextManager.setScenarioContext("Partner_Bungii_type", Type);

            Pickup_Address = dataMap.get("Pickup_Address");

            Delivery_Address = dataMap.get("Delivery_Address");
            if(dataMap.containsKey("Address_Enter")) {
                addressEnter = dataMap.get("Address_Enter");
            }

            cucumberContextManager.setScenarioContext("PickupAddress", Pickup_Address);
            //Delivery_Address = action.getText(Page_Partner_Dashboard.SetDeliveryAddress());
            cucumberContextManager.setScenarioContext("Delivery_Address", Delivery_Address);

            String Load_Unload = dataMap.get("Load_Unload_Time");

            //int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
            //int numberOf_Driver = dataMap.get("Driver").trim().equalsIgnoreCase("duo") ? 2 :1;

            //cucumberContextManager.setScenarioContext("GEOFENCE", geofence);
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence);
            cucumberContextManager.setScenarioContext("PP_Site", Site);
            cucumberContextManager.setScenarioContext("Portal_Name",Site);
            Thread.sleep(10000);
            if (Site.equalsIgnoreCase("normal")) {
                switch (Type) {
                    case "Solo":
                        //action.click(Page_Partner_Dashboard.Partner_Solo());
                        action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
                        action.click(Page_Partner_Dashboard.Button_PickupClear());
                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                        if(addressEnter.equalsIgnoreCase("CopyPaste")) {
                            String copyPickup = Pickup_Address + Keys.chord(Keys.CONTROL, "A") + Keys.chord(Keys.CONTROL, "C");
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), copyPickup + Keys.chord(Keys.CONTROL, "v"));

                        }
                        else{
//                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address);
                            Thread.sleep(2000);
                            action.click(Page_Partner_Dashboard.Icon_SearchPickupAdd());
                        }
//
                        Thread.sleep(3000);
                        action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        if(addressEnter.equalsIgnoreCase("CopyPaste")) {
                            String copyDelivery = Delivery_Address + Keys.chord(Keys.CONTROL, "A") + Keys.chord(Keys.CONTROL, "C");
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), copyDelivery + Keys.chord(Keys.CONTROL, "v"));
                            action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        }else {
//                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address);
                            action.click(Page_Partner_Dashboard.Icon_SearchPickupAdd());
                        }
                        Thread.sleep(3000);
//                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
//                        Thread.sleep(5000);
//                        action.click(Page_Partner_Dashboard.List_Delivery_Address());
//                        Thread.sleep(8000);
                        action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                        switch (Load_Unload) {
                            case "15 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 15);
                                break;
                            case "30 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 30);
                                break;
                            case "45 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 45);
                                break;
                            case "60 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 60);
                                break;
                            case "75 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 75);
                                break;
                            case "90+ minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 90);
                                break;
                            default:
                                break;
                        }
                        break;
                    case "Duo":

                        action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
                        action.click(Page_Partner_Dashboard.Button_PickupClear());

                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                        //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                        Thread.sleep(1000);
                        action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        Thread.sleep(6000);
                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        //Clicking on duo radio button
                        action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                        Thread.sleep(5000);
                        //Clicking on Load Unload dropdown
                        action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());

                        switch (Load_Unload) {
                            case "15 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 15);
                                break;
                            case "30 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 30);
                                break;
                            case "45 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 45);
                                break;
                            case "60 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 60);
                                break;
                            case "75 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 75);
                                break;
                            case "90+ minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 90);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
        /*
        Pickup_Address = action.getText(Page_Partner_Dashboard.SetPickupAddress());
        cucumberContextManager.setScenarioContext("PickupAddress",Pickup_Address);
        Delivery_Address = action.getText(Page_Partner_Dashboard.SetDeliveryAddress());
        cucumberContextManager.setScenarioContext("Delivery_Address", Delivery_Address);
        */

            } else if (Site.equalsIgnoreCase("kiosk mode")) {
                switch (Type) {
                    case "Solo":
                        action.click(Page_Partner_Dashboard.Button_Pickup_Edit());

                        action.click(Page_Partner_Dashboard.Button_PickupClear());
                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                        Thread.sleep(1000);
                        action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        Thread.sleep(2000);
                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        Thread.sleep(5000);

                        action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                        switch (Load_Unload) {
                            case "15 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 15);
                                break;
                            case "30 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 30);
                                break;
                            case "45 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 45);
                                break;
                            case "60 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 60);
                                break;
                            case "75 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 75);
                                break;
                            case "90+ minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 90);
                                break;
                            default:
                                break;
                        }
                        break;
                    case "Duo":
                        action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
                        action.click(Page_Partner_Dashboard.Button_PickupClear());

                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                        //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                        Thread.sleep(1000);
                        action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        Thread.sleep(2000);
                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        //Thread.sleep(1000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        //Clicking on duo radio button
                        action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                        Thread.sleep(5000);

                        //Clicking on Load Unload dropdown
                        action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());

                        switch (Load_Unload) {
                            case "15 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 15);
                                break;
                            case "30 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 30);
                                break;
                            case "45 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 45);
                                break;
                            case "60 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 60);
                                break;
                            case "75 minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 75);
                                break;
                            case "90+ minutes":
                                action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                                cucumberContextManager.setScenarioContext("LoadUnload_Time", 90);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }

            } else if (Site.equalsIgnoreCase("service level")) {
                switch (Type) {
                    case "Solo":

                        if(addressEnter.equalsIgnoreCase("CopyPaste")) {
                            String copyPickup = Pickup_Address + Keys.chord(Keys.CONTROL, "A") + Keys.chord(Keys.CONTROL, "C");
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), copyPickup + Keys.chord(Keys.CONTROL, "v"));
                        }
                        else {
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                        }
                        //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                        Thread.sleep(1000);
                        action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        Thread.sleep(5000);
                        if(addressEnter.equalsIgnoreCase("CopyPaste")) {
                            String copyDelivery = Delivery_Address + Keys.chord(Keys.CONTROL, "A") + Keys.chord(Keys.CONTROL, "C");
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), copyDelivery + Keys.chord(Keys.CONTROL, "v"));
                        }else {
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        }
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                        break;
                    case "Duo":
                        if(addressEnter.equalsIgnoreCase("CopyPaste")) {
                            String copyPickup = Pickup_Address + Keys.chord(Keys.CONTROL, "A") + Keys.chord(Keys.CONTROL, "C");
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), copyPickup + Keys.chord(Keys.CONTROL, "v"));
                        }
                        else {
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                        }
                        //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                        Thread.sleep(1000);
                        action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        Thread.sleep(5000);
                        if(addressEnter.equalsIgnoreCase("CopyPaste")) {
                            String copyDelivery = Delivery_Address + Keys.chord(Keys.CONTROL, "A") + Keys.chord(Keys.CONTROL, "C");
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), copyDelivery + Keys.chord(Keys.CONTROL, "v"));
                        }else {
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        }
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        //Clicking on duo radio button
                        action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                        Thread.sleep(2000);

                        //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                        break;
                    default:
                        break;
                }

            } else if (Site.equalsIgnoreCase("FloorDecor service level")) {
                switch (Type) {
                    case "Solo":
                        action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
                        action.click(Page_Partner_Dashboard.Button_PickupClear());
                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address);
                        action.click(Page_Partner_Dashboard.Icon_SearchPickupAdd());
//                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
//                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
//                        Thread.sleep(5000);
//                        action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        Thread.sleep(5000);
                        if(addressEnter.equalsIgnoreCase("CopyPaste")) {
                            String copyDelivery = Delivery_Address + Keys.chord(Keys.CONTROL, "A") + Keys.chord(Keys.CONTROL, "C");
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), copyDelivery + Keys.chord(Keys.CONTROL, "v"));
                        }else {
//                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address);
                        }
                        action.click(Page_Partner_Dashboard.Icon_SearchPickupAdd());
//                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
//                        Thread.sleep(5000);
//                        action.click(Page_Partner_Dashboard.List_Delivery_Address());
//                        action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                        break;
                    case "Duo":

                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                        //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                        Thread.sleep(1000);
                        action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        Thread.sleep(5000);
                        if(addressEnter.equalsIgnoreCase("CopyPaste")) {
                            String copyDelivery = Delivery_Address + Keys.chord(Keys.CONTROL, "A") + Keys.chord(Keys.CONTROL, "C");
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), copyDelivery + Keys.chord(Keys.CONTROL, "v"));
                        }else {
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        }
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        //Clicking on duo radio button
                        action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                        Thread.sleep(2000);

                        //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                        break;
                    default:
                        break;
                }

            }
            else if (Site.equalsIgnoreCase("fnd multiple phone")) {
                switch (Type) {
                    case "Solo":
                        if(addressEnter.equalsIgnoreCase("CopyPaste")) {
                            String copyDelivery = Delivery_Address + Keys.chord(Keys.CONTROL, "A") + Keys.chord(Keys.CONTROL, "C");
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), copyDelivery + Keys.chord(Keys.CONTROL, "v"));
                        }else {
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        }
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        break;
                    case "Duo":
                        //Clicking on duo radio button
                        action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                        // Thread.sleep(2000);
                        if(addressEnter.equalsIgnoreCase("CopyPaste")) {
                            String copyDelivery = Delivery_Address + Keys.chord(Keys.CONTROL, "A") + Keys.chord(Keys.CONTROL, "C");
                            action.sendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), copyDelivery + Keys.chord(Keys.CONTROL, "v"));
                        }else {
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        }
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(1000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());
                        Thread.sleep(3000);
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());
                        break;
                    default:
                        break;
                }
            }
            else if (Site.equalsIgnoreCase("Cort service level")) {
                    switch (Type) {
                        case "Solo":
                            action.click(Page_Partner_Dashboard.Button_Pickup_Edit());

                            action.click(Page_Partner_Dashboard.Button_PickupClear());
                            action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                            action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                            Thread.sleep(3000);
                            action.click(Page_Partner_Dashboard.List_Pickup_Address());

                            Thread.sleep(5000);
                            action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                            Thread.sleep(3000);
                            action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                            Thread.sleep(5000);
                            action.click(Page_Partner_Dashboard.List_Delivery_Address());

                            Thread.sleep(5000);


                            action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                            break;

                        case "Duo":

                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                            //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                            action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                            Thread.sleep(1000);
                            action.click(Page_Partner_Dashboard.List_Pickup_Address());

                            Thread.sleep(5000);
                            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                            action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                            Thread.sleep(5000);
                            action.click(Page_Partner_Dashboard.List_Delivery_Address());

                            //Clicking on duo radio button
                            action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                            Thread.sleep(2000);

                            //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                            break;
                        default:
                            break;
                    }

            } else if (Site.equalsIgnoreCase("BestBuy service level")) {
                switch (Type) {
                    case "Solo":
                        //action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                        //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                        //action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                        //Thread.sleep(1000);
                        //action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        //Thread.sleep(2000);
                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address);
                        action.click(Page_Partner_Dashboard.Icon_SearchPickupAdd());
                        Thread.sleep(3000);
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(3000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                        break;
                    case "Duo":

//                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
//                        //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
//                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
//                        Thread.sleep(1000);
//                        action.click(Page_Partner_Dashboard.List_Pickup_Address());
//
//                        Thread.sleep(2000);
                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        //Clicking on duo radio button
                        action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                        Thread.sleep(2000);

                        //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                        break;
                    default:
                        break;
                }
                log("I request " + Type + " Bungii trip in partner portal configured for " + Site + " in " + geofence + " geofence", "I have requested " + Type + " Bungii trip in partner portal configured for " + Site + " in " + geofence + " geofence", false);

            }
            else if (Site.equalsIgnoreCase("Equip-bid")) {
                switch (Type) {
                    case "Solo":
                        action.click(Page_Partner_Dashboard.Button_Pickup_Edit());

                        action.click(Page_Partner_Dashboard.Button_PickupClear());
                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                        Thread.sleep(1000);
                        action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        Thread.sleep(2000);
                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());

                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                        break;
                }
                log("I request " + Type + " Bungii trip in partner portal configured for " + Site + " in " + geofence + " geofence", "I have requested " + Type + " Bungii trip in partner portal configured for " + Site + " in " + geofence + " geofence", false);

            }
            else if (Site.equalsIgnoreCase("Home outlet service level")) {
                switch (Type) {
                    case "Solo":

                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                        Thread.sleep(2000);
                        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                        Thread.sleep(1000);
                        action.click(Page_Partner_Dashboard.List_Pickup_Address());

                        Thread.sleep(2000);
                        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                        Thread.sleep(5000);
                        action.click(Page_Partner_Dashboard.List_Delivery_Address());
                        Thread.sleep(5000);
                        break;
                }
                log("I request " + Type + " Bungii trip in partner portal configured for " + Site + " in " + geofence + " geofence", "I have requested " + Type + " Bungii trip in partner portal configured for " + Site + " in " + geofence + " geofence", false);

            }
                } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I navigate to \"([^\"]*)\" page$")
    public void i_navigate_to_something_page(String strArg1) throws Throwable {
        try {
            String url = utility.getCurrentUrl().replace("/login", "/quote-only");
            action.navigateTo(url);
            log("I navigate to Quote-only page",
                    "I navigated to Quote-only page", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in navigating to Quote-only page",
                    true);
        }
    }

    @Then("^I should see the estimate cost quote$")
    public void i_should_see_the_estimate_cost_quote() throws Throwable {
        try {
            String Alias_Name = (String) cucumberContextManager.getScenarioContext("Alias");
            String Selected_Service = (String) cucumberContextManager.getScenarioContext("Selected_service");
            String Trip_Type = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
            int Driver_Number = 1;
            if (Trip_Type.equalsIgnoreCase("Duo")) {
                Driver_Number = 2;
            }
           /* String pickupAddress = (String) cucumberContextManager.getScenarioContext("PickupAddress");
            String dropoffAddress =(String) cucumberContextManager.getScenarioContext("Delivery_Address");
            String Estimate_distance  = new GoogleMaps().getMiles(pickupAddress, dropoffAddress);
            double Estimate_distance_value = Double.parseDouble(Estimate_distance)/1000;
            Estimate_distance_value = Estimate_distance_value / 1.609344; //to convert kms to miles
            */
            String Estimated_distance = action.getText(Page_Partner_Dashboard.Label_Distance()).replace(" miles", "");//calculate values as per the displayed miles value to avoid mismatch in calculation
            double Estimate_distance_value = Double.parseDouble(Estimated_distance);

            logger.detail("Estimated Distance : " + Estimated_distance);
            String Last_Tier_Milenge_Min_Range = dbUtility.getMaxMilengeValue(Alias_Name, Selected_Service);
            double Last_Tier_Milenge_Min_Range_value = Double.parseDouble(Last_Tier_Milenge_Min_Range);
            String Price = "";
            if (Estimate_distance_value <= Last_Tier_Milenge_Min_Range_value) {
                Price = dbUtility.getServicePrice(Alias_Name, Driver_Number, String.valueOf(Estimated_distance), Selected_Service);
            } else {
                Price = dbUtility.getServicePriceLastTier(Alias_Name, Driver_Number, String.valueOf(Estimated_distance), Selected_Service);
            }
            String Price_Estimated_Page = action.getText(Page_Partner_Dashboard.Label_Estimated_Cost());
            Price_Estimated_Page = Price_Estimated_Page.replace("Estimated Cost: $", "");
            testStepAssert.isEquals(Price_Estimated_Page, Price, "For Selected " + Selected_Service + " service correct price should be shown. Expected : " + Price + " | Actual : " + Price_Estimated_Page, "For Selected " + Selected_Service + " service correct price is shown. Expected : " + Price + " | Actual : " + Price_Estimated_Page, "For Selected " + Selected_Service + " service correct price is not shown. Expected : " + Price + " | Actual : " + Price_Estimated_Page);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in getting estimate cost quote",
                    true);
        }

    }

    @Then("^Fields get reset to default state$")
    public void fields_get_reset_to_default_state() throws Throwable {
        try {
            testStepAssert.isEquals(Page_Partner_Dashboard.Dropdown_Pickup_Address().getAttribute("value"), "", "Pickup address field should get cleared", "Pickup address field is cleared", "Pickup address field is not cleared");
            testStepAssert.isEquals(Page_Partner_Dashboard.Dropdown_Delivery_Address().getAttribute("value"), "", "Dropdown address field should get cleared", "Dropdown address field is cleared", "Dropdown address field is not cleared");
            testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Label_NoServiceSelected(), "Service Level should get default to No Service Selected", "Service Level gets default to No Service Selected", "Service Level should is not defaulted to No Service Selected");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in starting over",
                    true);
        }

    }

    @Then("^I should see header as \"([^\"]*)\"$")
    public void i_should_see_header_as_something(String strArg1) throws Throwable {
        try {
            testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Header_QuotesOnly(), "Header Get Quotes should be displayed", "Header Get Quotes is displayed", "Header Get Quotes is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }

    @And("^Pickup Time field should not be displayed$")
    public void pickup_time_field_should_not_be_displayed() throws Throwable {
        try {
            testStepAssert.isFalse(action.isElementPresent(Page_Partner_Dashboard.Dropdown_Pickup_Time(true)), "Header Get Quotes should be displayed", "Header Get Quotes is displayed", "Header Get Quotes is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }

    @Then("^Delivery Status should be displayed correctly as \"([^\"]*)\"$")
    public void delivery_status_should_be_displayed_correctly_as_something(String status) throws Throwable {
        try {
            String PickupDateTime = (String) cucumberContextManager.getScenarioContext("Schedule_Date_Time");
            String PickupDatewithoutTimezone = PickupDateTime.substring(0,PickupDateTime.length()-6);
            String PickAddress = (String) cucumberContextManager.getScenarioContext("PickupAddress");
            String DropAddress = (String) cucumberContextManager.getScenarioContext("Delivery_Address");
            String EstimatedDistance = (String) cucumberContextManager.getScenarioContext("ESTIMATED_DISTANCE");
            String DriverName1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            String Est_Delivery_Time = (String) cucumberContextManager.getScenarioContext("ESTIMATED_DELIVERY_TIME");
            switch (status) {
                case "En Route To Pickup":
                case "Driver Arrived At Pickup":
                case "Loading Items":
                case "Driving To Drop Off":
                case "Unloading Items":
                    testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Text_DeliveryStatus(status),"Delivery Status should be shown as " + status,"Delivery Status is shown as " + status,"Delivery status is not shown as " + status);
                    testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Text_PickupTime(PickupDatewithoutTimezone),"Pickup Time "+PickupDatewithoutTimezone+" should be displayed correctly","PickupTime is displayed correctly","PickupTime is not displayed correctly");
                    testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Text_PickupAddress(PickAddress),"Pickup address "+PickAddress+" should be displayed correctly","Pickup address is  displayed correctly","Pickup address is not displayed correctly");
                    testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Text_DeliveryAddress(DropAddress),"Delivery address "+DropAddress+" should be displayed correctly","Delivery address is  displayed correctly","Delivery address is not displayed correctly");
                    testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Text_Distance(EstimatedDistance),"Estimated distance: " + EstimatedDistance + "should be displayed", "Estimated distance is displayed correctly", "Estimated distance is not displayed correctly");
                    testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Text_Driver1(DriverName1),"Driver name "+ DriverName1+ "should be displayed correctly","Driver name is  displayed correctly","Driver name is not displayed correctly");
                    if (((String) cucumberContextManager.getScenarioContext("BUNGII_TYPE")).equals("Duo Scheduled"))
                    {
                        String DriverName2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
                        testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Text_Driver1(DriverName2),"Driver name "+ DriverName2+ "should be displayed correctly","Driver name is  displayed correctly","Driver name is not displayed correctly");
                    }
//                    TO BE IMPLEMENTED AFTER CORE-3842
//                    testStepVerify.isElementDisplayed(partner_Delivery_StatusPage.Text_EstDeliveryTime(Est_Delivery_Time),"Est. delivery time should be displayed correctly","Est. delivery time is  displayed correctly","Est. delivery time is not displayed correctly");
                    testStepVerify.isElementDisplayed(partner_Delivery_StatusPage.Icon_CallDriver(),"Driver calling icon should be displayed","Driver calling icon is displayed","Driver calling icon is not displayed");
                    action.switchToTab(1);
                    break;
                case "Successfully Completed":
                    testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Label_SuccessMessage(),"Success Message is displayed","Success Message is displayed","Success Message is not displayed");
                    testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Text_SuccessMessage(),"Success Message is displayed","Success Message is displayed","Success Message is not displayed");
                    break;
                case "Delivery Cancelled":
                    testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Label_CanceledMessage(),"Delivery Cancelled message should be displayed","Delivery Cancelled message is displayed","Delivery Cancelled message is snot displayed");
                    testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Text_CanceledMessage(),"Delivery Cancelled message should be displayed","Delivery Cancelled message is displayed","Delivery Cancelled message is snot displayed");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }
    @Then("^In \"([^\"]*)\" the trip should be  having a indicator with the text \"([^\"]*)\"$")
    public void in_something_the_trip_should_be_having_a_indicator_with_the_text_something(String TripPosition, String tripNumber) throws Throwable {
       try {
           String expectedBackgroundColor = PropertyUtility.getDataProperties("partner.baltimore.bestbuy2.trip.indicator");
        switch (TripPosition){
          case "Scheduled Deliveries":
            case "Live Deliveries":
                Thread.sleep(1000);
                String indicatorText = action.getText(admin_ScheduledTripsPage.Text_TripIndicator());
                testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Text_TripIndicator(), "Indicator with text "+ tripNumber + " should be displayed", "Indicator with text "+ tripNumber + " should be displayed", "Indicator with text is not "+ tripNumber + " should be displayed");
                testStepAssert.isEquals(indicatorText,tripNumber,"Indicator text should be " +tripNumber ,"Indicator text is "+ indicatorText,"Indicator text is not " +tripNumber );
                String indicatorBgColor = admin_ScheduledTripsPage.Text_TripIndicator().getCssValue("background-color");
                testStepVerify.isEquals(indicatorBgColor,expectedBackgroundColor,"Indictor color should be green","Indicator color is green","Indicator color is not green");
            break;
            case "All Deliveries":
                Thread.sleep(1000);
                String indicatorTextForAllDeliveries = action.getText(admin_TripsPage.Text_AllTripIndicator());
                testStepAssert.isElementDisplayed(admin_TripsPage.Text_AllTripIndicator(), "Indicator with text "+ tripNumber + " should be displayed", "Indicator with text "+ tripNumber + " should be displayed", "Indicator with text is not "+ tripNumber + " should be displayed");
                testStepAssert.isEquals(indicatorTextForAllDeliveries,tripNumber,"Indicator text should be " +tripNumber ,"Indicator text is "+ indicatorTextForAllDeliveries,"Indicator text is not " +tripNumber );
                String indicatorbgForAllTrips = admin_TripsPage.Text_AllTripIndicator().getCssValue("background-color");
                testStepAssert.isEquals(indicatorbgForAllTrips,expectedBackgroundColor,"Indictor color should be green","Indicator color is green","Indicator color is not green");
                break;
      }
       } catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }

    @Then("^The delivery should not be having indicator$")
    public void the_delivery_should_not_be_having_indicator() throws Throwable {
        try{
        testStepAssert.isNotElementDisplayed(admin_ScheduledTripsPage.Text_TripIndicator(true), "Indicator Should not be displayed", "Indicator is not displayed","Indicator is displayed");

        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("I should be able to see {string}")
    public void iShouldBeAbleToSee(String type) {
        try{
            switch (type){
                case "admin cancelled event - driver accepted":
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_CanceledEvent")),
                            "Correct event should be displayed",
                            "Incorrect event is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData())).equals(PropertyUtility.getMessage("Text_DriverAccepted")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData())).contains(PropertyUtility.getMessage("Text_AdminCanceled")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    break;
                case "accessorial charges":
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_AccessorialChargeEvent")),
                            "Correct event should be displayed",
                            "Incorrect event is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData())).equals("$0"),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    String newValue=action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData()).replaceAll("\n","");
                    testStepAssert.isTrue((newValue).contains("Total: "+cucumberContextManager.getScenarioContext("OTHER_AMOUNT")+".00 (Other) (Includes share of "+cucumberContextManager.getScenarioContext("DRIVER_1")+": $20.00)"),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    break;
                case "partner cancelled event - driver not accepted":
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_CanceledEvent")),
                            "Correct event should be displayed",
                            "Incorrect event is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData())).equals(PropertyUtility.getMessage("Text_AssigningDrivers")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData())).contains(PropertyUtility.getMessage("Text_PartnerCancel")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    break;
                case "price-override driver earnings only":
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_PriceOverrideDriverEvent")),
                            "Correct event should be displayed",
                            "Incorrect event is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData())).equals("$"+cucumberContextManager.getScenarioContext("OLD_DRIVER_CUT")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData())).contains("$"+cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT")+" ("+cucumberContextManager.getScenarioContext("DRIVER_1")+")"),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    break;
                case "admin-revive-driver cancelled":
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_RevivedEvent")),
                            "Correct event should be displayed",
                            "Incorrect event is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData())).equals(PropertyUtility.getMessage("Text_DriverCanceled")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData())).contains(PropertyUtility.getMessage("Text_AssigningDrivers")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    break;
                case "admin-revive-admin cancelled":
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_RevivedEvent")),
                            "Correct event should be displayed",
                            "Incorrect event is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData())).equals(PropertyUtility.getMessage("Text_AdminCanceled")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData())).contains(PropertyUtility.getMessage("Text_AssigningDrivers")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    break;
                case "history prior revival":
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValueRow2())).equals(PropertyUtility.getMessage("Text_CanceledEvent")),
                            "Correct event should be displayed",
                            "Incorrect event is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueDataRow2())).equals(PropertyUtility.getMessage("Text_AssigningDrivers")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueDataRow2())).contains(PropertyUtility.getMessage("Text_AdminCanceled")),
                            "Correct value should be displayed",
                            "Incorrect value is displayed");
                    break;

            }
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
