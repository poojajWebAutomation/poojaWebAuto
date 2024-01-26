package com.bungii.android.utilityfunctions;

import com.bungii.common.manager.DbContextManager;
import com.bungii.common.utilities.LogUtility;

import java.util.HashMap;
import java.util.List;

public class DbUtility extends DbContextManager {
    private static LogUtility logger = new LogUtility(DbUtility.class);

    /**
     * Connect to MS sql server and get verification SMS verification code
     *
     * @param phoneNumber
     * @return
     */
    public static String getVerificationCode(String phoneNumber) {
        String smsCode = "";
/*        String queryString = "SELECT SmsVerificationCode FROM Driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMsSqlServer(queryString);*/
        String queryString = "SELECT SmsVerificationCode FROM customer WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }

    /**
     * Check if phoneNumber is unique
     *
     * @param phoneNumber Phone Number that us to be check
     * @return
     */
    public static boolean isPhoneNumberUnique(String phoneNumber) {
        String id = "";
/*        String queryString = "SELECT Id FROM Driver WHERE Phone = " + phoneNumber;
        id = getDataFromMsSqlServer(queryString);*/
        String queryString = "SELECT Id FROM customer WHERE Phone = " + phoneNumber;
        id = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Phone Number " + phoneNumber + "is unique " + id.equals(""));
        return id.equals("");
    }

    public static String getCustomerRefference(String phoneNumber) {
        String custRef = "";
        String queryString = "SELECT CustomerRef  FROM customer WHERE Phone = " + phoneNumber;
        custRef = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "customer reference is " + custRef);
        return custRef;
    }

