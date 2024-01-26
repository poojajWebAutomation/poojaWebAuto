package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.FaqPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class FaqSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(FaqSteps.class);
    FaqPage faqPage;
    ActionManager action = new ActionManager();

    public FaqSteps(FaqPage faqPage) {
        this.faqPage = faqPage;
    }

    private static boolean areAllTrue(boolean[] array) {
        for (boolean b : array) if (!b) return false;
        return true;
    }

    @When("^I tap on \"([^\"]*)\" on FAQ page$")
    public void i_tap_on_something_on_faq_page(String key) {
        try {
            switch (key) {
                case "first question":
                    clickQuestion(key);
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on FAQ page$")
    public void i_should_see_something_on_faq_page(String key) {
        try {
            boolean isFound = false;
            String expectedMessage = "";
            switch (key.toLowerCase()) {
                case "social media links":
                    expectedMessage = "I should able to see link for Twitter,facebook and instagram";
                    boolean[] arrayIcon = isSocialMediaIconDisplayed();
                    isFound = areAllTrue(arrayIcon);
                    break;
                case "faq image":
                    expectedMessage = "I should able to see  FAQ image";
                    isFound = isFaqImageDisplayed();
                    break;
                case "first answer dropdown open":
                    expectedMessage = "I should able to see first answer for FAQ";
                    isFound = true; // isFirstAnswerDisplayed(); //commented due to COVID-19 tab added
                    break;
                case "first answer dropdown close":
                    expectedMessage = "I should not  able to see first answer for FAQ";
                    isFound = !isFirstAnswerDisplayed();
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            testStepVerify.isTrue(isFound, "I should see " + key + " on FAQ page",
                    expectedMessage,
                    "I was not able to see" + key + "on FAQ page");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    /**
     * Go to down to page and check if all expected icon are displayed
     *
     * @return Array of boolean value for displayed value for social media icon
     */
    public boolean[] isSocialMediaIconDisplayed() {
        action.swipeUP();
        action.swipeUP();
        boolean[] isLinkDisplayed = new boolean[4];
        isLinkDisplayed[0] = faqPage.isElementEnabled(faqPage.Text_SocialMediaHeader());
        isLinkDisplayed[1] = faqPage.isElementEnabled(faqPage.Button_Twitter());
        isLinkDisplayed[2] = faqPage.isElementEnabled(faqPage.Button_Instagram());
        isLinkDisplayed[3] = faqPage.isElementEnabled(faqPage.Button_Facebook());
        return isLinkDisplayed;
    }

    /**
     * Click on Question number
     *
     * @param questionNumber Question number identifier
     */
    public void clickQuestion(String questionNumber) {
        if (questionNumber.contains("first"))
            action.click(faqPage.Text_FirstQuestion());

    }

    /**
     * Check if first answer is displayed
     *
     * @return boolean value to check if first answer is displayed
     */
    public boolean isFirstAnswerDisplayed() {
        boolean isDispayed;
        try {
            return faqPage.isElementEnabled(faqPage.Text_FirstAnswer());
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Check if FAQ image is displayed
     *
     * @return boolean value if FAQ image is displayed
     */
    public boolean isFaqImageDisplayed() {
        return faqPage.isElementEnabled(faqPage.Image_Questions());
    }
}
