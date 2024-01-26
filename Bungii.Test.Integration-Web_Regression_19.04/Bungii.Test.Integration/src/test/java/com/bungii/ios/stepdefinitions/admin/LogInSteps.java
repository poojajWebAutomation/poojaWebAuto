package com.bungii.ios.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.ios.pages.admin.LogInPage;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;


public class LogInSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LogInSteps.class);
    ActionManager action = new ActionManager();
    LogInPage logInPage;
    GeneralUtility utility = new GeneralUtility();

    public LogInSteps(LogInPage logInPage) {
        this.logInPage = logInPage;
    }

    @When("^I navigate to admin portal$")
    public void i_navigate_to_admin_portal() {
        try {
            SetupManager.getDriver().navigate().to(utility.GetAdminUrl());
            String navigatedUrl = SetupManager.getDriver().getCurrentUrl();

            pass("I should be navigate to admin portal",
                    "I navigate to admin portal url : "+ navigatedUrl, true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I log in to admin portal$")
    public void i_log_in_to_admin_portal() {
        try {
            logger.detail(SetupManager.getDriver().getTitle());
            logger.detail(SetupManager.getDriver().getWindowHandle());
            logger.detail(SetupManager.getDriver().manage().window().getSize());
            action.sendKeys(logInPage.TextBox_Phone(),PropertyUtility.getDataProperties("admin.user"));
            action.sendKeys(logInPage.TextBox_Pass(),PropertyUtility.getDataProperties("admin.password"));
            //logInPage.TextBox_Phone().sendKeys(PropertyUtility.getDataProperties("admin.user"));
            //logInPage.TextBox_Pass().sendKeys(PropertyUtility.getDataProperties("admin.password"));
            //logInPage.Button_LogIn().click();
            action.click(logInPage.Button_LogIn());
            pass("I log in to admin portal",
                    "I got log in to admin portal", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

/*    @When("^I Switch to admin portal$")
    public void i_switch_to_admin_portal() {
        try {
        DriverManager.getObject().useDriverInstance("ADMIN");
        pass("Switch to  admin portal application",
                "Switch to  admin portal application", true);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }*/
}
