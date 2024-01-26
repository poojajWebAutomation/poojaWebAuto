package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_DriverVerificationPage;
import com.bungii.web.pages.admin.Admin_MenuLinksPage;
import com.bungii.web.pages.admin.Admin_ScheduledTripsPage;
import com.bungii.web.pages.driver.Driver_DetailsPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_DriverVerificationSteps extends DriverBase {
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();
    Admin_MenuLinksPage admin_MenuLinksPage = new Admin_MenuLinksPage();
    Driver_DetailsPage driver_detailsPage = new Driver_DetailsPage();
    Admin_ScheduledTripsPage admin_scheduledTripsPage=new Admin_ScheduledTripsPage();


    GeneralUtility utility = new GeneralUtility();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_DriverVerificationSteps.class);

    @And("^I check if each field has an \"([^\"]*)\" option$")
    public void i_check_if_each_field_has_an_something_option(String p0) throws Throwable {
        try{
        switch(p0)
        {
            case "accept":
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Driver Picture",true),"I accept Driver Pic","I accepted Driver Pic","Error in accepting Driver pic");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Driver Picture",true),"I accept Driver Pic","I accepted Driver Pic","Error in accepting Driver pic");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("First Name",true),"I accept Driver Firstname","I accepted Driver Firstname","Error in accepting Driver Firstname");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Last Name",true),"I accept Driver Lastname","I accepted Driver Lastname","Error in accepting Driver Lastname");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Street address",true),"I accept Driver street address","I accepted Driver street address","Error in accepting Driver street address");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("City",true),"I accept Driver city","I accepted Driver city","Error in accepting Driver city");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("State",true),"I accept Driver State","I accepted Driver State","Error in accepting Driver State");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Zip code",true),"I accept Driver ZIP","I accepted Driver ZIP","Error in accepting Driver ZIP");
               // testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverSSN(),"I accept Driver SSN","I accepted Driver SSN","Error in accepting Driver SSN");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Birthday",true),"I accept Driver birthday","I accepted Driver birthday","Error in accepting Driver birthday");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Pickup images",true),"I accept Driver pickup images","I accepted Driver pickup images","Error in accepting Driver pickup images");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Pickup make",true),"I accept Driver pickup make","I accepted Driver pickup make","Error in accepting Driver pickup make");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Pickup model",true),"I accept Driver pickup model","I accepted Driver pickup model","Error in accepting Driver pickup model");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Pickup year",true),"I accept Driver pickup year","I accepted Driver pickup year","Error in accepting Driver pickup year");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Pickup license number",true),"I accept Driver pickup licence","I accepted Driver pickup licence","Error in accepting Driver pickup licence");;
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("License image",true),"I accept Driver licence image","I accepted Driver licence image","Error in accepting Driver licence image");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("License number",true),"I accept Driver licence number","I accepted Driver licence number","Error in accepting Driver licence number");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("License expiration",true),"I accept Driver licence expiration","I accepted Driver licence expiration","Error in accepting Driver licence expiration");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Insurance image",true),"I accept Driver insuration image","I accepted Driver insuration image","Error in accepting Driver insuration image");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Insurance Expiration",true),"I accept Driver insuration expiration","I accepted Driver insuration expiration","Error in accepting Driver insuration expiration");
                //testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverRoutingNumber(),"I accept Driver routing number","I accepted Driver routing number","Error in accepting Driver routing number");
                //testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverAccountNumber(),"I accept Driver account number","I accepted Driver account number","Error in accepting Driver account number");
                break;

            case "reject":
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Driver Picture",true),"I reject Driver Pic","I rejected Driver Pic","Error in rejecting Driver pic");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("First Name",true),"I reject Driver Firstname","I rejected Driver Firstname","Error in rejecting Driver Firstname");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Last Name",true),"I reject Driver Lastname","I rejected Driver Lastname","Error in rejecting Driver Lastname");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Street address",true),"I reject Driver street address","I rejected Driver street address","Error in rejecting Driver street address");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("City",true),"I reject Driver city","I rejected Driver city","Error in rejecting Driver city");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("State",true),"I reject Driver State","I rejected Driver State","Error in rejecting Driver State");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Zip code",true),"I reject Driver ZIP","I rejected Driver ZIP","Error in rejecting Driver ZIP");
               // testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_SSN(),"I reject Driver SSN","I rejected Driver SSN","Error in rejecting Driver SSN");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Birthday",true),"I reject Driver birthday","I rejected Driver birthday","Error in rejecting Driver birthday");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Pickup images",true),"I reject Driver pickup images","I rejected Driver pickup images","Error in rejecting Driver pickup images");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Pickup make",true),"I reject Driver pickup make","I rejected Driver pickup make","Error in rejecting Driver pickup make");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Pickup model",true),"I reject Driver pickup model","I rejected Driver pickup model","Error in rejecting Driver pickup model");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Pickup year",true),"I reject Driver pickup year","I rejected Driver pickup year","Error in rejecting Driver pickup year");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Pickup license number",true),"I reject Driver pickup licence","I rejected Driver pickup licence","Error in rejecting Driver pickup licence");;
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("License image",true),"I reject Driver licence image","I rejected Driver licence image","Error in rejecting Driver licence image");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("License number",true),"I reject Driver licence number","I rejected Driver licence number","Error in rejecting Driver licence number");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("License expiration",true),"I reject Driver licence expiration","I rejected Driver licence expiration","Error in rejecting Driver licence expiration");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Insurance image",true),"I reject Driver insuration image","I rejected Driver insuration image","Error in rejecting Driver insuration image");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_DriverDetails("Insurance Expiration",true),"I reject Driver insuration expiration","I rejected Driver insuration expiration","Error in rejecting Driver insuration expiration");
                //testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_RoutingNumber(),"I reject Driver routing number","I rejected Driver routing number","Error in rejecting Driver routing number");
                //testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_AccountNumber(),"I reject Driver account number","I rejected Driver account number","Error in rejecting Driver account number");
                break;
        }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }


    @And("^I verify and approve the \"([^\"]*)\" field$")
    public void i_verify_and_approve_the_something_field(String strArg1) throws Throwable {
        try{
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Driver Picture",true));
        //action.click(admin_DriverVerificationPage.Verify_Approve_DriverPic());
        log("I verify and approve the "+strArg1+" field",
                "I verified and approve the "+strArg1+" field", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I verify and reject the \"([^\"]*)\" field$")
    public void i_verify_and_reject_the_something_field(String strArg1) throws Throwable {
        try{
        switch(strArg1)
        {
            case "Birthday":
                action.click(admin_DriverVerificationPage.Verify_DriverDetails("Birthday",false));
                //action.click(admin_DriverVerificationPage.Verify_Reject_Birthday());
                break;
            case "Driver Picture":
                action.click(admin_DriverVerificationPage.Verify_DriverDetails("Driver Picture",false));
                //action.click( admin_DriverVerificationPage.Verify_Reject_DriverPicture());
                break;
        }
        log("I verify and reject the "+strArg1+" field",
                "I verified and reject the "+strArg1+" field", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I check if the status has been changed to \"([^\"]*)\"$")
    public void i_check_if_the_status_has_been_changed_to_something(String strArg1) throws Throwable {
        try{
        switch(strArg1)
        {
            case "accepted":
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Status_Accepted(),"Status accepted should be displayed","Status accepted is displayed","Status accepted is not displayed");
                break;

            case "rejected":
                action.sendKeys(admin_DriverVerificationPage.Textinput_ReasonforReject_DriverDetails("Birthday","AcceptedRejected"),"Invalid DOB");
                log("I enter rejection reason in field",
                        "I have entered rejection reason in field", false);
                break;
            case "No Driver(s) Found":
                action.waitUntilIsElementExistsAndDisplayed(admin_scheduledTripsPage.Text_TripStatus(),(long) 5000);
                testStepAssert.isEquals(admin_scheduledTripsPage.Text_TripStatus().getText(),"No Driver(s) Found",
                        "The status should be No Driver(s) Found",
                        "The status is No Driver(s) Found",
                        "Incorrect status displayed");
                break;
        }
        log("The status should be changed","The status is changed",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I click and reset the status of \"([^\"]*)\" field$")
    public void i_click_and_reset_the_status_of_something_field(String strArg1) throws Throwable {
        try{
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Driver Picture",false));
        //action.click(admin_DriverVerificationPage.Verify_Reject_DriverPicture());
        log("I click and reset the status of "+strArg1+" field",
                "I clicked and reseted the status of "+strArg1+" field", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I check if the status of \"([^\"]*)\" field has been changed to rejected$")
    public void i_check_if_the_status_of_something_field_has_been_changed_to_rejected(String strArg1) throws Throwable {
        try{
        action.sendKeys(admin_DriverVerificationPage.Textinput_ReasonforRejection_DriverPicture(),"Clear picture needed");
        log("I check if the status of "+strArg1+" field has been changed to rejected",
                "I checked if the status of "+strArg1+" field has been changed to rejected", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click and reset the Rejected status of \"([^\"]*)\" field$")
    public void i_click_and_reset_the_rejected_status_of_something_field(String strArg1) throws Throwable {
        try{
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Driver Picture",false));
        //action.click(admin_DriverVerificationPage.Verify_Reject_DriverPicture());
        log("I click and reset the Rejected status of "+strArg1+" field",
                "I clicked and reseted the Rejected status of "+strArg1+" field", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^I verify that the status has been reset$")
    public void i_verify_that_the_status_has_been_reset() throws Throwable {
        testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Status_Accepted(),"Status accepted should not be displayed","Status accepted is not displayed","Status accepted is displayed");
    }

    @And("^I check if the Save and cancel buttons are seen by default$")
    public void i_check_if_the_save_and_cancel_buttons_are_seen_by_default() throws Throwable {
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Button_Save(),"Save should be displayed","Save is displayed","Save is not displayed");
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Button_Cancel(),"Cancel should be displayed","Cancel is displayed","Cancel is not displayed");

    }

    @And("^I check if a Cancel confirmation message is shown$")
    public void i_check_if_a_cancel_confirmation_message_is_shown() throws Throwable {
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Popup_ConfirmCancelDriverVerificationApplication(),"Confirm Cancel should be displayed","Confirm Cancel is displayed","Confirm Cancel is not displayed");
    }
    @And("^I check if a Cancel confirmation message is not shown$")
    public void i_check_if_a_cancel_confirmation_message_is_not_shown() throws Throwable {
        testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Popup_ConfirmCancelDriverVerificationApplication(),"Confirm Cancel should not be displayed","Confirm Cancel is not displayed","Confirm Cancel is displayed");
    }
    @And("^I check if admin gets directed to dashboard$")
    public void i_check_if_admin_gets_directed_to_dashboard() throws Throwable {
        testStepAssert.isElementDisplayed(admin_MenuLinksPage.Menu_Dashboard(),"Menu dashboard should be displayed","Menu dashboard is not displayed","Menu dashboard is displayed");
    }

    @Then("^The accepted tick is removed for \"([^\"]*)\" field previously accepted by admin$")
    public void the_accepted_tick_is_removed_for_something_field_previously_accepted_by_admin(String strArg1) throws Throwable {
        try{
        String s = admin_DriverVerificationPage.Button_VerifyDOB().getAttribute("class");
        testStepAssert.isTrue(admin_DriverVerificationPage.Button_VerifyDOB().getAttribute("class").equalsIgnoreCase("btn btn-default ok"),"DOB field is not highligted ","DOB field is  highligted ");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }

    @Then("^I check if driver SSN is masked$")
    public void i_check_if_driver_ssn_is_masked() throws Throwable {
        try{
        String actualSSN= driver_detailsPage.TextBox_SSN().getAttribute("value");
        String expectedSSN= PropertyUtility.getDataProperties("driver.social.security.number");

        testStepAssert.isEquals(actualSSN, expectedSSN, expectedSSN+ " is present.",
                "SSN is marked and displayed.", "SSN displayed is incorrect.");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
}
