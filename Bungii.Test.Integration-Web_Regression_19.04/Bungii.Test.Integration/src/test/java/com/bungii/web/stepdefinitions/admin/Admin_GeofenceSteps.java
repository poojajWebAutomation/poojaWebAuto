package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.web.utilityfunctions.DbUtility.*;
import static com.bungii.web.utilityfunctions.DbUtility.getGeofenceAttributes;

public class Admin_GeofenceSteps extends DriverBase {

    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();

    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_CustomerPage admin_CustomerPage = new Admin_CustomerPage();
    Admin_DriversPage admin_DriversPage = new Admin_DriversPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
    Admin_PartnersPage admin_PartnersPage = new Admin_PartnersPage();
    Admin_GeofencePage admin_geofencePage=new Admin_GeofencePage();
    Admin_PotentialPartnersPage admin_potentialPartnersPage = new Admin_PotentialPartnersPage();
    Admin_GeofenceAtrributesPage admin_geofenceAtrributesPage =  new Admin_GeofenceAtrributesPage();
    GeneralUtility utility = new GeneralUtility();
    private static LogUtility logger = new LogUtility(Admin_PromoCodesSteps.class);

    ActionManager action = new ActionManager();

    @When("^I click on the \"([^\"]*)\" Button on \"([^\"]*)\" Screen$")
    public void i_click_on_the_something_button_on_something_screen(String button, String screen) throws Throwable {

        try{
        switch(screen) {

            case "Geofence":
                switch(button) {
                    case "Save":
                        action.click(admin_GeofencePage.Button_Save());
                        break;
                    case "Cancel":
                        action.click(admin_GeofencePage.Button_Cancel());
                        break;
                    case "Settings":
                        action.click(admin_GeofencePage.Button_Settings());
                        break;
                }
            break;

            case "Geofence Settings":
                switch (button) {
                    case "Save":
                        action.click(admin_GeofencePage.Button_SaveGeofenceSettings());
                }
                break;
            case"GeofenceAttributes":
                switch(button){
                    case "Save":
                        action.click(admin_geofenceAtrributesPage.Button_Save());
                        break;
                    case "Cancel":
                        action.click(admin_geofenceAtrributesPage.Button_Cancel());
                        break;
                }
                break;

        }
        log("And I click on the "+button+" Button on "+screen ,
                "I have clicked on the "+button+" Button on " +screen, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @When("^I click on the geofence name \"([^\"]*)\"$")
    public void i_click_on_the_geofence_name_something(String geofenceName) throws Throwable {
        try{
        String GeofenceName = (String) cucumberContextManager.getScenarioContext("GF_GEONAME");
        String Xpath =String.format("//tr/td[contains(.,'%s')]",GeofenceName);
        action.click( SetupManager.getDriver().findElement(By.xpath(Xpath)));
        log("I click on the geofence name "+ geofenceName ,
                "I have clicked on the geofence name "+ geofenceName, false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @When("^I edit the geofence \"([^\"]*)\"$")
    public void i_edit_the_geofence(String geofenceName) throws Throwable {
        try{
        action.waitUntilIsElementExistsAndDisplayed(admin_GeofencePage.Button_Edit(),(long)3000 );
        action.click(admin_GeofencePage.Button_Edit());
        log("I edit the geofence "+ geofenceName ,
                "I have edited the geofence "+ geofenceName, false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @Then("^the geofence gets saved successfully and it is displayed in the \"([^\"]*)\" grid$")
    public void the_geofence_gets_saved_successfully_and_it_is_displayed_in_the_something_grid(String strArg1) throws Throwable {
        try{
        String Name = (String) cucumberContextManager.getScenarioContext("GF_GEONAME");
        String Timezone = (String) cucumberContextManager.getScenarioContext("GF_GEOTIMEZONE");
        String Status = (String) cucumberContextManager.getScenarioContext("GF_STATUS");

        String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]",Name,Status,Timezone);
        cucumberContextManager.setScenarioContext("XPATH", Xpath);
        testStepAssert.isElementDisplayed(admin_GeofencePage.Row_geofenceList(Name,Status,Timezone),"Geofence should be listed in grid", "Geofence is listed in grid","Geofence is not listed in grid");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @Then("^Geofence data is populated correctly$")
    public void geofence_data_is_populated_correctly() throws Throwable {
        try{
        String Name = (String) cucumberContextManager.getScenarioContext("GF_GEONAME");
        String Timezone = (String) cucumberContextManager.getScenarioContext("GF_GEOTIMEZONE");
        String Status = (String) cucumberContextManager.getScenarioContext("GF_STATUS");
        String Primary = (String) cucumberContextManager.getScenarioContext("GF_PRIMARY");
        String Secondary = (String) cucumberContextManager.getScenarioContext("GF_SECONDARY");

        testStepAssert.isElementTextEquals(admin_GeofencePage.TextBox_Primary(),Primary,Primary + " should be displayed",Primary +" is displayed",Primary + " is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.TextBox_Secondary(),Secondary,Secondary + " should be displayed",Secondary +" is displayed",Secondary + " is not displayed");

        testStepAssert.isEquals(admin_GeofencePage.Label_GeoName(true).getAttribute("value"),Name,Name + " should be displayed",Name +" is displayed",Name + " is not displayed");

        testStepAssert.isElementDisplayed(admin_GeofencePage.Dropdown_Timezone().findElement(By.xpath(String.format("//option[@selected='selected' and text()='%s']",Timezone))),Timezone + " should be displayed",Timezone +" is displayed",Timezone + " is not displayed");
        testStepAssert.isElementDisplayed(admin_GeofencePage.Dropdown_Status().findElement(By.xpath(String.format("//option[@selected='selected' and text()='%s']",Status))),Status + " should be displayed",Status +" is displayed",Status + " is not displayed");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @Then("^the following timezones are listed in the \"([^\"]*)\" dropdown$")
    public void the_following_timezones_are_listed_in_the_something_dropdown(String dropdown, DataTable data) throws Throwable {

        try {
            List<Map<String, String>> DataList = data.asMaps();
            Long now = Instant.now().toEpochMilli();
            int i =0;

            switch (dropdown) {
                case "Geo-TimeZone" :
                    while (i < DataList.size()) {
                        String Timezone = DataList.get(i).get("Timezone");
                        action.selectElementByText(admin_GeofencePage.Dropdown_Timezone(), Timezone);
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Dropdown_Timezone().findElement(By.xpath(String.format("//option[text()='%s']",Timezone))),Timezone + " should be displayed",Timezone +" is displayed",Timezone + " is not displayed");
                        i++;
                    }
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^the following statuses are listed in the \"([^\"]*)\" dropdown$")
    public void the_following_statuses_are_listed_in_the_something_dropdown(String dropdown , DataTable data) throws Throwable {
        try {
            List<Map<String, String>> DataList = data.asMaps();
            Long now = Instant.now().toEpochMilli();
            int i =0;

            switch (dropdown) {
                case "Geo-Status" :
                    while (i < DataList.size()) {
                        String Status = DataList.get(i).get("Status");
                        action.selectElementByText(admin_GeofencePage.Dropdown_Status(), Status);
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Dropdown_Status().findElement(By.xpath(String.format("//option[text()='%s']",Status))),Status + " should be displayed",Status +" is displayed",Status + " is not displayed");
                        i++;
                    }
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I navigate to following pages one by one$")
    public void i_navigate_to_following_pages_one_by_one(DataTable data) throws Throwable {

        try {
            List<Map<String, String>> DataList = data.asMaps();
            Long now = Instant.now().toEpochMilli();
            int i =0;
                    while (i < DataList.size()) {
                        String Page = DataList.get(i).get("Page");
                        switch (Page) {
                            case "Dashboard" :
                                action.click(admin_DashboardPage.Menu_Dashboard());
                                break;
                            case "Drivers" :
                                action.click(admin_DriversPage.Menu_Drivers());
                                break;
                            case "Customers" :
                                action.click(admin_CustomerPage.Menu_Customers());
                                break;
//                            case "Trips" :
//                                action.click(admin_TripsPage.Menu_Trips());
//                                break;
//                            case "Scheduled Trips" :
//                                action.click(admin_TripsPage.Menu_Trips());
//                                action.click(admin_ScheduledTripsPage.Menu_ScheduledTrips());
//                                break;
//                            case "Live Trips" :
//                                action.click(admin_TripsPage.Menu_Trips());
//                                action.click(admin_LiveTripsPage.Menu_LiveTrips());
//                                break;
                            case "Completed Deliveries" :
                                action.click(admin_TripsPage.Menu_Trips());
                                action.click(admin_ScheduledTripsPage.Menu_CompletedDeliveries());
                                break;
                            case "Scheduled Deliveries" :
                                action.click(admin_TripsPage.Menu_Trips());
                                action.click(admin_ScheduledTripsPage.Menu_ScheduledTrips());
                                break;
                            case "Live Deliveries" :
                                action.click(admin_TripsPage.Menu_Trips());
                                action.click(admin_LiveTripsPage.Menu_LiveTrips());
                                break;
                            case "Potential Partners" :
                                action.click(admin_PartnersPage.Menu_Partners());
                                action.click(admin_PartnersPage.Assign_Partners());
                                break;

                        }
                        log("I navigate to "+Page+" page" ,
                                "I have navigated to "+Page+" page", false);

                       // i_should_see_something_in_the_dropdown_on_the_something_page(Page);
                       // i_should_not_see_something_in_the_dropdown_on_the_something_page(Page);
                        i++;
                    }


        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on \"([^\"]*)\" page$")
    public void i_click_on_something_page(String page) throws Throwable {
        try{
        switch(page){
            case "Dashboard":
                action.click(admin_DashboardPage.Menu_Dashboard());
                log("I click on "+page+" page" ,
                        "I have clicked on "+page+" page", false);
                break;
        }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @And("^I select \"([^\"]*)\" geofence$")
    public void i_select_something_geofence(String geofenceName) throws Throwable {
        try{
            switch (geofenceName) {
                case "Kansas":
                   // action.click(admin_potentialPartnersPage.Dropdown_Geofence());
                   // action.selectElementByText(admin_potentialPartnersPage.Dropdown_Geofence(),"Kansas");
                    Thread.sleep(3000);
                    utility.selectGeofenceDropdown(geofenceName);
                    break;

                case "Goa":
                   // action.click(admin_potentialPartnersPage.Dropdown_Geofence());
                    //action.selectElementByText(admin_potentialPartnersPage.Dropdown_Geofence(), "Goa");
                    utility.selectGeofenceDropdown(geofenceName);

                    break;

            }
            log("I select "+geofenceName+" geofence" ,
                    "I have selected "+geofenceName+" geofence", false);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see active zone in the dropdown on the \"([^\"]*)\" page$")
    public void i_should_see_something_in_the_dropdown_on_the_something_page(String page) throws Throwable {
        try{
        if(!page.equals("respective")) {

           String zone =  PropertyUtility.getDataProperties("active.geofence");
            //action.selectElementByText(admin_DashboardPage.Dropdown_Geofence(), zone);
            utility.searchGeofenceDropdown(zone);
            testStepAssert.isElementDisplayed(admin_geofencePage.Checkbox_GeofenceLabel(zone), zone + " should be displayed", zone + " is displayed", zone + " is not displayed");
            utility.closeGeofenceDropdown();
            log("I close geofence dropdown" ,
                    "I have closed geofence dropdown", false);

        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @And("^I should not see inactive zone in the dropdown on the \"([^\"]*)\" page$")
    public void i_should_not_see_something_in_the_dropdown_on_the_something_page(String page) throws Throwable {
        try{
        if(!page.equals("respective")) {

            String zone =  PropertyUtility.getDataProperties("inactive.geofence");
            int i = 0;
            Boolean testStatus = false;
            utility.searchGeofenceDropdown(zone);
            testStepVerify.isElementNotDisplayed(admin_geofencePage.Checkbox_GeofenceLabel(zone, true), zone + " should not be displayed", zone + " is not displayed", zone + " is displayed");
            utility.closeGeofenceDropdown();
            log("I close geofence dropdown" ,
                    "I have closed geofence dropdown", false);
          /*  List<WebElement> options = admin_DashboardPage.Dropdown_Geofence().findElements(By.tagName("option"));
               for (WebElement option : options)
               {
                   if(option.getText().equals(zone)) {
                       testStepAssert.isTrue( !option.getText().equals(zone), zone + " should not be displayed", zone + " is not displayed", zone + " is displayed");
                   }
               }*/
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @When("^I go to \"([^\"]*)\" page$")
    public void i_go_to_something_page(String screen) throws Throwable {
        testStepAssert.isElementDisplayed(admin_GeofencePage.Header_Attributes(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
    }

    @Then("^I verify that the default settings are displayed$")
    public void i_verify_that_the_default_settings_are_displayed() throws Throwable {
        String CustomerFAQLink = PropertyUtility.getDataProperties("customer.faq.link");
        String DriverFAQLink = PropertyUtility.getDataProperties("driver.faq.link");
        String MinTimeForDuoTrip = PropertyUtility.getDataProperties("Min.time.Duo.trip");
        String MinTimeForSoloTrip = PropertyUtility.getDataProperties("Min.time.Solo.trip");
        String MinTripCost=PropertyUtility.getDataProperties("Minimum.trip.cost");

        String SurveyEmailLink=PropertyUtility.getDataProperties("Survey.email.link");
        String TripCostPerMile=PropertyUtility.getDataProperties("Trip.cost.per.mile");
        String TripCostPerMinute=PropertyUtility.getDataProperties("Trip.cost.per.minute");

        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_CustomerFAQLink(), CustomerFAQLink, CustomerFAQLink+" should be displayed", CustomerFAQLink+" is displayed", CustomerFAQLink+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_DriverFAQLink(), DriverFAQLink ,DriverFAQLink+" should be displayed", DriverFAQLink+" is displayed", DriverFAQLink+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_MinTimeForDuoTrip(), MinTimeForDuoTrip,MinTimeForDuoTrip+" should be displayed", MinTimeForDuoTrip+" is displayed", MinTimeForDuoTrip+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_MinTimeForSoloTrip(), MinTimeForSoloTrip,MinTimeForSoloTrip+" should be displayed", MinTimeForSoloTrip+" is displayed", MinTimeForSoloTrip+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_MinTripCost(), MinTripCost,MinTripCost+" should be displayed", MinTripCost+" is displayed", MinTripCost+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_SurveyEmailLink(), SurveyEmailLink,SurveyEmailLink+" should be displayed", SurveyEmailLink+" is displayed", SurveyEmailLink+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_TripCostPerMile(), TripCostPerMile,TripCostPerMile+" should be displayed", TripCostPerMile+" is displayed", TripCostPerMile+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_TripCostPerMinute(), TripCostPerMinute,TripCostPerMinute+" should be displayed", TripCostPerMinute+" is displayed", TripCostPerMinute+" is not displayed");
    }

    @Then("^I cannot uncheck \"([^\"]*)\" for \"([^\"]*)\" settings when \"([^\"]*)\" is checked$")
    public void i_cannot_uncheck_something_for_something_settings_when_something_is_checked(String strArg1, String strArg2, String strArg3) throws Throwable {
        Thread.sleep(3000);
        testStepAssert.isElementEnabled(admin_GeofencePage.Checkbox_Solo(),"Solo Checkbox should be enabled","Solo Checkbox is enabled","Solo Checkbox is not enabled");
    }

    @When("^I \"([^\"]*)\" option \"([^\"]*)\" for Scheduled trip$")
    public void i_something_option_something_for_scheduled_trip(String action1, String trip_type) throws Throwable {
        try{
        switch (trip_type){
            case "Duo":
            {
                switch (action1)
                {
                    case "check":
                        if(!admin_GeofencePage.Checkbox_Duo().isSelected())
                            action.click(admin_GeofencePage.Checkbox_Duo());
                        break;
                    case "uncheck":
                        if(admin_GeofencePage.Checkbox_Duo().isSelected())
                            action.click(admin_GeofencePage.Checkbox_Duo());
                        break;
                }
            }
            break;

            case "Solo":
            {
                switch (action1)
                {
                    case "check":
                        if(!admin_GeofencePage.Checkbox_Solo().isSelected())
                            action.click(admin_GeofencePage.Checkbox_Solo());
                        break;
                    case "uncheck":
                        if(admin_GeofencePage.Checkbox_Solo().isSelected())
                            action.click(admin_GeofencePage.Checkbox_Solo());
                        break;
                }
            }
            break;

            case "Ondemand":
            {
                switch(action1)
                {
                    case "uncheck":
                        if(admin_GeofencePage.Checkbox_OnDemand().isSelected())
                            action.click((admin_GeofencePage.Checkbox_OnDemand()));
                }
            }
            break;
        }
        log("I "+action1+" option "+trip_type+" for Scheduled trip" ,
                "I have "+action1+" option "+trip_type+" for Scheduled trip", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }


    @Then("^I can deselect \"([^\"]*)\" option for Scheduled trip$")
    public void i_can_deselect_something_option_for_scheduled_trip(String strArg1)  {
        try{
        testStepAssert.isElementEnabled(admin_GeofencePage.Checkbox_Solo(),"Solo is enabled","Solo is enabled","Solo is disbled");
        action.click(admin_GeofencePage.Checkbox_Solo());
        log("I click option " + strArg1,
                "I have clicked option "+ strArg1, false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @When("^I check \"([^\"]*)\" option$")
    public void i_check_something_option(String strArg1) throws Throwable {
        try{
        action.click(admin_GeofencePage.Checkbox_Duo());
        log("I click option " + strArg1,
                "I have clicked option "+ strArg1, false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @Then("^The \"([^\"]*)\" gets selected automatically$")
    public void the_something_gets_selected_automatically(String strArg1) throws Throwable {
        testStepVerify.isElementSelected(admin_GeofencePage.Checkbox_Solo(),"Solo should be selected","Pass","Fail");
    }

    @When("^I click on the geofence \"([^\"]*)\"$")
    public void i_click_on_the_geofence_something(String GeofenceName) throws Throwable {
        try{
            String xpath;
            switch (GeofenceName){
                case "Chicago":
                    xpath =String.format("//td[contains(text(),'%s')]/following-sibling::td[text()='%s']",GeofenceName,"Active");
                    break;

                case "Chicago-inactive":
                    GeofenceName="Chicago";
                    xpath =String.format("//td[contains(text(),'%s')]/following-sibling::td[text()='%s']",GeofenceName,"Inactive");
                    break;

                case "new-geofence":
                    GeofenceName = (String) cucumberContextManager.getScenarioContext("GF_GEONAME");
                    xpath =String.format("//td[contains(text(),'%s')]/following-sibling::td[text()='%s']",GeofenceName,"Inactive");
                    break;

                default :
                    xpath =String.format("//td[contains(text(),'%s')]/following-sibling::td[text()='%s']",GeofenceName,"Active");

            }
        action.waitUntilIsElementExistsAndDisplayed(admin_GeofencePage.findElement(xpath, PageBase.LocatorType.XPath), (long) 3000);
        action.click(admin_GeofencePage.findElement(xpath, PageBase.LocatorType.XPath));
        log("I click on the geofence" ,
                "I have clicked on the geofence", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }

    }
    @And("^I \"([^\"]*)\" geofence polylines$")
    public void i_something_geofence_polylines(String actionType) throws Throwable {
        try{
            switch (actionType){
                case "extend":
                    String primaryPolyline=PropertyUtility.getDataProperties("chicago.extended.primary.polyline");
                    String secondaryPolyline=PropertyUtility.getDataProperties("chicago.extended.secondary.polyline");
                    action.clearSendKeys(admin_GeofencePage.TextBox_Primary(),primaryPolyline);
                    action.clearSendKeys(admin_GeofencePage.TextBox_Secondary(),secondaryPolyline);
                    action.click(admin_GeofencePage.Button_Save());
                    break;
                case "reduce":
                    String reducedPrimaryPolyline=PropertyUtility.getDataProperties("chicago.reduced.primary.polyline");
                    String reducedSecondaryPolyline=PropertyUtility.getDataProperties("chicago.reduced.secondary.polyline");
                    action.clearSendKeys(admin_GeofencePage.TextBox_Primary(),reducedPrimaryPolyline);
                    action.clearSendKeys(admin_GeofencePage.TextBox_Secondary(),reducedSecondaryPolyline);
                    action.click(admin_GeofencePage.Button_Save());
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }
    @When("^I uncheck both on demand and Scheduled for a geofence$")
    public void i_uncheck_both_on_demand_and_scheduled_for_a_geofence() throws Throwable {
        try{
        i_something_option_something_for_scheduled_trip("uncheck","Duo");
        i_something_option_something_for_scheduled_trip("uncheck","Solo");
        i_something_option_something_for_scheduled_trip("uncheck","Ondemand");
        log("I uncheck both on demand and Scheduled for a geofence" ,
                "I have unchecked both on demand and Scheduled for a geofence", false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @Then("^The validation error message is displayed$")
    public void the_validation_error_message_is_displayed() throws Throwable {
          testStepAssert.isElementDisplayed(admin_GeofencePage.Label_SettingsError(),"Active geofence should allow either Scheduled or On demand trip. - message is displayed","Pass","fail");
    }

    @Then("^check if error message is displayed for \"([^\"]*)\"$")
    public void check_if_error_message_is_displayed_for_something(String bungiiType) throws Throwable {
        String duoTime= (String) cucumberContextManager.getScenarioContext("MIN_TIME_DUO");
        int duoTimeValue=Integer.parseInt(duoTime);
        String soloTime= (String) cucumberContextManager.getScenarioContext("MIN_TIME_SOLO");
        int soloTimeValue=Integer.parseInt(duoTime);

        int dbValFromTime= Integer.parseInt(PropertyUtility.getDataProperties("schedule.pickup.from.time"));
        int dbValToTime=Integer.parseInt(PropertyUtility.getDataProperties("schedule.pickup.to.time"));
        int dbValMaxProcessTime=Integer.parseInt(PropertyUtility.getDataProperties("schedule.pickup.max.processing.time"));
        try{
            switch(bungiiType){
                case "duo trip":
                  /*  if(duoTimeValue < dbValFromTime || duoTimeValue > dbValToTime || duoTimeValue < dbValMaxProcessTime) {
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should be displayed.", "Validation message is displayed ->"+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText(),"Validation message is not displayed.");
                    }
                    else{
                        testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText());
                    }*/
                    if(duoTimeValue >= dbValFromTime && duoTimeValue <= dbValToTime) {
                        testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText());
                        //testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should be displayed.", "Validation message is displayed ->"+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText(),"Validation message is not displayed.");
                    }
                    else{
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should be displayed.", "Validation message is displayed ->"+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText(),"Validation message is not displayed.");
                        //testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText());
                    }
                    break;

                case "solo trip":
                   /* if(soloTimeValue < dbValFromTime || soloTimeValue > dbValToTime  || soloTimeValue < dbValMaxProcessTime) {
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should be displayed.", "Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText(),"Validation message is not displayed.");
                    }
                    else{
                        testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText());
                    }*/
                    if(soloTimeValue >= dbValFromTime && soloTimeValue <= dbValToTime) {
                        testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText());
                        //testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should be displayed.", "Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText(),"Validation message is not displayed.");
                    }
                    else{
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should be displayed.", "Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText(),"Validation message is not displayed.");
                        //testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText());
                    }
                    break;
            }
            log("And I verified the time for  "+ bungiiType,
                    "And I have verified the time for  "+bungiiType,true);
        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^Enter value should get saved and error message is not displayed$")
    public void check_if_error_message_is_not_display_for_something() throws Throwable {
        testStepAssert.isElementDisplayed(admin_GeofencePage.Button_Settings(),"Setting button should be displayed.", "Setting button is displayed ->"+ admin_GeofencePage.Button_Settings().getText(),"Setting button is not displayed.");

    }

    @And("^I change the value of \"([^\"]*)\" to \"([^\"]*)\" minutes$")
    public void i_change_the_value_of_something_to_something_minutes(String type, String timeValue) throws Throwable {
        try{
            switch(type){
                case "Minimum scheduled time for Duo trip":
                    action.JavaScriptClear(admin_GeofencePage.TextBox_MinimumScheduledtimeforduo());
                    action.sendKeys(admin_GeofencePage.TextBox_MinimumScheduledtimeforduo(), timeValue);
                    cucumberContextManager.setScenarioContext("MIN_TIME_DUO", timeValue);
                    break;
                case "Minimum scheduled time for Solo trip":
                    action.JavaScriptClear(admin_GeofencePage.TextBox_MinimumScheduledtimeforsolo());
                    action.sendKeys(admin_GeofencePage.TextBox_MinimumScheduledtimeforsolo(), timeValue);
                    cucumberContextManager.setScenarioContext("MIN_TIME_SOLO", timeValue);
                    break;
            }
            log("And I enter the text "+timeValue,
                    "And I have entered the text "+timeValue,true);
        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("I load Geofence Attributes Page and Click on New Attributes button")
    public void i_load_geofence_attributes_page_and_click_on_new_attributes_button() throws Throwable {
        try{
        Thread.sleep(2000);
        String loadGeoFenceAttributesUrl = PropertyUtility.getDataProperties("qa.attributes.url").concat("/GetSecuredGeofenceAttributes");
        action.navigateTo(loadGeoFenceAttributesUrl);
        action.click(admin_geofenceAtrributesPage.Button_NewAttribute());
        log("I load Geofence Attributes Page and Click on New Attributes button",
                "I have loaded Geofence Attributes Page and Clicked on New Attributes button", false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @Then("^the \"([^\"]*)\" message is displayed  in geofence popup")
    public void the_something_message_is_displayed_in_geofence_popup(String message) throws Throwable {
        testStepAssert.isEquals(action.getText(admin_geofenceAtrributesPage.Label_ErrorTextOnEmpty()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
    }

    @Then("the error message is displayed")
    public void the_error_message_is_displayed(String message) throws Throwable {
        testStepAssert.isEquals(action.getText(admin_geofenceAtrributesPage.Label_ErrorTextOnEmpty()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
    }

    @When("^I search by Name \"([^\"]*)\" in \"([^\"]*)\" page geofence$")
    public void iSearchByNameInPageGeofence(String arg0, String arg1) throws Throwable {
        try{
        Thread.sleep(2000);
        String Name = (String) cucumberContextManager.getScenarioContext("GF_ATTR_LABEL");
        action.clearSendKeys(admin_geofenceAtrributesPage.TextBox_SearchCriteria(),Name + Keys.ENTER);
        log("I search by name in Geo fence New attributes page",
                "I searched by name in Geo fence New attributes page", false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

//    @When("^I search by Name \"([^\"]*)\" in \"([^\"]*)\" page geofence$")
//    public void i_search_by_name_something_in_something_page_geofence(String strArg1, String strArg2) throws Throwable {
//        Thread.sleep(2000);
//        String Name = (String) cucumberContextManager.getScenarioContext("GF_ATTR_LABEL");
//        action.clearSendKeys(admin_geofenceAtrributesPage.TextBox_SearchCriteria(),Name + Keys.ENTER);
//        log("I search by name in Geo fence New attributes page",
//                "I searched by name in Geo fence New attributes page", true);
//    }

    @Then("The geofence Attributes gets saved successfully and it is displayed in the grid")
    public void the_geofence_attributes_gets_saved_successfully_and_it_is_displayed_in_the_grid() throws Throwable {
try{
        String Key = (String) cucumberContextManager.getScenarioContext("GF_ATTR_KEY");
        String DefaultValue = (String) cucumberContextManager.getScenarioContext("GF_ATTR_DEFAULT_VALUE");
        String Description = (String) cucumberContextManager.getScenarioContext("GF_ATTR_DESCRIPTION");
        String Label = (String) cucumberContextManager.getScenarioContext("GF_ATTR_LABEL");
        boolean checkIfCountEquals = false;
        List getListOfGeoFence = getListOfGeoFenceIds();
        for(int i = 0; i < getListOfGeoFence.size(); i++)
        {
            HashMap<String, Object> tmpData = (HashMap<String, Object>) getListOfGeoFence.get(i);
            Set<String> key = tmpData.keySet();
            Iterator it = key.iterator();
            while (it.hasNext()) {
                String hmKey = (String)it.next();
                Integer hmData = (Integer) tmpData.get(hmKey);
                int getIfExists =  getGeofenceSettingsVersions(hmData);
                int getGeofenceSettings = getGeofenceSettings(getIfExists);
                int getAttributeTableCount = getGeofenceAttributes();
                if(getGeofenceSettings == getAttributeTableCount) {
                    checkIfCountEquals = true;
                }
                if(checkIfCountEquals) {
                    String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]",DefaultValue, Label);
                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Geofence attributes should be listed in grid", "Geofence attributes is listed in grid","Geofence attributes is not listed in grid");
                }
                System.out.println("Key: "+hmKey +" & Data: "+hmData);
                it.remove(); // avoids a ConcurrentModificationException
            }

        }
//        List getListOfGeoFence = fetchAllDataForGeoFence();
//        int getAttributeTableCount = getGeofenceAttributes();
//
//        for(int i=0;i<getListOfGeoFence.size();i++) {
//
//                if(getAttributeTableCount == getListOfGeoFence[i]) checkIfCountEquals = true;
//
//        }
//        for(int i=0;i<getListOfGeoFence.size();i++) {
//            int newGEoFenceSetting = getGeofenceSettingsVersions((Integer) getListOfGeoFence.get(i));
//
//            if(newGEoFenceSetting > 0) {
//                int getIfExists =  getGeofenceSettingsVersions(newGEoFenceSetting);
//                int getAttributeTableCount = getGeofenceAttributes();
//                if(getIfExists == getAttributeTableCount) {
//                    checkIfCountEquals = true;
//                }
//                if(checkIfCountEquals) {
//                    String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]",Label, Description);
//                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Geofence attributes should be listed in grid", "Geofence attributes is listed in grid","Geofence attributes is not listed in grid");
//                }
//            }
//        }
        String Xpath =String.format("//tr/td[contains(.,'%s')]",DefaultValue);
        cucumberContextManager.setScenarioContext("G_XPATH", Xpath );
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Geofence attributes should be listed in grid", "Geofence attributes is listed in grid","Geofence attributes is not listed in grid");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @And("I check the Searched result is displayed correctly")
    public void i_check_the_searched_result_is_displayed_correctly() throws Throwable {
        try{
        Thread.sleep(2000);
        String xpath = (String)cucumberContextManager.getScenarioContext("G_XPATH");
        testStepAssert.isElementDisplayed(action.getElementByXPath(xpath),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
        }

    @And("I set \"([^\"]*)\" % Bungii Cut Per Delivery for the geofence")
    public void i_set_some_bungii_cut_per_delivery_for_the_geofence(String BungiiRateData){
        try{
        switch(BungiiRateData){
            case "Valid":
                int BungiiRate = Integer.parseInt(PropertyUtility.getDataProperties("valid.bungii.rate"));
                cucumberContextManager.setScenarioContext("Bungii_cut",BungiiRate);
                action.clearSendKeys(admin_GeofencePage.TextBox_Bunggi_Cut_Rate(),Integer.toString(BungiiRate));
                break;
            case "Above100":
                int Above100BungiiRate = Integer.parseInt(PropertyUtility.getDataProperties("Above100.bungii.rate"));
                action.clearSendKeys(admin_GeofencePage.TextBox_Bunggi_Cut_Rate(),Integer.toString(Above100BungiiRate)+Keys.TAB);
                break;
            case "Below Zero":
                int BelowZeroBungiiRate = Integer.parseInt(PropertyUtility.getDataProperties("BelowZero.bungii.rate"));
                action.clearSendKeys(admin_GeofencePage.TextBox_Bunggi_Cut_Rate(),Integer.toString(BelowZeroBungiiRate)+Keys.TAB);
                break;
            case "Blank":
                action.clear(admin_GeofencePage.TextBox_Bunggi_Cut_Rate());
                break;
        }
        log("I set "+BungiiRateData+" % Bungii Cut Per Delivery for the geofence",
                "I have set "+BungiiRateData+" % Bungii Cut Per Delivery for the geofence", false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }

    }

    @Then("I check that correct Driver cut calculated based on Bungii Cut Per Delivery")
    public void I_check_that_correct_Driver_cut_calculated_based_on_Bungii_Cut_Per_Delivery(){
        try{
        int bungii_cut = Integer.parseInt((String)cucumberContextManager.getScenarioContext("Bungii_cut"));
        int driver_cut = 100-bungii_cut;
        String dc = Integer.toString(driver_cut);

        String driver_rate = action.getElementByXPath("//input[@id='attributeValueDiverCutPerDelivery']").getAttribute("value");
        testStepVerify.isEquals(dc,driver_rate,"Correct Driver cut is calculated","Incorrect Driver cut");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }

    }

    @Then("I see \"([^\"]*)\" validation error message.")
    public void I_see_some_validation_error_message(String ErrorMessage){
        try{
        switch(ErrorMessage){
            case "Above 100 Bungii rate":
                testStepVerify.isElementTextEquals(admin_GeofencePage.TextError_BunggiCut(),"Please enter a value less than or equal to 100.");
                break;
            case "Blank Bungii rate":
                testStepVerify.isElementTextEquals(admin_GeofencePage.TextError_General(),"Oops! It looks like you missed something. Please fill out all fields before proceeding.");
                testStepVerify.isElementTextEquals(admin_GeofencePage.TextError_BunggiCut(),"bungii cut per delivery is required.");
                break;
            case "Below Zero Bungii rate":
                testStepVerify.isElementTextEquals(admin_GeofencePage.TextError_BunggiCut(),"Please enter a value greater than or equal to 0.");
                break;
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }

    }

    @And("^I uncheck the Active Geofences Only Checkbox$")
    public void I_uncheck_the_active_geofences_only_checkbox(){
        try{
        action.click(admin_GeofencePage.Checkbox_Active_Geofences());
        log("I uncheck the Active Geofences Only Checkbox",
                "I have uncheck the Active Geofences Only Checkbox", false);
        //Thread.sleep(2000);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }
    @And("^I verify if \"([^\"]*)\" are downloaded$")
    public void i_verify_if_something_are_downloaded(String downloadType) throws Throwable {
        try{
            Thread.sleep(7000);
            String home = SystemUtils.getUserHome().getPath();
            File theNewestFile = null;
            File dir = new File(home + "/Downloads");
            FileFilter fileFilter = new WildcardFileFilter("*.csv");
            File[] files = dir.listFiles(fileFilter);
            logger.detail("Download directory : "+ home + "/Downloads");
            if(files!=null) {
                logger.detail("Files Length : " + files.length);
                for (int i = 0; i < files.length; i++)
                    logger.detail("File : " + files[i].getName());

                if (files.length > 0) {
                    Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                    theNewestFile = files[0];
                    String fileName =theNewestFile.getName();
                    cucumberContextManager.setScenarioContext("CSV_FILE_NAME",fileName);
                }
            }
            String filename= (String) cucumberContextManager.getScenarioContext("CSV_FILE_NAME");
            BufferedReader reader = new BufferedReader(new FileReader(dir+"/"+filename));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
            String content = stringBuilder.toString();

            switch (downloadType){
                case "only active geofence zip codes":
                    List <WebElement>numberOfActive=admin_GeofencePage.List_RowCount();
                    int rows=numberOfActive.size();
                    for (int j=1;j<=rows;j++){
                        String geofence = admin_GeofencePage.List_ActiveGeofence(j).getText();
                        if(geofence.equalsIgnoreCase("Goa")){
                            //do nothing
                        }else{
                            testStepAssert.isTrue(content.contains(geofence),
                                    "Only active geofence zip codes should be displayed",
                                    "Only active geofence zip codes are not displayed");
                        }
                    }
                    break;
                case "deactive geofence is not":
                    String inactiveGeofence = (String) cucumberContextManager.getScenarioContext("DEACTIVATED_GEOFENCE");
                    testStepAssert.isFalse(content.contains(inactiveGeofence),
                            "The inactive geofence zip codes should not be present",
                            "The inactive geofence zip codes are present");
                    break;
                case "active geofence":
                    String activeGeofence = (String) cucumberContextManager.getScenarioContext("ACTIVATED_GEOFENCE");
                    testStepAssert.isTrue(content.contains(activeGeofence),
                            "The active geofence zip codes should be present",
                            "The active geofence zip codes are not present");
                    break;
                case "count of Chicago":
                    String geofenceName = (String) cucumberContextManager.getScenarioContext("ACTIVATED_GEOFENCE");
                    cucumberContextManager.setScenarioContext("BEFORE_EXTEND_POLYLINES_COUNT",content.length());
                    break;
                case "count of Chicago after extend":
                    String geofence = (String) cucumberContextManager.getScenarioContext("ACTIVATED_GEOFENCE");
                    String beforeExtendCount= (String) cucumberContextManager.getScenarioContext("BEFORE_EXTEND_POLYLINES_COUNT");
                    int beforeExtenDCount= Integer.parseInt(beforeExtendCount);
                    testStepAssert.isTrue(content.length()>beforeExtenDCount,
                            "The number of zip codes should be more after extending the polylines",
                            "The number of zip codes is not more after extending the polylines");
                    cucumberContextManager.setScenarioContext("AFTER_EXTEND_POLYLINES_COUNT",content.length());
                    break;
                case "count of Chicago after reduce":
                    String afterExtend= (String) cucumberContextManager.getScenarioContext("AFTER_EXTEND_POLYLINES_COUNT");
                    int afterExtendCount = Integer.parseInt(afterExtend);
                    testStepAssert.isTrue(content.length()<afterExtendCount,
                            "The number of zip codes should be less after reducing the polylines",
                            "The number of zip codes is not less after reducing the polylines");
                    break;
                case "new-geofence":
                    String newGeofence= (String) cucumberContextManager.getScenarioContext("GF_GEONAME");
                    testStepAssert.isTrue(content.contains(newGeofence),
                            "The newly added geofence zip codes should be present",
                            "The newly added  geofence zip codes are not present");
                    break;
            }
            log("I should be able to verify if the file is downloaded correctly",
                    "I am able to verify if the file is downloaded correctly",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I \"([^\"]*)\" status for \"([^\"]*)\" geofence$")
    public void i_something_status_for_something_geofence(String status, String geofenceName) throws Throwable {
        try{
            switch (status) {
                case "deactivate":
//                    action.click(admin_GeofencePage.Button_Edit());
                    Thread.sleep(5000);
                    action.click(admin_GeofencePage.Dropdown_Status());
                    Thread.sleep(5000);
                    action.selectElementByText(admin_GeofencePage.Dropdown_Status(),"Inactive");
                    action.click(admin_GeofencePage.Button_Save());
                    cucumberContextManager.setScenarioContext("DEACTIVATED_GEOFENCE",geofenceName);
                    break;
                case "activate":
//                    action.click(admin_GeofencePage.Button_Edit());
                    Thread.sleep(5000);
                    action.click(admin_GeofencePage.Dropdown_Status());
                    Thread.sleep(5000);
                    action.selectElementByText(admin_GeofencePage.Dropdown_Status(),"Active");
                    action.click(admin_GeofencePage.Button_Save());
                    cucumberContextManager.setScenarioContext("ACTIVATED_GEOFENCE",geofenceName);
                    break;

            }
            log("I should be able to change the status of the geofence",
                    "I am able to change the status of the geofence",false);
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("I open a newly created geofence")
    public void iOpenANewlyCreatedGeofence() {
        try{
            String geofenceName = (String) cucumberContextManager.getScenarioContext("GF_GEONAME");
            action.click(admin_GeofencePage.Row_GeofenceName(geofenceName));

            log("I should be able to open newly created geofence",
                    "I am able to open newly created geofence",false);
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("I observe log details in the Geo-History section")
    public void iObserveLogDetailsInTheGeoHistorySection() {
        try{
            testStepVerify.isElementDisplayed(admin_GeofencePage.Text_GeoHistory(),"I should able to see Geo History text.","I am able to see Geo History text.","I am not able to see Geo History text.");
            testStepVerify.isElementDisplayed(admin_GeofencePage.Text_SrNo(),"I should able to see Sr.No. text.","I am able to see Sr.No. text.","I am not able to see Sr.No. text.");
            testStepVerify.isElementDisplayed(admin_GeofencePage.Text_ModifiedDate(),"I should able to see Modified Date text.","I am able to see Modified Date text.","I am not able to see Modified Date text.");
            testStepVerify.isElementDisplayed(admin_GeofencePage.Text_ModifiedBy(),"I should able to see Modified By text.","I am able to see Modified By text.","I am not able to see Modified By text.");
            testStepVerify.isElementDisplayed(admin_GeofencePage.Text_Phone(),"I should able to see Phone text.","I m able to see Phone text.","I am not able to see Phone text.");
            testStepVerify.isElementDisplayed(admin_GeofencePage.Text_Changes(),"I should able to see Changes text.","I am able to see Changes text.","I am not able to see Changes text.");


        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("I note the Geo History log records count")
    public void iNoteTheGeoHistoryLogRecordsCount() {
        try {
            List<WebElement> logsRecordCount = admin_GeofencePage.Rows_GeoHistoryLogs();
            int count = logsRecordCount.size();
            cucumberContextManager.setScenarioContext("RECORDS_COUNT", count);
            log("I should able to note the Geo History log records count.","I am able to note the Geo History records count.");
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("I check that log record is shown for \"([^\"]*)\" change in Geo History")
    public void iCheckThatLogRecordIsShownForChangeInGeoHistory(String logEntryFor) {
        try{
            switch (logEntryFor){
                case "Status":
                case "Region":
                case "Timezone":
                case "Geo-Coding":
                    String oldRecordCount= (String) cucumberContextManager.getScenarioContext("RECORDS_COUNT");
                    List<WebElement> logsRecordCount = admin_GeofencePage.Rows_GeoHistoryLogs();
                    int newCount = logsRecordCount.size();
                    int oldCount = Integer.parseInt(oldRecordCount);
                    testStepVerify.isEquals(oldCount+1,newCount);
                    cucumberContextManager.setScenarioContext("RECORDS_COUNT", newCount);
                    break;
                default:
                    break;
            }
            cucumberContextManager.setScenarioContext("GEO_CHANGES",logEntryFor);

        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("I click on changes hyperlink")
    public void iClickOnChangesHyperlink() {
        try{
            List<WebElement> logsRecordCount = admin_GeofencePage.Rows_GeoHistoryLogs();
            //int value = logsRecordCount.size()-1;
            action.click(admin_GeofencePage.Link_Changes());
            log("I should able to click on changes hyperlink.","I am able to click on changes hyperlink.");
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("I should see all fields with old and new changed value")
    public void iShouldSeeAllFieldsWithOldAndNewChangedValue() {
        try{
            //List<WebElement> logsRecordCount = admin_GeofencePage.Rows_GeoHistoryLogs();
            //int value = logsRecordCount.size()-1;
            testStepVerify.isElementDisplayed(admin_GeofencePage.Text_Fields(),"I should be able to see Fields text.","I am able to see Fields text.","I am not able to see Fields text.");
            testStepVerify.isElementDisplayed(admin_GeofencePage.Text_OldValue(),"I should be able to see Old Value text.","I am able to see Old Value text.","I am not able to see Old Value text.");
            testStepVerify.isElementDisplayed(admin_GeofencePage.Text_NewValue(),"I should be able to see New Value text.","I am able to see New Value text.","I am not able to see New Value text.");

        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("I check correct log details are shown")
    public void iCheckCorrectLogDetailsAreShown() {
        try {
            String expectedModifiedBy = PropertyUtility.getDataProperties("admin.testuser.name");
            Date date1 = new Date();
            DateFormat dtf = new SimpleDateFormat("M/dd/YYYY");

            String expedtedModifiedDate = dtf.format(date1);
            String expectedPhone = PropertyUtility.getDataProperties("admin.testuser");
            String expectedChanges = (String) cucumberContextManager.getScenarioContext("GEO_CHANGES");

            testStepVerify.isEquals(action.getText(admin_GeofencePage.Value_ModifiedDate()), expedtedModifiedDate);
            testStepVerify.isEquals(action.getText(admin_GeofencePage.Value_ModifiedBy()), expectedModifiedBy);
            testStepVerify.isEquals(action.getText(admin_GeofencePage.Value_Phone()), expectedPhone);
            testStepVerify.isEquals(action.getText(admin_GeofencePage.Value_Changes()), expectedChanges);
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("I change the \"([^\"]*)\" for the geofence")
    public void iChangeTheForTheGeofence(String valueChange,DataTable data) {
        try{
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            switch (valueChange){
                case "Region":
                    String region = dataMap.get("New_Region").trim();
                    action.selectElementByText(admin_GeofencePage.Dropdown_Region(),region);
                    break;
                case "Timezone":
                    String timeZone= dataMap.get("Geo-TimeZone").trim();
                    action.selectElementByText(admin_GeofencePage.Dropdown_Timezone(), timeZone);
                    break;
                case "Geo-Coding":
                    String primaryGeoCoding = dataMap.get("Primary").trim();
                    String secondaryGeoCoding = dataMap.get("Secondary").trim();
                    action.clearSendKeys(admin_GeofencePage.TextBox_Primary(),primaryGeoCoding);
                    action.clearSendKeys(admin_GeofencePage.TextBox_Secondary(),secondaryGeoCoding);
                    break;
                default:
                    break;
            }
            log("I should able to change " + valueChange + "for the geofence.","I am able to changed " + valueChange + "for the geofence.");

        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("I load Geofence Attributes Page")
    public void iLoadGeofenceAttributesPage() {
        try{
            action.waitUntilIsElementExistsAndDisplayed(admin_geofenceAtrributesPage.Menu_Attributes(), (long) 3000);
            action.click(admin_geofenceAtrributesPage.Menu_Attributes());
            log("I load Geofence Attributes Page",
                    "I have loaded Geofence Attributes Page", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @Then("^The date on the downloaded csv should have proper date format$")
    public void the_date_on_the_downloaded_csv_should_have_proper_date_format() throws Throwable {
        try {
            String []downloadedCsvFileNameForGeofenceZipcodes = cucumberContextManager.getScenarioContext("CSV_FILE_NAME").toString().replace(".csv", "").split("_");
            String yearMonthDate = LocalDate.now().toString().replaceAll("-","");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date =sdf.parse(downloadedCsvFileNameForGeofenceZipcodes[2]);
            boolean dateIsparsedProperly = false;
            if(date.toString().length()==28){
                dateIsparsedProperly=true;
            }
            String initialFileName = PropertyUtility.getDataProperties("geofence.csv.file.name");
            String expectedFileName = initialFileName + yearMonthDate;
            String downloadedFileName = downloadedCsvFileNameForGeofenceZipcodes[0] + "_" +downloadedCsvFileNameForGeofenceZipcodes[1] +"_" +downloadedCsvFileNameForGeofenceZipcodes[2];
            testStepAssert.isTrue(dateIsparsedProperly,"The date should be in proper format","The date is in proper format","The date is not in proper format");
            testStepAssert.isEquals(downloadedFileName, expectedFileName, "The file name should be " + expectedFileName,
                    "The file name is " + expectedFileName, "The file name is not " + expectedFileName);
        }catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

}
