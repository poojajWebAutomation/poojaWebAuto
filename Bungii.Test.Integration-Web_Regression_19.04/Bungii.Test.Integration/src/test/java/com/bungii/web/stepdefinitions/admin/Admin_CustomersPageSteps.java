package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_CustomersPageSteps extends DriverBase {
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_BusinessUsersSteps.class);
    Admin_CustomerPage admin_customerPage = new Admin_CustomerPage();
    Admin_DriversPage admin_driversPage = new Admin_DriversPage();
    Admin_TripsPage admin_tripsPage = new Admin_TripsPage();
    Admin_DashboardPage admin_dashboardPage = new Admin_DashboardPage();
    Admin_LiveTripsPage admin_liveTripsPage = new Admin_LiveTripsPage();
    Admin_ScheduledTripsPage admin_scheduledTripsPage = new Admin_ScheduledTripsPage();
    GeneralUtility utility = new GeneralUtility();

    @And("^I enter \"([^\"]*)\" in the \"([^\"]*)\" box$")
    public void i_enter_something_in_the_something_box(String script, String strArg2) throws Throwable {
        try{
        action.clearSendKeys(admin_dashboardPage.TextBox_SearchCustomer(),script + Keys.ENTER);
            log("I enter "+script+" in the "+strArg2+" box" ,
                    "I have entered "+script+" in the "+strArg2+" box", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @When("^I enter \"([^\"]*)\" \"([^\"]*)\" in the \"([^\"]*)\" box as \"([^\"]*)\"$")
    public void i_enter_something_something_in_the_something_box_as(String strArg1, String strArg2, String strArg3, String name) throws Throwable {
        try{
        String driverFirstName = PropertyUtility.getDataProperties("web.driver.firstname");
        cucumberContextManager.setScenarioContext("DRIVERFIRSTNAME", driverFirstName);
        String driverLastName = PropertyUtility.getDataProperties("web.driver.lastname");
        cucumberContextManager.setScenarioContext("DRIVERLASTNAME", driverLastName);

        switch (strArg3) {
            case "Customers search":
                switch (strArg2) {
                    case "first name":
                        cucumberContextManager.setScenarioContext("CUSTFIRSTNAME", name);
                        action.clearSendKeys(admin_customerPage.TextBox_SearchCustomer(), name + Keys.ENTER);
                        Thread.sleep(2000);
                        break;

                    case "last name":
                        cucumberContextManager.setScenarioContext("CUSTLASTNAME", name);
                        action.clearSendKeys(admin_customerPage.TextBox_SearchCustomer(), name + Keys.ENTER);
                        Thread.sleep(2000);
                        break;
                }
                break;

            case "Drivers search":

                switch (strArg2) {
                    case "first name":
                        action.clearSendKeys(admin_driversPage.Textbox_SearchCriteria(), driverFirstName + Keys.ENTER);
                        Thread.sleep(2000);
                        break;

                    case "last name":
                        action.clearSendKeys(admin_driversPage.Textbox_SearchCriteria(), driverLastName + Keys.ENTER);
                        Thread.sleep(2000);
                        break;
                }
                break;

            case "Deliveries search":
//            case "Trips search":
                //Select geoFenceDropdown = new Select(admin_tripsPage.Dropdown_Geofence());
                // geoFenceDropdown.selectByVisibleText("-- All --");
                utility.resetGeofenceDropdown();

                Select dropdown = new Select(admin_tripsPage.DropDown_SearchForPeriod());
                dropdown.selectByVisibleText("The Beginning of Time");
                switch (strArg2) {
                    case "first name":
                        if (strArg1.equalsIgnoreCase("customers")) {
                            cucumberContextManager.setScenarioContext("CUSTFIRSTNAME", name);
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), name + Keys.ENTER);
                        } else {
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), driverFirstName + Keys.ENTER);
                        }
                        Thread.sleep(2000);
                        break;

                    case "last name":
                        if (strArg1.equalsIgnoreCase("customers")) {
                            cucumberContextManager.setScenarioContext("CUSTLASTNAME", name);
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), name + Keys.ENTER);
                        } else {
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), driverLastName + Keys.ENTER);
                        }
                        Thread.sleep(2000);
                        break;
                }
                break;
            case "Dashboard search":
                switch (strArg2){
                    case "first name":
                        if(strArg1.equalsIgnoreCase("customers")){
                            cucumberContextManager.setScenarioContext("CUSTFIRSTNAME", name);
                            action.clearSendKeys(admin_dashboardPage.TextBox_SearchCustomer(),name + Keys.ENTER);
                        }else {
                            action.clearSendKeys(admin_dashboardPage.Textbox_DriverSearch(), driverFirstName);
                            action.click(admin_dashboardPage.Icon_Search());
                        }
                        Thread.sleep(2000);
                        break;
                    case "last name":
                        if(strArg1.equalsIgnoreCase("customers")){
                            cucumberContextManager.setScenarioContext("CUSTLASTNAME", name);
                            action.clearSendKeys(admin_customerPage.TextBox_SearchCustomer(), name + Keys.ENTER);
                        }else {
                            action.clearSendKeys(admin_dashboardPage.Textbox_DriverSearch(), driverLastName);
                            action.click(admin_dashboardPage.Icon_Search());
                        }
                        Thread.sleep(1000);
                        break;
                }
                break;
        }
        log("I enter "+strArg1+" "+strArg2+" in the "+strArg3+" box as "+ name ,
                "I have entered "+strArg1+" "+strArg2+" in the "+strArg3+" box as "+ name);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I enter \"([^\"]*)\" \"([^\"]*)\" in the \"([^\"]*)\" box$")
    public void i_enter_something_something_in_the_something_box(String strArg1, String strArg2, String strArg3) throws Throwable {
        try{
        String customerFirstName = PropertyUtility.getDataProperties("change.customer.firstname");
        cucumberContextManager.setScenarioContext("CUSTFIRSTNAME", customerFirstName);
        String customerLastName = PropertyUtility.getDataProperties("change.customer.lastname");
        cucumberContextManager.setScenarioContext("CUSTLASTNAME", customerLastName);

        String driverFirstName = PropertyUtility.getDataProperties("web.driver.firstname");
        cucumberContextManager.setScenarioContext("DRIVERFIRSTNAME", driverFirstName);
        String driverLastName = PropertyUtility.getDataProperties("web.driver.lastname");
        cucumberContextManager.setScenarioContext("DRIVERLASTNAME", driverLastName);

        switch (strArg3) {
            case "Customers search":
                switch (strArg2) {
                    case "first name":
                        action.clearSendKeys(admin_customerPage.TextBox_SearchCustomer(), customerFirstName + Keys.ENTER);
                        Thread.sleep(2000);
                        break;

                    case "last name":
                        action.clearSendKeys(admin_customerPage.TextBox_SearchCustomer(), customerLastName + Keys.ENTER);
                        Thread.sleep(2000);
                        break;
                }
                break;

            case "Drivers search":

                switch (strArg2) {
                    case "first name":
                        action.clearSendKeys(admin_driversPage.Textbox_SearchCriteria(), driverFirstName + Keys.ENTER);
                        Thread.sleep(2000);
                        break;

                    case "last name":
                        action.clearSendKeys(admin_driversPage.Textbox_SearchCriteria(), driverLastName + Keys.ENTER);
                        Thread.sleep(2000);
                        break;
                }
                break;

            case "Deliveries search":
//            case "Trips search":
               //Select geoFenceDropdown = new Select(admin_tripsPage.Dropdown_Geofence());
               // geoFenceDropdown.selectByVisibleText("-- All --");
                utility.resetGeofenceDropdown();

                Select dropdown = new Select(admin_tripsPage.DropDown_SearchForPeriod());
                dropdown.selectByVisibleText("The Beginning of Time");
                switch (strArg2) {
                    case "first name":
                        if (strArg1.equalsIgnoreCase("customers")) {
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), customerFirstName + Keys.ENTER);
                        } else {
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), driverFirstName + Keys.ENTER);
                        }
                        Thread.sleep(2000);
                        break;

                    case "last name":
                        if (strArg1.equalsIgnoreCase("customers")) {
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), customerLastName + Keys.ENTER);
                        } else {
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), driverLastName + Keys.ENTER);
                        }
                        Thread.sleep(2000);
                        break;
                }
                break;
            case "Dashboard search":
                switch (strArg2){
                    case "first name":
                        if(strArg1.equalsIgnoreCase("customers")){
                            action.clearSendKeys(admin_dashboardPage.TextBox_SearchCustomer(),customerFirstName + Keys.ENTER);
                        }else {
                            action.clearSendKeys(admin_dashboardPage.Textbox_DriverSearch(), driverFirstName);
                            action.click(admin_dashboardPage.Icon_DriverSearch());
                        }
                        Thread.sleep(2000);
                        break;
                    case "last name":
                        if(strArg1.equalsIgnoreCase("customers")){
                            action.clearSendKeys(admin_customerPage.TextBox_SearchCustomer(), customerLastName + Keys.ENTER);
                        }else {
                            action.clearSendKeys(admin_dashboardPage.Textbox_DriverSearch(), driverLastName);
                            action.click(admin_dashboardPage.Icon_DriverSearch());
                        }
                        Thread.sleep(1000);
                        break;
                }
                break;
                }
        log("I enter "+strArg1+" "+strArg2+" in the "+strArg3+" box" ,
                "I have entered "+strArg1+" "+strArg2+" in the "+strArg3+" box");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^I should see \"([^\"]*)\" listed on the \"([^\"]*)\" page$")
    public void i_should_see_something_listed_on_the_something_page(String strArg1, String page) throws Throwable {
        try{
        String Xpath =null;
        if (page.equalsIgnoreCase("Customers")) {
            switch (strArg1) {
                case "customer first name":
                    String custFirstName = (String) cucumberContextManager.getScenarioContext("CUSTFIRSTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", custFirstName);
                    cucumberContextManager.setScenarioContext("XPATH",Xpath);
                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Customer's first name should be listed in grid.",
                            "Customer's first name is listed in grid.",
                            "Customer's first name is not listed in grid.");
                    break;

                case "customer last name":
                    String custLastName = (String) cucumberContextManager.getScenarioContext("CUSTLASTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", custLastName);
                    cucumberContextManager.setScenarioContext("XPATH",Xpath);
                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Customer's last name should be listed in grid.",
                            "Customer's last name is listed in grid.",
                            "Customer's last name is not listed in grid.");
                    break;
            }
        }
        else if (page.equalsIgnoreCase("Drivers"))
        {
            switch (strArg1) {
                case "driver first name":
                    String driverFirstName = (String) cucumberContextManager.getScenarioContext("DRIVERFIRSTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", driverFirstName);

                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Driver's first name should be listed in grid.",
                            "Driver's first name is listed in grid.",
                            "Driver's first name is not listed in grid.");
                    break;

                case "driver last name":
                    String driverLastName = (String) cucumberContextManager.getScenarioContext("DRIVERLASTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", driverLastName);

                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Driver's last name should be listed in grid.",
                            "Driver's last name is listed in grid.",
                            "Driver's last name is not listed in grid.");
                    break;
            }
        }
        else if (page.equalsIgnoreCase("Deliveries"))
