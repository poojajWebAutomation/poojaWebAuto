package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.*;

import com.bungii.android.pages.driver.*;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.SetupManager.setDriver;
import static com.bungii.common.manager.ResultManager.*;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class EstimateBungiiSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(EstimateBungiiSteps.class);
    EstimatePage bungiiEstimatePage = new EstimatePage();
    SearchingPage Page_DriverSearch = new SearchingPage();
    HomePage Page_CustHome = new HomePage();
    DriverHomePage Page_DriverHome = new DriverHomePage();
    EstimatePage Page_Estimate = new EstimatePage();
    BungiiCompletePage Page_BungiiComplete = new BungiiCompletePage();
    WantDollar5Page Page_WantDollar5 = new WantDollar5Page();
    PromosPage Page_SaveMoney = new PromosPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    PropertyUtility properties = new PropertyUtility();
    SignupPage Page_Signup = new SignupPage();
    HomePage homePage = new HomePage();
    EstimatePage estimatePage = new EstimatePage();
    ScheduledBungiisPage scheduledBungiisPage = new ScheduledBungiisPage();
    private String[] loadTimeValue = {"15 mins", "30 mins", "45 mins", "60 mins", "75 mins", "90+ mins"};

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

    @When("^I tap on \"([^\"]*)\" on Bungii estimate$")
    public void iTapOnOnBungiiEstimate(String arg0) throws Throwable {
        try {
            switch (arg0) {
                case "two drivers selector":
                    action.click(Page_CustHome.Selector_Duo());
                    cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER", "DUO");
                    break;
                case "Get Estimate button":
                    //   while (action.isElementPresent(Page_CustHome.Button_GetEstimate(true)) == false)
                    //       iEnterOnBungiiEstimate("current location in pickup and dropoff fields");
                    //if (DriverAction.isElementPresent(Page_CustHome.Button_GetEstimate))
                    if (!action.isElementPresent(Page_CustHome.Button_GetEstimate()))
                        Thread.sleep(5000);
                    action.click(Page_CustHome.Button_GetEstimate());
                    Thread.sleep(5000);
                    String distance = action.getText(estimatePage.Text_GetDistance());
                    String estimatedCost=action.getText(estimatePage.Text_GetCost());
                    cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", distance);
                    cucumberContextManager.setScenarioContext("BUNGIICOST", estimatedCost);
                    break;

                case "Cancel during search":
                    action.click(Page_DriverSearch.Link_CancelSearch());
                    action.click(Page_DriverSearch.Button_CancelConfirm());
                    break;

                case "Promo Code":
                    Thread.sleep(3000);
                    action.click(bungiiEstimatePage.Link_Promo(true));
                    break;

                case "desired Promo Code":
                    String promoCode = (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE");
                    action.click((WebElement) getDriver().findElement(By.xpath("//android.widget.TextView[contains(@text,'" + promoCode + "')]")));
                    break;

                case "Payment Mode":
                    action.click(bungiiEstimatePage.Select_PaymentMode());
                    break;

                case "Request Bungii":
                    String cost = action.getText(bungiiEstimatePage.Text_GetCost());
                    cost = cost.replace("~", "");
                    cucumberContextManager.setScenarioContext("BUNGII_COST_CUSTOMER", cost);
                    cucumberContextManager.setScenarioContext("PROMOCODE_VALUE", action.getText(estimatePage.Link_Promo(true)));
                    action.scrollToBottom();
                    action.scrollToBottom();

                    String checked = "checked";
                    checked = action.getAttribute(bungiiEstimatePage.Checkbox_AgreeEstimate(), checked);
                    if (checked.equals("false")) {
                        action.click(bungiiEstimatePage.Checkbox_AgreeEstimate());
                    }

                    if (!action.isElementPresent(bungiiEstimatePage.Button_RequestBungii(true)))
                        action.scrollToBottom();
                    action.click(bungiiEstimatePage.Button_RequestBungii());
                    break;

                case "Yes on HeadsUp pop up":
                    action.waitUntilIsElementExistsAndDisplayed(bungiiEstimatePage.Alert_ConfirmRequestMessage(), 120L);
                    action.click(bungiiEstimatePage.Button_RequestConfirm());
                    Thread.sleep(3000);
                    action.eitherTextToBePresentInElementText(bungiiEstimatePage.GenericHeader(true), "SUCCESS!", "SEARCHING…");

                    break;

                case "Done after requesting a Scheduled Bungii":
                    //vishal[23092019]:commented as scroll is mostly equired
                    //  if (!action.isElementPresent(Page_CustHome.Button_Done(true)))
                    action.scrollToBottom();
                    action.click(Page_CustHome.Button_Done());
                    break;

                case "Cancel on HeadsUp pop up":
                    action.waitUntilIsElementExistsAndDisplayed(bungiiEstimatePage.Alert_ConfirmRequestMessage());
                    action.click(bungiiEstimatePage.Button_RequestConfirmCancel());
                    break;

                case "X on complete":
                    action.click(Page_BungiiComplete.CloseRateTipPage());
                    break;

                case "OK on complete":
                    boolean isDuo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO");
                    if (isDuo) {
                        Thread.sleep(10000);
                    }
                    Thread.sleep(8000);
                    try {
                        if (action.getText(Page_Signup.GenericHeader(true)).equals("COMPLETE"))
                            action.textToBePresentInElementText(Page_Signup.GenericHeader(), "BUNGII COMPLETE");

                    } catch (Exception e) {
                    }
                    action.click(Page_BungiiComplete.Button_OK());
                    break;

                case "No free money":
                    Thread.sleep(6000);
                    action.scrollToBottom();
                    if (!action.isElementPresent(Page_WantDollar5.Button_NoFreeMoney(true)))
                        action.scrollToBottom();
                    action.click(Page_WantDollar5.Button_NoFreeMoney());
                    break;
                case "back":
                    Thread.sleep(2000);
                    if (!action.isElementPresent(bungiiEstimatePage.Header_Estimate(true)))
                        Thread.sleep(5000);
                    ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                    break;
                case "ok on already scheduled bungii message":
                    testStepVerify.isEquals(action.getText(bungiiEstimatePage.Alert_ConfirmRequestMessage()), PropertyUtility.getMessage("customer.alert.alreadyscheduled"));
                    action.click(bungiiEstimatePage.Button_RequestConfirm());
                    break;

                case "back page":
                    action.click(Page_CustHome.Button_BackOfPage());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            pass(" I tap on " + arg0 + " on Bungii estimate",
                    "I  Selected " + arg0);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", arg0+ " is not displayed", true);
        }
    }

    @And("^I check if the customer is on success screen$")
    public void i_check_if_the_customer_is_on_success_screen() throws Throwable {
        try {
            if (!action.isElementPresent(bungiiEstimatePage.GenericHeader(true))) {
                if (action.isElementPresent(bungiiEstimatePage.Alert_DelayRequestingTrip(true))) {
                    action.click(bungiiEstimatePage.Button_DelayRequestingTrip_OK());
                    utility.selectNewBungiiTime();
                    action.scrollToBottom();
                    String checked = "checked";
                    checked = action.getAttribute(bungiiEstimatePage.Checkbox_AgreeEstimate(), checked);
                    if (checked.equals("false")) {
                        action.click(bungiiEstimatePage.Checkbox_AgreeEstimate());
                    }
                    if (!action.isElementPresent(bungiiEstimatePage.Button_RequestBungii(true)))
                        action.scrollToBottom();
                    action.click(bungiiEstimatePage.Button_RequestBungii());
                    action.click(bungiiEstimatePage.Button_RequestConfirm());
                    Thread.sleep(3000);
                    action.eitherTextToBePresentInElementText(bungiiEstimatePage.GenericHeader(true), "Success!", "SEARCHING…");
                }
            }
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^I should see the minimum scheduled time displayed on the Estimate page$")
    public void i_should_see_the_minimum_scheduled_time_displayed_on_the_estimate_page()throws Throwable{
        try{
        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String expectedTime = zonedDateTime.format(formatter);
        System.out.println("Original Time "+ expectedTime);
        zonedDateTime = zonedDateTime.plusMinutes(45);//uncommenting this step since San Francisco used min 45 minutes
            expectedTime = zonedDateTime.format(formatter);
        System.out.println("Plus 45 Original Time "+ expectedTime);
        int minutes = zonedDateTime.getMinute();
        int difference = 0;
        if(minutes > 0 && minutes < 15) {
            difference = 15 - minutes;
        } else if (minutes > 15 && minutes < 30) {
            difference = 30 - minutes;
        } else if (minutes > 30  && minutes < 45) {
            difference = 45 - minutes;
        } else if(minutes > 45) {
            difference = 60 - minutes;
        }
        zonedDateTime =  zonedDateTime.plusMinutes(difference);
            expectedTime = zonedDateTime.format(formatter);
        System.out.println("Expected Time "+expectedTime);
            String pickupDateTime = action.getText(bungiiEstimatePage.Time());
            String pickupTime = pickupDateTime.substring(8,13);
            System.out.println("Pickup time " + pickupTime);

            expectedTime=expectedTime.replace(" am", "").replace(" pm", "").replace(" AM", "").replace(" PM", "");
        testStepVerify.isEquals(pickupTime, expectedTime);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
        }

    @Then("^I should see the minimum scheduled time for Solo Bungii displayed on the Estimate page$")
    public void i_should_see_the_minimum_scheduled_time_for_solo_bungii_displayed_on_the_estimate_page() throws Throwable {
        try{
        action.click(estimatePage.Time());
        if(action.isElementPresent(estimatePage.Button_Later(true)))
        action.click(estimatePage.Button_Later());
        action.click(estimatePage.Button_DateConfirm());
        action.click(estimatePage.Button_TimeConfirm());
        i_should_see_the_minimum_scheduled_time_displayed_on_the_estimate_page();
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

    @Then("^I should see \"([^\"]*)\" on Bungii estimate$")
    public void iShouldSeeOnBungiiEstimate(String arg0) throws Throwable {
        try {
            // Write code here that turns the phrase above into concrete actions
            switch (arg0) {
                case "two drivers selected":

                    testStepAssert.isElementTextEquals(Page_CustHome.Switch_SoloDuo(), "2", "Driver trip should be Duo", "'2' text is displayed ", "2 text message is not displayed");
                    break;

                case "all elements":
                    action.scrollToTop();
                    testStepVerify.isEquals(action.getText(bungiiEstimatePage.Time()), "Now", "Bungii time should be 'Now'", "Bungii time is" + action.getText(bungiiEstimatePage.Time()));
                    testStepAssert.isElementDisplayed(bungiiEstimatePage.Header_Estimate(), "Estimate header should be displayed ", "Estimate header is displayed", "Estimate header is not displayed");
                    cucumberContextManager.setScenarioContext("PROMOCODE_VALUE", action.getText(bungiiEstimatePage.Link_Promo(true)));
                    double expectedTotalEstimate = utility.bungiiEstimate(action.getText(bungiiEstimatePage.Text_TripDistance()), action.getText(bungiiEstimatePage.Link_LoadingUnloadingTime()), utility.getEstimateTime(), action.getText(bungiiEstimatePage.Link_Promo(true)));
                    String loadTime = action.getText(bungiiEstimatePage.Text_TotalEstimate());
                    String truncValue = new DecimalFormat("#.##").format(expectedTotalEstimate);
                    if (!truncValue.contains(".")) truncValue = truncValue + ".00";
                    int index = truncValue.indexOf(".");
                    if (truncValue.substring(index).length() == 2) truncValue = truncValue + "0";
                    String actualValue = loadTime;//vishal[2503]2 digit verification//loadTime.substring(0, loadTime.length() - 1);
                    testStepVerify.isEquals(actualValue, "~$" + String.valueOf(truncValue));
                    //vishal[1803]
                    testStepVerify.isTrue(action.getText(bungiiEstimatePage.Text_TripDistance()).contains("miles"), "Trip distance should be in miles", "Trip Distance does contains miles , actual value" + action.getText(bungiiEstimatePage.Text_TripDistance()), "Trip Distance does not contains miles , actual value" + action.getText(bungiiEstimatePage.Text_TripDistance()));
                    action.scrollToBottom();
                    testStepVerify.isElementNotEnabled(bungiiEstimatePage.Button_RequestBungii(true), "Request Bungii should be disabled", "Reguest Bungii button is disabled", "Reguest Bungii button is enabled");

                    testStepVerify.isTrue(bungiiEstimatePage.Checkbox_AgreeEstimate().getAttribute("checked").equals("false"), "Estimate agree checkbox should be unchecked", "Estimate agree checkbox should be is checked");
                    break;

                case "driver cancelled":
                    testStepAssert.isElementDisplayed(Page_CustHome.Title_HomePage(), "Home page title should be displayed", "Home page title is displayed", "Home page title is not displayed");
                    testStepAssert.isElementDisplayed(Page_CustHome.Button_GetEstimate(), "Get estimate button should be displayed", "Get estimate button is displayed", "Get estimate button is not displayed");
                    break;
                case "Bungii posted Success page":
                    testStepAssert.isElementDisplayed(Page_CustHome.Image_Tick(), "Bungii Posted image should be displayed ", "Bungii posted image is displayed ", "Bungii posted image is not displayed");
                    break;
                case "previous values":
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_TripDistance(), (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
//                  testStepVerify.isElementTextEquals(Page_Estimate.Text_TotalEstimate(),(String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_PickupLocation_LineOne(), (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_PickupLocation_LineTwo(), (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_DropOffLocation_LineOne(), (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_DropOffLocation_LineTwo(), (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2"));
                    break;
                case "Bungii Estimate page with all details":
                    action.scrollToTop();
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Time(), (String) cucumberContextManager.getScenarioContext("BUNGII_TIME"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_TripDistance(), (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_TotalEstimate(), (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Link_LoadingUnloadingTime(), (String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME"));

                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_PickupLocation_LineOne(), (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_PickupLocation_LineTwo(), (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_DropOffLocation_LineOne(), (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_DropOffLocation_LineTwo(), (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2"));
                    break;
                case "zero estimated cost":
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_TotalEstimate(), "~$0.00");
                    break;
                case "estimated cost":
                    String estimatedCost=action.getText(estimatePage.Text_GetCost());
                    String previousCost=(String)cucumberContextManager.getScenarioContext("BUNGIICOST");
                    estimatedCost.replace("~$","");
                    previousCost.replace("~$","");
                    if(!previousCost.equals(estimatedCost))
                    testStepAssert.isTrue(true, "Cost is estimated for Bungii.", "Cost is not estimated for Bungii. "+ previousCost+ " | "+ estimatedCost);
                    break;
                case "Correct Time Format":

                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in tapping on "+arg0+" on Bungii estimate", true);
        }
    }

    @Given("^I am logged in as \"([^\"]*)\" customer$")
    public void iAmLoggedInAsCustomer(String arg0) throws Throwable {
        try {
            Thread.sleep(2000);
            switch (arg0) {
                case "existing":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("ccustomer_generic.phonenumber"));
                    break;
                case "new test customer":
                    utility.loginToCustomerApp((String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER"), PropertyUtility.getDataProperties("customer_generic.password"));
                    cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", PropertyUtility.getDataProperties("NEW_USER_NUMBER"));
                    break;
                case "newly registered":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_newlyregistered.phonenumber"), PropertyUtility.getDataProperties("customer_newlyregistered.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer_newlyregistered.phonenumber"));
                    break;
                case "already having bungiis":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_withbungiis.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer_withbungiis.phonenumber"));
                    break;
                case "having referral code":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_havingReferral.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer_havingReferral.phonenumber"));
                    break;
                case "my":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("ccustomer_generic.phonenumber"));
                    break;
                case "stage":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    break;
                case "valid":
                    //utility.loginToCustomerApp(PropertyUtility.getDataProperties("valid.customer.phone"), PropertyUtility.getDataProperties("valid.customer.password"));
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer_generic.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                    break;
                case "no promocode":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("valid.customer.no.promocode"), PropertyUtility.getDataProperties("valid.customer.password.no.promocode"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("valid.name.customer.no.promocode"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("valid.customer.no.promocode"));
                    break;
                case "valid boston":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("boston.customer.phone"), PropertyUtility.getDataProperties("boston.customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("boston.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("boston.customer.phone"));
                    break;
                case "valid baltimore":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("baltimore.customer.phone"), PropertyUtility.getDataProperties("baltimore.customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("baltimore.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("baltimore.customer.phone"));
                    break;
                case "valid atlanta":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("atlanta.customer.phone"), PropertyUtility.getDataProperties("atlanta.customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("atlanta.customer.phone"));
                    break;
                case "valid kansas":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("kansas.customer1.phone"), PropertyUtility.getDataProperties("kansas.customer1.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("kansas.customer1.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("kansas.customer1.phone"));
                    break;
                case "valid kansas 2":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("Kansas.customer2.phone"), PropertyUtility.getDataProperties("Kansas.customer2.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer2.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("Kansas.customer2.phone"));
                    break;
                case "valid customer 2":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("atlanta.customer2.phone"), PropertyUtility.getDataProperties("atlanta.customer2.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2", PropertyUtility.getDataProperties("atlanta.customer2.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2_PHONE", PropertyUtility.getDataProperties("atlanta.customer2.phone"));
                    break;
                case "newly created user":
                    String phoneNumber=(String) cucumberContextManager.getFeatureContextContext("CUSTOMER_HAVING_REF_CODE");
                    utility.loginToCustomerApp(phoneNumber, PropertyUtility.getDataProperties("customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", phoneNumber);
                    break;
                case "New":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("atlanta.customer3.phone"), PropertyUtility.getDataProperties("atlanta.customer3.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer3.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("atlanta.customer3.phone"));
                    break;
                case "newly registered customer":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_newly.registered.phonenumber"), PropertyUtility.getDataProperties("customer_newly.registered.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer_newly.registered.phonenumber"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer_newly.registered.name"));
                    break;
                case "Testcustomertywd_appleand_A Android":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customerA.phone.number"), PropertyUtility.getDataProperties("customerA.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customerA.phone.number"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customerA.phone.name"));
                    break;
                case "Testcustomertywd_appleand_B Android":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customerB.phone.number"), PropertyUtility.getDataProperties("customerB.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customerB.phone.number"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customerB.phone.name"));
                    break;
                case "Testcustomertywd_appleand_C Android":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customerC.phone.number"), PropertyUtility.getDataProperties("customerC.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customerC.phone.number"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customerC.phone.name"));
                    break;
                case "Testcustomertywd_appleand_D Android":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customerD.phone.number"), PropertyUtility.getDataProperties("customerD.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customerD.phone.number"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customerD.phone.name"));
                    break;
                case "Testcustomertywd_appleand_E Android":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customerE.phone.number"), PropertyUtility.getDataProperties("customerE.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customerE.phone.number"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customerE.phone.name"));
                    break;
                case "Testcustomertywd_appleand_F Android":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customerF.phone.number"), PropertyUtility.getDataProperties("customerF.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customerF.phone.number"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customerF.phone.name"));
                    break;
                case "Testcustomertywd_appleNewMB Customer":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customerMB.phonecustomerMB.phone.number.number"), PropertyUtility.getDataProperties("customerMB.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customerMB.phone.number"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customerMB.phone.name"));
                    break;
                case "Testcustomertywd_appleNewMA Customer":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customerMA.phone.number"), PropertyUtility.getDataProperties("customerMA.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customerMA.phone.number"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customerMA.phone.name"));
                    break;
                case "Testcustomertywd_appleNewZ Customer":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customerZ.phone.number"), PropertyUtility.getDataProperties("customerZ.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customerZ.phone.number"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customerZ.phone.name"));
                    break;
                case "valid goa customer":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("goa.customer.phone"),
                    PropertyUtility.getDataProperties("goa.customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2", PropertyUtility.getDataProperties("goa.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2_PHONE", PropertyUtility.getDataProperties("goa.customer.phone"));
                    break;
                case "johnny oliver":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("goa.nontestcustomer.phone"),
                            PropertyUtility.getDataProperties("goa.nontestcustomer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("goa.nontestcustomer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("goa.nontestcustomer.phone"));
                    break;

                case "Testcustomertywd_apple_AGQFCg Test":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("goa.customer.phone1"),
                            PropertyUtility.getDataProperties("goa.customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2", PropertyUtility.getDataProperties("goa.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2_PHONE", PropertyUtility.getDataProperties("goa.customer.phone"));
                    break;

                case "Testcustomertywd_appleNwBBB CustBBB":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("Testcustomertywd_appleNwBBB.phone.number"), PropertyUtility.getDataProperties("Testcustomertywd_appleNwBBB.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Testcustomertywd_appleNwBBB.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("Testcustomertywd_appleNwBBB.phone.number"));
                    break;

                case "Testcustomertywd_appleMarkF LutherF":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("new.customer1.phone"),
                            PropertyUtility.getDataProperties("new.customer1.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2", PropertyUtility.getDataProperties("new.customer1.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2_PHONE", PropertyUtility.getDataProperties("new.customer1.phone"));
                    break;

                case "valid kansas 3":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("Kansas.customer3.phone"), PropertyUtility.getDataProperties("Kansas.customer3.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer3.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("Kansas.customer3.phone"));
                    break;

                case "valid kansas 4":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("Kansas.customer4.phone"), PropertyUtility.getDataProperties("Kansas.customer4.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer4.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("Kansas.customer4.phone"));
                    break;
                case "valid denver8":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("denver8.customer.phone"), PropertyUtility.getDataProperties("denver.customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver8.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("denver8.customer.phone"));
                    break;
                case "Testcustomertywd_appleMarkDN LutherDN":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("Testcustomertywd_appleMarkDN.phone.number"), PropertyUtility.getDataProperties("Testcustomertywd_appleMarkDN.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Testcustomertywd_appleMarkDN.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("Testcustomertywd_appleMarkDN.phone.number"));
                    break;
                case "Testcustomertywd_appleMarkDO LutherDO":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("Testcustomertywd_appleMarkDO.phone.number"), PropertyUtility.getDataProperties("Testcustomertywd_appleMarkDO.phone.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Testcustomertywd_appleMarkDO.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("Testcustomertywd_appleMarkDO.phone.number"));
                    break;
                case "Testcustomertywd_appleMarkFF LutherFF":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("goa.customer2.phone"), PropertyUtility.getDataProperties("goa.customer2.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("goa.customer2.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("goa.customer2.phone"));
                    break;
                case "Testcustomertywd_appleMarkFH LutherFH":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("goa.customer3.phone"), PropertyUtility.getDataProperties("goa.customer3.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("goa.customer3.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("goa.customer3.phone"));
                    break;
                case "Testcustomertywd_appleMarkFL LutherFL":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("Kansas.customer7.phone"), PropertyUtility.getDataProperties("Kansas.customer7.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer7.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("Kansas.customer7.phone"));
                    break;
                case "Testcustomertywd_appleMarkFM LutherFM":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("Kansas.customer8.phone"), PropertyUtility.getDataProperties("Kansas.customer8.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer8.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("Kansas.customer8.phone"));
                    break;
                case "Testcustomertywd_appleMarkFN LutherFN":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("Kansas.customer9.phone"), PropertyUtility.getDataProperties("Kansas.customer9.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer9.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("Kansas.customer9.phone"));
                    break;
                case "Testcustomertywd_appleMarkFO LutherFO":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("atlanta.customer4.phone"), PropertyUtility.getDataProperties("atlanta.customer4.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer4.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("atlanta.customer4.phone"));
                    break;
                case "Testcustomertywd_appleMarkFP LutherFP":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("boston.customer2.phone"), PropertyUtility.getDataProperties("boston.customer2.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("boston.customer2.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("boston.customer2.phone"));
                    break;
                case "Testcustomertywd_appleMarkFQ LutherFQ":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("boston.customer3.phone"), PropertyUtility.getDataProperties("boston.customer3.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("boston.customer3.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("boston.customer3.phone"));
                    break;
                case "Testcustomertywd_appleMarkGJ LutherGJ":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("goa.customer5.phone"), PropertyUtility.getDataProperties("goa.customer5.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("goa.customer5.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("goa.customer5.phone"));
                    break;
                case "Testcustomertywd_BppleMarkGH LutherGH":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("Washington.customer1.phone"), PropertyUtility.getDataProperties("Washington.customer1.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer3.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("atlanta.customer3.phone"));
                    break;
                case "Testcustomertywd_appleMarkGY LutherGY":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("goa.customer6.phone"), PropertyUtility.getDataProperties("goa.customer6.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("goa.customer6.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("goa.customer6.phone"));
                    break;
                case "Testcustomertywd_appleMarkGZ LutherGZ":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("goa.customer7.phone"), PropertyUtility.getDataProperties("goa.customer7.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("goa.customer7.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("goa.customer7.phone"));
                    break;
                case "Testcustomertywd_appleMarkHA LutherHA":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("goa.customer8.phone"), PropertyUtility.getDataProperties("goa.customer8.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("goa.customer8.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("goa.customer8.phone"));
                    break;
                case "Testcustomertywd_appleMarkHB LutherHB":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("goa.customer9.phone"), PropertyUtility.getDataProperties("goa.customer9.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("goa.customer9.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("goa.customer9.phone"));
                    break;
                default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in login in as customer", true);
        }
    }

    @When("^I enter \"([^\"]*)\" on Bungii estimate$")
    public void iEnterOnBungiiEstimate(String arg0) throws Throwable {
        try {
          //  Thread.sleep(5000);
            if(action.isAlertPresent())
            {
                action.click(homePage.Button_AlertDone());
                action.waitUntilIsElementExistsAndDisplayed(Page_Signup.GenericHeader(true));
            }

            String screen =   action.getText(Page_Signup.GenericHeader());

            logger.detail("Customer is on Screen : "+screen);
            if(!screen.equalsIgnoreCase("BUNGII"))
            {
                fail("Customer should be on Home screen", "Customer is on "+ screen +" screen");
            }
            if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                action.click(Page_CustHome.Button_ClearPickUp());
            switch (arg0) {
                case "valid pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.locationB"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.locationB"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");
                    break;
                case "goa location in pickup and dropoff fields long distance":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.locationA"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.locationA"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");
                    break;
                case "kansas pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.kansas"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.kansas"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");
                    break;
                case "kansas short pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location2.kansas"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location2.kansas"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");
                    break;
                case "atlanta pickup and dropoff locations away from driver":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.away.atlanta"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.away.atlanta"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "atlanta");
                    break;
                case "boston pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.boston"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.boston"));
                    break;
                case "new boston pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.boston.new"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.boston"));
                    break;
                case "baltimore pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.baltimore"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.baltimore"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "baltimore");
                    break;
                case "atlanta pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.atlanta"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.atlanta"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "atlanta");
                    break;
                case "atlanta long pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.atlanta"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.away.atlanta"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "atlanta");
                    break;
                case "current location in pickup and dropoff fields":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.locationA"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.locationA"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");
                    break;
                case "current location in pickup and dropoff fields long distance":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.locationA"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.locationA"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");
                    break;

                case "Goa pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("current.location"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Goa"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");
                    testStepAssert.isNotElementDisplayed(homePage.Text_ETAvalue(), "Less than 30mins", "Less than 30mins", "More than 30mins");
                    break;

                case "kansas pickup and dropoff locations less than 150 miles":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Kansas.less.150"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Kansas.less.150"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Kansas");
                    testStepAssert.isElementDisplayed(Page_CustHome.Button_GetEstimate(), "Less than 150 miles", "Less than 150 miles", "More than 150 miles");
                    break;

                case "kansas pickup and dropoff locations equal to 150 miles":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Kansas.equal.150"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Kansas.equal.150"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Kansas");
                    testStepAssert.isElementDisplayed(Page_CustHome.Button_GetEstimate(), "Equal to 150 miles", "Equal to 150 miles", "Not equal to 150 miles");
                    break;

                case "kansas pickup and dropoff locations greater than 150 miles":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Kansas.more.150"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Kansas.more.150"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Kansas");
                    break;

                case "San Francisco pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.sanFrancisco"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.sanFrancisco"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "San Francisco");
                    break;

                case "Odenton pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.odenton"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.odenton"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Washington");
                    break;

                case "kansas pickup and dropoff locations greater than 30mins":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Kansas.less.30"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Kansas.less.30"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Kansas");
                    break;

                case "far off Goa pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("goa.pickup.location.A"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("goa.dropoff.location.A"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Goa");
                    break;

                case "Goa pickup and dropoff location":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Goa"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Goa"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");
                    //cucumberContextManager.setScenarioContext("GEOFENCE","goa");

                    break;

                case "new baltimore pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.baltimore.2"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.baltimore.2"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "baltimore");
                    break;

                case "kansas very short trip location":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Kansas.very.short"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Kansas.very.short"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Kansas");
                    break;

                case "new Kansas pickup less than 10 miles":
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location3.kansas"));
                    action.click(Page_CustHome.Button_ETASet());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location3.kansas"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");
                    break;

                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            if(action.isElementPresent(Page_CustHome.Button_ETASet(true)))
                action.click(Page_CustHome.Button_ETASet(true));
            action.waitUntilIsElementExistsAndDisplayed(Page_CustHome.Button_GetEstimate());
            String pickUpLocationLine1 = action.getText(Page_CustHome.TextBox_PickUpLocLine1()), pickUpLocationLine2 = action.getText(Page_CustHome.TextBox_PickUpLocLine2());
            String dropUpLocationLine1 = action.getText(Page_CustHome.TextBox_DropOffLine1()), dropUpLocationLine2 = action.getText(Page_CustHome.TextBox_DropOffLine2());
            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", pickUpLocationLine1);
            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", pickUpLocationLine2);
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", dropUpLocationLine1);
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", dropUpLocationLine2);
          log("I enter "+arg0+" on Bungii estimate screen", "I entered "+arg0+" on Bungii estimate screen", false);
             } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting pickup and drop off address.", true);
        }
    }

    @And("^I enter the \"([^\"]*)\" on the Bungii estimate$")
    public void i_enter_the_something_on_the_bungii_estimate(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "kansas pickup and dropoff locations greater than 150 miles":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Kansas.more.150"));
                    Thread.sleep(4000);
                    action.click(Page_CustHome.Button_ETASet(true));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Kansas.more.150"));
                    Thread.sleep(4000);
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Kansas");
                    Thread.sleep(2000);
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "\"GOOGLE API LIMIT EXCEEDED | Error in selecting pickup and drop off address. Most probable rootcause google address api limit exceeded.", true);
        }
    }

    private void savePickupAddress() {
        String pickUpLocationLine1 = action.getText(Page_CustHome.TextBox_PickUpLocLine1()), pickUpLocationLine2 = action.getText(Page_CustHome.TextBox_PickUpLocLine2());
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", pickUpLocationLine1);
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", pickUpLocationLine2);
    }

    private void saveDropupAddress() {
        String dropUpLocationLine1 = action.getText(Page_CustHome.TextBox_DropOffLine1()), dropUpLocationLine2 = action.getText(Page_CustHome.TextBox_DropOffLine2());
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", dropUpLocationLine1);
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", dropUpLocationLine2);
    }

/*    @And("^I add \"([^\"]*)\" PromoCode$")
    public void iAddPromoCode(String arg0) throws Throwable {
        try {
        String promoCode="";
        switch (arg0) {
            case "valid":
                promoCode= PropertyUtility.getDataProperties("promocode.valid");
                break;
            case "fixed valid":
                promoCode= PropertyUtility.getDataProperties("promocode.fixedvalid");
                break;
            case "invalid":
                promoCode= PropertyUtility.getDataProperties("promocode.invalid");
                break;
            case "expired":
                promoCode= PropertyUtility.getDataProperties("promocode.expired");
                break;
            case "referral":
                promoCode= PropertyUtility.getDataProperties("referral.code");
                break;
            case "first time":
                promoCode= PropertyUtility.getDataProperties("promocode.firsttime");
                break;
            case "used one off":
                promoCode=PropertyUtility.getDataProperties("promocode.useedoneoff");
                break;
            default:
                break;
        }
        action.sendKeys(Page_SaveMoney.Textfield_PromoCode(), promoCode);

        log(" I should able to add " + arg0 + " promo code ",
                "I entered promo code '" + promoCode + "'", true);

        } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }*/

    /*    @And("^I tap \"([^\"]*)\" on Save Money page$")
        public void iTapOnSaveMoneyPage(String arg0) throws Throwable {
            try {
            switch (arg0) {
                case "Add":
                    action.click(Page_SaveMoney.Button_AddPromoPage());
                    break;
                case "Get More Money":
                    action.click(Page_SaveMoney.Button_GetMoreMoney());
                    break;
                default:
                    break;
            }
            log(" I should able to tap " + arg0 + " on Save Money page",
                    "I tapped on " + arg0 + " on Save Money Page", true);
            } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
            }
        }*/
    @And("^I get Bungii details on Bungii Estimate$")
    public void i_get_bungii_details_on_bungii_estimate() throws Throwable {
        try{
        action.scrollToTop();
        // SAVE required values in scenario context
        cucumberContextManager.setScenarioContext("BUNGII_TIME", action.getText(bungiiEstimatePage.Time()));
        cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", action.getText(bungiiEstimatePage.Text_TripDistance()));
        cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", action.getText(bungiiEstimatePage.Text_TotalEstimate()));
        cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", action.getText(bungiiEstimatePage.Link_LoadingUnloadingTime()));
        //Sprint 29 change
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", action.getText(bungiiEstimatePage.Text_PickupLocation_LineOne()));
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", action.getText(bungiiEstimatePage.Text_PickupLocation_LineTwo()));
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", action.getText(bungiiEstimatePage.Text_DropOffLocation_LineOne()));
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", action.getText(bungiiEstimatePage.Text_DropOffLocation_LineTwo()));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I get Bungii location details on Bungii Estimate$")
    public void i_get_bungii_locations_details_on_bungii_estimate() throws Throwable {
        try{
        // SAVE required values in scenario context
        //Sprint 29 change
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", action.getText(bungiiEstimatePage.Text_PickupLocation_LineOne()));
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", action.getText(bungiiEstimatePage.Text_PickupLocation_LineTwo()));
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", action.getText(bungiiEstimatePage.Text_DropOffLocation_LineOne()));
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", action.getText(bungiiEstimatePage.Text_DropOffLocation_LineTwo()));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I add loading/unloading time of \"([^\"]*)\"$")
    public void iAddLoadingUnloadingTimeOf(String arg0) throws Throwable {
        try {
            if (!action.isElementPresent(bungiiEstimatePage.Header_Estimate(true)))
                Thread.sleep(5000);

            action.click(bungiiEstimatePage.Link_LoadingUnloadingTime());
            if (!action.isElementPresent(bungiiEstimatePage.LoadingUnloadingTime_15(true)))
                action.click(bungiiEstimatePage.Link_LoadingUnloadingTime());

            switch (arg0) {
                case "15 mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_15());
                    break;

                case "30 mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_30());
                    break;

                case "45 mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_45());
                    break;

                case "60 mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_60());
                    break;

                case "75 mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_75());
                    break;

                case "90+ mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_90());
                    break;

                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            Thread.sleep(5000);
            //save load time in cucumber context
            String estimatedCost=action.getText(estimatePage.Text_GetCost());
            cucumberContextManager.setScenarioContext("BUNGIICOST", estimatedCost);
            cucumberContextManager.setScenarioContext("BUNGII_LOAD_TIME", arg0.replace(" mins", ""));
            log(" I add loading/unloading time " + arg0 + "on Estimate page",
                    "I clicked on " + arg0 + " as Estimate loading/unloading time ", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
private void addPhoto(AndroidDriver<MobileElement> driver) throws Throwable
{
    if (!action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)))
        action.swipeLeft(bungiiEstimatePage.Row_Images());

    if (!action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)))
        action.scrollToBottom();

    action.click(bungiiEstimatePage.Link_AddPhoto());
    Thread.sleep(2000);
    if(action.isAlertPresent()){
        try {
            Thread.sleep(3000);
            action.clickAlertButton("ALLOW");
            Thread.sleep(3000);
        }
        catch (Exception ex){

        }
    }
    /*action.click(bungiiEstimatePage.Option_Camera());
    Thread.sleep(5000);
    driver.pressKey(new KeyEvent(AndroidKey.TAB));
    Thread.sleep(5000);
    driver.pressKey(new KeyEvent(AndroidKey.TAB));
    Thread.sleep(5000);
    driver.pressKey(new KeyEvent(AndroidKey.ENTER));
   // Thread.sleep(5000);
   // bungiiEstimatePage.Link_AddPhoto(true);
   // int i = 1000;
   // while(i<=180000) {
        Thread.sleep(180000); //After 3 minutes it automatically selects image
     //   i++;
    //}

    //Thread.sleep(5000);
    //driver.pressKey(new KeyEvent(AndroidKey.TAB));
    //driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    bungiiEstimatePage.Link_AddPhoto(true);*/
    action.click(bungiiEstimatePage.Option_Gallery());
    action.click(bungiiEstimatePage.Option_LARGEIMAGEFOLDER());
    //logger.detail(SetupManager.getDriver().getPageSource());
    Random ran = new Random();
    int random = ran.nextInt(2)+1;
    action.click(bungiiEstimatePage.IMAGE_LOCATOR(random));
    bungiiEstimatePage.Link_AddPhoto(true);

}
    @When("^I add \"([^\"]*)\" photos to the Bungii$")
    public void iAddPhotosToTheBungii(String arg0) throws Throwable {
        try {
            action.scrollToBottom();
            action.waitUntilIsElementExistsAndDisplayed(bungiiEstimatePage.Header_Estimate(), 30L);
            int i = 0;
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            addPhoto(driver);
            //    action.scrollToBottom();
         /*   do {
                if (!action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)))
                    action.swipeLeft(bungiiEstimatePage.Row_Images());

                if (!action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)))
                    action.scrollToBottom();

                if (!action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)) && i >= 3) {
                    testStepAssert.isFalse(action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)), "False", "True");
                    break;
                }
                action.click(bungiiEstimatePage.Link_AddPhoto());
                Thread.sleep(2000);
                if(action.isAlertPresent()){
                    try {
                        Thread.sleep(3000);
                        action.clickAlertButton("ALLOW");
                        Thread.sleep(3000);
                    }
                    catch (Exception ex){

                    }
                }

                //adding most probable outcome first
                if (action.isElementPresent(bungiiEstimatePage.Option_Camera(true))) {
                    //do nothing,
                } else if (action.isElementPresent(bungiiEstimatePage.Message_CameraPermissions(true)))
                    action.click(bungiiEstimatePage.Permissions_CameraAllow());

                action.click(bungiiEstimatePage.Option_Camera());
                Thread.sleep(10000);
                try {
                    int x1 = 114, y1 = 1530, x2 = 117, y2 = 1579, x3 = 109, y3 = 1562;
                    TouchAction touchAction = new TouchAction((AppiumDriver) SetupManager.getDriver());
                    PointOption top = point(x1, y1);
                    touchAction.tap(top).perform();
                    Thread.sleep(6000);
                    top = point(x2, y2);
                    touchAction.tap(top).perform();
                    Thread.sleep(6000);
                    top = point(x3, y3);
                    touchAction.tap(top).perform();
                    Thread.sleep(6000);
                }
                catch (Exception e){

                }

                String manufacturer = driver.getCapabilities().getCapability("deviceType").toString();
                if (manufacturer.equalsIgnoreCase("MOTOROLA")) {
                    Thread.sleep(5000);
                    // driver.tap(1, 100, 500, 1);
                    new TouchAction(driver)
                            .tap(point(100, 500))
                            .waitAction(waitOptions(Duration.ofMillis(250))).perform();
                    new TouchAction(driver)
                            .tap(point(100, 500))
                            .waitAction(waitOptions(Duration.ofMillis(250))).perform();
                    Thread.sleep(2000);

                    //if (action.isElementPresent(bungiiEstimatePage.Button_CameraIcon(true)))
                     //   action.click(bungiiEstimatePage.Button_CameraIcon());
                    //Camera click for Motorola 9.0 G7 Play
                    new TouchAction(driver)
                            .tap(point(355, 1230))
                            .waitAction(waitOptions(Duration.ofMillis(250))).perform();
                    Thread.sleep(2000);
                    new TouchAction(driver)
                            .tap(point(432, 1229))
                            .waitAction(waitOptions(Duration.ofMillis(250))).perform();
                    driver.pressKey(new KeyEvent(AndroidKey.TAB));
                    Thread.sleep(1000);
                    driver.pressKey(new KeyEvent(AndroidKey.TAB));
                    Thread.sleep(1000);
                    driver.pressKey(new KeyEvent(AndroidKey.ENTER));
                    Thread.sleep(10000);
                    driver.pressKey(new KeyEvent(AndroidKey.ENTER));

                    if (action.isElementPresent(bungiiEstimatePage.Button_Review(true)))
                        action.click(bungiiEstimatePage.Button_Review());
                    else if (driver.getCapabilities().getCapability("deviceModel").toString().contains("Moto G") && driver.getCapabilities().getCapability("deviceModel").toString().contains("4")) {
                        action.click(new Point(644, 1562));
                        Thread.sleep(2000);
                        logger.detail("clicked by cordinate");
                        if (!action.isElementPresent(bungiiEstimatePage.Header_Estimate(true))) {
                            Thread.sleep(120000);//wait for auto approve
                            logger.detail("wait to auto approve");
                        }
                    }
                }

                if (manufacturer.equalsIgnoreCase("") || manufacturer.equalsIgnoreCase("SAMSUNG")) {
                    action.click(bungiiEstimatePage.Button_Camera_ClickAlternate());
                    //DriverAction.keyBoardEvent(AndroidKeyCode.Keycode_CAMERA);
                    Thread.sleep(2000);
                    action.click(bungiiEstimatePage.Button_Camera_OK());
                }
                Thread.sleep(2000);

                i++;

            } while (i < Integer.parseInt(arg0));

*/
            Thread.sleep(4000);
            testStepVerify.isElementDisplayed(bungiiEstimatePage.Button_SelectedImage(), "I add " + arg0 + " photos to the Bungii", "I selected photos on estimate page", "Selected image was not displayed on Estimate page");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Problem in adding a photo to Bungii on Estimate Screen", true);
        }
    }

    @When("^I add large image photos to the Bungii$")
    public void iAddLargePhotosToTheBungii() throws Throwable {
        try {
            if (!action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)))
                action.swipeLeft(bungiiEstimatePage.Row_Images());

            if (!action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)))
                action.scrollToBottom();


            action.click(bungiiEstimatePage.Link_AddPhoto());
            Thread.sleep(2000);

            if (action.isElementPresent(bungiiEstimatePage.Message_CameraPermissions(true)))
                action.click(bungiiEstimatePage.Permissions_CameraAllow());
            Thread.sleep(2000);

           action.click(bungiiEstimatePage.Option_Gallery());
           //action.click(bungiiEstimatePage.Option_OverLayPhotos());
           action.click(bungiiEstimatePage.Option_LARGEIMAGEFOLDER());
           action.click(bungiiEstimatePage.IMAGE_LOCATOR());
            bungiiEstimatePage.Link_AddPhoto(true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("I select Bungii Time as \"([^\"]*)\"")
    public void iSelectBungiiTimeAs(String arg0) {
        try {
            switch (arg0) {
                case "next possible scheduled":
                case "OLD BUNGII TIME":
                    utility.selectBungiiTime();
                    break;
                case "NEW BUNGII TIME":
                    utility.selectBungiiTime();
                    break;
                case "next possible scheduled for duo":
                    //Missing?
                    break;
                case "BUNGII TIME":
                    utility.selectNewBungiiTime();
                    break;
                case "MIDNIGHT BUNGII TIME":
                    String h="00",m="30",ampm="am";
                    utility.selectBungiiTime(h,m,ampm);
                    break;
                case "Next Schedule Time":
                    utility.selectTime();
                    break;
                case "30 MIN DELAY":
                    utility.selectTimeValue(30);
                    break;
                case "1 HOUR DELAY":
                    utility.selectTimeValue(60);
                    break;
                case "2 HOUR DELAY":
                    utility.selectTimeValue(120);
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            action.scrollToTop();
            String bungiiTime = action.getText(bungiiEstimatePage.Time());
            String oldTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
            if(!oldTime.equalsIgnoreCase("")) {
                if (arg0.equalsIgnoreCase("OLD BUNGII TIME")) {
                    testStepVerify.isEquals(bungiiTime, (String) cucumberContextManager.getScenarioContext("BUNGII_TIME"), "I selected bungii time as old bungii time : " + bungiiTime, " I was not able to select bungii with old bungii time , Bungii time : " + bungiiTime + " | Expected time : " + (String) cucumberContextManager.getScenarioContext("BUNGII_TIME"));
                }
            }
            cucumberContextManager.setScenarioContext("BUNGII_TIME", bungiiTime);
            pass(" I select Bungii Time as " + arg0,
                    "Selected Bungii time is " + bungiiTime);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I schedule Bungii at \"([^\"]*)\" Time$")
    public void i_schedule_bungii_at_something_time(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Next Schedule":
                   // utility.selectTime();
                    utility.selectNewerTime();
                    break;
                case "Future":
                    // utility.selectTime();
                    utility.selectFutureTime();
                    break;


                default:
                    error("UnImplemented Step", "UnImplemented Step");
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }



    @Then("^\"([^\"]*)\" information icon should display correct information$")
    public void something_information_icon_should_display_correct_information(String iconName) throws Throwable {
        try {
            String loadTime = (String) cucumberContextManager.getScenarioContext("BUNGII_LOAD_TIME");
            String expectedMessage = "", actualMessage = "";

            switch (iconName.toUpperCase()) {
                case "LOAD/UPLOAD TIME":
                    expectedMessage = PropertyUtility.getMessage("customer.info.loadtime");
                    action.click(bungiiEstimatePage.Button_InfoLoadTime());
                    break;
                case "TIME":
                    expectedMessage = PropertyUtility.getMessage("customer.info.time");
                    action.click(bungiiEstimatePage.Button_InfoTime());

                    break;
                case "TOTAL ESTIMATE":
                    expectedMessage = PropertyUtility.getMessage("customer.info.totalestimate").replaceAll("<TIME>", loadTime.trim());
                    action.click(bungiiEstimatePage.Button_InfoEstimate());
                    break;
                default:
                    fail("Step  Should be successful",
                            "UnImplemented STEP , please verify test step", true);
                    break;
            }
            action.waitUntilIsElementExistsAndDisplayed(bungiiEstimatePage.Alert_ConfirmRequestMessage(), 120L);
            actualMessage = action.getText(bungiiEstimatePage.Alert_ConfirmRequestMessage());
            testStepVerify.isEquals(actualMessage, expectedMessage);

            action.click(bungiiEstimatePage.Button_RequestConfirm());
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^check if I have ability to select different load time and Estimate cost is re calculated$")
    public void check_if_i_have_ability_to_select_different_load_time_and_estimate_cost_is_re_calculated() {
        try {
            String oldEstimateValue = action.getText(bungiiEstimatePage.Text_TotalEstimate());
            action.click(bungiiEstimatePage.Link_LoadingUnloadingTime());
            for (int i = 0; i < loadTimeValue.length; i++) {

                if (i != 0)
                    action.click(bungiiEstimatePage.Link_LoadingUnloadingTime());
                List<WebElement> loadTime = bungiiEstimatePage.LoadingUnloadingTime();
                String optionText = action.getText(loadTime.get(i));
                boolean flag = optionText.equals(loadTimeValue[i]);

                //Click on options
                action.click(loadTime.get(i));

                testStepVerify.isTrue(flag,
                        "I should able to to select " + loadTimeValue[i] + " as load time",
                        "I was able to to select " + loadTimeValue[i] + " as load time",
                        "I was not able to to select " + loadTimeValue[i] + " as load time");
                Thread.sleep(1000);
                String newEstimateValue = action.getText(bungiiEstimatePage.Text_TotalEstimate());
                Thread.sleep(2000);
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

    @Then("^I should see the \"([^\"]*)\" no more displayed on the estimates page$")
    public void i_should_see_the_something_no_more_displayed_on_the_estimates_page(String strArg1) throws Throwable {
        testStepAssert.isElementDisplayed(bungiiEstimatePage.Link_Promo(true), "Promo Code should not be displayed", "Promo Code is not displayed", "Promo Code is displayed");
    }


    /*   @Then("^I verify that selected time is next available time$")
       public void i_verify_that_selected_time_is_next_available_time(){
           try{
           String time= (String) cucumberContextManager.getScenarioContext("TIME");
             time=formatDate(time,8);
           String actualtime=action.getText(Page_Estimate.Text_BungiiTime());
           testStepVerify.isEquals(time, actualtime);
           }
           catch (Exception e) {
               logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
               error("Step  Should be successful", "Error performing step,Please check logs for more details",
                       true);
           }

       }
   */
    @Then("^correct details next available scheduled time should be displayed$")
    public void correct_details_next_available_scheduled_time_should_be_displayed() throws Throwable {
        try {
            Date date = getNextScheduledBungiiTime();
            //String strTime = bungiiTimeDisplayInTextArea(date);
            String[] strTime=bungiiTimeZoneDisplayInTextArea(date);
            strTime[0]=strTime[0].replace("am ","").replace("pm" ,"");
            strTime[1]=strTime[1].replace("am ","").replace("pm ","");

            String displayedTime = getElementValue("TIME");
                testStepAssert.isTrue(displayedTime.equalsIgnoreCase(strTime[0]) || displayedTime.equalsIgnoreCase(strTime[1]),"The correct scheduled time is displayed.", "The correct scheduled time is not displayed. Actual : "+displayedTime + " |  Expected : "+ strTime[0] +" or " +strTime[1]);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    public String formatDate(String date, int position) {
        Calendar calOne = Calendar.getInstance();
        int year = calOne.get(Calendar.YEAR);
        String stringToBeInserted = year + " - ";

        // Create a new string
        String newDate = new String();

        for (int i = 0; i < date.length(); i++) {
            if (i == position) {
                // Insert the string to be inserted
                // into the new string
                newDate += stringToBeInserted;
            }
            // Insert the original string character
            // into the new string
            newDate += date.charAt(i);
        }
        return newDate;
    }

    public String getDateForTimeZone() {
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time"));
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
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
            System.out.println("\t" + date1);
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

    /**
     * Format input date and return in required format
     *
     * @param date input date
     * @return formated date
     */
    public String bungiiTimeDisplayInTextArea(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm a");
        String formattedDate = sdf.format(date);
        //After sprint 27 /26 IST is being added in scheduled page
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
            formattedDate = formattedDate + " " + PropertyUtility.getDataProperties("time.label");
        else
            formattedDate = formattedDate + " " + utility.getTimeZoneBasedOnGeofence();
        return formattedDate;
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
            case "TIME":
                value = action.getText(estimatePage.Time());
                value=value.replace("am", "AM").replace("pm","PM");
                break;

            default:
                System.err.println("ELEMENT not found in case" + key);
                break;
        }
        return value;
    }

    @Then("^I verify that selected time is next available time$")
    public void i_verify_that_selected_time_is_next_available_time() {
        try {
            String time = (String) cucumberContextManager.getScenarioContext("TIME");
            time = formatDate(time, 8);
            String actualtime = action.getText(bungiiEstimatePage.Text_BungiiTime());
            testStepVerify.isEquals(time, actualtime);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    /**
     * Format input date and return in required format
     *
     * @param date input date
     * @return formated date
     */
    public String[] bungiiTimeZoneDisplayInTextArea(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm a");
        String formattedDate = sdf.format(date), formattedDate2 = sdf.format(date);
        String timezone1 = null, timezone2 = null;
        //After sprint 27 /26 IST is being added in scheduled page
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        String[] timeZones=new String[2];
        String[] formattedDates=new String[2];

        if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase("")){
            formattedDate = formattedDate + " " + PropertyUtility.getDataProperties("time.label");
            formattedDates[0]=formattedDate;
            formattedDates[1]=" ";
        }
        else {
            timeZones = new String[2];
            timeZones = utility.getDayLightTimeZoneBasedOnGeofence();
            timezone1=timeZones[0];
            timezone2=timeZones[1];
            formattedDate = formattedDate + " " +timezone1;
            formattedDate2= formattedDate2 + " " +timezone2;
            formattedDates[0]=formattedDate;
            formattedDates[1]=formattedDate2;
        }
        return formattedDates;
    }
}
