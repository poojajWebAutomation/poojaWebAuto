package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.android.pages.customer.SetPickupTimePage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.enums.Status;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.UpdateStatusPage;
import com.bungii.ios.pages.other.MessagesPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;


public class TripStatusSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(TripStatusSteps.class);
    MessagesPage messagesPage;
    ActionManager action = new ActionManager();
    private UpdateStatusPage updateStatusPage;
    GeneralUtility utility = new GeneralUtility();

    public TripStatusSteps(UpdateStatusPage updateStatusPage, MessagesPage messagesPage) {
        this.updateStatusPage = updateStatusPage;
        this.messagesPage = messagesPage;
    }

    @Then("^Customer should be navigated to \"([^\"]*)\" trip status screen$")
    public void customerShouldBeNaviagatedToTripStatusScreen(String screen) {
        try {
            int activeStatus=0;

            if (action.isAlertPresent()) {
                String alertMessage = action.getAlertMessage();
                List<String> getListOfAlertButton = action.getListOfAlertButton();
                if (alertMessage.contains("we are not operating in your area")) {
                    if (getListOfAlertButton.contains("Done")) {
                        action.clickAlertButton("Done");
                    }
                }
            }

            boolean pageFlag = false;
            if (screen.equalsIgnoreCase(Status.ARRIVED.toString())){
                pageFlag = isUpdatePage(Status.ARRIVED.toString());activeStatus=1;}
            else if (screen.equals(Status.EN_ROUTE.toString())){
                pageFlag = isUpdatePage(Status.EN_ROUTE.toString());activeStatus=0;}
            else if (screen.equals(Status.LOADING_ITEMS.toString())){
                pageFlag = isUpdatePage(Status.LOADING_ITEMS.toString());activeStatus=2;}

            else if (screen.equals(Status.DRIVING_TO_DROPOFF.toString())){
                pageFlag = isUpdatePage(Status.DRIVING_TO_DROPOFF.toString());activeStatus=3;}
            else if (screen.equals(Status.UNLOADING_ITEMS.toString())){
                pageFlag = isUpdatePage(Status.UNLOADING_ITEMS.toString());activeStatus=4;}

            boolean[] statusCheck=utility.checkStatusOnCustomer();
            for(int i=0;i<statusCheck.length;i++){
                if(activeStatus==i){
                    Thread.sleep(5000);
                    testStepVerify.isTrue(statusCheck[i],"I should be navigated to " + screen + " screen", screen + " screen icon is highlighted",screen + " screen icon is not highlighted");
                }else {
                    int screenNo=i+1;
                    if(statusCheck[i])
                        testStepVerify.isFalse(statusCheck[i],"I should be navigated to " + screen + " screen","Pickup status "+screenNo+" screen should not be highlighted",screenNo+" status should is highlighted");
                    else
                        log("Pickup status "+screenNo+" screen should not be highlighted",screenNo+" status should is not highlighted",false);
                }

            }
            testStepVerify.isTrue(pageFlag ,"I should be navigated to " + screen + " screen", "I didnt navigate to " + screen);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Trip Information should be correctly displayed on \"([^\"]*)\" status screen for customer$")
    public void trip_information_should_be_correctly_displayed_on_something_status_screen_for_customer(String key) {
        try {
            boolean isInfoDisplayed = false;

            logger.detail("before driver name");

            String expectedDriverName = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            expectedDriverName = expectedDriverName.substring(0, expectedDriverName.indexOf(" ") + 2);
            boolean isDriverDisplayed = getCustomerName().replace("  "," ").equals(expectedDriverName);
            logger.detail("after driver name");

            switch (key) {
                case "EN ROUTE":
                    validateEnRouteInfo(getTripInformation());
                    break;
                case "ARRIVED":
                    validateArrivedInfo(getTripInformation(), key);
                    break;
                case "LOADING ITEMS":
                    validateArrivedInfo(getTripInformation(), key);
                    break;
                case "DRIVING TO DROP-OFF":
                    validateDrivingInfo(getTripInformation());
                    break;
                case "UNLOADING ITEMS":
                    validateUnloadingInfo(getTripInformation());
                    break;
                default:
                    break;
            }

            if (/*isInfoDisplayed && */isDriverDisplayed) {
                pass(
                        "Trip Information should be correctly displayed and driver name :" + expectedDriverName
                                + "should be displayed",
                        "Trip Information is correctly displayed and driver name :" + expectedDriverName
                                + "is displayed correctly");
            } else {
                fail(
                        "Trip Information should be correctly displayed and driver name :" + expectedDriverName
                                + "should be displayed",
                        "Trip Information is not correctly displayed and driver name :" + getCustomerName().replace("  "," ")
                                + "is displayed correctly");

            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    private boolean validateDrivingInfo(List<String> actualInfo) {
        logger.detail("customer trip info");
        String dropOffLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String actualDropOfflocation=actualInfo.get(2).replace(",","").replace("  "," ");

        boolean isTagDisplayed = actualInfo.get(1).equals("DROP OFF LOCATION"),
                isEtaDisplayed = actualInfo.get(3).contains("ETA:") && actualInfo.get(3).contains("mins"),
                //country is not displayed now
                isDropLocationDisplayed = actualDropOfflocation
                        .contains(dropOffLocationLineOne) &&actualDropOfflocation
                        .contains(dropOffLocationLineTwo);

        if (isTagDisplayed && isEtaDisplayed && isDropLocationDisplayed) {
            //removed pass statement to avoid multiple screenshot and log in result
        } else {
            testStepVerify.isTrue(isTagDisplayed,

                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'DROP OFF LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isEtaDisplayed,
                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

            testStepVerify.isTrue(isDropLocationDisplayed,

                    "DROP OFF location should be correctly displayed ",
                    "DROP OFF location was correctly displayed , actual was is" + actualDropOfflocation + "and expected is"
                            + dropOffLocationLineOne+dropOffLocationLineTwo,
                    "DROP OFF location was not displayed correctly, actual was is" + actualDropOfflocation
                            + "and expected is" + dropOffLocationLineOne+dropOffLocationLineTwo);
        }
        return isTagDisplayed && isEtaDisplayed && isDropLocationDisplayed;
    }

    private boolean validateUnloadingInfo(List<String> actualInfo) {
        logger.detail("customer trip info");
        SetupManager.getDriver().getPageSource();
        String dropOffLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String actualDropOfflocation=actualInfo.get(2).replace(",","").replace("  "," ");

        boolean isTagDisplayed = actualInfo.get(1).equals("DROP OFF LOCATION"),
                isDropDisplayed = actualDropOfflocation.contains(dropOffLocationLineOne) &&actualDropOfflocation.contains(dropOffLocationLineTwo);

        if (isTagDisplayed && isDropDisplayed) {
            //removed pass statement to avoid multiple screenshot and log in result
        } else {
            testStepVerify.isTrue(isTagDisplayed,

                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'DROP OFF LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isDropDisplayed,

                    "DROP OFF location should be correctly displayed ",
                    "DROP OFF location was correctly displayed , actual was is" + actualDropOfflocation + "and expected is"
                            + dropOffLocationLineOne+dropOffLocationLineTwo,
                    "DROP OFF location was not displayed correctly, actual was is" + actualDropOfflocation
                            + "and expected is" + dropOffLocationLineOne+dropOffLocationLineTwo);
        }
        return isTagDisplayed && isDropDisplayed;
    }

    private boolean validateEnRouteInfo(List<String> actualInfo) {
        logger.detail("customer trip info");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",","").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",","").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        boolean isTagDisplayed = actualInfo.get(1).equals("PICKUP LOCATION"),
                isEtaCorrect = actualInfo.get(3).contains("ETA:") && actualInfo.get(3).contains("mins");
        String pickUpValue=actualInfo.get(2).replace(",","").replace("  "," ");
        boolean isPickUpCorrect = pickUpValue.contains(pickUpLocationLineOne) &&pickUpValue.contains(pickUpLocationLineTwo);
        if (isTagDisplayed && isEtaCorrect && isPickUpCorrect) {
            //removed pass statement to avoid multiple screenshot and log in result
        } else {
            testStepVerify.isTrue(isTagDisplayed,

                    "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
                    "'PICKUP LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isEtaCorrect,

                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

            testStepVerify.isTrue(isPickUpCorrect,

                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + pickUpValue + "and expected is"
                            + pickUpLocationLineOne+pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + pickUpValue
                            + "and expected is" + pickUpLocationLineOne+pickUpLocationLineTwo);
        }

        return isTagDisplayed && isEtaCorrect && isPickUpCorrect;
    }

    private boolean validateArrivedInfo(List<String> actualInfo, String screen) {
        logger.detail("customer trip info");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",","").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",","").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String actualPickuplocation=actualInfo.get(2).replace(",","").replace("  "," ");

        boolean isTagDisplayed = actualInfo.get(1).equals("PICKUP LOCATION"),
                pickUpCorrect = actualPickuplocation.contains(pickUpLocationLineOne) &&actualPickuplocation.contains(pickUpLocationLineTwo);
        if (isTagDisplayed && pickUpCorrect) {
            //removed pass statement to avoid multiple screenshot and log in result
        } else {
            testStepVerify.isTrue(isTagDisplayed,

                    "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
                    "'PICKUP LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(pickUpCorrect,

                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualPickuplocation + "and expected is"
                            + pickUpLocationLineOne+pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualPickuplocation
                            + "and expected is" +  pickUpLocationLineOne+pickUpLocationLineTwo);
        }
        return isTagDisplayed && pickUpCorrect;
    }

    @Then("^correct details should be displayed to customer on \"([^\"]*)\" app$")
    public void correct_details_should_be_displayed_to_customer_on_something_app(String key) {
        try {
            switch (key.toUpperCase()) {
                case "SMS":
                    clickSMSToDriver();

                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()));
                    break;
                case "CALL":
                    clickCallToDriver();
                    if(action.isAlertPresent())
                    validateCallButtonAction();// purposely checked above to skip for bs as sometimes it doesnt show
                    else
                        warning("Call app should be shown", "Call app is not shown on browserstack");
                    break;
                default:
                    throw new Exception("UN IMPLEMENTED STEPS");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^correct support details should be displayed to customer on \"([^\"]*)\" app$")
    public void correct_support_details_should_be_displayed_to_customer_on_something_app(String key) {
        try {
            switch (key.toUpperCase()) {
                case "SMS":
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()),PropertyUtility.getMessage("scheduled.support.number"));
                    break;
                case "ADMIN-SMS":
                    DriverManager.getObject().closeAllDriverInstanceExceptOriginal();
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()),PropertyUtility.getMessage("customer.scheduled.cancel.support.number"));
                    break;
                case "CALL":
                    validateCallButtonAction(PropertyUtility.getMessage("scheduled.support.number"));
                    break;
                default:
                    throw new Exception("UN IMPLEMENTED STEPS");
            }
            log("correct support details should be displayed to customer on "+key+" app","correct support details is displayed to customer on "+key+" app", false );
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in viewing SMS/Call on ios app ", true);
        }
    }
    @Then("^correct details should be displayed to customer for \"([^\"]*)\"$")
    public void correct_details_should_be_displayed_to_customer_for_something_app(String key) throws Throwable {
        try {
            switch (key.toUpperCase()) {
                case "DUO DRIVER 1-CALL DRIVER":
                    action.click(updateStatusPage.Button_DuoMoreOptions1());
                    action.click(updateStatusPage.Button_CallDriver());
                    if(action.isAlertPresent())
                        validateCallButtonAction(PropertyUtility.getMessage("twilio.number"));// purposely checked above to skip for bs as sometimes it doesnt show
                    else
                        warning("Call app should be shown", "Call app is not shown on browserstack");

                    break;
                case "DUO DRIVER 1-TEXT DRIVER":
                    action.click(updateStatusPage.Button_DuoMoreOptions1());
                    action.click(updateStatusPage.Button_SmsDriver());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()),PropertyUtility.getMessage("twilio.number"));
                    break;
                case "DUO DRIVER 2-CALL DRIVER":
                    action.click(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_CallDriver());
                    if(action.isAlertPresent())
                        validateCallButtonAction(PropertyUtility.getMessage("twilio.number.driver2"));// purposely checked above to skip for bs as sometimes it doesnt show
                    else
                        warning("Call app should be shown", "Call app is not shown on browserstack");

                    break;
                case "DUO DRIVER 2-TEXT DRIVER":
                    action.click(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_SmsDriver());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()),PropertyUtility.getMessage("twilio.number.driver2"));
                    break;
                case "CUSTOMER SUPPORT-SMS":
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()),PropertyUtility.getMessage("driver.support.number"));
                    break;
                default:
                    throw new Exception("UN IMPLEMENTED STEPS");
            }
            log("correct support details should be displayed to customer on "+key+" app","correct support details is displayed to customer on "+key+" app", false );

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }    }

    @When("^I click \"([^\"]*)\" on bungii accepted screen$")
    public void i_click_something_on_bungii_accepted_screen(String button) throws Throwable {
        try {
            List<String> getListOfAlertButton;
            switch (button) {
                case "CANCEL BUNGII":
                    action.click(updateStatusPage.Button_CancelBungii());
                    break;
                case "Cantact Support on Alert message":
                    getListOfAlertButton = action.getListOfAlertButton();
                    if (getListOfAlertButton.contains("Contact Customer Support"))
                        action.clickAlertButton("Contact Customer Support");
                    else
                        fail("I should able to click dismiss","I was able not able to find Contact Customer Support, list of alert button"+getListOfAlertButton.toString());
                    break;

                case "CANCEL BUNGII on Alert message":

                    getListOfAlertButton = action.getListOfAlertButton();
                    if (getListOfAlertButton.contains("Cancel Bungii"))
                        action.clickAlertButton("Cancel Bungii");
                    else
                        fail("I should able to click Cancel Bungii","I was able not able to find Cancel Bungii, list of alert button"+getListOfAlertButton.toString());

                    break;
                case"Dismiss on Alert message":
                    getListOfAlertButton = action.getListOfAlertButton();
                    if (getListOfAlertButton.contains("Dismiss"))
                        action.clickAlertButton("Dismiss");
                    else
                        fail("I should able to click dismiss","I was able not able to find Dismiss, list of alert button"+getListOfAlertButton.toString());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I tap on" + button, "I tapped on actionItem"+button, true);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in clicking button "+button, true);

        }    }


    @Then("^I see \"([^\"]*)\" on bungii accepted screen$")
    public void     i_see_something_on_bungii_accepted_screen(String strArg1) throws Throwable {
        try {
            action.waitForAlert();
            switch (strArg1) {
                case "Alert: Bungii cancel confirmation":
                    testStepVerify.isEquals( action.getAlertMessage(),PropertyUtility.getMessage("customer.stack.cancel.confirm.alert"));
                    break;
                case "Alert: Bungii cancel sucessfully":
                    Thread.sleep(5000);
                    testStepVerify.isEquals( action.getAlertMessage(),PropertyUtility.getMessage("customer.stack.cancel.success.alert"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in viewing alert messages : " + strArg1, true);

        }
    }

    @Then("^ratting should be correctly displayed on Bungii progress page$")
    public void ratting_should_be_correctly_displayed_on_bungii_progress_page() throws Throwable {
        try {
            String    driverPhoneNumber=(String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");

            String ratingString = DbUtility.getDriverRating(driverPhoneNumber);
            cucumberContextManager.setScenarioContext("DRIVER_CURRENT_RATTING",ratingString);
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(ratingString));
            int ratingInt = bigDecimal.intValue();
            BigDecimal ratingDecimal = bigDecimal.subtract(new BigDecimal(ratingInt));

            System.out.println("ratingString: " + ratingString);
            System.out.println("Integer Part: " + ratingInt);
            System.out.println("Decimal Part: " + ratingDecimal);

            updateStatusPage.WaitUntilElementIsDisplayed(By.xpath("//XCUIElementTypeButton[@name=\"rating filled star icon\"])"));

            int filledStarCount = updateStatusPage.FilledStars().size();
            int HalfFilledStarCount = updateStatusPage.HalfFilledStar().size();

            testStepVerify.isEquals(filledStarCount, ratingInt);

            if (ratingDecimal.doubleValue() >= 0.5) {
                testStepVerify.isEquals(HalfFilledStarCount, 1);
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    private void validateSMSNumber(String actualValue) {

        String expectedNumber = PropertyUtility.getMessage("twilio.number").replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "");
        boolean /*isMessagePage = isMessageAppPage(), */isNumberCorrect = actualValue.contains(expectedNumber);
/*
            testStepVerify.isTrue(isMessagePage,
                    "I should be navigated to SMS app", "I was navigate to sms app", "I was not navigated to sms app");
*/

            testStepVerify.isTrue(isNumberCorrect,
                    "To Field should contains " + expectedNumber,
                    "To Field should contains " + expectedNumber + "and  actual value is" + actualValue,
                    "To Field should contains " + expectedNumber + "and  actual value is" + actualValue);
        action.click(messagesPage.Button_Cancel());
    }
    private void validateSMSNumber(String actualValue,String expectedValue) {
        String expectedNumber = expectedValue.replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "");
       /* boolean isMessagePage = isMessageAppPage();*/
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
        try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
    }

    private void validateCallButtonAction() {
        action.waitForAlert();
        String actualMessage = action.getAlertMessage().replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
                .replace("?", "").replace("+", "").trim();
        actualMessage = actualMessage.substring(1, actualMessage.length() - 1);
        String expectedMessage = PropertyUtility.getMessage("twilio.number").replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "").replace("+", "").trim();
   //     List<String> options = action.getListOfAlertButton();

        boolean isMessageCorrect = actualMessage.equals(expectedMessage)/*,isOptionCorrect = options.contains("Cancel") && options.contains("Call")*/;


            testStepVerify.isTrue(isMessageCorrect,
                    "I should be alerted to call twillo number", "Twillo number was displayed in alert message",
                    "Twillo number was not displayed in alert message , Actual message :" + actualMessage
                            + " , Expected Message:" + PropertyUtility.getMessage("twilio.number"));
/*
            testStepVerify
                    .isTrue(isOptionCorrect,
                            "Alert should have option to cancel and call twilio number ",
                            "Alert  have option to cancel and call twilio number , options are" + options.get(0)
                                    + " and " + options.get(1),
                            "Alert dont have option to cancel and call twilio number");*/

        action.clickAlertButton("Cancel");
    }
    private void validateCallButtonAction(String expectedNumber) {
        action.waitForAlert();
        String actualMessage = action.getAlertMessage().replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
                .replace("?", "").replace("+", "").trim();
        logger.detail("Number : "+ actualMessage );
        actualMessage = actualMessage.substring(1, actualMessage.length() - 1);
        String expectedMessage = expectedNumber.replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "").replace("+", "").trim();
     //   List<String> options = action.getListOfAlertButton();

        boolean isMessageCorrect = actualMessage.equals(expectedMessage)/*,isOptionCorrect = options.contains("Cancel") && options.contains("Call")*/;


            testStepVerify.isTrue(isMessageCorrect,
                    "I should be alerted to call twillo number", "Twillo number was displayed in alert message",
                    "Twillo number was not displayed in alert message , Actual message :" + actualMessage
                            + " , Expected Message:" + PropertyUtility.getMessage("twilio.number"));

/*            testStepVerify
                    .isTrue(isOptionCorrect,
                            "Alert should have option to cancel and call twilio number ",
                            "Alert  have option to cancel and call twilio number , options are" + options.get(0)
                                    + " and " + options.get(1),
                            "Alert dont have option to cancel and call twilio number");*/

        action.clickAlertButton("Cancel");
    }

    /**
     * Check if active page is support page
     *
     * @param pageName Page name of update status page
     * @return return comparison result navigation header to expected msg from
     * property
     */
    public boolean isUpdatePage(String pageName) {
        action.textToBePresentInElementName(updateStatusPage.Text_NavigationBar(), pageName);
        return action.getScreenHeader(updateStatusPage.Text_NavigationBar()).equals(pageName);

    }

    /**
     * Get Current bungii customer name
     *
     * @return Name of customer
     */
    public String getCustomerName() {
        return action.getValueAttribute(updateStatusPage.Text_DriverName());
    }

    /**
     * Get trip information , Information while is below status icon , ex drop location etc
     *
     * @return Get trip information
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
     * Check If status image is displayed or not
     *
     * @param key Identifier for image, there should be key in image.properties with same name
     * @return boolean value if image is present or not
     */
    public boolean verifyStatus(String key) {
        return action.verifyImageIsPresent(key);
    }

    /**
     * Click call button to driver
     */
    public void clickCallToDriver() {
        action.click(updateStatusPage.Button_Call());
    }

    /**
     * Click sms button to driver
     */
    public void clickSMSToDriver() {
        action.click(updateStatusPage.Button_Sms());
        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
    }

    public boolean isMessageAppPage() {
        action.textToBePresentInElementName(updateStatusPage.Text_NavigationBar(), PropertyUtility.getMessage("messages.navigation.new"));
        return action.getScreenHeader(updateStatusPage.Text_NavigationBar()).equals(PropertyUtility.getMessage("messages.navigation.new"));
    }

}
