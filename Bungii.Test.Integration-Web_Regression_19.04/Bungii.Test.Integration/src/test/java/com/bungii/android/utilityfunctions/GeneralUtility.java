package com.bungii.android.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.DashBoardPage;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.customer.ForgotPasswordPage;
import com.bungii.android.pages.customer.LocationPage;
import com.bungii.android.pages.customer.LoginPage;
import com.bungii.android.pages.driver.BungiiCompletedPage;
import com.bungii.android.pages.driver.*;
import com.bungii.android.pages.customer.PrivacyPolicyPage;
import com.bungii.android.pages.otherApps.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.EmailUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.ios.enums.Status;
import com.bungii.web.pages.admin.Admin_DashboardPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.warning;
import static com.google.common.base.Preconditions.checkArgument;
import static java.util.concurrent.TimeUnit.SECONDS;

public class GeneralUtility extends DriverBase {
    static  double MIN_COST = 39;
    private static LogUtility logger = new LogUtility(GeneralUtility.class);
    ActionManager action = new ActionManager();
    LoginPage Page_Login = new LoginPage();
    SignupPage Page_Signup = new SignupPage();
    TermsPage Page_CustTerms = new TermsPage();
    HomePage homePage = new HomePage();
    DriverHomePage driverHomePage = new DriverHomePage();
    BungiiCompletePage customerBungiiCompletePage = new BungiiCompletePage();
    MenuPage Page_Menu = new MenuPage();
    OtherAppsPage otherAppsPage = new OtherAppsPage();
    EstimatePage estimatePage = new EstimatePage();
    AccountPage cutomerAccountPage = new AccountPage();
    PaymentPage paymentPage = new PaymentPage();
    FAQPage faqPage = new FAQPage();
    SupportPage supportPage = new SupportPage();
    PromosPage promosPage = new PromosPage();
    PrivacyPolicyPage privacyPolicyPage = new PrivacyPolicyPage();
    com.bungii.android.pages.driver.LoginPage driverLoginPage = new com.bungii.android.pages.driver.LoginPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    TermsPage termsPage = new TermsPage();
    SearchingPage searchingPage = new SearchingPage();
    InProgressBungiiPages driverBungiiProgressPage = new InProgressBungiiPages();
    BungiiCompletedPage bungiiCompletedPage = new BungiiCompletedPage();
    WantDollar5Page wantDollar5Page = new WantDollar5Page();
    ScheduledBungiisPage scheduledBungiisPage = new ScheduledBungiisPage();
    MyBungiisPage myBungiisPage = new MyBungiisPage();
    InvitePage invitePage = new InvitePage();
    LocationPage locationPage= new LocationPage();
    com.bungii.android.pages.driver.LocationPage driverLocation = new com.bungii.android.pages.driver.LocationPage();
    SetPickupTimePage setPickupTimePage = new SetPickupTimePage();
    DbUtility dbUtility=new DbUtility();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    BungiiProgressPage Page_CustomerBungiiProgress = new BungiiProgressPage();
    AccountsPage accountPage = new AccountsPage();
    DashBoardPage admin_dashboardPage = new DashBoardPage();
    AvailableTripsPage availableTripsPage = new AvailableTripsPage();

