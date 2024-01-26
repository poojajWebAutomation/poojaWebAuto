package com.bungii.api.utilityFunctions;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.UrlBuilder;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bungii.common.manager.ResultManager.fail;
import static io.restassured.RestAssured.given;


public class WebPortal extends DriverBase {
    private static Cookies adminCookies,adminCookies2 ;
    private static String CUSTOMER_CANCELPICKUP = "/BungiiReports/CustomerCancelPickup";
    private static String CAN_EDIT_PICKUP = "/BungiiReports/CanEditPickup";
    private static String CALCULATE_COST = "/BungiiReports/CalculateCost";
    private static String MANUALLY_END = "/BungiiReports/ManuallyEndPickup";
    private static String SCHEDULED_DELIVERY = "/BungiiReports/ScheduledTrips";
    private static String LIVE_DELIVERY_DETAIL="/BungiiReports/TripDetails?tripRef=";


    private static LogUtility logger = new LogUtility(AuthServices.class);


    public Response AdminLogin() {
        logger.detail("API REQUEST : Admin Login " + PropertyUtility.getDataProperties("admin.user"));
        String loginURL = new GeneralUtility().GetAdminUrl();
        Response responseGet = given()
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .when().redirects().follow(false).
                        get(loginURL);
        String responseData = responseGet.asString();
        adminCookies = responseGet.then().extract().response().getDetailedCookies();
       // logger.detail("Admin Cookie : "+ adminCookies);
        String verificationToken = "";//responseGet.htmlPath().getString("html.body.span.input.@value");
        String csrfToken = ""; //responseGet.getCookie("__RequestVerificationToken");
        Pattern pattern = Pattern.compile("return '(.+?)'");
        Matcher matcher = pattern.matcher(responseData);
        if (matcher.find())
        {
            csrfToken =matcher.group(1);
           // logger.detail("CSRF Token : "+csrfToken) ;
        }
         pattern = Pattern.compile("__RequestVerificationToken\" type=\"hidden\" value=\"(.+?)\"");
         matcher = pattern.matcher(responseData);
        if (matcher.find())
        {
            verificationToken=matcher.group(1);
           // logger.detail("Verification Token POST Parameter : "+ verificationToken);
        }

        Response response = given().cookies(adminCookies).contentType("application/x-www-form-urlencoded; charset=UTF-8").urlEncodingEnabled(false)
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Upgrade-Insecure-Requests", "1")
                //.header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "gzip, deflate")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/81.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("__requestverificationtoken",csrfToken)
                .queryParams("__RequestVerificationToken",verificationToken,"PhoneNo",PropertyUtility.getDataProperties("admin.user"), "Password",PropertyUtility.getDataProperties("admin.password"))
                .when().redirects().follow(true).
                        post(loginURL);
        adminCookies2 = response.then().extract().response().getDetailedCookies();
        //logger.detail("Admin Cookie : "+adminCookies2);
        return response;
    }


