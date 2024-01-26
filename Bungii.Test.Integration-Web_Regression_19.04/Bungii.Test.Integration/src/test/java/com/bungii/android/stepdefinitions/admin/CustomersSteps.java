package com.bungii.android.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static com.bungii.common.manager.ResultManager.log;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.bungii.common.manager.ResultManager.error;

public class CustomersSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.android.stepdefinitions.admin.DashBoardSteps.class);

    ActionManager action = new ActionManager();
    DashBoardPage dashBoardPage=new DashBoardPage();
    CustomersPage customersPage=new CustomersPage();


    @And("^I Search for customer$")
    public void i_search_for_customer() throws Throwable {
        try {
            Thread.sleep(3000);
            action.sendKeys(customersPage.TextBox_SearchCustomer(), (String) cucumberContextManager.getScenarioContext("FIRST_NAME") + Keys.ENTER);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I Search for customer with phone number$")
    public void i_search_for_customer_with_phone_number() throws Throwable {
        try {
            Thread.sleep(3000);
            action.sendKeys(customersPage.TextBox_SearchCustomer(), (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE") + Keys.ENTER);
            log("Customer associated with phone number should get searched.","Customer associated with phone number got searched.",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I verify the trip count$")
    public void i_verify_the_trip_count() throws Throwable {
        try {
            String name= (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            //LocalDateTime now = LocalDateTime.now();
            String now="Today";
            int count=1;
            String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/preceding-sibling::td[contains(.,'%s')][1]",name,now,count);
            testStepAssert.isElementDisplayed(customersPage.findElement(Xpath, PageBase.LocatorType.XPath), Xpath + "Element should be displayed", Xpath + "Element is displayed", Xpath + "Element is not displayed");
            action.click(dashBoardPage.Button_Customers());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in verifying delivery count on admin portal",
                    true);
        }
    }
}
