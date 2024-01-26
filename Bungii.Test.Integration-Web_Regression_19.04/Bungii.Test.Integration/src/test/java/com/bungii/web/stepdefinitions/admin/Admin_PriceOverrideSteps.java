package com.bungii.web.stepdefinitions.admin;

import com.bungii.android.pages.admin.LiveTripsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_EditScheduledBungiiPage;
import com.bungii.web.pages.admin.Admin_LiveTripsPage;
import com.bungii.web.pages.admin.Admin_ScheduledTripsPage;
import com.bungii.web.pages.admin.Admin_TripDetailsPage;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import com.bungii.web.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.DecimalFormat;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;


public class Admin_PriceOverrideSteps extends DriverBase {

    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_AccessorialChargesSteps.class);
    DbUtility dbUtility = new DbUtility();

    Admin_TripDetailsPage admin_tripDetailsPage = new Admin_TripDetailsPage();
    Partner_DeliveryPage partner_deliveryPage = new Partner_DeliveryPage();
    Admin_TripDetailsPage Page_Admin_Trips_Details = new Admin_TripDetailsPage();
    Admin_EditScheduledBungiiPage admin_editScheduledBungiiPage= new Admin_EditScheduledBungiiPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_LiveTripsPage admin_liveTripsPage=new Admin_LiveTripsPage();
    LiveTripsPage liveTripsPage=new LiveTripsPage();

    @And("^I check if \"([^\"]*)\" button is displayed$")
    public void i_check_if_something_button_is_displayed(String button) throws Throwable {

        try{
            switch (button){
                case "Price Override":
                    testStepAssert.isElementDisplayed(admin_tripDetailsPage.Button_Price_Override(),"I should be able to see Price Override button","I could see the Price Override button","I could not see the Price Override button");
                    break;
                case "Stop Searching":
                    testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Button_StopSearching(),"I should be able to see Stop Searching button","I could see the Stop Searching button","I could not see the Stop Searching button");
                    break;
            }
            log("I should be able to check if "+button+" button is displayed","I am able to check if "+button+" button is displayed",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on \"([^\"]*)\" button on delivery details$")
    public void i_click_on_something_button_on_delivery_details(String button) throws Throwable {
        try{
            switch (button){
                case "Price Override":
                    action.click(admin_tripDetailsPage.Button_Price_Override());
                    Thread.sleep(5000);
                    break;
                case "Delivery Overview":
                    action.click(admin_tripDetailsPage.Button_DeliveryOverview());
                    break;
                case "Payment Details":
                    action.click(admin_tripDetailsPage.Button_PaymentDetails());
                    break;
                default:
                    logger.detail("Unimplemented Step");
            }
            log("I should be able to click on "+button+" button ",
                    "I could click on "+button+" button",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I get the old values of \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_get_the_old_values_of_something_for_something(String price, String type) throws Throwable {
        try {
            switch (type){
                case "Service level":
                    switch (price){
                        case "Customer price":
                            Thread.sleep(3000);
                            String customerPrice = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String oldCustomerPrice = customerPrice.substring(1).replace("Price Override","");
                            float oldPrice= Float.parseFloat(oldCustomerPrice);
                            float newPrice= (float) (oldPrice+20.08);
                            cucumberContextManager.setScenarioContext("OLD_CUSTOMER_PRICE", oldCustomerPrice);
                            cucumberContextManager.setScenarioContext("NEW_CUSTOMER_PRICE",newPrice);
                            break;

                        case "Driver cut":
                            String driverCut = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
                            String oldDriverCut = driverCut.substring(1);
                            float oldDriverPrice= Float.parseFloat(oldDriverCut);
                            float newDriverPrice= (float) (oldDriverPrice+10.08);
                            float conversionDriverPrice=newDriverPrice+newDriverPrice;
                            cucumberContextManager.setScenarioContext("OLD_DRIVER_CUT",oldDriverCut);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_CUT",newDriverPrice);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_ONE_CUT",newDriverPrice);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_TWO_CUT",newDriverPrice);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_CUT_AFTER_CONVERSION",conversionDriverPrice);
                            break;
                    }
                    break;

                case "Service level - fnd":
                    switch (price){
                        case "Customer price":
                            String customerPriceFnd = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String oldCustomerPriceFnd = customerPriceFnd.substring(1).replace("Price Override","");
                            float oldPriceFnd= Float.parseFloat(oldCustomerPriceFnd);
                            float newPriceFnd= (float) (oldPriceFnd+20.08);
                            cucumberContextManager.setScenarioContext("OLD_CUSTOMER_PRICE", oldCustomerPriceFnd);
                            cucumberContextManager.setScenarioContext("NEW_CUSTOMER_PRICE",newPriceFnd);
                            break;

                        case "Driver cut":
                            String goodsWeight= (String) cucumberContextManager.getScenarioContext("Weight");
                            String driverCutFnd = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings_Fnd());
                            String oldDriverCutFnd = driverCutFnd.substring(1);
                            float oldDriverPriceFnd= Float.parseFloat(oldDriverCutFnd);
                            float newDriverPriceFnd= (float) (oldDriverPriceFnd+20.08);
                            DecimalFormat df = new DecimalFormat("0.00");
                            float newFormatedDriverPriceFnd = Float.parseFloat(df.format(newDriverPriceFnd));
                            cucumberContextManager.setScenarioContext("OLD_DRIVER_CUT",oldDriverPriceFnd);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_CUT",newFormatedDriverPriceFnd);
                            break;

                    }
                 break;
                case "Service level-duo":
                    switch (price) {
                        case "Customer price":
                            String customerPriceFnd = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String oldCustomerPriceFnd = customerPriceFnd.substring(1).replace("Price Override","");
                            float oldPriceFnd= Float.parseFloat(oldCustomerPriceFnd);
                            float newPriceFnd= (float) (oldPriceFnd+20.08);
                            cucumberContextManager.setScenarioContext("OLD_CUSTOMER_PRICE", oldCustomerPriceFnd);
                            cucumberContextManager.setScenarioContext("NEW_CUSTOMER_PRICE",newPriceFnd);
                            break;

                        case "Driver cut":
                            String driverCut = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
                            String oldDriverCut = driverCut.substring(1);
                            float oldDriverPrice= Float.parseFloat(oldDriverCut);
                            float newDriverPrice= (float) (oldDriverPrice+2.08);
                            DecimalFormat df = new DecimalFormat("0.00");
                            float newFormatedDriverPrice = Float.parseFloat(df.format(newDriverPrice));
                            cucumberContextManager.setScenarioContext("OLD_DRIVER_CUT",oldDriverCut);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_CUT",newFormatedDriverPrice);
                            break;
                    }
                    break;
                case "Service level - fnd-duo":
                    switch (price){
                        case "Driver cut":
                            String driverOneCut = action.getText(admin_tripDetailsPage.Text_DriverOneEarnings());
                            String driverTwoCut = action.getText(admin_tripDetailsPage.Text_DriverTwoEarnings());
                            String oldDriverOneCut = driverOneCut.substring(1);
                            String oldDriverTwoCut = driverTwoCut.substring(1);
                            float oldDriverOnePrice= Float.parseFloat(oldDriverOneCut);
                            float oldDriverTwoPrice= Float.parseFloat(oldDriverTwoCut);
                            float newDriverOnePrice= (float) (oldDriverOnePrice+2.08);
                            float newDriverTwoPrice= (float) (oldDriverTwoPrice+2.08);
                            DecimalFormat df = new DecimalFormat("0.00");
                            float newFormatedDriverOnePrice = Float.parseFloat(df.format(newDriverOnePrice));
                            float newFormatedDriverTwoPrice = Float.parseFloat(df.format(newDriverTwoPrice));
                            float conversionDriverPrice = newFormatedDriverOnePrice+newFormatedDriverTwoPrice;
                            cucumberContextManager.setScenarioContext("OLD_DRIVER_ONE_CUT",oldDriverOneCut);
                            cucumberContextManager.setScenarioContext("OLD_DRIVER_TWO_CUT",oldDriverTwoCut);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_ONE_CUT",newFormatedDriverOnePrice);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_TWO_CUT",newFormatedDriverTwoPrice);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_CUT_AFTER_CONVERSION",conversionDriverPrice);
                            break;
                    }
                    break;

            }
            log("I should be able to save the old values of customer price and driver cut",
                    "I could save the old values of customer price and driver cut",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I check the new values of \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_check_the_new_values_of_something_for_something(String price, String type) throws Throwable {
        try {
            switch (type){
                case "Service level":
                    switch (price) {
                        case "Customer price":
                            action.refreshPage();
                            String estimatedCharges = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String actualEstimatedCharges = estimatedCharges.substring(1).replace("Price Override","");
                            String expectedEstimatedCharges = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
                            float newExpectedEstimatedCharges = (float) (Math.floor( Float.parseFloat(expectedEstimatedCharges)* 100) / 100);
                            testStepAssert.isEquals(actualEstimatedCharges, String.valueOf(newExpectedEstimatedCharges), "Estimated Charges should be overridden", "Estimated Charges are overridden", "Estimated Charges are not overridden");
                            break;

                        case "Driver Fixed Earnings":
                            action.refreshPage();
                            String driverCharges = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
                            String actualDriverCharges = driverCharges.substring(1);
                            String expectedDriverCharges = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                            float newExpectedDriverCharges = (float) (Math.floor( Float.parseFloat(expectedDriverCharges)* 100) / 100);
                            testStepAssert.isEquals(actualDriverCharges, String.valueOf(newExpectedDriverCharges), "Driver Charges should be overridden", "Driver Charges are overridden", "Driver Charges are not overridden");
                            break;
                    }
                    break;
                case "Service level - fnd":
                    switch (price) {
                        case "Customer price":
                            action.refreshPage();
                            String estimatedChargesFnd = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String actualEstimatedChargesFnd = estimatedChargesFnd.substring(1).replace("Price Override","");
                            String expectedEstimatedChargesFnd = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
                            testStepAssert.isEquals(actualEstimatedChargesFnd, expectedEstimatedChargesFnd, "Estimated Charges should be overridden", "Estimated Charges are overridden", "Estimated Charges are not overridden");
                            break;

                        case "Driver Fixed Earnings":
                            action.refreshPage();
                            String goodsWeight = (String) cucumberContextManager.getScenarioContext("Weight");
                            String driverChargesFnd = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings_Fnd());
                            String actualDriverChargesFnd = driverChargesFnd.substring(1);
                            String expectedDriverChargesFnd = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                            testStepAssert.isEquals(actualDriverChargesFnd, expectedDriverChargesFnd, "Driver Charges should be overridden", "Driver Charges are overridden", "Driver Charges are not overridden");
                            break;
                    }
                    break;
                case "Service level-duo":
                    switch (price){
                        case "Customer price":
                            action.refreshPage();
                            String estimatedChargesFnd = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String actualEstimatedChargesFnd = estimatedChargesFnd.substring(1).replace("Price Override","");
                            String expectedEstimatedChargesFnd = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
                            testStepAssert.isEquals(actualEstimatedChargesFnd, expectedEstimatedChargesFnd, "Estimated Charges should be overridden", "Estimated Charges are overridden", "Estimated Charges are not overridden");
                            break;

                        case "Driver Fixed Earnings":
                            action.refreshPage();
                            String driverCharges = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
                            String actualDriverCharges = driverCharges.substring(1);
                            String expectedDriverCharges = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                            testStepAssert.isEquals(actualDriverCharges, expectedDriverCharges, "Driver Charges should be overridden", "Driver Charges are overridden", "Driver Charges are not overridden");
                            break;

                    }
                    break;
            }
            log("I should be able to check the new values of customer price and driver cut",
                    "I could check the new values of customer price and driver cut",false);


        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I check if \"([^\"]*)\" icon is displayed$")
    public void i_check_if_something_icon_is_displayed(String icon) throws Throwable {
       try{
           switch (icon)
           {
               case "Price Override":
                   action.refreshPage();
                   testStepAssert.isElementDisplayed(admin_tripDetailsPage.Icon_Price_Override(),
                           "I should be able to see price override icon displayed",
                           "I could see price override icon displayed",
                           "Price override icon is not displayed");
                   break;
               case "i":
                   testStepAssert.isElementDisplayed(admin_tripDetailsPage.Icon_DriverEarnings(),
                           "I should be able to see i icon displayed",
                           "I could see i icon displayed",
                           "i icon is not displayed");
                   break;
               case "same day payment i":
                   String driverOne= (String) cucumberContextManager.getScenarioContext("DRIVER_1");
                   String driverTwo= (String) cucumberContextManager.getScenarioContext("DRIVER_2");
                   testStepAssert.isElementDisplayed(admin_tripDetailsPage.Icon_DriverSameDayPayment(driverOne),
                           "I should be able to see i icon next to driver with same day payment setting.",
                           "I am able to see i icon next to driver with same day payment setting.",
                           "i icon is not displayed next to driver with same day payment setting.");
                   testStepAssert.isFalse(action.isElementPresent(admin_tripDetailsPage.Icon_DriverSameDayPayment(driverTwo,true)),
                           "i icon should not be present next to driver with weekly payment setting.",
                           "i icon is not present next to driver with weekly payment setting.",
                           "i icon is displayed next to driver with weekly payment setting.");
                   break;
           }

       }
       catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }
    @When("^I navigate back to Live Deliveries$")
    public void i_navigate_back_to_live_deliveries() throws Throwable {
        try {
                action.click(Page_Admin_Trips_Details.Button_Ok());
                log("I should able to navigate back to Live Deliveries.","I have navigated back to Live Deliveries", true);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I change the \"([^\"]*)\"$")
    public void i_change_the_something(String price) throws Throwable {
        try{
            switch (price){
                case "Customer price":
                    String newCustomerPrice = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
                    action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Customer_Price(),newCustomerPrice);
                    break;

                case "Driver cut":
                    String newDriverCut = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                    float roundDriverCut = (float) (Math.floor( Float.parseFloat(newDriverCut)* 100) / 100);
                    action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Driver_Cut(), String.valueOf(roundDriverCut));
                    break;

                case "Driver cut-duo":
                    String newDriverOneCut = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_ONE_CUT");
                    String newDriverTwoCut = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_TWO_CUT");
                    DecimalFormat df = new DecimalFormat("0.00");
                    float roundDriverOneCut = (float) (Math.floor( Float.parseFloat(newDriverOneCut)* 100) / 100);
                    float roundDriverTwoCut = (float) (Math.floor( Float.parseFloat(newDriverTwoCut)* 100) / 100);
                    action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Driver_Cut(), String.valueOf(roundDriverOneCut));
                    action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Driver_Cut_Duo(), String.valueOf(roundDriverTwoCut));
                    break;

                case "Customer price - equal to driver earnings":
                    String driverCharges = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                    float expectedDriverCharge = (float) (Math.floor( Float.parseFloat(driverCharges)* 100) / 100);
                    action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Customer_Price(), String.valueOf(expectedDriverCharge));
                    break;

                case "driver boosted earnings":
                    action.clearSendKeys(admin_tripDetailsPage.Input_DriverBoostedPeriod(),(String) cucumberContextManager.getScenarioContext("DRIVER_SEARCH_TIME"));
                    break;
            }
            log("I should be able to change "+price,
                    "I could change "+price,false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I change the \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_change_the_something_for_something(String price, String type) throws Throwable {
        try{
            switch (type){
                case "Service level-duo":
                    switch (price){
                        case "Driver cut":
                            String oldDriverCut = (String) cucumberContextManager.getScenarioContext("OLD_DRIVER_CUT");
                            String newDriverCut = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                            action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Driver_Cut(),newDriverCut);
                            action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Driver_Cut_Duo(),oldDriverCut);
                            break;

                    }
                    log("I should be able to override the driver cut for duo delivery",
                            "I could override the driver cut for duo delivery",false);
            }

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I select Reason as \"([^\"]*)\"$")
    public void i_select_reason_as_something(String reason) throws Throwable {
        try{
            switch (reason){
                case "Custom Quote":
                    Select selectOverrideReason = new Select((WebElement) admin_tripDetailsPage.Dropdown_Reason_Override_Customer_Price());
                    selectOverrideReason.selectByVisibleText("Custom Quote");
                    break;

                case "Driver Incentive":
                    Select selectDriverOverrideReason = new Select((WebElement) admin_tripDetailsPage.Dropdown_Reason_Override_Driver_Cut());
                    selectDriverOverrideReason.selectByVisibleText("Driver Incentive");
                    break;
            }
            log("I should be able to select reason to override the customer price and driver cut",
                    "I could select reason to override the customer price and driver cut",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^I click on \"([^\"]*)\" button on price override pop-up$")
    public void i_click_on_something_button_on_price_override_popup(String button) throws Throwable {
        try {
            switch (button){
                case "Save":
                    action.click(admin_tripDetailsPage.Button_Save());
                    break;
                case "Ok":
                    action.click(admin_tripDetailsPage.Button_Success_Ok());
                    break;
                case "Cancel":
                    action.click(admin_tripDetailsPage.Button_Override_Cancel());
                    break;
            }
            log("I should be able to click on the "+button+"button",
                    "I could click on the "+button+"button",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^I check if values of \"([^\"]*)\" and \"([^\"]*)\" remain unchanged$")
    public void i_check_if_values_of_something_and_something_remain_unchanged(String strArg1, String strArg2) throws Throwable {
       try{
            action.refreshPage();
           String CustomerPrice = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
           String actualCustomerPrice = CustomerPrice.substring(1).replace("Price Override","");
           String expectedCustomerPrice = (String) cucumberContextManager.getScenarioContext("OLD_CUSTOMER_PRICE");
           testStepAssert.isEquals(actualCustomerPrice,expectedCustomerPrice,
                   "Estimated Charges should not be overridden",
                   "Estimated Charges are not overridden",
                   "Estimated Charges are overridden");


           String driverCut = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
           String actualDriverCut = driverCut.substring(1);
           String expectedDriverCut = (String) cucumberContextManager.getScenarioContext("OLD_DRIVER_CUT");
           testStepAssert.isEquals(actualDriverCut,expectedDriverCut,
                   "Estimated Charges should not be overridden",
                   "Estimated Charges are not overridden",
                   "Estimated Charges are overridden");
       }
       catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }
    @Then("^I check if the delivery cost is updated$")
    public void i_check_if_the_delivery_cost_is_updated() throws Throwable {
        try{
            String expectedEstimatedCharges = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
            String actualDeliveryCost = action.getText(admin_tripDetailsPage.Text_Partner_Delivery_Cost());
            testStepAssert.isEquals(actualDeliveryCost,expectedEstimatedCharges,"Driver Charges should not be overridden","Driver Charges are not overridden","Driver Charges are overridden");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I check if the \"([^\"]*)\" menu is displayed$")
    public void i_check_if_the_something_menu_is_displayed(String menuName) throws Throwable {
        try{
            switch (menuName){
                case "Customer price":
                    testStepAssert.isElementDisplayed(admin_tripDetailsPage.Textbox_Override_Customer_Price(),"I should be able to check if Customer price is displayed","I could check if Customer price is displayed","Customer price is not displayed");
                    break;
                case "Driver cut":
                    testStepAssert.isElementDisplayed(admin_tripDetailsPage.Textbox_Override_Driver_Cut(),"I should be able to check if Driver cut is displayed","I could check if Driver cut is displayed","Driver cut is not displayed");
                    break;
            }
            log("I should be able to check if "+menuName+"is displayed",
                    "I could check if "+menuName+"is displayed",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("^I check if the delivery cost is updated on partner portal$")
    public void i_check_if_the_delivery_cost_is_updated_on_partner_portal() throws Throwable {
        try {
            String expectedEstimatedCharges = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
            String estimatedCharges = action.getText(partner_deliveryPage.Text_Estiated_Cost());
            String actualestimatedCharges = estimatedCharges.substring(1);
            testStepAssert.isEquals(actualestimatedCharges, expectedEstimatedCharges,
                    "Estimated Charges should not be overridden",
                    "Estimated Charges are not overridden",
                    "Estimated Charges are overridden");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @And("^I increase the \"([^\"]*)\" more than \"([^\"]*)\"$")
    public void i_increase_the_something_more_than_something(String strArg1, String strArg2) throws Throwable {
        try {
            String newCustomerPrice = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
            action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Driver_Cut(), newCustomerPrice);

            log("I should be able to change customer price",
                    "I could change customer price",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^\"([^\"]*)\" error message should be displayed$")
    public void something_error_message_should_be_displayed(String errorMessage) throws Throwable {
        try{
            testStepAssert.isElementDisplayed(admin_tripDetailsPage.Text_Driver_Cut_Error(),errorMessage,errorMessage+" is displayed",errorMessage+"is not displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^I check the new values of \"([^\"]*)\" and \"([^\"]*)\" for changed \"([^\"]*)\"$")
    public void i_check_the_new_values_of_something_and_something_for_changed_something(String strArg1, String strArg2, String type) throws Throwable {

        try{
            switch (type){
                case "Service level":
                    action.refreshPage();
                    String estimatedCharges = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                    String actualEstimatedCharges = estimatedCharges.substring(1);
                    String expectedEstimatedCharges = (String) cucumberContextManager.getScenarioContext("OLD_CUSTOMER_PRICE");
                    testStepAssert.isEquals(actualEstimatedCharges,expectedEstimatedCharges,
                            "Estimated Charges should be overridden",
                            "Estimated Charges are overridden",
                            "Estimated Charges are not overridden");


                    String driverCharges = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
                    String actualDriverCharges = driverCharges.substring(1);
                    String expectedDriverCharges  = (String) cucumberContextManager.getScenarioContext("OLD_DRIVER_CUT");
                    testStepAssert.isEquals(actualDriverCharges,expectedDriverCharges,
                            "Driver Charges should be overridden",
                            "Driver Charges are overridden",
                            "Driver Charges are not overridden");
                    break;

                case "Service level - fnd":
                    action.refreshPage();
                    String estimatedChargesFnd = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                    String actualEstimatedChargesFnd = estimatedChargesFnd.substring(1);
                    String expectedEstimatedChargesFnd = (String) cucumberContextManager.getScenarioContext("OLD_CUSTOMER_PRICE");
                    testStepAssert.isEquals(actualEstimatedChargesFnd,expectedEstimatedChargesFnd,
                            "Estimated Charges should be overridden",
                            "Estimated Charges are overridden",
                            "Estimated Charges are not overridden");

                    String goodsWeight= (String) cucumberContextManager.getScenarioContext("Weight");
                    String driverChargesFnd = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings_Fnd());
                    String actualDriverChargesFnd = driverChargesFnd.substring(1);
                    String expectedDriverChargesFnd  = (String) cucumberContextManager.getScenarioContext("OLD_DRIVER_CUT");
                    testStepAssert.isEquals(actualDriverChargesFnd,expectedDriverChargesFnd,
                            "Driver Charges should be overridden",
                            "Driver Charges are overridden",
                            "Driver Charges are not overridden");
                    break;

            }

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^I check if \"([^\"]*)\" button is not present$")
    public void i_check_if_something_button_is_not_present(String button) throws Throwable {
        try{
            switch (button){
                case "Price override":
                    action.isElementPresent(admin_tripDetailsPage.Button_Price_Override(true));
                    break;
                case "Stop Searching":
                    testStepAssert.isFalse(action.isElementPresent(admin_ScheduledTripsPage.Button_StopSearching(true)),
                            "Stop Searching button should not be displayed",
                            "Stop Searching button is not be displayed",
                            "Stop Searching button is displayed");
                    break;
                case "driver location":
                    Thread.sleep(2000);
                    String driver = (String)cucumberContextManager.getScenarioContext("DRIVER_1");
                    testStepAssert.isFalse(action.isElementPresent(admin_liveTripsPage.Image_DriverLocation(driver,true)),
                            "Driver live location pin should not be displayed",
                            "Driver live location pin is not be displayed",
                            "Driver live location pin is displayed");
                    break;
                case "driver duo location":
                    Thread.sleep(2000);
                    String driver1 = (String)cucumberContextManager.getScenarioContext("DRIVER_1");
                    String driver2 = (String)cucumberContextManager.getScenarioContext("DRIVER_2");
                    Thread.sleep(2000);
                    testStepAssert.isFalse(action.isElementPresent(admin_liveTripsPage.Image_DriverLocation(driver1,true)),
                            "The duo first driver live location pin should not be displayed.",
                            "The duo first driver live location pin is not displayed.",
                            "The duo first driver live location pin is displayed.");
                    testStepAssert.isFalse(action.isElementPresent(admin_liveTripsPage.Image_DriverLocation(driver2,true)),
                            "The duo second driver live location pin should not be displayed.",
                            "The duo second driver live location pin is not displayed.",
                            "The duo second driver live location pin is  displayed.");
                    break;
            }


            log("I should not be able to see "+button+" button",
                    "I could not see the "+button+" button",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I check \"([^\"]*)\" is retained after \"([^\"]*)\" conversion$")
    public void i_check_something_is_retained_after_something_conversion(String charge, String conversion) throws Throwable {
       try{
           switch (conversion){
               case "duo to solo":
                   switch (charge){
                       case "Customer price":
                           action.refreshPage();
                           String estimatedCharges = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                           String actualEstimatedCharges = estimatedCharges.substring(1);
                           String expectedEstimatedCharge = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
                           float expectedEstimatedCharges = (float) (Math.floor( Float.parseFloat(expectedEstimatedCharge)* 100) / 100);
                           testStepAssert.isEquals(actualEstimatedCharges, String.valueOf(expectedEstimatedCharges), "Estimated Charges overriden should be retained after converted from DUO to SOLO", "Estimated Charges overriden are retained after converted from DUO to SOLO", "Estimated Charges overriden are not retained after converted from DUO to SOLO");
                           break;
                       case "Driver Earning":
                           action.refreshPage();
                           String driverOneCut = action.getText(admin_tripDetailsPage.Text_DriverOneEarnings());
                           String actualDriverCharges = driverOneCut.substring(1);
                           String expectedDriverCharges = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT_AFTER_CONVERSION");
                           float expectedDriverCharge = (float) (Math.floor( Float.parseFloat(expectedDriverCharges)* 100) / 100);
                           testStepAssert.isEquals(actualDriverCharges, String.valueOf(expectedDriverCharge), "Driver Charges overriden should be retained after converted from DUO to SOLO", "Driver Charges overriden are retained after converted from DUO to SOLO", "Driver Charges overriden are not retained after converted from DUO to SOLO");
                           break;
                   }
                   break;
               case "solo to duo":
                   switch (charge){
                       case "Customer price":
                           action.refreshPage();
                           String estimatedCharges = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                           String actualEstimatedCharges = estimatedCharges.substring(1);
                           String expectedEstimatedCharge = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
                           float expectedEstimatedCharges = (float) (Math.floor( Float.parseFloat(expectedEstimatedCharge)* 100) / 100);
                           float newExpectedEstimatedCharges = (float) (Math.floor((expectedEstimatedCharges*2)* 100) / 100);
                           testStepAssert.isEquals(actualEstimatedCharges, String.valueOf(newExpectedEstimatedCharges), "Estimated Charges overriden should be retained after converted from DUO to SOLO", "Estimated Charges overriden are retained after converted from DUO to SOLO", "Estimated Charges overriden are not retained after converted from DUO to SOLO");
                           break;
                       case "Driver Earning":
                           action.refreshPage();
                           String driverOneCut = action.getText(admin_tripDetailsPage.Text_DriverOneEarnings());
                           String actualDriverCharges = driverOneCut.substring(1);
                           String expectedDriverCharges = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                           float expectedDriverCharge = (float) (Math.floor( Float.parseFloat(expectedDriverCharges)* 100) / 100);
                           testStepAssert.isEquals(actualDriverCharges, String.valueOf(expectedDriverCharge), "Driver Charges overriden should be retained after converted from DUO to SOLO", "Driver Charges overriden are retained after converted from DUO to SOLO", "Driver Charges overriden are not retained after converted from DUO to SOLO");
                           break;
                   }
                   break;
           }

       }
       catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }

    @Then("^I check if DUO option is disabled$")
    public void i_check_if_duo_option_is_disabled() throws Throwable {
        try{
            Thread.sleep(1000);
           String duoDisabled = admin_editScheduledBungiiPage.RadioButton_Duo().getAttribute("disabled");
           testStepAssert.isTrue(duoDisabled.equals("true"),"Duo should be disabled","Duo is disabled","Duo is not disabled");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I open the trip for \"([^\"]*)\" the customer for delivery details$")
    public void i_open_the_trip_for_something_the_customer_for_delivery_details(String custName) throws Throwable {
        try{
            String[] name = custName.split(" ");

            action.clearSendKeys(admin_ScheduledTripsPage.Textbox_Search(),name[0]);
            action.click(admin_ScheduledTripsPage.Button_Search());

            Thread.sleep(25000);
            List<WebElement> rows_editicon = admin_ScheduledTripsPage.findElements(String.format("//td/a[contains(text(),'%s')]/parent::td/following-sibling::td/div/img",name[0]), PageBase.LocatorType.XPath);
            List<WebElement> rows_editlink = admin_ScheduledTripsPage.findElements(String.format("//td/a[contains(text(),'%s')]/ancestor::td/following-sibling::td/div/ul/li/p[contains(text(),'Delivery Details')]",name[0]),PageBase.LocatorType.XPath);

            if(rows_editicon.size()>0)
            {
                rows_editicon.get(0).click();
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

    @Then("^I check if error message is displayed$")
    public void i_check_if_error_message_is_displayed() throws Throwable {
        try{
            String errorMessage = PropertyUtility.getMessage("error.message.price.override");
            testStepVerify.isEquals(admin_tripDetailsPage.Text_PriceOverrideError().getText(),errorMessage,
                    "The correct error message should be displayed when customer cost is less than or equal to driver earnings",
                    "The correct error message is displayed when customer cost is less than or equal to driver earnings",
                    "The correct error message is not displayed when customer cost is less than or equal to driver earnings");

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I check if stop search status is updated in DB$")
    public void i_check_if_stop_search_status_is_updated_in_db() throws Throwable {
        try{
            String pickUpRef= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String pickUpId = dbUtility.getPickupId(pickUpRef);
            String driverSearchFlag = dbUtility.getStopSearchStatus(pickUpId);
            testStepAssert.isTrue(driverSearchFlag.contains("1"),"Stop search status is updated","Stop search status is not updated");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I verify overriden prices are retained$")
    public void i_verify_overriden_prices_are_retained() throws Throwable {
        try{
            String newCustomerPrice = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
            String newDriverCut = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
            float str1 = Float.parseFloat(newDriverCut);
            String newDriverCut1 = new DecimalFormat("#.##").format(str1);
            String tripPayment = action.getText(liveTripsPage.Text_TripPayment());
            String DriverEarnings = action.getText(liveTripsPage.Text_DriverEarnings());
            String TrimedTripPayment=(tripPayment.replace('$',' ')).trim();
            String TrimDriverEarning=(DriverEarnings.replace('$',' ')).trim();
            testStepAssert.isEquals(TrimedTripPayment,newCustomerPrice,
                    "The Delivery cost "+newCustomerPrice+" should be retained",
                    "The Delivery cost "+newCustomerPrice+" are retained",
                    "The "+TrimedTripPayment+" cost is shown");
            testStepAssert.isEquals(TrimDriverEarning, newDriverCut1,
                    "The Driver earnings "+newDriverCut1+" should be retained",
                    "The driver earnings "+newDriverCut1+" are retained",
                    "The earnings "+TrimDriverEarning+" are shown");

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
