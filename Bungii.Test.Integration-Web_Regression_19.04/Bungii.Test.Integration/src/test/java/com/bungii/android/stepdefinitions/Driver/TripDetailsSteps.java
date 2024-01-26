package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.*;
import com.bungii.android.pages.driver.TripDetailsPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.pages.admin.Admin_EditScheduledBungiiPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class TripDetailsSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(TripDetailsSteps.class);
    ActionManager action = new ActionManager();
    TripDetailsPage tripDetailsPage = new TripDetailsPage();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility= new GeneralUtility();
    AvailableTripsPage availableTripsPage=new AvailableTripsPage();
    ScheduledBungiiPage scheduledBungiiPage=new ScheduledBungiiPage();
    InProgressBungiiPages inProgressBungiiPages = new InProgressBungiiPages();
    Admin_EditScheduledBungiiPage admin_EditScheduledBungiiPage = new Admin_EditScheduledBungiiPage();
    @When("I tap on \"([^\"]*)\" on driver Trip details Page")
    public void iTapOnOnDriverTripDetailsPage(String arg0) throws InterruptedException {
        try {
            switch (arg0.toUpperCase()) {
                case "ACCEPT":
                    Thread.sleep(10000);
                    if(action.isNotificationAlertDisplayed()){
                            action.click(Page_BungiiRequest.AlertButton_Cancel());
                        }
                    action.click(tripDetailsPage.Button_Accept());
                    Thread.sleep(2000);
                    break;
                default:
                        error("UnImplemented Step or incorrect button name", "UnImplemented Step");break;
            }
            log("I tap on "+arg0+" from driver available trip","I tap on "+arg0+" from driver available trip");


        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should able to see \"([^\"]*)\" available trip$")
    public void i_should_able_to_see_something_available_trip(String strArg1) throws Throwable {
        try{
            List<WebElement> listOfBungii = availableTripsPage.Image_SelectBungiis();
            List<WebElement> listOfCustomerBungii = availableTripsPage.Row_CustomerTrips();
            switch (strArg1) {
                case "two":
                    testStepAssert.isTrue(listOfBungii.size() == 2, "There should be two available deliveries",listOfBungii.size()+" available deliveries are displayed");
                    break;
                case "zero":
                    testStepAssert.isTrue(listOfBungii.size() == 0, "There should be zero available deliveries", listOfBungii.size()+" available deliveries are displayed.");
                    break;
                case "two customer":
                    testStepAssert.isTrue(listOfCustomerBungii.size() >= 2, "There should be two available app customer deliveries",listOfBungii.size()+" available app customer deliveries are displayed");
                    break;
                case "old":
                    int count = Integer.parseInt(String.valueOf(cucumberContextManager.getScenarioContext("COUNT_OF_AVAILABLE_TRIPS")));
                    testStepAssert.isTrue(listOfBungii.size() == count, "There should be no additional available deliveries",listOfBungii.size()+" available deliveries are displayed");
                    break;
                default:
                    error("UnImplemented Step or incorrect available delivery count", "Incorrect available delivery count");
                    break;
            }
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should able to see \"([^\"]*)\" scheduled trip$")
    public void i_should_able_to_see_something_scheduled_trip(String strArg1) throws Throwable {
        try{
            List<WebElement> listOfScheduledTrip = scheduledBungiiPage.List_ScheduledBungiis();
            switch (strArg1) {
                case "two":
                    testStepVerify.isTrue(listOfScheduledTrip.size() == 2, "There should be two scheduled trips","There is/are "+listOfScheduledTrip.size()+" Scheduled trips");
                    break;
                case "one":
                    testStepVerify.isTrue(listOfScheduledTrip.size() == 1, "There should be one scheduled trip", "There is/are "+listOfScheduledTrip.size()+" Scheduled trips");
                    break;
                case "zero":
                    testStepVerify.isTrue(listOfScheduledTrip.size() == 0, "There should be two scheduled trip", "There is/are "+listOfScheduledTrip.size()+" Scheduled trips");
                    break;
                default:
                    error("UnImplemented Step or incorrect scheduled delivery count", "Incorrect scheduled delivery count");
            }
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @And("^I Count the number of available bungiis$")
    public void i_count_the_number_of_available_bungiis() throws Throwable {
        try{
            List<WebElement> listOfBungii = availableTripsPage.Image_SelectBungiis();
            cucumberContextManager.setScenarioContext("COUNT_OF_AVAILABLE_TRIPS",listOfBungii.size());
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I click on the Duo teammate image$")
    public void i_click_on_the_duo_teammate_image() throws Throwable {
        try{
        Thread.sleep(6000);
        action.click(inProgressBungiiPages.Image_BungiiDuoTeammate());
        log("I should be able to click on duo teammat image","I could click on duo teammate image",false);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }
    @Then("^I should see the driver vehicle information$")
    public void i_should_see_the_driver_vehicle_information() throws Throwable {
        try {
        Thread.sleep(5000);
        boolean isVehicleModelDisplayed = inProgressBungiiPages.Text_DuoDriverVehicleModel().isDisplayed();
        String VehicleModel = action.getText(inProgressBungiiPages.Text_DuoDriverVehicleModel());
        testStepVerify.isTrue(isVehicleModelDisplayed,"Driver vehicle model " +VehicleModel +" should be displayed","Driver vehicle model " +VehicleModel +" is displayed","Driver vehicle model " +VehicleModel +" is not displayed" );
        boolean isVehicleLicenseNumberDisplayed = inProgressBungiiPages.Text_DuoDriverVehicleNumber().isDisplayed();
        String VehicleLicenseNumber = action.getText(inProgressBungiiPages.Text_DuoDriverVehicleNumber());
        testStepVerify.isTrue(isVehicleLicenseNumberDisplayed,"Driver vehicle licence number " +VehicleLicenseNumber +" should be displayed","Driver vehicle licence number " +VehicleLicenseNumber +" is displayed","Driver licence number " +VehicleLicenseNumber +" is not displayed" );
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

    @And("^I change delivery type from \"([^\"]*)\"")
    public void i_change_on_something_radiobutton(String radiobutton) throws Throwable {
        try{
            switch (radiobutton) {
                case "Solo to Duo":
                    action.click(admin_EditScheduledBungiiPage.RadioButton_Duo());
                    cucumberContextManager.setScenarioContext("BUNGII_TYPE","DUO");
                    break;
                case "Duo to Solo":
                    action.click(admin_EditScheduledBungiiPage.RadioButton_Solo());
                    cucumberContextManager.setScenarioContext("BUNGII_TYPE","SOLO");
                    break;
            }
            log("I change delivery type from  "+ radiobutton,
                    "I changed delivery type from "+ radiobutton, false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^The bungii teammate icon should not be displayed$")
    public void the_bungii_teammate_icon_should_not_be_displayed() throws Throwable {
        try{
        Thread.sleep(2000);
       testStepAssert.isNotElementDisplayed(inProgressBungiiPages.Image_BungiiDuoTeammate(true),"TeamMate icon should not be displayed","TeamMate icon is not displayed","TeamMate icon is displayed");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }
}