    public static String getEstimateTime(String custRef) {
        String estTime = "";
        String queryString = "SELECT EstTime FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        estTime = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " Extimate time is " + estTime);
        return estTime;
    }
    public static String getPickupID(String custRef) {
        String PickupID = "";
        String queryString = "SELECT PickupID FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        PickupID = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " Extimate time is " + PickupID);
        return PickupID;
    }
    public static String getPickupReff(String custRef) {
        String PickupID = "";
        String queryString = "SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        PickupID = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " PickupRef is " + PickupID);
        return PickupID;
    }
    public static String getActualTime(String pickupID) {
        String queryString = "select ActualTime from triprequest  WHERE PickupID = '" + pickupID + "' order by pickupid desc limit 1";

        String actualTime = getDataFromMySqlServer(queryString);

        logger.detail("pickupID" + pickupID + "  time is " + actualTime);
        return actualTime;
    }
    public static String getVerificationCodeDriver(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT SmsVerificationCode FROM driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }
    public static String getTELETfromDb(String custRef) {
        String telet = "";
        String queryString = "SELECT TELET FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        telet = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " TELET time is " + telet);
        return telet;
    }
    public static String[] getLoadingTimeStamp(String customerPhone){
        String[] loadingTme= new String[2];
        String pickupID = "",custRef=getCustomerRefference(customerPhone);
        String queryString = "SELECT PickupID FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        pickupID = getDataFromMySqlServer(queryString);
        loadingTme[0]=getDataFromMySqlServer("select StatusTimestamp from tripevents where pickupid ="+pickupID+" and TripStatus=25");
        loadingTme[1]=getDataFromMySqlServer("select StatusTimestamp from tripevents where pickupid ="+pickupID+" and TripStatus=26");
        return loadingTme;
    }
    public static String getDriverReference(String phoneNumber) {
        String driverRef = "";
        String queryString = "SELECT DriverRef  FROM driver WHERE Phone = " + phoneNumber;
        driverRef = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "DriverRef is " + driverRef);
        return driverRef;
    }
    public static String[] getDriverLocation(String phoneNumber) {
        String driverId = "";String driverLocation[] = new String[2];
        String queryString = "SELECT Id FROM driver WHERE Phone = " + phoneNumber;
        driverId = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "driverId is " + driverId);

        driverLocation[0]=    getDataFromMySqlServer("select Latitude from driverlocation where driverid = "+driverId);
        driverLocation[1]=    getDataFromMySqlServer("select Longitude from driverlocation where driverid = "+driverId);
        logger.detail("For driverId " + driverId + " driver location is " + driverLocation[0]+","+driverLocation[1]);

        return driverLocation;
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

    public static String getStatusTimeStampForStack(String customerPhone){
        String custRef=getCustomerRefference(customerPhone);
        String pickupRef=getDataFromMySqlServer("SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1");
        String statusTimeStamp=  getDataFromMySqlServer("select StatusTimestamp from tripevents te join pickupdetails pd on te.pickupid = pd.pickupid where pickupRef = '"+pickupRef+"' and te.TripStatus = 40");
        logger.detail("For pickupRef " + pickupRef + " status 40 TimeStamp is " + statusTimeStamp);
        return statusTimeStamp;
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

    public static String getDriversReference(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT Reference FROM driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Reference code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }
    public static String getDriverActiveFlag(String phoneNumber){
        String UserRef = getDriversReference(phoneNumber);
        String queryString2 = "select Active from device where UserRef ='"+UserRef+ "' order by devid desc limit 1";
        String activeFlag = getDataFromMySqlServer(queryString2);
        return activeFlag;
    }
    public String getCustomerDeviceToken(String phoneNumber){
        String queryString2 = "select token from device where UserRef IN (select CustomerRef from customer where phone='"+phoneNumber+"') order by DevID desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public String getDriverDeviceToken(String phoneNumber){
        String queryString2 = "select token from device where UserRef IN (select DriverRef from driver  where phone='"+phoneNumber+"') order by DevID desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public String getCustomersMostRecentBungii(String phoneNumber){

        String queryString2 = "SELECT PickupRef FROM pickupdetails  WHERE customerRef IN(SELECT CustomerRef FROM customer WHERE phone='"+phoneNumber+"') order by pickupid desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public String getFinalBungiiCost(String pickupref){

        String queryString2 = "SELECT pickup_revenue FROM pickupdetails WHERE pickupref='"+pickupref+"'";
        String cost = getDataFromMySqlServer(queryString2);
        return cost;
    }
    public String getCustomersMostRecentBungiiPickupId(String phoneNumber){
        String queryString2 = "SELECT Pickupid FROM pickupdetails WHERE customerRef IN (SELECT CustomerRef FROM customer WHERE phone='"+phoneNumber+"') order by pickupid desc limit 1";
        String Pickupid = getDataFromMySqlServer(queryString2);
        return Pickupid;
    }
    public String getPickupNoteOfLastPickupOf(String phoneNumber){
        String getLastPickupId = getCustomersMostRecentBungiiPickupId(phoneNumber);
        String queryString2 = "select cust_sch_conversion_remark_comments from pickup_detail_remarks where pickup_id in ('"+getLastPickupId+"')";
        String getPickupNote = getDataFromMySqlServer(queryString2);
        return getPickupNote;
    }

    public static String getDriverPushNotificationContent(String phoneNumber, String pickupRef){
        String queryString2= "select Payload from pushnotification where userid in (select Id from driver where phone = '"+phoneNumber+"') and Payload Like '%"+pickupRef+"%' and UserType ='AUD'";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }
    public static String getCustomerPushNotificationContent(String phoneNumber, String pickupRef){
        String queryString2= "select Payload from pushnotification where userid in (select Id from customer where phone = '"+phoneNumber+"') and Payload Like '%"+pickupRef+"%' and UserType ='AUC'";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }
    public void getLastFivePushNotification(){
        String queryString2= "select payload from pushnotification order by PNID DESC limit 10;";
        List<HashMap<String,Object>> pushnotification = getDataFromMySqlServerMap(queryString2);
        logger.detail("*** LAST 10 Pushnotifications from Pushnotifications table ***");
        for (int i = 0; i < pushnotification.size(); i++) {
            logger.detail(pushnotification.get(i).values().toString());
        }
    }

    public static String getPickupRef(String customerPhone){
        String custRef=getCustomerRefference(customerPhone);
        String pickupRef=getDataFromMySqlServer("SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1");
        return pickupRef;
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
    public static String getPickupIdWithRef(String pickupRef) {
        String pickupid = "";
        String queryString = "SELECT Pickupid FROM pickupdetails WHERE pickupref ='" + pickupRef + "'";
        pickupid = getDataFromMySqlServer(queryString);
        logger.detail("Pickupid  " + pickupid + " of pickupref " + pickupRef);
        return pickupid;
    }
    public static String[] getPickupAndDropLocationWithID(String pickupID){
        String tripLocation[] = new String[4];
        tripLocation[0]=    getDataFromMySqlServer("select PickupLat from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[1]=    getDataFromMySqlServer("select PickupLong from pickupdropaddress  where PickupID= "+pickupID);
        tripLocation[2]=    getDataFromMySqlServer("select DropOffLat from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[3]=    getDataFromMySqlServer("select DropOffLong from pickupdropaddress  where PickupID= "+pickupID);
        logger.detail("For PickupID " + pickupID + " Pickup location is " + tripLocation[0]+","+tripLocation[1]);
        logger.detail("For PickupID " + pickupID + " DropOff location is " + tripLocation[2]+","+tripLocation[3]);
        return tripLocation;
    }
    public static String getTimeAtPickUpAndDrop(String type, String subdomainName) {
        String time = "";
        String queryString = "select "+type+"\n" +
                "from bungii_admin_qa_auto.bp_service_level sl\n" +
                "join bungii_admin_qa_auto.bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bungii_admin_qa_auto.bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "where bungii_admin_qa_auto.fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like '"+subdomainName+"%' and service_level_number=3\n" +
                "order by subdomain_name, service_level_number, service_name;";
        time = getDataFromMySqlServer(queryString);
        logger.detail("Time at pickup/dropoff is "+ time);
        return time;
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
        logger.detail("Driver Arrival Time is " + drverArrivalTime);
        return drverArrivalTime;
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

    public static String getDriverVehicleInfo(String phoneNumber) {
        String driverVehicleInfo = "";
        String queryString = "select vehicle_info  from driver where phone=" + phoneNumber;
        driverVehicleInfo = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "Drivers vehicle information is " + driverVehicleInfo);
        return driverVehicleInfo;
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

}