package com.bungii.web.stepdefinitions.driver;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.driver.*;
import com.bungii.web.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Driver_ForgotPasswordSteps extends DriverBase {


    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    Driver_ForgotPasswordPage Page_ForgotPassword = new Driver_ForgotPasswordPage();
    Driver_VerifyPhonePage Page_VerifyPhone = new Driver_VerifyPhonePage();
    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    Driver_DashboardPage Page_Driver_Dashboard = new Driver_DashboardPage();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Driver_ForgotPasswordSteps.class);


    @When("^I enter \"([^\"]*)\" Phone Number on Forgot password page$")
    public void i_enter_something_phone_number_on_forgot_password_page(String strArg1) throws Throwable {
        try{
            switch (strArg1) {
                case "valid":
                    action.clearSendKeys(Page_ForgotPassword.Textfield_ForgotPass_Phone(), PropertyUtility.getDataProperties("DriverPhoneNumber"));
                    cucumberContextManager.setScenarioContext("DriverPhone", PropertyUtility.getDataProperties("DriverPhoneNumber"));
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("DriverName"));
                    break;
                case "invalid":
                    action.clearSendKeys(Page_ForgotPassword.Textfield_ForgotPass_Phone(), PropertyUtility.getDataProperties("Invalid_DriverPhoneNumber"));
                    break;
                default:
                    break;
                }
            log("I should able to enter "+strArg1+" driver phone number on Forgot password page","I entered "+strArg1 +" driver phone number on Forgot password page", true);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I enter \"([^\"]*)\" code on Verify your phone page$")
    public void i_enter_something_code_on_verify_your_phone_page(String strArg1) throws Throwable {
        try{
            switch (strArg1)
            {
                case "valid":
                    String smscode = DbUtility.getVerificationCode(PropertyUtility.getDataProperties("DriverPhoneNumber"));
                    action.clearSendKeys(Page_VerifyPhone.Textfield_Code(), smscode);
                    break;
                case "invalid":
                    action.clearSendKeys(Page_VerifyPhone.Textfield_Code(), PropertyUtility.getDataProperties("InvalidValue"));
                    break;
                default: break;
            }
            log("I enter "+strArg1+" code on Verify your phone page","I entered "+strArg1 +" code on Verify your phone page", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I enter \"([^\"]*)\" new password on Verify your phone page$")
    public void i_enter_something_new_password_on_verify_your_phone_page(String strArg1) throws Throwable {
       try{ switch (strArg1)
            {
                case "valid":
                    action.clearSendKeys(Page_VerifyPhone.Textfield_Password(), PropertyUtility.getDataProperties("DriverPassword"));
                    break;
                case "short":
                    action.clearSendKeys(Page_VerifyPhone.Textfield_Password(), PropertyUtility.getDataProperties("Short_DriverPassword"));
                    break;
                case "invalid":
                    action.clearSendKeys(Page_VerifyPhone.Textfield_Password(), PropertyUtility.getDataProperties("Invalid_DriverPassword"));
                    break;
                default: break;
            }
            log("I enter "+strArg1+" new password on Verify your phone page","I entered "+strArg1 +" new password on Verify your phone page", false);
       } catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step should be successful", "Error performing step,Please check logs for more details", true);
       }
    }

    @And("^I enter \"([^\"]*)\" password in Confirm password field$")
    public void i_enter_something_password_in_confirm_password_field(String strArg1) throws Throwable {
       try{
           switch (strArg1)
           {
               case "correct":
                   action.clearSendKeys(Page_VerifyPhone.Textfield_ConfirmPassword(), PropertyUtility.getDataProperties("DriverPassword"));
                   break;
               default: break;
           }
            log("I should able to enter "+strArg1+" password in Confirm password field","I entered "+strArg1 +" password in Confirm password field", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^driver should see \"([^\"]*)\" during phone verification$")
    public void driver_should_see_something_during_phone_verification(String strArg1) throws Throwable {
        try{
        switch (strArg1)
        {
            case "validation for invalid phone":
                testStepVerify.isEquals(action.getText(Page_ForgotPassword.Err_ForgotPass_Phone()), PropertyUtility.getMessage("Err_DriverLogin_Phone"));
                break;
            case "correct phone number":
                String phone = PropertyUtility.getDataProperties("DriverPhoneNumber");
                String PhoneLast4 = PropertyUtility.getMessage("VerifyPhoneText1");
                testStepVerify.isEquals(action.getText(Page_VerifyPhone.Text_Verify_PhoneNo()), PhoneLast4);
                break;
            case "new verification code":
                String Code_Initial =(String) cucumberContextManager.getScenarioContext("Code_Initial");
                Thread.sleep(4000);
                String Code_New = DbUtility.getVerificationCode(PropertyUtility.getDataProperties("DriverPhoneNumber"));
                testStepAssert.isFalse(Code_Initial.equals(Code_New),"New Code should not be equal to old code ","New code is equal to old code");
                break;
            case "validation for invalid code":
                testStepVerify.isEquals(action.getText(Page_VerifyPhone.Err_VerifyPhone_Code_Incorrect()), PropertyUtility.getMessage("VerifCode_Err_Invalid"));
                break;
            case "validations for password fields":
                testStepVerify.isEquals(action.getText(Page_VerifyPhone.Err_VerifyPhone_Password_Invalid()), PropertyUtility.getMessage("DReg_Password_Short"));
                testStepVerify.isEquals(action.getText(Page_VerifyPhone.Err_VerifyPhone_ConfirmPassword()), PropertyUtility.getMessage("DReg_ConfirmPassword_Incorrect"));
                break;
            case "validation for invalid password":
                testStepVerify.isEquals(action.getText(Page_VerifyPhone.Err_VerifyPhone_Password_Invalid()), PropertyUtility.getMessage("DReg_Password_Invalid"));
                break;
            case "success message driver login page":
                testStepVerify.isEquals(action.getText(Page_Driver_Login.Text_PasswordResetSuccess()), PropertyUtility.getMessage("Msg_PasswordResetSuccess"));
                break;
            default: break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
}
