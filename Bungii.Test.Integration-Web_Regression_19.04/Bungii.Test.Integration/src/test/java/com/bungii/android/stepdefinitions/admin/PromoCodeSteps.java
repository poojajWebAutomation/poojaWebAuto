package com.bungii.android.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.android.pages.admin.PromoCodePage;
import com.bungii.android.stepdefinitions.admin.LogInSteps;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class PromoCodeSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LogInSteps.class);
    PromoCodePage promosPage;

    public PromoCodeSteps(PromoCodePage promosPage) {
        this.promosPage = promosPage;
    }

    @Then("^I get promo code for \"([^\"]*)\"$")
    public void iGetPromoCodeFor(String codeType) {
        try {
            switch (codeType.toLowerCase()) {
                case "referral":
                    cucumberContextManager.setFeatureContextContext("REFERRAL", getPromoCode(codeType));
                    break;
                case "valid":
                    cucumberContextManager.setFeatureContextContext("VALID", getPromoCode(codeType));
                    break;
                case "promo":
                    cucumberContextManager.setFeatureContextContext("PROMO", getPromoCode(codeType));
                    break;
                case "expired":
                    cucumberContextManager.setFeatureContextContext("EXPIRED", getPromoCode(codeType));
                    break;
                case "one off":
                    cucumberContextManager.setFeatureContextContext("ONE_OFF", getPromoCode(codeType));
                    break;
                case "used one off":
                    cucumberContextManager.setFeatureContextContext("USED_ONE_OFF", getPromoCode(codeType));
                    break;
                case "unused one off":
                    cucumberContextManager.setFeatureContextContext("UNUSED_ONE_OFF", getPromoCode(codeType));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should able to get promo code for "+codeType,"I got "+codeType+" from admin website" ,true );
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    /**
     * Find required promocode and return list of it
     *
     * @param key type of promocode that is to be searched
     * @return list of promocode for input category
     */
    public List<String> getPromoCode(String key) {
        List<String> codeList = new ArrayList<String>();
        if (!promosPage.Text_ActivePageNumber().getText().equals("1"))
            promosPage.Button_Previouspage().click();
        while (codeList.size() <= 5) {
            List<WebElement> codes = new ArrayList<WebElement>();
            switch (key.toLowerCase()) {
                case "referral":
                    codes = promosPage.Text_ReferralCode();
                    break;
                case "one off":
                    codes = promosPage.Text_OneOffCode();
                    break;
                case "used one off":
                    codes = promosPage.Text_UsedOneOffCode();
                    break;
                case "unused one off":
                    codes = promosPage.Text_UnUsedOneOffCode();
                    break;
                case "valid":
                case "promo":
                    codes = promosPage.Text_PromoCode();
                    break;
                case "expired":
                    codes = promosPage.Text_ExpiredPromoCode();
                    break;
                default:
                    break;
            }
            for (WebElement code : codes) {
                codeList.add(code.getText());
            }
            promosPage.Button_Nextpage().click();
            promosPage.waitForPageLoad();
            //  action.invisibilityOfElementLocated(promosPage.Loadder());
        }
        logger.detail("Promo code list for key "+key+ " is "+String.join(", ", codeList));
        return codeList;
    }
}
