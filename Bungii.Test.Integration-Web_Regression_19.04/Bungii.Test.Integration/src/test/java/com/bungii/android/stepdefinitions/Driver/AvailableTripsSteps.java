package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.enums.Rejection_Reason;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.ScheduledTripsPage;
import com.bungii.android.pages.driver.*;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.android.pages.driver.AvailableTripsPage;
import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;

public class AvailableTripsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(AvailableTripsSteps.class);
    AvailableTripsPage availableTrips = new AvailableTripsPage();
    ActionManager action = new ActionManager();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility= new GeneralUtility();
    DriverHomePage driverHomePage = new DriverHomePage();
    ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();
    DbUtility dbUtility = new DbUtility();
    UpdateStatusPage updateStatusPage = new UpdateStatusPage();

    @And("I Select Trip from driver available trip")
    public void iSelectTripFromDriverAvailableTrip() {
        try {
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String numberOfDriver = (String) cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
            //   customerName="Vishal Bagi";numberOfDriver="SOLO";
            boolean isSelected = selectBungiiFromList(numberOfDriver, customerName.substring(0, customerName.indexOf(" ") + 2));
            if(!isSelected){

                if (action.isNotificationAlertDisplayed()) {
                    if (action.getText(Page_BungiiRequest.Alert_Msg(true)).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))) {
                        utility.acceptNotificationAlert();

                    } else {
                        //  action.click(Page_BungiiRequest.Button_Reject());
                        action.click(Page_BungiiRequest.AlertButton_Cancel());
                        isSelected = selectBungiiFromList(numberOfDriver, customerName.substring(0, customerName.indexOf(" ") + 2));
                    }

                }
            }

            log("I Select Trip from driver available trip","I Select Trip from driver available trip");
          //  testStepVerify.isTrue(isSelected, "I should able to select trip from available trip", "I was not able find available trip for customer " + customerName + " Estimate and Customer Cancel type " + numberOfDriver);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
   /* @Then("^I should able to see \"([^\"]*)\" available trip$")
    public void i_should_able_to_see_something_available_trip(String strArg1) throws Throwable {
        try {
            List<WebElement> listOfBungii=availableTrips.List_AvailableBungiis();
            switch (strArg1) {
                case "two":
                    testStepVerify.isTrue(listOfBungii.size()==2,"There should be two available trip");
                    break;
                case "zero":
                    testStepVerify.isTrue(listOfBungii.size()==0,"There should be two available trip");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }	}*/
    public boolean selectBungiiFromList(String bungiiType, String customerName) {
        boolean isSelected = false;

        try {
            //  action.scrollToBottom();
            //TODO: check diff between solo and duo on screen
            String expectedInstance = "2";
            if (bungiiType.toUpperCase().equals("SOLO")) {
                expectedInstance = "2";
            } else {
                expectedInstance = "4";

            }
            if(action.isElementPresent(driverHomePage.Alert_NewBungii(true)))
            {
                action.click(driverHomePage.Notification_AlertReject());

            }
            //List_AvailableBungiis
            List<WebElement> elements = availableTrips.List_AvailableBungiis();
            if(elements.size()==0)
            {
                fail("Trip should be displayed in available bungii list of driver",
                        "Trip is not displayed in available bungii list of driver", true);
            }
            else if (elements.size()==1)
            {
                logger.detail("Only One Available Bungii List Is Available. : "+ elements.get(0).getText());
                for (WebElement element : elements) {
                element.findElement(By.id("com.bungii.driver:id/row_available_pickup_imageview_arrow")).click();
                isSelected = true;
                }
            }
            else
            for (WebElement element : elements) {
                MobileElement image = element.findElement(By.id("com.bungii.driver:id/row_available_pickup_imageview_type"));
                WebElement actualCustomer = element.findElement(By.id("com.bungii.driver:id/row_available_pickup_drivername"));
                String actualCustomerName = actualCustomer.getText();
                System.out.println(SetupManager.getDriver().getPageSource());
                //      String  instance =image.getAttribute("instance");
                if (actualCustomerName.equals(customerName)) {
                    element.findElement(By.id("com.bungii.driver:id/row_available_pickup_imageview_arrow")).click();
                    isSelected = true;
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        //    error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
        return isSelected;

    }
    @Then("^I should be navigated to Available Nungiis screen on driver app$")
    public void i_should_be_navigated_to_something_screen_on_driver_app() throws Throwable {
        try {
            String getNaviagationText = action.getText(availableTrips.NavigationBar_Text());
            testStepVerify.isEquals(PropertyUtility.getMessage("driver.navigation.available.trips"), getNaviagationText, "I should be navigated to Available Trip page", "I am not navigated to Available Trip, Title is" + getNaviagationText);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I Select Trip from available trip$")
    public void i_select_trip_from_available_trip() throws Throwable {
        try{
            Thread.sleep(6000);
            if(action.isElementPresent(Page_BungiiRequest.Alert_NewBungiiRequest(true))){
                action.click(Page_BungiiRequest.Button_No_Thanks());
            }
            String expectedText = action.getText(availableTrips.Text_FromHomeMiles());
            boolean textDisplayed = (expectedText.contains("miles") || expectedText.contains("mile") )? true : false;

            testStepAssert.isTrue(textDisplayed,"Text should be updated to miles","Text is updated to miles","Text is not updated to miles");
        action.click(availableTrips.Row_AvailableTrip());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Trips are not listed in Available Bungiis of Driver", true);
        }
    }
    @And("^I Select second Trip from available trip$")
    public void i_select_second_trip_from_available_trip() throws Throwable {
        try{
            Thread.sleep(6000);

            action.click(availableTrips.Row_SecondAvailable());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Trips are not listed in Available Bungiis of Driver", true);
        }
    }

    @Then("^Partner Portal name should be displayed in \"([^\"]*)\" section$")
    public void partner_portal_name_should_be_displayed_in_something_section(String Screen) throws Throwable {
        try {
            String partnerNameExpected = (String) cucumberContextManager.getScenarioContext("Partner_Portal_Name");
            String customerNameFull= (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String [] customerName = customerNameFull.split(" ");
            String expectedCustomerName =customerName[0];


            switch (Screen) {
                case "AVAILABLE BUNGIIS":
                case "SCHEDULED BUNGIIS":
                    String partnerName = action.getText(availableTrips.Partner_Name());
                    testStepAssert.isEquals(partnerName, partnerNameExpected,"Partner Portal name should be displayed on " + Screen + " screen","Partner Portal name is displayed in " + Screen + " screen","Partner Portal name is not displayed in " + Screen + " screen");
                    break;
                case "EN ROUTE":
                case "ARRIVED":
                case "LOADING ITEMS":
                    String partnerNameText = action.getText(availableTrips.Partner_Name_For_Enroute());
                    testStepAssert.isEquals(partnerNameText, partnerNameExpected,"Partner Portal name should be displayed on " + Screen + " screen","Partner Portal name is displayed in " + Screen + " screen","Partner Portal name is not displayed in " + Screen + " screen");
                    break;
                case "DRIVING TO DROP-OFF":
                case "UNLOADING ITEMS":
                    String[] customerNameText = action.getText(availableTrips.Partner_Name_For_Enroute()).split(" ");
                    String customerNameproper = customerNameText[0];
                    testStepAssert.isEquals(customerNameproper, expectedCustomerName, "Customer  name should be displayed on " + Screen + " screen", "Customer name is displayed in " + Screen + " screen", "Customer name is not displayed in " + Screen + " screen");
                    break;
                default:
                    log("Correct screen", "Wrong screen", true);
                    break;
            }
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Partner Portal name is not displayed on "+Screen,
                    true);
        }
    }
    @And("^I check if variable sign is shown under \"([^\"]*)\"$")
    public void i_check_if_variable_sign_is_shown_under_something(String page) throws Throwable {
      try{
          switch (page){
              case "available bungii details":
                  Thread.sleep(2000);
                String driverEarnings = availableTrips.Text_DriverEarning().getText();
                testStepAssert.isTrue(driverEarnings.contains("~"),
                        "The variable sign (~) should be present",
                        "The variable sign (~) is not present");
                  break;
              case "schedule bungii details":
                  Thread.sleep(2000);
                  String driverEarningsSchedulePage = availableTrips.Text_DriverEarningSchedulePage().getText();
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
    @And("^I check if variable sign is not shown under \"([^\"]*)\"$")
    public void i_check_if_variable_sign_is_not_shown_under_something(String page) throws Throwable {
        try{
            switch (page){
                case "available bungii details":
                    Thread.sleep(2000);
                    String driverEarnings = availableTrips.Text_DriverEarning().getText();
                    testStepAssert.isFalse(driverEarnings.contains("~"),
                            "The variable sign (~) should not be present",
                            "The variable sign (~) is present");
                    break;
                case "schedule bungii details":
                    Thread.sleep(2000);
                    String driverEarningsSchedulePage = availableTrips.Text_DriverEarningSchedulePage().getText();
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
    @And("^I click on the back button and verify the rejection popup$")
    public void i_click_on_the_back_button_and_verify_the_rejection_popup() throws Throwable {
      try{
            Thread.sleep(1000);
            action.click(availableTrips.Button_Back());
            Thread.sleep(2000);

            testStepAssert.isElementDisplayed(availableTrips.Text_RejectionPopup(),"Rejection Reason pop-up must be displayed","Rejection Reason pop-up is displayed","Rejection Reason pop-up is not displayed");
      }
      catch (Exception ex){
          logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
          error("Step should be successful", "I cannot click on back button",
                  true);
      }
    }
    @Then("^I check if \"([^\"]*)\" customer trip that is rejected is displayed$")
    public void i_check_if_something_customer_trip_that_is_rejected_is_displayed(String custName) throws Throwable {
       try{
           String customerName =custName.substring(0,29);

           if (action.isElementPresent(availableTrips.Text_CustomerName(true))){
               String actualName=availableTrips.Text_CustomerName().getText();
               if(actualName==customerName)
               {
                   testStepAssert.isTrue(false,"Customer trip should not be present","Customer trip is present");
               }
           }
           else if(action.isElementPresent(availableTrips.Text_NoBungiisAvailable(true)))
           {
               testStepAssert.isTrue(true,"Customer trip should not be present","Customer trip is present");
           }
           else {
               testStepAssert.isTrue(false,"Customer trip should not be present","Customer trip is present");

           }
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful",
                   "Error performing step,Please check logs for more details", true);
       }
    }
    @And("^I click on the back button and verify that rejection popup is absent$")
    public void i_click_on_the_back_button_and_verify_that_rejection_popup_is_absent() throws Throwable {
        try{
            action.click(availableTrips.Button_Back());
            Thread.sleep(2000);

            testStepAssert.isFalse(action.isElementPresent(availableTrips.Text_RejectionPopup(true)),"Rejection Reason pop-up must not be displayed","Rejection Reason pop-up is not displayed", "Rejection Reason pop-up is displayed");

        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "I cannot click on back button",
                    true);
        }
    }
    @And("^I check if all reasons are displayed on rejection popup$")
    public void i_check_if_all_reasons_are_displayed_on_rejection_popup() throws Throwable {
       try{
           List<String> expectedOptions = new ArrayList() {{
              Rejection_Reason.TOO_FAR_AWAY.toString();
              Rejection_Reason.EARNINGS.toString();
              Rejection_Reason.LABOR_REQUIREMENTS.toString();
              Rejection_Reason.TYPE_OF_ITEM.toString();
              Rejection_Reason.NOT_ENOUGH_INFORMATION.toString();
              Rejection_Reason.NOT_AVAILABLE.toString();
           }};
          for (int j =0;j<expectedOptions.size();j++){
             String expectedReason= expectedOptions.get(j);
             String actualReason = availableTrips.Text_RejectionReason(j+1).getAttribute("text");
             testStepAssert.isEquals(actualReason,expectedReason,"The actual and expected reasons should be same","The actual and expected reasons are the same","The actual and expected reasons are not the same");
          }

       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful",
                   "Error performing step,Please check logs for more details", true);
       }
    }
    @And("^I click on \"([^\"]*)\" button on rejection popup$")
    public void i_click_on_something_button_on_rejection_popup(String button) throws Throwable {
        try {
            switch (button){
                case "CANCEL":
                    action.click(availableTrips.Button_Cancel());
                    break;
                case "SUBMIT":
                    action.click(availableTrips.Button_Submit());
                    break;
            }
            log("I should be able to click on "+button+" button",
                    "I am able to click on "+button+" button",
                    false);
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "I cannot click on "+button+" button",
                    true);
        }
    }
    @Then("^I check if the reason is saved in db$")
    public void i_check_if_the_reason_is_saved_in_db() throws Throwable {
        try{
            String driverNumber = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
            String reason = dbUtility.checkRejectionReason(driverNumber);
            if(!(reason.isEmpty()))
            {
                testStepAssert.isTrue(true,"The rejection reason is saved in db","The rejection reason is not saved in db");
            }
            else{
                testStepAssert.isTrue(false,"The rejection reason should be saved in db","The rejection reason is not saved in db");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I verify the rejection popup is displayed$")
    public void i_verify_the_rejection_popup_is_displayed() throws Throwable {
        try{
            testStepAssert.isElementDisplayed(availableTrips.Text_RejectionPopup(),"Rejection Reason pop-up must be displayed","Rejection Reason pop-up is displayed","Rejection Reason pop-up is not displayed");
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I check that latest reason is saved when \"([^\"]*)\" button is clicked$")
    public void i_check_that_latest_reason_is_saved_when_something_button_is_clicked(String strArg1) throws Throwable {
        try {
                Thread.sleep(2000);
                Boolean checkedRadioButton= Boolean.valueOf(availableTrips.RadioButton_LatestRejectionReason().getAttribute("checked"));
                Thread.sleep(2000);
                testStepAssert.isTrue(checkedRadioButton,"The latest reason is selected","The latest reason is selected","The latest reason is not selected");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Latest reason is not saved", true);
        }
    }
    @Then("^I check the notification for \"([^\"]*)\"$")
    public void i_check_the_notification_for_something(String strArg1) throws Throwable {
        try{
            String expectedMessage="";
            action.showNotifications();

            String scheduleDate = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
            String date=scheduleDate.substring(0,6);
            String checkZero=date.substring(4,5);
            if (checkZero.equalsIgnoreCase("0"))
            {
                date= date.replace("0","");
            }
            String time=scheduleDate.substring(8,16);
            Date d=new Date();
            String year= String.valueOf(d).substring(24);
            String message = PropertyUtility.getMessage("biglots.partner.cancel");
            String subMessage = PropertyUtility.getMessage("partner.cancel.message");
            expectedMessage = message+" "+date+", "+year+" at "+time+". "+subMessage;
            cucumberContextManager.setScenarioContext("EXPECTED_MESSAGE",expectedMessage);

            log("Checking notifications","Checking notifications",true);

            boolean isFound = utility.clickOnNofitication("Bungii", expectedMessage);
            if (!isFound) {
                Thread.sleep(80000);
                isFound = utility.clickOnNofitication("Bungii", expectedMessage);
            }
            //if no notificatiaon then hide
            if (!isFound) {
                action.hideNotifications();
            }
            testStepAssert.isTrue(isFound, "I should be able to click on notification for " + strArg1, "I clicked on notification for " + strArg1 + " with message " + expectedMessage, "PUSH NOTIFICATION NOT RECEIVED : " + expectedMessage + " message");
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
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
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneWeight()),palletOneWeight+" lbs",
                                    "The correct weight should be displayed.",
                                    "The correct weight is displayed.",
                                    "The incorrect weight is displayed.");
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneDimensions()),palletOneDimensions+" in",
                                    "The correct dimension should be displayed.",
                                    "The correct dimension is displayed.",
                                    "The incorrect dimension is displayed.");
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneName()),palletOneName,
                                    "The correct name should be displayed.",
                                    "The correct name is displayed.",
                                    "The incorrect name is displayed.");
                            break;
                    }
                    break;
                case "schedule bungii":
                    switch (pallet) {
                        case "pallet-1":
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneWeightSchedulePage()),palletOneWeight+" lbs",
                                    "The correct weight should be displayed.",
                                    "The correct weight is displayed.",
                                    "The incorrect weight is displayed.");
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneDimensionsSchedulePage()),palletOneDimensions+" in",
                                    "The correct dimension should be displayed.",
                                    "The correct dimension is displayed.",
                                    "The incorrect dimension is displayed.");
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneNameSchedulePage()),palletOneName,
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
    @Then("^I check already accepted pallet pop up is displayed$")
    public void i_check_already_accepted_pallet_pop_up_is_displayed() throws Throwable {
        try{
            action.click(scheduledTripsPage.Button_Accept());
            String expectedSnackMsg = PropertyUtility.getMessage("pallet.already.accepted.message");
            testStepVerify.isEquals(utility.getDriverSnackBarMessage(), expectedSnackMsg);

            log("I should be able to see the pallet already accepted message",
                    "I am able to see the pallet already accepted message",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I should see \"([^\"]*)\" header displayed$")
    public void i_should_see_something_header_displayed(String strArg1) throws Throwable {
        try{
        action.scrollToBottom();
        Thread.sleep(3000);
        action.scrollToBottom();
        switch (strArg1){
            case "SOLO LIFT":
                action.scrollToBottom();
                boolean isSoloLiftDisplayed = availableTrips.Label_SoloLift().isDisplayed();
                testStepAssert.isTrue(isSoloLiftDisplayed,"Solo Lift label should be displayed","Solo Lift label is displayed","Solo Lift label is not displayed");
                String expectedSoloLiftMessage = PropertyUtility.getDataProperties("solo.lift.message");
                String soloLiftInstructions = action.getText(availableTrips.Text_SoloLiftMessage());
                testStepAssert.isEquals(soloLiftInstructions,expectedSoloLiftMessage,expectedSoloLiftMessage+" Message should be displayed",soloLiftInstructions+" Message is displayed",expectedSoloLiftMessage+" Message is not displayed");
                break;
            case "CUSTOMER HELP":
                boolean isCustomerHelpLabelDisplayed = availableTrips.Label_CustomerHelp().isDisplayed();
                testStepAssert.isTrue(isCustomerHelpLabelDisplayed,"Solo Lift header should be displayed","Solo Lift header is displayed","Solo Lift header is not displayed");
                String expectedCustomerHelpMessage = PropertyUtility.getDataProperties("customer.help.message");
                String customerHelpInstructions = action.getText(availableTrips.Text_CustomerHelpMessage());
                testStepAssert.isEquals(customerHelpInstructions,expectedCustomerHelpMessage,expectedCustomerHelpMessage+" Message should be displayed",customerHelpInstructions+" Message is displayed",expectedCustomerHelpMessage+" Message is not displayed");
                break;
            case "DUO LIFT":
                boolean isDuoLiftDisplayed = availableTrips.Label_DuoLift().isDisplayed();
                testStepAssert.isTrue(isDuoLiftDisplayed,"Duo Lift label should be displayed","Duo Lift label is displayed","Duo Lift label is not displayed");
                String expectedDuoLiftMessage = PropertyUtility.getDataProperties("duo.lift.message");
                String duoLiftInstructions = action.getText(availableTrips.Text_DuoLiftMessage());
                testStepAssert.isEquals(duoLiftInstructions,expectedDuoLiftMessage,expectedDuoLiftMessage+" Message should be displayed",duoLiftInstructions+" Message is displayed",expectedDuoLiftMessage+" Message is not displayed");
                break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click on the \"([^\"]*)\" link beside scheduled bungii for \"([^\"]*)\"$")
    public void i_click_on_the_something_link_beside_scheduled_bungii_for_something(String strArg1, String deliveryType) throws Throwable {
        try{
            switch (deliveryType){
                case "Completed Deliveries":
                    Thread.sleep(4000);
                    action.click(scheduledTripsPage.Link_CompletedDeliveryDetails());
                    Thread.sleep(2000);
                    action.click(scheduledTripsPage.List_ViewDeliveries());
                    break;
                case "Scheduled Deliveries":
                    Thread.sleep(4000);
                    action.click(scheduledTripsPage.Link_DeliveryDetails());
                    Thread.sleep(2000);
                    action.click(scheduledTripsPage.List_ViewDeliveries());
                    break;
                case "Live Deliveries":
                    Thread.sleep(4000);
                    action.click(scheduledTripsPage.Link_LiveDeliveryDetails());
                    Thread.sleep(2000);
                    action.click(scheduledTripsPage.List_ViewDeliveries());
                    break;
            }
            log("I should be able to click on "+deliveryType+" link","I could click on "+deliveryType+" link",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^\"([^\"]*)\" icon should be displayed in all deliveries details page$")
    public void something_icon_should_be_displayed_in_all_deliveries_details_page(String expectedText) throws Throwable {
        try{
        Thread.sleep(2000);
        String expectedBackgroundColor =PropertyUtility.getDataProperties("customer.help.highlight");
        testStepAssert.isTrue(action.isElementPresent(scheduledTripsPage.Icon_CustomerHelpAdminPortal()),"Customer Help Icon should be displayed","Customer Help icon is displayed","Customer help icon is not displayed");
        String backgroundIconColor = scheduledTripsPage.Icon_CustomerHelpAdminPortal().getCssValue("background-color");
        testStepAssert.isEquals(backgroundIconColor,expectedBackgroundColor,"Icon should have yellow highlight","Icon has yellow highlight","Icon doesnt have yellow highlight");
        String iconText =action.getText(scheduledTripsPage.Icon_CustomerHelpAdminPortal()).toLowerCase();
        testStepAssert.isEquals(iconText,expectedText.toLowerCase(),"The text should be "+ expectedText,"The text is "+iconText,"The text is not  "+ expectedText);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The \"([^\"]*)\" should match$")
    public void the_something_should_match(String strArg1) throws Throwable {
        try {
            int driverTime= Integer.parseInt(PropertyUtility.getDataProperties("driver.buffer.drive.time"));
            String pickupReference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String[] ArrivalTimeAndUnloadingLoadingTime = DbUtility.getArrivalTimeAndLoadingUnloadingTime(pickupReference);
            switch (strArg1) {
                case "Arrival time":
                    String calculatedArrivalTime = ConvertTimeToTheRequiredGeoFence(ArrivalTimeAndUnloadingLoadingTime[1].split(" "));

                    if (calculatedArrivalTime.startsWith("0")) {

                        String hourWithoutZero = calculatedArrivalTime.replaceFirst("0", "");
                        cucumberContextManager.setScenarioContext("ArrivalTime", hourWithoutZero);
                    } else {
                        cucumberContextManager.setScenarioContext("ArrivalTime", calculatedArrivalTime);
                    }

                    String arrivalTimeOnUi = action.getText(updateStatusPage.ArrivalTimeAtPickupValue());
                    String properArrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");

                    testStepAssert.isEquals(arrivalTimeOnUi, properArrivalTime, "The arrival time should be " + properArrivalTime,
                            "The arrival time time is  " + properArrivalTime,
                            "The  incorrect arrival time displayed is  " + properArrivalTime);
                    break;
                case "stacked bungii":
                    String onlyHours = (String) cucumberContextManager.getScenarioContext("Hours");
                    String onlyMinutes = (String) cucumberContextManager.getScenarioContext("Minutes");

                    int deliveryCreatedTimeInMinutes = (Integer.parseInt(onlyHours) * 60) + Integer.parseInt(onlyMinutes);

                    String[] delivery2pickupLatAndLong = DbUtility.getLatAndLonPickupAndDropLocation(pickupReference);
                    String[] dropLocationOfFirstDelivery = new String[2];
                    dropLocationOfFirstDelivery[0] = delivery2pickupLatAndLong[0];
                    dropLocationOfFirstDelivery[1] = delivery2pickupLatAndLong[1];

                    String[] pickUpLocationOfSecondDelivery = new String[2];
                    pickUpLocationOfSecondDelivery[0] = (String) cucumberContextManager.getScenarioContext("onlyDropOffLat");
                    pickUpLocationOfSecondDelivery[1] = (String) cucumberContextManager.getScenarioContext("onlyDropOffLong");


                    long[] timeFromDropOffTo2ndDeliveryPickup = new GoogleMaps().getDurationInTraffic(dropLocationOfFirstDelivery, pickUpLocationOfSecondDelivery);
                    int finalValue = (int) (deliveryCreatedTimeInMinutes + Math.round(timeFromDropOffTo2ndDeliveryPickup[0] / 60));

                    String arrivalTimeForStackedRequest = action.getText(updateStatusPage.ArrivalTimeAtPickupValue());
                    String calculatedStackedDeliveryRequestTime = LocalTime.MIN.plus(Duration.ofMinutes(finalValue)).toString();

                    if (calculatedStackedDeliveryRequestTime.startsWith("0")) {
                        String hourWithoutZero = calculatedStackedDeliveryRequestTime.replaceFirst("0", "");
                        cucumberContextManager.setScenarioContext("StackedDeliveryArrivalTime", hourWithoutZero);
                    } else {
                        cucumberContextManager.setScenarioContext("StackedDeliveryArrivalTime", calculatedStackedDeliveryRequestTime);
                    }
                    String stackedArrivalTime = (String) cucumberContextManager.getScenarioContext("StackedDeliveryArrivalTime");

                    testStepVerify.isEquals(arrivalTimeForStackedRequest.substring(0, arrivalTimeForStackedRequest.length() - 3), stackedArrivalTime,
                            "Expected Arrival time for stacked delivery should be " + stackedArrivalTime + " +/- 5 minutes max",
                            "Expected Arrival time for stacked delivery is  " + stackedArrivalTime + " +/- 5 minutes max");
                    break;
                case "Ondemand bungii":
                    String onDemandArrivalTIme = ConvertTimeToTheRequiredGeoFence(ArrivalTimeAndUnloadingLoadingTime[1].split(" "));
                    String[] createdDeliveryInHoursAndMinutes = onDemandArrivalTIme.substring(0, onDemandArrivalTIme.length() - 3).split(":");
                    String onlyHour = createdDeliveryInHoursAndMinutes[0];
                    String onlyMinute = createdDeliveryInHoursAndMinutes[1];
                    int deliveryCreatedTimeInMinute = (Integer.parseInt(onlyHour) * 60) + Integer.parseInt(onlyMinute);

                    String[] pickup1Locations = DbUtility.getLatAndLonPickupAndDropLocation(pickupReference);
                    String[] pickup2Locations = DbUtility.getLatAndLonPickupAndDropLocation(pickupReference);

                    String[] dropLocation = new String[2];
                    dropLocation[0] = pickup1Locations[2];
                    dropLocation[1] = pickup1Locations[3];
                    String[] newPickupLocations = new String[2];
                    newPickupLocations[0] = pickup2Locations[0];
                    newPickupLocations[1] = pickup2Locations[1];

                    long[] timeToCoverDistance = new GoogleMaps().getDurationInTraffic(newPickupLocations, dropLocation);
                    int timeToCoverDistanceInMinutes = (int) (deliveryCreatedTimeInMinute + Math.round(timeToCoverDistance[0] / 60));
                    String arrivalTimeBasedOnCalculation = LocalTime.MIN.plus(Duration.ofMinutes(timeToCoverDistanceInMinutes)).toString();


                    String arrivalTimeForOndemand = action.getText(updateStatusPage.ArrivalTimeAtPickupValue());

                    if (arrivalTimeBasedOnCalculation.startsWith("0")) {
                        String hourWithoutZero = arrivalTimeBasedOnCalculation.replaceFirst("0", "");
                        cucumberContextManager.setScenarioContext("OndemandDeliveryArrivalTime", hourWithoutZero);
                    } else {
                        cucumberContextManager.setScenarioContext("OndemandDeliveryArrivalTime", arrivalTimeBasedOnCalculation);
                    }
                    String ondemandArrivalTime = (String) cucumberContextManager.getScenarioContext("OndemandDeliveryArrivalTime");


                    testStepVerify.isEquals(arrivalTimeForOndemand.substring(0, arrivalTimeForOndemand.length() - 3), ondemandArrivalTime,
                            "Expected On demand Arrival time for stacked delivery should be " + ondemandArrivalTime + " +/- 5 minutes max",
                            "Expected On demand Arrival time for stacked delivery is  " + ondemandArrivalTime + " +/- 5 minutes max");
                    break;
                case "Expected time at drop-off":
                case "Stacked delivery dropOff range":
                case "Ondemand delivery dropOff range":
                case "driver at arrival state":
                case "admin edits dropoff Address":
                case "Expected time at drop-off for duo":
                    if (strArg1.contentEquals("Expected time at drop-off") || strArg1.contentEquals("Expected time at drop-off for duo")) {
                        String arrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");
                        String[] hoursAndMinutes = arrivalTime.substring(0, arrivalTime.length() - 3).split(":");
                        String hours = hoursAndMinutes[0];
                        String minutes = hoursAndMinutes[1];
                        cucumberContextManager.setScenarioContext("Hours", hours);
                        cucumberContextManager.setScenarioContext("Minutes", minutes);
                    } else if ((strArg1.contentEquals("Stacked delivery dropOff range")) || (strArg1.contentEquals("Ondemand delivery dropOff range"))) {
                        String dropOffRangeTime = ConvertTimeToTheRequiredGeoFence(ArrivalTimeAndUnloadingLoadingTime[1].split(" "));
                        String[] createdDeliveryInHoursAndMinutess = dropOffRangeTime.substring(0, dropOffRangeTime.length() - 3).split(":");
                        String hours = createdDeliveryInHoursAndMinutess[0];
                        String minutes = createdDeliveryInHoursAndMinutess[1];
                        cucumberContextManager.setScenarioContext("Hours", hours);
                        cucumberContextManager.setScenarioContext("Minutes", minutes);
                    } else if ((strArg1.contentEquals("driver at arrival state"))) {
                        String pickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                        String changedDeliveryDetailsTime[] = DbUtility.getStatusTimestamp(pickupRef).split(" ");
                        String removedValueFromDot = changedDeliveryDetailsTime[1].substring(0, changedDeliveryDetailsTime[1].length() - 4);
                        changedDeliveryDetailsTime[1] = removedValueFromDot;
                        String arrivalStateAdminEdit = ConvertTimeToTheRequiredGeoFence(changedDeliveryDetailsTime);
                        String[] hoursAndMinutes = arrivalStateAdminEdit.substring(0, arrivalStateAdminEdit.length() - 3).split(":");
                        String hours = hoursAndMinutes[0];
                        String minutes = hoursAndMinutes[1];
                        cucumberContextManager.setScenarioContext("Hours", hours);
                        cucumberContextManager.setScenarioContext("Minutes", minutes);
                    } else if ((strArg1.contentEquals("admin edits dropoff Address"))) {
                        String pickupId = DbUtility.getPickupId(pickupReference);
                        String changedDeliveryDetailsTime[] = DbUtility.getAdminEditTime(pickupId).split(" ");
                        String z = ConvertTimeToTheRequiredGeoFence(changedDeliveryDetailsTime);
                        String[] hoursAndMinutes = z.substring(0, z.length() - 3).split(":");
                        String hours = hoursAndMinutes[0];
                        String minutes = hoursAndMinutes[1];
                        cucumberContextManager.setScenarioContext("Hours", hours);
                        cucumberContextManager.setScenarioContext("Minutes", minutes);
                    }
                    String hours = (String) cucumberContextManager.getScenarioContext("Hours");
                    String minutes = (String) cucumberContextManager.getScenarioContext("Minutes");

                    int convertHoursToMinutes = (Integer.parseInt(hours) * 60) + Integer.parseInt(minutes);

                    if (strArg1.contentEquals("Expected time at drop-off for duo")) {
                        String serviceBasedTime = (String) cucumberContextManager.getScenarioContext("ServiceBasedTotalTime");
                        cucumberContextManager.setScenarioContext("ServiceTimeOrUnloadingLoadingTIme", serviceBasedTime);
                    } else {
                        int unloadingLoadingTimeWithoutServiceLevel = (int) Float.parseFloat(ArrivalTimeAndUnloadingLoadingTime[2]);
                        cucumberContextManager.setScenarioContext("ServiceTimeOrUnloadingLoadingTIme", unloadingLoadingTimeWithoutServiceLevel);
                    }
                    int unloadingLoadingTime = Integer.parseInt((String) cucumberContextManager.getScenarioContext("ServiceTimeOrUnloadingLoadingTIme"));
                    int totalMinutes = convertHoursToMinutes + (unloadingLoadingTime / 3) + (Integer.parseInt(ArrivalTimeAndUnloadingLoadingTime[0])) + driverTime;
                    final SimpleDateFormat formatTochangeChangeTo12Hours = new SimpleDateFormat("hh:mm");

                    String roundedTime = roundedUpTime(LocalTime.MIN.plus(Duration.ofMinutes(totalMinutes)).toString());
                    LocalTime TimeInhours = LocalTime.parse(roundedTime);
                    String plus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(TimeInhours.plusHours(1))));
                    String minus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(TimeInhours.minusHours(1))));
                    cucumberContextManager.setScenarioContext("Timeplus1hour", plus1Hour);
                    cucumberContextManager.setScenarioContext("Timeminus1hour", minus1Hour);

                    String timeOneHourAhead = (String) cucumberContextManager.getScenarioContext("Timeplus1hour");
                    String timeOneHourBack = (String) cucumberContextManager.getScenarioContext("Timeminus1hour");

                    if (timeOneHourAhead.startsWith("0")) {
                        String hourWithoutZero = timeOneHourAhead.replaceFirst("0", "");
                        cucumberContextManager.setScenarioContext("StartingDropOffTimeRange", hourWithoutZero);
                    } else {
                        cucumberContextManager.setScenarioContext("StartingDropOffTimeRange", timeOneHourAhead);

                    }
                    if (timeOneHourBack.startsWith("0")) {
                        String hourWithoutZero = timeOneHourBack.replaceFirst("0", "");
                        cucumberContextManager.setScenarioContext("EndingDropOffTimeRange", hourWithoutZero);
                    } else {
                        cucumberContextManager.setScenarioContext("EndingDropOffTimeRange", timeOneHourBack);

                    }

                    if ((strArg1.contentEquals("driver at arrival state")) || strArg1.contentEquals("admin edits dropoff Address")) {
                        action.scrollToBottom();
                        action.scrollToBottom();
                        Thread.sleep(2000);
                        String expectedDroffTimeRange = action.getText(updateStatusPage.Text_DropOffRangeFromDeliveryDetails());
                        cucumberContextManager.setScenarioContext("DropoffTime", expectedDroffTimeRange);

                    } else {
                        String expectedDroffTimeRange = action.getText(updateStatusPage.ExpectedTimeAtDropOff());
                        cucumberContextManager.setScenarioContext("DropoffTime", expectedDroffTimeRange);
                    }

                    String expectedDroffTimeRange = (String) cucumberContextManager.getScenarioContext("DropoffTime");
                    String startingTimeRange = (String) cucumberContextManager.getScenarioContext("StartingDropOffTimeRange");
                    String endingTimeRange = (String) cucumberContextManager.getScenarioContext("EndingDropOffTimeRange");

                    if (expectedDroffTimeRange.contains("PM") && expectedDroffTimeRange.contains("AM")||expectedDroffTimeRange.contains("pm") && expectedDroffTimeRange.contains("am")) {

                        String onlyTimeRange = expectedDroffTimeRange.replace("PM", "").replace("AM", "").replace(" ", "");
                        ;
                        cucumberContextManager.setScenarioContext("UITimeRange", onlyTimeRange);
                    } else {
                        String onlyTimeRange = expectedDroffTimeRange.substring(0, expectedDroffTimeRange.length() - 3).replace(" ", "");
                        cucumberContextManager.setScenarioContext("UITimeRange", onlyTimeRange);
                    }

                    String UITimeRange = (String) cucumberContextManager.getScenarioContext("UITimeRange");
                    String calculatedDropoffTimeRange = endingTimeRange + "-" + startingTimeRange;
                    cucumberContextManager.setScenarioContext("DropOffRangeCalculated", calculatedDropoffTimeRange);
                    testStepAssert.isEquals(UITimeRange, calculatedDropoffTimeRange, "The dropOff time range should be " + calculatedDropoffTimeRange,
                            "The dropOff time range is  " + calculatedDropoffTimeRange,
                            "The  incorrect dropOff time range displayed is  " + UITimeRange);
                    break;
            }
        }   catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @Then("^I save the dropoff latitude and longitude of the first delivery$")
    public void i_save_the_dropoff_latitude_and_longitude_of_the_first_delivery() throws Throwable {
        try{
        String pickupReference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String teletTimeInDb = DbUtility.getTelet(pickupReference);
        String convertedTeletTime = ConvertTimeToTheRequiredGeoFence(teletTimeInDb.substring(0, teletTimeInDb.length() - 4).split(" "));
        String[] tcreatedDeliveryInHoursAndMinutes =convertedTeletTime.substring(0, convertedTeletTime.length() - 3).split(":");
        String onlyHours = tcreatedDeliveryInHoursAndMinutes[0];
        String onlyMinutes = tcreatedDeliveryInHoursAndMinutes[1];
        cucumberContextManager.setScenarioContext("Hours",onlyHours);
        cucumberContextManager.setScenarioContext("Minutes",onlyMinutes);;
        String[] location1PickupAndDropOffLatLong = DbUtility.getLatAndLonPickupAndDropLocation(pickupReference);
        cucumberContextManager.setScenarioContext("onlyDropOffLat",location1PickupAndDropOffLatLong[2]);
        cucumberContextManager.setScenarioContext("onlyDropOffLong",location1PickupAndDropOffLatLong[3]);;
        log("I should be able to save the dropoff latitide and longitude of the first delivery",
                "I could save the dropoff latitide and longitude of the first delivery",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I get the service level time for \"([^\"]*)\" having \"([^\"]*)\" service type$")
    public void i_get_the_service_level_time_for_something_having_something_service_type(String subdomain, String serviceName) throws Throwable {
        try{
        long default_Pickup_Time = DbUtility.getDefaultPickupTime(serviceName, subdomain);
        default_Pickup_Time = utility.Milliseconds_To_Minutes(default_Pickup_Time);
        long default_Dropoff_time = DbUtility.getDefaultDropoffTime(serviceName, subdomain);
        default_Dropoff_time = utility.Milliseconds_To_Minutes(default_Dropoff_time);
        int  sumLoadUnload = (int) (default_Pickup_Time+default_Dropoff_time);;
        cucumberContextManager.setScenarioContext("ServiceBasedTotalTime",sumLoadUnload);
        log("I should be able to get the service level time for the mentioned partner",
                "I could get the service level time for the mentioned partner",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^The \"([^\"]*)\" for customer delivery should match$")
    public void the_something_for_customer_delivery_should_match(String strArg1) throws Throwable {
        try{
        int driverTime= Integer.parseInt(PropertyUtility.getDataProperties("driver.buffer.drive.time"));
        String custPhone = (String)cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        String custRef = DbUtility.getCustomerRefference(custPhone);
        String []ArrivalTimeAndUnloadingLoadingTime = DbUtility.getArrivalTimeAndLoadingUnloadingTimeForCustomer(custRef);
        switch (strArg1){
            case "Arrival time":
                String calculatedArrivalTime = ConvertTimeToTheRequiredGeoFence(ArrivalTimeAndUnloadingLoadingTime[1].split(" "));

                if (calculatedArrivalTime.startsWith("0")) {

                    String hourWithoutZero = calculatedArrivalTime.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("ArrivalTime", hourWithoutZero);
                } else {
                    cucumberContextManager.setScenarioContext("ArrivalTime", calculatedArrivalTime);
                }

                String arrivalTimeOnUi = action.getText(updateStatusPage.ArrivalTimeAtPickupValue());
                String properArrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");

                testStepAssert.isEquals(arrivalTimeOnUi, properArrivalTime, "The arrival time should be " + properArrivalTime,
                        "The arrival time time is  " + properArrivalTime,
                        "The  incorrect arrival time displayed is  " + properArrivalTime);
                break;
            case "Expected time at drop-off":
                if(strArg1.contentEquals("Expected time at drop-off")){
                    String arrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");
                    String[] hoursAndMinutes =arrivalTime.substring(0, arrivalTime.length() - 3).split(":");
                    String hours = hoursAndMinutes[0];
                    String minutes = hoursAndMinutes[1];
                    cucumberContextManager.setScenarioContext("Hours",hours);
                    cucumberContextManager.setScenarioContext("Minutes",minutes);
                    // for scheduled deliveries formula -->
                    // [Projected start time] + ([Projected LoadUnload Time] / 3) + [Projected Drive Time] + 40
                }
                String hours =(String) cucumberContextManager.getScenarioContext("Hours");
                String minutes =(String) cucumberContextManager.getScenarioContext("Minutes");

                int convertHoursToMinutes = (Integer.parseInt( hours)*60) +Integer.parseInt( minutes) ;
                if(strArg1.contentEquals("Expected time at drop-off for duo")){
                    String serviceBasedTime = (String) cucumberContextManager.getScenarioContext("ServiceBasedTotalTime");
                    cucumberContextManager.setScenarioContext("ServiceTimeOrUnloadingLoadingTIme",serviceBasedTime);
                }
                else {
                    int unloadingLoadingTimeWithoutServiceLevel = (int) Float.parseFloat(ArrivalTimeAndUnloadingLoadingTime[2]);
                    cucumberContextManager.setScenarioContext("ServiceTimeOrUnloadingLoadingTIme",unloadingLoadingTimeWithoutServiceLevel);
                }
                int unloadingLoadingTime = Integer.parseInt((String) cucumberContextManager.getScenarioContext("ServiceTimeOrUnloadingLoadingTIme"));
                int totalMinutes = convertHoursToMinutes  + (unloadingLoadingTime/3)+ (Integer.parseInt(ArrivalTimeAndUnloadingLoadingTime[0]))+driverTime;
                final SimpleDateFormat formatTochangeChangeTo12Hours = new SimpleDateFormat("hh:mm");

                String roundedTime =roundedUpTime(LocalTime.MIN.plus(Duration.ofMinutes( totalMinutes)).toString());
                LocalTime TimeInhours =LocalTime.parse(roundedTime);
                String plus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(TimeInhours.plusHours(1)))) ;
                String minus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(TimeInhours.minusHours(1)))) ;
                cucumberContextManager.setScenarioContext("Timeplus1hour",plus1Hour);
                cucumberContextManager.setScenarioContext("Timeminus1hour",minus1Hour);

                String timeOneHourAhead = (String) cucumberContextManager.getScenarioContext("Timeplus1hour");
                String timeOneHourBack =(String) cucumberContextManager.getScenarioContext("Timeminus1hour") ;

                if (timeOneHourAhead.startsWith("0")) {
                    String hourWithoutZero = timeOneHourAhead.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("StartingDropOffTimeRange", hourWithoutZero);
                } else {
                    cucumberContextManager.setScenarioContext("StartingDropOffTimeRange", timeOneHourAhead);

                }
                if (timeOneHourBack.startsWith("0")) {
                    String hourWithoutZero = timeOneHourBack.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("EndingDropOffTimeRange", hourWithoutZero);
                } else {
                    cucumberContextManager.setScenarioContext("EndingDropOffTimeRange", timeOneHourBack);

                }
                String expectedDroffTimeRange = action.getText(updateStatusPage.ExpectedTimeAtDropOff());
                String startingTimeRange = (String) cucumberContextManager.getScenarioContext("StartingDropOffTimeRange");
                String endingTimeRange = (String) cucumberContextManager.getScenarioContext("EndingDropOffTimeRange");

                if (expectedDroffTimeRange.contains("PM") && expectedDroffTimeRange.contains("AM")||expectedDroffTimeRange.contains("pm") && expectedDroffTimeRange.contains("am")) {

                    String onlyTimeRange = expectedDroffTimeRange.replace("PM", "").replace("AM", "").replace(" ", "");
                    ;
                    cucumberContextManager.setScenarioContext("UITimeRange", onlyTimeRange);
                } else {
                    String onlyTimeRange = expectedDroffTimeRange.substring(0, expectedDroffTimeRange.length() - 3).replace(" ", "");
                    cucumberContextManager.setScenarioContext("UITimeRange", onlyTimeRange);
                }

                String UITimeRange = (String) cucumberContextManager.getScenarioContext("UITimeRange");
                String calculatedDropoffTimeRange = endingTimeRange + "-" + startingTimeRange;
                cucumberContextManager.setScenarioContext("DropOffRangeCalculated", calculatedDropoffTimeRange);
                testStepAssert.isEquals(UITimeRange, calculatedDropoffTimeRange, "The dropOff time range should be " + calculatedDropoffTimeRange,
                        "The dropOff time range is  " + calculatedDropoffTimeRange,
                        "The  incorrect dropOff time range displayed is  " + UITimeRange);
                break;
        }
        }    catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    private String roundedUpTime(String IncorrectTime) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        Date dt = formatter.parse(IncorrectTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int unroundedMinutes = cal.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 5;
        if (mod ==0){
            ; cal.add(Calendar.MINUTE,(0-mod));
        }
        else  if ( (mod>=1) && (mod < 3) ){
            cal.add(Calendar.MINUTE,(5-mod));
        }
        else {
            cal.add(Calendar.MINUTE,(5-mod));
        }

        String hourAndMinute = formatter.format(cal.getTime());
        logger.detail("The rounded up time for "+ IncorrectTime+ " time is "+ hourAndMinute);
        return hourAndMinute;
    }

    private String ConvertTimeToTheRequiredGeoFence(String[] uctToCstTime) {
        String date[] = uctToCstTime[0].split("-");
        String time[] = uctToCstTime[1].split(":");
        if(time[2].contains(".")){
            String seconds = time[2].substring(0,time[2].length()-4);
            cucumberContextManager.setScenarioContext("SecondsWithoutPointValue",seconds);
        }
        else {
            cucumberContextManager.setScenarioContext("SecondsWithoutPointValue",time[2]);

        }
        String seconds = (String) cucumberContextManager.getScenarioContext("SecondsWithoutPointValue");
        ZonedDateTime instant1 = ZonedDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(seconds),0,ZoneId.of("UTC"));
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        ZonedDateTime instantInUTC = instant1.withZoneSameInstant(ZoneId.of(geofenceLabel));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String convertedTime = instantInUTC.format(formatter);
        return convertedTime;
    }



    @And("I check if customer name is {string} under {string}")
    public void iCheckIfCustomerNameIsUnder(String name, String page) throws Throwable {
        try{
            switch (page){
                case "available bungii details":
                case "schedule bungii details":
                case "EN ROUTE":
                case "ARRIVED":
                case "LOADING ITEMS":
                case "DRIVING TO DROP-OFF":
                case "UNLOADING ITEMS":

                    Thread.sleep(2000);
                    action.scrollToBottom();
                    List<WebElement> customerNameSchedulePage =availableTrips.List_CustomerName();
                    testStepAssert.isEquals(customerNameSchedulePage.get(1).getText(),name,
                            "The customer name should be trimmed & only initial should be displayed of second word",
                            "The customer name is trimmed & only initial is displayed of second word",
                            "The customer name is not trimmed & whole second word is displayed");
                    action.scrollToTop();
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
}

