package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.enums.Status;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.LiveTripsPage;
import com.bungii.ios.pages.customer.EstimatePage;
import com.bungii.ios.pages.driver.BungiiDetailsPage;
import com.bungii.ios.pages.driver.ForgotPasswordPage;
import com.bungii.ios.pages.driver.TripDetailsPage;
import com.bungii.ios.pages.driver.UpdateStatusPage;
import com.bungii.ios.pages.other.MessagesPage;
import com.bungii.ios.pages.other.SafariPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.web.utilityfunctions.DbUtility.getLinkedPickupRef;


public class UpdateStatusSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(UpdateStatusSteps.class);
    MessagesPage messagesPage;
    Rectangle initial;
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    private TripDetailsPage tripDetailsPage;
    private UpdateStatusPage updateStatusPage;
    LiveTripsPage liveTripsPage = new LiveTripsPage();
    private EstimatePage estimatePage;
    private BungiiDetailsPage bungiiDetailsPage;
    private com.bungii.ios.pages.driver.UpdateStatusPage driverUpdateStatusPage;

    public UpdateStatusSteps(BungiiDetailsPage bungiiDetailsPage,EstimatePage estimatePage,UpdateStatusPage updateStatusPage, MessagesPage messagesPage,TripDetailsPage tripDetailsPage,com.bungii.ios.pages.driver.UpdateStatusPage driverUpdateStatusPage) {
        this.bungiiDetailsPage = bungiiDetailsPage;
        this.estimatePage = estimatePage;
        this.updateStatusPage = updateStatusPage;
        this.messagesPage = messagesPage;
        this.tripDetailsPage= tripDetailsPage;
        this.driverUpdateStatusPage = driverUpdateStatusPage;
    }
    ForgotPasswordPage driverforgotPasswordPage=new ForgotPasswordPage();
    SafariPage safariPage= new SafariPage();

    @Then("^I check ETA of \"([^\"]*)\"$")
    public void i_check_eta_of_something(String strArg1){
        try {
            switch (strArg1.toLowerCase()) {
                case "control driver":
                    cucumberContextManager.setScenarioContext("ETA_VALUE",action.getNameAttribute(updateStatusPage.Text_ETAValue()));
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" eta should be displayed to customer$")
    public void something_eta_should_be_displayed_to_customer(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "control driver":
                    String controlDriverEta=(String) cucumberContextManager.getScenarioContext("ETA_VALUE");
                    testStepVerify.isTrue(action.getNameAttribute(updateStatusPage.Text_ETAValue()).equals(controlDriverEta),controlDriverEta+" should be displayed");
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }    }
    @When("^I slide update button on \"([^\"]*)\" Screen$")
    public void i_start_selected_bungii(String screen) {
        try {
            String expectedMessage = "";
            switch (screen.toUpperCase()) {
                case "EN ROUTE TO PICKUP":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.enroute");
                    break;
                case "ARRIVED AT PICKUP":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.arrived");
                    break;
                case "LOADING ITEMS AT PICKUP":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.loading");
                    break;
                case "DRIVING TO DROP-OFF":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.drop.off");
                    break;
                case "UNLOADING ITEMS AT DROP-OFF":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.unloading");
                    break;
                default:
                    break;
            }
            //String actualValue = updateStatusPage.getSliderText();

            updateStatus();
            Thread.sleep(5000);
            log("I slide update button on " + screen + " screen", "I slide update button on " + screen + " screen", true);

		/*testStepVerify.isEquals(actualValue, expectedMessage,
				"I slide update button on " + screen + " Screen",
				"Slider value should be" + expectedMessage + "and actual is" + actualValue,
				"Slider value should be" + expectedMessage + "and actual is" + actualValue);*/
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in sliding on " + screen + " screen in driver app", true);
        }
    }

    @Then("^non control driver should see \"([^\"]*)\" screen$")
    public void non_control_driver_should_see_something_screen(String strArg1) throws Throwable {
        try{
            testStepVerify.isElementEnabled(updateStatusPage.Activity_loader(true)," Driver should be shown loader screen");
            testStepVerify.isElementTextEquals(updateStatusPage.Text_WaitingForDriver(),"Waiting for the other driver to end Bungii.");

    } catch (Throwable e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }
    @When("^I verify and slide update button on \"([^\"]*)\" Screen$")
    public void i_verify_and_start_selected_bungii(String screen) {
        try {
            String expectedMessage = "";

            switch (screen.toUpperCase()) {
                case "EN ROUTE":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.enroute");
                    break;
                case "ARRIVED":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.arrived");
                    break;
                case "LOADING ITEM":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.loading");
                    break;
                case "DRIVING TO DROP OFF":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.drop.off");
                    break;
                case "UNLOADING ITEM":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.unloading");
                    break;
                default:
                    break;
            }
            String actualValue = getSliderText();

            updateStatus();
            Thread.sleep(7000);
            testStepVerify.isEquals(actualValue, expectedMessage,
                    "I slide update button on " + screen + " Screen",
                    "Slider value should be" + expectedMessage + "and actual is" + actualValue,
                    "Slider value should be" + expectedMessage + "and actual is" + actualValue);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Trip Information should be correctly displayed on \"([^\"]*)\" status screen for driver$")
    public void trip_information_should_be_correctly_displayed_on_something_status_screen_for_customer(String key) {
        try {
            boolean isInfoCorrectlyDisplayed = false;

            String expectedCustName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            expectedCustName = expectedCustName.substring(0, expectedCustName.indexOf(" ") + 2);
            String actualName ="" ;
            //boolean isCustomerNameCorrect = ActualName.equals(expectedCustName);
            String TripType= (String) cucumberContextManager.getScenarioContext("TripType");

            switch (key) {
                case "EN ROUTE":
                    if(TripType.equals("Duo")){
                        actualName = getCustomerNameOnDriverApp(5);
                    }
                    else {
                        actualName = getCustomerNameOnDriverApp(4);
                    }
                    isInfoCorrectlyDisplayed = validateEnRouteInfo(getTripInformation());
                    break;
                case "ARRIVED":
                    if(TripType.equals("Duo")){
                        actualName = getCustomerNameOnDriverApp(4);
                    }
                    else {
                        actualName = getCustomerNameOnDriverApp(4);
                    }
                    isInfoCorrectlyDisplayed = validateArrivedInfo(getTripInformation());
                    break;
                case "LOADING ITEMS":
                    if(TripType.equals("Duo")){
                        actualName = getCustomerNameOnDriverApp(5);
                    }
                    else {
                        actualName = getCustomerNameOnDriverApp(4);
                    }
                    isInfoCorrectlyDisplayed = validateLoadingItemsInfo(getTripInformation());
                    break;
                case "DRIVING TO DROP-OFF":
                    if(TripType.equals("Duo")){
                        actualName = getCustomerNameOnDriverApp(5);
                    }
                    else {
                        actualName = getCustomerNameOnDriverApp(4);
                    }
                    isInfoCorrectlyDisplayed = validateDrivingInfo(getTripInformation());
                    break;
                case "UNLOADING ITEMS":
                    if(TripType.equals("Duo")){
                        actualName = getCustomerNameOnDriverApp(5);
                    }
                    else {
                        actualName = getCustomerNameOnDriverApp(4);
                    }
                    isInfoCorrectlyDisplayed = validateUnloadingInfo(getTripInformation());
                    break;
                default:
                    break;
            }

            boolean isCustomerNameCorrect = actualName.equals(expectedCustName);

            if (isInfoCorrectlyDisplayed && isCustomerNameCorrect) {
                pass("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");
            } else {
                fail("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");

            }
          //  logger.detail("PageSource" + SetupManager.getDriver().getPageSource());
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^correct details should be displayed to driver on \"([^\"]*)\" app$")
    public void correct_details_should_be_displayed_to_customer_on_something_app(String key) {
        try {
            switch (key.toUpperCase()) {
                case "SMS":
                    clickSMSToCustomer();
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()));
                    break;
                case "CALL":
                    clickCallToCustomer();
                    if(action.isAlertPresent()) {
                        validateCallButtonAction(); //Commented Call validation as it doesnt open call app or alert on browserstack so if alert is not shown then its skipped
                    }
                    else
                    {
                        warning("Call alert with phone number should be shown","Call alert with phone number is not shown. but test will continue as Browserstack phones doesnt show call app");
                    }
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in tapping on call/sms icons", true);
        }
    }

    @Then("^correct details should be displayed to driver for \"([^\"]*)\"$")
    public void correct_details_should_be_displayed_to_driver_for_something(String key) throws Throwable {
        try {
            ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
            switch (key.toUpperCase()) {
                case "VIEW ITEMS":
                    clickViewItems();
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    Thread.sleep(5000);
                    validateViewImage(1);
                    break;
                case "SMS FOR SUPPORT":
                    clickSMSToSupport();
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), PropertyUtility.getMessage("driver.support.number"));
                    break;
                case "SMS FOR CANCEL INCASE OF EMERGENCEY":
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), PropertyUtility.getMessage("driver.support.number"));
                    break;
                case "DUO CUSTOMER-VIEW ITEM":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions1());
                    action.click(updateStatusPage.Button_ViewItems());
                    validateViewImage(1);
                    break;
                case "DUO CUSTOMER-CALL CUSTOMER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions1());
                    action.click(updateStatusPage.Button_Call());
                    if(action.isAlertPresent()) {
                        validateCallButtonAction(); //Commented Call validation as it doesnt open call app or alert on browserstack so if alert is not shown then its skipped
                    }
                    else
                    {
                        warning("Call alert with phone number should be shown","Call alert with phone number is not shown. but test will continue as Browserstack phones doesnt show call app");
                    }
                    break;
                case "DUO CUSTOMER-TEXT CUSTOMER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions1());
                    action.click(updateStatusPage.Button_Sms());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()));
                    break;
                case "DUO CUSTOMER-TEXT BUNGII SUPPORT":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions1());
                    action.click(updateStatusPage.Button_SupportSms());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), PropertyUtility.getMessage("driver.support.number"));
                    break;
                case "DUO DRIVER 1-CALL DRIVER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_CallDriver());
                    if(action.isAlertPresent()) {
                        validateCallButtonAction(String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_1_PHONE"))); //Commented Call validation as it doesnt open call app or alert on browserstack so if alert is not shown then its skipped
                    }
                    else
                    {
                        warning("Call alert with phone number should be shown","Call alert with phone number is not shown. but test will continue as Browserstack phones doesnt show call app");
                    }

                    break;
                case "DUO DRIVER 2-CALL DRIVER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_CallDriver());
                    if(action.isAlertPresent()) {
                        validateCallButtonAction(String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_2_PHONE"))); //Commented Call validation as it doesnt open call app or alert on browserstack so if alert is not shown then its skipped
                    }
                    else
                    {
                        warning("Call alert with phone number should be shown","Call alert with phone number is not shown. but test will continue as Browserstack phones doesnt show call app");
                    }
                    break;
                case "DUO DRIVER 1-TEXT DRIVER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_SmsDriver());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_1_PHONE")));
                    break;
                case "DUO DRIVER 2-TEXT DRIVER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_SmsDriver());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_2_PHONE")));
                    break;
                case "DUO DRIVER-TEXT BUNGII SUPPORT":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_SupportSms());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), PropertyUtility.getMessage("driver.support.number"));
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    private void validateViewImage(int image) {

        //  testStepVerify.isElementEnabled(updateStatusPage.Image_TripItem(),"Trip Item should be displayed");
        //testStepVerify.isTrue(action.getValueAttribute(updateStatusPage.PageIndicator_Page1()).equals("page 1 of 1"), "One image scrol slide should be present");
        testStepVerify.isElementDisplayed(updateStatusPage.Header_Item_Details(),"Item Details Header should display.","Item Details Header is display.","Item Details Header is not display.");
        testStepVerify.isTrue(action.getValueAttribute(updateStatusPage.PageIndicator_Page1()).equals("Photos"), "One image scrol slide should be present");
        //   testStepVerify.isElementEnabled(updateStatusPage.PageIndicator_Page1(),"Trip Item should be displayed");

        action.click(updateStatusPage.Button_CloseViewItems());
    }

    private void validateSMSNumber(String actualValue) {
        String expectedNumber = PropertyUtility.getMessage("twilio.number").replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "");
     //   boolean isMessagePage = isMessageAppPage();
        boolean isPhoneNumCorrect = actualValue.contains(expectedNumber);

