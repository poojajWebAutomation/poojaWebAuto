package com.bungii.ios.stepdefinitions.driver;

import com.bungii.SetupManager;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.BungiiDetailsPage;
import com.bungii.ios.pages.driver.BungiiRequestPage;
import com.bungii.ios.stepdefinitions.customer.SignupSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DecimalFormat;

import static com.bungii.common.manager.ResultManager.error;

public class BungiiRequestSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    ActionManager action = new ActionManager();
    private BungiiRequestPage bungiiRequestPage=new BungiiRequestPage();
    DbUtility dbUtility = new DbUtility();
    @Then("\"([^\"]*)\" should be displayed on Bungii request screen")
    public void i_click_something_button_on_forgot_password_screen_on_driver_app(String option) {
        try {
            String expectedPickUpLocationLineOne="",expectedPickUpLocationLineTwo="",expectedDropLocationLineOne="",expectedDropLocationLineTwo="",expectedTripNoOfDriver="";
            String pickUpLocationLine1="",pickUpLocationLine2="",dropUpLocationLine1="",dropUpLocationLine2="",estimate="",truncValue="";
            double flestimate,transactionFee,estimatedDriverCut;

            switch (option) {
                case "correct stack trip details":
                    logger.detail(SetupManager.getDriver().getPageSource());
                    expectedPickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));expectedPickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));
                            expectedDropLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));expectedDropLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")); expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO") ? "DUO" : "SOLO";
                    pickUpLocationLine1 = action.getNameAttribute(bungiiRequestPage.TextBox_Pickup_LineOne());pickUpLocationLine2= action.getNameAttribute(bungiiRequestPage.TextBox_Pickup_LineTwo());
                    dropUpLocationLine1 = action.getNameAttribute(bungiiRequestPage.TextBox_Drop_LineOne());dropUpLocationLine2 = action.getNameAttribute(bungiiRequestPage.TextBox_Drop_LineTwo());
                    testStepVerify.isTrue(pickUpLocationLine1.equals(expectedPickUpLocationLineOne) && pickUpLocationLine2.equals(expectedPickUpLocationLineTwo),

                            "Pick up address should be " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo, "Pick up address is " + pickUpLocationLine1+pickUpLocationLine2,
                            "Expected pickup address is " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo + ", but actual is" + pickUpLocationLine1+pickUpLocationLine2);
                    testStepVerify.isTrue(dropUpLocationLine1.equals(expectedDropLocationLineOne) &&  dropUpLocationLine2.equals(expectedDropLocationLineTwo),

                            "Drop address should be " + expectedDropLocationLineOne +expectedDropLocationLineTwo, "Drop address is " + dropUpLocationLine1 +dropUpLocationLine2,
                            "Expected Drop address is " + expectedDropLocationLineOne +expectedDropLocationLineTwo + ", but actual is" + dropUpLocationLine1 +dropUpLocationLine2);
                    testStepVerify.isElementEnabled(bungiiRequestPage.Text_DistanceTag(),"TO PICKUP tag should be displayed");
                    testStepVerify.isElementEnabled(bungiiRequestPage.Text_EstimatedEarningTag(),"Earning tag should be displayed");

                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_ValueDistance(),(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
                    flestimate=Double.valueOf(estimate.replace("~$","").trim());
                    transactionFee=(flestimate*0.029)+0.3;
                    estimatedDriverCut=(0.7*flestimate)-transactionFee;
                    truncValue = new DecimalFormat("#.00").format(estimatedDriverCut);
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_EstimatedEarningValue(),"~$"+truncValue);
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_Reject(),"Reject button should be displayed");
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_Accept(),"Accept button should be displayed");
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_NavigationBar(),"STACKED BUNGII REQUEST");
                    break;
                case "correct scheduled trip details":
                    logger.detail(SetupManager.getDriver().getPageSource());
                    expectedPickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));expectedPickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));
                            expectedDropLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));expectedDropLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")); expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO") ? "DUO" : "SOLO";
                    pickUpLocationLine1 = action.getNameAttribute(bungiiRequestPage.TextBox_Pickup_LineOne());pickUpLocationLine2= action.getNameAttribute(bungiiRequestPage.TextBox_Pickup_LineTwo());
                    dropUpLocationLine1 = action.getNameAttribute(bungiiRequestPage.TextBox_Drop_LineOne());dropUpLocationLine2 = action.getNameAttribute(bungiiRequestPage.TextBox_Drop_LineTwo());
                    testStepVerify.isTrue(pickUpLocationLine1.equals(expectedPickUpLocationLineOne) && pickUpLocationLine2.equals(expectedPickUpLocationLineTwo),

                            "Pick up address should be " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo, "Pick up address is " + pickUpLocationLine1+pickUpLocationLine2,
                            "Expected pickup address is " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo + ", but actual is" + pickUpLocationLine1+pickUpLocationLine2);
                    testStepVerify.isTrue(dropUpLocationLine1.equals(expectedDropLocationLineOne) &&  dropUpLocationLine2.equals(expectedDropLocationLineTwo),

                            "Drop address should be " + expectedDropLocationLineOne +expectedDropLocationLineTwo, "Drop address is " + dropUpLocationLine1 +dropUpLocationLine2,
                            "Expected Drop address is " + expectedDropLocationLineOne +expectedDropLocationLineTwo + ", but actual is" + dropUpLocationLine1 +dropUpLocationLine2);
                    testStepVerify.isElementEnabled(bungiiRequestPage.Text_EstimatedEarningTag(),"Earning tag should be displayed");

                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_ValueDistance(),(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
                    flestimate=Double.valueOf(estimate.replace("~$","").trim());
                    transactionFee=(flestimate*0.029)+0.3;
                    estimatedDriverCut=(0.7*flestimate)-transactionFee;
                    truncValue = new DecimalFormat("#.00").format(estimatedDriverCut);
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_EstimatedEarningValue(),"~$"+truncValue);
                    String tripTime =(String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE_TIME_LOAD_TIME");
                    if(tripTime.equalsIgnoreCase(""))
                        tripTime =(String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE_TIME");
                    tripTime= tripTime.replaceAll("  "," ");
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_ValueTripTime(),tripTime);

                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_Reject(),"Reject button should be displayed");
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_Accept(),"Accept button should be displayed");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("I check the status for {string} in db")
    public void iCheckTheStatusForInDb(String checkingParameter) {
    try {
        String pickUpRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        switch (checkingParameter) {
            case "same day payment":
            String driverOne = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
            if(driverOne.equalsIgnoreCase("")){
                driverOne=(String)cucumberContextManager.getScenarioContext("DRIVER_PHONE_NUMBER");
            }
            String disbursmentTypeDriverOne = dbUtility.getDisbursementType(pickUpRef, driverOne);
            testStepAssert.isEquals(disbursmentTypeDriverOne, PropertyUtility.getDataProperties("same.day.payment.disbursement.type.value"),
                    "Correct disbursement type value should be set for same day payment setting",
                    "Correct disbursement type value is set for same day payment setting",
                    "Incorrect disbursement type value is set for same day payment setting");
            break;
            case "same day payment-external reference":
                String externalReference = dbUtility.getExternalRefernce(pickUpRef);
                testStepAssert.isTrue(externalReference==null,
                        "The external reference should be null.",
                        "The external reference is null.");
            break;
            case "weekly payment":
                String driver = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
                String disbursmentTypeDriver = dbUtility.getDisbursementType(pickUpRef,driver);
                testStepAssert.isEquals(disbursmentTypeDriver, PropertyUtility.getDataProperties("weekly.payment.disbursement.type.value"),
                        "Correct disbursement type value should be set for weekly payment setting",
                        "Correct disbursement type value is set for weekly payment setting",
                        "Incorrect disbursement type value is set for weekly payment setting");
            break;
            case "weekly payment-external reference":
                String externalRef = dbUtility.getExternalRefernce(pickUpRef);
                testStepAssert.isTrue(externalRef!=null,
                        "The external reference should not be null.",
                        "The external reference is null.");
            break;
        }
    }
    catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }
}