    EmailUtility emailUtility = new EmailUtility();
    /**
     * Launch driver application's using package and activity
     *
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    public void launchDriverApplication() throws MalformedURLException, InterruptedException {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyUtility.getProp("implicit.wait")), SECONDS);

/*            //TODO: REMOVE HARD CODING, read from properties
            String appPackage = "com.bungii.driver";
            String appActivity = "com.bungii.ui.activities.splash.SplashScreenActivity";*/
            String appPackage = PropertyUtility.getProp("bundleId_Driver");
            String appActivity = PropertyUtility.getProp("driver.initial.activity");
            Activity activity = new Activity(appPackage, appActivity);
            activity.setStopApp(false);
            ((AndroidDriver<MobileElement>) driver).startActivity(activity);
            //   Thread.sleep(3000);
        } catch (Exception e) {

        }
    }

    /**
     * Launch customer application's using package and activity
     *
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    public void launchCustomerApplication() throws MalformedURLException, InterruptedException {
        try {
            AndroidDriver<AndroidElement> driver = (AndroidDriver<AndroidElement>) SetupManager.getDriver();
/*
        //TODO: REMOVE HARD CODING, read from properties
        String appPackage = "com.bungii.customer";
        String appActivity = "com.bungii.ui.activities.splash.SplashScreenActivity";
*/
            String appPackage = PropertyUtility.getProp("bundleId_Customer");
            String appActivity = PropertyUtility.getProp("customer.initial.activity");

            Activity activity = new Activity(appPackage, appActivity);
            activity.setStopApp(false);
            ((AndroidDriver<AndroidElement>) driver).startActivity(activity);
            driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyUtility.getProp("implicit.wait")), SECONDS);
            Thread.sleep(3000);
        } catch (Exception e) {
        }

    }

    /**
     * Check if customer application is open . check if there is application open which has element that contains customer app resource id
     *
     * @return
     * @throws InterruptedException
     */
    public boolean isCustomerApplicationOpen() throws InterruptedException {
        //new method introduced in recent appium version to check application state
        //  if( (((AndroidDriver)SetupManager.getDriver()).queryAppState("com.bungii.customer")).equals(ApplicationState.RUNNING_IN_FOREGROUND))
        //     return true;
        if (action.isElementPresent(homePage.Generic_Element(true)))
            return true;
        else {
            // Thread.sleep(5000);
            //logger.detail(SetupManager.getDriver().getPageSource());
            return false;
            // return action.isElementPresent(homePage.Generic_Element(true));
        }
    }

    /**
     * Check if driver application is open . check if there is application open which has element that contains driver app resource id
     *
     * @return
     * @throws InterruptedException
     */
    public boolean isDriverApplicationOpen() throws InterruptedException {
        //new method introduced in recent appium version to check application state
        //  if( (((AndroidDriver)SetupManager.getDriver()).queryAppState("com.bungii.driver")).equals(ApplicationState.RUNNING_IN_FOREGROUND))
        //      return true;
        if (action.isElementPresent(driverHomePage.Generic_Element(true)))
            return true;
        else {
            //logger.detail(SetupManager.getDriver().getPageSource());
            //Thread.sleep(5000);
            return false;
            //return action.isElementPresent(driverHomePage.Generic_Element(true));
        }
    }

    /**
     * Check if correct page is open
     *
     * @param p0 identifier for page
     * @return
     */
    public boolean isCorrectPage(String p0) throws InterruptedException {
        boolean isCorrectPage = false;
        switch (p0) {
            case "FAQ":
                isCorrectPage = action.isElementPresent(faqPage.Header_FAQPage(true));
                break;
            case "Account":
                isCorrectPage = action.isElementPresent(cutomerAccountPage.Header_AccountPage(true));
                break;
            case "ACCOUNT INFO":
                isCorrectPage = action.isElementPresent(cutomerAccountPage.Header_AccountInfoPage(true));
                break;
            case "Delete account":
                Thread.sleep(2000);
                isCorrectPage = action.isElementPresent(cutomerAccountPage.Header_DeleteAccount(true));
                if(isCorrectPage){
                    testStepAssert.isElementTextEquals(cutomerAccountPage.Text_PasswordToConfirm(),PropertyUtility.getMessage("customer.account.deleted.confirm"),PropertyUtility.getMessage("customer.account.deleted.confirm") + "should be display.",PropertyUtility.getMessage("customer.account.deleted.confirm") + "is displayed.",PropertyUtility.getMessage("customer.account.deleted.confirm") + " is not displayed.");
                    testStepAssert.isElementTextEquals(cutomerAccountPage.Text_ActionCannotUndone(),PropertyUtility.getMessage("customer.account.deleted.undone"),PropertyUtility.getMessage("customer.account.deleted.undone") + "should be display.",PropertyUtility.getMessage("customer.account.deleted.undone") + "text is displayed.",PropertyUtility.getMessage("customer.account.deleted.undone") + "text is not displayed.");
                }
                break;
            case "MY BUNGIIS":
                isCorrectPage = action.isElementPresent(scheduledBungiisPage.Title_ScheduledBungiis());
                break;
            case "Payment":
                isCorrectPage = action.isElementPresent(paymentPage.Header_PaymentPage(true));
                break;
            case "Support":
                isCorrectPage = action.isElementPresent(supportPage.Header_SupportPage(true));
                break;
            case "Promos":
                isCorrectPage = action.isElementPresent(promosPage.Header_SavePage(true));
                break;
            case "Privacy Policy":
                isCorrectPage = action.isElementPresent(privacyPolicyPage.Header_PrivacyPolicyPage(true));
                action.isElementPresent(privacyPolicyPage.Text_Privacy(true));
                break;
            case "Home":
                isCorrectPage = action.isElementPresent(homePage.Header_HomePage(true));
                break;
            case "Estimate":
                isCorrectPage = action.isElementPresent(estimatePage.Header_Estimate(true));
                break;
            case "Login":
            case "Logout":
                isCorrectPage = action.isElementPresent(Page_Login.Header_LoginPage(true));
                break;
            case "Signup":
                isCorrectPage = action.isElementPresent(Page_Signup.Header_SignUp(true));
                break;
            case "Terms and Conditions":
                isCorrectPage = action.isElementPresent(termsPage.Header_TermsPage(true));
                break;
            case "Tutorial":
                isCorrectPage = action.isElementPresent(homePage.Text_TutorialPdf());
                // isCorrectPage=action.getText(homePage.Text_TutorialHeader()).equals(PropertyUtility.getMessage("customer.tutorial.header"));
                break;
            /*case "DRIVER NOT AVAILABLE":
                isCorrectPage = action.isElementPresent(searchingPage.Header_DriverNotAvailable(true));
                break;*/
            case "bungii.com":
                if (!action.isElementPresent(otherAppsPage.Text_ChromeUrl(true)))                  
Thread.sleep(5000);
                isCorrectPage = action.isElementPresent(otherAppsPage.Text_ChromeUrl(true)) && action.getText(otherAppsPage.Text_ChromeUrl()).contains("bungii.com/drive");
                break;
            case "Enroute screen":
                Thread.sleep(5000);
                    String currentPage = action.getText(driverBungiiProgressPage.Title_Status_Generic(true));
                    if (!currentPage.equalsIgnoreCase(""))
                        isCorrectPage = action.getText(driverBungiiProgressPage.Title_Status_Generic()).equals(Status.EN_ROUTE.toString());
                    else
                        isCorrectPage = action.getText(driverBungiiProgressPage.Title_Status_Generic_Alt()).equals(Status.EN_ROUTE.toString());
                    break;
                case "Arrived screen":
                    Thread.sleep(5000);
                    if (!action.getText(driverBungiiProgressPage.Title_Status_Generic(true)).equalsIgnoreCase(""))
                        isCorrectPage = action.getText(driverBungiiProgressPage.Title_Status_Generic()).equals(Status.ARRIVED.toString());
                    else
                        isCorrectPage = action.getText(driverBungiiProgressPage.Title_Status_Generic_Alt()).equals(Status.ARRIVED.toString());
                    break;
                case "Loading Item screen":
		
		                Thread.sleep(5000);
                if (!action.getText(driverBungiiProgressPage.Title_Status_Generic(true)).equalsIgnoreCase(""))
                    isCorrectPage = action.getText(driverBungiiProgressPage.Title_Status_Generic()).equals(Status.LOADING_ITEM.toString());
                else
                    isCorrectPage = action.getText(driverBungiiProgressPage.Title_Status_Generic_Alt()).equals(Status.LOADING_ITEM.toString());
                break;
            case "Driving to DropOff screen":
                Thread.sleep(5000);
                if (!action.getText(driverBungiiProgressPage.Title_Status_Generic(true)).equalsIgnoreCase(""))
                    isCorrectPage = action.getText(driverBungiiProgressPage.Title_Status_Generic()).equals(Status.DRIVING_TO_DROP_OFF.toString());
                else
                    isCorrectPage = action.getText(driverBungiiProgressPage.Title_Status_Generic_Alt()).equals(Status.DRIVING_TO_DROP_OFF.toString());
                break;
            case "Unloading Item screen":
                Thread.sleep(5000);
                if (!action.getText(driverBungiiProgressPage.Title_Status_Generic(true)).equalsIgnoreCase(""))
                    isCorrectPage = action.getText(driverBungiiProgressPage.Title_Status_Generic()).equals(Status.UNLOADING_ITEM.toString());
                else
                    isCorrectPage = action.getText(driverBungiiProgressPage.Title_Status_Generic_Alt()).equals(Status.UNLOADING_ITEM.toString());
                break;
            case "INVITE":
                isCorrectPage = action.isElementPresent(invitePage.Header_Invite());
                break;
            case "SCHEDULED BUNGII":
                isCorrectPage=action.isElementPresent(driverHomePage.Text_ScheduledBungiiSolo(true));
                break;
            case "SCHEDULED BUNGIIS":
                isCorrectPage=action.isElementPresent(driverHomePage.Text_ScheduledBungiisSolo(true));
                break;
            case "LOCATION":
                isCorrectPage = action.getText(locationPage.Header_Location()).equals("LOCATION");
                break;
            case"DRIVER's LOCATION":
                isCorrectPage = action.getText(driverLocation.Header_Location()).equals("LOCATION");
                break;
                case "SET PICKUP TIME":
                    isCorrectPage = action.isElementPresent(searchingPage.Header_DriverNotAvailable(true));
                    break;

                case "Bungii Completed":
                    isCorrectPage= action.isElementPresent(driverHomePage.Text_BungiiCompleted(true));
                    break;

                case "BUNGII COMPLETE":
                    isCorrectPage=action.isElementPresent(customerBungiiCompletePage.PageTitle_BungiiCompleteGeneric());
                    break;
                case "BUNGII DETAILS":
                    isCorrectPage=action.isElementPresent(availableTripsPage.PageTitle_BungiiDetails());
                    break;

                case "EARNINGS":
                    isCorrectPage=action.isElementPresent(myBungiisPage.Header_Earnings());
                    break;

            case "Rate duo teammate":
                isCorrectPage=action.isElementPresent(myBungiisPage.Header_RateDuoTeammate());
                break;
            case "REFERRAL":
                isCorrectPage=action.isElementPresent(myBungiisPage.Header_ReferralPage());
                break;
            case "REFERRAL HISTORY":
                isCorrectPage=action.isElementPresent(myBungiisPage.Header_ReferralHistory());
                break;

                default:
                    String expectedMessage = p0;
                    try {
                        if (!action.isElementPresent(driverHomePage.Generic_HeaderElement(true))) {
                            Thread.sleep(9000);
                        }


 			} catch (InterruptedException e) {
                   		 e.printStackTrace();
               		 }
                    action.textToBePresentInElementText(driverHomePage.Generic_HeaderElement(), expectedMessage);
                    isCorrectPage = action.getText(driverHomePage.Generic_HeaderElement()).equals(expectedMessage);

                    if (!isCorrectPage) {
                        action.textToBePresentInElementText(driverHomePage.Generic_HeaderElement(), expectedMessage);
                        isCorrectPage = action.getText(driverHomePage.Generic_HeaderElement()).equals(expectedMessage);


                }
            break;

        }
        return isCorrectPage;
    }

    public void resetApp() {
        ((AndroidDriver) SetupManager.getDriver()).resetApp();
    }
    public boolean installCustomerApp() {
        boolean isInstalled = false;
        try {

            String customerApkFile =PropertyUtility.getDataProperties("customer.apk.file.location").replace("{ENVT}", PropertyUtility.environment);


            logger.detail("apk file Location " + customerApkFile);
            if (!Files.exists(Paths.get(customerApkFile))) {
                logger.detail("apk file doesnot exist " + customerApkFile);

/*                warning("IPA file doesnot exist on local machine",
                        "File " + customerIPAFile, false);*/
            }
            ((AndroidDriver<MobileElement>) SetupManager.getDriver()).closeApp();
            ((AndroidDriver<MobileElement>) SetupManager.getDriver()).removeApp(PropertyUtility.getProp("bundleId_Customer"));
            ((AndroidDriver<MobileElement>) SetupManager.getDriver()).installApp(customerApkFile);
            ((AndroidDriver<MobileElement>) SetupManager.getDriver()).launchApp();
            isInstalled = true;

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        return isInstalled;


    }

    public boolean installDriverApp() {
        boolean isInstalled = false;
        try {

            String driverApkFile = PropertyUtility.getDataProperties("driver.apk.file.location").replace("{ENVT}", PropertyUtility.environment);

            logger.detail("apk file Location " + driverApkFile);
            if (!Files.exists(Paths.get(driverApkFile))) {
                logger.detail("apk file doesnot exist " + driverApkFile);

/*                warning("IPA file doesnot exist on local machine",
                        "File " + customerIPAFile, false);*/
            }
            ((AndroidDriver<MobileElement>) SetupManager.getDriver()).closeApp();
            ((AndroidDriver<MobileElement>) SetupManager.getDriver()).removeApp(PropertyUtility.getProp("bundleId_Driver"));
            ((AndroidDriver<MobileElement>) SetupManager.getDriver()).installApp(driverApkFile);
            ((AndroidDriver<MobileElement>) SetupManager.getDriver()).launchApp();
            isInstalled = true;

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        return isInstalled;


    }
    /**
     * Verification that correct page is displayed
     *
     * @param expectedPage
     */
    public void verifyIsPageIsCorrectlyDisplayed(String expectedPage) throws InterruptedException {
        testStepAssert.isTrue(isCorrectPage(expectedPage), expectedPage + " page should be displaed ", expectedPage + " Page is successfully displayed", expectedPage + " Page is not displayed");
    }

    public String getAlertMessage() {
        return action.getText(estimatePage.Alert_ConfirmRequestMessage(true));
    }

    /**
     * Get snack bar message
     *
     * @return return snack bar message
     */
    public String getSnackBarMessage() {
        WebElement snackBar = forgotPasswordPage.Snackbar_ForgotPassword(true);
        String actualMessage = "";
        if (snackBar == null) {
            warning("Snackbar message for success should be displayed", "Snackbar message was not displayed or was displayed for small amount of time to capture snackbar message text");
        } else {
            actualMessage = snackBar.getText();
        }
        return actualMessage;
    }

    public String getSignupAlertMessage() {
        WebElement snackBar = Page_Signup.Cust_Signup_Error_InactivePromo();
        String actualMessage = "";
        if (snackBar == null) {
            warning("Alert message for inactive promo code should be displayed", "Alert message for inactive promo code was not displayed or was displayed for small amount of time to capture Alert message text");
        } else {
            actualMessage = snackBar.getText();
        }
        return actualMessage;
    }

    /**
     * Check if  phone number is correct
     *
     * @param element Web element of phone number
     * @param value   expected phine number value
     */
    public void isPhoneNumbersEqual(WebElement element, String value) {
        String actualText = element.getText().replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        String expectedText = value.replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        if (!actualText.equalsIgnoreCase(expectedText)) {
            if (expectedText.startsWith("1")) expectedText = expectedText.replaceFirst("1", "");
        }
        testStepVerify.isEquals(actualText, expectedText, "Twillio Number should be correct", "Twillio number is not correct. actualText:" + actualText + "expectedText:" + expectedText);
    }

    /**
     * Check if  phone number is correct
     *
     * @param element Web element of phone number
     * @param value   expected phine number value
     */
    public void isPhoneNumbersEqual(WebElement element, String value, String expectedMessage, String errorMessage) {
        String actualText = element.getText().replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        String expectedText = value.replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        if (!actualText.equalsIgnoreCase(expectedText)) {
            if (expectedText.startsWith("1")) expectedText = expectedText.replaceFirst("1", "");
        }
        testStepVerify.isEquals(actualText, expectedText, expectedMessage, errorMessage);
    }

    /**
     * Calculate estimate cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance
     * @param loadTime
     * @param estTime
     * @param Promo
     * @return
     */
    public double bungiiEstimate(String tripDistance, String loadTime, String estTime, String Promo) {

        double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double loadUnloadTime = Double.parseDouble(loadTime.replace(" mins", ""));
        Promo = Promo.contains("ADD") ? "0" : Promo;
        double tripTime = Double.parseDouble(estTime);
        double actualValue = distance + loadUnloadTime + tripTime;
        double discount = 0;
        String promoValue = Promo;
        if (Promo.contains("$"))
            discount = Double.parseDouble(Promo.replace("-$", ""));
        else if (Promo.contains("%"))
            discount = actualValue * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100;

        double estimate = distance + loadUnloadTime + tripTime - discount;
        if(promoValue.contains("-100%"))
            estimate =estimate;
            else
        estimate = estimate > MIN_COST ? estimate : MIN_COST;
        return estimate;
    }

    /**
     * Calculate  cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance
     * @param Promo
     * @return
     */
    public double bungiiCustomerCost(String tripDistance, String tripTime, String Promo, String tripType) {
        logger.detail("tripDistance" + tripDistance + ".tripTime" + tripTime + "Promo" + Promo + "tripType" + tripType);
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get minimum cost,Mile value,Minutes value of Geofence
        double minCost = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.minimum.cost")),
                perMileValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.miles")),
                perMinutesValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.minutes"));
        double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double tripActualTime = Double.parseDouble(tripTime);
        double tripValue = distance * perMileValue + tripActualTime * perMinutesValue;
        if (tripType.equalsIgnoreCase("DUO")) {
            tripValue = tripValue * 2;
            //MIN_COST = MIN_COST * 2;
            minCost = minCost * 2;
        }
        Promo = Promo.contains("ADD") ? "0" : Promo;

        double discount = 0;
        if (Promo.contains("$"))
            discount = Double.parseDouble(Promo.replace("-$", ""));
        else if (Promo.contains("%")) {
            discount = tripValue * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100;
            if (tripType.equalsIgnoreCase("DUO")) {
                //discount is rounded to floor
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.FLOOR);
                discount = new Double(df.format(discount));
            }
        }
        double costToCustomer = tripValue - discount;
        //costToCustomer = costToCustomer > MIN_COST ? costToCustomer : MIN_COST;
        if (costToCustomer==0.00)
            {
                //costToCustomer = 0;
                //do nothing
            }
            else
                costToCustomer = costToCustomer > minCost ? costToCustomer : minCost;

        return costToCustomer;
    }

    /**
     * Input value on Numeric keyboard
     *
     * @param strNum Number that is to be input
     * @throws InterruptedException
     */
    public void inputOnNumberKeyBoard(String strNum) throws InterruptedException {
        for (char c : strNum.toCharArray()) {
            ((AndroidDriver) SetupManager.getDriver()).pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + c)));
            Thread.sleep(200);
        }
        System.out.println("                  TEXT ENTERED :" + strNum);
        try {
            ((AndroidDriver) SetupManager.getDriver()).hideKeyboard();
        } catch (Exception e) {
        }
    }

    /**
     * Click button on customer menu bar
     *
     * @param menuItem identifier for menu
     */
    public void clickCustomerMenuItem(String menuItem) {
        try {
          /* */

            action.click(homePage.Button_NavigationBar());
            if(action.isElementPresent(Page_CustomerBungiiProgress.Alert_Message(true)))
            {
                action.click(homePage.Button_AlertDone());
            }

        } catch (org.openqa.selenium.NoSuchElementException e) {
            if (action.isElementPresent(homePage.Button_NavigationBarCompleter(true))) {
                if(action.isElementPresent(Page_CustomerBungiiProgress.Alert_Message(true)))
                {
                    action.click(homePage.Button_AlertDone());
                }
                WebElement Button_NavigationBar = homePage.Button_NavigationBarCompleter();
                int xAxisStartPoint = Button_NavigationBar.getLocation().getX() + 20;
                int yAxis = Button_NavigationBar.getLocation().getY() + Button_NavigationBar.getRect().getHeight() / 2;
                action.click(new Point(xAxisStartPoint, yAxis));
            }
        }

        switch (menuItem.toUpperCase()) {
            case "HOME":
                action.click(homePage.Button_NavHome());
                break;
            case "FAQ":
                action.click(homePage.Button_NavFAQ());
                break;
            case "ACCOUNT":
                if(action.isElementPresent(accountPage.Button_Navigate_Up(true)))
                {
                    action.click(accountPage.Button_Navigate_Up());
                    action.click(homePage.Button_NavAccountInfo());

                }
                else if(action.isElementPresent(homePage.Button_NavAccount(true))){
                    action.click(homePage.Button_NavAccount());
                    action.click(homePage.Button_NavAccountInfo());
                }
                else
                    action.click(homePage.Button_NavAccountInfo());
                break;
            case "MY BUNGIIS":
                action.click(homePage.Button_NavSchBungii());
                break;
            case "PAYMENT":
                if(action.isElementPresent(accountPage.Button_Navigate_Up(true)))
                {
                    action.click(accountPage.Button_Navigate_Up());
                    action.click(homePage.Button_NavPayment());

                }
                else if(action.isElementPresent(homePage.Button_NavAccount(true))){
                    action.click(homePage.Button_NavAccount());
                    action.click(homePage.Button_NavPayment());
                }
                else
                    action.click(homePage.Button_NavPayment());
                break;
            case "SUPPORT":
                action.click(homePage.Button_NavSupport());
                break;
            case "ACCOUNT>PROMOS":
                if(action.isElementPresent(accountPage.Button_Navigate_Up(true)))
                {
                    action.click(accountPage.Button_Navigate_Up());
                    action.click(homePage.Button_NavPromos());

                }
                else if(action.isElementPresent(homePage.Button_NavAccount(true))){
                    action.click(homePage.Button_NavAccount());
                    action.click(homePage.Button_NavPromos());
                }
                else
                    action.click(homePage.Button_NavPromos());
                break;
             case "ACCOUNT > PRIVACY POLICY":
                 if(action.isElementPresent(accountPage.Button_Navigate_Up(true)))
                 {
                     action.click(accountPage.Button_Navigate_Up());
                     action.click(homePage.Button_NavPrivacyPolicy());

                 }
                 else if(action.isElementPresent(homePage.Button_NavAccount(true))){
                     action.click(homePage.Button_NavAccount());
                     action.click(homePage.Button_NavPrivacyPolicy());
                 }
                 else
                     action.click(homePage.Button_NavPrivacyPolicy());
                 break;
            case "ACCOUNT>LOGOUT":
                //String currentPage = action.getText(Page_Signup.GenericHeader(true));
               // if (currentPage.equalsIgnoreCase("ACCOUNT INFO")||currentPage.equalsIgnoreCase("PROMOS")||currentPage.equalsIgnoreCase("PAYMENT"))
                if(action.isElementPresent(accountPage.Button_Navigate_Up(true)))
                {
                    action.click(accountPage.Button_Navigate_Up());
                    action.click(homePage.Button_Navlogout());

                }
                else if(action.isElementPresent(homePage.Button_NavAccount(true))){
                    action.click(homePage.Button_NavAccount());
                    action.click(homePage.Button_Navlogout());
                }
                else
                    action.click(homePage.Button_Navlogout());
                break;
            case "SIGN UP TO DRIVE":
                action.click(homePage.Button_NavDrives());
                break;
        }
    }


    public String getEstimateTime() {

        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //   phoneNumber="9999996170";
        String custRef = dbUtility.getCustomerRefference(phoneNumber);
        return DbUtility.getEstimateTime(custRef);
    }

    public String getEstimateDistance() {

        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //   phoneNumber="9999996170";
        String custRef = dbUtility.getCustomerRefference(phoneNumber);
        return DbUtility.getEstimateTime(custRef);
    }

    public String getActualTime() {

        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //   phoneNumber="9403960188";
        String custRef = dbUtility.getCustomerRefference(phoneNumber);
        String pickUpId = DbUtility.getPickupID(custRef);
        String actualTime = DbUtility.getActualTime(pickUpId);
        return actualTime;
    }

    public String getPickupRef(String phoneNumber) {

        String custRef = dbUtility.getCustomerRefference(phoneNumber);
        String pickupReff = DbUtility.getPickupReff(custRef);
        return pickupReff;
    }

    public void goToSignupPage() {
        action.waitUntilIsElementExistsAndDisplayed(Page_Signup.GenericHeader(true));

        String currentPage = action.getText(Page_Signup.GenericHeader(true));
        switch (currentPage.toUpperCase()) {
            case "BUNGII":
            case "FAQ":
            case "ACCOUNT":
            case "SCHEDULED BUNGIIS":
            case "PAYMENT":
            case "SUPPORT":
            case "PROMOS":
                clickCustomerMenuItem("LOGOUT");
                action.click(Page_Login.Link_Signup());
                break;
            case "SIGN UP":
                break;
            case "LOGIN":
                action.click(Page_Login.Link_Signup());
                break;
        }

    }

    public void goToLoginPage() {
        action.waitUntilIsElementExistsAndDisplayed(Page_Signup.GenericHeader(true));

        boolean skipNormalFlow = false;
        //    System.out.println("Page"+SetupManager.getDriver().getPageSource());
        if(action.isElementPresent(Page_Signup.Message_Error(true)))
        {
           action.click(Page_Signup.Button_Retry());
           logger.detail("Retried on error on Could not connet to server");
        }
        String currentPage = action.getText(Page_Signup.GenericHeader(true));
        switch (currentPage.toUpperCase()) {
            case "BUNGII":
            case "FAQ":
            case "ACCOUNT":
            case "SCHEDULED BUNGIIS":
            case "PAYMENT":
            case "SUPPORT":
            case "PROMOS":
                clickCustomerMenuItem("LOGOUT");
                skipNormalFlow = true;
                break;
            case "SIGN UP":
                action.click(Page_Signup.Link_Login());
                skipNormalFlow = true;

                break;
            case "LOGIN":
                //   action.click(Page_Signup.Link_Login());
                skipNormalFlow = true;

                break;

        }
        if (!skipNormalFlow) {
            if (action.isElementPresent(Page_Signup.Link_Login(true)))
                action.click(Page_Signup.Link_Login());
            else if (action.isElementPresent(Page_Login.Header_LoginPage(true))) {
                //do nothing
            } else
                clickCustomerMenuItem("LOGOUT");
        }
    }


    public String trimString(String stringtext) {
        stringtext = stringtext.trim().replace("\t", "").replace("\n", "").replace("\r", "");
        return stringtext;
    }

    /**
     * Check if promo code is present in list of available promocode in promocode page
     *
     * @param promoCode promocode that is to be checked
     * @return
     */
    public boolean isPromoCodePresent(String promoCode) {
        List<WebElement> listOfPromoCode = promosPage.List_PromoCode();
        boolean isPromoCodePresent = false;
        for (WebElement lstPromo : listOfPromoCode) {
            if (action.getText(lstPromo).contains(promoCode)) {
                isPromoCodePresent = true;
                break;
            }
        }
        return isPromoCodePresent;
    }

    public String generateMobileNumber() {

        String phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");
        //  phoneNumber="9999993248";
        while (!DbUtility.isPhoneNumberUnique(phoneNumber)) {
            phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");

        }
        return phoneNumber;
    }

    public void loginToCustomerApp(String phone, String password) throws InterruptedException {
        boolean isNextScreenLogIN = false;
        //   System.out.println(SetupManager.getDriver().getPageSource());
        action.waitUntilIsElementExistsAndDisplayed(Page_Signup.GenericHeader(true));

        String currentPage = action.getText(Page_Signup.GenericHeader(true));
        switch (currentPage.toUpperCase()) {
            case "BUNGII":
            case "FAQ":
            case "ACCOUNT":
            case "SCHEDULED BUNGIIS":
            case "PAYMENT":
            case "SUPPORT":
            case "PROMOS":
                clickCustomerMenuItem("LOGOUT");
                isNextScreenLogIN = true;
                break;
            case "SIGN UP":
                action.click(Page_Signup.Link_Login());
                isNextScreenLogIN = true;
                break;
            case "TERMS & CONDITIONS":
                action.click(Page_CustTerms.Checkbox_Agree());
                action.click(Page_CustTerms.Button_Continue());
                if (action.isElementPresent(Page_CustTerms.Header_PermissionsLocation(true))) {
                    // action.click(Page_CustTerms.Button_GoToSetting());
                    action.click(Page_CustTerms.Button_PermissionsSure());
                    action.click(Page_CustTerms.Button_PermissionsAllow());
                    // ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                }
                if (action.isElementPresent(homePage.Button_Closetutorials(true)))
                    action.click(homePage.Button_Closetutorials());
                break;

        }
        currentPage = action.getText(Page_Signup.GenericHeader(true));
        logger.detail("Current Page is "+currentPage);
        if (currentPage.equalsIgnoreCase("LOGIN") || isNextScreenLogIN) {
            WebElement element = Page_Login.TextField_PhoneNumber();
            if (StringUtils.isNumeric(phone)) {
                //element.sendKeys();
                element.click();
                inputOnNumberKeyBoard(phone);
            } else {
                action.clearSendKeys(Page_Login.TextField_PhoneNumber(), phone);
            }

            action.clearSendKeys(Page_Login.TextField_Password(), password);
            action.click(Page_Login.Button_Login());
            Thread.sleep(10000);
            //   action.invisibilityOfElementLocated(Page_Login.Button_Login(true));
            String nextPage = "";
            try {
                action.waitUntilIsElementExistsAndDisplayed(Page_Signup.GenericHeader(true));
                nextPage = action.getText(Page_Signup.GenericHeader(true));
            } catch (Exception e) {
                nextPage = action.getText(Page_Signup.GenericHeader(true));
            }
            if (nextPage.equalsIgnoreCase("LOGIN")) {
                Thread.sleep(10000);
                action.waitUntilIsElementExistsAndDisplayed(Page_Signup.GenericHeader(true));
                nextPage = action.getText(Page_Signup.GenericHeader(true));
            }
                if (nextPage.equalsIgnoreCase("TERMS & CONDITIONS")) {
                action.click(Page_CustTerms.Checkbox_Agree());
                action.click(Page_CustTerms.Button_Continue());
                if (action.isElementPresent(Page_CustTerms.Header_PermissionsLocation(true))) {
                    action.click(Page_CustTerms.Button_PermissionsSure());
                    action.click(Page_CustTerms.Button_PermissionsAllow());
                    //((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                }
                if (action.isElementPresent(homePage.Button_Closetutorials(true)))
                    action.click(homePage.Button_Closetutorials());
            }
            log(" I am logged in as customer having phone number [ "+phone+" ]",
                    " I am logged in as customer having phone number [ "+phone+" ]", true);
        } else {
            logger.detail("Customer [ "+phone+" ] is not logged in");
        }
        //AssertionManager.ElementDisplayed(homePage.Title_HomePage);
        //AssertionManager.ElementDisplayed(homePage.Link_Invite);
    }

    public void LogoutCustomerApp() {
        if (action.isElementPresent(homePage.Link_Menu())) {
            action.click(homePage.Link_Menu());
            action.click(Page_Menu.Menu_Logout());
        }
    }

    public void loginToDriverApp(String phone, String password) throws InterruptedException {
        action.waitUntilIsElementExistsAndDisplayed(driverHomePage.Generic_HeaderElement(true));
        Thread.sleep(5000);
        String currentPage = action.getText(driverHomePage.Generic_HeaderElement(true));

        if (currentPage.equals("LOGIN")) {
            // if (action.isElementPresent(driverLoginPage.TextField_PhoneNumber(true))) {
            WebElement element = driverLoginPage.TextField_PhoneNumber();

            if (StringUtils.isNumeric(phone)) {
                element.click();
                inputOnNumberKeyBoard(phone);
            } else {
                action.sendKeys(driverLoginPage.TextField_PhoneNumber(), phone);
            }
            action.sendKeys(driverLoginPage.TextField_Password(), password);
            Thread.sleep(2000);
            action.click(driverLoginPage.Button_Login());
            Thread.sleep(5000);
            try {
                action.waitUntilIsElementExistsAndDisplayed(driverHomePage.Generic_HeaderElement(true));
                currentPage = action.getText(driverHomePage.Generic_HeaderElement(true));
                if(currentPage.equals("LOGIN"))
                {
                    Thread.sleep(5000);
                    currentPage = action.getText(driverHomePage.Generic_HeaderElement(true));
                }
            } catch (StaleElementReferenceException ex) {
                Thread.sleep(5000);
                WebElement header = driverHomePage.Generic_HeaderElement();
                currentPage = header.getText();
            }
            if (currentPage.equals("ONLINE") || currentPage.equals("OFFLINE") || currentPage.equals("EN ROUTE") || currentPage.equals("ARRIVED") || currentPage.equals("LOADING ITEM") || currentPage.equals("DRIVING TO DROP OFF") || currentPage.equals("UNLOADING ITEM")) {

            } else if (currentPage.equals("LOCATION")) {
                action.click(driverLoginPage.Button_Sure());
                action.click(driverLoginPage.Button_Allow());

            } else if (action.isElementPresent(driverLoginPage.Header_Location(true))) {
                action.click(driverLoginPage.Button_Sure());
                action.click(driverLoginPage.Button_Allow());
            }
        }
        else {
            //Not on Login page
        }
    }

    public void enterDriverPhoneAndPassword(String phone, String password) throws InterruptedException {
        if (action.isElementPresent(driverLoginPage.TextField_PhoneNumber(true))) {
            WebElement element = driverLoginPage.TextField_PhoneNumber();

            if (StringUtils.isNumeric(phone)) {
                element.click();
                inputOnNumberKeyBoard(phone);
            } else {
                action.sendKeys(driverLoginPage.TextField_PhoneNumber(), phone);
            }
            action.sendKeys(driverLoginPage.TextField_Password(), password);
        } else {
            //Not on Login page
        }
    }

    public void goToDriverLoginPage() throws InterruptedException {

        String currentPage = action.getText(driverHomePage.Generic_HeaderElement());

        if (currentPage.equals("LOGIN")) {
        } else if (currentPage.equals("ONLINE") || currentPage.equals("OFFLINE")) {
            clickDriverMenuItem("ACCOUNT");
            clickDriverSubMenuItem("LOGOUT");
        } else if (action.isElementEnabled(driverLoginPage.Button_ForgotPassword(true))) {
        }
        else if (currentPage.equals("LOCATION")) {
            action.click(driverLoginPage.Button_Sure());
            action.click(driverLoginPage.Button_Allow());
            clickDriverMenuItem("ACCOUNT");
            clickDriverSubMenuItem("LOGOUT");

        } else if (action.isElementPresent(driverLoginPage.Header_Location(true))) {
            action.click(driverLoginPage.Button_Sure());
            action.click(driverLoginPage.Button_Allow());
            clickDriverMenuItem("ACCOUNT");
            clickDriverSubMenuItem("LOGOUT");
        }
        else if (action.isNotificationAlertDisplayed()) {
            if (action.getText(Page_BungiiRequest.Alert_Msg(true)).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))) {
                acceptNotificationAlert();
            } else {
                action.click(Page_BungiiRequest.AlertButton_Cancel());
            }

        }
        else {
            clickDriverMenuItem("ACCOUNT");
            clickDriverSubMenuItem("LOGOUT");
        }

    }

    public boolean clickDriverMenu(String menuItem) {
        Boolean isClicked = false;
        List<WebElement> elements = driverHomePage.Button_NavigationBarText();
        for (WebElement element : elements) {
            logger.detail("Menu : "+ element.getText());
            if (element.getText().equalsIgnoreCase(menuItem)) {
                logger.detail("Clicked on menu : "+ element.getText());
                action.click(element);
                isClicked = true;
                break;
            }
        }
        logger.detail("Value of isClicked : "+ isClicked);
        return isClicked;
    }

    public boolean clickDriverSubMenu(String menuItem) {
        Boolean isClicked = false;
        List<WebElement> elements = driverHomePage.Label_SubMenuText();
        for (WebElement element : elements) {
            logger.detail("Menu : "+ element.getText());
            if (element.getText().equalsIgnoreCase(menuItem)) {
                logger.detail("Clicked on menu : "+ element.getText());
                action.click(element);
                isClicked = true;
                break;
            }
        }
        logger.detail("Value of isClicked : "+ isClicked);
        return isClicked;
    }

    public void clickDriverMenuItem(String menuItem) {
        action.click(driverHomePage.Button_NavigationBar());
        boolean isClicked = clickDriverMenu(menuItem);

        if (!isClicked) {
            action.scrollToBottom();
            isClicked = clickDriverMenu(menuItem);
        }
        testStepAssert.isTrue(isClicked, "I should able to click " + menuItem, "Not able to select " + menuItem + " from App menu");
    }

    public void clickDriverSubMenuItem(String menuItem) throws InterruptedException {
        //action.click(driverHomePage.Button_NavigationBar());
        boolean isClicked = clickDriverSubMenu(menuItem);

        if (!isClicked) {
            action.scrollToBottom();
            isClicked = clickDriverMenu(menuItem);
        }
        testStepAssert.isTrue(isClicked, "I should able to click " + menuItem, "Not able to select " + menuItem + " from App menu");
    }

    public boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }

    public void isDriverLoginSucessful() {
        //testStepAssert.isElementEnabled(driverHomePage.Title_Status(true), "driver should be sucessfully login in", "driver was logged in sucessfuly and driver status is" + action.getText(driverHomePage.Title_Status()), "driver was logged in successfuly");
    }

    public boolean clickOnNofitication(String appName, String notificationMessage) {
        boolean isDisplayed = false;
        //   List<WebElement> notificationHeader = otherAppsPage.Text_NotificationTitle();
        //  List<WebElement> notificationText = otherAppsPage.Text_Notification();
        //  System.out.println(SetupManager.getDriver().getPageSource());

        //FIX FOR APPIUM 1.42
        if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.notification.ondemand"))) {
            if (action.isElementPresent(otherAppsPage.Notification_OnDemand(true))) {
                action.click(otherAppsPage.Notification_OnDemand());
                isDisplayed = true;
            }
        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.notification.stack"))) {
            if (action.isElementPresent(otherAppsPage.Notification_Stack(true))) {
                action.click(otherAppsPage.Notification_Stack());
                isDisplayed = true;
            }

        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.notification.stack.cancel"))) {
            if (action.isElementPresent(otherAppsPage.Notification_StackCustomerCancel(true))) {
                action.click(otherAppsPage.Notification_StackCustomerCancel());
                isDisplayed = true;
            }

        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.accepted.stack"))) {
            if (action.isElementPresent(otherAppsPage.Notification_StackDriverAccepted(true))) {
                action.click(otherAppsPage.Notification_StackDriverAccepted());
                isDisplayed = true;
            }

        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.started.stack"))) {
            if (action.isElementPresent(otherAppsPage.Notification_StackDriverStarted(true))) {
                action.click(otherAppsPage.Notification_StackDriverStarted());
                isDisplayed = true;
            }

        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.bungii.accepted.stack"))) {
            if (action.isElementPresent(otherAppsPage.Notification_StackDriverAccepted1(true))) {
                action.click(otherAppsPage.Notification_StackDriverAccepted1());
                isDisplayed = true;
            }

        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.scheduled.t.minus.2"))) {
            if (action.isElementPresent(otherAppsPage.Notification_TMinus2(true))) {
                action.click(otherAppsPage.Notification_TMinus2());
                isDisplayed = true;
            }
        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.cancelled"))) {
            if (action.isElementPresent(otherAppsPage.Notification_OtherDriverCancel(true))) {
                action.click(otherAppsPage.Notification_OtherDriverCancel());
                isDisplayed = true;
            }
        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.bungii.customer.scheduled.cancel"))) {
            if (action.isElementPresent(otherAppsPage.Notification_CustomerCancel(true))) {
                action.click(otherAppsPage.Notification_CustomerCancel());
                isDisplayed = true;
            }
        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.other.driver.bungii.cancel.notification"))) {
            if (action.isElementPresent(otherAppsPage.Notification_DriverBungiiCancel(true))) {
                action.click(otherAppsPage.Notification_DriverBungiiCancel());
                isDisplayed = true;
            }
        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.finish.bungii"))) {
            if (action.isElementPresent(otherAppsPage.Notification_CustomerFinsihBungii(true))) {
                action.click(otherAppsPage.Notification_CustomerFinsihBungii());
                isDisplayed = true;
            }
            else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.activate.bungii"))) {
                if (action.isElementPresent(otherAppsPage.Notification_ActivateBungii(true))) {
                    action.click(otherAppsPage.Notification_ActivateBungii(true));
                    isDisplayed = true;
                }
            }
        }
        else{
            String message= (String) cucumberContextManager.getScenarioContext("EXPECTED_MESSAGE");
            if (action.isElementPresent(otherAppsPage.Notification_PartnerCancel(message))) {
                action.click(otherAppsPage.Notification_PartnerCancel(message));
                isDisplayed = true;
            }
        }
        return isDisplayed;
    }
    public boolean getNofitication(String appName, String notificationMessage) {
        boolean isDisplayed = false;
        //   List<WebElement> notificationHeader = otherAppsPage.Text_NotificationTitle();
        //  List<WebElement> notificationText = otherAppsPage.Text_Notification();
        //  System.out.println(SetupManager.getDriver().getPageSource());

        //FIX FOR APPIUM 1.42
        if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.notification.ondemand"))) {
            if (action.isElementPresent(otherAppsPage.Notification_OnDemand(true))) {
                isDisplayed = true;
            }
        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.notification.stack"))) {
            if (action.isElementPresent(otherAppsPage.Notification_Stack(true))) {
                isDisplayed = true;
            }

        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.notification.stack.cancel"))) {
            if (action.isElementPresent(otherAppsPage.Notification_StackCustomerCancel(true))) {
                isDisplayed = true;
            }

        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.accepted.stack"))) {
            if (action.isElementPresent(otherAppsPage.Notification_StackDriverAccepted(true))) {
                isDisplayed = true;
            }

        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.started.stack"))) {
            if (action.isElementPresent(otherAppsPage.Notification_StackDriverStarted(true))) {
                isDisplayed = true;
            }

        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.bungii.accepted.stack"))) {
            if (action.isElementPresent(otherAppsPage.Notification_StackDriverAccepted1(true))) {
                isDisplayed = true;
            }

        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.scheduled.t.minus.2"))) {
            if (action.isElementPresent(otherAppsPage.Notification_TMinus2(true))) {
                isDisplayed = true;
            }
        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.cancelled"))) {
            if (action.isElementPresent(otherAppsPage.Notification_OtherDriverCancel(true))) {
                isDisplayed = true;
            }
        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.bungii.customer.scheduled.cancel"))) {
            if (action.isElementPresent(otherAppsPage.Notification_CustomerCancel(true))) {
                isDisplayed = true;
            }
        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.other.driver.bungii.cancel.notification"))) {
            if (action.isElementPresent(otherAppsPage.Notification_DriverBungiiCancel(true))) {
                isDisplayed = true;
            }
        } else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.finish.bungii"))) {
            if (action.isElementPresent(otherAppsPage.Notification_CustomerFinsihBungii(true))) {
                isDisplayed = true;
            }
            else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.activate.bungii"))) {
                if (action.isElementPresent(otherAppsPage.Notification_ActivateBungii(true))) {
                    isDisplayed = true;
                }
            }
        }
        return isDisplayed;
    }
    public String getPageHeader() {
        return action.getText(driverHomePage.Generic_HeaderElement());
    }

    public String getExpectedNotification(String identifier) {
        String text = "";
        try {

            switch (identifier.toUpperCase()) {
                case "ON DEMAND TRIP":
                    text = PropertyUtility.getMessage("driver.notification.ondemand");
                    break;
                case "DRIVER CANCELLED":
                    text = PropertyUtility.getMessage("customer.notification.driver.cancelled");
                    break;
                case "DRIVER ENROUTE":
                    text = PropertyUtility.getMessage("customer.notification.driver.accepted");
                    break;
                case "STACK TRIP":
                    text = PropertyUtility.getMessage("driver.notification.stack");
                    break;
                case "CUSTOMER CANCEL STACK TRIP":
                    text = PropertyUtility.getMessage("driver.notification.stack.cancel");
                    break;
                case "CUSTOMER -DRIVER ACCEPTED STACK BUNGII":
                    text = PropertyUtility.getMessage("customer.notification.driver.accepted.stack");
                    break;
                case "CUSTOMER -DRIVER STARTED STACK BUNGII":
                    text = PropertyUtility.getMessage("customer.notification.driver.started.stack");
                    break;
                case "SCHEDULED PICKUP ACCEPTED":
                    text = PropertyUtility.getMessage("customer.notification.driver.bungii.accepted.stack");
                    break;
                case "T-2 BEFORE SCHEDULED TRIP":
                    text = PropertyUtility.getMessage("customer.notification.scheduled.t.minus.2");
                    break;
                case "DRIVER CANCELLED BUNGII":
                    text = PropertyUtility.getMessage("customer.notification.driver.cancelled");
                    break;
                case "CUSTOMER CANCELLED SCHEDULED BUNGII":
                    text = PropertyUtility.getMessage("driver.bungii.customer.scheduled.cancel");
                    break;
                case "OTHER DRIVER CANCELLED BUNGII":
                    text = PropertyUtility.getMessage("driver.other.driver.bungii.cancel.notification");
                    break;
                case "CUSTOMER-JUST FINISHED BUNGII":
                    text=PropertyUtility.getMessage("customer.finish.bungii");
                    break;
                case "TAP NOTIFICATION TO ACTIVATE BUNGII":
                    text=PropertyUtility.getMessage("driver.activate.bungii");
                    break;
                case "TIP RECEIVED 5 DOLLAR":
                    String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
                    String expectedCustomerName = custName.substring(0, custName.indexOf(" ") + 2);
                    text = PropertyUtility.getMessage("driver.received.5.dollar.tip");
                    text=text.replace("<Customer Name>", expectedCustomerName);
                    break;
                case "URGENT SCHEDULED PICKUP AVAILABLE":
                    text = PropertyUtility.getMessage("driver.notification.scheduled.urgent");
                    break;
                case "SCHEDULED PICKUP AVAILABLE":
                    text = PropertyUtility.getMessage("driver.notification.scheduled");
                    //	$<Day>, $<MONTH> <$Date>

                    String schDate = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
                    String geofenceLabel = getTimeZoneBasedOnGeofenceId();


                    //	DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyyy", Locale.ENGLISH);
                    DateFormat format = new SimpleDateFormat("MMM dd, HH:mm a zzz", Locale.ENGLISH);
                    try {
                        format.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

                        Date date = format.parse(schDate);
                        Date currentDate = new Date();
                        //int year=currentDate.getYear()+1900;
                        date.setYear(currentDate.getYear());
                        int month = date.getMonth();
                        String strMonth = getMonthForInt(month);
                        int dayOfMonth = date.getDate();
                        String dayOfMonthStr = String.valueOf(dayOfMonth) + getDayOfMonthSuffix(dayOfMonth);

                        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
                        String dayOfWeek = simpleDateformat.format(date);

                        text = text.replace("$<Day>", dayOfWeek);
                        text = text.replace("$<MONTH>", strMonth);
                        text = text.replace("$<Date>", dayOfMonthStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        return text;
    }
    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    String getDayOfMonthSuffix(final int n) {
        checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
    public void selectBungiiTime() {
        action.scrollToTop();
        action.click(estimatePage.Time());
        if(action.isElementPresent(estimatePage.Button_Later(true)))
            action.click(estimatePage.Button_Later());
        action.click(estimatePage.Button_DateConfirm());
        action.click(estimatePage.Button_TimeConfirm());
// Estimate picker
    }

    public void selectNewBungiiTime() {
        action.scrollToTop();
        action.click(estimatePage.Time());
        if(action.isElementPresent(estimatePage.Button_Later(true)))
            action.click(estimatePage.Button_Later());
        action.click(estimatePage.Button_DateConfirm());
        action.click(estimatePage.Button_TimeConfirm());
    }

    public void selectBungiiTime(String hour, String minutes, String ampm) {
        action.scrollToTop();
        action.click(estimatePage.Time()); if(action.isElementPresent(estimatePage.Button_Later(true)))
            action.click(estimatePage.Button_Later());
        action.click(estimatePage.Button_DateConfirm());
        action.sendKeys(estimatePage.TextBox_CurrentBungiiHour(), hour);
        action.sendKeys(estimatePage.TextBox_CurrentBungiiMinutes(), minutes);
        action.sendKeys(estimatePage.TextBox_CurrentBungiiAMPM(), ampm);
        action.click(estimatePage.Button_TimeConfirm());

    }

    public void selectTime() {
        String month=setPickupTimePage.Text_MonthPicker().getText();
        String Day=setPickupTimePage.Text_DayPicker().getText();
        String year=setPickupTimePage.Text_YearPicker().getText();
        action.click(estimatePage.Button_DateConfirm());
        int currentHour= Integer.parseInt(setPickupTimePage.Text_SelectHours().getText());
        currentHour=currentHour+1;
        action.sendKeys(setPickupTimePage.Text_SelectHours(),String.valueOf(currentHour));
        String mins=setPickupTimePage.Text_SelectMinutes().getText();
        action.click(setPickupTimePage.Button_TimePickerOK());
        String scheduleBungiiDate=month+" "+Day+", "+String.valueOf(currentHour)+":"+mins;
        cucumberContextManager.setScenarioContext("SCHEDULE_BUNGII_DATE", scheduleBungiiDate);

        String myBungiiDateTime=month+" "+Day+", "+year+" - "+String.valueOf(currentHour)+":"+mins;
        cucumberContextManager.setScenarioContext("MY_BUNGII_DATE", myBungiiDateTime);
    }
/*    public void selectTimeValue(int min) {
        action.scrollToTop();
        action.click(estimatePage.Time()); if(action.isElementPresent(estimatePage.Button_Later(true)))
            action.click(estimatePage.Button_Later());
        action.click(estimatePage.Button_DateConfirm());
        int currentMin= Integer.parseInt(setPickupTimePage.Text_SelectMinutes().getText());
        currentMin=currentMin+min;
        action.sendKeys(setPickupTimePage.Text_SelectMinutes(),String.valueOf(currentMin));
        String mins=setPickupTimePage.Text_SelectMinutes().getText();
        action.click(setPickupTimePage.Button_TimePickerOK());
    }*/

    public void selectTimeValue(int min) {
        action.scrollToTop();
        action.click(estimatePage.Time()); if(action.isElementPresent(estimatePage.Button_Later(true)))
            action.click(estimatePage.Button_Later());
        action.click(estimatePage.Button_DateConfirm());
        int currentMin= Integer.parseInt(setPickupTimePage.Text_SelectMinutes().getText());
        int currentHour= Integer.parseInt(setPickupTimePage.Text_SelectHours().getText());
        if(min>=60)
        {
            int hoursToAdd =min/60;
            currentHour=currentHour+hoursToAdd;
            action.enterText(setPickupTimePage.Text_SelectHours(),String.valueOf(currentHour));
        }
        else currentMin=currentMin+min;
        action.enterText(setPickupTimePage.Text_SelectMinutes(),String.valueOf(currentMin));
        String mins=setPickupTimePage.Text_SelectMinutes().getText();
        action.click(setPickupTimePage.Button_TimePickerOK());
    }

    public void selectNewerTime() {
        action.click(estimatePage.Button_DateConfirm());
        int currentHour= Integer.parseInt(setPickupTimePage.Text_SelectHours().getText());
        currentHour=currentHour+1;
        action.sendKeys(setPickupTimePage.Text_SelectHours(),String.valueOf(currentHour));
        String mins=setPickupTimePage.Text_SelectMinutes().getText();
        action.click(setPickupTimePage.Button_TimePickerOK());
    }
    public void selectFutureTime() {
        action.click(estimatePage.Button_DateConfirm());
        int currentHour= Integer.parseInt(setPickupTimePage.Text_SelectHours().getText());
        currentHour=currentHour+2;
        action.sendKeys(setPickupTimePage.Text_SelectHours(),String.valueOf(currentHour));
        String mins=setPickupTimePage.Text_SelectMinutes().getText();
        action.click(setPickupTimePage.Button_TimePickerOK());
    }
    /*private void selectHour(String hour) throws InterruptedException {
        int hrs = Integer.parseInt(action.getText(setPickupTimePage.Text_SelectHours()));
        if (hrs == Integer.parseInt(hour)) {
            //do nothing
        } else if (hrs > Integer.parseInt(hour)) {
            WebElement back = estimatePage.Text_TimeHourPickerBack();
            for (int i = 0; i < (hrs - Integer.parseInt(hour)); i++) {
                action.click(back);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (hrs < Integer.parseInt(hour)) {
            WebElement next = estimatePage.Text_TimeHourPickerNext();
            for (int i = 0; i < ( Integer.parseInt(hour)-hrs); i++) {
                action.click(next);
                Thread.sleep(2000);
            }
        }
    }*/

    /**
     * Get geofence data from properties file
     *
     * @param geofenceName Geofence name
     * @param partialKey   this is partial value that is to be searched in properties file
     * @return get message from Geofence propertiese file
     */
    public String getGeofenceData(String geofenceName, String partialKey) {
        geofenceName = (geofenceName.isEmpty() || geofenceName.equals("")) ? PropertyUtility.getGeofenceData("current.geofence") : geofenceName.toLowerCase();
        String actualKey = geofenceName + "." + partialKey;
        return PropertyUtility.getGeofenceData(actualKey);
    }

    public void selectAddress(WebElement element, String searchstring) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
        action.tap(element);
        action.clearSendKeys(element,searchstring);
        int x = element.getLocation().getX() + 32;
        int y = element.getLocation().getY() + element.getRect().getHeight() + 10;
        Thread.sleep(4000);
        new TouchAction(driver).tap(new PointOption().withCoordinates(x, y)).release().perform();
        Thread.sleep(2000);

    }

    /**
     * Get timezone for geofence, read it from properties file and conver into Time zone object
     *
     * @return
     */
    public String getTimeZoneBasedOnGeofence() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get timezone value of Geofence
        String getGeofenceTimeZone = getGeofenceData(currentGeofence, "geofence.timezone");
        return getGeofenceTimeZone;
    }
//Richa
    /**
     * Get timezone for geofence, read it from properties file and conver into Time zone object
     *
     * @return
     */
    public String[] getDayLightTimeZoneBasedOnGeofence() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get timezone value of Geofence
        String getGeofenceTimeZone = getGeofenceData(currentGeofence, "geofence.timezone");
        String getDayLightGeofenceTimeZone=null;
        String getDayLightGeofenceTimeZoneGMT=null;

        switch (getGeofenceTimeZone){
            case "CST":
                getDayLightGeofenceTimeZone="CDT";
                break;
            case "EST":
                getDayLightGeofenceTimeZone="EDT";
                break;
            case "MST":
                getDayLightGeofenceTimeZone="MDT";
                break;
            case "IST":
                getDayLightGeofenceTimeZone="IST";
                break;
        }
        String [] timeZones=new String[2];
        timeZones[0]=getGeofenceTimeZone;
        timeZones[1]=getDayLightGeofenceTimeZone;
        return timeZones;
    }
    /**
     * Get timezone for geofence, read it from properties file and conver into Time zone object
     *
     * @return
     */
    public String getTimeZoneBasedOnGeofenceId() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        // currentGeofence="kansas";
        //get timezone value of Geofence
        String getGeofenceTimeZone = getGeofenceData(currentGeofence, "geofence.timezone.id");
        return getGeofenceTimeZone;
    }

    public String getCustomerSnackBarMessage() {
        String snackbarMessage =null;
        try {
        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("SnakBarWaitTime")));
        snackbarMessage = wait.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("com.bungii.customer:id/snackbar_text"))).getText();
        }
        catch(TimeoutException ex)
        {
            snackbarMessage ="";
        }
        return snackbarMessage;
    }
    public Boolean matchSnackBarMessage(String message) {

        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("SnakBarWaitTime")));
        Boolean snackbarMessageDisplayed = wait.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//snackbar_text[@text='"+message+"']"))).isDisplayed();
        return snackbarMessageDisplayed;
    }
    public String getCustomerPromoInfoMessage() {

        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("SnakBarWaitTime")));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/message")));
        return action.getText(element);
    }

    public String getDriverSnackBarMessage() {
        String snackbarMessage =null;
        try {
    WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("SnakBarWaitTime")));
     snackbarMessage = wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.presenceOfElementLocated(By.id("com.bungii.driver:id/snackbar_text"))).getText();

        }
        catch(TimeoutException ex)
        {
            snackbarMessage ="";
            }
        return snackbarMessage;
    }


    public boolean isForgotPasswordMessageCorrect() {
        final FluentWait<WebDriver> wait = new FluentWait<>(SetupManager.getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(NoSuchElementException.class);
        boolean isMessageCorrect = wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    final WebElement webElement = webDriver.findElement(By.id("com.bungii.customer:id/snackbar_text"));
                    return webElement.getText().equals(PropertyUtility.getMessage("customer.forgotpassword.success.android"));

                } catch (final StaleElementReferenceException | TimeoutException e) {
                    return false;
                }
            }
        });
        return isMessageCorrect;
    }

    public void acceptNotificationAlert() {
        action.click(driverHomePage.Notification_AlertAccept());
    }

    /**
     * Hide notification
     */
    public void hideNotifications() {
        action.hideNotifications();
    }


    public void recoverScenario() {
        logger.detail("********* RESTORING APP STATE : DRIVER *********");
        try{
            if(action.isElementPresent(driverHomePage.Generic_DriverCustomerApp(true))){
            }
            else if(action.isElementPresent(otherAppsPage.Notification_Screen(true))){
                action.hideNotifications();
            }
        }catch (Exception e) {
        }
        try {
            SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
            ((AndroidDriver) SetupManager.getDriver()).resetApp();
        } catch (Exception e) {
        }

        try {
            if (action.isElementPresent(Page_BungiiRequest.Button_Reject(true))) {
                action.click(Page_BungiiRequest.Button_Reject()); //Handle pushnotification
            }
            //   if (action.isElementPresent(driverBungiiProgressPage.Title_Status(true))) {
            if (action.isElementPresent(driverHomePage.Generic_HeaderElement(true))) {
                String screen = action.getText(driverHomePage.Generic_HeaderElement());
                logger.detail("Driver App Screen " + screen);
                if (screen.equalsIgnoreCase("Google Play Store")) {
                    //TODO
                }
                if (screen.equalsIgnoreCase(Status.ARRIVED.toString())) {
                    logger.detail("Driver is on Arrived screen");
                    action.click(driverBungiiProgressPage.Button_Cancel());
                    action.click(driverBungiiProgressPage.Button_Cancel_Yes());
                    launchCustomerApplication();
                    action.click(estimatePage.Button_OK());

                } else if (screen.equals("LOGIN") || screen.equals("ONLINE") || screen.equals("OFFLINE")) {
                    //do nothing
                } else if (screen.equals(Status.EN_ROUTE.toString())) {
                    logger.detail("Driver is on EN_ROUTE screen");
                    action.click(driverBungiiProgressPage.Button_Cancel());
                    action.click(driverBungiiProgressPage.Button_Cancel_Yes());
                    launchCustomerApplication();
                    action.click(estimatePage.Button_OK());
                } else if (screen.equals(Status.LOADING_ITEM.toString())) {
                    logger.detail("Driver is on LOADING_ITEM screen");
                    action.swipeRight(driverBungiiProgressPage.Slider());

                    if (action.isElementPresent(driverBungiiProgressPage.Alert_Message(true))) {
                        if (action.getText(driverBungiiProgressPage.Alert_Message()).equalsIgnoreCase(PropertyUtility.getMessage("bungii.duo.driver.pickup"))) {
                            action.getText(driverBungiiProgressPage.Alert_Message());
                            action.click(driverBungiiProgressPage.Alert_Accept());
                        }
                    }
                    action.swipeRight(driverBungiiProgressPage.Slider());
                    action.swipeRight(driverBungiiProgressPage.Slider());

                    if (action.isElementPresent(driverBungiiProgressPage.Alert_Message(true))) {
                        if (action.getText(driverBungiiProgressPage.Alert_Message()).equalsIgnoreCase(PropertyUtility.getMessage("bungii.duo.driver.drop"))) {
                            action.getText(driverBungiiProgressPage.Alert_Message());
                            action.click(driverBungiiProgressPage.Alert_Accept());
                        }
                    }
                    action.click(bungiiCompletedPage.Button_OnToTheNext());
                } else if (screen.equals(Status.DRIVING_TO_DROP_OFF.toString())) {
                    logger.detail("Driver is on DRIVING_TO_DROP_OFF screen");
                    action.swipeRight(driverBungiiProgressPage.Slider());
                    action.swipeRight(driverBungiiProgressPage.Slider());

                    if (action.isElementPresent(driverBungiiProgressPage.Alert_Message(true))) {
                        if (action.getText(driverBungiiProgressPage.Alert_Message()).equalsIgnoreCase(PropertyUtility.getMessage("bungii.duo.driver.drop"))) {
                            action.getText(driverBungiiProgressPage.Alert_Message());
                            action.click(driverBungiiProgressPage.Alert_Accept());
                        }
                    }
                    action.click(bungiiCompletedPage.Button_OnToTheNext());
                } else if (screen.equals(Status.UNLOADING_ITEM.toString())) {
                    logger.detail("Driver is on UNLOADING_ITEM screen");
                    action.swipeRight(driverBungiiProgressPage.Slider());

                    if (action.isElementPresent(driverBungiiProgressPage.Alert_Message(true))) {
                        if (action.getText(driverBungiiProgressPage.Alert_Message()).equalsIgnoreCase(PropertyUtility.getMessage("bungii.duo.driver.drop"))) {
                            action.getText(driverBungiiProgressPage.Alert_Message());
                            action.click(driverBungiiProgressPage.Alert_Accept());
                        }
                    }
                    action.click(bungiiCompletedPage.Button_OnToTheNext());
                } else if (screen.equalsIgnoreCase("Bungii Completed")) {
                    action.click(bungiiCompletedPage.Button_OnToTheNext());

                }
            } else if (action.isElementPresent(bungiiCompletedPage.Button_OnToTheNext(true))) {
                logger.detail("Driver is on bungii completed screen");
                action.click(bungiiCompletedPage.Button_OnToTheNext());
            } else if (action.isElementPresent(estimatePage.Button_OK(true))) {
                logger.detail("Driver is on Popup screen ");
                action.click(estimatePage.Button_OK());
            }

        } catch (Exception e) {
        }
        logger.detail("********* RESTORING APP STATE : CUSTOMER *********");
        SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Customer"));
        ((AndroidDriver) SetupManager.getDriver()).resetApp();
        String appHeader = "";
        try {
            appHeader = action.getText(Page_Signup.GenericHeader(true));
        } catch (Exception e) {
        }
        {
        }
        if (appHeader.equalsIgnoreCase("BUNGII") || appHeader.equalsIgnoreCase("SIGN UP") || appHeader.equalsIgnoreCase("LOGIN")) {
            //do nothing
        } else if (action.isElementPresent(searchingPage.ProgressBar(true))) {
            logger.detail("customer is on searching screen");
            action.click(searchingPage.Link_CancelSearch());
            action.click(searchingPage.Button_CancelConfirm());
        } else if (action.isElementPresent(customerBungiiCompletePage.PageTitle_BungiiComplete(true))) {
            logger.detail("customer is on bungii complete screen");
            action.click(customerBungiiCompletePage.CloseRateTipPage());
            action.click(wantDollar5Page.Button_NoFreeMoney());
        } else if (action.isElementPresent(wantDollar5Page.Titlebar_WantDollar5Page(true))) {
            logger.detail("Customer is on promotion screen");
            action.click(wantDollar5Page.Button_NoFreeMoney());
        }
    }

    public String GetSpecificURLs(String expectedFromAddress, String expectedToAddress, String expectedSubject) {

        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress, expectedSubject, 1);
            System.out.println("No of Total recent Messages : " + recentMessages.length);
            String fromAddress = PropertyUtility.getEmailProperties("email.from.address");
            boolean emailFound = false;
            String surveylink;
            for (int i = recentMessages.length; i > 0; i--) {

                System.out.println("MESSAGE " + (i) + ":");
                Message msg = recentMessages[i - 1];
                System.out.println(msg.getMessageNumber());
                String subject = msg.getSubject();

                System.out.println("Subject: " + subject);
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("To: " + msg.getAllRecipients()[0]);
                System.out.println("Date: " + msg.getReceivedDate());
                System.out.println("Plain text: " + emailUtility.getTextFromMessage(msg));
                if ((msg.getFrom()[0].toString().contains(fromAddress)) && (subject.contains(expectedSubject)) && (msg.getAllRecipients()[0].toString().contains(expectedToAddress)))
                {
                    surveylink =  emailUtility.getURLFromMessage((javax.mail.internet.MimeMessage) msg);
                    return surveylink;
                }
            }
            return null;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String GetSpedificMultipartTextEmailIfReceived(String expectedFromAddress, String expectedToAddress, String expectedSubject) {
        String strEmailContent="";

        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress, expectedSubject, 1);
            System.out.println("No of Total recent Messages : " + recentMessages.length);
            String fromAddress = expectedFromAddress;
            for (int i = recentMessages.length; i > 0; i--) {

                System.out.println("*****************************************************************************");
                System.out.println("MESSAGE " + (i) + ":");
                Message msg = recentMessages[i - 1];
                System.out.println(msg.getMessageNumber());
                String subject = msg.getSubject();//important value

                System.out.println("Subject: " + subject);
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("To: " + msg.getAllRecipients()[0]);//important value
                System.out.println("Date: " + msg.getReceivedDate());
                //  System.out.println("Message with Multipart: " + getText(msg));

                //  readLineByLineJava8("D:\\Bungii-QA-Automation\\Bungii.Test.Integration\\src\\main\\resources\\EmailTemplate\\BungiiReceipt.txt", getText(msg));
                //System.out.println("Size: "+msg.getSize());
                //System.out.println(msg.getFlags());
                if ((msg.getFrom()[0].toString().contains(fromAddress)) && (subject.equals(expectedSubject)) && (msg.getAllRecipients()[0].toString().toLowerCase().contains(expectedToAddress.toLowerCase()))) {
                    String EmailContent = msg.getContent().toString();
                    // System.out.println("Email Found!!!\nEmail Content: \n" + EmailContent);//need to get extract link value from here
                    //Invoke jSoupHTMLToString object
                    Document emailContent = Jsoup.parse(EmailContent);
                    strEmailContent =  emailUtility.readPlainContent((javax.mail.internet.MimeMessage) msg);

                    break;
                }


            }
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strEmailContent;

    }
    public String getCustomerSignupTemplate(String customerName)
    {
        String emailMessage = "";

        try{
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath())+"\\EmailTemplate\\CustomerSignup.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%CustomerName%",customerName)
                    ;
                    emailMessage += s;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return emailMessage;
    }
    public String GetSpecificPlainTextEmailIfReceived(String expectedFromAddress, String expectedToAddress, String expectedSubject) {

        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress, expectedSubject, 1);
            System.out.println("No of Total recent Messages : " + recentMessages.length);
            String fromAddress = expectedFromAddress;
            boolean emailFound = false;
            String emailContent = "";
            for (int i = recentMessages.length; i > 0; i--) {

                System.out.println("MESSAGE " + (i) + ":");
                Message msg = recentMessages[i - 1];
                System.out.println(msg.getMessageNumber());
                String subject = msg.getSubject();//important value

                System.out.println("Subject: " + subject);
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("To: " + msg.getAllRecipients()[0]);//important value
                System.out.println("Date: " + msg.getReceivedDate());
                System.out.println("Plain text: " + emailUtility.getTextFromMessage(msg));
                if ((msg.getFrom()[0].toString().contains(fromAddress)) && (subject.contains(expectedSubject)) && (msg.getAllRecipients()[0].toString().contains(expectedToAddress)))
                {
                    // String EmailContent = msg.getContent().toString();
                    emailContent =  emailUtility.readPlainContent((javax.mail.internet.MimeMessage) msg);
                    emailUtility.deleteEmailWithSubject(expectedSubject,null);
                    return emailContent;
                }
            }
            return null;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
    public boolean validateCustomerSignupEmail(String filePath, String emailValue, String customerName, String url1, String url2, String url3, String url4, String url5, String url6, String url7, String url8, String url9) throws IOException {
        boolean isEmailCorrect = false;
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\r\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileValue = contentBuilder.toString();
        Path p1 = Paths.get(filePath);

        List<String> listF1 = Files.readAllLines(p1);
        List<String> listF2 = Arrays.asList(emailValue.split("\r\n"));

        for (int i = 0; i < listF1.size(); i++) {
            if (listF1.get(i).contains("%CustomerName%")) {
                listF1.set(i, listF1.get(i).replace("%CustomerName%", customerName));
            }
            if (listF1.get(i).contains("%URL_1%")) {
                listF1.set(i, listF1.get(i).replace("%URL_1%", url1));
            }
            if (listF1.get(i).contains("%URL_2%")) {
                listF1.set(i, listF1.get(i).replace("%URL_2%", url2));
            }
            if (listF1.get(i).contains("%URL_3%")) {
                listF1.set(i, listF1.get(i).replace("%URL_3%", url3));
            }
            if (listF1.get(i).contains("%URL_4%")) {
                listF1.set(i, listF1.get(i).replace("%URL_4%", url4));
            }
            if (listF1.get(i).contains("%URL_5%")) {
                listF1.set(i, listF1.get(i).replace("%URL_5%", url5));
            }
            if (listF1.get(i).contains("%URL_6%")) {
                listF1.set(i, listF1.get(i).replace("%URL_6%", url6));
            }
            if (listF1.get(i).contains("%URL_7%")) {
                listF1.set(i, listF1.get(i).replace("%URL_7%", url7));
            }
            if (listF1.get(i).contains("%URL_8%")) {
                listF1.set(i, listF1.get(i).replace("%URL_8%", url8));
            }
            if (listF1.get(i).contains("%URL_9%")) {
                listF1.set(i, listF1.get(i).replace("%URL_9%", url9));
            }
        }
        if (listF1.size() == listF2.size()) {
            if ((listF1.equals(listF2))) {
                System.out.println("Both list are matching");
                isEmailCorrect = true;
            } else {
                isEmailCorrect = false;

                //both list are not matching ,iterate over all line to check value
                for (int i = 0; i < listF1.size(); i++) {

                    if (listF1.get(i).equals(listF2.get(i))) {

                    } else {
                        logger.detail("EMAIL MISMATCH |||" + i + "|||" + listF1.get(i) + "|||" + listF2.get(i));
                        //  System.out.println(listF1.get(i));
                        //  System.out.println(listF2.get(i));

                    }
                }
            }
        }

        return isEmailCorrect;
    }

    public String getExpectedPoorRatingMail(String driverName,String customerName,String ratingValue,String tripDetails)
    {
        String emailMessage = "";

        try{
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath())+"\\EmailTemplate\\PoorRatingEmail.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%DriverName%", driverName)
                            .replaceAll("%CustomerName%",customerName)
                            .replaceAll("%RatingValue%",ratingValue)
                            .replaceAll("%TripDetailsUrl%",tripDetails);
                    emailMessage += s;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedBungiiReceiptMail(String customerName,String driver1Name,String BungiiDate,String PickupAddress,String DropAddress,String BungiiDuration,String BungiiCost,String EstimateTime,String ActualTime,String EstimateloadTime,String ActualloadTime,String EstimateCost,String ActualCost)
    {
        String emailMessage = "";

        try{
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath())+"\\EmailTemplate\\BungiiSoloReceipt.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%Driver1%", driver1Name)
                            .replaceAll("%CustomerName%",customerName)
                            .replaceAll("%BungiiDate%",BungiiDate)
                            .replaceAll("%PickupAddress%",PickupAddress)
                            .replaceAll("%DropAddress%",DropAddress)
                            .replaceAll("%BungiiDuration%",BungiiDuration)
                            .replaceAll("%BungiiCost%",BungiiCost)
                            .replaceAll("%Estimate.Time%",EstimateTime)
                            .replaceAll("%Actual.Time%",ActualTime)
                            .replaceAll("%Estimate.loadTime%",EstimateloadTime)
                            .replaceAll("%Actual.loadTime%",ActualloadTime)
                            .replaceAll("%Estimate.Cost%",EstimateCost)
                            .replaceAll("%Actual.Cost%",ActualCost)
                    ;
                    emailMessage += s;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return emailMessage;
    }
    public void logCustomerDeviceToken(String phoneNumber){
        try {
            if(!phoneNumber.trim().equalsIgnoreCase(""))
                logger.detail("Device token of customer ["+phoneNumber+"] is "+dbUtility.getCustomerDeviceToken(phoneNumber));
        }catch (Exception e){
            logger.detail("Error getting deviceToken - ", ExceptionUtils.getStackTrace(e));
        }
    }
    public void logDriverDeviceToken(String phoneNumber){
        try {
            if(!phoneNumber.trim().equalsIgnoreCase(""))
                logger.detail("Device token of Driver ["+phoneNumber+"] is "+dbUtility.getDriverDeviceToken(phoneNumber));
        }catch (Exception e){
            logger.detail("Error getting deviceToken - ", ExceptionUtils.getStackTrace(e));
        }
    }
    public void logCustomerRecentTrip(String phoneNumber){
        try {
            if(!phoneNumber.trim().equalsIgnoreCase(""))
                logger.detail("Most recent trip of customer ["+phoneNumber+"] is with pickup ref "+dbUtility.getCustomersMostRecentBungii(phoneNumber));
        }catch (Exception e){
            logger.detail("Error getting deviceToken  ", ExceptionUtils.getStackTrace(e));
        }
    }

    public String calculateTeletTime() throws ParseException {

        String scheduledTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");

        // scheduledTime = "Dec 21, 11:15 AM GMT+5:30";

        Date bungiiDate = new SimpleDateFormat("MMM d, h:mm a").parse(scheduledTime);
        Date currentDate = new Date();


        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"); //phoneNumber="9403960189";
        String loadtime = (String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME");//, "15 mins");
        loadtime = loadtime.toLowerCase().replace("mins", "").replace("min", "").trim();
        String custRef = dbUtility.getCustomerRefference(phoneNumber);
        String estimateTime = dbUtility.getEstimateTime(custRef);
        long totalEstimateDuration = Integer.parseInt(loadtime) + Integer.parseInt(estimateTime);
        double timeToBeAdded = (totalEstimateDuration * 1.5) + 30; //30; //telet calc
        Date telet = DateUtils.addMinutes(bungiiDate, (int) timeToBeAdded);

        //int year=currentDate.getYear()+1900;
        telet.setYear(currentDate.getYear());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //By default data is in UTC
        //   dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String teletTimeInUtc = null;

        teletTimeInUtc = dateFormat.format(telet);
        return teletTimeInUtc;


    }
    public String GetAdminUrl() {
        String adminURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("DEV"))
            adminURL = PropertyUtility.getDataProperties("dev.admin.url");
        if (environment.equalsIgnoreCase("QA") || environment.equalsIgnoreCase("QA_AUTO")|| environment.equalsIgnoreCase("QA_AUTO_AWS"))
            adminURL = PropertyUtility.getDataProperties("qa.admin.url");
        if (environment.equalsIgnoreCase("STAGE"))
            adminURL = PropertyUtility.getDataProperties("stage.admin.url");
        return adminURL;
    }
    public String getTripTimezone(String geofence)
    {
        geofence= geofence.toLowerCase();
        String timezone = null;
        switch (geofence)
        {
            case "Washington DC":
            case "washingtondc":
                timezone = "EST";
                break;
            case "goa":
                timezone = "IST";
                break;
            default:
            case "Kansas City":
                timezone = "CST";
                break;

        }
        return timezone;

    }
    public void selectGeofenceDropdown(String geofence){
        action.click(admin_dashboardPage.List_Geofence());
        action.clearSendKeys(admin_dashboardPage.TextBox_SearchGeofence(),geofence);
        action.JavaScriptClick(admin_dashboardPage.Checkbox_Geofence(geofence));
        action.click(admin_dashboardPage.Button_ApplyGeofence());
    }
    public void reApplyGeofenceDropdown(){
        action.click(admin_dashboardPage.List_Geofence());
        action.click(admin_dashboardPage.Button_ApplyGeofence());
    }
    public String NavigateToPartnerLogin(String Site){

        String partnerURL = GetPartnerUrl(Site);
        action.deleteAllCookies();
        action.navigateTo(partnerURL);
        return partnerURL;
    }
    private String GetPartnerUrl(String PP_Site) {
        String partnerURL = null;
        cucumberContextManager.setScenarioContext("SiteUrl", PP_Site);
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("QA_AUTO") || environment.equalsIgnoreCase("QA_AUTO_AWS")) {
            if (PP_Site.equalsIgnoreCase("normal")) {
                partnerURL = PropertyUtility.getDataProperties("qa.partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("service level")) {
                partnerURL = PropertyUtility.getDataProperties("qa.service_level_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.service_level_partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("FloorDecor service level")) {
                partnerURL = PropertyUtility.getDataProperties("qa.fnd_service_level_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.fnd_service_level_partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("kiosk mode")) {
                partnerURL = PropertyUtility.getDataProperties("qa.kiosk_mode_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.kiosk_mode_partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("BestBuy service level")) {
                partnerURL = PropertyUtility.getDataProperties("qa.bestbuy.service_level_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.bestbuy.service_level_partner.ref"));

            } else if (PP_Site.equalsIgnoreCase("Cort service level")) {
                partnerURL = PropertyUtility.getDataProperties("qa.cort_service_level_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.cort_service_level_partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("BestBuy2 service level")) {
                partnerURL = PropertyUtility.getDataProperties("qa.bestbuy2.service_level_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.bestbuy2.service_level_partner.ref"));
            }
        }
        return partnerURL;
    }
    public long Milliseconds_To_Minutes(long milliseconds){
        long minutes = (milliseconds / 1000) / 60;
        return minutes;
    }
    public String getABungiiDriverIsHeadingYourWay(String driverName,String driverPhone,String driverCarLicenceNumber,String Customer_Name) {
        String emailMessage = "";
        FileReader fr;
        try {
            fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\ABungiiDriverIsHeadingYourWay.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%DriverName%",driverName);
                    s = s.replaceAll("%DriverPhoneNumber%",driverPhone);
                    s = s.replaceAll("%driverCarLicenceNumber%",driverCarLicenceNumber);
                    s = s.replaceAll("%CustomerName%",Customer_Name);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }
    public String getABungiiDeliveryScheduled(String pickUp,String pickupdate,String Customer_Name ,String CustomerPhone ,String driverName,String driverPhone,String driverCarLicenceNumber,String itemsToDeliver,String specialInstructions,String deliveryPurpose,String rbsbNumber,String scheduledBy) {
        String emailMessage = "";
        FileReader fr;
        try {
            fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\BungiiDeliveryScheduled.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%pickupLocation%",pickUp);
                    s = s.replaceAll("%TimeStamp%",pickupdate);
                    s = s.replaceAll("%CustomerName%",Customer_Name);
                    s = s.replaceAll("%CustomerPhone%",CustomerPhone);
                    s = s.replaceAll("%DriverName%",driverName);
                    s = s.replaceAll("%DriverPhoneNumber%",driverPhone);
                    s = s.replaceAll("%driverCarLicenceNumber%",driverCarLicenceNumber);
                    s = s.replaceAll("%itemsToDeliver%",itemsToDeliver);
                    s = s.replaceAll("%SpecialInstructions%",specialInstructions);
                    s = s.replaceAll("%DeliveryPurpose%",deliveryPurpose);
                    s = s.replaceAll("%rbsbNumber%",rbsbNumber);
                    s = s.replaceAll("%scheduledBy%",scheduledBy);


                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getABungiiDriverHasArrived(String driverName,String driverPhone,String driverCarLicenceNumber,String Customer_Name) {
        String emailMessage = "";
        FileReader fr;
        try {
            fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\ABungiiDriverHasArrived.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%DriverName%",driverName);
                    s = s.replaceAll("%DriverPhoneNumber%",driverPhone);
                    s = s.replaceAll("%driverCarLicenceNumber%",driverCarLicenceNumber);
                    s = s.replaceAll("%CustomerName%",Customer_Name);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }
}