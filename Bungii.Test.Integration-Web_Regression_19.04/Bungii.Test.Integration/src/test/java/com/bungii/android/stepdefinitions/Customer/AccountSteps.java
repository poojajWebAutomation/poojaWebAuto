package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.AccountPage;
import com.bungii.android.pages.customer.PrivacyPolicyPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class AccountSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(AccountSteps.class);
    ActionManager action = new ActionManager();
    AccountPage accountPage = new AccountPage();
    PrivacyPolicyPage privacyPolicyPage = new PrivacyPolicyPage();

    /**
     * Read customer details and store it in scenario context
     */
    @Then("^I get customer account details$")
    public void i_get_customer_account_details() {
        try {
            String[] details = new String[2];
            details[0] = action.getText(accountPage.Account_Name());
            details[1] = action.getText(accountPage.Account_Phone());

            //replace all character
            String phone = details[1].replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
            //store customer name and phone in scenario context
            cucumberContextManager.setScenarioContext("CUSTOMER", details[0]);
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", phone);

            logger.detail("I get customer account details , Driver name is " + details[0] + " and Phone Number is " + details[1]);
            log("I get customer account details", "Driver name is " + details[0] + " and Phone Number is " + details[1],
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^logged in Customer details should be displayed$")
    public void logged_in_customer_details_should_be_displayed() {
        try {
            action.waitUntilIsElementExistsAndDisplayed(accountPage.Account_Phone());
            String actualName = action.getText(accountPage.Account_Name());
         //   String expectedName = PropertyUtility.getDataProperties("customer.first.valid.name") + " " + PropertyUtility.getDataProperties("customer.last.valid.name");
            String expectedName = PropertyUtility.getDataProperties("customer_generic.name");
            String actualPhone = action.getText(accountPage.Account_Phone()).replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
         //   String expectedPhoneNumber = PropertyUtility.getDataProperties("valid.customer.phone");
            String expectedPhoneNumber = PropertyUtility.getDataProperties("customer_generic.phonenumber");
            testStepVerify.isEquals(actualName, expectedName, "Driver name on account page should be " + expectedName, "Driver name on account page is" + actualName, "Driver name on account page is " + actualName + " , but expected is" + expectedName);
            testStepVerify.isEquals(actualPhone, expectedPhoneNumber);
            testStepVerify.isEquals(action.getText(accountPage.Account_Email()), PropertyUtility.getDataProperties("customer.valid.email"));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I click on \"([^\"]*)\" button on the \"([^\"]*)\" page of customer app$")
    public void i_click_on_something_button_on_the_something_page_of_customer_app(String strArg1, String strArg2) throws Throwable {
        try {
            switch (strArg2) {
                case "PAYMENT":
                case "PROMOS":
                case "ACCOUNT INFO":
                    action.click(accountPage.Button_Cust_Navigate_Up());
                    break;
                case "PRIVACY POLICY":
                    action.click(privacyPolicyPage.Button_Cust_Navigate_Up());
                    break;
                default:
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

}
