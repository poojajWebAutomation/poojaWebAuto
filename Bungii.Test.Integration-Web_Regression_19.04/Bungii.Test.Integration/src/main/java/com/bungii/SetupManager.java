package com.bungii;

import com.bungii.common.enums.TargetPlatform;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.*;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.appmanagement.AndroidTerminateApplicationOptions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.PageLoadStrategy;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

public class SetupManager extends EventFiringWebDriver {
    private static LogUtility logger = new LogUtility(SetupManager.class);
    private static JSONObject jsonParsed, jsonCaps;
    private static WebDriver driver = null;
    private static final Thread CLOSE_THREAD = new Thread() {

        @Override
        public void run() {
            driver.quit();
        }
    };
    private static String APPIUM_SERVER_IP, REMOTE_ADB_ADDRESS = "", REMOTE_ADB_PORT = "5554";
    private static SetupManager setupManager;
    private static String TARGET_PLATFORM;
    private static AppiumDriverLocalService service = null;
    private static String BrowserStackLocal;
    private static String browserStackOSVersion;


    static {
        TARGET_PLATFORM = PropertyUtility.getProp("target.platform");
        logger.detail("PLATFORM : " + TARGET_PLATFORM);
        APPIUM_SERVER_IP = PropertyUtility.getProp("server");
        if (TARGET_PLATFORM.equalsIgnoreCase("IOS") || TARGET_PLATFORM.equalsIgnoreCase("ANDROID")) {
            String deviceID = System.getProperty("DEVICE");
            String APPIUM_SERVER_PORT = String.valueOf(returnPortNumber(deviceID));
            DesiredCapabilities dc = getCapabilities(deviceID);
            CucumberContextManager.getObject().setScenarioContext("FAILURE", "FALSE");

            if (TARGET_PLATFORM.equalsIgnoreCase("IOS")) {
                try {
                    driver = (IOSDriver<MobileElement>) startAppiumDriver(dc, APPIUM_SERVER_PORT);
                } catch (SessionNotCreatedException e) {
                    logger.detail("Initialing driver failed, on " + deviceID + " SessionNotCreatedException");
                    try {
                        driver = (IOSDriver<MobileElement>) startAppiumDriver(dc, APPIUM_SERVER_PORT);
                        ((IOSDriver) driver).executeScript("mobile: pressButton", ImmutableMap.of("name", "home"));
                        ((IOSDriver) driver).runAppInBackground(Duration.ofSeconds(-1));

                    } catch (Exception e1) {
                        ManageDevices.afterSuiteManageDevice();
                        CucumberContextManager.getObject().setScenarioContext("FAILURE", "TRUE");

                    }
                } catch (Exception e) {
                    logger.detail(getStackTrace(e));
                    logger.detail("Initialising driver failed. Trying again ");
                    try {
                        driver = (IOSDriver<MobileElement>) startAppiumDriver(dc, APPIUM_SERVER_PORT);
                    } catch (Exception e1) {
                        ManageDevices.afterSuiteManageDevice();
                        CucumberContextManager.getObject().setScenarioContext("FAILURE", "TRUE");

                    }
                }
                if (dc.getCapability("app").toString().contains("customer"))
                    CucumberContextManager.getObject().setFeatureContextContext("CURRENT_APPLICATION", "CUSTOMER");
                else
                    CucumberContextManager.getObject().setFeatureContextContext("CURRENT_APPLICATION", "DRIVER");

            } else if (TARGET_PLATFORM.equalsIgnoreCase("ANDROID")) {
                try {
                    driver = (AndroidDriver<MobileElement>) startAppiumDriver(dc, APPIUM_SERVER_PORT);
                } catch (SessionNotCreatedException e) {
                    try {
                        driver = (AndroidDriver<MobileElement>) startAppiumDriver(dc, APPIUM_SERVER_PORT);

                    } catch (Exception e1) {
                        ManageDevices.afterSuiteManageDevice();
                        CucumberContextManager.getObject().setScenarioContext("FAILURE", "TRUE");
                    }
                }
            }
        } else if (TARGET_PLATFORM.equalsIgnoreCase("WEB")) {
            driver = createWebDriverInstance(PropertyUtility.getProp("default.browser"));
            driver.manage().window().maximize();
        }
        if (driver != null)
        {
            if (!TARGET_PLATFORM.equalsIgnoreCase("WEB")) {
                SessionId sessionid = ((RemoteWebDriver) driver).getSessionId();
                logger.detail(" BROWSERSTACK SESSION ID : " + sessionid);
                CucumberContextManager.getObject().setScenarioContext("SESSION", sessionid);
            }
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);
            DriverManager.getObject().setPrimaryInstanceKey("ORIGINAL");
            DriverManager.getObject().storeDriverInstance("ORIGINAL", driver);
            DriverManager.getObject().setDriver(driver);
        }
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }
    private static void removeWebdriverAgent(){
        try {
            //  if (SystemUtils.IS_OS_MAC) {
            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {
                //commented code to remove webdriver agent
                String deviceInfoFileKey = "ios.capabilities.file";
                String deviceId = System.getProperty("DEVICE");


                DesiredCapabilities capabilities = new DesiredCapabilities();
                String capabilitiesFilePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("capabilities.folder"), PropertyUtility.getFileLocations(deviceInfoFileKey));

                ParseUtility jsonParser = new ParseUtility(capabilitiesFilePath);
                JSONObject jsonParsed, jsonCaps;
                jsonParsed = jsonParser.getObjectFromJSON();
                jsonCaps = jsonParsed.getJSONObject(deviceId);
                String udid = jsonCaps.getString("udid");


                Runtime.getRuntime().exec("./src/main/resources/Scripts/Mac/deleteWebDriverAgent.sh " + udid);
                logger.detail("Deleted WebdriverAgent for Device : " + deviceId);
            }
        } catch (Exception e) {
            // logger.error("Error removing webdriver aggent ", ExceptionUtils.getStackTrace(e));

        }
    }
    private static void restartIphone(){
        try {
            //  if (SystemUtils.IS_OS_MAC) {
            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {

                String deviceInfoFileKey = "ios.capabilities.file";
                String deviceId = System.getProperty("DEVICE");


                DesiredCapabilities capabilities = new DesiredCapabilities();
                String capabilitiesFilePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("capabilities.folder"), PropertyUtility.getFileLocations(deviceInfoFileKey));

                ParseUtility jsonParser = new ParseUtility(capabilitiesFilePath);
                JSONObject jsonParsed, jsonCaps;
                jsonParsed = jsonParser.getObjectFromJSON();
                jsonCaps = jsonParsed.getJSONObject(deviceId);
                String udid = jsonCaps.getString("udid");


                Runtime.getRuntime().exec("./src/main/resources/Scripts/Mac/restartIphone.sh " + udid);
                logger.detail("Restarted iPhone Device : " + deviceId);

            }
        } catch (Exception e) {
            // logger.error("Error removing webdriver aggent ", ExceptionUtils.getStackTrace(e));

        }
    }
    /**
     * Make class singleton, one instance of driver is shared with all the class
     */
    private SetupManager() {

        super(driver);

    }

    public static SetupManager getObject() {
        /*This logic will ensure that no more than
         * one object can be created at a time */
        if (setupManager == null) {
            setupManager = new SetupManager();
        }
        return setupManager;
    }

    /**
     * Create appium server url
     *
     * @return Appium server url
     */
    public static String getAppiumServerURL(String portNumber) {
        // String browserlocal ="false";
        if (APPIUM_SERVER_IP.equalsIgnoreCase("localhost") || APPIUM_SERVER_IP.equals("") || APPIUM_SERVER_IP.equals("0.0.0.0"))
            APPIUM_SERVER_IP = "127.0.0.1";

        if(!APPIUM_SERVER_IP.equalsIgnoreCase("127.0.0.1")){
            return "https://" + APPIUM_SERVER_IP + "/wd/hub"; //browserstack
        }
        else {
            return "http://" + APPIUM_SERVER_IP + ":" + portNumber + "/wd/hub";
        }
        //return "https://" + APPIUM_SERVER_IP + "/wd/hub"; //browserstack
    }

    public static void startAppiumServer(String APPIUM_SERVER_IP, String portNumber) {
        Map<String, String> env = new HashMap<String, String>(System.getenv());

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress(APPIUM_SERVER_IP);
        builder.usingPort(Integer.parseInt(portNumber));
        //relaxed security tag
        builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
        // builder.withEnvironment(env);
        service = AppiumDriverLocalService.buildService(builder);
        service.start();

    }

    public static void stopAppiumServer() {
        if (service != null)
            service.stop();
    }

    public static boolean checkIfServerIsRunnning(String port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(port));
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    /**
     * Return IOS driver instance
     *
     * @return ios driver instance
     */
    public static WebDriver getDriver() {
        // return APPIUM_DRIVER;
        if (DriverManager.getObject().getDriver()!=null)
            return DriverManager.getObject().getDriver();
        else
            return null;
    }

    public static void setDriver(WebDriver newDriver) {
        //PageBase.driver=APPIUM_DRIVER;
        DriverManager.getObject().setDriver(newDriver);
        driver = driver;
    }

    public static WebDriver createWebDriverInstance(String browser) {
        WebDriver driver = null;

        String chromeDriverPath = "src/main/resources/BrowserExecutables/chromedriver.exe";
        if (SystemUtils.IS_OS_MAC)
            chromeDriverPath = "src/main/resources/BrowserExecutables/chromedriver";

        switch (browser.toUpperCase()) {
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", FileUtility.getSuiteResource("", chromeDriverPath));

                // DesiredCapabilities capabilities = getChromeDesiredCapabilities();

                ChromeOptions options = getChromeDesiredCapabilities();
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(FileUtility.getSuiteResource("", chromeDriverPath)))
                        .usingAnyFreePort()
                        .build();
                driver = new ChromeDriver(service, options);
                driver.manage().window().maximize();
                break;
            default:
                logger.error("webdriver method for " + browser + "is not implemented ");
        }


        return driver;
    }

    private static ChromeOptions getChromeDesiredCapabilities() {

        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", SystemUtils.getUserHome().getPath() + File.separator + "Downloads");
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("--no-sandbox");
        //if (PropertyUtility.getProp("target.platform").equalsIgnoreCase("IOS")) {
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");
        if (PropertyUtility.getProp("target.platform").equalsIgnoreCase("IOS")) {
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-gui");
            chromeOptions.addArguments("--force-device-scale-factor=1");
            chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        }
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("--allow-running-insecure-content");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-features=VizDisplayCompositor");
        return chromeOptions;
    }
    /**
     * Start and return appium driver instance
     *
     * @return appium driver instance
     */
    private static WebDriver startAppiumDriver(DesiredCapabilities capabilities, String portNumber) {
        try {
            //logger.detail("Getting Appium Driver at port : " + portNumber);
            String appiumServerUrl = getAppiumServerURL(portNumber);
            if (TARGET_PLATFORM.equalsIgnoreCase("ANDROID"))
                driver = new AndroidDriver<MobileElement>(new URL(appiumServerUrl), capabilities);
            else
                driver = new IOSDriver<MobileElement>(new URL(appiumServerUrl), capabilities);
            //logger.detail("Appium Driver Running at port : " + portNumber); //Not needed for browserstack

        } catch (Exception e) {
            // e.printStackTrace();
            logger.detail("Error in creating Appium Session : " + e.getLocalizedMessage());
        }
        return driver;
    }

    /**
     * @param deviceId Device key whose capabilities need to be fetch from JSON
     * @return
     */
    public static DesiredCapabilities getCapabilities(String deviceId) {
        String deviceInfoFileKey = "";
        String phoneDetails ="";
        String browserlocal ="false";
        if (TARGET_PLATFORM.equalsIgnoreCase("IOS"))
            deviceInfoFileKey = "ios.capabilities.file";
        else if (TARGET_PLATFORM.equalsIgnoreCase("ANDROID"))
            deviceInfoFileKey = "android.capabilities.file";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        String capabilitiesFilePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("capabilities.folder"), PropertyUtility.getFileLocations(deviceInfoFileKey));

        ParseUtility jsonParser = new ParseUtility(capabilitiesFilePath);
        jsonParsed = jsonParser.getObjectFromJSON();
        jsonCaps = jsonParsed.getJSONObject(deviceId);
        Iterator<String> keys = jsonCaps.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            if(key.toString().equalsIgnoreCase("otherApps"))
            {
                String[] Arrary = new String[]{jsonCaps.get(key).toString()};
                capabilities.setCapability(key, Arrary);
            }
            else {
                capabilities.setCapability(key, jsonCaps.get(key));
                if (key.toString().equalsIgnoreCase("browserstack.local")) {
                    browserlocal = jsonCaps.get(key).toString();
                    //phoneDetails += " " + jsonCaps.get(key).toString();
                    if(browserlocal.equalsIgnoreCase("true")){
                        BrowserStackLocal = "true";
                    }
                    else{
                        BrowserStackLocal = "false";
                    }
                }
//                if (key.toString().equalsIgnoreCase("os_version")) {
//                    browserStackOSVersion = jsonCaps.get(key).toString();
//                }
                if (key.toString().equalsIgnoreCase("deviceName")) {
                    phoneDetails += " " + jsonCaps.get(key).toString();
                }
                if (key.toString().equalsIgnoreCase("platformName")) {
                    phoneDetails += " " + jsonCaps.get(key).toString();
                }
                if (key.toString().equalsIgnoreCase("platformVersion")) {
                    phoneDetails += " " + jsonCaps.get(key).toString();
                }
            }
        }

        if(browserlocal.equalsIgnoreCase("true")) {
            if (TARGET_PLATFORM.equalsIgnoreCase("IOS")) {
                capabilities.setCapability("app", PropertyUtility.getDataProperties("ios.primary.app.key"));
                String[] Arrary = new String[]{PropertyUtility.getDataProperties("ios.secondary.app.key")};
                capabilities.setCapability("otherApps", Arrary);

            } else if (TARGET_PLATFORM.equalsIgnoreCase("ANDROID")) {
                capabilities.setCapability("app", PropertyUtility.getDataProperties("android.primary.app.key"));
                String[] Arrary = new String[]{PropertyUtility.getDataProperties("android.secondary.app.key")};
                capabilities.setCapability("otherApps", Arrary);
            }
        }

        if (!System.getProperty("remoteAdbHost").trim().equals("") && TARGET_PLATFORM.equalsIgnoreCase(TargetPlatform.ANDROID.toString())) {
            capabilities.setCapability("remoteAdbHost", System.getProperty("remoteAdbHost"));
            capabilities.setCapability("adbPort", REMOTE_ADB_PORT);
        }
        logger.detail("CONNECTING ["+ deviceId.toUpperCase() + ":" + phoneDetails+"] DEVICE");
        return capabilities;
    }

    /**
     * @param deviceId Device key whose port Number on which Appium server is running is to fetches
     * @return
     */
    public static int returnPortNumber(String deviceId) {
        String deviceInfoFileKey = "";
        if (TARGET_PLATFORM.equalsIgnoreCase("IOS"))
            deviceInfoFileKey = "ios.capabilities.file";
        else if (TARGET_PLATFORM.equalsIgnoreCase("ANDROID"))
            deviceInfoFileKey = "android.capabilities.file";

        //logger.detail("deviceInfoFileKey=" + deviceInfoFileKey);
        String capabilitiesFilePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("capabilities.folder"), PropertyUtility.getFileLocations(deviceInfoFileKey));

        ParseUtility jsonParser = new ParseUtility(capabilitiesFilePath);
        jsonParsed = jsonParser.getObjectFromJSON();
        jsonCaps = jsonParsed.getJSONObject("connection");
        //try to fetch json value for devices , if none is present  it will throw JSONException  exception and return 0
        try {
            return (int) jsonCaps.get(deviceId);
        } catch (Exception e) {

            //  logger.error("NOT able to fetch port number from JSON file . Please  verify key " + deviceId + " in JSON file"); // Not needed for Browserstack
            return 0;
        }

    }

    public static String BrowserStackLocal(){
        return BrowserStackLocal;
    }

    public void restartApp() {
        if (TARGET_PLATFORM.equalsIgnoreCase("IOS")) {
            ((IOSDriver) getDriver()).launchApp();
            logger.detail("Restarted App");
        } else if (TARGET_PLATFORM.equalsIgnoreCase("ANDROID")) {
            ((AndroidDriver) getDriver()).closeApp();
            ((AndroidDriver) getDriver()).launchApp();
            logger.detail("Restarted App");
        }
    }

    public void launchApp(String bundleId) {
        if (TARGET_PLATFORM.equalsIgnoreCase("IOS")) {
            ((IOSDriver) SetupManager.getDriver()).activateApp(bundleId);
        } else if (TARGET_PLATFORM.equalsIgnoreCase("ANDROID")) {
            ((AndroidDriver) SetupManager.getDriver()).activateApp(bundleId);


        }

    }

    public void terminateApp(String bundleId) {

        if (TARGET_PLATFORM.equalsIgnoreCase("IOS")) {
            ((IOSDriver) SetupManager.getDriver()).terminateApp(bundleId);

        } else if (TARGET_PLATFORM.equalsIgnoreCase("ANDROID")) {
            try {
                ((AndroidDriver) SetupManager.getDriver()).terminateApp(bundleId, new AndroidTerminateApplicationOptions().withTimeout(Duration.ofMillis(5000)));
            } catch (org.openqa.selenium.WebDriverException e) {
                logger.detail(" Issue with stopping app" + bundleId);
            }

        }

    }

    public void restartApp(String bundleId) {

        if (TARGET_PLATFORM.equalsIgnoreCase("IOS")) {
            ((IOSDriver) SetupManager.getDriver()).terminateApp(bundleId);
            ((IOSDriver) SetupManager.getDriver()).activateApp(bundleId);

        } else if (TARGET_PLATFORM.equalsIgnoreCase("ANDROID")) {
            try {
                ((AndroidDriver) SetupManager.getDriver()).terminateApp(bundleId, new AndroidTerminateApplicationOptions().withTimeout(Duration.ofMillis(5000)));
            } catch (org.openqa.selenium.WebDriverException e) {
                logger.detail(" Issue with stopping app" + bundleId);
            }
            ((AndroidDriver) SetupManager.getDriver()).activateApp(bundleId);

        }
        logger.detail("Restarted App : " + bundleId);

    }

    public void useDriverInstance(String instanceKey) {
        try {
            DriverManager.getObject().useDriverInstance(instanceKey);
            logger.detail("Running on Driver Instance : " + instanceKey);
        }
        catch(Exception ex)
        {
            logger.detail("Getting Driver Instance Failed : " + instanceKey);
            CucumberContextManager.getObject().setScenarioContext("FAILURE", "TRUE");
        }

    }

    public String getCurrentInstanceKey() {
        return DriverManager.getObject().getCurrentKey();
    }

    public AppiumDriver<MobileElement> createAdditionAppiumDriver(String serverPort, DesiredCapabilities capabilities) throws MalformedURLException {
        String appiumServerUrl = getAppiumServerURL(serverPort);
        return new AppiumDriver<>(new URL(appiumServerUrl), capabilities);
    }

    /**
     * Create new appium driver instance as per setting in config file and assign it to variable
     */
    public void createNewAppiumInstance(String key, String deviceId) throws MalformedURLException {

        String appiumPortNumber = String.valueOf(returnPortNumber(deviceId));
        DesiredCapabilities capabilities = getCapabilities(deviceId);
        AppiumDriver<MobileElement> newDriverInstance = null;

        if (TARGET_PLATFORM.equalsIgnoreCase("IOS")) {
            IOSDriver<MobileElement> iosDriverInstance = (IOSDriver<MobileElement>) startAppiumDriver(capabilities, appiumPortNumber);
            iosDriverInstance.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);
            DriverManager.getObject().storeDriverInstance(key, iosDriverInstance);
        } else {
            newDriverInstance = createAdditionAppiumDriver(appiumPortNumber, capabilities);
            newDriverInstance.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);
            DriverManager.getObject().storeDriverInstance(key, newDriverInstance);
        }
    }

    /**
     * Create new appium driver instance as per setting in config file and assign it to variable
     */
    public void createNewAndroidInstance(String key, String deviceId) throws MalformedURLException {

        String appiumPortNumber = String.valueOf(returnPortNumber(deviceId));
        DesiredCapabilities capabilities = getCapabilities(deviceId);
        AndroidDriver<MobileElement> newDriverInstance = null;

        String appiumServerUrl = getAppiumServerURL(appiumPortNumber);
        newDriverInstance = new AndroidDriver<MobileElement>(new URL(appiumServerUrl), capabilities);
        newDriverInstance.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);

        DriverManager.getObject().storeDriverInstance(key, newDriverInstance);
    }

    /**
     * Create new webdriver instance
     *
     * @param key     instance name that is to be saved
     * @param browser type of browser
     */
    public void createNewWebdriverInstance(String key, String browser) {
        WebDriver newWebDriverInstance = createWebDriverInstance(browser);
        newWebDriverInstance.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);
        newWebDriverInstance.manage().window().maximize();
        DriverManager.getObject().storeDriverInstance(key, newWebDriverInstance);
    }

    /*
     * @Override method of closing driver instance
     * (non-Javadoc)
     * @see org.openqa.selenium.support.events.EventFiringWebDriver#close()
     */
    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

}