/*

            testStepVerify.isTrue(isMessagePage,
                    "I should be navigated to SMS app", "I was navigate to sms app", "I was not navigated to sms app");
*/

            testStepVerify.isTrue(isPhoneNumCorrect,
                    "To Field should contains " + expectedNumber,
                    "To Field should contains " + expectedNumber + "and  actual value is" + actualValue,
                    "To Field should contains " + expectedNumber + "and  actual value is" + actualValue);

        action.click(messagesPage.Button_Cancel());
    }

    private void validateSMSNumber(String actualValue, String expectedValue) {
        String expectedNumber = expectedValue.replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "");
       // boolean isMessagePage = isMessageAppPage();
        boolean isPhoneNumCorrect = actualValue.contains(expectedNumber);

/*

            testStepVerify.isTrue(isMessagePage,
                    "I should be navigated to SMS app", "I was navigate to sms app", "I was not navigated to sms app");
*/

            testStepVerify.isTrue(isPhoneNumCorrect,
                    "To Field should contains " + expectedNumber,
                    "To Field should contains " + expectedNumber + "and  actual value is" + actualValue,
                    "To Field should contains " + expectedNumber + "and  actual value is" + actualValue);

        action.click(messagesPage.Button_Cancel());
    }

    private void validateCallButtonAction() {
        String iosVersion ="";
        if(SetupManager.BrowserStackLocal().equalsIgnoreCase("true")){
//            iosVersion = SetupManager.getBrowserStackOSVersion();
            iosVersion =  ((IOSDriver) SetupManager.getDriver()).getCapabilities().getCapability("os_version").toString();
            action.waitForAlert();
        } else {
            iosVersion = ((IOSDriver) SetupManager.getDriver()).getCapabilities().getCapability("platformVersion").toString();
        }
        if (!iosVersion.startsWith("10.")) {
            action.waitForAlert();
            String actualMessage = action.getAlertMessage().replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
                    .replace("?", "").replace("+", "").trim();
            actualMessage = actualMessage.substring(1, actualMessage.length() - 1);
            String expectedMessage = PropertyUtility.getMessage("twilio.number").replace("(", "").replace(")", "").replace(" ", "")
                    .replace("-", "").replace("+", "").trim();
            //    List<String> options = action.getListOfAlertButton();
            boolean isMessageCorrect = actualMessage.equals(expectedMessage);
            //   boolean isOptionsCorrect = options.contains("Cancel") && options.contains("Call");

            testStepVerify.isTrue(isMessageCorrect,
                    "I should be alerted to call twillo number", "Twillo number was displayed in alert message",
                    "Twillo number was not displayed in alert message , Actual message :" + actualMessage + " , Expected Message:" + PropertyUtility.getMessage("twilio.number"));

/*                testStepVerify
                        .isTrue(isOptionsCorrect,
                                "Alert should have option to cancel and call twilio number ",
                                "Alert  have option to cancel and call twilio number , options are" + options.get(0) + " and " + options.get(1),
                                "Alert dont have option to cancel and call twilio number");*/

            action.clickAlertButton("Cancel");
        } else {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //logger.detail("Pagesource" + SetupManager.getDriver().getPageSource());
            String actualMessage = updateStatusPage.CallNumeberValue_iOS10().getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
                    .replace("?", "").replace("+", "").trim();
            String expectedMessage = PropertyUtility.getMessage("twilio.number").replace("(", "").replace(")", "").replace(" ", "")
                    .replace("-", "").replace("+", "").trim();
            boolean isMessageCorrect = actualMessage.equals(expectedMessage);
            testStepVerify.isTrue(isMessageCorrect,
                    "I should be alerted to call twillo number", "Twillo number was displayed in alert message",
                    "Twillo number was not displayed in alert message , Actual message :" + actualMessage + " , Expected Message:" + PropertyUtility.getMessage("twilio.number"));
            if (action.isElementPresent(updateStatusPage.ButtonCancelCall_iOS10(true)))
                action.click(updateStatusPage.ButtonCancelCall_iOS10());
            action.click(updateStatusPage.EndCall_iOS10());
        }
    }


    private void validateCallButtonAction(String expectedNumber) {
        action.waitForAlert();
        String actualMessage = action.getAlertMessage().replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
                .replace("?", "").replace("+", "").trim();
        actualMessage = actualMessage.substring(1, actualMessage.length() - 1);
        String expectedMessage = expectedNumber.replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "").replace("+", "").trim();
   //     List<String> options = action.getListOfAlertButton();
        boolean isAlertMessageCorrect = actualMessage.equals(expectedMessage);
   //     boolean isOptionsCorrect = options.contains("Cancel") && options.contains("Call");


            testStepVerify.isTrue(isAlertMessageCorrect,
                    "I should be alerted to call twillo number", "Twillo number was displayed in alert message",
                    "Twillo number was not displayed in alert message , Actual message :" + actualMessage + " , Expected Message:" + expectedNumber);


