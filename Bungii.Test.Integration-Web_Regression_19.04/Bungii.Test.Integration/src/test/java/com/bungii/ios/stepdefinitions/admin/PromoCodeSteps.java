package com.bungii.ios.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.ios.pages.admin.PromoCodePage;
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
    ActionManager action = new ActionManager();

    public PromoCodeSteps(PromoCodePage promosPage) {
        this.promosPage = promosPage;
    }

    @Then("^I get promo code for \"([^\"]*)\"$")
    public void iGetPromoCodeFor(String codeType) {
        try {
            action.click(promosPage.Button_Filter());
            switch (codeType.toLowerCase()) {
                case "referral":
                    action.click(promosPage.CheckBox_FilterReferral());
                    action.click(promosPage.Button_Apply());
                    Thread.sleep(2000);
                    cucumberContextManager.setFeatureContextContext("REFERRAL", getPromoCode(codeType));
                    break;
                case "valid":
                    action.click(promosPage.CheckBox_FilterPromo());
                    action.click(promosPage.Button_Apply());
                    Thread.sleep(2000);
                    cucumberContextManager.setFeatureContextContext("VALID", getPromoCode(codeType));
                    break;
                case "promo":
                    action.click(promosPage.CheckBox_FilterPromo());
                    action.click(promosPage.Button_Apply());
                    Thread.sleep(2000);
                    cucumberContextManager.setFeatureContextContext("PROMO", getPromoCode(codeType));
                    break;
                case "expired":
                    action.click(promosPage.CheckBox_FilterPromo());
                    action.click(promosPage.Button_Apply());
                    Thread.sleep(2000);
                    cucumberContextManager.setFeatureContextContext("EXPIRED", getPromoCode(codeType));
                    break;
                case "one off":
                    action.click(promosPage.CheckBox_FilterOneOffByAdmin());
                    action.click(promosPage.Button_Apply());
                    Thread.sleep(2000);
                    cucumberContextManager.setFeatureContextContext("ONE_OFF", getPromoCode(codeType));
                    break;
                case "used one off":
                    action.click(promosPage.CheckBox_FilterOneOffByAdmin());
                    action.click(promosPage.Button_Apply());
                    Thread.sleep(2000);
                    cucumberContextManager.setFeatureContextContext("USED_ONE_OFF", getPromoCode(codeType));
                    break;
                case"{valid one off}":
                case "unused one off":
                    action.click(promosPage.CheckBox_FilterOneOffByAdmin());
                    action.click(promosPage.Button_Apply());
                    Thread.sleep(2000);
                    cucumberContextManager.setFeatureContextContext("UNUSED_ONE_OFF", getPromoCode("unused one off"));
                    break;
                case "{promo fixed}":
                case "promo fixed":
                    action.click(promosPage.CheckBox_FilterPromo());
                    action.click(promosPage.Button_Apply());
                    Thread.sleep(2000);
                    cucumberContextManager.setFeatureContextContext("PROMO_FIXED", getPromoCode("promo fixed"));
                    break;
                case "{promo percent}":
                    action.click(promosPage.CheckBox_FilterPromo());
                    action.click(promosPage.Button_Apply());
                    Thread.sleep(2000);
                    cucumberContextManager.setFeatureContextContext("PROMO_PERCENT", getPromoCode(codeType));
                    break;
                case "promoter_type_promo":
                    action.click(promosPage.CheckBox_FilterDeliveryChargesByPromoter());
                    action.click(promosPage.Button_Apply());
                    Thread.sleep(2000);
                    cucumberContextManager.setFeatureContextContext("PROMOTER_TYPE_PROMO", getPromoCode(codeType));
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
    public List<String> getPromoCode(String key) throws InterruptedException {
        List<String> codeList = new ArrayList<String>();
        //Vishal[12042019]: Temp fixed , Duo to QA _ Auto , TODO: Remove this
       // if (!promosPage.Text_ActivePageNumber().getText().equals("1"))
      //      promosPage.Button_Previouspage().click();
     //   while (codeList.size() <= 5) {

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
                case "promo fixed":
                    codes = promosPage.Text_PromoCodeFixed();
                        break;
                case "{promo percent}":
                    codes = promosPage.Text_PromoCodePercent();
                    break;
                case"promoter_type_promo":
                    codes = promosPage.Text_PromoCodePromoter();
                    break;
                default:
                    break;
            }
            for (WebElement code : codes) {
                codeList.add(code.getText());
            }
            Thread.sleep(1000);
         //   action.click(promosPage.Button_Nextpage());
         //   promosPage.waitForPageLoad();
            //  action.invisibilityOfElementLocated(promosPage.Loadder());
                logger.detail("Promo code list for key "+key+ " is "+String.join(", ", codeList));

        return codeList;
    }

}
