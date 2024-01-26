package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.SupportPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class SupportSteps extends DriverBase {
    SupportPage supportPage = new SupportPage();
    GeneralUtility utility = new GeneralUtility();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(SupportSteps.class);

    @When("^I enter \"([^\"]*)\" text in Support field$")
    public void i_enter_something_text_in_support_field(String p0) throws Throwable {
        try {
        String textValue = "";
        //action.click(supportPage.TextField());
        //action.hideKeyboard();
        switch (p0) {
            case "valid":
                textValue = "Test";
                break;
            case "space":
                textValue = "          ";
                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }

        action.sendKeys(supportPage.TextField(), textValue);

        action.hideKeyboard();
        log("I should able to enter " + p0 + " ", "I enter " + textValue + " in support field",true);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @Then("^The user should see \"([^\"]*)\" on Support page$")
    public void the_user_should_see_something_on_support_page(String strArg1) throws Throwable {
        try {
        switch (strArg1) {
            case "snackbar validation":
                //supportPage.Snackbar()
                testStepAssert.isEquals(utility.getCustomerSnackBarMessage(), PropertyUtility.getMessage("customer.support.submitted"), "Support message submitted should be displayed ", "Support message submitted is displayed", "Support message submitted is not displayed");
                break;
            case "Send button disabled":
                testStepAssert.isElementDisplayed(supportPage.Button_Send(), "Send button should be disabled", "Send button is disabled", "Send button is enabled");
                break;
            case "validation message":
                action.hideKeyboard();
                testStepAssert.isEquals(action.getText(supportPage.Error_Blank()), PropertyUtility.getMessage("customer.support.emptyfield"), "Proper validation message should be displayed", "'" + PropertyUtility.getMessage("customer.support.emptyfield") + "'message is displayed", "'" + PropertyUtility.getMessage("customer.support.emptyfield") + "'message is not displayed");
                break;
            case "support question":
                testStepAssert.isEquals(action.getText(supportPage.Text_Title()).trim(),PropertyUtility.getMessage("customer.support.question"),"Support Menu Title must be displayed sucessfully","Support text "+PropertyUtility.getMessage("customer.support.question")+"is displayed correctly","Support text "+PropertyUtility.getMessage("customer.support.question")+"is not displayed correctly");
                testStepAssert.isEquals(action.getText(supportPage.Text_SubTitle()).trim(),PropertyUtility.getMessage("customer.support.question.label.android"),"Support Menu Title must be displayed sucessfully","Support text "+PropertyUtility.getMessage("customer.support.question.label.android")+"is displayed correctly","Support text "+PropertyUtility.getMessage("customer.support.question.label.android")+"is not displayed correctly");
                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Validation message not displayed",
                true);
    }
    }
    @Then("^The user should see \"([^\"]*)\" on tap of \"([^\"]*)\" on Support page$")
    public void the_user_should_see_something_on_support_page(String strArg1, String button) throws Throwable {
        try {
            switch (strArg1) {
                case "snackbar message":
                    log("I should tap" + button + " ", "I tapped " + button + " in support field");
                    action.click(supportPage.Button_Send());
                    testStepAssert.isEquals(utility.getCustomerSnackBarMessage(), PropertyUtility.getMessage("customer.support.submitted"), "Support message submitted should be displayed ", "Support message submitted is displayed", "Support message submitted is not displayed");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Validation message not displayed : " +strArg1,
                    true);
        }
    }

    @And("^I tap \"([^\"]*)\" on Support page$")
    public void i_tap_something_on_support_page(String strArg1) throws Throwable {
        try {
        action.click(supportPage.Button_Send());
        log("I should tap" + strArg1 + " ", "I tapped " + strArg1 + " in support field");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
}