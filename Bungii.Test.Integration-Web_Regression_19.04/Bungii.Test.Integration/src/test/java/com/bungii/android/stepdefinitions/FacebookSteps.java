package com.bungii.android.stepdefinitions;

import com.bungii.android.pages.otherApps.FacebookPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.android.manager.ActionManager;

import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class FacebookSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(NotificationSteps.class);
    ActionManager action = new ActionManager();
    FacebookPage facebookPage=new FacebookPage();

    @When("^I enter \"([^\"]*)\" on Overlay Facebook screen$")
    public void i_enter_something_on_overlay_facebook_screen(String identifier) {
        String inputValue="";
        try {
            switch (identifier.toLowerCase()) {
                case "valid data":
                    inputValue= PropertyUtility.getDataProperties("support.text");
                    action.sendKeys(facebookPage.Text_Input(),inputValue);
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should able to enter "+identifier+" on facebook screen","I was able to enter  "+inputValue+" on "+identifier +" on facebook screen",true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }    }
}
