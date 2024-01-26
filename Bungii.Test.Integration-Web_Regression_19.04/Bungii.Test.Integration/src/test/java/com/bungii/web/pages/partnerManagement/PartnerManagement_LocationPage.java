package com.bungii.web.pages.partnerManagement;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;
import java.util.List;

public class PartnerManagement_LocationPage extends PageBase {

    //clear filter button near search bar
    public WebElement Button_ClearFilter() { return findElement("//div[text() =\"Clear filter\"]", LocatorType.XPath); }

    // partners based on alphabets order displayed on partner management
    public List<WebElement> Text_PartnerLength() { return findElements("letter", LocatorType.ClassName); }

    //All available partner location on PM
    public List<WebElement> List_AllPartners() { return findElements("//div[@class =\"menu\"]/div/div[@class=\"menu-item\"]/div[1]", LocatorType.XPath); }

    //Arrow button to go 1 step inside
    public WebElement Link_AccessChildLocation() { return findElement("//div[@class =\"menu\"]/div[2]/div[2]/div[2]", LocatorType.XPath); }

    //to move from child node to parent node
    public WebElement Link_Partners() { return findElement("//span[text()=\"Partners\"]", LocatorType.XPath); }

    //first partner inside parent node
    public WebElement Link_FirstChildPartner() { return findElement("//div[@class=\"menu\"]/div[3]", LocatorType.XPath); }

    //Header h3 level text
    public WebElement Header_MainPageHeaders(String expectedHeader) { return findElement(String.format("//div/h3[text()=\"%s\"]",expectedHeader), LocatorType.XPath);}

    //location name of partner
    public WebElement Text_LocationName() { return findElement("//div/div[@class=\"row\"]/div[1]/div[1]/div/div[2]", LocatorType.XPath);}

   //validation message when no match is found
    public WebElement Text_NoDataFound() { return findElement("//div[@class=\"menu\"]/div", LocatorType.XPath);}

    //To delete the text from the search bar
    public WebElement Link_ClearText() { return findElement("//div[@class=\"sidebar\"]/div[2]/*[local-name()='svg']", LocatorType.XPath);}

    //Location type text of partner
    public WebElement Text_LocationType() { return findElement("//div/div[@class=\"row\"]/div[1]/div[2]/div/div[2]", LocatorType.XPath);}

    //Text in the search bar
    public WebElement Text_HighlightedTextInSearchBar() { return findElement("highlighted", LocatorType.ClassName);}

    //Information from geofence setting
    public WebElement Text_SoloEarliestScheduleTime() { return findElement("//div/div[text()=\"Solo\"]/following-sibling::div[contains(text() ,\"mins\")]", LocatorType.XPath);}
    public WebElement Text_DuoEarliestScheduleTime() { return findElement("//div/div[text()=\"Duo\"]/following-sibling::div[contains(text() ,\"mins\")]", LocatorType.XPath);}
    public WebElement Text_EarliestScheduleTimeLabel() { return findElement("//div[text()=\"Earliest Schedule time:\"]", LocatorType.XPath);}
    public WebElement Text_SoloRowForEarliestScheduleTimeTable() { return findElement("//div[text()=\"Earliest Schedule time:\"]", LocatorType.XPath);}
    public WebElement Text_DuoRowForEarliestScheduleTimeTable() { return findElement("//div[text()=\"Earliest Schedule time:\"]", LocatorType.XPath);}

    //Information from Partner setting
    public WebElement Text_PickupAddressLabel() { return findElement("//span[text()=\"Pickup Address\"]", LocatorType.XPath);}
    public WebElement Text_EmailLabel() { return findElement("//div[text()=\"Email Address (Max 5)\"]", LocatorType.XPath);}
    public WebElement Text_ContactNumberLabel() { return findElement("//div[text()=\"Contact Number\"]", LocatorType.XPath);}
    public WebElement Text_EmailOnPartnerSetting() { return findElement("//div[text()=\"Email Address (Max 5)\"]/following-sibling::div/div/div[1]/a", LocatorType.XPath);}
    public WebElement Text_ContactNumberPartnerSetting() { return findElement("//div[text()=\"Contact Number\"]/following-sibling::div", LocatorType.XPath);}
    public WebElement Text_PickupAddressPartnerSetting() { return findElement("//div[@class=\"location-item\"]/div[2]", LocatorType.XPath);}

    //Information from Partner portal row
    public WebElement Text_DifferentPartnerPortalModes(String mode) { return findElement(String.format("//div[text()=\"%s\"]",mode), LocatorType.XPath);}
    public WebElement Text_SubdomainURLLabel() { return findElement("//div[text()=\"Subdomain URL\"]", LocatorType.XPath);}
    public WebElement Text_SubdomainURL() { return findElement("//div[@class=\"value\"]/a", LocatorType.XPath);}
    public WebElement Text_PartnerPortalNameLabel() { return findElement("//div[text()=\"Partner Portal Name\"]", LocatorType.XPath);}
    public WebElement Text_Text_PartnerPortalName() { return findElement("//div[text()=\"Partner Portal Name\"]/following-sibling::div", LocatorType.XPath);}
    public WebElement Text_StatusLabel() { return findElement("//div[text()=\"Status:\"]", LocatorType.XPath);}
    public WebElement Text_Status() { return findElement("//div[text()=\"Status:\"]/following-sibling::div/button", LocatorType.XPath);}
    public WebElement Image_RedCross() { return findElement("//div[@class=\"pp-item\"][1]/*[local-name()=\"svg\"]", LocatorType.XPath);}

    //Information from customer Interaction
    public WebElement Text_DifferentCustomerInteractions(String mode) { return findElement(String.format("//span[text()=\"%s\"]",mode), LocatorType.XPath);}
    public WebElement Image_GreenCross() { return findElement("//span[text()=\"Label\"]/parent::div/parent::div/*[local-name()=\"svg\"]", LocatorType.XPath);}

    //Information from Trip setting
    public WebElement Text_DifferentTripSetting(String mode) { return findElement(String.format("//div[text()=\"%s\"]",mode), LocatorType.XPath);}
    public WebElement Text_DifferentTripSettingValues(String text) { return findElement(String.format("//div[text()=\"%s\"]/following-sibling::div",text), LocatorType.XPath);}
    public WebElement Text_DifferentTripSettingLabel(String mode) { return findElement(String.format("//span[text()=\"%s\"]",mode), LocatorType.XPath);}
    public WebElement Image_GreenCrossTripSettings() { return findElement("//span[text()=\"Barcode Scan at Pickup\"]/preceding-sibling::*[local-name()=\"svg\"]", LocatorType.XPath);}


}
