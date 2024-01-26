package com.bungii.android.stepdefinitions.Customer;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.otherApps.*;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class InviteSteps extends DriverBase {
    InvitePage invitePage = new InvitePage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    HomePage Page_CustHome = new HomePage();
    private static LogUtility logger = new LogUtility(InviteSteps.class);
    OtherAppsPage otherAppsPage = new OtherAppsPage();
    LocationPage locationPage = new LocationPage();

    @When("^I tap \"([^\"]*)\" on Invite page$")
    public void i_tap_something_on_invite_page(String actionItem) throws Throwable {
        try {
            switch (actionItem) {
                case "Share":
                    Thread.sleep(7000);
                    action.waitUntilIsElementExistsAndDisplayed(invitePage.Button_Share());
                    action.click(invitePage.Button_Share());
                    break;
                case "Share on Facebook":
                    action.longPress(invitePage.Share_Facebook());
                    Thread.sleep(30000);
                    if(!action.isElementPresent(invitePage.FBApp_PostLink(true))){

                       // i_tap_something_on_invite_page("Share");
                        //action.click(invitePage.Share_Facebook());

                    }
                    break;
                case "Share on Twitter":
                    action.click(invitePage.Share_Twitter());
                    break;
                case "Share by Email":
                    action.click(invitePage.Share_Email());
                    break;
                case "Share by Text Message":
                    action.click(invitePage.Share_TextMessage());
                    break;
                case "Back":
                    //action.tap(invitePage.Button_Back());
                    ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log("I should able to tap on" + actionItem, "I tapped on actionItem", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I get Invite Code$")
    public void i_get_invite_code() {
        try {
            String inviteCode = getPromoCode();
            testStepVerify.isTrue(inviteCode.trim().length() > 4,
                    "Invite Code Should  more than 4 letter long", "Invite Code is " + inviteCode,
                    "Invite code is  " + inviteCode + ", less then 4 char");
            cucumberContextManager.setScenarioContext("INVITE_CODE", inviteCode);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on Invite Page$")
    public void i_should_see_something_on_invite_page(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "all elements":
                    testStepAssert.isElementDisplayed(invitePage.Header_Invite(), "Invite page header should be displayed", "Invite page is displayed", "Invite page is not displayed");
                    testStepAssert.isElementTextEquals(invitePage.Invite_Title(), PropertyUtility.getMessage("customer.invite.header"), "Invite title should be displayed ", " Invite page title is displayed correctly", "Invite page title is not correctly displayed");
                    testStepAssert.isElementTextEquals(invitePage.Invite_Text(), PropertyUtility.getMessage("customer.invite.info"), "Invite page info is correctly displayed", "Invite page info is correctly displayed", "Invite page info is not displayed correctly");
                    testStepAssert.isTrue(utility.isAlphaNumeric(action.getText(invitePage.Invite_Code())), "Invite code should be alphanumeric", "Invite code is alphanumeric", "Invite code is not alphanumeric");
                    testStepAssert.isEquals("5", String.valueOf(action.getText(invitePage.Invite_Code()).trim().length()), "Promo code should be of 5 character", "Promo code is of 5 character", "Promo code is not 5 character ");
                    testStepAssert.isElementEnabled(invitePage.Button_Share(), "Share button should be enabled", "Share button is enabled ", "Share button is not enabled");
                    break;
                case "Referral Code":
                    Thread.sleep(5000);
                    action.waitUntilIsElementExistsAndDisplayed(invitePage.Invite_Code());
                    if(action.getText(invitePage.Invite_Code()).equals(""))
                        Thread.sleep(5000);
                    cucumberContextManager.setScenarioContext("ReferralCode", action.getText(invitePage.Invite_Code()));
                    cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE",action.getText(invitePage.Invite_Code()));
                    log("I should able to see referral code", " Referral Code is " + (String) cucumberContextManager.getScenarioContext("ReferralCode"), true);
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

    @And("^I share on \"([^\"]*)\"$")
    public void i_share_on_something(String strArg1) throws Throwable {
        try {

            switch (strArg1) {
                case "Facebook with app installed":
                   /* Thread.sleep(60000);
                    testStepAssert.isElementDisplayed(invitePage.FBApp_PostLink(true), "Overlay post button should be be displayed", "Post button is displayed", "Post button is not displayed");
                    action.sendKeys(invitePage.FBApp_StatusText(), PropertyUtility.getDataProperties("support.text"));
                    Thread.sleep(10000);
                    int retrycount =4;
                    boolean retry = true;
                    while (retry == true && retrycount >0) {
                        try {
                            action.click(invitePage.FBApp_PostLink());
                            Thread.sleep(8000);
                            if(Page_CustHome.TextBox_PickUpTextBox().isDisplayed()==true)
                            {
                                retrycount=0;
                                retry = false;
                            }
                            else
                            {
                                retrycount--;
                                retry = true;
                            }

                        } catch (Exception ex) {
                            retrycount--;
                            retry = true;
                        }
                    }*/
                    testStepAssert.isElementDisplayed(invitePage.FBApp_Policy(), "By clicking continue, you agree to our Terms and Privacy Policy. We use a service that's pre-installed on your device to auto-update apps. You can turn off the service at any time. Learn more.", "Facebook policy is displayed", "Facebook policy is not displayed");
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

    @Then("^I should see post \"([^\"]*)\"$")
    public void i_should_see_post_something(String strArg1) throws Throwable {
        try {
            String referralCode = (String) cucumberContextManager.getScenarioContext("ReferralCode");
            String expectedText = "";
            switch (strArg1) {
                case "on text message app":
                    if (action.isElementPresent(invitePage.TextMsg_TextField(true))) {
                        action.hideKeyboard();
                        expectedText = PropertyUtility.getMessage("customer.invite.sms").replace("{0}", referralCode);
                        testStepVerify.contains(action.getText(invitePage.TextMsg_TextField()), expectedText, " I should able to see properly invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");
                    } else {
                        //send any phone number
                        action.sendKeys(invitePage.Text_Receipient(), "55");
                        ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));
                        action.hideKeyboard();
                        expectedText = PropertyUtility.getMessage("customer.invite.sms").replace("{0}", referralCode);
                        testStepVerify.contains(action.getText(invitePage.Text_Body()), expectedText, " I should able to see properly invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");

                    }

                    break;

                case "on gmail app":
                    action.hideKeyboard();
                    if (action.isElementPresent(invitePage.Gmail_SkipTutorial(true))) {
                        action.click(invitePage.Gmail_SkipTutorial());
                    } else {
                        testStepVerify.isElementTextEquals(invitePage.Gmail_Referral_Subject(), PropertyUtility.getMessage("customer.invite.mailsub"));
                        expectedText = PropertyUtility.getMessage("customer.invite.mailbody").replace("{0}", referralCode);


                        if (action.isElementPresent(invitePage.Gmail_Referral_Body_other(true)))
                            testStepVerify.contains(action.getText(invitePage.Gmail_Referral_Body_other()), expectedText, " I should able to see proper invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");
                        else
                            testStepVerify.contains(action.getText(invitePage.Gmail_Referral_Body()), expectedText, " I should able to see proper invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");
                    }
                    break;

                case "on Twitter in browser":
                    action.hideKeyboard();
                    if (action.isElementPresent(locationPage.Option_Chrome(true))) {
                        action.click(locationPage.Option_Chrome(true));
                        action.click(locationPage.Button_Always());
                    }
                    String url ="twitter.com/intent/tweet?text=Check+out+%40BungiiApp%2C+like+“Uber+for+trucks.”+Use+my+promo+code%2C+"+referralCode+"+for+%2410+off+your+first+trip.+%23UseBungii+https%3A%2F%2Fdjg8x.app.goo";
                    String actual = invitePage.Browser_bar().getText();
                    if(actual.contains(url))
                    testStepAssert.isTrue(true," Application should redirect to Twitter with Referral code ", " Application redirected to Twitter with Referral code : "+ actual, "Application didnt redirect to twitter with Referral code : " + url + actual );
                    else
                        testStepAssert.isTrue(false," Application should redirect to Twitter with Referral code ", " Application redirected to Twitter with Referral code : "+ actual, "Application didnt redirect to twitter with Referral code : " + url + actual );

                    /*expectedText = PropertyUtility.getMessage("customer.invite.twitter.on.browser").replace("{0}", referralCode);
                    if(action.isElementPresent(invitePage.Twitter_Referral_Body(true)))
                    testStepVerify.contains(action.getText(invitePage.Twitter_Referral_Body()), expectedText, " I should able to see proper invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");
                    else if(action.isElementPresent(invitePage.Twitter_SignUP(true))) {
                        action.sendKeys(invitePage.Twitter_PhoneNumber(), "cc.claracooper@gmail.com");
                        action.sendKeys(invitePage.Twitter_Password(), "google2020");action.click(invitePage.Twitter_Login());Thread.sleep(5000);
                        testStepVerify.contains(action.getText(invitePage.Twitter_Referral_BodyChrome()), expectedText, " I should able to see proper invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");
                    }
                        else
                    testStepVerify.contains(action.getText(invitePage.Twitter_Referral_BodyChrome()), expectedText, " I should able to see proper invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");
*/
                    break;

                case "on Facebook app":
                    break;
                case "Tweet Post in Twitter app":
                    action.hideKeyboard();
                    expectedText = PropertyUtility.getMessage("customer.invite.twitter.on.browser").replace("{0}", referralCode);
                    testStepVerify.contains(action.getText(otherAppsPage.Text_TweeterPost()), expectedText, " I should able to see proper invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");
                    action.click(otherAppsPage.Button_Tweet());
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

    /**
     * Return value for promocode
     *
     * @return customer promocode that is to be shared
     */
    public String getPromoCode() {
        return action.getText(invitePage.Invite_Code()).trim();
    }
}
