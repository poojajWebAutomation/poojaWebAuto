package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.web.utilityfunctions.DbUtility.*;

public class Admin_PartnerFirmSteps extends DriverBase {

    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    private static LogUtility logger = new LogUtility(Admin_PartnerFirmSteps.class);

    @Then("^Admin receives \"([^\"]*)\" trip email for \"([^\"]*)\" status$")
    public void admin_receives_something_trip_email_for_something_status(String emailSubject, String pickupStatus) throws Throwable {
       // throw new PendingException();

        try{
        String emailBody  =  utility.GetSpecificPlainTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"),emailSubject);
        if(emailBody== null)
        {
           testStepAssert.isFail("Email : "+ emailSubject + " not received");
        }
        String message = null;

        String pickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

        String pickupId = getPickupId(pickupRef);
        String pickupLocation = getPickupLocation(pickupId);
        String pickupAddress = null;

        String name = (String) cucumberContextManager.getScenarioContext("BUSINESSUSER_NAME");
        String customerName = null;
        if (!name.isEmpty())
            customerName = (String) cucumberContextManager.getScenarioContext("BUSINESSUSER_NAME")+ " Business User";
        else
            customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String customerPhone = getCustomerPhone((String) cucumberContextManager.getScenarioContext("BUSINESSUSER_NAME"),"Business User");
        message = utility.getExpectedFailedTripEmailContent(pickupId, pickupRef, pickupStatus, customerName, customerPhone, pickupLocation, pickupAddress);


        testStepAssert.isEquals(emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ",""), message.replaceAll(" ",""),"Email "+emailBody+" content should match", "Email  "+emailBody+" content matches", "Email "+emailBody+"  content doesn't match");

    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I ensure no driver accepts the trip$")
    public void i_ensure_no_driver_accepts_the_trip() throws Throwable {
        try{
      Thread.sleep(480000);
        log("I ensure no driver accepts the trip" ,
                "I have ensured no driver accepts the trip", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

}
