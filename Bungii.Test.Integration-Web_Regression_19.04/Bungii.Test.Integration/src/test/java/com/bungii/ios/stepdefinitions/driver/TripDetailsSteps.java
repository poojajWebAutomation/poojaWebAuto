package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.pages.driver.TripDetailsPage;
import com.bungii.ios.pages.driver.UpdateStatusPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.*;


public class TripDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(TripDetailsSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility GeneralUtility = new GeneralUtility();
    private TripDetailsPage tripDetailsPage;
    private UpdateStatusPage updateStatusPage;
    private ScheduledTripsPage scheduledTripsPage;


    public TripDetailsSteps(TripDetailsPage tripDetailsPage,UpdateStatusPage updateStatusPage,ScheduledTripsPage scheduledTripsPage) {
        this.tripDetailsPage = tripDetailsPage;
        this.updateStatusPage = updateStatusPage;
        this.scheduledTripsPage = scheduledTripsPage;
    }

    @When("^I accept selected Bungii$")
    public void i_accept_selected_bungii() {
        try {
            if (action.isAlertPresent()) {
                SetupManager.getDriver().switchTo().alert().dismiss();
                Thread.sleep(1000);
            }
            AcceptBungii();
            log("Bungii should be Bungii", "Bungii is sucessfully accepted");
        } catch (Exception e) {
            error("Bungii should be Bungii", "Error occured while accepting bungii" + e.getMessage());
        }

    }
    @And("^I check if variable sign is shown under \"([^\"]*)\"$")
    public void i_check_if_variable_sign_is_shown_under_something(String page) throws Throwable {
        try{
            switch (page){
                case "available bungii details":
                    Thread.sleep(2000);
                    String driverEarnings = action.getValueAttribute(tripDetailsPage.Text_EstimatedEarnings());
                    testStepAssert.isTrue(driverEarnings.contains("~"),
                            "The variable sign (~) should be present",
                            "The variable sign (~) is not present");
                    break;
                case "schedule bungii details":
                    Thread.sleep(2000);
                    String driverEarningsSchedulePage = tripDetailsPage.Text_EstimatedEarningsSchedule().getText();
                    testStepAssert.isTrue(driverEarningsSchedulePage.contains("~"),
                            "The variable sign (~) should be present",
                            "The variable sign (~) is not present");
                    break;
            }
            log("I should be able to check the variable sign","I was able to check the variable sign",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I select \"([^\"]*)\" from items$")
    public void i_select_something_from_items(String pallet) throws Throwable {
        try{
            switch (pallet){
                case "Pallet-1":
                    action.clickBy2Points(47,650);
                    break;

                case "Pallet-2":
                    action.clickBy2Points(45,756);
                    break;
            }
            log("I should be able to select the pallet",
                    "I am able to select the pallet",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I check inadequate payload pop up is displayed$")
    public void i_check_inadequate_payload_pop_up_is_displayed() throws Throwable {
      try{
            testStepAssert.isElementDisplayed(tripDetailsPage.PopUp_InadequatePayload(),
                    "The pop up for inadequate payload should be displayed",
                    "The pop up for inadequate payload is displayed",
                    "The pop up for inadequate payload is not displayed");

            String expectedPopUpMesssage = PropertyUtility.getMessage("low.payload.capacity.message");
            String actualPopUpMesssage = tripDetailsPage.PopUp_InadequatePayload().getText();
            testStepAssert.isEquals(actualPopUpMesssage,expectedPopUpMesssage,
                    "The pop up message displayed should be correct.",
                    "The pop up message displayed is correct.",
                    "The pop up message displayed is incorrect.");
      }
      catch (Exception e) {
          logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
          error("Step  Should be successful",
                  "Error performing step,Please check logs for more details", true);
      }
    }
    @Then("^I check already accepted pallet pop up is displayed$")
    public void i_check_already_accepted_pallet_pop_up_is_displayed() throws Throwable {
        try{
            testStepAssert.isElementDisplayed(tripDetailsPage.PopUp_AlreadyAcceptedPallet(),
                    "The pop up for already accepted pallet should be displayed",
                    "The pop up for already accepted pallet is displayed",
                    "The pop up for already accepted pallet is not displayed");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I check information of both the pallets are displayed separately$")
    public void i_check_information_of_both_the_pallets_are_displayed_separately() throws Throwable {
        try{
            testStepAssert.isElementDisplayed(tripDetailsPage.Text_PalletOne(),
                    "The pallet one information should be displayed",
                    "The pallet one information is displayed",
                    "The pallet one information is not displayed");

            testStepAssert.isElementDisplayed(tripDetailsPage.Text_PalletTwo(),
                    "The pallet two information should be displayed",
                    "The pallet two information is displayed",
                    "The pallet two information is not displayed");

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I click \"([^\"]*)\" button on alert message$")
    public void i_click_something_button_on_alert_message(String button) throws Throwable {
        try {
            switch (button) {
                case "OK":
                    action.click(tripDetailsPage.Button_Ok());
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
    @And("^I check \"([^\"]*)\" details are displayed on \"([^\"]*)\" page$")
    public void i_check_something_details_are_displayed_on_something_page(String pallet, String page) throws Throwable {
        try{
            String palletOneWeight= PropertyUtility.getDataProperties("partner.washingtondc.weight.item.one");
            String palletOneDimensions= PropertyUtility.getDataProperties("partner.washingtondc.dimensions.item.one");
            String palletOneName= PropertyUtility.getDataProperties("partner.washingtondc.name.item.one");
            switch (page){
                case "available bungii":
                    switch (pallet){
                        case "pallet-1":
                            testStepAssert.isEquals(action.getText(tripDetailsPage.Text_PalletOneWeight()),palletOneWeight+" lbs",
                                    "The correct weight should be displayed.",
                                    "The correct weight is displayed.",
                                    "The incorrect weight is displayed.");
                            testStepAssert.isEquals(action.getText(tripDetailsPage.Text_PalletOneDimensions()),palletOneDimensions+" in",
                                    "The correct dimension should be displayed.",
                                    "The correct dimension is displayed.",
                                    "The incorrect dimension is displayed.");
                            testStepAssert.isEquals(action.getText(tripDetailsPage.Text_PalletOneName()),palletOneName,
                                    "The correct name should be displayed.",
                                    "The correct name is displayed.",
                                    "The incorrect name is displayed.");
                            break;
                    }
                    break;
                case "schedule bungii":
                    switch (pallet) {
                        case "pallet-1":
                            testStepAssert.isEquals(action.getText(tripDetailsPage.Text_PalletOneWeightSchedulePage()),palletOneWeight+" lbs",
                                    "The correct weight should be displayed.",
                                    "The correct weight is displayed.",
                                    "The incorrect weight is displayed.");
                            testStepAssert.isEquals(action.getText(tripDetailsPage.Text_PalletOneDimensionsSchedulePage()),palletOneDimensions+" in",
                                    "The correct dimension should be displayed.",
                                    "The correct dimension is displayed.",
                                    "The incorrect dimension is displayed.");
                            testStepAssert.isEquals(action.getText(tripDetailsPage.Text_PalletOneNameSchedulePage()),palletOneName,
                                    "The correct name should be displayed.",
                                    "The correct name is displayed.",
                                    "The incorrect name is displayed.");
                            break;

                    }
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I check if variable sign is not shown under \"([^\"]*)\"$")
    public void i_check_if_variable_sign_is_not_shown_under_something(String page) throws Throwable {
        try{
            switch (page){
                case "available bungii details":
                    Thread.sleep(2000);
                    String driverEarnings = tripDetailsPage.Text_EstimatedEarnings().getText();
                    testStepAssert.isFalse(driverEarnings.contains("~"),
                            "The variable sign (~) should not be present",
                            "The variable sign (~) is present");
                    break;
                case "schedule bungii details":
                    Thread.sleep(2000);
                    String driverEarningsSchedulePage = tripDetailsPage.Text_EstimatedEarningsSchedule().getText();
                    testStepAssert.isFalse(driverEarningsSchedulePage.contains("~"),
                            "The variable sign (~) should not be present",
                            "The variable sign (~) is present");
                    break;
            }
            log("I should be able to check if the variable sign is absent","I was able to check if the variable sign is absent",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Driver Bungii Information should be correctly displayed on BUNGII DETAILS screen$")
    public void driver_information_should_be_correctly_displayed_on_somethingBUNGIIDETAILS_screen() {
        try {
            action.swipeUP();
            String[] actualDetails = getTripDetails();
            cucumberContextManager.setScenarioContext("BUNGII_DRIVER_ESTIMATE", actualDetails[1]);
            String expectedTripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_FORMATTED")); ///Added to compare formatted time and not BUNGII_TIME
            String expectedTripDistance = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
            String timeValue = expectedTripTime.split(",")[1].trim().replace("am","AM").replace("pm","PM");
            String expectedDate = expectedTripTime.split(",")[0].trim();
            boolean isDateCorrect = actualDetails[2].split("\\|")[0].trim().contains(expectedDate.trim());
            String deliveryTime = actualDetails[2].split("\\|")[1].trim().split(" ")[0].trim();
            boolean isTimeCorrect = timeValue.split(" ")[0].trim().equals(deliveryTime);

            boolean isDistanceCorrect = actualDetails[0].contains(expectedTripDistance.split(" ")[0].trim());

            testStepVerify.isTrue(isTimeCorrect,
                    "Driver Trip Information should be correctly displayed on BUNGII DETAILS screen",
                    "Driver Time is correctly displayed [Excluding timezone] : "+ deliveryTime,
                    "Driver Time is not displayed correctly displayed ,Expected trip time:" + timeValue + " actual trip time " + actualDetails[2].trim());

            testStepVerify.isTrue(isDateCorrect,
                    "Driver Information should be correctly displayed on BUNGII DETAILS screen",
                    "Driver Time should be correctly displayed ",
                    "Driver Time is not displayed correctly displayed , expected Trip date:" + expectedDate + " actual trip date:" + actualDetails[2].trim());


            testStepVerify.isTrue(isDistanceCorrect,
                    "Driver Information should be correctly displayed on BUNGII DETAILS screen",
                    "Driver Distance should be correctly displayed ",
                    "Driver Distance is not displayed correctly displayed , expected Trip distance" + expectedTripDistance + " actual trip distance " + actualDetails[0]);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Driver Bungii Information is not correctly displayed on BUNGII DETAILS screen", true);
        }

    }

    /**
     * Accept Bungii
     */
    public void AcceptBungii() {
        action.click(tripDetailsPage.Button_Accept());
    }


    /**
     * Get trip details from trip details page
     *
     * @return Return array of string specifing trip details
     */
    public String[] getTripDetails() {
        String[] tripDetails = new String[4];

        try {


            tripDetails[0] = action.getValueAttribute(tripDetailsPage.Text_Distance());
            tripDetails[1] = action.getValueAttribute(tripDetailsPage.Text_EstimatedEarnings());
            tripDetails[2] = action.getValueAttribute(tripDetailsPage.Text_ScheduledDateTime());
          //  action.swipeUP();
           // tripDetails[3] = action.getValueAttribute(tripDetailsPage.Text_ScheduledTime());
        } catch (Exception e) {
            if (action.isAlertPresent()) {
                SetupManager.getDriver().switchTo().alert().dismiss();
            }
            tripDetails[0] = action.getValueAttribute(tripDetailsPage.Text_Distance());
            tripDetails[1] = action.getValueAttribute(tripDetailsPage.Text_EstimatedEarnings());
            tripDetails[2] = action.getValueAttribute(tripDetailsPage.Text_ScheduledDateTime());
       //     action.swipeUP();
       //     tripDetails[3] = action.getValueAttribute(tripDetailsPage.Text_ScheduledTime());
        }
        return tripDetails;
    }
    @Then("^I should see the customers name under the customer name field$")
    public void i_should_see_the_customers_name_under_the_customer_name_field() throws Throwable {
        try{
        String deliveryCreatedCustomerName = cucumberContextManager.getScenarioContext("CUSTOMER").toString().substring(0, 30);
        String customerName = action.getText(updateStatusPage.Text_CustomerNameOnDriverApp());
       testStepAssert.isEquals(customerName,deliveryCreatedCustomerName,deliveryCreatedCustomerName+ "Should be displayed",customerName+ "is displayed",deliveryCreatedCustomerName+ "is not displayed");
    } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I should be able to add the text \"([^\"]*)\" in the signed by field$")
    public void i_should_be_able_to_add_the_text_something_in_the_signed_by_field(String text) throws Throwable {
        try{
        Thread.sleep(1000);
        action.clearSendKeys(updateStatusPage.TextBox_SignedByField(),text);
        log("I should be able to add the text "+ text + " in the signed by field","I should be able to add the text "+ text + " in the signed by field",false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
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
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
    }
    }

    @Then("^I should see the customer signature row \"([^\"]*)\" in admin portal all delivery details page$")
    public void i_should_see_the_customer_signature_row_something_in_admin_portal_all_delivery_details_page(String CustomerSignature) throws Throwable {
        try{
            switch (CustomerSignature) {
                case "Present":
                    boolean isCustomerSignatureDisplayed = scheduledTripsPage.Label_CustomerSignature().isDisplayed();
                    testStepAssert.isTrue(isCustomerSignatureDisplayed, "Customer Signature row should be present", "Customer Signature row is  present", "Customer Signature row is not present");
                    break;
                case "Not Present":
                    testStepAssert.isFalse(action.isElementPresent(scheduledTripsPage.Label_CustomerSignature(true)),"Customer Signature row should not be present","Customer Signature row is not present","Customer Signature row is present");
                    break;
            }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
    }
    }

    @And("^I select \"([^\"]*)\" from the dropdown$")
    public void i_select_something_from_the_dropdown(String status) throws Throwable {
        try{
            Thread.sleep(5000);
            action.click(scheduledTripsPage.Link_ChangeDeliveryStatus());
            Thread.sleep(4000);
            action.click(scheduledTripsPage.DropDown_DeliveryStatus());
            switch (status){
                case "Admin Canceled":
                case "Partner Canceled":
                case "Driver Canceled":
                case "Customer Canceled":
                    action.click(scheduledTripsPage.Text_DeliveryStatus(status));
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
            action.click(scheduledTripsPage.DropDown_DeliveryStatusReason());
            switch (changestatusreason) {
                case "Driver initiated":
                case "Customer initiated - other reason":
                case "Outside of delivery scope":
                case "Solo: Driver not found":
                case "Other":
                    action.click(scheduledTripsPage.Text_DeliveryStatusReason(changestatusreason));
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
                testStepAssert.isTrue(signatureDisplayed,customerSign +" should be displayed",customerSign +" is  displayed",customerSign +" is not displayed");
                testStepAssert.isEquals(customerSign,expectedText,"Header should be "+expectedText,"Header should be "+expectedText,"Header should be "+expectedText);
                break;
            case "Not Displayed":
                testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Header_CustomerSignature(true)),expectedText+ " should not  be displayed",expectedText+ " is not displayed",expectedText+ " is displayed");
                break;
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
    }
    }

    @And("^The customer signature field is \"([^\"]*)\"$")
    public void the_customer_signature_field_is_something(String expectedText) throws Throwable {
        try{
        switch (expectedText) {
            case "Not required N/A":
                Thread.sleep(2000);
                String customerSignatureFieldText =action.getText(scheduledTripsPage.Label_CustomerSignatureNA()).replace("\n", " ");
                testStepAssert.isEquals(customerSignatureFieldText,expectedText,"Signature filed should have the text " +expectedText,"Signature filed has the text " +customerSignatureFieldText,"Signature filed doesnt have the text " +expectedText);
                break;
            case "Signature Present":
                Thread.sleep(2000);
                String ExpectedText ="Customer Signature";
                boolean isSignaturePresent = scheduledTripsPage.Image_CustomerSignature().isDisplayed();
               String customerSignaturePresent = (scheduledTripsPage.Image_CustomerSignature().getAttribute("title"));
                testStepAssert.isTrue(isSignaturePresent, "Customer signature should be displayed","Customer signature is displayed","Customer signature is not  displayed");
                testStepAssert.isEquals(customerSignaturePresent,ExpectedText,"Customer signature field should have signature present","Customer signature field is having  signature present","Customer signature field is not having signature present");
                break;
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
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
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
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
            String geofenceLabel = GeneralUtility.getTimeZoneBasedOnGeofenceIdForIos();
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
                    action.click(updateStatusPage.RadioButton_EditDeliveryStatus());
                    Thread.sleep(1000);
                    break;
                case "Delivery Completed":
                    action.click(updateStatusPage.RadioButton_BungiiComlpeted());
                    Thread.sleep(1000);
                    break;
                case "Delivery Canceled":
                    action.click(updateStatusPage.RadioButton_DeliveryCanceled());
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
            action.click(scheduledTripsPage.Icon_Dropdown());
            action.click(scheduledTripsPage.Option_Edit());
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

            action.click(scheduledTripsPage.Checkbox_driver());
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
            action.click(scheduledTripsPage.Link_DeliveryDetails());
            switch (buttonText) {
                case "Edit":
                    action.click(scheduledTripsPage.Button_Edit());
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
    public void i_search_the_delivery_using_something(String strArg1) throws Throwable {
        try {
            Thread.sleep(1000);
            action.clearSendKeys(scheduledTripsPage.TextBox_Search(), (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST") + Keys.ENTER);
            log("I should be able to search the delivery using pickup reference","I could search the delivery using pickup reference",false);
        } catch(Exception e){
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
    @And("^I view All Deliveries list on the admin portal$")
    public void i_view_all_deliveries_list_on_the_admin_portal() throws Throwable {
        try{
            //Thread.sleep(120000);
            action.click(scheduledTripsPage.Menu_Trips());
            Thread.sleep(3000);
            action.click(scheduledTripsPage.Menu_AllDeliveries());
            //action.click(admin_LiveTripsPage.Menu_LiveTrips());
            action.selectElementByText(scheduledTripsPage.Dropdown_SearchForPeriod(),"The Beginning of Time");
            Thread.sleep(2000);
            log("I view All Deliveries on the admin portal",
                    "I viewed All Deliveries on the admin portal", true);
        }
        catch (Throwable e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Delivery should be shown on All Deliveries", "Delivery is not shown on All Deliveries",
                    true);

        }
    }
    @Then("^The \"([^\"]*)\" should be in \"([^\"]*)\" state$")
    public void the_something_should_be_in_something_state(String adminPage ,String deliveryStatus) throws Throwable {
        try{
            switch (adminPage){
                case "All Deliveries":
                    switch (deliveryStatus){
                        case "Admin Canceled - No Driver(s) Found":
                        case "Partner Canceled":
                        case "Driver Canceled":
                        case "Payment Pending":
                        case "Payment Successful":
                            Thread.sleep(3000);
                            String status = action.getText(scheduledTripsPage.Text_DeliveryStatus(12));
                            testStepAssert.isEquals(status,deliveryStatus,"Delivery Should be in " +deliveryStatus+ " state",
                                    "Delivery is  in " +status+ " state",
                                    "Delivery is not in " +deliveryStatus+ " state");
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
}
