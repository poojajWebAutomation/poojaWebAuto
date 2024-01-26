package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.TutorialPage;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class TutorialSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(TutorialSteps.class);
    ActionManager action = new ActionManager();
    TutorialPage tutorialPage = new TutorialPage();

    @Then("^I close tutorial Page using close button$")
    public void i_close_tutorial_page() throws Throwable {
        try {
            action.click(tutorialPage.Button_Close());
            pass("I close tutorial Page", "I clicked on close button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
    @Then("^I close tutorial Page by using Start button$")
    public void i_close_tutorial_pageButton_Start() throws Throwable {
        try {
            action.click(tutorialPage.Button_Start());
            pass("I close tutorial Page by using Start button", "I clicked on close button by using Start button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
    @Then("^I should able to see \"([^\"]*)\" on Tutorials screen$")
    public void i_should_ableto_see_something_on_tutorials_screen(String strArg1) throws Throwable {

        try {
            switch (strArg1.toLowerCase()) {
                case "tutorials page 1":
                    testStepVerify.isElementEnabled(tutorialPage.Image_tutorialstep1(true), " tutorials page 1 should be displayed ");
                    testStepVerify.isEquals(action.getValueAttribute(tutorialPage.PageIndicator()), "page 1 of 5");
                    break;
                case "tutorials page 2":
                    testStepVerify.isElementEnabled(tutorialPage.Image_tutorialstep2(true), " tutorials page 2 should be displayed");
                    testStepVerify.isEquals(action.getValueAttribute(tutorialPage.PageIndicator()), "page 2 of 5");
                    break;
                case "tutorials page 3":
                    testStepVerify.isElementEnabled(tutorialPage.Image_tutorialstep3(true), " tutorials page 3 should be displayed");
                    testStepVerify.isEquals(action.getValueAttribute(tutorialPage.PageIndicator()), "page 3 of 5");
                    break;
                case "tutorials page 4":
                    testStepVerify.isElementEnabled(tutorialPage.Image_tutorialstep4(true), " tutorials page 4 should be displayed");
                    testStepVerify.isEquals(action.getValueAttribute(tutorialPage.PageIndicator()), "page 4 of 5");
                    break;
                case "tutorials page 5":
                    testStepVerify.isElementEnabled(tutorialPage.Image_tutorialstep5(true), " tutorials page 5 should be displayed");
                    testStepVerify.isEquals(action.getValueAttribute(tutorialPage.PageIndicator()), "page 5 of 5");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name",
                            "UnImplemented Step");
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I \"([^\"]*)\" swipe on tutorials page$")
    public void i_something_swipe_on_tutorials_page(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "left":
                    action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                    break;
                case "right":
                    action.swipeRight(tutorialPage.Image_Generictutorialstep());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name",
                            "UnImplemented Step");
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

}
