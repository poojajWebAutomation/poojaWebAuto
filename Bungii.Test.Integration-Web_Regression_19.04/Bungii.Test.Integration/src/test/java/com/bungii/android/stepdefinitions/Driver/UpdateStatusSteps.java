package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.pages.customer.EstimatePage;
import com.bungii.android.pages.driver.*;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.otherApps.*;
import com.bungii.android.pages.driver.UpdateStatusPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.awt.event.KeyEvent;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class UpdateStatusSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.android.stepdefinitions.Driver.UpdateStatusSteps.class);
    DbUtility dbUtility = new DbUtility();
    Rectangle initial;
    ActionManager action = new ActionManager();
    UpdateStatusPage updateStatusPage = new UpdateStatusPage();
    InProgressBungiiPages inProgressBungiiPages = new InProgressBungiiPages();
    EstimatePage bungiiEstimatePage = new EstimatePage();
    GeneralUtility utility = new GeneralUtility();

    @When("^I slide update button on \"([^\"]*)\" Screen$")
    public void i_start_selected_bungii(String screen) {
        try {
            String expectedMessage = "";
            switch (screen.toUpperCase()) {
                case "EN ROUTE":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.enroute");
                    break;
                case "ARRIVED":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.arrived");
                    break;
                case "LOADING ITEM":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.loading");
                    break;
                case "DRIVING TO DROP OFF":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.drop.off");
                    break;
                case "UNLOADING ITEM":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.unloading");
                    break;
                default:
                    break;
            }
            //String actualValue = updateStatusPage.getSliderText();

            updateStatus();
            Thread.sleep(5000);
            log("I slide update button on " + screen + " screen", "I slide update button on " + screen + " screen", true);

		/*testStepVerify.isEquals(actualValue, expectedMessage,
				"I slide update button on " + screen + " Screen",
				"Slider value should be" + expectedMessage + "and actual is" + actualValue,
				"Slider value should be" + expectedMessage + "and actual is" + actualValue);*/
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    /**
     * Slide the slider to update status
     */
    public void updateStatus() throws InterruptedException {
        action.swipeRight(inProgressBungiiPages.Slider());
    }

    @Then("^non control driver should see \"([^\"]*)\" screen$")
    public void non_control_driver_should_see_something_screen(String strArg1) throws Throwable {
        try{
            testStepVerify.isElementEnabled(updateStatusPage.Activity_loader(true)," Driver should be shown loader screen");
            testStepVerify.isElementTextEquals(updateStatusPage.Text_WaitingForDriver(true),"Waiting for the other driver to end Bungii.");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I check ETA of \"([^\"]*)\"$")
    public void i_check_eta_of_something(String strArg1){
        try {
            switch (strArg1.toLowerCase()) {
                case "control driver":
                    cucumberContextManager.setScenarioContext("ETA_VALUE",action.getText(inProgressBungiiPages.Bungii_ETA()));
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" eta should be displayed to customer$")
    public void something_eta_should_be_displayed_to_customer(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "control driver":
                    String controlDriverEta=(String) cucumberContextManager.getScenarioContext("ETA_VALUE");
                    testStepVerify.isTrue(action.getText(inProgressBungiiPages.Bungii_ETACustomer()).equals(controlDriverEta),controlDriverEta+" should be displayed");
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }    }


    @And("^I check all the elements are displayed on driver rating page$")
    public void i_check_all_the_elements_are_displayed_on_driver_rating_page() throws Throwable {
        try
        {
            testStepAssert.isElementDisplayed(updateStatusPage.Header_RateTeamate(),
                    "The header Rate Duo teammate should be displayed",
                    "The header Rate Duo teammate is displayed",
                    "The header Rate Duo teammate is not displayed");

            testStepAssert.isElementDisplayed(updateStatusPage.Star_Rating(),
                    "Star ratings should be displayed",
                    "Star ratings are displayed",
                    "Star ratings is not displayed");

            testStepAssert.isElementDisplayed(updateStatusPage.Text_ChooseRating(),
                    "Choose Rating should be displayed",
                    "Choose Rating is displayed",
                    "Choose Rating is not displayed");

            testStepAssert.isElementDisplayed(updateStatusPage.Text_DriverExperience(),
                    "Driver experience should be displayed",
                    "Driver experience is displayed",
                    "Driver experience is not displayed");

            testStepAssert.isElementDisplayed(updateStatusPage.Button_DriverSubmit(),
                    "The submit button should be displayed",
                    "The submit button is displayed",
                    "The submit button is not displayed");

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I select \"([^\"]*)\" Ratting star for solo Driver 1$")
    public void i_select_somethingrd_ratting_star_for_solodriver_1(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "3":
                    action.click(updateStatusPage.RatingBar());
                    cucumberContextManager.setScenarioContext("RATING_VALUE","3");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should be able to select rating star","I am able to select rating star",false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I add a comment for driver$")
    public void i_add_a_comment_for_driver() throws Throwable {
        try{
            action.scrollToBottom();
            action.scrollToBottom();
            testStepAssert.isElementDisplayed(updateStatusPage.Textbox_AdditionalFeedback(),
                    "The textbox to add additional feedback should be displayed",
                    "The textbox to add additional feedback is displayed",
                    "The textbox to add additional feedback is not displayed");

            action.sendKeys(updateStatusPage.Textbox_AdditionalFeedback(),"The driver was helpful");
            action.click(updateStatusPage.Text_Additional());

            log("I should be able to add a comment for duo driver teammate","I was able to add a comment for duo driver teammate",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I check if the rating is saved in the db$")
    public void i_check_if_the_rating_is_saved_in_the_db() throws Throwable {
        try{
            String pickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String driverRating = dbUtility.getDriverRatingFromDriver(pickupRef);
            if(!(driverRating.isEmpty()))
            {
                testStepAssert.isTrue(true,"The driver rating is saved in db","The driver rating is not saved in db");
            }
            else{
                testStepAssert.isTrue(false,"The driver rating should be saved in db","The driver rating is not saved in db");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}