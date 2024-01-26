package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.*;
import com.bungii.ios.pages.customer.EstimatePage;
import com.bungii.ios.pages.customer.ScheduledBungiiPage;
import com.bungii.ios.pages.driver.BungiiCompletedPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bungii.SetupManager.BrowserStackLocal;
import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.*;


public class EstimateSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(EstimateSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    EstimatePage estimatePage = new EstimatePage();
    com.bungii.ios.pages.customer.ScheduledBungiiPage scheduledBungiiPage = new ScheduledBungiiPage();
    // private EstimatePage estimatePage;
    private String[] loadTimeValue = {"15", "30", "45", "60", "75", "90+"};

    @When("^I confirm trip with following details$")
    public void iEnterTripInformation(DataTable tripInformation) {
        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String loadTime = data.get("LoadTime"), promoCode = data.get("PromoCode"), time = data.get("Time"),
                    pickUpImage = data.get("PickUpImage");
            //Vishal[21/12]: added this to save time , It takes time to read trip value from estimate page
            boolean saveDetails = true;
            try {
                String save_trip_info = data.get("Save Trip Info");
                saveDetails = save_trip_info.equalsIgnoreCase("No") ? false : true;
            } catch (Exception e) {
            }
            boolean isCorrectTime = false, isAlertCorrect;
            String strTime = "";

            enterLoadingTime(loadTime);
            //  addPromoCode(promoCode);
            addBungiiPickUpImage(pickUpImage);
            String BungiiType = (String)cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            verifyDisclaimer(BungiiType);
            clickAcceptTerms();
            strTime = enterTime(time);
            strTime=strTime.replace("am","AM").replace("pm","PM");
            String actualTime = "";
            String[] details = new String[4];
            //  action.swipeUP();
            if (saveDetails) {
                details = getEstimateDetails();
                isCorrectTime = details[1].equals(strTime);
                logger.detail("Expected Time is :"+strTime +" ||| Actual time is :"+details[1]);
            } else {
                actualTime = action.getValueAttribute(estimatePage.Text_TimeValue());
                isCorrectTime = actualTime.equals(strTime);
                logger.detail("Expected Time is :"+strTime +" ||| Actual time is :"+actualTime);
                testStepAssert.isTrue(isCorrectTime, "I confirm trip with following details",
                        "I selected trip for " + strTime, "Trip was not successfully confirmed ,Bungii request time "
                                + strTime + " | " + actualTime + " not matching with entered time ");
            }

            clickRequestBungii();
            // verification
            isAlertCorrect = verifyAndAcceptAlert(loadTime);

            // SAVE required values in scenario context
            cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);
            //cucumberContextManager.setScenarioContext("BUNGII_TIME", details[1]);
            cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", details[0]);
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", details[2]);
            cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", details[3]);
            Thread.sleep(1000);

            if (action.isAlertPresent()) {
                if (action.getAlertMessage().equalsIgnoreCase(PropertyUtility.getMessage("customer.alert.delay.scheduled"))) {
                    warning("I should able to select bungii time", "I am changing bungii time due to delay in bungii request", true);
                    SetupManager.getDriver().switchTo().alert().accept();
                    strTime = enterTime("NEXT_POSSIBLE AFTER ALERT");
                    strTime=strTime.replace("am","AM").replace("pm","PM");
                    String timeValue = action.getValueAttribute(estimatePage.Text_TimeValue()).replace("am","AM").replace("pm","PM");
                    if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date()))
                    {
                        timeValue = action.getValueAttribute(estimatePage.Text_TimeValue()).replace("ST","DT").replace("st","dt");
                    }
                    isCorrectTime = (timeValue).equals(strTime);
                    cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);
                    clickRequestBungii();
                    isAlertCorrect = verifyAndAcceptAlert(loadTime);
                }
            }
            testStepAssert.isTrue(isAlertCorrect, "Heads up alert message should be correctly displayed",
                    "Heads up alert message is correctly displayed", "Heads up alert message is not correctly displayed");
            utility.logCustomerRecentTrip((String)cucumberContextManager.getScenarioContext("LATEST_LOGGEDIN_CUSTOMER_PHONE"));

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in requesting Delivery",
                    true);
        }

    }


    @When("^I confirm trip with following detail$")
    public void iEnterTripInformations(DataTable tripInformation) throws Throwable {
        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String loadTime = data.get("LoadTime"), promoCode = data.get("PromoCode"), time = data.get("Time"),
                    pickUpImage = data.get("PickUpImage");
            //Vishal[21/12]: added this to save time , It takes time to read trip value from estimate page
            boolean saveDetails = true;
            try {
                String save_trip_info = data.get("Save Trip Info");
                saveDetails = save_trip_info.equalsIgnoreCase("No") ? false : true;
            } catch (Exception e) {
            }
            boolean isCorrectTime = false;
            String strTime = "";

            enterLoadingTime(loadTime);
            //  addPromoCode(promoCode);
            addBungiiPickUpImage(pickUpImage);
            //clickAcceptTerms();
            action.swipeDown();
            strTime = enterTime(time);

            String[] details = new String[4];
            if (saveDetails) {
                details = getEstimateDetails();
            }
            action.swipeUP();
            action.swipeUP();
            Thread.sleep(3000);
            String tripDateAndTime= action.getText(scheduledBungiiPage.Text_CustomerTrip_DateTime());
            cucumberContextManager.setScenarioContext("CUSTOMER_APP_TRIP_TIME",tripDateAndTime);
            clickAcceptTerms();
            i_request_for_bungii_using_request_bungii_button();

            // SAVE required values in scenario context
            cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);
            cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", details[0]);
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", details[2]);
            cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", details[3]);

            if (action.isAlertPresent()) {
                if (action.getAlertMessage().equalsIgnoreCase(PropertyUtility.getMessage("customer.alert.delay.scheduled"))) {
                    warning("I should able to select bungii time", "I am changing bungii time due to delay in bungii request", true);
                    SetupManager.getDriver().switchTo().alert().accept();
                    strTime = enterTime("NEXT_POSSIBLE AFTER ALERT");
                    cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);
                    i_request_for_bungii_using_request_bungii_button();
                }
            }

            log("I confirm trip with following details", "Trip was successfully confirmed ");
            utility.logCustomerRecentTrip((String)cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in requesting delivery",
                    true);
        }

    }
    @When("^I try to confirm trip with following detail$")
    public void itryEnterTripInformations(DataTable tripInformation) throws Throwable {
        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String loadTime = data.get("LoadTime"), promoCode = data.get("PromoCode"), time = data.get("Time"),
                    pickUpImage = data.get("PickUpImage");
            //Vishal[21/12]: added this to save time , It takes time to read trip value from estimate page
            boolean saveDetails = true;
            try {
                String save_trip_info = data.get("Save Trip Info");
                saveDetails = save_trip_info.equalsIgnoreCase("No") ? false : true;
            } catch (Exception e) {
            }
            boolean isCorrectTime = false;
            String strTime = "";
            enterLoadingTime(loadTime);
            //  addPromoCode(promoCode);
            addBungiiPickUpImage(pickUpImage);

            action.swipeDown();
            strTime = enterTime(time);

            String[] details = new String[4];
            if (saveDetails) {
                details = getEstimateDetails();
            }
            action.swipeUP();
            action.swipeUP();
            clickAcceptTerms();
            action.click(estimatePage.Button_RequestBungii());

            // SAVE required values in scenario context
            cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);
            cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", details[0]);
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", details[2]);
            cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", details[3]);
            String actualText = getDriver().switchTo().alert().getText();
            getDriver().switchTo().alert().accept();

            log("I try confirming trip with following details", "I tried requesting bungii ");
            utility.logCustomerRecentTrip((String)cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in trying to request delivery",
                    true);
        }

    }
    @When("^I request for bungii using Request Bungii Button$")
    public void i_request_for_bungii_using_request_bungii_button() throws Throwable {
        clickRequestBungii();
        String actualText = getDriver().switchTo().alert().getText();
        getDriver().switchTo().alert().accept();
        Thread.sleep(5000);
        utility.logCustomerRecentTrip((String)cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
        pass("I request for bungii using Request Bungii Button",
                "I requested for bungii using Request Bungii Button");
        logger.detail("Popup text on head up alert message:" + actualText);
    }

    private void addPromoCode(String code) {
        action.click(estimatePage.Button_AddPromoCode());

    }

    @When("^I select load time as \"([^\"]*)\" mins$")
    public void i_select_load_time_as_something_mins(String loadTime) throws Throwable {
        enterLoadingTime(loadTime.trim());
    }

    @Then("^Estimate value for trip should be properly displayed$")
    public void estimate_value_for_trip_should_be_properly_displayed() {
        try {
            String distance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
            String estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
            estimate = estimate.replace("~$", "");
            String loadTime = (String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME");
            GeneralUtility utility = new GeneralUtility();
            //get data from DB instead of Phone Screen
            //TODO: verify DB and phone value
            String totalDistance = utility.getEstimateDistance();
            double expectedValue = utility.bungiiEstimate(totalDistance, loadTime, getEstimateTime(), "");

            String actualValue = estimate.substring(0, estimate.length() - 1);
            String truncValue = new DecimalFormat("#.00").format(expectedValue);
            //  String truncValue = new DecimalFormat("#.##").format(expectedValue);
            testStepAssert.isEquals(estimate.trim(), truncValue.trim(), "Estimate value for trip should be properly displayed.(NOTE: Failure might me due to truncation)", "Expected Estimate value for bungii is" + truncValue + " and Actual value is" + actualValue + ",(Truncate to single float point)", "Expected Estimate value for bungii is" + truncValue + " and Actual value is" + estimate);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in getting Estimate value for trip",
                    true);
        }
    }

    @Then("^I should see \"([^\"]*)\" code selected on Bungii estimate$")
    public void i_should_see_first_time_only_code_selected_on_bungii_estimate(String strArg1) throws Throwable {
        try {
            String value = getElementValue("Promo Code");
            String expectedPromoValue = "";
            if (strArg1.equalsIgnoreCase("first time only"))
                expectedPromoValue = "-$11.00";
            else if (strArg1.equalsIgnoreCase("selected"))
                expectedPromoValue = "-" + cucumberContextManager.getScenarioContext("PROMO_CODE_VALUE");

            testStepVerify.isEquals(value, expectedPromoValue);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    private String getEstimateTime() {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        String custRef = DbUtility.getCustomerRefference(phoneNumber);
        return DbUtility.getEstimateTime(custRef);
    }

    @When("^I try to schedule bungii for \"([^\"]*)\"$")
    public void i_try_to_schedule_bungii_for_something(String strArg1) throws Throwable {
        try {
            String browserstack= BrowserStackLocal();
            switch (strArg1.toLowerCase()) {
                case "today - after working hour":
                    //selectBungiiTime(0, "11", "45", "PM");

                        selectBungiiTime(0, "11", "45", "PM");
                        log("I select time for trip as 11:45  pm", "I selected time for trip as 11:45  pm");


                    break;
                case "tommorow - before working hour":

                        selectBungiiTime(1, "12", "00", "AM");
                        log("I select time for trip tomorrow 12 00 AM", "I selected time for trip as  tomorrow 12 00 AM");

                    break;
                case "today+5":
                    selectBungiiTime(5, "", "", "");
                    log("I select time for trip today+5 1:00 pm", "I selected time for trip as today+5 1:00 pm");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    public String enterTime(String time) throws ParseException, InterruptedException {
        String strTime = "";
        if (time.equalsIgnoreCase("NOW")) {
             //   selectBungiiTimeNow();
            strTime = "Now";
        } else if (time.equalsIgnoreCase("NEXT_POSSIBLE")) {
            Date date = getNextScheduledBungiiTimeForGeofence();
            /*
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            //int mnts = calendar.get(Calendar.MINUTE);

            //calendar.set(Calendar.MINUTE, mnts - 30);
            int unroundedMinutes = calendar.get(Calendar.MINUTE);
            int mod = unroundedMinutes % 15;
            calendar.add(Calendar.MINUTE, (15 - mod));
            calendar.set(Calendar.SECOND, 0);

            Date nextQuatter = calendar.getTime();

             */
            String[] dateScroll = bungiiTimeForScroll(date);
            strTime = bungiiTimeDisplayInTextArea(date);
            Thread.sleep(3000);
            action.click(estimatePage.Row_TimeSelect());
            Thread.sleep(6000);
            if(!action.isElementPresent(estimatePage.Button_Set(true))) {
                action.click(estimatePage.Row_TimeSelect()); //Retry to select time - workaround for duo cases
            }
            //  selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);
            action.click(estimatePage.Button_Set());
        }else if (time.equalsIgnoreCase("NEXT_SECOND_POSSIBLE")) {
            Date date = getNextScheduledBungiiTimeForGeofence();
            String[] dateScroll = bungiiTimeForScroll(date);
            strTime = bungiiTimeDisplayInTextArea(date);
            Thread.sleep(3000);
            action.click(estimatePage.Row_TimeSelect());
            Thread.sleep(6000);
            if(!action.isElementPresent(estimatePage.Button_Set(true))) {
                action.click(estimatePage.Row2_TimeSelect()); //Retry to select time - workaround for duo cases
            }
            //  selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);
            action.click(estimatePage.Button_Set());
        }
        else if (time.equalsIgnoreCase("30_MIN_AHEAD")) {
            //Date date = getNextScheduledBungiiTimeForGeofence();
            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");

            Date date = Calendar.getInstance().getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            //int mnts = calendar.get(Calendar.MINUTE);

            //calendar.set(Calendar.MINUTE, mnts - 30);
            int unroundedMinutes = calendar.get(Calendar.MINUTE);
            int mod = unroundedMinutes % 15;
            calendar.add(Calendar.MINUTE, (30 - mod));
            calendar.set(Calendar.SECOND, 0);

            TimeZone tz = TimeZone.getTimeZone(geofenceLabel);
            sdf.setTimeZone(tz);
            Date dt = calendar.getTime();
            sdf.format(dt);

           // Date nextQuatter = calendar.getTime();
            String NQ = sdft.format(dt);
            Date nextQuatter = sdft.parse(NQ);
            sdft.setTimeZone(tz);
            String nextQuatter1 = sdft.format(nextQuatter);
            nextQuatter = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").parse(nextQuatter1);
            //Date nextQuatter = calendar.getTime();

            String[] dateScroll = bungiiTimeForScroll(nextQuatter);
            strTime = bungiiTimeDisplayInTextArea(nextQuatter);
            Thread.sleep(3000);
            action.click(estimatePage.Row_TimeSelect());
            Thread.sleep(6000);
            if(!action.isElementPresent(estimatePage.Button_Set(true))) {
                action.click(estimatePage.Row2_TimeSelect()); //Retry to select time - workaround for duo cases
            }
            //Thread.sleep(6000);
            //if(!action.isElementPresent(estimatePage.Button_Set(true))) {
                //action.click(estimatePage.Row2_TimeSelect()); //Retry to select time - workaround for duo cases
            //}
              //selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);
            //action.click(estimatePage.Row_TimeSelect());
            action.dateTimePicker(estimatePage.DatePicker_BungiiTime, estimatePage.DateWheel_BungiiTime, 0, dateScroll[1], dateScroll[2], dateScroll[3]);
            //  action.click(estimatePage.Row_TimeSelect());
            action.click(estimatePage.Button_Set());
            //action.click(estimatePage.Button_Set());
        }
        else if (time.equalsIgnoreCase("NEXT_POSSIBLE AFTER ALERT")) {
            Date date = getNextScheduledBungiiTimeForGeofence();
            String[] dateScroll = bungiiTimeForScroll(date);
            strTime = bungiiTimeDisplayInTextArea(date);
            //action.click(estimatePage.Row_TimeSelect());
              selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);
            action.click(estimatePage.Button_Set());
        }
        else if (time.equalsIgnoreCase("<TIME WITHIN TELET>") || time.equalsIgnoreCase("<TIME WITHIN TELET OF CUSTOMER 2>")) {

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


            String[] dateScroll = bungiiTimeForScroll(teletTimeInLocal);
            strTime = bungiiTimeDisplayInTextArea(teletTimeInLocal);
            action.click(estimatePage.Row_TimeSelect());
            //BrowserStack Changes for not display of AM and PM
            String bs = BrowserStackLocal();
            if(bs.equalsIgnoreCase("True")){
                dateScroll[3] ="";
            }
            selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);

        } else if (time.equalsIgnoreCase("<START TIME WITHIN TELET OF CUSTOMER 1>")) {

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
             if(mnts>=30)
            calendar.set(Calendar.MINUTE, mnts - 30);
             else
                 calendar.set(Calendar.MINUTE, 0);
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


            String[] dateScroll = bungiiTimeForScroll(teletTimeInLocal);
            strTime = bungiiTimeDisplayInTextArea(teletTimeInLocal);
            //action.click(estimatePage.Row_TimeSelect()); added inside so removing it
            selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);

        } else if (time.equals("<TELET TIME OVERLAP WITH START TIME OF CUSTOMER 1>")) {
            Date date = getNextScheduledBungiiTimeForGeofence();
            String[] dateScroll = bungiiTimeForScroll(date);
            strTime = bungiiTimeDisplayInTextArea(date);
            action.click(estimatePage.Row_TimeSelect());
            //  selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);
            action.click(estimatePage.Button_Set());
        } else if (time.equals("<AFTER TELET>")){

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

            calendar.set(Calendar.MINUTE, mnts+120);
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


            String[] dateScroll = bungiiTimeForScroll(teletTimeInLocal);
            strTime = bungiiTimeDisplayInTextArea(teletTimeInLocal);
            action.click(estimatePage.Row_TimeSelect());
            selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);
        } else if (time.equals("<1 HOUR AFTER TELET>")) {

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
            calendar.add(Calendar.HOUR,1);
            int mnts = calendar.get(Calendar.MINUTE);

            calendar.set(Calendar.MINUTE, mnts);
            int unroundedMinutes = calendar.get(Calendar.MINUTE);
            int mod = unroundedMinutes % 15;
            calendar.add(Calendar.MINUTE, (15 - mod));

            calendar.set(Calendar.SECOND, 0);

            //Date nextQuatter = calendar.getTime();
            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();

            DateFormat formatterForLocalTimezone = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

            formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

            String strdate = formatter.format(calendar.getTime());
            Date teletTimeInLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(strdate);


            String[] dateScroll = bungiiTimeForScroll(teletTimeInLocal);
            strTime = bungiiTimeDisplayInTextArea(teletTimeInLocal);
            action.click(estimatePage.Row_TimeSelect());
            selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);
        } else if (time.equals("<OLD BUNGII TIME>")) {
            String expectedTripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            String tripDay = expectedTripTime.split(",")[0];
            String tripTime = expectedTripTime.split(",")[1];
            String[] timeSplit = tripTime.trim().split("\\W");
            selectBungiiTime(0, timeSplit[0], timeSplit[1], timeSplit[2]);
            strTime = expectedTripTime;
        } else if (time.equals("NEXT_POSSIBLE_IN_DATE_SCROLL")) {
            Date date = getNextScheduledBungiiTime();
            String[] dateScroll = bungiiTimeForScroll(date);
            strTime = bungiiTimeDisplayInTextArea(date);
            selectBungiiTime();
        } else if (time.equalsIgnoreCase("Today+1 1:00 PM")) {
            //BrowserStack Changes for not display of AM and PM
            String bs = BrowserStackLocal();
            if(bs.equalsIgnoreCase("True")){
                selectBungiiTime(1, "1", "00", "");
            }
            else {
                selectBungiiTime(1, "1", "00", "PM");
            }
        } else if (time.equalsIgnoreCase("Today+2 1:00 PM")) {
            //BrowserStack Changes for not display of AM and PM
            String bs = BrowserStackLocal();
            if(bs.equalsIgnoreCase("True")){
                selectBungiiTime(2, "1", "00", "");
            }else {
                selectBungiiTime(2, "1", "00", "PM");
            }
        } else if (time.equalsIgnoreCase("Today+3 1:00 PM")) {
            //BrowserStack Changes for not display of AM and PMse
            String bs = BrowserStackLocal();
            if(bs.equalsIgnoreCase("True")){
                selectBungiiTime(3, "1", "00", "");
            }else {
                selectBungiiTime(3, "1", "00", "PM");
            }
        } else if (time.equalsIgnoreCase("Today+4 1:00 PM")) {
            //BrowserStack Changes for not display of AM and PM
            String bs = BrowserStackLocal();
            if(bs.equalsIgnoreCase("True")){
                selectBungiiTime(4, "1", "00", "");
            }else {
                selectBungiiTime(4, "1", "00", "PM");
            }
        } else {
            selectBungiiTime(0, "", "", "");
            strTime = "Now";
        }

        return strTime;
    }

    @When("^I select Bungii time as per table$")
    public void i_select_bungii_time_as_per_table(DataTable tripTimeDetails) {
        try {
            Map<String, String> data = tripTimeDetails.transpose().asMap(String.class, String.class);
            String bungiiTime = data.get("Time"), bungiiDate = data.get("Date");
            String[] dateScroll = getDateForScroll(bungiiTime);
            selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    /**
     * @param bungiiTime Bungii time took from user
     * @return
     */
    public String[] getDateForScroll(String bungiiTime) {
        int differance = 0;
        String[] dateScroll = new String[3];
        if (bungiiTime.equalsIgnoreCase("2 hour before"))
            differance = -120;
        else if (bungiiTime.equalsIgnoreCase("2 hour after"))
            differance = 120;

        Date date = getScheduledBungiiTime(differance);
        dateScroll = bungiiTimeForScroll(date);

        if (differance == 0)
            if (bungiiTime.equalsIgnoreCase("before 7 AM")) {
                dateScroll[0] = "6";
                dateScroll[1] = "45";
                dateScroll[2] = "AM";
            } else if (bungiiTime.equalsIgnoreCase("after 9 PM")) {
                dateScroll[0] = "9 ";
                dateScroll[1] = "45";
                dateScroll[2] = "PM";
            }

        return dateScroll;
    }

    /**
     * Format input date and return in required format
     *
     * @param date input date
     * @return formated date
     */
    public String bungiiTimeDisplayInTextArea(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm aa");
        String formattedDate = sdf.format(date);
        //After sprint 27 /26 IST is being added in scheduled page
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
            formattedDate = formattedDate + " " + PropertyUtility.getDataProperties("time.label");
        else
            formattedDate = formattedDate + " " + utility.getTimeZoneBasedOnGeofence();

        cucumberContextManager.setScenarioContext("BUNGII_FORMATTED", formattedDate);
        return formattedDate;
    }

    public String geofenceBaseBungiiCalculatedTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int mnts = calendar.get(Calendar.MINUTE);

        calendar.set(Calendar.MINUTE, mnts + 30);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 15;
        calendar.add(Calendar.MINUTE, (15 - mod));
        calendar.set(Calendar.SECOND, 0);
        Date calculatedDate = calendar.getTime();
        //String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm a z");
        sdf.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        String formattedDate = sdf.format(calculatedDate);
        //Calendar calendar = Calendar.getInstance();

        return formattedDate;
    }

    public String bungiiTimeDisplayDriverEarning(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
        String formattedDate = sdf.format(date);
        //After sprint 27 /26 IST is being added in scheduled page
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
            formattedDate = formattedDate + " " + PropertyUtility.getDataProperties("time.label");
        else
            formattedDate = formattedDate + " " + utility.getTimeZoneBasedOnGeofence();

        cucumberContextManager.setScenarioContext("BUNGII_FORMATTED", formattedDate);
        return formattedDate;
    }

    /**
     * Format input date and return in required format
     *
     * @param date input date
     * @return formated date
     */
    public String[] bungiiTimeForScroll(Date date) {
        //get timezone
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM|h|mm|a");
        String formattedDate = sdf.format(date);
        String[] SplitDate = formattedDate.split("\\|");
        if (DateUtils.isSameDay(date, new Date())) {
            SplitDate[0] = "Today";
        }
        return SplitDate;
    }

    public String getDateForTimeZone() {
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time"));
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + nextTripTime + 15); //15 added to eliminate there is delay in requeting bungii
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.MINUTE, (15 - unroundedMinutes % 15));

        String strdate = formatter.format(calendar.getTime());
        return strdate;
    }
    public String getDateForTimeZoneToMatch() {
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time.nashville"));
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + nextTripTime);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.MINUTE, (15 - unroundedMinutes % 15));

        String strdate = formatter.format(calendar.getTime());
        return strdate;
    }
    public String getDateForTimeZone(int minuteDifferance) {
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time"));
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + nextTripTime + minuteDifferance);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.MINUTE, (15 - unroundedMinutes % 15));

        String strdate = formatter.format(calendar.getTime());
        return strdate;
    }
    public String getDateForTimeZoneDependingOn(int minuteDifferance) {
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time"));
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minuteDifferance);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.MINUTE, (15 - unroundedMinutes % 15));

        String strdate = formatter.format(calendar.getTime());
        return strdate;
    }
    public String getDateForTimeZoneForGeofence() {
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        int nextTripTime=0;
        cucumberContextManager.getScenarioContext("MIN_TIME_DUO");
        String aa= (String) cucumberContextManager.getScenarioContext("MIN_TIME_SOLO");
        String bungiiType= (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        if(bungiiType.equalsIgnoreCase("solo")) {
             nextTripTime = Integer.parseInt((String) cucumberContextManager.getScenarioContext("MIN_TIME_SOLO"));
        }
        else{
             nextTripTime = Integer.parseInt((String) cucumberContextManager.getScenarioContext("MIN_TIME_DUO"));
        }
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");

        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        TimeZone tz = TimeZone.getTimeZone(geofenceLabel);
        formatter.setTimeZone(tz);
        String dt = formatter.format(tz);

        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + nextTripTime);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.MINUTE, (15 - unroundedMinutes % 15));

        String strdate = formatter.format(calendar.getTime());
        return strdate;
    }
    public Date getFormatedTime() {
        Date date1 = Calendar.getInstance().getTime();
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(getDateForTimeZone());
            //System.out.println("\t" + date1);
        } catch (Exception e) {
        }

        return date1;
    }
    public Date getFormatedTimeToMatch() {
        Date date1 = Calendar.getInstance().getTime();
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(getDateForTimeZoneToMatch());
            //System.out.println("\t" + date1);
        } catch (Exception e) {
        }

        return date1;
    }

    public Date getFormatedTimeForGeofence() {
        Date date1 = Calendar.getInstance().getTime();
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS aa").parse(getDateForTimeZoneForGeofence());
           // System.out.println("\t" + date1);
        } catch (Exception e) {
        }

        return date1;
    }

    public Date getFormatedTime(int minuteDifferance) {
        Date date1 = Calendar.getInstance().getTime();
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(getDateForTimeZone(minuteDifferance));
            //System.out.println("\t" + date1);
        } catch (Exception e) {
        }

        return date1;
    }
    public Date getFormatedTimeDependingOn(int minuteDifferance) {
        Date date1 = Calendar.getInstance().getTime();
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(getDateForTimeZoneDependingOn(minuteDifferance));
            //System.out.println("\t" + date1);
        } catch (Exception e) {
        }

        return date1;
    }

    /**
     * Read property file for minimum difference for next bunii time
     *
     * @return next possible valid bungii time
     */
    public Date getNextScheduledBungiiTime() {
        return getFormatedTime();
    }
    public Date getNextScheduledBungiiTimeToMatch() {
        return getFormatedTimeToMatch();
    }

    public Date getNextScheduledBungiiTimeForGeofence(){
        return getFormatedTimeForGeofence();
    }
    /**
     * Read property file for minimum difference for next bunii time
     *
     * @return next possible valid bungii time
     */
    public Date getNextScheduledBungiiTime(int minuteDifferance) {
        return getFormatedTime(minuteDifferance);
    }
    public Date getNextScheduledBungiiTimeDependingOn(int minuteDifferance) {
        return getFormatedTimeDependingOn(minuteDifferance);
    }
    public Date getScheduledBungiiTime(int minuteDifferance) {
        int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time"));
        Calendar calendar = Calendar.getInstance();
        // int mnts = calendar.get(Calendar.MINUTE);

        calendar.set(Calendar.MINUTE, (calendar.get(Calendar.MINUTE) + nextTripTime + minuteDifferance));
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.MINUTE, (15 - unroundedMinutes % 15));

        Date nextQuatter = calendar.getTime();
        return nextQuatter;
    }

    @Then("^\"([^\"]*)\" information icon should display correct information$")
    public void something_should_display_correct_information(String iconName) {
        try {

            String loadTime = (String) cucumberContextManager.getScenarioContext("BUNGII_LOAD_TIME");
            String expectedMessage = "", actualMessage = "";
            switch (iconName.toUpperCase()) {
                case "LOAD/UPLOAD TIME":
                    expectedMessage = PropertyUtility.getMessage("customer.info.loadtime");
                    actualMessage = getInfoMessage("LOAD/UPLOAD TIME");
                    break;
                case "TIME":
                    expectedMessage = PropertyUtility.getMessage("customer.info.time");
                    actualMessage = getInfoMessage("TIME");
                    break;
                case "TOTAL ESTIMATE":
                    expectedMessage = PropertyUtility.getMessage("customer.info.totalestimate").replaceAll("<TIME>", loadTime.trim());
                    actualMessage = getInfoMessage("TOTAL ESTIMATE");
                    break;
                default:
                    fail("Step  Should be successful",
                            "UnImplemented STEP , please verify test step", true);
                    break;
            }

            testStepVerify.isEquals(actualMessage, expectedMessage);
            getDriver().switchTo().alert().accept();

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^check if I have ability to select different load time and Estimate cost is re calculated$")
    public void check_if_i_have_ability_to_select_different_load_time_and_estimate_cost_is_re_calculated() {
        try {
            String oldEstimateValue = getElementValue("Total Estimate");
            for (int i = 0; i < loadTimeValue.length; i++) {

                boolean flag = checkLoadingTime(loadTimeValue[i]);
                testStepVerify.isTrue(flag,
                        "I should able to to select " + loadTimeValue[i] + "mins as load time",
                        "I was able to to select " + loadTimeValue[i] + "mins as load time",
                        "I was not able to to select " + loadTimeValue[i] + "mins as load time");
                String newEstimateValue = getElementValue("Total Estimate");

/*                if (i == 0)
                    testStepVerify.isTrue(newEstimateValue.equals(oldEstimateValue),
                            "total Estimated cost is calculated considering  Loading/unloading time",
                            "Total Estimate cost for first scroll value should be same as default one, Previous cost is " + oldEstimateValue + " , new cost is " + newEstimateValue,
                            "Total Estimate cost was recalculated");
                else*/
                testStepVerify
                        .isFalse(newEstimateValue.equals(oldEstimateValue),
                                "total Estimated cost is calculated considering  Loading/unloading time",
                                "Total Estimate cost is recalculated , previous cost is" + oldEstimateValue + " , new cost is" + newEstimateValue,
                                "Total Estimate cost was not recalculated");
                oldEstimateValue = newEstimateValue;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I store default card value$")
    public void i_store_default_card_value() throws Throwable {
        try {
            String defaultCard = getElementValue("Payment Method");
            cucumberContextManager.setScenarioContext("DEFAULT_CARD", defaultCard);
            pass("I store default card value",
                    "Default card value us " + defaultCard, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I save bungii trip time details$")
    public void i_save_bungii_trip_time_details() throws Throwable {
        cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE_TIME",action.getNameAttribute(estimatePage.Text_DurationValue()));
    }

    @Then("^I save bungii promo details$")
    public void i_save_bungii_details() throws Throwable {
        String value = getElementValue("Promo Code");

        cucumberContextManager.setScenarioContext("PROMOCODE_VALUE", value);
    }

    @When("^I enter following details on \"([^\"]*)\" screen$")
    public void i_enter_following_details_on_something_screen(String strArg1, DataTable tripInformation) {

        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String loadTime = data.get("LoadTime"), promoCode = data.get("PromoCode"), time = data.get("Time"),
                    pickUpImage = data.get("PickUpImage");
            if (!loadTime.equals("")) {
                enterLoadingTime(loadTime);
                cucumberContextManager.setScenarioContext("BUNGII_LOAD_TIME", loadTime);

            }

            addBungiiPickUpImage(pickUpImage);
            clickAcceptTerms();
            String[] details = getEstimateDetails();
            // SAVE required values in scenario context
            cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", details[0]);
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", details[2]);
            cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", details[3]);
            cucumberContextManager.setScenarioContext("BUNGII_TIME", details[1]);

            String value = getElementValue("Promo Code");
            logger.detail("Requested Field Values : "+ details[0]+", "+ details[1]+", "+ details[2]+", "+ details[3]);

            cucumberContextManager.setScenarioContext("PROMOCODE_VALUE", value);
            String fieldValues = details[0]+", "+ details[1]+", "+ details[2]+", "+ details[3];
            if (!time.equals("")) {
                String strTime = enterTime(time);
                String[] redetails = getEstimateDetails();
                cucumberContextManager.setScenarioContext("BUNGII_TIME", redetails[1]);
                logger.detail("Requested Field Values : "+ redetails[1]);
                fieldValues =  redetails[0]+", "+ redetails[1]+", "+ redetails[2]+", "+ redetails[3];
            }

            log("I enter details on Estimate screen", "I was able to add "+ fieldValues+" on estimate screen", true);


        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in requesting delivery ",
                    true);
        }
    }


    @Then("^Estimate Screen should have element as per below table$")
    public void estimate_screen_should_have_element_as_per_below_table(DataTable estimateInformation) {
        try {
            Map<String, String> data = estimateInformation.transpose().asMap(String.class, String.class);
            // Store expected data in variable
            String tripDistance = data.get("Trip Distance"), loadTime = data.get("Load/unload time"),
                    promoCode = data.get("Promo Code"), tripEstimate = data.get("Total Estimate"),
                    paymentMethod = data.get("Payment Method"), tripTime = data.get("Time"),
                    termsAndCondition = data.get("Terms And Condition"), requestBungii = data.get("REQUEST BUNGII");
            // check each field for expected value
            if (!tripDistance.isEmpty())
                checkTripDistance(tripDistance);

            if (!loadTime.isEmpty())
                checkLoadTime(loadTime);
            if (!promoCode.isEmpty())
                checkPromoCode(promoCode);
            if (!tripEstimate.isEmpty())
                checkEstimate(tripEstimate);
            if (!paymentMethod.isEmpty())
                checkPayment(paymentMethod);
            if (!tripTime.isEmpty())
                checkTime(tripTime);
            if (!termsAndCondition.isEmpty())
                checkTermsAndConditon(termsAndCondition);
            if (!requestBungii.isEmpty())
                checkRequestBungii(requestBungii);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful",
                    "Error in verifying values of fields on estimate screen", true);
        }
    }


    @Then("^Trip Information should be correctly displayed on Estimate screen$")
    public void trip_information_should_be_correctly_displayed_on_something_screen() {
        try {
            String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",", "").trim();
            String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",", "").trim();

            String dropOffLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",", "").trim();
            String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",", "").trim();

            String[] tripLocation = new String[4];
            tripLocation[0] = action.getValueAttribute(estimatePage.Text_PickUpLocationLineOne()).replace(",", "").trim();
            tripLocation[1] = action.getValueAttribute(estimatePage.Text_PickUpLocationLineTwo()).replace(",", "").trim();
            tripLocation[2] = action.getValueAttribute(estimatePage.Text_DropOffLocationLineOne()).replace(",", "").trim();
            tripLocation[3] = action.getValueAttribute(estimatePage.Text_DropOffLocationLineTwo()).replace(",", "").trim();

            if (tripLocation[0].equals(pickUpLocationLineOne) && tripLocation[1].equals(pickUpLocationLineTwo) && tripLocation[2].equals(dropOffLocationLineOne) && tripLocation[3].equals(dropOffLocationLineTwo)) {
                pass("Trip Information should be correctly displayed on Estimate screen",
                        "Pick up location :" + pickUpLocationLineOne + " , Drop location: " + dropOffLocationLineOne
                                + "is correctly displayed on estimate screen ", true);

            } else {
                fail("Trip Information should be correctly displayed on Estimate screen",
                        "Pick up location on request screen is:" + pickUpLocationLineOne + " and on Estimate screen is"
                                + tripLocation[0] + " .Drop off location on request screen is:" + dropOffLocationLineOne + " and on Estimate screen is" + tripLocation[1],
                        true);
            }

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }


    @When("^I tap \"([^\"]*)\" on Estimate screen$")
    public void i_tap_something_on_estimate_screen(String button) throws Throwable {
        try {
            switch (button.toUpperCase()) {
                case "PROMO CODE":
                    action.click(estimatePage.Row_PromoCode());
                    break;
                default:
                    fail("Step  Should be successful",
                            "UnImplemented STEP , please verify test step", true);
                    break;
            }
            log("I should able to tap on " + button + " on Estimate screen", "I was able to tab on estimate screen", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^i add \"([^\"]*)\" of pickup item$")
    public void i_add_something_images_of_pickup_item(String pickUpImage) throws Throwable {
        try {
            addBungiiPickUpImage(pickUpImage);

            log("I should able to tap on " + pickUpImage + " on Estimate screen", "I was able to add" + pickUpImage + " on estimate screen", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I wait for 15 minutes slot overlap period if occurs$")
    public void i_wait_for_15_minutes_slot_overlap_period_if_occurs() throws Throwable {
        //throw new PendingException();
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        //int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time"));
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        //calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + nextTripTime);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        if(unroundedMinutes%15<=1);
        {
            Thread.sleep(120000);
        }


    }

    @Then("^correct details next available scheduled time should be displayed$")
    public void correct_details_next_available_scheduled_time_should_be_displayed() throws Throwable {
        try {
            String displayedTime = getElementValue("TIME");
            Date date = getNextScheduledBungiiTimeToMatch();
            String strTime = bungiiTimeDisplayInTextArea(date);
            if(BrowserStackLocal().equalsIgnoreCase("true")) {
                strTime = utility.getGmtTime(strTime);
                strTime = strTime.replace("am","a.m.").replace("pm","p.m.").replace("AM","a.m.").replace("PM","p.m.");

                testStepAssert.isEquals(displayedTime, strTime,"Default Pickup Time should be calculated properly","Default Pickup Time is displayed properly : "+ displayedTime,"Default Pickup Time is not displayed properly : "+ displayedTime);
            }
            else
                testStepAssert.isEquals(displayedTime.replace("am","AM").replace("pm","PM"),strTime.replace("am","AM").replace("pm","PM"),"Default Pickup Time should be calculated properly","Default Pickup Time is displayed properly : "+ displayedTime,"Default Pickup Time is not displayed properly : "+ displayedTime);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }




    @And("^I select pickup time$")
    public void i_select_pickup_time() throws Throwable {
        try {
            action.click(estimatePage.Row_TimeSelect());
            action.click(estimatePage.Button_Set());
            String time=action.getValueAttribute(estimatePage.Text_TimeValue());
            Date date = getNextScheduledBungiiTimeForGeofence();
            String strTime = bungiiTimeDisplayInTextArea(date);
            cucumberContextManager.setScenarioContext("CALCULATED_TIME",strTime);
            cucumberContextManager.setScenarioContext("DISPLAYED_TIME",time);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I calculate the schedule time$")
    public void I_calculate_the_schedule_time() throws Throwable {
        try {
            cucumberContextManager.setScenarioContext("MIN_TIME_DUO", "30");
            cucumberContextManager.setScenarioContext("MIN_TIME_SOLO", "30");

                String time = action.getValueAttribute(estimatePage.Text_TimeValue());
                Date date = getNextScheduledBungiiTimeForGeofence();
                String strTime = bungiiTimeDisplayInTextArea(date);
                cucumberContextManager.setScenarioContext("CALCULATED_TIME", strTime);
                cucumberContextManager.setScenarioContext("DISPLAYED_TIME", time);


        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^correct next available scheduled time should be displayed$")
    public void correct_next_available_scheduled_time_should_be_displayed() throws Throwable {
        try {

            String displayedTime = getElementValue("TIME");
            //Date date = getNextScheduledBungiiTimeForGeofence();
            //String strTime =(String)cucumberContextManager.getScenarioContext("CALCULATED_TIME");
            Date date = getNextScheduledBungiiTimeForGeofence();
            String strTime = geofenceBaseBungiiCalculatedTime(date);

            if(BrowserStackLocal().equalsIgnoreCase("true")) {
                if(displayedTime.contains("a.m.")||displayedTime.contains("p.m.")) {
                    strTime = strTime.replace("am", "a.m.").replace("pm", "p.m.").replace("AM", "a.m.").replace("PM", "p.m.");
                }
                String TstrTime = strTime;
                if(strTime.contains("GMT")||strTime.contains("CDT")||strTime.contains("CST")) {
                    strTime = utility.getGmtTime(strTime);
                }
                testStepAssert.isTrue(displayedTime.equals(strTime) || displayedTime.equals(TstrTime) ,strTime+" OR "+TstrTime+" should be displayed",strTime+" OR "+TstrTime+" is displayed", strTime+" OR "+TstrTime+" is not displayed instead "+ displayedTime +" is displayed");
            }
            else
            testStepAssert.isEquals(displayedTime.replace("am","AM").replace("pm","PM"), strTime.replace("am","AM").replace("pm","PM"),strTime+" should be displayed",strTime+" is displayed", strTime+" is not displayed instead "+ displayedTime +"is displayed");


        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * Verify if request bungii button is enabled or disabled
     *
     * @param expectedValue expected value for button
     */
    public void checkRequestBungii(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("REQUEST BUNGII");

        if (expectedValue.equalsIgnoreCase("DISABLED"))
            testStepVerify.isFalse(isElementPresent,
                    "REQUEST BUNGII' button should be disabled", "REQUEST BUNGII' button is disabled",
                    "REQUEST BUNGII' button is enabled");
        else
            testStepVerify.isTrue(isElementPresent,
                    "REQUEST BUNGII' button should be enabled", "REQUEST BUNGII' button is enabled",
                    "REQUEST BUNGII' button is disabled");

    }

    /**
     * Verify terms checkbox in estimate page
     *
     * @param expectedValue expected value for terms and condition check box
     */
    public void checkTermsAndConditon(String expectedValue) {
        boolean isElementPresent = false;
        if (expectedValue.equalsIgnoreCase("UNCHECK")) {
            isElementPresent = checkIfElementIsPresent("TERMS OFF");
        } else {
            isElementPresent = checkIfElementIsPresent("TERMS ON");
        }
        String value = getElementValue("TERMS AND CONDITION");
        String expectedMsg = PropertyUtility.getMessage("customer.terms.checkbox.text.new");
        boolean isValueCorrect = value.equals(expectedMsg);

        testStepVerify.isTrue(isElementPresent,
                "Verify Terms & Condition checkBox should be " + expectedValue,
                "Verify Terms & Condition checkBox is " + expectedValue,
                "Verify Terms & Condition checkBox is not " + expectedValue);
        testStepVerify.isTrue(isValueCorrect,
                " Terms & Condition value Should be " + PropertyUtility.getMessage("customer.terms.checkbox.text"),
                " Terms & Condition value is " + PropertyUtility.getMessage("customer.terms.checkbox.text.new") + "as expected",
                "'Terms & Condition' value is not matching ,expected is " + PropertyUtility.getMessage("customer.terms.checkbox.text")
                        + "but actual is" + value);

    }

    /**
     * Verify trip Time Estimate row and value in estimate page
     *
     * @param expectedValue expected value for Time
     */
    public void checkTime(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("Time");
        String value = getElementValue("Time");
        System.err.println("Value is " + value);
        boolean isValueCorrect = value.equals(expectedValue);
        testStepVerify.isTrue(isElementPresent, "'Time' row should be present",
                "'Time' row  is present'", "'Time' row  not present'");
        if (!expectedValue.equals(""))
            testStepVerify.isTrue(isValueCorrect,
                    " Time value Should be " + expectedValue, " Time value is " + expectedValue + "as expected",
                    "'Time' value is not matching ,expected is" + expectedValue + "but actual is" + value);

    }

    /**
     * Verify trip Payment Method row and value in estimate page
     *
     * @param expectedValue expected value for Payment Method
     */
    public void checkPayment(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("Payment Method");
        String value = getElementValue("Payment Method");
        System.err.println("Value is " + value);

        if (expectedValue.trim().equalsIgnoreCase("{PREVIOUS VALUE}"))
            expectedValue = (String) cucumberContextManager.getScenarioContext("DEFAULT_CARD");

        boolean isValueCorrect = value.equals(expectedValue);
        String[] allCard=expectedValue.split("/");
        if (expectedValue.contains("/")){
            if(allCard.length==3)
                isValueCorrect = value.equals(expectedValue.split("/")[0]) || value.equals(expectedValue.split("/")[1])|| value.equals(expectedValue.split("/")[2]);
            else
                isValueCorrect = value.equals(expectedValue.split("/")[0]) || value.equals(expectedValue.split("/")[1]);
        }
        testStepVerify.isTrue(isElementPresent,
                "'Payment Method' row should be present", "'Payment Method' row  is present'",
                "'Payment Method' row  not present'");
        testStepVerify.isTrue(isValueCorrect,
                " Payment Method value Should be " + expectedValue,
                " Payment Method value is " + expectedValue + "as expected",
                "'Payment Method' value is not matching ,expected is" + expectedValue + "but actual is" + value);
    }

    /**
     * Verify trip Total Estimate row and value in estimate page
     *
     * @param expectedValue expected value for Total Estimate
     */
    public void checkEstimate(String expectedValue) {
        action.swipeUP();
        boolean isElementPresent = checkIfElementIsPresent("Total Estimate");
        String value = getElementValue("Total Estimate");
        System.err.println("Value is " + value);
        boolean isValueCorrect = false;

        if (expectedValue.equals("<IN DOLLAR>")) {
            String v = value.substring(2);
            boolean correct = v.matches("[0-9]*\\.?[0-9]+");

            isValueCorrect = value.startsWith("~$") && correct;
        } else if (expectedValue.equals("{PREVIOUS VALUE}")) {

            isValueCorrect = value.equals((String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));

        } else {
            isValueCorrect = value.equals(expectedValue);
        }

        testStepVerify.isTrue(isElementPresent,
                "'Total Estimate' row should be present", "'Total Estimate' row  is present'",
                "'Total Estimate' row  not present'");
        testStepVerify.isTrue(isValueCorrect,
                " Total Estimate value Should be " + expectedValue, " Time value is " + value + " as expected",
                "' Total Estimate' value is not matching ,expected is" + expectedValue + "but actual is" + value);
        action.swipeDown();
    }

    /**
     * Verify trip Promo row and value in estimate page
     *
     * @param expectedValue expected value for Promo
     */
    public void checkPromoCode(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("Promo Code");
        String value = getElementValue("Promo Code");
        System.err.println("Value is " + value);

        boolean isValueCorrect = false;

        if (expectedValue.equalsIgnoreCase("<ADDED_PROMO_CODE>")) {
            expectedValue = (String) cucumberContextManager.getScenarioContext("ADDED_PROMO_CODE");
            isValueCorrect = value.equals(expectedValue);
        } else {
            isValueCorrect = value.equals(expectedValue);
        }
        testStepVerify.isTrue(isElementPresent,
                "'Promo Code' row should be present", "'Promo Code' row  is present'",
                "'Promo Code' row  not present'");
        testStepVerify.isTrue(isValueCorrect,
                " Promo Code value Should be " + expectedValue, " Promo Code value is " + expectedValue + "as expected",
                "'Promo Code' value is not matching ,expected is" + expectedValue + "but actual is" + value);

    }

    /**
     * Verify trip load time row and value in estimate page
     *
     * @param expectedValue expected value for load time
     */
    public void checkLoadTime(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("Load/unload time");
        String value = getElementValue("Load/unload time");
        System.err.println("Value is " + value);
        boolean isValueCorrect = false;
        if (expectedValue.equals("{PREVIOUS VALUE}")) {
            isValueCorrect = value.equals((String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));

        }
        isValueCorrect = value.equals(expectedValue);
        testStepVerify.isTrue(isElementPresent,
                "'Load/unload time' row should be present", "'Load/unload time' row  is present'",
                "'Load/unload time' row  not present'");
        testStepVerify.isTrue(isValueCorrect,
                " Load/unload time value Should be " + expectedValue,
                "Load/unload time value is " + expectedValue + "as expected",
                "'Load/unload time' value is not matching ,expected is" + expectedValue + "but actual is" + value);

    }

    /**
     * Verify trip distance row and value in estimate page
     *
     * @param expectedValue expected value for trip distance
     */
    public void checkTripDistance(String expectedValue) {
        action.swipeUP();
        boolean isElementPresent = checkIfElementIsPresent("Trip Distance");
        String value = getElementValue("Trip Distance");
        System.err.println("Value is " + value);

        boolean isValueCorrect = false;
        if (expectedValue.equals("<IN MILES>")) {
            int val = value.indexOf(" ");
            String v = value.substring(0, val);

            boolean correct = v.matches("[0-9]*\\.?[0-9]+");

            isValueCorrect = value.contains("miles") && correct;
        } else if (expectedValue.equals("{PREVIOUS VALUE}")) {
            isValueCorrect = value.equals((String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));

        } else {
            isValueCorrect = value.equals(expectedValue);
        }

        testStepVerify.isTrue(isElementPresent,
                "'Trip Distance' row should be present", "'Trip Distance' row  is present'",
                "'Trip Distance' row  not present'");
        testStepVerify.isTrue(isValueCorrect,
                " Trip Distance value Should be " + expectedValue, " Trip Distance value is " + value + " as expected",
                "'Trip Distance' value is not matching ,expected is" + expectedValue + "but actual is" + value);
        action.swipeDown();
    }


    /**
     * Get Estimate details
     *
     * @return return value of different row from estimate screen
     */
    public String[] getEstimateDetails() {
        String[] details = new String[4];

        List<WebElement> genericStaticText = estimatePage.Text_GenericStaticText();

        details[0] = action.getValueAttribute(estimatePage.Text_DistValue()); //action.getValueAttribute(genericStaticText.get(1)); //miles
        //  details[0] = action.getValueAttribute(estimatePage.Text_DistanceValue());//2
        details[1] = action.getValueAttribute(estimatePage.Text_TimeValue());//action.getValueAttribute(genericStaticText.get(10));//Now  from 13 index moved to 10
        //   details[1] = action.getValueAttribute(estimatePage.Text_TimeValue());//11
        details[2] = action.getValueAttribute(estimatePage.Text_AmountValue()); //action.getValueAttribute(genericStaticText.get(5));//amount
        //    details[2] = action.getValueAttribute(estimatePage.Text_EstimateValue());//6
        details[3] = action.getValueAttribute(estimatePage.Value_LoadTime()); //action.getValueAttribute(genericStaticText.get(12));//mins from 14 index moved to 12
        //   details[3] = action.getValueAttribute(estimatePage.Text_LoadUnLoadTimeValue());//10
        return details;
    }

    /**
     * Enter loading/unloading time
     *
     * @param timeValue input that is to be entered
     */
    public void enterLoadingTime(String timeValue) {
        String inputValue = timeValue + " mins";
        action.click(estimatePage.Text_LoadTime());
        WebElement timeScroll = estimatePage.Wheel_LoadTime();
        //WebElement timeScroll = waitForExpectedElement(Wheel_LoadTime);

        if (!timeScroll.getAttribute("value").equals(inputValue))
            timeScroll.sendKeys(inputValue);
        //action.invisibilityOfElementLocated(estimatePage.Indicator_Loading());
        try{estimatePage.Button_Set().click();} catch(Exception ex){}
    }

    public boolean checkLoadingTime(String timeValue) {
        String inputValue = timeValue + " mins";
        action.click(estimatePage.Text_LoadTime());
        //	WebElement timeScroll = waitForExpectedElement(estimatePage.Wheel_LoadTime());
        WebElement timeScroll = estimatePage.Wheel_LoadTime();
        timeScroll.sendKeys(inputValue);
        String actualValue = estimatePage.Wheel_LoadTime().getAttribute("value");
        action.click(estimatePage.Button_Set());
        // action.click(estimatePage.Text_LoadTime());
        return actualValue.equals(inputValue);

    }

    /**
     * Click info icon of element and return info text
     *
     * @param key Element identifier whose info icon is to check
     * @return info string of alert
     */
    public String getInfoMessage(String key) {
        switch (key.toUpperCase()) {
            case "TIME":
                action.click(estimatePage.Button_InfoTime());
                break;
            case "LOAD/UPLOAD TIME":
                action.click(estimatePage.Button_InfoLoadingTime());
                break;
            case "TOTAL ESTIMATE":
                action.click(estimatePage.Button_InfoTotalEstimate());
                break;
            default:
                break;
        }

        return getDriver().switchTo().alert().getText();
    }


    /**
     * Select Bungii time in scroll wheel
     *
     * @param forwordDate integer of how far you want to scroll
     * @param hour        Trip hour
     * @param minutes     trip minutes
     * @param meridiem    AM/PM
     */
    public void selectBungiiTime(int forwordDate, String hour, String minutes, String meridiem) {

        action.click(estimatePage.Row_TimeSelect());
        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
        action.dateTimePicker(estimatePage.DatePicker_BungiiTime, estimatePage.DateWheel_BungiiTime, forwordDate, hour, minutes, meridiem);
        //  action.click(estimatePage.Row_TimeSelect());
        action.click(estimatePage.Button_Set());
    }

    /**
     * Select Bungii time
     */
    public void selectBungiiTime() {
        action.click(estimatePage.Row_TimeSelect());
        //  action.click(estimatePage.Row_TimeSelect());
        action.click(estimatePage.Button_Set());
    }

    /**
     * Select Bungii trip time to Now
     */
    public void selectBungiiTimeNow() {
        action.click(estimatePage.Row_TimeSelect());
        action.click(estimatePage.Button_Now());
        //    action.click(estimatePage.Button_Set());

    }

    /**
     * Add Last image from camera roll folder as bungii pickup item image
     */
    public void addBungiiPickUpImage(String option) throws InterruptedException{
        if (option.equalsIgnoreCase("4 images")) {
            addImage(4);
        } else if (option.equalsIgnoreCase("Default")) {
            addImage(1);
        } else if (option.equalsIgnoreCase("No image")) {
            addImage(0);
        } else if (option.equalsIgnoreCase("large image")) {
            addImage(1);
        } else
            addImage(1);

    }

    private void addImage(int numberOfImage) throws InterruptedException {
        for (int i = 1; i <= numberOfImage; i++) {
            if (i == 1)
                estimatePage.Button_AddPhoto().click();
            else
                estimatePage.Button_AddPhotoAdditional().click();
            //capture image instead of uploading existing image. this saves some time
            action.click(estimatePage.Button_Camera());
            if (action.isElementPresent(estimatePage.Button_PhotoCapture(true))) {
                //do nothing, directly move to steps after IF conditions
            } else if (action.isElementPresent(estimatePage.Button_OK(true)))
                action.click(estimatePage.Button_OK());
            Thread.sleep(3000);
            action.click(estimatePage.Button_PhotoCapture());
            Thread.sleep(3000);
            action.click(estimatePage.Button_UsePhoto());


            //commmented code to add image from galary
   /*         estimatePage.Button_Gallary().click();
            estimatePage.PhotosFolder().click();
            List<WebElement> folder = estimatePage.Cell_Photo();
            folder.get(folder.size() - 1).click();*/
        }
    }

    private void addLargeImage() {
        estimatePage.Button_AddPhoto().click();
        action.click(estimatePage.Button_Gallary());
        action.click(estimatePage.LargeImagePhotosFolder());
        List<WebElement> folder = estimatePage.Cell_Photo();
        folder.get(folder.size() - 1).click();
    }

    /**
     * Accept terms and condition checkbox
     */
    public void clickAcceptTerms() {
        action.swipeUP();
        if(!estimatePage.CheckBoxOff_Terms().isSelected()) {
            testStepVerify.isEquals(action.getText(estimatePage.Text_Checkbox_Terms()),PropertyUtility.getDataProperties("customer.checkbox"));
            action.click(estimatePage.CheckBoxOff_Terms());
            logger.detail("Checkbox Terms Selected : true ");
        }
        else
        {
            logger.detail("Checkbox Terms Selected [Existing] : true ");
        }
    }

    public void verifyDisclaimer(String Type) {
        if(Type.equalsIgnoreCase("solo")){
            testStepVerify.isEquals(action.getValueAttribute(estimatePage.Text_Solo_Disclaimer()),PropertyUtility.getDataProperties("customer.solo.disclaimer"));
            logger.detail("Disclaimer for "+Type+":"+action.getValueAttribute(estimatePage.Text_Solo_Disclaimer()));
        }
        else{
            testStepVerify.isEquals(action.getValueAttribute(estimatePage.Text_Duo_Disclaimer()),PropertyUtility.getDataProperties("customer.duo.disclaimer"));
            logger.detail("Disclaimer for "+Type+":"+action.getValueAttribute(estimatePage.Text_Duo_Disclaimer()));
        }
    }

    /**
     * Click cancel button on Navigation bar
     */
    public void clickCancel() {
        action.click(estimatePage.Button_Cancel());
    }

    /**
     * Click request bungii
     */
    public void clickRequestBungii() {
        action.swipeUP();
        action.swipeUP();
        action.click(estimatePage.Button_RequestBungii());
    }

    /**
     * Verify and accept alert message displayed while requesting bungii
     *
     * @param loadTime Load/UnLoad time
     * @return boolean value comparing actual alert message with that of expected message from properties file
     */
    public boolean verifyAndAcceptAlert(String loadTime) {

        String actualText = getDriver().switchTo().alert().getText();

        //Replace '<TIME>' keyword with load/unload time for current trip
        String bungiiType = (String) cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");

        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get geofence data from proper
        String perMileVlaue = utility.getGeofenceData(currentGeofence, "geofence.dollar.per.miles"), perMinutesVlaue = utility.getGeofenceData(currentGeofence, "geofence.dollar.per.minutes");

        String expectedText = PropertyUtility.getMessage("alert.Request.Bungii.ios").replaceAll("<TIME>", loadTime.trim()).replaceAll("<PER_MIN>", perMinutesVlaue.trim()).replaceAll("<PER_MILE>", perMileVlaue.trim());
        //VISHAL[21/12]: added message for duo as there is different message for duo trip
        if (bungiiType.equalsIgnoreCase("DUO"))
            expectedText = PropertyUtility.getMessage("alert.request.duo.bungii").replaceAll("<TIME>", loadTime.trim()).replaceAll("<PER_MIN>", perMinutesVlaue.trim()).replaceAll("<PER_MILE>", perMileVlaue.trim());
        getDriver().switchTo().alert().accept();
        logger.detail("Popup text on head up alert message:" + actualText);
        return actualText.equals(expectedText);
    }

    /**
     * return boolean value according to present of element on screen
     *
     * @param key Element identifier
     * @return boolean value according to presence of element
     */
    public boolean checkIfElementIsPresent(String key) {

        try {
            boolean isPresent = false;
            switch (key.toUpperCase()) {
                case "TRIP DISTANCE":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_Distance());
                    break;
                case "LOAD/UNLOAD TIME":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_LoadUnLoadTime());
                    break;
                case "PROMO CODE":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_PromoCode());
                    break;
                case "TOTAL ESTIMATE":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_Estimate());
                    break;
                case "PAYMENT METHOD":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_PaymentMethod());
                    break;
                case "TIME":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_Time());
                    break;
                case "REQUEST BUNGII":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Button_RequestBungii());
                    break;
                case "TERMS OFF":
                    isPresent = estimatePage.isElementEnabled(estimatePage.CheckBoxOff_Terms());
                    break;
                case "TERMS ON":
                    isPresent = estimatePage.isElementEnabled(estimatePage.CheckBoxOn_Terms());
                    break;
                default:
                    System.err.println("ELEMET not found in case" + key);
                    break;
            }
            return isPresent;
        } catch (Exception e) {
            return false;

        }
    }

    /**
     * Return value of element displayed on screen
     *
     * @param key Element identifier
     * @return
     */
    public String getElementValue(String key) {
        String value = "";
        switch (key.toUpperCase()) {
            case "TRIP DISTANCE":
                value = action.getValueAttribute(estimatePage.Text_DistanceValue());
                break;
            case "LOAD/UNLOAD TIME":
                value = action.getValueAttribute(estimatePage.Text_LoadUnLoadTimeValue());
                break;
            case "PROMO CODE":
                value = action.getNameAttribute(estimatePage.Text_PromoCodeValue());
                break;
            case "TOTAL ESTIMATE":
                value = action.getValueAttribute(estimatePage.Text_EstimateValue());
                break;
            case "PAYMENT METHOD":
                value = action.getNameAttribute(estimatePage.Text_PaymentMethodValue());
                break;
            case "TIME":
                value = action.getValueAttribute(estimatePage.Text_TimeValue());
                break;
            case "TERMS AND CONDITION":
                value = action.getValueAttribute(estimatePage.CheckBox_Value());
                break;
            default:
                System.err.println("ELEMENT not found in case" + key);
                break;
        }
        return value;
    }
}
