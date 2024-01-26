package com.bungii.api.utilityFunctions;

import com.bungii.common.manager.DbContextManager;
import com.bungii.common.utilities.LogUtility;

import java.util.HashMap;
import java.util.List;

public class DbUtility extends DbContextManager {
    private static LogUtility logger = new LogUtility(DbUtility.class);

    /**
     * Connect to MS squl server and get verification SMS verification code
     *
     * @param phoneNumber
     * @return
     */
    public static String getVerificationCode(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT SmsVerificationCode FROM customer WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }
    public static List<HashMap<String,Object>> getAllDriversEligible(String pickupRef) {
        List<HashMap<String,Object>> drivers = null;
        String queryString = "SELECT phone from driver where Id in (SELECT DriverID FROM eligibletripdriver WHERE pickupid in (select PickupId from pickupdetails where pickupref = '" + pickupRef+"'))";
        drivers = getDataFromMySqlServerMap(queryString);
        logger.detail("Eligible Phone numbers are " + drivers + ", query, " + queryString);
        return drivers;
    }
    public static String getVerificationCodeDriver(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT SmsVerificationCode FROM driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }


    public static boolean isPhoneNumberUnique(String phoneNumber) {
        String id = "";
        String queryString = "SELECT Id FROM customer WHERE Phone = " + phoneNumber;
        id = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Phone Number " + phoneNumber + "is unique " + id.equals(""));
        return id.equals("");
    }

