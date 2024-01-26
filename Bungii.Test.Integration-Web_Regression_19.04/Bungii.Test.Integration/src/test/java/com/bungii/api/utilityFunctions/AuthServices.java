package com.bungii.api.utilityFunctions;

import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;

import java.util.Map;

public class AuthServices extends DriverBase {
    private static String CUST_LOGIN_ENDPOINT = "/api/customer/login";
    private static String DRIVER_LOGIN_ENDPOINT = "/api/driver/login";
    private static String BUSINESSPARTNER_LOGIN = "/api/businesspartner/login";
    private static String PARTNER_SETTINGS = "/api/partner/settings";
    private static LogUtility logger = new LogUtility(AuthServices.class);

    /**
     * Customer login
     * @param custPhoneCode Phone country code
     * @param custPhoneNum Phone number
     * @param custPassword Password
     * @return access token
     */
    public Response customerLogin(String custPhoneCode, String custPhoneNum, String custPassword) {
       // logger.detail("API REQUEST : Customer Login of " + custPhoneNum);
        String RequestText = "API REQUEST : Login Customer :  " + custPhoneNum;
        String token = "";
            Map<String, String> data = new HashedMap();
            data.put("PhoneCountryCode", custPhoneCode);
            data.put("Password", custPassword);
            data.put("PhoneNo", custPhoneNum);
            String loginURL = UrlBuilder.createApiUrl("cust_auth", CUST_LOGIN_ENDPOINT);
            Response response = ApiHelper.postDetailsForCustomer(loginURL, data);
            ApiHelper.genericResponseValidation(response,RequestText);
        return response;
    }
    //get customer access token
    public String getCustomerToken(String custPhoneCode, String custPhoneNum, String custPassword){
       // String RequestText = "API REQUEST : Get Customer Token of " + custPhoneNum;
        Response response=customerLogin( custPhoneCode, custPhoneNum, custPassword);
        //ApiHelper.genericResponseValidation(response,RequestText);

        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("AccessToken");
    }
    //Driver login
    public Response driverLogin(String driverPhoneCode, String driverPhoneNum, String driverPassword) {
        String RequestText = "API REQUEST : Login Driver : " + driverPhoneNum;
        Map<String, String> data = new HashedMap();
            data.put("PhoneCountryCode", driverPhoneCode);
            data.put("Password", driverPassword);
            data.put("PhoneNo", driverPhoneNum);
            String loginURL = UrlBuilder.createApiUrl("driver_auth", DRIVER_LOGIN_ENDPOINT);
            Response response= ApiHelper.postDetailsForDriver(loginURL, data);
            ApiHelper.genericResponseValidation(response, RequestText);
        return response;
    }
    //Get driver access token
    public String getDriverToken(String driverPhoneCode, String driverPhoneNum, String driverPassword){
       // String RequestText = "API REQUEST : Get Driver Token of " + driverPhoneNum;
        Response response=driverLogin( driverPhoneCode, driverPhoneNum, driverPassword);
       // ApiHelper.genericResponseValidation(response, RequestText);
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("AccessToken");
    }

