package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.PromosPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.common.manager.ResultManager.log;

public class PromoSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(PromoSteps.class);
    PromosPage promosPage;
    ActionManager action = new ActionManager();

    public PromoSteps(PromosPage promosPage) {
        this.promosPage = promosPage;
    }

    @When("^I add \"([^\"]*)\" PromoCode$")
    public void iAddPromoCode(String key) {
        List<String> codeList = new ArrayList<String>();
        try {
            switch (key.toUpperCase()) {
                case "VALID":
                    codeList = (List<String>) cucumberContextManager.getFeatureContextContext("VALID");
                    break;
                case "{EXPIRED}":
                    codeList = (List<String>) cucumberContextManager.getFeatureContextContext("EXPIRED");
                    break;
                case "{PROMO FIXED}":
                    codeList = (List<String>) cucumberContextManager.getFeatureContextContext("PROMO_FIXED");
                    break;
                case "{PROMO PERCENT}":
                    codeList = (List<String>) cucumberContextManager.getFeatureContextContext("PROMO_PERCENT");
                    break;
                case "{VALID ONE OFF}":
                    codeList = (List<String>) cucumberContextManager.getFeatureContextContext("UNUSED_ONE_OFF");
                    break;
                case "PROMOTER_TYPE_PROMO":
                    codeList = (List<String>) cucumberContextManager.getFeatureContextContext("PROMOTER_TYPE_PROMO");
                    break;
                case "PROMO DOLLAR OFF":
                    codeList = Arrays.asList(PropertyUtility.getDataProperties("promocode.dollar.off"));
                    break;
                case "PROMO PERCENT OFF":
                    codeList = Arrays.asList(PropertyUtility.getDataProperties("promocode.percent.off"));
                    break;
                case "ONE OFF":
                    codeList = Arrays.asList(PropertyUtility.getDataProperties("promocode.one.off.ios"));
                    break;
                case "ONE OFF2":
                    codeList = Arrays.asList(PropertyUtility.getDataProperties("promocode.one.off.ios"));
                    break;
                case "ONE OFF VALID 2":
                    codeList = Arrays.asList(PropertyUtility.getDataProperties("promocode.one.off.ios2"));
                    break;
                case "PROMOTER TYPE PROMO":
                    codeList = Arrays.asList(PropertyUtility.getDataProperties("promocode.promotor.off"));
                    break;
                case "PROMOTER TYPE MULTIPLE PROMO":
                    codeList = Arrays.asList(PropertyUtility.getDataProperties("promocode.promotor.multiple"));
                    break;
                case "FIRST TIME":
                    codeList = Arrays.asList(PropertyUtility.getDataProperties("promocode.first.time"));
                    break;
                case "REFERRAL":
                    codeList = (List<String>)  cucumberContextManager.getFeatureContextContext("ADDED_PROMO_CODE");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }

            String inputCode = addUniquePromoCode(codeList);
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputCode);
            pass("I should able to add unique code", "I added code " + inputCode, true);

            testStepVerify.isFalse(inputCode.equals(""),
                    "I should able to enter unique " + key + "promo code",
                    "I added promocode :" + inputCode,
                    "I was not able to find unique promocode to enter");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I tap \"([^\"]*)\" on Promos screen$")
    public void i_tap_something_on_estimate_screen(String button) throws Throwable {
        try {
            switch (button.toUpperCase()) {
                case "BACK":
                    action.click(promosPage.Button_NavigationBack());
                    break;
                default:
                    fail("Step  Should be successful",
                            "UnImplemented STEP , please verify test step", true);
                    break;
            }
            log("I should able to tap on "+button+" on Estimate screen","I was able to tab on estimate screen",true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error in tapping back button", true);
        }    }

    @Then("^I should see \"([^\"]*)\" on Promos page$")
    public void i_should_see_something_on_promos_page(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "first time code subtext":
                testStepVerify.isTrue(action.isElementPresent(promosPage.Text_FirstTimeSubtext(true)),"'This code is only available for your first Bungii.' should be displayed");
                testStepVerify.isTrue(action.isElementPresent(promosPage.Text_FirstTag(true)),"First tag should be displayed");
                break;
                case"referral code received with out first time tag":
                    testStepVerify.isTrue(!action.isElementPresent(promosPage.Text_FirstTimeSubtext(true)),"'This code is only available for your first Bungii.' should not displayed");
                    testStepVerify.isTrue(!action.isElementPresent(promosPage.Text_FirstTag(true)),"First tag should not be displayed");
                    break;
                default:
                    fail("Step  Should be successful",
                            "UnImplemented STEP , please verify test step", true);
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }      }

    @Then("^I should able to see expected promo code in available promo code$")
    public void i_should_able_to_see_expected_promo_code_in_available_promo_code() {
        try {
            String usedPromoCode = (String) cucumberContextManager.getScenarioContext("ADDED_PROMO_CODE");
            testStepVerify.isTrue(isPromoCodePresent(usedPromoCode), "I should able to see expected promo code '" + usedPromoCode + "' in available promo code", "I was able to see '" + usedPromoCode + "' in available promo code", "I was not able to see '" + usedPromoCode + "' in available promo code");


        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I should able to see facebook promo code in available promo code$")
    public void i_should_able_to_see_facebookexpected_promo_code_in_available_promo_code() {
        try {
            boolean isPresent=false;
            String currentPromo="";
            List<WebElement> codes = promosPage.List_AvailablePromoCode();
            for (WebElement code : codes) {
                currentPromo=action.getValueAttribute(code);
                if (currentPromo.contains("FBSHARE")) {
                    isPresent = true;
                    code.click();
                    break;
                }
            }
            String usedPromoCode=currentPromo.split("-")[1].trim();
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE",usedPromoCode);
            testStepVerify.isTrue(isPresent, "I should able to see expected promo code '" + usedPromoCode + "' in available promo code", "I was able to see '" + usedPromoCode + "' in available promo code", "I was not able to see '" + usedPromoCode + "' in available promo code");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I add Same Promo Code again$")
    public void i_add_same_promo_code_again() {
        try {
            String usedPromoCode = (String) cucumberContextManager.getScenarioContext("ADDED_PROMO_CODE");
            addPromoCode(usedPromoCode);
            testStepAssert.isFalse(usedPromoCode.equals(""),
                    "I should able to add Same Promo Code again",
                    "I added promocode :" + usedPromoCode,
                    "I was not able to find previous promocode to enter");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I click added \"([^\"]*)\" promo code from available promo code$")
    public void i_click_added_something_promo_code_from_available_promo_code(String strArg1) throws Throwable {
        try {
            String addedPromoCode=(String) cucumberContextManager.getScenarioContext("ADDED_PROMO_CODE");
        //    SetupManager.getDriver().findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'- "+addedPromoCode+"')]")).click();
            WebElement webElement= SetupManager.getDriver().findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'- "+addedPromoCode+"')]"));

            cucumberContextManager.setScenarioContext("PROMO_CODE_VALUE",action.getNameAttribute(webElement).split("-")[0].trim());
            action.click(webElement);
            log("I should able to tap on "+addedPromoCode+" on Estimate screen","I was able to tab "+addedPromoCode+" on estimate screen",true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see the previously added promo code present for current Bungii request$")
    public void i_should_see_the_previously_added_promo_code_present_for_current_bungii_request() throws Throwable {
        try {
        String promoCode=(String)cucumberContextManager.getScenarioContext("ADDED_PROMO_CODE");
        testStepVerify.isTrue(isPromoCodePresent(promoCode), "I should able to see expected promo code '" + promoCode + "' in available promo code", "I was able to see '" + promoCode + "' in available promo code", "I was not able to see '" + promoCode + "' in available promo code");
        }
        catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see the \"([^\"]*)\" no more displayed on the promos page$")
    public void i_should_see_the_something_no_more_displayed_on_the_promos_page(String expiredpromocode) throws Throwable {
        try {
            String usedPromoCode = expiredpromocode;
            testStepVerify.isFalse(isPromoCodePresent(usedPromoCode), "I should able to see expected promo code '" + usedPromoCode + "' in available promo code", "I was able to see '" + usedPromoCode + "' in available promo code", "I was not able to see '" + usedPromoCode + "' in available promo code");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * Add given promocode in the field
     *
     * @param newCode Promo code that is to be entered
     */
    public void addPromoCode(String newCode) {
        promosPage.TextBox_EnterCode().clear();
        promosPage.TextBox_EnterCode().sendKeys(newCode);
    }

    /**
     * Click on promo image is present
     *
     * @return boolean value if promo image is present
     */
    public boolean isPromoImagePresent() {
        return promosPage.isElementEnabled(promosPage.Image_Promo());
    }

    /**
     * Add unique promo code
     *
     * @param newCode list of promocode that is fetched from admin site
     * @return unique promocode that is entered
     */
    public String addUniquePromoCode(List<String> newCode) {
        List<String> availableCode = getListOfAvailablePromoCode();
        ArrayList<String> uniques = new ArrayList<String>(newCode);
        String validCode = "";
        for (int i = 0; i<uniques.size();i++)
        {
            String codeFromAdminPortal = uniques.get(0);
            if(availableCode.size()>0) {
                if (!availableCode.contains(codeFromAdminPortal)) {
                    validCode = codeFromAdminPortal;
                    action.clearEnterText(promosPage.TextBox_EnterCode(), validCode);

                } else {
                    validCode = codeFromAdminPortal;
                }
            }
            else
            {
                validCode = codeFromAdminPortal;
                action.clearEnterText(promosPage.TextBox_EnterCode(), validCode);
            }
        }
       /* String validCode = "";
        ArrayList<String> uniques = new ArrayList<String>(newCode);
        if(availableCode.size()>0) {
            if (availableCode.contains(uniques)) {
                uniques.removeAll(availableCode);

                validCode = uniques.get(0);


                action.clearEnterText(promosPage.TextBox_EnterCode(), validCode);

            } else {
                validCode = uniques.get(0);
            }

       */
        return validCode;
    }

    /**
     * Get list of available promo code that is already present on promo page
     *
     * @return
     */
    private List<String> getListOfAvailablePromoCode() {
        List<WebElement> codes = promosPage.List_AvailablePromoCode();
        List<String> availableCodes = new ArrayList<>();
        for (WebElement code : codes) {
            String value = action.getValueAttribute(code);
            value = value.substring(value.indexOf("-") + 2);
            availableCodes.add(value);
        }
        return availableCodes;
    }

    /**
     * Check if promo code is present in available promocode
     *
     * @param expectedCode Promo code that is to be checked
     * @return boolean value if promocode is present
     */
    public boolean isPromoCodePresent(String expectedCode) {
        boolean isPresent = false;
        List<WebElement> codes = promosPage.List_AvailablePromoCode();
        for (WebElement code : codes) {
            if (action.getValueAttribute(code).contains(expectedCode)) {
                isPresent = true;
                code.click(); //newly added code
                break;
            }
        }
        return isPresent;
    }
}