/*            testStepVerify
                    .isTrue(isOptionsCorrect,
                            "Alert should have option to cancel and call twilio number ",
                            "Alert  have option to cancel and call twilio number , options are" + options.get(0) + " and " + options.get(1),
                            "Alert dont have option to cancel and call twilio number");*/

        action.clickAlertButton("Cancel");
    }

    private boolean validateUnloadingInfo(List<String> actualInfo) {
        logger.detail("INside trip info validation");
        String dropOffLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",", "").replace("Rd", "Road").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",", "").replace("Rd", "Road").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();
        //boolean isTagDisplayed = actualInfo.get(1).equals("DROP OFF LOCATION");
        String actualDropOfflocation = actualInfo.get(5).replace(",", "").replace("  ", " ");

        boolean isDropLocationDisplayed = actualDropOfflocation
                .contains(dropOffLocationLineOne) && actualDropOfflocation
                .contains(dropOffLocationLineTwo);

       // if (isTagDisplayed && isDropLocationDisplayed) {
            //removed pass statement to avoid multiple screenshot and log in result

       // } else {
        /*
            testStepVerify.isEquals(actualInfo.get(1), "DROP OFF LOCATION",
                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'DROP OFF LOCATION' Tag was not correctly displayed");


            testStepVerify.isEquals(actualInfo.get(5), (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"),

                    "DROP OFF location should be correctly displayed ",
                    "DROP OFF location was correctly displayed , actual was is " + actualDropOfflocation + "and expected is " + dropOffLocationLineOne + dropOffLocationLineTwo,
                    "DROP OFF location was not displayed correctly, actual was is " + actualDropOfflocation + " and expected is" + dropOffLocationLineOne + dropOffLocationLineTwo);

        //}
         */
        return isDropLocationDisplayed;
        //return isTagDisplayed && isDropLocationDisplayed;
    }

    private boolean validateEnRouteInfo(List<String> actualInfo) {
        logger.detail("Inside trip info validation");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",", "").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",", "").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();

        //boolean isTagDisplayed = actualInfo.get(5).equals("PICKUP LOCATION");

