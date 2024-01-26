package com.bungii.web.stepdefinitions.partnerManagement;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partnerManagement.PartnerManagement_Email;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Keys;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Partner_Management_Email_Steps extends DriverBase {
    ActionManager action = new ActionManager();
    PartnerManagement_Email Page_PartnerManagement_Email = new PartnerManagement_Email();
    private static LogUtility logger = new LogUtility(Partner_Management_Email_Steps.class);


    @And("^I search for \"([^\"]*)\" partner on partner management$")
    public void i_search_for_something_partner_on_partner_management(String partner) throws Throwable {
        try{
        Thread.sleep(2000);
        action.clearSendKeys(Page_PartnerManagement_Email.TextBox_Search(),partner+ Keys.ENTER);
        cucumberContextManager.setScenarioContext("NameOfPartnerLocationOrPartnerPortalName",partner);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I add \"([^\"]*)\" as the new email$")
    public void i_add_something_as_the_new_email(String email) throws Throwable {
        try{
        Thread.sleep(3000);
        String oldEmailAddress = action.getAttributeValue(Page_PartnerManagement_Email.Text_OldEmailAddress());
        cucumberContextManager.setScenarioContext("Old Email",oldEmailAddress);
        switch (email){
            case "Primary email address":
                action.clearSendKeys(Page_PartnerManagement_Email.TextBox_AddNewEmail(), PropertyUtility.getDataProperties("qa.auto.test.email.address"));
                cucumberContextManager.setScenarioContext("NewEmail",PropertyUtility.getDataProperties("qa.auto.test.email.address"));
                break;
            case "Primary email address for edit":
                action.clearSendKeys(Page_PartnerManagement_Email.TextBox_AddNewEmail(), PropertyUtility.getDataProperties("qa.auto.test.email.address.edit"));
                cucumberContextManager.setScenarioContext("EditedEmail",PropertyUtility.getDataProperties("qa.auto.test.email.address"));
                break;
            case "Secondary email address":
                action.clearSendKeys(Page_PartnerManagement_Email.TextBox_AddNewEmail(),PropertyUtility.getDataProperties("DriverEmail"));
                cucumberContextManager.setScenarioContext("NewEmail",PropertyUtility.getDataProperties("DriverEmail"));
                break;
        }
        log("I should be able to add the email address "+cucumberContextManager.getScenarioContext("NewEmail"),"I could add the email address "+cucumberContextManager.getScenarioContext("NewEmail"),false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^All email addresses should be displayed for the mentioned partner$")
    public void all_email_addresses_should_be_displayed_for_the_mentioned_partner() throws Throwable {
        try{
        String newlyAddedEmail =(String) cucumberContextManager.getScenarioContext("NewEmail");
        String partner = (String) cucumberContextManager.getScenarioContext("NameOfPartnerLocationOrPartnerPortalName") ;
        String emailAddressStoredInDB[] = com.bungii.web.utilityfunctions.DbUtility.getAllEmailAddress(partner).split(",");
        boolean isNewlyAddedEmailPresent =false;
        for(String differentEmailAddresses:emailAddressStoredInDB){
            if(differentEmailAddresses.contentEquals(newlyAddedEmail)){
                testStepAssert.isTrue(true,"Newly added Email "+newlyAddedEmail+ " should be present ","Newly added Email "+newlyAddedEmail+ " is present ","Newly added Email "+differentEmailAddresses+ " is not present ");
                isNewlyAddedEmailPresent =true;
            }

        }
        if(!isNewlyAddedEmailPresent){
            testStepAssert.isFail("Newly added email is not present");

        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^Edited email addresses should be displayed for the mentioned partner$")
    public void edited_email_addresses_should_be_displayed_for_the_mentioned_partner() throws Throwable {
        try{
            String newlyEditedEmail =(String) cucumberContextManager.getScenarioContext("EditedEmail");
            String partner = (String) cucumberContextManager.getScenarioContext("NameOfPartnerLocationOrPartnerPortalName") ;
            String emailAddressStoredInDB[] = com.bungii.web.utilityfunctions.DbUtility.getAllEmailAddress(partner).split(",");
            boolean isEditedEmailPresent =false;
            for(String differentEmailAddresses:emailAddressStoredInDB){
                if(differentEmailAddresses.contentEquals(newlyEditedEmail)){
                    testStepAssert.isTrue(true,"Newly Edited Email "+newlyEditedEmail+ " should be present ","Newly Edited Email "+newlyEditedEmail+ " is present ","Newly Edited Email "+newlyEditedEmail+ " is not present ");
                    isEditedEmailPresent =true;
                }

            }
            if(!isEditedEmailPresent){
                testStepAssert.isFail("Newly edited email is not present");

            }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
