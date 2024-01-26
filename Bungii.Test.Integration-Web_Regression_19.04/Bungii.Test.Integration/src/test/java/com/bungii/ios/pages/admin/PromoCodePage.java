package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PromoCodePage extends PageBase {

//	@FindBy(how = How.XPATH, using = "//tr/td[.='One Off']/following-sibling::td[1]/span[text()='Active']/parent::td/preceding-sibling::td[2]")
//	public WebElement Text_OneOffCode;


    //  public By Loadder = By.className("link_Next");
    public WebElement Loadder() { return findElement("link_Next", LocatorType.ClassName); };



    public List<WebElement> Text_OneOffCode() {
        return findElements("//tr/td[.='One Off']/following-sibling::td[1]/span[text()='Active']/parent::td/following-sibling::td[3][text()='0']/preceding-sibling::td[5][not(contains(text(),'FBSHARE'))]",LocatorType.XPath);
    //    return findElements("//tr/td[.='One Off']/following-sibling::td[1]/span[text()='Active']/parent::td/preceding-sibling::td[2]", LocatorType.XPath);
    }



    public List<WebElement> Text_UsedOneOffCode() {
        return findElements("//tr/td[.='One Off']/following-sibling::td[1]/span[text()='Active']/parent::td/following-sibling::td[3][.='1']/preceding-sibling::td[5]", LocatorType.XPath);
    }



    public List<WebElement> Text_UnUsedOneOffCode() {
        return findElements("//tr/td[.='One Off']/following-sibling::td[1]/span[text()='Active']/parent::td/following-sibling::td[3][.='0']/preceding-sibling::td[5][not(contains(text(),'FBSHARE'))]", LocatorType.XPath);
    }



    public List<WebElement> Text_PromoCode() {
        return findElements("//tr/td[.='Promo']/following-sibling::td[text()='Active']/preceding-sibling::td[2]", LocatorType.XPath);
    }

    public List<WebElement> Text_PromoCodeFixed() {
        return findElements("//tr/td[.='Promo']/following-sibling::td[1]/span[text()='Active']/parent::td/following-sibling::td[1]/span[contains(text(),'$')]/parent::td/preceding-sibling::td[3]", LocatorType.XPath);
    }
    public List<WebElement> Text_PromoCodePercent() {
        return findElements("//tr/td[.='Promo']/following-sibling::td[1]/span[text()='Active']/parent::td/following-sibling::td[1]/span[contains(text(),'%')]/parent::td/preceding-sibling::td[3]", LocatorType.XPath);
    }

    public List<WebElement> Text_PromoCodePromoter() {
        return findElements("//tr/td[.='Delivery By Promoter']/following-sibling::td[1]/span[text()='Active']/parent::td/following-sibling::td[1]/span[contains(text(),'%')]/parent::td/preceding-sibling::td[3]", LocatorType.XPath);
    }

    public List<WebElement> Text_ReferralCode() {
        return findElements("//tr/td[.='Referral']/following-sibling::td[text()='Active']/preceding-sibling::td[2]", LocatorType.XPath);
    }



    public List<WebElement> Text_ExpiredPromoCode() {
        return findElements("//tr/td[.='Promo']/following-sibling::td[text()='Expired']/preceding-sibling::td[2]", LocatorType.XPath);
    }



    public WebElement Text_ActivePageNumber() {
        return findElement("//*[@class='page-item active']/a", LocatorType.XPath);
    }




    public WebElement Button_Previouspage() {
        return findElement("link_Prev", LocatorType.Id);
    }


    //public WebElement Loadder() { return FindElement("link_Next", LocatorType.ClassName); };

    public WebElement Button_Nextpage() {
        return findElement("link_Next", LocatorType.Id);
    }
    public WebElement Button_Filter() { return findElement("//div[@class='filter']/button", LocatorType.XPath); }

    public WebElement CheckBox_FilterAll() { return findElement("chkCodeTypeFilterAll", LocatorType.Id); }

    public WebElement CheckBox_FilterPromo() { return findElement("Promo", LocatorType.Id); }

    public WebElement CheckBox_FilterReferral() { return findElement("Referral", LocatorType.Id); }

    public WebElement CheckBox_FilterOneOffByAdmin() { return findElement("One Off", LocatorType.Id); }

    public WebElement CheckBox_FilterOneOffFBShare() { return findElement("chkCodeTypeFilterOneOffFBShare", LocatorType.Id); }

    public WebElement CheckBox_FilterDeliveryChargesByPromoter() { return findElement("Delivery By Partner", LocatorType.Id); }
    public WebElement Button_Apply() { return findElement("//button[text()='APPLY']", LocatorType.XPath); }

}