    public static String getCustomerRefference(String phoneNumber) {
        String custRef = "";
        String queryString = "SELECT CustomerRef  FROM customer WHERE Phone = " + phoneNumber;
        custRef = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + " customer reference is " + custRef);
        return custRef;
    }

    public static String getEstimateTime(String custRef) {
        String estTime = "";
        String queryString = "SELECT EstTime FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        estTime = getDataFromMySqlServer(queryString);
        logger.detail("For customer reference " + custRef + " Estimate time is " + estTime);
        return estTime;
    }

    public static String getEstimateDistance(String custRef) {
        String estTime = "";
        String queryString = "SELECT EstDistance FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        estTime = getDataFromMySqlServer(queryString);
        logger.detail("For customer reference is " + custRef + " Estimate time is " + estTime);
        return estTime;
    }

    public static String getPickupID(String custRef) {
        String PickupID = "";
        String queryString = "SELECT PickupID FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        PickupID = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " Estimate time is " + PickupID);
        return PickupID;
    }

    public static String getActualTime(String pickupID) {
        String queryString = "select ActualTime from triprequest  WHERE PickupID = '" + pickupID + "' order by pickupid desc limit 1";

        String actualTime = getDataFromMySqlServer(queryString);

        logger.detail("pickupID" + pickupID + "  time is " + actualTime);
        return actualTime;
    }

    public static String getDriverRating(String phoneNumber) {
        String rating = "";
        String queryString = "SELECT Rating FROM driver WHERE phone = " + phoneNumber;
        rating = getDataFromMySqlServer(queryString);
        logger.detail("Rating is" + rating + ", query, " + queryString);
        return rating;
    }

    public static String getDriverAssignedForTrip(String pickupId) {
        String phoneNumber = "";
        String queryString = "select Phone  from driver where id In (select ControlDriverID from pickupdetails where PickupRef='"+pickupId+"' );";
        phoneNumber = getDataFromMySqlServer(queryString);
        logger.detail("phoneNumber is" + phoneNumber + ", query, " + queryString);
        return phoneNumber;
    }
    public static String getTELETfromDb(String custRef) {
        String PickupID = "";
        String queryString = "SELECT TELET FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        PickupID = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " Extimate time is " + PickupID);
        return PickupID;
    }
    public static boolean isDriverEligibleForTrip(String phoneNumber, String pickupRequest) {
            String queryString = "SELECT Id FROM driver WHERE phone = " + phoneNumber;
            String driverID = getDataFromMySqlServer(queryString);
            String queryString2 = "select DriverID from eligibletripdriver where pickupid IN (select PickupID from pickupdetails where pickupRef ='" + pickupRequest + "' )";
            boolean isDriverEligible =false;/* checkIfExpectedDataFromMySqlServer(queryString2,driverID);*/

            for(int i=0;i<30 && !isDriverEligible;i++){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isDriverEligible = checkIfExpectedDataFromMySqlServer(queryString2,driverID);

            }
            return isDriverEligible;

    }

    public static String getPickupRef(String customerPhone){
        String custRef=getCustomerRefference(customerPhone);
        String pickupRef=getDataFromMySqlServer("SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1");
        return pickupRef;
    }

    public static String getDriverToPickupTime(String driverPhoneNumber, String pickupID){
        String queryString = "SELECT Id FROM driver WHERE phone = " + driverPhoneNumber;
        String driverID = getDataFromMySqlServer(queryString);
        String queryString2 = "select DriverToPickupTime from eligibletripdriver where pickupid ="+pickupID+ " and  DriverID="+driverID;
        String driverToPickupTime = getDataFromMySqlServer(queryString2);
        return driverToPickupTime;
    }
    public static String getReference(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT Reference FROM customer WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Reference code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }
    public static String getActiveFlag(String phoneNumber){
        String UserRef = getReference(phoneNumber);
        String queryString2 = "select Active from device where UserRef ='"+UserRef+ "' order by devid desc limit 1";
        String activeFlag = getDataFromMySqlServer(queryString2);
        return activeFlag;
    }

    public static String getDriverReference(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT Reference FROM driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Reference code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }
    public static String getDriverActiveFlag(String phoneNumber){
        String UserRef = getDriverReference(phoneNumber);
        String queryString2 = "select Active from device where UserRef ='"+UserRef+ "' order by devid desc limit 1";
        String activeFlag = getDataFromMySqlServer(queryString2);
        return activeFlag;
    }

    public static String getResarchedPickupReference(String pickupRequest) {
        String pickupRef = "";
        String queryString = "SELECT PickupRef FROM pickupdetails WHERE LinkedPickupID in (SELECT PickupID from pickupdetails where pickupref='" + pickupRequest+"')";
        try {
            Thread.sleep(120000); //Waiting for research trip to synch
        }
        catch(Exception ex){}
        pickupRef = getDataFromMySqlServer(queryString);
        logger.detail("Researched Pickup Ref is" + pickupRef + ", query, " + queryString);
        return pickupRef;
    }

    public static String getCustomerDeviceToken(String phoneNumber){
        String queryString2 = " select token from device where UserRef IN (select CustomerRef from customer where phone="+phoneNumber+") order by DevID desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public static String getDriverDeviceToken(String phoneNumber){
        String queryString2 = " select token from device where UserRef IN (select DriverRef from driver  where phone="+phoneNumber+") order by DevID desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public static String getCustomersMostRecentBungii(String phoneNumber){

        String queryString2 = "SELECT PickupRef FROM pickupdetails  WHERE customerRef IN(SELECT CustomerRef FROM customer WHERE phone="+phoneNumber+") order by pickupid desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public static String UnassignTripFromCustomer(String pickupRef){

        String queryString2 = "update customer c\n" +
                "join pickupdetails pd on pd.customerRef = c.customerRef\n" +
                "set currentassignedpickup = null\n" +
                "where pd.PickupRef = "+pickupRef+";";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public static String BusinessPartnerDefaultAddressRef(String businessPartnerLocationRef ){

        String queryString2 = "select business_partner_default_address_ref from business_partner_default_address where business_partner_location_config_version_id in (select business_partner_location_config_version_id from business_partner_location_config_version where business_partner_location_id in (select business_partner_location_id from business_partner_location where business_partner_location_ref ='"+businessPartnerLocationRef+"'));";
        //String businessPartnerDefaultAddressRef  = getDataFromMySqlServer(queryString2);
        String businessPartnerDefaultAddressRef  = getDataFromMySqlMgmtServer(queryString2);
        return businessPartnerDefaultAddressRef;
    }
    public static String getRemarkId(String rejectionReason) {
        String remarkID;
        String queryString = "select remark_id from pickup_remarks where description like '"+rejectionReason+"'";
        remarkID = getDataFromMySqlServer(queryString);
        logger.detail("Remark_ID for description '"+rejectionReason+"' is "+remarkID);
        return remarkID;

    }
    public static int getFromTime(String index, String DayOfTheWeek,String PartnerPortalName) {
        String fromTime;
        String queryStringFromTime = "select from_time from bp_store_operating_hour oh join bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = oh.bp_config_version_id\n" +
                "join bp_store s on s.bp_store_id = fnm.bp_store_id where fnm.bp_setting_fn_id = 16 and subdomain_name is not null\n" +
                " and subdomain_name like '"+PartnerPortalName+"%' order by subdomain_name ,week_day and week_day = '"+index+"'  desc limit 1";
        fromTime = getDataFromMySqlMgmtServer(queryStringFromTime);
        int timeIn12HoursFormat = (Integer.parseInt(fromTime) / 3600000) ;
        logger.detail("Partners initial working hour is "+timeIn12HoursFormat+ " for the day  "+ DayOfTheWeek);
        return timeIn12HoursFormat;

    }

    public static int getToTime(String index, String DayOfTheWeek,String PartnerPortalName) {
        String toTime;
        String queryStringFromTime = "select to_time from bp_store_operating_hour oh join bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = oh.bp_config_version_id\n" +
                "join bp_store s on s.bp_store_id = fnm.bp_store_id where fnm.bp_setting_fn_id = 16 and subdomain_name is not null\n" +
                " and subdomain_name like '"+PartnerPortalName+"%' order by subdomain_name ,week_day and week_day = '"+index+"'  desc limit 1";
        toTime = getDataFromMySqlMgmtServer(queryStringFromTime);
        int timeIn12HoursFormat = (Integer.parseInt(toTime) / 3600000) -12;
        logger.detail("Partners last working hours is "+timeIn12HoursFormat+ " for the day  "+ DayOfTheWeek);
        return timeIn12HoursFormat;

    }

    public static String getPickupId(String pickupRef) {
        String pickupid = "";
        String queryString = "SELECT Pickupid FROM pickupdetails WHERE pickupref ='" + pickupRef + "'";
        pickupid = getDataFromMySqlServer(queryString);
        logger.detail("Pickupid  " + pickupid + " of pickupref " + pickupRef);
        return pickupid;
    }

    public static String getTripReference(String reference) {
        String tripRef = "";
        String pickupID = getPickupId(reference);
        String queryString = "select TripRef from triprequest where Pickupid= "+pickupID;
        tripRef = getDataFromMySqlServer(queryString);
        logger.detail("Trip reference is "+tripRef);
        return tripRef;
    }

    public static String[] getTripReferenceForDuo(String reference) {
        String pickupID = getPickupId(reference);
        String tripRefereneces[] = new String[2];
        String reference1 = "select TripRef from triprequest where Pickupid= "+pickupID + " order by TripRef desc limit 1";
        String reference2 = "select TripRef from triprequest where Pickupid= "+pickupID + " order by TripRef asc limit 1";
        tripRefereneces[0] = getDataFromMySqlServer(reference1);
        tripRefereneces [1]= getDataFromMySqlServer(reference2);
        logger.detail("Trip reference1 is "+tripRefereneces[0]);
        logger.detail("Trip reference2 is "+tripRefereneces[1]);
        return tripRefereneces;
    }
    public static Double[] getDriverLocation(String phoneNumber) {
        String driverId = "";
        Double driverLocation[] = new Double[2];
        String queryString = "SELECT Id FROM driver WHERE Phone = " + phoneNumber;
        driverId = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "driverId is " + driverId);

        driverLocation[0]= Double.valueOf(getDataFromMySqlServer("select Latitude from driverlocation where driverid = "+driverId));
        driverLocation[1]= Double.valueOf(getDataFromMySqlServer("select Longitude from driverlocation where driverid = "+driverId));
        logger.detail("For driverId " + driverId + " driver location is " + driverLocation[0]+","+driverLocation[1]);

        return driverLocation;
    }
    public static String getWaypointId(String reference,int statusId) {
        String waypointId = "";
        String queryString;
        String pickupID = getPickupId(reference);
        if(statusId==26 || statusId==27 || statusId==28){
            queryString = "select delivery_stop_ref from delivery_stop where pickupid ="+pickupID+" and delivery_stop_type=2";
        }else{
         queryString = "select delivery_stop_ref from delivery_stop where pickupid ="+pickupID+" and delivery_stop_type=1";
        }
        waypointId = getDataFromMySqlServer(queryString);
        logger.detail("Waypoint ID is "+waypointId);
        return waypointId;
    }
}