package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_DashboardPage extends PageBase {

//Header - Dashboard
    public WebElement Header_Dashboard () { return findElement("//body/div/div/div/div/div/h4", LocatorType.XPath); }

    public WebElement SideNavigationSetting () { return findElement("//div[@class='user-panel mb-1 d-flex']", LocatorType.XPath); }

    public WebElement SideNavigationGeneral () { return findElement("//ul[@class='w-100 my-lg-0 flex-column navbar-nav navbar-nav-scroll']", LocatorType.XPath); }

    public WebElement Link_Logout(){return  findElement("//a[contains(text(),'log out')]", LocatorType.XPath);}

    public WebElement Link_DriverDetails(){return  findElement("driver-details", LocatorType.Id);}

    public WebElement Link_DriverBasicInfo(){return  findElement("//li[contains(text(),'Driver Basic Info')]", LocatorType.XPath);}

    public WebElement Link_PickupInfo(){return  findElement("pickup-info", LocatorType.Id);}

    public WebElement Button_Update(){return  findElement("btnUpdate", LocatorType.Id);}

    public WebElement Button_Submit(){return  findElement("btnsend", LocatorType.Id);}

    public WebElement Button_Yes(){return  findElement("btnsendagree", LocatorType.Id);}

    public WebElement Link_RemoveFile1(){return  findElement("//div[@id='dropzone1']/div[2]/a[text()='Remove file']", LocatorType.XPath);}

    public WebElement Link_RemoveFile2(){return  findElement("//div[@id='dropzone1']/div[3]/a[text()='Remove file']]", LocatorType.XPath);}

    public WebElement Link_RemoveFile3(){return  findElement("//div[@id='dropzone1']/div[4]/a[text()='Remove file']", LocatorType.XPath);}

    public WebElement TextBox_DOB(){return  findElement("DateOfBirth", LocatorType.Id);}

    public WebElement Text_BranchWalletCreated() {return findElement("//td[contains(text(),'Payment Account Status')]/following-sibling::td/strong[text()='Wallet Created']", LocatorType.XPath);}

    public WebElement Text_BranchProcessing() {return findElement("//td[contains(text(),'Payment Account Status')]/following-sibling::td/strong[text()='Proccessing']", LocatorType.XPath);}

    public WebElement Text_AccNotCreated() {return findElement("//h4[contains(text(),'Branch Account Details')]/following-sibling::div/strong", LocatorType.XPath);}

    public WebElement Menu_PrivacyPolicy() { return findElement("//a[contains(text(),'Privacy Policy')]", LocatorType.XPath); }

    public WebElement Menu_Terms() { return findElement("//a[contains(text(),'Terms')]", LocatorType.XPath); }

    public WebElement Menu_TermsAndConditions() { return findElement("//a[contains(text(),'Terms & Conditions')]", LocatorType.XPath); }

    public WebElement Heading_DriverAgreement() { return findElement("//h1[contains(text(),'Bungii™ Driver Agreement')]", LocatorType.XPath);}

    public WebElement SubPoint_Definations() { return findElement("//b[contains(text(),'Definitions')]", LocatorType.XPath);}

    public WebElement SubPoint_UseOfBungiiServices() { return findElement("//b[contains(text(),'2. Use of Bungii Services.')]", LocatorType.XPath);}

    public WebElement SubPoint_LocationBasedServices() { return findElement("//b[contains(text(),'3. Location Based Services.')]", LocatorType.XPath);}

    public WebElement SubPoint_Ratings() { return findElement("//b[contains(text(),'4. Ratings.')]", LocatorType.XPath);}

    public WebElement SubPoint_Mobiledevices() { return findElement("//b[contains(text(),'5. Mobile Devices.')]", LocatorType.XPath);}

    public WebElement SubPoint_YouAndYourVehicle() { return findElement("//b[contains(text(),'6. You and Your Vehicle.')]", LocatorType.XPath);}

    public WebElement SubPoint_FinancialTerms() { return findElement("//b[contains(text(),'7. Financial Terms.')]", LocatorType.XPath);}

    public WebElement SubPoint_ProprietaryRightsLicense() { return findElement("//b[contains(text(),'8. Proprietary Rights; License.')]", LocatorType.XPath);}

    public WebElement SubPoint_Confidentiality() { return findElement("//b[contains(text(),'9. Confidentiality.')]", LocatorType.XPath);}

    public WebElement SubPoint_Privacy() { return findElement("//b[contains(text(),'10. Privacy.')]", LocatorType.XPath);}


    public WebElement SubPoint_Insurance() { return findElement("//b[contains(text(),'11. Insurance.')]", LocatorType.XPath);}

    public WebElement SubPoint_RepresentationsAndWarrantiesDisclaimers() { return findElement("//b[contains(text(),'12. Representations and Warranties; Disclaimers.')]", LocatorType.XPath);}

    public WebElement SubPoint_Indemnification() { return findElement("//b[contains(text(),'13. Indemnification.')]", LocatorType.XPath);}

    public WebElement SubPoint_LimitsOfLiability() { return findElement("//b[contains(text(),'14. Limits of Liability.')]", LocatorType.XPath);}

    public WebElement SubPoint_TermAndTermination() { return findElement("//b[contains(text(),'15. Term and Termination.')]", LocatorType.XPath);}

    public WebElement SubPoint_RelationshipOfTheParties() { return findElement("//b[contains(text(),'16. Relationship of the Parties.')]", LocatorType.XPath);}

    public WebElement SubPoint_GoverningLaw() { return findElement("//b[contains(text(),'17. Governing Law; Venue and Jurisdiction; Waiver ')]", LocatorType.XPath);}

    public WebElement SubPoint_DisputeResolutionArbitration() { return findElement("//b[contains(text(),'18. Dispute Resolution; Arbitration.')]", LocatorType.XPath);}

    public WebElement SubPoint_MiscellaneousTerms() { return findElement("//b[contains(text(),'19. Miscellaneous Terms.')]", LocatorType.XPath);}

    public WebElement Heading_PrivacyPolicy() { return findElement("//h1[contains(text(),'Privacy Policy')]", LocatorType.XPath);}

    public WebElement Subpoint_CollectionOfInformation() { return findElement("//h2[contains(text(),'Collection of Information')]", LocatorType.XPath);}

    public WebElement Subpoint_UseOfInformation() { return findElement("//h2[contains(text(),'Use of Information')]", LocatorType.XPath);}

    public WebElement Subpoint_SharingOfInformation() { return findElement("//h2[contains(text(),'Sharing of Information')]", LocatorType.XPath);}

    public WebElement Subpoint_AdvertisingAndAnalyticsServices() { return findElement("//h2[contains(text(),'Advertising & Analytics Services Provided by Other')]", LocatorType.XPath);}

    public WebElement Subpoint_Security() { return findElement("//h2[contains(text(),'Security')]", LocatorType.XPath);}

    public WebElement Subpoint_AgeLimit() { return findElement("//b[contains(text(),'Must be Sixteen (16) Years Old to Use the Services')]", LocatorType.XPath);}

    public WebElement Subpoint_YourChoices() { return findElement("//h2[contains(text(),'Your Choices')]", LocatorType.XPath);}

    public WebElement Subpoint_CaliforniaResidents() { return findElement("//b[contains(text(),'California Residents')]", LocatorType.XPath);}

    public WebElement Subpoint_ChangesToThePrivacyPolicy() { return findElement("//b[contains(text(),'Changes to the Privacy Policy')]", LocatorType.XPath);}

    public WebElement Subpoint_ContactingUs() { return findElement("//strong[contains(text(),'Contacting Us')]", LocatorType.XPath);}

}
