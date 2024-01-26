package com.bungii.ios.stepdefinitions.driver;

import com.bungii.SetupManager;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.BungiiCompletedPage;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DecimalFormat;

import static com.bungii.common.manager.ResultManager.error;

public class BungiiCompletedSteps extends DriverBase {
    BungiiCompletedPage bungiiCompletePage;
    private static LogUtility logger = new LogUtility(BungiiCompletedSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility= new GeneralUtility();
    public BungiiCompletedSteps(BungiiCompletedPage bungiiCompletePage) {
        this.bungiiCompletePage = bungiiCompletePage;

    }
    double DRIVER_SHARE=0.7,TRANSACTION_FEE=0.029,TR_COST=0.3;

    @Then("^Bungii driver should see \"([^\"]*)\" on Bungii completed page$")
    public void bungii_driver_should_see_something_on_bungii_completed_page(String identifier) throws Throwable {
        try {Thread.sleep(5000);
            if (action.isAlertPresent()){ logger.detail("Alert message"+action.getAlertMessage());;SetupManager.getDriver().switchTo().alert().dismiss();   Thread.sleep(1000);        }

            switch (identifier.toLowerCase()) {
                case "correct details":
                    verifyBungiiCompletedPage();
                    verifyTripValue();
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
    public void verifyBungiiCompletedPage(){
        testStepVerify.isElementEnabled(bungiiCompletePage.Title_Status(),"'Bungii Completed' Navigation bar message should be displayed");
       // testStepVerify.isElementEnabled(bungiiCompletePage.Image_Dollar(),"'Dollar Image' should be displayed on Summary page","'Dollar Image' is displayed","'Dollar Image' is not displayed");
        //testStepVerify.isElementEnabled(bungiiCompletePage.Text_Label(),"'Cha-Ching' should be displayed on Summary page","'Cha-Ching' is displayed","'Cha-Ching' is not displayed");
     //   testStepVerify.isElementEnabled(bungiiCompletePage.Text_TotalTimeLabel(),"Total Time label should be displayed on Summary page");
      //  testStepVerify.isElementEnabled(bungiiCompletePage.Text_TotalDistanceLabel(),"Total Distance label should be displayed on Summary page");
        testStepVerify.isElementEnabled(bungiiCompletePage.Text_TotalEarningsLabel(),"Total Earning label should be displayed on Summary page");
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

        String truncValue = new DecimalFormat("#.##").format(bungiiDriver);
        String tripDistance =(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
        String totalEarning =action.getValueAttribute(bungiiCompletePage.Text_TotalEarnings());
/*
        String tripTime =utility.getActualTime();

        String totalTime=action.getValueAttribute(bungiiCompletePage.Text_TotalTime());
         String totalDistance=action.getValueAttribute(bungiiCompletePage.Text_TotalDistance());


        int intTotalTime=Integer.parseInt(tripTime);
        if(intTotalTime==1)
            testStepVerify.isTrue(totalTime.trim().contains(tripTime+"  min")||totalTime.trim().contains(tripTime+" min"),"Total time should contains "+tripTime+"minute");
        else
            testStepVerify.isTrue(totalTime.trim().contains(tripTime+"  mins")||totalTime.trim().contains(tripTime+" mins"),"Total time should contains "+tripTime+"minute");
        testStepVerify.isTrue(totalDistance.equalsIgnoreCase(tripDistance),"Total Distance should contains"+tripDistance+" miles");
     //   testStepVerify.isTrue(toatlEarning.equalsIgnoreCase("$"+truncValue),"Total Earning should contains $"+truncValue);
     */
        testStepVerify.isTrue(totalEarning.contains("$")&& (Double.parseDouble(totalEarning.trim().replace("$",""))==Double.parseDouble(truncValue)),"Total Earning should contains $"+truncValue);


    }
}
