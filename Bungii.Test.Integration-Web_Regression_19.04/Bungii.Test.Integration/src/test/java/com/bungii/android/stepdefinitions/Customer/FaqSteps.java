package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.FAQPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class FaqSteps extends DriverBase {
    ActionManager action = new ActionManager();
    FAQPage faqPage = new FAQPage();
    private static LogUtility logger = new LogUtility(FaqSteps.class);

    @When("^I tap on \"([^\"]*)\" on FAQ page$")
    public void i_tap_on_something_on_faq_page(String strArg1) throws Throwable {
        try {
            Thread.sleep(5000);
            switch (strArg1) {
                case "first question":
                    Thread.sleep(5000);
                    action.invisibilityOfElementLocated(faqPage.FAQ_Loading(true));

                   // action.scrollToBottom();
                    action.invisibilityOfElementLocated(faqPage.FAQ_FirstQuestion());
                    action.click(faqPage.FAQ_FirstQuestion());
                    break;
                case "expanded first question":
                    action.scrollToBottom();
                    action.click(faqPage.FAQ_FirstQuestion());

                    //    action.click(faqPage.FAQ_FirstQuestion_open());
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

    @Then("^I should see \"([^\"]*)\" on FAQ page$")
    public void i_should_see_something_on_faq_page(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "first answer dropdown open":
                    Thread.sleep(20000);
                    //    action.scrollToBottom();
                    testStepAssert.isTrue(action.getText(faqPage.FAQ_FirstAnswer()).contains("Bungii drivers follow"), "First answer dropdown should open", "First answer dropdown is displayed", "First answer dropdown is not displayed");
                    break;
                case "first answer dropdown close":Thread.sleep(2000);
                //    testStepAssert.isFalse(action.getText(faqPage.FAQ_FirstAnswer()).contains("an app similar to other popular ridesharing apps"), "First answer dropdown should close", "First answer dropdown should be closed", "First answer dropdown is displayed");
                    testStepAssert.isFalse(action.isElementPresent(faqPage.FAQ_FirstAnswer(true)), "First answer dropdown should close", "First answer dropdown should be closed", "First answer dropdown is displayed");
                    break;
                case "last question":
                    action.scrollToBottom();
                    action.scrollToBottom();
                    action.scrollToBottom();
                    action.scrollToBottom();
                    action.scrollToBottom();
                    action.scrollToBottom();
                    ;
                    action.scrollToBottom();
                    testStepAssert.isElementDisplayed(faqPage.FAQ_LastQuestion(true), "Last question should be displayed ", "Last question is displayed", "Last questuion is not displayed");
                    break;
                case "social media links":
                    testStepAssert.isElementDisplayed(faqPage.FAQ_TwitterLogo(), "Twitter logo should be displayed", "Twitter logo is displayed", "Twitter logo is not displayed");
                    testStepAssert.isElementDisplayed(faqPage.FAQ_InstagramLogo(), "Instagram logo should be displayed", "Instagram logo is displayed", "Instagram logo is not displayed");
                    testStepAssert.isElementDisplayed(faqPage.FAQ_FBLogo(), "Facebook logo should be displayed", "Facebook logo is displayed", "Facebook logo is not displayed");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in viewing " + strArg1+ "on FAQ page",
                    true);
        }
    }
}
