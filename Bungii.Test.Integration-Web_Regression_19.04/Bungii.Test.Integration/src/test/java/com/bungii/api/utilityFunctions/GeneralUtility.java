package com.bungii.api.utilityFunctions;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class GeneralUtility extends DriverBase {

    public String genearateRandomString() {
        int stringlength = 10;
        String charsName = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
//generate random string
        String randomstring = "";
        for (int i = 0; i < stringlength; i++) {
            Random randomGeneratorName = new Random();
            int randomIntName = randomGeneratorName.nextInt(charsName.length());
            randomstring += charsName.substring(randomIntName, randomIntName + 1);
        }
        return randomstring;
    }

    public Float[] getDriverLocation(String geofence) {
        Float maxChange = 0.004f;
        Float minChange = -0.004f;
        Float pickupLat = 0f, pickupLong = 0f;
        // Add random float to  driver location
        Random rand = new Random();

        if (geofence.equalsIgnoreCase("goa")) {
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("goa.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("goa.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("kansas")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("kansas.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("kansas.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("boston")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("boston.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("boston.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("baltimore")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("baltimore.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("baltimore.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("atlanta")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("atlanta.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("atlanta.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("atlanta.far")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("atlanta.far.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("atlanta.far.pickup.longitude"));
        }
        else if(geofence.equalsIgnoreCase("miami")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("miami.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("miami.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("nashville")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("nashville.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("nashville.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("denver")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("denver.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("denver.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("washingtondc")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("washingtondc.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("washingtondc.pickup.longitude"));
        }
        Float[] driverCordinate = new Float[2];

        driverCordinate[0] = pickupLat + rand.nextFloat() * (maxChange - minChange) + minChange;

        driverCordinate[1] = pickupLong + rand.nextFloat() * (maxChange - minChange) + minChange;
        return driverCordinate;
    }

    public static String getCurrentUTCTime() throws InterruptedException {
        // Get formatted UTC time
        Thread.sleep(3000); //Added purposely to delay status update by 2 seconds - OPS-476 changes
        Calendar calendar = Calendar.getInstance();
       // calendar.add(Calendar.SECOND, 2);
        Date dateTime = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateFormatted = sdf.format(dateTime);
        return dateFormatted;
    }
    public static String getBungiiEndTimeForManuallyEnd() {
        //11/15/2019 12:43 AM
        Calendar calendar = Calendar.getInstance();
        Date dateTime = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone(new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofenceId()));
        String dateFormatted = sdf.format(dateTime);
        return dateFormatted;
    }

    public String getBungiiTimeZoneLanel(){
        String timeLabel=" "+new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofence();
        String timeZoneCompleteText="";
        //TODO: Add other timezone
                switch (timeLabel.trim().toUpperCase()){
                    case "CST":
                        timeZoneCompleteText="Central Standard Time";
                        break;
                    case "IST":
                        timeZoneCompleteText="India Standard Time";
                        break;
                }
        return timeZoneCompleteText;
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

    public String[] getDaysLaterTime(int days)
    {
        String[] rtnArray = new String[2];
        int bufferTimeToStartTrip = 0;
        Calendar calendar = Calendar.getInstance();
        int mnts = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.DATE, days);
        calendar.set(Calendar.MINUTE, mnts+ 45);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 15;
        calendar.add(Calendar.MINUTE, (15 - mod));
        calendar.set(Calendar.SECOND, 0);
        Date nextQuatter = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// create a formatter for date
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(nextQuatter);
        String wait = (((15 - mod) + bufferTimeToStartTrip) * 1000 * 60) + "";
        rtnArray[0] = formattedDate+".000";
        rtnArray[1] = wait;
        cucumberContextManager.setScenarioContext("BUNGII_UTC", rtnArray[0]);
        return rtnArray;
    }
}
