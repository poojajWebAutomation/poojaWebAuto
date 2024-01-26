package com.bungii.ios.utilityfunctions;

import com.bungii.common.manager.DbContextManager;
import com.bungii.common.utilities.LogUtility;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String queryString = "SELECT CustomerRef FROM customer WHERE Phone = " + phoneNumber;
        custRef = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "customer reference is " + custRef);
        return custRef;
    }

    public static String getEstimateTime(String custRef) {
        String estTime = "";
        String queryString = "SELECT EstTime FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        estTime = getDataFromMySqlServer(queryString);
        logger.detail("For customer reference  " + custRef + " Extimate time is " + estTime);
        return estTime;
    }

    public static String getEstimateDistance(String custRef) {
        String estTime = "";
        String queryString = "SELECT EstDistance FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        estTime = getDataFromMySqlServer(queryString);
        logger.detail("For customer reference  " + custRef + " Extimate time is " + estTime);
        return estTime;
    }

    public static String getPickupID(String custRef) {
        String PickupID = "";
        String queryString = "SELECT PickupID FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        PickupID = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference " + custRef + " Extimate time is " + PickupID);
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

            for(int i=0;i<8 && !isDriverEligible;i++){
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
        String pickupRef="";
        if(customerPhone!="") {
            String custRef = getCustomerRefference(customerPhone);
             pickupRef = getDataFromMySqlServer("SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1");
        }
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

    public static String getDriverCurrentToken(String phoneNumber){

        String queryString2 = "select Token from driverlogin where driverid in (select id from driver where Phone= '"+phoneNumber+"')  and OutTime is null";
        String deviceToken = getDataFromMySqlMgmtServer(queryString2);
        return deviceToken;
    }

     public static String getCustomerCurrentToken(String phoneNumber){

        String queryString2 = "select Token from customerlogin where customerid in (select id from customer where Phone= '"+phoneNumber+"') and OutTime is null";
        String deviceToken = getDataFromMySqlMgmtServer(queryString2);
        return deviceToken;
    }
    public static String getPushNotificationContent(String phoneNumber, String pickupRef){
        String queryString2= "select Payload from pushnotification where userid in (select Id from driver where phone = '"+phoneNumber+"') and Payload Like '%"+pickupRef+"%'";
        String Payload = getDataFromMySqlServer(queryString2);
        logger.detail("Query : "+ queryString2 +" | Payload : "+ Payload);
        return Payload;
    }

    public static String getCustomerPushNotificationContent(String customerPhoneNum, String pickupRef, String content)
    {
        String queryString2= "select Payload from pushnotification where userid in (select Id from customer where phone = '"+customerPhoneNum+"') and Payload Like '%"+pickupRef+"%' and Payload Like '%"+content+"%'";
        String Payload = getDataFromMySqlServer(queryString2);
        logger.detail("Query : "+ queryString2 +" | Payload : "+ Payload);
        return Payload;

    }
    public static String getDriverPushNotificationContent(String driverPhoneNum, String pickupRef, String content)
    {
        String queryString2= "select Payload from pushnotification where userid in (select Id from driver where phone = '"+driverPhoneNum+"') and Payload Like '%"+pickupRef+"%' and Payload Like '%"+content+"%'";
        String Payload = getDataFromMySqlServer(queryString2);
        logger.detail("Query : "+ queryString2 +" | Payload : "+ Payload);
        return Payload;

    }
    public static String getCustomerName(String phoneNumber) {
        String fullname = "";
        String queryString = "SELECT fullname FROM customer WHERE Phone = " + phoneNumber;
        fullname = getDataFromMySqlServer(queryString);
        logger.detail("Fullname is" + fullname + ", query, " + queryString);
        return fullname;
    }

    public static String checkRejectionReason(String driverPhone){
        String driverId=getDataFromMySqlServer("Select Id from driver where phone= "+driverPhone);
        String reasonId = getDataFromMySqlServer("select pickup_driver_reject_reason_id from pickup_driver_reject_reason where driver_id = "+driverId);
        return reasonId;
    }
    public static String getDriverShareSameTier() {
        String driverShare = "";
        String queryString = "select driver_share from bungii_admin_qa_auto.bp_service_level sl\n" +
                "join bungii_admin_qa_auto.bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bungii_admin_qa_auto.bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bungii_admin_qa_auto.bp_fixed_distance_weight_price_matrix pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like 'qaauto-floordecor166%' and sl.service_level_number = 1 and weight_range_min = '1000' and weight_range_max = '1500' \n" +
                "and mile_range_min = '0' and mile_range_max = '10'\n" +
                "order by subdomain_name, sl.service_level_number, mile_range_min, weight_range_min;";
        driverShare = getDataFromMySqlServer(queryString);
        logger.detail("Driver Share is "+ driverShare);
        return driverShare;
    }
    public static String getDriverShareDifferentTier() {
        String driverShare = "";
        String queryString = "select driver_share from bungii_admin_qa_auto.bp_service_level sl\n" +
                "join bungii_admin_qa_auto.bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bungii_admin_qa_auto.bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bungii_admin_qa_auto.bp_fixed_distance_weight_price_matrix pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like 'qaauto-floordecor166%' and sl.service_level_number = 1 and weight_range_min = '0' and weight_range_max = '500' \n" +
                "and mile_range_min = '0' and mile_range_max = '10'\n" +
                "order by subdomain_name, sl.service_level_number, mile_range_min, weight_range_min;";
        driverShare = getDataFromMySqlServer(queryString);
        logger.detail("Driver Share is " + driverShare);
        return driverShare;
    }
    public static String getDriverShareDifferentSeviceLevel() {
        String driverShare = "";
        String queryString = "select driver_share from bungii_admin_qa_auto.bp_service_level sl\n" +
                "join bungii_admin_qa_auto.bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bungii_admin_qa_auto.bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bungii_admin_qa_auto.bp_fixed_distance_weight_price_matrix pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like 'qaauto-floordecor166%' and sl.service_level_number = 2 and weight_range_min = '1000' and weight_range_max = '1500' " +
                "and mile_range_min = '20' and mile_range_max = '30'\n" +
                "order by subdomain_name, sl.service_level_number, mile_range_min, weight_range_min;";
        driverShare = getDataFromMySqlServer(queryString);
        logger.detail("Driver Share is " + driverShare);
        return driverShare;
    }
    public static String getAdminEditTime(String pickUpID) {
        String time = "";
        String queryString = "select created_on from pickup_history where pickupid="+pickUpID+" limit 1";
        time = getDataFromMySqlServer(queryString);

        logger.detail("For pickUpID " + pickUpID + " admin edit time is " + time);
        return time;
    }
    public static String[] getPickupAndDropLocation(String customerPhone){
        String custRef=getCustomerRefference(customerPhone);
        String queryString = "SELECT PickupID FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        String pickupID = getDataFromMySqlServer(queryString);
        String tripLocation[] = new String[4];
        tripLocation[0]=    getDataFromMySqlServer("select PickupLat from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[1]=    getDataFromMySqlServer("select PickupLong from pickupdropaddress  where PickupID= "+pickupID);
        tripLocation[2]=    getDataFromMySqlServer("select DropOffLat from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[3]=    getDataFromMySqlServer("select DropOffLong from pickupdropaddress  where PickupID= "+pickupID);
        logger.detail("For PickupID " + pickupID + " Pickup location is " + tripLocation[0]+","+tripLocation[1]);
        logger.detail("For PickupID " + pickupID + " DropOff location is " + tripLocation[2]+","+tripLocation[3]);
        return tripLocation;
    }
    public static String getLoadUnloadTime(String pickUpID) {
        String loadUnloadTime = "";
        String queryString = "select LoadingUnloadingTime from pickupdetails where PickupID = "+pickUpID;
        loadUnloadTime = getDataFromMySqlServer(queryString);
        logger.detail("Load/Unload time required is " + loadUnloadTime);
        return loadUnloadTime;
    }
    public static String getDriverArrivalTime(String pickUpID) {
        String drverArrivalTime = "";
        String queryString = "select ChangeTimeStamp from tripevents where pickupid ="+pickUpID+" and TripStatus = 24";
        drverArrivalTime = getDataFromMySqlServer(queryString);
        logger.detail("Driver Arrival Time is" + drverArrivalTime);
        return drverArrivalTime;
    }

    public static String getPartnerPortalLeadTimeSoloDelivery() {
        String leadTime;
        String queryString = "select json_extract(cvss.config_value,'$.SOLO_EARLIEST_SCHEDULE_TIME') as solo_lead_time from bungii_admin_qa_auto.bp_store bs inner join bungii_admin_qa_auto.bp_store_setting_fn_matrix fn on fn.bp_store_id = bs.bp_store_id inner join bungii_admin_qa_auto.bp_config_version_store_setting cvss on cvss.bp_config_version_id = fn.bp_config_version_id where subdomain_name like  'qauto-equip-bid' and fn.bp_setting_fn_id = 12;";
        leadTime = getDataFromMySqlServer(queryString);
        logger.detail("Partner Portal lead time  is "+leadTime+ " for Solo deliveries");
        return leadTime;

    }

    public static String getPartnerPortalLeadTimeDuoDelivery() {
        String leadTime;
        String queryString = "select json_extract(cvss.config_value,'$.DUO_EARLIEST_SCHEDULE_TIME') as duo_lead_time from bungii_admin_qa_auto.bp_store bs inner join bungii_admin_qa_auto.bp_store_setting_fn_matrix fn on fn.bp_store_id = bs.bp_store_id inner join bungii_admin_qa_auto.bp_config_version_store_setting cvss on cvss.bp_config_version_id = fn.bp_config_version_id where subdomain_name like  'qauto-equip-bid' and fn.bp_setting_fn_id = 12;";
        leadTime = getDataFromMySqlServer(queryString);
        logger.detail("Partner Portal lead time  is "+leadTime+ " for Duo deliveries");
        return leadTime;

    }

    public static String getLinkedPickupRef(String pickupRef) {
        String linkedpickupref = "";
        String queryString = "SELECT PICKUPREF FROM pickupdetails WHERE LINKEDPICKUPID in (SELECT Pickupid FROM pickupdetails WHERE pickupref ='" + pickupRef+"' )";
        linkedpickupref =getDataFromMySqlServer(queryString);
        logger.detail("Linked Pickupref " + linkedpickupref + " of pickupref " + pickupRef );
        return linkedpickupref;
    }
    public static String getDriverStatus(String phoneNumber){
        String driverStatus;
        String entireQueryString = "select OnlineStatus from driver where Phone= " +phoneNumber;
        driverStatus = getDataFromMySqlServer(entireQueryString);
        return driverStatus;
    }
    public static String getDriverRatingFromDriver(String pickupRef){
        String pickupId=getDataFromMySqlServer("select PickupID from pickupdetails where PickupRef = '"+pickupRef+"'");
        String rating = getDataFromMySqlServer("select tr.driverrating from pickupdetails pd\n" +
                "inner join  triprequest tr on tr.pickupid= pd.pickupid\n" +
                "where pickupstatus = 11 and pd.pickupid ="+pickupId+" limit 1;");
        logger.detail("The driver rating for pickupId " + pickupId + " is " + rating);
        return rating;
    }
    public static String getFixedBasedPrice(String minMiles,String maxMiles){
        String amount;
        amount= getDataFromMySqlServer("select  amount\n" +
                "from bungii_admin_qa_auto.bp_service_level sl\n" +
                "join bungii_admin_qa_auto.bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bungii_admin_qa_auto.bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bungii_admin_qa_auto.bp_service_level_fixed_distance_price pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like 'qaauto-biglots%' and sl.service_level_number =1 and mile_range_min ="+minMiles+" and mile_range_max="+maxMiles+" and no_of_drivers=1\n" +
                "order by subdomain_name, sl.service_level_number, pr.tier_number, pr.no_of_drivers;");
        logger.detail("The amount for delivery is "+amount);
        return amount;
    }
    public static String[] getLatAndLonPickupAndDropLocation(String reference){
        String pickupID = getPickupIdFromRef(reference);
        String tripLocation[] = new String[4];
        tripLocation[0]=    getDataFromMySqlServer("select PickupLat from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[1]=    getDataFromMySqlServer("select PickupLong from pickupdropaddress  where PickupID= "+pickupID);
        tripLocation[2]=    getDataFromMySqlServer("select DropOffLat from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[3]=    getDataFromMySqlServer("select DropOffLong from pickupdropaddress  where PickupID= "+pickupID);
        logger.detail("For PickupID " + pickupID + " Pickup location is " + tripLocation[0]+","+tripLocation[1]);
        logger.detail("For PickupID " + pickupID + " DropOff location is " + tripLocation[2]+","+tripLocation[3]);
        return tripLocation;
    }
    public static String getPickupIdFromRef(String pickupRef) {
        String pickupid = "";
        String queryString = "SELECT Pickupid FROM pickupdetails WHERE pickupref ='" + pickupRef + "'";
        pickupid = getDataFromMySqlServer(queryString);
        logger.detail("Pickupid  " + pickupid + " of pickupref " + pickupRef);
        return pickupid;
    }
    public static String getFixedBasedDriverCut(String minMiles,String maxMiles){
        String amount;
        amount= getDataFromMySqlServer("select  driver_share\n" +
                "from bungii_admin_qa_auto.bp_service_level sl\n" +
                "join bungii_admin_qa_auto.bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bungii_admin_qa_auto.bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bungii_admin_qa_auto.bp_service_level_fixed_distance_price pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like 'qaauto-biglots%' and sl.service_level_number =1 and mile_range_min ="+minMiles+" and mile_range_max="+maxMiles+" and no_of_drivers=1\n" +
                "order by subdomain_name, sl.service_level_number, pr.tier_number, pr.no_of_drivers;");
        logger.detail("The amount for delivery is "+amount);
        return amount;
    }
    public static String getDriverShareWeightBased(String minMiles,String maxMiles) {
        String driverShare = "";
        String queryString = "select driver_share from bungii_admin_qa_auto.bp_service_level sl\n" +
                "join bungii_admin_qa_auto.bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bungii_admin_qa_auto.bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bungii_admin_qa_auto.bp_fixed_distance_weight_price_matrix pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like 'qaauto-floordecor166%' and sl.service_level_number = 1 and weight_range_min = '1000' and weight_range_max = '1500' \n" +
                "and mile_range_min = '"+minMiles+"' and mile_range_max = '"+maxMiles+"'\n" +
                "order by subdomain_name, sl.service_level_number, mile_range_min, weight_range_min;";
        driverShare = getDataFromMySqlServer(queryString);
        logger.detail("Driver Share is "+ driverShare);
        return driverShare;
    }
    public static String getDriverBranchRegistrationDate(String driver){
        String date=getDataFromMySqlServer("select BranchRegisteredDate from bungii_admin_qa_auto.driver where phone ="+driver);
        logger.detail("The branch registered date for driver is " +date);
        return date;
    }
    public static String getDriverWalletInfo(String driver){
        String id=getDataFromMySqlServer("select Id from bungii_admin_qa_auto.driver where phone = "+driver);
        String wallet=getDataFromMySqlServer("select DriverAccountInfoBranch from bungii_admin_qa_auto.driverdetail where driver = "+id);
        logger.detail("The wallet info for driver: " +wallet);
        return wallet;
    }

    public static String getStatusTimestamp(String Pickup_Reference) {
        String timeStamp;
        String tripStatus;
        String queryStringForPickupTripStatus ="select tripevents.TripStatus from tripevents join pickupdetails on tripevents.PickupID=pickupdetails.PickupID where PickupRef ='"+Pickup_Reference+ "' order by TripStatus desc limit 1";
        tripStatus = getDataFromMySqlServer(queryStringForPickupTripStatus);
        logger.detail("TripStatus is "+tripStatus+ " for pickup reference "+ Pickup_Reference);
        String queryStringForTime = "select StatusTimestamp from tripevents where TripStatus ='"+tripStatus+"'";
        timeStamp = getDataFromMySqlServer(queryStringForTime);
        logger.detail("StatusTimestamp is "+timeStamp+ " for pickup reference "+ Pickup_Reference);
        return timeStamp;

    }

    public static String getTelet(String pickupRef) {
        String custRef = "";
        String queryString = "select Telet from pickupdetails where PickupRef ='"+pickupRef+"'";
        custRef = getDataFromMySqlServer(queryString);
        logger.detail("Telet for pickup reference " + pickupRef + " is " + custRef);
        return custRef;
    }

    public static String getPickupId(String pickupRef) {
        String pickupid = "";
        String queryString = "SELECT Pickupid FROM pickupdetails WHERE pickupref ='" + pickupRef + "'";
        pickupid = getDataFromMySqlServer(queryString);
        logger.detail("Pickupid  " + pickupid + " of pickupref " + pickupRef);
        return pickupid;
    }

    public static String[] getArrivalTimeAndLoadingUnloadingTime(String Pickup_Reference) {
        String trackingID ;
        String[] ArrivalAndLoadingUnloadingTimeAndEstTime = new String[3];
        String toGetTrackingId="select pickup_token from pickupdetails where PickupRef = '"+Pickup_Reference+"'";
        trackingID = getDataFromMySqlServer(toGetTrackingId);
        logger.detail("Tracking Id for "+Pickup_Reference+" is "+ trackingID);
        String toGetEstTime="select EstTime from pickupdetails where PickupRef = '"+Pickup_Reference+"'";
        ArrivalAndLoadingUnloadingTimeAndEstTime[0] = getDataFromMySqlServer(toGetEstTime);
        logger.detail("EstTime for "+Pickup_Reference+" is "+ ArrivalAndLoadingUnloadingTimeAndEstTime[0]);
        String queryStringForarrivalTime ="SELECT pickupdate FROM bungii_reports_qa_auto.factpickup where pickup_token='"+trackingID+"'";
        String queryStringForLoadingUnloadingTime ="SELECT loadingunloadingtime  FROM bungii_reports_qa_auto.factpickup where pickup_token='"+trackingID+"'";
        ArrivalAndLoadingUnloadingTimeAndEstTime[1]=getDataFromMySqlServer(queryStringForarrivalTime);
        ArrivalAndLoadingUnloadingTimeAndEstTime[2]=getDataFromMySqlServer(queryStringForLoadingUnloadingTime);
        logger.detail("The expected Arrival time for the delivery having pickup refernce "+Pickup_Reference+" is "+ ArrivalAndLoadingUnloadingTimeAndEstTime[1]);
        logger.detail("The expected Loading/Unloading time for the delivery having pickup refernce "+Pickup_Reference+" is "+ ArrivalAndLoadingUnloadingTimeAndEstTime[2]);
        return ArrivalAndLoadingUnloadingTimeAndEstTime;
    }

    public static String[] getArrivalTimeAndLoadingUnloadingTimeForCustomer(String Cust_Reference) {
        String[] ArrivalAndLoadingUnloadingTimeAndEstTimeForCustomer = new String[4];
        String toGetEstTime="SELECT EstTime FROM pickupdetails WHERE CustomerRef ='"+Cust_Reference+"'order by pickupid desc limit 1";
        ArrivalAndLoadingUnloadingTimeAndEstTimeForCustomer[0] = getDataFromMySqlServer(toGetEstTime);
        logger.detail("EstTime for "+Cust_Reference+" is "+ ArrivalAndLoadingUnloadingTimeAndEstTimeForCustomer[0]);
        String queryStringForarrivalTime ="SELECT  ScheduledTimestamp FROM pickupdetails WHERE CustomerRef='"+Cust_Reference+"' order by pickupid desc limit 1";
        String queryStringForLoadingUnloadingTime ="SELECT  loadingunloadingtime FROM pickupdetails WHERE CustomerRef='"+Cust_Reference+"'order by pickupid desc limit 1";
        ArrivalAndLoadingUnloadingTimeAndEstTimeForCustomer[1]=getDataFromMySqlServer(queryStringForarrivalTime);
        ArrivalAndLoadingUnloadingTimeAndEstTimeForCustomer[2]=getDataFromMySqlServer(queryStringForLoadingUnloadingTime);
        logger.detail("The expected Arrival time for the delivery having customer refernce "+Cust_Reference+" is "+ ArrivalAndLoadingUnloadingTimeAndEstTimeForCustomer[1]);
        logger.detail("The expected Loading/Unloading time for the delivery having customer refernce "+Cust_Reference+" is "+ ArrivalAndLoadingUnloadingTimeAndEstTimeForCustomer[2]);
        return ArrivalAndLoadingUnloadingTimeAndEstTimeForCustomer;
    }
    public static String getDisbursementType(String pickUpRef,String driverPhone) {
        String disbursementType;
        String driverId;
        String queryString = "Select Id from driver where phone= "+driverPhone;
        driverId = getDataFromMySqlMgmtServer(queryString);
        logger.detail("The driver Id for "+driverPhone+" is "+driverId);
        String queryString1 ="select disbursement_type from payment_trans_disburse_branch where payment_transaction_id in (select Id from paymenttransaction where clientgroupref in ('"+pickUpRef+"')) and driver_id="+driverId;
        disbursementType = getDataFromMySqlMgmtServer(queryString1);
        logger.detail("The disbursement type for "+driverId+" is "+disbursementType);
        return disbursementType;
    }
    public static String getExternalRefernce(String pickUpRef) {
        String reference;
        String queryString1 ="select ExternalTransactionRef from bungii_admin_qa_auto.paymenttransaction where clientgroupref ='"+pickUpRef+"'";
        reference = getDataFromMySqlMgmtServer(queryString1);
        logger.detail("The external reference is "+reference);
        return reference;
    }
    public static String getPickupRefForPartnerTrips(String phoneNumber) {
        String pickupId = "";
        String pickupRef = "";
        String queryString = "SELECT pickup_id from pickup_additional_info where customer_phone=" + phoneNumber + " order by  pickup_id desc limit 1";
        pickupId = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + " latest PickupId is " + pickupId);
        String queryString1 = "SELECT PickupRef FROM pickupdetails WHERE PickupId= "+pickupId;
        pickupRef = getDataFromMySqlServer(queryString1);
        logger.detail("For Pickup ID " + pickupId + " latest Pickup Ref is " + pickupRef);
        return pickupRef;
    }
}