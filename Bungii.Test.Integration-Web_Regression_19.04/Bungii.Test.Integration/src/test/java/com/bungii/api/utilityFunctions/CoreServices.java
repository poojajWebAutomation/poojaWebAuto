package com.bungii.api.utilityFunctions;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.UrlBuilder;
import com.bungii.ios.stepdefinitions.customer.*;
import com.bungii.api.utilityFunctions.DbUtility;
import com.google.gson.Gson;
import cucumber.api.junit.Cucumber;
import cucumber.deps.com.thoughtworks.xstream.mapper.Mapper;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.runner.Request;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.utilities.ApiHelper.gson;
import static io.restassured.RestAssured.given;

public class CoreServices extends DriverBase {
    private static LogUtility logger = new LogUtility(CoreServices.class);
    private static String VALIDATE_PICKUP_REQUEST = "/api/pickup/validatepickuprequest";
    private static String PICKUP_REQUEST = "/api/pickup/request";
    private static String RECALCULATE_ESTIMAT = "/api/pickup/recalculateestimate";
    private static String CUSTOMER_CONFIRMATION = "/api/pickup/customerconfirmation";
    private static String CUSTOMER_VIEW = "/api/pickup/customerview";
    private static String DRIVER_VIEW = "/api/pickup/driverview";
    private static String UPDATE_LOCATION = "/api/driver/updatelocation";
    private static String UPDATE_STATUS = "/api/driver/updatestatus";
    private static String PICKUP_DETAILS = "/api/driver/pickupdetails";
    private static String UPDATE_PICKUP_STATUS = "/api/pickup/updatestatus";
    private static String RATE_AND_TIP = "/api/customer/rateandtipdriver";
    private static String AVAILABLE_PICKUPLIST = "/api/driver/availablepickuplist";
    private static String CUSTOMER_SCHEDULEDLIST = "/api/customer/scheduledpickuplist";
    private static String DRIVER_SCHEDULEDLIST = "/api/driver/scheduledpickuplist";
    private static String CUSTOMER_SCHEDULEDPICKUPLIST = "/api/customer/scheduledpickupdetails";
    private static String CUSTOMER_CANCELPICKUPLIST = "/api/customer/cancelpickup";
    private static String GET_PROMOCODE = "/api/customer/getpromocodes";
    private static String DRIVER_CANCELPICKUPLIST = "/api/driver/cancelpickup";
    private static String STACKED_PICKUP_CONFIRMATION = "/api/driver/stackedpickupconfirmation";
    private static String PARTNER_PICKUPESTIMATE = "/api/partner/pickupestimate";
    private static String PARTNER_SERVICELEVEL = "/api/partner/servicelevels";
    private static String PARTNER_PICKUPDETAILS = "/api/partner/pickupdetails?pickuprequestid=";
    private static String PARTNER_DELIVERYINFOMATION = "/api/partner/deliveryinformation";
    private static String PARTNER_GRAPHQL = "/graphql";
    private static String PARTNER_CONFIRM_PICKUP = "/api/partner/confirmpickup";
    private static String DRIVER_REJECTION_REASON = "/api/driver/getRemarks?remarkby=4";
    private static String DRIVER_SAVE_REJECTION_REASON = "/api/driver/pickupremark";

    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();

