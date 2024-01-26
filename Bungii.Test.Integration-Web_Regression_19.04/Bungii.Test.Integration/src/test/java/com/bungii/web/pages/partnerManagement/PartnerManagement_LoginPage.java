package com.bungii.web.pages.partnerManagement;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class PartnerManagement_LoginPage extends PageBase {

    //Partner Management Phone Field
    public WebElement TextBox_PartnerManagementPhone() { return findElement("//form/div[1]/input", LocatorType.XPath); }

    //Partner Management Password Field
    public WebElement TextBox_PartnerManagementPassword() { return findElement("//form/div[2]/input", LocatorType.XPath); }

    //Button Login in on partner management
    public WebElement Button_Login() { return findElement("//button[text()=\"Login\"]", LocatorType.XPath);}

    //Validation message when incorrect user credentials are added
    public WebElement Text_InvalidCredentialsErrorMessage() { return findElement("//div[text()=\"Invalid credentials.\"]", LocatorType.XPath);}

    //Header h2 level text
    public WebElement Header_MainPage(String expectedHeader) { return findElement(String.format("//div/h2[text()=\"%s\"]",expectedHeader), LocatorType.XPath);}

   //button to log out from partner management
    public WebElement Button_Logout() { return findElement("logout", LocatorType.ClassName);}

    //Bungii icon on top left corner
    public WebElement Image_BungiiLogo() { return findElement("//div[@class=\"sidebar-header\"]/img", LocatorType.XPath);}

    //logged in users name
    public WebElement Text_UserName() { return findElement("//div[@class=\"user\"]/span", LocatorType.XPath);}

    //Logged in users profile pic
    public WebElement Image_UserProfilePic() { return findElement("//div[@class=\"sidebar-header\"]/div/img", LocatorType.XPath);}

    //search bar
    public WebElement Textbox_SearchBox() { return findElement("searchbox", LocatorType.ClassName);}

}
