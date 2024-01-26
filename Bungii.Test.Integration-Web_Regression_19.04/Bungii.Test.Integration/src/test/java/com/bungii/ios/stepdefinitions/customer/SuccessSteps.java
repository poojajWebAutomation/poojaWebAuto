package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.SuccessPage;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class SuccessSteps extends DriverBase {
	private SuccessPage successPage;
	private static LogUtility logger = new LogUtility(SuccessSteps.class);

	public SuccessSteps(SuccessPage successPage) {
		this.successPage =successPage;
	}
	ActionManager action = new ActionManager();
    @Then("^Bungii Posted message should be displayed$")
    public void bungii_posted_message_should_be_displayed()  {
    	try{
    	testStepAssert.isTrue(isPostedMessageDisplayed(),"Bungii Posted message should be displayed","Bungii Posted message is not displayed");
	} catch (Exception e) {
		logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
		error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
	}
    }



	/**
	 * Check if bungii posted message is displayed
	 * @return boolean value if Posted message is displayed
	 */
	public boolean isPostedMessageDisplayed(){
		return successPage.isElementEnabled(successPage.Text_BungiiPosted());
	}


}
