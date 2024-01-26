package com.bungii.web.utilityfunctions;

import com.bungii.common.manager.DbContextManager;
import com.bungii.common.utilities.LogUtility;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbUtility extends DbContextManager {
    private static LogUtility logger = new LogUtility(DbUtility.class);
    //Sample function

    /**
     * Connect to MS sql server and get verification SMS verification code
     *
     * @param phoneNumber
     * @return
     */
    public static String getVerificationCode(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT SmsVerificationCode FROM driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }

    public static String getDriverRatings(String pickupref) {
        String rating = "";
        String queryString = "select DriverRating from triprequest where pickupid in (select pickupid from pickupdetails where pickupref='"+pickupref+"')";
        rating = getDataFromMySqlServer(queryString);
        logger.detail("Driver Rating is" + rating + ", query, " + queryString);
        return rating;
    }
    public static String getDriverShare(String pickupref) {
        String amount = "";
        String queryString = "select submerchant_amount from triprequest where pickupid in (select pickupid from pickupdetails where pickupref='"+pickupref+"')";
        amount = getDataFromMySqlServer(queryString);
        logger.detail("Driver submerchant_amount is" + amount + ", query, " + queryString);
        return amount;
    }

    public static String getScheduledTime(String customerPhone) {
        String pickupId = getPickupIdfrom_pickup_additional_info(customerPhone);
        String Scheduled_Time = getDataFromMySqlServer("SELECT ScheduledTimestamp FROM pickupdetails WHERE pickupid = '" + pickupId + "' order by pickupid desc limit 1");
        return Scheduled_Time;
    }
    public static String getScheduledDays(String subdomain) {
        String scheduledTime = getDataFromMySqlMgmtServer("select advance_schedule_days from bp_store st join bp_store_portal_setting ps on ps.bp_store_id = st.bp_store_id where st.subdomain_name like '%" + subdomain + "%';");
        return scheduledTime;
    }

    public static String getPickupRef(String customerPhone){
        String pickupId=getPickupIdfrom_pickup_additional_info(customerPhone);
        String pickupRef=getDataFromMySqlServer("SELECT PickupRef FROM pickupdetails WHERE pickupid = '" + pickupId + "' order by pickupid desc limit 1");
        return pickupRef;
    }

    public static String getPickupIdfrom_pickup_additional_info(String phoneNumber) {
        String pickupId = "";
        //String queryString = "SELECT CustomerRef  FROM customer WHERE Phone = " + phoneNumber;
        String queryString = "SELECT pickup_id from pickup_additional_info where customer_phone=" + phoneNumber + " order by  pickup_id desc limit 1";
        pickupId = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "latest PickupId is " + pickupId);
        return pickupId;
    }

    public static boolean isPhoneNumberUnique(String phoneNumber) {
        String id = "";
        String queryString = "SELECT Id FROM driver WHERE Phone = " + phoneNumber;
        id = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Phone Number " + phoneNumber + "is unique " + id.equals(""));
        return id.equals("");
    }

    public static String getLatestPickupRefOfCustomer(String customerRef) {
        String pickupRef = "";
        String queryString = "SELECT PickupRef FROM pickupdetails WHERE CustomerRef='" + customerRef + "' ORDER BY Pickupid DESC LIMIT 1";
        pickupRef = getDataFromMySqlServer(queryString);
        logger.detail("Pickup Reference " + pickupRef + " of customer " + customerRef);
        return pickupRef;
    }

    public static String getCustomerPhone(String firstName, String lastName) {
        String phone = "";
        String queryString = "SELECT Phone FROM customer WHERE FirstName like'" + firstName + "' and Lastname like '" + lastName + "'LIMIT 1";
        phone = getDataFromMySqlServer(queryString);
        logger.detail("Phone  " + phone + " of customer " + firstName + " " + lastName);
        return phone;
    }

    public static String getCustomerEmail(String firstName, String lastName) {
        String email = "";
        String queryString = "SELECT EmailAddress FROM customer WHERE FirstName like'" + firstName + "' and Lastname like '" + lastName + "'LIMIT 1";
        email = getDataFromMySqlServer(queryString);
        logger.detail("Email  " + email + " of customer " + firstName + " " + lastName);
        return email;
    }

    public static String getPickupId(String pickupRef) {
        String pickupid = "";
        String queryString = "SELECT Pickupid FROM pickupdetails WHERE pickupref ='" + pickupRef + "'";
        pickupid = getDataFromMySqlServer(queryString);
        logger.detail("Pickupid  " + pickupid + " of pickupref " + pickupRef);
        return pickupid;
    }

    public static List<HashMap<String, Object>> getDriverRating(String pickupId) {
        // String driverRating ;
        List<HashMap<String, Object>> driverRating = new ArrayList<>();
        String queryString = "SELECT DriverRating from triprequest where pickupid ='" + pickupId + "'";
        //driverRating =getDataFromMySqlServer(queryString);
        driverRating = getDataFromMySqlServerMap(queryString);
        logger.detail("Driver Rating =  " + driverRating + " is shown in database for PickupId= " + pickupId);
        return driverRating;
    }

    public static String getPickupIdFromFactPickup(String pickupRef) {
        String pickupid = "";
        String queryString = "SELECT id FROM factpickup WHERE pickupref ='" + pickupRef + "'";
        pickupid = getDataFromMySqlReportServer(queryString);
        logger.detail("Pickupid  " + pickupid + " of pickupref " + pickupRef);
        return pickupid;
    }

    public static String getCustomerServiceNote(String pickupReference) {
        String note = "";
        String initialId = "";
        String queryString = "SELECT initial_pickup_id FROM factpickup WHERE pickupref ='" + pickupReference + "'";
        initialId = getDataFromMySqlReportServer(queryString);
        String queryNote = "select note from fact_pickup_note where initial_pickup_id  ='" + initialId + "'";
        note = getDataFromMySqlReportServer(queryNote);
        logger.detail("Customer Note = " + note + " is shown in database for pickup " + pickupReference);
        return note;
    }

    public static String getLinkedPickupRef(String pickupRef) {
        String linkedpickupref = "";
        String queryString = "SELECT PICKUPREF FROM pickupdetails WHERE LINKEDPICKUPID in (SELECT Pickupid FROM pickupdetails WHERE pickupref ='" + pickupRef + "' )";
        linkedpickupref = getDataFromMySqlServer(queryString);
        logger.detail("Linked Pickupref " + linkedpickupref + " of pickupref " + pickupRef);
        return linkedpickupref;
    }

    public static String getPickupLocation(String pickupId) {
        String pickupLat, pickupLong, location = "";
        String queryString = "SELECT concat(ifnull(pickuplat,''),',',ifnull(pickuplong,'')) FROM pickupdropaddress WHERE pickupId =" + pickupId;
        location = getDataFromMySqlServer(queryString);
        //queryString = "SELECT PickupLong FROM customer WHERE pickupId ='" + pickupId;
        // pickupLong =getDataFromMySqlServer(queryString);
        // location = pickupLat+","+pickupLong;
        logger.detail("Location  " + location + " of PickupId " + pickupId);
        return location;
    }

    public static String getOndemandStartTime(String pickupref) {
        String ondemandStartTime = "";
        String queryString = "SELECT StatusTimestamp  FROM tripevents where TripStatus = 23 and pickupid in (SELECT pickupid FROM pickupdetails WHERE pickupref ='" + pickupref + "')";
        ondemandStartTime = getDataFromMySqlServer(queryString);
        logger.detail("Ondemand Start time  " + ondemandStartTime + " of PickupRef " + pickupref);
        return ondemandStartTime;
    }

    public static String getBungiiRate(String Geofence) {
        String bungiiRate = "";
        String queryString = "select Value from geofencesettings where GeofenceAttributeID=11 and GeofenceVersionID   in (select GeofenceSettingVersionID  from geofencesettingsversions where Geofenceid in (select id from geofence where name='" + Geofence + "') and IsActive = 1)";
        bungiiRate = getDataFromMySqlServer(queryString);
        logger.detail("Bungii Rate for  " + Geofence + " Geofence is " + bungiiRate);
        return bungiiRate;
    }

    public static String getEstPrice(String PickupRequest) {
        String ActualPriceAfterDiscount = "";
        String queryString = "SELECT ActualEstCostAfterDiscount  FROM pickupdetails where PickupRef='" + PickupRequest + "'";
        ActualPriceAfterDiscount = getDataFromMySqlServer(queryString);
        logger.detail("Actual Cost after discount for  " + PickupRequest + " is " + ActualPriceAfterDiscount);
        return ActualPriceAfterDiscount;
    }

    public static String getActualPrice(String PickupRequest) {
        String ActualPriceAfterDiscount = "";
        Integer PickupId;
        Integer TRID;
        String queryString = "select ActualCostAfterDiscount from trippaymentdetails where trid in(select TRID from triprequest where Pickupid in(SELECT PickupID  FROM pickupdetails where PickupRef='" + PickupRequest + "'))";

        ActualPriceAfterDiscount = getDataFromMySqlServer(queryString);
        logger.detail("Actual Cost after discount for  " + PickupRequest + " is " + ActualPriceAfterDiscount);
        return ActualPriceAfterDiscount;
    }

    public static List<HashMap<String, Object>> getListOfGeoFenceIds() {
        List<HashMap<String, Object>> listOfGeofenceIds = new ArrayList<>();
        String queryString = "select gsv.geofenceID from geofence gf\n" +
                "inner join geofencesettingsversions gsv on gf.Id = gsv.geofenceID\n" +
                "group by gsv.geofenceID";
        listOfGeofenceIds = getDataFromMySqlServerMap(queryString);
        return listOfGeofenceIds;
    }


    public static int getGeofenceSettingsVersions(int geofenceId) {
        int geoFenceRefId = 0;
        //select *   from  geofencesettingsversions where GeofenceID = '12' and IsActive = true
        String queryString = "select GeofenceSettingVersionID from geofencesettingsversions where geofenceID ='" + geofenceId + "' and isActive = true";
        geoFenceRefId = Integer.parseInt(getDataFromMySqlServer(queryString));
        logger.detail("geoFenceRefId  " + geoFenceRefId + " of geofenceId " + geofenceId);
        return geoFenceRefId;
    }

    public static int getGeofenceSettings(int geoFenceSettingVerId) {
        int geoFenceSettingVerRef = 0;
        //select *   from  geofencesettingsversions where GeofenceID = '12' and IsActive = true
        String queryString = "select count(*) from geofencesettings where GeofenceSettingVersionID ='" + geoFenceSettingVerId + "'";
        System.out.println(queryString);
        geoFenceSettingVerRef = Integer.parseInt(getDataFromMySqlMgmtServer(queryString));
        logger.detail("geoFenceSettingVerRef  " + geoFenceSettingVerRef + " of geofencesettingVersionID " + geoFenceSettingVerId);
        return geoFenceSettingVerRef;
    }


    public static int getGeofenceAttributes() {
        int returnedKey = 0;
        String queryString = "SELECT count(*) FROM geofenceattributes";
        returnedKey = Integer.parseInt(getDataFromMySqlMgmtServer(queryString));
        logger.detail("Value returned " + returnedKey);
        return returnedKey;
    }

    public static List<HashMap<String, Object>> fetchAllDataForGeoFence() {
        List<HashMap<String, Object>> listOfGeofences = new ArrayList<>();
        String queryString = "select count(*),  gfs.GeofenceSettingVersionID from geofence gf\n" +
                "inner join geofencesettingsversions gsv on gf.Id = gsv.geofenceID and gsv.isActive=true\n" +
                "inner join geofencesettings gfs on gsv.GeofenceSettingVersionID = gfs.GeofenceSettingVersionID \n" +
                "group by gfs.GeofenceSettingVersionID";
        listOfGeofences = getDataFromMySqlServerMap(queryString);
        return listOfGeofences;
    }

    public static String getEstimateDistance(String alias) {
        String Estimate_distance;
        // String queryString = "SELECT EstDistance FROM pickupdetails where customerRef='"+customerReference+"'order by  pickupid desc limit 1";
//        String queryString = "SELECT EstDistance FROM pickupdetails where customerRef in (select customerRef from customer where Id in (select customer_id from business_partner_location where alias like '" + alias + "')) order by  pickupid  desc limit 1";
        String queryString1 = "select Reference from bp_store bp join customer c on c.id = bp.customer_id where store_alias ='" + alias + "'";
        String partnerReference = getDataFromMySqlMgmtServer(queryString1);
        String queryString2 = "SELECT EstDistance FROM pickupdetails where customerRef = '" + partnerReference + "' order by  pickupid desc limit 1";
        Estimate_distance = getDataFromMySqlServer(queryString2);
        logger.detail("Estimate Distance =  " + Estimate_distance + " of latest delivery");
        return Estimate_distance;

    }

    public static String getEstimateDistanceByPartnerReference(String partnerRef) {
        String Estimate_distance;
//        String queryString = "SELECT EstDistance FROM pickupdetails where customerRef in (select CustomerRef from business_partner_location bpl join customer c on c.id = bpl.customer_id where business_partner_location_ref = '"+partnerRef+"')order by  pickupid desc limit 1";
        String queryString1 = "select Reference from bungii_admin_qa_auto.bp_store bp join bungii_admin_qa_auto.customer c on c.id = bp.customer_id where bp_store_ref ='" + partnerRef + "'";
        String partnerReference = getDataFromMySqlMgmtServer(queryString1);
        String queryString2 = "SELECT EstDistance FROM pickupdetails where customerRef = '" + partnerReference + "' order by  pickupid desc limit 1";
        Estimate_distance = getDataFromMySqlServer(queryString2);
        logger.detail("Estimate Distance =  " + Estimate_distance + " of Partner Location Reference ");
        return Estimate_distance;

    }

    public static String getDriverReference(String phoneNumber) {
        String driverRef = "";
        String queryString = "SELECT DriverRef  FROM driver WHERE Phone = " + phoneNumber;
        driverRef = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "DriverRef is " + driverRef);
        return driverRef;
    }

    public static List<String> getHoursWorkedQuarterToDate(String driverRef, String quarterStartDate, String quarterEndDate) {
        List<String> intialTime=null;
        String queryString = "select actualtime\n" +
                "from factpickup fp\n" +
                "inner join facttrip ft on ft.pickupid = fp.id\n" +
                "inner join dimdriver d on d.id = ft.driver\n" +
                "where pickup_datetime_per_tz between '"+quarterStartDate+"' and '"+quarterEndDate+"'\n" +
                "and fp.pickupstatus in (10, 11, 14, 28)\n" +
                "and d.reference = '"+driverRef+"'";
        //intialTime = getDataFromMySqlServerMap(queryString);
        intialTime = getListDataFromMySqlReportServer(queryString);

        //intialTime = getDataFromMySqlServerMap(queryString);
        return intialTime;
    }

    public static String getEstimateTimeByPartnerReference(String partnerRef) {
        String Estimate_time;
//        String queryString = "SELECT EstTime FROM pickupdetails where customerRef in (select CustomerRef from business_partner_location bpl join customer c on c.id = bpl.customer_id where business_partner_location_ref = '"+partnerRef+"')order by  pickupid desc limit 1";
        String queryString1 = "select Reference from bungii_admin_qa_auto.bp_store bp join bungii_admin_qa_auto.customer c on c.id = bp.customer_id where bp_store_ref ='" + partnerRef + "'";
        String partnerReference = getDataFromMySqlMgmtServer(queryString1);
        String queryString2 = "SELECT EstTime FROM pickupdetails where customerRef = '" + partnerReference + "' order by  pickupid desc limit 1";
        Estimate_time = getDataFromMySqlServer(queryString2);
        logger.detail("Estimate Time =  " + Estimate_time + " of latest trip of Partner Location Reference " + partnerRef);
        return Estimate_time;

    }

    public static String getServicePrice(String Alias, int No_of_Driver, String Trip_Estimate_Distance, String Service_name) {
        String Trip_Price;

        String queryString = "select amount\n" +
                "from bp_service_level sl\n" +
                "join bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bp_service_level_fixed_distance_price pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like '%" + Alias + "%'\n" +
                "and sl.service_name = '" + Service_name + "'\n" +
                "and no_of_drivers=" + No_of_Driver + " and  " + Trip_Estimate_Distance + " BETWEEN mile_range_min and mile_range_max\n" +
                "order by sl.service_level_number, pr.tier_number, pr.no_of_drivers";

        Trip_Price = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Calculated Estimated Service Price  =  " + Trip_Price + " of " + Service_name + " [ Alias :" + Alias + " ]" + " For Drivers : " + No_of_Driver + " | Trip Distance : " + Trip_Estimate_Distance);
        return Trip_Price;

    }

    public static String getServicePriceLastTier(String Alias, int No_of_Driver, String Trip_Estimate_Distance, String Service_name) {
        Double Trip_Price;
        String Last_Tier;
        Double Second_Last_Tier_Amount;
        double Second_Last_Tier_Milenge;

        //Query for selecting last tier number
        String queryString = "select max(tier_number)\n" +
                "from bp_service_level sl\n" +
                "join bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bp_service_level_fixed_distance_price pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like '%" + Alias + "%'\n" +
                "and sl.service_name = '" + Service_name + "'\n" +
                "order by subdomain_name, sl.service_level_number, pr.tier_number, pr.no_of_drivers";
        Last_Tier = getDataFromMySqlMgmtServer(queryString);
        int Last_Tier_Number = Integer.parseInt(Last_Tier);
        logger.detail("Last_Tier_Number : " + Last_Tier_Number);
        int Second_Last_Tier = Last_Tier_Number - 1;
        logger.detail("Second_Last_Tier : " + Second_Last_Tier);

        //Query for selecting second last tier amount
        String queryString1 = "select amount\n" +
                "from bp_service_level sl\n" +
                "join bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bp_service_level_fixed_distance_price pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like '%" + Alias + "%'\n" +
                "and sl.service_name = '" + Service_name + "'\n" +
                "and no_of_drivers=" + No_of_Driver + " and tier_number=" + Second_Last_Tier + "\n" +
                "order by sl.service_level_number, pr.tier_number, pr.no_of_drivers";
        Second_Last_Tier_Amount = Double.parseDouble(getDataFromMySqlMgmtServer(queryString1));
        logger.detail("Second_Last_Tier_Amount =  " + Second_Last_Tier_Amount);

        String queryString2 = "select mile_range_max\n" +
                "from bp_service_level sl\n" +
                "join bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bp_service_level_fixed_distance_price pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like '%" + Alias + "%'\n" +
                "and sl.service_name = '" + Service_name + "'\n" +
                "and no_of_drivers=" + No_of_Driver + " and tier_number=" + Second_Last_Tier + "\n" +
                "order by sl.service_level_number, pr.tier_number, pr.no_of_drivers";
        Second_Last_Tier_Milenge = Double.parseDouble(getDataFromMySqlMgmtServer(queryString2));
        logger.detail("Second_Last_Tier_Milenge =  " + Second_Last_Tier_Milenge);
        Double Remaining_Milenge = Double.parseDouble(Trip_Estimate_Distance) - Second_Last_Tier_Milenge;
        logger.detail("Remaining_Milenge =  " + Remaining_Milenge);

        //Multiplying factor
        String queryString3 = "select amount\n" +
                "from bp_service_level sl\n" +
                "join bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bp_service_level_fixed_distance_price pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like '%" + Alias + "%'\n" +
                "and sl.service_name = '" + Service_name + "'\n" +
                "and no_of_drivers=" + No_of_Driver + " and tier_number=" + Last_Tier_Number + "\n" +
                "order by sl.service_level_number, pr.tier_number, pr.no_of_drivers";


        Double Multiplier = Double.parseDouble(getDataFromMySqlMgmtServer(queryString3));
        logger.detail("Multiplier =  " + Multiplier);
        Double Cal2 = Remaining_Milenge * Multiplier;
        Trip_Price = Second_Last_Tier_Amount + Cal2;
        DecimalFormat dec = new DecimalFormat("#.00");
        String priceValue = dec.format(Trip_Price).toString();
        logger.detail("Calculated Estimate amount [For Exceeding Last Tier] =  " + priceValue + " of latest trip");
        return priceValue;

    }

    public static String getMaxMilengeValue(String Alias, String Service_name) {
        String Max_Value_Min_Milenge;

        String queryString = "select max(mile_range_min)\n" +
                "from bp_service_level sl\n" +
                "join bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bp_service_level_fixed_distance_price pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like '%" + Alias + "%'\n" +
                "and sl.service_name = '" + Service_name + "'\n" +
                "order by subdomain_name, sl.service_level_number, pr.tier_number, pr.no_of_drivers";


        Max_Value_Min_Milenge = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Max mile_range_min =  " + Max_Value_Min_Milenge + " of Alias " + Alias + " and service " + Service_name);
        return Max_Value_Min_Milenge;

    }


    public static String getAccessorialCharge(String pickupref) {
        String accessorial_Charge;
        String queryString = "SELECT accessorial_fee_amount FROM triprequest WHERE pickupid IN (SELECT  pickupid FROM pickupdetails WHERE pickupref='" + pickupref + "');";
        accessorial_Charge = getDataFromMySqlServer(queryString);
        logger.detail("Total Accessorial Charge =  " + accessorial_Charge + " of delivery " + pickupref);
        return accessorial_Charge;

    }

    public static String getBusinessNotes(String pickupref) {
        String business_notes;
        String queryString = "SELECT business_notes FROM trippaymentdetails where TRID in (SELECT TRID FROM triprequest WHERE pickupid IN (SELECT  pickupid FROM pickupdetails WHERE pickupref='" + pickupref + "'));";
        business_notes = getDataFromMySqlServer(queryString);
        logger.detail("Business Note =  " + business_notes + " of delivery " + pickupref);
        return business_notes;

    }

    public static long getEstimateTimeforPickup(String Pickup_Reference) {
        long Estimate_time;
        String queryString = "SELECT EstTime FROM pickupdetails where PickupRef='" + Pickup_Reference + "'";
        Estimate_time = Long.parseLong(getDataFromMySqlServer(queryString));
        logger.detail("Estimate Time=  " + Estimate_time + " of PickupRef=" + Pickup_Reference);
        return Estimate_time;

    }

    public static String getPickupToken(String Pickup_Reference) {
        String pickup_token;
        String queryString = "SELECT pickup_token FROM pickupdetails where PickupRef='" + Pickup_Reference + "'";
        pickup_token = getDataFromMySqlServer(queryString);
        logger.detail("Pickup_Token =  " + pickup_token + " of PickupRef=" + Pickup_Reference);
        return pickup_token;

    }

    public static long getDefaultPickupTime(String Service_Name, String SubDomain) {
        long default_Pickup_Time = 0;
        String queryString = "select sl.default_pickup_time\n" +
                "from bp_service_level sl\n" +
                "join bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id =sl.bp_config_version_id\n" +
                "join bp_store st on st.bp_store_id = fnm.bp_store_id\n" +
                "where fnm.bp_setting_fn_id = 3 and service_name='" + Service_Name + "' and subdomain_name like '%" + SubDomain + "%'";

        String default_Pickup_Time_Db = getDataFromMySqlMgmtServer(queryString);
        if (default_Pickup_Time_Db != null) {
            default_Pickup_Time = Long.parseLong(default_Pickup_Time_Db);
            logger.detail("Default Pickup Time=  " + default_Pickup_Time + " for Service: " + Service_Name);
        } else {
            logger.error("Default pickup time is not fetch for service " + Service_Name + " for SubDomain " + SubDomain);
        }


        return default_Pickup_Time;
    }

    public static long getDefaultDropoffTime(String Service_Name, String SubDomain) {
        long default_Dropoff_Time = 0;
        String queryString = "select sl.default_dropoff_time\n" +
                "from bp_service_level sl\n" +
                "join bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id =sl.bp_config_version_id\n" +
                "join bp_store st on st.bp_store_id = fnm.bp_store_id\n" +
                "where fnm.bp_setting_fn_id = 3 and service_name='" + Service_Name + "' and subdomain_name like '%" + SubDomain + "%'";

        String default_Dropoff_Time_Db = getDataFromMySqlMgmtServer(queryString);
        if (default_Dropoff_Time_Db != null) {
            default_Dropoff_Time = Long.parseLong(default_Dropoff_Time_Db);
            logger.detail("Default Dropoff Time=  " + default_Dropoff_Time + " for Service: " + Service_Name);
        } else {
            logger.error("Default Dropoff time is not fetch for service " + Service_Name + " for SubDomain " + SubDomain);
        }

        return default_Dropoff_Time;
    }

    public static List<HashMap<String, Object>> getListOfService(String Alias_Name) {
        List<HashMap<String, Object>> Service = new ArrayList<>();
        String queryString = "select ss.service_name\n" +
                "from bp_service_level ss\n" +
                "join bp_store_setting_fn_matrix c on c.bp_config_version_id =ss.bp_config_version_id\n" +
                "join bp_store d on d.bp_store_id = c.bp_store_id\n" +
                "where c.bp_setting_fn_id = 3 and store_alias like '" + Alias_Name + "%'";
        Service = getListDataFromMySqlMgmtServer(queryString);
        return Service;
    }

    public static List<HashMap<String, Object>> getRegionsList() {
        List<HashMap<String, Object>> regions = new ArrayList<>();
        String queryString = "select name from geofence where org_level = 2";
        regions = getListDataFromMySqlMgmtServer(queryString);
        return regions;
    }

    public static String getPartnerName(String Sub_Domain_Name) {
        String partnerName;
        String queryString = "select store_name from bp_store where subdomain_name ='"+ Sub_Domain_Name +"'";
        partnerName = getDataFromMySqlServer(queryString);
        logger.detail("Partner_Name =  " + partnerName + " of Subdomain=" + Sub_Domain_Name);
        return partnerName;

    }

    public String getSlotUsedCount(String date, String time, String address) {
        String slotUsedCount = "";
        String queryString = "SELECT slot_used_count\n" +
                "FROM bp_store_location sl\n" +
                "JOIN bp_store_slot ss ON ss.bp_store_location_id = sl.bp_store_location_id\n" +
                "JOIN time_slot ts ON ts.time_slot_id = ss.time_slot_id\n" +
                "WHERE address1 like '%" + address + "%'\n" +
                "and slot_date in ('" + date + "') and time_slot_from in('" + time + "')\n" +
                "ORDER BY slot_date, time_slot_from;\n";
        slotUsedCount = getDataFromMySqlServer(queryString);
        logger.detail("Slots used are " + slotUsedCount);
        return slotUsedCount;
    }

    public String getRecord(String phonenumber) {
        String record = "";
        String queryString = "select phone from driver where phone ="+phonenumber;
        record = getDataFromMySqlServer(queryString);
        logger.detail("Records present is" + record);
        return record;
    }

    public String getSlotUsedCountByStoreName(String date, String time, String storeName) {
        String slotUsedCount = "";
        String queryString = "SELECT slot_used_count\n" +
                "FROM bp_store_location sl\n" +
                "JOIN bp_store_slot ss ON ss.bp_store_location_id = sl.bp_store_location_id\n" +
                "JOIN time_slot ts ON ts.time_slot_id = ss.time_slot_id\n" +
                "WHERE store_location_name ='" + storeName + "'\n" +
                "and slot_date in ('" + date + "') and time_slot_from in('" + time + "')\n" +
                "ORDER BY slot_date, time_slot_from;";
        slotUsedCount = getDataFromMySqlServer(queryString);
        logger.detail("Slots used are " + slotUsedCount);
        return slotUsedCount;
    }

    public static List<String> getBodcCode(String Sub_Domain_Name) {
        List<String> bodcCodeOptions = new ArrayList<>();
        String queryString = "select JSON_EXTRACT(cvss.config_value ,'$.ControlValues') as DropdownValues from bungii_admin_qa_auto.bp_store bs\n" +
                "inner join bungii_admin_qa_auto.bp_store_setting_fn_matrix fn on fn.bp_store_id = bs.bp_store_id\n" +
                "inner join bungii_admin_qa_auto.bp_config_version_store_setting cvss on cvss.bp_config_version_id = fn.bp_config_version_id\n" +
                "where bs.subdomain_name ='" + Sub_Domain_Name + "' and fn.bp_setting_fn_id = 1 and config_field_display_label='BODC Code'";
        String response = getDataFromMySqlMgmtServer(queryString);
        response = response.replace("[", "").replace("]", "").replace("\"", "");
        String[] bodcCodeOptions1 = response.split(", ");
        bodcCodeOptions.add("Select");
        for (int i = 0; i < bodcCodeOptions1.length; i++) {
            bodcCodeOptions.add(bodcCodeOptions1[i]);
        }
        logger.detail("BodcCodeOptions=  " + bodcCodeOptions + " of Subdomain=" + Sub_Domain_Name);
        return bodcCodeOptions;
    }

    public static List<String> getAccessorialFeeType(String pickupref) {
        List<String> FeeType = new ArrayList<>();
        String queryString = "select ft.accessorial_fee_type from factpickup fp join facttrip ft on ft.pickupid = fp.id where fp.pickupref = '" + pickupref + "';";
        String response = getDataFromMySqlReportServer(queryString);
        String[] FeeType1 = response.split("\\|");
        for (int i = 0; i < FeeType1.length; i++) {
            FeeType.add(FeeType1[i].trim());
        }
        logger.detail("AccessorialFeeType =  " + FeeType + " of delivery " + pickupref);
        return FeeType;
    }
    public String getLoadUnloadTime(String pickUpID) {
        String loadUnloadTime = "";
        String queryString = "select LoadingUnloadingTime from pickupdetails where pickupref = '"+pickUpID+"'";
        loadUnloadTime = getDataFromMySqlServer(queryString);
        logger.detail("Load/Unload time required is " + loadUnloadTime);
        return loadUnloadTime;
    }
    public String getProjectedDriverTime(String pickUpID) {
        String projectedDriverTime = "";
        String queryString = "select EstTime from pickupdetails where pickupref = '"+pickUpID+"'";
        projectedDriverTime = getDataFromMySqlServer(queryString);
        logger.detail("Projected Driver Time is " + projectedDriverTime);
        return projectedDriverTime;
    }
    public static String getDriverStatus(String phoneNumber){
        String driverStatus;
        String entireQueryString = "select OnlineStatus from driver where Phone= " +phoneNumber;
        driverStatus = getDataFromMySqlServer(entireQueryString);
        return driverStatus;
    }

    public static String getPaymentTransactionType(String Pickup_Reference) {
        String transactionType;
        String queryString = "select PaymentTransactionType from bungii_admin_qa_auto.paymenttransaction where clientgroupref ='" + Pickup_Reference + "' ORDER BY ID DESC limit 1";
        transactionType = getDataFromMySqlServer(queryString);
        logger.detail("Payment transaction status is  " + transactionType + " for pickup reference"+Pickup_Reference);
        return transactionType;

    }

    public static String getStatusMessage(String Pickup_Reference) {
        String statusMessage;
        String queryString = "select StatusMessage from bungii_admin_qa_auto.paymenttransaction where clientgroupref ='" + Pickup_Reference + "' ORDER BY ID DESC limit 1";
        statusMessage = getDataFromMySqlServer(queryString);
        logger.detail("The status message is " + statusMessage + " for pickup reference"+Pickup_Reference);
        return statusMessage;

    }

    public static String getDriverPaid(String Pickup_Reference) {
        String driverPaid;
        String queryString = "select IsDriverPaid from bungii_admin_qa_auto.paymenttransaction where clientgroupref ='" + Pickup_Reference + "' ORDER BY ID DESC limit 1";
        driverPaid = getDataFromMySqlServer(queryString);
        logger.detail("Drive paid paid status is " + driverPaid + " for pickup reference"+Pickup_Reference);
        return driverPaid;

    }
    public static List<HashMap<String,Object>>  getAccessorialAmount(String Pickup_Reference) {
        List<HashMap<String,Object>> allcharges = new ArrayList<>();
        String queryString = "select Amount from bungii_admin_qa_auto.paymenttransaction where clientgroupref ='" + Pickup_Reference + "' ORDER BY ID DESC limit 8";
        allcharges = getListDataFromMySqlMgmtServer(queryString);
        return allcharges;

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
    public static String getStopSearchStatus(String pickUpID) {
        String statusFlag;
        String query ="select halt_driver_search from pickup_configurations where pickup_id="+pickUpID;
        statusFlag = getDataFromMySqlServer(query);
        logger.detail("The stop search status is "+statusFlag);
        return statusFlag;

    }

    public static String getDeliveryStatus(String Pickup_Reference) {
        String deliveryStatus;
        String queryStringForPickupTripStatus ="select PickupStatus from pickupdetails where PickupRef ='"+Pickup_Reference+"'";
        deliveryStatus = getDataFromMySqlServer(queryStringForPickupTripStatus);
        logger.detail("Delivery Status is "+deliveryStatus+ " for pickup reference "+ Pickup_Reference);
        return deliveryStatus;

    }

    //for checking added multiple customer sms recipients in database
    public static List<HashMap<String, Object>> getCustomerSMSRecipients(String PickupRef)
    {

        List<HashMap<String,Object>> CustSMSRecipients = new ArrayList<>();
        String Querystring= "select pa.sms_customer_phone_list from pickup_additional_info pa join pickupdetails pd on pa.pickup_id = pd.PickupID where pd.PickupRef ='"+PickupRef+"'";
        CustSMSRecipients= getDataFromMySqlServerMap(Querystring);
        return CustSMSRecipients;

    }

    public static String[] getLatAndLonPickupAndDropLocation(String reference){
        String pickupID = getPickupId(reference);
        String tripLocation[] = new String[4];
        tripLocation[0]=    getDataFromMySqlServer("select PickupLat from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[1]=    getDataFromMySqlServer("select PickupLong from pickupdropaddress  where PickupID= "+pickupID);
        tripLocation[2]=    getDataFromMySqlServer("select DropOffLat from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[3]=    getDataFromMySqlServer("select DropOffLong from pickupdropaddress  where PickupID= "+pickupID);
        logger.detail("For PickupID " + pickupID + " Pickup location is " + tripLocation[0]+","+tripLocation[1]);
        logger.detail("For PickupID " + pickupID + " DropOff location is " + tripLocation[2]+","+tripLocation[3]);
        return tripLocation;
    }

    public static String[] getPickupAndDropLocation(String reference){
        String pickupID = getPickupId(reference);
        String tripLocation[] = new String[2];
        tripLocation[0]=    getDataFromMySqlServer("select PickupAddress1 from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[1]=    getDataFromMySqlServer("select DropOffAddress1 from pickupdropaddress  where PickupID= "+pickupID);
        logger.detail("For PickupID " + pickupID + " Pickup location is " + tripLocation[0]);
        logger.detail("For PickupID " + pickupID + " DropOff location is " + tripLocation[1]);
        return tripLocation;
    }

    public static String getPartnerPortalEmailAddress(String PartnerPortal) {
        String emailAddress;
        String queryStringForEmailAddress ="select email_address from bungii_admin_qa_auto.bp_store st join bungii_admin_qa_auto.bp_store_portal_setting ps on ps.bp_store_id = st.bp_store_id where st.subdomain_name like '%"+PartnerPortal+"%'";
        emailAddress = getDataFromMySqlServer(queryStringForEmailAddress);
        logger.detail("Default email address for partner portal  "+PartnerPortal+" is "+ emailAddress);
        return emailAddress;

    }

    public static String getAllEmailAddress(String PartnerPortal) {
        String allEmailAddresses;
        String queryStringForEmailAddress ="select email_address  from bungii_admin_qa_auto.bp_store st join bungii_admin_qa_auto.bp_store_portal_setting ps where store_name= '"+PartnerPortal+"' limit  1";
        allEmailAddresses = getDataFromMySqlServer(queryStringForEmailAddress);
        logger.detail("All Email address for Partner portal   "+PartnerPortal+" are "+ allEmailAddresses);
        return allEmailAddresses;


    }

    public static String getDisbursementType(String type,String pickUpRef,String driverPhone) {
        String transactionType = "";
        String driverId;
        String queryString;

        switch (type) {
            case "Disbursement type":
                queryString = "Select Id from driver where phone= "+driverPhone;
                driverId = getDataFromMySqlMgmtServer(queryString);
                String queryString1 ="select disbursement_type from payment_trans_disburse_branch where payment_transaction_id in (select Id from paymenttransaction where clientgroupref in ('"+pickUpRef+"')) and driver_id="+driverId;
                transactionType = getDataFromMySqlMgmtServer(queryString1);
                logger.detail("The driver Id for "+driverPhone+" is "+driverId);
                logger.detail("The disbursement type for "+driverId+" is "+transactionType);
            break;

            case "Payment transaction type":
                queryString = "Select Id from driver where phone= "+driverPhone;
                driverId = getDataFromMySqlMgmtServer(queryString);
                String queryString2 ="select PaymentTransactionType  from bungii_admin_qa_auto.paymenttransaction where Driver= "+driverId+ " and PaymentTransactionType =10 limit 1";
                transactionType = getDataFromMySqlMgmtServer(queryString2);
                logger.detail("The payment transaction type for "+driverId+" is "+transactionType);
            break;
        }
        return transactionType;
    }
    public static String getDriverPaidStatus(String pickUpRef) {
        String driverStatus;
        String queryString1 ="select IsDriverPaid from paymenttransaction where clientgroupref ='"+pickUpRef+"' and TransactionType=3";
        driverStatus = getDataFromMySqlMgmtServer(queryString1);
        logger.detail("The status for driver payment is "+driverStatus);
        return driverStatus;
    }
    public static String getTripId(String pickupId) {
        String tripId;
        String queryString1 ="select TRID from triprequest where Pickupid="+pickupId;
        tripId = getDataFromMySqlServer(queryString1);
        logger.detail("The TRID for trip is "+tripId);
        return tripId;
    }
    public static String getTransactionStatus(String tripId) {
        String status;
        String queryString1 ="select IsTransactionSuccessful from trippaymentdetails where trid in ("+tripId+")";
        status = getDataFromMySqlServer(queryString1);
        logger.detail("The status for trip is "+status);
        return status;
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
    public static String getCustomerRefference(String phoneNumber) {
        String custRef = "";
        String queryString = "SELECT CustomerRef FROM customer WHERE Phone = " + phoneNumber;
        custRef = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "customer reference is " + custRef);
        return custRef;
    }
    public static String[] getFullPickUpAndDropOff(String reference){
        String pickupID = getPickupId(reference);
        String tripLocation[] = new String[2];
        String pickUpAddress2 = getDataFromMySqlServer("SELECT address2 FROM delivery_stop WHERE pickupId= "+pickupID+" and delivery_stop_type=1");
        String dropOffAddress2 = getDataFromMySqlServer("SELECT address2 FROM delivery_stop WHERE pickupId= "+pickupID+" and delivery_stop_type=2");
        if (!(pickUpAddress2==null)){
            tripLocation[0]= getDataFromMySqlServer("SELECT concat(ifnull(address1,''),', ',ifnull(address2,''),', ',ifnull(city,''),', ',ifnull(state,''),', ',ifnull(country,''),', ',ifnull(zip_postal_code,'')) FROM delivery_stop WHERE pickupId="+pickupID+" and delivery_stop_type=1");
        }
        else{
            tripLocation[0]= getDataFromMySqlServer("SELECT concat(ifnull(address1,''),', ',ifnull(city,''),', ',ifnull(state,''),', ',ifnull(country,''),', ',ifnull(zip_postal_code,'')) FROM delivery_stop WHERE pickupId="+pickupID+" and delivery_stop_type=1");
        }
        if(!(dropOffAddress2==null)){
            tripLocation[1]= getDataFromMySqlServer("SELECT concat(ifnull(address1,''),', ',ifnull(address2,''),', ',ifnull(city,''),', ',ifnull(state,''),', ',ifnull(country,''),', ',ifnull(zip_postal_code,'')) FROM delivery_stop WHERE pickupId="+pickupID+" and delivery_stop_type=2");
        }
        else{
            tripLocation[1]= getDataFromMySqlServer("SELECT concat(ifnull(address1,''),', ',ifnull(city,''),', ',ifnull(state,''),', ',ifnull(country,''),', ',ifnull(zip_postal_code,'')) FROM delivery_stop WHERE pickupId="+pickupID+" and delivery_stop_type=2");
        }
        logger.detail("For PickupID " + pickupID + " Pickup location is " + tripLocation[0]);
        logger.detail("For PickupID " + pickupID + " DropOff location is " + tripLocation[1]);
        return tripLocation;
    }

    public static boolean isDriverEligibleForTrip(String phoneNumber, String pickupRequest) {
        String queryString = "SELECT Id FROM driver WHERE phone = " + phoneNumber;
        String driverID = getDataFromMySqlServer(queryString);
        String queryString2 = "select DriverID from eligibletripdriver where pickupid IN (select PickupID from pickupdetails where pickupRef ='" + pickupRequest + "' )";
        boolean isDriverEligible =false;/* checkIfExpectedDataFromMySqlServer(queryString2,driverID);*/

        for(int i=0;i<8 && !isDriverEligible;i++){
            try {
                Thread.sleep(5000);
                isDriverEligible = checkIfExpectedDataFromMySqlServer(queryString2,driverID);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        return isDriverEligible;

    }

    public static String isDriverActive(String driverName) {
        String driverStatus = "";
        String queryString = "select IsDriverActive from driver where FirstName= '"+driverName+"'";
        driverStatus = getDataFromMySqlServer(queryString);
        logger.detail("Driver " + driverName + " has  " + driverStatus + " status");
        return driverStatus;
    }

    public static String getDriverPhoneNumber(String driverName) {
        String driverPhoneNumber = "";
        String queryString = "select Phone from driver where FirstName= '"+driverName+"'";
        driverPhoneNumber = getDataFromMySqlServer(queryString);
        logger.detail("Driver " + driverName + " Phone number is   " + driverPhoneNumber);
        return driverPhoneNumber;
    }


    public static String getAdminVerificationCode(String phoneNumber) {
        String verificationCode = "";
        String queryString = "select SmsVerificationCode from bouser where phone =" + phoneNumber;
        verificationCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS verification code for admin having phone number " + phoneNumber + "  is " + verificationCode);
        return verificationCode;
    }
    public static String getDriverVehicleInfo(String phoneNumber) {
        String driverVehicleInfo = "";
        String queryString = "select vehicle_info  from driver where phone=" + phoneNumber;
        driverVehicleInfo = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "Drivers vehicle information is " + driverVehicleInfo);
        return driverVehicleInfo;
    }

}

