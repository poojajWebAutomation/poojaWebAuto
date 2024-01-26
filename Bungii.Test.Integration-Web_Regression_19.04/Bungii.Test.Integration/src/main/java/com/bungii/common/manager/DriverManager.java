package com.bungii.common.manager;

import com.bungii.common.utilities.LogUtility;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DriverManager  {
    private static DriverManager driverManager;
    public static Map<String, WebDriver> driverArray = new ConcurrentHashMap<>();
    private static LogUtility logger = new LogUtility(DriverManager.class);
    protected static WebDriver driver;
    private static String primaryInstanceKey;
    private static String currentKey="ORIGINAL";

    public static DriverManager getObject() {
        /*This logic will ensure that no more than
         * one object can be created at a time */
        if (driverManager == null) {
            driverManager = new DriverManager();
        }
        return driverManager;
    }
/*    private DriverManager() {
		// TODO Auto-generated constructor stub
	}*/

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver newDriver) {
        driver=newDriver;
    }
    public void setPrimaryInstanceKey(String key){
        primaryInstanceKey =key;
    }


    public void storeDriverInstance(String key, WebDriver driver) {
        driverArray.put(key, driver);
    }




    public void closeDriverInstance(String key) {
        if (driverArray.containsKey(key)) {
            WebDriver driver = driverArray.get(key);
            driver.quit();
            driverArray.remove(key);
        } else {
            logger.error("DRIVER INSTANCE NOT FOUND ");
        }
    }

    /**
     * Close all webdriver instance except original one,This is to be called at end of each test case
     */
    public void closeAllDriverInstanceExceptOriginal() {
        WebDriver driver;


        Set<String> keys = driverArray.keySet();
        for (String key : keys) {
            if (!key.equalsIgnoreCase(primaryInstanceKey)) {
                driver = driverArray.get(key);
                driver.quit();
                driverArray.remove(key);
            }
        }
    }

    /**
     * Print all driver instance , No use in test case , Only for debugging
     */
    public void showAllInstance() {
        WebDriver driver;
        Set<String> keys = driverArray.keySet();
        for (String key : keys) {
            driver = driverArray.get(key);
            System.err.println("KEY:" + key + "driver" + driver);
        }
    }

    /**
     * Use new instance of driver as default instance in action com.bungii.android.manager
     */
    public void useDriverInstance(String key) {
        if (driverArray.containsKey(key)) {
            setDriver(driverArray.get(key));
            currentKey=key;
        } else {
            logger.error("DRIVER INSTANCE NOT FOUND ");
        }
    }

    public static String getCurrentKey() {
        return currentKey;
    }
}
