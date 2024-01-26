package com.bungii.common.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyUtility {
    private static final String IMAGE_PROPERTY_FILE = "/UserProperties/images.properties";
    private static final String CSV_PROPERTY_FILE = "/UserProperties/csv.properties";
    private static final String FILE_LOCATION_PROPERTY_FILE = "/UserProperties/resourcesFilePaths.properties";
    private static final String CONFIG_PROPERY_FILE = "/UserProperties/config.properties";
    private static final String RESULT_CONFIG_PROPERTY_FILE = "/SystemProperties/resultConfig.properties";
    private static String JDBC_CONFIG_PROPERTY_FILE ="" ;//"/SystemProperties/jdbcConfig.properties";
    private static String DATA_PROPERTY_FILE = "" ;//"/UserProperties/data.properties";
    private static String LOGIN_PROPERTY_FILE = "" ;//"/UserProperties/LoginProperties/login.properties";
    private static String LOGIN_PROPERTY_FOLDER = "/Profiles/{ENVT}/LoginProperties";
    private static String GEOFENCE_PROPERTY_FILE = "/Profiles/{ENVT}/geofence.properties";
    private static String EMAILCLIENT_PROPERTY_FILE = "/UserProperties/emailConfig.properties";

    private static Properties properties;
    private static Properties fileLocations;
    private static Properties images;
    private static Properties csv;
    private static Properties data;
    private static Properties resultConfig;
    private static Properties jdbcConfig;
    private static Properties loginData;
    private static Properties geoFenceData;
    private static Properties email;

    public static  String targetPlatform="",environment="";
    /**
     * Gets the key from Config.properties related to chosen profile
     *
     * @param key
     **/

    public static String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return properties.getProperty(key);

        }
    }


    /**
     * Gets the key from Config.properties related to chosen profile
     *
     * @param key
     **/

    public static String getFileLocations(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return fileLocations.getProperty(key);

        }
    }

    /**
     * Gets the key from images.properties related to chosen profile
     *
     * @param key
     **/

    public static String getDataProperties(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return data.getProperty(key);

        }
    }

    /**
     * Gets the key from images.properties related to chosen profile
     *
     * @param key
     **/

    public static String getResultConfigProperties(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return resultConfig.getProperty(key);

        }
    }


    /**
     * Gets the key from images.properties related to chosen profile
     *
     * @param key
     **/

    public static String getJdbcConfigProperties(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return jdbcConfig.getProperty(key);

        }
    }

    /**
     * Gets the key from images.properties related to chosen profile
     *
     * @param key
     **/

    public static String getImageLocations(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return images.getProperty(key);

        }
    }
    public static String getCsvLocations(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return csv.getProperty(key);

        }
    }
    public static String getEmailProperties(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return email.getProperty(key);

        }
    }
    /**
     * Gets the key from messages.properties for a framework
     *
     * @param key
     **/
    public static String getMessage(String key) {

        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return ResourceBundle.getBundle("UserProperties/messages").getString(key);

        }
    }

    /**
     * Gets the key from geofence.properties for a framework
     *
     * @param key
     **/
    public static String getGeofenceData(String key) {

        if ((key == null) || key.isEmpty()) {
            return "";
        } else {

            return ResourceBundle.getBundle(PropertyUtility.getFileLocations("geofence.properties.file").substring(1).replace("{ENVT}",properties.getProperty("environment").toUpperCase())).getString(key);

        }
    }


    /**
     * Update Login property fileName that is to be used for run
     */
    public static void updateLoginPropertyFileName() {
        String loginFileName = null;
        try {
            loginFileName = System.getProperty("LOGIN_FILE");
        } catch (Exception e) {
            //do nothing
        }
        if (!(loginFileName == null)) {
            LOGIN_PROPERTY_FILE = LOGIN_PROPERTY_FOLDER.replace("{ENVT}",environment) + File.separator + loginFileName + ".properties";
            System.out.println("Login File for thread "+LOGIN_PROPERTY_FILE);

        }
    }

    public static void loadRunConfigProps() {

        properties = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(CONFIG_PROPERY_FILE)) {
            properties.load(inputStream);
            //  update value of environment and target platform in property object
            if(!environment.equals("") && !targetPlatform.equals("")){properties.setProperty("target.platform", targetPlatform);properties.setProperty("environment", environment);}
           // properties.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }

        fileLocations = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(FILE_LOCATION_PROPERTY_FILE)) {
            fileLocations.load(inputStream);
           // fileLocations.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }
        //update data property for multiple data properties
        LOGIN_PROPERTY_FILE=fileLocations.getProperty("login.properties.file").replace("{ENVT}",properties.getProperty("environment").toUpperCase());
        updateLoginPropertyFileName();
        loginData = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(LOGIN_PROPERTY_FILE)) {
            loginData.load(inputStream);
            //loginData.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }
        DATA_PROPERTY_FILE=fileLocations.getProperty("data.properties.file").replace("{ENVT}",properties.getProperty("environment").toUpperCase());
        data = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(DATA_PROPERTY_FILE)) {
            data.load(inputStream);
            //     data.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }

        //add login Data properties to main data properties
        data.putAll(loginData);
       // System.out.println("Listing merged data + login properties ("+LOGIN_PROPERTY_FILE+")");
       // data.list(System.out);

        resultConfig = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(RESULT_CONFIG_PROPERTY_FILE)) {
            resultConfig.load(inputStream);
          //  resultConfig.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }
        JDBC_CONFIG_PROPERTY_FILE=fileLocations.getProperty("jdbc.properties.file").replace("{ENVT}",properties.getProperty("environment").toUpperCase());
        jdbcConfig = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(JDBC_CONFIG_PROPERTY_FILE)) {
            jdbcConfig.load(inputStream);
           // jdbcConfig.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }
        try {
            images = new Properties();
            try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(IMAGE_PROPERTY_FILE)) {
                images.load(inputStream);
             //   images.list(System.out);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            csv = new Properties();
            try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(CSV_PROPERTY_FILE)) {
                csv.load(inputStream);
              //  csv.list(System.out);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            email = new Properties();
            try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(EMAILCLIENT_PROPERTY_FILE)) {
                email.load(inputStream);
                //  csv.list(System.out);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
