package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.*;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DecimalFormat;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class BungiiRequestSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(BungiiRequestSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    BungiiRequest bungiiRequestPage = new BungiiRequest();


    @And("^\"([^\"]*)\" should be displayed on Bungii request screen$")
    public void something_should_be_displayed_on_bungii_request_screen(String option) throws Throwable {
        try {
            String expectedPickUpLocationLineOne ="", expectedPickUpLocationLineTwo="", expectedDropLocationLineOne="",expectedDropLocationLineTwo="",expectedTripNoOfDriver="",
                    pickUpLocationLine1="", pickUpLocationLine2="", dropUpLocationLine1="", dropUpLocationLine2="",estimate = "",truncValue="";
            double flestimate, transactionFee, estimatedDriverCut;

            switch (option) {
                case "correct duo scheduled trip details":
                    expectedPickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));
                    expectedPickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));

                    String Pickupaddress[]=expectedPickUpLocationLineTwo.split(", ");
                    expectedPickUpLocationLineTwo=Pickupaddress[0];
                    expectedPickUpLocationLineTwo=expectedPickUpLocationLineTwo+", United States";
                    expectedDropLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));
                    expectedDropLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2"));
                    String DropOffaddress[]=expectedDropLocationLineTwo.split(", ");
                    expectedDropLocationLineTwo=DropOffaddress[0];
                    expectedDropLocationLineTwo=expectedDropLocationLineTwo+", United States";

                    expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO") ? "DUO" : "SOLO";
                    pickUpLocationLine1 = action.getText(bungiiRequestPage.Text_PickupLocation_LineOne1());
                    pickUpLocationLine2= action.getText(bungiiRequestPage.Text_PickupLocation_LineTwo2());
                    dropUpLocationLine1 = action.getText(bungiiRequestPage.Text_DropOffLocation_LineOne1());
                    dropUpLocationLine2 = action.getText(bungiiRequestPage.Text_DropOffLocation_LineTwo2());
                    testStepVerify.isTrue(pickUpLocationLine1.equals(expectedPickUpLocationLineOne) && pickUpLocationLine2.equals(expectedPickUpLocationLineTwo),
                            "Pick up address should be " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo, "Pick up address is " + pickUpLocationLine1+pickUpLocationLine2,
                            "Expected pickup address is " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo + ", but actual is" + pickUpLocationLine1+pickUpLocationLine2);
                    testStepVerify.isTrue(dropUpLocationLine1.equals(expectedDropLocationLineOne) &&  dropUpLocationLine2.equals(expectedDropLocationLineTwo),
                            "Drop address should be " + expectedDropLocationLineOne +expectedDropLocationLineTwo, "Drop address is " + dropUpLocationLine1 +dropUpLocationLine2,
                            "Expected Drop address is " + expectedDropLocationLineOne +expectedDropLocationLineTwo + ", but actual is" + dropUpLocationLine1 +dropUpLocationLine2);
                    testStepVerify.isElementEnabled(bungiiRequestPage.Text_Earning(),"Earning tag should be displayed");
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_DistanceValue(),(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
                    flestimate=Double.valueOf(estimate.replace("$","").replace("~","").trim());
                    //transactionfeedifferentforsoloandduo
                    transactionFee=((flestimate*0.029*0.5)+0.3)*2;
                    estimatedDriverCut=(0.7*flestimate)-transactionFee;
                    truncValue = new DecimalFormat("#.00").format(estimatedDriverCut/2);
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_ValueEarning(),"~$"+truncValue);
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_StartBungii(),"START BUNGII button should be displayed");
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_CancelBungii(),"CANCEL BUNGII button should be displayed");
                    break;

                case "correct scheduled trip details":
                     expectedPickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));
                     expectedPickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));
                     expectedDropLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));
                     expectedDropLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2"));
                     expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO") ? "DUO" : "SOLO";
                     pickUpLocationLine1 = action.getText(bungiiRequestPage.Text_PickupLocation_LineOne());
                     pickUpLocationLine2= action.getText(bungiiRequestPage.Text_PickupLocation_LineTwo());
                     dropUpLocationLine1 = action.getText(bungiiRequestPage.Text_DropOffLocation_LineOne());
                     dropUpLocationLine2 = action.getText(bungiiRequestPage.Text_DropOffLocation_LineTwo());
                    testStepVerify.isTrue(pickUpLocationLine1.equals(expectedPickUpLocationLineOne) && pickUpLocationLine2.equals(expectedPickUpLocationLineTwo),
                            "Pick up address should be " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo, "Pick up address is " + pickUpLocationLine1+pickUpLocationLine2,
                            "Expected pickup address is " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo + ", but actual is" + pickUpLocationLine1+pickUpLocationLine2);
                    testStepVerify.isTrue(dropUpLocationLine1.equals(expectedDropLocationLineOne) &&  dropUpLocationLine2.equals(expectedDropLocationLineTwo),
                            "Drop address should be " + expectedDropLocationLineOne +expectedDropLocationLineTwo, "Drop address is " + dropUpLocationLine1 +dropUpLocationLine2,
                            "Expected Drop address is " + expectedDropLocationLineOne +expectedDropLocationLineTwo + ", but actual is" + dropUpLocationLine1 +dropUpLocationLine2);
                    testStepVerify.isElementEnabled(bungiiRequestPage.Text_Earning(),"Earning tag should be displayed");
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_ValueDistance(),(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
                    flestimate=Double.valueOf(estimate.replace("$","").replace("~","").trim());
                    transactionFee=(flestimate*0.029)+0.3;
                    estimatedDriverCut=(0.7*flestimate)-transactionFee;
                    truncValue = new DecimalFormat("#.00").format(estimatedDriverCut);
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_ValueEarning(),"~$"+truncValue); //~
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_Reject(),"Reject button should be displayed");
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_Accept(),"Accept button should be displayed");
                    //testStepVerify.isElementTextEquals(bungiiRequestPage.Navigation_Header(),"STACKED BUNGII REQUEST"); normal trip not stacked
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I tap on the \"([^\"]*)\" Button on Bungii Request screen$")
    public void i_tap_on_the_something_button_on_bungii_request_screen(String option) throws Throwable {
        try {
            switch (option) {
                case "ACCEPT":
                    action.click(bungiiRequestPage.Button_Accept());
                    break;
                case "REJECT":
                    action.click(bungiiRequestPage.Button_Reject());
                    break;
            }
            log("I should be able to click on "+option,"I am able to click on "+option,false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }    }

}
