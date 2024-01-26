package com.bungii.api.utilityFunctions;

import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CustomerServices {
    private static String customer_PROFILE = "/api/customer/profile";
    private static LogUtility logger = new LogUtility(CustomerServices.class);


    public Response customerProfile(String authToken) {
        String RequestText="API REQUEST : Get Customer Profile by authToken " + authToken;
        String loginURL = null;
        loginURL = UrlBuilder.createApiUrl("customer", customer_PROFILE);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForCustomer(loginURL, header);
        ApiHelper.genericResponseValidation(response,RequestText);
        return response;
    }

    public String getCustomerRef(String authToken) {
        String RequestText="API REQUEST : Get Customer Reference by authToken " + authToken;
        Response response = customerProfile(authToken);
        ApiHelper.genericResponseValidation(response,RequestText);
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("CustomerProfile.CustomerRef");
    }

}
