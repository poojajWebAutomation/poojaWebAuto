package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_DriverVerificationPage;
import com.bungii.web.pages.admin.Admin_TripDetailsPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_DriverRejectSteps extends DriverBase {
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    Driver_LoginPage driver_LoginPage= new Driver_LoginPage();
    private static LogUtility logger = new LogUtility(Admin_DriverRejectSteps.class);
    ActionManager action = new ActionManager();

    @When("^I click on \"([^\"]*)\" link$")
    public void i_click_on_something_link(String link) throws Throwable {
        try{
            switch(link) {
                case "Reject Application":
                    action.click(admin_DriverVerificationPage.Link_RejectApplication());
                    break;
                case "Login":
                    action.click(driver_LoginPage.Tab_LogIn());
                    break;
                case "Manually End Bungii":
                    action.click(admin_TripDetailsPage.Link_ManuallyEndBungii());
                    break;
                case "Privacy Policy":
                    action.click(admin_TripDetailsPage.Link_PrivacyPolicy());
                    break;
                }
                log("I click on "+link+" link", "I have clicked on "+link+" link", false);
            } catch(Exception e){
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I reject the \"([^\"]*)\"confirm action$")
    public void i_reject_the_somethingconfirm_action(String strArg1) throws Throwable {
        try{
        action.click(admin_DriverVerificationPage.Button_DriverConfirmReject_No());
        log("I reject the "+strArg1+ " confirm action",
                "I rejected the "+strArg1+ " confirm action", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I check if a validation message \"([^\"]*)\" is shown$")
    public void i_check_if_a_validation_message_something_is_shown(String strArg1) throws Throwable {
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Validation_Message_PleaseAddRejectionReason(),"I check if a validation message is displayed","Validation message is displayed","Validation message is not displayed");
///remove
    }

    @When("^I do not enter the reject reason$")
    public void i_do_not_enter_the_reject_reason() throws Throwable {
        try{
        admin_DriverVerificationPage.Textinput_ReasonforRejectDriverApplication().clear();
        Thread.sleep(5000);
        log("I do not enter the reject reason",
                "^I did not enter the reject reason", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I check if \"([^\"]*)\" confirm action is shown$")
    public void i_check_if_something_confirm_action_is_shown(String strArg1) throws Throwable {
        try{
        switch(strArg1)
        {
            case "Driver Reject Application":
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Popup_RejectApplication(), "Driver Reject Application message should be available","Driver Reject Application message is displayed","Driver Reject Application message is not available");
                break;
        }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }

    @And("^Manually end bungii link is removed for live trips$")
    public void manually_end_bungii_link_is_removed_for_live_trips() throws Throwable {
        try{
            testStepAssert.isNotElementDisplayed(admin_TripDetailsPage.findElement("btnEndPickup", PageBase.LocatorType.Id,true), "Manually end bungii link should not be displayed" , "Manually end bungii link is NOT displayed" , "Manually end bungii link is displayed");
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
