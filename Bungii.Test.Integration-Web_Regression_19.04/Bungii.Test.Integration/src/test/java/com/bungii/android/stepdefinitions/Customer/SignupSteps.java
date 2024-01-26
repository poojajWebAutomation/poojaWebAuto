package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.SignupPage;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.ios.enums.REFERRAL_SOURCE;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;

public class SignupSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    SignupPage Page_Signup = new SignupPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbutility = new DbUtility();

    @When("^I enter \"([^\"]*)\" customer phone number on Signup Page$")
    public void i_enter_something_customer_phone_number_on_signup_page(String strArg1) throws Throwable {
        try {
            String customerPhone = "";
            switch (strArg1) {
                case "unique":
                    customerPhone = utility.generateMobileNumber();
                    cucumberContextManager.setScenarioContext("CUSTOMER_HAVING_REF_CODE", customerPhone);
                    cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", customerPhone);
                    break;
                case "new valid":
                    customerPhone = utility.generateMobileNumber();
                    cucumberContextManager.setScenarioContext("CUSTOMER_DELETE_ACCOUNT", customerPhone);
                    cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", customerPhone);
                    break;
                case "deleted valid":
                    customerPhone = (String) cucumberContextManager.getScenarioContext("CUSTOMER_DELETE_ACCOUNT");
                    cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", customerPhone);
                    break;
                case "blank":
                    break;
                case "invalid":
                    customerPhone = PropertyUtility.getDataProperties("customer.phone.number.invalid");
                    break;
                case "existing":
                    customerPhone = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            if (StringUtils.isNumeric(customerPhone)) {
                //element.sendKeys();
                Page_Signup.TextField_Phonenumber().click();
                utility.inputOnNumberKeyBoard(customerPhone);
            }
            //action.enterText(Page_Signup.TextField_Phonenumber(), customerPhone);
            cucumberContextManager.setScenarioContext("CustomerPhoneNum", customerPhone);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I enter \"([^\"]*)\" data in mandatory fields on Signup Page$")
    public void i_enter_something_data_in_mandatory_fields_on_signup_page(String strArg1) throws Throwable {
        try {
            String firstName="";

            switch (strArg1) {
                case "valid":
                    action.clearSendKeys(Page_Signup.TextField_FirstName(),PropertyUtility.getDataProperties("customer.first.name")+ RandomGeneratorUtility.getData("{RANDOM_STRING}",3));
                     firstName= Page_Signup.TextField_FirstName().getText();
                    cucumberContextManager.setScenarioContext("FIRST_NAME",firstName);
                    action.clearSendKeys(Page_Signup.TextField_LastName(), PropertyUtility.getDataProperties("customer.last.name"));
                    action.click(Page_Signup.TextField_Email());
                    String emailAddress="bungiiauto+"+RandomGeneratorUtility.getData("{RANDOM_STRING}",4)+"@gmail.com";
                    cucumberContextManager.setScenarioContext("NEW_USER_EMAIL_ADDRESS",emailAddress);
                    cucumberContextManager.setScenarioContext("NEW_USER_FIRST_NAME",firstName);
                    action.enterText(Page_Signup.TextField_Email(),emailAddress);
                    action.hideKeyboard();
                    //    action.clearsendKeys(Page_Signup.TextField_Email(), /*PropertyUtility.getDataProperties("customer.email")*/"@cc.com");
                    action.clearSendKeys(Page_Signup.TextField_Password(), PropertyUtility.getDataProperties("customer.password.new.password"));
                    action.hideKeyboard();
                    action.click(Page_Signup.Select_ReferralSource());
                    action.click(Page_Signup.Option_ReferralSource());
                    action.click(Page_Signup.Link_ReferralSourceDone());
                    String customerName=firstName+" "+PropertyUtility.getDataProperties("customer.last.name");
                    cucumberContextManager.setFeatureContextContext("CUSTOMER",customerName);
                    cucumberContextManager.setScenarioContext("CUSTOMER",customerName);
                    break;
                case "valid test":
                    action.clearSendKeys(Page_Signup.TextField_FirstName(),"Testcustomertywd"+ RandomGeneratorUtility.getData("{RANDOM_STRING}",3));
                    firstName= Page_Signup.TextField_FirstName().getText();
                    cucumberContextManager.setScenarioContext("FIRST_NAME",firstName);
                    action.clearSendKeys(Page_Signup.TextField_LastName(), PropertyUtility.getDataProperties("customer.last.name"));
                    action.click(Page_Signup.TextField_Email());
                    action.enterText(Page_Signup.TextField_Email(),PropertyUtility.getDataProperties("customer.email"));
                    action.hideKeyboard();
                    //    action.clearsendKeys(Page_Signup.TextField_Email(), /*PropertyUtility.getDataProperties("customer.email")*/"@cc.com");
                    action.clearSendKeys(Page_Signup.TextField_Password(), PropertyUtility.getDataProperties("customer.password.new.password"));
                    action.hideKeyboard();
                    action.click(Page_Signup.Select_ReferralSource());
                    action.click(Page_Signup.Option_ReferralSource());
                    action.click(Page_Signup.Link_ReferralSourceDone());
                    break;
                case "blank":
                    action.clearSendKeys(Page_Signup.TextField_FirstName(), "");
                    action.clearSendKeys(Page_Signup.TextField_LastName(), "");
                    action.enterText(Page_Signup.TextField_Email(), "");
                    action.clearSendKeys(Page_Signup.TextField_Password(), "");
                    break;

                case "invalid":
                    action.click(Page_Signup.TextField_Email());
                    action.enterText(Page_Signup.TextField_Email(),PropertyUtility.getDataProperties("customer.email.invalid"));
                    action.hideKeyboard();
                    //action.sendKeys(Page_Signup.TextField_Email(), PropertyUtility.getDataProperties("customer.email.invalid"));
                    action.sendKeys(Page_Signup.TextField_Password(), PropertyUtility.getDataProperties("customer.password.invalid"));
                    Page_Signup.TextField_FirstName().click();
                    action.hideKeyboard();
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I enter \"([^\"]*)\" Verification code$")
    public void i_enter_something_verificationcode(String strArg1) {
        String smsCode = "";
        try {
            switch (strArg1) {
                case "valid":
                    Thread.sleep(20000);
                    smsCode = dbutility.getVerificationCode((String) cucumberContextManager.getScenarioContext("CustomerPhoneNum"));
                    break;
                default:
                        error("UnImplemented Step or incorrect button name", "UnImplemented Step");
            }
            action.enterText(Page_Signup.Textfield_SMSCode(), smsCode);

            pass("I should able to enter verification code",
                    "I entered verification code : " + smsCode + "in sms code field", true);
            //TODO:REMOVE THIS
            //  Thread.sleep(20000);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("I am on Sign up page")
    public void iAmOnSignUpPage() {
        try {
            if (!action.isElementPresent(Page_Signup.Header_SignUp(true)))
                utility.goToSignupPage();
            Thread.sleep(5000);
            testStepVerify.isElementDisplayed(Page_Signup.Header_SignUp(true), "Signup button should be displayed ", "Sign up button is displayed", "Sign up button is not displayed");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I tap on the \"([^\"]*)\" button on Signup Page$")
    public void i_tap_on_the_something_button_on_signup_page(String strArg1) throws Throwable {
        try {
        switch (strArg1) {
            case "Sign Up":
                action.scrollToBottom();
                action.click(Page_Signup.Button_Signup());
                break;
            case "No, Continue":
                action.click(Page_Signup.Button_NoReferralConfirm());
                break;
            case "Yes":
                action.click(Page_Signup.Button_NoReferralYes());
                break;
            case"Log in":
                action.click(Page_Signup.Link_Login());
                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^the new user should see \"([^\"]*)\"$")
    public void the_new_user_should_see_something(String strArg1) throws Throwable {
        try {
            String actual = "";
        switch (strArg1) {
            case "sign up button disabled":
                testStepVerify.isElementNotEnabled(Page_Signup.Button_Signup(true), "Signup button should be disabled", "Signup button is disabled", "Signup button is enabled");
                break;

            case "validations for all fields":
                testStepVerify.isElementTextEquals(Page_Signup.Cust_Signup_Error_Email(), PropertyUtility.getMessage("customer.signup.invalid.email.android"));
                testStepVerify.isElementTextEquals(Page_Signup.Cust_Signup_Error_Phone(), PropertyUtility.getMessage("customer.signup.invalid.phone.number.android"));
                testStepVerify.isElementTextEquals(Page_Signup.Cust_Signup_Error_Password(), PropertyUtility.getMessage("customer.signup.invalid.password.android"));
                break;

            case "Signup page":
                testStepAssert.isElementDisplayed(Page_Signup.Button_Signup(), "Signup button should be displayed", "Signup button is displayed ", "Signup button is not displayed");
                testStepAssert.isTrue(utility.isCorrectPage("Signup"), "Signup should be displayed", "Signup page is displayed", "Signup page is not displayed");
                break;

            case "snackbar validation message for existing user":
                testStepVerify.isEquals(utility.getCustomerSnackBarMessage(), PropertyUtility.getMessage("customer.signup.existinguser"), "Warning message for Existing message should be displayed", "Snackbar message is displayed", "Snackbar message is not displayed");
                break;
            case "Inactive Promo Code message":
                 actual = utility.getSignupAlertMessage();
                testStepAssert.isEquals(actual, PropertyUtility.getMessage("customer.signup.inactivepromo.android"), "Alert message for Inactive Promo Code should be displayed", "Alert message is displayed : " + PropertyUtility.getMessage("customer.signup.inactivepromo.android"), "Alert message is not displayed : " + PropertyUtility.getMessage("customer.signup.inactivepromo.android") + " | Actual : "+ actual);
                action.click(Page_Signup.Button_Yes());
                break;
            case "Invalid Promo Code message":
                 actual = utility.getSignupAlertMessage();
                testStepAssert.isEquals(actual, PropertyUtility.getMessage("customer.promos.invalid"), "Alert message for Invalid Promo Code should be displayed", "Alert message is displayed : " + PropertyUtility.getMessage("customer.promos.invalid"), "Alert message is not displayed : " + PropertyUtility.getMessage("customer.promos.invalid") + " | Actual : "+ actual);
                action.click(Page_Signup.Button_Yes());

                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Validation message not displayed ",
                true);
    }
    }


    @And("^I enter \"([^\"]*)\" promo code on Signup Page$")
    public void i_enter_something_promo_code_on_signup_page(String strArg1) throws Throwable {
        try {
        String strPromoCode = "";
        switch (strArg1) {
            case "ValidPercent":
                strPromoCode = PropertyUtility.getDataProperties("promocode.valid.percent");
                break;
            case "invalid":
                strPromoCode = PropertyUtility.getDataProperties("promocode.invalid");
                break;
            case "Referral":
                strPromoCode = PropertyUtility.getDataProperties("referral.code");
                break;
            case "Code":
                strPromoCode= (String) cucumberContextManager.getScenarioContext("INVITE_CODE");
                break;
            case "FutureActive":
                strPromoCode = PropertyUtility.getDataProperties("promocode.futureactive");
                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }
        action.click(Page_Signup.CheckBox_Promo());
        String isChecked=action.getAttribute(Page_Signup.CheckBox_Promo(), "checked");
        if(isChecked.equals("false"))
        {
            action.click(Page_Signup.CheckBox_Promo());
        }

        action.enterText(Page_Signup.TextField_Referral(), strPromoCode);
        action.hideKeyboard();
        log("I should able to enter Promo code in signup Page ",
                "I entered  " + strPromoCode + " as " + strArg1 + "promoCode", true);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I Select Referral source$")
    public void i_select_referral_source() throws Throwable {
            try {
                action.click(Page_Signup.Select_ReferralSource());
                action.click(Page_Signup.Option_ReferralSource());
                action.click(Page_Signup.Link_ReferralSourceDone());
            } catch (Exception e) {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error( "Step  Should be successful",
                        "Error performing step,Please check logs for more details", true);
            }
        }

}
