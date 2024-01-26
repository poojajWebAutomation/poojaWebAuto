package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.InvitePage;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;


public class InviteSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(InviteSteps.class);
    ActionManager action = new ActionManager();
    private InvitePage invitePage;

    public InviteSteps(InvitePage invitePage) {
        this.invitePage = invitePage;
    }

    @Then("^Invite Referral page should be properly displayed$")
    public void invite_referral_page_should_be_properly_displayed() {
        try {

            String[] invitePageInfo = getPromoCodeInfo();
            testStepVerify.isEquals(invitePageInfo[0], PropertyUtility.getMessage("customer.invite.header"),
                    "Invite Header should be properly displayed",
                    "Header should be " + PropertyUtility.getMessage("customer.invite.header"), "Expected header is "
                            + PropertyUtility.getMessage("customer.invite.header") + "Actual Header is" + invitePageInfo[0]);
            testStepVerify.isEquals(invitePageInfo[1], PropertyUtility.getMessage("customer.invite.info").replace("â", "’"),
                    "Invite Referral page should be properly displayed", "Invite Info should be properly displayed",
                    "Expected Info is :" + PropertyUtility.getMessage("customer.invite.info") + "Actual Info is :"
                            + invitePageInfo[1]);
            testStepVerify.isTrue(invitePageInfo[2].trim().length() > 4,
                    "Invite Code Should  more than 4 letter long",
                    "Invite Code is" + invitePageInfo[2],
                    "Invite code is  " + invitePageInfo[2] + ", less then 4 char");
            testStepVerify.isEquals(invitePageInfo[3], PropertyUtility.getMessage("customer.invite.underline"),
                    "Invite underline header should be displayed",
                    "Invite underline is" + PropertyUtility.getMessage("customer.invite.underline"), "Expected underline is "
                            + PropertyUtility.getMessage("customer.invite.underline") + " but actual is " + invitePageInfo[3]);
            testStepVerify.isTrue(isShareButtonPresent(),
                    "Share button should be present",
                    "Share button is present", "Share button is not present");
            testStepVerify.isTrue(isInviteReferralIconPresent(),
                    "Dollar image should be present",
                    "Dollar image is present", "Dollar image is not present");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^I get Invite Code$")
    public void i_get_invite_code() {
        try {
            String inviteCode = getPromoCode();
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inviteCode);
            testStepVerify.isTrue(inviteCode.trim().length() > 4,
                    "Invite Code Should  more than 4 letter long", "Invite Code is " + inviteCode,
                    "Invite code is  " + inviteCode + ", less then 4 char");
            cucumberContextManager.setScenarioContext("INVITE_CODE", inviteCode);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see draft post in \"([^\"]*)\" application$")
    public void iShouldSeeDraftPostInApplication(String application) {
        try {
            String messagePost = "";
            String expectedText = "";
            String inviteCode = (String) cucumberContextManager.getScenarioContext("INVITE_CODE");
            Thread.sleep(30000);
            switch (application.toUpperCase()) {
                case "MESSAGE":
                    messagePost = getTextMessageValue();
                    expectedText = PropertyUtility.getMessage("customer.invite.sms").replace("{0}", inviteCode);
                    break;
                case "MAIL":

                    String[] data = getMailMessageValue();
                    messagePost = data[1];
                    expectedText = PropertyUtility.getMessage("customer.invite.mailbody").replace("{0}", inviteCode);
                    testStepVerify.isEquals(data[0], PropertyUtility.getMessage("customer.invite.mailsub"),
                            "Post message subject should be" + PropertyUtility.getMessage("customer.invite.mailsub"),
                            "Post message is" + PropertyUtility.getMessage("customer.invite.mailsub"), "Expected Post message is"
                                    + PropertyUtility.getMessage("customer.invite.mailsub") + ", but actual is " + data[0]);
                    break;
                case "TWITTER":
                    messagePost = getTwitterPostValue();
                    expectedText = PropertyUtility.getMessage("customer.invite.twiiter.post").replace("{0}", inviteCode);
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            testStepVerify.contains(messagePost, expectedText,
                    "Post message should be" + expectedText, "Post message is" + expectedText,
                    "Expected Post message is" + expectedText + ", but actual is" + messagePost);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    /**
     * @return String array for promo code page information
     */
    public String[] getPromoCodeInfo() {
        String[] promoInfo = new String[4];
        promoInfo[0] = action.getValueAttribute(invitePage.Text_InviteHeader());
        promoInfo[1] = action.getValueAttribute(invitePage.Text_InviteInfo());
        promoInfo[2] = getPromoCode();
        promoInfo[3] = action.getValueAttribute(invitePage.Text_PromoCode());
        return promoInfo;
    }

    /**
     * Check if share button is present
     *
     * @return boolean value if share button is present
     */
    public boolean isShareButtonPresent() {
        return invitePage.isElementEnabled(invitePage.Button_Share());
    }

    /**
     * Check if Invite button is present
     *
     * @return boolean value if Invite button is present
     */
    public boolean isInviteReferralIconPresent() {
        return invitePage.isElementEnabled(invitePage.Image_InviteIcon());
    }


    /**
     * Return value for promocode
     *
     * @return customer promocode that is to be shared
     */
    public String getPromoCode() {
        return action.getNameAttribute(invitePage.Button_Code()).trim();
    }


    /**
     * Get SMS text that is auto populated
     *
     * @return Get value of SMS body
     */
    public String getTextMessageValue() {
        String textBody = action.getValueAttribute(invitePage.Text_SMS());
        action.click(invitePage.Button_CancelApp());
        return textBody;
    }

    public String getTwitterPostValue(){
        String textBody = action.getValueAttribute(invitePage.Text_TwitterBody());
        action.click(invitePage.Buttin_Tweet());
        return textBody;
    }
    /**
     * Get Mail body and subject that is auto populated
     *
     * @return Get value of SMS body
     */
    public String[] getMailMessageValue() {
        String[] data = new String[2];
        data[0] = action.getValueAttribute(invitePage.Text_EmailSubject());
        data[1] = action.getValueAttribute(invitePage.Text_EmailBody());
        action.click(invitePage.Button_CancelApp());
        action.click(invitePage.Button_Delete());

        return data;
    }
}
