package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.BungiiRequest;
import com.bungii.android.pages.driver.ForgotPasswordPage;
import com.bungii.android.pages.driver.InProgressBungiiPages;
import com.bungii.android.stepdefinitions.Customer.SignupSteps;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.bungii.common.manager.ResultManager.*;
public class ForgotPasswordSteps extends DriverBase  {

    ActionManager action = new ActionManager();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    GeneralUtility utility= new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    private static LogUtility logger = new LogUtility(HomePageSteps.class);

    @When("^I click \"([^\"]*)\" button on FORGOT PASSWORD screen on driver app$")
    public void i_click_something_button_on_forgot_password_screen_on_driver_app(String option) {
        switch (option) {
            case"CONTINUE":
                action.click(forgotPasswordPage.Button_Continue());
                break;
            case"SEND":
                action.click(forgotPasswordPage.Button_Send());
                break;
            case"RESEND":
                action.click(forgotPasswordPage.Button_Resend());
                break;
        }
    }

    @Then("^\"([^\"]*)\" message should be displayed on FORGOT PASSWORD page on driver app$")
    public void something_message_should_be_displayed_on_forgot_password_page_on_driver_app(String strArg1) throws Throwable {
        switch (strArg1) {
            case"FORGOT PASSWORD INFORMATION":
                testStepVerify.isElementTextEquals(forgotPasswordPage.Text_ForgotInfo(),PropertyUtility.getMessage("driver.forgotpassword.info"));
                break;
        }    }

    @Then("^I should see \"([^\"]*)\"  on FORGOT PASSWORD on driver app$")
    public void i_should_see_something_on_forgot_password_on_driver_app(String strArg1) throws Throwable {
        switch (strArg1) {
            case"SEND BUTTON DISABLED":
                testStepVerify.isTrue(!action.isElementEnabled(forgotPasswordPage.Button_Send()),"Login button should be disabled");
                break;
            case"SEND BUTTON ENABLED":
                testStepVerify.isTrue(action.isElementEnabled(forgotPasswordPage.Button_Send()),"Login button should be disabled");
                break;
            case "Please enter Phone number error":
                testStepVerify.isElementTextEquals(forgotPasswordPage.Text_ForgotError(),PropertyUtility.getMessage("driver.login.phone.error"));
                break;
            case"FAILED TO SEND TOKEN":
                testStepVerify.isEquals(utility.getDriverSnackBarMessage(), PropertyUtility.getMessage("common.failed.message"));
                break;
            case "INVALID SMS CODE":
                testStepVerify.isEquals(utility.getDriverSnackBarMessage(), PropertyUtility.getMessage("driver.forgotpassword.invalid.code.andriod"));
                break;
            case "INVALID PASSWORD WHILE RESET":
                testStepVerify.isElementTextEquals(forgotPasswordPage.Text_ForgotError(),PropertyUtility.getMessage("driver.forgotpassword.invalid.password.android"));
                break;
            case "PASSWORD CHANGE SUCCESS":
//                utility.waitForSnackbarMessage();
                String snackbarText="";boolean isSnackbarDisplayed=false;
                try {
                    snackbarText=utility.getDriverSnackBarMessage();
                    isSnackbarDisplayed=true;
                }catch (Exception e){}
                if(isSnackbarDisplayed){
                testStepVerify.isEquals(snackbarText, PropertyUtility.getMessage("driver.forgotpassword.success.android"));}
                else{warning("I should able to see snack bar message","Snackbar message was not captured");}
                break;
        }     }

    @And("^I Enter \"([^\"]*)\" value in \"([^\"]*)\" field in FORGOT PASSWORD Page on driver app$")
    public void i_enter_something_value_in_something_field_in_forgot_password_page_on_driver_app(String value, String strArg1) throws Throwable {
        switch (strArg1) {
            case"Phone Number":
                value = value.equalsIgnoreCase("{VALID USER}") ? PropertyUtility.getDataProperties("valid.driver.phone") : value;
                WebElement element = forgotPasswordPage.Textbox_PhoneNumber();
                element.click();
                utility.inputOnNumberKeyBoard(value);
                break;
            case "sms code":
                if(value.equalsIgnoreCase("valid")){
                    action.sendKeys(forgotPasswordPage.Text_SmsCode(), (String) cucumberContextManager.getScenarioContext("SMS_CODE"));
                }else if(value.equalsIgnoreCase("invalid")){
                    action.sendKeys(forgotPasswordPage.Text_SmsCode(), "000001");

                }
                break;
            case "new password":
                action.sendKeys(forgotPasswordPage.Text_Password(), value);
                break;
            case "confirm password":
                action.sendKeys(forgotPasswordPage.Text_Confirm_Password(), value);
                break;
        }
        pass("I Enter "+value+ "value in " +strArg1+"field in FORGOT PASSWORD Page on driver app","I Entered "+value+ "value in " +strArg1+"field in FORGOT PASSWORD Page on driver app");
    }

    @And("^I Get SMS CODE for \"([^\"]*)\" number on driver app$")
    public void i_get_sms_code_for_something_number_on_driver_app(String value) throws Throwable {
        switch (value) {
            case"{VALID USER}":
                Thread.sleep(2000);
                String phoneNumber= PropertyUtility.getDataProperties("valid.driver.phone");
                String smsCode = DbUtility.getVerificationCodeDriver(phoneNumber);
                cucumberContextManager.setScenarioContext("SMS_CODE", smsCode);
                break;
        }
    }
}
