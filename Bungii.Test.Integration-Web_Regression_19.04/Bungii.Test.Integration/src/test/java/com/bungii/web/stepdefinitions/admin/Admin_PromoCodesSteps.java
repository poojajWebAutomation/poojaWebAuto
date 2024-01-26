package com.bungii.web.stepdefinitions.admin;

import bsh.org.objectweb.asm.Constants;
import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.customer.EstimateSteps;
import com.bungii.web.manager.*;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.partnerManagement.PartnerManagement_LocationPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static com.bungii.common.manager.ResultManager.*;

public class Admin_PromoCodesSteps extends DriverBase {
    Admin_PromoCodesPage admin_PromoCodesPage = new Admin_PromoCodesPage();
    Admin_BusinessUsersPage admin_BusinessUsersPage = new Admin_BusinessUsersPage();
    Admin_PromoterPage admin_PromoterPage = new Admin_PromoterPage();
    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_PotentialPartnersPage admin_potentialPartnersPage = new Admin_PotentialPartnersPage();

    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_PromoCodesSteps.class);
    Admin_ReferralSourcePage admin_ReferralSourcePage = new Admin_ReferralSourcePage();
    Admin_CustomerPage admin_customerPage=new Admin_CustomerPage();
    Admin_DriversPage admin_DriverPage=new Admin_DriversPage();
    GeneralUtility utility = new GeneralUtility();
    Admin_PaymentMethodsPage admin_paymentMethodsPage = new Admin_PaymentMethodsPage();
    Admin_PartnerPortalPage admin_partnerPortalPage = new Admin_PartnerPortalPage();
    Admin_EditScheduledBungiiPage admin_EditScheduledBungiiPage = new Admin_EditScheduledBungiiPage();
    PartnerManagement_LocationPage Page_PartnerManagement_Location = new PartnerManagement_LocationPage();
    Admin_LoginPage adminLoginPage = new Admin_LoginPage();

    @When("^I click on \"([^\"]*)\" Menu$")
    public void i_click_something_menu(String link) throws Throwable {
        try{
       switch(link) {
           case "Marketing  > Promocode" :
              action.click(admin_PromoCodesPage.Menu_Marketing());
              break;
           case "Promo Codes > Standard Codes" :
               action.click(admin_PromoterPage.Menu_Promotion());
               break;
           case "Marketing  > Referral Sources" :
               action.click(admin_PromoCodesPage.Menu_Marketing());
               action.click(admin_ReferralSourcePage.Menu_ReferralSource());
               break;
//           case "Business Users  > Business Users" :
           case "Bulk Delivery Upload  > Partners" :
               action.click(admin_BusinessUsersPage.Menu_BusinessUsers());
               break;
           case "Bulk Delivery Upload  > Upload Deliveries" :
               action.click(admin_BusinessUsersPage.Menu_BusinessUsers());
               action.click(admin_BusinessUsersPage.Menu_BulkTrips());
               break;
//           case "Business Users  > Business Users Payment" :
           case "Bulk Delivery Upload  > Partner Payment" :
               action.click(admin_BusinessUsersPage.Menu_BusinessUsers());
               action.click(admin_BusinessUsersPage.Menu_BusinessUsersPayment());
               break;
           case "Promo Codes > Free Deliveries Codes  > Partners" :
               action.click(admin_PromoterPage.Menu_Promotion());
               action.click(admin_PromoterPage.Menu_Promotion_Free_Delivery());
               break;
           case "Free Deliveries Codes  > Free Delivery Credit Card" :
               action.click(admin_PromoterPage.Menu_Promotion());
               action.click(admin_PromoterPage.Menu_Promotion_Free_Delivery());
               action.click(admin_PromoterPage.Menu_PromoterPayment());
               break;
           case "Geofences  > Geofences" :
               action.click(admin_GeofencePage.Menu_Geofences());
               break;

           case "Geofences  > Attributes":
               action.click(admin_GeofencePage.Menu_Geofences());
               action.click(admin_GeofencePage.Menu_Attributes());
               break;

           case "Customers":
               action.click(admin_customerPage.Menu_Customers());
               break;

           case "Deliveries > All Deliveries" :
               action.click(admin_TripsPage.Menu_Trips());
               action.click(admin_TripsPage.Menu_CompletedTrips());
               break;

           case "Deliveries > All DeliveriesPage" :
               action.click(admin_TripsPage.Menu_Trips());
               action.click(admin_TripsPage.Menu_AllTrips());
               break;

           case "Drivers":
               action.click(admin_DriverPage.Menu_Drivers());
               break;

           case "Potential Partners > Assign Partner":
                action.click(admin_potentialPartnersPage.Menu_AssignPartner());
               break;

           case "Potential Partners > Partner Search":
               action.click(admin_potentialPartnersPage.Menu_PartnerSearch());
               break;
           case "Partner Portal  > Partner Card":
               action.click(admin_paymentMethodsPage.Menu_Partners());
               action.click(admin_paymentMethodsPage.Menu_PaymentMethodsSubMenu());

               break;
           case "Partner Portal  > Bungii Card":
               action.click(admin_paymentMethodsPage.Menu_Partners());
               action.click(admin_paymentMethodsPage.Menu_PaymentMethodsSubMenu());
               action.click(admin_paymentMethodsPage.Menu_BungiiCards());
               break;
           case "Partner Portal  > Partners":
               Thread.sleep(5000);
               action.click(admin_paymentMethodsPage.Menu_Partners());
               action.click(admin_paymentMethodsPage.Menu_PartnersSubMenu());
               break;
           case "Partner Portal  > Locations":
               Thread.sleep(5000);
               action.click(admin_paymentMethodsPage.Menu_Partners());
               action.click(admin_paymentMethodsPage.Menu_LocationsSubMenu());
               break;
           case "Deliveries > Rejected API Deliveries" :
               action.click(admin_TripsPage.Menu_Trips());
               action.click(admin_TripsPage.Menu_RejectedAPIDeliveries());
               break;

       }
        log("I click on "+link+" menu link" ,
                "I have clicked on "+link+" menu link", false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @When("^I search by Name \"([^\"]*)\"$")
    public void i_search_by_name_something(String strArg1) throws Throwable {
        try{
        String Name = (String)cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
        action.clearSendKeys(admin_PromoCodesPage.TextBox_Search(),Name +Keys.ENTER);
        log("I search "+ Name + "prmocode" ,
                "I have on searched "+Name+" prmocode", true);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @When("^I search by Code \"([^\"]*)\"$")
    public void i_search_by_code_something(String strArg1) throws Throwable {

        try{
            Thread.sleep(3000);
            String Code = (String) cucumberContextManager.getScenarioContext("PROMOCODE");
            action.clearSendKeys(admin_PromoCodesPage.TextBox_Search(), Code + Keys.ENTER);


        log("I search "+ Code + "prmocode" ,
                "I have on searched "+Code+" prmocode", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @When("^I search by string \"([^\"]*)\"$")
    public void i_search_by_string_something(String strArg1) throws Throwable {

        try{
        action.sendKeys(admin_PromoCodesPage.TextBox_Search(), strArg1 + Keys.ENTER);
        log("I search "+ strArg1 + " string" ,
                "I have on searched "+strArg1+" string", false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }
    @When("^I search by first code generated for above promocode$")
    public void i_search_by_any_code_generated_for_above_promocode() throws Throwable {
        try{
        action.click(admin_PromoCodesPage.Button_Filter());
        action.click(admin_PromoCodesPage.Button_Reset());
        action.click(admin_PromoCodesPage.Icon_CloseFilter());
        Thread.sleep(2000);
        String LastCode = (String) cucumberContextManager.getScenarioContext("LASTCODE");
        action.sendKeys(admin_PromoCodesPage.TextBox_Search(), LastCode);
        action.click(admin_PromoCodesPage.Button_Search());
        Thread.sleep(4000);
        log("I search "+ LastCode + "prmocode" ,
                "I have on searched "+LastCode+" prmocode", false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }
    @Then("^the searched promocode data gets populated correctly$")
    public void the_searched_promocode_data_gets_populated_correctly() throws Throwable {
        try{
        String Count = (String) cucumberContextManager.getScenarioContext("CODE_COUNT");
        String Name = (String)cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
        String Type = (String)cucumberContextManager.getScenarioContext("PROMOCODE_TYPE");
        String Expires = (String)cucumberContextManager.getScenarioContext("EXP_DATE_VIEW");
        String Promoter = (String)cucumberContextManager.getScenarioContext("PROMOTER");
        String Promotion = (String)cucumberContextManager.getScenarioContext("PROMOTION");
        String Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
        String PromotionStartDate = (String) cucumberContextManager.getScenarioContext("PROMOTION_STARTDATE");
        String expectedNoOfCodes="0";
//        Date PromoStartDate=new SimpleDateFormat("MM/dd/yyyy").parse(PromotionStartDate);
//        Date ExpirationDate=new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(Expires);

        testStepAssert.isEquals(admin_PromoCodesPage.TextBox_PromoCodeName().getAttribute("value"), Name, "Promocode name "+Name+" should be displayed", "Promocode name "+Name+" is displayed", "Promocode name "+Name+" is not displayed" );
        String LastCode = (String) cucumberContextManager.getScenarioContext("LASTCODE");

        testStepAssert.isElementDisplayed(admin_PromoCodesPage.DropDown_PromoType().findElement(By.xpath(String.format("//span[@class='react-inputs-validation__select__optionItem_current_display_name' and text()='Delivery By Partner']",Type))),Type + " should be displayed",Type +" is displayed",Type + " is not displayed");
        testStepAssert.isElementDisplayed(admin_PromoCodesPage.DropDown_Promoter().findElement(By.xpath(String.format("//span[@class='react-inputs-validation__select__optionItem_current_display_name' and text()='World Market Promotion']",Promoter))),Promoter+ " should be displayed",Promoter + " is displayed",Promoter +" is not displayed");
        Thread.sleep(4000);
        testStepAssert.isElementDisplayed(admin_PromoCodesPage.DropDown_Promotion().findElement(By.xpath(String.format("//span[@class='react-inputs-validation__select__optionItem_current_display_name' and text()='Promotion']",Promotion))),Promotion+ " should be displayed",Promotion + " is displayed",Promotion+ " is not displayed");
        Thread.sleep(4000);
//        testStepAssert.isEquals(admin_PromoCodesPage.TextBox_DiscountValue().getAttribute("value"), Value, "Discount Value " +Value +" should be displayed", "Discount Value " + Value +" is displayed", "Discount Value " + Value + " is not displayed" );

        testStepAssert.isEquals(admin_PromoCodesPage.TextBox_PromoCode().getAttribute("value"), expectedNoOfCodes , expectedNoOfCodes  +"should be displayed", expectedNoOfCodes  +" is displayed", expectedNoOfCodes  +" is not displayed" );
        testStepAssert.isEquals(admin_PromoCodesPage.TextBox_PromotionStartDate().getAttribute("value"), PromotionStartDate.toString(),  PromotionStartDate.toString()+" should be displayed",  PromotionStartDate.toString()+" is displayed",  PromotionStartDate.toString()+" is not displayed" );
        testStepAssert.isEquals(admin_PromoCodesPage.TextBox_PromotionExpirationDate().getAttribute("value"), Expires.toString(), Expires.toString()+ " should be displayed", Expires.toString()+" is displayed", Expires.toString() +" is not displayed" );
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @When("^I view the searched promocode$")
    public void i_view_the_searched_promocode() throws Throwable {
        try{
       String xpath = (String) cucumberContextManager.getScenarioContext("XPath");
       Thread.sleep(4000);
        action.click(SetupManager.getDriver().findElement(By.xpath(xpath)).findElement(By.xpath("following-sibling::td[1]")));
        log("I click on View link" ,
                "I have clicked on View link", false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @When("^I click on \"([^\"]*)\" icon$")
    public void i_click_on_something_icon(String button) throws Throwable {

        try {
            switch (button) {
                case "Filter":
                    action.clear(admin_PromoCodesPage.TextBox_Search());
                    action.click(admin_PromoCodesPage.Button_Filter());
                    break;
                case "Close":
                    action.click((admin_ScheduledTripsPage.Button_Close()));
                    break;
                case "Driver Trips":
                    String driver = (String) cucumberContextManager.getScenarioContext("DRIVER");
                    String xpath = String.format("//td[contains(text(),'%s')]/following-sibling::td/div/a/img[@title='Driver Deliveries']", driver);
//                    action.waitUntilIsElementExistsAndDisplayed(admin_DriverPage.Icon_DriverTrips(xpath), (long) 5000);
                    action.click((admin_DriverPage.Icon_DriverTrips(xpath)));
                    break;
                case "Profile":
                    driver=(String) cucumberContextManager.getScenarioContext("DRIVER");
                    xpath= String.format("//td[contains(text(),'%s')]/following-sibling::td[7]/div/a/img[@title='Profile']", driver);
                    action.click((admin_DriverPage.Icon_DriverTrips(xpath)));
            }
            log("I click on " + button + " icon",
                    "I have clicked on " + button + " icon", true);
        }catch (StaleElementReferenceException e) {
            log("I click on " + button + " icon",
                    "I have clicked on " + button + " icon", true);
        }
    }


    @When("^I select \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_select_something_as_something1(String CodeType, String value) throws Throwable {
        try{
        switch (CodeType)
        {
            case "Code Type":
                switch (value) {
                    case "Promo":
                        action.click(admin_PromoCodesPage.CheckBox_FilterPromo());
                        break;
                    case "Referral":
                        action.click(admin_PromoCodesPage.CheckBox_FilterReferral());
                        break;
                    case "One Off":
                        action.click(admin_PromoCodesPage.CheckBox_FilterOneOffByAdmin());
                        break;
                    case "FB Shared":
                        action.click(admin_PromoCodesPage.CheckBox_FilterOneOffFBShare());
                        break;
                    case "Delivery By Partner":
                        action.click(admin_PromoCodesPage.CheckBox_FilterDeliveryChargesByPromoter());
                        break;
                    case "Delivery By Partner (M)":
                        action.click(admin_PromoCodesPage.CheckBox_FilterDeliveryChargesByPromoterMultipleUse());
                        break;
                }
                break;
            case "Active":
            case "Hide Expired":
                action.click(admin_PromoCodesPage.CheckBox_FilterAll());
                action.click(admin_PromoCodesPage.CheckBox_HideExpired());
                break;
        }
        log("I select "+value+" in CodeType "+ CodeType ,
                "I have selected "+value+" in CodeType "+ CodeType, false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @When("^I select promocode type as \"([^\"]*)\"$")
    public void i_select_promocode_type_as_something(String promoCodeType) throws Throwable {
        try{
        Thread.sleep(5000);
        action.click(admin_PromoCodesPage.DropDown_PromoType());
        Thread.sleep(1000);
      // action.selectElementByText(admin_PromoCodesPage.DropDown_PromoType(), promoCodeType);
            Thread.sleep(10000);
           action.click(admin_PromoCodesPage.Select_PromoType(promoCodeType));
        log("I select promocode type as "+ promoCodeType ,
                "I have selected promocode type as "+ promoCodeType, false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @Then("^the \"([^\"]*)\" type promocode gets saved successfully and it is displayed in the Promocodes grid$")
    public void the_something_type_promocode_gets_saved_successfully_and_it_is_displayed_in_the_promocodes_grid(String promocodetype) throws Throwable {

        try{
        String Name = null, Type = null, CreatedDate= null, Expires = "", Code = null, Status ="Active", Value = null, Discount = null,Entered = "0", Used ="0";
        String xpath = null;
        Name = (String)cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
        Type = (String)cucumberContextManager.getScenarioContext("PROMOCODE_TYPE");
        Type = Type.replace("Delivery By Promoter (M)","Delivery Charges By Promoter Multiple Use");
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date today = new Date();
        CreatedDate = dateFormat.format(today).toString();
        switch (promocodetype)
        {
            case "Promo":
                // CreatedDate = dateFormat.format(tomorrow).toString();

                 Expires = (String)cucumberContextManager.getScenarioContext("EXP_DATE");

                 Code =(String)cucumberContextManager.getScenarioContext("PROMOCODE");;
                 Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                 Discount = (cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY").equals("Dollars") ? "$"+Value : Value+"%");
                cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY");

                 xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']",Name, CreatedDate, Expires, Code, Type, Status, Discount, Entered, Used);
                testStepAssert.isElementDisplayed(action.getElementByXPath(xpath),xpath +" Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
                cucumberContextManager.setScenarioContext("XPath",xpath);

                break;

            case "One Off":
                 Code =(String)cucumberContextManager.getScenarioContext("PROMOCODE");;
                 Status ="Active";
                 Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                 Discount = (cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY").equals("Dollars") ? "$"+Value : Value+"%");

                cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY");

                 xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[normalize-space(.)='']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']",Name, CreatedDate, Code, Type, Status, Discount, Entered, Used);
                testStepAssert.isElementDisplayed(action.getElementByXPath(xpath),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
                cucumberContextManager.setScenarioContext("XPath",xpath);

                break;
            case "Delivery By Promoter (M)":
                Code =(String)cucumberContextManager.getScenarioContext("PROMOCODE");;
                Status ="Active";
                Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                Discount = (cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY").equals("Dollars") ? "$"+Value : Value+"%");
                Expires = (String)cucumberContextManager.getScenarioContext("EXP_DATE");
                cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY");
                xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']",Name, CreatedDate, Expires, Type, Status, Discount, Entered, Used);
                testStepAssert.isElementDisplayed(action.getElementByXPath(xpath),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
                cucumberContextManager.setScenarioContext("XPath",xpath);

                break;

        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @Then("^the promocode \"([^\"]*)\" is displayed in the Promocodes grid$")
    public void the_promocode_something_is_displayed_in_the_promocodes_grid(String strArg1) throws Throwable {
        try{
        Thread.sleep(2000);
        String xpath = (String)cucumberContextManager.getScenarioContext("XPath");
        testStepAssert.isElementDisplayed(action.getElementByXPath(xpath),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @Then("^the \"([^\"]*)\" type 5 promocodes gets saved successfully and it is displayed in the Promocodes grid$")
    public void the_something_type_5_promocodes_gets_saved_successfully_and_it_is_displayed_in_the_promocodes_grid(String strArg1) throws Throwable {

        try{
        String Name = null, Type = null, CreatedDate= null, Expires = "", Code = null, Status ="Active", Value = null, Discount = null,Entered = "0", Used ="0";
        String xpath = null;
        String Count = (String) cucumberContextManager.getScenarioContext("CODE_COUNT");
        Name = (String)cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
        Type = (String)cucumberContextManager.getScenarioContext("PROMOCODE_TYPE");
        Expires = (String)cucumberContextManager.getScenarioContext("EXP_DATE");
        Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
        Discount = (cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY").equals("Dollars") ? "$"+Value : Value+"%");
        Date today = new Date();
        Date today1 = new Date(today.getTime());
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
        CreatedDate = dateFormat.format(today).toString();
        Thread.sleep(5000);
//        xpath=String.format(("//tr/td[text()='%s']"),Name);
        xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']",Name, CreatedDate, Type, Status, Discount, Entered, Used);
        Thread.sleep(5000);
        List<WebElement> listOfElements = SetupManager.getDriver().findElements(By.xpath(xpath));
        cucumberContextManager.setScenarioContext("XPath",xpath);
        String LastCode = "";
        for (int i=0; i<listOfElements.size();i++){
            WebElement element = listOfElements.get(i).findElement(By.xpath(".."));
            if(i==listOfElements.size()-1)
//                LastCode = listOfElements.get(i).findElement(By.xpath("//tr//td[1]")).getText();
              LastCode = listOfElements.get(i).findElement(By.xpath("preceding-sibling::td[5]")).getText();
        }
        cucumberContextManager.setScenarioContext("LASTCODE", LastCode);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @Then("^the promocode is displayed in the Promocodes grid$")
    public void the_promocode_is_displayed_in_the_promocodes_grid() throws Throwable {
        try{
        String Name = null, Type = null, CreatedDate = null, Expires = "", Code = null, Status = "Active", Value = null, Discount = null, Entered = "0", Used = "0";
        String xpath = null;

        String Count = (String) cucumberContextManager.getScenarioContext("CODE_COUNT");
        Name = (String) cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
        Type = (String) cucumberContextManager.getScenarioContext("PROMOCODE_TYPE");
        Expires = (String) cucumberContextManager.getScenarioContext("EXP_DATE");
        Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
        Discount = (cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY").equals("Dollars") ? "$" + Value : Value + "%");
        Code = (String) cucumberContextManager.getScenarioContext("LASTCODE");
        Date today = new Date();
        Date today1 = new Date(today.getTime());
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        CreatedDate = dateFormat.format(today1).toString();
        xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']", Name, CreatedDate, Code, Type, Status, Discount, Entered, Used);
        action.waitUntilIsElementExistsAndDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)),30L);
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)), xpath + "Element should be displayed", xpath + "Element is displayed", xpath + "Element is not displayed");
        cucumberContextManager.setScenarioContext("XPath", xpath);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }
    @Then("^the promocode saved data gets populated correctly$")
    public void the_saved_data_gets_populated_correctly() throws Throwable {

        testStepAssert.isElementDisplayed(admin_PromoCodesPage.DropDown_PromoType().findElement(By.xpath("//option[3][@selected='selected' and text()='Delivery By Promoter']")),"Delivery By Promoter should be displayed","Delivery By Promoter is displayed","Delivery By Promoter is not displayed");

    }

    @Then("^the \"([^\"]*)\" and \"([^\"]*)\" is set to \"([^\"]*)\" by default$")
    public void the_something_and_something_is_set_to_something_by_default(String strArg1, String strArg2, String strArg3) throws Throwable {
        Thread.sleep(2000);
        testStepAssert.isTrue(admin_PromoCodesPage.CheckBox_FilterAll().isSelected()," Checkbox Code Type All should be selected","Checkbox Code Type All is selected","Checkbox Code Type All is not selected");
        testStepAssert.isTrue(admin_PromoCodesPage.CheckBox_DateFilterAll().isSelected()," Checkbox Creation Date All should be selected","Checkbox Creation Date All is selected","Checkbox Creation Date All is not selected");
    }

    @Then("^the promocode grid shows the results by type \"([^\"]*)\"$")
    public void the_promocode_grid_shows_the_results_by_type_something(String Type) throws Throwable {
        try{
        Type = Type.replace("Delivery By Partner (M)","Delivery Charges By Partner Multiple Use"); ////////////////
        String xpath = String.format("//tr/td[5][text()='%s']",Type);
        //page 1 records verified
        List<WebElement> rowswithtype = SetupManager.getDriver().findElements(By.xpath(xpath));
        List<WebElement> rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
        testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithtype.size()),Type + " records should be displayed",Type + " records is displayed", Type + " records is not displayed");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }
    @Then("^the promocode grid shows the results by type \"([^\"]*)\" having Code value starting with \"([^\"]*)\"$")
    public void the_promocode_grid_shows_the_results_by_type_something_having_code_value_starting_with_something(String Type, String Value) throws Throwable {
        try{
        String xpath = String.format("//tr/td[contains(text(),'%s')]/following-sibling::td[text()='%s']",Value, Type);
        //page 1 records verified
        List<WebElement> rowswithtype = SetupManager.getDriver().findElements(By.xpath(xpath));
        List<WebElement> rows = SetupManager.getDriver().findElements(By.xpath("//tr"));

        testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithtype.size()),Type + " records should be displayed",Type + " records is displayed", Type + " records is not displayed");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @Then("^the promocode grid shows the only the \"([^\"]*)\" promocodes$")
    public void the_promocode_grid_shows_the_only_the_something_promocodes(String Value) throws Throwable {
        try{
        String xpath = String.format("//tr/td[6][contains(.,'%s')]",Value);
        //page 1 records verified
        List<WebElement> rowswithtype = SetupManager.getDriver().findElements(By.xpath(xpath));
        List<WebElement> rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
        testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithtype.size()),Value + " records should be displayed",Value + " records is displayed", Value + " records is not displayed");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @Then("^the promocode grid shows the both \"([^\"]*)\" promocodes$")
    public void the_promocode_grid_shows_the_both_something_promocodes(String strArg1) throws Throwable {

try{
        String xpath1 = String.format("//tr/td[6][contains(.,'%s')]","Active");
        String xpath2 = String.format("//tr/td[6][contains(.,'%s')]","Expired");
        //page 1 records verified
        List<WebElement> rowswithstatusActive = SetupManager.getDriver().findElements(By.xpath(xpath1));
        List<WebElement> rowswithstatusExpired = SetupManager.getDriver().findElements(By.xpath(xpath2));
        int pageno = 2;
        while (rowswithstatusActive.size() == 0 || rowswithstatusExpired.size() == 0 ){
            action.JavaScriptScrollToBottom();
            action.click(SetupManager.getDriver().findElement(By.xpath("//a[text()='"+String.valueOf(pageno)+"']")));
            //action.click(SetupManager.getDriver().findElement(By.xpath("//span[@class='page-link'][text()='"+String.valueOf(pageno)+"']")));
            pageno=pageno+2;
            Thread.sleep(1000);
            rowswithstatusActive = SetupManager.getDriver().findElements(By.xpath(xpath1));
            rowswithstatusExpired = SetupManager.getDriver().findElements(By.xpath(xpath2));
        }
        int totalValue = rowswithstatusActive.size()+rowswithstatusExpired.size();
        List<WebElement> rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
        testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(totalValue),String.valueOf(rows.size()) + " records should be displayed",String.valueOf(rows.size()) + " records are displayed", String.valueOf(rows.size()) + " records are not displayed");
} catch (Exception e) {
    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    error("Step Should be successful", "Error in viewing result set",
            true);
}
    }

    @Then("^the \"([^\"]*)\" popup gets removed from UI$")
    public void the_something_popup_gets_removed_from_ui(String popup) throws Throwable {
        try{
        switch(popup) {
            case "Add New Promocode":
                Thread.sleep(5000);
                testStepAssert.isNotElementDisplayed(admin_PromoCodesPage.Button_Save(true), popup + " Popup should be hidden", popup +" Popup is hidden", popup+" Popup is not hidden");
                break;

            case "Partners":
                testStepAssert.isElementDisplayed(admin_BusinessUsersPage.Button_Save(), popup + " Popup should be hidden", popup +" Popup is hidden", popup+" Popup is not hidden");
//                testStepAssert.isNotElementDisplayed(admin_BusinessUsersPage.Button_Save(), popup + " Popup should be hidden", popup +" Popup is hidden", popup+" Popup is not hidden");
                break;
              //BOC
            case "Business User Payment":
                testStepAssert.isNotElementDisplayed(admin_BusinessUsersPage.Button_PaymentSave(), popup + " Popup should be hidden", popup +" Popup is hidden", popup+" Popup is not hidden");
                break;
             //EOC
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
        }

    @Then("^the \"([^\"]*)\" message is displayed$")
    public void the_something_message_is_displayed(String message) throws Throwable {
try{
        switch(message) {
            case "Oops! It looks like you missed something. Please fill out all fields before proceeding.":
                testStepAssert.isEquals(action.getText(admin_PromoCodesPage.Label_ErrorContainer()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;
            case "Deliveries have been requested successfully.":
                testStepAssert.isEquals(action.getText(admin_BusinessUsersPage.Label_BulkTripSuccess()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;

            case "Please enter a valid date.":
                testStepAssert.isEquals(action.getText(admin_PromoCodesPage.Label_PromoCodeExpiryDateErrorContainer()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;

            case "Please enter a valid Promo Code Name containing alphanumeric and special characters only":
                testStepAssert.isEquals(action.getText(admin_PromoCodesPage.Label_ErrorContainer()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;

            case "Please enter a valid Code containing alphanumeric and special characters like $,&,#,@,!,%,?,+ only":
                testStepAssert.isEquals(action.getText(admin_PromoCodesPage.Label_PromoCodeExpiryDateErrorContainer()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;
//BOC
            case "No promo codes found.":
                testStepAssert.isEquals(action.getText(admin_PromoCodesPage.Label_NoPromoCodesFound()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;

            case "No data.":
                testStepAssert.isEquals(action.getText(admin_PromoCodesPage.Label_NoPromoCodesFound()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;

            case "No Customers found.":
                message="No Data.";
                testStepAssert.isEquals(action.getText(admin_customerPage.Label_NoCustomerFound()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;
            case "Payment details added successfully for Business User.":
                testStepAssert.isEquals(action.getText(admin_BusinessUsersPage.Label_PaymentMethodSavedMessage()), message,message+ " should be displayed", message + " is displayed", message + " is not displayed");
                break;

            case "This card number is not valid.":
                String xpath=null;
                xpath = String.format("//div[contains(text(),'This card number is not valid.')]");
                testStepAssert.isEquals(action.getText(SetupManager.getDriver().findElement(By.xpath(xpath))),"This card number is not valid.", "This card number is not valid.","The message is listed in grid", "The message is not listed in grid");
                break;

            case "No Partners found.":
                testStepAssert.isEquals(action.getText(admin_BusinessUsersPage.Label_NoBusinessUsersFound()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;

            case " Phone number already exists":
                testStepAssert.isEquals(action.getText(admin_BusinessUsersPage.Label_ErrorContainer()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;
                //EOC
            case "Your changes are good to be saved.":
                String actualMessage=action.getText(admin_potentialPartnersPage.Text_VerifyChangesSavedMessage());
                if(actualMessage.equalsIgnoreCase(message)){
                    testStepAssert.isTrue(true,"Expected message is displayed.","Expected message is not displayed.");
                }
                else {
                    testStepAssert.isFail("Expected message is not displayed.");
                }
                break;

            case "Bungii Saved!":
                Thread.sleep(3000);
                actualMessage=action.getText(admin_potentialPartnersPage.Text_SuccessMessage());
                if(actualMessage.equalsIgnoreCase(message)){
                    testStepAssert.isTrue(true,"Expected message is displayed.","Expected message is not displayed.");
                }
                else {
                    testStepAssert.isFail("Expected message is not displayed.");
                }
                break;
            case "Are you sure, you want to change the payment status?":
                String expectedMessage = action.getText(admin_TripsPage.Text_ChangePaymentStatusMessage());
                if(expectedMessage.equalsIgnoreCase(message)){
                    testStepAssert.isTrue(true,"Expected message is displayed.","Expected message is not displayed.");
                }
                else {
                    testStepAssert.isFail("Expected message is not displayed.");
                }
                testStepAssert.isEquals(action.getText(admin_TripsPage.Text_ChangePaymentStatusMessage()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;
            case "Your pickup is scheduled outside partner working hours.":
                actualMessage=action.getText(admin_EditScheduledBungiiPage.Label_WarningForOutsideBungiiHoursTimeSet());
                if(actualMessage.equals(message)){
                    testStepAssert.isTrue(true,"Expected message '"+message+"' is displayed.","Expected message '"+message+"'is not displayed.");
                }
                else {
                    testStepAssert.isFail("Expected message is not displayed.");
                }
                break;
            case "No data found for the search filter. Click here to clear":
                Thread.sleep(3000);
                actualMessage=action.getText(Page_PartnerManagement_Location.Text_NoDataFound());
                if(actualMessage.equalsIgnoreCase(message)){
                    testStepAssert.isTrue(true,"Expected message '"+message+"' is displayed.","Expected message '"+message+"'is not displayed.");
                }
                else {
                    testStepAssert.isFail("Expected message is not displayed.");
                }
                break;

            case "Please check your information and try again.":
                testStepAssert.isEquals(action.getText(admin_paymentMethodsPage.Label_ErrorContainerPayWithCard()),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "Should contain alphanumeric and -@#$&_: special characters.":
                testStepAssert.isEquals(action.getText(admin_PromoCodesPage.Label_BlankspacCodeName()),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "Invalid login credentials. Your account has been locked.":
                Thread.sleep(4000);
                testStepAssert.isEquals(action.getText(adminLoginPage.Text_AccountBlockedMessage()),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;

        }
} catch (Exception e) {
    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    error("Step Should be successful", "Error in viewing result set",
            true);
}
    }


    @And("^I click on the \"([^\"]*)\" Button on \"([^\"]*)\" popup$")
    public void i_click_on_the_something_button_on_something_popup(String button, String popup) throws Throwable {
        try{
        switch(popup) {
            case "Add New Promocode":
            action.click(admin_PromoCodesPage.Button_Cancel());
            break;
            case "OK":
                action.click(admin_BusinessUsersPage.Button_OK());
            break;
            case "Partner":
//            case "Business Users":
                switch(button) {
                    case "Cancel":
                    action.click(admin_BusinessUsersPage.Button_Cancel());
                    break;
                    case "Save":
                        action.click(admin_BusinessUsersPage.Button_Save());
                        break;
                }
                break;
            case "Add New Partner":
                switch(button) {
                    case "Cancel":
                        action.click(admin_PromoterPage.Button_Cancel());
                        break;
                    case "Save":
                        action.click(admin_PromoterPage.Button_SavePromoter());
                        break;
                }
                break;
            case "Add Event":
                switch(button) {
                    case "Cancel":
                        action.click(admin_PromoterPage.Button_Cancel());
                        break;
                    case "Save":
                        action.click(admin_PromoterPage.Button_SavePromotion());
                        break;
                }
                 break;
            case "Generate Promo Code":
                switch(button) {
                    case "No":
                        action.click(admin_PromoterPage.Button_SavePromotionNo());
                        break;
                    case "Yes":
                        action.JavaScriptClick(admin_PromoterPage.Button_SavePromotionYes());
                      //  action.click(admin_PromoterPage.Button_SavePromotionYes());
                        break;
                }
                break;
                //BOC
            case "Business Users Payment":
                switch(button) {
                    case "Cancel":
                        action.click(admin_BusinessUsersPage.Button_PaymentCancel());
                        break;
                    case "Save":
                        action.JavaScriptClick(admin_BusinessUsersPage.Button_PaymentSave());
                        break;
                }
                break;
                //EOC
            case "Add Partner":
                switch(button) {
                    case "Cancel":
                        action.click(admin_partnerPortalPage.Button_Cancel());
                        break;
                    case "Save":
                        action.click(admin_partnerPortalPage.Button_Save());
                        break;
                }
                break;
        }
        log("I click on "+button+" on "+ popup ,
                "I have clicked on "+button+" on "+ popup, false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }
    @And("^I enter following values in fields$")
    public void i_enter_following_values_in_fields(DataTable data) throws Throwable {

        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            Long now = Instant.now().toEpochMilli();
            int i=now.intValue();
            String Code =  null,  DiscountValue = null,DiscountCategory = null, Promoter= null, Promotion= null,NoOfCodes= null;
            String PromoCodeType = dataMap.get("Promo Code Type").trim();
            String PromoCodeName = dataMap.get("Promo Code Name").trim().replace("<<CurrentDateTime>>",Integer.toString(i).substring(0,9));
            Thread.sleep(10000);
            action.click(admin_PromoCodesPage.DropDown_PromoType());
            action.click(admin_PromoCodesPage.Select_PromoType(PromoCodeType));
            Thread.sleep(10000);
            action.sendKeys(admin_PromoCodesPage.TextBox_PromoCodeName(), PromoCodeName);
            cucumberContextManager.setScenarioContext("PROMOCODE_TYPE", PromoCodeType);
            cucumberContextManager.setScenarioContext("PROMOCODE_NAME", PromoCodeName);


            switch (PromoCodeType) {
                case "Promo":
                    DiscountValue = dataMap.get("Discount Value").trim();DiscountCategory = dataMap.get("Discount Category").trim();
                    Date today = new Date();
                    Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
                    DateFormat dateFormatFetch = new SimpleDateFormat("MMM dd, yyyy");
                    DateFormat dateFormatInput = new SimpleDateFormat("MM/dd/yyyy");
                    String ExpirationDate = dataMap.get("Expiration Date").trim();
                    Code = dataMap.get("Code").trim().replace("<<CurrentDateTime>>",Integer.toString(i));
                    action.sendKeys(admin_PromoCodesPage.TextBox_Code(), Code.substring(0,7));
                    cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", DiscountValue);
                    cucumberContextManager.setScenarioContext("DISCOUNT_CATEGORY", DiscountCategory);
                    cucumberContextManager.setScenarioContext("EXP_DATE", dateFormatFetch.format(tomorrow).toString());
                    cucumberContextManager.setScenarioContext("PROMOCODE", admin_PromoCodesPage.TextBox_Code().getAttribute("value"));
                    action.click(admin_PromoCodesPage.TextBox_DiscountValue());
                    action.clear(admin_PromoCodesPage.TextBox_DiscountValue());
                    admin_PromoCodesPage.TextBox_DiscountValue().sendKeys(Keys.BACK_SPACE);
                    action.sendKeys(admin_PromoCodesPage.TextBox_DiscountValue(), DiscountValue);
                    action.click(admin_PromoCodesPage.RadioButton_Dollars());
                    break;
                case "One Off":
                     DiscountValue = dataMap.get("Discount Value").trim();
                     DiscountCategory = dataMap.get("Discount Category").trim();
                    Code = dataMap.get("Code").trim().replace("<<CurrentDateTime>>",Integer.toString(i));
                    cucumberContextManager.setScenarioContext("EXP_DATE", "");
                    action.sendKeys(admin_PromoCodesPage.TextBox_Code(), Code.substring(0,7));
                    cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", DiscountValue);
                    cucumberContextManager.setScenarioContext("DISCOUNT_CATEGORY", DiscountCategory);
                    cucumberContextManager.setScenarioContext("PROMOCODE", admin_PromoCodesPage.TextBox_Code().getAttribute("value"));
                    action.click(admin_PromoCodesPage.TextBox_DiscountValue());
                    action.clear(admin_PromoCodesPage.TextBox_DiscountValue());
                    admin_PromoCodesPage.TextBox_DiscountValue().sendKeys(Keys.BACK_SPACE);
                    action.sendKeys(admin_PromoCodesPage.TextBox_DiscountValue(), DiscountValue);
                    action.click(admin_PromoCodesPage.RadioButton_Percent());
                    break;
                case "Delivery By Partner":
                    DiscountValue=PropertyUtility.getDataProperties("partner.default.discount.value");
                    Promoter = dataMap.get("Select Promoter").trim();
                    Promotion = dataMap.get("Select Promotion").trim();
                    NoOfCodes = dataMap.get("No Of Codes").trim();
                    action.click(admin_PromoCodesPage.DropDown_Promoter());
                    action.click(admin_PromoCodesPage.DropDown_PromoterOneOff(Promoter));
                    action.clearSendKeys(admin_PromoCodesPage.TextBox_CodeCount(), NoOfCodes);
                    action.click(admin_PromoCodesPage.DropDown_Promotion());
                    action.click(admin_PromoCodesPage.DropDown_PromotionOption(Promotion));
                    DateFormat dateFormatFetch1 = new SimpleDateFormat("MMM dd, yyyy");
                    Date date = new Date(admin_PromoCodesPage.TextBox_PromotionExpirationDate().getAttribute("value"));
                    cucumberContextManager.setScenarioContext("PROMOTER", Promoter);
                    cucumberContextManager.setScenarioContext("PROMOTION", Promotion);
                    cucumberContextManager.setScenarioContext("EXP_DATE", dateFormatFetch1.format(date).toString());
                    cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", DiscountValue);
                    Thread.sleep(2000);
                    cucumberContextManager.setScenarioContext("PROMOTION_STARTDATE", admin_PromoCodesPage.TextBox_PromotionStartDate().getAttribute("value"));
                    cucumberContextManager.setScenarioContext("EXP_DATE_VIEW", admin_PromoCodesPage.TextBox_PromotionExpirationDate().getAttribute("value").toString());
                    cucumberContextManager.setScenarioContext("CODE_COUNT", NoOfCodes);
                    cucumberContextManager.setScenarioContext("DISCOUNT_CATEGORY", (admin_PromoCodesPage.RadioButton_DollarsDisabled().isSelected() ? "Dollars" : "Percent"));
                    break;
                case "Delivery By Partner (M)":
                    DiscountValue=PropertyUtility.getDataProperties("partner.default.discount.value");
                    Promoter = dataMap.get("Select Promoter").trim();
                    Promotion = dataMap.get("Select Promotion").trim();
                    Code = dataMap.get("Code").trim().replace("<<CurrentDateTime>>",Integer.toString(i));
                    action.click(admin_PromoCodesPage.DropDown_Promoter());
                    action.click(admin_PromoCodesPage.DropDown_PromoterOneOff(Promoter));
                    action.click(admin_PromoCodesPage.DropDown_Promotion());
                    action.click(admin_PromoCodesPage.DropDown_PromotionOption(Promotion));
                    Thread.sleep(3000);
                    action.clearSendKeys(admin_PromoCodesPage.TextBox_Code(),Code);
                    DateFormat dateFormatFetch2 = new SimpleDateFormat("MMM dd, yyyy");
                    Date date2 = new Date(admin_PromoCodesPage.TextBox_PromotionExpirationDate().getAttribute("value"));
                    cucumberContextManager.setScenarioContext("PROMOTER", Promoter);
                    cucumberContextManager.setScenarioContext("PROMOTION", Promotion);
                    cucumberContextManager.setScenarioContext("EXP_DATE", dateFormatFetch2.format(date2).toString());
                    cucumberContextManager.setScenarioContext("EXP_DATE_VIEW", admin_PromoCodesPage.TextBox_PromotionExpirationDate().getAttribute("value").toString());
                    cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", DiscountValue);
                    cucumberContextManager.setScenarioContext("PROMOTION_STARTDATE", admin_PromoCodesPage.TextBox_PromotionStartDate().getAttribute("value"));
                    cucumberContextManager.setScenarioContext("PROMOCODE", Code);
                    cucumberContextManager.setScenarioContext("DISCOUNT_CATEGORY", (admin_PromoCodesPage.RadioButton_DollarsDisabled().isSelected() ? "Dollars" : "Percent"));

                    break;
            }
            log("I enter data into Add Promocode popup" ,
                    "I have entered data into Add Promocode popup" , true);
        } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }




    @And("^I uncheck \"([^\"]*)\"$")
    public void i_uncheck_something(String strArg1) throws Throwable {
        try{
            //  if(admin_PromoCodesPage.CheckBox_HideExpired().isSelected())
               action.click(admin_PromoCodesPage.CheckBox_HideExpired());
        log("I uncheck Hide Expired filter" ,
                "I have unchecked Hide Expired filter" , false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @And("^the \"([^\"]*)\" message is displayed for the \"([^\"]*)\" field$")
    public void the_something_message_is_displayed_for_the_something_field(String message, String field) throws Throwable {

        try{
        switch(field)
        {
            case "Select Promoter":
                testStepAssert.isEquals(admin_PromoCodesPage.Label_PromoterErrorContainer().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "No of Codes":
                testStepAssert.isEquals(admin_PromoCodesPage.Label_CountErrorContainer(message).getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "Phone Number":
                testStepAssert.isEquals(admin_BusinessUsersPage.Label_ErrorContainerPhone().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "Email":
                testStepAssert.isEquals(admin_BusinessUsersPage.Label_ErrorContainerEmail().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
//BOC
            case "Card Number":
                switch (message){
                    case "This card number is not valid." :
                        testStepAssert.isEquals(action.getText(admin_paymentMethodsPage.Label_ErrorContainerInvalidCarNumber()),message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                        break;
                    case "Please fill out a card number.":
                        testStepAssert.isEquals(action.getText(admin_BusinessUsersPage.Label_ErrorContainerCarNumber()),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                        break;
                }
                break;

            case "Expiration Date":
                Thread.sleep(4000);
                switch (message){
                    case "This expiration date is not valid." :
                        testStepAssert.isEquals(action.getText(admin_paymentMethodsPage.Label_ErrorContainerInvalidExpiryDate()),message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                        break;
                    case "Please fill out an expiration date.":
                        testStepAssert.isEquals(action.getText(admin_BusinessUsersPage.Label_ErrorContainerExpiryDate()),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                        break;
                }
                break;

            case "CVV":
                switch (message){
                    case "This security code is not valid." :
                        testStepAssert.isEquals(action.getText(admin_paymentMethodsPage.Label_ErrorContainerInvalidCVV()),message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                        break;
                    case "Please fill out a CVV.":
                        testStepAssert.isEquals(admin_BusinessUsersPage.Label_ErrorContainerCVV().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                        break;
                }
                break;

            case "Postal Code":
                testStepAssert.isEquals(admin_BusinessUsersPage.Label_ErrorContainerPostalCode().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
                //EOC
            case "Please check your information and try again.":
                testStepAssert.isEquals(action.getText(admin_paymentMethodsPage.Label_ErrorContainerPayWithCard()),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "This card number is not valid.":
                testStepAssert.isEquals(admin_paymentMethodsPage.Label_ErrorContainerInvalidCarNumber().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "This expiration date is not valid.":
                testStepAssert.isEquals(admin_paymentMethodsPage.Label_ErrorContainerInvalidExpiryDate().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "This security code is not valid.":
                testStepAssert.isEquals(admin_paymentMethodsPage.Label_ErrorContainerInvalidCVV().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
        }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @When("^I enter \"([^\"]*)\" field with below values and click Save$")
    public void i_enter_something_field_with_below_values_and_click_save(String field , DataTable data) throws Throwable {
        try{
            List<Map<String, String>> DataList = data.asMaps();

        int i = 0;
        switch (field) {
            case "No of Codes":

            while (i < DataList.size()) {
                String Value = DataList.get(i).get("Value");
                String Message = DataList.get(i).get("Message");
                i++;

                action.clearSendKeys(admin_PromoCodesPage.TextBox_CodeCount(), Value);
                action.click(admin_PromoCodesPage.Button_Save());
                the_corresponding_message_is_displayed_beside_the_something_field(Message,field);
                log("I enter data into No of Codes field on Add Promocode popup" ,
                        "I have entered data into No of Codes field on Add Promocode popup" , true);
            }
            break;
            case "Code Initials":

                while (i < DataList.size()) {
                    String Value = DataList.get(i).get("Value");
                    String Message = DataList.get(i).get("Message");
                    i++;

                    action.sendKeys(admin_PromoterPage.TextBox_CodeInitials(), Value);
                    action.click(admin_PromoterPage.Button_SavePromoter());
                    the_corresponding_message_is_displayed_beside_the_something_field(Message,field);
                    log("I enter data into Code Initials field on Add Promoter popup" ,
                            "I have entered data into Code Initials field on Add Promoter popup" , true);
                }
                break;
        }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }

    }

    @Then("^the \"([^\"]*)\" message is displayed beside the \"([^\"]*)\" field$")
    public void the_corresponding_message_is_displayed_beside_the_something_field(String message, String field) throws Throwable {
        try{
        switch (field)
        {
            case "respective":
                break;
            case "No of Codes":
          testStepAssert.isEquals(admin_PromoCodesPage.Label_CountErrorContainer(message).getText(),message,message +" should be displayed",message +" is displayed",message +" is not displayed");
       break;
            case "Code Initials":
                testStepAssert.isEquals(admin_PromoterPage.Label_CodeInitialsContainer().getText(),message,message +" should be displayed",message +" is displayed",message +" is not displayed");
                break;
            default:
                testStepAssert.isTrue(false, message + "should be displayed", message + " displayed", message + "did not displayed");
                break;
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }

    }

    @And("^I change the \"([^\"]*)\" to past date$")
    public void i_change_the_something_to_past_date(String ExpiryDate) throws Throwable {
        try{
        String PastExpiryDate= PropertyUtility.getDataProperties("past.expiry.date");

        switch (ExpiryDate)
        {
            case "Expiration Date":
                action.JavaScriptClear(admin_PromoCodesPage.TextBox_PromotionExpirationDate());
                action.clearSendKeys(admin_PromoCodesPage.TextBox_PromotionExpirationDate(),PastExpiryDate +Keys.ENTER);
                break;
        }
        log("I enter expiration date" ,
                "I have entered expiration date" , false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @And("^I edit the Promo Code Name$")
    public void i_edit_the_promo_code_name() throws Throwable {
        try{
        String PromoCodeName=null;
        Long now = Instant.now().toEpochMilli();
        int i=now.intValue();
        PromoCodeName = "EDIT_".trim().concat(Integer.toString(i));
        cucumberContextManager.setScenarioContext("PROMOCODE_NAME", PromoCodeName);
        Thread.sleep(3000);
        action.JavaScriptClear(admin_PromoCodesPage.TextBox_PromoCodeName());
        action.sendKeys(admin_PromoCodesPage.TextBox_PromoCodeName(), PromoCodeName);
        log("I edit the Promo Code name" ,
                "I have edited promo Code name" , false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @Then("^the edited promocode is displayed in the Promocodes grid$")
    public void the_edited_promocode_is_displayed_in_the_promocodes_grid() throws Throwable {
        try{
        String PromoCodeName=cucumberContextManager.getScenarioContext("PROMOCODE_NAME").toString();
        String editFullSentence[]=action.getText(admin_PromoCodesPage.Button_EditPromoCodeView()).split(" ");
        String editedPromoOnly=editFullSentence[0];
        testStepAssert.isEquals(editedPromoOnly, PromoCodeName, PromoCodeName+ " Element should be displayed", editedPromoOnly + " Element is displayed", PromoCodeName + " Element is not displayed");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }


    @And("^I change the \"([^\"]*)\" to future date$")
    public void i_change_the_something_to_future_date(String ExpiryDate) throws Throwable {
        try{
        switch (ExpiryDate)
        {
            case "Expiration Date":
                String Date=utility.GetUniqueFutureDate();
                System.out.println(Date);
//                Date="11/02/2022";
                action.JavaScriptClear(admin_PromoCodesPage.TextBox_PromotionExpirationDate());
                action.clearSendKeys(admin_PromoCodesPage.TextBox_PromotionExpirationDate(),Date +Keys.ENTER);
                cucumberContextManager.setScenarioContext("EXPIRY_DATE", Date);
                break;
        }
        log("I want to change expiration date" ,
                "I have changed expiration date" , false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @Then("^the date gets saved$")
    public void the_date_gets_saved() throws Throwable {
        try{
        String PromoCodeName=cucumberContextManager.getScenarioContext("PROMOCODE_NAME").toString();
        String date=cucumberContextManager.getScenarioContext("EXPIRY_DATE").toString();
        String FromFormat="MM/dd/yyyy", ToFormat="MMM dd, yyyy";
        String date1=utility.GetDateInFormat(date, FromFormat, ToFormat);
        Thread.sleep(5000);
        action.clearSendKeys(admin_PromoCodesPage.TextBox_Search(),""+Keys.ENTER);
        String xpath= String.format("//tr[1]/td[text()='%s']/following-sibling::td[2][contains(text(),'%s')]",PromoCodeName, date1);
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)), xpath + "Element should be displayed", xpath + "Element is displayed", xpath + "Element is not displayed");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    @And("^I enter the following values in fields$")
    public void i_enter_the_following_values_in_fields(DataTable data) throws Throwable {

        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            Long now = Instant.now().toEpochMilli();
            int i = now.intValue();
            String Code = null, DiscountValue = null, DiscountCategory = null, Promoter = null, Promotion = null, NoOfCodes = null;
            String PromoCodeType = dataMap.get("Promo Code Type").trim();
            String PromoCodeName = dataMap.get("Promo Code Name").trim().replace("<<CurrentDateTime>>", Integer.toString(i));
            Thread.sleep(5000);
            action.selectElementByText(admin_PromoCodesPage.DropDown_PromoType(), PromoCodeType);

            action.sendKeys(admin_PromoCodesPage.TextBox_PromoCodeName(), PromoCodeName);

            cucumberContextManager.setScenarioContext("PROMOCODE_TYPE", PromoCodeType);
            cucumberContextManager.setScenarioContext("PROMOCODE_NAME", PromoCodeName);


            switch (PromoCodeType) {
                case "Promo":
                    DiscountValue = dataMap.get("Discount Value").trim();
                    DiscountCategory = dataMap.get("Discount Category").trim();
                    Date today = new Date();
                    Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
                    DateFormat dateFormatFetch = new SimpleDateFormat("MMM dd, yyyy");
                    DateFormat dateFormatInput = new SimpleDateFormat("MM/dd/yyyy");
                    String ExpirationDate = dataMap.get("Expiration Date").trim();
                    Code = utility.GenerateSpecialCharString();
                    action.sendKeys(admin_PromoCodesPage.TextBox_PromoCode(), Code);
                    cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", DiscountValue);
                    cucumberContextManager.setScenarioContext("DISCOUNT_CATEGORY", DiscountCategory);
                    cucumberContextManager.setScenarioContext("EXP_DATE", dateFormatFetch.format(tomorrow).toString());
                    cucumberContextManager.setScenarioContext("PROMOCODE", admin_PromoCodesPage.TextBox_PromoCode().getAttribute("value"));
                    action.click(admin_PromoCodesPage.TextBox_DiscountValue());
                    action.clear(admin_PromoCodesPage.TextBox_DiscountValue());
                    admin_PromoCodesPage.TextBox_DiscountValue().sendKeys(Keys.BACK_SPACE);
                    action.sendKeys(admin_PromoCodesPage.TextBox_DiscountValue(), DiscountValue);
                    action.click(admin_PromoCodesPage.RadioButton_Dollars());
                    action.sendKeys(admin_PromoCodesPage.TextBox_PromotionExpirationDate(), dateFormatInput.format(tomorrow).toString());
                    break;
            }

            log("I want to select "+PromoCodeType ,
                    "I have selected "+PromoCodeType , true);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    //BOC

    @When("^I search by the Code \"([^\"]*)\"$")
    public void i_search_by_the_code_something(String Code) throws Throwable {
        try{
        switch (Code) {
            case "Promo":

            case "@#$@@":
                Thread.sleep(2000);
                action.clearSendKeys(admin_PromoCodesPage.TextBox_Search(), Code + Keys.ENTER);
                break;

            case "Testcustomertywd_apple":
                action.sendKeys(admin_BusinessUsersPage.TextBox_Search(), Code + Keys.ENTER);
                break;
        }
        log("I want to enter "+Code+" value " ,
                "I have enetered  "+Code+" value " , false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @And("^I check if pages exists$")
    public void i_check_if_pages_exists() throws Throwable {
        try{
        Thread.sleep(2000);
        List<WebElement> elements = SetupManager.getDriver().findElements(By.xpath("//ul[@class='pagination pagination-sm']/li/a"));

        for (int i = 0; i < elements.size(); i++) {
            String TextValue = elements.get(i).getAttribute("id");
            if (TextValue.equals("link_Next")) {
                elements.get(i).click();
                break;
            }
        }
        log("I check if pagination exists " ,
                "I have checked if pagination exists" , false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }


    @And("^I check that \"([^\"]*)\" and \"([^\"]*)\" button exists$")
    public void i_check_that_something_and_something_button_exists(String strArg1, String strArg2) throws Throwable {
        testStepVerify.isElementDisplayed(admin_PromoCodesPage.Button_NextPage(),"Next Page should be displayed","Next Page is displayed","Next Page is not displayed");
        testStepVerify.isElementDisplayed(admin_PromoCodesPage.Button_PreviousPage(),"Previous Page should be displayed","Previous Page is displayed","Previous Page is not displayed");
    }

    @Then("^I verify that pagination exists$")
    public void i_verify_that_pagination_exists() throws Throwable {
        try{
        List<WebElement> pagination = SetupManager.getDriver().findElements(By.xpath("//ul[@class='pagination pagination-sm']/li/a"));
        if (pagination.size() > 0) {
            testStepAssert.isTrue(true,"Pagination exists","Error: No pagination found.");
        }
        else{
            testStepAssert.isFalse(false,"Pagination doesnot exists","Error: No pagination found.");
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step Should be successful", "Error in viewing result set",
                true);
    }
    }

    //EOC
}