    public void cancelScheduledBungii(String pickupRequestId) {
        logger.detail("API REQUEST : Cancel Scheduled Bungii " + pickupRequestId);
        String scheduledDelivery = UrlBuilder.createApiUrl("web core", SCHEDULED_DELIVERY);
        String responseGet = given().cookies(adminCookies).cookies(adminCookies2)
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .when().redirects().follow(false).
                        get(scheduledDelivery).asString();
        String verificationToken = "";//responseGet.htmlPath().getString("html.body.span.input.@value");
        String csrfToken = ""; //responseGet.getCookie("__RequestVerificationToken");
        Pattern pattern = Pattern.compile("return '(.+?')");
        Matcher matcher = pattern.matcher(responseGet);
        if (matcher.find())
        {
            csrfToken =matcher.group(1);
        }

        pattern = Pattern.compile("__RequestVerificationToken\" type=\"hidden\" value=\"(.+?)\"");
        matcher = pattern.matcher(responseGet);
        if (matcher.find())
        {
            verificationToken =matcher.group(1);
        }
        String cancelBungii = UrlBuilder.createApiUrl("web core", CUSTOMER_CANCELPICKUP);

        Response response = given().cookies(adminCookies).cookies(adminCookies2)
                .header("__requestverificationtoken",csrfToken)
                .formParams("PickupRequestID", pickupRequestId, "CancellationFee", "6", "CancelComments", "test","__RequestVerificationToken",verificationToken)
                .when().redirects().follow(false).
                        post(cancelBungii);
        // response.then().log().body();
    }
    public void unlockPartner(String partnerLocRef) {
        logger.detail("API REQUEST : Unlock Partner Portal " + partnerLocRef);
        String lockedPartner = UrlBuilder.createApiUrl("web core", "/Partner/UnlockPartners");
        Response responseGet = given().cookies(adminCookies).cookies(adminCookies2)
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Accept-Encoding", "gzip, deflate")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .when().redirects().follow(false).
                get(lockedPartner);
        String responseData= responseGet.asString();
        String csrfToken = "";
        Pattern pattern = Pattern.compile("return '(.+?)'");
        Matcher matcher = pattern.matcher(responseData);
        if (matcher.find())
        {
            csrfToken =matcher.group(1);
        }
        String unlockPartner = UrlBuilder.createApiUrl("web core", "/Partner/UnlockPartner");
        Response response = given().cookies(adminCookies).cookies(adminCookies2)
                .header("__requestverificationtoken",csrfToken)
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("cookies", adminCookies+";"+adminCookies2)
                .header("x-requested-with", "XMLHttpRequest")
                .formParams("PartnerLocationRef", partnerLocRef)
                .when().redirects().follow(false).
                post(unlockPartner);
    }
    public void cancelBungiiAsAdmin(String pickupRequestId) {
        AdminLogin();
        cancelScheduledBungii(pickupRequestId);
    }
    public void unlockPartnerAsAdmin() {
        AdminLogin();
        String partnerLocRef= PropertyUtility.getDataProperties("qa.fnd_service_level_partner.ref");
        unlockPartner(partnerLocRef);
    }

    public void asAdminManuallyEndBungii(String pickupRequestId) {
        AdminLogin();
        com.bungii.api.utilityFunctions.GeneralUtility utility = new com.bungii.api.utilityFunctions.GeneralUtility();
        String bungiiEndTime=utility.getBungiiEndTimeForManuallyEnd();
        String bungiiTimeZoneLabel=utility.getBungiiTimeZoneLanel();
        //canEditPickup(pickupRequestId);
        calculateManuallyEndCost(pickupRequestId,bungiiEndTime,bungiiTimeZoneLabel);
        calculateManuallyBungii(pickupRequestId,bungiiEndTime,bungiiTimeZoneLabel);
    }


    public void canEditPickup(String pickupRequestId) {
        logger.detail("API REQUEST : Edit Pickup " + pickupRequestId);
        String scheduledDelivery = UrlBuilder.createApiUrl("web core", SCHEDULED_DELIVERY);

        String responseGet = given().cookies(adminCookies).cookies(adminCookies2)
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .when().redirects().follow(false).
                        get(scheduledDelivery).asString();
        String verificationToken = "";//responseGet.htmlPath().getString("html.body.span.input.@value");
        String csrfToken = ""; //responseGet.getCookie("__RequestVerificationToken");
        Pattern pattern = Pattern.compile("return '(.+?)'");
        Matcher matcher = pattern.matcher(responseGet);
        if (matcher.find())
        {
            csrfToken =matcher.group(1);
        }


        String cancelBungii = UrlBuilder.createApiUrl("web core", CAN_EDIT_PICKUP);
        Response response = given().cookies(adminCookies).cookies(adminCookies2)
                .header("__requestverificationtoken",csrfToken)
                .param("pickupRequestID", pickupRequestId)
                .when().redirects().follow(false).
                        get(cancelBungii);
        JsonPath jsonPathEvaluator1 = response.jsonPath();
        boolean isSuccess = jsonPathEvaluator1.get("Success");
        if(!isSuccess)
            fail("I should able to get edit Bungii"+pickupRequestId, "I was not able to get edit Bungii  "+pickupRequestId);


    }