    public Response validatePickupRequest(String authToken, String geoFence) {
        String RequestText ="API REQUEST : Validate Pickup Request : " + authToken +" : "+ geoFence;
        JSONObject jsonObj = new JSONObject();
        JSONObject dropOffCordinate = new JSONObject();
        JSONObject pickupCordinates = new JSONObject();

        if (geoFence.equalsIgnoreCase("goa")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("goa.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("goa.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("goa.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("goa.pickup.longitude")));
        } else if (geoFence.equalsIgnoreCase("kansas")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("kansas.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("kansas.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("kansas.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("kansas.pickup.longitude")));
        }
        else if (geoFence.equalsIgnoreCase("boston")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("boston.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("boston.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("boston.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("boston.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("atlanta")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("atlanta.far")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.far.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.far.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.far.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.far.pickup.longitude")));
        }
        else if (geoFence.equalsIgnoreCase("baltimore")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("baltimore.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("baltimore.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("baltimore.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("baltimore.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("miami")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("miami.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("miami.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("miami.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("miami.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("nashville")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("nashville.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("nashville.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("nashville.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("nashville.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("denver")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("denver.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("denver.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("denver.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("denver.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("washingtondc")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("newjersey")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("newjersey.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("newjersey.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("newjersey.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("newjersey.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("phoenix")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("phoenix.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("phoenix.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("phoenix.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("phoenix.pickup.longitude")));
        }


        jsonObj.put("DropoffLocation", dropOffCordinate);
        jsonObj.put("PickupLocation", pickupCordinates);


        Header header = new Header("AuthorizationToken", authToken);


        String apiURL = UrlBuilder.createApiUrl("core", VALIDATE_PICKUP_REQUEST);
        Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
        //logger.detail(response.then().log().body());
        ApiHelper.genericResponseValidation(response,RequestText);
        return response;

    }
    public Response validatePickupRequestOfPartnerFirm(String authToken, String geoFence) {
        String RequestText ="API REQUEST : Validate Pickup Request Of Partner Firm : " + authToken +" : "+ geoFence;

        JSONObject jsonObj = new JSONObject();
        JSONObject dropOffCordinate = new JSONObject();
        JSONObject pickupCordinates = new JSONObject();

       if (geoFence.equalsIgnoreCase("washingtondc")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.pickup.latitude.PartnerFirm")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.pickup.longitude.PartnerFirm")));
        }
        if (geoFence.equalsIgnoreCase("goa")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("goa.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("goa.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("goa.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("goa.pickup.longitude")));
        }
        jsonObj.put("DropoffLocation", dropOffCordinate);
        jsonObj.put("PickupLocation", pickupCordinates);


        Header header = new Header("AuthorizationToken", authToken);


        String apiURL = UrlBuilder.createApiUrl("core", VALIDATE_PICKUP_REQUEST);
        Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
       // logger.detail(response.then().log().body());
        ApiHelper.genericResponseValidation(response,RequestText);
        return response;

    }
    public Response availablePickupList(String authToken) {
        String RequestText ="API REQUEST : Available Pickup List : Auth token " + authToken;
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("core", AVAILABLE_PICKUPLIST);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForDriver(apiURL, header);
        ApiHelper.genericResponseValidation(response,RequestText);
        return response;
    }

    public boolean isPickupIsListedInAvailableTrip(String authToken, String expectedPickupRequest) {
        boolean isPickupInAvailableTrip = false;
        JsonPath jsonPathEvaluator = availablePickupList(authToken).jsonPath();
        ArrayList availableArray = jsonPathEvaluator.get("AvailablePickups");
        if (availableArray != null) {
            for (int i = 0; i < availableArray.size(); i++) {
                HashMap pickupDetails = (HashMap) availableArray.get(i);
                String pickupRequest = (String) pickupDetails.get("PickupRef");
                if (pickupRequest.equalsIgnoreCase(expectedPickupRequest)) {
                    isPickupInAvailableTrip = true;
                    logger.detail("Driver is eligible for pickup : "+ pickupRequest);
                    break;
                }
                else
                {
                    logger.detail("Pickup requests for which driver is eligible are : "+ pickupRequest);
                }
            }
        }
        else
            logger.detail("No Pickup requests found for which driver is eligible");

        return isPickupInAvailableTrip;
    }

    // wait for 5 mins for pickup to be displayed in available trips
    public void waitForAvailableTrips(String driverDetail, String authToken, String expectedPickupRequest) {
        logger.detail("Waiting for Available Trips : Driver " + driverDetail + " : Pickup Request : "+ expectedPickupRequest);
        try {

            boolean foundPickup = false;
            for (int i = 0; i < 10; i++) {
                foundPickup = isPickupIsListedInAvailableTrip(authToken, expectedPickupRequest);
                if (foundPickup) {
                    break;
                } else {
                    Thread.sleep(8000);
                    logger.detail("Waiting for Driver "+driverDetail+" to be eligible for Pickup Request : "+ expectedPickupRequest);
                }

            }
            if (!foundPickup) {

                List<HashMap<String,Object>> driverEligible =dbUtility.getAllDriversEligible(expectedPickupRequest);
                String drivers = " Phone numbers of drivers who are eligible for pickup : "+ expectedPickupRequest+ " : ";
                int i =0 ;

                while (i<driverEligible.size())
                {
                    drivers = drivers + " "+driverEligible.get(i).get("Phone");
                    i++;
                }
                logger.detail("Scheduled trip should be displayed in available trip", "Scheduled trip is not displayed in available trip since Driver "+driverDetail+" is not eligible for pickup : "+expectedPickupRequest +" | "+ drivers, false);
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", false);
        }
    }

    public Response pickupRequest(String authToken, int numberOfDriver, String geoFence) {
        String RequestText ="API REQUEST : Pickup Request for Authtoken " + authToken+ " : Number of Drivers : "+ numberOfDriver + " : Geofence : "+ geoFence;

        JSONObject jsonObj = new JSONObject();
        JSONObject dropOffAddress = new JSONObject();
        JSONObject dropOffCordinate = new JSONObject();
        JSONObject pickUpAddress = new JSONObject();
        JSONObject pickUpCordinate = new JSONObject();
        if (geoFence.equalsIgnoreCase("nashville")||geoFence.equalsIgnoreCase("newjersey")||geoFence.equalsIgnoreCase("goa")||geoFence.equalsIgnoreCase("kansas")||geoFence.equalsIgnoreCase("boston")||geoFence.contains("atlanta")||geoFence.equalsIgnoreCase("baltimore") ||geoFence.equalsIgnoreCase("miami")||geoFence.equalsIgnoreCase("denver")||geoFence.equalsIgnoreCase("washingtondc")||geoFence.equalsIgnoreCase("phoenix")) {
            dropOffAddress.put("Address1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address1"));
            dropOffAddress.put("Address2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address2"));
            dropOffAddress.put("City", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.city"));
            dropOffAddress.put("Country", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.country"));
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.longitude")));
            dropOffAddress.put("Location", dropOffCordinate);
            dropOffAddress.put("State", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.state"));
            dropOffAddress.put("ZipPostalCode", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.zipcode"));

            pickUpAddress.put("Address1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address1"));
            pickUpAddress.put("Address2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address2"));
            pickUpAddress.put("City", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.city"));
            pickUpAddress.put("Country", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.country"));
            pickUpCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.latitude")));
            pickUpCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.longitude")));
            pickUpAddress.put("Location", pickUpCordinate);
            pickUpAddress.put("State", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.state"));
            pickUpAddress.put("ZipPostalCode", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.zipcode"));

            jsonObj.put("DropOffAddress", dropOffAddress);
            jsonObj.put("PickupAddress", pickUpAddress);
            jsonObj.put("NoOfDrivers", numberOfDriver);

            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address1") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address2"));
            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.city") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.state"));
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address1") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address2"));
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.city") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.state"));
            cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER", numberOfDriver == 1 ? "SOLO" : "DUO");
        } else {
            logger.detail("Specify valid geofence");
        }
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = UrlBuilder.createApiUrl("core", PICKUP_REQUEST);
        Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
        ApiHelper.genericResponseValidation(response, RequestText);
        return response;


    }
    public Response pickupRequestWithZeroDistance(String authToken, int numberOfDriver, String geoFence) {
        String RequestText ="API REQUEST : Pickpup Request " + authToken+ " : Number of Drivers : "+ numberOfDriver + " : Geofence : "+ geoFence;

        JSONObject jsonObj = new JSONObject();
        JSONObject dropOffAddress = new JSONObject();
        JSONObject dropOffCordinate = new JSONObject();
        JSONObject pickUpAddress = new JSONObject();
        JSONObject pickUpCordinate = new JSONObject();
        if (geoFence.equalsIgnoreCase("nashville")||geoFence.equalsIgnoreCase("goa")||geoFence.equalsIgnoreCase("kansas")||geoFence.equalsIgnoreCase("boston")||geoFence.contains("atlanta")||geoFence.equalsIgnoreCase("baltimore") ||geoFence.equalsIgnoreCase("miami")||geoFence.equalsIgnoreCase("denver")||geoFence.equalsIgnoreCase("washingtondc")) {
            dropOffAddress.put("Address1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address1"));
            dropOffAddress.put("Address2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address2"));
            dropOffAddress.put("City", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.city"));
            dropOffAddress.put("Country", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.country"));
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.longitude")));
            dropOffAddress.put("Location", dropOffCordinate);
            dropOffAddress.put("State", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.state"));
            dropOffAddress.put("ZipPostalCode", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.zipcode"));

            pickUpAddress.put("Address1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address1"));
            pickUpAddress.put("Address2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address2"));
            pickUpAddress.put("City", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.city"));
            pickUpAddress.put("Country", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.country"));
            pickUpCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.latitude")));
            pickUpCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.longitude")));
            pickUpAddress.put("Location", pickUpCordinate);
            pickUpAddress.put("State", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.state"));
            pickUpAddress.put("ZipPostalCode", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.zipcode"));

            jsonObj.put("DropOffAddress", dropOffAddress);
            jsonObj.put("PickupAddress", pickUpAddress);
            jsonObj.put("NoOfDrivers", numberOfDriver);

            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address1") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address2"));
            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.city") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.state"));
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address1") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address2"));
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.city") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.state"));
            cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER", numberOfDriver == 1 ? "SOLO" : "DUO");
        } else {
            logger.detail("Specify valid geofence");
        }
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = UrlBuilder.createApiUrl("core", PICKUP_REQUEST);
        Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
        ApiHelper.genericResponseValidation(response, RequestText);
        return response;


    }
    public Response pickupRequestPartnerFirm(String authToken, int numberOfDriver, String geoFence) {
        String RequestText ="API REQUEST : Pickpup Request of Partner Firm " + authToken+ " : Number of Drivers : "+ numberOfDriver + " : Geofence : "+ geoFence;

        JSONObject jsonObj = new JSONObject();
        JSONObject dropOffAddress = new JSONObject();
        JSONObject dropOffCordinate = new JSONObject();
        JSONObject pickUpAddress = new JSONObject();
        JSONObject pickUpCordinate = new JSONObject();
        if (geoFence.equalsIgnoreCase("nashville")||geoFence.equalsIgnoreCase("goa")||geoFence.equalsIgnoreCase("kansas")||geoFence.equalsIgnoreCase("boston")||geoFence.equalsIgnoreCase("atlanta")||geoFence.equalsIgnoreCase("baltimore") ||geoFence.equalsIgnoreCase("miami")||geoFence.equalsIgnoreCase("denver")||geoFence.equalsIgnoreCase("washingtondc")) {
            dropOffAddress.put("Address1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address1"));
            dropOffAddress.put("Address2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address2"));
            dropOffAddress.put("City", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.city"));
            dropOffAddress.put("Country", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.country"));
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.longitude")));
            dropOffAddress.put("Location", dropOffCordinate);
            dropOffAddress.put("State", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.state"));
            dropOffAddress.put("ZipPostalCode", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.zipcode"));

            pickUpAddress.put("Address1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address1.PartnerFirm"));
            pickUpAddress.put("Address2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address2.PartnerFirm"));
            pickUpAddress.put("City", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.city.PartnerFirm"));
            pickUpAddress.put("Country", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.country.PartnerFirm"));
            pickUpCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.latitude.PartnerFirm")));
            pickUpCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.longitude.PartnerFirm")));
            pickUpAddress.put("Location", pickUpCordinate);
            pickUpAddress.put("State", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.state.PartnerFirm"));
            pickUpAddress.put("ZipPostalCode", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.zipcode.PartnerFirm"));

            jsonObj.put("DropOffAddress", dropOffAddress);
            jsonObj.put("PickupAddress", pickUpAddress);
            jsonObj.put("NoOfDrivers", numberOfDriver);

            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address1") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address2"));
            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.city") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.state"));
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address1") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address2"));
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.city") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.state"));
            cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER", numberOfDriver == 1 ? "SOLO" : "DUO");
        } else {
            logger.detail("Specify valid geofence");
        }
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = UrlBuilder.createApiUrl("core", PICKUP_REQUEST);
        Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
        ApiHelper.genericResponseValidation(response, RequestText);
        return response;


    }


    public String getPickupRequest(String authToken, int numberOfDriver, String geoFence) {
        logger.detail("API REQUEST : Get Pickpup Request " + authToken+ " : Number of Drivers : "+ numberOfDriver + " : Geofence : "+ geoFence);
        Response response = pickupRequest(authToken, numberOfDriver, geoFence);
        JsonPath jsonPathEvaluator = response.jsonPath();
        saveAppliedPromoCode(response);
        return jsonPathEvaluator.get("PickupRequestID");

    }
    public String getPickupRequestWithZeroDistance(String authToken, int numberOfDriver, String geoFence) {
        logger.detail("API REQUEST : Get Pickpup Request " + authToken+ " : Number of Drivers : "+ numberOfDriver + " : Geofence : "+ geoFence);
        Response response = pickupRequestWithZeroDistance(authToken, numberOfDriver, geoFence);
        JsonPath jsonPathEvaluator = response.jsonPath();
        saveAppliedPromoCode(response);
        return jsonPathEvaluator.get("PickupRequestID");

    }
    public String getPickupRequestOfPartnerFirm(String authToken, int numberOfDriver, String geoFence) {
        logger.detail("API REQUEST : Get Pickpup Request Of Partner Firm" + authToken+ " : Number of Drivers : "+ numberOfDriver + " : Geofence : "+ geoFence);
        Response response = pickupRequestPartnerFirm(authToken, numberOfDriver, geoFence);
        JsonPath jsonPathEvaluator = response.jsonPath();
        saveAppliedPromoCode(response);
        return jsonPathEvaluator.get("PickupRequestID");

    }
    public String  saveAppliedPromoCode(Response response){
        logger.detail("Saving Applied Promocode");
        String promoCode="",walletRef="";
        JsonPath jsonPathEvaluator =response.jsonPath();
        ArrayList availableArray = jsonPathEvaluator.get("Estimate.DiscountCost");
        //interation to go through all promo code, will be useful in future
        if (availableArray != null) {
            for (int i = 0; i < availableArray.size(); i++) {
                HashMap pickupDetails = (HashMap) availableArray.get(i);
                promoCode = (String) pickupDetails.get("Code");
                walletRef=(String) pickupDetails.get("WalletRef");
                break;
            }
            cucumberContextManager.setScenarioContext("ADDED_PROMOCODE", promoCode);
            cucumberContextManager.setScenarioContext("ADDED_PROMOCODE_WALLETREF", walletRef);
        }

        return walletRef;
    }
    public void recalculateEstimate(String pickupRequestID, String walletReferance, String authToken) {
        try {
            String RequestText ="Recalculating Estimate of pickup request : " + pickupRequestID+" | Wallet Reference : "+ walletReferance+" | Auth Token : "+ authToken;

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupRequestID", pickupRequestID);
            jsonObj.put("WalletRef", walletReferance);
            jsonObj.put("EstLoadUnloadTimeInMilliseconds", 900000);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", RECALCULATE_ESTIMAT);
            Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
            JsonPath jsonPathEvaluator = response.jsonPath();

            ApiHelper.genericResponseValidation(response,RequestText);
            cucumberContextManager.setScenarioContext("BUNGII_TIME", "NOW");
            String bungiiDistance="";
           /* if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS"))
                bungiiDistance = new DecimalFormat("#.0").format(jsonPathEvaluator.get("Estimate.DistancePickupToDropOff")) + " miles";
            else*/
                bungiiDistance = jsonPathEvaluator.get("Estimate.DistancePickupToDropOff") + " miles";
                String truncValue = new DecimalFormat("#.00").format(jsonPathEvaluator.get("Estimate.Cost"));

            cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", bungiiDistance);
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", "~$" +truncValue);
            cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", "15 mins");
            int estimateTripDuration=jsonPathEvaluator.get("Estimate.TimePickupToDropOff");
            logger.detail("estimateTripDuration "+ estimateTripDuration);
            Double estimateDuration=Double.valueOf(estimateTripDuration)/60000;
            Long estimateDurationWithLoadUnload=Math.round(estimateDuration)+15;
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE_TIME", "~"+Math.round(estimateDuration)+"  mins");
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE_TIME_LOAD_TIME", "~"+estimateDurationWithLoadUnload+"  mins");
        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }
    public void recalculateEstimate(String pickupRequestID, String walletReferance, String authToken,String customerLabel) {
        try {
            String RequestText ="Recalculating Estimate of pickup request : " + pickupRequestID+" | Wallet Reference : "+ walletReferance+" | Auth Token : "+ authToken;

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupRequestID", pickupRequestID);
            jsonObj.put("WalletRef", walletReferance);
            jsonObj.put("EstLoadUnloadTimeInMilliseconds", 900000);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", RECALCULATE_ESTIMAT);
            Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
            JsonPath jsonPathEvaluator = response.jsonPath();

            ApiHelper.genericResponseValidation(response, RequestText);
            cucumberContextManager.setScenarioContext("BUNGII_TIME"+customerLabel, "NOW");
            String bungiiDistance="";
            //now two decimal point are shown
/*            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS"))
                bungiiDistance = new DecimalFormat("#.0").format(jsonPathEvaluator.get("Estimate.DistancePickupToDropOff")) + " miles";
            else*/
            bungiiDistance = jsonPathEvaluator.get("Estimate.DistancePickupToDropOff") + " miles";
            String truncValue = new DecimalFormat("#.00").format(jsonPathEvaluator.get("Estimate.Cost"));

            cucumberContextManager.setScenarioContext("BUNGII_DISTANCE"+customerLabel, bungiiDistance);
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE"+customerLabel, "~$" +truncValue);
            cucumberContextManager.setScenarioContext("BUNGII_LOADTIME"+customerLabel, "15 mins");
        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }

/*
    public void customerConfirmation(String pickRequestID, String paymentMethodID, String authToken) {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("WalletRef", "");
            jsonObj.put("EstLoadUnloadTimeInMilliseconds", 900000);
            jsonObj.put("PickupRequestID", pickRequestID);
            jsonObj.put("Description", "");
            jsonObj.put("PaymentMethodID", paymentMethodID);
            jsonObj.put("IsScheduledPickup", false);

            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_CONFIRMATION);
            Response response = ApiHelper.uploadImage(apiURL, jsonObj, header);
            ApiHelper.genericResponseValidation(response);

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }*/


    public Response customerConfirmation(String pickRequestID, String paymentMethodID, String authToken, String scheduledDateTime) {
        String RequestText ="API REQUEST : Customer Confirmation of pickup request "+ pickRequestID+" | Payment Method ID: "+ paymentMethodID+" | Auth Token : "+ authToken +" | Scheduled Date Time : "+ scheduledDateTime;

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("WalletRef", (String)cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"));
        jsonObj.put("EstLoadUnloadTimeInMilliseconds", 900000);
        jsonObj.put("PickupRequestID", pickRequestID);
        jsonObj.put("PaymentMethodID", paymentMethodID);
        jsonObj.put("Description", "");
        String str="";
        if (!scheduledDateTime.equals("")) {
            jsonObj.put("IsScheduledPickup", true);
            jsonObj.put("ScheduledDateTime", scheduledDateTime);
            str= "TestNote for Scheduled "+pickRequestID;
        } else {
            jsonObj.put("IsScheduledPickup", false);
            str= "TestNote for OnDemand "+pickRequestID;

        }
        jsonObj.put("PickupNote",str);
        cucumberContextManager.setScenarioContext("PICKUP_NOTE",str);
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;
        try{
        Thread.sleep(10000);} catch(Exception ex){}

        apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_CONFIRMATION);
        Response response = ApiHelper.uploadImage(apiURL, jsonObj, header);
        JsonPath jsonPathEvaluator = response.jsonPath();
        HashMap error = jsonPathEvaluator.get("Error");
        if (error != null && error.size()!=0) {
             String errorCode = jsonPathEvaluator.get("Error.Code").toString();
             if (errorCode=="20027")
             {
                 scheduledDateTime = getNextTime(scheduledDateTime);
                 logger.detail("Oops! Since there has been a delay in requesting this trip, the scheduled time selected is no longer valid. Requesting with 15 minutes later time.");
                 response = customerConfirmation(pickRequestID, paymentMethodID, authToken, scheduledDateTime);
             }
            else if (errorCode=="3004")
            {
                try{ Thread.sleep(30000);}catch (InterruptedException e){}
                scheduledDateTime = getNextTime(scheduledDateTime);
                logger.detail("There was a problem processing your credit card; please double check your payment information and try again. | Retrying in 30 Seconds");
                response = customerConfirmation(pickRequestID, paymentMethodID, authToken, scheduledDateTime);
            }
            else
                 ApiHelper.genericResponseValidation(response, RequestText);
        }
        else
        ApiHelper.genericResponseValidation(response, RequestText);
        return response;
    }

    public Response customerConfirmationWithoutNotes(String pickRequestID, String paymentMethodID, String authToken, String scheduledDateTime) {
        String RequestText ="API REQUEST : Customer Confirmation of pickup request "+ pickRequestID+" | Payment Method ID: "+ paymentMethodID+" | Auth Token : "+ authToken +" | Scheduled Date Time : "+ scheduledDateTime;

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("WalletRef", (String)cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"));
        jsonObj.put("EstLoadUnloadTimeInMilliseconds", 900000);
        jsonObj.put("PickupRequestID", pickRequestID);
        jsonObj.put("PaymentMethodID", paymentMethodID);
        jsonObj.put("Description", "");
        String str="";
        if (!scheduledDateTime.equals("")) {
            jsonObj.put("IsScheduledPickup", true);
            jsonObj.put("ScheduledDateTime", scheduledDateTime);
            str= "";
        } else {
            jsonObj.put("IsScheduledPickup", false);
            str= "TestNote for OnDemand "+pickRequestID;

        }
        jsonObj.put("PickupNote",str);
        cucumberContextManager.setScenarioContext("PICKUP_NOTE",str);
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;
        try{
            Thread.sleep(10000);} catch(Exception ex){}

        apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_CONFIRMATION);
        Response response = ApiHelper.uploadImage(apiURL, jsonObj, header);
        JsonPath jsonPathEvaluator = response.jsonPath();
        HashMap error = jsonPathEvaluator.get("Error");
        if (error != null && error.size()!=0) {
            String errorCode = jsonPathEvaluator.get("Error.Code").toString();
            if (errorCode=="20027")
            {
                scheduledDateTime = getNextTime(scheduledDateTime);
                logger.detail("Oops! Since there has been a delay in requesting this trip, the scheduled time selected is no longer valid. Requesting with 15 minutes later time.");
                response = customerConfirmationWithoutNotes(pickRequestID, paymentMethodID, authToken, scheduledDateTime);
            }
            else if (errorCode=="3004")
            {
                try{ Thread.sleep(30000);}catch (InterruptedException e){}
                scheduledDateTime = getNextTime(scheduledDateTime);
                logger.detail("There was a problem processing your credit card; please double check your payment information and try again. | Retrying in 30 Seconds");
                response = customerConfirmationWithoutNotes(pickRequestID, paymentMethodID, authToken, scheduledDateTime);
            }
            else
                ApiHelper.genericResponseValidation(response, RequestText);
        }
        else
            ApiHelper.genericResponseValidation(response, RequestText);
        return response;
    }
    public String getNextTime(String scheduledDateTime)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date d = null;
        try {
            d = df.parse(scheduledDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, 15);
        String newTime = df.format(cal.getTime());
        return newTime;
    }

    public String[] getScheduledBungiiTime() {
        String[] rtnArray = new String[2];
        int bufferTimeToStartTrip = 0;
        Calendar calendar = Calendar.getInstance();
        int mnts = calendar.get(Calendar.MINUTE);

        String slot= (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
        if(slot.equalsIgnoreCase("NEXT_POSSIBLE_FIRST_SLOT")){
            calendar.set(Calendar.MINUTE, mnts+ 30);
        }
        else if(slot.equalsIgnoreCase("NEXT_POSSIBLE_THIRD_SLOT")){
            calendar.set(Calendar.MINUTE, mnts+ 60);
        }
        else{
            calendar.set(Calendar.MINUTE, mnts+ 45);// Always choose 2nd possible slot to avoid issues with delay in requesting bungii
        }
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

        logger.detail("TIME CALC BLOCK3 : "+  rtnArray[0]);
        cucumberContextManager.setScenarioContext("BUNGII_UTC", rtnArray[0]);

        return rtnArray;

    }
    public String[] getScheduledBungiiTime(String teletTime) {
        String[] rtnArray = new String[2];
        int bufferTimeToStartTrip = 0;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = null;
        try {
            date = sdfd.parse(teletTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int mnts = calendar.get(Calendar.MINUTE);
        String teletType=(String)cucumberContextManager.getScenarioContext("TELET_TYPE");
            if(teletType.equalsIgnoreCase("TELET SAME TIME")) {
                calendar.set(Calendar.MINUTE, mnts + 30);
            }
            else if(teletType.equalsIgnoreCase("TELET OVERLAP")){
                calendar.set(Calendar.MINUTE, mnts + 45);
            }
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 15;
        calendar.add(Calendar.MINUTE, (15 - mod));
        calendar.set(Calendar.SECOND, 0);
        Date nextQuatter = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// create a formatter for date
        //sdf.setTimeZone(TimeZone.getTimeZone("UTC")); //Commeting TELET IS already in UTC
        String formattedDate = sdf.format(nextQuatter);

        String wait = (((15 - mod) + bufferTimeToStartTrip) * 1000 * 60) + "";
        rtnArray[0] = formattedDate+".000";
        rtnArray[1] = wait;
        logger.detail("TIME CALC BLOCK TELET : "+ rtnArray[0]);
        cucumberContextManager.setScenarioContext("BUNGII_UTC", rtnArray[0]);

        return rtnArray;

    }
    public String[] getScheduledBungiiTimeFurtureSlot(String teletTime) {
        String[] rtnArray = new String[2];
        int bufferTimeToStartTrip = 0;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = null;
        try {
            date = sdfd.parse(teletTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int mnts = calendar.get(Calendar.MINUTE);
        String teletType=(String)cucumberContextManager.getScenarioContext("TELET_TYPE");
        if(teletType.equalsIgnoreCase("TELET SAME TIME")) {
            calendar.set(Calendar.MINUTE, mnts + 30);
        }
        else if(teletType.equalsIgnoreCase("TELET OVERLAP")){
            calendar.set(Calendar.MINUTE, mnts + 45);
        }
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 15;
        if(mod!=0)
            calendar.add(Calendar.MINUTE, (15 - mod));
        calendar.set(Calendar.SECOND, 0);
        Date nextQuatter = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// create a formatter for date
        //sdf.setTimeZone(TimeZone.getTimeZone("UTC")); //Commeting TELET IS already in UTC
        String formattedDate = sdf.format(nextQuatter);

        String wait = (((15 - mod) + bufferTimeToStartTrip) * 1000 * 60) + "";
        rtnArray[0] = formattedDate+".000";
        rtnArray[1] = wait;
        logger.detail("TIME CALC BLOCK TELET : "+ rtnArray[0]);
        cucumberContextManager.setScenarioContext("BUNGII_UTC", rtnArray[0]);

        return rtnArray;

    }

    public String[] getScheduledBungiiTime(int minuteDifferance) {
        String[] rtnArray = new String[2];
        int bufferTimeToStartTrip = 0;
        Calendar calendar = Calendar.getInstance();
        int mnts = calendar.get(Calendar.MINUTE);

       /* if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date()))
        {
            calendar.set(Calendar.MINUTE, mnts + 30 + minuteDifferance);
            int timer = mnts + 30 + minuteDifferance;
            logger.detail("Calculated Time [Daylight On] : " + timer);
        }
        else*/
        {
            calendar.set(Calendar.MINUTE, mnts + minuteDifferance);
            int timer = mnts + minuteDifferance;
            logger.detail("Calculated Time [Daylight Off] : " + timer);
        }
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 15;
        calendar.add(Calendar.MINUTE, (15 - mod));
        calendar.set(Calendar.SECOND, 0);
        Date nextQuatter = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// create a formatter for date
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(nextQuatter);

        String wait = (((15 - mod) + bufferTimeToStartTrip) * 1000 * 60) + "";
        rtnArray[0] = formattedDate+".000";;
        rtnArray[1] = wait;
        logger.detail("Schedule Time  : " +  rtnArray[0]);
        logger.detail("TIME CALC BLOCK with Differnece of "+ minuteDifferance);
        cucumberContextManager.setScenarioContext("BUNGII_UTC", rtnArray[0]);


        return rtnArray;

    }
    public int customerConfirmationScheduled(String pickRequestID, String paymentMethodID, String authToken) {
        //get utc time and time for bungii to start
        logger.detail("Customer Confirmation of Scheduled pickup request "+ pickRequestID+" | Payment Method ID: "+ paymentMethodID+" | Auth Token : "+ authToken);
        String[] nextAvailableBungii = getScheduledBungiiTime();
        Date date = new EstimateSteps().getNextScheduledBungiiTime();
        String strTime = new EstimateSteps().bungiiTimeDisplayInTextArea(date);

        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        if(PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID") &&currentGeofence.equalsIgnoreCase("goa")){

            String timeLabel=" " + new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofence();

            if(strTime.contains(timeLabel))
                strTime=strTime.replace(timeLabel,"");
        }
            cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime.replace("am", "AM").replace("pm","PM"));
            cucumberContextManager.setScenarioContext("SCHEDULED_BUNGII_TIME", strTime.replace("am", "AM").replace("pm","PM"));
        //   if (PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID"))
    //        cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);

        int waitDuraton = Integer.parseInt(nextAvailableBungii[1]);
        customerConfirmation(pickRequestID, paymentMethodID, authToken, nextAvailableBungii[0]);
        return waitDuraton;
    }
    public int customerConfirmationScheduled(String pickRequestID, String paymentMethodID, String authToken,int minDiff) throws ParseException {
        //get utc time and time for bungii to start
        logger.detail("Customer Confirmation of Scheduled pickup request "+ pickRequestID+" | Payment Method ID: "+ paymentMethodID+" | Auth Token : "+ authToken +" | Minute Difference "+ minDiff);

        String[] nextAvailableBungii = getScheduledBungiiTime(minDiff);

        Date date = new EstimateSteps().getNextScheduledBungiiTimeDependingOn(minDiff);
        String strTime = new EstimateSteps().bungiiTimeDisplayInTextArea(date);
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        if(PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID") &&currentGeofence.equalsIgnoreCase("goa")){
            String timeLabel=" "+new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofence();
            if(strTime.contains(timeLabel))
                strTime=strTime.replace(timeLabel,"");
        }

        cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime.replace("am", "AM").replace("pm","PM"));
        cucumberContextManager.setScenarioContext("SCHEDULED_BUNGII_TIME", strTime.replace("am", "AM").replace("pm","PM"));

        //   if (PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID"))
        //        cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);

        int waitDuraton = Integer.parseInt(nextAvailableBungii[1]);
        customerConfirmation(pickRequestID, paymentMethodID, authToken, nextAvailableBungii[0]);
        return waitDuraton;
    }
    public int customerConfirmationScheduled(String pickRequestID, String paymentMethodID, String authToken,String label) {
        //get utc time and time for bungii to start
        logger.detail("Customer Confirmation of Scheduled pickup request "+ pickRequestID+" | Payment Method ID: "+ paymentMethodID+" | Auth Token : "+ authToken);

        String[] nextAvailableBungii = getScheduledBungiiTime();
        Date date = new EstimateSteps().getNextScheduledBungiiTime();
        String strTimeDriverEarnings = new EstimateSteps().bungiiTimeDisplayDriverEarning(date);
        String strTime = new EstimateSteps().bungiiTimeDisplayInTextArea(date);
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        cucumberContextManager.setScenarioContext("TIME",strTime);
        if(PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID") &&currentGeofence.equalsIgnoreCase("goa")){
            String timeLabel=" "+new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofence();
            if(strTime.contains(timeLabel))
                strTime=strTime.replace(timeLabel,"");
        }

        cucumberContextManager.setScenarioContext("BUNGII_TIME"+label, strTime.replace("am", "AM").replace("pm","PM"));
        cucumberContextManager.setScenarioContext("SCHEDULED_BUNGII_TIME", strTime.replace("am", "AM").replace("pm","PM"));

        cucumberContextManager.setScenarioContext("Scheduled_Time",strTimeDriverEarnings.replace("am", "AM").replace("pm","PM"));
        //   if (PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID"))
        //        cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);

        int waitDuraton = Integer.parseInt(nextAvailableBungii[1]);
        if(cucumberContextManager.getScenarioContext("Customer_Notes").equals("Blank")){
            customerConfirmationWithoutNotes(pickRequestID, paymentMethodID, authToken, nextAvailableBungii[0]);
        }
        else {
            customerConfirmation(pickRequestID, paymentMethodID, authToken, nextAvailableBungii[0]);
        }
        return waitDuraton;
    }

    public int customerConfirmationScheduledForTelet(String pickRequestID, String paymentMethodID, String authToken,String teletTime) {
        //get utc time and time for bungii to start
        logger.detail("Customer Confirmation of Scheduled pickup request "+ pickRequestID+" | Payment Method ID: "+ paymentMethodID+" | Auth Token : "+ authToken);

        String[] nextAvailableBungii = getScheduledBungiiTime(teletTime);
       Date date = new EstimateSteps().getNextScheduledBungiiTime();
        String strTime = new EstimateSteps().bungiiTimeDisplayInTextArea(date);
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        cucumberContextManager.setScenarioContext("TIME",strTime);
        if(PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID") &&currentGeofence.equalsIgnoreCase("goa")){
            String timeLabel=" "+new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofence();
            if(strTime.contains(timeLabel))
                strTime=strTime.replace(timeLabel,"");
        }

        cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime.replace("am", "AM").replace("pm","PM"));
        cucumberContextManager.setScenarioContext("SCHEDULED_BUNGII_TIME", strTime.replace("am", "AM").replace("pm","PM"));

        //   if (PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID"))
        //        cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);

        int waitDuraton = Integer.parseInt(nextAvailableBungii[1]);
        customerConfirmation(pickRequestID, paymentMethodID, authToken, nextAvailableBungii[0]);
        return waitDuraton;
    }

    public int customerConfirmationScheduledForFuture(String pickRequestID, String paymentMethodID, String authToken,String futureTime) throws ParseException {
        //get utc time and time for bungii to start
        logger.detail("Customer Confirmation of Scheduled pickup request [Future Date] "+ pickRequestID+" | Payment Method ID: "+ paymentMethodID+" | Auth Token : "+ authToken);

        String[] nextAvailableBungii = getScheduledBungiiTimeFurtureSlot(futureTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sdf.parse(nextAvailableBungii[0]);
        Calendar cal = new GregorianCalendar();
        cal.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));

        cal.setTime(date);
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd, HH:mm a z", Locale.US);
        sdf1.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));
        System.out.println(sdf1.format(date));
       // LocalDateTime.parse(nextAvailableBungii[0], DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss.SSS" , Locale.US )).atZone( ZoneId.of( "America/New_York" ) );
        String strTime = sdf1.format(date);
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        cucumberContextManager.setScenarioContext("TIME",strTime);
        cucumberContextManager.setScenarioContext("Schedule_Date_Time",strTime);
        if(PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID") &&currentGeofence.equalsIgnoreCase("goa")){
            String timeLabel=" "+new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofence();
            if(strTime.contains(timeLabel))
                strTime=strTime.replace(timeLabel,"");
        }
        cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime.replace("am", "AM").replace("pm","PM"));
        cucumberContextManager.setScenarioContext("SCHEDULED_BUNGII_TIME", strTime.replace("am", "AM").replace("pm","PM"));

        //   if (PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID"))
        //        cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);
        logger.detail("UTC TIME : "+ nextAvailableBungii[0] + " and geofence based time : "+ strTime);
        int waitDuraton = Integer.parseInt(nextAvailableBungii[1]);
        customerConfirmation(pickRequestID, paymentMethodID, authToken, nextAvailableBungii[0]);
        return waitDuraton;
    }

    public Response customerView(String pickuprequestid, String authToken) {
        String RequestText ="API REQUEST : Get Customer View "+ pickuprequestid +" | Auth Token : "+ authToken;

        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_VIEW);
        Response response = ApiHelper.givenCustConfig().header(header).param("pickuprequestid", pickuprequestid).when().
                get(apiURL);
        //response.then().log().body();
        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response,RequestText);
        return response;

    }

    public Response driverView(String pickuprequestid, String authToken) {
        String RequestText ="API REQUEST : Get Driver View "+ pickuprequestid +" | Auth Token : "+ authToken;

        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", DRIVER_VIEW);
        Response response = ApiHelper.givenDriverConfig().header(header).param("pickuprequestid", pickuprequestid).when().
                get(apiURL);
       // response.then().log().body();
        ApiHelper.genericResponseValidation(response,RequestText);
        return response;


    }

    public String driverPaymentMethod(String pickuprequestid, String authToken) {
        try {
            String RequestText ="API REQUEST : Get Driver Payment Method "+ pickuprequestid +" | Auth Token : "+ authToken;
            Response response = driverView(pickuprequestid, authToken);
            ApiHelper.genericResponseValidation(response,RequestText);
            JsonPath jsonPathEvaluator = response.jsonPath();
            return jsonPathEvaluator.get("PickupDetails.PaymentMethodRef");
        } catch (Exception e) {
            return "";
        }
    }

    public Response updateDriverLocation(String authToken, String geofence) {
        Float[] driverLocations = utility.getDriverLocation(geofence);
        String RequestText ="API REQUEST : (For Ondemand Bungiis) Update Driver Location of Authtoken : "+ authToken +" | Geofence : "+ geofence+" | Location : "+ driverLocations[0]+","+driverLocations[1];
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Latitude", driverLocations[0]);
        jsonObj.put("Longitude", driverLocations[1]);
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", UPDATE_LOCATION);
        Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
        ApiHelper.genericResponseValidation(response, RequestText);
        return response;

    }
    public Response stackedPickupConfirmation(String pickuprequestid, String authToken) {
       String RequestText= "API REQUEST : Stacked Pickup Request Confirmation of Pickup Request id:  "+ pickuprequestid +" | Auth Token : "+ authToken;

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("DeviceName", "XT1092");
        //make status online
        jsonObj.put("PickupRequestID", pickuprequestid);
        jsonObj.put("EventSource", 2);
        jsonObj.put("DeviceToken", "fYUrbPrSXAo:APAS1bFc7QqYIWYyYaIvlcu1Nz30Swc67UDBg75rwUlNbPZDIi2dLdrsgdplYB5GmJqOihXVB64bwVmfEqZAF0DkTOsYX8b8VrjleMHjkSVdQy3ao2nWrCot_HcXx6jYY7pksq3JbKCHP0QYyvmywSA6HRNIhXgiSa" + utility.genearateRandomString());
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", STACKED_PICKUP_CONFIRMATION);
        Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
        ApiHelper.genericResponseValidation(response, RequestText);
        return response;
    }

    public Response updateDriverStatus(String authToken) {
        logger.detail("API REQUEST : Make Driver with Authtoken Online: "+ authToken);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("DeviceName", "XT1092");
        //make status online
        jsonObj.put("Status", 1);
        jsonObj.put("DeviceToken", "fYUrbPrSXAo:APAS1bFc7QqYIWYyYaIvlcu1Nz30Swc67UDBg75rwUlNbPZDIi2dLdrsgdplYB5GmJqOihXVB64bwVmfEqZAF0DkTOsYX8b8VrjleMHjkSVdQy3ao2nWrCot_HcXx6jYY7pksq3JbKCHP0QYyvmywSA6HRNIhXgiSa" + utility.genearateRandomString());
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", UPDATE_STATUS);
        Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
        //ApiHelper.genericResponseValidation(response);
        return response;
    }
    public void updateStatusForDriverReject(String pickupID, String authToken) {
        try {
            String utcTime= utility.getCurrentUTCTime();
            String RequestText = "API REQUEST : Select rejection reason pickup id : "+ pickupID + " | Authtoken : "+ authToken + " at "+ utcTime;
            String apiURL = null;
            apiURL = UrlBuilder.createApiUrl("core",DRIVER_REJECTION_REASON);

            Header header = new Header("AuthorizationToken", authToken);

            Response response = ApiHelper.givenCustConfig().header(header).param("pickuprequestid", pickupID).when().get(apiURL);
            //response.then().log().body();
            String rejectionReason= response.getBody().jsonPath().get("Remarks[1].Description");

            String apiurl = null;
            apiurl=UrlBuilder.createApiUrl("core",DRIVER_SAVE_REJECTION_REASON);

            String remarkID=new DbUtility().getRemarkId(rejectionReason);

            JSONObject jsonObj = new JSONObject();
            JSONObject status = new JSONObject();
                status.put("PickupRequestID", pickupID);
                status.put("RemarkID", remarkID);
                status.put("RemarkText", "");

            Response respons = ApiHelper.postDetailsForDriver(apiurl, status, header);
            ApiHelper.genericResponseValidation(respons,RequestText);
        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }

    }

    public void updateStatus(String pickupID, String authToken, int statusID) {
        try {
            String utcTime= utility.getCurrentUTCTime();
            String RequestText = "API REQUEST : Set Status of pickup id : "+ pickupID + " | Authtoken : "+ authToken + " | Status ID : "+ statusID +" at "+ utcTime;

            cucumberContextManager.setScenarioContext("ONDEMAND_PICKUP_ID",pickupID);
            Float[] driverLocations = utility.getDriverLocation((String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE"));
                String waypointId=DbUtility.getWaypointId((String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST"),statusID);
                JSONObject driverCoordinates = new JSONObject();
                driverCoordinates.put("Latitude", driverLocations[0]);
                driverCoordinates.put("Longitude", driverLocations[1]);

                JSONObject jsonObj = new JSONObject();
                JSONObject status = new JSONObject();
                JSONArray statusArray = new JSONArray();
                status.put("PickupId", pickupID);
                status.put("PickupStatusId", pickupID);
                status.put("synced", false);
                status.put("StatusTimestamp", utcTime);
                status.put("Status", statusID);
                status.put("Location", driverCoordinates);
                status.put("WaypointId", waypointId);
                statusArray.put(status);
                jsonObj.put("Statuses", statusArray);
                jsonObj.put("PickupRequestID", pickupID);

                Header header = new Header("AuthorizationToken", authToken);

                String apiURL = null;

                apiURL = UrlBuilder.createApiUrl("core", UPDATE_PICKUP_STATUS);
                Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
                ApiHelper.genericResponseValidation(response,RequestText);
            //make status online

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }

    }

    public void updateStatusForWeightBased(String pickupID, String authToken, int statusID) {
        try {
            String utcTime= utility.getCurrentUTCTime();
            String RequestText = "API REQUEST : Set Status of pickup id : "+ pickupID + " | Authtoken : "+ authToken + " | Status ID : "+ statusID +" at "+ utcTime;

            cucumberContextManager.setScenarioContext("ONDEMAND_PICKUP_ID",pickupID);


            String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String waypointId=DbUtility.getWaypointId(pickupRequest,statusID);
            String tripRef =  DbUtility.getTripReference(pickupRequest);
            String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
            JSONObject jsonObj = new JSONObject();
            JSONObject status = new JSONObject();
            JSONArray statusArray = new JSONArray();
            JSONObject driverCordinate = new JSONObject();
            Float[] driverLocations = utility.getDriverLocation(geofence);

            status.put("StatusTimestamp", utcTime);

            driverCordinate.put("Latitude", driverLocations[0]);
            driverCordinate.put("Longitude", driverLocations[1]);
            status.put("Location", driverCordinate);

            status.put("TripRef", tripRef);
            status.put("Status", statusID);
            status.put("PickupId", pickupID);
            status.put("WaypointId", waypointId);
            statusArray.put(status);
            jsonObj.put("Statuses", statusArray);


            //make status online
            jsonObj.put("PickupRequestID", pickupID);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", UPDATE_PICKUP_STATUS);
            Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
            ApiHelper.genericResponseValidation(response,RequestText);


        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }

    }

    public void updateStatusForDuoWeightBased(String pickupID, String authToken, int statusID) {
        try {
            String utcTime= utility.getCurrentUTCTime();
            String RequestText = "API REQUEST : Set Status of pickup id : "+ pickupID + " | Authtoken : "+ authToken + " | Status ID : "+ statusID +" at "+ utcTime;

            cucumberContextManager.setScenarioContext("ONDEMAND_PICKUP_ID",pickupID);
            String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
            String waypointId=DbUtility.getWaypointId(pickupRequest,statusID);

            Float[] driverLocations = utility.getDriverLocation(geofence);
            String[] allTripRef = DbUtility.getTripReferenceForDuo(pickupRequest);
            if(cucumberContextManager.getScenarioContext("DRIVER_STATUS").toString().contentEquals("Weight Based Driver1")){
                cucumberContextManager.setScenarioContext("TripRef",allTripRef[1]);
            }
            else{
                cucumberContextManager.setScenarioContext("TripRef",allTripRef[0]);
            }

            String tripRef = (String) cucumberContextManager.getScenarioContext("TripRef");

            JSONObject jsonObj = new JSONObject();
            JSONObject status = new JSONObject();
            JSONArray statusArray = new JSONArray();
            JSONObject driverCordinate = new JSONObject();


            status.put("StatusTimestamp", utcTime);

            driverCordinate.put("Latitude", driverLocations[0]);
            driverCordinate.put("Longitude", driverLocations[1]);
            status.put("Location", driverCordinate);

            status.put("TripRef", tripRef);
            status.put("Status", statusID);
            status.put("PickupId", pickupID);
            status.put("WaypointId", waypointId);
            statusArray.put(status);
            jsonObj.put("Statuses", statusArray);


            //make status online
            jsonObj.put("PickupRequestID", pickupID);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", UPDATE_PICKUP_STATUS);
            Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
            ApiHelper.genericResponseValidation(response,RequestText);




        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }

    }


    public Response AcceptBungii(String pickupID, String authToken) {
        Response response = null;
        try {
            String RequestText = "API REQUEST : Set Status of pickup id : "+ pickupID + " | Authtoken : "+ authToken + " | Status ID : "+ 21;
            JSONObject jsonObj = new JSONObject();
            JSONObject status = new JSONObject();
            JSONArray statusArray = new JSONArray();
            status.put("PickupId", pickupID);
            status.put("PickupStatusId", pickupID);
            status.put("synced", false);
            status.put("StatusTimestamp", utility.getCurrentUTCTime());
            status.put("Status", 21);
            statusArray.put(status);
            jsonObj.put("Statuses", statusArray);

            //make status online
            jsonObj.put("PickupRequestID", pickupID);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", UPDATE_PICKUP_STATUS);
            response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
          //  ApiHelper.genericResponseValidation(response,RequestText);


        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
      return response ;
    }
    public void pickupdetails(String pickupID, String authToken, String geofence) {
        try {
            String RequestText = "API REQUEST : Get Pickup Details of pickup id : "+ pickupID + " | Authtoken : "+ authToken + " | Geofence : "+ geofence;
            JSONObject jsonObj = new JSONObject();
            JSONObject driverCordinate = new JSONObject();
            Float[] driverLocations = utility.getDriverLocation(geofence);

            driverCordinate.put("Latitude", driverLocations[0]);
            driverCordinate.put("Longitude", driverLocations[1]);
            //        driverCordinate.put("Latitude", 15.36773730);
            //       driverCordinate.put("Longitude", 73.936542900);
            //make status online
            jsonObj.put("Location", driverCordinate);
            jsonObj.put("PickupRequestID", pickupID);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", PICKUP_DETAILS);
            Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
            ApiHelper.genericResponseValidation(response, RequestText);


        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }

    }

    public JsonPath getPickupdetails(String pickupID, String authToken, String geofence) {
        JsonPath jsonPathEvaluator= null;
        try {

            String RequestText = "API REQUEST : Get Pickup Details of pickup id : "+ pickupID + " | Authtoken : "+ authToken + " | Geofence : "+ geofence;
            JSONObject jsonObj = new JSONObject();
            JSONObject driverCordinate = new JSONObject();
            Float[] driverLocations = utility.getDriverLocation(geofence);

            driverCordinate.put("Latitude", driverLocations[0]);
            driverCordinate.put("Longitude", driverLocations[1]);
            jsonObj.put("Location", driverCordinate);
            jsonObj.put("PickupRequestID", pickupID);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", PICKUP_DETAILS);
            Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
            jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
        return jsonPathEvaluator;
    }

    public JsonPath getPickupdetailsFromPushNotification(String pickupID, String authToken, String geofence) {
        JsonPath jsonPathEvaluator= null;
        try {

            String RequestText = "API REQUEST : Get Pickup Details of pickup id : "+ pickupID + " | Authtoken : "+ authToken + " | Geofence : "+ geofence;
            JSONObject jsonObj = new JSONObject();
            JSONObject driverCordinate = new JSONObject();
            Float[] driverLocations = utility.getDriverLocation(geofence);

            driverCordinate.put("Latitude", driverLocations[0]);
            driverCordinate.put("Longitude", driverLocations[1]);
            jsonObj.put("Location", driverCordinate);
            jsonObj.put("PickupRequestID", pickupID);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", PICKUP_DETAILS);
            Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
            jsonPathEvaluator = response.jsonPath();
           // ApiHelper.genericResponseValidation(response, RequestText);

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
        return jsonPathEvaluator;
    }

    public void rateAndTip(String pickupRef, String authToken, String driverRef, String tipPaymentMethod, Double
            rating, Double tipAmount) {
        try {
            String RequestText = "API REQUEST : Rate and Tip | pickup Request : "+ pickupRef + " | Authtoken : "+ authToken + " | Driver Ref : "+ driverRef + " | Tip Payment Method : "+ tipPaymentMethod+ " | Rating : "+ rating+ " | Tip Amount : "+ tipAmount;
            JSONObject jsonObj = new JSONObject();
            JSONObject driver = new JSONObject();
            JSONArray driverArray = new JSONArray();

            driver.put("DriverRef", driverRef);
            driver.put("TipPaymentMethodRef", tipPaymentMethod);
            driver.put("Rating", rating);
            driver.put("TipAmount", tipAmount);
            driverArray.put(driver);
            jsonObj.put("Driver", driverArray);
            jsonObj.put("PickupRequestID", pickupRef);

            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", RATE_AND_TIP);
            Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }

    public void rateAndTip(String pickupRef, String authToken, String driverRef, String tipPaymentMethod, Double
            rating, Double tipAmount, String driver2Ref, String tipPayment2Method) {
        try {
            String RequestText = "API REQUEST : Rate and Tip | pickup Request : "+ pickupRef + " | Authtoken : "+ authToken + " | Driver Ref : "+ driverRef + " | Tip Payment Method : "+ tipPaymentMethod+ " | Rating : "+ rating+ " | Tip Amount : "+ tipAmount;
            JSONObject jsonObj = new JSONObject();
            JSONObject driver = new JSONObject();
            JSONObject driver2 = new JSONObject();
            JSONArray driverArray = new JSONArray();

            driver.put("DriverRef", driverRef);
            driver.put("TipPaymentMethodRef", tipPaymentMethod);
            driver.put("Rating", rating);
            driver.put("TipAmount", tipAmount);

            driver2.put("DriverRef", driver2Ref);
            driver2.put("TipPaymentMethodRef", tipPayment2Method);
            driver2.put("Rating", rating);
            driver2.put("TipAmount", tipAmount);

            driverArray.put(driver);
            driverArray.put(driver2);
            jsonObj.put("Driver", driverArray);
            jsonObj.put("PickupRequestID", pickupRef);

            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", RATE_AND_TIP);
            Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response,RequestText);

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }
    public void driverPollingCalls(String pickupRequest, String geofence, String driverAccessToken){
        logger.detail("Driver Polling | Pickup Request : "+ pickupRequest  + "| Geofence : "+ geofence + "| Driver Access token : "+ driverAccessToken);

        if(!driverAccessToken.equalsIgnoreCase("")) {
            driverView(pickupRequest, driverAccessToken);
            updateDriverLocation(driverAccessToken, geofence);
        }else{
            logger.detail("Please specify valid access token");
        }

    }
    public Response getCustomersScheduledPickupList(String authToken) {
        String RequestText = "API REQUEST : Get Customer Scheduled Pickup List | Auth Token : "+ authToken;

        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_SCHEDULEDLIST);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForDriver(apiURL, header);
        ApiHelper.genericResponseValidation(response,RequestText);

        return response;

    }

    public Response getDriverScheduledPickupList(String authToken) {
        String RequestText = "API REQUEST : Get driver Scheduled Pickup List | Auth Token : "+ authToken;

        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("core", DRIVER_SCHEDULEDLIST);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForDriver(apiURL, header);
        ApiHelper.genericResponseValidation(response,RequestText);
        //response.then().log().body();
        return response;

    }
    public void cancelAllScheduledBungiis(String authToken){

        JsonPath jsonPathEvaluator = getCustomersScheduledPickupList(authToken).jsonPath();

        ArrayList ScheduledPickups = jsonPathEvaluator.get("ScheduledPickups");
        if (ScheduledPickups != null) {
            logger.detail("***Total scheduled deliveries found for customer : "+ ScheduledPickups.size()+"***");
            for (int i = 0; i < ScheduledPickups.size(); i++) {
                HashMap pickupDetails = (HashMap) ScheduledPickups.get(i);
                String pickupRequest = (String) pickupDetails.get("PickupRef");
                boolean CanBeCancelled = (boolean) pickupDetails.get("CanBeCancelled");
                getScheduledPickupDetails(pickupRequest,authToken);
               /* if(CanBeCancelled) {
                    cancelBungiiAsCustomer(pickupRequest, authToken);
                    logger.detail("***Cancelled Pickup Request as Customer | Pickup Request : "+ pickupRequest+"***");
                }
                else {*/
                    new WebPortal().cancelBungiiAsAdmin(pickupRequest);
                    logger.detail("***Cancelled Pickup Request as Admin | Pickup Request : "+ pickupRequest+"***");
                //}
            }
        }
        else
            logger.detail("***No Scheduled Bungiis Found for Customer***");


    }
    public void cancelOrCompleteOngoingBungii(String custAccessToken){
        logger.detail("***Checking Ongoing Bungiis of Customer***");
        Response response= customerView("", custAccessToken);


        JsonPath jsonPathEvaluator = response.jsonPath();
        Object pickupDetails = jsonPathEvaluator.get("PickupDetails");

        //IF customer is has some bungii onn screen
        if(pickupDetails != null ) {
            String pickupRequestID = jsonPathEvaluator.get("PickupDetails.PickupRequestID");
            int pickupStatus = jsonPathEvaluator.get("PickupDetails.PickupStatus");
            int numberOfDriver = jsonPathEvaluator.get("PickupDetails.NoOfDrivers");
            //on demand searching
            if (pickupStatus == 4) {
                cancelBungiiAsCustomer(pickupRequestID, custAccessToken);
                logger.detail("***Cancelled Pickup Request as Customer | Pickup Request : "+ pickupRequestID+"***");
            }
            else if(pickupStatus == 23 || pickupStatus == 24) {
                //cancel Bungii as driver
                String driverPhoneCode="1";
                String driverPhoneNum=new DbUtility().getDriverAssignedForTrip(pickupRequestID);
                String driverPassword= ((String) cucumberContextManager.getScenarioContext("DRIVER_1_PASSWORD")).equals("")? "Cci12345":(String) cucumberContextManager.getScenarioContext("DRIVER_1_PASSWORD");
                String driverAccessToken = new AuthServices().getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);

                updateStatus(pickupRequestID, driverAccessToken, 66);
                logger.detail("***Pickup Status moved to 66 From either 23 or 24 | Pickup Request : "+ pickupRequestID+"***");

            } else if(pickupStatus == 25 || pickupStatus == 26 ||pickupStatus == 27 ||pickupStatus == 28) {
                //complete Bungii as driver
                String driverPhoneCode="1";
                String driverPhoneNum=new DbUtility().getDriverAssignedForTrip(pickupRequestID);
                String driverPassword=((String) cucumberContextManager.getScenarioContext("DRIVER_1_PASSWORD")).equals("")? "Cci12345":(String) cucumberContextManager.getScenarioContext("DRIVER_1_PASSWORD");
                String driverAccessToken = new AuthServices().getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
                switch (pickupStatus){
                    case 25:
                        updateStatus(pickupRequestID, driverAccessToken, 26);
                    case 26:
                        updateStatus(pickupRequestID, driverAccessToken, 27);
                    case 27:
                        updateStatus(pickupRequestID, driverAccessToken, 28);
                    case 28:
                       // try {Thread.sleep(35000); } catch (InterruptedException e) {e.printStackTrace();}
                }
                String paymentMethod = new PaymentServices().getPaymentMethodRef(custAccessToken);

                JsonPath jsonPathEvaluator1 = response.jsonPath();
                if(numberOfDriver==1) {
                    String driverRef = jsonPathEvaluator1.get("PickupDetails.TripDetails[0].Driver.DriverRef");
                    rateAndTip(pickupRequestID, custAccessToken, driverRef, paymentMethod, 5.0, 0.0);
                }else{
                    String driver1Ref = jsonPathEvaluator1.get("PickupDetails.TripDetails[0].Driver.DriverRef");
                    String driver2Ref = jsonPathEvaluator1.get("PickupDetails.TripDetails[1].Driver.DriverRef");
                    rateAndTip(pickupRequestID, custAccessToken, driver1Ref, paymentMethod, 5.0, 0.0, driver2Ref, paymentMethod);

                }
                logger.detail("***Completed In Progress Trip | Pickup Request : "+ pickupRequestID+"***");
            }

        }
        else
            logger.detail("***No Ongoing Bungiis Found for the Customer***" );
    }
    public Response getScheduledPickupDetails(String pickuprequestid, String authToken) {
        String RequestText ="API REQUEST : Get Scheduled Pickup Details | pickup Request : "+ pickuprequestid + " | Authtoken : "+ authToken;

        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_SCHEDULEDPICKUPLIST);
        Response response = ApiHelper.givenCustConfig().header(header).param("pickuprequestid", pickuprequestid).when().
                get(apiURL);
       // response.then().log().body();
        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response,RequestText);
        return response;
    }

    public void cancelBungiiAsCustomer(String pickupRef, String authToken) {
        try {
            String RequestText ="API REQUEST : Cancel Bungii As a Customer | pickup Request : "+ pickupRef + " | Authtoken : "+ authToken + " | Status : "+ 64;

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("Status", 64);
            jsonObj.put("StatusTimestamp", utility.getCurrentUTCTime());
            jsonObj.put("PickupRequestID", pickupRef);
            Header header = new Header("AuthorizationToken", authToken);
            String apiURL = null;
            apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_CANCELPICKUPLIST);
            Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
            ApiHelper.genericResponseValidation(response,RequestText);

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }

    public Response getPromoCodes(String authToken,String pickupRequestid) {
        String RequestText ="API REQUEST : Get Promo Codes | pickup Request : "+ pickupRequestid + " | Authtoken : "+ authToken;

        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("core", GET_PROMOCODE);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.givenCustConfig().header(header).param("pickuprequestid", pickupRequestid).when().
                get(apiURL);
       // response.then().log().body();
        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response, RequestText);
        return response;
     }

    public String partnerPickupEstimate(String Partner_Portal,String Geofence,String Bungii_Time,String BusinessPartnerDefaultAddressRef,String BusinessPartnerDefaultAddressConfigVersionID){
        String RequestText ="API REQUEST : Partner Estimate Cost(Post) |  : "+ Partner_Portal;
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("core",PARTNER_PICKUPESTIMATE);
        //String PartnerLocationConfigVersion = (String) cucumberContextManager.getScenarioContext("PartnerLocationReference");
        String[] nextAvailableBungii = new String[0];
        int No_of_Driver;
        Response response = null;

        String BungiiType = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        if(BungiiType.equalsIgnoreCase("SOLO")){
            No_of_Driver=1;
        }else {
            No_of_Driver=2;
        }
        cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER",No_of_Driver);
        if(Bungii_Time.equalsIgnoreCase("NEXT_POSSIBLE") || Bungii_Time.equalsIgnoreCase("NEXT_POSSIBLE_FIRST_SLOT") || Bungii_Time.equalsIgnoreCase("NEXT_POSSIBLE_THIRD_SLOT")){
            nextAvailableBungii = getScheduledBungiiTime();
            String temp = nextAvailableBungii[0];
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            //By default data is in UTC
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date dt = null;
            try {
                dt = formatter.parse(temp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            int mnts = calendar.get(Calendar.MINUTE);
            calendar.set(Calendar.MINUTE, mnts - 15);
            int unroundedMinutes = calendar.get(Calendar.MINUTE);
            int mod = unroundedMinutes % 15;
            calendar.add(Calendar.MINUTE, (15 - mod));
            calendar.set(Calendar.SECOND, 0);

            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();

            //DateFormat formatterForLocalTimezone = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            DateFormat formatterForLocalTimezone = new SimpleDateFormat("MMM dd, HH:mm a z");
            formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

            formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

            String strdate = formatterForLocalTimezone.format(calendar.getTime());

            cucumberContextManager.setScenarioContext("BUNGII_TIME",strdate);
        }
        else if(Bungii_Time.equalsIgnoreCase("1_DAY_LATER")) {
            nextAvailableBungii = utility.getDaysLaterTime(1);
        }
        else if(Bungii_Time.equalsIgnoreCase("4_DAY_LATER")) {
            nextAvailableBungii = utility.getDaysLaterTime(4);
        }

        if(Geofence.equalsIgnoreCase("Kansas")) {
            if(Partner_Portal.equalsIgnoreCase("Equip-bid")){
                String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

                String Pickup_Address1 = PropertyUtility.getDataProperties("partner.equip-bid.pickup_address1");
                String Pickup_City = PropertyUtility.getDataProperties("partner.equip-bid.pickup_city");
                String Pickup_Country = PropertyUtility.getDataProperties("partner.equip-bid.pickup_country");
                String Pickup_Latitude = PropertyUtility.getDataProperties("partner.equip-bid.pickup_latitude");
                String Pickup_Longitude = PropertyUtility.getDataProperties("partner.equip-bid.pickup_longitude");
                String Pickup_State = PropertyUtility.getDataProperties("partner.equip-bid.pickup_state");
                String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.equip-bid.pickup_zippostalcode");

                String ServiceLevelRef = PropertyUtility.getDataProperties("partner.equip-bid.service_level_ref");
                String PricingModelConfigVersionRef = PropertyUtility.getDataProperties("partner.equip-bid.pricing_model_ref");

                String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.equip-bid.dropoff_address_id");
                String DropOff_Address1 = PropertyUtility.getDataProperties("partner.equip-bid.dropoff_address1");
                String DropOff_City = PropertyUtility.getDataProperties("partner.equip-bid.dropoff_city");
                String DropOff_Country = PropertyUtility.getDataProperties("partner.equip-bid.dropoff_country");
                String DropOff_Latitude = PropertyUtility.getDataProperties("partner.equip-bid.dropoff_latitude");
                String DropOff_Longitude = PropertyUtility.getDataProperties("partner.equip-bid.dropoff_longitude");
                String DropOff_State = PropertyUtility.getDataProperties("partner.equip-bid.dropoff_state");
                String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.equip-bid.dropoff_zippostalcode");

                //for Pickup Location
                JSONObject jsonPickupLocation = new JSONObject();
                jsonPickupLocation.put("Latitude", Pickup_Latitude);
                jsonPickupLocation.put("Longitude", Pickup_Longitude);

                //for Default Feilds
                JSONArray DefaultFeilds = new JSONArray();

                //for Pickup Address
                JSONObject jsonPickupAddress = new JSONObject();
                jsonPickupAddress.put("Address1", Pickup_Address1);
                jsonPickupAddress.put("Address2", "");
                jsonPickupAddress.put("AddressLabel", "");
                jsonPickupAddress.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonPickupAddress.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);
                jsonPickupAddress.put("City", Pickup_City);
                jsonPickupAddress.put("Country", Pickup_Country);
                jsonPickupAddress.put("DefaultStaticFields", DefaultFeilds);
                jsonPickupAddress.put("IsDefault", true);
                jsonPickupAddress.put("Location", jsonPickupLocation);
                jsonPickupAddress.put("PickupInstructions",JSONObject.NULL );
                jsonPickupAddress.put("State", Pickup_State);
                jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);


                //for Dropoff location
                JSONObject jsonDropOffLocation = new JSONObject();
                jsonDropOffLocation.put("Latitude", DropOff_Latitude);
                jsonDropOffLocation.put("Longitude", DropOff_Longitude);

                //for Dropoff Address
                JSONObject jsonDropoffAddress = new JSONObject();
                jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
                jsonDropoffAddress.put("Address1", DropOff_Address1);
                jsonDropoffAddress.put("Address2", "");
                jsonDropoffAddress.put("City", DropOff_City);
                jsonDropoffAddress.put("Country", DropOff_Country);
                jsonDropoffAddress.put("Location", jsonDropOffLocation);
                jsonDropoffAddress.put("State", DropOff_State);
                jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

                JSONArray jsonCompletePickup= new JSONArray();
                jsonCompletePickup.put(jsonPickupAddress);
                JSONArray jsonCompleteDropOff= new JSONArray();
                jsonCompleteDropOff.put(jsonDropoffAddress);

                //final main json for request payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupAddress", jsonPickupAddress);
                jsonObj.put("DropOffAddress", jsonDropoffAddress);
                jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
                jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
                jsonObj.put("IsScheduledPickup", true);
                jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
                jsonObj.put("PickupRequestID",JSONObject.NULL);
                jsonObj.put("ServiceLevelRef", JSONObject.NULL);
                jsonObj.put("NoOfDrivers",No_of_Driver);
                jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);

                //Header header = new Header("AuthorizationToken", AccessToken);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
                //response.then().log().body();
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);
            }
            else{
                String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");
                String Load_Unload = PropertyUtility.getDataProperties("partner.load.unload.time");

                String Pickup_Address_Id = PropertyUtility.getDataProperties("partner.kansas.pickup_address_id");
                String Pickup_Address1 = PropertyUtility.getDataProperties("partner.kansas.pickup_address1");
                String Pickup_Address2 = PropertyUtility.getDataProperties("partner.kansas.pickup_address2");
                String Pickup_City = PropertyUtility.getDataProperties("partner.kansas.pickup_city");
                String Pickup_Country = PropertyUtility.getDataProperties("partner.kansas.pickup_country");
                String Pickup_Latitude = PropertyUtility.getDataProperties("partner.kansas.pickup_latitude");
                String Pickup_Longitude = PropertyUtility.getDataProperties("partner.kansas.pickup_longitude");
                String Pickup_State = PropertyUtility.getDataProperties("partner.kansas.pickup_state");
                String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.kansas.pickup_zippostalcode");

                String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.kansas.dropoff_address_id");
                String DropOff_Address1 = PropertyUtility.getDataProperties("partner.kansas.dropoff_address1");
                String DropOff_Address2 = PropertyUtility.getDataProperties("partner.kansas.dropoff_address2");
                String DropOff_City = PropertyUtility.getDataProperties("partner.kansas.dropoff_city");
                String DropOff_Country = PropertyUtility.getDataProperties("partner.kansas.dropoff_country");
                String DropOff_Latitude = PropertyUtility.getDataProperties("partner.kansas.dropoff_latitude");
                String DropOff_Longitude = PropertyUtility.getDataProperties("partner.kansas.dropoff_longitude");
                String DropOff_State = PropertyUtility.getDataProperties("partner.kansas.dropoff_state");
                String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.kansas.dropoff_zippostalcode");

                //for Pickup Location
                JSONObject jsonPickupLocation = new JSONObject();
                jsonPickupLocation.put("Latitude", Pickup_Latitude);
                jsonPickupLocation.put("Longitude", Pickup_Longitude);

                //JSONObject jsonPickup =  new JSONObject();
                //jsonPickup.put("Location",jsonPickupLocation);

                //for Pickup Address:
                JSONObject jsonPickupAddress = new JSONObject();
                jsonPickupAddress.put("AddressId", Pickup_Address_Id);
                jsonPickupAddress.put("Address1", Pickup_Address1);
                jsonPickupAddress.put("Address2", Pickup_Address2);
                jsonPickupAddress.put("City", Pickup_City);
                jsonPickupAddress.put("Country", Pickup_Country);
                jsonPickupAddress.put("Location", jsonPickupLocation);
                jsonPickupAddress.put("State", Pickup_State);
                jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);

                //for Dropoff location
                JSONObject jsonDropOffLocation = new JSONObject();
                jsonDropOffLocation.put("Latitude", DropOff_Latitude);
                jsonDropOffLocation.put("Longitude", DropOff_Longitude);

                //JSONArray jsonDrop=  new JSONArray();
                //jsonDrop.put(jsonDropOffLocation);

                //for Dropoff Address:
                JSONObject jsonDropoffAddress = new JSONObject();
                jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
                jsonDropoffAddress.put("Address1", DropOff_Address1);
                jsonDropoffAddress.put("Address2", DropOff_Address2);
                jsonDropoffAddress.put("City", DropOff_City);
                jsonDropoffAddress.put("Country", DropOff_Country);
                jsonDropoffAddress.put("Location", jsonDropOffLocation);
                jsonDropoffAddress.put("State", DropOff_State);
                jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

                JSONArray jsonCompletePickup = new JSONArray();
                jsonCompletePickup.put(jsonPickupAddress);
                JSONArray jsonCompleteDropOff = new JSONArray();
                jsonCompleteDropOff.put(jsonDropoffAddress);

                //final main json for request payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupAddress", jsonPickupAddress);
                jsonObj.put("DropOffAddress", jsonDropoffAddress);
                jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
                jsonObj.put("EstLoadUnloadTimeInMilliseconds", Load_Unload);
                jsonObj.put("IsScheduledPickup", true);
                //jsonObj.put("PartnerLocationConfigVersion", PartnerLocationConfigurationVersionRef);
                jsonObj.put("PickupRequestID", JSONObject.NULL);
                jsonObj.put("ServiceLevelRef", JSONObject.NULL);
                jsonObj.put("NoOfDrivers", No_of_Driver);
                jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID", BusinessPartnerDefaultAddressConfigVersionID);

                //Header header = new Header("AuthorizationToken", AccessToken);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
                // response.then().log().body();
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);
            }
        }
        else if(Geofence.equalsIgnoreCase("washingtondc")) {

                 String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

                String Pickup_Address1 = PropertyUtility.getDataProperties("partner.washingtondc.pickup_address1");
                String Pickup_City = PropertyUtility.getDataProperties("partner.washingtondc.pickup_city");
                String Pickup_Country = PropertyUtility.getDataProperties("partner.washingtondc.pickup_country");
                String Pickup_Latitude = PropertyUtility.getDataProperties("partner.washingtondc.pickup_latitude");
                String Pickup_Longitude = PropertyUtility.getDataProperties("partner.washingtondc.pickup_longitude");
                String Pickup_State = PropertyUtility.getDataProperties("partner.washingtondc.pickup_state");
                String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.washingtondc.pickup_zippostalcode");

                String ServiceLevelRef = PropertyUtility.getDataProperties("partner.washingtondc.service_level_ref");
                String PricingModelConfigVersionRef = PropertyUtility.getDataProperties("partner.washingtondc.pricing_model_ref");

                String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.washingtondc.dropoff_address_id");
                String DropOff_Address1 = PropertyUtility.getDataProperties("partner.washingtondc.dropoff_address1");
                String DropOff_City = PropertyUtility.getDataProperties("partner.washingtondc.dropoff_city");
                String DropOff_Country = PropertyUtility.getDataProperties("partner.washingtondc.dropoff_country");
                String DropOff_Latitude = PropertyUtility.getDataProperties("partner.washingtondc.dropoff_latitude");
                String DropOff_Longitude = PropertyUtility.getDataProperties("partner.washingtondc.dropoff_longitude");
                String DropOff_State = PropertyUtility.getDataProperties("partner.washingtondc.dropoff_state");
                String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.washingtondc.dropoff_zippostalcode");

                //for Pickup Location
                JSONObject jsonPickupLocation = new JSONObject();
                jsonPickupLocation.put("Latitude", Pickup_Latitude);
                jsonPickupLocation.put("Longitude", Pickup_Longitude);

                //for Default Feilds
                JSONArray DefaultFeilds = new JSONArray();

                //for Pickup Address
                JSONObject jsonPickupAddress = new JSONObject();
                jsonPickupAddress.put("Address1", Pickup_Address1);
                jsonPickupAddress.put("Address2", "");
                jsonPickupAddress.put("AddressLabel", "");
                jsonPickupAddress.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonPickupAddress.put("BusinessPartnerDefaultAddressConfigVersionID", BusinessPartnerDefaultAddressConfigVersionID);
                jsonPickupAddress.put("City", Pickup_City);
                jsonPickupAddress.put("Country", Pickup_Country);
                jsonPickupAddress.put("DefaultStaticFields", DefaultFeilds);
                jsonPickupAddress.put("IsDefault", true);
                jsonPickupAddress.put("Location", jsonPickupLocation);
                jsonPickupAddress.put("PickupInstructions", JSONObject.NULL);
                jsonPickupAddress.put("State", Pickup_State);
                jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);

                //for Dropoff location
                JSONObject jsonDropOffLocation = new JSONObject();
                jsonDropOffLocation.put("Latitude", DropOff_Latitude);
                jsonDropOffLocation.put("Longitude", DropOff_Longitude);

                //for Dropoff Address
                JSONObject jsonDropoffAddress = new JSONObject();
                jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
                jsonDropoffAddress.put("Address1", DropOff_Address1);
                jsonDropoffAddress.put("Address2", "");
                jsonDropoffAddress.put("City", DropOff_City);
                jsonDropoffAddress.put("Country", DropOff_Country);
                jsonDropoffAddress.put("Location", jsonDropOffLocation);
                jsonDropoffAddress.put("State", DropOff_State);
                jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

                JSONArray jsonCompletePickup = new JSONArray();
                jsonCompletePickup.put(jsonPickupAddress);
                JSONArray jsonCompleteDropOff = new JSONArray();
                jsonCompleteDropOff.put(jsonDropoffAddress);

                //for Service Level
                String apiServiceLevel = null;
                apiServiceLevel = UrlBuilder.createApiUrl("core", PARTNER_SERVICELEVEL);
                JSONObject jsonServiceLevel = new JSONObject();
                jsonServiceLevel.put("DropoffLocation", jsonDropOffLocation);
                jsonServiceLevel.put("NoOfDrivers", No_of_Driver);
                jsonServiceLevel.put("PickupLocation", jsonPickupLocation);
                jsonServiceLevel.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonServiceLevel.toString()).when().post(apiServiceLevel);//body(jsonObj.toString()).

                //final main json for request payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupAddress", jsonPickupAddress);
                jsonObj.put("DropOffAddress", jsonDropoffAddress);
                jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
                jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
                jsonObj.put("IsScheduledPickup", true);
                jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
                jsonObj.put("PickupRequestID", JSONObject.NULL);
                jsonObj.put("ServiceLevelRef", ServiceLevelRef);
                jsonObj.put("NoOfDrivers", No_of_Driver);
                jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID", BusinessPartnerDefaultAddressConfigVersionID);

                //Header header = new Header("AuthorizationToken", AccessToken);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
                //response.then().log().body();
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);
             }
        else if(Geofence.equalsIgnoreCase("baltimore")&& Partner_Portal.equalsIgnoreCase("BestBuy2 service level") ) {
            String expectedStoreAddress = (String) cucumberContextManager.getScenarioContext("StoreCity");
            String expectedWarehouseAddress = (String) cucumberContextManager.getScenarioContext("WarehouseCity");

            if(Partner_Portal.equalsIgnoreCase("BestBuy2 service level")&&expectedWarehouseAddress.contentEquals("Catonsville")) {

                String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

                String Pickup_Address1 = PropertyUtility.getDataProperties("partner.baltimore.warehouse.pickup_address1");
                String Pickup_City = PropertyUtility.getDataProperties("partner.baltimore.warehouse.pickup_city");
                String Pickup_Country = PropertyUtility.getDataProperties("partner.baltimore.warehouse.pickup_country");
                String Pickup_Latitude = PropertyUtility.getDataProperties("partner.baltimore.warehouse.pickup_latitude");
                String Pickup_Longitude = PropertyUtility.getDataProperties("partner.baltimore.warehouse.pickup_longitude");
                String Pickup_State = PropertyUtility.getDataProperties("partner.baltimore.warehouse.pickup_state");
                String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.baltimore.warehouse.pickup_zippostalcode");

                String ServiceLevelRef = PropertyUtility.getDataProperties("partner.baltimore.warehouse.service_level_ref");
                String PricingModelConfigVersionRef = PropertyUtility.getDataProperties("partner.baltimore.warehouse.pricing_model_ref");

                String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.baltimore.warehouse.dropoff_address_id");
                String DropOff_Address1 = PropertyUtility.getDataProperties("partner.baltimore.warehouse.dropoff_address1");
                String DropOff_City = PropertyUtility.getDataProperties("partner.baltimore.warehouse.dropoff_city");
                String DropOff_Country = PropertyUtility.getDataProperties("partner.baltimore.warehouse.dropoff_country");
                String DropOff_Latitude = PropertyUtility.getDataProperties("partner.baltimore.warehouse.dropoff_latitude");
                String DropOff_Longitude = PropertyUtility.getDataProperties("partner.baltimore.warehouse.dropoff_longitude");
                String DropOff_State = PropertyUtility.getDataProperties("partner.baltimore.warehouse.dropoff_state");
                String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.baltimore.warehouse.dropoff_zippostalcode");

                //for Pickup Location
                JSONObject jsonPickupLocation = new JSONObject();
                jsonPickupLocation.put("Latitude", Pickup_Latitude);
                jsonPickupLocation.put("Longitude", Pickup_Longitude);

                //for Default Feilds
                JSONArray DefaultFeilds = new JSONArray();

                //for Pickup Address
                JSONObject jsonPickupAddress = new JSONObject();
                jsonPickupAddress.put("Address1", Pickup_Address1);
                jsonPickupAddress.put("Address2", JSONObject.NULL);
                jsonPickupAddress.put("AddressLabel", "store address");
                jsonPickupAddress.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonPickupAddress.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);
                jsonPickupAddress.put("City", Pickup_City);
                jsonPickupAddress.put("Country", Pickup_Country);
                jsonPickupAddress.put("DefaultStaticFields", DefaultFeilds);
                jsonPickupAddress.put("IsDefault", true);
                jsonPickupAddress.put("Location", jsonPickupLocation);
                jsonPickupAddress.put("PickupInstructions",JSONObject.NULL );
                jsonPickupAddress.put("State", Pickup_State);
                jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);


                //for Dropoff location
                JSONObject jsonDropOffLocation = new JSONObject();
                jsonDropOffLocation.put("Latitude", DropOff_Latitude);
                jsonDropOffLocation.put("Longitude", DropOff_Longitude);

                //for Dropoff Address
                JSONObject jsonDropoffAddress = new JSONObject();
                jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
                jsonDropoffAddress.put("Address1", DropOff_Address1);
                jsonDropoffAddress.put("Address2", JSONObject.NULL);
                jsonDropoffAddress.put("City", DropOff_City);
                jsonDropoffAddress.put("Country", DropOff_Country);
                jsonDropoffAddress.put("Location", jsonDropOffLocation);
                jsonDropoffAddress.put("State", DropOff_State);
                jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

                JSONArray jsonCompletePickup= new JSONArray();
                jsonCompletePickup.put(jsonPickupAddress);
                JSONArray jsonCompleteDropOff= new JSONArray();
                jsonCompleteDropOff.put(jsonDropoffAddress);

                //for Service Level
                String apiServiceLevel = null;
                apiServiceLevel = UrlBuilder.createApiUrl("core",PARTNER_SERVICELEVEL);
                JSONObject jsonServiceLevel = new JSONObject();
                jsonServiceLevel.put("DropoffLocation", jsonDropOffLocation);
                jsonServiceLevel.put("NoOfDrivers",No_of_Driver);
                jsonServiceLevel.put("PickupLocation",jsonPickupLocation);
                jsonServiceLevel.put("PricingModelConfigVersionRef",PricingModelConfigVersionRef);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonServiceLevel.toString()).when().post(apiServiceLevel);//body(jsonObj.toString()).


