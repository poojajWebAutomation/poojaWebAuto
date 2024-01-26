package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.SearchingPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class SearchingSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    SearchingPage searchingPage = new SearchingPage();
    ActionManager action = new ActionManager();
    @And("^I wait for SEARCHING screen to disappear$")
    public void i_wait_for_searching_screen_to_disappear() {
        try{
            WaitForSearchingPageDisappear(Integer.parseInt(PropertyUtility.getProp("on.demand.search.time")));
            //Thread.sleep(20000);

            //	testStepVerify.isFalse(isProgressBarVisible(),  "Progress bar should disappear", "Progress bar is not visible", "Progress bar is visible");
            log( "I wait for SEARCHING screen to disappear", "I waited on Searching screen", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }


    /**
     * Wait for searching message to disappear
     * @param searchTime
     * @throws InterruptedException
     */
    public void WaitForSearchingPageDisappear(int searchTime) throws InterruptedException{
        action.hardWait(searchTime+2);
    }

}
