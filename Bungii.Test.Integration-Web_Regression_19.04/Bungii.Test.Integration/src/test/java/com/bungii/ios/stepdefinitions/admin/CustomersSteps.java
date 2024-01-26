package com.bungii.ios.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.pages.admin.CustomersPage;
import com.bungii.web.manager.ActionManager;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class CustomersSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    ActionManager action = new ActionManager();
    CustomersPage customersPage = new CustomersPage();
    @Then("^trips requested count should be \"([^\"]*)\"$")
    public void trips_requested_count_should_be_something(String strArg1) throws Throwable {
        try {
            String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
           if(custName.equalsIgnoreCase(""))
               custName=cucumberContextManager.getScenarioContext("NEW_USER_FIRST_NAME")+" "+ cucumberContextManager.getScenarioContext("NEW_USER_LAST_NAME");

            action.sendKeys(customersPage.Text_SearchCriteria(),custName.substring(0,custName.indexOf(" ")));
            action.click(customersPage.Button_Search());Thread.sleep(5000);
            String tripRequested=action.getText(SetupManager.getDriver().findElement(By.xpath("//td[text()='"+custName+"']/following-sibling::td[2]")));
            testStepVerify.isTrue(tripRequested.equalsIgnoreCase(strArg1),"Trip Requested should be"+strArg1);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
