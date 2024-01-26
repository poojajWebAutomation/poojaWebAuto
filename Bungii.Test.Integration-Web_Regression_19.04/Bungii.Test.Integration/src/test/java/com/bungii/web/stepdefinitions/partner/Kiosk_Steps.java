package com.bungii.web.stepdefinitions.partner;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partner.Kiosk_Page;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static com.bungii.common.manager.ResultManager.log;

public class Kiosk_Steps extends DriverBase {
    ActionManager action = new ActionManager();
    Kiosk_Page Page_Kiosk = new Kiosk_Page();
    GeneralUtility utility = new GeneralUtility();

    @And("^I click on \"([^\"]*)\" Link on Kiosk Partner Portal$")
    public void i_click_on_some_link_on_kiosk_partner_portal(String linkName){
        switch(linkName)
        {
            case "Menu":
                action.click(Page_Kiosk.Link_Setting());
                break;
        }
        log("I click on "+linkName+" Link on Kiosk Partner Portal","I have clicked on "+linkName+" Link on Kiosk Partner Portal", false);

    }

    @And("^I enter \"([^\"]*)\" password for Admin access$")
    public void i_enter_some_password_for_admin_access(String value){
        switch (value)
        {
            case "valid":
                action.clearSendKeys(Page_Kiosk.Textbox_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                break;
            case "invalid"  :
                action.clearSendKeys(Page_Kiosk.Textbox_Password(),PropertyUtility.getDataProperties("Invalid_PartnerPassword"));
                break;

        }
        log("I enter "+value+" password for Admin access","I have entered "+value+" password for Admin access", false);


    }

    @Then("^I should \"([^\"]*)\" on Kiosk Partner Portal$")
    public void i_should_see_admin_access(String value){
        switch (value)
        {
            case "see Admin access":
                testStepVerify.isEquals(action.getText(Page_Kiosk.Text_Admin_Access()), PropertyUtility.getMessage("Kiosk_Admin_Access"));
                break;
            case "see validations message for incorrect password field":
                testStepVerify.isEquals(action.getText(Page_Kiosk.Text_Incorrect_Password()),PropertyUtility.getMessage("Incorrect_Password_Kiosk"));
                break;
            case "see validations message for blank password field"  :
                testStepVerify.isEquals(action.getText(Page_Kiosk.Text_Incorrect_Password()),PropertyUtility.getMessage("Blank_Password_Kiosk"));
                break;
        }
    }

    @And("^I click on \"([^\"]*)\" button on Kiosk Partner Portal$")
    public void i_click_on_some_button_on_kiosk_partner_portal(String value) throws InterruptedException {
        switch(value)
        {
            case "Continue":
                action.click(Page_Kiosk.Button_Continue());
                Thread.sleep(2000);
                break;
        }
        log("I click on "+value+" button on Kiosk Partner Portal","I have clicked on "+value+" button on Kiosk Partner Portal", false);


    }

    @And("^I should logout from Kiosk Partner Portal$")
    public void i_should_logout_from_partner_portal() throws Throwable {
        //action.click(Page_Partner_Done.Dropdown_Setting());
        //Thread.sleep(5000);
        utility.PartnerLogout();

        testStepAssert.isElementDisplayed(action.getElementByXPath("//button[@id='login']"), "SIGN IN button should be displayed on partner portal", "SIGN IN button is displayed on partner portal", "SIGN IN button is not displayed on partner portal");
        //action.getElementByXPath(Page_Partner_Login.Button_Sign_In())
        log("I should be logged out from Partner Portal ","I clicked ", true);
    }

    @And("^I set the Admin Access mode \"([^\"]*)\" on partner portal$")
    public void i_set_the_Admin_Access_mode_something(String value){
        switch (value)
        {
            case "ON":
                action.click(Page_Kiosk.Button_Admin_Access());
                action.click(Page_Kiosk.Link_SettingClose());
                break;
        }
        log("I set the Admin Access mode "+value+" on partner portal","I have set the Admin Access mode "+value+" on partner portal", false);

    }



}
