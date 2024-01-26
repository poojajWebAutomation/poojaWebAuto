package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.EnableLocationPage;
import com.bungii.ios.pages.customer.EnableNotificationPage;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;

public class EnabledNotificationSteps extends DriverBase {
    ActionManager action = new ActionManager();
    EnableNotificationPage enableNotificationPage = new EnableNotificationPage();
    EnableLocationPage enableLocationPage = new EnableLocationPage();

    private static LogUtility logger = new LogUtility(EnabledNotificationSteps.class);

    @Then("^I verify and allow access of Notification from Bungii application$")
    public void i_allow_access_of_notification_from_bungii_application() throws Throwable {
        try {
            action.click(enableNotificationPage.Button_Sure());
            testStepVerify.isEquals(action.getAlertMessage(),PropertyUtility.getMessage("customer.notifications.alert.text"));
            action.clickAlertButton("Allow");
            pass("I allow access of Notification from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^I should see \"([^\"]*)\" on allow notifications screen$")
    public void i_should_see_something_on_allow_notifications_screen(String identifier) throws Throwable {
        try {

            switch (identifier.toLowerCase()) {
                case "all details":
                    testStepVerify.isEquals(action.getScreenHeader(enableNotificationPage.Text_Header()), PropertyUtility.getMessage("customer.navigation.allow.notifications.header"));
                    testStepVerify.isEquals(action.getNameAttribute(enableNotificationPage.Text_Label()), PropertyUtility.getMessage("customer.navigation.allow.notifications.text"));

                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }    }

    @Then("^I verify and allow access of Notification from Bungii driver application$")
    public void i_allow_access_of_notification_from_bungiidriver_application() throws Throwable {
        try {
            action.click(enableNotificationPage.Button_Sure());
            testStepVerify.isEquals(action.getAlertMessage(),PropertyUtility.getMessage("driver.notifications.alert.text"));
            action.clickAlertButton("Allow");
            pass("I allow access of Notification from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
    @Then("^I accept \"([^\"]*)\" and \"([^\"]*)\" permission if exist$")
    public void I_acceptNotificationAndLocationPermissionIfExist(String Notification, String Location) throws Throwable {
        try {
            GeneralUtility utility = new GeneralUtility();
            String pageName = utility.getPageHeader();
            if(action.isElementPresent(enableNotificationPage.Button_Sure())) {
                action.click(enableNotificationPage.Button_Sure());
                logger.detail("On Notifications Popup");
                action.clickAlertButton("Allow");
                Thread.sleep(15000);
            }
           // Thread.sleep(6000);
            if(action.isAlertPresent())
            {
                action.clickAlertButton("Always Allow");
            }
            else
            if(action.isElementPresent(enableNotificationPage.Button_Sure())) {
                action.click(enableLocationPage.Button_Sure());
                logger.detail("On Locations Popup");

                Thread.sleep(3000);
                action.clickAlertButton("Allow While Using App");
                //pageName = utility.getPageHeader();
            }
            if(action.isAlertPresent()){
                logger.detail("On Location Always Allow Popup");
                Thread.sleep(3000);
                action.clickAlertButton("Change to Always Allow");
                if(action.isElementPresent(enableLocationPage.Button_Done())){
                    action.click(enableLocationPage.Button_Done());
                }
            }

        } catch (Exception e) {
           // action.clickAlertButton("Always Allow");
           // action.clickAlertButton("Always Allow");

        }

    }
    @Then("^I should see \"([^\"]*)\" on allow notifications driver screen$")
    public void i_should_see_something_on_allow_notificationsdriver_screen(String identifier) throws Throwable {
        try {

            switch (identifier.toLowerCase()) {
                case "all details":
                    testStepVerify.isEquals(action.getScreenHeader(enableNotificationPage.Text_Header()), PropertyUtility.getMessage("driver.navigation.allow.notifications.header"));
                    testStepVerify.isEquals(action.getNameAttribute(enableNotificationPage.Text_Label()), PropertyUtility.getMessage("driver.navigation.allow.notifications.text"));

                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }    }


}
