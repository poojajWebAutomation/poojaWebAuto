package com.bungii.android.stepdefinitions;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.otherApps.ChromePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.common.manager.ResultManager.fail;

public class MobileFriendlySteps extends DriverBase {
    private static LogUtility logger = new LogUtility(NotificationSteps.class);
    ActionManager action = new ActionManager();
    ChromePage chromePage=new ChromePage();
    GeneralUtility utility = new GeneralUtility();

    @And("I open {string} partner portal")
    public void iOpenPartnerPortal(String portal) {
        try{

            switch (portal){
                case "weight based":
                    action.clearSendKeys(chromePage.Textbox_GoogleSearchBar(),PropertyUtility.getDataProperties("qa.fnd_service_level_partner.url"));
                    action.click(chromePage.DropDown_FirstValue());
                    break;
                case "geofence based":
                    action.clearSendKeys(chromePage.Textbox_GoogleSearchBar(),PropertyUtility.getDataProperties("qa.equip-bid.url"));
                    action.click(chromePage.DropDown_FirstValue());
                    break;
                case "fixed pricing":
                    action.clearSendKeys(chromePage.Textbox_GoogleSearchBar(),PropertyUtility.getDataProperties("qa.service_level_partner.url"));
                    action.click(chromePage.DropDown_FirstValue());
                    break;
            }
            action.waitUntilIsElementExistsAndDisplayed(chromePage.Textbox_EnterPassword());
            action.clearSendKeys(chromePage.Textbox_EnterPassword(),PropertyUtility.getDataProperties("PartnerPassword"));
            action.click(chromePage.Button_SignIn());
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
                    action.waitUntilIsElementExistsAndDisplayed(chromePage.Icon_BungiiLogo());
                    action.scrollToTop();
                    testStepAssert.isElementDisplayed(chromePage.Icon_BungiiLogo(),
                            "Bungii Logo should be displayed",
                            "Bungii Logo is displayed",
                            "Bungii Logo is not displayed");
                    testStepAssert.isElementDisplayed(chromePage.Text_WhatsNeeded(),
                            "Whats needed section should be displayed",
                            "Whats needed section is displayed",
                            "Whats needed section is not displayed");
                    testStepAssert.isElementDisplayed(chromePage.Icon_Logout(),
                            "Log-out icon should be displayed",
                            "Log-out icon is displayed",
                            "Log-out icon is not displayed");
                    action.scrollToBottom();
                    testStepAssert.isElementDisplayed(chromePage.Text_DeliveryAddress(),
                            "Delivery address section should be displayed",
                            "Delivery address section is displayed",
                            "Delivery address section is not displayed");
                    testStepAssert.isElementDisplayed(chromePage.Text_PickUpDate(),
                            "Pick-up date section should be displayed",
                            "Pick-up date section is displayed",
                            "Pick-up date section is not displayed");
                    testStepAssert.isElementDisplayed(chromePage.Text_PickUpTime(),
                            "Pick-up time section should be displayed",
                            "Pick-up time section is displayed",
                            "Pick-up time section is not displayed");
                    switch (portalName){
                        case "weight based":
                            action.scrollToTop();
                            action.scrollToTop();
                            testStepAssert.isElementDisplayed(chromePage.Icon_PartnerLogo(),
                                    "Partner Logo should be displayed",
                                    "Partner Logo is displayed",
                                    "Partner Logo is not displayed");
                            testStepAssert.isElementDisplayed(chromePage.Text_Pallet1(),
                                    "Pallet-1 section should be displayed",
                                    "Pallet-1 section is displayed",
                                    "Pallet-1 section is not displayed");
                            testStepAssert.isElementDisplayed(chromePage.Text_Pallet2(),
                                    "Pallet-2 section should be displayed",
                                    "Pallet-2 section is displayed",
                                    "Pallet-2 section is not displayed");
                            testStepAssert.isElementDisplayed(chromePage.Text_CustomQuotes(),
                                    "Custom Quotes section should be displayed",
                                    "Custom Quotes section is displayed",
                                    "Custom Quotes section is not displayed");
                            action.scrollToBottom();
                            testStepAssert.isElementDisplayed(chromePage.Text_PickUpAddress(),
                                    "Pick-up address section should be displayed",
                                    "Pick-up address section is displayed",
                                    "Pick-up address section is not displayed");
                            testStepAssert.isElementDisplayed(chromePage.Text_ServiceLevel(),
                                    "Service Level section should be displayed",
                                    "Service Level section is displayed",
                                    "Service Level section is not displayed");
                            break;
                        case "geofence based":
                            action.scrollToTop();
                            testStepAssert.isElementDisplayed(chromePage.Text_Solo(),
                                    "Solo driver section should be displayed",
                                    "Solo driver section is displayed",
                                    "Solo driver section is not displayed");
                            testStepAssert.isElementDisplayed(chromePage.Text_Duo(),
                                    "Duo driver section should be displayed",
                                    "Duo driver section is displayed",
                                    "Duo driver section is not displayed");
                            testStepAssert.isElementDisplayed(chromePage.Text_PickUpAddress(),
                                    "Pick-up address section should be displayed",
                                    "Pick-up address section is displayed",
                                    "Pick-up address section is not displayed");
                            action.scrollToBottom();
                            testStepAssert.isElementDisplayed(chromePage.Button_GetQuote(),
                                    "Get quote button should be displayed",
                                    "Get quote button is displayed",
                                    "Get quote button is not displayed");
                            break;
                    }
                    break;
                case "success":
                    testStepAssert.isElementDisplayed(chromePage.Text_SucessMsg(),
                            "Success message should be displayed",
                            "Success message is displayed",
                            "Success message is not displayed");
                    testStepAssert.isElementDisplayed(chromePage.Text_TrackingId(),
                            "Tracking ID should be displayed",
                            "Tracking ID is displayed",
                            "Tracking ID is not displayed");
                    testStepAssert.isElementDisplayed(chromePage.Text_Time(),
                            "Schedule time should be displayed",
                            "Schedule time is displayed",
                            "Schedule time is not displayed");
                    action.scrollToBottom();
                    testStepAssert.isElementDisplayed(chromePage.Text_Drivers(),
                            "Whats needed section should be displayed",
                            "Whats needed section is displayed",
                            "Whats needed section is not displayed");
                    testStepAssert.isElementDisplayed(chromePage.Text_Payment(),
                            "Payment section should be displayed",
                            "Payment section is displayed",
                            "Payment section is not displayed");
                    testStepAssert.isElementDisplayed(chromePage.Button_NewBungii(),
                            "New Bungii button should be displayed",
                            "New Bungii button is displayed",
                            "New Bungii button is not displayed");
                    break;

            }
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
                    if(portalName.equalsIgnoreCase("weight based portal"))
                    {
                        action.click(chromePage.Button_Edit());
                        action.click(chromePage.Button_ClearAddress());
                        utility.selectAddress(chromePage.Textbox_PickUpAddress(),dataMap.get("Pickup_Address"));
                        action.waitUntilIsElementExistsAndDisplayed(chromePage.Textbox_DeliveryAddress());
                        utility.selectAddress(chromePage.Textbox_DeliveryAddress(),dataMap.get("Delivery_Address"));
                        action.scrollToBottom();
                        action.click(chromePage.Dropdown_ServiceLevel());
                        action.click(chromePage.Dropdown_FirstThreshold());
                    }
                    if(portalName.equalsIgnoreCase("geofence based"))
                    {
                        action.scrollToTop();
                        action.scrollToTop();
                        action.click(chromePage.Button_Edit());
                        action.click(chromePage.Button_ClearAddress());
                        utility.selectAddress(chromePage.Textbox_PickUpAddress(),dataMap.get("Pickup_Address"));
                        action.scrollToBottom();
                        action.waitUntilIsElementExistsAndDisplayed(chromePage.Textbox_DeliveryAddress());
                        utility.selectAddress(chromePage.Textbox_DeliveryAddress(),dataMap.get("Delivery_Address"));
                        action.click(chromePage.Text_Disclaimer());
                    }
                    break;
                case "delivery details":
                    Map<String, String> dataMapDetails = data.transpose().asMap(String.class, String.class);
                    if(portalName.equalsIgnoreCase("weight based portal")){
                        action.waitUntilIsElementExistsAndDisplayed(chromePage.Textbox_Items());
                        action.clearSendKeys(chromePage.Textbox_Items(),dataMapDetails.get("Product_Description"));
                        action.clearSendKeys(chromePage.Textbox_Dimensions(),dataMapDetails.get("Dimensions"));
                        action.clearSendKeys(chromePage.Textbox_Weight(),dataMapDetails.get("Weight"));
                        action.scrollToBottom();
                        action.clearSendKeys(chromePage.Textbox_CustomerName(),dataMapDetails.get("Customer_Name"));
                        action.clearSendKeys(chromePage.Textbox_CustomerNumber(),dataMapDetails.get("Customer_Mobile"));
                        action.scrollToBottom();
                        action.clearSendKeys(chromePage.Textbox_DeliveryPurpose(),dataMapDetails.get("Delivery_Purpose"));
                        action.clearSendKeys(chromePage.Textbox_RbNumber(),dataMapDetails.get("Rb_Sb_Number"));
                        action.clearSendKeys(chromePage.Textbox_ScheduleBy(),dataMapDetails.get("ScheduledBy"));
                    }
                    if(portalName.equalsIgnoreCase("geofence based portal")){
                        action.waitUntilIsElementExistsAndDisplayed(chromePage.Textbox_ItemsToDeliver());
                        action.clearSendKeys(chromePage.Textbox_ItemsToDeliver(),dataMapDetails.get("Product_Description"));
                        action.scrollToBottom();
                        action.clearSendKeys(chromePage.Textbox_CustomerName(),dataMapDetails.get("Customer_Name"));
                        action.clearSendKeys(chromePage.Textbox_CustomerNumber(),dataMapDetails.get("Customer_Mobile"));
                        action.clearSendKeys(chromePage.Textbox_PickupName(),dataMapDetails.get("Pickup_Contact_Name"));
                        action.scrollToBottom();
                        action.clearSendKeys(chromePage.Textbox_PickupNumber(),dataMapDetails.get("Pickup_Contact_Phone"));
                        action.clearSendKeys(chromePage.Textbox_DropOffName(),dataMapDetails.get("Drop_Off_Contact_Name"));
                        action.clearSendKeys(chromePage.Textbox_DropOffNumber(),dataMapDetails.get("Drop_Contact_Phone"));
                        action.clearSendKeys(chromePage.Textbox_DeliveryPurpose(),dataMapDetails.get("Delivery_Purpose"));
                        action.clearSendKeys(chromePage.Textbox_LotNumber(),dataMapDetails.get("Lot_Number"));
                        action.clearSendKeys(chromePage.Textbox_BidderNumber(),dataMapDetails.get("Bidder_Number"));
                        action.scrollToBottom();
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
            action.sendKeys(chromePage.Textbox_CardNumber(),cardNumber);
            action.sendKeys(chromePage.Textbox_ExpirationDate(),expiry);
            action.sendKeys(chromePage.Textbox_CVV(),cvvValue);
            action.sendKeys(chromePage.Textbox_PostalCode(),postalCodeValue);



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
}