    public void calculateManuallyEndCost(String pickupRequestId,String bungiiEndTime,String bungiiTimeZoneLabel) {
        logger.detail("API REQUEST : Calculate Manually End Cost : " + pickupRequestId);

        String scheduledDelivery = UrlBuilder.createApiUrl("web core", LIVE_DELIVERY_DETAIL+pickupRequestId+"&isComplete=False&caller=1&userType=1");
        String responseGet = given().cookies(adminCookies).cookies(adminCookies2)
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .when().
                        get(scheduledDelivery).asString();

        String csrfToken = ""; //responseGet.getCookie("__RequestVerificationToken");
        Pattern pattern = Pattern.compile("return '(.+?)'");
        Matcher matcher = pattern.matcher(responseGet);
        if (matcher.find())
        {
            csrfToken =matcher.group(1);
        }
        String endBungii = UrlBuilder.createApiUrl("web core", CALCULATE_COST);
        Response response = given().cookies(adminCookies).cookies(adminCookies2)
                .header("__requestverificationtoken",csrfToken)
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                .param("PickupRequestID", pickupRequestId).param( "PickupEndTime", bungiiEndTime).param( "PickupTimeZone", bungiiTimeZoneLabel)
                .when().redirects().follow(false).
                        post(endBungii);
        JsonPath jsonPathEvaluator1 = response.jsonPath();
        boolean isSuccess = jsonPathEvaluator1.get("Success");
        String cost =jsonPathEvaluator1.get("Cost").toString();
        cucumberContextManager.setScenarioContext("ACTUAL_COST",cost);
        if(!isSuccess)
            fail("I should able to get Bungii Cost "+pickupRequestId, "I was not able to get Bungii Cost "+pickupRequestId +" : Cost "+ cost);
    }

    public void calculateManuallyBungii(String pickupRequestId,String bungiiEndTime,String bungiiTimeZoneLabel) {
        logger.detail("API REQUEST : Manually End Bungii : " + pickupRequestId);

       // String scheduledDelivery = UrlBuilder.createApiUrl("web core", SCHEDULED_DELIVERY);
        String scheduledDelivery = UrlBuilder.createApiUrl("web core", LIVE_DELIVERY_DETAIL+pickupRequestId+"&isComplete=False&caller=1&userType=1");
        String responseGet = given().cookies(adminCookies).cookies(adminCookies2)
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .when().
                        get(scheduledDelivery).asString();
        String verificationToken = "";//responseGet.htmlPath().getString("html.body.span.input.@value");
        String csrfToken = ""; //responseGet.getCookie("__RequestVerificationToken");
        Pattern pattern = Pattern.compile("return '(.+?)'");
        Matcher matcher = pattern.matcher(responseGet);
        if (matcher.find())
        {
            csrfToken =matcher.group(1);
        }

        String endBungii = UrlBuilder.createApiUrl("web core", MANUALLY_END);
        Response response = given().cookies(adminCookies).cookies(adminCookies2)
                .header("__requestverificationtoken",csrfToken)
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                .formParams("PickupRequestID", pickupRequestId, "PickupEndTime", bungiiEndTime, "PickupTimeZone", bungiiTimeZoneLabel)
                .when().redirects().follow(false).
                post(endBungii);
        JsonPath jsonPathEvaluator1 = response.jsonPath();
        boolean isSuccess = jsonPathEvaluator1.get("Success");
        if(!isSuccess)
            fail("I should able to end Bungii "+pickupRequestId, "I was not able to manually end Bungii"+pickupRequestId);

    }
}