    public String partnerLogin(String Partner_Portal){
        String RequestText ="API REQUEST : Partner Login(Post) |  : "+ Partner_Portal;
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("partner_auth",BUSINESSPARTNER_LOGIN);
        String Partner_Location_Reference = "";
        String partnerURL = "";
        cucumberContextManager.setScenarioContext("Partner_Portal_Name",Partner_Portal);

        if(Partner_Portal.equalsIgnoreCase("MRFM")){
            Partner_Location_Reference = PropertyUtility.getDataProperties("partner.location.reference.MRFM");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            partnerURL = PropertyUtility.getDataProperties("qa.partner.url");
            cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerURL);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        // floor and decor
        else if(Partner_Portal.equalsIgnoreCase("Floor and Decor") || Partner_Portal.equalsIgnoreCase("Floor and Decor - Different Weights")){
            Partner_Location_Reference= PropertyUtility.getDataProperties("qa.fnd_service_level_partner.ref");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            partnerURL = PropertyUtility.getDataProperties("qa.fnd_service_level_partner.url");
            cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerURL);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        //Cort
        else if(Partner_Portal.equalsIgnoreCase("Cort")){
            Partner_Location_Reference= PropertyUtility.getDataProperties("qa.cort_service_level_partner.ref");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            partnerURL = PropertyUtility.getDataProperties("qa.cort_service_level_partner.url");
            cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerURL);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        // bestbuy11
        else if(Partner_Portal.equalsIgnoreCase("BestBuy2 service level") || Partner_Portal.equalsIgnoreCase("BestBuy2 warehouse service level")){
            Partner_Location_Reference= PropertyUtility.getDataProperties("qa.bestbuy2.service_level_partner.ref");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            partnerURL = PropertyUtility.getDataProperties("qa.bestbuy2.service_level_partner.url");
            cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerURL);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        //biglots
        else if(Partner_Portal.equalsIgnoreCase("Biglots")){
            Partner_Location_Reference= PropertyUtility.getDataProperties("qa.service_level_partner.ref");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            partnerURL = PropertyUtility.getDataProperties("qa.service_level_partner.url");
            cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerURL);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        //Tile shop
        else if(Partner_Portal.equalsIgnoreCase("Tile Shop")){
            Partner_Location_Reference= PropertyUtility.getDataProperties("qa.tileshop.ref");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            partnerURL = PropertyUtility.getDataProperties("qa.tileshop.url");
            cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerURL);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        else if(Partner_Portal.equalsIgnoreCase("Equip-bid")){
            Partner_Location_Reference= PropertyUtility.getDataProperties("qa.equip-bid.ref");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            partnerURL = PropertyUtility.getDataProperties("qa.equip-bid.url");
            cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerURL);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        else if(Partner_Portal.equalsIgnoreCase("Cort Furniture")){
            Partner_Location_Reference= PropertyUtility.getDataProperties("qa.cort.service_level_partner.ref");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            partnerURL = PropertyUtility.getDataProperties("qa.cort.service_level_partner.url");
            cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerURL);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        else if(Partner_Portal.equalsIgnoreCase("Floor and Decor 106")){
            Partner_Location_Reference= PropertyUtility.getDataProperties("qa.floor.and.decor.106.ref");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            partnerURL = PropertyUtility.getDataProperties("qa.floor.and.decor.106.url");
            cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerURL);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        else if(Partner_Portal.equalsIgnoreCase("Floor and Decor bos")){
            Partner_Location_Reference= PropertyUtility.getDataProperties("qa.floor.and.decor.bos.ref");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            partnerURL = PropertyUtility.getDataProperties("qa.floor.and.decor.bos.url");
            cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerURL);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        else{
            logger.detail("Please provide proper partner portal alias.");
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("PartnerLocationReference", Partner_Location_Reference);
        String Pwd = PropertyUtility.getDataProperties("PartnerPassword");
        jsonObj.put("Password", Pwd);
        //Header header = new Header("AuthorizationToken",);

        Response response = ApiHelper.givenPartnerConfig().body(jsonObj.toString()).when().post(apiURL);
        //response.then().log().all();

        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response, RequestText);
        return jsonPathEvaluator.get("AccessToken");
        //return response;

    }

    public String[] partnerSettings(String Auth_Token){
        String RequestText ="API REQUEST : Partner Settings(GET) |  : for Authorization_Token:- "+ Auth_Token;
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("partner",PARTNER_SETTINGS);

        //JSONObject jsonObj = new JSONObject();
        //jsonObj.put("authorizationtoken", Auth_Token);
        //Header header = new Header("AuthorizationToken",);

        Response response = ApiHelper.givenPartnerAccess(Auth_Token).when().get(apiURL);
        //response.then().log().all();

        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response, RequestText);

        String portalName= (String) cucumberContextManager.getScenarioContext("Portal_Name");
        if (portalName.equalsIgnoreCase("Biglots")){
            return null;
        }else {
            String[] businessPartnerDefaultAddressRef = {jsonPathEvaluator.get("PartnerLocationSettings.DefaultPickupLocationInfo.Address.BusinessPartnerDefaultAddressRef[0]").toString(),jsonPathEvaluator.get("PartnerLocationSettings.DefaultPickupLocationInfo.Address.BusinessPartnerDefaultAddressConfigVersionID[0]").toString()};
            return businessPartnerDefaultAddressRef;
        }
        //return response;

    }
}
