package com.bungii.ios.stepdefinitions.other;

import com.bungii.ios.manager.ActionManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.pages.other.FacebookPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class FacebookSteps extends DriverBase {
    FacebookPage facebookpage;
    private static LogUtility logger = new LogUtility(NotificationSteps.class);
    ActionManager action = new ActionManager();
    public FacebookSteps(FacebookPage facebookpage){
        this.facebookpage=facebookpage;
    }


    @When("^I tap \"([^\"]*)\" button on Overlay Facebook screen$")
    public void i_tap_something_button_on_overlay_facebook_screen(String button) throws Throwable {
        try {

            switch (button.toUpperCase()) {
                case "SHARE":
                    action.click(facebookpage.Button_Share());
                    break;
                case "NEXT":
                    action.click(facebookpage.Button_Next());
                    break;
                case "POST":
                    if(action.isElementPresent(facebookpage.Button_Post(true)))
                        action.click(facebookpage.Button_Post());
                    else{
                        action.click(facebookpage.Button_Next());
                        action.click(facebookpage.Button_Share());
                    }

                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should able to tap "+button+" button on Overlay Facebook screen","I clicked on "+button +" on facebook screen",true);
            } catch (Throwable e) {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step  Should be successful",
                        "Error performing step,Please check logs for more details", true);
            }
    }

    @Then("^I should see \"([^\"]*)\" Overlay Facebook screen$")
    public void i_should_see_something_overlay_facebook_screen(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "promo server url":
                    testStepVerify.isElementEnabled(facebookpage.Button_ShareLink(true),"Promo server url should be displayed","Promo service url is displayed","Promo service url is not displayed");
                    break;
                case "popup to post":
                    testStepVerify.isElementEnabled(facebookpage.Text_NavigationBar(),"Header on overlay popup should be facebook" ,"Header on overlay popup is 'facebook'" ,"Header on overlay popup is not facebook");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I enter \"([^\"]*)\" on Overlay Facebook screen$")
    public void i_enter_something_on_overlay_facebook_screen(String identifier) {
        String inputValue="";
        try {
            switch (identifier.toLowerCase()) {
                case "valid data":
                    inputValue=PropertyUtility.getDataProperties("support.text");
                    action.sendKeys(facebookpage.Text_Input(),inputValue);
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should able to enter "+identifier+" on facebook screen","I was able to enter  "+inputValue+" on "+identifier +" on facebook screen",true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Probably facebook app is not installed", true);
        }    }


}
