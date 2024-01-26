package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_AccessorialChargesPage;
import com.bungii.web.pages.admin.Admin_PaymentMethodsPage;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_PaymentMethodSteps extends DriverBase {

        ActionManager action = new ActionManager();
        private static LogUtility logger = new LogUtility(Admin_PaymentMethodSteps.class);
        Admin_AccessorialChargesPage admin_accessorialChargesPage= new Admin_AccessorialChargesPage();
        Admin_PaymentMethodsPage admin_paymentMethodsPage = new Admin_PaymentMethodsPage();

        @Then("^The \"([^\"]*)\" gets saved successfully and it is displayed in the grid$")
        public void the_something_gets_saved_successfully_and_it_is_displayed_in_the_grid(String pageName) throws Throwable {
            try{
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
            Date parsedDate = null;
            switch (pageName) {
                case "Partner Cards":
                    String CardNumber = (String) cucumberContextManager.getScenarioContext("CARD_NUMBER");
                    String CardExpiryDate = (String) cucumberContextManager.getScenarioContext("CARD_EXPIRY_DATE");
                     parsedDate = sdf.parse(CardExpiryDate);
                    CardExpiryDate = sdf1.format(parsedDate).toString();
                    Thread.sleep(4000);
                    CardNumber = "**** **** **** " + CardNumber.substring(11,15);
                    String PartnerXpath = String.format("//tr/td[text()='Visa ']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/i[contains(@class, ' fa fa-check-circle fa-3x default-icon')]", CardNumber, CardExpiryDate);
                    testStepAssert.isElementDisplayed(admin_paymentMethodsPage.Label_SuccessMessageForPartner(), "Partner Payment Method added successfully. message should be displayed", "Partner Payment Method added successfully. message is displayed", "Partner Payment Method added successfully. message is not displayed");
                    cucumberContextManager.setScenarioContext("XPath", PartnerXpath);
                    break;
                case "Bungii Cards":
                    String BungiiCardNumber = (String) cucumberContextManager.getScenarioContext("CARD_NUMBER");;
                    String BungiiCardExpiryDate = (String) cucumberContextManager.getScenarioContext("CARD_EXPIRY_DATE");
                     parsedDate = sdf.parse(BungiiCardExpiryDate);
                    BungiiCardExpiryDate = sdf1.format(parsedDate).toString();
                    Thread.sleep(4000);
                    BungiiCardNumber = "**** **** **** " + BungiiCardNumber.substring(11,15);
                    String BungiiXpath = String.format("//tr/td[text()='Visa ']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/i[contains(@class, ' fa fa-check-circle fa-3x default-icon')]", BungiiCardNumber, BungiiCardExpiryDate);
                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(BungiiXpath)), BungiiXpath + " Element should be displayed", BungiiXpath + " Element is displayed", BungiiXpath + " Element is not displayed");
                    cucumberContextManager.setScenarioContext("XPath", BungiiXpath);
                    break;

            }
        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
        }

//    @Then("^the card is added to the user \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void the_card_is_added_to_the_user_something_on_something_page(String strArg1, String strArg2) throws Throwable {
//            testStepAssert.isElementTextEquals(admin_paymentMethodsPage.Label_SuccessMessage(),"Partner Payment Method added successfully.","Partner Payment Method added successfully. message should be displayed" ,"Partner Payment Method added successfully. message is displayed","Partner Payment Method added successfully. message should be displayed is not displayed");
//    }
        @Then("^the card is added to the user \"([^\"]*)\" page$")
        public void the_card_is_added_to_the_user_something_page(String PageName) throws Throwable {
            try{
            switch(PageName) {
                case "partner Cards":
                    testStepAssert.isElementDisplayed(admin_paymentMethodsPage.Label_SuccessMessageForPartner(),"'Partner Payment Method added successfully. message should be displayed" ,"'Partner Payment Method added successfully. message is displayed","'Partner Payment Method added successfully. message should be displayed is not displayed");
                    break;

                case "Bungii Cards":
                    testStepAssert.isElementTextEquals(admin_paymentMethodsPage.Label_SuccessMessageForBungii(),"Bungii Payment Method added successfully.","Bungii Payment Method added successfully. message should be displayed" ,"Bungii Payment Method added successfully. message is displayed","Bungii Payment Method added successfully. message should be displayed is not displayed");
                    break;
            }

        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
        }

        @Then("^The \"([^\"]*)\" details screen is removed from UI$")
        public void the_something_details_screen_is_removed_from_ui(String PageName) throws Throwable {
            try{
            switch(PageName) {
                case "Add Partner Cards":
                    testStepAssert.isNotElementDisplayed(admin_paymentMethodsPage.Button_Save(true), PageName + " button should be hidden",
                            PageName +" Popup is hidden", PageName+" Popup is not hidden");
                    break;

                case "Add Bungii Cards":
                    testStepAssert.isNotElementDisplayed(admin_paymentMethodsPage.Button_Save(true), PageName + " button should be hidden",
                            PageName +" button is hidden", PageName+" button is not hidden");
                    break;
            }
        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
        }

    @And("^I click on \"([^\"]*)\" dropdown$")
    public void i_click_on_something_dropdown(String dropdown) throws Throwable {
        try {
               switch(dropdown){
                 case "Partners":
                    action.click(admin_paymentMethodsPage.Dropdown_Partners());
                    Thread.sleep(1000);
                    break;
                 case "Select Fee Type":
                    action.click(admin_accessorialChargesPage.Dropdown_SelectFeeType());
                    break;
            }
            log("I should be able to click on the "+dropdown+" dropdown",
                    "I could click on the "+dropdown+" dropdown",false);
            
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I check if duplicate partner entries are shown in dropdown$")
    public void i_check_if_duplicate_partner_entries_are_shown_in_dropdown() throws Throwable {
        try {
            Select select = new Select(admin_paymentMethodsPage.Dropdown_Partners());
            List<String> Options = new ArrayList();
            List<WebElement> actualOptions = select.getOptions();
            int size = actualOptions.size();
            for(int i =0; i<size ; i++){
                String options = actualOptions.get(i).getText();
                Options.add(options);
            }
            Set duplicateSet = Options.stream().filter(i -> Collections.frequency(Options, i) > 1).collect(Collectors.toSet());
             if(duplicateSet.size()>0){
                   testStepAssert.isFalse(false,"The dropdown should not have duplicate values","The dropdown has duplicate values");
              }
            testStepAssert.isTrue(true,"The dropdown should not have duplicate values","The dropdown does not have duplicate values","The dropdown has duplicate values");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

}
