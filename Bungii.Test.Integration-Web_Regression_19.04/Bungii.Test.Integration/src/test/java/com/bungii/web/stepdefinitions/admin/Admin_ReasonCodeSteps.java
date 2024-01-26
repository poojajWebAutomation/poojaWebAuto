package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_EditScheduledBungiiPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.bungii.web.pages.admin.Admin_TripDetailsPage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_ReasonCodeSteps extends DriverBase {

    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_TripsSteps.class);
    Admin_EditScheduledBungiiPage admin_EditScheduledBungiiPage = new Admin_EditScheduledBungiiPage();

    Admin_TripDetailsPage Page_Admin_TripDetails = new Admin_TripDetailsPage();

    @And("^I click on \"([^\"]*)\" in the dropdown$")
    public void i_click_on_something_in_the_dropdown(String dropdown) throws Throwable {
        try{
        switch (dropdown) {
            case "Customer initiated":
                    Select selectCustomer = new Select((WebElement) admin_EditScheduledBungiiPage.Dropdown_Result());
                    selectCustomer.selectByVisibleText("Customer initiated");
                    break;
            case "Partner initiated":
                    Select selectPartner = new Select((WebElement) admin_EditScheduledBungiiPage.Dropdown_Result());
                    selectPartner.selectByVisibleText("Partner initiated");
                    break;
            case "Delivery Details":
                    action.click(admin_EditScheduledBungiiPage.Button_Delivery_Details());
                    break;

            default: break;
        }
            log("I view "+dropdown+" in the dropdown",
                    "I could see "+dropdown+" in the dropdown", false);
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @And("^I click on the \"([^\"]*)\" and select future time$")
    public void i_click_on_the_something_and_select_future_time(String scheduleDate) throws Throwable {
        try{
        switch (scheduleDate) {
            case "Time":
                    String time = (String) cucumberContextManager.getScenarioContext("SCHEDULED_BUNGII_TIME");
                    String time2 = (String) cucumberContextManager.getScenarioContext("Scheduled_Time");
                    if (time.equalsIgnoreCase(""))
                    {
                        time = time2;
                    }
                    time=time.substring(8,16);
                    action.click(admin_EditScheduledBungiiPage.TimePicker_Time());
                    Thread.sleep(3000);
                    action.click(admin_EditScheduledBungiiPage.Dropdown_ScheduledDate_Time(time));
                    String timeChanged = action.getText(admin_EditScheduledBungiiPage.Dropdown_ScheduledDate_Time(time));
                    cucumberContextManager.setScenarioContext("Time_Changed", timeChanged);
                    break;
            case "Date":
                    action.click(admin_EditScheduledBungiiPage.DatePicker_ScheduledDate());
                    Thread.sleep(1000);
                    action.click(admin_EditScheduledBungiiPage.Changed_Date());
                    String dateChanged = action.getAttributeValue(admin_EditScheduledBungiiPage.DatePicker_ScheduledDate());
                    Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(dateChanged);
                    String date= String.valueOf(date1);
                    date =date.substring(4,10)+", "+date.substring(24);
                    cucumberContextManager.setScenarioContext("Date_Changed", date);
                    break;

            default: break;
        }
            log("I can select future time/date",
                    "I was able to change time/date to future time/date", false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }

    }

    @And("^I click on \"([^\"]*)\" for change time$")
    public void i_click_on_something_for_change_time(String strArg1) throws Throwable {

        try {
            action.click(admin_EditScheduledBungiiPage.Dropdown_Result());

            log("I can click on reason dropdown",
                    "I clicked on reason dropdown", false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("^I check if the \"([^\"]*)\" field is hidden$")
    public void i_check_if_the_something_field_is_hidden(String strArg1) throws Throwable {
        try {
            testStepAssert.isFalse(action.isElementPresent(admin_EditScheduledBungiiPage.Dropdown_Result(true)), "Reason dropdown should not be displayed", "Reason dropdown is displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see \"([^\"]*)\" section$")
    public void iShouldSeeSection(String string) throws Throwable{
        try {
            testStepAssert.isElementDisplayed(Page_Admin_TripDetails.Text_TitleTransactionHistory(),
                    string + " should be displayed on page", string + " is displayed on page",
                    string + " is not displayed on page");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step. Please check logs for more details", true);
        }
    }

    @And("I should see following details in the Transaction history section")
    public void iShouldSeeFollowingDetailsInTheTransactionHistorySection(DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String deliveryTotal = dataMap.get("Delivery Total").trim();
            String customerRefund = dataMap.get("Customer Refund").trim();
            String driverEarnings = dataMap.get("Driver Testdrivertywd_appleky_a_eapi Driver One Earnings").trim();
            String bungiiEarnings = dataMap.get("Bungii Earnings").trim();

            String deliveryTotalAmount = action.getText(Page_Admin_TripDetails.Text_TransactionHistoryDeliveryTotal());
            String customerRefundAmount = action.getText(Page_Admin_TripDetails.Text_TransactionHistoryCustomerRefundAmount());
            String driverEarningsAmount = action.getText(Page_Admin_TripDetails.Text_TransactionHistoryDriverEarnings());
            String bungiiEarningsAmount = action.getText(Page_Admin_TripDetails.Text_TransactionHistoryBungiiEarnings());

            testStepAssert.isEquals(deliveryTotalAmount,deliveryTotal,"Delivery total should match Actual Delivery total amount",
                    "Delivery total matches Actual Delivery total amount",
                    "Delivery total does not match Actual Delivery total amount");
            testStepAssert.isEquals(customerRefundAmount,customerRefund,"Customer refund should match Actual Customer refund amount",
                    "Customer refund matches Actual Customer refund amount",
                    "Customer refund does not match Actual Customer refund amount");
            testStepAssert.isEquals(driverEarningsAmount,driverEarnings,"Driver earnings should match Actual Driver earnings amount",
                    "Driver earnings matches Actual Driver earnings amount",
                    "Driver earnings does not match Actual Driver earnings amount");
            testStepAssert.isEquals(bungiiEarningsAmount,bungiiEarnings,"Bungii earnings should match Actual Bungii earnings amount",
                    "Bungii earnings matches Actual Bungii earnings amount",
                    "Bungii earnings does not match Actual Bungii earnings amount");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", " Error performing step. Please check logs for more details", true);
        }
    }
    @And("^I click on \"([^\"]*)\" for change time and check reason dropdown values$")
    public void i_click_on_something_for_change_time_and_check_reason_dropdown_values(String strArg1) throws Throwable {
        try {
            List<String> expectedOptions = new ArrayList() {{
                add("Select Reason");
                add("Partner initiated");
                add("Customer initiated");
                add("No drivers available");
            }};
            action.click(admin_EditScheduledBungiiPage.Dropdown_Result());
            Select select = new Select(admin_EditScheduledBungiiPage.Dropdown_Result());
            List<String> Options = new ArrayList();
            List<WebElement> actualOptions = select.getOptions();
            int size = actualOptions.size();
            for(int i =0; i<size ; i++){
                String options = actualOptions.get(i).getText();
                Options.add(options);
            }
            testStepAssert.isTrue(Options.containsAll(expectedOptions),"Correct reasons need to be displayed","Correct reasons are displayed","Incorrect reasons are displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I check if the \"([^\"]*)\" field is present$")
    public void i_check_if_the_something_field_is_present(String strArg1) throws Throwable {
        try {
            testStepAssert.isFalse(action.isElementPresent(admin_EditScheduledBungiiPage.Dropdown_Result(true)),"Reasons should be displayed","Reasons is displayed", "Reasons is not displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on \"([^\"]*)\" and add \"([^\"]*)\" driver$")
    public void i_click_on_something_and_add_something_driver(String strArg1, String driverName) throws Throwable {
        try{
        action.click(admin_EditScheduledBungiiPage.TextBox_DriverSearch());
        action.sendKeys(admin_EditScheduledBungiiPage.TextBox_DriverSearch(),driverName);
        action.waitForElement("//div[contains(.,'"+driverName+"')]");
//       action.sendKeys(admin_EditScheduledBungiiPage.TextBox_DriverSearch()," ");
//        action.clickOnDropdown();
        action.click(admin_EditScheduledBungiiPage.Dropdown_Driver_Result(driverName));
        cucumberContextManager.setScenarioContext("Driver_Name",driverName);
        Thread.sleep(1000);

            log("I can add a driver on edit delivery page",
                    "I added a driver on edit delivery page", false);
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I click \"([^\"]*)\" on scheduled delivery details page$")
    public void i_click_something_on_scheduled_delivery_details_page(String strArg1) throws Throwable {
        try {
            action.click(admin_EditScheduledBungiiPage.Changed_Time());
            Thread.sleep(5000);

            log("I can click on Change Time tab",
                    "I clicked on change time tab", false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^the updated time should be displayed on delivery details page$")
    public void the_updated_time_should_be_displayed_on_delivery_details_page() throws Throwable {
        try {
            String expectedTime = (String) cucumberContextManager.getScenarioContext("Time_Changed");
            action.refreshPage();
            String actualTime = action.getText(admin_EditScheduledBungiiPage.Changed_Time());
            testStepAssert.isTrue(actualTime.contains(expectedTime), "Correct time need to be display", "Correct time is display", "Incorrect time is displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^the updated date should be displayed on delivery details page$")
    public void the_updated_date_should_be_displayed_on_delivery_details_page() throws Throwable {
        try {
            String expectedDate = (String) cucumberContextManager.getScenarioContext("Date_Changed");
            action.refreshPage();
            String actualDate = action.getText(admin_EditScheduledBungiiPage.Changed_Time());
            testStepAssert.isTrue(actualDate.contains(expectedDate), "Correct date need to be display", "Correct date is display", "Incorrect date is displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

}