//        boolean isETACorrect = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("mins");
        String actualPickuplocation="";
        String tripType= (String) cucumberContextManager.getScenarioContext("TripType");
        if(tripType.equalsIgnoreCase("Duo")){
            actualPickuplocation = actualInfo.get(6).replace(",", "").replace("  ", " ");
        }else {
            actualPickuplocation = actualInfo.get(5).replace(",", "").replace("  ", " ");
        }
        boolean isPickUpDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) && actualPickuplocation.contains(pickUpLocationLineTwo);

       // if (isETACorrect && isPickUpDisplayed) {
            //removed pass statement to avoid multiple screenshot and log in result

        //} else {
            //testStepVerify.isTrue(isTagDisplayed, "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed", "'PICKUP LOCATION' Tag was not correctly displayed");
/*            testStepVerify.isTrue(isETACorrect,
                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

 */
            testStepVerify.isTrue(isPickUpDisplayed,
                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualPickuplocation + " and expected is " + pickUpLocationLineOne + pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualPickuplocation + " and expected is " + pickUpLocationLineOne + pickUpLocationLineTwo);
        //}
        //return isTagDisplayed && isETACorrect && isPickUpDisplayed;
        //return isETACorrect && isPickUpDisplayed;
        return isPickUpDisplayed;
    }

    private boolean validateDrivingInfo(List<String> actualInfo) {
        logger.detail("inside trip info validation");

        String dropOffLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",", "").replace("Rd", "Road").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",", "").replace("Rd", "Road").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();
        //boolean isTagDisplayed = actualInfo.get(1).equals("DROP OFF LOCATION");
        boolean isETAdisplayed = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("mins");
        String actualDropoffLocation = actualInfo.get(5).replace(",", "").replace("  ", " ");
        boolean isDropDisplayed = actualDropoffLocation.contains(dropOffLocationLineOne) && actualDropoffLocation.contains(dropOffLocationLineTwo);

       // if (isTagDisplayed && isETAdisplayed && isDropDisplayed) {
            //removed pass statement to avoid multiple screenshot and log in result

       // } else {
        /*
            testStepVerify.isTrue(isTagDisplayed,
                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'DROP OFF LOCATION' Tag was not correctly displayed");
            */

            testStepVerify.isTrue(isETAdisplayed,
                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

            testStepVerify.isTrue(isDropDisplayed,
                    "DROP OFF  location should be correctly displayed ",
                    "DROP OFF  location was correctly displayed , actual was is" + actualDropoffLocation + " and expected is " + dropOffLocationLineOne + dropOffLocationLineTwo,
                    "DROP OFF location was not displayed correctly, actual was is" + actualDropoffLocation + "and expected is" + dropOffLocationLineOne + dropOffLocationLineTwo);
        //}
        //return isTagDisplayed && isETAdisplayed && isDropDisplayed;
        return isETAdisplayed && isDropDisplayed;
    }

    private boolean validateArrivedInfo(List<String> actualInfo) {
        logger.detail("inside trip info validation");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",", "").replace("  ", " ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",", "").replace("  ", " ").trim();

       // boolean isTagDisplayed = actualInfo.get(1).equals("PICKUP LOCATION");
        String actualPickuplocation ="";
        String tripType= (String) cucumberContextManager.getScenarioContext("TripType");
        if(tripType.equalsIgnoreCase("Duo")) {
            actualPickuplocation = actualInfo.get(7).replace(",", "").replace("  ", " ");
        }
        else{
            actualPickuplocation = actualInfo.get(6).replace(",", "").replace("  ", " ");
        }
        boolean isPickupDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) && actualPickuplocation
                .contains(pickUpLocationLineTwo);
        //if (isTagDisplayed && isPickupDisplayed) {
            //removed pass statement to avoid multiple screenshot and log in result
        //} else {
        /*
            testStepVerify.isTrue(isTagDisplayed,
                    "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
                    "'PICKUP LOCATION' Tag was not correctly displayed");

         */

            testStepVerify.isTrue(isPickupDisplayed,
                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualPickuplocation + " and expected is " + pickUpLocationLineOne + pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualPickuplocation + "and expected is" + pickUpLocationLineOne + pickUpLocationLineTwo);
        //}
        //return isTagDisplayed && isPickupDisplayed;
        return isPickupDisplayed;
    }

    private boolean validateLoadingItemsInfo(List<String> actualInfo) {
        logger.detail("inside trip info validation");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",", "").replace("  ", " ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",", "").replace("  ", " ").trim();

        // boolean isTagDisplayed = actualInfo.get(1).equals("PICKUP LOCATION");
        String actualPickuplocation ="";
        String tripType= (String) cucumberContextManager.getScenarioContext("TripType");
        if(tripType.equalsIgnoreCase("Duo")) {
            actualPickuplocation = actualInfo.get(5).replace(",", "").replace("  ", " ");
        }
        else {
            actualPickuplocation = actualInfo.get(5).replace(",", "").replace("  ", " ");
        }
        boolean isPickupDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) && actualPickuplocation
                .contains(pickUpLocationLineTwo);
        //if (isTagDisplayed && isPickupDisplayed) {
        //removed pass statement to avoid multiple screenshot and log in result
        //} else {
        /*
            testStepVerify.isTrue(isTagDisplayed,
                    "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
                    "'PICKUP LOCATION' Tag was not correctly displayed");

         */

        testStepVerify.isTrue(isPickupDisplayed,
                "Pick up location should be correctly displayed ",
                "Pick up location was correctly displayed , actual was is" + actualPickuplocation + " and expected is " + pickUpLocationLineOne + pickUpLocationLineTwo,
                "Pick up location was not displayed correctly, actual was is" + actualPickuplocation + "and expected is" + pickUpLocationLineOne + pickUpLocationLineTwo);
        //}
        //return isTagDisplayed && isPickupDisplayed;
        return isPickupDisplayed;
    }



    @Then("^I should be navigated to \"([^\"]*)\" trip status screen$")
    public void iShouldBeNaviagatedToTripStatusScreen(String screen) {
        try {
            int activeStatus = 0;

            boolean pageFlag = false;
            if (screen.equalsIgnoreCase(Status.ARRIVED_AT_PICKUP.toString())) {
                pageFlag = isUpdatePage(Status.ARRIVED_AT_PICKUP.toString());
                activeStatus = 1;
            } else if (screen.equals(Status.EN_ROUTE_TO_PICKUP.toString())) {
                pageFlag = isUpdatePage(Status.EN_ROUTE_TO_PICKUP.toString());
                activeStatus = 0;
            } else if (screen.equals(Status.LOADING_ITEMS_AT_PICKUP.toString())) {
                pageFlag = isUpdatePage(Status.LOADING_ITEMS_AT_PICKUP.toString());
                activeStatus = 2;
            } else if (screen.equals(Status.DRIVING_TO_DROP_OFF.toString())) {
                pageFlag = isUpdatePage(Status.DRIVING_TO_DROP_OFF.toString());
                activeStatus = 3;
            } else if (screen.equals(Status.UNLOADING_ITEMS_AT_DROP_OFF.toString())) {
                pageFlag = isUpdatePage(Status.UNLOADING_ITEMS_AT_DROP_OFF.toString());
                activeStatus = 4;
            }

            boolean[] statusCheck = utility.checkStatusOnDriver();
            for (int i = 0; i < statusCheck.length; i++) {
                if (activeStatus == i) {
                    testStepVerify.isTrue(statusCheck[i], "I should be navigated to " + screen + "screen", screen + " screen icon is highlighted", screen + " screen icon is not highlighted");
                } else {
                    int screenNo = i + 1;
                    if (statusCheck[i])
                        testStepVerify.isFalse(statusCheck[i], "I should be navigated to " + screen + "screen", "Pickup status " + screenNo + " screen should not be highlighted", screenNo + " status should is highlighted");
                    else
                        log("Pickup status " + screenNo + " screen should not be highlighted", screenNo + " status should is not highlighted", false);
                }

            }
            testStepVerify.isTrue(pageFlag, "I should be navigated to " + screen + "screen", "I was not navigated to" + screen);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be navigated to \"([^\"]*)\" trip status screen on driver$")
    public void i_should_be_navigated_to_something_trip_status_screen_on_driver(String screen) throws Throwable {
        try{
            Thread.sleep(1000);
            if(action.isElementPresent(bungiiDetailsPage.Text_PickupInstructions(true))) {
                action.click(bungiiDetailsPage.Button_General_Instruction_Got_It());
            }
            testStepAssert.isElementDisplayed(updateStatusPage.Text_Header(screen),"I should be navigated to " + screen + "screen","I have navigated to " + screen + "screen","I have not navigated to " + screen + "screen");
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
        //throw new PendingException();
    }

    @Then("^I accept Alert message for \"([^\"]*)\"$")
    public void i_accept_alert_message_for_something(String strArg1) throws Throwable {
        action.waitForAlert();

        String actualText = action.getAlertMessage();
        String expectedText = "";
        switch (strArg1) {
            case "Reminder: both driver at pickup":
                expectedText = PropertyUtility.getMessage("bungii.duo.driver.pickup");
                testStepVerify.isEquals(actualText, expectedText, strArg1 + "should be displayed", expectedText + " is displayed", "Expect alert text is " + expectedText + " and actual is " + actualText);
                action.clickAlertButton("Initiate");
                break;
            case "Reminder: both driver at drop off":
                expectedText = PropertyUtility.getMessage("bungii.duo.driver.drop");
                testStepVerify.isEquals(actualText, expectedText, strArg1 + "should be displayed", expectedText + " is displayed", "Expect alert text is " + expectedText + " and actual is " + actualText);
                action.clickAlertButton("Complete");
                break;

        }
    }

    @And("^stack trip information should be displayed on deck$")
    public void stack_trip_information_should_be_displayed_on_deck() {
        try {
            //String customerName = (String) cucumberContextManager.getScenarioContext("LATEST_LOGGEDIN_CUSTOMER_NAME");
            //testStepVerify.isElementTextEquals(updateStatusPage.Text_NextLabel(), "NEXT CUSTOMER","'NEXT CUSTOMER' text lable should be displayed","'NEXT CUSTOMER' text lable is displayed","'NEXT CUSTOMER' text lable is not displayed");
            String OnDeckText= action.getText(updateStatusPage.Text_OnDeckLabel());
            boolean onDeck=false;
            if(OnDeckText.contains("Bungii on deck, try to finish up by"))
                onDeck=true;
            testStepVerify.isTrue(onDeck,"Bungii on deck, try to finish up by should be displayed","Bungii on deck, try to finish up by is not displayed.");

            //testStepVerify.isElementTextEquals(updateStatusPage.Text_OnDeckLabel(), "ON DECK","'ON DECK' text lable should be displayed","'ON DECK' text lable is displayed","'ON DECK' text lable is not displayed");
            //testStepVerify.isElementTextEquals(updateStatusPage.Text_StackCustomer(), customerName.substring(0, customerName.indexOf(" ") + 2));
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^stack trip information should not be displayed on deck$")
    public void stack_trip_information_should_not_be_displayed_on_deck() {
        try {
            testStepVerify.isElementNotEnabled(updateStatusPage.Text_NextLabel(true), "Next tag should not be enabled","Next tag is not displayed","Next tag is displayed");
            testStepVerify.isElementNotEnabled(updateStatusPage.Text_OnDeckLabel(true), "ON DECK should not be displayed" ,"'ON DECK' text lable is not displayed","ON DECK is displayed");
            testStepVerify.isElementNotEnabled(updateStatusPage.Text_StackCustomer(true),"stack Customer name should be not be diplayed","stack Customer name should be not be diplayed","stack Customer name is diplayed");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^try to finish time should be correctly displayed for long stack trip$")
    public void try_to_finish_time_should_be_correctly_displayed() throws Throwable {
        try{
        if(((String)cucumberContextManager.getScenarioContext("DRIVER_MIN_ARRIVAL")).equalsIgnoreCase(""))
        {
            String[] calculatedTime=utility.getTeletTimeinLocalTimeZone();
            cucumberContextManager.setScenarioContext("DRIVER_TELET",calculatedTime[0]);
            cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL",calculatedTime[1]);
            cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL",calculatedTime[2]);
        }
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        String expectedTime="";
        if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
            expectedTime = ((String)cucumberContextManager.getScenarioContext("DRIVER_TELET")) + "  "; //+ PropertyUtility.getDataProperties("browserstack.time.label");
        else
            expectedTime = ((String)cucumberContextManager.getScenarioContext("DRIVER_TELET")) + "  " + utility.getTimeZoneBasedOnGeofence();
       // expectedTime=expectedTime.replace("am", "AM").replace("pm","PM");
        expectedTime=expectedTime.replace("am", "").replace("pm","").replace("AM", "").replace("PM","").trim();
        String actualValue= action.getText(updateStatusPage.Text_StackInfo());
        testStepAssert.isTrue(actualValue.contains(expectedTime), "Try to finish by should be displayed","Try to finish by "+expectedTime+" is displayed", "Try to finish by "+expectedTime+ " is not displayed. instead "+ actualValue +"is displayed");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^try to finish time should be correctly displayed for short stack trip$")
    public void try_to_finish_time_should_be_correctly_displayed_ShortStack() throws Throwable {
        try {
            if (((String) cucumberContextManager.getScenarioContext("DRIVER_MIN_ARRIVAL")).equalsIgnoreCase("")) {
                String[] calculatedTime = utility.getTeletTimeinLocalTimeZone();
                cucumberContextManager.setScenarioContext("DRIVER_TELET", calculatedTime[0]);
                cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL", calculatedTime[1]);
                cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL", calculatedTime[2]);
            }
            String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
            String expectedTime = "";
            if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
                expectedTime = ((String) cucumberContextManager.getScenarioContext("DRIVER_FINISH_BY")) + " ";//+ PropertyUtility.getDataProperties("browserstack.time.label");
            else
                expectedTime = ((String) cucumberContextManager.getScenarioContext("DRIVER_FINISH_BY")) + " " + utility.getTimeZoneBasedOnGeofence();
            // expectedTime=expectedTime.replace("am", "AM").replace("pm","PM");
            expectedTime = expectedTime.replace("am", "").replace("pm", "").replace("AM", "").replace("PM", "").trim();
            String elementText = updateStatusPage.Text_StackInfo().getText();
            elementText = elementText.replace("  ", "").trim();
            logger.detail("Element Text" + elementText);
            testStepAssert.isTrue(elementText.contains(expectedTime), "Try to finish by should be displayed", "Try to finish by " + expectedTime + " is displayed", "Try to finish by " + expectedTime + " is not displayed");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I calculate projected driver arrival time$")
    public void i_calculate_projected_driver_arrival_time() throws Throwable {
        try{
        utility.calculateShortStack();
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @When("^I verify that driver to pickup time is greater than 100 mins for second trip$")
    public void i_verify_that_driver_to_pickup_time_is_greater_than_100_mins_for_second_trip() {
        try {
            String customer2PhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
            String driverPhoneNumber = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
            String custRef = DbUtility.getCustomerRefference(customer2PhoneNumber);
            String pickupID = DbUtility.getPickupID(custRef);
            String pickupRef = DbUtility.getPickupRef(customer2PhoneNumber);
            DbUtility.isDriverEligibleForTrip(driverPhoneNumber, pickupRef);
            int driverToPickUP = Integer.valueOf(DbUtility.getDriverToPickupTime(driverPhoneNumber, pickupID));

            testStepVerify.isTrue(driverToPickUP > 100, "Driver to pickp value should be greater that 100 ", "Driver to pickup value is " + driverToPickUP + " min", "Driver to pickup value is " + driverToPickUP + " min");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I click on the Duo teammate image$")
    public void i_click_on_the_duo_teammate_image() throws Throwable {
        try{
        Thread.sleep(5000);
        action.clickBy4Points(375,425,367,438);
        log("I should be able to click on duo teammate image","I could click on duo teammate image",false);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }

    }
    @Then("^I should see the driver vehicle information$")
    public void i_should_see_the_driver_vehicle_information() throws Throwable {
        try{
        boolean isVehicleModelDisplayed = tripDetailsPage.Text_DriverVehicleModel().isDisplayed();
        String VehicleModel = action.getText(tripDetailsPage.Text_DriverVehicleModel());
        testStepVerify.isTrue(isVehicleModelDisplayed,"Driver vehicle model " +VehicleModel +" should be displayed","Driver vehicle model " +VehicleModel +" is displayed","Driver vehicle model " +VehicleModel +" is not displayed" );
        boolean isVehicleLicenseNumberDisplayed = tripDetailsPage.Text_DriverVehicleLicenseNumber().isDisplayed();
        String VehicleLicenseNumber = action.getText(tripDetailsPage.Text_DriverVehicleLicenseNumber());
        testStepVerify.isTrue(isVehicleLicenseNumberDisplayed,"Driver vehicle licence number " +VehicleLicenseNumber +" should be displayed","Driver vehicle licence number " +VehicleLicenseNumber +" is displayed","Driver licence number " +VehicleLicenseNumber +" is not displayed" );
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

    @And("^I navigate back$")
    public void i_navigate_back() throws Throwable {
        try{
        action.clickBy2Points(201,265);
        log("I should be able to navigate back","I could navigate back",false);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }

    }
    @And("^I click on \"([^\"]*)\" icon$")
    public void i_click_on_something_icon(String icon) throws Throwable {
        try{
            switch (icon){
                case "$":
                    action.click(liveTripsPage.Icon_DollarSign());
                    break;
                case "Back":
                    action.click(liveTripsPage.Button_Back());
                    break;
                case "i earning":
                    action.click(liveTripsPage.Icon_EarningsInfo());
                    Thread.sleep(3000);
                    testStepVerify.isEquals(action.getText(liveTripsPage.Text_EarningsInfo()),PropertyUtility.getDataProperties("ios.earnings.alert.info"),
                            "Correct alert message should be displayed.",
                            "Correct alert message is displayed.",
                            "Correct alert message is not displayed.");
                    break;
                case "log-out":
                    action.click(safariPage.Icon_Logout());
                    break;
            }
            log("I should be able to click on the icon","I am able to click on the icon",false);

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The \"([^\"]*)\" \"([^\"]*)\" should be displayed$")
    public void the_something_something_should_be_displayed(String element, String strArg2) throws Throwable {
        try{
            String expectedText = "";
            switch (element){
                case "Delivery Details":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Header_DeliveryDetails()),"Delivery Details Header should be displayed","Delivery Details Header is displayed","Delivery Details Header is not displayed");
                    break;
                case "Delivery Instructions":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Icon_DeliveryInstructions()),"Delivery Instructions Icon should be displayed","Delivery Instructions Icon is displayed","Delivery Instructions Icon is not displayed");
                    break;
                case "Item Details":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Button_ViewItems()),"Item Details Icon should be displayed","Item Details Icon is displayed","Item Details Icon is not displayed");
                    break;
                case "Bungii Support":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Button_SupportSms()),"Bungii Support Icon should be displayed","Bungii Support Icon is displayed","Bungii Support Icon is not displayed");
                    break;
                case "More Options":
                    testStepAssert.isTrue(action.isElementPresent(driverUpdateStatusPage.Button_MoreOptions()),"More Options Icon should be displayed","More Options Icon is displayed","More Options Icon is not displayed");
                    break;
                case "Call":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Icon_Call()),"Call Icon should be displayed","Call Icon is displayed","Call Icon is not displayed");
                    break;
                case "Text":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Icon_Text()),"Phone Icon should be displayed","Phone Icon is displayed","Phone Icon is not displayed");
                    break;
                case "Pickup":
                    testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Icon_Pickup()),"Pickup Icon should be displayed","Pickup Icon is displayed","Pickup Icon is not displayed");
                    break;
                case "Dropoff":
                    testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Icon_DropOff()),"Dropoff Icon should be displayed","Dropoff Icon is displayed","Dropoff Icon is not displayed");
                    break;
                case "Photos":
                    int noOfPhotos=bungiiDetailsPage.List_Photos().size();
                    testStepAssert.isTrue(noOfPhotos==3,
                            "The photos added by driver should be displayed.",
                            "The photos added by driver are not displayed.");
                    break;
                case "Contact Duo Teammate":
                    By Text_ContactDuo = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"Contact DUO\"]");
                    By Text_TeamMate = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"Teammate\"]");
                    testStepAssert.isTrue(action.waitForExpectedElementToBeDisplayed(Text_ContactDuo),"Contact Duo text should be displayed","Contact Duo text is displayed","Contact Duo text is not displayed");
                    Thread.sleep(7000);
                    testStepAssert.isTrue(action.waitForExpectedElementToBeDisplayed(Text_TeamMate),"Teammate text should be displayed","Teammate text is displayed","Teammate text is not displayed");
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
                    action.swipeDown();
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Label_Pickup()),"Pickup label should be displayed","Pickup label is displayed","Pickup label is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Label_ArrivalTime()),"Arrival time label should be displayed","Arrival time label is displayed","Arrival time label is not displayed");

                    String pickupText = action.getText(updateStatusPage.Label_Pickup());
                    String arrivalTimeText = action.getText(updateStatusPage.Label_ArrivalTime());
                    String completeText =pickupText +arrivalTimeText;
                    testStepVerify.isEquals(completeText, element, element + " Text should be displayed", element + " Text is displayed", completeText + "is displayed");
                    if(strArg2.equals("at Enroute screen")||strArg2.equals("at Arrival screen")||strArg2.equals("at Loading Items screen")){
                        String arrivalTimeOnUIValue = action.getText(updateStatusPage.Text_ArrivalTimeForDifferentStates());
                        cucumberContextManager.setScenarioContext("ArrivalTimeFromUI",arrivalTimeOnUIValue.substring(0,arrivalTimeOnUIValue.length()-3));
                    }
                    else {
                        String arrivalTimeOnUIValue = action.getText(updateStatusPage.Text_ArrivalTimeValue());
                        cucumberContextManager.setScenarioContext("ArrivalTimeFromUI",arrivalTimeOnUIValue.substring(0,arrivalTimeOnUIValue.length()-3));

                    }
                    String arrivalTimeOnUI = (String) cucumberContextManager.getScenarioContext("ArrivalTimeFromUI");
                    String properArrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");
                    testStepAssert.isEquals(properArrivalTime, arrivalTimeOnUI,"The arrival time should be "+properArrivalTime,
                            "The arrival time is "+properArrivalTime,"The arrival is not "+properArrivalTime+" ,The time is "+properArrivalTime);
                    break;
                case "DROP-OFF(Expected time)":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Label_DropOff()),"Dropoff label should be displayed","Dropoff label is displayed","Dropoff label is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Label_ExpectedTime()),"Expected time label should be displayed","Expected time label is displayed","Expected time label is not displayed");

                    String dropOffText = action.getText(updateStatusPage.Label_DropOff());
                    String expectedTimeText = action.getText(updateStatusPage.Label_ExpectedTime());
                    String entireText =dropOffText +expectedTimeText;
                    testStepAssert.isEquals(entireText, element, element + " Text should be displayed", element + " Text is displayed", entireText + "is displayed");
                    if(strArg2.equals("at Unloading Items screen")){
                        String expectedTimeForDropOff = action.getText(updateStatusPage.Text_ArrivalTimeForDifferentStates());

                        cucumberContextManager.setScenarioContext("DropOffFromUI",expectedTimeForDropOff);
                    }
                    else {
                        String expectedTimeForDropOff = action.getText(updateStatusPage.Text_ArrivalTimeValue());
                        cucumberContextManager.setScenarioContext("DropOffFromUI",expectedTimeForDropOff);

                    }
                    String dropOffRangeOnUIValue =(String) cucumberContextManager.getScenarioContext("DropOffFromUI");
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
                case "Bungii: The Ultimate Side Hustle":
                    expectedText = PropertyUtility.getMessage("driver.navigation.bungii.the.ultimate.side.hustle");
                    testStepAssert.isTrue(action.isElementPresent(driverforgotPasswordPage.Label_BungiiTheUltimateSideHustle()),expectedText+" should be displayed",expectedText+" is displayed",expectedText+" is not displayed");
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
                case "Hold steady and center the barcode to scan. You need to scan any one item to proceed":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Text_BarCodeScanningInstructions()),"Instructions should be displayed","Instructions  are  displayed","Instructions are not displayed");
                    String arrivalStateInstructions=action.getText(updateStatusPage.Text_BarCodeScanningInstructions());
                    System.out.println(arrivalStateInstructions.replace("\n",""));
                    System.out.println(element);
                    testStepAssert.isEquals(arrivalStateInstructions.replace("\n",""),element,element +" Text should be displayed ",
                            element +" Text is displayed ",
                            element +" Text is not displayed ");
                    break;
                case "Please take photos and scan item(s) barcode before loading, just ‘slide to load items’ and follow the prompts.":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Text_NotificationTextOnLoadingItemsForBarCode()),"Notification should be displayed","Notification is displayed","Notification is not displayed");
                    String notificationText = action.getText(updateStatusPage.Text_NotificationTextOnLoadingItemsForBarCode());
                    testStepAssert.isEquals(notificationText,element,element +" Text should be displayed ",
                            element +" Text is displayed ",
                            element +" Text is not displayed ");
                    break;
                case "Please take photos and scan item(s) barcode after unloading, just ‘slide to complete Bungii’ and follow the prompts":
                    testStepAssert.isTrue(action.isElementPresent(updateStatusPage.Text_NotificationTextOnUnloadingForBarCode()),"Notification should be displayed","Notification is displayed","Notification is not displayed");
                    String notificationMessage = action.getText(updateStatusPage.Text_NotificationTextOnUnloadingForBarCode());
                    testStepAssert.isEquals(notificationMessage,element,element +" Text should be displayed ",
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

    public boolean isMessageAppPage() {
        action.textToBePresentInElementName(updateStatusPage.Text_NavigationBar(), PropertyUtility.getMessage("messages.navigation.new"));
        return action.getNameAttribute(updateStatusPage.Text_NavigationBar()).equals(PropertyUtility.getMessage("messages.navigation.new"));
    }

    /**
     * Slide the slider to update status
     */
    public void updateStatus() {
        //get locator rectangle is time consuming process
/*        if (initial == null)
            initial = action.getLocatorRectangle(updateStatusPage.AreaSlide());*/
/*        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));*/


        WebElement slider = updateStatusPage.AreaSlide();
        Rectangle initial;
        if (!utility.isSliderValueContainsInContext("DRIVER")) {
            initial = action.getLocatorRectangle(slider);
            utility.addSliderValueToFeatureContext("DRIVER", initial);

        } else {
            initial = utility.getSliderValueFromContext("DRIVER");
        }

        action.dragFromToForDuration(0, 0, initial.getWidth(), initial.getHeight(), 1, slider);
    }

    /**
     * Check if active page is update page.
     *
     * @return return comparison result navigation header to expected msg from
     * property
     */
    public boolean isUpdatePage(String pageName) {
        action.textToBePresentInElementName(updateStatusPage.Text_NavigationBarScreen(pageName), pageName);
        return action.getScreenHeader(updateStatusPage.Text_NavigationBarScreen(pageName)).equals(pageName);

    }

    /**
     * Cancel accepted bungii
     */
    public void cancelAcceptedBungii() {
        action.click(updateStatusPage.Button_Cancel());
    }

    /**
     * Get text on slider box
     *
     * @return value of slider box
     */
    public String getSliderText() {
        return action.getValueAttribute(updateStatusPage.AreaSlide());
    }

    /**
     * Click call to customer
     */
    public void clickCallToCustomer() throws InterruptedException{
        action.click(updateStatusPage.Button_Call());
        //Thread.sleep(2000);

    }

    /**
     * Click SMS to customer
     */
    public void clickSMSToCustomer() throws InterruptedException{

        action.click(updateStatusPage.Button_Sms());
        Thread.sleep(2000);

    }

    /**
     * Click SMS to Bungii
     */
    public void clickSMSToSupport() throws InterruptedException{
        action.click(updateStatusPage.Button_SupportSms());
        Thread.sleep(2000);

    }

    /**
     * Click View Items
     */
    public void clickViewItems() throws InterruptedException {
        action.click(updateStatusPage.Button_ViewItems());
        Thread.sleep(2000);

    }

    /**
     * Get Customer Name
     *
     * @return value of customer name
     */
    public String getCustomerName() {
        String customerName= getTripInformation().get(4);
        return action.getNameAttribute(updateStatusPage.Text_Customer());
    }

    /**
     * Get trip information that is displayed below status icon
     *
     * @return List of string containing trip information
     */
    public List<String> getTripInformation() {
        List<String> details = new ArrayList<>();
        List<WebElement> textInfo = updateStatusPage.Text_Info();
        for (WebElement info : textInfo) {
            details.add(action.getValueAttribute(info));
        }
        return details;
    }

    /**
     * Get name information that is displayed in driver app
     *
     * @return customer name string containing trip information in driver app
     */
    public String getCustomerNameOnDriverApp(int i) throws InterruptedException {
        List<String> details = new ArrayList<>();
        List<WebElement> textInfo = updateStatusPage.Text_Info();
        Thread.sleep(2000);
        for (WebElement info : textInfo) {
            details.add(action.getValueAttribute(info));
        }
        String CustName= details.get(i);
        return CustName;
    }

    /**
     * Verify if image status is displayed or not
     *
     * @param key Identifier for image, Key with same name should be present in image.properties
     * @return
     */
    public boolean verifyStatus(String key) {

        return action.verifyImageIsPresent(key);
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

}
