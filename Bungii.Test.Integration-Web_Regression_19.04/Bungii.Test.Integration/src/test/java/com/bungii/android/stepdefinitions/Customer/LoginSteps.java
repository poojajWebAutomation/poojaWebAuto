package com.bungii.android.stepdefinitions.Customer;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.AccountPage;
import com.bungii.android.pages.customer.LoginPage;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class LoginSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    LoginPage loginPage = new LoginPage();
    AccountPage customerAccountPage = new AccountPage();
    GeneralUtility utility = new GeneralUtility();
    AccountPage accountPage = new AccountPage();

    @Given("^I am on customer Log in page$")
    public void i_am_on_customer_log_in_page() throws Throwable {
        try {
         //   utility.launchCustomerApplication();
            utility.goToLoginPage();
            action.waitUntilIsElementExistsAndDisplayed(loginPage.Header_LoginPage(true));
            testStepVerify.isElementDisplayed(loginPage.Header_LoginPage(true), "Login button should be displayed", "Login button is displayed", "Sign up button is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I login as customer \"([^\"]*)\" and is on Home Page$")
    public void i_login_as_customer_something_and_is_on_home_page(String strArg1) throws Throwable {
        i_am_on_customer_log_in_page();
        i_enter_customers_something_phone_number(strArg1);
        i_enter_customers_something_password("valid");
        i_tap_on_the_something_button("Log in");

        Thread.sleep(15000);


    }
    @When("^I enter customers \"([^\"]*)\" Phone Number$")
    public void i_enter_customers_something_phone_number(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "valid":
                    action.sendKeys(loginPage.TextField_PhoneNumber(), PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE_EXTRA", PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                    break;
                case "existing valid":
                    action.sendKeys(loginPage.TextField_PhoneNumber(), PropertyUtility.getDataProperties("customer_existing.phonenumber"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer_existing.phonenumber"));
                    break;
                case "invalid":
                    action.sendKeys(loginPage.TextField_PhoneNumber(), PropertyUtility.getDataProperties("customer_Invalid.phonenumber"));
                    break;
                case "blank":
                    action.sendKeys(loginPage.TextField_PhoneNumber(), "");
                    break;
                case "Valid_ToBeLocked":
                    action.sendKeys(loginPage.TextField_PhoneNumber(), PropertyUtility.getDataProperties("customer.ValidToBeLockedUser"));
                    break;
                case "Testcustomertywd_appleMarkFK LutherFK":
                    action.sendKeys(loginPage.TextField_PhoneNumber(), PropertyUtility.getDataProperties("Kansas.customer6.phone"));
                    break;
                default:
                    action.sendKeys(loginPage.TextField_PhoneNumber(), strArg1);
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE_EXTRA",strArg1);

                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I enter customers \"([^\"]*)\" Password$")
    public void i_enter_customers_something_password(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "valid":
                    action.sendKeys(loginPage.TextField_Password(), PropertyUtility.getDataProperties("customer_generic.password"));
                    break;
                case "valid1":
                    action.sendKeys(customerAccountPage.TextField_Password(),PropertyUtility.getDataProperties("customer_generic.password"));
                    break;
                case "invalid":
                    action.sendKeys(loginPage.TextField_Password(), PropertyUtility.getDataProperties("customer_LessThan6.password"));
                    break;
                case "invalid1":
                    action.sendKeys(customerAccountPage.TextField_Password(),PropertyUtility.getDataProperties("customer_LessThan6.password"));
                    break;
                case "blank":
                    action.sendKeys(loginPage.TextField_Password(), "");
                    break;
                case "generic":
                    action.sendKeys(loginPage.TextField_Password(), PropertyUtility.getDataProperties("customer_generic2.password"));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I tap on the \"([^\"]*)\" Button on Login screen$")
    public void i_tap_on_the_something_button(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Log in":
                    action.click(loginPage.Button_Login());
                    break;
                case "Sign up":
                    action.click(loginPage.Button_Signup());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The user should see \"([^\"]*)\" on log in page$")
    public void the_user_should_see_something_on_log_in_page(String strArg1) throws Throwable {
        try {
            String errorMessage = "", actualMessage = "", expectedMessage = "";
            switch (strArg1) {
                case "snackbar validation message invalid password":
                    //testStepVerify.isElementTextEquals(loginPage.Snackbar(), PropertyUtility.getMessage("customer.error.invalidpassword"));
                    testStepVerify.isEquals(utility.getCustomerSnackBarMessage(), PropertyUtility.getMessage("customer.error.invalidpassword"));
                    break;
                case "snackbar validation message invalid password for account deletion":
                    //testStepVerify.isElementTextEquals(loginPage.Snackbar(), PropertyUtility.getMessage("customer.error.invalidpassword"));
                    testStepVerify.isEquals(utility.getCustomerSnackBarMessage(), PropertyUtility.getMessage("customer.error.invalidpassword.accountdeletion"));
                    break;
                case "snackbar validation message scheduled bungii for account deletion":
                    action.click(accountPage.Button_Delete());
                    testStepAssert.isEquals(utility.getCustomerSnackBarMessage(),PropertyUtility.getMessage("customer.error.scheduledbungii.accountdeletion"),"message should display","message is display","message is not display");
                    //testStepVerify.isEquals(utility.getCustomerSnackBarMessage(), PropertyUtility.getMessage("customer.error.scheduledbungii.accountdeletion"));
                    break;
                case "snackbar validation message active bungii for account deletion":
                    testStepAssert.isEquals(utility.getCustomerSnackBarMessage(),PropertyUtility.getMessage("customer.error.activebungii.accountdeletion"),"message should display","message is display","message is not display");
                    break;
                case "field validations for password":
                    actualMessage = utility.trimString(action.getText(loginPage.Error_EnterPassword()));
                    expectedMessage = PropertyUtility.getMessage("customer.login.password.error");
                    testStepVerify.isEquals(actualMessage, expectedMessage);
                    break;
                case "field validations for phone number":
                    actualMessage = utility.trimString(action.getText(loginPage.Error_EnterPhone()));
                    expectedMessage = PropertyUtility.getMessage("customer.login.phone.error");
                    testStepVerify.isEquals(actualMessage, expectedMessage);
                    break;
                case "login button disabled":
                    testStepVerify.isElementNotEnabled(loginPage.Button_Login(), "Login button should not be enabled", "Login button is not enabled", "Login button is Enabled");
                    break;
                case "Invalid login credentials. 3 out of 5 attempts exhausted message":
                    testStepVerify.isEquals(utility.getCustomerSnackBarMessage(), PropertyUtility.getMessage("customer.error.threeoutoffive.attemptsexhausted"));
                    break;
                case "Invalid login credentials. Your account has been locked message":
                    testStepVerify.isEquals(utility.getCustomerSnackBarMessage(), PropertyUtility.getMessage("customer.error.accountlocked"));
                    break;
                default:
                        error("UnImplemented Step or incorrect button name", "UnImplemented Step");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Validation messages not displayed",
                    true);
        }
    }
}
