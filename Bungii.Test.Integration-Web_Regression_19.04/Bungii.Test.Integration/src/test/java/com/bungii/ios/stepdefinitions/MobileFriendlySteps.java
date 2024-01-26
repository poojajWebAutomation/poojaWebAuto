package com.bungii.ios.stepdefinitions;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.other.SafariPage;

import com.bungii.ios.stepdefinitions.customer.HomeSteps;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.*;

public class MobileFriendlySteps extends DriverBase {
    private static LogUtility logger = new LogUtility(CommonSteps.class);
    ActionManager action = new ActionManager();
    SafariPage safariPage= new SafariPage();
    @When("^I terminate \"([^\"]*)\" app on \"([^\"]*)\" devices$")
    public void i_terminate_app(String appName, String device) {

        try {
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    ((IOSDriver<MobileElement>) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                    break;
                case "CUSTOMER":
                    ((IOSDriver<MobileElement>) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }

            pass("Terminated " + appName + " application", "Termination of " + appName + " application is successful");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);

        }

    }
    @And("I open {string} partner portal")
    public void iOpenPartnerPortal(String portal) {
        try{

            switch (portal){
                case "fixed pricing":
                    action.click(safariPage.Textbox_SafariSearch());
                    action.sendKeys(safariPage.Textbox_SafariSearchBar(),PropertyUtility.getDataProperties("qa.service_level_partner.url"));
                    action.click(safariPage.Button_Go());
                    break;
                case "kiosk mode":
                    action.click(safariPage.Textbox_SafariSearch());
                    action.sendKeys(safariPage.Textbox_SafariSearchBar(),PropertyUtility.getDataProperties("qa.kiosk_mode_partner.url"));
                    action.click(safariPage.Button_Go());
                    break;
            }
            action.waitUntilIsElementExistsAndDisplayed(safariPage.Textbox_EnterPassword());
            action.clearSendKeys(safariPage.Textbox_EnterPassword(),PropertyUtility.getDataProperties("PartnerPassword"));
            action.click(safariPage.Button_SignIn());
            log("I should be able to login to "+portal,"I am able to login to "+portal,false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("I verify the ui links on {string} page for {string} partner")
    public void iVerifyTheUiLinksOnPageForPartner(String pageName, String portalName) {
        try{
            switch (pageName){
                case "get estimate":
                    action.waitUntilIsElementExistsAndDisplayed(safariPage.Icon_BungiiLogo());
                    testStepAssert.isElementDisplayed(safariPage.Icon_BungiiLogo(),
                            "Bungii Logo should be displayed",
                            "Bungii Logo is displayed",
                            "Bungii Logo is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Text_WhatsNeeded(),
                            "Whats needed section should be displayed",
                            "Whats needed section is displayed",
                            "Whats needed section is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Icon_Logout(),
                            "Log-out icon should be displayed",
                            "Log-out icon is displayed",
                            "Log-out icon is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Text_DeliveryAddress(),
                            "Delivery address section should be displayed",
                            "Delivery address section is displayed",
                            "Delivery address section is not displayed");
                    switch (portalName){
                        case "fixed pricing":
                            testStepAssert.isElementDisplayed(safariPage.Text_Solo(),
                                    "Solo section should be displayed",
                                    "Solo section is displayed",
                                    "Solo section is not displayed");
                            testStepAssert.isElementDisplayed(safariPage.Text_Duo(),
                                    "Duo section should be displayed",
                                    "Duo section is displayed",
                                    "Duo section is not displayed");
                            testStepAssert.isElementDisplayed(safariPage.Text_PickUpAddress(),
                                    "Pick-up address section should be displayed",
                                    "Pick-up address section is displayed",
                                    "Pick-up address section is not displayed");
                            action.swipeUP();
                            testStepAssert.isElementDisplayed(safariPage.Text_PickUpDate(),
                                    "Pick-up date section should be displayed",
                                    "Pick-up date section is displayed",
                                    "Pick-up date section is not displayed");
                            testStepAssert.isElementDisplayed(safariPage.Text_PickUpTime(),
                                    "Pick-up time section should be displayed",
                                    "Pick-up time section is displayed",
                                    "Pick-up time section is not displayed");
                            testStepAssert.isElementDisplayed(safariPage.Text_ServiceLevel(),
                                    "Service Level section should be displayed",
                                    "Service Level section is displayed",
                                    "Service Level section is not displayed");
                            break;
                        case "kiosk mode":
                            testStepAssert.isElementDisplayed(safariPage.Text_Solo(),
                                    "Solo section should be displayed",
                                    "Solo section is displayed",
                                    "Solo section is not displayed");
                            testStepAssert.isElementDisplayed(safariPage.Text_PickUpAddress(),
                                    "Pick-up address section should be displayed",
                                    "Pick-up address section is displayed",
                                    "Pick-up address section is not displayed");
                            action.swipeUP();
                            testStepAssert.isElementDisplayed(safariPage.Text_PickUpDate(),
                                    "Pick-up date section should be displayed",
                                    "Pick-up date section is displayed",
                                    "Pick-up date section is not displayed");
                            testStepAssert.isElementDisplayed(safariPage.Text_PickUpTime(),
                                    "Pick-up time section should be displayed",
                                    "Pick-up time section is displayed",
                                    "Pick-up time section is not displayed");
                            testStepAssert.isElementDisplayed(safariPage.Text_LoadUnload(),
                                    "Load unload section should be displayed",
                                    "Load unload section is displayed",
                                    "Load unload section is not displayed");
                            break;
                    }
                    break;
                case "success":
                    testStepAssert.isElementDisplayed(safariPage.Text_SucessMsg(),
                            "Success message should be displayed",
                            "Success message is displayed",
                            "Success message is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Text_TrackingId(),
                            "Tracking ID should be displayed",
                            "Tracking ID is displayed",
                            "Tracking ID is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Text_Time(),
                            "Schedule time should be displayed",
                            "Schedule time is displayed",
                            "Schedule time is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Text_Drivers(),
                            "Whats needed section should be displayed",
                            "Whats needed section is displayed",
                            "Whats needed section is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Text_Payment(),
                            "Payment section should be displayed",
                            "Payment section is displayed",
                            "Payment section is not displayed");
                    action.swipeUP();
                    testStepAssert.isElementDisplayed(safariPage.Button_NewBungii(),
                            "New Bungii button should be displayed",
                            "New Bungii button is displayed",
                            "New Bungii button is not displayed");
                    break;
            }
            String pickupRequest= DbUtility.getPickupRefForPartnerTrips((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickupRequest);
            log("I should be able to verify ui elements on "+pageName,"I am able to verify ui elements on "+pageName,false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @When("I enter all details on {string} for {string}")
    public void iEnterAllDetailsOnFor(String pageName, String portalName, DataTable data) {
        try{
            switch (pageName){
                case "get estimate page":
                    Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
                    if(portalName.equalsIgnoreCase("fixed pricing"))
                    {
                        action.clearSendKeys(safariPage.Textbox_PickUpAddress(),dataMap.get("Pickup_Address"));
                        action.click(safariPage.Button_Return());
                        action.click(safariPage.Button_Done());
                        action.clearSendKeys(safariPage.Textbox_DeliveryAddress(),dataMap.get("Delivery_Address"));
                        action.click(safariPage.Button_Return());
                    }
                   else if(portalName.equalsIgnoreCase("kiosk mode"))
                    {
                        action.click(safariPage.Button_Edit());
                        action.clear(safariPage.Textbox_PickUpAddressKioski());
                        if(action.isElementPresent(safariPage.Button_Cut())){
                            action.click(safariPage.Button_Cut());
                        }
                        action.clearSendKeys(safariPage.Textbox_PickUpAddress(),dataMap.get("Pickup_Address"));
                        action.click(safariPage.Button_Return());
                        action.click(safariPage.Button_Done());
                        action.clearSendKeys(safariPage.Textbox_DeliveryAddress(),dataMap.get("Delivery_Address"));
                        action.click(safariPage.Button_Return());
                        if(action.isElementPresent(safariPage.Text_PickUpValidation(true))) {
                            action.clearSendKeys(safariPage.Textbox_PickUpAddress(), dataMap.get("Pickup_Address"));
                            action.click(safariPage.Button_Return());
                            action.click(safariPage.Button_Done());
                            action.clearSendKeys(safariPage.Textbox_DeliveryAddress(),dataMap.get("Delivery_Address"));
                            action.click(safariPage.Button_Return());
                        }
                        if(action.isElementPresent(safariPage.Text_DropOffValidation(true)))
                        {
                            action.clearSendKeys(safariPage.Textbox_DeliveryAddress(),dataMap.get("Delivery_Address"));
                            action.click(safariPage.Button_Return());
                        }
                    }
                    break;
                case "delivery details":
                    Map<String, String> dataMapDetails = data.transpose().asMap(String.class, String.class);
                    if(portalName.equalsIgnoreCase("fixed pricing portal")){

                        action.clearSendKeys(safariPage.Textbox_Instructions(),dataMapDetails.get("Special_Instruction"));
                        action.clearSendKeys(safariPage.Textbox_Items(),dataMapDetails.get("Product_Description"));
                        action.swipeUP();
                        action.clearSendKeys(safariPage.Textbox_PickupName(),dataMapDetails.get("Pickup_Contact_Name"));
                        action.clearSendKeys(safariPage.Textbox_PickupNumber(),dataMapDetails.get("Pickup_Contact_Phone"));
                        action.clearSendKeys(safariPage.Textbox_DropOffName(),dataMapDetails.get("Drop_Off_Contact_Name"));
                        action.clearSendKeys(safariPage.Textbox_DropOffNumber(),dataMapDetails.get("Drop_Contact_Phone"));
                        action.clearSendKeys(safariPage.Textbox_Receipt(),dataMapDetails.get("Reciept"));
                        action.swipeDown();
                        action.clearSendKeys(safariPage.Textbox_CustomerMobile(),dataMapDetails.get("Customer_Mobile"));
                        cucumberContextManager.setScenarioContext("CUSTOMER_PHONE",dataMapDetails.get("Customer_Mobile"));
                        action.click(safariPage.Button_Done());
                        action.swipeUP();
                    }
                    if(portalName.equalsIgnoreCase("kiosk mode portal")){
                        action.clearSendKeys(safariPage.Textbox_Instructions(),dataMapDetails.get("Special_Instruction"));
                        action.clearSendKeys(safariPage.Textbox_Items(),dataMapDetails.get("Product_Description"));
                        action.click(safariPage.Button_Done());
                        action.clearSendKeys(safariPage.Textbox_CustomerName(),dataMapDetails.get("Customer_Name"));
                        action.clearSendKeys(safariPage.Textbox_CustomerNumber(),dataMapDetails.get("Customer_Mobile"));
                        action.swipeUP();
                        action.clearSendKeys(safariPage.Textbox_PickupName(),dataMapDetails.get("Pickup_Contact_Name"));
                        action.clearSendKeys(safariPage.Textbox_PickupNumber(),dataMapDetails.get("Pickup_Contact_Phone"));
                        action.clearSendKeys(safariPage.Textbox_DropOffName(),dataMapDetails.get("Drop_Off_Contact_Name"));
                        action.clearSendKeys(safariPage.Textbox_DropOffNumber(),dataMapDetails.get("Drop_Contact_Phone"));
                        action.clearSendKeys(safariPage.Textbox_Receipt(),dataMapDetails.get("Reciept"));
                        action.click(safariPage.Button_Done());
                    }
                    break;
            }
            log("I should be able to enter all the details on "+pageName,"I am able to enter all the details on "+pageName,false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("I select {string} service level")
    public void iSelectServiceLevel(String serviceLevel) {
        try{
            action.click(safariPage.Dropdown_ServiceLevel());
            switch (serviceLevel){
                case "Threshold":
                    action.click(safariPage.Dropdown_Values(serviceLevel));
                    break;
            }
            log("I should be able to select "+serviceLevel+" service level","I am able to select "+serviceLevel+" service level",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And(("^I enter following Credit Card details on Partner Portal$"))
    public void i_enter_credit_card_details(DataTable data) {

        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String cardType = dataMap.get("Card_Type");
        String expiry = dataMap.get("Expiry");
        String postal_code = dataMap.get("Postal_Code");
        String cvv = dataMap.get("Cvv");
        try {
            String cardNumber;
            switch (cardType.toUpperCase()) {
                case "VISA CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.visa");
                    break;
                case "MASTER CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.mastercard");
                    break;
                default:
                    cardNumber = cardType;
            }

            String postalCodeValue="",cvvValue="";
            switch (cvv.toUpperCase()) {
                case "VALID CVV":
                    cvvValue = PropertyUtility.getDataProperties("valid.card.cvv");
                    break;
                case "INVALID CVV":
                    cvvValue = PropertyUtility.getDataProperties("invalid.card.cvv");
                    break;
                default:break;
            }

            switch (postal_code.toUpperCase()) {
                case "VALID POSTAL CODE":
                    postalCodeValue = PropertyUtility.getDataProperties("valid.card.postal.code");
                    break;
                case "INVALID POSTAL CODE":
                    postalCodeValue = PropertyUtility.getDataProperties("invalid.card.postal.code");
                    break;
                default:break;
            }
            action.swipeUP();
            action.sendKeys(safariPage.Textbox_CardNumber(),cardNumber);
            action.sendKeys(safariPage.Textbox_ExpirationDate(),expiry);
            action.sendKeys(safariPage.Textbox_CVV(),cvvValue);
            action.sendKeys(safariPage.Textbox_PostalCode(),postalCodeValue);

            cucumberContextManager.setScenarioContext("CARD_NUMBER", cardNumber);
            cucumberContextManager.setScenarioContext("CARD_EXPIRY", expiry);
            cucumberContextManager.setScenarioContext("Postal_Code", postalCodeValue);
            cucumberContextManager.setScenarioContext("CVV", cvvValue);

            pass("I should able enter " + cardNumber + " and " + expiry + " and " + postal_code + " and " + cvv + " on Card Details page",
                    "I entered " + cardNumber + " and " + expiry + " and " + postal_code + " and " + cvv + " on Card Details page",
                    true);
        } catch (Exception e) {
            fail(
                    "I should able enter " + cardType + " and " + expiry + " and " + postal_code + " and " + cvv + " on Card Details page",
                    "I was not able to entered " + cardType + " and " + expiry + " and " + postal_code + " and " + cvv + " on Card Details page",
                    true);
        }
    }

    @And("I select {string} load time")
    public void iSelectLoadTime(String loadTime) {
        try{
            Thread.sleep(3000);
            action.click(safariPage.Dropdown_LoadTime());
            switch (loadTime){
                case "15 minutes":
                    action.click(safariPage.Dropdown_LoadTimeValue(loadTime));
                    break;
            }
            log("I should be able to select "+loadTime+" as load time","I am able to select "+loadTime+" as load time",false);

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("I select {string} as Payment Method")
    public void iSelectAsPaymentMethod(String paymentMethod) {
        try{
            switch (paymentMethod){
                case "Partner Invoice":
                    action.click(safariPage.RadioButton_PartnerInvoice());
                    break;
                default:break;
            }
            log("I select "+paymentMethod+" as Payment Method","I have selected "+paymentMethod+" as Payment Method", false);

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I enter \"([^\"]*)\" password for Admin access$")
    public void i_enter_some_password_for_admin_access(String value) {
        try {
            switch (value) {
                case "valid":
                    action.clearSendKeys(safariPage.Textbox_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                    break;
            }
            log("I enter " + value + " password for Admin access", "I have entered " + value + " password for Admin access", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
}
