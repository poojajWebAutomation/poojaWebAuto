package com.bungii.web.stepdefinitions.driver;

import com.bungii.api.stepdefinitions.BungiiSteps;
import com.bungii.api.utilityFunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.driver.Driver_RegistrationPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Driver_LoginSteps extends DriverBase {

    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    Driver_DashboardPage Page_Driver_Dashboard = new Driver_DashboardPage();
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    DriverRegistrationSteps driverRegistrationSteps = new DriverRegistrationSteps();
    BungiiSteps bungiiSteps = new BungiiSteps();
    ActionManager action = new ActionManager();
    AuthServices authServices = new AuthServices();
    CoreServices coreServices = new CoreServices();
    private static LogUtility logger = new LogUtility(Driver_LoginSteps.class);

    @When("^I enter \"([^\"]*)\" driver Phone Number on Driver portal$")
    public void WhenIEnterDriverPhoneNumberOnDriverPortal(String p0)
    {
        try{
        switch (p0)
        {
            case "valid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), PropertyUtility.getDataProperties("DriverPhoneNumber"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), PropertyUtility.getDataProperties("Invalid_DriverPhoneNumber"));
                break;
            default: break;
        }
        log("I should able to enter "+p0+" driver phone number on Driver portal","I entered "+p0 +" driver phone number on Driver portal", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I enter \"([^\"]*)\" driver Password on Driver portal$")
    public void WhenIEnterDriverPasswordOnDriverPortal(String p0)
    {
        try{
        switch (p0)
        {
            case "valid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("DriverPassword"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("Invalid_DriverPassword"));
                break;
            default: break;
        }
        log("I should able to enter "+p0+" driver Password on Driver portal","I entered "+p0 +" driver Password on Driver portal", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the driver should \"([^\"]*)\"$")
    public void ThenTheDriverShould(String p0)
    {
    try{
        switch (p0) {
           case "be logged in":
               try {
                    testStepAssert.isElementDisplayed(Page_Driver_Dashboard.SideNavigationSetting(), "Driver should log in to driver portal", "Driver is logged in to driver portal", "Driver is not logged in to driver portal due to error");
                    testStepAssert.isElementDisplayed(Page_Driver_Dashboard.SideNavigationGeneral(), "Driver should log in to driver portal", "Driver is logged in to driver portal", "Driver is not logged in to driver portal due to error");
                    }
                    catch(Exception ex) {
                        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
                        error("Driver should log in to driver portal", "Driver is not logged in due to error [Probable root cause : encryption decryption in local environment]", true);
                    }
                    break;
           case "see validation message for blank fields":
                    testStepVerify.isEquals(action.getText(Page_Driver_Login.Err_DriverLogin_Blank()), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                    break;
           case "see validation message for invalid phone field":
                    testStepVerify.isEquals(action.getText(Page_Driver_Login.Err_DriverLogin_Phone()), PropertyUtility.getMessage("Err_DriverLogin_Phone"));
                    break;
           case "see validation message for incorrect credentials":
                    testStepVerify.isEquals(action.getText(Page_Driver_Login.Err_DriverLogin_FieldValidation()), PropertyUtility.getMessage("Err_DriverLogin_IncorrectCredentials"));
                    break;
                    default:
                    break;
           }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I login to the driver portal as driver \"([^\"]*)\"$")
    public void i_login_to_the_driver_portal_as_driver_something(String strArg1) throws Throwable {
        String phone = bungiiSteps.getDriverPhone(strArg1);
        driverRegistrationSteps.i_navigate_to_something("Bungii Driver URL");
        driverRegistrationSteps.i_click_something_on_driver_portal("LOG IN link");
        driverRegistrationSteps.i_enter_driver_phone_number_as_something_and_valid_password(phone);
        driverRegistrationSteps.i_click_something_on_driver_portal("LOG IN button");
        try {
            testStepAssert.isElementDisplayed(Page_Driver_Dashboard.SideNavigationSetting(), "Driver should log in to driver portal", "Driver is logged in to driver portal", "Driver is not logged in to driver portal due to error");
            testStepAssert.isElementDisplayed(Page_Driver_Dashboard.SideNavigationGeneral(), "Driver should log in to driver portal", "Driver is logged in to driver portal", "Driver is not logged in to driver portal due to error");
        }
        catch(Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Driver should log in to driver portal", "Driver is not signed in due to error.", true);
        }
    }

    @Given("^I Login as a driver with below phone numbers and Make them online$")
    public void i_login_as_a_driver_with_below_phone_numbers_and_make_them_online(DataTable data) throws Throwable {
       try{
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String phoneNumber = dataMap.get("PhoneNumber").trim();
            String driverAccessToken = authServices.getDriverToken("1", phoneNumber, "cci12345");
            coreServices.updateDriverStatus(driverAccessToken);
            log("I Login as a driver with phone number "+phoneNumber+" and Make him online","I haved Logged in as a driver with phone number "+phoneNumber+" and Make him online", false);
       } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I click on the open \"([^\"]*)\" link on the driver login page$")
    public void i_click_on_the_open_something_link_on_the_driver_login_page(String strArg1) throws Throwable {
        try {
            action.waitUntilIsElementExistsAndDisplayed(Page_Driver_Login.Link_Login_OpenEye(), (long) 3000);
            action.click(Page_Driver_Login.Link_Login_OpenEye());
            log("I should be able to click on the closed eye link","I could click on the closed eye link",false);
            } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^The password for driver login should be masked$")
    public void the_password_for_driver_login_should_be_masked() throws Throwable {
        try {
            String passwordMasked = "password";
            action.waitUntilIsElementExistsAndDisplayed(Page_Driver_Login.TextBox_DriverLogin_Password(), (long) 5000);
            String expectedPasswordMasked = Page_Driver_Login.TextBox_DriverLogin_Password().getAttribute("type");
            testStepAssert.isTrue(expectedPasswordMasked.contentEquals(passwordMasked), "Password should be masked", "Password is masked", "Password is not masked");
        }catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see the password in the form of text$")
    public void i_should_see_the_password_in_the_form_of_text() throws Throwable {
        try {
            String passwordUnmasked = "text";
            action.waitUntilIsElementExistsAndDisplayed(Page_Driver_Login.TextBox_DriverLogin_Password(), (long) 3000);
            String expectedPasswordUnMasked = Page_Driver_Login.TextBox_DriverLogin_Password().getAttribute("type");
            testStepAssert.isTrue(expectedPasswordUnMasked.contentEquals(passwordUnmasked), "Password should not be masked", "Password is not masked", "Password is masked");
        }catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}