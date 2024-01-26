package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.*;
import com.bungii.web.pages.admin.Admin_DriverVerificationPage;
import com.bungii.web.pages.admin.Admin_DriversPage;
import com.bungii.web.pages.admin.Admin_LogviewPage;
import com.bungii.web.pages.admin.Admin_TripDetailsPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_DriverDetails extends DriverBase{
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    Admin_DriversPage admin_Driverspage = new Admin_DriversPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    Admin_LogviewPage admin_logviewPage = new Admin_LogviewPage();
    private static LogUtility logger = new LogUtility(Admin_DriverDetails.class);

    @Then("^Set the Geofence dropdown to \"([^\"]*)\"$")
    public void set_the_geofence_dropdown_to_something(String strArg1) throws Throwable {
      //  action.selectElementByText(admin_Driverspage.Dropdown_Geofence(), "-- All --");
        try{
        utility.resetGeofenceDropdown();
        log("I set the Geofence dropdown to" + strArg1,
                "I have set the Geofence dropdown to" + strArg1, false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @When("^I search driver \"([^\"]*)\"$")
    public void i_search_driver_something(String driver) throws Throwable {
        try{
        action.clearSendKeys(admin_Driverspage.Textbox_SearchCriteria(),driver+ Keys.ENTER);
        cucumberContextManager.setScenarioContext("DRIVER",driver);
        log("I search driver " + driver,
                "I have searched driver " + driver, false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The \"([^\"]*)\" page should be displayed$")
    public void the_something_page_should_be_displayed(String page) throws Throwable {
        try{
        switch (page){
            case "Trip List":
                testStepAssert.isElementDisplayed(admin_Driverspage.Label_TripListHeader(),"Drivers Trip List page should be displayed","Drivers Trip List page is displayed", "Drivers Trip List page is not displayed");
                break;
        }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^List of trips completed by the driver should be displayed on the Trip List Page$")
    public void list_of_trips_completed_by_the_driver_should_be_displayed_on_the_trip_list_page() throws Throwable {
        try{
        action.selectElementByText(admin_Driverspage.Dropdown_SearchForPeriod(), "The Beginning of Time");
        if(!action.getPagesource().contains("No Data."))
        testStepAssert.isElementDisplayed(admin_Driverspage.Grid_TripList(),"Trip List grid should be displayed","Trip List grid is displayed", "Trip List grid is not displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on \"([^\"]*)\" Link$")
    public void i_click_on_some_link(String Linkname){
        try{
        switch (Linkname){
            case "View Profile":
                action.click(admin_Driverspage.Link_ViewProfile());
                break;
            case "Download Query Result(comma)":
                action.click(admin_logviewPage.Link_DownloadQueryResultWithComma());
                break;
            case "Download Query Result(pipe)":
                action.click(admin_logviewPage.Link_DownloadQueryResultWithPipe());
                break;

        }
        log("I click on "+Linkname+" Link",
                "I have clicked on "+Linkname+" Link", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The Delivery List page should display the delivery in \"([^\"]*)\" state$")
    public void the_trip_list_page_should_display_the_trip_in_something_state(String status) throws Throwable {
        try{
        String scheduled_time = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
        String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        String timezone = utility.getTripTimezone(geofence);
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
        String XPath = "";
      
        if (!scheduled_time.equalsIgnoreCase("NOW")) {
            Date inputdate = new SimpleDateFormat("MMM dd, hh:mm a z").parse(scheduled_time);
            inputdate.setYear(new Date().getYear());
            ZoneId zoneId = TimeZone.getDefault().toZoneId();
            if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date()))
            {
             if (timezone=="EDT" || timezone=="CDT")
                     inputdate.setHours(inputdate.getHours() + 1); /// EDT time when changed to EST it looses 1 hour thus added this code
            }
            String formattedDate = new SimpleDateFormat("MMM dd, yyyy hh:mm a z").format(inputdate); // removed ss
            formattedDate= utility.getbungiiDayLightTimeValue(formattedDate);
            XPath = String.format("//a[text()='%s']/parent::td/following-sibling::td[8]", formattedDate);
            String actualStatus = action.getText(SetupManager.getDriver().findElement(By.xpath(XPath)));
            testStepAssert.isEquals(actualStatus,status,
                    "The delivery status should be displayed correctly.",
                    "The delivery status is displayed correctly.",
                    "The delivery status is not displayed correctly.");
        }
        else
        {
            String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String tripType[] = tripTypeAndCategory.split(" ");
            String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
            String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String driver = driver1;
            if (tripType[0].equalsIgnoreCase("duo"))
                driver = driver1 + "," + driver2;
            char[] delimiters = { ' ', '_' };
            XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[3]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : WordUtils.capitalizeFully(tripType[0], delimiters), driver, customer);

        }

        int retrycount =10;
        boolean retry = true;
        while (retry == true && retrycount >0) {
            try {
                WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                retry = false;
            } catch (Exception ex) {
                SetupManager.getDriver().navigate().refresh();
                retrycount--;
                retry = true;
            }

        }

        cucumberContextManager.setScenarioContext("XPATH",XPath);
        testStepAssert.isElementTextEquals(action.getElementByXPath(XPath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }

    @Then("^The Driver Trip List page should display the trip in \"([^\"]*)\" state$")
    public void the_driver_trip_list_page_should_display_the_trip_in_something_state(String status) throws Throwable {
        try{
        String scheduled_time = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
        String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        String timezone = utility.getTripTimezone(geofence);
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
        String XPath = "";
        if (!scheduled_time.equalsIgnoreCase("NOW")) {
            Date inputdate = new SimpleDateFormat("MMM dd, hh:mm a z").parse(scheduled_time);
            inputdate.setYear(new Date().getYear());
            if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date()))
            {
                if (timezone=="CST")
                    inputdate.setHours(inputdate.getHours()+1);
            }
            String formattedDate = new SimpleDateFormat("MMM dd, yyyy hh:mm a z").format(inputdate);
            String[] status1 = status.split("");

                XPath = String.format("//a[text()='%s']/parent::td/following-sibling::td[contains(.,'%s') and contains(.,'%s')]", formattedDate, status1[0],status1[1]);

        }
        else
        {
            String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String tripType[] = tripTypeAndCategory.split(" ");
            String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
            String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String driver = driver1;
            if (tripType[0].equalsIgnoreCase("duo"))
                driver = driver1 + "," + driver2;
            
            XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[2]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : StringUtils.capitalize(tripType[0]), driver, customer);

        }

        int retrycount =10;
        boolean retry = true;
        while (retry == true && retrycount >0) {
            try {
                WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                retry = false;
            } catch (Exception ex) {
                SetupManager.getDriver().navigate().refresh();
                retrycount--;
                retry = true;
            }

        }

        cucumberContextManager.setScenarioContext("XPATH",XPath);
        String actualStatus = action.getElementByXPath(XPath).getText();
        //testStepAssert.isEquals(actualStatus, status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
        testStepAssert.isElementTextEquals(action.getElementByXPath(XPath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }

    @And("^I do not see regions listed under Geofence information on Driver details page$")
    public void i_do_not_see_regions_listed_under_geofence_information_on_driver_details_page() throws Throwable {
        try{
            List<HashMap<String, Object>> regions = dbUtility.getRegionsList();
                for ( HashMap<String, Object> region : regions) {
                    String Xpath = String.format("//strong[contains(text(),'Other Geofences')]/parent::td/parent::tr/following-sibling::tr/td[text()='%s']",region);
                    testStepAssert.isNotElementDisplayed(admin_Driverspage.findElement(Xpath, PageBase.LocatorType.XPath,true), "Region" + regions + " should not be displayed" , "Region" + regions + " is displayed" , "Region" + regions + " is not displayed");
                }
                action.click(admin_TripDetailsPage.Button_Cancel());
            } catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("I verify correct {string} is set in db")
    public void iVerifyCorrectDisbursementTypeIsSetInDb(String type) throws Throwable{
        try{
            switch (type) {
                case "Disbursement type":
                    String pickUpRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    String driverOne = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
                    String driverTwo = (String) cucumberContextManager.getScenarioContext("DRIVER_2_PHONE");
                    String disbursmentTypeDriverOne = dbUtility.getDisbursementType(type, pickUpRef, driverOne);
                    String disbursmentTypeDriverTwo = dbUtility.getDisbursementType(type, pickUpRef, driverTwo);
                    testStepAssert.isEquals(disbursmentTypeDriverOne, PropertyUtility.getDataProperties("same.day.payment.disbursement.type.value"),
                            "Correct disbursement type value should be set for same day payment setting",
                            "Correct disbursement type value is set for same day payment setting",
                            "Incorrect disbursement type value is set for same day payment setting");
                    testStepAssert.isEquals(disbursmentTypeDriverTwo, PropertyUtility.getDataProperties("weekly.payment.disbursement.type.value"),
                            "Correct disbursement type value should be set for weekly payment setting",
                            "Correct disbursement type value is set for weekly payment setting",
                            "Incorrect disbursement type value is set for weekly payment setting");
                    break;
                case "Records":

                    String driverPhone = (String) cucumberContextManager.getScenarioContext("DriverPhone");
                    String recordphone = dbUtility.getRecord(driverPhone);
                    testStepAssert.isEquals(driverPhone,recordphone, "Driver record should be present in db after signup", "Driver record is present in db after signup",
                            "Driver record is not present in db after signup");
                    break;

            }
        }

                catch (Exception e) {
                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step should be successful", "Error performing step,Please check logs for more details",
                            true);
                }
            }


    @Then("^The delivery \"([^\"]*)\" eligible for driver \"([^\"]*)\"$")
    public void the_delivery_something_eligible_for_driver_something(String driverEligibility, String driverName) throws Throwable {
      try {
          String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
          String []driverFirstName =driverName.split(" ") ;
          String driverPhoneNum = new DbUtility().getDriverPhoneNumber(driverFirstName[0]);
          boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
          if (driverEligibility.equals("Should be")) {
              testStepAssert.isTrue(isDriverEligible, "Driver " + driverName + " should be eligible for delivery having pickup ref " + pickupRequest,
                      "Driver " + driverName + " is eligible for delivery having pickup ref " + pickupRequest,
                      "Driver " + driverName + " is not eligible for delivery having pickup ref " + pickupRequest);
          } else {
              testStepAssert.isFalse(isDriverEligible, "Driver " + driverName + " should not be eligible for delivery having pickup ref " + pickupRequest,
                      "Driver " + driverName + " is not eligible for delivery having pickup ref " + pickupRequest,
                      "Driver " + driverName + " is eligible for delivery having pickup ref " + pickupRequest);
          }
      }catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I check if Driver status is \"([^\"]*)\" for driver \"([^\"]*)\"$")
    public void i_check_if_driver_status_is_something_for_driver_something(String driverStatus, String driverName) throws Throwable {
        try{
        String [] onlyFirstName = driverName.split(" ");
        String DriverActiveORInActive = new DbUtility().isDriverActive(onlyFirstName[0]);
        if (driverStatus.equals("Active")) {
            testStepAssert.isTrue(DriverActiveORInActive.equals("1"), "Driver " + driverName + " status should is active",
                    "Driver " + driverName + " status is active",
                    "Driver " + driverName + " status  is " + DriverActiveORInActive + " ,it should be 1");
        }
        else {
            testStepAssert.isTrue(DriverActiveORInActive.equals("0"), "Driver " + driverName + " status should is inactive",
                    "Driver " + driverName + " status is inactive",
                    "Driver " + driverName + " status  is " + DriverActiveORInActive + " ,it should be 0");
        }
    }catch(Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^I search by \"([^\"]*)\"$")
    public void i_search_by_something(String SerachCriteria) throws Throwable{
        try
        {
            switch (SerachCriteria){
                case "Customer Name":
                    String CustomerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
                    action.clearSendKeys(admin_Driverspage.Textbox_SearchCriteria(), CustomerName);
                    action.click(admin_Driverspage.Button_Search());
                    break;
            }

            log("I search by "+SerachCriteria,
                    "I have searched by " +SerachCriteria, false);
        }

        catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^Column \"([^\"]*)\" should display correct details$")
    public void column_something_should_display_correct_details(String Column) throws Throwable{
        try
        {
            switch (Column){
                case "Customer":
                    String ExpectedCustomerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
                    String ActualCustomerName=action.getText(admin_Driverspage.Text_CustomerNameRow1());
                    testStepAssert.isEquals(ExpectedCustomerName , ActualCustomerName,ExpectedCustomerName +" should be displayed",ActualCustomerName+" is displayed", ActualCustomerName+" is displayed instead of "+ExpectedCustomerName );
                    action.clear(admin_Driverspage.Textbox_SearchCriteria());
                    action.click(admin_Driverspage.Button_Search());
                    break;
            }
            log("Should display correct details",
                    "have displayed correct details under " +Column, false);
        }

        catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
