package com.bungii.android.stepdefinitions.Customer;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.ScheduledTripsPage;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.driver.*;
import com.bungii.android.stepdefinitions.CommonSteps;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bungii.common.manager.ResultManager.*;

public class ScheduledBungiiSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(PromosSteps.class);
    ActionManager action = new ActionManager();
    ScheduledBungiisPage scheduledBungiisPage;
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    CommonSteps commonSteps = new CommonSteps();
    InvitePage invitePage = new InvitePage();
    InProgressBungiiPages inProgressBungiiPages = new InProgressBungiiPages();
    BungiiRequest bungiiRequest = new BungiiRequest();
    BungiiAcceptedPage bungiiAcceptedPage = new BungiiAcceptedPage();
    BungiiCompletedPage bungiiCompletedPage = new BungiiCompletedPage();
    BungiiDetailsPage bungiiDetailsPage = new BungiiDetailsPage();
    EstimatePage estimatePage = new EstimatePage();
    ScheduledBungiiPage scheduledBungiiPage = new ScheduledBungiiPage();
    WantDollar5Page wantDollar5Page = new WantDollar5Page();
    HomePage homePage = new HomePage();
    PromosPage promosPage=new PromosPage();
    ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();
    SetPickupTimePage setPickupTimePage = new SetPickupTimePage();
    UpdateStatusPage updateStatusPage= new UpdateStatusPage();
    public ScheduledBungiiSteps(ScheduledBungiisPage scheduledBungiisPage) {
        this.scheduledBungiisPage = scheduledBungiisPage;
    }

    @Then("^Bungii must be removed from \"([^\"]*)\" screen$")
    public void bungii_must_be_removed_from_something_screen(String screen) {
        try {
            String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            //        tripTime="Mar 14, 01:15 PM";
            Thread.sleep(20000);
            action.scrollToTop();
            boolean isBungiiPresent = isBungiiPresent(tripNoOfDriver, tripTime);
            //do half screen swipe if Bungii is present
            if (isBungiiPresent) {
                action.scrollToTop();
                isBungiiPresent = isBungiiPresent(tripNoOfDriver, tripTime);
            }
            testStepVerify.isFalse(isBungiiPresent, "Bungii must be removed from " + screen + " screen",
                    "Bungii is deleted", "Bungii is not deleted");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I select already scheduled bungii$")
    public void i_select_already_scheduled_bungii() {
        try {
            String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));

            selectBungii(tripNoOfDriver, tripTime);
            pass("I select already scheduled bungii", "I selected already scheduled bungii of " + tripNoOfDriver + " type and at time: " + tripTime, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "There is Problem in selecting Scheduled Bungii from customers scheduled list | Expected Bungii : "+ String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME")), true);
        }
    }

    @Then("^I verify the \"([^\"]*)\"$")
    public void i_verify_the_something(String strArg1) throws Throwable {
        try {
            String driver1Name, driver2Name;
            String appDriver1Name, appDriver2Name;
            switch (strArg1) {
                case "solo driver names":
                    driver1Name=(String)cucumberContextManager.getScenarioContext("ASSIGNED_DRIVER1_NAME");

                    appDriver1Name=scheduledTripsPage.Text_Driver1Name().getText();
                    if (driver1Name.contains(appDriver1Name))
                    {
                        testStepAssert.isTrue(true, "Driver name match.", "Driver name doesn't match.");
                    }
                    else{
                        testStepAssert.isFail("Driver name doesn't match." + appDriver1Name +" | "+ driver1Name);
                    }
                    break;

                case "duo driver names":
                    driver1Name=(String)cucumberContextManager.getScenarioContext("ASSIGNED_DRIVER1_NAME");

                    appDriver1Name=scheduledTripsPage.Text_Driver1Name().getText();
                    driver2Name=(String)cucumberContextManager.getScenarioContext("ASSIGNED_DRIVER2_NAME");

                    appDriver2Name=scheduledTripsPage.Text_Driver2Name().getText();
                    if (driver1Name.contains(appDriver1Name) && driver2Name.contains(appDriver2Name) )
                    {
                        testStepAssert.isTrue(true, "Driver names match.", "Driver names doesn't match.");
                    }
                    else{
                        testStepAssert.isFail("Driver name doesn't match." + appDriver1Name +" | "+ driver1Name + " & "+ appDriver2Name+ " | " + driver2Name );
                    }
                    break;

                case "control driver name":
                    driver1Name=(String)cucumberContextManager.getScenarioContext("ASSIGNED_DRIVER1_NAME");

                    appDriver1Name=scheduledTripsPage.Text_Driver1Name().getText();
                    if (driver1Name.contains(appDriver1Name))
                    {
                        testStepAssert.isTrue(true, "Driver name match.", "Driver name doesn't match.");
                    }
                    else{
                        testStepAssert.isFail("Driver name doesn't match." + appDriver1Name +" | "+ driver1Name);
                    }
                    break;

                case "noncontrol driver name":
                    driver2Name=(String)cucumberContextManager.getScenarioContext("ASSIGNED_DRIVER2_NAME");

                    appDriver2Name=scheduledTripsPage.Text_Driver2Name().getText();
                    if (driver2Name.contains(appDriver2Name))
                    {
                        testStepAssert.isTrue(true, "Driver name match.", "Driver name doesn't match.");
                    }
                    else{
                        testStepAssert.isFail("Driver name doesn't match." + appDriver2Name +" | "+ driver2Name);
                    }
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^The status on \"([^\"]*)\" should be displayed as \"([^\"]*)\"$")
    public void the_status_on_something_should_be_displayed_as_something(String strArg1, String status) throws Throwable {
        try {
            if(status.equalsIgnoreCase("estimated cost"))
                testStepVerify.isElementTextEquals(scheduledBungiisPage.Text_TripStatus(), (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
            else
                testStepVerify.isElementTextEquals(scheduledBungiisPage.Text_TripStatus(), status);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^trips status on bungii details should be \"([^\"]*)\"$")
    public void trips_status_on_bungii_details_should_be_something(String strArg1) throws Throwable {

        try {
            String tripStatus = "", actualDriverName = "";
            switch (strArg1.toLowerCase()) {
                case "driver 1 - contacting drivers":
                    tripStatus = action.getText(bungiiDetailsPage.Text_Driver1Status());
                    testStepVerify.isEquals(tripStatus, "Contacting");
                    testStepVerify.isElementEnabled(bungiiDetailsPage.Text_Driver1StatusTag(), " Driver # 1 tag should be displayed");
                    break;
                case "driver 2 - contacting drivers":
                    tripStatus = action.getText(bungiiDetailsPage.Text_Driver2Status());
                    testStepVerify.isEquals(tripStatus, "Contacting");
                    testStepVerify.isElementEnabled(bungiiDetailsPage.Text_Driver2StatusTag(), " Driver # 2 tag should be displayed");
                    break;

                case "driver1 name":
                    actualDriverName = action.getText(bungiiDetailsPage.Text_Driver1Name());
                    String expectedDriver1Name = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
                    if (expectedDriver1Name.contains(actualDriverName)) {
                        testStepVerify.isElementDisplayed(bungiiDetailsPage.Text_Driver1Name(), " Driver # 1 Name should be displayed", "Driver # 1 Name should be displayed", "Driver # 1 Name is not displayed");
                    }
                    break;

                case "driver2 name":
                    actualDriverName = action.getText(bungiiDetailsPage.Text_Driver2Name());
                    String expectedDriver2Name = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
                    if (expectedDriver2Name.contains(actualDriverName)) {
                        testStepVerify.isElementEnabled(bungiiDetailsPage.Text_Driver2Name(), " Driver # 2 Name should be displayed");
                    }
                    break;

                case "driver 2 - contacting drivers1":
                    tripStatus = action.getText(bungiiDetailsPage.Text_Driver2Status1());
                    testStepVerify.isEquals(tripStatus, "Contacting");
                    testStepVerify.isElementEnabled(bungiiDetailsPage.Text_Driver2StatusTag1(), " 1Driver # 2 tag should be displayed");
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I try to schedule bungii for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_try_to_schedule_bungii_for_something_for_something(String strArg1, String tripType) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "today - after working hour":
                    selectBungiiTime(0, "11", "45", "PM", tripType);
                    log("I select time for trip as 11:45  pm", "I selected time for trip as 11:45  pm");
                    break;
                case "tommorow - before working hour":
                    selectBungiiTime(1, "12", "00", "AM", tripType);
                    log("I select time for trip tomorrow 12 00 AM", "I selected time for trip as  tomorrow 12 00 AM");
                    break;
                case "today+5":
                    selectBungiiTime(5, "", "", "", tripType);
                    log("I select time for trip today+5 1:00 pm", "I selected time for trip as today+5 1:00 pm");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");

            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I confirm trip with following detail$")
    public void i_confirm_trip_with_following_detail(DataTable tripInformation) throws Throwable {
        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String day = data.get("Day"),
                    tripType = data.get("Trip Type");
            verifyDisclaimer(tripType);
            action.scrollToTop();
            selectBungiiTime(Integer.parseInt(day), "05", "30", "PM", tripType);
            log("I confirm trip for " + day +" and type "+tripType, "I confirmed trip for " + day +" and type "+ tripType);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I confirm trip with following details$")
    public void i_confirm_trip_with_following_details(DataTable tripInformation) throws Throwable {
        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String day = data.get("Day"),
                    tripType = data.get("Trip Type"),
                    time = data.get("Time");
            if (time.equalsIgnoreCase("<TIME WITHIN TELET>") || time.equalsIgnoreCase("<TIME WITHIN TELET OF CUSTOMER 1>") || time.equalsIgnoreCase("<TIME WITHIN TELET OF CUSTOMER 2>")) {

                String teletTime = (String) cucumberContextManager.getScenarioContext("TELET");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                //By default data is in UTC
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date teletTimeInUtc = null;
                try {
                    teletTimeInUtc = formatter.parse(teletTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(teletTimeInUtc);
                int mnts = calendar.get(Calendar.MINUTE);

                calendar.set(Calendar.MINUTE, mnts - 30);
                int unroundedMinutes = calendar.get(Calendar.MINUTE);
                int mod = unroundedMinutes % 15;
                calendar.add(Calendar.MINUTE, (15 - mod));
                calendar.set(Calendar.SECOND, 0);

                Date nextQuatter = calendar.getTime();
                String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();

                DateFormat formatterForLocalTimezone = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

                formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

                String strdate = formatter.format(calendar.getTime());
                Date teletTimeInLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(strdate);

                String[] dateScroll = commonSteps.bungiiTimeForScroll(teletTimeInLocal);
                selectBungiiTime(Integer.parseInt(day), dateScroll[1], dateScroll[2], dateScroll[3], tripType);

            } else if (time.equals("<TELET TIME OVERLAP WITH START TIME OF CUSTOMER 1>")) {
                //do nothing, for duo  trip already required time is selected
            } else if (time.equals("<AFTER TELET>")) {

                String teletTime = (String) cucumberContextManager.getScenarioContext("TELET");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                //By default data is in UTC
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date teletTimeInUtc = null;
                try {
                    teletTimeInUtc = formatter.parse(teletTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(teletTimeInUtc);
                int mnts = calendar.get(Calendar.MINUTE);

                calendar.add(Calendar.HOUR,1);
                calendar.set(Calendar.MINUTE, mnts);
                int unroundedMinutes = calendar.get(Calendar.MINUTE);
                int mod = unroundedMinutes % 15;
                calendar.add(Calendar.MINUTE, (15 - mod));
                calendar.set(Calendar.SECOND, 0);

                Date nextQuatter = calendar.getTime();
                String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();

                DateFormat formatterForLocalTimezone = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

                formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

                Date abc= calendar.getTime();
                String strdate = formatter.format(calendar.getTime());
                Date teletTimeInLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(strdate);


                String[] dateScroll = commonSteps.bungiiTimeForScroll(teletTimeInLocal);
                selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3], tripType);

                String custTripTime= action.getText(estimatePage.Text_ScheduledTime());
                cucumberContextManager.setScenarioContext("SCHEDULED_TIME",custTripTime);
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I get the pickupref$")
    public void i_get_the_pickupref() throws Throwable {
        try {
           String phone =  (String)cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST",dbUtility.getCustomersMostRecentBungii(phone));

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error Occured While fetching pickuprequest",
                    true);
        }
    }

    @And("I get the pickupref for \"([^\"]*)\"")
    public void i_get_the_pickupref_for_somenumber(String custNumber) throws Throwable {
        try {
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST2",dbUtility.getCustomersMostRecentBungii(custNumber));

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error Occured While fetching pickuprequest",
                    true);
        }
    }

    @And("^I select \"([^\"]*)\" from pop-up$")
    public void i_select_something_from_popup(String notificationButton) throws Throwable {
       try{
           switch (notificationButton){
               case "View Request":
                   action.click(bungiiRequest.Alert_ViewRequest());
                   break;
           }
           log("I should be able to click on the notification button","I was able to click on the notification button",false);

       }catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error Occured While Requesting Bungii. Check Screenshot for more details",
                   true);
       }
    }

    @And("^I should select the \"([^\"]*)\" customer on driver app$")
    public void i_should_select_the_something_customer_on_driver_app(String customer) throws Throwable {
        try{
            action.click(scheduledBungiiPage.Customer_ScheduledDelivery());
            pass("I should able to select scheduled bungii for customer"+customer,"I have selected scheduled bungii for customer"+customer);
        }
        catch (Exception e) {
            //logger.error("Error performing step", SetupManager.getDriver().getPageSource());
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" screen$")
    public void i_click_something_button_on_something_screen(String button, String strArg2) throws Throwable {
        try {

            switch (button)
            {
                case "Done":
                    action.scrollToBottom();
                    action.click(estimatePage.Button_DoneOnSuccess());
                    break;
                case "On To The Next One":
                    action.scrollToBottom();
                    action.click(estimatePage.Button_NextBungii());
                    break;
                case "Driver Submit":
                    action.click(updateStatusPage.Button_DriverSubmit());
                    break;
                case "Scheduled Bungiis":
                    action.click(scheduledBungiiPage.Button_ThreeDot());
                    Thread.sleep(1000);
                    action.click(scheduledBungiiPage.Link_ScheduledBungiis());
                    break;
                case "Customer Signature":
                    action.click(inProgressBungiiPages.Tab_CustomerSignature());
                    break;
                case "Submit":
                    action.click(inProgressBungiiPages.Button_Submit());
                    break;

            }
            log("I should be able to click on the "+button+" button",
                    "I am able to click on the "+button+" button",false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error Occured While Requesting Bungii. Check Screenshot for more details",
                    true);
        }
    }

    @When("^I click \"([^\"]*)\" button on Bungii Request screen$")
    public void i_click_something_button_on_bungii_request_screen(String buttonName) throws Throwable {
       try{
           switch (buttonName){

               case "ACCEPT":
                   Thread.sleep(15000);
                   action.click(estimatePage.Button_BungiiAccept());
                   break;

               case "REJECT":
                   action.click(estimatePage.Button_CancelRequest());
                   break;

               case "ACCEPT NOTIFICATION":
                   action.click(bungiiRequest.Button_Accept());
                   break;
           }
           log("I should be able to click on "+buttonName,"I was able to click on "+buttonName,false);
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }

    @When("^I click \"([^\"]*)\" button on SCHEDULED BUNGII screen$")
    public void i_click_something_button_on_scheduled_bungii_screen(String buttonName) throws Throwable {

        try{
            switch (buttonName){

                case "ACCEPT":
                    action.click(estimatePage.Button_AcceptRequestScheduledBungii());
                    break;

                case "REJECT":
                    action.click(estimatePage.Button_RejectRequestScheduledBungii());
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I check if the status is \"([^\"]*)\"$")
    public void i_check_if_the_status_is_something(String status) throws Throwable {
        try{
            switch (status){
                case "ONLINE":
                    testStepAssert.isTrue(action.isElementPresent(bungiiCompletedPage.Slider_Online()),
                            "The status should be online",
                            "The status is not online");
                    break;
                case "OFFLINE":
                    testStepAssert.isTrue(action.isElementPresent(bungiiCompletedPage.Slider_Offline()),
                            "The status should be offline",
                            "The status is not offline");
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I check online or offline pop up is displayed$")
    public void i_check_online_or_offline_pop_up_is_displayed() throws Throwable {
        try {
                testStepAssert.isElementDisplayed(bungiiCompletedPage.Notification_DriverStatus(),
                        "The driver should get a pop-up to change status",
                        "The driver got a pop-up to change status",
                        "The driver did not get a pop-up to change status");

                String header = bungiiCompletedPage.Notification_DriverStatus().getText();
                String expectedHeader =PropertyUtility.getMessage("header.stayOnline.goOffline.notification");
                testStepAssert.isEquals(header,expectedHeader,
                        expectedHeader+" should be displayed as header",
                    expectedHeader+" is displayed as header",
                    expectedHeader+" is not displayed as header");

                String subText = bungiiCompletedPage.Text_NotificationDriverStatus().getText();
                String expectedSubText =PropertyUtility.getMessage("subHeader.stayOnline.goOffline.notification");
                testStepAssert.isEquals(subText,expectedSubText,
                        expectedSubText+" should be displayed as sub text",
                        expectedSubText+" is displayed as sub text",
                    expectedSubText+" is not displayed as sub text");

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I check online or offline pop up is not displayed$")
    public void i_check_online_or_offline_pop_up_is_not_displayed() throws Throwable {
        try{
            testStepAssert.isFalse(action.isElementPresent(bungiiCompletedPage.Notification_DriverStatus(true)),
                    "The driver should not get a pop-up to change status",
                    "The driver did not get a pop-up to change status",
                    "The driver did get a pop-up to change status");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I click \"([^\"]*)\" button on the \"([^\"]*)\" screen$")
    public void i_click_something_button_on_the_something_screen(String strArg1, String strArg2) throws Throwable {
        try {
            Thread.sleep(2000);
            switch (strArg1) {
                case "ACCEPT":
                    action.click(bungiiRequest.Button_Accept());
                    break;
                case "Ok":
                    action.click(bungiiAcceptedPage.Button_OK());
                    break;
                case "On To The Next One":
                    action.click(bungiiCompletedPage.Button_OnToTheNext());
                    break;
                case "YES, I'LL TAKE $5":
                    action.click(wantDollar5Page.Button_Take5());
                    int retrycount =4;
                    boolean retry = true;
                    while (retry == true && retrycount >0) {
                        try {
                            action.click(wantDollar5Page.Button_Take5());
                            if(invitePage.FBApp_PostLink(true).isDisplayed()==true)
                            {
                                retrycount=0;
                                retry = false;
                            }
                            else
                            {
                                retrycount--;
                                retry = true;
                            }

                        } catch (Exception ex) {
                            retrycount--;
                            retry = true;
                        }
                    }
                    break;
                case "I DON'T LIKE FREE MONEY":
                    action.click(wantDollar5Page.Button_NoFreeMoney());
                    break;
                case "OK":
                    Thread.sleep(5000);
                    action.click(estimatePage.Button_OkDriverNotAvailable());
                    break;
                case "Schedule Bungii":
                    Thread.sleep(5000);
                    action.click(estimatePage.Button_Schedule());
                    break;
                case "Next Bungii":
                    Thread.sleep(5000);
                    action.click(bungiiCompletedPage.Button_NextBungii());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I try to schedule bungii for \"([^\"]*)\"$")
    public void i_try_to_schedule_bungii_for_something(String strArg1, DataTable tripInformation) throws Throwable {
        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String day = data.get("Day"), tripType = data.get("Trip Type");

            selectBungiiTime(Integer.parseInt(day), "05", "30", "PM", tripType);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I select 1st trip from scheduled bungii$")
    public void i_select_first_already_scheduled_bungii() {
        try {
            action.click(scheduledBungiiPage.Cell_FirstTrip());
            pass("I select already scheduled bungii", "I selected first scheduled bungii", true);
        } catch (Exception e) {
            logger.error("Error performing step", SetupManager.getDriver().getPageSource());
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    private void selectHour(String hour) throws InterruptedException {
        int hrs = Integer.parseInt(action.getText(estimatePage.Text_TimeHourPicker()));
        if (hrs == Integer.parseInt(hour)) {
            //do nothing
        } else if (hrs > Integer.parseInt(hour)) {
            WebElement back = estimatePage.Text_TimeHourPickerBack();
            for (int i = 0; i < (hrs - Integer.parseInt(hour)); i++) {
                action.click(back);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (hrs < Integer.parseInt(hour)) {
            WebElement next = estimatePage.Text_TimeHourPickerNext();
            for (int i = 0; i < ( Integer.parseInt(hour)-hrs); i++) {
                action.click(next);
                Thread.sleep(2000);
            }
        }
    }

    private void selectMins(String minutes) {
        int mins = Integer.parseInt(action.getText(estimatePage.Text_TimeMinutesPicker()));
        if (mins == Integer.parseInt(minutes)) {
            //do nothing
        } else {
            for (int i = 0; i < 4; i++) {
                action.click(estimatePage.Text_TimeMinutesPickerNext());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mins = Integer.parseInt(action.getText(estimatePage.Text_TimeMinutesPicker()));
                if (mins == Integer.parseInt(minutes))
                    break;
            }
        }
    }



    @Then("^trips status should be \"([^\"]*)\"$")
    public void trips_status_should_be_something(String status) throws Throwable {
        try{
            String actualStatus="";
            switch (status){
                case "Contacting Other Driver":
                    actualStatus=action.getText(scheduledBungiiPage.Text_ScheduledBungiiStatus());
                    testStepVerify.isEquals(actualStatus,status);
                    break;

                case "estimated cost":
                   // tripStatus = action.getNameAttribute(scheduledBungiiPage.Trip_Status());
                    System.out.println((String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
                    testStepVerify.isEquals(actualStatus, (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
                    break;

                case "estimated cost of duo trip":
                    String estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
                    double flestimate=Double.valueOf(estimate.replace("~$","").trim());
                    //transaction fee different for solo and duo
                    double transactionFee=((flestimate*0.029*0.5)+0.3)*2;
                    double estimatedDriverCut=(0.7*flestimate)-transactionFee;
                    //divide by 2 for individual driver value
                    String truncValue = new DecimalFormat("#.00").format(estimatedDriverCut/2);
                    actualStatus = action.getText(scheduledBungiiPage.Text_ScheduledBungiiStatus());
                    testStepVerify.isEquals(actualStatus,"~$"+truncValue);
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @And("^I Select Trip from scheduled trip$")
    public void i_select_trip_from_scheduled_trip() throws Throwable {
        try{
            action.click(scheduledBungiiPage.Cell_FirstTrip());
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I click on \"([^\"]*)\" icon on \"([^\"]*)\" Page$")
    public void i_click_on_something_icon_on_something_page(String text, String page) throws Throwable {

        switch (text) {
            case "BUNGII DATE TIME":
                String dateTime=setPickupTimePage.Text_DateTime().getText();
                cucumberContextManager.setScenarioContext("ONDEMANDBUNGIITIME",dateTime);
                action.click(setPickupTimePage.Text_DateTime());
                break;
        }
        log("I click on " + text + " on " + page + " page",
                "I have clicked on " + text + " on " + page + " page", true);
    }

    @Then("^the trip is displayed on \"([^\"]*)\" screen$")
    public void the_trip_is_displayed_on_something_screen(String strArg1) throws Throwable {
        try{
            int year = DateTime.now().getYear();
            String actualMyBungiiTime ="";
            switch (strArg1) {
            case "MY BUNGIIS":
                action.scrollToTop();
                action.scrollToTop();
                String expectedMyBungiiTime = (String) cucumberContextManager.getScenarioContext("MY_BUNGII_DATE");
                actualMyBungiiTime = setPickupTimePage.Text_BungiiTime().getText().replace(String.valueOf(year)+" - " , "");

                testStepAssert.isEquals(actualMyBungiiTime, expectedMyBungiiTime,expectedMyBungiiTime+" is expected schedule date and time.", expectedMyBungiiTime+" is displayed.", expectedMyBungiiTime+" is not displayed. "+ actualMyBungiiTime+" is displayed");
                break;

            case "MY BUNGII":
                 expectedMyBungiiTime = (String) cucumberContextManager.getScenarioContext("NEW_SCHDL_BUNGII_TIME");
                 actualMyBungiiTime = setPickupTimePage.Text_BungiiTime().getText().replace(String.valueOf(year)+" - " , "");
                testStepAssert.isEquals(actualMyBungiiTime, expectedMyBungiiTime,expectedMyBungiiTime+" is expected schedule date and time.", expectedMyBungiiTime+" is displayed.", expectedMyBungiiTime+" is not displayed. "+ actualMyBungiiTime+" is displayed");
                break;
        }
    }
        catch (Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

    private void selectMeridean(String Meridian) {
        String mer = action.getText(estimatePage.Text_TimeMeridian());
        if (mer.equalsIgnoreCase(Meridian)) {
            //do nothing
        } else {
            action.click(estimatePage.Text_TimeMeridianNext());
            mer = action.getText(estimatePage.Text_TimeMeridian());


        }
    }

    /**
     * Select Bungii time in scroll wheel
     *
     * @param nextDate Date you want to be selected
     * @param hour     Trip hour
     * @param minutes  trip minutes
     * @param meridiem AM/PM
     */
    public void selectBungiiTime(int nextDate, String hour, String minutes, String meridiem, String tripType) {
        try {
            if (nextDate == 0) {
                    action.click(estimatePage.Time());

                if (tripType.equals("SOLO")) {
                    action.click(estimatePage.Button_Later());
                }
                action.click(estimatePage.Button_SystemCalenderOK());

                selectHour(hour);
                selectMins(minutes);
                selectMeridean(meridiem);

                //      Thread.sleep(2000);
                action.click(estimatePage.Button_TimeConfirm());

            } else if (nextDate > 0) {

                    String dayValue[] = generateNextDay(nextDate);
                    String month = "";
                    String day = "";
                    String year = "";
                    day = dayValue[0];
                    month = dayValue[1];
                    year = dayValue[2];

                    action.click(estimatePage.Time());
                    //    Thread.sleep(1000);
                    if (tripType.equals("SOLO")) {
                        action.click(estimatePage.Button_Later());
                    }

                    String Date = day + " " + month + " " + year;
                    String tempMonth=getCurrentMonthName();
                    if(tempMonth.equals(month))
                    {
                        //do nothing
                    }
                    else {
                        action.click(estimatePage.Calendar_NextMonth());
                    }
                if(nextDate !=5) {
                    WebElement Select_Day = scheduledBungiisPage.findElement("//android.view.View[@content-desc='" + Date + "']", PageBase.LocatorType.XPath);
                    action.click(Select_Day);
                    action.click(estimatePage.Button_SystemCalenderOK());
                    selectHour(hour);
                    selectMins(minutes);
                    selectMeridean(meridiem);
                    action.click(estimatePage.Button_OKOnTimePicker());
                }
                else{
                    String text=" selected";
                    WebElement Select_Day = scheduledBungiisPage.findElement("//android.view.View[@content-desc='" + Date +"']", PageBase.LocatorType.XPath);
                    testStepAssert.isElementNotEnabled(Select_Day,"Current Date + 5 should be disabled","Current Date + 5 is not enabled", "Current Date + 5 is enabled");
                    action.click(Select_Day);
                    action.click(estimatePage.Button_SystemCalenderOK());
                    selectHour(hour);
                    selectMins(minutes);
                    selectMeridean(meridiem);
                    action.click(estimatePage.Button_OKOnTimePicker());
                    //action.click(estimatePage.Button_SystemCalenderOK());

                   // String snackBarMessage=utility.getCustomerSnackBarMessage();//action.getText(promosPage.Snackbar());
                      //      testStepAssert.isEquals(snackBarMessage, PropertyUtility.getMessage("customer.valid.bungii.date"), "Please select a valid date.",snackBarMessage+" is not displayed.", snackBarMessage+" is displayed.");
                   // ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                }
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    public String[] generateNextDay(int nextDate) {

        Date curDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DATE,nextDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String DateToStr = format.format(curDate);
        format = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        DateToStr = format.format(cal.getTime());
        DateToStr.toString();
        String dateValue[] = DateToStr.split(" ");
        return dateValue;
    }

    public String getCurrentMonthName() {
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)];
        return month;
    }

    public String getNextMonthName() {
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH) + 1];
        return month;
    }
//Richa
    /**
     * select bungii
     *
     * @param bungiiType identifer for bungii type
     * @param bungiiTime Scheduled bungii time
     */
    public void selectBungii(String bungiiType, String bungiiTime) {
        //Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        int year = ((GregorianCalendar) cal).getWeekYear();
        //int year = currentDate.getYear() + 1900;
        String[] timeZones=utility.getDayLightTimeZoneBasedOnGeofence();
       // String bungiiDayLightTime=getbungiiDayLightTimeValue(bungiiTime);

        if (bungiiTime.contains(timeZones[0]) || bungiiTime.contains(timeZones[1]) || bungiiTime.contains("GMT+5:30")|| bungiiTime.contains("GMT+05:30")) {
            action.click(getLocatorForBungiiTime(bungiiType, bungiiTime.replace(",", ", " + year + " -"), bungiiTime.replace(",", ", " + year + " -").replace("+5:30", "+05:30").replace("ST", "DT")));
            log("I should click on schedule delivery which display Timezones or GMT+05:30 for Time"+bungiiTime,"I clicked on schedule delivery which display Timezones or GMT+05:30 for Time"+bungiiTime);
        }
        else {
            action.click(getLocatorForBungiiTime(bungiiType, bungiiTime.replace(",", ", " + year + " -") + " " + timeZones[0],
                    bungiiTime.replace(",", ", " + year + " -") + " " + timeZones[1]));
            log("I should click on schedule delivery which does not display Timezone or GMT+05:30 for Time"+bungiiTime,"I clicked on schedule delivery which does not display Timezone or GMT+05:30 for Time"+bungiiTime);
        }
    }


    public String getbungiiDayLightTimeValue(String bungiiTime){
        String time=null;

        if(bungiiTime.contains("CST")) { time=bungiiTime.replace("CST","CDT").replace("CDT","CDT"); }
        else if(bungiiTime.contains("EST")){ time=bungiiTime.replace("EST","EDT").replace("EDT","EDT"); }
        else if(bungiiTime.contains("MST")){ time=bungiiTime.replace("MST","MDT"); }
        else if(bungiiTime.contains("IST")){ time=bungiiTime; }
        return time;
    }

    /**
     * Check if bungii is present
     *
     * @param bungiiType Bungii Type , Solo /Duo
     * @param bungiiTime Scheduled bungii time
     * @return
     */
    public boolean isBungiiPresent(String bungiiType, String bungiiTime) {
        try {
            return action.isElementPresent(getLocatorForBungii(bungiiType, bungiiTime));
        } catch (Exception e) {
            return false;
        }
    }
//Richa
    /**
     * Construct locator for bungii from given bungii information
     *
     * @param bungiiType identifer for bungii type
     * @param bungiiTime Scheduled bungii time
     * @return
     */
    public WebElement getLocatorForBungii(String bungiiType, String bungiiTime) {
        //By Image_SelectBungii = MobileBy.xpath("//XCUIElementTypeStaticText[@name='" + bungiiTime+ "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell");

        WebElement Image_SelectBungii = scheduledBungiisPage.findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/item_my_bungii_tv_date' and @text='" + bungiiTime + "']", PageBase.LocatorType.XPath);

        return Image_SelectBungii;
    }

    /**
     * Construct locator for bungii from given bungii information
     *
     * @param bungiiType identifer for bungii type
     * @param bungiiTime Scheduled bungii time
     * @return
     */
    public WebElement getLocatorForBungiiTime(String bungiiType, String bungiiTime, String bungiiTimeDayLight) {
        //By Image_SelectBungii = MobileBy.xpath("//XCUIElementTypeStaticText[@name='" + bungiiTime+ "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell");
        //bungiiTime=bungiiTime.replace(" AM", "").replace(" PM", "");
        //bungiiTimeDayLight=bungiiTimeDayLight.replace(" AM", "").replace(" PM", "");
        WebElement Image_SelectBungii = scheduledBungiisPage.findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/item_my_bungii_tv_date' and @text='" + bungiiTime + "' or 'com.bungii.customer:id/item_my_bungii_tv_date' and @text='" + bungiiTimeDayLight + "']", PageBase.LocatorType.XPath);

        return Image_SelectBungii;
    }

    public void verifyDisclaimer(String Type) {
        if (Type.equalsIgnoreCase("SOLO")) {
            testStepVerify.isEquals(action.getText(estimatePage.Text_Solo_Disclaimer()), PropertyUtility.getDataProperties("customer.solo.disclaimer"));
            logger.detail("Disclaimer for " + Type + ":" + action.getText(estimatePage.Text_Solo_Disclaimer()));
        } else {
            testStepVerify.isEquals(action.getText(estimatePage.Text_Duo_Disclaimer()), PropertyUtility.getDataProperties("customer.duo.disclaimer"));
            logger.detail("Disclaimer for " + Type + ":" + action.getText(estimatePage.Text_Duo_Disclaimer()));
        }
    }
}
