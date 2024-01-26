package com.bungii.ios.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.DashBoardPage;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.common.manager.ResultManager.error;

public class DashBoardSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    DashBoardPage dashBoardPage;
    ActionManager action = new ActionManager();
    ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();
    private DbUtility dbUtility = new DbUtility();

    public DashBoardSteps(DashBoardPage dashBoardPage) {
        this.dashBoardPage = dashBoardPage;
    }

    @And("^I get the estimated charge \"([^\"]*)\"$")
    public void i_get_the_estimated_charge_something(String type) throws Throwable {
        try{
            switch (type)
            {
                case "for customer":
                    String estimateCharge = action.getText(scheduledTripsPage.Text_EstimateCharge()).substring(1);
                    cucumberContextManager.setScenarioContext("CUSTOMER_CHARGE",estimateCharge);
                    break;
            }
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I get the driver earnings displayed for \"([^\"]*)\"$")
    public void i_get_the_driver_earnings_displayed_for_something(String type) throws Throwable {
        try{
            switch (type)
            {
                case "solo":
                    String soloDriverEarnings = action.getText(scheduledTripsPage.Text_SoloDriverEarnings()).substring(1);
                    cucumberContextManager.setScenarioContext("SOLO_DRIVER_EARNING",soloDriverEarnings);
                    break;

                case "duo":
                    String duoDriver1Earnings = action.getText(scheduledTripsPage.Text_DuoDriver1Earnings()).substring(1);
                    cucumberContextManager.setScenarioContext("DUO_DRIVER1_EARNING",duoDriver1Earnings);
                    String duoDriver2Earnings = action.getText(scheduledTripsPage.Text_DuoDriver2Earnings()).substring(1);
                    cucumberContextManager.setScenarioContext("DUO_DRIVER2_EARNING",duoDriver2Earnings);
                    break;
            }
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I calculate the driver share and check for \"([^\"]*)\"$")
    public void i_calculate_the_driver_share_and_check_for_something(String type) throws Throwable {
       try {
           float estimateCustomerCharge = Float.parseFloat(((String) cucumberContextManager.getScenarioContext("CUSTOMER_CHARGE")).replaceAll("Price Override",""));
           switch (type)
           {
               case "solo":
                   float soloAmt= Float.parseFloat((dbUtility.getDriverShareSameTier()));
                   float merchantAmt=(float) (Math.round((estimateCustomerCharge-soloAmt)* 100.0) / 100.0);
                   float transFeeSolo= (float) (Math.round(((merchantAmt+soloAmt)*0.029+0.30)* 100.0) / 100.0);
                   float driverEarningsCalculated =(float) (Math.round((soloAmt-transFeeSolo)* 100.0) / 100.0);
                   float driverShareCalculatedRound = (float) (Math.round(driverEarningsCalculated * 100.0) / 100.0);
                   cucumberContextManager.setScenarioContext("CALCULATED_DRIVER_SHARE",driverShareCalculatedRound);
                   float driverShareDisplayed = Float.parseFloat((String) cucumberContextManager.getScenarioContext("SOLO_DRIVER_EARNING"));
                   testStepVerify.isTrue(driverShareCalculatedRound==driverShareDisplayed,
                           "The driver share calculated should be same as displayed",
                           "The driver share calculated is same as displayed",
                           "The driver share calculated is not same as displayed");
                   break;

               case "duo":
                   float duoAmt= Float.parseFloat((dbUtility.getDriverShareSameTier()));
                   float merchantAmount=(float) (Math.round((estimateCustomerCharge-(duoAmt+duoAmt))* 100.0) / 100.0);
                   float merchantAmountPerTF=(float) (Math.round((merchantAmount/2)* 100.0) / 100.0);
                   float transFeeDuo= (float)  (Math.round(((merchantAmountPerTF+duoAmt)*0.029+0.30)* 100.0) / 100.0);
                   float driverEarningsCalculatedDuo =  (float) (Math.round((duoAmt-transFeeDuo)* 100.0) / 100.0);
                   float driverShareCalculatedRoundDuo = (float) (Math.round(driverEarningsCalculatedDuo * 100.0) / 100.0);
                   cucumberContextManager.setScenarioContext("CALCULATED_DRIVER_SHARE_SAME_TIRE",driverShareCalculatedRoundDuo);
                   float driverShareDisplayedDriver1 = Float.parseFloat((String) cucumberContextManager.getScenarioContext("DUO_DRIVER1_EARNING"));
                   testStepAssert.isTrue(driverShareCalculatedRoundDuo==driverShareDisplayedDriver1,
                           "The driver share calculated should be same as displayed",
                           "The driver share calculated is same as displayed",
                           "The driver share calculated is not same as displayed");
                   float driverShareDisplayedDriver2 = Float.parseFloat((String) cucumberContextManager.getScenarioContext("DUO_DRIVER2_EARNING"));
                   testStepAssert.isTrue(driverShareCalculatedRoundDuo==driverShareDisplayedDriver2,
                           "The driver share calculated should be same as displayed",
                           "The driver share calculated is same as displayed",
                           "The driver share calculated is not same as displayed");
                   break;

               case "duo-different tier":
                   float duoDriver1Amt= Float.parseFloat((dbUtility.getDriverShareSameTier()));
                   float duoDriver2Amt= Float.parseFloat((dbUtility.getDriverShareDifferentTier()));
                   float merchantAmountDuo=estimateCustomerCharge-(duoDriver1Amt+duoDriver2Amt);
                   float merchantAmtPerTF=merchantAmountDuo/2;
                   float transFeeDuoDriver1= (float) ((merchantAmtPerTF+duoDriver1Amt)*0.029+0.30);
                   float transFeeDuoDriver2= (float) ((merchantAmtPerTF+duoDriver2Amt)*0.029+0.30);
                   float driver1EarningsCalculatedDuo = duoDriver1Amt-transFeeDuoDriver1;
                   float driver2EarningsCalculatedDuo = duoDriver2Amt-transFeeDuoDriver2;
                   float driver1ShareCalculatedRoundDuo = (float) (Math.round(driver1EarningsCalculatedDuo * 100.0) / 100.0);
                   float driver2ShareCalculatedRoundDuo = (float) (Math.round(driver2EarningsCalculatedDuo * 100.0) / 100.0);
                   cucumberContextManager.setScenarioContext("CALCULATED_DRIVER1_SHARE_DIFFERENT_TIRE",driver1ShareCalculatedRoundDuo);
                   cucumberContextManager.setScenarioContext("CALCULATED_DRIVER2_SHARE_DIFFERENT_TIRE",driver2ShareCalculatedRoundDuo);

                   float driverShareDisplayedDriver1Tier = Float.parseFloat((String) cucumberContextManager.getScenarioContext("DUO_DRIVER1_EARNING"));
                   testStepAssert.isTrue(driver1ShareCalculatedRoundDuo==driverShareDisplayedDriver1Tier,
                           "The driver share calculated should be same as displayed",
                           "The driver share calculated is same as displayed",
                           "The driver share calculated is not same as displayed");

                   float driverShareDisplayedDriver2Tire = Float.parseFloat((String) cucumberContextManager.getScenarioContext("DUO_DRIVER2_EARNING"));
                   testStepAssert.isTrue(driver2ShareCalculatedRoundDuo==driverShareDisplayedDriver2Tire,
                           "The driver share calculated should be same as displayed",
                           "The driver share calculated is same as displayed",
                           "The driver share calculated is not same as displayed");
                   break;

               case "changed address and service level":
                   float soloDriverAmt=Float.parseFloat((dbUtility.getDriverShareDifferentSeviceLevel()));
                   float merchantAmtChangedSL= (float) (Math.round((estimateCustomerCharge-soloDriverAmt)* 100.0) / 100.0);
                   float transFeeSoloChangedSL= (float) (Math.round((((merchantAmtChangedSL+soloDriverAmt)*0.029+0.30)* 100.0)) / 100.0);
                   float changedDriverEarningsCalculated =(float) (Math.round((soloDriverAmt-transFeeSoloChangedSL) * 100.0) / 100.0);
                   float changedDriverShareCalculatedRound = (float) (Math.round(changedDriverEarningsCalculated * 100.0) / 100.0);
                   cucumberContextManager.setScenarioContext("DRIVER_SHARE_FOR_CHANGED_SL_AND_ADDRESS",changedDriverShareCalculatedRound);
                   break;

               case "duo to solo conversion":
                   float duoToSoloAmt1= Float.parseFloat((dbUtility.getDriverShareSameTier()));
                   float duoToSoloAmt2= Float.parseFloat((dbUtility.getDriverShareDifferentTier()));
                   float processingFee= (float) ((float)(estimateCustomerCharge+0.30)*0.029);
                   float driverEarningsCalculatedDuoToSolo =(float) (Math.floor((duoToSoloAmt1+duoToSoloAmt2)-processingFee)* 100.0 / 100.0);
                   cucumberContextManager.setScenarioContext("CALCULATED_DRIVER_SHARE",driverEarningsCalculatedDuoToSolo);
                   float driverShareDisplayedDuoToSolo = Float.parseFloat((String) cucumberContextManager.getScenarioContext("SOLO_DRIVER_EARNING"));
                   testStepVerify.isTrue(driverEarningsCalculatedDuoToSolo==driverShareDisplayedDuoToSolo,
                           "The driver share calculated should be same as displayed",
                           "The driver share calculated is same as displayed",
                           "The driver share calculated is not same as displayed");
                   break;

           }
       }
       catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }
    @Then("^I verify the driver earnings displayed on driver app for \"([^\"]*)\"$")
    public void i_verify_the_driver_earnings_displayed_on_driver_app_for_something(String type) throws Throwable {
        try{
            switch (type)
            {
                case "solo":
                    float soloDriverEarnings = Float.parseFloat((action.getText(scheduledTripsPage.Text_SoloDriverEarningsApp()).substring(1)));
                    float driverShareCalculated =Float.parseFloat((String) cucumberContextManager.getScenarioContext("CALCULATED_DRIVER_SHARE"));
                    testStepVerify.isTrue(soloDriverEarnings==driverShareCalculated,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");
                    break;
                case "duo":
                    float duoDriver1Earnings = Float.parseFloat((action.getText(scheduledTripsPage.Text_DuoDriver1EarningsApp()).substring(1)));
                    float driverShareCalculatedDriver1 =Float.parseFloat((String) cucumberContextManager.getScenarioContext("CALCULATED_DRIVER_SHARE_SAME_TIRE"));
                    testStepAssert.isTrue(duoDriver1Earnings==driverShareCalculatedDriver1,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");
                    float duoDriver2Earnings = Float.parseFloat((action.getText(scheduledTripsPage.Text_DuoDriver2EarningsApp()).substring(1)));
                    float driverShareCalculatedDriver2 =Float.parseFloat((String) cucumberContextManager.getScenarioContext("CALCULATED_DRIVER_SHARE_SAME_TIRE"));
                    testStepAssert.isTrue(duoDriver2Earnings==driverShareCalculatedDriver2,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");
                    break;

                case "duo-different tier":
                    float duoDriver1EarningsTier1 = Float.parseFloat((action.getText(scheduledTripsPage.Text_DuoDriver1EarningsApp()).substring(1)));
                    float driverShareCalculatedDriver1Tier1 =Float.parseFloat((String) cucumberContextManager.getScenarioContext("CALCULATED_DRIVER1_SHARE_DIFFERENT_TIRE"));
                    testStepAssert.isTrue(duoDriver1EarningsTier1==driverShareCalculatedDriver1Tier1,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");
                    float duoDriver2EarningsTier2 = Float.parseFloat((action.getText(scheduledTripsPage.Text_DuoDriver2EarningsApp()).substring(1)));
                    float driverShareCalculatedDriver2Tier2 =Float.parseFloat((String) cucumberContextManager.getScenarioContext("CALCULATED_DRIVER2_SHARE_DIFFERENT_TIRE"));
                    testStepAssert.isTrue(duoDriver2EarningsTier2==driverShareCalculatedDriver2Tier2,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");

                    break;

                case "changed address and service level":
                    float soloDriverEarningsChangedSL = Float.parseFloat((action.getText(scheduledTripsPage.Text_SoloDriverEarningsApp()).substring(1)));
                    float driverShareCalculatedChangedSL =Float.parseFloat((String) cucumberContextManager.getScenarioContext("DRIVER_SHARE_FOR_CHANGED_SL_AND_ADDRESS"));
                    testStepAssert.isTrue(soloDriverEarningsChangedSL==driverShareCalculatedChangedSL,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");
                    break;
            }
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I select the live trip for \"([^\"]*)\" customer for delivery details$")
    public void i_select_the_live_trip_for_something_customer_for_delivery_details(String cust) throws Throwable {
        try {
            String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            action.sendKeys(scheduledTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
            action.click(scheduledTripsPage.Button_Search());
            Thread.sleep(5000);
            action.click(scheduledTripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/img", custName), PageBase.LocatorType.XPath));
            action.click(scheduledTripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/ul/li/*[contains(text(),'Delivery Details')]", custName),PageBase.LocatorType.XPath));

            log("I should be able to open delivery details for the customer",
                    "I am able to open delivery details for the customer",false);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I Select \"([^\"]*)\" from admin sidebar$")
    public void i_select_something_from_admin_sidebar(String option) {
        try {
            switch (option.toLowerCase()) {
                case "scheduled trip":
                    Thread.sleep(5000);
                    action.click(dashBoardPage.Button_Trips());
                    Thread.sleep(3000);
                    action.click(dashBoardPage.Button_ScheduledTrips());
                    break;
                case "promo code":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_PromoCode());
                    break;
                case "referral source":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_ReferralSource());
                    break;
                case "live trips":
                    action.click(dashBoardPage.Button_Trips());
                    Thread.sleep(3000);
                    action.click(dashBoardPage.Button_LiveTrips());
                    break;
                case "trips":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_Deliveries());
                    break;
                case "customers":
                    action.click(dashBoardPage.Button_Customers());
                    break;
                case "drivers":
                    action.click(dashBoardPage.Button_Drivers());
                    break;
                case "geofence":
                    Thread.sleep(2000);
                    action.click(dashBoardPage.Menu_Geofences());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should able to select "+option,"I Selected "+option+" on admin sidebar" ,true );
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I uncheck the Active Geofences Only$")
    public void i_uncheck_the_active_geofence_only() throws InterruptedException {
        try{
        action.click(dashBoardPage.Checkbox_Active_geofence());
        Thread.sleep(1000);
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I open the trip for \"([^\"]*)\" the customer for delivery details$")
    public void i_open_the_trip_for_something_the_customer_for_delivery_details(String custName) throws Throwable {
        try{
            String[] name = custName.split(" ");

            action.clearSendKeys(dashBoardPage.Text_SearchCriteria(),name[0]);
            action.click(dashBoardPage.Button_Search());

            Thread.sleep(25000);

            List<WebElement> rows_editicon = dashBoardPage.findElements(String.format("//td/span[contains(text(),'%s')]/parent::td/following-sibling::td/div/img",name[0]), PageBase.LocatorType.XPath);

            if(rows_editicon.size()>0)
            {
                rows_editicon.get(0).click();
                List<WebElement> rows_editlink = dashBoardPage.findElements(String.format("//td/span[contains(text(),'%s')]/ancestor::td/following::div/div/a[contains(text(),'Delivery Details')]",name[0]),PageBase.LocatorType.XPath);
                rows_editlink.get(0).click();
            }

            pass("I should able to open trip", "I viewed scheduled delivery",
                    false);

            log(" I click on Delivery Details besides the scheduled bungii",
                    "I have clicked on Delivery Details besides the scheduled bungii", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I check if delivery status is \"([^\"]*)\"$")
    public void i_check_if_delivery_status_is_something(String status) throws Throwable {
        try {

            testStepAssert.isEquals(dashBoardPage.Text_BungiiStatus().getText(),status,"The status should be No Driver(s) Found","The status is No Driver(s) Found","The status is not No Driver(s) Found");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

}