                //final main json for request payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupAddress", jsonPickupAddress);
                jsonObj.put("DropOffAddress", jsonDropoffAddress);
                jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
                jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
                jsonObj.put("IsScheduledPickup", true);
                jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
                jsonObj.put("PickupRequestID",JSONObject.NULL);
                jsonObj.put("ServiceLevelRef", ServiceLevelRef);
                jsonObj.put("NoOfDrivers",No_of_Driver);
                jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);

                //Header header = new Header("AuthorizationToken", AccessToken);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
                //response.then().log().body();
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);
            }
            else if(Partner_Portal.equalsIgnoreCase("BestBuy2 service level")||expectedStoreAddress.contentEquals("MD")){
                String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

                String Pickup_Address1 = PropertyUtility.getDataProperties("partner.baltimore.pickup_address1");
                String Pickup_City = PropertyUtility.getDataProperties("partner.baltimore.pickup_city");
                String Pickup_Country = PropertyUtility.getDataProperties("partner.baltimore.pickup_country");
                String Pickup_Latitude = PropertyUtility.getDataProperties("partner.baltimore.pickup_latitude");
                String Pickup_Longitude = PropertyUtility.getDataProperties("partner.baltimore.pickup_longitude");
                String Pickup_State = PropertyUtility.getDataProperties("partner.baltimore.pickup_state");
                String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.baltimore.pickup_zippostalcode");

                String ServiceLevelRef = PropertyUtility.getDataProperties("partner.baltimore.service_level_ref");
                String PricingModelConfigVersionRef = PropertyUtility.getDataProperties("partner.baltimore.pricing_model_ref");

                String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.baltimore.dropoff_address_id");
                String DropOff_Address1 = PropertyUtility.getDataProperties("partner.baltimore.dropoff_address1");
                String DropOff_City = PropertyUtility.getDataProperties("partner.baltimore.dropoff_city");
                String DropOff_Country = PropertyUtility.getDataProperties("partner.baltimore.dropoff_country");
                String DropOff_Latitude = PropertyUtility.getDataProperties("partner.baltimore.dropoff_latitude");
                String DropOff_Longitude = PropertyUtility.getDataProperties("partner.baltimore.dropoff_longitude");
                String DropOff_State = PropertyUtility.getDataProperties("partner.baltimore.dropoff_state");
                String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.baltimore.dropoff_zippostalcode");

                //for Pickup Location
                JSONObject jsonPickupLocation = new JSONObject();
                jsonPickupLocation.put("Latitude", Pickup_Latitude);
                jsonPickupLocation.put("Longitude", Pickup_Longitude);

                //for Default Feilds
                JSONArray DefaultFeilds = new JSONArray();

                //for Pickup Address
                JSONObject jsonPickupAddress = new JSONObject();
                jsonPickupAddress.put("Address1", Pickup_Address1);
                jsonPickupAddress.put("Address2", JSONObject.NULL);
                jsonPickupAddress.put("AddressLabel", "store address");
                jsonPickupAddress.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonPickupAddress.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);
                jsonPickupAddress.put("City", Pickup_City);
                jsonPickupAddress.put("Country", Pickup_Country);
                jsonPickupAddress.put("DefaultStaticFields", DefaultFeilds);
                jsonPickupAddress.put("IsDefault", true);
                jsonPickupAddress.put("Location", jsonPickupLocation);
                jsonPickupAddress.put("PickupInstructions",JSONObject.NULL );
                jsonPickupAddress.put("State", Pickup_State);
                jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);


                //for Dropoff location
                JSONObject jsonDropOffLocation = new JSONObject();
                jsonDropOffLocation.put("Latitude", DropOff_Latitude);
                jsonDropOffLocation.put("Longitude", DropOff_Longitude);

                //for Dropoff Address
                JSONObject jsonDropoffAddress = new JSONObject();
                jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
                jsonDropoffAddress.put("Address1", DropOff_Address1);
                jsonDropoffAddress.put("Address2", JSONObject.NULL);
                jsonDropoffAddress.put("City", DropOff_City);
                jsonDropoffAddress.put("Country", DropOff_Country);
                jsonDropoffAddress.put("Location", jsonDropOffLocation);
                jsonDropoffAddress.put("State", DropOff_State);
                jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

                JSONArray jsonCompletePickup= new JSONArray();
                jsonCompletePickup.put(jsonPickupAddress);
                JSONArray jsonCompleteDropOff= new JSONArray();
                jsonCompleteDropOff.put(jsonDropoffAddress);

                //for Service Level
                String apiServiceLevel = null;
                apiServiceLevel = UrlBuilder.createApiUrl("core",PARTNER_SERVICELEVEL);
                JSONObject jsonServiceLevel = new JSONObject();
                jsonServiceLevel.put("DropoffLocation", jsonDropOffLocation);
                jsonServiceLevel.put("NoOfDrivers",No_of_Driver);
                jsonServiceLevel.put("PickupLocation",jsonPickupLocation);
                jsonServiceLevel.put("PricingModelConfigVersionRef",PricingModelConfigVersionRef);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonServiceLevel.toString()).when().post(apiServiceLevel);//body(jsonObj.toString()).


                //final main json for request payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupAddress", jsonPickupAddress);
                jsonObj.put("DropOffAddress", jsonDropoffAddress);
                jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
                jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
                jsonObj.put("IsScheduledPickup", true);
                jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
                jsonObj.put("PickupRequestID",JSONObject.NULL);
                jsonObj.put("ServiceLevelRef", ServiceLevelRef);
                jsonObj.put("NoOfDrivers",No_of_Driver);
                jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);

                //Header header = new Header("AuthorizationToken", AccessToken);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
                //response.then().log().body();
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);
            }

        }
        else if(Geofence.equalsIgnoreCase("atlanta")){

            if( Partner_Portal.equalsIgnoreCase("biglots")) {
             String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

            String Pickup_Address_Id = PropertyUtility.getDataProperties("partner.biglots.pickup_address_id");
            String Pickup_Address1 = PropertyUtility.getDataProperties("partner.biglots.pickup_address1");
            String Pickup_City = PropertyUtility.getDataProperties("partner.biglots.pickup_city");
            String Pickup_Country = PropertyUtility.getDataProperties("partner.biglots.pickup_country");
            String Pickup_Latitude = PropertyUtility.getDataProperties("partner.biglots.pickup_latitude");
            String Pickup_Longitude = PropertyUtility.getDataProperties("partner.biglots.pickup_longitude");
            String Pickup_State = PropertyUtility.getDataProperties("partner.biglots.pickup_state");
            String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.biglots.pickup_zippostalcode");

            String ServiceLevelRef = PropertyUtility.getDataProperties("partner.biglots.service_level_ref");
            String PricingModelConfigVersionRef=PropertyUtility.getDataProperties("partner.biglots.pricing_model_ref");

            String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.biglots.dropoff_address_id");
            String DropOff_Address1 = PropertyUtility.getDataProperties("partner.biglots.dropoff_address1");
            String DropOff_City = PropertyUtility.getDataProperties("partner.biglots.dropoff_city");
            String DropOff_Country = PropertyUtility.getDataProperties("partner.biglots.dropoff_country");
            String DropOff_Latitude = PropertyUtility.getDataProperties("partner.biglots.dropoff_latitude");
            String DropOff_Longitude = PropertyUtility.getDataProperties("partner.biglots.dropoff_longitude");
            String DropOff_State = PropertyUtility.getDataProperties("partner.biglots.dropoff_state");
            String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.biglots.dropoff_zippostalcode");

            //for Pickup Location
            JSONObject jsonPickupLocation = new JSONObject();
            jsonPickupLocation.put("Latitude", Pickup_Latitude);
            jsonPickupLocation.put("Longitude", Pickup_Longitude);


            //for Pickup Address
            JSONObject jsonPickupAddress = new JSONObject();
            jsonPickupAddress.put("Address1", Pickup_Address1);
            jsonPickupAddress.put("Address2", "");
            jsonPickupAddress.put("AddressId", Pickup_Address_Id);
            jsonPickupAddress.put("City", Pickup_City);
            jsonPickupAddress.put("Country", Pickup_Country);
            jsonPickupAddress.put("Location", jsonPickupLocation);
            jsonPickupAddress.put("State", Pickup_State);
            jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);


            //for Dropoff location
            JSONObject jsonDropOffLocation = new JSONObject();
            jsonDropOffLocation.put("Latitude", DropOff_Latitude);
            jsonDropOffLocation.put("Longitude", DropOff_Longitude);

            //for Dropoff Address
            JSONObject jsonDropoffAddress = new JSONObject();
            jsonDropoffAddress.put("Address1", DropOff_Address1);
            jsonDropoffAddress.put("Address2", "");
            jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
            jsonDropoffAddress.put("City", DropOff_City);
            jsonDropoffAddress.put("Country", DropOff_Country);
            jsonDropoffAddress.put("Location", jsonDropOffLocation);
            jsonDropoffAddress.put("State", DropOff_State);
            jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

            JSONArray jsonCompletePickup= new JSONArray();
            jsonCompletePickup.put(jsonPickupAddress);
            JSONArray jsonCompleteDropOff= new JSONArray();
            jsonCompleteDropOff.put(jsonDropoffAddress);

            //for Service Level
            String apiServiceLevel = null;
            apiServiceLevel = UrlBuilder.createApiUrl("core",PARTNER_SERVICELEVEL);
            JSONObject jsonServiceLevel = new JSONObject();
            jsonServiceLevel.put("DropoffLocation", jsonDropOffLocation);
            jsonServiceLevel.put("NoOfDrivers",No_of_Driver);
            jsonServiceLevel.put("PickupLocation",jsonPickupLocation);
            jsonServiceLevel.put("PricingModelConfigVersionRef",PricingModelConfigVersionRef);
            response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonServiceLevel.toString()).when().post(apiServiceLevel);//body(jsonObj.toString()).


            //final main json for request payload
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupAddress", jsonPickupAddress);
            jsonObj.put("DropOffAddress", jsonDropoffAddress);
            jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
            jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
            jsonObj.put("IsScheduledPickup", true);
            jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
            jsonObj.put("PickupRequestID",JSONObject.NULL);
            jsonObj.put("ServiceLevelRef", ServiceLevelRef);
            jsonObj.put("NoOfDrivers",No_of_Driver);
            jsonObj.put("BusinessPartnerDefaultAddressRef", JSONObject.NULL);
            jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID",JSONObject.NULL);

            //Header header = new Header("AuthorizationToken", AccessToken);
            response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
            //response.then().log().body();
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);
            }
            else if( Partner_Portal.equalsIgnoreCase("Floor and Decor 106")) {
                String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

                String Pickup_Address1 = PropertyUtility.getDataProperties("partner.floor.and.decor.106.pickup_address1");
                String Pickup_City = PropertyUtility.getDataProperties("partner.floor.and.decor.106.pickup_city");
                String Pickup_Country = PropertyUtility.getDataProperties("partner.floor.and.decor.106.pickup_country");
                String Pickup_Latitude = PropertyUtility.getDataProperties("partner.floor.and.decor.106.pickup_latitude");
                String Pickup_Longitude = PropertyUtility.getDataProperties("partner.floor.and.decor.106.pickup_longitude");
                String Pickup_State = PropertyUtility.getDataProperties("partner.floor.and.decor.106.pickup_state");
                String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.floor.and.decor.106.pickup_zippostalcode");

                String ServiceLevelRef = PropertyUtility.getDataProperties("partner.floor.and.decor.106.service_level_ref");
                String PricingModelConfigVersionRef=PropertyUtility.getDataProperties("partner.floor.and.decor.106.pricing_model_ref");

                String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.floor.and.decor.106.dropoff_address_id");
                String DropOff_Address1 = PropertyUtility.getDataProperties("partner.floor.and.decor.106.dropoff_address1");
                String DropOff_City = PropertyUtility.getDataProperties("partner.floor.and.decor.106.dropoff_city");
                String DropOff_Country = PropertyUtility.getDataProperties("partner.floor.and.decor.106.dropoff_country");
                String DropOff_Latitude = PropertyUtility.getDataProperties("partner.floor.and.decor.106.dropoff_latitude");
                String DropOff_Longitude = PropertyUtility.getDataProperties("partner.floor.and.decor.106.dropoff_longitude");
                String DropOff_State = PropertyUtility.getDataProperties("partner.floor.and.decor.106.dropoff_state");
                String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.floor.and.decor.106.dropoff_zippostalcode");

                //for Pickup Location
                JSONObject jsonPickupLocation = new JSONObject();
                jsonPickupLocation.put("Latitude", Pickup_Latitude);
                jsonPickupLocation.put("Longitude", Pickup_Longitude);

                //for Default Feilds
                JSONArray DefaultFeilds = new JSONArray();

                //for Pickup Address
                JSONObject jsonPickupAddress = new JSONObject();
                jsonPickupAddress.put("Address1", Pickup_Address1);
                jsonPickupAddress.put("Address2", JSONObject.NULL);
                jsonPickupAddress.put("AddressLabel", "store address");
                jsonPickupAddress.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonPickupAddress.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);
                jsonPickupAddress.put("City", Pickup_City);
                jsonPickupAddress.put("Country", Pickup_Country);
                jsonPickupAddress.put("DefaultStaticFields", DefaultFeilds);
                jsonPickupAddress.put("IsDefault", true);
                jsonPickupAddress.put("Location", jsonPickupLocation);
                jsonPickupAddress.put("PickupInstructions",JSONObject.NULL );
                jsonPickupAddress.put("State", Pickup_State);
                jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);

                //for Dropoff location
                JSONObject jsonDropOffLocation = new JSONObject();
                jsonDropOffLocation.put("Latitude", DropOff_Latitude);
                jsonDropOffLocation.put("Longitude", DropOff_Longitude);

                //for Dropoff Address
                JSONObject jsonDropoffAddress = new JSONObject();
                jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
                jsonDropoffAddress.put("Address1", DropOff_Address1);
                jsonDropoffAddress.put("Address2", JSONObject.NULL);
                jsonDropoffAddress.put("City", DropOff_City);
                jsonDropoffAddress.put("Country", DropOff_Country);
                jsonDropoffAddress.put("Location", jsonDropOffLocation);
                jsonDropoffAddress.put("State", DropOff_State);
                jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

                JSONArray jsonCompletePickup= new JSONArray();
                jsonCompletePickup.put(jsonPickupAddress);
                JSONArray jsonCompleteDropOff= new JSONArray();
                jsonCompleteDropOff.put(jsonDropoffAddress);

                //for Service Level
                String apiServiceLevel = null;
                apiServiceLevel = UrlBuilder.createApiUrl("core",PARTNER_SERVICELEVEL);
                JSONObject jsonServiceLevel = new JSONObject();
                jsonServiceLevel.put("DropoffLocation", jsonDropOffLocation);
                jsonServiceLevel.put("NoOfDrivers",No_of_Driver);
                jsonServiceLevel.put("PickupLocation",jsonPickupLocation);
                jsonServiceLevel.put("PricingModelConfigVersionRef",PricingModelConfigVersionRef);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonServiceLevel.toString()).when().post(apiServiceLevel);//body(jsonObj.toString()).

                //final main json for request payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupAddress", jsonPickupAddress);
                jsonObj.put("DropOffAddress", jsonDropoffAddress);
                jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
                jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
                jsonObj.put("IsScheduledPickup", true);
                jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
                jsonObj.put("PickupRequestID",JSONObject.NULL);
                jsonObj.put("ServiceLevelRef", ServiceLevelRef);
                jsonObj.put("NoOfDrivers",No_of_Driver);
                jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);

                //Header header = new Header("AuthorizationToken", AccessToken);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
                //response.then().log().body();
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);

            }
            else if(Partner_Portal.equalsIgnoreCase("Cort Furniture")) {

                String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

                String Pickup_Address1 = PropertyUtility.getDataProperties("partner.cort.pickup_address1");
                String Pickup_City = PropertyUtility.getDataProperties("partner.cort.pickup_city");
                String Pickup_Country = PropertyUtility.getDataProperties("partner.cort.pickup_country");
                String Pickup_Latitude = PropertyUtility.getDataProperties("partner.cort.pickup_latitude");
                String Pickup_Longitude = PropertyUtility.getDataProperties("partner.cort.pickup_longitude");
                String Pickup_State = PropertyUtility.getDataProperties("partner.cort.pickup_state");
                String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.cort.pickup_zippostalcode");

                String ServiceLevelRef = PropertyUtility.getDataProperties("partner.cort.service_level_ref");
                String PricingModelConfigVersionRef=PropertyUtility.getDataProperties("partner.cort.pricing_model_ref");

                String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.cort.dropoff_address_id");
                String DropOff_Address1 = PropertyUtility.getDataProperties("partner.cort.dropoff_address1");
                String DropOff_City = PropertyUtility.getDataProperties("partner.cort.dropoff_city");
                String DropOff_Country = PropertyUtility.getDataProperties("partner.cort.dropoff_country");
                String DropOff_Latitude = PropertyUtility.getDataProperties("partner.cort.dropoff_latitude");
                String DropOff_Longitude = PropertyUtility.getDataProperties("partner.cort.dropoff_longitude");
                String DropOff_State = PropertyUtility.getDataProperties("partner.cort.dropoff_state");
                String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.cort.dropoff_zippostalcode");

                //for Pickup Location
                JSONObject jsonPickupLocation = new JSONObject();
                jsonPickupLocation.put("Latitude", Pickup_Latitude);
                jsonPickupLocation.put("Longitude", Pickup_Longitude);

                //for Default Feilds
                JSONArray DefaultFeilds = new JSONArray();

                //for Pickup Address
                JSONObject jsonPickupAddress = new JSONObject();
                jsonPickupAddress.put("Address1", Pickup_Address1);
                jsonPickupAddress.put("Address2", "");
                jsonPickupAddress.put("AddressLabel", "");
                jsonPickupAddress.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonPickupAddress.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);
                jsonPickupAddress.put("City", Pickup_City);
                jsonPickupAddress.put("Country", Pickup_Country);
                jsonPickupAddress.put("DefaultStaticFields", DefaultFeilds);
                jsonPickupAddress.put("IsDefault", true);
                jsonPickupAddress.put("Location", jsonPickupLocation);
                jsonPickupAddress.put("PickupInstructions",JSONObject.NULL );
                jsonPickupAddress.put("State", Pickup_State);
                jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);


                //for Dropoff location
                JSONObject jsonDropOffLocation = new JSONObject();
                jsonDropOffLocation.put("Latitude", DropOff_Latitude);
                jsonDropOffLocation.put("Longitude", DropOff_Longitude);

                //for Dropoff Address
                JSONObject jsonDropoffAddress = new JSONObject();
                jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
                jsonDropoffAddress.put("Address1", DropOff_Address1);
                jsonDropoffAddress.put("Address2", "");
                jsonDropoffAddress.put("City", DropOff_City);
                jsonDropoffAddress.put("Country", DropOff_Country);
                jsonDropoffAddress.put("Location", jsonDropOffLocation);
                jsonDropoffAddress.put("State", DropOff_State);
                jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

                JSONArray jsonCompletePickup= new JSONArray();
                jsonCompletePickup.put(jsonPickupAddress);
                JSONArray jsonCompleteDropOff= new JSONArray();
                jsonCompleteDropOff.put(jsonDropoffAddress);

                //for Service Level
                String apiServiceLevel = null;
                apiServiceLevel = UrlBuilder.createApiUrl("core",PARTNER_SERVICELEVEL);
                JSONObject jsonServiceLevel = new JSONObject();
                jsonServiceLevel.put("DropoffLocation", jsonDropOffLocation);
                jsonServiceLevel.put("NoOfDrivers",No_of_Driver);
                jsonServiceLevel.put("PickupLocation",jsonPickupLocation);
                jsonServiceLevel.put("PricingModelConfigVersionRef",PricingModelConfigVersionRef);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonServiceLevel.toString()).when().post(apiServiceLevel);//body(jsonObj.toString()).


                //final main json for request payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupAddress", jsonPickupAddress);
                jsonObj.put("DropOffAddress", jsonDropoffAddress);
                jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
                jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
                jsonObj.put("IsScheduledPickup", true);
                jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
                jsonObj.put("PickupRequestID",JSONObject.NULL);
                jsonObj.put("ServiceLevelRef", ServiceLevelRef);
                jsonObj.put("NoOfDrivers",No_of_Driver);
                jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);

                //Header header = new Header("AuthorizationToken", AccessToken);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
