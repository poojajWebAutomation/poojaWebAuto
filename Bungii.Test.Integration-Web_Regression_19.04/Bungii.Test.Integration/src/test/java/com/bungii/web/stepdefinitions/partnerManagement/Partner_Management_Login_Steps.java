package com.bungii.web.stepdefinitions.partnerManagement;
import static com.bungii.common.manager.ResultManager.log;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partnerManagement.PartnerManagement_LoginPage;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;
import static com.bungii.common.manager.ResultManager.error;

public class Partner_Management_Login_Steps extends DriverBase {
    ActionManager action = new ActionManager();
    PartnerManagement_LoginPage Page_PartnerManagement_Login = new PartnerManagement_LoginPage();
    private static LogUtility logger = new LogUtility(Partner_Management_Login_Steps.class);


    @And("^I add credentials of \"([^\"]*)\"$")
    public void i_add_credentials_of_something(String user) throws Throwable {
        try{
        switch (user){
            case "Invalid user":
                action.clearSendKeys(Page_PartnerManagement_Login.TextBox_PartnerManagementPhone(), PropertyUtility.getDataProperties("partner.management.invalid.user.phone"));
                action.clearSendKeys(Page_PartnerManagement_Login.TextBox_PartnerManagementPassword(),PropertyUtility.getDataProperties("partner.management.invalid.user.password"));
                break;
            case "Valid user":
                action.clearSendKeys(Page_PartnerManagement_Login.TextBox_PartnerManagementPhone(),PropertyUtility.getDataProperties("partner.management.valid.user.phone"));
                action.clearSendKeys(Page_PartnerManagement_Login.TextBox_PartnerManagementPassword(),PropertyUtility.getDataProperties("partner.management.valid.user.password"));
                break;
        }log("I should be able to add credentials of "+user,"I could add credentials of "+user,false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

}
