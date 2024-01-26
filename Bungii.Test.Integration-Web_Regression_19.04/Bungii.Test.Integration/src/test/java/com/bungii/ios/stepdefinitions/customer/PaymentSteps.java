package com.bungii.ios.stepdefinitions.customer;


import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.PaymentPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.*;


public class PaymentSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(PaymentSteps.class);
    PaymentPage paymentPage;
    ActionManager action = new ActionManager();

    public PaymentSteps(PaymentPage paymentPage) {
        this.paymentPage = paymentPage;
    }

    @Then("^PAYMENT page should be properly displayed$")
    public void payment_page_should_be_properly_displayed() {
        try {
            testStepVerify.isTrue(paymentPageInfoCorrect(), "Add New payment method should be displayed",
                    "Add New payment method is displayed",
                    "Add New payment method is not displayed");

            testStepVerify.isTrue(!isSaveButtonEnabled(), "Save button should be disabled",
                    "Save button should be disabled",
                    "Save button should be disabled");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I enter Card No:([^\"]*) and Expiry :([^\"]*) on Card Details page$")
    public void i_enter_and_on_card_details_page(String cardType, String expiry) {
        try {
            String cardNumber;
            switch (cardType.toUpperCase()) {
                case "VISA CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.visa");
                    break;
                case "DISCOVER CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.discover");
                    break;
                case "INVALID CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.invalid.card");
                    break;
                case "FRAUD CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.fraud.card");
                    break;
                default:
                    cardNumber = cardType;
            }
            addCardDetails(cardNumber, expiry);
            cucumberContextManager.setScenarioContext("CARD_NUMBER", cardNumber);
            cucumberContextManager.setScenarioContext("CARD_EXPIRY", expiry);
            pass("I should able enter " + cardType + " and " + expiry + " on Card Details page",
                    "I entered " + cardNumber + " and " + expiry + " on Card Details page",
                    true);
        }  catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Screen is not loaded properly. Probable rootcause : An SSL error occured and a secure connection to server cannot be made.", true);
        }
    }

    @And("^I enter postal code :(.+) and Cvv: (.+) on Card Details page$")
    public void i_enter_postal_code_and_cvv_on_card_details_page(String postalcode, String cvv) throws Throwable {

        try {
            String postalCodeValue = "", cvvValue = "";
            switch (postalcode.toUpperCase()) {
                case "VALID POSTAL CODE":
                    postalCodeValue = PropertyUtility.getDataProperties("valid.card.postal.code");
                    break;
/*            case "INVALID POSTAL CODE":
                postalCode = PropertyUtility.getDataProperties("payment.valid.card.discover");
                break;*/
            }
            switch (cvv.toUpperCase()) {
                case "VALID CVV":
                    cvvValue = PropertyUtility.getDataProperties("valid.card.cvv");
                    break;
/*            case "INVALID POSTAL CODE":
                postalCode = PropertyUtility.getDataProperties("payment.valid.card.discover");
                break;*/
            }
            action.sendKeys(paymentPage.TextBox_PostalCode(), postalCodeValue);
            action.sendKeys(paymentPage.TextBox_CVV(), cvvValue);
            pass("I enter postal code :" + postalcode + " and Cvv: " + cvv + " on Card Details page",
                    "I entered " + postalCodeValue + " and " + cvvValue + " on Card Details page", true);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Screen is not loaded properly. Probable rootcause : An SSL error occured and a secure connection to server cannot be made.", true);
        }
    }

    @When("^I swipe \"([^\"]*)\" card on the payment page$")
    public void iSwipeCardOnThePaymentPage(String cardType) {
        try {
                switch (cardType.toLowerCase()) {
                    case "default":
                        leftSwipeDefaultCard();
                        break;
                    case "other":
                        leftSwipeOtherCard();
                        break;
                    default:
                        error("UnImplemented Step or in correct app", "UnImplemented Step");
                        break;                }
            pass("I swipe" + cardType + " card on the payment page",
                    "I swipe" + cardType + " card on the payment page",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I get \"([^\"]*)\" default card$")
    public void i_get_something_default_card(String strArg1) {
        try {
            String currentDefaultCard = getDefaultCardNumber();
            cucumberContextManager.setScenarioContext("DEFAULT_CARD", currentDefaultCard);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on Payment page$")
    public void i_should_see_something_on_payment_page(String message) {
        try {
            switch (message.toLowerCase()) {
                case "invalid card":
                    testStepVerify.isTrue(isFieldInvalid("card number"), "I should see " + message + " error",
                            "I was able to see " + message + " error",
                            "I was not able to see " + message + " error");
                    break;
                case "invalid expiry":
                    Thread.sleep(5000);
                    testStepVerify.isTrue(isFieldInvalid("expiry"), "I should see " + message + " error",
                            "I was able to see " + message + " error",
                            "I was not able to see " + message + " error");
                    break;
                case "the card has been deleted":
                    int noOfCard = getNumberOfOtherCard();
                    int beforeDelete = (int) cucumberContextManager.getScenarioContext("NO_CARD_BEFORE");
                    testStepVerify.isTrue(noOfCard == beforeDelete - 1, "Card should deleted from payment page",
                            "Card is deleted from payment page",
                            "Card is not deleted from payment page");
                    break;
                case "new card":
                    String cardNumber = (String) cucumberContextManager.getScenarioContext("CARD_NUMBER");

                    testStepVerify.isTrue(isCardPresent(cardNumber.substring(12)), "I should see " + message + "",
                            "I was able to see " + message + " ",
                            "I was not able to see " + message + " ");
                    break;
                case "new default card":
                    String prvDefaultCard = (String) cucumberContextManager.getScenarioContext("DEFAULT_CARD");
                    String currentDefaultCard = getDefaultCardNumber();
                    testStepVerify.isTrue(!prvDefaultCard.equals(currentDefaultCard), "I should see " + message + "",
                            "Previous default card was " + prvDefaultCard + " and new card is " + currentDefaultCard,
                            "Previous card is same as new default card");
                    break;
                case "no delete button":
                    testStepVerify.isElementNotEnabled(paymentPage.Button_Delete(true),"Delete button should not be displayed","Delete button is not displayed","Delete button is displayed");
                    break;

                case "There was a problem processing your credit card; please double check your payment information and try again.":

                    testStepVerify.isEquals(action.getAlertMessage(),message, "I should see " + message + " error",
                            "I was able to see " + message + " error",
                            "I was not able to see " + message + " error");
                    break;


                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I tap on \"([^\"]*)\" on Payment page$")
    public void i_tap_on_something_on_payment_page(String key) {
        try {

            if (key.equalsIgnoreCase("Delete")) {
                cucumberContextManager.setScenarioContext("NO_CARD_BEFORE", getNumberOfOtherCard());
                tabDeleteButton();
            } else if (key.equalsIgnoreCase("Other card")) {
                String defaultCard = (String) cucumberContextManager.getScenarioContext("DEFAULT_CARD");
                boolean clicked = clickOtherCard(defaultCard);
                testStepAssert.isTrue(clicked, "I should able to click on card having different last 4 digit then default one",
                        "I was able to click Other card not having card number " + defaultCard,
                        "I was not able to click Other card not having card number " + defaultCard);
            } else if (key.equalsIgnoreCase("Checkbox")) {
                selectCheckBox();

            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * Check if save button is enabled
     *
     * @return boolean value if save button is enabled
     */
    public boolean isSaveButtonEnabled() {
        return paymentPage.isElementEnabled(paymentPage.Button_Save());
    }


    /**
     * Check if add image is present on screen
     *
     * @return boolean value is add image is present on screen
     */
    public boolean isAddImagePresent() {
        return paymentPage.isElementEnabled(paymentPage.Image_Add());
    }

    /**
     * Check if add button is present on screen
     *
     * @return boolean value is add button is present on screen
     */
    public boolean isAddButtonPresent() {
        return paymentPage.isElementEnabled(paymentPage.Button_ADD());
    }

    /**
     * Check if payment page info is correct
     *
     * @return boolean value that page paye info message is correct
     */
    public boolean paymentPageInfoCorrect() {
        return paymentPage.isElementEnabled(paymentPage.Button_AddNew()) && paymentPage.isElementEnabled(paymentPage.Text_PaymentMethod());
    }


    /**
     * Click(make default) if any other card other than existing default card
     *
     * @param defaultCard Default value of payment
     * @return
     */
    public boolean clickOtherCard(String defaultCard) {
        try {
          //  action.click(paymentPage.findElement("//XCUIElementTypeOther[@name='Other cards']/following::XCUIElementTypeCell/XCUIElementTypeStaticText[2][@name!='" + defaultCard + "']", PageBase.LocatorType.XPath));
            action.tapByElement(paymentPage.findElement("//XCUIElementTypeOther[@name='Other cards']/following::XCUIElementTypeCell/XCUIElementTypeStaticText[1][@name!='" + defaultCard + "']", PageBase.LocatorType.XPath));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * select checkbox
     */
    public void selectCheckBox() {
        action.click(paymentPage.Button_CheckBoxOff());
    }

    /**
     * Check if field is valid
     *
     * @param field Identifier for field
     * @return boolean value if field is valid
     */
    public boolean isFieldInvalid(String field) {
        boolean flag = false;
        switch (field.toLowerCase()) {
            case "card number":
                flag = paymentPage.isElementEnabled(paymentPage.TextBox_InvalidCardNumber());
                break;
            case "expiry":
                flag = paymentPage.isElementEnabled(paymentPage.TextBox_InvalidExpiry());
                break;
            default:
                break;
        }
        return flag;
    }

    /**
     * Click on delete button
     */
    public void tabDeleteButton() {
        action.click(paymentPage.Button_Delete());
    }

    /**
     * Left swipe the card to delete it
     */
    public void leftSwipeOtherCard() {
        action.swipeLeft(paymentPage.Cell_CardNumber().get(0));
    }

    /**
     * Left swipe the default card to delete it
     */
    public void leftSwipeDefaultCard() {
        action.swipeLeft(paymentPage.Cell_DefaultCard());
    }

    /**
     * Get value of default card
     *
     * @return Get default card number
     */
    public String getDefaultCardNumber() {
        return action.getValueAttribute(paymentPage.Cell_DefaultCardNumber());
    }

    /**
     * Return number of other cards
     *
     * @return Number of other cards
     */
    public int getNumberOfOtherCard() {
        paymentPage.Cell_CardNumber();
        return paymentPage.Cell_CardNumber().size();
    }

    /**
     * Check if card number is present
     *
     * @param cardNumber Card number that is to be searched
     * @return
     */
    public boolean isCardPresent(String cardNumber) {
        paymentPage.visibilityOf(paymentPage.Button_AddNew());

        boolean isPresent = false;
        List<WebElement> cards = paymentPage.Text_CardNumber();

        if(cards.size()==0)
            cards=paymentPage.Text_CardNumber_iOS11_2();
        for (WebElement card : cards) {
            if (action.getValueAttribute(card).equalsIgnoreCase(cardNumber)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    /**
     * Add new card with given details
     *
     * @param cardNo Card number of new card
     * @param expiry expiry of new card
     */
    public void addCardDetails(String cardNo, String expiry) {
//		action.waitForExpectedElement(paymentPage.TextBox_CardNumber());
        action.sendKeys(paymentPage.TextBox_CardNumber(), cardNo);
        action.sendKeys(paymentPage.TextBox_expiry(), expiry);
    }


    /**
     * Check if add payment method is enabled
     */
    public boolean isAddPaymentMethodEnabled() {
        return paymentPage.isElementEnabled(paymentPage.Button_AddPayment());
    }
}