//            response.then().log().body();
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);
            }
            else if( Partner_Portal.equalsIgnoreCase("Floor and Decor bos")) {
                String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

                String Pickup_Address1 = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.pickup_address1");
                String Pickup_City = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.pickup_city");
                String Pickup_Country = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.pickup_country");
                String Pickup_Latitude = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.pickup_latitude");
                String Pickup_Longitude = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.pickup_longitude");
                String Pickup_State = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.pickup_state");
                String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.pickup_zippostalcode");

                String ServiceLevelRef = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.service_level_ref");
                String PricingModelConfigVersionRef=PropertyUtility.getDataProperties("partner.floor.and.decor.bos.pricing_model_ref");

                String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.dropoff_address_id");
                String DropOff_Address1 = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.dropoff_address1");
                String DropOff_City = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.dropoff_city");
                String DropOff_Country = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.dropoff_country");
                String DropOff_Latitude = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.dropoff_latitude");
                String DropOff_Longitude = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.dropoff_longitude");
                String DropOff_State = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.dropoff_state");
                String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.floor.and.decor.bos.dropoff_zippostalcode");

                //for Pickup Location
                JSONObject jsonPickupLocation = new JSONObject();
                jsonPickupLocation.put("Latitude", Pickup_Latitude);
                jsonPickupLocation.put("Longitude", Pickup_Longitude);

                //for Default Feilds
                JSONArray DefaultFeilds = new JSONArray();

                //for Pickup Address
                JSONObject jsonPickupAddress = new JSONObject();
                jsonPickupAddress.put("Address1", Pickup_Address1);
                jsonPickupAddress.put("Address2", JSONObject.NULL);
                jsonPickupAddress.put("AddressLabel", "store address");
                jsonPickupAddress.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonPickupAddress.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);
                jsonPickupAddress.put("City", Pickup_City);
                jsonPickupAddress.put("Country", Pickup_Country);
                jsonPickupAddress.put("DefaultStaticFields", DefaultFeilds);
                jsonPickupAddress.put("IsDefault", true);
                jsonPickupAddress.put("Location", jsonPickupLocation);
                jsonPickupAddress.put("PickupInstructions",JSONObject.NULL );
                jsonPickupAddress.put("State", Pickup_State);
                jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);

                //for Dropoff location
                JSONObject jsonDropOffLocation = new JSONObject();
                jsonDropOffLocation.put("Latitude", DropOff_Latitude);
                jsonDropOffLocation.put("Longitude", DropOff_Longitude);

                //for Dropoff Address
                JSONObject jsonDropoffAddress = new JSONObject();
                jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
                jsonDropoffAddress.put("Address1", DropOff_Address1);
                jsonDropoffAddress.put("Address2", JSONObject.NULL);
                jsonDropoffAddress.put("City", DropOff_City);
                jsonDropoffAddress.put("Country", DropOff_Country);
                jsonDropoffAddress.put("Location", jsonDropOffLocation);
                jsonDropoffAddress.put("State", DropOff_State);
                jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

                JSONArray jsonCompletePickup= new JSONArray();
                jsonCompletePickup.put(jsonPickupAddress);
                JSONArray jsonCompleteDropOff= new JSONArray();
                jsonCompleteDropOff.put(jsonDropoffAddress);

                //for Service Level
                String apiServiceLevel = null;
                apiServiceLevel = UrlBuilder.createApiUrl("core",PARTNER_SERVICELEVEL);
                JSONObject jsonServiceLevel = new JSONObject();
                jsonServiceLevel.put("DropoffLocation", jsonDropOffLocation);
                jsonServiceLevel.put("NoOfDrivers",No_of_Driver);
                jsonServiceLevel.put("PickupLocation",jsonPickupLocation);
                jsonServiceLevel.put("PricingModelConfigVersionRef",PricingModelConfigVersionRef);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonServiceLevel.toString()).when().post(apiServiceLevel);//body(jsonObj.toString()).

                //final main json for request payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupAddress", jsonPickupAddress);
                jsonObj.put("DropOffAddress", jsonDropoffAddress);
                jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
                jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
                jsonObj.put("IsScheduledPickup", true);
                jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
                jsonObj.put("PickupRequestID",JSONObject.NULL);
                jsonObj.put("ServiceLevelRef", ServiceLevelRef);
                jsonObj.put("NoOfDrivers",No_of_Driver);
                jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);

                //Header header = new Header("AuthorizationToken", AccessToken);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
                //response.then().log().body();
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);

            }

        }
        else if(Geofence.equalsIgnoreCase("nashville")) {

            String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

            String Pickup_Address1 = PropertyUtility.getDataProperties("partner.tileshop.pickup_address1");
            String Pickup_City = PropertyUtility.getDataProperties("partner.tileshop.pickup_city");
            String Pickup_Country = PropertyUtility.getDataProperties("partner.tileshop.pickup_country");
            String Pickup_Latitude = PropertyUtility.getDataProperties("partner.tileshop.pickup_latitude");
            String Pickup_Longitude = PropertyUtility.getDataProperties("partner.tileshop.pickup_longitude");
            String Pickup_State = PropertyUtility.getDataProperties("partner.tileshop.pickup_state");
            String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.tileshop.pickup_zippostalcode");

            String ServiceLevelRef = PropertyUtility.getDataProperties("partner.tileshop.service_level_ref");
            String PricingModelConfigVersionRef=PropertyUtility.getDataProperties("partner.tileshop.pricing_model_ref");

            String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.tileshop.dropoff_address_id");
            String DropOff_Address1 = PropertyUtility.getDataProperties("partner.tileshop.dropoff_address1");
            String DropOff_City = PropertyUtility.getDataProperties("partner.tileshop.dropoff_city");
            String DropOff_Country = PropertyUtility.getDataProperties("partner.tileshop.dropoff_country");
            String DropOff_Latitude = PropertyUtility.getDataProperties("partner.tileshop.dropoff_latitude");
            String DropOff_Longitude = PropertyUtility.getDataProperties("partner.tileshop.dropoff_longitude");
            String DropOff_State = PropertyUtility.getDataProperties("partner.tileshop.dropoff_state");
            String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.tileshop.dropoff_zippostalcode");

            //for Pickup Location
            JSONObject jsonPickupLocation = new JSONObject();
            jsonPickupLocation.put("Latitude", Pickup_Latitude);
            jsonPickupLocation.put("Longitude", Pickup_Longitude);

            //for Default Feilds
            JSONArray DefaultFeilds = new JSONArray();

            //for Pickup Address
            JSONObject jsonPickupAddress = new JSONObject();
            jsonPickupAddress.put("Address1", Pickup_Address1);
            jsonPickupAddress.put("Address2", "");
            jsonPickupAddress.put("AddressLabel", "");
            jsonPickupAddress.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
            jsonPickupAddress.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);
            jsonPickupAddress.put("City", Pickup_City);
            jsonPickupAddress.put("Country", Pickup_Country);
            jsonPickupAddress.put("DefaultStaticFields", DefaultFeilds);
            jsonPickupAddress.put("IsDefault", true);
            jsonPickupAddress.put("Location", jsonPickupLocation);
            jsonPickupAddress.put("PickupInstructions",JSONObject.NULL );
            jsonPickupAddress.put("State", Pickup_State);
            jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);


            //for Dropoff location
            JSONObject jsonDropOffLocation = new JSONObject();
            jsonDropOffLocation.put("Latitude", DropOff_Latitude);
            jsonDropOffLocation.put("Longitude", DropOff_Longitude);

            //for Dropoff Address
            JSONObject jsonDropoffAddress = new JSONObject();
            jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
            jsonDropoffAddress.put("Address1", DropOff_Address1);
            jsonDropoffAddress.put("Address2", "");
            jsonDropoffAddress.put("City", DropOff_City);
            jsonDropoffAddress.put("Country", DropOff_Country);
            jsonDropoffAddress.put("Location", jsonDropOffLocation);
            jsonDropoffAddress.put("State", DropOff_State);
            jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

            JSONArray jsonCompletePickup= new JSONArray();
            jsonCompletePickup.put(jsonPickupAddress);
            JSONArray jsonCompleteDropOff= new JSONArray();
            jsonCompleteDropOff.put(jsonDropoffAddress);

            //for Service Level
            String apiServiceLevel = null;
            apiServiceLevel = UrlBuilder.createApiUrl("core",PARTNER_SERVICELEVEL);
            JSONObject jsonServiceLevel = new JSONObject();
            jsonServiceLevel.put("DropoffLocation", jsonDropOffLocation);
            jsonServiceLevel.put("NoOfDrivers",No_of_Driver);
            jsonServiceLevel.put("PickupLocation",jsonPickupLocation);
            jsonServiceLevel.put("PricingModelConfigVersionRef",PricingModelConfigVersionRef);
            response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonServiceLevel.toString()).when().post(apiServiceLevel);//body(jsonObj.toString()).


            //final main json for request payload
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupAddress", jsonPickupAddress);
            jsonObj.put("DropOffAddress", jsonDropoffAddress);
            jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
            jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
            jsonObj.put("IsScheduledPickup", true);
            jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
            jsonObj.put("PickupRequestID",JSONObject.NULL);
            jsonObj.put("ServiceLevelRef", ServiceLevelRef);
            jsonObj.put("NoOfDrivers",No_of_Driver);
            jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
            jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);

            //Header header = new Header("AuthorizationToken", AccessToken);
            response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
            //response.then().log().body();
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);
        }
        else if(Partner_Portal.equalsIgnoreCase("Equip-bid")) {
            String expectedGeofence = (String) cucumberContextManager.getScenarioContext("PhoenixEquip-bid");

            if(expectedGeofence.equalsIgnoreCase("phoenix")){
                String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

                String Pickup_Address1 = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.pickup_address1");
                String Pickup_City = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.pickup_city");
                String Pickup_Country = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.pickup_country");
                String Pickup_Latitude = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.pickup_latitude");
                String Pickup_Longitude = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.pickup_longitude");
                String Pickup_State = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.pickup_state");
                String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.pickup_zippostalcode");

                String ServiceLevelRef = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.service_level_ref");
                String PricingModelConfigVersionRef = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.pricing_model_ref");

                String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.dropoff_address_id");
                String DropOff_Address1 = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.dropoff_address1");
                String DropOff_City = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.dropoff_city");
                String DropOff_Country = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.dropoff_country");
                String DropOff_Latitude = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.dropoff_latitude");
                String DropOff_Longitude = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.dropoff_longitude");
                String DropOff_State = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.dropoff_state");
                String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.equip-bid.phoenix.dropoff_zippostalcode");

                //for Pickup Location
                JSONObject jsonPickupLocation = new JSONObject();
                jsonPickupLocation.put("Latitude", Pickup_Latitude);
                jsonPickupLocation.put("Longitude", Pickup_Longitude);

                //for Default Feilds
                JSONArray DefaultFeilds = new JSONArray();

                //for Pickup Address
                JSONObject jsonPickupAddress = new JSONObject();
                jsonPickupAddress.put("Address1", Pickup_Address1);
                jsonPickupAddress.put("Address2", "");
                jsonPickupAddress.put("AddressLabel", "");
                jsonPickupAddress.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonPickupAddress.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);
                jsonPickupAddress.put("City", Pickup_City);
                jsonPickupAddress.put("Country", Pickup_Country);
                jsonPickupAddress.put("DefaultStaticFields", DefaultFeilds);
                jsonPickupAddress.put("IsDefault", true);
                jsonPickupAddress.put("Location", jsonPickupLocation);
                jsonPickupAddress.put("PickupInstructions",JSONObject.NULL );
                jsonPickupAddress.put("State", Pickup_State);
                jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);


                //for Dropoff location
                JSONObject jsonDropOffLocation = new JSONObject();
                jsonDropOffLocation.put("Latitude", DropOff_Latitude);
                jsonDropOffLocation.put("Longitude", DropOff_Longitude);

                //for Dropoff Address
                JSONObject jsonDropoffAddress = new JSONObject();
                jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
                jsonDropoffAddress.put("Address1", DropOff_Address1);
                jsonDropoffAddress.put("Address2", "");
                jsonDropoffAddress.put("City", DropOff_City);
                jsonDropoffAddress.put("Country", DropOff_Country);
                jsonDropoffAddress.put("Location", jsonDropOffLocation);
                jsonDropoffAddress.put("State", DropOff_State);
                jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

                JSONArray jsonCompletePickup= new JSONArray();
                jsonCompletePickup.put(jsonPickupAddress);
                JSONArray jsonCompleteDropOff= new JSONArray();
                jsonCompleteDropOff.put(jsonDropoffAddress);

                //final main json for request payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupAddress", jsonPickupAddress);
                jsonObj.put("DropOffAddress", jsonDropoffAddress);
                jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
                jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
                jsonObj.put("IsScheduledPickup", true);
                jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
                jsonObj.put("PickupRequestID",JSONObject.NULL);
                jsonObj.put("ServiceLevelRef", JSONObject.NULL);
                jsonObj.put("NoOfDrivers",No_of_Driver);
                jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);

                //Header header = new Header("AuthorizationToken", AccessToken);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
                //response.then().log().body();
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);
            }
        }
        else if(Geofence.equalsIgnoreCase("memphis")) {

            if( Partner_Portal.equalsIgnoreCase("Cort Furniture")) {
                String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

                String Pickup_Address1 = PropertyUtility.getDataProperties("partner.cort.memphis.pickup_address1");
                String Pickup_City = PropertyUtility.getDataProperties("partner.cort.memphis.pickup_city");
                String Pickup_Country = PropertyUtility.getDataProperties("partner.cort.memphis.pickup_country");
                String Pickup_Latitude = PropertyUtility.getDataProperties("partner.cort.memphis.pickup_latitude");
                String Pickup_Longitude = PropertyUtility.getDataProperties("partner.cort.memphis.pickup_longitude");
                String Pickup_State = PropertyUtility.getDataProperties("partner.cort.memphis.pickup_state");
                String Pickup_ZipPostalCode = PropertyUtility.getDataProperties("partner.cort.memphis.pickup_zippostalcode");

                String ServiceLevelRef = PropertyUtility.getDataProperties("partner.cort.memphis.service_level_ref");
                String PricingModelConfigVersionRef=PropertyUtility.getDataProperties("partner.cort.memphis.pricing_model_ref");

                String DropOff_Address_Id = PropertyUtility.getDataProperties("partner.cort.memphis.dropoff_address_id");
                String DropOff_Address1 = PropertyUtility.getDataProperties("partner.cort.memphis.dropoff_address1");
                String DropOff_City = PropertyUtility.getDataProperties("partner.cort.memphis.dropoff_city");
                String DropOff_Country = PropertyUtility.getDataProperties("partner.cort.memphis.dropoff_country");
                String DropOff_Latitude = PropertyUtility.getDataProperties("partner.cort.memphis.dropoff_latitude");
                String DropOff_Longitude = PropertyUtility.getDataProperties("partner.cort.memphis.dropoff_longitude");
                String DropOff_State = PropertyUtility.getDataProperties("partner.cort.memphis.dropoff_state");
                String DropOff_ZipPostalCode = PropertyUtility.getDataProperties("partner.cort.memphis.dropoff_zippostalcode");

                //for Pickup Location
                JSONObject jsonPickupLocation = new JSONObject();
                jsonPickupLocation.put("Latitude", Pickup_Latitude);
                jsonPickupLocation.put("Longitude", Pickup_Longitude);

                //for Default Feilds
                JSONArray DefaultFeilds = new JSONArray();

                //for Pickup Address
                JSONObject jsonPickupAddress = new JSONObject();
                jsonPickupAddress.put("Address1", Pickup_Address1);
                jsonPickupAddress.put("Address2", "");
                jsonPickupAddress.put("AddressLabel", "");
                jsonPickupAddress.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonPickupAddress.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);
                jsonPickupAddress.put("City", Pickup_City);
                jsonPickupAddress.put("Country", Pickup_Country);
                jsonPickupAddress.put("DefaultStaticFields", DefaultFeilds);
                jsonPickupAddress.put("IsDefault", true);
                jsonPickupAddress.put("Location", jsonPickupLocation);
                jsonPickupAddress.put("PickupInstructions",JSONObject.NULL );
                jsonPickupAddress.put("State", Pickup_State);
                jsonPickupAddress.put("ZipPostalCode", Pickup_ZipPostalCode);


                //for Dropoff location
                JSONObject jsonDropOffLocation = new JSONObject();
                jsonDropOffLocation.put("Latitude", DropOff_Latitude);
                jsonDropOffLocation.put("Longitude", DropOff_Longitude);

                //for Dropoff Address
                JSONObject jsonDropoffAddress = new JSONObject();
                jsonDropoffAddress.put("AddressId", DropOff_Address_Id);
                jsonDropoffAddress.put("Address1", DropOff_Address1);
                jsonDropoffAddress.put("Address2", "");
                jsonDropoffAddress.put("City", DropOff_City);
                jsonDropoffAddress.put("Country", DropOff_Country);
                jsonDropoffAddress.put("Location", jsonDropOffLocation);
                jsonDropoffAddress.put("State", DropOff_State);
                jsonDropoffAddress.put("ZipPostalCode", DropOff_ZipPostalCode);

                JSONArray jsonCompletePickup= new JSONArray();
                jsonCompletePickup.put(jsonPickupAddress);
                JSONArray jsonCompleteDropOff= new JSONArray();
                jsonCompleteDropOff.put(jsonDropoffAddress);

                //for Service Level
                String apiServiceLevel = null;
                apiServiceLevel = UrlBuilder.createApiUrl("core",PARTNER_SERVICELEVEL);
                JSONObject jsonServiceLevel = new JSONObject();
                jsonServiceLevel.put("DropoffLocation", jsonDropOffLocation);
                jsonServiceLevel.put("NoOfDrivers",No_of_Driver);
                jsonServiceLevel.put("PickupLocation",jsonPickupLocation);
                jsonServiceLevel.put("PricingModelConfigVersionRef",PricingModelConfigVersionRef);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonServiceLevel.toString()).when().post(apiServiceLevel);//body(jsonObj.toString()).


                //final main json for request payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupAddress", jsonPickupAddress);
                jsonObj.put("DropOffAddress", jsonDropoffAddress);
                jsonObj.put("DeliveryDateTime", nextAvailableBungii[0]);
                jsonObj.put("EstLoadUnloadTimeInMilliseconds", 0);
                jsonObj.put("IsScheduledPickup", true);
                jsonObj.put("PricingModelConfigVersionRef", PricingModelConfigVersionRef);
                jsonObj.put("PickupRequestID",JSONObject.NULL);
                jsonObj.put("ServiceLevelRef", ServiceLevelRef);
                jsonObj.put("NoOfDrivers",No_of_Driver);
                jsonObj.put("BusinessPartnerDefaultAddressRef", BusinessPartnerDefaultAddressRef);
                jsonObj.put("BusinessPartnerDefaultAddressConfigVersionID",BusinessPartnerDefaultAddressConfigVersionID);

                //Header header = new Header("AuthorizationToken", AccessToken);
                response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);//body(jsonObj.toString()).
