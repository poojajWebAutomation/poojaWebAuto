package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.driver.DriverHomePage;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.android.pages.customer.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class PromosSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(PromosSteps.class);
    ActionManager action = new ActionManager();
    PromosPage promoPage = new PromosPage();
    EstimatePage estimatePage=new EstimatePage();
    GeneralUtility utilities = new GeneralUtility();
    SetPickupTimePage setPickupTimePage = new SetPickupTimePage();
    DriverHomePage driverHomePage = new DriverHomePage();
    @And("^I add \"([^\"]*)\" PromoCode$")
    public void iAddPromoCode(String arg0) throws Throwable {
        try {
            String promoCode = "";
            switch (arg0) {
                case "valid":
                    promoCode = PropertyUtility.getDataProperties("promocode.valid");
                    break;
                case "valid percent":
                    promoCode = PropertyUtility.getDataProperties("promocode.valid.percent");
                    break;
                case "valid one off":
                    promoCode=PropertyUtility.getDataProperties("promocode.one.off");
                    break;
                case "fixed valid":
                    promoCode = PropertyUtility.getDataProperties("promocode.fixedvalid");
                    break;
                case "invalid":
                    promoCode = PropertyUtility.getDataProperties("promocode.invalid");
                    break;
                case "expired":
                    promoCode = PropertyUtility.getDataProperties("promocode.expired");
                    break;
                case "referral":
                    promoCode = PropertyUtility.getDataProperties("referral.code");
                    break;
                case "first time":
                    promoCode = PropertyUtility.getDataProperties("promocode.firsttime");
                    break;
                case "used one off":
                    promoCode = PropertyUtility.getDataProperties("promocode.usedoneoff");
                    break;
                case "promoter type":
                    promoCode = PropertyUtility.getDataProperties("promocode.promotor.off");
                    break;
                case "PROMOTER TYPE PROMO":
                    promoCode=PropertyUtility.getDataProperties("promocode.type.promoter");
                    break;
                case "PROMOTER TYPE PROMO-1":
                    promoCode=PropertyUtility.getDataProperties("promocode.type.promoter1");
                    break;
                case "PROMO DOLLAR OFF":
                    promoCode =PropertyUtility.getDataProperties("promocode.dollar.off");
                    break;
                case "PROMO PERCENT OFF":
                    promoCode = PropertyUtility.getDataProperties("promocode.percent.off");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            action.clearSendKeys(promoPage.Textfield_PromoCode(), promoCode);
            cucumberContextManager.setScenarioContext("ADDED_PROMOCODE", promoCode);
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", promoCode);
            log(" I should able to add " + arg0 + " promo code ",
                    "I entered promo code '" + promoCode + "'", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" promo code should be displayed$")
    public void something_promo_code_should_be_displayed(String strArg1) throws Throwable {
        try {
            String expectedMessage = PropertyUtility.getDataProperties("promocode.valid.percent");
            action.scrollToBottom();
            testStepAssert.isTrue(utilities.isPromoCodePresent(expectedMessage), "Promo code should be added", "Promocode is added", "Promocode is not added");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on Save Money page$")
    public void i_should_see_something_on_save_money_page(String strArg1) throws Throwable {
        try {
            String expectedMessage = "";
            String actualMessage = "";
            switch (strArg1) {
                case "promocode added":
                    expectedMessage = PropertyUtility.getDataProperties("promocode.valid");
                    action.scrollToBottom();action.scrollToBottom();action.scrollToBottom();
                    testStepAssert.isTrue(utilities.isPromoCodePresent(expectedMessage), "Promo code should be added", "Promocode is added", "Promocode is not added");
                    break;
                case "snackbar message for invalid code":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.invalid");
                    actualMessage = utilities.getCustomerSnackBarMessage();
//                    testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(actualMessage, expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + actualMessage + "' message is displayed", "'" + actualMessage + "' message is displayed");
                    break;
                case "snackbar message for expired code":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.expired");
                    actualMessage = utilities.getCustomerSnackBarMessage();

                    //     testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(actualMessage, expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + actualMessage + "' message is displayed", "'" + actualMessage + "' message is displayed");
                    break;
                case "snackbar message for already added code":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.already.existing.code");
                    actualMessage = utilities.getCustomerSnackBarMessage();
                    //        testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(actualMessage, expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + actualMessage + "' message is displayed", "'" + actualMessage + "' message is displayed");
                    break;
                case "snackbar stating referrals are only for new users":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.referral.error");
                  //  actualMessage = action.getText(promoPage.Snackbar());
                    actualMessage = utilities.getCustomerSnackBarMessage();
                    testStepAssert.isEquals(actualMessage, expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + actualMessage + "' message is displayed", "'" + actualMessage + "' message is displayed");
                    break;
                case "snackbar stating first time code is for new users":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.first.time.error.android");
                //    expectedMessage = PropertyUtility.getMessage("customer.promos.first.time.old.user");
                    actualMessage = utilities.getCustomerSnackBarMessage();
                    //    testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(actualMessage, expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + actualMessage + "' message is displayed", "'" + actualMessage + "' message is displayed");
                    break;
                case "snackbar message for used one off code":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.invalid");
                    actualMessage = utilities.getCustomerSnackBarMessage();
                    //    testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(actualMessage, expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    break;
                case "snackbar message stating referral already exists":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.referral.error");
                    actualMessage = utilities.getCustomerSnackBarMessage();
                    //     testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(actualMessage, expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Snackbar message is not displayed : "+strArg1 ,
                    true);
        }
    }

/*    @And("^I tap \"([^\"]*)\" on Save Money page$")
    public void i_tap_something_on_save_money_page(String strArg1) throws Throwable {
        switch (strArg1)
        {
            case "Add":
                action.click(promoPage.Button_Add());
                break;
            case "Get More Money":
                action.click(promoPage.Button_GetMoreMoney());
                break;
            default: break;
        }
        log(" I should able at tap "+strArg1+" on Save Money page", "I clicked on "+strArg1, true);
    }*/

    @And("^I tap \"([^\"]*)\" on Save Money page$")
    public void iTapOnSaveMoneyPage(String arg0) throws Throwable {
        try {
            switch (arg0) {
                case "Add":
                    action.click(promoPage.Button_AddPromoPage());
                    break;
                case "Get More Money":
                    action.click(promoPage.Button_GetMoreMoney());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log(" I should able to tap " + arg0 + " on Save Money page",
                    "I tapped on " + arg0 + " on Save Money Page", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I enter \"([^\"]*)\" promo code$")
    public void i_enter_something_promo_code(String promoCode) throws Throwable {
        try{
            switch (promoCode) {
                case "PROMO1":
                    action.sendKeys(promoPage.Textfield_PromoCode(), promoCode);
                    break;
                default:
                    error("Implemented Step", "UnImplemented Step");
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I click on \"([^\"]*)\" icon$")
    public void i_click_on_something_icon(String icon) throws Throwable {
        try {
            switch (icon) {
                case "i":
                    if(action.isElementPresent(promoPage.Text_First(true)))
                    {
                        action.click(promoPage.Text_First(true));
                    }
                    action.click(promoPage.Image_InfoIcon());
                    break;

                case "i info":
                    action.click(setPickupTimePage.Icon_PickupTimeInfo());
                    break;

                case "$":
                    action.click(driverHomePage.Icon_Referral());
                    break;

                case "Back":
                    action.click(driverHomePage.Button_Back());
                    break;

                case "i earning":
                    action.click(driverHomePage.Icon_Earnings());
                    testStepVerify.isEquals(action.getText(driverHomePage.Text_EarningsInfo()),PropertyUtility.getDataProperties("android.earnings.alert.info"),
                            "Correct alert message should be displayed.",
                            "Correct alert message is displayed.",
                            "Correct alert message is not displayed.");
                    break;

                default:
                    error("Implemented Step", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("^I should see \"([^\"]*)\" on Bungii estimate page$")
    public void i_should_see_something_on_bungii_estimate_page(String strArg1) throws Throwable {
        i_should_see_something_on_save_money_page(strArg1);
    }

    @And("^I tap \"([^\"]*)\" on Promos page$")
    public void i_tap_something_on_promos_page(String strArg1) throws Throwable {
        iTapOnSaveMoneyPage(strArg1);
    }

    @When("^I tap on the \"([^\"]*)\" icon$")
    public void i_tap_on_the_something_icon(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "i":
                    Thread.sleep(3000);
                    action.click(promoPage.Icon_i());
                    break;
                default:
                    error("UnImplemented Step or incorrect icon name", "UnImplemented Step");
                    break;
            }
            log(" I should able to tap " + strArg1 + " icon",
                    "I tapped on " + strArg1 + " icon", false);
        } catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("^The \"([^\"]*)\" is displayed$")
    public void the_something_is_displayed(String info) throws Throwable {
        try{
            switch (info) {
                case "Info Message":
                    testStepVerify.isElementTextEquals(promoPage.Text_InformationMessage(), PropertyUtility.getMessage("promo.codes.info.message"));
                    action.click(promoPage.Button_Ok());
                    break;
                case "This code is only available for your first Bungii.":
                    testStepVerify.isElementTextEquals(promoPage.Text_FirstTimeInfo(), PropertyUtility.getMessage("promo.code.first.time.message"));
                    testStepVerify.isElementTextEquals(promoPage.Text_First(), "FIRST");
                    break;
                case"referral code received with out first time tag":
                    testStepVerify.isTrue(!action.isElementPresent(promoPage.Text_FirstTimeInfo(true)),"'This code is only available for your first Bungii.' should not displayed");
                    testStepVerify.isTrue(!action.isElementPresent(promoPage.Text_First(true)),"First tag should not be displayed");
                    //testStepVerify.isElementTextEquals(promoPage.Text_First(true), "FIRST");
                    break;
                case "Info":
                    testStepVerify.isElementTextEquals(promoPage.Text_InformationMessage(), PropertyUtility.getMessage("customer.promos.first.time.info"));
                    action.click(promoPage.Button_Ok());
                    break;
                default:
                    error("Implemented Step", "UnImplemented Step");
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("^I should see \"([^\"]*)\" message on the Promos page$")
    public void i_should_see_something_message_on_the_promos_page(String strArg1) throws Throwable {
        try {
            String expectedMessage = "";
            String actualMessage = "";
            switch (strArg1) {
                case "Promo code for first Bungii selected by default":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.first.time.info");
                    testStepAssert.isEquals(utilities.getCustomerPromoInfoMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    action.click(promoPage.Button_OK());
                    log("I click OK button on the info popup",
                            "I have clicked OK button on the info popup", true);
                    break;
                case "Promo codes are automatically applied":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.info");
                    testStepAssert.isEquals(utilities.getCustomerPromoInfoMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    action.click(promoPage.Button_OK());
                    log("I click OK button on the info popup",
                            "I have clicked OK button on the info popup", true);
                    break;
                case "First time promo code not used":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.first.time.unused.info");
                    testStepAssert.isEquals(utilities.getCustomerPromoInfoMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    action.click(promoPage.Button_Cancel());
                    log("I click Cancel button on the alert popup",
                            "I have clicked Cancel button on the alert popup", true);
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should able to see expected promo code in available promo code$")
    public void i_should_able_to_see_expected_promo_code_in_available_promo_code() {
        try {
            String usedPromoCode = (String) cucumberContextManager.getScenarioContext("ADDED_PROMO_CODE");

            testStepAssert.isTrue(isPromoCodePresent(usedPromoCode), "I should able to see expected promo code '" + usedPromoCode + "' in available promo code", "I was able to see '" + usedPromoCode + "' in available promo code", "I was not able to see '" + usedPromoCode + "' in available promo code");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * Check if promo code is present in available promocode
     *
     * @param expectedCode Promo code that is to be checked
     * @return boolean value if promocode is present
     */
    public boolean isPromoCodePresent(String expectedCode) {
        boolean isPresent = false;
        List<WebElement> codes = promoPage.List_PromoCode();
        for (WebElement code : codes) {
            if (action.getText(code).contains(expectedCode)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    @And("^I should see the \"([^\"]*)\" PromoCode selected by default$")
    public void i_should_see_the_something_promocode_selected_by_default(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "first time":
                    testStepAssert.isElementDisplayed(promoPage.FirstTime_PromoCode_SelectedByDefault(), "Invite page header should be displayed", "Invite page is displayed", "Invite page is not displayed");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I select \"([^\"]*)\" on the Promos page$")
    public void i_select_something_on_the_promos_page(String strArg1) throws Throwable {
        try {
            Thread.sleep(3000);
            //Richa-changed the locator from PromoCode_R0D1_OnEstimate to PromoCode_R3D5_OnEstimate
            //action.click(promoPage.PromoCode_R0D1());
            action.click(promoPage.PromoCode_R3D5());
            log("I click on the promo Code on Promos page",
                    "I have clicked on the promo Code on Promos page", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the unused promo code$")
    public void i_should_see_the_unused_promo_code() throws Throwable {
        try{
            //Richa-changed the locator from PromoCode_R0D1_OnEstimate to PromoCode_R3D5_OnEstimate
        testStepAssert.isElementDisplayed(promoPage.PromoCode_R3D5(), "Promo code should be displayed", "Promo code is displayed", "Promo code is not displayed");
        log("I click on the promo Code on Promos page",
                "I have clicked on the promo Code on Promos page", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see unused promo code$")
    public void i_should_see_unused_promo_code() throws Throwable {
        try{
            //Richa-changed the locator from PromoCode_R0D1_OnEstimate to PromoCode_R3D5_OnEstimate
            testStepAssert.isElementDisplayed(promoPage.PromoCode_R0D1(), "Promo code should be displayed", "Promo code is displayed", "Promo code is not displayed");
            log("I click on the promo Code on Promos page",
                    "I have clicked on the promo Code on Promos page", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should not see the expired promo code on the Promos page$")
    public void i_should_not_see_the_expired_promo_code_on_the_promos_page() throws Throwable {
        try{
        testStepAssert.isNotElementDisplayed(promoPage.PromoCode_J0W1(true), "Promo code should not be displayed", "Promo code is not displayed", "Promo code is displayed");
        log("I should not see the expired promo code on the Promos page",
                "I did not see the expired promo code on the Promos page", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I select the added promo code$")
    public void i_select_the_added_promo_code() throws Throwable {
        try{
        Thread.sleep(3000);
            //Richa-changed the locator from PromoCode_R0D1_OnEstimate to PromoCode_R3D5_OnEstimate
        action.click(promoPage.PromoCode_R3D5());
        log("I select the added promo code",
                "I select the added promo code", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the previously added promo code present for current Bungii request$")
    public void i_should_see_the_previously_added_promo_code_present_for_current_bungii_request() throws Throwable {
        try{
            //Richa-changed the locator from PromoCode_R0D1_OnEstimate to PromoCode_R3D5_OnEstimate
        testStepAssert.isElementDisplayed(promoPage.PromoCode_R3D5_OnEstimate(), "Promo code should be displayed", "Promo code is displayed", "Promo code is not displayed");
            log("I should see the previously added promo code present for current Bungii request",
                    "I am able to see the previously added promo code present for current Bungii request", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I tap on \"([^\"]*)\" on Estimate screen$")
    public void i_tap_on_something_on_estimate_screen(String strArg1) throws Throwable {
        try{
            switch (strArg1)
            {
                case "Promo code":
                    Thread.sleep(3000);
                    action.click(estimatePage.Link_Promo(true));
                    break;
                case "Promo code value":
                    Thread.sleep(3000);
                    action.click(estimatePage.Link_PromoValue(true));
                    break;
                case "Details":
                    action.click(estimatePage.Button_Details(true));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

}
