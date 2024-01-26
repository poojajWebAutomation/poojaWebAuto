package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_PartnerPortalPage;
import com.bungii.web.pages.admin.Admin_PartnersPage;
import com.bungii.web.pages.admin.Admin_PaymentMethodsPage;
import com.bungii.web.pages.partner.Partner_LoginPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.web.utilityfunctions.DbUtility.*;

public class Admin_PartnerSteps extends DriverBase {

    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    private static LogUtility logger = new LogUtility(Admin_PartnerSteps.class);
    Admin_PartnerPortalPage admin_partnerPortalPage = new Admin_PartnerPortalPage();
    Admin_PartnersPage admin_partnersPage=new Admin_PartnersPage();
    Admin_PaymentMethodsPage admin_paymentMethodsPage = new Admin_PaymentMethodsPage();
    Partner_LoginPage Page_Partner_Login = new Partner_LoginPage();

    @When("^I search by partner Name \"([^\"]*)\"$")
    public void i_search_by_partner_name_something(String unique) throws Throwable {
        try{
        String searchTerm= (String)cucumberContextManager.getScenarioContext("PARTNER");
        action.clearSendKeys(admin_partnerPortalPage.TextBox_Search(), searchTerm+Keys.ENTER);
        log("I search partner" ,
                "I have searched partner "+ searchTerm, false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the partner \"([^\"]*)\" gets saved successfully and it is displayed in the Partners grid$")
    public void the_partner_something_gets_saved_successfully_and_it_is_displayed_in_the_partners_grid(String unique) throws Throwable {
        try{
        String Partner =(String) cucumberContextManager.getScenarioContext("PARTNER");;
        String Phone = (String) cucumberContextManager.getScenarioContext("PHONE");
        String Email = (String) cucumberContextManager.getScenarioContext("EMAIL");
        String Status = (String)cucumberContextManager.getScenarioContext("STATUS");
        Thread.sleep(6000);
        i_search_by_partner_name_something(Partner);
        String xpath = String.format("//tr/td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td/a[text()='%s']",Status, Email, Phone , Partner);
        testStepAssert.isElementDisplayed(admin_partnerPortalPage.findElement(xpath, PageBase.LocatorType.XPath),xpath +" Element should be displayed",xpath+ " Element is displayed", xpath+ " Element is not displayed");
        cucumberContextManager.setScenarioContext("XPath",xpath);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the partner \"([^\"]*)\" is displayed in the Partners grid$")
    public void the_partner_something_is_displayed_in_the_partners_grid(String unique) throws Throwable {
        try{
        String Partner =(String) cucumberContextManager.getScenarioContext("PARTNER");;
        String Phone = (String) cucumberContextManager.getScenarioContext("PHONE");
        String Email = (String) cucumberContextManager.getScenarioContext("EMAIL");
        String Status = (String)cucumberContextManager.getScenarioContext("STATUS");
        Thread.sleep(6000);
        String xpath = String.format("//tr/td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td/a[text()='%s']",Status, Email, Phone , Partner);
        testStepAssert.isElementDisplayed(admin_partnerPortalPage.findElement(xpath, PageBase.LocatorType.XPath),xpath +" Element should be displayed",xpath+ " Element is displayed", xpath+ " Element is not displayed");
        cucumberContextManager.setScenarioContext("XPath",xpath);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @When("^I view the partner \"([^\"]*)\"$")
    public void i_view_the_partner_something(String unique) throws Throwable {
        try{
       String xpath = (String) cucumberContextManager.getScenarioContext("XPath");
       Thread.sleep(5000);
        action.click(SetupManager.getDriver().findElement(By.xpath(xpath)));
        log("I view partner "+unique ,
                "I have viewed partner", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @Then("^the saved data is getting displayed in the \"([^\"]*)\" page$")
    public void the_saved_data_is_getting_displayed_in_the_something_page(String strArg1) throws Throwable {
        try{
       String partnerName= (String)    cucumberContextManager.getScenarioContext("PARTNER");
        String partnerType = (String)    cucumberContextManager.getScenarioContext("PARTNERTYPE");
        String phone= (String) cucumberContextManager.getScenarioContext("PHONE" );
        String email= (String)  cucumberContextManager.getScenarioContext("EMAIL");
        String Status= (String)  cucumberContextManager.getScenarioContext("STATUS");
        String firstName= (String)  cucumberContextManager.getScenarioContext("FIRSTNAME");
        String lastName= (String)  cucumberContextManager.getScenarioContext("LASTNAME");
        String addressOne= (String)  cucumberContextManager.getScenarioContext("ADDRESSONE");
        String addressTwo= (String)  cucumberContextManager.getScenarioContext("ADDRESSTWOW");
        String city= (String) cucumberContextManager.getScenarioContext("CITY");
        String zip= (String)  cucumberContextManager.getScenarioContext("ZIP");
        String state= (String) cucumberContextManager.getScenarioContext("STATE" );
        String comments= (String)  cucumberContextManager.getScenarioContext("COMMENTS");
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.TextBox_PartnerName()),partnerName,partnerName +" should be displayed",partnerName +" is displayed", partnerName +" is not displayed");
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.TextBox_Phone()),phone,phone +" should be displayed",phone +" is displayed", phone +" is not displayed");
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.TextBox_Email()),email,email +" should be displayed",email +" is displayed", email +" is not displayed");
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.TextBox_PartnerFirstName()),firstName,firstName +" should be displayed",firstName +" is displayed", firstName +" is not displayed");
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.TextBox_PartnerLastName()),lastName,lastName +" should be displayed",lastName +" is displayed", lastName +" is not displayed");
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.TextBox_AddressOne()),addressOne,addressOne +" should be displayed",addressOne +" is displayed", addressOne +" is not displayed");
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.TextBox_AddressTwo()),addressTwo,addressTwo +" should be displayed",addressTwo +" is displayed", addressTwo +" is not displayed");
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.TextBox_City()),city,city +" should be displayed",city +" is displayed", city +" is not displayed");
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.TextBox_Zip()),zip,zip +" should be displayed",zip +" is displayed", zip +" is not displayed");
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.TextBox_Comments()),comments,comments +" should be displayed",comments +" is displayed", comments +" is not displayed");
        testStepAssert.isEquals(action.getFirstSelectedOption(admin_partnerPortalPage.DropDown_PartnerType()),partnerType,partnerType +" should be displayed",partnerType +" is displayed", partnerType +" is not displayed");
        testStepAssert.isEquals(action.getFirstSelectedOption(admin_partnerPortalPage.DropDown_State()),state,state +" should be displayed",state +" is displayed", state +" is not displayed");

        if(Status=="Active")
        testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.Value_PartnerStatus()),"2",Status +" should be displayed",Status +" is displayed", Status +" is not displayed");
        else
            testStepAssert.isEquals(action.getAttributeValue(admin_partnerPortalPage.Value_PartnerStatus()),"3",Status +" should be displayed",Status +" is displayed", Status +" is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I update following values in fields in \"([^\"]*)\" screen$")
    public void i_update_following_values_in_fields_in_something_screen(String screen,DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            switch (screen) {
                case "Add Partner":
                    String id = uniqid();
                    String partnerName = dataMap.get("Partner Name").trim().replace("<<Unique>>", id);
                    String partnerType = dataMap.get("Partner Type").trim();
                    String phone = dataMap.get("Phone").trim();
                    String email = dataMap.get("Email").trim();
                    String firstName = dataMap.get("First Name").trim();
                    String lastName = dataMap.get("Last Name").trim();
                    String addressOne = dataMap.get("Address 1").trim();
                    String addressTwo = dataMap.get("Address 2").trim();
                    String city = dataMap.get("City").trim();
                    String state = dataMap.get("State").trim();
                    String zip = dataMap.get("Zip").trim();
                    String comments = dataMap.get("Comments").trim();
                    String logo = dataMap.get("Logo").trim();
                    String status = dataMap.get("Status").trim();

                    action.clearSendKeys(admin_partnerPortalPage.TextBox_PartnerName(), partnerName);
                    action.selectElementByText(admin_partnerPortalPage.DropDown_PartnerType(), partnerType);
                    action.clearSendKeys(admin_partnerPortalPage.TextBox_Email(), email);
                    action.clearSendKeys(admin_partnerPortalPage.TextBox_Phone(), phone);
                    action.clearSendKeys(admin_partnerPortalPage.TextBox_PartnerFirstName(), firstName);
                    action.clearSendKeys(admin_partnerPortalPage.TextBox_PartnerLastName(), lastName);
                    action.clearSendKeys(admin_partnerPortalPage.TextBox_AddressOne(), addressOne);
                    action.clearSendKeys(admin_partnerPortalPage.TextBox_AddressTwo(), addressTwo);
                    action.clearSendKeys(admin_partnerPortalPage.TextBox_City(), city);
                    action.clearSendKeys(admin_partnerPortalPage.TextBox_Zip(), zip);
                    action.selectElementByText(admin_partnerPortalPage.DropDown_State(), state);
                    action.clearSendKeys(admin_partnerPortalPage.TextBox_Comments(), comments);
                    String Status = "";
                    if(status.equalsIgnoreCase("Active")) {
                        if (action.getAttributeValue(admin_partnerPortalPage.Value_PartnerStatus()) != "2") {
                            action.JavaScriptClick(admin_partnerPortalPage.Swtich_PartnerStatus());
                        }
                        Status = "Active";

                    }
                    else {
                        if (action.getAttributeValue(admin_partnerPortalPage.Value_PartnerStatus()) != "3") {
                            action.JavaScriptClick(admin_partnerPortalPage.Swtich_PartnerStatus());
                        }
                        Status = "InActive";
                    }
                    cucumberContextManager.setScenarioContext("PARTNER", partnerName);
                    cucumberContextManager.setScenarioContext("PARTNERTYPE", partnerType);
                    cucumberContextManager.setScenarioContext("PHONE", phone);
                    cucumberContextManager.setScenarioContext("EMAIL", email);
                    cucumberContextManager.setScenarioContext("STATUS", Status);
                    cucumberContextManager.setScenarioContext("FIRSTNAME", firstName);
                    cucumberContextManager.setScenarioContext("LASTNAME", lastName);
                    cucumberContextManager.setScenarioContext("ADDRESSONE", addressOne);
                    cucumberContextManager.setScenarioContext("ADDRESSTWOW", addressTwo);
                    cucumberContextManager.setScenarioContext("CITY", city);
                    cucumberContextManager.setScenarioContext("ZIP", zip);
                    cucumberContextManager.setScenarioContext("STATE", state);
                    cucumberContextManager.setScenarioContext("COMMENTS", comments);
                    break;
            }

            log("I update data into Edit Partner screen",
                    "I have updated data into Edit Partner screen", true);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I see the \"([^\"]*)\" message$")
    public void i_see_the_something_message(String errorMessage) throws Throwable {
        try{
            String actualMessage= action.getText(admin_partnersPage.Text_Invalid_Password_Message());
            testStepAssert.isEquals(actualMessage,errorMessage,"Invalid login credentials. Your account has been locked.","Correct error message is displayed","In-correct error message is displayed: "+actualMessage);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @And("^I enter \"([^\"]*)\" password and click \"([^\"]*)\" ten times on Partner Portal$")
    public void i_enter_something_password_and_click_something_ten_times_on_partner_portal(String strArg1, String strArg2) throws Throwable {

        try{
            String currentUrl= action.getCurrentURL();
            int indexValueOne = currentUrl.indexOf("/",(currentUrl.indexOf("/") + 1));
            int indexValueTwo = currentUrl.indexOf(".");
            String subDomainName= currentUrl.substring(indexValueOne+1,indexValueTwo);
            cucumberContextManager.setScenarioContext("SUB_DOMAIN_NAME",subDomainName);

            int i =1;
            for(i=1;i<=10;i++)
            {
                action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("Invalid_PartnerPassword"));
                action.click(Page_Partner_Login.Button_Sign_In());
            }
            cucumberContextManager.setScenarioContext("LOCK_BIT",true);

            log("I should be able to enter incorrect password for 10 times",
                    "I entered incorrect password 10 times",false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }

    }

    @And("^I click on \"([^\"]*)\" in the side menu$")
    public void i_click_on_something_in_the_side_menu(String strArg1) throws Throwable {

        try{
            action.click(admin_paymentMethodsPage.Menu_Partners());

            log("I should be able to click on Partners in the side menu",
                    "I am able to click on Partners in the side menu",false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }

    }

    @And("^I click on \"([^\"]*)\" in Partner Portal$")
    public void i_click_on_something_in_partner_portal(String strArg1) throws Throwable {

        try{
            action.click(admin_paymentMethodsPage.Menu_UnlockPartnersSubMenu());

            log("I should be able to click on Unlock Partners in the sub menu",
                    "I am able to click on Unlock Partners in the sub menu",false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @When("^I check for the locked partner user and click \"([^\"]*)\" button$")
    public void i_check_for_the_locked_partner_user_and_click_something_button(String strArg1) throws Throwable {

        try{
            String subDomainName= (String) cucumberContextManager.getScenarioContext("SUB_DOMAIN_NAME");
            String partnerName = DbUtility.getPartnerName(subDomainName);
            action.click(admin_partnersPage.Button_Unlock(partnerName));
            cucumberContextManager.setScenarioContext("PARTNER_NAME",partnerName);

            cucumberContextManager.setScenarioContext("LOCK_BIT",false);

            log("I should be able to see the locked partner, click on the unlock button and unlock the locked partner",
                    "I am able to see the locked partner, click on the unlock button and unlock the locked partner",false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @And("^I check if the unlocked partner is displayed in the list$")
    public void i_check_if_the_unlocked_partner_is_displayed_in_the_list() throws Throwable {
        try{
            String subDomainName= (String) cucumberContextManager.getScenarioContext("SUB_DOMAIN_NAME");
            String partnerName = DbUtility.getPartnerName(subDomainName);
            testStepAssert.isFalse(action.isElementPresent(admin_partnersPage.Button_Unlock(partnerName,true)), "Unlocked partner should not be present", "Unlocked partner is not present", "Unlocked partner is present");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }


    @And("^I enter following values in fields in \"([^\"]*)\" screen$")
    public void i_enter_following_values_in_fields_in_something_screen(String screen,DataTable data) throws Throwable {

        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            switch (screen) {
                case "Add Partner":
                    String id = uniqid();
                    String partnerName = dataMap.get("Partner Name").trim().replace("<<Unique>>", id);
                    String partnerType = dataMap.get("Partner Type").trim();
                    String phone = dataMap.get("Phone").trim();
                    String email = dataMap.get("Email").trim();
                    String firstName = dataMap.get("First Name").trim();
                    String lastName = dataMap.get("Last Name").trim();
                    String addressOne = dataMap.get("Address 1").trim();
                    String addressTwo = dataMap.get("Address 2").trim();
                    String city = dataMap.get("City").trim();
                    String state = dataMap.get("State").trim();
                    String zip = dataMap.get("Zip").trim();
                    String comments = dataMap.get("Comments").trim();
                    String logo = dataMap.get("Logo").trim();
                    String status = dataMap.get("Status").trim();

                    action.sendKeys(admin_partnerPortalPage.TextBox_PartnerName(), partnerName);
                    action.selectElementByText(admin_partnerPortalPage.DropDown_PartnerType(), partnerType);
                    action.sendKeys(admin_partnerPortalPage.TextBox_Email(), email);
                    action.sendKeys(admin_partnerPortalPage.TextBox_Phone(), phone);
                    action.sendKeys(admin_partnerPortalPage.TextBox_PartnerFirstName(), firstName);
                    action.sendKeys(admin_partnerPortalPage.TextBox_PartnerLastName(), lastName);
                    action.sendKeys(admin_partnerPortalPage.TextBox_AddressOne(), addressOne);
                    action.sendKeys(admin_partnerPortalPage.TextBox_AddressTwo(), addressTwo);
                    action.sendKeys(admin_partnerPortalPage.TextBox_City(), city);
                    action.sendKeys(admin_partnerPortalPage.TextBox_Zip(), zip);
                    action.selectElementByText(admin_partnerPortalPage.DropDown_State(), state);
                    action.sendKeys(admin_partnerPortalPage.TextBox_Comments(), comments);
                    if (logo.equalsIgnoreCase("CC"))
                        action.sendKeys(admin_partnerPortalPage.Input_Logo(), FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"), PropertyUtility.getImageLocations("PARTNER_IMAGE")));
                    String Status = "Active";
                    if (!status.equalsIgnoreCase("Active"))
                    {
                        action.JavaScriptClick(admin_partnerPortalPage.Swtich_PartnerStatus());
                        Status="InActive";
                    }
                    cucumberContextManager.setScenarioContext("PARTNER", partnerName);
                    cucumberContextManager.setScenarioContext("PARTNERTYPE", partnerType);
                    cucumberContextManager.setScenarioContext("PHONE", phone);
                    cucumberContextManager.setScenarioContext("EMAIL", email);
                    cucumberContextManager.setScenarioContext("STATUS", Status);
                    cucumberContextManager.setScenarioContext("FIRSTNAME", firstName);
                    cucumberContextManager.setScenarioContext("LASTNAME", lastName);
                    cucumberContextManager.setScenarioContext("ADDRESSONE", addressOne);
                    cucumberContextManager.setScenarioContext("ADDRESSTWOW", addressTwo);
                    cucumberContextManager.setScenarioContext("CITY", city);
                    cucumberContextManager.setScenarioContext("ZIP", zip);
                    cucumberContextManager.setScenarioContext("STATE", state);
                    cucumberContextManager.setScenarioContext("COMMENTS", comments);

                    break;
            }

                log("I enter data into Add Partner screen",
                        "I have entered data into Add Partner screen", true);
            } catch(Exception e){
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step  Should be successful", "Error performing step,Please check logs for more details",
                        true);
            }

        }
    @Then("^The validation message should be displayed against the \"([^\"]*)\" fields$")
    public void the_validation_message_should_be_displayed_against_the_something_fields(String feature, DataTable data) throws Throwable {
        try {
            List<Map<String, String>> DataList = data.asMaps();

            switch (feature) {
                case "Partner":
                    for (int i = 0; i<DataList.size();i++) {
                        String Field = DataList.get(i).get("Field").trim();
                        String Message = DataList.get(i).get("Message").trim();

                        switch (Field) {
                            case "Partner Name":
                                testStepAssert.isEquals(action.getText(admin_partnerPortalPage.Label_PartnerNameError()),Message,Message +" should be displayed",Message +" is displayed", Message +" is not displayed");
                                break;
                            case "Partner Type":
                                testStepAssert.isEquals(action.getText(admin_partnerPortalPage.Label_PartnerTypeError()),Message,Message +" should be displayed",Message +" is displayed", Message +" is not displayed");
                                break;
                            case "Phone":
                                testStepAssert.isEquals(action.getText(admin_partnerPortalPage.Label_PartnerPhoneError()),Message,Message +" should be displayed",Message +" is displayed", Message +" is not displayed");
                                break;
                            case "Email":
                                testStepAssert.isEquals(action.getText(admin_partnerPortalPage.Label_PartnerEmailError()),Message,Message +" should be displayed",Message +" is displayed", Message +" is not displayed");
                                break;
                            case "Address1":
                                testStepAssert.isEquals(action.getText(admin_partnerPortalPage.Label_PartnerAddress1Error()),Message,Message +" should be displayed",Message +" is displayed", Message +" is not displayed");
                                break;
                            case "City":
                                testStepAssert.isEquals(action.getText(admin_partnerPortalPage.Label_PartnerCityError()),Message,Message +" should be displayed",Message +" is displayed", Message +" is not displayed");
                                break;
                            case "State":
                                testStepAssert.isEquals(action.getText(admin_partnerPortalPage.Label_PartnerStateError()),Message,Message +" should be displayed",Message +" is displayed", Message +" is not displayed");
                                break;
                            case "Zipcode":
                                testStepAssert.isEquals(action.getText(admin_partnerPortalPage.Label_PartnerZipCodeError()),Message,Message +" should be displayed",Message +" is displayed", Message +" is not displayed");
                                break;
                        }
                    }

                    break;
            }

            log("I enter data into Add Partner screen",
                    "I have entered data into Add Partner screen", true);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    private String uniqid() {
        Random random = new Random();
        String tag = Long.toString(Math.abs(random.nextLong()), 36);
        return tag.substring(0, 3).toUpperCase();
    }

}
