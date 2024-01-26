package com.bungii.android.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PromoCodePage extends PageBase {

//	@FindBy(how = How.XPATH, using = "//tr/td[.='One Off']/following-sibling::td[1]/span[text()='Active']/parent::td/preceding-sibling::td[2]")
//	public WebElement Text_OneOffCode;


  //  public By Loadder = By.className("link_Next");
    public WebElement Loadder() { return findElement("link_Next", LocatorType.ClassName); };



    public List<WebElement> Text_OneOffCode() {
        return findElements("//tr/td[.='One Off']/following-sibling::td[1]/span[text()='Active']/parent::td/preceding-sibling::td[2]", LocatorType.XPath);
    }



    public List<WebElement> Text_UsedOneOffCode() {
        return findElements("//tr/td[.='One Off']/following-sibling::td[1]/span[text()='Active']/parent::td/following-sibling::td[3][.='1']/preceding-sibling::td[5]", LocatorType.XPath);
    }



    public List<WebElement> Text_UnUsedOneOffCode() {
        return findElements("//tr/td[.='One Off']/following-sibling::td[1]/span[text()='Active']/parent::td/following-sibling::td[3][.='0']/preceding-sibling::td[5]", LocatorType.XPath);
    }



    public List<WebElement> Text_PromoCode() {
        return findElements("//tr/td[.='Promo']/following-sibling::td[1]/span[text()='Active']/parent::td/preceding-sibling::td[2]", LocatorType.XPath);
    }



    public List<WebElement> Text_ReferralCode() {
        return findElements("//tr/td[.='Referral']/following-sibling::td[1]/span[text()='Active']/parent::td/preceding-sibling::td[2]", LocatorType.XPath);
    }



    public List<WebElement> Text_ExpiredPromoCode() {
        return findElements("//tr/td[.='Promo']/following-sibling::td[1]/span[text()='Expired']/parent::td/preceding-sibling::td[2]", LocatorType.XPath);
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


}
