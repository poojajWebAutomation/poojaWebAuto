package com.bungii.android.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.android.pages.admin.ReferralSourcePage;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;


public class ReferralSourceSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.android.stepdefinitions.admin.ReferralSourceSteps.class);
    ReferralSourcePage referralSourcePage;

    public ReferralSourceSteps(ReferralSourcePage referralSourcePage) {
        this.referralSourcePage = referralSourcePage;
    }

    @Then("^I get Referral Source info for \"([^\"]*)\"$")
    public void iGetReferralSourceInfoFor(String source) {
        try {
            String[] referralInfo = getSourceInfo(source);
            cucumberContextManager.setFeatureContextContext("NUM_ACCOUNT_" + source.toUpperCase().replaceAll(" ", "_"), referralInfo[0]);
            cucumberContextManager.setFeatureContextContext("PERCENTAGE_ACCOUNT_" + source.toUpperCase().replaceAll(" ", "_"), referralInfo[1]);

            if (referralInfo[0] == null) {
                error("I should get Referral Source info for " + source,
                        "I was not able to get Referral Source info for " + source + " Check test data", true);
            } else {
                pass("I should get Referral Source info for " + source,
                        "I got Referral Source info for " + source + ". Number of data account created for source " + source + " is " + referralInfo[0] + " , and percentage of total is " + referralInfo[0], true);
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^account created info for \"([^\"]*)\" should be \"([^\"]*)\"$")
    public void i_account_created_info_for_something_should_be_something(String source, String expected) {
        try {
            Thread.sleep(120000);//TODO: remove this
            String[] referralInfo = getSourceInfo(source);
            String numberOfAccount = (String) cucumberContextManager.getFeatureContextContext("NUM_ACCOUNT_" + source.toUpperCase().replaceAll(" ", "_"));
            String percentageOfAccount = (String) cucumberContextManager.getFeatureContextContext("PERCENTAGE_ACCOUNT_" + source.toUpperCase().replaceAll(" ", "_"));

            switch (expected.toLowerCase()) {
                case "increase by 1":
                    testStepVerify.isTrue(Integer.parseInt(referralInfo[0]) == (Integer.parseInt(numberOfAccount) + 1), "account created info for " + source + " should be :" + referralInfo[0], "Previous account created info for " + source + "was :" + numberOfAccount + " and new is :" + referralInfo[0], "Previous account created info for " + source + "was :" + numberOfAccount + " and new is :" + referralInfo[0]);
                    testStepVerify.isFalse(referralInfo[1].equalsIgnoreCase(percentageOfAccount), "Percentage of account created info for " + source + " should not be :" + percentageOfAccount, "Previous percentege account created info for " + source + "was :" + percentageOfAccount + " and new is :" + referralInfo[1], "Previous percentage account created info for " + source + "was :" + percentageOfAccount + " and new is :" + referralInfo[1]);
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");

            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    /**
     * Get number of user and percentage for given source
     *
     * @param source key for source
     * @return String array containing number of account and percentage
     */
    public String[] getSourceInfo(String source) {
        SetupManager.getDriver().navigate().refresh();
        String[] info = new String[2];
        switch (source.toLowerCase()) {
            case "facebook":
                info[0] = referralSourcePage.Text_NumFacebookAccount().getText();
                info[1] = referralSourcePage.Text_PerFacebookAccount().getText();
                break;
            case "other":
                info[0] = referralSourcePage.Text_NumOtherAccount().getText();
                info[1] = referralSourcePage.Text_PerOtherAccount().getText();
                break;
        }
        return info;
    }

}
