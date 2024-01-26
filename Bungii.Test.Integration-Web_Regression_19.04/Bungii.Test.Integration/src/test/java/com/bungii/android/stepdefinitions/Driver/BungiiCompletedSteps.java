package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.BungiiCompletedPage;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DecimalFormat;

import static com.bungii.common.manager.ResultManager.error;

public class BungiiCompletedSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(AvailableTripsSteps.class);
    BungiiCompletedPage bungiiCompletedPage = new BungiiCompletedPage();
    ActionManager action = new ActionManager();
    double DRIVER_SHARE=0.7,TRANSACTION_FEE=0.029,TR_COST=0.3;
    GeneralUtility utility= new GeneralUtility();
    @Then("^Bungii driver should see \"([^\"]*)\" on Bungii completed page$")
    public void bungii_driver_should_see_something_on_bungii_completed_page(String identifier) throws Throwable {
        try {

            switch (identifier.toLowerCase()) {
                case "correct details":
                    verifyBungiiCompletedPage();
                    verifyTripValue();
                    break;
                case "correct details for duo trip":
                    //action.scrollToBottom();
                    verifyTripValue();
                    break;
                case "summary":
                    verifyBungiiCompletedPage();
                    break;
                default:
                        error("UnImplemented Step or incorrect button name", "UnImplemented Step");break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }    }

    /**
     * Verify Static texts on Bungii Completed page
     */
        public void verifyBungiiCompletedPage() throws InterruptedException {
            Thread.sleep(6000);
            testStepVerify.isElementTextEquals(bungiiCompletedPage.Title_Status(),"BUNGII COMPLETED");

           // testStepVerify.isElementEnabled(bungiiCompletedSteps.Image_Dollar(),"'Dollar Image' should be displayed on Summary page","'Dollar Image' is displayed","'Dollar Image' is not displayed");
           // testStepVerify.isElementEnabled(bungiiCompletedSteps.Text_Label(),"'Cha-Ching' should be displayed on Summary page","'Cha-Ching' is displayed","'Cha-Ching' is not displayed");
           // testStepVerify.isElementEnabled(bungiiCompletedSteps.Text_TotalTimeLabel(),"Total Time label should be displayed on Summary page");
           // testStepVerify.isElementEnabled(bungiiCompletedSteps.Text_TotalDistanceLabel(),"Total Distance label should be displayed on Summary page");
           // testStepVerify.isElementTextEquals(bungiiCompletedSteps.Text_TotalEarningsLabel(),"Total Earnings:");
        }

    /**
     * Verify variable texts in Bungii Complete Page
     */
        public void verifyTripValue(){

            double bungiiCostCustomer=Double.parseDouble(((String)cucumberContextManager.getScenarioContext("BUNGII_COST_CUSTOMER")).replace("$",""));

            double bungiiDriver=(DRIVER_SHARE*bungiiCostCustomer-TRANSACTION_FEE*bungiiCostCustomer-TR_COST);
            String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            if(numberOfDriver.equalsIgnoreCase("duo"))
                bungiiDriver=((DRIVER_SHARE*bungiiCostCustomer-((TRANSACTION_FEE*bungiiCostCustomer*0.5+TR_COST)*2))/2);

            String truncValue = new DecimalFormat("#.00").format(bungiiDriver);
            String toatlEarning=action.getText(bungiiCompletedPage.Text_TotalEarnings());
            /*String tripDistance =(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
            //Trip distance value is displayed till 1 decimanl point

            int indexOfSpace=tripDistance.indexOf(" ");
            String strDistanceValue=tripDistance.substring(0,indexOfSpace);
            Double dblDistanceValue= new Double(strDistanceValue);
            String truncValueMiles = new DecimalFormat("#.0").format(dblDistanceValue);

            tripDistance = truncValueMiles+tripDistance.substring(indexOfSpace);
            String tripTime =utility.getActualTime();

            String totalTime=action.getText(bungiiCompletedSteps.Text_TotalTime()),
                    actualTotalDistance=action.getText(bungiiCompletedSteps.Text_TotalDistance()),
                    toatlEarning=action.getText(bungiiCompletedSteps.Text_TotalEarnings());
            testStepVerify.isTrue(totalTime.equalsIgnoreCase(tripTime+" mins") ||totalTime.equalsIgnoreCase(tripTime+" min"),"Total time should contains "+tripTime+" minute");
           // testStepVerify.isTrue(actualTotalDistance.equalsIgnoreCase(tripDistance),"Total Distance should be"+tripDistance);
            testStepVerify.isEquals(actualTotalDistance,tripDistance);*/
            testStepVerify.isTrue(toatlEarning.equalsIgnoreCase("$"+truncValue),"Total Earning be $"+truncValue);
        }

    @And("Bungii Driver selects customer experience as {string}")
    public void bungiiDriverSelectsCustomerExperienceAs(String arg0) {
            try{
                Thread.sleep(5000);
                switch (arg0){
                    case "Friendly":
                        action.scrollToBottom();
                        action.click(bungiiCompletedPage.Icon_RatingFriendly());
                        break;
                    case "Respectful":
                        action.click(bungiiCompletedPage.Icon_RatingRespectful());
                        break;
                    case "Clear Expectations":
                        action.click(bungiiCompletedPage.Icon_RatingClearExpectations());
                        break;
                    case "Smiled":
                        action.click(bungiiCompletedPage.Icon_RatingSmiled());
                        break;
                    case "Available":
                        action.click(bungiiCompletedPage.Icon_RatingAvailable());
                        break;
                    case "Grateful":
                        action.click(bungiiCompletedPage.Icon_RatingGrateful());
                        break;
                }
                action.click(bungiiCompletedPage.Button_SubmitRating());
            }
            catch(Exception e){
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step  Should be successful",
                        "Error performing step,Please check logs for more details", true);
            }
    }
}
