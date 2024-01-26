package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.EstimatePage;
import com.bungii.ios.pages.driver.BungiiDetailsPage;
import com.bungii.ios.stepdefinitions.customer.SignupSteps;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    private BungiiDetailsPage bungiiDetailsPage;
    EstimatePage estimatePage = new EstimatePage();

    public BungiiDetailsSteps(BungiiDetailsPage bungiiDetailsPage) {
        this.bungiiDetailsPage = bungiiDetailsPage;

    }
    @And("^I start selected Bungii for \"([^\"]*)\"$")
    public void i_start_selected_bungii_for_something(String type) throws Throwable {
        try{
            switch (type){
                case "floor and decor":
                    Thread.sleep(3000);
                    if (action.isAlertPresent())
                        SetupManager.getDriver().switchTo().alert().accept();

                    action.swipeUP();
                    action.click(bungiiDetailsPage.Button_StartBungii());
                    Thread.sleep(2000);
                    if(action.isElementPresent(bungiiDetailsPage.Text_General_Instruction(true))) {
                        action.click(bungiiDetailsPage.Button_General_Instruction_Got_It());
                    }
                    log("I start selected Bungii ", "I started selected Bungii", true);
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^Driver adds photos to the Bungii$")
    public void driver_adds_photos_to_the_bungii() throws Throwable {
        try{
            action.click(bungiiDetailsPage.Tab_AddPhoto());
            addBungiiPickUpImage("3 images");
            Thread.sleep(1000);

            log("I should be able to add photos for delivery",
                    "I am able to add photos for delivery",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    public void addBungiiPickUpImage(String option) throws InterruptedException{
        if (option.equalsIgnoreCase("3 images")) {
            addImage(3);
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

        if(action.isAlertPresent()){
            try {
                Thread.sleep(3000);
                action.clickAlertButton("OK");
                Thread.sleep(3000);
            }
            catch (Exception ex){

            }
        }
        for (int i = 1; i <= numberOfImage; i++) {

            //capture image instead of uploading existing image. this saves some time
            if (action.isElementPresent(estimatePage.Button_PhotoCapture(true))) {
                //do nothing, directly move to steps after IF conditions
            } else if (action.isElementPresent(estimatePage.Button_OK(true)))
                action.click(estimatePage.Button_OK());
            Thread.sleep(3000);
            action.click(estimatePage.Button_PhotoCapture());
            Thread.sleep(3000);
            action.click(estimatePage.Button_UsePhoto());

            if(i<3){
                action.click(bungiiDetailsPage.Tab_AddPhoto());
            }
            else{
                action.click(bungiiDetailsPage.Button_SavePhotos());
            }

        }
    }
    @Then("^Bungii driver should see \"([^\"]*)\"$")
    public void bungiiDriverShouldSee(String arg0) throws Throwable {
        try {
            switch (arg0) {
                case "Pickup instructions":
                    testStepVerify.isElementDisplayed(bungiiDetailsPage.Text_PickupInstructions(),"Pickup instructions should be shown","Pickup instructions is shown","Pickup instructions is not shown");
                    action.click(bungiiDetailsPage.Button_General_Instruction_Got_It());
                    break;
                case "Photo Verification":
                    testStepVerify.isElementDisplayed(bungiiDetailsPage.Text_PhotoVerification(),"Photo Verification should be shown","Photo Verification is shown","Photo Verification is not shown");
                    break;
                case "Drop-off instructions":
                    testStepVerify.isElementDisplayed(bungiiDetailsPage.Text_DropOffInstructions(),"Drop-off instructions should be shown","Drop-off instructions is shown","Drop-off instructions is not shown");
                    action.click(bungiiDetailsPage.Button_General_Instruction_Got_It());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^Start button should not be shown$")
    public void start_button_should_not_be_shown() throws Throwable {
        try {
            Thread.sleep(2000);
            testStepVerify.isElementNotDisplayed(bungiiDetailsPage.Button_StartBungii(),"Start button shouldn't displayed","Start button is not displayed","Start button is display");

        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in Starting Bungii as Driver", true);
        }

    }

        @When("^I start selected Bungii$")
    public void i_start_selected_bungii() {
        try {
            if (action.isAlertPresent())
                SetupManager.getDriver().switchTo().alert().accept();
            action.swipeUP();
            action.click(bungiiDetailsPage.Button_StartBungii());
            Thread.sleep(2000);
            if(action.isElementPresent(bungiiDetailsPage.Text_General_Instruction(true))) {
                action.click(bungiiDetailsPage.Button_General_Instruction_Got_It());
            }
            log("I start selected Bungii ", "I started selected Bungii", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in Starting Bungii as Driver", true);
        }
    }

    @When("^I try to cancel selected Bungii$")
    public void i_cancel_selected_bungii() {
        try {
            if (action.isAlertPresent())
                SetupManager.getDriver().switchTo().alert().accept();

            action.swipeUP();
            action.click(bungiiDetailsPage.Button_CancelBungii());
            log("I try to cancel selected Bungii ", "I tried cancel selected Bungii", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I wait for Minimum duration for Bungii Start Time$")
    public void i_wait_for_minimum_duration_for_bungii_start_time() {
       /* try {
            String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
            //    bungiiTime="Aug 09, 12:45 AM CDT";
            int mininumWaitTime = Integer.parseInt(PropertyUtility.getProp("scheduled.min.start.time"));
            if (!bungiiTime.equalsIgnoreCase("NOW")) {
                String geofenceLabel = utility.getTimeZoneBasedOnGeofence().toUpperCase();

                DateFormat formatter = new SimpleDateFormat("MMM d, h:mm a");
                formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));
                Date bungiiDate = formatter.parse(bungiiTime);


                Date currentDate = new Date();
                bungiiDate.setYear(currentDate.getYear());//(Integer.parseInt(currentDate.getYear()));
                long duration = bungiiDate.getTime() - currentDate.getTime();

                long diffInMinutes;
                if (duration > 0) {
                    diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration) - mininumWaitTime;
                    //1 min extra buffer
                    diffInMinutes = diffInMinutes > 0 ? diffInMinutes + 1 : 0;

                } else {
                    diffInMinutes = 1;
                }
                action.hardWaitWithSwipeUp((int) diffInMinutes);
                log("I wait for " + diffInMinutes + " Minutes for Bungii Start Time ", "I waited for " + diffInMinutes + " (with Extra buffer)", true);
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
        */
       //Need to rework on this
    }

    @Then("^I wait for \"([^\"]*)\" mins$")
    public void i_wait_for_something_mins(String strArg1) throws Throwable {
        action.hardWaitWithSwipeUp(Integer.parseInt(strArg1));
    }

    @Then("^I wait for promoter job to run$")
    public void i_wait_for_promoter_mins() throws Throwable {

        String[] rtnArray = new String[2];
        int bufferTimeToStartTrip = 0;
        Calendar calendar = Calendar.getInstance();
        int mnts = calendar.get(Calendar.MINUTE);

        calendar.set(Calendar.MINUTE, mnts + 30);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 15;


        int wait = (15 - mod) + bufferTimeToStartTrip;
        logger.detail("I wait for " + wait + " Minutes for Bungii Start Time ");

        for (int i = 0; i <= wait; i++) {
            SetupManager.getDriver().navigate().refresh();
            Thread.sleep(60000);
        }
    }

    @When("^I wait for 1 hour for Bungii Schedule Time$")
    public void i_wait_for_one_hour_for_bungii_start_time() {
        try {
            String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
            int mininumWaitTime = 60;
            if (!bungiiTime.equalsIgnoreCase("NOW")) {
                String geofenceLabel = utility.getTimeZoneBasedOnGeofence().toUpperCase();

                DateFormat formatter = new SimpleDateFormat("MMM d, h:mm a");
                formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));
                Date bungiiDate = formatter.parse(bungiiTime);


                Date currentDate = new Date();
                bungiiDate.setYear(currentDate.getYear());//(Integer.parseInt(currentDate.getYear()));
                long duration = bungiiDate.getTime() - currentDate.getTime();

                long diffInMinutes;
                if (duration > 0) {
                    diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration) - mininumWaitTime;
                    //1 min extra buffer
                    diffInMinutes = diffInMinutes > 0 ? diffInMinutes + 1 : 0;

                } else {
                    diffInMinutes = 1;
                }
               /* action.hardWaitWithSwipeUp((int) diffInMinutes);*/ //Commented not to wait
                log("I wait for " + diffInMinutes + " Minutes for Bungii Start Time ", "I waited for " + diffInMinutes + " (with Extra buffer)", true);
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I wait for Minimum duration for \"([^\"]*)\" Bungii to be in Driver not accepted state$")
    public void i_wait_for_minimum_duration_for_something_bungii_to_be_in_driver_not_accepted_state(String strArg1) {
        try {
            long initialTime;
            if (strArg1.equalsIgnoreCase("current"))
                initialTime = (long) cucumberContextManager.getFeatureContextContext("BUNGII_INITIAL_SCH_TIME");
            else
                initialTime = (long) cucumberContextManager.getFeatureContextContext("BUNGII_INITIAL_SCH_TIME" + "_" + strArg1);
            long currentTime = System.currentTimeMillis() / 1000L;
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(currentTime - initialTime);
            if (diffInMinutes > 15) {
                //do nothing
            } else {
                // minimum wait of 30 mins
                action.hardWaitWithSwipeUp(15 - (int) diffInMinutes);

            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I wait for Minimum duration for current Bungii to be T-2 hours$")
    public void i_wait_for_minimum_duration_for_something_bungii_to_be_in_t_minus2() {
        try {

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

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^\"([^\"]*)\" should be displayed on Bungii Details screen$")
    public void something_should_be_displayed_on_bungii_request_screen(String option) throws Throwable {
        try {
            String expectedPickUpLocationLineOne = "", expectedPickUpLocationLineTwo = "", expectedDropLocationLineOne = "", expectedDropLocationLineTwo = "", expectedTripNoOfDriver = "";
            String pickUpLocation = "", dropUpLocation = "", estimate = "", truncValue = "";
            double flestimate, transactionFee, estimatedDriverCut;

            switch (option) {
                case "correct duo scheduled trip details":
                    expectedPickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));
                    expectedPickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));
                    expectedDropLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));
                    expectedDropLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2"));
                    expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO") ? "DUO" : "SOLO";
                    pickUpLocation = action.getNameAttribute(bungiiDetailsPage.TextBox_Pickup_LineOne());
                    dropUpLocation = action.getNameAttribute(bungiiDetailsPage.TextBox_Drop_LineOne());
                    testStepVerify.isTrue(pickUpLocation.replaceAll(",","").contains(expectedPickUpLocationLineOne.replaceAll(",","")) && pickUpLocation.replaceAll(",","").contains(expectedPickUpLocationLineTwo.replaceAll(",","")),
                            "Pick up address should be " + expectedPickUpLocationLineOne + expectedPickUpLocationLineTwo, "Pick up address is " + pickUpLocation,
                            "Expected pickup address is " + expectedPickUpLocationLineOne + expectedPickUpLocationLineTwo + ", but actual is" + pickUpLocation);
                    testStepVerify.isTrue(dropUpLocation.replace(",","").contains(expectedDropLocationLineOne.replaceAll(",","")) && dropUpLocation.replaceAll(",","").contains(expectedDropLocationLineTwo.replaceAll(",","")),

                            "Drop address should be " + expectedDropLocationLineOne + expectedDropLocationLineTwo, "Drop address is " + dropUpLocation,
                            "Expected Drop address is " + expectedDropLocationLineOne + expectedDropLocationLineTwo + ", but actual is" + dropUpLocation);
                    testStepVerify.isElementEnabled(bungiiDetailsPage.Text_EstimatedEarningTag(), "Earning tag should be displayed");

                    testStepVerify.isElementTextEquals(bungiiDetailsPage.Text_ValueDistance(), (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
                    flestimate = Double.valueOf(estimate.replace("~$", "").trim());
                    //transaction fee different for solo and duo
                    transactionFee = ((flestimate * 0.029 * 0.5) + 0.3) * 2;
                    estimatedDriverCut = (0.7 * flestimate) - transactionFee;
                    //divide by 2 for individual driver value
                    truncValue = new DecimalFormat("#.00").format(estimatedDriverCut / 2);
                    testStepVerify.isElementTextEquals(bungiiDetailsPage.Text_EstimatedEarningValue(), "~$" + truncValue);

                    Calendar calendar = Calendar.getInstance();
                    Date dateTime = calendar.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE");
                    sdf.setTimeZone(TimeZone.getTimeZone(new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofenceId()));
                    String dateFormatted = sdf.format(dateTime);

                    String time = action.getText(bungiiDetailsPage.Text_BungiiTime());
                    String expected = dateFormatted+", "+((String) cucumberContextManager.getScenarioContext("BUNGII_TIME")).replace(",", " |").substring(0,14);
                    logger.detail("Expected Time"+expected);
                    testStepVerify.isEquals(time, expected);
                    testStepVerify.isElementTextEquals(bungiiDetailsPage.Text_NavigationBar(), "BUNGII DETAILS");
//                    testStepVerify.isElementTextEquals(bungiiDetailsPage.Text_TypeTag(), "Type");
                    testStepVerify.isElementTextEquals(bungiiDetailsPage.Text_TypeValue(), "DUO LIFT");
                   // testStepVerify.isElementTextEquals(bungiiDetailsPage.Text_ValueTripTime(), ((String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE_TIME_LOAD_TIME").toString().replace("  "," ")));
                   // testStepAssert.isEquals("", "", "Verify Trip Time: ","NOTE: 1 min difference appears sometimes -> Need to recalculate and fix this case : "+ "~"+action.getText(bungiiDetailsPage.Text_ValueTripTime())+" and " +((String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE_TIME_LOAD_TIME").toString().replace("  "," ")),"NOTE: 1 min difference appears -> Need to recalculate and fix this case");

                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("I check if {string} button is {string}")
    public void iCheckIfButtonIs(String type, String act)throws Throwable {
        try{
            switch (type){
                case "photo verification":
                    testStepAssert.isFalse(action.isElementPresent(bungiiDetailsPage.Tab_AddPhoto(true)),
                            "Photo verification should not be displayed",
                            "Photo verification pin is not be displayed",
                            "Photo verification pin is displayed");
                    break;
                case "Save":
                    testStepAssert.isFalse(bungiiDetailsPage.Button_SavePhotos().isEnabled(),
                            "The save button should be disabled.",
                            "The save button is enabled.");
                    break;
            }
            log("I should be able to check if "+type+" is "+act,"I am able to check if "+type+" is "+act,false);
        }
        catch (Exception e){
            logger.error("Error performing step", e);
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
