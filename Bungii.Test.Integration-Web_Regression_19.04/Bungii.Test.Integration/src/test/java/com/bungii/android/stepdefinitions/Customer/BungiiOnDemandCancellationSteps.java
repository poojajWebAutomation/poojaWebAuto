package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.AccountPage;
import com.bungii.android.pages.customer.SearchingPage;
import com.bungii.android.pages.customer.SetPickupTimePage;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.warning;

public class BungiiOnDemandCancellationSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(AccountSteps.class);
    ActionManager actionManager = new ActionManager();
    SetPickupTimePage setPickupTimePage = new SetPickupTimePage();
    SearchingPage searchingPage = new SearchingPage();
    DbUtility dbutility = new DbUtility();

    @Then("^A popup with \"([^\"]*)\" should appear$")
    public void a_popup_with_something_should_appear(String option) {
        try{
            switch (option){
                case "Cancel Reasons":
                    //setPickupTimePage.Button_Ok()
                    actionManager.isElementPresent(setPickupTimePage.Popup_CancelReasonTitle());

                    break;

                default:
                    error("Implemented Step", "UnImplemented Step");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I select reason as \"([^\"]*)\"$")
    public void i_select_reason_as_something(String reason) throws Throwable {
        try{
            switch (reason){
                case "I needed it right away.":
                    actionManager.click(setPickupTimePage.Text_FirstCancellationReason());
                    break;

                case "I found an alternative.":
                    actionManager.click(setPickupTimePage.Text_SecondCancellationReason());
                    break;

                case "I don’t need it anymore.":
                    actionManager.click(setPickupTimePage.Text_ThirdCancellationReason());
                     break;
                case "Other...":
                    actionManager.click(setPickupTimePage.Text_FourthCancellationReason());
                    break;

                default:
                    error("Implemented Step", "UnImplemented Step");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^The \"([^\"]*)\" textbox should be displayed$")
    public void the_something_textbox_should_be_displayed(String option) throws Throwable {
        try{
            switch (option){
                case "CANCELLATION REASON":
                    testStepAssert.isElementDisplayed(setPickupTimePage.TextBox_CancellationReason(), "The text-box should be displayed.", "The text-box is displayed.", "The text-box is not displayed.");
                    break;

                default:
                    error("Implemented Step", "UnImplemented Step");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I enter \"([^\"]*)\" on Cancellation popup textbox$")
    public void i_enter_something_on_cancellation_popup_textbox(String option){
        try{
            switch (option){
                case "reason":
                    actionManager.sendKeys(setPickupTimePage.TextBox_CancellationReason(), "Test Other... reason option.");
                    break;

                default:
                    error("Implemented Step", "UnImplemented Step");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I check if a \"([^\"]*)\" is shown in the table$")
    public void i_check_if_a_something_is_shown_in_the_table(String expectedReason) throws Throwable {
        try{
            Thread.sleep(10000);
            String customerPhone = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE") ;
            if(customerPhone=="")
                customerPhone = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE") ;
            String actualNote = dbutility.getPickupNoteOfLastPickupOf(customerPhone);
            testStepAssert.isEquals(actualNote,expectedReason,expectedReason + "should be displayed", actualNote + " is displayed", actualNote + " is displayed instead of "+expectedReason);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}