//            response.then().log().body();
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);
            }

        }

        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response, RequestText);
        return jsonPathEvaluator.get("PickupRequestID");
            //return response;

    }

    public String partnerPickupDetails(String PickupRequest){
        String RequestText ="API REQUEST : Partner PickupDetails(Get) |PICKUP REQUEST  : "+ PickupRequest;
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("reporting",PARTNER_PICKUPDETAILS+PickupRequest);
        String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

        Response response = ApiHelper.givenPartnerAccess(AccessToken).when().get(apiURL);
        //response.then().log().all();

        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response, RequestText);
        return jsonPathEvaluator.get("AccessToken");
        //return response;

    }

    public String partnerDeliveryInformation(String PickupRequest,String Partner_Portal){
        String RequestText ="API REQUEST : Partner PickupDetails(PATCH) |PICKUP REQUEST  : "+ PickupRequest;
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("core",PARTNER_DELIVERYINFOMATION);
        String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");
        String Partner_Customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String Partner_Customer_Phone1 = (String) cucumberContextManager.getScenarioContext("Phone");
        ArrayList<String> Partner_Customer_Phone = new ArrayList<String>();
        Partner_Customer_Phone.add(Partner_Customer_Phone1);
        String No_Of_Driver = (String) cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");

        if(Partner_Portal.equalsIgnoreCase("MRFM")) {

            JSONObject jsonFiled1 = new JSONObject();
            jsonFiled1.put("FieldRef", "f2bd9004-6757-11ea-a4a3-00155d0a8706");
            jsonFiled1.put("FieldValue", "Test Pickup");

            //Pickup contact
            JSONObject jsonFiled2 = new JSONObject();
            jsonFiled2.put("FieldRef", "f2bd908c-6757-11ea-a4a3-00155d0a8706");
            jsonFiled2.put("FieldValue", "3456765435");

            //Drop Name
            JSONObject jsonFiled3 = new JSONObject();
            jsonFiled3.put("FieldRef", "f2bd90b3-6757-11ea-a4a3-00155d0a8706");
            jsonFiled3.put("FieldValue", "Test Drop");

            //Drop Contact
            JSONObject jsonFiled4 = new JSONObject();
            jsonFiled4.put("FieldRef", "f2bd90d3-6757-11ea-a4a3-00155d0a8706");
            jsonFiled4.put("FieldValue", "4567898765");

            //Drop Contact
            JSONObject jsonFiled5 = new JSONObject();
            jsonFiled5.put("FieldRef", "f2bd91b2-6757-11ea-a4a3-00155d0a8706");
            jsonFiled5.put("FieldValue", "RN01");

            JSONArray SF = new JSONArray();
            SF.put(jsonFiled1);
            SF.put(jsonFiled2);
            SF.put(jsonFiled3);
            SF.put(jsonFiled4);
            SF.put(jsonFiled5);

            //JSONObject jsonStaticFields = new JSONObject();
            //jsonStaticFields.put("StaticFields",SF);
            JSONArray CF = new JSONArray();
            JSONArray ITD = new JSONArray();


            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupRequestID", PickupRequest);
            jsonObj.put("CustomerName", Partner_Customer);
            jsonObj.put("CustomerMobile", Partner_Customer_Phone);
            jsonObj.put("PickupNote", "Test Automation Script");
            jsonObj.put("SpecialInstructions", "SPL from QA script");
            jsonObj.put("StaticFields", SF);
            jsonObj.put("CustomFields", CF);
            jsonObj.put("PaymentOption", "CC");
            jsonObj.put("PaymentMethodNonce", "fake-valid-nonce");
            jsonObj.put("ItemsToDeliver", ITD);
            Response response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().patch(apiURL);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);
        }

        else if(Partner_Portal.equalsIgnoreCase("Floor and Decor") || Partner_Portal.equalsIgnoreCase("Floor and Decor - Different Weights")) {

                String dimensionsItemOne = PropertyUtility.getDataProperties("partner.washingtondc.dimensions.item.one");
                String nameItemOne = PropertyUtility.getDataProperties("partner.washingtondc.name.item.one");
                String weightItemOne = PropertyUtility.getDataProperties("partner.washingtondc.weight.item.one");
                String dimensionsItemTwo = PropertyUtility.getDataProperties("partner.washingtondc.dimensions.item.two");
                String nameItemTwo = PropertyUtility.getDataProperties("partner.washingtondc.name.item.two");
                String weightItemTwo = PropertyUtility.getDataProperties("partner.washingtondc.weight.item.two");

                //customer name
                JSONArray customFields = new JSONArray();
                JSONObject field1 = new JSONObject();
                field1.put("FieldRef", "8b0c893b-0be2-11ec-a1b2-0280ec37d420");
                field1.put("FieldValue", "Test");
                customFields.put(field1);

                JSONArray ItemsToDeliver = new JSONArray();
                if(Partner_Portal.equalsIgnoreCase("Floor and Decor - Different Weights")){
                    JSONObject firstDeliverables = new JSONObject();
                    JSONObject secondDeliverables = new JSONObject();
                    firstDeliverables.put("Dimensions",dimensionsItemOne);
                    firstDeliverables.put("ID", "1");
                    firstDeliverables.put("Name",nameItemOne);
                    firstDeliverables.put("Weight",weightItemOne);
                    ItemsToDeliver.put(firstDeliverables);
                    secondDeliverables.put("Dimensions",dimensionsItemTwo);
                    secondDeliverables.put("ID", "2");
                    secondDeliverables.put("Name",nameItemTwo);
                    secondDeliverables.put("Weight",weightItemTwo);
                    ItemsToDeliver.put(secondDeliverables);
                }
                else {
                    if (No_Of_Driver.equalsIgnoreCase("1")) {
                        //items details for solo
                        JSONObject deliverables = new JSONObject();
                        deliverables.put("Dimensions",dimensionsItemOne);
                        deliverables.put("ID", "1");
                        deliverables.put("Name",nameItemOne);
                        deliverables.put("Weight",weightItemOne);
                        ItemsToDeliver.put(deliverables);
                    } else {
                        //items details for duo
                        JSONObject firstDeliverables = new JSONObject();
                        JSONObject secondDeliverables = new JSONObject();
                        firstDeliverables.put("Dimensions",dimensionsItemOne);
                        firstDeliverables.put("ID", "1");
                        firstDeliverables.put("Name",nameItemOne);
                        firstDeliverables.put("Weight",weightItemOne);
                        ItemsToDeliver.put(firstDeliverables);
                        secondDeliverables.put("Dimensions",dimensionsItemTwo);
                        secondDeliverables.put("ID", "2");
                        secondDeliverables.put("Name",nameItemTwo);
                        secondDeliverables.put("Weight",weightItemOne);
                        ItemsToDeliver.put(secondDeliverables);
                    }
                }

                //static fields
                JSONArray staticFields = new JSONArray();

                JSONObject field2 = new JSONObject();
                field2.put("FieldRef", "f2bd9004-6757-11ea-a4a3-00155d0a8706");
                field2.put("FieldValue", "Tester");

                JSONObject field3 = new JSONObject();
                field3.put("FieldRef", "f2bd908c-6757-11ea-a4a3-00155d0a8706");
                field3.put("FieldValue", "9999999106");

                JSONObject field4 = new JSONObject();
                field4.put("FieldRef", "f2bd90b3-6757-11ea-a4a3-00155d0a8706");
                field4.put("FieldValue", "NewCustomer");

                JSONObject field5 = new JSONObject();
                field5.put("FieldRef", "f2bd90d3-6757-11ea-a4a3-00155d0a8706");
                field5.put("FieldValue", "9999999108");

                JSONObject field6 = new JSONObject();
                field6.put("FieldRef", "f2bd9171-6757-11ea-a4a3-00155d0a8706");
                field6.put("FieldValue", "Education");

                JSONObject field7 = new JSONObject();
                field7.put("FieldRef", "f2bd91b2-6757-11ea-a4a3-00155d0a8706");
                field7.put("FieldValue", "A323");

                staticFields.put(field2);
                staticFields.put(field3);
                staticFields.put(field4);
                staticFields.put(field5);
                staticFields.put(field6);
                staticFields.put(field7);

                //main payload
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("PickupRequestID", PickupRequest);
                jsonObj.put("CustomerName", Partner_Customer);
                jsonObj.put("CustomFields", customFields);
                jsonObj.put("CustomerMobile", Partner_Customer_Phone);
                jsonObj.put("ItemsToDeliver", ItemsToDeliver);
                jsonObj.put("StaticFields", staticFields);
                jsonObj.put("PaymentMethodNonce", JSONObject.NULL);
                jsonObj.put("PickupNote", JSONObject.NULL);
                jsonObj.put("PaymentOption", "MI");
                jsonObj.put("SpecialInstructions", "SPL from QA script");

                Response response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().patch(apiURL);
                JsonPath jsonPathEvaluator = response.jsonPath();
                ApiHelper.genericResponseValidation(response, RequestText);

        }

        else if(Partner_Portal.equalsIgnoreCase("Cort Furniture")) {

            //customer name
            JSONArray customFields = new JSONArray();


            JSONArray ItemsToDeliver = new JSONArray();


            //static fields
            JSONArray staticFields = new JSONArray();

            JSONObject field2 = new JSONObject();
            field2.put("FieldRef", "f2bd9004-6757-11ea-a4a3-00155d0a8706");
            field2.put("FieldValue", "Test");

            JSONObject field3 = new JSONObject();
            field3.put("FieldRef", "f2bd908c-6757-11ea-a4a3-00155d0a8706");
            field3.put("FieldValue", "3213213210");

            JSONObject field4 = new JSONObject();
            field4.put("FieldRef", "f2bd90b3-6757-11ea-a4a3-00155d0a8706");
            field4.put("FieldValue", "");

            JSONObject field5 = new JSONObject();
            field5.put("FieldRef", "f2bd90d3-6757-11ea-a4a3-00155d0a8706");
            field5.put("FieldValue", "SVC02/307/03");

            JSONObject field6 = new JSONObject();
            field6.put("FieldRef", "f2bd9171-6757-11ea-a4a3-00155d0a8706");
            field6.put("FieldValue", "Today");

;

            staticFields.put(field2);
            staticFields.put(field3);
            staticFields.put(field4);
            staticFields.put(field5);
            staticFields.put(field6);


            //main payload
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupRequestID", PickupRequest);
            jsonObj.put("CustomerName", Partner_Customer);
            jsonObj.put("CustomFields", customFields);
            jsonObj.put("CustomerMobile", Partner_Customer_Phone);
            jsonObj.put("ItemsToDeliver", ItemsToDeliver);
            jsonObj.put("StaticFields", staticFields);
            jsonObj.put("PaymentMethodNonce", JSONObject.NULL);
            jsonObj.put("PickupNote", "Furniture");
            jsonObj.put("PaymentOption", "CC");
            jsonObj.put("PaymentMethodNonce", "fake-valid-nonce");
            jsonObj.put("SpecialInstructions", "SPL from QA script");

            Response response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().patch(apiURL);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);

        }
        else if(Partner_Portal.equalsIgnoreCase("BestBuy2 service level")) {
            String dropOffContactName =PropertyUtility.getDataProperties("best.buy11.partner.portal.dropOff.contact.name");
//            //customer name
            JSONArray customFields = new JSONArray();


            JSONArray ItemsToDeliver = new JSONArray();
            if(No_Of_Driver=="1"){
                //items details for solo
                JSONObject deliverables = new JSONObject();
                deliverables.put("Depth", "18 inches");
                deliverables.put("Height",  "24 inches");
                deliverables.put("Name", "Insignia™ - TV Stand for Most Flat-Panel TVs Up to 60\" - Mocha");
                deliverables.put("Quantity", "1");
                deliverables.put("Width", "54 inches");
                deliverables.put("SKU", "5067400");
                ItemsToDeliver.put(deliverables);
            }
            else{
                //items details for duo
                JSONObject firstDeliverables = new JSONObject();
                JSONObject secondDeliverables = new JSONObject();
                secondDeliverables.put("Depth", JSONObject.NULL);
                secondDeliverables.put("Height", JSONObject.NULL);
                secondDeliverables.put("Name", "Samsung - 75” Class QN85B Neo QLED 4K Smart Tizen TV");
                secondDeliverables.put("Quantity", "1");
                firstDeliverables.put("Width", "65.7 inches");
                firstDeliverables.put("SKU", "6500302");
                ItemsToDeliver.put(secondDeliverables);
            }


            //static fields
            JSONArray staticFields = new JSONArray();

            JSONObject field2 = new JSONObject();
            field2.put("FieldRef", "f2bd9004-6757-11ea-a4a3-00155d0a8706");
            field2.put("FieldValue", "Best Buy Customer Service");

            JSONObject field3 = new JSONObject();
            field3.put("FieldRef", "f2bd908c-6757-11ea-a4a3-00155d0a8706");
            field3.put("FieldValue", "9923261261");

            JSONObject field4 = new JSONObject();
            field4.put("FieldRef", "f2bd90b3-6757-11ea-a4a3-00155d0a8706");
            field4.put("FieldValue", dropOffContactName );

            JSONObject field5 = new JSONObject();
            field5.put("FieldRef", "f2bd90d3-6757-11ea-a4a3-00155d0a8706");
            field5.put("FieldValue", "9998881112");

            JSONObject field6 = new JSONObject();
            field6.put("FieldRef", "f2bd9171-6757-11ea-a4a3-00155d0a8706");
            field6.put("FieldValue", "01");

            JSONObject field7 = new JSONObject();
            field7.put("FieldRef", "f2bd91b2-6757-11ea-a4a3-00155d0a8706");
            field7.put("FieldValue", "Krishna");

            cucumberContextManager.setScenarioContext("DROPOFFCONTACTNAME",field4.get("FieldValue"));


            staticFields.put(field2);
            staticFields.put(field3);
            staticFields.put(field4);
            staticFields.put(field5);
            staticFields.put(field6);
            staticFields.put(field7);

            //main payload
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupRequestID", PickupRequest);
            jsonObj.put("CustomerName", Partner_Customer);
            jsonObj.put("CustomFields", customFields);
            jsonObj.put("CustomerMobile", Partner_Customer_Phone);
            jsonObj.put("ItemsToDeliver", ItemsToDeliver);
            jsonObj.put("StaticFields", staticFields);
            jsonObj.put("PaymentMethodNonce", JSONObject.NULL);
            jsonObj.put("PickupNote", JSONObject.NULL);
            jsonObj.put("PaymentOption", "MI");
            jsonObj.put("SpecialInstructions", "SPL from QA script");

            Response response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().patch(apiURL);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);

        }
        else if(Partner_Portal.equalsIgnoreCase("Biglots")) {
            //customer name
            JSONArray customFields = new JSONArray();
            JSONArray itemsToDeliver = new JSONArray();
            JSONArray staticFields = new JSONArray();

            JSONObject field1 = new JSONObject();
            field1.put("FieldRef", "f2bd9004-6757-11ea-a4a3-00155d0a8706");
            field1.put("FieldValue", "Tester");

            JSONObject field2 = new JSONObject();
            field2.put("FieldRef", "f2bd908c-6757-11ea-a4a3-00155d0a8706");
            field2.put("FieldValue", "9999999999");

            staticFields.put(field1);
            staticFields.put(field2);

            //main payload
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupRequestID", PickupRequest);
            jsonObj.put("CustomerName", Partner_Customer);
            jsonObj.put("CustomFields", customFields);
            jsonObj.put("CustomerMobile", Partner_Customer_Phone);
            jsonObj.put("ItemsToDeliver", itemsToDeliver);
            jsonObj.put("StaticFields", staticFields);
            jsonObj.put("PaymentOption", "CC");
            jsonObj.put("PaymentMethodNonce", "fake-valid-nonce");
            jsonObj.put("PickupNote","Books");
            jsonObj.put("SpecialInstructions", "SPL from QA script");

            Response response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().patch(apiURL);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);
        }
        else if(Partner_Portal.equalsIgnoreCase("Tile Shop")) {

            String dimensionsItemOne = PropertyUtility.getDataProperties("partner.tileshop.dimensions.item.one");
            String nameItemOne = PropertyUtility.getDataProperties("partner.tileshop.name.item.one");
            String weightItemOne = PropertyUtility.getDataProperties("partner.tileshop.weight.item.one");
            String dimensionsItemTwo = PropertyUtility.getDataProperties("partner.tileshop.dimensions.item.two");
            String nameItemTwo = PropertyUtility.getDataProperties("partner.tileshop.name.item.two");
            String weightItemTwo = PropertyUtility.getDataProperties("partner.tileshop.weight.item.two");

            //customer name
            JSONArray customFields = new JSONArray();


            JSONArray ItemsToDeliver = new JSONArray();
                if (No_Of_Driver.equalsIgnoreCase("1")) {
                    //items details for solo
                    JSONObject deliverables = new JSONObject();
                    deliverables.put("Dimensions",dimensionsItemOne);
                    deliverables.put("ID", "1");
                    deliverables.put("Name",nameItemOne);
                    deliverables.put("Weight",weightItemOne);
                    ItemsToDeliver.put(deliverables);
                } else {
                    //items details for duo
                    JSONObject firstDeliverables = new JSONObject();
                    JSONObject secondDeliverables = new JSONObject();
                    firstDeliverables.put("Dimensions",dimensionsItemOne);
                    firstDeliverables.put("ID", "1");
                    firstDeliverables.put("Name",nameItemOne);
                    firstDeliverables.put("Weight",weightItemOne);
                    ItemsToDeliver.put(firstDeliverables);
                    secondDeliverables.put("Dimensions",dimensionsItemTwo);
                    secondDeliverables.put("ID", "2");
                    secondDeliverables.put("Name",nameItemTwo);
                    secondDeliverables.put("Weight",weightItemTwo);
                    ItemsToDeliver.put(secondDeliverables);
                }


            //static fields
            JSONArray staticFields = new JSONArray();

            JSONObject field2 = new JSONObject();
            field2.put("FieldRef", "f2bd9004-6757-11ea-a4a3-00155d0a8706");
            field2.put("FieldValue", "");

            JSONObject field3 = new JSONObject();
            field3.put("FieldRef", "f2bd908c-6757-11ea-a4a3-00155d0a8706");
            field3.put("FieldValue", "");

            JSONObject field4 = new JSONObject();
            field4.put("FieldRef", "f2bd90b3-6757-11ea-a4a3-00155d0a8706");
            field4.put("FieldValue", "");

            JSONObject field5 = new JSONObject();
            field5.put("FieldRef", "f2bd90d3-6757-11ea-a4a3-00155d0a8706");
            field5.put("FieldValue", "");

            JSONObject field6 = new JSONObject();
            field6.put("FieldRef", "f2bd9150-6757-11ea-a4a3-00155d0a8706");
            field6.put("FieldValue", "12345");

            JSONObject field7 = new JSONObject();
            field7.put("FieldRef", "f2bd9171-6757-11ea-a4a3-00155d0a8706");
            field7.put("FieldValue", "money");

            JSONObject field8 = new JSONObject();
            field8.put("FieldRef", "f2bd91b2-6757-11ea-a4a3-00155d0a8706");
            field8.put("FieldValue", "0001");

            JSONObject field9 = new JSONObject();
            field9.put("FieldRef", "f2bd91d1-6757-11ea-a4a3-00155d0a8706");
            field9.put("FieldValue", "Today");

            staticFields.put(field2);
            staticFields.put(field3);
            staticFields.put(field4);
            staticFields.put(field5);
            staticFields.put(field6);
            staticFields.put(field7);
            staticFields.put(field8);
            staticFields.put(field9);

            //main payload
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupRequestID", PickupRequest);
            jsonObj.put("CustomerName", Partner_Customer);
            jsonObj.put("CustomFields", customFields);
            jsonObj.put("CustomerMobile", Partner_Customer_Phone);
            jsonObj.put("ItemsToDeliver", ItemsToDeliver);
            jsonObj.put("StaticFields", staticFields);
            jsonObj.put("PaymentMethodNonce", JSONObject.NULL);
            jsonObj.put("PickupNote", "chair");
            jsonObj.put("PaymentOption", "MI");
            jsonObj.put("SpecialInstructions", "SPL from QA script");

            Response response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().patch(apiURL);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);

        }
        else if(Partner_Portal.equalsIgnoreCase("Equip-bid")) {

            //customer name
            JSONArray customFields = new JSONArray();

           JSONArray ItemsToDeliver = new JSONArray();


            //static fields
            JSONArray staticFields = new JSONArray();

            JSONObject field2 = new JSONObject();
            field2.put("FieldRef", "f2bd9004-6757-11ea-a4a3-00155d0a8706");
            field2.put("FieldValue", "");

            JSONObject field3 = new JSONObject();
            field3.put("FieldRef", "f2bd908c-6757-11ea-a4a3-00155d0a8706");
            field3.put("FieldValue", "");

            JSONObject field4 = new JSONObject();
            field4.put("FieldRef", "f2bd90b3-6757-11ea-a4a3-00155d0a8706");
            field4.put("FieldValue", "");

            JSONObject field5 = new JSONObject();
            field5.put("FieldRef", "f2bd90d3-6757-11ea-a4a3-00155d0a8706");
            field5.put("FieldValue", "");

            JSONObject field6 = new JSONObject();
            field6.put("FieldRef", "f2bd9150-6757-11ea-a4a3-00155d0a8706");
            field6.put("FieldValue", "001");

            JSONObject field7 = new JSONObject();
            field7.put("FieldRef", "f2bd9171-6757-11ea-a4a3-00155d0a8706");
            field7.put("FieldValue", "Money");

            JSONObject field8 = new JSONObject();
            field8.put("FieldRef", "f2bd91b2-6757-11ea-a4a3-00155d0a8706");
            field8.put("FieldValue", "01");


            staticFields.put(field2);
            staticFields.put(field3);
            staticFields.put(field4);
            staticFields.put(field5);
            staticFields.put(field6);
            staticFields.put(field7);
            staticFields.put(field8);;

            //main payload
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupRequestID", PickupRequest);
            jsonObj.put("CustomerName", Partner_Customer);
            jsonObj.put("CustomFields", customFields);
            jsonObj.put("CustomerMobile", Partner_Customer_Phone);
            jsonObj.put("ItemsToDeliver", ItemsToDeliver);
            jsonObj.put("StaticFields", staticFields);
            jsonObj.put("PaymentMethodNonce", JSONObject.NULL);
            jsonObj.put("PickupNote", JSONObject.NULL);
            jsonObj.put("PaymentOption", "CC");
            jsonObj.put("PaymentMethodNonce", "fake-valid-nonce");
            jsonObj.put("SpecialInstructions", "SPL from QA script");

            Response response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().patch(apiURL);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);

        }
        else if(Partner_Portal.equalsIgnoreCase("Floor and Decor 106")|| Partner_Portal.equalsIgnoreCase("Floor and Decor bos")) {
            String dimensionsItemOne = PropertyUtility.getDataProperties("partner.floor.and.decor.106.dimensions.item.one");
            String nameItemOne = PropertyUtility.getDataProperties("partner.floor.and.decor.106.name.item.one");
            String weightItemOne = PropertyUtility.getDataProperties("partner.floor.and.decor.106.weight.item.one");
            String dimensionsItemTwo = PropertyUtility.getDataProperties("partner.floor.and.decor.106.dimensions.item.two");
            String nameItemTwo = PropertyUtility.getDataProperties("partner.floor.and.decor.106.name.item.two");
            String weightItemTwo = PropertyUtility.getDataProperties("partner.floor.and.decor.106.weight.item.two");
            //customer name
            JSONArray customFields = new JSONArray();
            JSONObject field1 = new JSONObject();
            field1.put("FieldRef", "8b0c893b-0be2-11ec-a1b2-0280ec37d420");
            field1.put("FieldValue", "Today");
            customFields.put(field1);

            cucumberContextManager.setScenarioContext("ItemsToDeliver",nameItemOne);
            cucumberContextManager.setScenarioContext("ScheduleBY",customFields.getJSONObject(0).get("FieldValue"));

            JSONArray ItemsToDeliver = new JSONArray();
            if (No_Of_Driver.equalsIgnoreCase("1")) {
                //items details for solo
                JSONObject deliverables = new JSONObject();
                deliverables.put("Dimensions",dimensionsItemOne);
                deliverables.put("ID", "1");
                deliverables.put("Name",nameItemOne);
                deliverables.put("Weight",weightItemOne);
                ItemsToDeliver.put(deliverables);
            } else {
                //items details for duo
                JSONObject firstDeliverables = new JSONObject();
                JSONObject secondDeliverables = new JSONObject();
                firstDeliverables.put("Dimensions",dimensionsItemOne);
                firstDeliverables.put("ID", "1");
                firstDeliverables.put("Name",nameItemOne);
                firstDeliverables.put("Weight",weightItemOne);
                ItemsToDeliver.put(firstDeliverables);
                secondDeliverables.put("Dimensions",dimensionsItemTwo);
                secondDeliverables.put("ID", "2");
                secondDeliverables.put("Name",nameItemTwo);
                secondDeliverables.put("Weight",weightItemTwo);
                ItemsToDeliver.put(secondDeliverables);
            }

            //static fields
            JSONArray staticFields = new JSONArray();

            JSONObject field2 = new JSONObject();
            field2.put("FieldRef", "f2bd9004-6757-11ea-a4a3-00155d0a8706");
            field2.put("FieldValue", "");

            JSONObject field3 = new JSONObject();
            field3.put("FieldRef", "f2bd908c-6757-11ea-a4a3-00155d0a8706");
            field3.put("FieldValue", "");

            JSONObject field4 = new JSONObject();
            field4.put("FieldRef", "f2bd90b3-6757-11ea-a4a3-00155d0a8706");
            field4.put("FieldValue", "");

            JSONObject field5 = new JSONObject();
            field5.put("FieldRef", "f2bd90d3-6757-11ea-a4a3-00155d0a8706");
            field5.put("FieldValue", "");

            JSONObject field6 = new JSONObject();
            field6.put("FieldRef", "f2bd9171-6757-11ea-a4a3-00155d0a8706");
            field6.put("FieldValue", "Money");

            JSONObject field7 = new JSONObject();
            field7.put("FieldRef", "f2bd91b2-6757-11ea-a4a3-00155d0a8706");
            field7.put("FieldValue", "135");

            staticFields.put(field2);
            staticFields.put(field3);
            staticFields.put(field4);
            staticFields.put(field5);
            staticFields.put(field6);
            staticFields.put(field7);

            cucumberContextManager.setScenarioContext("DeliveryPurpose",field6.get("FieldValue"));
            cucumberContextManager.setScenarioContext("RB/SB_Number",field7.get("FieldValue"));
            //main payload
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupRequestID", PickupRequest);
            jsonObj.put("CustomerName", Partner_Customer);
            jsonObj.put("CustomFields", customFields);
            jsonObj.put("CustomerMobile", Partner_Customer_Phone);
            jsonObj.put("ItemsToDeliver", ItemsToDeliver);
            jsonObj.put("StaticFields", staticFields);
            jsonObj.put("PaymentMethodNonce", JSONObject.NULL);
            jsonObj.put("PickupNote", JSONObject.NULL);
            jsonObj.put("PaymentOption", "MI");
            jsonObj.put("SpecialInstructions", "SPL from QA script");

            cucumberContextManager.setScenarioContext("SpecialInstruction",jsonObj.get("SpecialInstructions"));

            Response response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().patch(apiURL);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response, RequestText);

        }
        else {
            logger.detail("Please provide proper partner portal alias.");
        }

        //Header header = new Header("AuthorizationToken",);


        //response.then().log().all();


        return AccessToken;
        //return response;

    }

    public String partner_graphql(){
        String RequestText ="API REQUEST : Partner graphql braintree API)";
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("braintree",PARTNER_GRAPHQL);
        //String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");
        String Auth = PropertyUtility.getDataProperties("Braintree_Authorization");

        JSONObject clientSdkMetadata = new JSONObject();
        clientSdkMetadata.put("source","client");
        clientSdkMetadata.put("integration","dropin2");
        clientSdkMetadata.put("sessionId","126dfb9997-dedc-42a1-bef1-602376ff9b38");

        JSONObject billing_Address = new JSONObject();
        billing_Address.put("postalCode","xcs");

        JSONObject CCVar = new JSONObject();
        CCVar.put("number","4111111111111111");
        CCVar.put("expirationMonth","12");
        CCVar.put("expirationYear","2031");
        CCVar.put("cvv","123");
        CCVar.put("billingAddress",billing_Address);

        JSONObject Credit_card = new JSONObject();
        Credit_card.put("creditCard",CCVar);

        JSONObject Validate = new JSONObject();
        Validate.put("validate",false);

        JSONObject variables = new JSONObject();
        variables.put("input",Credit_card);
        variables.put("options",Validate);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("clientSdkMetadata",clientSdkMetadata);
        jsonObj.put("query", "mutation TokenizeCreditCard($input: TokenizeCreditCardInput!) {   tokenizeCreditCard(input: $input) {     token     creditCard {       bin       brandCode       last4       cardholderName       expirationMonth      expirationYear      binData {         prepaid         healthcare         debit         durbinRegulated         commercial         payroll         issuingBank         countryOfIssuance         productId       }     }   } }");
        jsonObj.put("variables",variables);
        jsonObj.put("operationName","TokenizeCreditCard");
        //Header header = new Header("AuthorizationToken",);


        //Response response = ApiHelper.givenPartnerAccess(Auth).when().get(apiURL);
        Response response = ApiHelper.givenPartnerBraintree(Auth).body(jsonObj.toString()).when().post(apiURL);
        //response.then().log().all();

        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response, RequestText);
        //String str = response.toString();
        return jsonPathEvaluator.get("data.tokenizeCreditCard.token");
        //return response;

    }

    public Response partnerConfirmPickup(String PickupRequest){
        String RequestText ="API REQUEST : Partner Confirm Pickup(Post) |PICKUP REQUEST  : "+ PickupRequest;
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("core",PARTNER_CONFIRM_PICKUP);
        String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("PickupRequestID",PickupRequest);

        Response response = ApiHelper.givenPartnerAccess(AccessToken).body(jsonObj.toString()).when().post(apiURL);
        //response.then().log().all();

        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response, RequestText);
        return response;
        //return response;

    }

}







