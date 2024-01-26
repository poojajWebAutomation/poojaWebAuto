package com.bungii.web.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.utilities.EmailUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.web.manager.*;
import com.bungii.web.pages.admin.Admin_DriversPage;
import com.bungii.web.pages.admin.Admin_GeofencePage;
import com.bungii.web.pages.admin.Admin_LoginPage;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.driver.Driver_RegistrationPage;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_LoginPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.io.*;
import java.util.Base64;
import org.apache.commons.io.IOUtils;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bungii.web.utilityfunctions.DbUtility.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static com.bungii.common.manager.ResultManager.error;

public class GeneralUtility extends DriverBase {
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    private static LogUtility logger = new LogUtility(com.bungii.android.manager.ActionManager.class);
    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    DbUtility dbUtility = new DbUtility();
    ActionManager action = new ActionManager();
    Driver_DashboardPage driver_dashboardPage = new Driver_DashboardPage();
    Admin_LoginPage Page_AdminLogin = new Admin_LoginPage();
    Partner_LoginPage Page_PartnerLogin = new Partner_LoginPage();
    EmailUtility emailUtility = new EmailUtility();
    Partner_DashboardPage partner_dashboardPage = new Partner_DashboardPage();
    Admin_GeofencePage admin_geofencePage = new Admin_GeofencePage();
    Admin_DriversPage admin_DriverPage=new Admin_DriversPage();

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
            }
            else if (PP_Site.equalsIgnoreCase("FloorDecor service level #214")) {
                partnerURL = PropertyUtility.getDataProperties("qa.fnd_service_level#214_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.fnd_service_level#214_partner.ref"));
            }
            else if (PP_Site.equalsIgnoreCase("kiosk mode")) {
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
            } else if (PP_Site.equalsIgnoreCase("Equip-bid")) {
                partnerURL = PropertyUtility.getDataProperties("qa.equip-bid.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.equip-bid.ref"));
            } else if (PP_Site.equalsIgnoreCase("fnd multiple phone")) {
                partnerURL = PropertyUtility.getDataProperties("qa.floordecor130_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.floordecor130_partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("Home Outlet")) {
                partnerURL = PropertyUtility.getDataProperties("qa.home.outlet.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.home.outlet.ref"));
            }
        }
        return partnerURL;
    }

    private String GetDriverUrl() {
        String driverURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("DEV"))
            driverURL = PropertyUtility.getDataProperties("dev.driver.url");
        if (environment.equalsIgnoreCase("QA") || environment.equalsIgnoreCase("QA_AUTO") || environment.equalsIgnoreCase("QA_AUTO_AWS"))
            driverURL = PropertyUtility.getDataProperties("qa.driver.url");
        if (environment.equalsIgnoreCase("STAGE"))
            driverURL = PropertyUtility.getDataProperties("stage.driver.url");
        return driverURL;
    }

    public String GetAdminUrl() {
        String adminURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("DEV"))
            adminURL = PropertyUtility.getDataProperties("dev.admin.url");
        if (environment.equalsIgnoreCase("QA") || environment.equalsIgnoreCase("QA_AUTO") || environment.equalsIgnoreCase("QA_AUTO_AWS"))
            adminURL = PropertyUtility.getDataProperties("qa.admin.url");
        if (environment.equalsIgnoreCase("STAGE"))
            adminURL = PropertyUtility.getDataProperties("stage.admin.url");
        return adminURL;
    }

    public String GetQueryPanelUrl() {
        String queryPanelURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("DEV"))
            queryPanelURL = PropertyUtility.getDataProperties("dev.querypanel.url");
        if (environment.equalsIgnoreCase("QA") || environment.equalsIgnoreCase("QA_AUTO") || environment.equalsIgnoreCase("QA_AUTO_AWS"))
            queryPanelURL = PropertyUtility.getDataProperties("qa.querypanel.url");
        if (environment.equalsIgnoreCase("STAGE"))
            queryPanelURL = PropertyUtility.getDataProperties("stage.querypanel.url");
        return queryPanelURL;
    }

    public String GetBungiiUrl() {
        String adminURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("DEV"))
            adminURL = PropertyUtility.getDataProperties("dev.bungii.url");
        if (environment.equalsIgnoreCase("QA") || environment.equalsIgnoreCase("QA_AUTO") || environment.equalsIgnoreCase("QA_AUTO_AWS"))
            adminURL = PropertyUtility.getDataProperties("qa.bungii.url");
        if (environment.equalsIgnoreCase("STAGE"))
            adminURL = PropertyUtility.getDataProperties("stage.bungii.url");
        return adminURL;
    }

    public String getCurrentUrl() throws InterruptedException {

        Thread.sleep(5000);
        String adminURL = SetupManager.getObject().getCurrentUrl();
        return adminURL;
    }

    public void DriverLogin(String Phone, String Password) {
        String driverURL = GetDriverUrl();

        action.navigateTo(driverURL);
        action.click(Page_Driver_Login.Tab_LogIn());
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), Phone);
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), Password);
        action.click(Page_Driver_Login.Button_DriverLogin());
    }

    public void NavigateToDriverLogin() {
        String driverURL = GetDriverUrl();
        action.deleteAllCookies();
        action.navigateTo(driverURL);
    }

    public String NavigateToPartnerLogin(String Site) {

        String partnerURL = GetPartnerUrl(Site);
        action.deleteAllCookies();
        action.navigateTo(partnerURL);
        return partnerURL;
    }

    public void AdminLogin() throws InterruptedException {
        String adminURL = GetAdminUrl();
        Thread.sleep(2000);
        action.navigateTo(adminURL);
        action.sendKeys(Page_AdminLogin.TextBox_Phone(), PropertyUtility.getDataProperties("admin.user"));
        action.sendKeys(Page_AdminLogin.TextBox_Password(), PropertyUtility.getDataProperties("admin.password"));
        action.click(Page_AdminLogin.Button_AdminLogin());
    }

    public void QueryPanelLogin() throws InterruptedException {
        String querypanelURL = GetQueryPanelUrl();
        Thread.sleep(2000);
        action.navigateTo(querypanelURL);
        action.sendKeys(Page_AdminLogin.TextBox_QueryPanelPhone(), PropertyUtility.getDataProperties("admin.user"));
        action.sendKeys(Page_AdminLogin.TextBox_QueryPanelPassword(), PropertyUtility.getDataProperties("admin.password"));
        action.click(Page_AdminLogin.Button_QueryPanelLogin());
    }

    public void NavigateToAdminPortal() throws InterruptedException {
        String adminURL = GetAdminUrl();
        Thread.sleep(2000);
        action.navigateTo(adminURL);
    }

    public void NavigateToBungiiPortal() throws InterruptedException {
        String bungiiURL = GetBungiiUrl();
        Thread.sleep(2000);
        action.navigateTo(bungiiURL);
    }

    public void AdminLoginFromPartner() throws InterruptedException {
        String adminURL = GetAdminUrl();
        Thread.sleep(2000);
        action.openNewTab();
        action.navigateTo(adminURL);
        action.sendKeys(Page_AdminLogin.TextBox_Phone(), PropertyUtility.getDataProperties("admin.user"));
        action.sendKeys(Page_AdminLogin.TextBox_Password(), PropertyUtility.getDataProperties("admin.password"));
        action.click(Page_AdminLogin.Button_AdminLogin());
    }

    public void NavigateDriverRatingWebLink() throws InterruptedException {
        String URL = (String) cucumberContextManager.getScenarioContext("PartnerPortalURL");
        String Pickup_Id = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String pickup_Token = DbUtility.getPickupToken(Pickup_Id);
        URL = URL.replace("login", "Pickup/" + pickup_Token);
        action.openNewTab();
        action.navigateTo(URL);
        Thread.sleep(5000);

    }

    public void TestAdminLogin() {
        String adminURL = GetAdminUrl();

        action.navigateTo(adminURL);
        action.sendKeys(Page_AdminLogin.TextBox_Phone(), PropertyUtility.getDataProperties("admin.testuser"));
        action.sendKeys(Page_AdminLogin.TextBox_Password(), PropertyUtility.getDataProperties("admin.testpassword"));
        action.click(Page_AdminLogin.Button_AdminLogin());
    }

    public void DriverLogout() {
        action.click(driver_dashboardPage.Link_Logout());
    }

    public void PartnerLogout() {
        action.click(partner_dashboardPage.Button_Partner_Logout());
    }

    public String generateMobileNumber() {

        String phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");
        while (!DbUtility.isPhoneNumberUnique(phoneNumber)) {
            phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");

        }
        return phoneNumber;
    }


    public void addImageInDropZone(WebElement dropZoneWebelement, String filePath) {

        ((JavascriptExecutor) SetupManager.getDriver()).executeScript(" arguments[0].style.visibility = 'visible';", dropZoneWebelement);
        action.sendKeys(dropZoneWebelement, filePath);

    }

    public void addCSV(WebElement csvWebelement, String filePath) {

        ((JavascriptExecutor) SetupManager.getDriver()).executeScript(" arguments[0].style.visibility = 'visible';", csvWebelement);
        action.sendKeys(csvWebelement, filePath);

    }

    public void addImageInDropZone(WebElement dropZoneWebelement, String[] filePath) {
        String inputFilesString = "";
        for (String file : filePath) {
            inputFilesString = inputFilesString + " \n " + file;
        }
        ((JavascriptExecutor) SetupManager.getDriver()).executeScript(" arguments[0].style.visibility = 'visible';", dropZoneWebelement);
        action.sendKeys(dropZoneWebelement, inputFilesString.trim());

    }

    public String GetUniqueLastName() {
        String Lastname = RandomGeneratorUtility.getData("{RANDOM_STRING}", 4);
        Lastname.toLowerCase();
        Lastname = Convert(Lastname);
        Lastname = StringUtils.capitalize(Lastname.toLowerCase());
        return Lastname;

    }

    public static String GetFormattedString(String string, String word) {
        // Check if the word is present in string
        // If found, remove it using removeAll()
        if (string.contains(word)) {
            string = string.replaceAll(word, "");
        }

        // Return the resultant string
        return string;
    }

    public String GetUniqueFutureDate() {
        String newDate = null;
        String DATE_FORMAT = "MM/DD/YYYY";
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

        // Get current date
        Date currentDate = new Date();
        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 1 to 12 for months and 1 to 30 for days
        int months = rand.nextInt(12);
        int days = rand.nextInt(30);

        // convert date to localdatetime
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // plus one
        localDateTime = localDateTime.plusYears(1).plusMonths(months).plusDays(days);
        localDateTime = localDateTime.plusHours(1).plusMinutes(2).minusMinutes(1).plusSeconds(1);

        // convert LocalDateTime to date
        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        String futureNewDate[] = dateFormat.format(currentDatePlusOneDay).split("/");
        if (Integer.toString(days).length() == 1) {
            String daysStartsWithZero = "0" + days;
            newDate = futureNewDate[0] + "/" + daysStartsWithZero + "/" + futureNewDate[2];
        } else {
            newDate = futureNewDate[0] + "/" + days + "/" + futureNewDate[2];
        }
//        newDate = dateFormat.format(currentDatePlusOneDay);
        return newDate;
    }

    public String GetDateInFormat(String DateToFormat, String FromFormat, String ToFormat) {
        //date format you will get is e.g. Nov 21, 2020
        String newDateFormat = null;
        try {
            String start_dt = DateToFormat;
            DateFormat formatter = new SimpleDateFormat(FromFormat);
            Date date = (Date) formatter.parse(start_dt);
            SimpleDateFormat newFormat = new SimpleDateFormat(ToFormat);
            newDateFormat = newFormat.format(date);
        } catch (Exception e) {
            System.out.println(e);
        }
        return newDateFormat;
    }

    public String GenerateSpecialCharString() {
        int RANDOM_STRING_LENGTH = 5;
        String CHAR_LIST = "$&#@!%?+";
        StringBuffer randStr = new StringBuffer();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int number = GetRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        System.out.println("String of special characters: " + randStr);
        return randStr.toString();
    }

    private int GetRandomNumber() {
        String CHAR_LIST = "$&#@!%?+";
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

    public String GetSpecificPlainTextEmailIfReceived(String expectedFromAddress, String expectedToAddress, String expectedSubject) {

        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress, expectedSubject, 1);
            System.out.println("No of Total recent Messages : " + recentMessages.length);
            String fromAddress = PropertyUtility.getEmailProperties("email.from.address");
            boolean emailFound = false;
            String emailContent = "";
            for (int i = recentMessages.length; i > 0; i--) {

                System.out.println("***********");
                Message msg = recentMessages[i - 1];
                //   System.out.println(msg.getMessageNumber());
                String subject = msg.getSubject();//important value
                String from = String.valueOf(msg.getFrom()[0]).toLowerCase();
                String recipient = String.valueOf(msg.getAllRecipients()[0]);

                System.out.println("Subject: " + subject + " | Date: " + msg.getReceivedDate());
                // System.out.println("From: " + msg.getFrom()[0]);
                // System.out.println("To: " + msg.getAllRecipients()[0]);//important value
                System.out.println();
                // System.out.println("Plain text: " + emailUtility.getTextFromMessage(msg));
                if ((from.contains(fromAddress.toLowerCase())) && (subject.contains(expectedSubject)) && (recipient.contains(expectedToAddress))) {
                    // String EmailContent = msg.getContent().toString();
                    emailContent = emailUtility.readPlainContent((javax.mail.internet.MimeMessage) msg);
                    emailUtility.deleteEmailWithSubject(expectedSubject, null);
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
    public String GetSpecificVerificationcodeEmailIfReceived(String expectedFromAddress, String expectedToAddress, String expectedSubject) {

        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress, expectedSubject, 1);
            System.out.println("No of Total recent Messages : " + recentMessages.length);
            String fromAddress = PropertyUtility.getEmailProperties("email.from.driver.address");
            boolean emailFound = false;
            String emailContent = "";
            for (int i = recentMessages.length; i > 0; i--) {

                System.out.println("***********");
                Message msg = recentMessages[i - 1];
                String subject = msg.getSubject();//important value
                String from = String.valueOf(msg.getFrom()[0]).toLowerCase();
                String recipient = String.valueOf(msg.getAllRecipients()[0]);

                System.out.println("Subject: " + subject + " | Date: " + msg.getReceivedDate());
                System.out.println();
                if ((from.contains(fromAddress.toLowerCase())) && (subject.contains(expectedSubject)) && (recipient.contains(expectedToAddress)))
                {
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

    public void GetSpedificMultipartTextEmailIfReceived(String expectedFromAddress, String expectedToAddress, String expectedSubject, String expectedEmailContent) {

        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress, expectedSubject, 1);
            System.out.println("No of Total recent Messages : " + recentMessages.length);
            String fromAddress = PropertyUtility.getEmailProperties("email.from.address");

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
                if ((msg.getFrom()[0].toString().contains(fromAddress)) && (subject.equals(expectedSubject)) && (msg.getAllRecipients()[0].toString().contains(expectedToAddress))) {
                    String EmailContent = msg.getContent().toString();
                    // System.out.println("Email Found!!!\nEmail Content: \n" + EmailContent);//need to get extract link value from here
                    //Invoke jSoupHTMLToString object
                    Document emailContent = Jsoup.parse(EmailContent);

                }


            }

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String GetSpecificURLs(String expectedFromAddress, String expectedToAddress, String expectedSubject) {


        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress, expectedSubject, 1);
            System.out.println("No of Total recent Messages : " + recentMessages.length);
            String fromAddress = PropertyUtility.getEmailProperties("email.from.address");
            boolean emailFound = false;
            String surveylink = "";
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
                if ((msg.getFrom()[0].toString().contains(fromAddress)) && (subject.contains(expectedSubject)) && (msg.getAllRecipients()[0].toString().contains(expectedToAddress))) {
                    surveylink = emailUtility.getURLFromMessage((javax.mail.internet.MimeMessage) msg);
                    emailUtility.deleteEmailWithSubject(expectedSubject, null);
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

    public String generateScheduledBungiiCSV(String filePath, String timezone, int noOfDrivers, String customerName, String customerPhone) {
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
        DateFormat dateformatter = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeformatter = new SimpleDateFormat("hh:mm a");
        DateFormat dateformatterFile = new SimpleDateFormat("MMddyyyyHHmmSS");
        DateFormat pickuptimeformatter = new SimpleDateFormat("EEEE, MMMM dd, yyyy hh:mm a z");

        Date target;
        Date date = new Date();
        if (TimeZone.getTimeZone("America/New_York").inDaylightTime(date))
            date = DateUtils.setHours(date, date.getHours() + 1); //set 1 hour later if daylight savings is set

        int minutes = ((int) Math.ceil(date.getMinutes() / 15d) * 15 + 30);

        if (minutes >= 60) {
            minutes = minutes - 60;
            target = DateUtils.setMinutes(date, minutes); //set minute
            target = DateUtils.setHours(target, target.getHours() + 1); //set minute
        } else {
            target = DateUtils.setMinutes(date, minutes); //set minute
        }


        String pickupTime = pickuptimeformatter.format(target);
        cucumberContextManager.setScenarioContext("PICKUP_TIME", pickupTime);
        cucumberContextManager.setScenarioContext("TIMEZONE", timezone);

        String newFilePath = new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\BulkTrip_Runtime_" + dateformatterFile.format(date) + ".csv";
        String Line;
        String encoding = "UTF-8";
        try {
            List<String> inputLines = Files.readAllLines(Paths.get(filePath), UTF_8);
            ArrayList<String> address = getPartnerFirmAddress(timezone);
            List<String> fixedLines = new ArrayList<>(inputLines.size());

            for (String line : inputLines) {
                fixedLines.add(line.replace("DATE_TO_REPLACE", dateformatter.format(target))
                        .replace("TIME_TO_REPLACE", timeformatter.format(target))
                        .replace("PICKUPADDRESS_TO_REPLACE", address.get(0))
                        .replace("DROPOFFADDRESS_TO_REPLACE", address.get(1))
                        .replace("NOOFDRIVERS_TO_REPLACE", String.valueOf(noOfDrivers))
                        .replace("TIMEZONE_TO_REPLACE", timezone)
                        .replace("CUSTOMERNAME_TO_REPLACE", customerName)
                        .replace("CUSTOMERPHONE_TO_REPLACE", customerPhone)
                        .replace("LOAD_TO_REPLACE", "15"));
            }
            logger.detail("File Request Row : " + fixedLines);
            Files.write(Paths.get(newFilePath), fixedLines, UTF_8, CREATE);

        } catch (Exception ex) {
            newFilePath = null;
            ex.printStackTrace();
        }
        return newFilePath;
    }

    private ArrayList<String> getPartnerFirmAddress(String timezone) {
        ArrayList<String> address = new ArrayList<String>();
        switch (timezone) {
            case "EST":
            case "America/New_York":
                address.add((String) PropertyUtility.getDataProperties("washington.Partner.Firm.Pickup.Address"));
                address.add((String) PropertyUtility.getDataProperties("washington.Partner.Firm.Dropoff.Address"));
                break;

        }
        return address;
    }

    public String getExpectedPartnerFirmScheduledEmailContent(String pickupdate, String customerName, String customerPhone, String customerEmail, String driverName, String driverPhone, String driverLicencePlate, String supportNumber, String firmName) {
        String emailMessage = "";
        FileReader fr;
        try {
            {
                if (SystemUtils.IS_OS_WINDOWS) {
                    fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\PartnerFirmScheduledEmail.txt");
                } else {
                    fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/EmailTemplate/PartnerFirmScheduledEmail.txt");
                }
                String s;
                try (

                        BufferedReader br = new BufferedReader(fr)) {

                    while ((s = br.readLine()) != null) {
                        s = s.replaceAll("%TimeStamp%", pickupdate)
                                .replaceAll("%CustomerName%", customerName)
                                .replaceAll("%CustomerPhone%", customerPhone)
                                .replaceAll("%CustomerEmail%", customerEmail)
                                .replaceAll("%DriverName%", driverName)
                                .replaceAll("%DriverPhone%", driverPhone)
                                .replaceAll("%DriverLicencePlate%", driverLicencePlate)
                                .replaceAll("%SupportNumber%", supportNumber)
                                .replaceAll("%FirmName%", firmName);
                        emailMessage += s;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedPartnerFirmDuoScheduledEmailContent(String pickupdate, String customerName, String customerPhone, String customerEmail, String driverName, String driverPhone, String driverLicencePlate, String driverName1, String driverPhone1, String supportNumber, String firmName) throws IOException {

    String emailMessage ="";
    FileReader fr;
        try {
        {
            if (SystemUtils.IS_OS_WINDOWS) {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\PartnerFirmDuoScheduledEmail.txt");
            } else {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/EmailTemplate/PartnerFirmDuoScheduledEmail.txt");
            }
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%TimeStamp%", pickupdate)
                            .replaceAll("%CustomerName%", customerName)
                            .replaceAll("%CustomerPhone%", customerPhone)
                            .replaceAll("%CustomerEmail%", customerEmail)
                            .replaceAll("%DriverName%", driverName)
                            .replaceAll("%DriverPhone%", driverPhone)
                            .replaceAll("%DriverLicencePlate%", driverLicencePlate)
                            .replaceAll("%DriverName1%", driverName1)
                            .replaceAll("%DriverPhone1%", driverPhone1)
                            .replaceAll("%SupportNumber%", supportNumber)
                            .replaceAll("%FirmName%", firmName);
                    emailMessage += s;
                }
            }
        }
    }catch(
    Exception e)

    {
        e.printStackTrace();
    }
        return emailMessage;
    }

    public String getExpectedPartnerFirmUpdatedEmailContent(String pickupdate, String customerName, String customerPhone, String customerEmail, String driverName, String driverPhone, String driverLicencePlate, String supportNumber, String firmName) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\PartnerFirmUpdatedEmail.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%TimeStamp%", pickupdate)
                            .replaceAll("%CustomerName%", customerName)
                            .replaceAll("%CustomerPhone%", customerPhone)
                            .replaceAll("%CustomerEmail%", customerEmail)
                            .replaceAll("%DriverName%", driverName)
                            .replaceAll("%DriverPhone%", driverPhone)
                            .replaceAll("%DriverLicencePlate%", driverLicencePlate)
                            .replaceAll("%SupportNumber%", supportNumber)
                            .replaceAll("%FirmName%", firmName);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedPartnerFirmCanceledEmailContent(String customerName, String customerPhone, String customerEmail, String driverName, String supportNumber, String firmName) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\PartnerFirmCanceledEmail.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%CustomerName%", customerName)
                            .replaceAll("%CustomerPhone%", customerPhone)
                            .replaceAll("%CustomerEmail%", customerEmail)
                            .replaceAll("%DriverName%", driverName)
                            .replaceAll("%SupportNumber%", supportNumber)
                            .replaceAll("%FirmName%", firmName);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedPartnerFirmInitialDeliveriesEmailContent(String firmName, String deliveryCount) {
        String emailMessage = "";
        FileReader fr;
        try {
            if (SystemUtils.IS_OS_WINDOWS) {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\PartnerPortalIntialDeliveriesEmail.txt");
            } else {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/EmailTemplate/PartnerPortalIntialDeliveriesEmail.txt");
            }
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%PartnerName%", firmName);
                    s = s.replaceAll("%DeliveryCount%", deliveryCount);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedPartnerFirmFirstEmailContent(String firmName) {
        String emailMessage = "";
        FileReader fr;
        try {
            if (SystemUtils.IS_OS_WINDOWS) {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\PartnerPortalFirstScheduleDeliveryEmail.txt");
            } else {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/EmailTemplate/PartnerPortalFirstScheduleDeliveryEmail.txt");
            }
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%PartnerName%", firmName);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedPartnerFirmSecondEmailForScheduledDeliveryBeforeFirstDeliveryContent(String firmName) {
        String emailMessage = "";
        FileReader fr;
        try {
            fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\PartnerPortalSecondEmailForScheduledDeliveryBeforeFirstDelivery.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%PartnerName%", firmName);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedPartnerDeliveryScheduledBeyondSecondaryPolyline(String scheduled_Date, String pickup_Address, String dropup_Address, String customer_Name, String customer_Phone, String items_To_Deliver, String pickup_Contact_Name, String pickup_Contact_Phone, String tracking_Id) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\DeliveryScheduledBeyondSecondaryPolyline.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%ScheduledDate%", scheduled_Date)
                            .replaceAll("%PickupAddress%", pickup_Address)
                            .replaceAll("%DropupAddress%", dropup_Address)
                            .replaceAll("%CustomerName%", customer_Name)
                            .replaceAll("%CustomerPhone%", customer_Phone)
                            .replaceAll("%ItemsToDeliver%", items_To_Deliver)
                            .replaceAll("%PickupContactName%", pickup_Contact_Name)
                            .replaceAll("%PickupContactPhone%", pickup_Contact_Phone)
                            .replaceAll("%TrackingId%", tracking_Id);
                    emailMessage += s;
                }

            }
        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to read email for Partner Cancel delivery with driver",
                    true);
        }

        return emailMessage;
    }

    public String getExpectedDeliveryScheduledBeyondSecondaryPolyline(String scheduled_Date, String pickup_Address, String dropup_Address, String customer_Name, String customer_Phone, String tracking_Id) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\CustomerDeliveryScheduledBeyondSecondaryPolyline.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%ScheduledDate%", scheduled_Date)
                            .replaceAll("%PickupAddress%", pickup_Address)
                            .replaceAll("%DropupAddress%", dropup_Address)
                            .replaceAll("%CustomerName%", customer_Name)
                            .replaceAll("%CustomerPhone%", customer_Phone)
                            .replaceAll("%TrackingId%", tracking_Id);
                    emailMessage += s;
                }

            }
        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to read email for Partner Cancel delivery with driver",
                    true);
        }

        return emailMessage;
    }

    public String getExpectedPartnerPortalCanceledEmailContentWithDriver(String partner_Name, String scheduled_Date, String pickup_Address, String dropup_Address, String customer_Name, String customer_Phone, String driverName, String driverPhone, String driverLicencePlate, String items_To_Deliver, String pickup_Contact_Name, String pickup_Contact_Phone) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\PartnerPortalCanceledEmailWithDriver.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%PartnerName%", partner_Name)
                            .replaceAll("%ScheduledDate%", scheduled_Date)
                            .replaceAll("%PickupAddress%", pickup_Address)
                            .replaceAll("%DropupAddress%", dropup_Address)
                            .replaceAll("%CustomerName%", customer_Name)
                            .replaceAll("%CustomerPhone%", customer_Phone)
                            .replaceAll("%DriverName%", driverName)
                            .replaceAll("%DriverPhone%", driverPhone)
                            .replaceAll("%DriverLicencePlate%", driverLicencePlate)
                            .replaceAll("%ItemsToDeliver%", items_To_Deliver)
                            .replaceAll("%PickupContactName%", pickup_Contact_Name)
                            .replaceAll("%PickupContactPhone%", pickup_Contact_Phone);
                    emailMessage += s;
                }

            }
        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to read email for Partner Cancel delivery with driver",
                    true);
        }

        return emailMessage;
    }

    public String getExpectedPartnerPortalCanceledEmailContentWithoutDriver(String partner_Name, String scheduled_Date, String pickup_Address, String dropup_Address, String customer_Name, String customer_Phone, String items_To_Deliver, String pickup_Contact_Name, String pickup_Contact_Phone) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\PartnerPortalCanceledEmailWithoutDriver.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%PartnerName%", partner_Name)
                            .replaceAll("%ScheduledDate%", scheduled_Date)
                            .replaceAll("%PickupAddress%", pickup_Address)
                            .replaceAll("%DropupAddress%", dropup_Address)
                            .replaceAll("%CustomerName%", customer_Name)
                            .replaceAll("%CustomerPhone%", customer_Phone)
                            .replaceAll("%ItemsToDeliver%", items_To_Deliver)
                            .replaceAll("%PickupContactName%", pickup_Contact_Name)
                            .replaceAll("%PickupContactPhone%", pickup_Contact_Phone);
                    emailMessage += s;
                }

            }
        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to read email for Partner Cancel delivery without driver",
                    true);
        }

        return emailMessage;
    }

    public String getExpectedFailedTripEmailContent(String pickupId, String pickupRef, String pickupStatus, String customerName, String customerPhone, String pickupLocation, String pickupAddress) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\FailedTripEmail.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%PickupId%", pickupId)
                            .replaceAll("%CustomerName%", customerName)
                            .replaceAll("%CustomerPhone%", customerPhone)
                            .replaceAll("%PickupRef%", pickupRef)
                            .replaceAll("%PickupStatus%", pickupStatus)
                            .replaceAll("%PickupLocation%", pickupLocation)
                            .replaceAll("%PickupAddress%", pickupAddress);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedDriverRejectionEmailContent(String driverName) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\DriverRejectionEmail.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%DriverName%", driverName);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedDriverSubmissionEmailContent(String driverName) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\DriverSubmissionEmail.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%DriverName%", driverName);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedDriverActionRequiredEmailContent(String driverName) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\DriverActionRequiredEmail.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%DriverName%", driverName);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }
    public String getExpectedBungiiRefundCustomerEmail(String customerName,String deliveryTotal,String pickUpRefernce) {
        String emailMessage = "";
        FileReader fr;

        try {
            if (SystemUtils.IS_OS_WINDOWS) {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\BungiiRefundCustomerEmail.txt");
            } else {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/EmailTemplate/BungiiRefundCustomerEmail.txt");
            }
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%CustomerName%", customerName);
                    s = s.replaceAll("%DeliveryTotal%", deliveryTotal);
                    s = s.replaceAll("%PickUpReference%", pickUpRefernce);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }
    public String getExpectedBungiiRefundAdminEmail(String customerName,String deliveryTotal,String driverName,String pickUp,String dropOff) {
        String emailMessage = "";
        FileReader fr;

        try {
            if (SystemUtils.IS_OS_WINDOWS) {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\BungiiRefundAdminEmail.txt");
            } else {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/EmailTemplate/BungiiRefundAdminEmail.txt");
            }
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%CustomerName%", customerName);
                    s = s.replaceAll("%DeliveryTotal%", deliveryTotal);
                    s = s.replaceAll("%DriverName%", driverName);
                    s = s.replaceAll("%PickUp%", pickUp);
                    s = s.replaceAll("%DropOff%", dropOff);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }
    public String getExpectedPartnerFirmEmailForDropOffAddressEdit(String firmName,String pickUpdate,String pickUpAddress,String dropOffAddress,String serviceLevel,String deliveryCost,String customerName,String customerPhone,String driverName,String driverPhone,String driverLicencePlate) {
        String emailMessage = "";
        FileReader fr;
        try {
            if (SystemUtils.IS_OS_WINDOWS) {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\BungiiUpdateDropOffEdit.txt");
            } else {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/EmailTemplate/BungiiUpdateDropOffEdit.txt");
            }
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%PickUpDate%", pickUpdate);
                    s = s.replaceAll("%PickUpAddress%", pickUpAddress);
                    s = s.replaceAll("%DropOffAddress%", dropOffAddress);
                    s = s.replaceAll("%ServiceLevel%", serviceLevel);
                    s = s.replaceAll("%DeliveryCost%", deliveryCost);
                    s = s.replaceAll("%CustomerName%", customerName);
                    s = s.replaceAll("%CustomerPhone%", customerPhone);
                    s = s.replaceAll("%DriverName%", driverName);
                    s = s.replaceAll("%DriverPhone%", driverPhone);
                    s = s.replaceAll("%LicencePlate%", driverLicencePlate);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }
    public String getExpectedPartnerFirmEmailForPickUpAddressEdit(String firmName,String pickUpdate,String pickUpAddress,String dropOffAddress,String serviceLevel,String deliveryCost,String customerName,String customerPhone,String driverName,String driverPhone,String driverLicencePlate) {
        String emailMessage = "";
        FileReader fr;
        try {
            if (SystemUtils.IS_OS_WINDOWS) {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\BungiiUpdatePickUpEdit.txt");
            } else {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/EmailTemplate/BungiiUpdatePickUpEdit.txt");
            }
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%FirmName%", firmName);
                    s = s.replaceAll("%PickUpDate%", pickUpdate);
                    s = s.replaceAll("%PickUpAddress%", pickUpAddress);
                    s = s.replaceAll("%DropOffAddress%", dropOffAddress);
                    s = s.replaceAll("%ServiceLevel%", serviceLevel);
                    s = s.replaceAll("%DeliveryCost%", deliveryCost);
                    s = s.replaceAll("%CustomerName%", customerName);
                    s = s.replaceAll("%CustomerPhone%", customerPhone);
                    s = s.replaceAll("%DriverName%", driverName);
                    s = s.replaceAll("%DriverPhone%", driverPhone);
                    s = s.replaceAll("%LicencePlate%", driverLicencePlate);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getExpectedDriverApprovalEmailContent(String driverName) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\DriverApprovalEmail.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%DriverName%", driverName);
                    s = s.replaceAll("ā€™", "'");
                    s = s.replaceAll("’", "'");
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
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
        if (TimeZone.getTimeZone(getGeofenceTimeZone).inDaylightTime(new Date()))
            getGeofenceTimeZone = getGeofenceTimeZone.replace("ST", "DT");
        return getGeofenceTimeZone;
    }

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


    public String getExpectedDriverRegistrationCompleteEmailContent(String driverName, String driverPhone) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "\\EmailTemplate\\DriverRegistrationCompleteEmail.txt");
            String s;

            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%driverFullName%", driverName)
                            .replaceAll("%driverPhone%", driverPhone);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String getTripTimezone(String geofence) {
        geofence = geofence.toLowerCase();
        String timezone = null;
        switch (geofence) {
            case "Washington DC":
            case "washingtondc":
            case "newjersey":
            case "baltimore":
            case "atlanta":
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

    public String setDownloadLink(String message, String emailBody) {

//String HTML_TAG_PATTERN = "<a href=(.+?)>";
        String HTML_TAG_PATTERN = "https?:\\/\\/[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
        Pattern pLink = Pattern.compile(HTML_TAG_PATTERN);
        Matcher m = pLink.matcher(emailBody);
        while (m.find()) {
            String link = m.group(0);
            message = message.replace("%Link%", link);
        }
        return message;
    }

    public static String Convert(String str) {
        // Create a char array of given String
        char ch[] = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {

            // If first character of a word is found
            if (i == 0 && ch[i] != ' ' ||
                    ch[i] != ' ' && ch[i - 1] == ' ') {

                // If it is in lower-case
                if (ch[i] >= 'a' && ch[i] <= 'z') {

                    // Convert into Upper-case
                    ch[i] = (char) (ch[i] - 'a' + 'A');
                }
            }

            // If apart from first character
            // Any one is in Upper-case
            else if (ch[i] >= 'A' && ch[i] <= 'Z')

                // Convert into Lower-Case
                ch[i] = (char) (ch[i] + 'a' - 'A');
        }

        // Convert the char array to equivalent String
        String st = new String(ch);
        return st;
    }

    /**
     * Calculate estimate cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance Trip distance
     * @param loadTime     load / unload time
     * @param estTime      estimate trip complete time
     * @param Promo        Promo
     * @return
     */
    public double bungiiEstimate(String tripDistance, String loadTime, String estTime, String Promo) {
        //get bungii type and current geofence type.
        String bungiiType = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get minimum cost,Mile value,Minutes value of Geofence
        double minCost = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.minimum.cost")),
                perMileValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.miles")),
                perMinutesValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.minutes"));

        //Get trip distance from db instead of screen
        double distance = Double.parseDouble(tripDistance.trim());
        //     double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double loadUnloadTime = Double.parseDouble(loadTime.replace(" mins", ""));
        double tripTime = Double.parseDouble(estTime);

        double estimateCost = distance * perMileValue + loadUnloadTime * perMinutesValue + tripTime * perMinutesValue;
        //check if trip is duo trip , if yes then double estimate cost
        if (bungiiType.equalsIgnoreCase("DUO"))
            estimateCost = estimateCost * 2;
        //Subtract discount value from estimate cost
        Promo = Promo.contains("ADD") ? "0" : Promo;
        double discount = 0;
        if (Promo.contains("$"))
            discount = Double.parseDouble(Promo.replace("-$", ""));
        else if (Promo.contains("%"))
            discount = estimateCost * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100;
        estimateCost = estimateCost - discount;
        //Check if estimate is greater than min
        estimateCost = estimateCost > minCost ? estimateCost : minCost;

        return estimateCost;
    }

    public String calDriverEstEarning() {
        String CalculatedDriverValue = "";
        //Driver Cut used for calculation
        double DSE;

        String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        if (geofence.equalsIgnoreCase("washingtondc")) {
            geofence = "washington dc";
        }

        String Bungii_rate = getBungiiRate(geofence);

        int Driver_rate = 100 - Integer.parseInt(Bungii_rate);

        String PickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        Double Customer_Price_After_Discount = Double.parseDouble(getEstPrice(PickupRequest));

        String Bungii_Type = (String) cucumberContextManager.getScenarioContext("Bungii_Type");
        int num_Of_Driver;
        if (Bungii_Type.equalsIgnoreCase("Solo Scheduled") || Bungii_Type.equalsIgnoreCase("Solo")) {
            num_Of_Driver = 1;
        } else {
            num_Of_Driver = 2;
        }

        Double Transaction_Fees = (Customer_Price_After_Discount * (0.029)) + (0.3 * num_Of_Driver);

        DSE = (Customer_Price_After_Discount * (Driver_rate * 0.01)) - (Transaction_Fees);


        int Last_Zero_Digit = (int) ((DSE * 100) % 10);
        if (Last_Zero_Digit == 0) {
            CalculatedDriverValue = String.valueOf(String.format("%.1f", DSE));
        } else {
            CalculatedDriverValue = String.valueOf(String.format("%.2f", DSE));
        }
        return CalculatedDriverValue;
    }

    public String calDriverEarning() {
        String CalculatedDriverValue = "";
        //Driver Cut used for calculation
        double DSE;

        String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        if (geofence.equalsIgnoreCase("washingtondc")) {
            geofence = "washington dc";
        }

        String Bungii_rate = getBungiiRate(geofence);

        int Driver_rate = 100 - Integer.parseInt(Bungii_rate);

        String PickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        Double Customer_Price_After_Discount = Double.parseDouble(getActualPrice(PickupRequest));

        String Bungii_Type = (String) cucumberContextManager.getScenarioContext("Bungii_Type");
        int num_Of_Driver;
        if (Bungii_Type.equalsIgnoreCase("Solo Scheduled") || Bungii_Type.equalsIgnoreCase("Solo")) {
            num_Of_Driver = 1;
        } else {
            num_Of_Driver = 2;
        }

        Double Transaction_Fees = (Customer_Price_After_Discount * (0.029)) + (0.3 * num_Of_Driver);

        DSE = (Customer_Price_After_Discount * (Driver_rate * 0.01)) - (Transaction_Fees);


        int Last_Zero_Digit = (int) ((DSE * 100) % 10);
        if (Last_Zero_Digit == 0) {
            CalculatedDriverValue = String.valueOf(String.format("%.1f", DSE));
        } else {
            CalculatedDriverValue = String.valueOf(String.format("%.2f", DSE));
        }
        return CalculatedDriverValue;
    }

    public String getTimeZoneBasedOnGeofenceId() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        // currentGeofence="kansas";
        //get timezone value of Geofence
        String getGeofenceTimeZone = getGeofenceData(currentGeofence, "geofence.timezone.id");
        return getGeofenceTimeZone;
    }

    public long Milliseconds_To_Minutes(long milliseconds) {
        long minutes = (milliseconds / 1000) / 60;
        return minutes;
    }

    public String getbungiiDayLightTimeValue(String bungiiTime) {
        String time = bungiiTime;
        if (bungiiTime.contains("CDT")) {
            time = bungiiTime.replace("CDT", "CST");
        } else if (bungiiTime.contains("EDT")) {
            time = bungiiTime.replace("EDT", "EST");
        } else if (bungiiTime.contains("MDT")) {
            time = bungiiTime.replace("MDT", "MST");
        } else if (bungiiTime.contains("PDT")) {
            time = bungiiTime.replace("PDT", "PST");
        } else if (bungiiTime.contains("IST")) {
            time = bungiiTime;
        }
        return time;
    }

    public void searchGeofenceDropdown(String geofence) {
        action.click(admin_geofencePage.List_Geofence());
        action.clearSendKeys(admin_geofencePage.TextBox_SearchGeofence(), geofence);
    }

    public void resetGeofenceDropdown() throws InterruptedException {
        Thread.sleep(2000);
        action.click(admin_geofencePage.List_Geofence());
        Thread.sleep(2000);
        action.click(admin_geofencePage.Button_Clear());
        Thread.sleep(2000);
        action.click(admin_geofencePage.Button_ApplyGeofence());
    }

    public void selectGeofenceDropdown(String geofence) {
        action.click(admin_geofencePage.List_Geofence());
        action.clearSendKeys(admin_geofencePage.TextBox_SearchGeofence(), geofence);
        String geofencePartial = geofence.substring(1);
        action.JavaScriptClick(admin_geofencePage.Checkbox_Geofence(geofencePartial));
        action.click(admin_geofencePage.Button_ApplyGeofence());
    }

    public void reApplyGeofenceDropdown() throws InterruptedException {
        Thread.sleep(3000);
        action.click(admin_geofencePage.List_Geofence());
        Thread.sleep(3000);
        action.click(admin_geofencePage.Button_ApplyGeofence());
    }

    public void closeGeofenceDropdown() {
        action.click(admin_geofencePage.Button_ApplyGeofence());
    }

    public String encodeImage(String Image) {
        String base64 = "";
        try {
            InputStream iSteamReader = new FileInputStream(Image);
            byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
            base64 = Base64.getEncoder().encodeToString(imageBytes);
            //System.out.println(base64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "data:image/png;base64," + base64;
    }

    public int GetUniqueNumber() {
        Random random = new Random();
        // Generate random integers in range 0 to 999
        int random_int = random.nextInt(1000);
        return random_int;
    }

    public void calculateEstDeliveryTime(int minutes, java.sql.Time timeValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeValue);
        calendar.add(Calendar.MINUTE, minutes);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 5;
        calendar.add(Calendar.MINUTE, (5 - mod));
        calendar.add(Calendar.MINUTE, -60);
        String lowerRangeTime = String.valueOf(calendar.getTime());
        calendar.add(Calendar.MINUTE, 120);
        String upperRangeTime = String.valueOf(calendar.getTime());

        String estimateLowerRange = lowerRangeTime.substring(11, 16);
        cucumberContextManager.setScenarioContext("ESTIMATED_LOWER_RANGE_DELIVERY_TIME", estimateLowerRange);
        String estimateUpperRange = upperRangeTime.substring(11, 16);
        cucumberContextManager.setScenarioContext("ESTIMATED_UPPER_RANGE_DELIVERY_TIME", estimateUpperRange);
    }

    public void clearEquipment() {
        action.click(admin_DriverPage.Checkbox_ApplianceDolly());
        action.click(admin_DriverPage.Checkbox_FurnitureDolly());
        action.click(admin_DriverPage.Checkbox_HandDolly());
        action.click(admin_DriverPage.Checkbox_LiftGate());
        action.click(admin_DriverPage.Checkbox_Ramp());
    }

    public void clearVehicleType() {
        action.click(admin_DriverPage.Checkbox_BoxTruck());
        action.click(admin_DriverPage.Checkbox_MovingVan());
        action.click(admin_DriverPage.Checkbox_PickupTruck());
        action.click(admin_DriverPage.Checkbox_SUV());
    }

    public String NavigateToPartnerManagementLogin() {
        String partnerURL = GetPartnerManagementUrl();
        action.deleteAllCookies();
        action.navigateTo(partnerURL);
        return partnerURL;
    }

    public String GetPartnerManagementUrl() {
        String partnerManagementURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("QA_AUTO_AWS"))
            partnerManagementURL = PropertyUtility.getDataProperties("qa.auto.partner.management.url");
        return partnerManagementURL;
    }
    public String getExpectedDriverForgotPasswordEmailContent(String driverName, String verificationCode)
    {
        String emailMessage = "";
        FileReader fr;
        try{
            if(SystemUtils.IS_OS_WINDOWS){
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath())+"\\EmailTemplate\\DriverForgotPasswordVerificationCodeEmail.txt");
            }
            else {
                fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath())+"/EmailTemplate/DriverForgotPasswordVerificationCodeEmail.txt");
            }
            String s;
            try (
                BufferedReader br = new BufferedReader(fr)) {
                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%DriverName%",driverName).replaceAll("%VerificationCode%",verificationCode);
                    emailMessage += s;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return emailMessage;
    }
}