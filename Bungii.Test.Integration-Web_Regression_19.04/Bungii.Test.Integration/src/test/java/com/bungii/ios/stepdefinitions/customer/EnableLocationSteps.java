package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.EnableLocationPage;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;

public class EnableLocationSteps extends DriverBase {
    EnableLocationPage enableLocationPage = new EnableLocationPage();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(EnableLocationSteps.class);


    @Then("^I verify and allow access of Location from Bungii application$")
    public void i_allow_access_of_location_from_bungii_application() throws Throwable {
        try {
            action.click(enableLocationPage.Button_Sure());
            //testStepVerify.isEquals(action.getAlertMessage(),PropertyUtility.getMessage("customer.location.alert.text"));
            //action.clickAlertButton("Allow");
            action.clickAlertButton("Allow While Using App");  //Customer App alert

            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            error("Should accept Allow While Using App permission", "Error performing step,Please check logs for more details", true);
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on allow location screen$")
    public void i_should_see_something_on_allow_location_screen(String identifier) throws Throwable {
        try {

            switch (identifier.toLowerCase()) {
                case "all details":
                    testStepVerify.isEquals(action.getScreenHeader(enableLocationPage.Text_Header()), PropertyUtility.getMessage("customer.navigation.allow.location.header"));
                    testStepVerify.isEquals(action.getNameAttribute(enableLocationPage.Text_Label()), PropertyUtility.getMessage("customer.navigation.allow.location.text"));
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }      }

    @Then("^I verify and allow access of Location from Bungii driver application$")
    public void i_allow_access_of_location_from_bungiidriver_application() throws Throwable {
        try {
            action.click(enableLocationPage.Button_Sure());
            testStepVerify.isEquals(action.getAlertMessage(),PropertyUtility.getMessage("driver.location.alert.text"));
            action.clickAlertButton("Allow");

            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I verify and deny access of Location from Bungii driver application$")
    public void i_deny_access_of_location_from_bungiidriver_application() throws Throwable {
        try {
            action.click(enableLocationPage.Button_Sure());
            testStepVerify.isEquals(action.getAlertMessage(),PropertyUtility.getMessage("driver.location.alert.text"));
            action.clickAlertButton("Don’t Allow");

            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I should see \"([^\"]*)\" on allow location driver screen$")
    public void i_should_see_something_on_allow_locationdriver_screen(String identifier) throws Throwable {
        try {

            switch (identifier.toLowerCase()) {
                case "all details":
                    testStepVerify.isEquals(action.getScreenHeader(enableLocationPage.Text_Header()), PropertyUtility.getMessage("driver.navigation.allow.location.header"));
                    testStepVerify.isEquals(action.getNameAttribute(enableLocationPage.Text_Label()), PropertyUtility.getMessage("driver.navigation.allow.location.text"));
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }      }

}