//            else if (page.equalsIgnoreCase("Trips"))
        {
            switch (strArg1) {
                case "customer first name":
                    String custFirstName = (String) cucumberContextManager.getScenarioContext("CUSTFIRSTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", custFirstName);

                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Customer's first name should be listed in grid.",
                            "Customer's first name is listed in grid.",
                            "Customer's first name is not listed in grid.");
                    break;

                case "customer last name":
                    String custLastName = (String) cucumberContextManager.getScenarioContext("CUSTLASTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", custLastName);

                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Customer's last name should be listed in grid.",
                            "Customer's last name is listed in grid.",
                            "Customer's last name is not listed in grid.");
                    break;

                case "driver first name":
                    String driverFirstName = (String) cucumberContextManager.getScenarioContext("DRIVERFIRSTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", driverFirstName);

                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Driver's first name should be listed in grid.",
                            "Driver's first name is listed in grid.",
                            "Driver's first name is not listed in grid.");
                    break;

                case "driver last name":
                    String driverLastName = (String) cucumberContextManager.getScenarioContext("DRIVERLASTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", driverLastName);

                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Driver's last name should be listed in grid.",
                            "Driver's last name is listed in grid.",
                            "Driver's last name is not listed in grid.");
                    break;
            }
        }
        else if (page.equalsIgnoreCase("Dashboard"))
        {
            switch (strArg1) {
                case "customer first name":
                    String custFirstName = (String) cucumberContextManager.getScenarioContext("CUSTFIRSTNAME");
                    Xpath = String.format("//tr/td[contains(.,'%s')]", custFirstName);

                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Customer's first name should be listed in grid.",
                            "Customer's first name is listed in grid.",
                            "Customer's first name is not listed in grid.");
                    break;

                case "customer last name":
                    String custLastName = (String) cucumberContextManager.getScenarioContext("CUSTLASTNAME");
                    Xpath = String.format("//tr/td[contains(.,'%s')]", custLastName);

                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Customer's last name should be listed in grid.",
                            "Customer's last name is listed in grid.",
                            "Customer's last name is not listed in grid.");
                    break;

                case "driver first name":
                    String driverFirstName = (String) cucumberContextManager.getScenarioContext("DRIVERFIRSTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", driverFirstName);

                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Driver's first name should be listed in grid.",
                            "Driver's first name is listed in grid.",
                            "Driver's first name is not listed in grid.");
                    break;

                case "driver last name":
                    String driverLastName = (String) cucumberContextManager.getScenarioContext("DRIVERLASTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", driverLastName);

                    testStepAssert.isElementDisplayed(admin_driversPage.findElement(Xpath,PageBase.LocatorType.XPath),
                            "Driver's last name should be listed in grid.",
                            "Driver's last name is listed in grid.",
                            "Driver's last name is not listed in grid.");
                    break;
            }
        }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I edit \"([^\"]*)\" to \"([^\"]*)\" and save it$")
    public void i_edit_something_to_something_and_save_it(String field, String value) throws Throwable {
try{
        switch (field) {
            case "Phone":
                action.click(admin_customerPage.Icon_EditPhone());
                action.clearSendKeys(admin_customerPage.TextBox_Phone(),value);
                action.click(admin_customerPage.Button_SavePhone());
                action.clearSendKeys(admin_customerPage.TextBox_Comment(),"updated to "+ value);
                action.click(admin_customerPage.Button_Save());
                cucumberContextManager.setScenarioContext("PHONE",value);
                break;
            case "Email":
                action.click(admin_customerPage.Icon_EditEmail());
                Thread.sleep(2000);
                action.clearSendKeys(admin_customerPage.TextBox_Email(),value);
                action.click(admin_customerPage.Button_SaveEmail());
                action.clearSendKeys(admin_customerPage.TextBox_Comment(),"updated to "+ value);
                action.click(admin_customerPage.Button_Save());
                cucumberContextManager.setScenarioContext("EMAIL",value);
                break;
        }
        log("I edit "+field+ " to "+ value ,
                "I edited "+field+ " to "+ value);
} catch(Exception e){
    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    error("Step should be successful", "Error performing step,Please check logs for more details",
            true);
}

    }
    @When("^I edit \"([^\"]*)\" to \"([^\"]*)\" and try to save it$")
    public void i_edit_something_to_something_and_try_to_save_it(String field, String value) throws Throwable {
try{
        switch (field) {
            case "Phone":
                action.click(admin_customerPage.Icon_EditPhone());
                action.clearSendKeys(admin_customerPage.TextBox_Phone(),value);
                action.click(admin_customerPage.Button_SavePhone());
                cucumberContextManager.setScenarioContext("PHONE",value);
                break;
            case "Email":
                action.click(admin_customerPage.Icon_EditEmail());
                action.clearSendKeys(admin_customerPage.TextBox_Email(),value);
                action.click(admin_customerPage.Button_SaveEmail());
                cucumberContextManager.setScenarioContext("EMAIL",value);
                break;
        }
        log("I edit "+field+ " to "+ value ,
                "I edited "+field+ " to "+ value);
} catch(Exception e){
    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    error("Step should be successful", "Error performing step,Please check logs for more details",
            true);
}

    }

    @Then("^updated Phone and Email is displayed for the customer$")
    public void updated_phone_and_email_is_saved_for_the_customer() throws Throwable {
        try{
        String phone = (String)  cucumberContextManager.getScenarioContext("PHONE");
        String email = (String)  cucumberContextManager.getScenarioContext("EMAIL");
        String actualPhone= action.getText(admin_customerPage.Label_CustomerPhone());
        String actualEmail= action.getText(admin_customerPage.Label_CustomerEmail());

        testStepAssert.isEquals(actualPhone,phone,actualPhone+" should be displayed",actualPhone+" is displayed",actualPhone+" is not displayed");
        testStepAssert.isEquals(actualEmail,email,actualEmail+" should be displayed",actualEmail+" is displayed",actualEmail+" is not displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^updated Phone is displayed for the customer in the Customer List$")
    public void updated_phone_is_displayed_for_the_customer_in_the_customer_list() throws Throwable {
        try{
        String custName = (String) cucumberContextManager.getScenarioContext("CUSTFIRSTNAME") + " "+ (String) cucumberContextManager.getScenarioContext("CUSTLASTNAME");
        String phone = (String)  cucumberContextManager.getScenarioContext("PHONE");

        String xpath = String.format("//tr[1]/td[contains(.,'%s')]/following-sibling::td[text()='%s']", custName,phone);
        testStepAssert.isElementDisplayed(admin_customerPage.findElement(xpath,PageBase.LocatorType.XPath),
                "Customer's updated phone should be listed in grid.",
                "Customer's updated phone is listed in grid.",
                "Customer's updated phone is not listed in grid.");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I view the searched customer$")
    public void i_view_the_searched_customer() throws Throwable {
        try{
       String xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
        action.click(admin_customerPage.findElement(xpath,PageBase.LocatorType.XPath));
        log("I view searched Customer" ,
                "I viewed searched Customer");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I edit \"([^\"]*)\" to \"([^\"]*)\" and Cancel it$")
    public void i_edit_something_to_something_and_cancel_it(String field, String value) throws Throwable {
        try{
        switch (field) {
            case "Phone":
                action.click(admin_customerPage.Icon_EditPhone());
                String oldPhoneValue = action.getAttributeValue(admin_customerPage.TextBox_Phone());;
                action.clearSendKeys(admin_customerPage.TextBox_Phone(),value);
                action.click(admin_customerPage.Button_CancelPhone());
                cucumberContextManager.setScenarioContext("NEWPHONE",value);
                cucumberContextManager.setScenarioContext("OLDPHONE",oldPhoneValue);

                break;
            case "Email":
                action.click(admin_customerPage.Icon_EditEmail());
                String oldEmailValue = action.getAttributeValue(admin_customerPage.TextBox_Email());;
                action.clearSendKeys(admin_customerPage.TextBox_Email(),value);
                action.click(admin_customerPage.Button_CancelEmail());
                cucumberContextManager.setScenarioContext("NEWEMAIL",value);
                cucumberContextManager.setScenarioContext("OLDEMAIL",oldEmailValue);

                break;
        }
        log("I edit "+field+ " to "+ value +" but cancel it" ,
                "I edited "+field+ " to "+ value +" but canceled it");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^old Phone and Email is displayed for the customer$")
    public void old_phone_and_email_is_displayed_for_the_customer() throws Throwable {
        try{
        String phone = (String)  cucumberContextManager.getScenarioContext("OLDPHONE");
        String email = (String)  cucumberContextManager.getScenarioContext("OLDEMAIL");
        String actualPhone= action.getText(admin_customerPage.Label_CustomerPhone());
        String actualEmail= action.getText(admin_customerPage.Label_CustomerEmail());

        testStepAssert.isEquals(actualPhone,phone,actualPhone+" should be displayed",actualPhone+" is displayed",actualPhone+" is not displayed");
        testStepAssert.isEquals(actualEmail,email,actualEmail+" should be displayed",actualEmail+" is displayed",actualEmail+" is not displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^old Phone is displayed for the customer in the Customer List$")
    public void old_phone_is_displayed_for_the_customer_in_the_customer_list() throws Throwable {
        try{
        String custFirstName = (String) cucumberContextManager.getScenarioContext("CUSTFIRSTNAME");
        String phone = (String)  cucumberContextManager.getScenarioContext("OLDPHONE");

        String xpath = String.format("//tr[1]/td[contains(.,'%s')]/following-sibling::td[text()='%s']", custFirstName,phone);
        testStepAssert.isElementDisplayed(admin_customerPage.findElement(xpath,PageBase.LocatorType.XPath),
                "Customer's updated phone should be listed in grid.",
                "Customer's updated phone is listed in grid.",
                "Customer's updated phone is not listed in grid.");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I navigate to Customer List$")
    public void i_navigate_to_customer_list() {
        try {
            String url = utility.GetAdminUrl().replace("/admin/login", "") + "/BungiiReports/Customers";
            action.navigateTo(url);
            Thread.sleep(5000);
            action.isElementPresent(admin_customerPage.TextBox_SearchCustomer());
            Thread.sleep(5000);
            log("I navigate to Customer List",
                    "I navigated to Customer List");
        }
        catch(Exception ex)
        {
            logger.error("Error performing step" + ex);
            error("Step  Should be successful",
                    "Error in navigating to Customer List", true);
        }
    }

    @When("^I edit \"([^\"]*)\" to \"([^\"]*)\" and Cancel on Comments popup$")
    public void i_edit_something_to_something_and_cancel_on_comments_popup(String field, String value) throws Throwable {
        try{
        switch (field) {
            case "Phone":
                action.click(admin_customerPage.Icon_EditPhone());
                String oldPhoneValue = action.getAttributeValue(admin_customerPage.TextBox_Phone());;
                action.clearSendKeys(admin_customerPage.TextBox_Phone(),value);
                action.click(admin_customerPage.Button_SavePhone());
                action.clearSendKeys(admin_customerPage.TextBox_Comment(),"updated to "+ value);
                action.click(admin_customerPage.Button_Cancel());
                cucumberContextManager.setScenarioContext("NEWPHONE",value);
                cucumberContextManager.setScenarioContext("OLDPHONE",oldPhoneValue);
                break;
            case "Email":
                action.click(admin_customerPage.Icon_EditEmail());
                String oldEmailValue = action.getAttributeValue(admin_customerPage.TextBox_Email());;
                action.clearSendKeys(admin_customerPage.TextBox_Email(),value);
                action.click(admin_customerPage.Button_SaveEmail());
                action.clearSendKeys(admin_customerPage.TextBox_Comment(),"updated to "+ value);
                action.click(admin_customerPage.Button_Cancel());
                cucumberContextManager.setScenarioContext("NEWEMAIL",value);
                cucumberContextManager.setScenarioContext("OLDEMAIL",oldEmailValue);
                break;
        }
        log("I edit "+field+ " to "+ value +" but cancel on Comments popup" ,
                "I edited "+field+ " to "+ value +" but cancels on Comments popup");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^I should see \"([^\"]*)\" message for \"([^\"]*)\" field$")
    public void i_should_see_something_message_for_something_field(String message, String field) throws Throwable {
        try{
        String actualMessage ="";
        switch (field) {
            case "Phone":
                actualMessage = action.getText(admin_customerPage.Label_CustomerPhoneMessage()) ;
                testStepAssert.isEquals(actualMessage,message,
                        message+ " should be displayed",
                        actualMessage + " is displayed",
                        actualMessage + " is displayed instead of " +message);
                break;
            case "Email":
                actualMessage = action.getText(admin_customerPage.Label_CustomerEmailMessage()) ;
                testStepAssert.isEquals(actualMessage,message,
                        message+ " should be displayed",
                        actualMessage + " is displayed",
                        actualMessage + " is displayed instead of "+message);
                break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

}
