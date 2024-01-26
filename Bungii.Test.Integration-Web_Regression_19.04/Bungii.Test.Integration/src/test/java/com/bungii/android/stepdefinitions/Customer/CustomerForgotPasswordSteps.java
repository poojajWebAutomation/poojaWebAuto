package com.bungii.android.stepdefinitions.Customer;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.driver.AccountsPage;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.bungii.common.manager.ResultManager.*;

public class CustomerForgotPasswordSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(CustomerForgotPasswordSteps.class);
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    SignupPage Page_Signup = new SignupPage();
    LoginPage Page_CustomerLogin = new LoginPage();
    AccountPage Page_AccountInfo = new AccountPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbutility = new DbUtility();
    TermsPage Page_CustTerms = new TermsPage();
    HomePage homePage = new HomePage();
    String PreviousSMSCode = "";


    @And("I tap on the \"([^\"]*)\" Link")
    public void iTapOnTheLink(String arg0) {
        try {

            switch (arg0) {
                case "Login":
                    action.click(Page_Signup.Link_Login());
                    break;
                case "Forgot Password":
                    action.click(Page_CustomerLogin.Link_ForgotPassword());
                    break;
                case "Send":
                    action.click(forgotPasswordPage.Button_ForgotPass_Send());
                    break;
                case "Continue":
                    action.click(forgotPasswordPage.Button_Continue());
                    break;
                case "Sign Up":
                    action.click(Page_Signup.Button_Signup());
                    break;
                case "Verification Continue":
                    action.click(Page_Signup.Button_VerifyContinue());
                    break;
                case "Resend Code":
                    action.click(Page_Signup.Link_Resend());
                    break;
                case "Delete account":
                    action.click(Page_AccountInfo.Link_DeleteAccount());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log(" I tap on the " + arg0 + "Link",
                    "I tapped on " + arg0, false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("I enter \"([^\"]*)\" Phone Number")
    public void i_enter_Phone_Number(String string) {
        try {

            switch (string) {
                case "valid":
                    action.sendKeys(forgotPasswordPage.TextField_ForgotPass_PhoneNumber(), PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                    break;
                case "invalid":
                    action.sendKeys(forgotPasswordPage.TextField_ForgotPass_PhoneNumber(), PropertyUtility.getDataProperties("customer_Invalid.phonenumber"));
                    break;
                case "less than 10 digit":
                    action.sendKeys(forgotPasswordPage.TextField_ForgotPass_PhoneNumber(), PropertyUtility.getDataProperties("customer_LessThan10.phonenumber"));
                    break;
                case "Valid_ToBeLocked":
                    action.sendKeys(forgotPasswordPage.TextField_ForgotPass_PhoneNumber(), PropertyUtility.getDataProperties("customer.ValidToBeLockedUser"));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log(" I enter" + string + " Phone Number",
                    "I entered " + string + " phone number", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("I enter \"([^\"]*)\" SMS code")
    public void i_enter_SMS_code(String string) {
        try {

            switch (string) {
                case "valid":
                    Thread.sleep(2000);
                    String SMSCode = dbutility.getVerificationCode(PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                    action.sendKeys(forgotPasswordPage.TextField_SMSCode(), SMSCode);
                    break;
                case "valid code for locked":
                    action.sendKeys(forgotPasswordPage.TextField_SMSCode(), dbutility.getVerificationCode(PropertyUtility.getDataProperties("customer.ValidToBeLockedUser")));
                    break;
                case "invalid":
                    action.sendKeys(forgotPasswordPage.TextField_SMSCode(), PropertyUtility.getDataProperties("verificationcode.incorrect"));
                    break;
                case "previous":
                    action.sendKeys(forgotPasswordPage.TextField_SMSCode(), PreviousSMSCode);
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log(" I enter" + string + " SMS code",
                    "I entered " + string + " SMS code", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("I enter customers new \"([^\"]*)\" Password")
    public void i_enter_customers_new_Password(String string) {
        try {

            String newPassword = "";
            switch (string) {
                case "valid":
                    newPassword = PropertyUtility.getDataProperties("customer_generic.password");
                    break;
                case "invalid":
                    newPassword = PropertyUtility.getDataProperties("customer_LessThan6.password");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            action.sendKeys(forgotPasswordPage.TextField_NewPassword(), newPassword);

            log(" I enter customers new " + string + "Password",
                    "I entered " + newPassword + "as customers new " + string + " Password", false  );

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("The user should be logged in")
    public void the_user_should_be_logged_in() {
        try {
            if(utility.isCorrectPage("Terms and Conditions")){
                action.click(Page_CustTerms.Checkbox_Agree());
                action.click(Page_CustTerms.Button_Continue());
                if (action.isElementPresent(Page_CustTerms.Header_PermissionsLocation(true))) {
                    // action.click(Page_CustTerms.Button_GoToSetting());
                    action.click(Page_CustTerms.Button_PermissionsSure());
                    action.click(Page_CustTerms.Button_PermissionsAllow());
                    // ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                }
            }
                Thread.sleep(5000);
                if (action.isElementPresent(homePage.Button_Closetutorials(true)))
                    action.click(homePage.Button_Closetutorials());
                //Add code to handle Done

            testStepAssert.isTrue(utility.isCorrectPage("Home"), "Home page should be displayed", "Home page is displayed", "Home page was not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The user should see \"([^\"]*)\" on forgot password page$")
    public void the_user_should_see_something_on_forgot_password_page(String strArg1) throws Throwable {
        try {
            String errorMessage = "";
            switch (strArg1) {
                case "snackbar validation message for success once I click continue":
                    //Combined click with snackbar message
                    action.click(forgotPasswordPage.Button_Continue());
                   // String message=action.getText(forgotPasswordPage.Snackbar_ForgotPassword(true));
                    //Boolean isMessageCorrectlyDisplayed=utility.isForgotPasswordMessageCorrect();
                    testStepVerify.isEquals(utility.getCustomerSnackBarMessage(),PropertyUtility.getMessage("customer.forgotpassword.success.android"),PropertyUtility.getMessage("customer.forgotpassword.success.android") +" , should be correctly displayed. ",PropertyUtility.getMessage("customer.forgotpassword.success.android")+" is displayed","Snackbar message was not displayed or was displayed for small amount of time to capture snackbar message text" );

/*                    String actualMessage = SetupManager.getDriver().findElement(By.id("com.bungii.customer:id/snackbar_text")).getText();
                    if (actualMessage == null) {
                        warning("Snackbar message for success should be displayed", "Snackbar message was not displayed or was displayed for small amount of time to capture snackbar message text");
                        break;
                    } else {
                     //   actualMessage = action.getText(snackBar);
                        testStepVerify.isEquals(actualMessage,PropertyUtility.getMessage("customer.forgotpassword.success.android"));
                        //testStepVerify.isElementTextEquals(forgotPasswordPage.Snackbar_ForgotPassword(true), PropertyUtility.getMessage("customer.forgotpassword.success.android"));
                    }*/
                    break;

                case "snackbar validation message for invalid sms code":
                    testStepVerify.isEquals(utility.getCustomerSnackBarMessage(), PropertyUtility.getMessage("customer.forgotpassword.invalid.code.android"));
                    break;
                case "Send button disabled":
                    testStepVerify.isElementNotEnabled(forgotPasswordPage.Button_ForgotPass_Send(true), "Send buttons should be disabled ", "Send button is disabled", "Send Button is not disabled");
                    break;
                case "validation for incorrect password":
                    errorMessage = PropertyUtility.getMessage("customer.forgotpassword.invalid.password.android");
                    testStepVerify.isEquals(action.getText(forgotPasswordPage.Err_InvalidPassword()), errorMessage);
                    break;
                case "validation for incorrect number":
                    errorMessage = PropertyUtility.getMessage("customer.forgotpassword.invalid.phone");
                    testStepVerify.isEquals(action.getText(forgotPasswordPage.Err_InvalidPassword()), errorMessage);
                    break;
                case "snackbar validation message for invalid number":
                    errorMessage = PropertyUtility.getMessage("common.failed.message");
                    testStepVerify.isEquals(utility.getCustomerSnackBarMessage(), errorMessage);
                    break;

                default:
                    throw new Exception("Unimplemented step");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Validation message not displayed",
                    true);
        }
    }

    @And("^I record the SMS Code$")
    public void i_record_the_sms_code() throws Throwable {
        Thread.sleep(2000);
        PreviousSMSCode = dbutility.getVerificationCode(PropertyUtility.getDataProperties("customer_generic.phonenumber"));
    }
}
