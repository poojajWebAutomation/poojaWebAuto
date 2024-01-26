package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.LoginPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class LogInSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LogInSteps.class);
    LoginPage loginPage;
    DbUtility utility = new DbUtility();
    ActionManager action = new ActionManager();
    public LogInSteps(LoginPage loginPage){
        this.loginPage=loginPage;
    }

    @And("^I enter Username :(.+) and  Password :(.+)$")
    public void i_enter_valid_and_as_per_below_table(String username, String password) {
        String strUserName = "";
        String strPassWord ="";
        try {
             strUserName = username.equals("<BLANK>") ? "" : username.trim().equals("{VALID1}")? PropertyUtility.getDataProperties("customer.NonBungiiFlow.UserA"):username;
             strPassWord = password.equals("<BLANK>") ? "" : password.equals("{VALID}")? PropertyUtility.getDataProperties("customer.password"):password;
            strUserName =username.equalsIgnoreCase("{with no card}")? PropertyUtility.getDataProperties("no.payment.card.customer.user"):strUserName;
            strPassWord = password.equals("{with no card}") ?  PropertyUtility.getDataProperties("no.payment.card.customer.password"):strPassWord;

            action.clearEnterText(loginPage.Textfield_PhoneNumber(),strUserName);
            action.clearEnterText(loginPage.Textfield_Password(),strPassWord);
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE_EXTRA", strUserName);
            cucumberContextManager.setScenarioContext("LATEST_LOGGEDIN_CUSTOMER_PHONE",strUserName);

            cucumberContextManager.setScenarioContext("LATEST_LOGGEDIN_CUSTOMER_NAME",utility.getCustomerName(strUserName));

            pass( "Username and Password should be entered sucessfully",
            "Entered Customer Credentials ["+ strUserName+" / "+strPassWord+"] successfully");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step Should be successful", "Error in login to customer app as ["+ strUserName+" /"+strPassWord+"]", true);
        }
    }
}
