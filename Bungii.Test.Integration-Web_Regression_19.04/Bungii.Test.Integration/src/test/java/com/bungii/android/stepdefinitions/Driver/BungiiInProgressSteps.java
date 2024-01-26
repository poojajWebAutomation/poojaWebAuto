package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.ScheduledTripsPage;
import com.bungii.android.pages.customer.BungiiAcceptedPage;
import com.bungii.android.pages.customer.EstimatePage;
import com.bungii.android.pages.driver.InProgressBungiiPages;
import com.bungii.android.pages.driver.LoginPage;
import com.bungii.android.pages.driver.ScheduledBungiiPage;
import com.bungii.android.pages.driver.UpdateStatusPage;
import com.bungii.android.pages.otherApps.OtherAppsPage;
import com.bungii.android.stepdefinitions.Customer.SignupSteps;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiInProgressSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    InProgressBungiiPages bungiiProgressPage = new InProgressBungiiPages();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    BungiiAcceptedPage bungiiAcceptedPage = new BungiiAcceptedPage();
    OtherAppsPage otherAppsPage = new OtherAppsPage();
    ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();
    InProgressBungiiPages inProgressBungiiPages=new InProgressBungiiPages();
    EstimatePage bungiiEstimatePage = new EstimatePage();
    UpdateStatusPage updateStatusPage = new UpdateStatusPage();
    ScheduledBungiiPage scheduledBungiiPage = new ScheduledBungiiPage();
    GeneralUtility GeneralUtility = new GeneralUtility();
    InProgressBungiiPages Page_DriverBungiiProgress = new InProgressBungiiPages();
    InProgressBungiiPages inProgressPages=new InProgressBungiiPages();
    LoginPage driverLogInPage = new LoginPage();
    //ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();

    @Then("^Trip Information should be correctly displayed on \"([^\"]*)\" status screen for \"([^\"]*)\" driver$")
    public void trip_information_should_be_correctly_displayed_on_something_status_screen_for_customer(String key, String driverType) {
        try {


            String expectedCustName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            expectedCustName = expectedCustName.substring(0, expectedCustName.indexOf(" ") + 2);
            boolean isCustomerNameCorrect = false;
            boolean isDriverNameCorrect = false;
            if (driverType.equalsIgnoreCase("controller")) {
                //drivername and customer name validation
                if (String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO")) {
                    isCustomerNameCorrect = action.getText(bungiiProgressPage.Text_DuoCustomer_Name()).equals(expectedCustName);

                    String driver2Name = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
                    Thread.sleep(3000);
                    String driverName = action.getText(bungiiProgressPage.Text_DuoDriver_Name());
                    String expected2 = driver2Name.substring(0, driver2Name.indexOf(" ") + 2);

                    isDriverNameCorrect = driverName.equals(expected2);

                    logger.detail("Driver 2" + driver2Name.substring(0, driver2Name.indexOf(" ") + 2));
                    testStepVerify.isTrue(isDriverNameCorrect,
                            "Driver name should correctly display",
                            "Driver name was correctly displayed",
                            "Driver name was not correctly displayed. [" + driverName + " ] is displayed instead of " + expected2);
                } else
                    isCustomerNameCorrect = getCustomerName().equals(expectedCustName);

            }
            else
            {
                Thread.sleep(5000);
                if (String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO")) {
                    isCustomerNameCorrect = action.getText(bungiiProgressPage.Text_DuoCustomer_Name()).equals(expectedCustName);

                    String driver1Name = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
                    Thread.sleep(3000);

                    String driverName = action.getText(bungiiProgressPage.Text_DuoDriver_Name());
                    String expected1 = driver1Name.substring(0, driver1Name.indexOf(" ") + 2);

                    isDriverNameCorrect = driverName.equals(expected1);

                    logger.detail("driver1Name" + driver1Name.substring(0, driver1Name.indexOf(" ") + 2));
                    testStepVerify.isTrue(isDriverNameCorrect,
                            "Driver name should correctly display",
                            "Driver name was correctly displayed",
                            "Driver name was not correctly displayed. [" + driverName + " ] is displayed instead of " + expected1);
                } else
                    isCustomerNameCorrect = getCustomerName().equals(expectedCustName);
            }
            switch (key) {
                case "EN ROUTE":
                    validateEnRouteInfo(getTripInformation(key));
                    break;
                case "ARRIVED":
                    validateArrivedInfo(getTripInformation(key));
                    break;
                case "LOADING ITEM":
                    validateArrivedInfo(getTripInformation(key));
                    break;
                case "DRIVING TO DROP OFF":
                    validateDrivingInfo(getTripInformation(key));
                    break;
                case "UNLOADING ITEM":
                    validateUnloadingInfo(getTripInformation(key));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            if (/*isInfoCorrectlyDisplayed && */isCustomerNameCorrect) {
                pass("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");
            } else {
                fail("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");

            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Trip Information should be correctly displayed on \"([^\"]*)\" status screen for driver$")
    public void trip_information_should_be_correctly_displayed_on_something_status_screen_for_customer(String key) {
        try {


            String expectedCustName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            expectedCustName = expectedCustName.substring(0, expectedCustName.indexOf(" ") + 2);
            boolean isCustomerNameCorrect;
            //drivername and customer name validation
            if(String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO")){
                isCustomerNameCorrect=action.getText(bungiiProgressPage.Text_DuoCustomer_Name()).equals(expectedCustName);

                String driver1Name=(String) cucumberContextManager.getScenarioContext("DRIVER_1");
                String driver2Name=(String) cucumberContextManager.getScenarioContext("DRIVER_2");

                     String driverName = action.getText(bungiiProgressPage.Text_DuoDriver_Name());
                     String expected1 = driver1Name.substring(0,driver1Name.indexOf(" ")+2);
                     String expected2 = driver2Name.substring(0,driver2Name.indexOf(" ")+2);

                boolean isDriverNameCorrect=driverName.equals(expected1) || driverName.equals(expected2);

                logger.detail("driver1Name"+driver1Name.substring(0,driver1Name.indexOf(" ")+2) +"|||Driver 2"+driver2Name.substring(0,driver2Name.indexOf(" ")+2));
                testStepVerify.isTrue(isDriverNameCorrect,
                        "Driver name should correctly display",
                        "Driver name was correctly displayed",
                        "Driver name was not correctly displayed. ["+driverName+" ] is displayed instead of "+ expected1 +" or "+ expected2);
            }
            else
                isCustomerNameCorrect = getCustomerName().equals(expectedCustName);
            switch (key) {
                case "EN ROUTE":
                    validateEnRouteInfo(getTripInformation(key));
                    break;
                case "ARRIVED":
                    validateArrivedInfo(getTripInformation(key));
                    break;
                case "LOADING ITEM":
                    validateArrivedInfo(getTripInformation(key));
                    break;
                case "DRIVING TO DROP OFF":
                    validateDrivingInfo(getTripInformation(key));
                    break;
                case "UNLOADING ITEM":
                    validateUnloadingInfo(getTripInformation(key));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

            if (/*isInfoCorrectlyDisplayed && */isCustomerNameCorrect) {
                pass("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");
            } else {
                fail("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");

            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    /**
     * Get trip information , Information while is below status icon , ex drop location etc
     *
     * @return Get trip information
     */
    public List<String> getTripInformation(String key) {
        List<String> details = new ArrayList<>();
        switch (key) {
            case "EN ROUTE":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                details.add(action.getText(bungiiProgressPage.Bungii_ETA()));
                break;
            case "ARRIVED":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                break;
            case "LOADING ITEM":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                break;
            case "DRIVING TO DROP OFF":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                details.add(action.getText(bungiiProgressPage.Bungii_ETA()));
                break;
            case "UNLOADING ITEM":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }

        return details;
    }

    private boolean validateUnloadingInfo(List<String> actualInfo) {
        logger.detail("INside trip info validation");

        boolean isTagDisplayed = actualInfo.get(0).equals("DROP OFF LOCATION");
        String actualDroplocation=actualInfo.get(1).replace(",","").replace("  "," ");

        String dropOffLocationLineOne=String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        boolean isDropDisplayed =actualDroplocation.contains(dropOffLocationLineOne) &&actualDroplocation.contains(dropOffLocationLineTwo) ;


            testStepVerify.isEquals(actualInfo.get(0), "DROP OFF LOCATION",
                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'PDROP OFF LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isDropDisplayed,

                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is " + actualInfo.get(1) + "and expected is " + dropOffLocationLineOne+dropOffLocationLineTwo
                    ,
                    "Pick up location was not displayed correctly, actual was is " + actualInfo.get(1) + " and expected is" +dropOffLocationLineOne+dropOffLocationLineTwo
            );


        return isTagDisplayed && isDropDisplayed;
    }

    private boolean validateEnRouteInfo(List<String> actualInfo) {
        logger.detail("INside trip info validation");

        boolean isTagDisplayed = actualInfo.get(0).equals("PICKUP LOCATION");
        boolean isETACorrect = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("mins");
        String actualPickuplocation=actualInfo.get(1).replace(",","").replace("  "," ");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",","").replace("  "," ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",","").replace("  "," ").trim();
        boolean isPickUpDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) &&actualPickuplocation.contains(pickUpLocationLineTwo);

        String actualDroplocation=actualInfo.get(1).replace(",","").replace("  "," ");
        testStepVerify.isTrue(isTagDisplayed, "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed", "'PICKUP LOCATION' Tag was not correctly displayed");
            testStepVerify.isTrue(isETACorrect,
                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));
            testStepVerify.isTrue(isPickUpDisplayed,
                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + " and expected is " + pickUpLocationLineOne+pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualInfo.get(1) + " and expected is " +pickUpLocationLineOne+pickUpLocationLineTwo);

        return isTagDisplayed && isETACorrect && isPickUpDisplayed;
    }

    private boolean validateDrivingInfo(List<String> actualInfo) {
        logger.detail("inside trip info validation");

        boolean isTagDisplayed = actualInfo.get(0).equals("DROP OFF LOCATION");
        boolean isETAdisplayed = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("mins");
        String actualDropoffLocation=actualInfo.get(1).replace(",","").replace("  "," ");

        String dropOffLocationLineOne=String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        boolean isDropDisplayed =actualDropoffLocation.contains(dropOffLocationLineOne) &&actualDropoffLocation.contains(dropOffLocationLineTwo) ;


            testStepVerify.isTrue(isTagDisplayed,
                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'PDROP OFF LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isETAdisplayed,
                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

            testStepVerify.isTrue(isDropDisplayed,
                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + " and expected is " + dropOffLocationLineOne+dropOffLocationLineTwo
                    ,
                    "Pick up location was not displayed correctly, actual was is" + actualInfo.get(1) + "and expected is" + dropOffLocationLineOne+dropOffLocationLineTwo
            );

        return isTagDisplayed && isETAdisplayed && isDropDisplayed;
    }

    private boolean validateArrivedInfo(List<String> actualInfo) {
        logger.detail("inside trip info validation");

        boolean isTagDisplayed = actualInfo.get(0).equals("PICKUP LOCATION");
        String actualPickuplocation=actualInfo.get(1).replace(",","").replace("  "," ");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",","").replace("  "," ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",","").replace("  "," ").trim();
        boolean isPickUpDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) &&actualPickuplocation.contains(pickUpLocationLineTwo);

        String actualDroplocation=actualInfo.get(1).replace(",","").replace("  "," ");
            testStepVerify.isTrue(isTagDisplayed,
                    "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
                    "'PICKUP LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isPickUpDisplayed,
                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + " and expected is " + pickUpLocationLineOne+pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualInfo.get(1) + "and expected is" + pickUpLocationLineOne+pickUpLocationLineTwo);

        return isTagDisplayed && isPickUpDisplayed;
    }

    @And("^stack trip information should be displayed on deck$")
    public void stack_trip_information_should_be_displayed_on_deck() {
        try {
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER2");
//            testStepVerify.isElementTextEquals(bungiiProgressPage.Text_NextLabel(), "NEXT CUSTOMER","'NEXT CUSTOMER' text lable should be displayed","'NEXT' text lable is displayed","'NEXT' text lable is not displayed"); - Next customer not displayed with new driver app changes
            testStepVerify.isTrue(bungiiProgressPage.Text_OnDeckLabel().getText().contains("Bungii on deck, try to finish by"),"On Deck text label should be displayed","'On Deck text label is not displayed");
//            testStepVerify.isElementTextEquals(bungiiProgressPage.Text_StackCustomer(), customerName.substring(0, customerName.indexOf(" ") + 2));
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^stack trip information should not be displayed on deck$")
    public void stack_trip_information_should_not_be_displayed_on_deck() {
        try {
            testStepVerify.isElementNotEnabled(bungiiProgressPage.Text_NextLabel(true), "Next tag should not be enabled","Next tag is not displayed","Next tag is displayed");
            testStepVerify.isElementNotEnabled(bungiiProgressPage.Text_OnDeckLabel(true), "ON DECK should not be displayed" ,"'ON DECK' text lable is not displayed","ON DECK is displayed");
            testStepVerify.isElementNotEnabled(bungiiProgressPage.Text_StackCustomer(true),"stack Customer name should be not be diplayed","stack Customer name should be not be diplayed","stack Customer name is diplayed");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^try to finish time should be correctly displayed for long stack trip$")
    public void try_to_finish_time_should_be_correctly_displayed() throws Throwable {

        if(((String)cucumberContextManager.getScenarioContext("DRIVER_MIN_ARRIVAL")).equalsIgnoreCase(""))
        {
            String[] calculatedTime=getTeletTimeinLocalTimeZone();
            cucumberContextManager.setScenarioContext("DRIVER_TELET",calculatedTime[0]);
            cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL",calculatedTime[1]);
            cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL",calculatedTime[2]);
        }
        String telet=((String)cucumberContextManager.getScenarioContext("DRIVER_TELET"));
        //Initial Zero is truncated
        //Sprint 32 , Initial zero are displayed
/*        if (telet.startsWith("0"))
            telet = telet.substring(1);*/
//Richa
        String[] timeZone=utility.getDayLightTimeZoneBasedOnGeofence();
        String normalTimeZone="try to finish up by "+telet+" "+timeZone[0];
        String dayLightTimeZone="try to finish up by "+telet+" "+timeZone[1];
        String actualTime=action.getText(bungiiProgressPage.Text_OnDeckLabel());

        if(actualTime.contains(normalTimeZone) || actualTime.contains(dayLightTimeZone)) {
            testStepAssert.isTrue(true,"The finish time should be displayed correctly.", "The finish time is not displayed correctly.");
        }
        else
        {
            testStepAssert.isFail("The finish time is not displayed correctly.");
        }

    }
    @Then("^try to finish time should be correctly displayed for short stack trip$")
    public void try_to_finish_time_should_be_correctly_displayed_ShortStack() throws Throwable {
        calculateShortStack();
        testStepVerify.isElementTextEquals(bungiiProgressPage.Text_FinishBy(),"Bungii on deck, try to finish up by "+((String)cucumberContextManager.getScenarioContext("DRIVER_FINISH_BY"))+" "+utility.getTimeZoneBasedOnGeofence());

    }
    @Then("^I calculate projected driver arrival time$")
    public void i_calculate_projected_driver_arrival_time() throws Throwable {
           calculateShortStack();
    }

    @Then("^correct message should be displayed after clicking info button$")
    public void correct_message_should_be_displayed_after_clicking_info_button() throws Throwable {
        action.click(bungiiProgressPage.Button_StackInfo());
        testStepVerify.isElementTextEquals(bungiiProgressPage.Alert_Message(),PropertyUtility.getMessage("driver.stack.info.button.alert"));
    }

    public String[] getTeletTimeinLocalTimeZone(){
        String[] calculatedTime=new String[3];
        try {
            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
            String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
            String custRef = DbUtility.getCustomerRefference(phoneNumber);
            String teletTime = DbUtility.getTELETfromDb(custRef);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            //By default data is in UTC
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date teletTimeInUtc = formatter.parse(teletTime);
            DateFormat formatterForLocalTimezone = new SimpleDateFormat("hh:mm a");
            formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
            String teletInLocalTime = formatterForLocalTimezone.format(teletTimeInUtc);
            long t= teletTimeInUtc.getTime();
            long ONE_MINUTE_IN_MILLIS=Long.parseLong(PropertyUtility.getDataProperties("one.minute.in.milliseconds"));
            Date minTime=new Date(t - (15 * ONE_MINUTE_IN_MILLIS));
            String strMindate = formatterForLocalTimezone.format(minTime);


            Date maxTime=new Date(t + (30 * ONE_MINUTE_IN_MILLIS));
            String strMaxdate = formatterForLocalTimezone.format(maxTime);
            calculatedTime[0] =teletInLocalTime;
            calculatedTime[1] =strMindate;
            calculatedTime[2] =strMaxdate;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calculatedTime;
    }
    public void calculateShortStack() throws ParseException {
//        cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");
        try {

            int FROM_RANGE_FROM = -10;
            int FROM_RANGE_TO = +20;
            long ONE_MINUTE_IN_MILLIS = Long.parseLong(PropertyUtility.getDataProperties("one.minute.in.milliseconds"));

            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
            String customerPhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");//customerPhoneNumber="9999991889";
            String customer2PhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");//customer2PhoneNumber="9999991259";
            String driverPhoneNumber = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");//driverPhoneNumber="9955112208";

            String[] loadingTimeStamp = com.bungii.android.utilityfunctions.DbUtility.getLoadingTimeStamp(customerPhoneNumber);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //By default data is in UTC
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date loadingStartTime = formatter.parse(loadingTimeStamp[0]);
            Date loadingEtartTime = formatter.parse(loadingTimeStamp[1]);
            long duration = loadingEtartTime.getTime() - loadingStartTime.getTime();
            long loadingTime = TimeUnit.MILLISECONDS.toMinutes(duration);

            String[] driverLocation = com.bungii.android.utilityfunctions.DbUtility.getDriverLocation(driverPhoneNumber);
            String[] pickup1Locations = com.bungii.android.utilityfunctions.DbUtility.getPickupAndDropLocation(customerPhoneNumber);
            String[] pickup2Locations = com.bungii.android.utilityfunctions.DbUtility.getPickupAndDropLocation(customer2PhoneNumber);

            String[] dropLocation = new String[2];
            dropLocation[0] = pickup1Locations[2];
            dropLocation[1] = pickup1Locations[3];
            String[] newPickupLocations = new String[2];
            newPickupLocations[0] = pickup2Locations[0];
            newPickupLocations[1] = pickup2Locations[1];

            long[] timeToCoverDistance = new GoogleMaps().getDurationInTraffic(driverLocation, dropLocation, newPickupLocations);
            logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance[0]+" and "+timeToCoverDistance[1]);
            int FLUFF_TIME = 4;
            loadingTime = (loadingTime < 1 ? 10 : loadingTime);
            // loadingTime=10;
            logger.detail("loadingTime "+loadingTime);
            Double totalTimeETAtoPickup = (double)loadingTime + (double)timeToCoverDistance[0] / 60 + (double)timeToCoverDistance[1] / 60 + FLUFF_TIME;
            logger.detail("totalTimeETAtoPickup "+totalTimeETAtoPickup);
            Double tripProjectedEndTime = (double)loadingTime + (double) timeToCoverDistance[0] / 60;
            logger.detail("tripProjectedEndTime "+tripProjectedEndTime);
            String tripStartTime = com.bungii.android.utilityfunctions.DbUtility.getStatusTimeStampForStack(customer2PhoneNumber);
            logger.detail("Status Timestamp "+tripStartTime);
            Date tryToFinishTome_Temp = formatter.parse(tripStartTime);
            DateFormat formatterForLocalTimezone = new SimpleDateFormat("hh:mm a");
            formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

            Date tryToFinishTome = new Date(tryToFinishTome_Temp.getTime() +new Double(ONE_MINUTE_IN_MILLIS * new Double(tripProjectedEndTime)).longValue());
            String driverTime = formatterForLocalTimezone.format(tryToFinishTome);

            Date timeStampToCalculateDate = new Date(tryToFinishTome_Temp.getTime() + new Double(ONE_MINUTE_IN_MILLIS * new Double(totalTimeETAtoPickup)).longValue());


            Date minTime = new Date(timeStampToCalculateDate.getTime() + (FROM_RANGE_FROM * ONE_MINUTE_IN_MILLIS));
            String strMindate = formatterForLocalTimezone.format(minTime);

            Date maxTime = new Date(timeStampToCalculateDate.getTime() + (FROM_RANGE_TO * ONE_MINUTE_IN_MILLIS));
            String strMaxdate = formatterForLocalTimezone.format(maxTime);
            cucumberContextManager.setScenarioContext("DRIVER_FINISH_BY", driverTime);
            cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL", strMindate);
            cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL", strMaxdate);
            logger.detail("[As Per calculation Of Short Stack for Trip of Customer "+customer2PhoneNumber+"] Driver to Finish By :"+ driverTime + "Range "+FROM_RANGE_FROM+","+FROM_RANGE_TO+"["+strMindate+" : "+strMaxdate+"]");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

   /* public void calculateShortStack() throws ParseException {
        int FROM_RANGE_FROM =-10;
        int FROM_RANGE_TO =+20;

        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        String customerPhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");//customerPhoneNumber="9999991889";
        String customer2PhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");//customer2PhoneNumber="9999991259";
        String driverPhoneNumber = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");//driverPhoneNumber="9955112208";

         String [] loadingTimeStamp=DbUtility.getLoadingTimeStamp(customerPhoneNumber);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //By default data is in UTC
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date loadingStartTime = formatter.parse(loadingTimeStamp[0]);
        Date loadingEtartTime = formatter.parse(loadingTimeStamp[1]);
        long duration = loadingEtartTime.getTime() - loadingStartTime.getTime();
        long loadingTime=TimeUnit.MILLISECONDS.toMinutes(duration);


        String[] driverLocation = DbUtility.getDriverLocation(driverPhoneNumber);
        String [] pickup1Locations=DbUtility.getPickupAndDropLocation(customerPhoneNumber);
        String [] pickup2Locations=DbUtility.getPickupAndDropLocation(customer2PhoneNumber);

        String[] dropLocation = new String[2];dropLocation[0]=pickup1Locations[2];dropLocation[1]=pickup1Locations[3];
        String[] newPickupLocations = new String[2];newPickupLocations[0]=pickup2Locations[0];newPickupLocations[1]=pickup2Locations[1];

        long[]  timeToCoverDistance=new GoogleMaps().getDurationInTraffic(driverLocation,dropLocation,newPickupLocations);
     //   String custRef = DbUtility.getCustomerRefference(phoneNumber);
//
        //=if((C5<1),10,C5)+D5+E5+4
        int FLUFF_TIME=4;
        loadingTime=(loadingTime<1?10:loadingTime);
     //   loadingTime=10;
        long totalTimeETAtoPickup=loadingTime+timeToCoverDistance[0]+timeToCoverDistance[1]+FLUFF_TIME;
        long tripProjectedEndTime=loadingTime+timeToCoverDistance[0];
        String tripStartTime=DbUtility.getStatusTimeStampForStack(customer2PhoneNumber);
        Date tryToFinishTome_Temp = formatter.parse(tripStartTime);
        long ONE_MINUTE_IN_MILLIS=60000;//millisecs
        DateFormat formatterForLocalTimezone = new SimpleDateFormat("hh:mm a");
        formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

        Date tryToFinishTome=new Date(tryToFinishTome_Temp.getTime() + (ONE_MINUTE_IN_MILLIS * tripProjectedEndTime));
        String driverTime = formatterForLocalTimezone.format(tryToFinishTome);

        Date timeStampToCalculateDate=new Date(tryToFinishTome_Temp.getTime() + (ONE_MINUTE_IN_MILLIS * totalTimeETAtoPickup));


        Date minTime=new Date(timeStampToCalculateDate.getTime() + (FROM_RANGE_FROM * ONE_MINUTE_IN_MILLIS));
        String strMindate = formatterForLocalTimezone.format(minTime);

        Date maxTime=new Date(timeStampToCalculateDate.getTime() + (FROM_RANGE_TO * ONE_MINUTE_IN_MILLIS));
        String strMaxdate = formatterForLocalTimezone.format(maxTime);
        cucumberContextManager.setScenarioContext("DRIVER_FINISH_BY",driverTime);
        cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL",strMindate);
        cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL",strMaxdate);

    }
*/
    @When("^I click \"([^\"]*)\" on bungii accepted screen$")
    public void i_click_something_on_bungii_accepted_screen(String button) throws Throwable {
        try {
            switch (button) {
                case "CANCEL BUNGII":
                    testStepVerify.isElementTextEquals(bungiiAcceptedPage.Button_CancelBungii(),"CANCEL BUNGII");
                    action.click(bungiiAcceptedPage.Button_CancelBungii());
                    break;
                case "Cantact Support on Alert message":
                    testStepVerify.isElementTextEquals(bungiiAcceptedPage.Alert_ContactSupport(),"Contact Customer Support");
                    action.click(bungiiAcceptedPage.Alert_ContactSupport());
                    break;
                case "CANCEL BUNGII on Alert message":
                    testStepVerify.isElementTextEquals(bungiiAcceptedPage.Alert_CancelBungii(),"Cancel Bungii");
                    action.click(bungiiAcceptedPage.Alert_CancelBungii());
                    break;
                case"Dismiss on Alert message":
                    testStepVerify.isElementTextEquals(bungiiAcceptedPage.Alert_Dismiss(),"Dismiss");
                    action.click(bungiiAcceptedPage.Alert_Dismiss());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I tap on" + button, "I tapped on actionItem"+button, true);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }    }

    @Then("^I see \"([^\"]*)\" on bungii accepted screen$")
    public void     i_see_something_on_bungii_accepted_screen(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Alert: Bungii cancel confirmation":
                    testStepVerify.isElementTextEquals(bungiiAcceptedPage.Text_StackConfirmation(),PropertyUtility.getMessage("customer.stack.cancel.confirm.alert"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }


    @When("^I verify that driver to pickup time is greater than 100 mins for second trip$")
    public void i_verify_that_driver_to_pickup_time_is_greater_than_100_mins_for_second_trip() {
        String customer2PhoneNumber=(String)cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
        String driverPhoneNumber=(String)cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
        String custRef = DbUtility.getCustomerRefference(customer2PhoneNumber);
        String pickupID = DbUtility.getPickupID(custRef);
        String pickupRef = DbUtility.getPickupReff(custRef);
        DbUtility.isDriverEligibleForTrip(driverPhoneNumber,pickupRef);
        int driverToPickUP=Integer.valueOf(DbUtility.getDriverToPickupTime(driverPhoneNumber,pickupID));

        testStepVerify.isTrue(driverToPickUP>100,"Driver to pickp value should be greater that 100 ", "Driver to pickup value is "+driverToPickUP +" min","Driver to pickup value is "+driverToPickUP +" min");
    }

    @Then("^I should not get notification for ([^\"]*)$")
    public void i_should_not_get_notification_for_stack_trip(String message) {
            try {
                //   SetupManager.getObject().terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                action.showNotifications();
                log("Checking notifications","Checking notifications",true);
                String expecteMessage = utility.getExpectedNotification(message.toUpperCase());

                boolean isFound = utility.getNofitication("Bungii QAAuto", expecteMessage);
                action.hideNotifications();

               /* boolean isFound = utility.clickOnNofitication("Bungii", expecteMessage);
                if (!isFound) {
                    Thread.sleep(5000);
                    isFound = utility.clickOnNofitication("Bungii", expecteMessage);
                }
                logger.detail(SetupManager.getDriver().getPageSource());
                //stack take times to get notifications
                    for (int i=0; i<0 &&!isFound;i++){
                        Thread.sleep(40000);
                        isFound = utility.clickOnNofitication("Bungii", expecteMessage);
                        i++;
                    }

                //if no notification then hide
                if (!isFound) {
                    action.hideNotifications();
                    Thread.sleep(5000);

                    action.click(otherAppsPage.Status_Bar());

                }*/
                testStepVerify.isFalse(isFound, "I should not get notification "+ message ," I didnt get notification for stack trip","I got notifcation of stack trip");

            } catch (Exception e) {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
            }


    }
    @And("^I wait for Minimum duration for current Bungii to be T-2 hours$")
    public void i_wait_for_minimum_duration_for_something_bungii_to_be_in_t_minus2() {
        try {
/*
            String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");


            DateFormat formatter = new SimpleDateFormat("MMM d, h:mm a");
            formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));
            Date bungiiDate = formatter.parse(bungiiTime);


            Date currentDate = new Date();
            bungiiDate.setYear(currentDate.getYear());//(Integer.parseInt(currentDate.getYear()));
            long duration = bungiiDate.getTime() - currentDate.getTime();

            long diffInMinutes;
            int mininumWaitTime = 120;

            diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration) - mininumWaitTime;
            //minimum 1  min wait
            diffInMinutes = diffInMinutes + 1;
            if (diffInMinutes > 0) {
                action.hardWaitWithSwipeUp((int) diffInMinutes);
            } else {
                // minimum wait of 30 mins

            }
            */
                  //Commented since it waits more than hour

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should not be able to see \"([^\"]*)\" on screen$")
    public void i_should_not_be_able_to_see_something_on_screen(String option) throws Throwable {
        try {
            switch (option) {
                case "Details From Customer":
                    testStepAssert.isFalse(action.isElementPresent(inProgressBungiiPages.Button_DetailsFromCustomer(true)),"Option should not be present.", "Option is not present.", "Option is present.");
                    break;
            }

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    /**
     * Get Driver Name
     *
     * @return value of customer name
     */
    public String getCustomerName() {

        return action.getText(bungiiProgressPage.Bungii_Customer_Name());
    }

    @When("^Bungii driver uploads \"([^\"]*)\" image$")
    public void bungii_driver_uploads_something_image(String numberofimages) throws Throwable {
        try {
            action.click(inProgressBungiiPages.Button_AddPhoto());
            if (action.isElementPresent(bungiiEstimatePage.Message_CameraPermissions(true))){
                Thread.sleep(2000);
                action.click(bungiiEstimatePage.Permissions_CameraAllow());
                }
            if (!action.isElementPresent(bungiiEstimatePage.Button_CameraIcon(true))){
                Thread.sleep(2000);
                action.click(inProgressBungiiPages.Button_AddPhoto());
            }
            Thread.sleep(2000);
            switch (numberofimages){
                case "1":
                    Thread.sleep(2000);
                    action.click(bungiiEstimatePage.Button_CameraIcon());
                    Thread.sleep(2000);
                    Point p1 = new Point(360,1310);
                    Point p2= new Point(430, 1242);
                    action.click(p1);
                    Thread.sleep(2000);

                    action.click(p2);
                    if(!action.isElementPresent(inProgressBungiiPages.Image_UploadedImage(true))){

                        if (!action.isElementPresent(bungiiProgressPage.Button_Save(true))) {
                            action.click(p1);
                            Thread.sleep(2000);
                            action.click(p2);
                        }
                        else {
                            action.click(inProgressBungiiPages.Button_AddPhoto());
                            Thread.sleep(2000);
                            action.click(bungiiEstimatePage.Button_CameraIcon());
                            Thread.sleep(2000);
                            action.click(p1);
                            Thread.sleep(2000);
                            action.click(p2);
                        }
                    }
                    Thread.sleep(2000);
                   if(!action.isElementPresent(inProgressBungiiPages.Image_UploadedImage(true))) {
                    action.click(inProgressBungiiPages.Button_AddPhoto());
                    Thread.sleep(2000);
                       if(!action.isElementPresent(bungiiEstimatePage.Button_CameraIcon((true)))) {
                           action.click(inProgressBungiiPages.Button_AddPhoto());
                       }
                    action.click(bungiiEstimatePage.Button_CameraIcon());
                    Thread.sleep(2000);
                    action.click(p1);
                    Thread.sleep(2000);
                    action.click(p2);
                }
                    testStepVerify.isElementDisplayed(inProgressBungiiPages.Image_UploadedImage(true),"Captured Image should be shown","Captured Image is shown", "Captured Image is not shown");
                    Thread.sleep(2000);
                    action.click(bungiiProgressPage.Button_Save());
                    break;
            }
        }catch(Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }

    }
    @Then("^I should see the customers name under the customer name field$")
    public void i_should_see_the_customers_name_under_the_customer_name_field() throws Throwable {
        try{
        String deliveryCreatedCustomerName = cucumberContextManager.getScenarioContext("CUSTOMER").toString().substring(0,30);
        String customerName = action.getText(updateStatusPage.Text_CustomerNameOnDriverApp());
        testStepAssert.isEquals(customerName,deliveryCreatedCustomerName,deliveryCreatedCustomerName+ "Should be displayed",customerName+ "is displayed",deliveryCreatedCustomerName+ "is not displayed");
    }catch(Exception ex){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

    }
    }

    @And("^I should be able to add the text \"([^\"]*)\" in the signed by field$")
    public void i_should_be_able_to_add_the_text_something_in_the_signed_by_field(String text) throws Throwable {
        try{
        Thread.sleep(1000);
        action.clearSendKeys(updateStatusPage.TextBox_SignedByField(),text);
            log("I should be able to add the text "+ text + " in the signed by field","I should be able to add the text "+ text + " in the signed by field",false);
    }catch(Exception ex){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

    }
    }

    @And("^I should be able to add customer signature$")
    public void i_should_be_able_to_add_customer_signature() throws Throwable {
        try{
        Thread.sleep(2000);
        action.click(updateStatusPage.TextBox_Signature());
        int xStart =Integer.parseInt(PropertyUtility.getDataProperties("signature.x.start"));
        int yStart =Integer.parseInt(PropertyUtility.getDataProperties("signature.y.start"));
        int xEnd =Integer.parseInt(PropertyUtility.getDataProperties("signature.x.end"));
        int yEnd =Integer.parseInt(PropertyUtility.getDataProperties("signature.y.end"));
        action.DrawSignature(xStart,yStart,xEnd,yEnd);
        Thread.sleep(5000);
        log("I should be able to add signature","I could add signature",false);
    }catch(Exception ex){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

    }
    }


    @And("^I select \"([^\"]*)\" from the dropdown$")
    public void i_select_something_from_the_dropdown(String status) throws Throwable {
        try{
            Thread.sleep(5000);
            action.click(scheduledBungiiPage.Link_ChangeDeliveryStatus());
            Thread.sleep(4000);
            action.click(scheduledBungiiPage.DropDown_DeliveryStatus());
            switch (status){
                case "Admin Canceled":
                case "Partner Canceled":
                case "Driver Canceled":
                case "Customer Canceled":
                    action.click(scheduledBungiiPage.Text_DeliveryStatus(status));
                    break;
            }
            log("I should be able to click on "+status+" link", "I could click on "+status+" link",false);
        }catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I select \"([^\"]*)\" as the reason from the reason dropdown$")
    public void i_select_something_as_the_reason_from_the_reason_dropdown(String changestatusreason) throws Throwable {
        try {
            action.click(scheduledBungiiPage.DropDown_DeliveryStatusReason());
            switch (changestatusreason) {
                case "Driver initiated":
                case "Customer initiated - other reason":
                case "Outside of delivery scope":
                case "Solo: Driver not found":
                case "Other":
                    action.click(scheduledBungiiPage.Text_DeliveryStatusReason(changestatusreason));
                    break;
            }
            log("I should be able to click on " + changestatusreason + " option", "I could click on " + changestatusreason + " option", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the \"([^\"]*)\" header \"([^\"]*)\"$")
    public void i_should_see_the_something_header_something(String expectedText, String signature) throws Throwable {
        try{
        Thread.sleep(1000);
        switch (signature){
            case "Displayed":
                boolean signatureDisplayed = updateStatusPage.Header_CustomerSignature().isDisplayed();
                String customerSign = action.getText(updateStatusPage.Header_CustomerSignature());
                boolean CustomerDetails =updateStatusPage.Text_Details().isDisplayed();
                testStepAssert.isTrue(signatureDisplayed,customerSign +" should be displayed",customerSign +" is  displayed",customerSign +" is not displayed");
                testStepAssert.isTrue(CustomerDetails,"Customer details should be displayed","Customer details is displayed","Customer details is not displayed");
                testStepAssert.isEquals(customerSign,expectedText,"Header should be "+expectedText,"Header should be "+expectedText,"Header should be "+expectedText);
                break;
            case "Not Displayed":
                testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Header_CustomerSignature(true)),expectedText+ " should not  be displayed",expectedText+ " is not displayed",expectedText+ " is displayed");
                break;
        }
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^The customer signature field is \"([^\"]*)\"$")
    public void the_customer_signature_field_is_something(String expectedText) throws Throwable {
        try{
        switch (expectedText) {
            case "Required N/A":
                Thread.sleep(2000);
                String customerSignatureFieldText =action.getText(scheduledBungiiPage.Label_CustomerSignatureNA()).replace("\n", " ");
                testStepAssert.isEquals(customerSignatureFieldText,expectedText,"Signature filed should have the text " +expectedText,"Signature filed has the text " +customerSignatureFieldText,"Signature filed doesnt have the text " +expectedText);
                break;
            case "Signature Present":
                Thread.sleep(2000);
                String ExpectedText ="Customer Signature";
                boolean isSignaturePresent = scheduledBungiiPage.Image_CustomerSignature().isDisplayed();
                String customerSignaturePresent = (scheduledBungiiPage.Image_CustomerSignature().getAttribute("title"));
                testStepAssert.isTrue(isSignaturePresent, "Customer signature should be displayed","Customer signature is displayed","Customer signature is not  displayed");
                testStepAssert.isEquals(customerSignaturePresent,ExpectedText,"Customer signature field should have signature present","Customer signature field is having  signature present","Customer signature field is not having signature present");
                break;
            case "Not required N/A":
                Thread.sleep(2000);
                String customerSignatureFieldTextNA =action.getText(scheduledBungiiPage.Label_CustomerSignatureNA()).replace("\n", " ");
                testStepAssert.isEquals(customerSignatureFieldTextNA,expectedText,"Signature filed should have the text " +expectedText,"Signature filed has the text " +customerSignatureFieldTextNA,"Signature filed doesnt have the text " +expectedText);
                break;
        }
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The \"([^\"]*)\" message should be displayed for live delivery$")
    public void the_something_message_should_be_displayed_for_live_delivery(String message) throws Throwable {
        try{
        if(message.equalsIgnoreCase("Pick up has been successfully updated.")){
            testStepAssert.isElementTextEquals(updateStatusPage.Label_DeliverySuccessMessageLive(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
        }
        else {
            testStepAssert.isElementTextEquals(updateStatusPage.Label_CancelSuccessMessageLive(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
        }
        }catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^Confirmation message on edit live delivery pop up should be displayed$")
    public void confirmation_message_on_edit_live_delivery_pop_up_should_be_displayed() throws Throwable  {
        try
        {
            String expectedMessage = PropertyUtility.getMessage("admin.complete.confirm");
            String actualMessage = action.getText(updateStatusPage.Message_AdminCompleteConfirm());
            testStepAssert.isEquals(actualMessage, expectedMessage, expectedMessage + "should be displayed", expectedMessage + "is displayed", actualMessage + "is displayed");
        }

        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I enter delivery completion date and time as per geofence$")
    public void i_enter_delivery_completion_date_and_time_as_per_geofence() throws Throwable {
        try{
            String strDate="";
            String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
            String geofenceLabel = GeneralUtility.getTimeZoneBasedOnGeofenceId();
            Calendar calendar = Calendar.getInstance();
            DateFormat formatter = new SimpleDateFormat("MMddYYYY-hh:mm-a");
            formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));


            strDate = formatter.format(calendar.getTime());
            String[] dateTime = strDate.split("-");
            String date = dateTime[0];
            String time = dateTime[1];
            String meridian = dateTime[2];

            action.clearSendKeys(updateStatusPage.Textbox_PickupEndDate(),date);
            action.clearSendKeys(updateStatusPage.Textbox_PickupEndTime(),time);
            action.selectElementByText(updateStatusPage.Dropdown_ddlpickupEndTime(),meridian);
            log("Correct date= "+date+" and time= "+time+meridian+" should be enter for the "+geofence+" geofence.","Correct date= "+date+" and time= "+time+meridian+" is enter for the "+geofence+" geofence.",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on \"([^\"]*)\" radiobutton$")
    public void i_click_on_something_radiobutton(String radiobutton) throws Throwable {
        try{
            switch (radiobutton) {
                case "Edit Delivery Status":
                    Thread.sleep(3000);
                    action.click(updateStatusPage.RadioButton_EditDeliveryStatus());
                    Thread.sleep(1000);
                    break;
                case "Delivery Canceled":
                    Thread.sleep(3000);
                    action.click(updateStatusPage.RadioButton_DeliveryCanceled());
                    Thread.sleep(1000);
                    break;
                case "Delivery Completed":
                    Thread.sleep(3000);
                    action.click(updateStatusPage.RadioButton_DeliveryCompleted());
                    Thread.sleep(1000);
                    break;
                default:
                    break;
            }
            log("I click "+ radiobutton,
                    "I have clicked on "+ radiobutton, false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I click on \"([^\"]*)\" link beside live delivery$")
    public void i_click_on_something_link_beside_live_delivery(String link) throws Throwable {
        try{
            Thread.sleep(4000);
            action.click(scheduledTripsPage.Link_LiveDeliveryDetails());
//            action.click(scheduledBungiiPage.Icon_Dropdown());
            action.click(scheduledBungiiPage.Option_Edit());
            log(" I click on Edit link besides the live delivery",
                    "I have clicked on Edit link besides the live delivery", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I select the first driver$")
    public void i_select_the_first_driver() throws Throwable {
        try{

            action.click(scheduledBungiiPage.Checkbox_driver());
            log("I select the driver",
                    "I selected the driver", true);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I click on the \"([^\"]*)\" button from the dropdown$")
    public void i_click_on_the_something_button_from_the_dropdown(String buttonText) throws Throwable {
        try {
            Thread.sleep(5000);
            action.click(scheduledBungiiPage.Link_DeliveryDetails());
            switch (buttonText) {
                case "Edit":
                    action.click(scheduledBungiiPage.Button_Edit());
                    break;
            }
            log("I should be able to click on the " + buttonText + " button from the dropdown", "I could  click on the  " + buttonText + "  button from the dropdown", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I search the delivery using \"([^\"]*)\"$")
    public void i_search_the_delivery_using_something(String searchTerm) throws Throwable {
        try {
            switch (searchTerm){
                case "Pickup Reference":
                    Thread.sleep(1000);
                    action.clearSendKeys(scheduledBungiiPage.TextBox_Search(), (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST") + Keys.ENTER);
                    break;
                case "Customer name":
                    action.clearSendKeys(scheduledBungiiPage.TextBox_Search(),(String) cucumberContextManager.getScenarioContext("CUSTOMER") + Keys.ENTER);
                    break;
            }

            log("I should be able to search the delivery using "+searchTerm,"I could search the delivery using "+searchTerm,false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I should see the customer signature row \"([^\"]*)\" in admin portal \"([^\"]*)\" page$")
    public void i_should_see_the_customer_signature_row_something_in_admin_portal_something_page(String CustomerSignature, String Page) throws Throwable {
        try{
            switch(Page) {
                case "All Deliveries details":
                    switch (CustomerSignature) {
                        case "Present":
                            boolean isCustomerSignatureDisplayed = updateStatusPage.Label_CustomerSignature().isDisplayed();
                            testStepAssert.isTrue(isCustomerSignatureDisplayed, "Customer Signature row should be present", "Customer Signature row is  present", "Customer Signature row is not present");
                            break;
                        case "Not Present":
                            testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Label_CustomerSignature(true)), "Customer Signature row should not be present", "Customer Signature row is not present", "Customer Signature row is present");
                            break;
                    }
                    break;
                case "Live Deliveries details":
                    switch (CustomerSignature) {
                        case "Present":
                            boolean isCustomerSignatureDisplayed = updateStatusPage.Label_CustomerSignature().isDisplayed();
                            testStepAssert.isTrue(isCustomerSignatureDisplayed, "Customer Signature row should be present", "Customer Signature row is  present", "Customer Signature row is not present");
                            break;
                        case "Not Present":
                            testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Label_CustomerSignature(true)), "Customer Signature row should not be present", "Customer Signature row is not present", "Customer Signature row is present");
                            break;
                    }
                    break;
                case "Scheduled Delivery details":
                    switch (CustomerSignature) {
                        case "Present":
                            boolean isCustomerSignatureDisplayed = updateStatusPage.Label_CustomerSignature().isDisplayed();
                            testStepAssert.isTrue(isCustomerSignatureDisplayed, "Customer Signature row should be present", "Customer Signature row is  present", "Customer Signature row is not present");
                            break;
                        case "Not Present":
                            testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Label_CustomerSignature(true)), "Customer Signature row should not be present", "Customer Signature row is not present", "Customer Signature row is present");
                            break;
                    }
                    break;
            }
        }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I calculate the \"([^\"]*)\" time after \"([^\"]*)\"$")
    public void
    i_calculate_the_something_time_after_something(String timeType, String changeType) throws Throwable {
        try{
            switch (changeType){
                case "changed pickup":
                    switch (timeType){
                        case "telet":
//                      Formula = (TELET + drive time from drop off A to pickup B) - 15 minutes, + 30 minutes
                            String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"); //phoneNumber="9403960189"; c/// Stacked trip will be 2 customer you need of first trip
                            String custRef = DbUtility.getCustomerRefference(phoneNumber);
                            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
                            String teletTimeInDb = DbUtility.getTELETfromDb(custRef);
                            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date time2 = formatter.parse(teletTimeInDb);
                            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(geofenceLabel));
                            calendar.setTime(time2);
                            String teletInLocalTime = String.valueOf(calendar.getTime());
                            cucumberContextManager.setScenarioContext("NEW_TELET",teletInLocalTime);
                            String customerPhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");

                            String[] pickupLoc= DbUtility.getPickupAndDropLocation(customerPhoneNumber);
                            String[] pickup2Locations = DbUtility.getPickupAndDropLocation(PropertyUtility.getDataProperties("valid.customer.ondemand.phone"));
                            String[] dropLoc = new String[2];
                            dropLoc[0] = pickupLoc[2];
                            dropLoc[1] = pickupLoc[3];
                            String[] newPickupLocations = new String[2];
                            newPickupLocations[0] = pickup2Locations[0];
                            newPickupLocations[1] = pickup2Locations[1];

                            long[] timeToCoverDistance2 = new GoogleMaps().getDurationInTraffic(dropLoc, newPickupLocations);
                            logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance2[0]+" and "+timeToCoverDistance2[1]);

                            int times = (int) (timeToCoverDistance2[0]/60);
                            calendar.setTime(time2);
                            calendar.add(Calendar.MINUTE, times);
                            calendar.add(Calendar.MINUTE, -15);
                            String lowerRangeInLocalTime = String.valueOf(calendar.getTime());
                            cucumberContextManager.setScenarioContext("PAT_LOWER_RANGE",lowerRangeInLocalTime);
                            calendar.setTime(time2);
                            calendar.add(Calendar.MINUTE, 30);
                            String upperRangeInLocalTime = String.valueOf(calendar.getTime());
                            cucumberContextManager.setScenarioContext("PAT_UPPER_RANGE",upperRangeInLocalTime);
                            break;

                    }
                    break;
                case "changed service level":
                    switch (timeType){
                        case "telet":
//           Telet=Driver Arrival time + ((Estimated Duration from Pickup point to drop off point + Time at pickup and Drop off) * 1.5)
                            String pickupRefTrip1 =(String) cucumberContextManager.getScenarioContext("Pickup_Request");
                            String pickUpID = DbUtility.getPickupIdWithRef(pickupRefTrip1);
                            String adminArrival= DbUtility.getDriverArrivalTime(pickUpID);
                            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Calendar sourceCalendar = Calendar.getInstance();
                            sourceCalendar.setTime(formatter.parse(adminArrival));
                            //conversion from utc to edt
                            sourceCalendar.add(Calendar.MINUTE,-300);
                            String[] locationsTripOne = DbUtility.getPickupAndDropLocationWithID(pickUpID);
                            String[] dropLocation = new String[2];
                            dropLocation[0] = locationsTripOne[2];
                            dropLocation[1] = locationsTripOne[3];
                            String[] pickupLocations = new String[2];
                            pickupLocations[0] = locationsTripOne[0];
                            pickupLocations[1] = locationsTripOne[1];
                            long[] timeToCoverDistance = new GoogleMaps().getDurationInTraffic(pickupLocations, dropLocation);
                            logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance[0]+" and "+timeToCoverDistance[1]);

                            String timeAtPick=DbUtility.getTimeAtPickUpAndDrop("default_pickup_time",PropertyUtility.getDataProperties("subdomain.name.biglots"));
                            String timeAtDrop=DbUtility.getTimeAtPickUpAndDrop("default_dropoff_time",PropertyUtility.getDataProperties("subdomain.name.biglots"));
                            int pickUpInMins = (Integer.parseInt(timeAtPick)/1000)/60;
                            int dropOffInMins = (Integer.parseInt(timeAtDrop)/1000)/60;
                            int mins = (int) ((((timeToCoverDistance[0]/60)+dropOffInMins+pickUpInMins)*1.5));
                            sourceCalendar.add(Calendar.MINUTE,mins);
                            String newTelet= String.valueOf(sourceCalendar.getTime());
                            cucumberContextManager.setScenarioContext("DRIVER_TELET",newTelet.substring(11,16));
//                          Formula = (TELET + drive time from drop off A to pickup B) - 10 minutes, + 20 minutes
                            String pickupRefTrip2 = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                            String pickUpIDTrip2 = DbUtility.getPickupIdWithRef(pickupRefTrip2);
                            String[] locationsTripTwo = DbUtility.getPickupAndDropLocationWithID(pickUpIDTrip2);
                            String[] pickupLocations2 = new String[2];
                            pickupLocations2[0] = locationsTripTwo[0];
                            pickupLocations2[1] = locationsTripTwo[1];
                            long[] timeToCoverDistance2 = new GoogleMaps().getDurationInTraffic(dropLocation, pickupLocations2);
                            logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance2[0]+" and "+timeToCoverDistance2[1]);
                            int lowerMins= (int) (timeToCoverDistance2[0]/60);
                            sourceCalendar.add(Calendar.MINUTE,(lowerMins-10));
                            String newLowerPat= String.valueOf(sourceCalendar.getTime());
                            cucumberContextManager.setScenarioContext("PAT_LOWER_RANGE",newLowerPat.substring(11,16));
                            sourceCalendar.add(Calendar.MINUTE,30);
                            String newUpperPat= String.valueOf(sourceCalendar.getTime());
                            cucumberContextManager.setScenarioContext("PAT_UPPER_RANGE",newUpperPat.substring(11,16));
                            break;
                    }
                    break;
            }
            log("I should be able to calculate the telet","I am able to calculate the telet",false);
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^Correct details should do be displayed on (.+) screen for Stack screen$")
    public void correct_details_should_do_be_displayed_on_bungii_accepted_screen_for_stack_screen(String key)  {
        try{
            switch (key.trim()){
                case "BUNGII ACCEPTED with recalculation":
                    String labelOne="";
                    String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

                    if (geofence.equalsIgnoreCase("goa") || geofence.equalsIgnoreCase(""))
                        labelOne =  PropertyUtility.getDataProperties("time.label");
                    else
                        labelOne =  utility.getTimeZoneBasedOnGeofence();
                    String expectedPAT=(String)cucumberContextManager.getScenarioContext("PAT_LOWER_RANGE")+" - "+(String)cucumberContextManager.getScenarioContext("PAT_UPPER_RANGE")+" "+labelOne;
                    expectedPAT=expectedPAT.replace("am", "AM").replace("pm","PM");
                    String actualPAT = action.getText(bungiiAcceptedPage.Textlabel_ProjectedTimeValue());
                    testStepVerify.isTrue(expectedPAT.contains(actualPAT),"Projected arrival Time : "+ actualPAT +" is displayed","Projected arrival Time : "+ actualPAT +" is displayed instead of "+ expectedPAT);
                    break;
            }}
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^The \"([^\"]*)\" \"([^\"]*)\" should be displayed$")
    public void the_something_something_should_be_displayed(String element, String strArg2) throws Throwable {
        try{
            String expectedText = "";
            Thread.sleep(5000);
            switch (element){
                case "Delivery Instructions":
                    testStepAssert.isTrue(action.isElementPresent(inProgressPages.Button_DeliveryInstructions()),"Delivery Instructions Icon should be displayed","Delivery Instructions Icon is displayed","Delivery Instructions Icon is not displayed");
                    break;
                case "Item Details":
                    testStepAssert.isTrue(action.isElementPresent(Page_DriverBungiiProgress.Button_Customer_ViewItem()),"Item Details Icon should be displayed","Item Details Icon is displayed","Item Details Icon is not displayed");
                    break;
                case "Bungii Support":
                    testStepAssert.isTrue(action.isElementPresent(Page_DriverBungiiProgress.Button_Customer_CallSupport()),"Bungii Support Icon should be displayed","Bungii Support Icon is displayed","Bungii Support Icon is not displayed");
                    break;
                case "More Options":
                    testStepAssert.isTrue(action.isElementPresent(inProgressBungiiPages.Button_MoreOptions()),"More Options Icon should be displayed","More Options Icon is displayed","More Options Icon is not displayed");
                    break;
                case "Call":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Icon_Call()),"Call Icon should be displayed","Call Icon is displayed","Call Icon is not displayed");
                    break;
                case "Text":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Icon_Text()),"Phone Icon should be displayed","Phone Icon is displayed","Phone Icon is not displayed");
                    break;
                case "Pickup":
                    action.scrollToBottom();
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Icon_Pickup()),"Pickup Icon should be displayed","Pickup Icon is displayed","Pickup Icon is not displayed");
                    break;
                case "Dropoff":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Icon_DropOff()),"Dropoff Icon should be displayed","Dropoff Icon is displayed","Dropoff Icon is not displayed");
                    break;
                case "Contact Duo Teammate":
                    By Text_ContactDuo = MobileBy.id("com.bungii.driver:id/tv_contact_duo_label");
                    By Text_TeamMate = MobileBy.id("com.bungii.driver:id/tv_teammate_label");
                    testStepAssert.isTrue(action.waitForExpectedElementToBeDisplayed(Text_ContactDuo),"Contact Duo text should be displayed","Contact Duo text is displayed","Contact Duo text is not displayed");
                    Thread.sleep(10000);
                    testStepAssert.isTrue(action.waitForExpectedElementToBeDisplayed(Text_TeamMate),"Teammate text should be displayed","Teammate text is displayed","Teammate text is not displayed");
                    break;
                case "Bungii: The Ultimate Side Hustle":
                    expectedText = PropertyUtility.getMessage("driver.navigation.bungii.the.ultimate.side.hustle");
                    testStepAssert.isTrue(action.isElementPresent(driverLogInPage.Label_TheUltimateSideHustle()),expectedText+" should be displayed",expectedText+" is displayed",expectedText+" is not displayed");
                    break;
                case "Arrival time at pickup":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Label_ArivalTimeAtPickup()),"Arrival Time at pickup label should be displayed","Arrival Time at pickup label is displayed","Arrival Time at pickup label is not displayed");
                    String expectedTextAtPickup = action.getText(updateStatusPage.Label_ArivalTimeAtPickup());
                    testStepAssert.isEquals(expectedTextAtPickup, element, element + " Text should be displayed", element + " Text is displayed", expectedTextAtPickup + "is displayed");
                    break;
                case "Expected time at drop-off":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Label_ExpectedTimeAtDropOff()),"Expected time at drop-off label should be displayed","Expected time at drop-off label is displayed","Expected time at drop-off label is not displayed");
                    String expectedTextAtDropOff = action.getText(updateStatusPage.Label_ExpectedTimeAtDropOff());
                    testStepAssert.isEquals(expectedTextAtDropOff, element, element + " Text should be displayed", element + " Text is displayed", expectedTextAtDropOff + "is displayed");
                    break;
                case "PICKUP(Arrival time)":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Label_Pickup()),"Pickup label should be displayed","Pickup label is displayed","Pickup label is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Label_ArrivalTime()),"Arrival time label should be displayed","Arrival time label is displayed","Arrival time label is not displayed");

                    String pickupText = action.getText(updateStatusPage.Label_Pickup());
                    String arrivalTimeText = action.getText(updateStatusPage.Label_ArrivalTime());
                    String completeText =pickupText +arrivalTimeText;
                    testStepVerify.isEquals(completeText, element, element + " Text should be displayed", element + " Text is displayed", completeText + "is displayed");
                    String arrivalTimeOnUIValue = action.getText(updateStatusPage.Text_ArrivalTimeValue());
                    String properArrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");
                    testStepAssert.isEquals(arrivalTimeOnUIValue, properArrivalTime,"The arrival time should be "+properArrivalTime,
                            "The arrival time is "+properArrivalTime,"The arrival is not "+properArrivalTime+" ,The time is "+properArrivalTime);
                    break;
                case "DROP-OFF(Expected time)":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Label_Pickup()),"Dropoff label should be displayed","Dropoff label is displayed","Dropoff label is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Label_Pickup()),"Expected time label should be displayed","Expected time label is displayed","Expected time label is not displayed");

                    String dropOffText = action.getText(updateStatusPage.Label_Pickup());
                    String expectedTimeText = action.getText(updateStatusPage.Label_ArrivalTime());
                    String entireText =dropOffText +expectedTimeText;
                    testStepAssert.isEquals(entireText, element, element + " Text should be displayed", element + " Text is displayed", entireText + "is displayed");
                    String dropOffRangeOnUIValue = action.getText(updateStatusPage.Text_ArrivalTimeValue());
                    if(dropOffRangeOnUIValue.contains("PM") && dropOffRangeOnUIValue.contains("AM")){

                        String onlyTimeRange = dropOffRangeOnUIValue.replace("PM","").replace("AM","").replace(" ","");;
                        cucumberContextManager.setScenarioContext("DropOffUiTime",onlyTimeRange);
                    }
                    else {
                        String onlyTimeRange = dropOffRangeOnUIValue.substring(0, dropOffRangeOnUIValue.length()-3).replace(" ","");
                        cucumberContextManager.setScenarioContext("DropOffUiTime",onlyTimeRange);
                    }
                    String expectedDropOffRangeFromUI =(String) cucumberContextManager.getScenarioContext("DropOffUiTime");
                    String dropOffRangeBasedOnCalculation = (String) cucumberContextManager.getScenarioContext("DropOffRangeCalculated");
                    testStepAssert.isEquals(expectedDropOffRangeFromUI, dropOffRangeBasedOnCalculation,"The arrival time should be "+dropOffRangeBasedOnCalculation,
                            "The arrival time is "+dropOffRangeBasedOnCalculation,"The arrival is not "+expectedDropOffRangeFromUI+" ,The time is "+dropOffRangeBasedOnCalculation);
                    break;
                case "Barcode":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Image_Barcode()),"Barcode Image should be displayed","Barcode Image is displayed","Barcode Image is not displayed");
                    break;
                case "Scan Item barcode":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Button_ScanItemBarCode()),"Scan item barcode should be displayed","Scan item barcode is displayed","Scan item barcode is not displayed");
                    break;
                case "BARCODE SCANNER":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Header_BarcodeScanner()),"Header should be displayed","Header is displayed","Header is not displayed");
                    String textFromUi = action.getText(updateStatusPage.Header_BarcodeScanner());
                    testStepAssert.isEquals(textFromUi,element,element +" Text should be displayed ",
                            element +" Text is displayed ",
                            element +" Text is not displayed ");
                    break;
                case "Scan barcode":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Text_ScanBarCode()),"Scan barcode text should be displayed","Scan barcode text is displayed","Scan barcode text is not displayed");
                    expectedText = action.getText(updateStatusPage.Text_ScanBarCode());
                    testStepAssert.isEquals(expectedText,element,element +" Text should be displayed ",
                            element +" Text is displayed ",
                            element +" Text is not displayed ");
                    break;
                case "Skip":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Button_SkipBarCode()),"Skip barcode button should be displayed","Skip barcode button is displayed","Skip barcode button is not displayed");
                    break;
                case "Scan the item(s) barcode before loading & after unloading.":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Text_BarcodeInstructions()),"Notification should be displayed","Notification is displayed","Notification is not displayed");
                    String arrivalStateNotificationText=action.getText(updateStatusPage.Text_BarcodeInstructions()).replace("\n","");
                    testStepAssert.isEquals(arrivalStateNotificationText.replace("\n",""),element,element +" Text should be displayed ",
                            element +" Text is displayed ",
                            element +" Text is not displayed ");
                    break;
                case "Hold steady and center the barcode to scan.You need to scan any one item to proceed.":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Text_BarCodeScanningInstructions()),"Skip barcode button should be displayed","Skip barcode button is displayed","Skip barcode button is not displayed");
                    String arrivalStateInstructions=action.getText(updateStatusPage.Text_BarCodeScanningInstructions());
                    testStepAssert.isEquals(arrivalStateInstructions.replace("\n",""),element,element +" Text should be displayed ",
                            element +" Text is displayed ",
                            element +" Text is not displayed ");
                    break;
                case "Please take photos and scan item(s) barcode before loading, just ‘slide to load items’ and follow the prompts.":
                case "Please take photos and scan item(s) barcode after unloading, just ‘slide to complete Bungii’ and follow the prompts":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Text_NotificationTextOnArrivalAndUnloadingItemsForBarCode()),"Notification should be displayed","Notification is displayed","Notification is not displayed");
                    String notificationText = action.getText(updateStatusPage.Text_NotificationTextOnArrivalAndUnloadingItemsForBarCode());
                    testStepAssert.isEquals(notificationText,element,element +" Text should be displayed ",
                            element +" Text is displayed ",
                            element +" Text is not displayed ");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);

        }
    }

    @Then("^The \"([^\"]*)\" \"([^\"]*)\" should not be displayed$")
    public void the_something_something_should_not_be_displayed(String element, String strArg2) throws Throwable {
        try{
            switch (element){
                case "Contact Duo Teammate":
                    Thread.sleep(3000);
                    testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Text_ContactDuo(true)),"Contact Duo text should not be displayed","Contact Duo text is not displayed","Contact Duo text is displayed");
                    testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Text_TeamMate(true)),"Teammate text should not be displayed","Teammate text is not displayed","Teammate text is displayed");
                    break;
            }
        }    catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I should see the dropoff contact name under the customer name field$")
    public void i_should_see_the_dropoff_contact_name_under_the_customer_name_field() throws Throwable {
        try{
        String dropoffContactName = cucumberContextManager.getScenarioContext("DROPOFFCONTACTNAME").toString();
        String customerName = action.getText(updateStatusPage.Text_CustomerNameOnDriverApp());
        testStepAssert.isEquals(customerName,dropoffContactName,dropoffContactName+ "Should be displayed",customerName+ "is displayed",dropoffContactName+ "is not displayed");
    }    catch(Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I search the delivery of Customer$")
    public void i_search_the_delivery_of_customer() throws Throwable {
        try {
            String pickupRequestOld = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
            action.clearSendKeys(scheduledBungiiPage.TextBox_Search(), pickupRequestOld + Keys.ENTER);
            Thread.sleep(10000);
            log("I search the delivery of Customer", "I searched the delivery of Customer", false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I cancel the \"([^\"]*)\" delivery$")
    public void i_cancel_the_something_delivery(String deliveryType, DataTable cancelledDelivery) throws Throwable {
        try{
        Map<String, String> data = cancelledDelivery.transpose().asMap(String.class, String.class);
        String cancellationCharge = data.get("Charge").trim();
        String cancellationComment = data.get("Comments").trim();
        String cancellationReason = data.get("Reason").trim();

        action.clearSendKeys(scheduledBungiiPage.TextBox_Search(), (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST") + Keys.ENTER);
        logger.detail("I could search the delivery using pickup reference");

        Thread.sleep(5000);
        action.click(scheduledBungiiPage.Link_DeliveryDetails());
        Thread.sleep(3000);
        action.click(scheduledBungiiPage.Button_Edit());
        logger.detail( "I could  click on the kebab button from the dropdown and click on the edit button");

        Thread.sleep(5000);
        action.click(scheduledTripsPage.RadioBox_Cancel());
        logger.detail("I could  click on the cancel bungii radio button");

        Thread.sleep(2000);
        action.clearSendKeys(scheduledTripsPage.Textbox_CancellationFee(), cancellationCharge);
        action.clearSendKeys(scheduledTripsPage.Textbox_CancellationComment(), cancellationComment);
        action.click(scheduledTripsPage.Dropdown_CancellationReason());
        logger.detail("I have entered cancellation fee amount and comments");

        action.selectElementByText(scheduledTripsPage.Dropdown_CancellationReason(), cancellationReason);
        logger.detail("I could  select reason as " + cancellationReason + " from the dropdown");

        Thread.sleep(2000);
        action.click(scheduledTripsPage.Button_Submit());
        logger.detail("I could click on the submit button");
        log("I should be able to cancel the "+deliveryType+" delivery","I could cancel the "+deliveryType+" delivery",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


}
