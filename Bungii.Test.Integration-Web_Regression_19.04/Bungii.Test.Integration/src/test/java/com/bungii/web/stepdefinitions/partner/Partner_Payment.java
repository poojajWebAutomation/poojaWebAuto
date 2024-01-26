package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.*;

public class Partner_Payment extends DriverBase {

    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();
    ActionManager action = new ActionManager();


    @And("^I Select \"([^\"]*)\" as Payment Method$")
    public void i_select_something_as_payment_method(String str) throws InterruptedException {
try{
        switch (str){
            case "Customer Card":
                action.click(Page_Partner_Delivery.Radio_Button_Customer_Card());
                Thread.sleep(5000);
                break;
            case "Partner Pay":
                action.click(Page_Partner_Delivery.Radio_Button_Partner_Pay());
                break;
            case "Partner Invoice":
                action.click(Page_Partner_Delivery.RadioButton_PartnerInvoice());
                break;
            default:break;

        }
        log("I select "+str+" as Payment Method","I have selected "+str+" as Payment Method", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And(("^I enter following Credit Card details on Partner Portal$"))
    public void i_enter_credit_card_details(DataTable data) {

        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String cardType = dataMap.get("CardNo");
        String expiry = dataMap.get("Expiry");
        String postal_code = dataMap.get("Postal_Code");
        String cvv = dataMap.get("Cvv");

        //action.iframeSwitch();

        try {
            String cardNumber;
            switch (cardType.toUpperCase()) {
                case "VISA CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.visa");
                    break;
                case "VISA CARD2":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.visa2");
                    break;
                case "VISA CARD3":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.visa3");
                    break;
                case "VISA CARD4":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.visa4");
                    break;
                case "VISA CARD5":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.visa5");
                    break;
                case "VISA CARD6":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.visa6");
                    break;
                case "MASTER CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.mastercard");
                    break;
                case "MASTER CARD2":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.mastercard2");
                    break;
                case "DISCOVER CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.discover");
                    break;
                case "INVALID CARD":
                    cardNumber = PropertyUtility.getDataProperties("partner.invalid.card");
                    break;
                case "FRAUD CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.fraud.card");
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


            addCardDetails(cardNumber, expiry, cvvValue,postalCodeValue);


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

    @Then("^I should \"([^\"]*)\" on partner portal$")
    public void i_should_see_some_validation_message(String str){
try{
        switch(str){
            case "see validation message for invalid card number":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Message_Invalid_CardNumber()), PropertyUtility.getMessage("Invalid_Card_Number"));
                break;
            case "see validation message for Expired date":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Message_Invalid_ExpiredDate()), PropertyUtility.getMessage("Invalid_Expired_Date"));
                break;
            case "see validation message for Cvv":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Message_Invalid_CVV()), PropertyUtility.getMessage("Invalid_Cvv"));
                break;
            case "see validation message for Postal Code":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Message_Invalid_Postal_Code()), PropertyUtility.getMessage("Invalid_Postal_Code"));
                break;
            case "see validation message for fraud card number":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Message_Toast()), PropertyUtility.getMessage("payment.declined.error"));
                break;
            default:break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    public void addCardDetails(String cardNo, String expiry, String cvv, String postalCode) throws InterruptedException {
        Thread.sleep(1000);

        action.switchToFrame("braintree-hosted-field-number");
        action.click(Page_Partner_Delivery.TextBox_Card_Number());
        action.sendKeys(Page_Partner_Delivery.TextBox_Card_Number(),cardNo);
        Thread.sleep(1000);
        action.switchToMainFrame();
        //action.switchToFrame("Main");

        action.switchToFrame("braintree-hosted-field-expirationDate");
        action.click(Page_Partner_Delivery.TextBox_Expiry_Date());
        action.sendKeys(Page_Partner_Delivery.TextBox_Expiry_Date(), expiry);
        action.switchToMainFrame();

        action.switchToFrame("braintree-hosted-field-postalCode");
        action.click(Page_Partner_Delivery.TextBox_Postal_Code());
        action.sendKeys(Page_Partner_Delivery.TextBox_Postal_Code(), postalCode);
        action.switchToMainFrame();

        action.switchToFrame("braintree-hosted-field-cvv");
        action.click(Page_Partner_Delivery.TextBox_CVV());
        action.sendKeys(Page_Partner_Delivery.TextBox_CVV(), cvv);
        action.switchToMainFrame();

        Thread.sleep(1000);
    }
}
