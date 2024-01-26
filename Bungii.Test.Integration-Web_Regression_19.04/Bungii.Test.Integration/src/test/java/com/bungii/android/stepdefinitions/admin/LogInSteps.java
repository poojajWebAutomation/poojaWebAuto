package com.bungii.android.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.android.pages.admin.LogInPage;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;


public class LogInSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.android.stepdefinitions.admin.LogInSteps.class);
    LogInPage logInPage;
    GeneralUtility utility = new GeneralUtility();
    public LogInSteps(LogInPage logInPage) {
        this.logInPage = logInPage;
    }

    @When("^I navigate to admin portal$")
    public void i_navigate_to_admin_portal() {
        try {
            SetupManager.getDriver().get(utility.GetAdminUrl());

            pass("I should be navigate to admin portal",
                    "I navigated to admin portal", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error in navigating to admin portal in browser",
                    true);
        }
    }

    @And("^I log in to admin portal$")
    public void i_log_in_to_admin_portal() {
        try {
            logInPage.TextBox_Phone().sendKeys(PropertyUtility.getDataProperties("admin.user"));
            logInPage.TextBox_Pass().sendKeys(PropertyUtility.getDataProperties("admin.password"));
            logInPage.Button_LogIn().click();
            pass("I log in to admin portal",
                    "I am logged in to admin portal", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error in logging in to admin portal",
                    true);
        }
    }
}
