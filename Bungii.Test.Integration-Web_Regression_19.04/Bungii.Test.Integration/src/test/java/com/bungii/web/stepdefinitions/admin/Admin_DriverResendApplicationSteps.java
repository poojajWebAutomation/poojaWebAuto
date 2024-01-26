package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.web.pages.admin.Admin_DriverVerificationPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_DriverResendApplicationSteps extends DriverBase {
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_DriverResendApplicationSteps.class);


    @When("^I verify and reject the invalid verification fields$")
    public void i_verify_and_reject_the_invalid_verification_fields() throws Throwable {
        try{
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Driver Picture",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("First Name",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Last Name",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Street address",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("City",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("State",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Zip code",true));
            //  action.click(admin_DriverVerificationPage.Verify_Approve_DriverSSN());

            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Birthday",false));
            action.sendKeys(admin_DriverVerificationPage.Textinput_ReasonforReject_DriverDetails("Birthday","AcceptedRejected"), "Invalid DOB");
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup images",false));
            action.sendKeys(admin_DriverVerificationPage.Textinput_ReasonforReject_DriverDetails("Pickup images","AcceptedRejected"), "Invalid Pickup Images");

            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup make",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup model",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup year",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup license number",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("License image",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("License number",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("License expiration",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Insurance image",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Insurance Expiration",true));

            if(action.isElementPresent(admin_DriverVerificationPage.Verify_DriverDetails("Routing Number",true, true))) {
                action.click(admin_DriverVerificationPage.Verify_DriverDetails("Routing Number",true));
            }
            if(action.isElementPresent(admin_DriverVerificationPage.Verify_DriverDetails("Account Number",true,true))) {
                action.click(admin_DriverVerificationPage.Verify_DriverDetails("Account Number",true));
            }

            log("I verify and reject the invalid verification fields",
                    "I have verified and reject the invalid verification fields", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
