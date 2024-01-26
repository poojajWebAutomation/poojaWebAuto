package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.DriverNotAvailablePage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class DriverNotAvailableSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    DriverNotAvailablePage driverNotAvailablePage = new DriverNotAvailablePage();
    ActionManager action = new ActionManager();

    @Then("^I should see \"([^\"]*)\" on DRIVER NOT AVAILABLE screen$")
    public void i_should_see_something_on_driver_not_available_screen(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Schedule Bungii option":
                    testStepVerify.isElementEnabled(driverNotAvailablePage.Button_ScheduleBungii(), "SCHEDULE BUNGII option is available.");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }    }

    @And("^I tap \"([^\"]*)\" button on DRIVER NOT AVAILABLE screen$")
    public void i_tap_something_button_on_driver_not_available_screen(String strArg1) throws Throwable {

        try {
            switch (strArg1) {
                case "Schedule Bungii":
                    //action.click(driverNotAvailablePage.Alert_ScheduleBungii());
                    action.click(driverNotAvailablePage.Button_ScheduleBungii());
                    break;
                case "Ok":
                    action.click(driverNotAvailablePage.Button_Ok());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log("I should able tapped on "+strArg1 +" button on  DRIVER NOT AVAILABLE screen","I tapped on "+strArg1 +" button on  DRIVER NOT AVAILABLE screen",true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
}
