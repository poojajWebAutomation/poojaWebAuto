package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.*;
import com.bungii.web.pages.admin.Admin_PotentialPartnersPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Date;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_PotentialPartnersSteps extends DriverBase {
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_BusinessUsersSteps.class);
    Admin_PotentialPartnersPage admin_potentialPartnersPage = new Admin_PotentialPartnersPage();

    @Then("^I wait for \"([^\"]*)\" mins$")
    public void i_wait_for_something_mins(String strArg1) throws Throwable {
        action.hardWaitWithSwipeUp(Integer.parseInt(strArg1));
    }

    @Then("^I verify that the point of interests fields are populated$")
    public void i_verify_that_the_point_of_interests_fields_are_populated() throws Throwable {
        try {
            String pointOfInterests = admin_potentialPartnersPage.Label_PointOfInterest().getText();
            if (pointOfInterests.contains("Do Not Assign Right Now") && pointOfInterests.contains("Not A Business") && pointOfInterests.contains("Residential / Home") && pointOfInterests.contains("Location not listed - enter manually to create") && pointOfInterests.contains("Potential Locations")) {
                testStepAssert.isTrue(true, "The Point Of Interests are present.", "The Point Of Interests are not present.");
            } else {
                testStepAssert.isFail("The Point Of Interests are not present.");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @When("^I get the count of \"([^\"]*)\"$")
    public void i_get_the_count_of_something(String strArg1) throws Throwable {
        try {
            String countTrips = admin_potentialPartnersPage.Text_PickupsNumberInCluster().getText();
            cucumberContextManager.setScenarioContext("TRIPSINCLUSTER", countTrips);
            log("I get the count of" + strArg1 ,
                    "I got the count of" + strArg1, false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify the field \"([^\"]*)\"$")
    public void i_verify_the_field_something(String option) throws Throwable {
        try{
            switch (option){
                case "Pickups in this Cluster":
                    String pickupsCount=(String)cucumberContextManager.getScenarioContext("TRIPSINCLUSTER");
                    String pickupsCountUnderCLusterTrip=action.getText(admin_potentialPartnersPage.Text_PickupCountUnderClusterTrips());
                    testStepAssert.isEquals(pickupsCount,pickupsCountUnderCLusterTrip,"Count expected is "+pickupsCountUnderCLusterTrip,"Expected count is displayed.",pickupsCount+" count is not displayed.");
                    break;
                default:
                    error("UnImplemented Step or incorrect option.", "UnImplemented Step");
                    break;
            }

        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I click on \"([^\"]*)\" hyperlink$")
    public void i_click_on_something_hyperlink(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "View Trips":
                    action.click(admin_potentialPartnersPage.Hyperlink_ViewTrips());
                    break;
            }
            log("I click on " + strArg1 +" hyperlink" ,
                    "I have clicked on " + strArg1 +" hyperlink", false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }
    @And("^I assign driver \"([^\"]*)\" for the trip$")
    public void i_assign_driver_something_for_the_trip(String driverName) throws Throwable {
        try{
            Thread.sleep(5000);
            admin_potentialPartnersPage.TextBox_DriverSearch().sendKeys(driverName);
            action.JavaScriptClick(admin_potentialPartnersPage.Select_TestDriver());
            //String driver1Name=admin_potentialPartnersPage.Text_EditTrpDetailsDriver1Name().getText();
            cucumberContextManager.setScenarioContext("DRIVER1_NAME",driverName);
            cucumberContextManager.setScenarioContext("DRIVER2_NAME",driverName);
            log("I assign driver for the delivery ","I assigned driver "+driverName+" to the delivery",true );

        }catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify that the \"([^\"]*)\" is displayed$")
    public void i_verify_that_the_something_is_displayed(String strArg1) throws Throwable {

        try {
            switch (strArg1) {
                case "Testdrivertywd_appledc_a_web Sundarm":
                    String driverName= (String)cucumberContextManager.getScenarioContext("DRIVER2_NAME");
                    boolean condition= action.isElementPresent(admin_potentialPartnersPage.findElement(String.format("//tr[1]/td[contains(.,'%s')]",driverName),PageBase.LocatorType.XPath));

                    if(condition){
                        testStepAssert.isTrue(true, "Driver is assigned.", "Driver is not assigned.");
                    }
                    else {
                        testStepAssert.isFail("Driver is assigned.");
                    }
                    break;

                default:
                    logger.error("Method for " + strArg1 + "is not implemented ");

            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should not get alert as \"([^\"]*)\"$")
    public void i_should_not_get_alert_as_something(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Customer has ongoing trip":
                    String drivers= action.getText(admin_potentialPartnersPage.Text_DriversListScheduledTrips());
                    String driverName= (String)cucumberContextManager.getScenarioContext("DRIVER2_NAME");
                    if(drivers.contains(driverName)){
                        testStepAssert.isTrue(true, "Driver is assigned.", "Driver is not assigned.");
                    }
                    else {
                        testStepAssert.isFail("Driver is assigned.");
                    }
                    break;

                default:
                    logger.error("Method for " + strArg1 + "is not implemented ");

            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

}
